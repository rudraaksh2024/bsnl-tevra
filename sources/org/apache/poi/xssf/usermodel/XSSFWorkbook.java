package org.apache.poi.xssf.usermodel;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Spliterator;
import java.util.regex.Pattern;
import org.apache.commons.collections4.ListValuedMap;
import org.apache.commons.collections4.multimap.ArrayListValuedHashMap;
import org.apache.commons.io.output.UnsynchronizedByteArrayOutputStream;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ooxml.POIXMLDocument;
import org.apache.poi.ooxml.POIXMLDocumentPart;
import org.apache.poi.ooxml.POIXMLException;
import org.apache.poi.ooxml.util.PackageHelper;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.exceptions.OpenXML4JException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.openxml4j.opc.PackagePart;
import org.apache.poi.openxml4j.opc.PackagePartName;
import org.apache.poi.openxml4j.opc.PackageRelationship;
import org.apache.poi.openxml4j.opc.PackageRelationshipTypes;
import org.apache.poi.openxml4j.opc.PackagingURIHelper;
import org.apache.poi.openxml4j.opc.TargetMode;
import org.apache.poi.poifs.crypt.HashAlgorithm;
import org.apache.poi.ss.SpreadsheetVersion;
import org.apache.poi.ss.formula.SheetNameFormatter;
import org.apache.poi.ss.formula.udf.AggregatingUDFFinder;
import org.apache.poi.ss.formula.udf.IndexedUDFFinder;
import org.apache.poi.ss.formula.udf.UDFFinder;
import org.apache.poi.ss.usermodel.CellReferenceType;
import org.apache.poi.ss.usermodel.Date1904Support;
import org.apache.poi.ss.usermodel.Name;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.SheetVisibility;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.ss.util.WorkbookUtil;
import org.apache.poi.util.IOUtils;
import org.apache.poi.util.Internal;
import org.apache.poi.util.NotImplemented;
import org.apache.poi.util.Removal;
import org.apache.poi.xssf.XLSBUnsupportedException;
import org.apache.poi.xssf.model.CalculationChain;
import org.apache.poi.xssf.model.ExternalLinksTable;
import org.apache.poi.xssf.model.MapInfo;
import org.apache.poi.xssf.model.SharedStringsTable;
import org.apache.poi.xssf.model.StylesTable;
import org.apache.poi.xssf.model.ThemesTable;
import org.apache.poi.xssf.usermodel.helpers.XSSFFormulaUtils;
import org.apache.poi.xssf.usermodel.helpers.XSSFPasswordHelper;
import org.apache.xmlbeans.XmlObject;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTBookView;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCalcPr;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDefinedName;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDefinedNames;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDialogsheet;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotCache;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotCaches;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTSheet;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTSheets;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorkbook;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorkbookPr;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorkbookProtection;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.STCalcMode;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.STRefMode;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.STSheetState;

public class XSSFWorkbook extends POIXMLDocument implements Workbook, Date1904Support {
    private static final Pattern COMMA_PATTERN = Pattern.compile(",");
    private static final Logger LOG = LogManager.getLogger((Class<?>) XSSFWorkbook.class);
    public static final int PICTURE_TYPE_BMP = 11;
    public static final int PICTURE_TYPE_EPS = 10;
    public static final int PICTURE_TYPE_GIF = 8;
    public static final int PICTURE_TYPE_TIFF = 9;
    public static final int PICTURE_TYPE_WPG = 12;
    private XSSFCreationHelper _creationHelper;
    private Row.MissingCellPolicy _missingCellPolicy;
    private final IndexedUDFFinder _udfFinder;
    private CalculationChain calcChain;
    private boolean cellFormulaValidation;
    private List<ExternalLinksTable> externalLinks;
    private XSSFDataFormat formatter;
    private MapInfo mapInfo;
    private List<XSSFName> namedRanges;
    private ListValuedMap<String, XSSFName> namedRangesByName;
    private List<XSSFPictureData> pictures;
    private List<CTPivotCache> pivotCaches;
    private List<XSSFPivotTable> pivotTables;
    private SharedStringsTable sharedStringSource;
    /* access modifiers changed from: private */
    public List<XSSFSheet> sheets;
    private StylesTable stylesSource;
    private CTWorkbook workbook;
    private final XSSFFactory xssfFactory;

    public XSSFWorkbook() {
        this(XSSFWorkbookType.XLSX);
    }

    public XSSFWorkbook(XSSFFactory xSSFFactory) {
        this(XSSFWorkbookType.XLSX, xSSFFactory);
    }

    public XSSFWorkbook(XSSFWorkbookType xSSFWorkbookType) {
        this(xSSFWorkbookType, (XSSFFactory) null);
    }

    private XSSFWorkbook(XSSFWorkbookType xSSFWorkbookType, XSSFFactory xSSFFactory) {
        super(newPackage(xSSFWorkbookType));
        this._udfFinder = new IndexedUDFFinder(AggregatingUDFFinder.DEFAULT);
        this._missingCellPolicy = Row.MissingCellPolicy.RETURN_NULL_AND_BLANK;
        this.cellFormulaValidation = true;
        this.xssfFactory = xSSFFactory == null ? XSSFFactory.getInstance() : xSSFFactory;
        onWorkbookCreate();
    }

    public XSSFWorkbook(OPCPackage oPCPackage) throws IOException {
        super(oPCPackage);
        this._udfFinder = new IndexedUDFFinder(AggregatingUDFFinder.DEFAULT);
        this._missingCellPolicy = Row.MissingCellPolicy.RETURN_NULL_AND_BLANK;
        this.cellFormulaValidation = true;
        XSSFFactory instance = XSSFFactory.getInstance();
        this.xssfFactory = instance;
        beforeDocumentRead();
        load(instance);
        setBookViewsIfMissing();
    }

    public XSSFWorkbook(InputStream inputStream) throws IOException {
        this(inputStream, false);
    }

    private XSSFWorkbook(InputStream inputStream, boolean z) throws IOException {
        this(PackageHelper.open(inputStream, z));
    }

    public XSSFWorkbook(File file) throws IOException, InvalidFormatException {
        this(OPCPackage.open(file));
    }

    public XSSFWorkbook(String str) throws IOException {
        this(openPackage(str));
    }

    public XSSFWorkbook(PackagePart packagePart) throws IOException {
        this(packagePart.getInputStream(), true);
    }

    public XSSFFactory getXssfFactory() {
        return this.xssfFactory;
    }

    /* access modifiers changed from: protected */
    public void beforeDocumentRead() {
        if (!getCorePart().getContentType().equals(XSSFRelation.XLSB_BINARY_WORKBOOK.getContentType())) {
            this.pivotTables = new ArrayList();
            this.pivotCaches = new ArrayList();
            return;
        }
        throw new XLSBUnsupportedException();
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:70:0x0169, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:71:0x016a, code lost:
        if (r0 != null) goto L_0x016c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:73:?, code lost:
        r0.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:77:0x0174, code lost:
        throw r1;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onDocumentRead() throws java.io.IOException {
        /*
            r7 = this;
            org.apache.poi.openxml4j.opc.PackagePart r0 = r7.getPackagePart()     // Catch:{ XmlException -> 0x0175 }
            java.io.InputStream r0 = r0.getInputStream()     // Catch:{ XmlException -> 0x0175 }
            org.apache.xmlbeans.impl.schema.DocumentFactory<org.openxmlformats.schemas.spreadsheetml.x2006.main.WorkbookDocument> r1 = org.openxmlformats.schemas.spreadsheetml.x2006.main.WorkbookDocument.Factory     // Catch:{ all -> 0x0167 }
            org.apache.xmlbeans.XmlOptions r2 = org.apache.poi.ooxml.POIXMLTypeLoader.DEFAULT_XML_OPTIONS     // Catch:{ all -> 0x0167 }
            java.lang.Object r1 = r1.parse((java.io.InputStream) r0, (org.apache.xmlbeans.XmlOptions) r2)     // Catch:{ all -> 0x0167 }
            org.openxmlformats.schemas.spreadsheetml.x2006.main.WorkbookDocument r1 = (org.openxmlformats.schemas.spreadsheetml.x2006.main.WorkbookDocument) r1     // Catch:{ all -> 0x0167 }
            org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorkbook r1 = r1.getWorkbook()     // Catch:{ all -> 0x0167 }
            r7.workbook = r1     // Catch:{ all -> 0x0167 }
            if (r0 == 0) goto L_0x001d
            r0.close()     // Catch:{ XmlException -> 0x0175 }
        L_0x001d:
            java.util.HashMap r0 = new java.util.HashMap     // Catch:{ XmlException -> 0x0175 }
            r0.<init>()     // Catch:{ XmlException -> 0x0175 }
            java.util.HashMap r1 = new java.util.HashMap     // Catch:{ XmlException -> 0x0175 }
            r1.<init>()     // Catch:{ XmlException -> 0x0175 }
            java.util.List r2 = r7.getRelationParts()     // Catch:{ XmlException -> 0x0175 }
            java.util.Iterator r2 = r2.iterator()     // Catch:{ XmlException -> 0x0175 }
            r3 = 0
        L_0x0030:
            boolean r4 = r2.hasNext()     // Catch:{ XmlException -> 0x0175 }
            if (r4 == 0) goto L_0x0090
            java.lang.Object r4 = r2.next()     // Catch:{ XmlException -> 0x0175 }
            org.apache.poi.ooxml.POIXMLDocumentPart$RelationPart r4 = (org.apache.poi.ooxml.POIXMLDocumentPart.RelationPart) r4     // Catch:{ XmlException -> 0x0175 }
            org.apache.poi.ooxml.POIXMLDocumentPart r5 = r4.getDocumentPart()     // Catch:{ XmlException -> 0x0175 }
            boolean r6 = r5 instanceof org.apache.poi.xssf.model.SharedStringsTable     // Catch:{ XmlException -> 0x0175 }
            if (r6 == 0) goto L_0x0049
            org.apache.poi.xssf.model.SharedStringsTable r5 = (org.apache.poi.xssf.model.SharedStringsTable) r5     // Catch:{ XmlException -> 0x0175 }
            r7.sharedStringSource = r5     // Catch:{ XmlException -> 0x0175 }
            goto L_0x0030
        L_0x0049:
            boolean r6 = r5 instanceof org.apache.poi.xssf.model.StylesTable     // Catch:{ XmlException -> 0x0175 }
            if (r6 == 0) goto L_0x0052
            org.apache.poi.xssf.model.StylesTable r5 = (org.apache.poi.xssf.model.StylesTable) r5     // Catch:{ XmlException -> 0x0175 }
            r7.stylesSource = r5     // Catch:{ XmlException -> 0x0175 }
            goto L_0x0030
        L_0x0052:
            boolean r6 = r5 instanceof org.apache.poi.xssf.model.ThemesTable     // Catch:{ XmlException -> 0x0175 }
            if (r6 == 0) goto L_0x005a
            org.apache.poi.xssf.model.ThemesTable r5 = (org.apache.poi.xssf.model.ThemesTable) r5     // Catch:{ XmlException -> 0x0175 }
            r3 = r5
            goto L_0x0030
        L_0x005a:
            boolean r6 = r5 instanceof org.apache.poi.xssf.model.CalculationChain     // Catch:{ XmlException -> 0x0175 }
            if (r6 == 0) goto L_0x0063
            org.apache.poi.xssf.model.CalculationChain r5 = (org.apache.poi.xssf.model.CalculationChain) r5     // Catch:{ XmlException -> 0x0175 }
            r7.calcChain = r5     // Catch:{ XmlException -> 0x0175 }
            goto L_0x0030
        L_0x0063:
            boolean r6 = r5 instanceof org.apache.poi.xssf.model.MapInfo     // Catch:{ XmlException -> 0x0175 }
            if (r6 == 0) goto L_0x006c
            org.apache.poi.xssf.model.MapInfo r5 = (org.apache.poi.xssf.model.MapInfo) r5     // Catch:{ XmlException -> 0x0175 }
            r7.mapInfo = r5     // Catch:{ XmlException -> 0x0175 }
            goto L_0x0030
        L_0x006c:
            boolean r6 = r5 instanceof org.apache.poi.xssf.usermodel.XSSFSheet     // Catch:{ XmlException -> 0x0175 }
            if (r6 == 0) goto L_0x007e
            org.apache.poi.openxml4j.opc.PackageRelationship r4 = r4.getRelationship()     // Catch:{ XmlException -> 0x0175 }
            java.lang.String r4 = r4.getId()     // Catch:{ XmlException -> 0x0175 }
            org.apache.poi.xssf.usermodel.XSSFSheet r5 = (org.apache.poi.xssf.usermodel.XSSFSheet) r5     // Catch:{ XmlException -> 0x0175 }
            r0.put(r4, r5)     // Catch:{ XmlException -> 0x0175 }
            goto L_0x0030
        L_0x007e:
            boolean r6 = r5 instanceof org.apache.poi.xssf.model.ExternalLinksTable     // Catch:{ XmlException -> 0x0175 }
            if (r6 == 0) goto L_0x0030
            org.apache.poi.openxml4j.opc.PackageRelationship r4 = r4.getRelationship()     // Catch:{ XmlException -> 0x0175 }
            java.lang.String r4 = r4.getId()     // Catch:{ XmlException -> 0x0175 }
            org.apache.poi.xssf.model.ExternalLinksTable r5 = (org.apache.poi.xssf.model.ExternalLinksTable) r5     // Catch:{ XmlException -> 0x0175 }
            r1.put(r4, r5)     // Catch:{ XmlException -> 0x0175 }
            goto L_0x0030
        L_0x0090:
            org.apache.poi.openxml4j.opc.OPCPackage r2 = r7.getPackage()     // Catch:{ XmlException -> 0x0175 }
            org.apache.poi.openxml4j.opc.PackageAccess r2 = r2.getPackageAccess()     // Catch:{ XmlException -> 0x0175 }
            org.apache.poi.openxml4j.opc.PackageAccess r4 = org.apache.poi.openxml4j.opc.PackageAccess.READ     // Catch:{ XmlException -> 0x0175 }
            r5 = 0
            if (r2 != r4) goto L_0x009f
            r2 = 1
            goto L_0x00a0
        L_0x009f:
            r2 = r5
        L_0x00a0:
            org.apache.poi.xssf.model.StylesTable r4 = r7.stylesSource     // Catch:{ XmlException -> 0x0175 }
            if (r4 != 0) goto L_0x00ba
            if (r2 == 0) goto L_0x00ae
            org.apache.poi.xssf.model.StylesTable r4 = new org.apache.poi.xssf.model.StylesTable     // Catch:{ XmlException -> 0x0175 }
            r4.<init>()     // Catch:{ XmlException -> 0x0175 }
            r7.stylesSource = r4     // Catch:{ XmlException -> 0x0175 }
            goto L_0x00ba
        L_0x00ae:
            org.apache.poi.xssf.usermodel.XSSFRelation r4 = org.apache.poi.xssf.usermodel.XSSFRelation.STYLES     // Catch:{ XmlException -> 0x0175 }
            org.apache.poi.xssf.usermodel.XSSFFactory r6 = r7.xssfFactory     // Catch:{ XmlException -> 0x0175 }
            org.apache.poi.ooxml.POIXMLDocumentPart r4 = r7.createRelationship(r4, r6)     // Catch:{ XmlException -> 0x0175 }
            org.apache.poi.xssf.model.StylesTable r4 = (org.apache.poi.xssf.model.StylesTable) r4     // Catch:{ XmlException -> 0x0175 }
            r7.stylesSource = r4     // Catch:{ XmlException -> 0x0175 }
        L_0x00ba:
            org.apache.poi.xssf.model.StylesTable r4 = r7.stylesSource     // Catch:{ XmlException -> 0x0175 }
            r4.setWorkbook(r7)     // Catch:{ XmlException -> 0x0175 }
            org.apache.poi.xssf.model.StylesTable r4 = r7.stylesSource     // Catch:{ XmlException -> 0x0175 }
            r4.setTheme(r3)     // Catch:{ XmlException -> 0x0175 }
            org.apache.poi.xssf.model.SharedStringsTable r3 = r7.sharedStringSource     // Catch:{ XmlException -> 0x0175 }
            if (r3 != 0) goto L_0x00de
            if (r2 == 0) goto L_0x00d2
            org.apache.poi.xssf.model.SharedStringsTable r2 = new org.apache.poi.xssf.model.SharedStringsTable     // Catch:{ XmlException -> 0x0175 }
            r2.<init>()     // Catch:{ XmlException -> 0x0175 }
            r7.sharedStringSource = r2     // Catch:{ XmlException -> 0x0175 }
            goto L_0x00de
        L_0x00d2:
            org.apache.poi.xssf.usermodel.XSSFRelation r2 = org.apache.poi.xssf.usermodel.XSSFRelation.SHARED_STRINGS     // Catch:{ XmlException -> 0x0175 }
            org.apache.poi.xssf.usermodel.XSSFFactory r3 = r7.xssfFactory     // Catch:{ XmlException -> 0x0175 }
            org.apache.poi.ooxml.POIXMLDocumentPart r2 = r7.createRelationship(r2, r3)     // Catch:{ XmlException -> 0x0175 }
            org.apache.poi.xssf.model.SharedStringsTable r2 = (org.apache.poi.xssf.model.SharedStringsTable) r2     // Catch:{ XmlException -> 0x0175 }
            r7.sharedStringSource = r2     // Catch:{ XmlException -> 0x0175 }
        L_0x00de:
            java.util.ArrayList r2 = new java.util.ArrayList     // Catch:{ XmlException -> 0x0175 }
            int r3 = r0.size()     // Catch:{ XmlException -> 0x0175 }
            r2.<init>(r3)     // Catch:{ XmlException -> 0x0175 }
            r7.sheets = r2     // Catch:{ XmlException -> 0x0175 }
            org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorkbook r2 = r7.workbook     // Catch:{ XmlException -> 0x0175 }
            if (r2 == 0) goto L_0x015f
            org.openxmlformats.schemas.spreadsheetml.x2006.main.CTSheets r2 = r2.getSheets()     // Catch:{ XmlException -> 0x0175 }
            if (r2 == 0) goto L_0x015f
            org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorkbook r2 = r7.workbook     // Catch:{ XmlException -> 0x0175 }
            org.openxmlformats.schemas.spreadsheetml.x2006.main.CTSheets r2 = r2.getSheets()     // Catch:{ XmlException -> 0x0175 }
            org.openxmlformats.schemas.spreadsheetml.x2006.main.CTSheet[] r2 = r2.getSheetArray()     // Catch:{ XmlException -> 0x0175 }
            if (r2 == 0) goto L_0x015f
            org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorkbook r2 = r7.workbook     // Catch:{ XmlException -> 0x0175 }
            org.openxmlformats.schemas.spreadsheetml.x2006.main.CTSheets r2 = r2.getSheets()     // Catch:{ XmlException -> 0x0175 }
            org.openxmlformats.schemas.spreadsheetml.x2006.main.CTSheet[] r2 = r2.getSheetArray()     // Catch:{ XmlException -> 0x0175 }
            int r3 = r2.length     // Catch:{ XmlException -> 0x0175 }
            r4 = r5
        L_0x010b:
            if (r4 >= r3) goto L_0x0115
            r6 = r2[r4]     // Catch:{ XmlException -> 0x0175 }
            r7.parseSheet(r0, r6)     // Catch:{ XmlException -> 0x0175 }
            int r4 = r4 + 1
            goto L_0x010b
        L_0x0115:
            java.util.ArrayList r0 = new java.util.ArrayList     // Catch:{ XmlException -> 0x0175 }
            int r2 = r1.size()     // Catch:{ XmlException -> 0x0175 }
            r0.<init>(r2)     // Catch:{ XmlException -> 0x0175 }
            r7.externalLinks = r0     // Catch:{ XmlException -> 0x0175 }
            org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorkbook r0 = r7.workbook     // Catch:{ XmlException -> 0x0175 }
            boolean r0 = r0.isSetExternalReferences()     // Catch:{ XmlException -> 0x0175 }
            if (r0 == 0) goto L_0x015b
            org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorkbook r0 = r7.workbook     // Catch:{ XmlException -> 0x0175 }
            org.openxmlformats.schemas.spreadsheetml.x2006.main.CTExternalReferences r0 = r0.getExternalReferences()     // Catch:{ XmlException -> 0x0175 }
            org.openxmlformats.schemas.spreadsheetml.x2006.main.CTExternalReference[] r0 = r0.getExternalReferenceArray()     // Catch:{ XmlException -> 0x0175 }
            int r2 = r0.length     // Catch:{ XmlException -> 0x0175 }
        L_0x0133:
            if (r5 >= r2) goto L_0x015b
            r3 = r0[r5]     // Catch:{ XmlException -> 0x0175 }
            java.lang.String r4 = r3.getId()     // Catch:{ XmlException -> 0x0175 }
            java.lang.Object r4 = r1.get(r4)     // Catch:{ XmlException -> 0x0175 }
            org.apache.poi.xssf.model.ExternalLinksTable r4 = (org.apache.poi.xssf.model.ExternalLinksTable) r4     // Catch:{ XmlException -> 0x0175 }
            if (r4 != 0) goto L_0x0153
            org.apache.logging.log4j.Logger r4 = LOG     // Catch:{ XmlException -> 0x0175 }
            org.apache.logging.log4j.LogBuilder r4 = r4.atWarn()     // Catch:{ XmlException -> 0x0175 }
            java.lang.String r6 = "ExternalLinksTable with r:id {} was defined, but didn't exist in package, skipping"
            java.lang.String r3 = r3.getId()     // Catch:{ XmlException -> 0x0175 }
            r4.log((java.lang.String) r6, (java.lang.Object) r3)     // Catch:{ XmlException -> 0x0175 }
            goto L_0x0158
        L_0x0153:
            java.util.List<org.apache.poi.xssf.model.ExternalLinksTable> r3 = r7.externalLinks     // Catch:{ XmlException -> 0x0175 }
            r3.add(r4)     // Catch:{ XmlException -> 0x0175 }
        L_0x0158:
            int r5 = r5 + 1
            goto L_0x0133
        L_0x015b:
            r7.reprocessNamedRanges()     // Catch:{ XmlException -> 0x0175 }
            return
        L_0x015f:
            org.apache.poi.ooxml.POIXMLException r7 = new org.apache.poi.ooxml.POIXMLException     // Catch:{ XmlException -> 0x0175 }
            java.lang.String r0 = "Cannot read a workbook without sheets"
            r7.<init>((java.lang.String) r0)     // Catch:{ XmlException -> 0x0175 }
            throw r7     // Catch:{ XmlException -> 0x0175 }
        L_0x0167:
            r7 = move-exception
            throw r7     // Catch:{ all -> 0x0169 }
        L_0x0169:
            r1 = move-exception
            if (r0 == 0) goto L_0x0174
            r0.close()     // Catch:{ all -> 0x0170 }
            goto L_0x0174
        L_0x0170:
            r0 = move-exception
            r7.addSuppressed(r0)     // Catch:{ XmlException -> 0x0175 }
        L_0x0174:
            throw r1     // Catch:{ XmlException -> 0x0175 }
        L_0x0175:
            r7 = move-exception
            org.apache.poi.ooxml.POIXMLException r0 = new org.apache.poi.ooxml.POIXMLException
            r0.<init>((java.lang.Throwable) r7)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.xssf.usermodel.XSSFWorkbook.onDocumentRead():void");
    }

    public void parseSheet(Map<String, XSSFSheet> map, CTSheet cTSheet) {
        XSSFSheet xSSFSheet = map.get(cTSheet.getId());
        if (xSSFSheet == null) {
            LOG.atWarn().log("Sheet with name {} and r:id {} was defined, but didn't exist in package, skipping", cTSheet.getName(), cTSheet.getId());
            return;
        }
        xSSFSheet.sheet = cTSheet;
        xSSFSheet.onDocumentRead();
        this.sheets.add(xSSFSheet);
    }

    private void onWorkbookCreate() {
        CTWorkbook newInstance = CTWorkbook.Factory.newInstance();
        this.workbook = newInstance;
        newInstance.addNewWorkbookPr().setDate1904(false);
        setBookViewsIfMissing();
        this.workbook.addNewSheets();
        getProperties().getExtendedProperties().getUnderlyingProperties().setApplication(POIXMLDocument.DOCUMENT_CREATOR);
        this.sharedStringSource = (SharedStringsTable) createRelationship(XSSFRelation.SHARED_STRINGS, this.xssfFactory);
        StylesTable stylesTable = (StylesTable) createRelationship(XSSFRelation.STYLES, this.xssfFactory);
        this.stylesSource = stylesTable;
        stylesTable.setWorkbook(this);
        this.namedRanges = new ArrayList();
        this.namedRangesByName = new ArrayListValuedHashMap();
        this.sheets = new ArrayList();
        this.pivotTables = new ArrayList();
        this.externalLinks = new ArrayList();
    }

    private void setBookViewsIfMissing() {
        if (!this.workbook.isSetBookViews()) {
            this.workbook.addNewBookViews().addNewWorkbookView().setActiveTab(0);
        }
    }

    protected static OPCPackage newPackage(XSSFWorkbookType xSSFWorkbookType) {
        OPCPackage oPCPackage = null;
        try {
            oPCPackage = OPCPackage.create((OutputStream) new UnsynchronizedByteArrayOutputStream());
            PackagePartName createPartName = PackagingURIHelper.createPartName(XSSFRelation.WORKBOOK.getDefaultFileName());
            oPCPackage.addRelationship(createPartName, TargetMode.INTERNAL, PackageRelationshipTypes.CORE_DOCUMENT);
            oPCPackage.createPart(createPartName, xSSFWorkbookType.getContentType());
            oPCPackage.getPackageProperties().setCreatorProperty(POIXMLDocument.DOCUMENT_CREATOR);
            return oPCPackage;
        } catch (Exception e) {
            IOUtils.closeQuietly(oPCPackage);
            throw new POIXMLException((Throwable) e);
        }
    }

    @Internal
    public CTWorkbook getCTWorkbook() {
        return this.workbook;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0033, code lost:
        r4 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0034, code lost:
        if (r2 != null) goto L_0x0036;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:?, code lost:
        r2.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x003e, code lost:
        throw r4;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int addPicture(byte[] r4, int r5) {
        /*
            r3 = this;
            java.util.List r0 = r3.getAllPictures()
            int r0 = r0.size()
            r1 = 1
            int r0 = r0 + r1
            org.apache.poi.ooxml.POIXMLRelation[] r2 = org.apache.poi.xssf.usermodel.XSSFPictureData.RELATIONS
            r5 = r2[r5]
            org.apache.poi.xssf.usermodel.XSSFFactory r2 = r3.xssfFactory
            org.apache.poi.ooxml.POIXMLDocumentPart$RelationPart r5 = r3.createRelationship(r5, r2, r0, r1)
            org.apache.poi.ooxml.POIXMLDocumentPart r5 = r5.getDocumentPart()
            org.apache.poi.xssf.usermodel.XSSFPictureData r5 = (org.apache.poi.xssf.usermodel.XSSFPictureData) r5
            org.apache.poi.openxml4j.opc.PackagePart r2 = r5.getPackagePart()     // Catch:{ IOException -> 0x003f }
            java.io.OutputStream r2 = r2.getOutputStream()     // Catch:{ IOException -> 0x003f }
            r2.write(r4)     // Catch:{ all -> 0x0031 }
            if (r2 == 0) goto L_0x002a
            r2.close()     // Catch:{ IOException -> 0x003f }
        L_0x002a:
            java.util.List<org.apache.poi.xssf.usermodel.XSSFPictureData> r3 = r3.pictures
            r3.add(r5)
            int r0 = r0 - r1
            return r0
        L_0x0031:
            r3 = move-exception
            throw r3     // Catch:{ all -> 0x0033 }
        L_0x0033:
            r4 = move-exception
            if (r2 == 0) goto L_0x003e
            r2.close()     // Catch:{ all -> 0x003a }
            goto L_0x003e
        L_0x003a:
            r5 = move-exception
            r3.addSuppressed(r5)     // Catch:{ IOException -> 0x003f }
        L_0x003e:
            throw r4     // Catch:{ IOException -> 0x003f }
        L_0x003f:
            r3 = move-exception
            org.apache.poi.ooxml.POIXMLException r4 = new org.apache.poi.ooxml.POIXMLException
            r4.<init>((java.lang.Throwable) r3)
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.xssf.usermodel.XSSFWorkbook.addPicture(byte[], int):int");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0033, code lost:
        r4 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0034, code lost:
        if (r2 != null) goto L_0x0036;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:?, code lost:
        r2.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x003a, code lost:
        r5 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x003b, code lost:
        r3.addSuppressed(r5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x003e, code lost:
        throw r4;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int addPicture(java.io.InputStream r4, int r5) throws java.io.IOException {
        /*
            r3 = this;
            java.util.List r0 = r3.getAllPictures()
            int r0 = r0.size()
            r1 = 1
            int r0 = r0 + r1
            org.apache.poi.ooxml.POIXMLRelation[] r2 = org.apache.poi.xssf.usermodel.XSSFPictureData.RELATIONS
            r5 = r2[r5]
            org.apache.poi.xssf.usermodel.XSSFFactory r2 = r3.xssfFactory
            org.apache.poi.ooxml.POIXMLDocumentPart$RelationPart r5 = r3.createRelationship(r5, r2, r0, r1)
            org.apache.poi.ooxml.POIXMLDocumentPart r5 = r5.getDocumentPart()
            org.apache.poi.xssf.usermodel.XSSFPictureData r5 = (org.apache.poi.xssf.usermodel.XSSFPictureData) r5
            org.apache.poi.openxml4j.opc.PackagePart r2 = r5.getPackagePart()
            java.io.OutputStream r2 = r2.getOutputStream()
            org.apache.poi.util.IOUtils.copy((java.io.InputStream) r4, (java.io.OutputStream) r2)     // Catch:{ all -> 0x0031 }
            if (r2 == 0) goto L_0x002a
            r2.close()
        L_0x002a:
            java.util.List<org.apache.poi.xssf.usermodel.XSSFPictureData> r3 = r3.pictures
            r3.add(r5)
            int r0 = r0 - r1
            return r0
        L_0x0031:
            r3 = move-exception
            throw r3     // Catch:{ all -> 0x0033 }
        L_0x0033:
            r4 = move-exception
            if (r2 == 0) goto L_0x003e
            r2.close()     // Catch:{ all -> 0x003a }
            goto L_0x003e
        L_0x003a:
            r5 = move-exception
            r3.addSuppressed(r5)
        L_0x003e:
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.xssf.usermodel.XSSFWorkbook.addPicture(java.io.InputStream, int):int");
    }

    public XSSFSheet cloneSheet(int i) {
        return cloneSheet(i, (String) null);
    }

    public void close() throws IOException {
        try {
            super.close();
        } finally {
            IOUtils.closeQuietly(this.sharedStringSource);
        }
    }

    /* JADX WARNING: type inference failed for: r3v2, types: [org.apache.poi.ooxml.POIXMLDocumentPart] */
    /* JADX WARNING: Code restructure failed: missing block: B:53:0x011d, code lost:
        r7 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:54:0x011e, code lost:
        if (r2 != null) goto L_0x0120;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:56:?, code lost:
        r2.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:57:0x0124, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:59:?, code lost:
        r6.addSuppressed(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:60:0x0128, code lost:
        throw r7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:64:0x012b, code lost:
        r7 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:66:?, code lost:
        r8.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:70:0x0134, code lost:
        throw r7;
     */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public org.apache.poi.xssf.usermodel.XSSFSheet cloneSheet(int r7, java.lang.String r8) {
        /*
            r6 = this;
            java.lang.String r0 = "Failed to clone sheet"
            r6.validateSheetIndex(r7)
            java.util.List<org.apache.poi.xssf.usermodel.XSSFSheet> r1 = r6.sheets
            java.lang.Object r7 = r1.get(r7)
            org.apache.poi.xssf.usermodel.XSSFSheet r7 = (org.apache.poi.xssf.usermodel.XSSFSheet) r7
            if (r8 != 0) goto L_0x0018
            java.lang.String r8 = r7.getSheetName()
            java.lang.String r8 = r6.getUniqueSheetName(r8)
            goto L_0x001b
        L_0x0018:
            r6.validateSheetName(r8)
        L_0x001b:
            org.apache.poi.xssf.usermodel.XSSFSheet r6 = r6.createSheet((java.lang.String) r8)
            java.util.List r8 = r7.getRelationParts()
            java.util.Iterator r8 = r8.iterator()
            r1 = 0
        L_0x0028:
            boolean r2 = r8.hasNext()
            if (r2 == 0) goto L_0x0044
            java.lang.Object r2 = r8.next()
            org.apache.poi.ooxml.POIXMLDocumentPart$RelationPart r2 = (org.apache.poi.ooxml.POIXMLDocumentPart.RelationPart) r2
            org.apache.poi.ooxml.POIXMLDocumentPart r3 = r2.getDocumentPart()
            boolean r4 = r3 instanceof org.apache.poi.xssf.usermodel.XSSFDrawing
            if (r4 == 0) goto L_0x0040
            r1 = r3
            org.apache.poi.xssf.usermodel.XSSFDrawing r1 = (org.apache.poi.xssf.usermodel.XSSFDrawing) r1
            goto L_0x0028
        L_0x0040:
            addRelation(r2, r6)
            goto L_0x0028
        L_0x0044:
            org.apache.poi.openxml4j.opc.PackagePart r8 = r7.getPackagePart()     // Catch:{ InvalidFormatException -> 0x013c }
            org.apache.poi.openxml4j.opc.PackageRelationshipCollection r8 = r8.getRelationships()     // Catch:{ InvalidFormatException -> 0x013c }
            java.util.Iterator r8 = r8.iterator()     // Catch:{ InvalidFormatException -> 0x013c }
        L_0x0050:
            boolean r2 = r8.hasNext()     // Catch:{ InvalidFormatException -> 0x013c }
            if (r2 == 0) goto L_0x007c
            java.lang.Object r2 = r8.next()     // Catch:{ InvalidFormatException -> 0x013c }
            org.apache.poi.openxml4j.opc.PackageRelationship r2 = (org.apache.poi.openxml4j.opc.PackageRelationship) r2     // Catch:{ InvalidFormatException -> 0x013c }
            org.apache.poi.openxml4j.opc.TargetMode r3 = r2.getTargetMode()     // Catch:{ InvalidFormatException -> 0x013c }
            org.apache.poi.openxml4j.opc.TargetMode r4 = org.apache.poi.openxml4j.opc.TargetMode.EXTERNAL     // Catch:{ InvalidFormatException -> 0x013c }
            if (r3 != r4) goto L_0x0050
            org.apache.poi.openxml4j.opc.PackagePart r3 = r6.getPackagePart()     // Catch:{ InvalidFormatException -> 0x013c }
            java.net.URI r4 = r2.getTargetURI()     // Catch:{ InvalidFormatException -> 0x013c }
            java.lang.String r4 = r4.toASCIIString()     // Catch:{ InvalidFormatException -> 0x013c }
            java.lang.String r5 = r2.getRelationshipType()     // Catch:{ InvalidFormatException -> 0x013c }
            java.lang.String r2 = r2.getId()     // Catch:{ InvalidFormatException -> 0x013c }
            r3.addExternalRelationship(r4, r5, r2)     // Catch:{ InvalidFormatException -> 0x013c }
            goto L_0x0050
        L_0x007c:
            org.apache.commons.io.output.UnsynchronizedByteArrayOutputStream r8 = new org.apache.commons.io.output.UnsynchronizedByteArrayOutputStream     // Catch:{ IOException -> 0x0135 }
            r8.<init>()     // Catch:{ IOException -> 0x0135 }
            r7.write(r8)     // Catch:{ all -> 0x0129 }
            java.io.InputStream r2 = r8.toInputStream()     // Catch:{ all -> 0x0129 }
            r6.read(r2)     // Catch:{ all -> 0x011b }
            if (r2 == 0) goto L_0x0090
            r2.close()     // Catch:{ all -> 0x0129 }
        L_0x0090:
            r8.close()     // Catch:{ IOException -> 0x0135 }
            org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorksheet r8 = r6.getCTWorksheet()
            boolean r0 = r8.isSetLegacyDrawing()
            if (r0 == 0) goto L_0x00ab
            org.apache.logging.log4j.Logger r0 = LOG
            org.apache.logging.log4j.LogBuilder r0 = r0.atWarn()
            java.lang.String r2 = "Cloning sheets with comments is not yet supported."
            r0.log((java.lang.String) r2)
            r8.unsetLegacyDrawing()
        L_0x00ab:
            boolean r0 = r8.isSetPageSetup()
            if (r0 == 0) goto L_0x00bf
            org.apache.logging.log4j.Logger r0 = LOG
            org.apache.logging.log4j.LogBuilder r0 = r0.atWarn()
            java.lang.String r2 = "Cloning sheets with page setup is not yet supported."
            r0.log((java.lang.String) r2)
            r8.unsetPageSetup()
        L_0x00bf:
            r0 = 0
            r6.setSelected(r0)
            if (r1 == 0) goto L_0x011a
            boolean r0 = r8.isSetDrawing()
            if (r0 == 0) goto L_0x00ce
            r8.unsetDrawing()
        L_0x00ce:
            org.apache.poi.xssf.usermodel.XSSFDrawing r8 = r6.createDrawingPatriarch()
            org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTDrawing r0 = r8.getCTDrawing()
            org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTDrawing r1 = r1.getCTDrawing()
            org.apache.xmlbeans.XmlObject r1 = r1.copy()
            r0.set(r1)
            org.apache.poi.xssf.usermodel.XSSFDrawing r7 = r7.getDrawingPatriarch()
            if (r7 == 0) goto L_0x011a
            java.util.List r7 = r7.getRelationParts()
            java.util.Iterator r7 = r7.iterator()
        L_0x00ef:
            boolean r0 = r7.hasNext()
            if (r0 == 0) goto L_0x011a
            java.lang.Object r0 = r7.next()
            org.apache.poi.ooxml.POIXMLDocumentPart$RelationPart r0 = (org.apache.poi.ooxml.POIXMLDocumentPart.RelationPart) r0
            org.apache.poi.ooxml.POIXMLDocumentPart r1 = r0.getDocumentPart()
            boolean r2 = r1 instanceof org.apache.poi.xssf.usermodel.XSSFChart
            if (r2 == 0) goto L_0x0116
            org.apache.poi.ooxml.POIXMLDocumentPart$RelationPart r0 = r8.createChartRelationPart()
            org.apache.poi.ooxml.POIXMLDocumentPart r0 = r0.getDocumentPart()
            org.apache.poi.xssf.usermodel.XSSFChart r0 = (org.apache.poi.xssf.usermodel.XSSFChart) r0
            org.apache.poi.xssf.usermodel.XSSFChart r1 = (org.apache.poi.xssf.usermodel.XSSFChart) r1
            r0.importContent(r1)
            r0.replaceReferences(r6)
            goto L_0x00ef
        L_0x0116:
            addRelation(r0, r8)
            goto L_0x00ef
        L_0x011a:
            return r6
        L_0x011b:
            r6 = move-exception
            throw r6     // Catch:{ all -> 0x011d }
        L_0x011d:
            r7 = move-exception
            if (r2 == 0) goto L_0x0128
            r2.close()     // Catch:{ all -> 0x0124 }
            goto L_0x0128
        L_0x0124:
            r1 = move-exception
            r6.addSuppressed(r1)     // Catch:{ all -> 0x0129 }
        L_0x0128:
            throw r7     // Catch:{ all -> 0x0129 }
        L_0x0129:
            r6 = move-exception
            throw r6     // Catch:{ all -> 0x012b }
        L_0x012b:
            r7 = move-exception
            r8.close()     // Catch:{ all -> 0x0130 }
            goto L_0x0134
        L_0x0130:
            r8 = move-exception
            r6.addSuppressed(r8)     // Catch:{ IOException -> 0x0135 }
        L_0x0134:
            throw r7     // Catch:{ IOException -> 0x0135 }
        L_0x0135:
            r6 = move-exception
            org.apache.poi.ooxml.POIXMLException r7 = new org.apache.poi.ooxml.POIXMLException
            r7.<init>(r0, r6)
            throw r7
        L_0x013c:
            r6 = move-exception
            org.apache.poi.ooxml.POIXMLException r7 = new org.apache.poi.ooxml.POIXMLException
            r7.<init>(r0, r6)
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.xssf.usermodel.XSSFWorkbook.cloneSheet(int, java.lang.String):org.apache.poi.xssf.usermodel.XSSFSheet");
    }

    private static boolean addRelation(POIXMLDocumentPart.RelationPart relationPart, POIXMLDocumentPart pOIXMLDocumentPart) {
        PackageRelationship relationship = relationPart.getRelationship();
        if (relationship.getTargetMode() == TargetMode.EXTERNAL) {
            pOIXMLDocumentPart.getPackagePart().addRelationship(relationship.getTargetURI(), relationship.getTargetMode(), relationship.getRelationshipType(), relationship.getId());
            return true;
        }
        XSSFRelation instance = XSSFRelation.getInstance(relationship.getRelationshipType());
        if (instance == null) {
            LOG.atWarn().log("Can't clone sheet relationship (some data will be lost in the cloned sheet) - unknown relation type found: {}", (Object) relationship.getRelationshipType());
            return false;
        }
        pOIXMLDocumentPart.addRelation(relationship.getId(), instance, relationPart.getDocumentPart());
        return true;
    }

    /* JADX WARNING: Removed duplicated region for block: B:15:0x0046  */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x0062  */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x008f A[LOOP:0: B:12:0x0032->B:20:0x008f, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x008e A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.lang.String getUniqueSheetName(java.lang.String r9) {
        /*
            r8 = this;
            r0 = 40
            int r0 = r9.lastIndexOf(r0)
            r1 = 0
            java.lang.String r2 = ")"
            r3 = 2
            if (r0 <= 0) goto L_0x0031
            boolean r4 = r9.endsWith(r2)
            if (r4 == 0) goto L_0x0031
            int r4 = r0 + 1
            int r5 = r9.length()
            int r5 = r5 + -1
            java.lang.String r4 = r9.substring(r4, r5)
            java.lang.String r4 = r4.trim()     // Catch:{ NumberFormatException -> 0x0031 }
            int r4 = java.lang.Integer.parseInt(r4)     // Catch:{ NumberFormatException -> 0x0031 }
            int r4 = r4 + 1
            java.lang.String r0 = r9.substring(r1, r0)     // Catch:{ NumberFormatException -> 0x0032 }
            java.lang.String r9 = r0.trim()     // Catch:{ NumberFormatException -> 0x0032 }
            goto L_0x0032
        L_0x0031:
            r4 = r3
        L_0x0032:
            int r0 = r4 + 1
            java.lang.String r4 = java.lang.Integer.toString(r4)
            int r5 = r9.length()
            int r6 = r4.length()
            int r5 = r5 + r6
            int r5 = r5 + r3
            r6 = 31
            if (r5 >= r6) goto L_0x0062
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            java.lang.StringBuilder r5 = r5.append(r9)
            java.lang.String r6 = " ("
            java.lang.StringBuilder r5 = r5.append(r6)
            java.lang.StringBuilder r4 = r5.append(r4)
            java.lang.StringBuilder r4 = r4.append(r2)
            java.lang.String r4 = r4.toString()
            goto L_0x0087
        L_0x0062:
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            int r7 = r4.length()
            int r6 = r6 - r7
            int r6 = r6 - r3
            java.lang.String r6 = r9.substring(r1, r6)
            java.lang.StringBuilder r5 = r5.append(r6)
            java.lang.String r6 = "("
            java.lang.StringBuilder r5 = r5.append(r6)
            java.lang.StringBuilder r4 = r5.append(r4)
            java.lang.StringBuilder r4 = r4.append(r2)
            java.lang.String r4 = r4.toString()
        L_0x0087:
            int r5 = r8.getSheetIndex((java.lang.String) r4)
            r6 = -1
            if (r5 != r6) goto L_0x008f
            return r4
        L_0x008f:
            r4 = r0
            goto L_0x0032
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.xssf.usermodel.XSSFWorkbook.getUniqueSheetName(java.lang.String):java.lang.String");
    }

    public XSSFCellStyle createCellStyle() {
        return this.stylesSource.createCellStyle();
    }

    public XSSFDataFormat createDataFormat() {
        if (this.formatter == null) {
            this.formatter = new XSSFDataFormat(this.stylesSource);
        }
        return this.formatter;
    }

    public XSSFFont createFont() {
        XSSFFont xSSFFont = new XSSFFont();
        xSSFFont.registerTo(this.stylesSource);
        return xSSFFont;
    }

    public XSSFName createName() {
        CTDefinedName newInstance = CTDefinedName.Factory.newInstance();
        newInstance.setName("");
        return createAndStoreName(newInstance);
    }

    private XSSFName createAndStoreName(CTDefinedName cTDefinedName) {
        XSSFName xSSFName = new XSSFName(cTDefinedName, this);
        this.namedRanges.add(xSSFName);
        this.namedRangesByName.put(cTDefinedName.getName().toLowerCase(Locale.ENGLISH), xSSFName);
        return xSSFName;
    }

    public XSSFSheet createSheet() {
        String str = "Sheet" + this.sheets.size();
        int i = 0;
        while (getSheet(str) != null) {
            str = "Sheet" + i;
            i++;
        }
        return createSheet(str);
    }

    public XSSFSheet createSheet(String str) {
        if (str != null) {
            validateSheetName(str);
            if (str.length() > 31) {
                String substring = str.substring(0, 31);
                LOG.atWarn().log("Sheet '{}' will be added with a trimmed name '{}' for MS Excel compliance.", str, substring);
                str = substring;
            }
            WorkbookUtil.validateSheetName(str);
            CTSheet addSheet = addSheet(str);
            int i = 1;
            loop0:
            while (true) {
                for (XSSFSheet xSSFSheet : this.sheets) {
                    i = (int) Math.max(xSSFSheet.sheet.getSheetId() + 1, (long) i);
                }
                String fileName = XSSFRelation.WORKSHEET.getFileName(i);
                for (POIXMLDocumentPart next : getRelations()) {
                    if (next.getPackagePart() != null && fileName.equals(next.getPackagePart().getPartName().getName())) {
                        i++;
                    }
                }
                break loop0;
            }
            POIXMLDocumentPart.RelationPart createRelationship = createRelationship(XSSFRelation.WORKSHEET, this.xssfFactory, i, false);
            XSSFSheet xSSFSheet2 = (XSSFSheet) createRelationship.getDocumentPart();
            xSSFSheet2.sheet = addSheet;
            addSheet.setId(createRelationship.getRelationship().getId());
            addSheet.setSheetId((long) i);
            if (this.sheets.isEmpty()) {
                xSSFSheet2.setSelected(true);
            }
            this.sheets.add(xSSFSheet2);
            return xSSFSheet2;
        }
        throw new IllegalArgumentException("sheetName must not be null");
    }

    private void validateSheetName(String str) throws IllegalArgumentException {
        if (containsSheet(str, this.sheets.size())) {
            throw new IllegalArgumentException("The workbook already contains a sheet named '" + str + "'");
        }
    }

    /* access modifiers changed from: protected */
    public XSSFDialogsheet createDialogsheet(String str, CTDialogsheet cTDialogsheet) {
        return new XSSFDialogsheet(createSheet(str));
    }

    private CTSheet addSheet(String str) {
        CTSheet addNewSheet = this.workbook.getSheets().addNewSheet();
        addNewSheet.setName(str);
        return addNewSheet;
    }

    public XSSFFont findFont(boolean z, short s, short s2, String str, boolean z2, boolean z3, short s3, byte b) {
        return this.stylesSource.findFont(z, s, s2, str, z2, z3, s3, b);
    }

    public int getActiveSheetIndex() {
        return (int) this.workbook.getBookViews().getWorkbookViewArray(0).getActiveTab();
    }

    public List<XSSFPictureData> getAllPictures() {
        if (this.pictures == null) {
            List<PackagePart> partsByName = getPackage().getPartsByName(Pattern.compile("/xl/media/.*?"));
            this.pictures = new ArrayList(partsByName.size());
            for (PackagePart xSSFPictureData : partsByName) {
                this.pictures.add(new XSSFPictureData(xSSFPictureData));
            }
        }
        return this.pictures;
    }

    public XSSFCellStyle getCellStyleAt(int i) {
        return this.stylesSource.getStyleAt(i);
    }

    public XSSFFont getFontAt(int i) {
        return this.stylesSource.getFontAt(i);
    }

    public XSSFName getName(String str) {
        List<XSSFName> names = getNames(str);
        if (names.isEmpty()) {
            return null;
        }
        return names.iterator().next();
    }

    public List<XSSFName> getNames(String str) {
        return Collections.unmodifiableList(this.namedRangesByName.get((Object) str.toLowerCase(Locale.ENGLISH)));
    }

    /* access modifiers changed from: package-private */
    @Deprecated
    public XSSFName getNameAt(int i) {
        int size = this.namedRanges.size();
        if (size < 1) {
            throw new IllegalStateException("There are no defined names in this workbook");
        } else if (i >= 0 && i <= size) {
            return this.namedRanges.get(i);
        } else {
            throw new IllegalArgumentException("Specified name index " + i + " is outside the allowable range (0.." + (size - 1) + ").");
        }
    }

    public List<XSSFName> getAllNames() {
        return Collections.unmodifiableList(this.namedRanges);
    }

    /* access modifiers changed from: package-private */
    @Deprecated
    public int getNameIndex(String str) {
        XSSFName name = getName(str);
        if (name != null) {
            return this.namedRanges.indexOf(name);
        }
        return -1;
    }

    public int getNumCellStyles() {
        return this.stylesSource.getNumCellStyles();
    }

    public int getNumberOfFonts() {
        return this.stylesSource.getFonts().size();
    }

    @Deprecated
    @Removal(version = "6.0.0")
    public int getNumberOfFontsAsInt() {
        return getNumberOfFonts();
    }

    public int getNumberOfNames() {
        return this.namedRanges.size();
    }

    public int getNumberOfSheets() {
        return this.sheets.size();
    }

    public String getPrintArea(int i) {
        XSSFName builtInName = getBuiltInName(XSSFName.BUILTIN_PRINT_AREA, i);
        if (builtInName == null) {
            return null;
        }
        return builtInName.getRefersToFormula();
    }

    public XSSFSheet getSheet(String str) {
        for (XSSFSheet next : this.sheets) {
            if (str.equalsIgnoreCase(next.getSheetName())) {
                return next;
            }
        }
        return null;
    }

    public XSSFSheet getSheetAt(int i) {
        validateSheetIndex(i);
        return this.sheets.get(i);
    }

    public int getSheetIndex(String str) {
        int i = 0;
        for (XSSFSheet sheetName : this.sheets) {
            if (str.equalsIgnoreCase(sheetName.getSheetName())) {
                return i;
            }
            i++;
        }
        return -1;
    }

    public int getSheetIndex(Sheet sheet) {
        int i = 0;
        for (XSSFSheet xSSFSheet : this.sheets) {
            if (xSSFSheet == sheet) {
                return i;
            }
            i++;
        }
        return -1;
    }

    public String getSheetName(int i) {
        validateSheetIndex(i);
        return this.sheets.get(i).getSheetName();
    }

    public Iterator<Sheet> sheetIterator() {
        return new SheetIterator();
    }

    public Iterator<Sheet> iterator() {
        return sheetIterator();
    }

    public Spliterator<Sheet> spliterator() {
        return this.sheets.spliterator();
    }

    private final class SheetIterator<T extends Sheet> implements Iterator<T> {
        private final Iterator<T> it;

        public SheetIterator() {
            this.it = XSSFWorkbook.this.sheets.iterator();
        }

        public boolean hasNext() {
            return this.it.hasNext();
        }

        public T next() throws NoSuchElementException {
            return (Sheet) this.it.next();
        }

        public void remove() throws IllegalStateException {
            throw new UnsupportedOperationException("remove method not supported on XSSFWorkbook.iterator(). Use Sheet.removeSheetAt(int) instead.");
        }
    }

    public boolean isMacroEnabled() {
        return getPackagePart().getContentType().equals(XSSFRelation.MACROS_WORKBOOK.getContentType());
    }

    public void removeName(Name name) {
        if (!this.namedRangesByName.removeMapping(name.getNameName().toLowerCase(Locale.ENGLISH), name) || !this.namedRanges.remove(name)) {
            throw new IllegalArgumentException("Name was not found: " + name);
        }
    }

    /* access modifiers changed from: package-private */
    public void updateName(XSSFName xSSFName, String str) {
        if (this.namedRangesByName.removeMapping(str.toLowerCase(Locale.ENGLISH), xSSFName)) {
            this.namedRangesByName.put(xSSFName.getNameName().toLowerCase(Locale.ENGLISH), xSSFName);
            return;
        }
        throw new IllegalArgumentException("Name was not found: " + xSSFName);
    }

    public void removePrintArea(int i) {
        XSSFName builtInName = getBuiltInName(XSSFName.BUILTIN_PRINT_AREA, i);
        if (builtInName != null) {
            removeName(builtInName);
        }
    }

    public void removeSheetAt(int i) {
        validateSheetIndex(i);
        onSheetDelete(i);
        removeRelation((POIXMLDocumentPart) getSheetAt(i));
        this.sheets.remove(i);
        if (!this.sheets.isEmpty()) {
            int size = i >= this.sheets.size() ? this.sheets.size() - 1 : i;
            int activeSheetIndex = getActiveSheetIndex();
            if (activeSheetIndex == i) {
                setActiveSheet(size);
            } else if (activeSheetIndex > i) {
                setActiveSheet(activeSheetIndex - 1);
            }
        }
    }

    private void onSheetDelete(int i) {
        getSheetAt(i).onSheetDelete();
        this.workbook.getSheets().removeSheet(i);
        CalculationChain calculationChain = this.calcChain;
        if (calculationChain != null) {
            removeRelation((POIXMLDocumentPart) calculationChain);
            this.calcChain = null;
        }
        ArrayList<XSSFName> arrayList = new ArrayList<>();
        for (XSSFName next : this.namedRanges) {
            CTDefinedName cTName = next.getCTName();
            if (cTName.isSetLocalSheetId()) {
                long j = (long) i;
                if (cTName.getLocalSheetId() == j) {
                    arrayList.add(next);
                } else if (cTName.getLocalSheetId() > j) {
                    cTName.setLocalSheetId(cTName.getLocalSheetId() - 1);
                }
            }
        }
        for (XSSFName removeName : arrayList) {
            removeName(removeName);
        }
    }

    public Row.MissingCellPolicy getMissingCellPolicy() {
        return this._missingCellPolicy;
    }

    public void setMissingCellPolicy(Row.MissingCellPolicy missingCellPolicy) {
        this._missingCellPolicy = missingCellPolicy;
    }

    public void setActiveSheet(int i) {
        validateSheetIndex(i);
        for (CTBookView activeTab : this.workbook.getBookViews().getWorkbookViewArray()) {
            activeTab.setActiveTab((long) i);
        }
    }

    private void validateSheetIndex(int i) {
        int size = this.sheets.size() - 1;
        if (i < 0 || i > size) {
            String str = "(0.." + size + ")";
            if (size == -1) {
                str = "(no sheets)";
            }
            throw new IllegalArgumentException("Sheet index (" + i + ") is out of range " + str);
        }
    }

    public int getFirstVisibleTab() {
        return (short) ((int) this.workbook.getBookViews().getWorkbookViewArray(0).getFirstSheet());
    }

    public void setFirstVisibleTab(int i) {
        this.workbook.getBookViews().getWorkbookViewArray(0).setFirstSheet((long) i);
    }

    public void setPrintArea(int i, String str) {
        XSSFName builtInName = getBuiltInName(XSSFName.BUILTIN_PRINT_AREA, i);
        if (builtInName == null) {
            builtInName = createBuiltInName(XSSFName.BUILTIN_PRINT_AREA, i);
        }
        String[] split = COMMA_PATTERN.split(str);
        StringBuilder sb = new StringBuilder(32);
        for (int i2 = 0; i2 < split.length; i2++) {
            if (i2 > 0) {
                sb.append(',');
            }
            SheetNameFormatter.appendFormat(sb, getSheetName(i));
            sb.append('!');
            sb.append(split[i2]);
        }
        builtInName.setRefersToFormula(sb.toString());
    }

    public void setPrintArea(int i, int i2, int i3, int i4, int i5) {
        setPrintArea(i, getReferencePrintArea(getSheetName(i), i2, i3, i4, i5));
    }

    public CellReferenceType getCellReferenceType() {
        CTCalcPr calcPr = getCTWorkbook().getCalcPr();
        if (calcPr == null) {
            return CellReferenceType.UNKNOWN;
        }
        if (calcPr.getRefMode() == STRefMode.R_1_C_1) {
            return CellReferenceType.R1C1;
        }
        if (calcPr.getRefMode() == STRefMode.A_1) {
            return CellReferenceType.A1;
        }
        return CellReferenceType.UNKNOWN;
    }

    public void setCellReferenceType(CellReferenceType cellReferenceType) {
        CTCalcPr calcPr = getCTWorkbook().getCalcPr();
        if (cellReferenceType != CellReferenceType.UNKNOWN) {
            if (calcPr == null) {
                calcPr = getCTWorkbook().addNewCalcPr();
            }
            calcPr.setRefMode(cellReferenceType == CellReferenceType.R1C1 ? STRefMode.R_1_C_1 : STRefMode.A_1);
        } else if (calcPr != null) {
            calcPr.unsetRefMode();
        }
    }

    private static String getReferencePrintArea(String str, int i, int i2, int i3, int i4) {
        CellReference cellReference = new CellReference(str, i3, i, true, true);
        CellReference cellReference2 = new CellReference(str, i4, i2, true, true);
        return "$" + cellReference.getCellRefParts()[2] + "$" + cellReference.getCellRefParts()[1] + ":$" + cellReference2.getCellRefParts()[2] + "$" + cellReference2.getCellRefParts()[1];
    }

    /* access modifiers changed from: package-private */
    public XSSFName getBuiltInName(String str, int i) {
        for (XSSFName xSSFName : this.namedRangesByName.get((Object) str.toLowerCase(Locale.ENGLISH))) {
            if (xSSFName.getSheetIndex() == i) {
                return xSSFName;
            }
        }
        return null;
    }

    /* access modifiers changed from: package-private */
    public XSSFName createBuiltInName(String str, int i) {
        validateSheetIndex(i);
        CTDefinedName addNewDefinedName = (this.workbook.getDefinedNames() == null ? this.workbook.addNewDefinedNames() : this.workbook.getDefinedNames()).addNewDefinedName();
        addNewDefinedName.setName(str);
        addNewDefinedName.setLocalSheetId((long) i);
        if (getBuiltInName(str, i) == null) {
            return createAndStoreName(addNewDefinedName);
        }
        throw new POIXMLException("Builtin (" + str + ") already exists for sheet (" + i + ")");
    }

    public void setSelectedTab(int i) {
        int i2 = 0;
        for (XSSFSheet selected : this.sheets) {
            selected.setSelected(i2 == i);
            i2++;
        }
    }

    public void setSheetName(int i, String str) {
        if (str != null) {
            validateSheetIndex(i);
            String sheetName = getSheetName(i);
            if (str.length() > 31) {
                str = str.substring(0, 31);
            }
            WorkbookUtil.validateSheetName(str);
            if (!str.equals(sheetName)) {
                if (!containsSheet(str, i)) {
                    new XSSFFormulaUtils(this).updateSheetName(i, sheetName, str);
                    this.workbook.getSheets().getSheetArray(i).setName(str);
                    return;
                }
                throw new IllegalArgumentException("The workbook already contains a sheet of this name");
            }
            return;
        }
        throw new IllegalArgumentException("sheetName must not be null");
    }

    public void setSheetOrder(String str, int i) {
        int sheetIndex = getSheetIndex(str);
        List<XSSFSheet> list = this.sheets;
        list.add(i, list.remove(sheetIndex));
        CTSheets sheets2 = this.workbook.getSheets();
        XmlObject copy = sheets2.getSheetArray(sheetIndex).copy();
        this.workbook.getSheets().removeSheet(sheetIndex);
        sheets2.insertNewSheet(i).set(copy);
        CTSheet[] sheetArray = sheets2.getSheetArray();
        for (int i2 = 0; i2 < sheetArray.length; i2++) {
            this.sheets.get(i2).sheet = sheetArray[i2];
        }
        updateNamedRangesAfterSheetReorder(sheetIndex, i);
        updateActiveSheetAfterSheetReorder(sheetIndex, i);
    }

    private void updateNamedRangesAfterSheetReorder(int i, int i2) {
        for (XSSFName next : this.namedRanges) {
            int sheetIndex = next.getSheetIndex();
            if (sheetIndex != -1) {
                if (sheetIndex == i) {
                    next.setSheetIndex(i2);
                } else if (i2 <= sheetIndex && sheetIndex < i) {
                    next.setSheetIndex(sheetIndex + 1);
                } else if (i < sheetIndex && sheetIndex <= i2) {
                    next.setSheetIndex(sheetIndex - 1);
                }
            }
        }
    }

    private void updateActiveSheetAfterSheetReorder(int i, int i2) {
        int activeSheetIndex = getActiveSheetIndex();
        if (activeSheetIndex == i) {
            setActiveSheet(i2);
        } else if (activeSheetIndex < i && activeSheetIndex < i2) {
        } else {
            if (activeSheetIndex > i && activeSheetIndex > i2) {
                return;
            }
            if (i2 > i) {
                setActiveSheet(activeSheetIndex - 1);
            } else {
                setActiveSheet(activeSheetIndex + 1);
            }
        }
    }

    private void saveNamedRanges() {
        if (!this.namedRanges.isEmpty()) {
            CTDefinedNames newInstance = CTDefinedNames.Factory.newInstance();
            CTDefinedName[] cTDefinedNameArr = new CTDefinedName[this.namedRanges.size()];
            int i = 0;
            for (XSSFName cTName : this.namedRanges) {
                cTDefinedNameArr[i] = cTName.getCTName();
                i++;
            }
            newInstance.setDefinedNameArray(cTDefinedNameArr);
            if (this.workbook.isSetDefinedNames()) {
                this.workbook.unsetDefinedNames();
            }
            this.workbook.setDefinedNames(newInstance);
            reprocessNamedRanges();
        } else if (this.workbook.isSetDefinedNames()) {
            this.workbook.unsetDefinedNames();
        }
    }

    private void reprocessNamedRanges() {
        this.namedRangesByName = new ArrayListValuedHashMap();
        this.namedRanges = new ArrayList();
        if (this.workbook.isSetDefinedNames()) {
            for (CTDefinedName createAndStoreName : this.workbook.getDefinedNames().getDefinedNameArray()) {
                createAndStoreName(createAndStoreName);
            }
        }
    }

    private void saveCalculationChain() {
        CalculationChain calculationChain = this.calcChain;
        if (calculationChain != null && calculationChain.getCTCalcChain().sizeOfCArray() == 0) {
            removeRelation((POIXMLDocumentPart) this.calcChain);
            this.calcChain = null;
        }
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:11:?, code lost:
        r1.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x003d, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x003e, code lost:
        r4.addSuppressed(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0041, code lost:
        throw r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0036, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0037, code lost:
        if (r1 != null) goto L_0x0039;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void commit() throws java.io.IOException {
        /*
            r4 = this;
            r4.saveNamedRanges()
            r4.saveCalculationChain()
            org.apache.xmlbeans.XmlOptions r0 = new org.apache.xmlbeans.XmlOptions
            org.apache.xmlbeans.XmlOptions r1 = org.apache.poi.ooxml.POIXMLTypeLoader.DEFAULT_XML_OPTIONS
            r0.<init>(r1)
            javax.xml.namespace.QName r1 = new javax.xml.namespace.QName
            org.apache.xmlbeans.SchemaType r2 = org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorkbook.type
            javax.xml.namespace.QName r2 = r2.getName()
            java.lang.String r2 = r2.getNamespaceURI()
            java.lang.String r3 = "workbook"
            r1.<init>(r2, r3)
            r0.setSaveSyntheticDocumentElement(r1)
            org.apache.poi.openxml4j.opc.PackagePart r1 = r4.getPackagePart()
            java.io.OutputStream r1 = r1.getOutputStream()
            org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorkbook r4 = r4.workbook     // Catch:{ all -> 0x0034 }
            r4.save((java.io.OutputStream) r1, (org.apache.xmlbeans.XmlOptions) r0)     // Catch:{ all -> 0x0034 }
            if (r1 == 0) goto L_0x0033
            r1.close()
        L_0x0033:
            return
        L_0x0034:
            r4 = move-exception
            throw r4     // Catch:{ all -> 0x0036 }
        L_0x0036:
            r0 = move-exception
            if (r1 == 0) goto L_0x0041
            r1.close()     // Catch:{ all -> 0x003d }
            goto L_0x0041
        L_0x003d:
            r1 = move-exception
            r4.addSuppressed(r1)
        L_0x0041:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.xssf.usermodel.XSSFWorkbook.commit():void");
    }

    @Internal
    public SharedStringsTable getSharedStringSource() {
        return this.sharedStringSource;
    }

    public StylesTable getStylesSource() {
        return this.stylesSource;
    }

    public ThemesTable getTheme() {
        StylesTable stylesTable = this.stylesSource;
        if (stylesTable == null) {
            return null;
        }
        return stylesTable.getTheme();
    }

    public XSSFCreationHelper getCreationHelper() {
        if (this._creationHelper == null) {
            this._creationHelper = new XSSFCreationHelper(this);
        }
        return this._creationHelper;
    }

    private boolean containsSheet(String str, int i) {
        CTSheet[] sheetArray = this.workbook.getSheets().getSheetArray();
        if (str.length() > 31) {
            str = str.substring(0, 31);
        }
        for (int i2 = 0; i2 < sheetArray.length; i2++) {
            String name = sheetArray[i2].getName();
            if (name.length() > 31) {
                name = name.substring(0, 31);
            }
            if (i != i2 && str.equalsIgnoreCase(name)) {
                return true;
            }
        }
        return false;
    }

    @Internal
    public boolean isDate1904() {
        CTWorkbookPr workbookPr = this.workbook.getWorkbookPr();
        return workbookPr != null && workbookPr.getDate1904();
    }

    public List<PackagePart> getAllEmbeddedParts() throws OpenXML4JException {
        LinkedList linkedList = new LinkedList();
        for (XSSFSheet next : this.sheets) {
            Iterator<PackageRelationship> it = next.getPackagePart().getRelationshipsByType(XSSFRelation.OLEEMBEDDINGS.getRelation()).iterator();
            while (it.hasNext()) {
                linkedList.add(next.getPackagePart().getRelatedPart(it.next()));
            }
            Iterator<PackageRelationship> it2 = next.getPackagePart().getRelationshipsByType(XSSFRelation.PACKEMBEDDINGS.getRelation()).iterator();
            while (it2.hasNext()) {
                linkedList.add(next.getPackagePart().getRelatedPart(it2.next()));
            }
        }
        return linkedList;
    }

    @NotImplemented
    public boolean isHidden() {
        throw new RuntimeException("Not implemented yet");
    }

    @NotImplemented
    public void setHidden(boolean z) {
        throw new RuntimeException("Not implemented yet");
    }

    public boolean isSheetHidden(int i) {
        validateSheetIndex(i);
        return this.sheets.get(i).sheet.getState() == STSheetState.HIDDEN;
    }

    public boolean isSheetVeryHidden(int i) {
        validateSheetIndex(i);
        return this.sheets.get(i).sheet.getState() == STSheetState.VERY_HIDDEN;
    }

    public SheetVisibility getSheetVisibility(int i) {
        validateSheetIndex(i);
        STSheetState.Enum state = this.sheets.get(i).sheet.getState();
        if (state == STSheetState.VISIBLE) {
            return SheetVisibility.VISIBLE;
        }
        if (state == STSheetState.HIDDEN) {
            return SheetVisibility.HIDDEN;
        }
        if (state == STSheetState.VERY_HIDDEN) {
            return SheetVisibility.VERY_HIDDEN;
        }
        throw new IllegalArgumentException("This should never happen");
    }

    public void setSheetHidden(int i, boolean z) {
        setSheetVisibility(i, z ? SheetVisibility.HIDDEN : SheetVisibility.VISIBLE);
    }

    public void setSheetVisibility(int i, SheetVisibility sheetVisibility) {
        validateSheetIndex(i);
        CTSheet cTSheet = this.sheets.get(i).sheet;
        int i2 = AnonymousClass1.$SwitchMap$org$apache$poi$ss$usermodel$SheetVisibility[sheetVisibility.ordinal()];
        if (i2 == 1) {
            cTSheet.setState(STSheetState.VISIBLE);
        } else if (i2 == 2) {
            cTSheet.setState(STSheetState.HIDDEN);
        } else if (i2 == 3) {
            cTSheet.setState(STSheetState.VERY_HIDDEN);
        } else {
            throw new IllegalArgumentException("This should never happen");
        }
    }

    /* renamed from: org.apache.poi.xssf.usermodel.XSSFWorkbook$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$org$apache$poi$ss$usermodel$SheetVisibility;

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|(3:5|6|8)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        static {
            /*
                org.apache.poi.ss.usermodel.SheetVisibility[] r0 = org.apache.poi.ss.usermodel.SheetVisibility.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$org$apache$poi$ss$usermodel$SheetVisibility = r0
                org.apache.poi.ss.usermodel.SheetVisibility r1 = org.apache.poi.ss.usermodel.SheetVisibility.VISIBLE     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$org$apache$poi$ss$usermodel$SheetVisibility     // Catch:{ NoSuchFieldError -> 0x001d }
                org.apache.poi.ss.usermodel.SheetVisibility r1 = org.apache.poi.ss.usermodel.SheetVisibility.HIDDEN     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$org$apache$poi$ss$usermodel$SheetVisibility     // Catch:{ NoSuchFieldError -> 0x0028 }
                org.apache.poi.ss.usermodel.SheetVisibility r1 = org.apache.poi.ss.usermodel.SheetVisibility.VERY_HIDDEN     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.xssf.usermodel.XSSFWorkbook.AnonymousClass1.<clinit>():void");
        }
    }

    /* access modifiers changed from: protected */
    public void onDeleteFormula(XSSFCell xSSFCell) {
        if (this.calcChain != null) {
            this.calcChain.removeItem((int) xSSFCell.getSheet().sheet.getSheetId(), xSSFCell.getReference());
        }
    }

    @Internal
    public CalculationChain getCalculationChain() {
        return this.calcChain;
    }

    @Internal
    public List<ExternalLinksTable> getExternalLinksTable() {
        return this.externalLinks;
    }

    public Collection<XSSFMap> getCustomXMLMappings() {
        MapInfo mapInfo2 = this.mapInfo;
        return mapInfo2 == null ? new ArrayList() : mapInfo2.getAllXSSFMaps();
    }

    @Internal
    public MapInfo getMapInfo() {
        return this.mapInfo;
    }

    public int linkExternalWorkbook(String str, Workbook workbook2) {
        int i;
        if (getCreationHelper().getReferencedWorkbooks().containsKey(str)) {
            Iterator<POIXMLDocumentPart.RelationPart> it = getRelationParts().iterator();
            while (true) {
                if (!it.hasNext()) {
                    i = -1;
                    break;
                }
                POIXMLDocumentPart.RelationPart next = it.next();
                if ((next.getDocumentPart() instanceof ExternalLinksTable) && ((ExternalLinksTable) next.getDocumentPart()).getLinkedFileName().equals(str)) {
                    String uri = next.getRelationship().getTargetURI().toString();
                    String defaultFileName = XSSFRelation.EXTERNAL_LINKS.getDefaultFileName();
                    i = Integer.parseInt(uri.substring(defaultFileName.indexOf(35), defaultFileName.indexOf(46)));
                    break;
                }
            }
        } else {
            i = getNextPartNumber(XSSFRelation.EXTERNAL_LINKS, getPackagePart().getPackage().getPartsByContentType(XSSFRelation.EXTERNAL_LINKS.getContentType()).size() + 1);
            POIXMLDocumentPart.RelationPart createRelationship = createRelationship(XSSFRelation.EXTERNAL_LINKS, this.xssfFactory, i, false);
            ExternalLinksTable externalLinksTable = (ExternalLinksTable) createRelationship.getDocumentPart();
            externalLinksTable.setLinkedFileName(str);
            getExternalLinksTable().add(externalLinksTable);
            getCTWorkbook().addNewExternalReferences().addNewExternalReference().setId(createRelationship.getRelationship().getId());
        }
        getCreationHelper().addExternalWorkbook(str, workbook2);
        return i;
    }

    public boolean isStructureLocked() {
        return workbookProtectionPresent() && this.workbook.getWorkbookProtection().getLockStructure();
    }

    public boolean isWindowsLocked() {
        return workbookProtectionPresent() && this.workbook.getWorkbookProtection().getLockWindows();
    }

    public boolean isRevisionLocked() {
        return workbookProtectionPresent() && this.workbook.getWorkbookProtection().getLockRevision();
    }

    public void lockStructure() {
        safeGetWorkbookProtection().setLockStructure(true);
    }

    public void unLockStructure() {
        safeGetWorkbookProtection().setLockStructure(false);
    }

    public void lockWindows() {
        safeGetWorkbookProtection().setLockWindows(true);
    }

    public void unLockWindows() {
        safeGetWorkbookProtection().setLockWindows(false);
    }

    public void lockRevision() {
        safeGetWorkbookProtection().setLockRevision(true);
    }

    public void unLockRevision() {
        safeGetWorkbookProtection().setLockRevision(false);
    }

    public void setWorkbookPassword(String str, HashAlgorithm hashAlgorithm) {
        if (str != null || workbookProtectionPresent()) {
            XSSFPasswordHelper.setPassword(safeGetWorkbookProtection(), str, hashAlgorithm, "workbook");
        }
    }

    public boolean validateWorkbookPassword(String str) {
        if (!workbookProtectionPresent()) {
            return str == null;
        }
        return XSSFPasswordHelper.validatePassword(safeGetWorkbookProtection(), str, "workbook");
    }

    public void setRevisionsPassword(String str, HashAlgorithm hashAlgorithm) {
        if (str != null || workbookProtectionPresent()) {
            XSSFPasswordHelper.setPassword(safeGetWorkbookProtection(), str, hashAlgorithm, "revisions");
        }
    }

    public boolean validateRevisionsPassword(String str) {
        if (!workbookProtectionPresent()) {
            return str == null;
        }
        return XSSFPasswordHelper.validatePassword(safeGetWorkbookProtection(), str, "revisions");
    }

    public void unLock() {
        if (workbookProtectionPresent()) {
            this.workbook.unsetWorkbookProtection();
        }
    }

    private boolean workbookProtectionPresent() {
        return this.workbook.isSetWorkbookProtection();
    }

    private CTWorkbookProtection safeGetWorkbookProtection() {
        if (!workbookProtectionPresent()) {
            return this.workbook.addNewWorkbookProtection();
        }
        return this.workbook.getWorkbookProtection();
    }

    /* access modifiers changed from: package-private */
    public UDFFinder getUDFFinder() {
        return this._udfFinder;
    }

    public void addToolPack(UDFFinder uDFFinder) {
        this._udfFinder.add(uDFFinder);
    }

    public void setForceFormulaRecalculation(boolean z) {
        CTWorkbook cTWorkbook = getCTWorkbook();
        CTCalcPr calcPr = cTWorkbook.isSetCalcPr() ? cTWorkbook.getCalcPr() : cTWorkbook.addNewCalcPr();
        calcPr.setFullCalcOnLoad(z);
        if (z && calcPr.getCalcMode() == STCalcMode.MANUAL) {
            calcPr.setCalcMode(STCalcMode.AUTO);
        }
    }

    public boolean getForceFormulaRecalculation() {
        CTCalcPr calcPr = getCTWorkbook().getCalcPr();
        return calcPr != null && calcPr.isSetFullCalcOnLoad() && calcPr.getFullCalcOnLoad();
    }

    /* access modifiers changed from: protected */
    public CTPivotCache addPivotCache(String str) {
        CTPivotCaches cTPivotCaches;
        CTWorkbook cTWorkbook = getCTWorkbook();
        if (cTWorkbook.isSetPivotCaches()) {
            cTPivotCaches = cTWorkbook.getPivotCaches();
        } else {
            cTPivotCaches = cTWorkbook.addNewPivotCaches();
        }
        CTPivotCache addNewPivotCache = cTPivotCaches.addNewPivotCache();
        addNewPivotCache.setCacheId((long) (getPivotTables().size() + 1));
        addNewPivotCache.setId(str);
        if (this.pivotCaches == null) {
            this.pivotCaches = new ArrayList();
        }
        this.pivotCaches.add(addNewPivotCache);
        return addNewPivotCache;
    }

    public List<XSSFPivotTable> getPivotTables() {
        return this.pivotTables;
    }

    /* access modifiers changed from: protected */
    public void setPivotTables(List<XSSFPivotTable> list) {
        this.pivotTables = list;
    }

    public XSSFWorkbookType getWorkbookType() {
        return isMacroEnabled() ? XSSFWorkbookType.XLSM : XSSFWorkbookType.XLSX;
    }

    public void setWorkbookType(XSSFWorkbookType xSSFWorkbookType) {
        try {
            getPackagePart().setContentType(xSSFWorkbookType.getContentType());
        } catch (InvalidFormatException e) {
            throw new POIXMLException((Throwable) e);
        }
    }

    public void setVBAProject(InputStream inputStream) throws IOException {
        OutputStream outputStream;
        if (!isMacroEnabled()) {
            setWorkbookType(XSSFWorkbookType.XLSM);
        }
        try {
            PackagePartName createPartName = PackagingURIHelper.createPartName(XSSFRelation.VBA_MACROS.getDefaultFileName());
            OPCPackage oPCPackage = getPackage();
            if (!oPCPackage.containPart(createPartName)) {
                outputStream = createRelationship(XSSFRelation.VBA_MACROS, this.xssfFactory).getPackagePart().getOutputStream();
            } else {
                outputStream = oPCPackage.getPart(createPartName).getOutputStream();
            }
            try {
                IOUtils.copy(inputStream, outputStream);
            } finally {
                IOUtils.closeQuietly(outputStream);
            }
        } catch (InvalidFormatException e) {
            throw new POIXMLException((Throwable) e);
        }
    }

    public void setVBAProject(XSSFWorkbook xSSFWorkbook) throws IOException, InvalidFormatException {
        InputStream contents;
        if (xSSFWorkbook.isMacroEnabled() && (contents = XSSFRelation.VBA_MACROS.getContents(xSSFWorkbook.getCorePart())) != null) {
            setVBAProject(contents);
        }
    }

    public SpreadsheetVersion getSpreadsheetVersion() {
        return SpreadsheetVersion.EXCEL2007;
    }

    public XSSFTable getTable(String str) {
        List<XSSFSheet> list;
        if (str == null || (list = this.sheets) == null) {
            return null;
        }
        for (XSSFSheet tables : list) {
            Iterator<XSSFTable> it = tables.getTables().iterator();
            while (true) {
                if (it.hasNext()) {
                    XSSFTable next = it.next();
                    if (str.equalsIgnoreCase(next.getName())) {
                        return next;
                    }
                }
            }
        }
        return null;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:20:0x005e, code lost:
        r7 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x005f, code lost:
        if (r3 != null) goto L_0x0061;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:?, code lost:
        r3.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0065, code lost:
        r3 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:?, code lost:
        r6.addSuppressed(r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x0069, code lost:
        throw r7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x006c, code lost:
        r6 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:?, code lost:
        r4.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x0071, code lost:
        r4 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:?, code lost:
        r3.addSuppressed(r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x0075, code lost:
        throw r6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x0078, code lost:
        r4 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:43:?, code lost:
        r5.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:0x007d, code lost:
        r5 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:0x007e, code lost:
        r3.addSuppressed(r5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:0x0081, code lost:
        throw r4;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int addOlePackage(byte[] r4, java.lang.String r5, java.lang.String r6, java.lang.String r7) throws java.io.IOException {
        /*
            r3 = this;
            org.apache.poi.xssf.usermodel.XSSFRelation r0 = org.apache.poi.xssf.usermodel.XSSFRelation.OLEEMBEDDINGS
            org.apache.poi.openxml4j.opc.OPCPackage r3 = r3.getPackage()
            java.lang.String r1 = r0.getDefaultFileName()     // Catch:{ InvalidFormatException -> 0x0082 }
            int r1 = r3.getUnusedPartIndex(r1)     // Catch:{ InvalidFormatException -> 0x0082 }
            java.lang.String r2 = r0.getFileName(r1)     // Catch:{ InvalidFormatException -> 0x0082 }
            org.apache.poi.openxml4j.opc.PackagePartName r2 = org.apache.poi.openxml4j.opc.PackagingURIHelper.createPartName((java.lang.String) r2)     // Catch:{ InvalidFormatException -> 0x0082 }
            java.lang.String r0 = r0.getContentType()
            org.apache.poi.openxml4j.opc.PackagePart r3 = r3.createPart(r2, r0)
            org.apache.poi.poifs.filesystem.Ole10Native r0 = new org.apache.poi.poifs.filesystem.Ole10Native
            r0.<init>(r5, r6, r7, r4)
            org.apache.commons.io.output.UnsynchronizedByteArrayOutputStream r5 = new org.apache.commons.io.output.UnsynchronizedByteArrayOutputStream
            int r4 = r4.length
            int r4 = r4 + 500
            r5.<init>(r4)
            r0.writeOut(r5)     // Catch:{ all -> 0x0076 }
            org.apache.poi.poifs.filesystem.POIFSFileSystem r4 = new org.apache.poi.poifs.filesystem.POIFSFileSystem     // Catch:{ all -> 0x0076 }
            r4.<init>()     // Catch:{ all -> 0x0076 }
            org.apache.poi.poifs.filesystem.DirectoryNode r6 = r4.getRoot()     // Catch:{ all -> 0x006a }
            java.lang.String r7 = "\u0001Ole10Native"
            java.io.InputStream r0 = r5.toInputStream()     // Catch:{ all -> 0x006a }
            r6.createDocument(r7, r0)     // Catch:{ all -> 0x006a }
            org.apache.poi.hpsf.ClassIDPredefined r7 = org.apache.poi.hpsf.ClassIDPredefined.OLE_V1_PACKAGE     // Catch:{ all -> 0x006a }
            org.apache.poi.hpsf.ClassID r7 = r7.getClassID()     // Catch:{ all -> 0x006a }
            r6.setStorageClsid(r7)     // Catch:{ all -> 0x006a }
            java.io.OutputStream r3 = r3.getOutputStream()     // Catch:{ all -> 0x006a }
            r4.writeFilesystem(r3)     // Catch:{ all -> 0x005c }
            if (r3 == 0) goto L_0x0055
            r3.close()     // Catch:{ all -> 0x006a }
        L_0x0055:
            r4.close()     // Catch:{ all -> 0x0076 }
            r5.close()
            return r1
        L_0x005c:
            r6 = move-exception
            throw r6     // Catch:{ all -> 0x005e }
        L_0x005e:
            r7 = move-exception
            if (r3 == 0) goto L_0x0069
            r3.close()     // Catch:{ all -> 0x0065 }
            goto L_0x0069
        L_0x0065:
            r3 = move-exception
            r6.addSuppressed(r3)     // Catch:{ all -> 0x006a }
        L_0x0069:
            throw r7     // Catch:{ all -> 0x006a }
        L_0x006a:
            r3 = move-exception
            throw r3     // Catch:{ all -> 0x006c }
        L_0x006c:
            r6 = move-exception
            r4.close()     // Catch:{ all -> 0x0071 }
            goto L_0x0075
        L_0x0071:
            r4 = move-exception
            r3.addSuppressed(r4)     // Catch:{ all -> 0x0076 }
        L_0x0075:
            throw r6     // Catch:{ all -> 0x0076 }
        L_0x0076:
            r3 = move-exception
            throw r3     // Catch:{ all -> 0x0078 }
        L_0x0078:
            r4 = move-exception
            r5.close()     // Catch:{ all -> 0x007d }
            goto L_0x0081
        L_0x007d:
            r5 = move-exception
            r3.addSuppressed(r5)
        L_0x0081:
            throw r4
        L_0x0082:
            r3 = move-exception
            java.io.IOException r4 = new java.io.IOException
            java.lang.String r5 = "ole object name not recognized"
            r4.<init>(r5, r3)
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.xssf.usermodel.XSSFWorkbook.addOlePackage(byte[], java.lang.String, java.lang.String, java.lang.String):int");
    }

    public void setCellFormulaValidation(boolean z) {
        this.cellFormulaValidation = z;
    }

    public boolean getCellFormulaValidation() {
        return this.cellFormulaValidation;
    }

    public XSSFEvaluationWorkbook createEvaluationWorkbook() {
        return XSSFEvaluationWorkbook.create(this);
    }
}
