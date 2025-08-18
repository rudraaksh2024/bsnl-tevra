package org.apache.poi.xssf.usermodel;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.xml.namespace.QName;
import org.apache.poi.ooxml.POIXMLDocumentPart;
import org.apache.poi.ooxml.POIXMLTypeLoader;
import org.apache.poi.ss.SpreadsheetVersion;
import org.apache.poi.ss.usermodel.DataConsolidateFunction;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.AreaReference;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.util.Internal;
import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlOptions;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCacheSource;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTColFields;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDataField;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDataFields;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTField;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTItems;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTLocation;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPageField;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPageFields;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotField;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotFields;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableStyle;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTRowFields;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorksheetSource;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.STAxis;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.STDataConsolidateFunction;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.STItemType;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.STSourceType;

public class XSSFPivotTable extends POIXMLDocumentPart {
    protected static final short CREATED_VERSION = 3;
    protected static final short MIN_REFRESHABLE_VERSION = 3;
    protected static final short UPDATED_VERSION = 3;
    private Sheet dataSheet;
    private Sheet parentSheet;
    private XSSFPivotCache pivotCache;
    private XSSFPivotCacheDefinition pivotCacheDefinition;
    private XSSFPivotCacheRecords pivotCacheRecords;
    private CTPivotTableDefinition pivotTableDefinition;

    protected interface PivotTableReferenceConfigurator {
        void configureReference(CTWorksheetSource cTWorksheetSource);
    }

    protected XSSFPivotTable() {
        this.pivotTableDefinition = CTPivotTableDefinition.Factory.newInstance();
        this.pivotCache = new XSSFPivotCache();
        this.pivotCacheDefinition = new XSSFPivotCacheDefinition();
        this.pivotCacheRecords = new XSSFPivotCacheRecords();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:?, code lost:
        r2.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0019, code lost:
        r2 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x001a, code lost:
        r1.addSuppressed(r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x001d, code lost:
        throw r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0012, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0013, code lost:
        if (r2 != null) goto L_0x0015;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    protected XSSFPivotTable(org.apache.poi.openxml4j.opc.PackagePart r2) throws java.io.IOException {
        /*
            r1 = this;
            r1.<init>((org.apache.poi.openxml4j.opc.PackagePart) r2)
            java.io.InputStream r2 = r2.getInputStream()
            r1.readFrom(r2)     // Catch:{ all -> 0x0010 }
            if (r2 == 0) goto L_0x000f
            r2.close()
        L_0x000f:
            return
        L_0x0010:
            r1 = move-exception
            throw r1     // Catch:{ all -> 0x0012 }
        L_0x0012:
            r0 = move-exception
            if (r2 == 0) goto L_0x001d
            r2.close()     // Catch:{ all -> 0x0019 }
            goto L_0x001d
        L_0x0019:
            r2 = move-exception
            r1.addSuppressed(r2)
        L_0x001d:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.xssf.usermodel.XSSFPivotTable.<init>(org.apache.poi.openxml4j.opc.PackagePart):void");
    }

    public void readFrom(InputStream inputStream) throws IOException {
        try {
            XmlOptions xmlOptions = new XmlOptions(POIXMLTypeLoader.DEFAULT_XML_OPTIONS);
            xmlOptions.setLoadReplaceDocumentElement((QName) null);
            this.pivotTableDefinition = CTPivotTableDefinition.Factory.parse(inputStream, xmlOptions);
        } catch (XmlException e) {
            throw new IOException(e.getLocalizedMessage());
        }
    }

    public void setPivotCache(XSSFPivotCache xSSFPivotCache) {
        this.pivotCache = xSSFPivotCache;
    }

    public XSSFPivotCache getPivotCache() {
        return this.pivotCache;
    }

    public Sheet getParentSheet() {
        return this.parentSheet;
    }

    public void setParentSheet(XSSFSheet xSSFSheet) {
        this.parentSheet = xSSFSheet;
    }

    @Internal
    public CTPivotTableDefinition getCTPivotTableDefinition() {
        return this.pivotTableDefinition;
    }

    @Internal
    public void setCTPivotTableDefinition(CTPivotTableDefinition cTPivotTableDefinition) {
        this.pivotTableDefinition = cTPivotTableDefinition;
    }

    public XSSFPivotCacheDefinition getPivotCacheDefinition() {
        return this.pivotCacheDefinition;
    }

    public void setPivotCacheDefinition(XSSFPivotCacheDefinition xSSFPivotCacheDefinition) {
        this.pivotCacheDefinition = xSSFPivotCacheDefinition;
    }

    public XSSFPivotCacheRecords getPivotCacheRecords() {
        return this.pivotCacheRecords;
    }

    public void setPivotCacheRecords(XSSFPivotCacheRecords xSSFPivotCacheRecords) {
        this.pivotCacheRecords = xSSFPivotCacheRecords;
    }

    public Sheet getDataSheet() {
        return this.dataSheet;
    }

    private void setDataSheet(Sheet sheet) {
        this.dataSheet = sheet;
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:11:?, code lost:
        r1.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0037, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0038, code lost:
        r4.addSuppressed(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x003b, code lost:
        throw r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0030, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0031, code lost:
        if (r1 != null) goto L_0x0033;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void commit() throws java.io.IOException {
        /*
            r4 = this;
            org.apache.xmlbeans.XmlOptions r0 = new org.apache.xmlbeans.XmlOptions
            org.apache.xmlbeans.XmlOptions r1 = org.apache.poi.ooxml.POIXMLTypeLoader.DEFAULT_XML_OPTIONS
            r0.<init>(r1)
            javax.xml.namespace.QName r1 = new javax.xml.namespace.QName
            org.apache.xmlbeans.SchemaType r2 = org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition.type
            javax.xml.namespace.QName r2 = r2.getName()
            java.lang.String r2 = r2.getNamespaceURI()
            java.lang.String r3 = "pivotTableDefinition"
            r1.<init>(r2, r3)
            r0.setSaveSyntheticDocumentElement(r1)
            org.apache.poi.openxml4j.opc.PackagePart r1 = r4.getPackagePart()
            java.io.OutputStream r1 = r1.getOutputStream()
            org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition r4 = r4.pivotTableDefinition     // Catch:{ all -> 0x002e }
            r4.save((java.io.OutputStream) r1, (org.apache.xmlbeans.XmlOptions) r0)     // Catch:{ all -> 0x002e }
            if (r1 == 0) goto L_0x002d
            r1.close()
        L_0x002d:
            return
        L_0x002e:
            r4 = move-exception
            throw r4     // Catch:{ all -> 0x0030 }
        L_0x0030:
            r0 = move-exception
            if (r1 == 0) goto L_0x003b
            r1.close()     // Catch:{ all -> 0x0037 }
            goto L_0x003b
        L_0x0037:
            r1 = move-exception
            r4.addSuppressed(r1)
        L_0x003b:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.xssf.usermodel.XSSFPivotTable.commit():void");
    }

    /* access modifiers changed from: protected */
    public void setDefaultPivotTableDefinition() {
        this.pivotTableDefinition.setMultipleFieldFilters(false);
        this.pivotTableDefinition.setIndent(0);
        this.pivotTableDefinition.setCreatedVersion(3);
        this.pivotTableDefinition.setMinRefreshableVersion(3);
        this.pivotTableDefinition.setUpdatedVersion(3);
        this.pivotTableDefinition.setItemPrintTitles(true);
        this.pivotTableDefinition.setUseAutoFormatting(true);
        this.pivotTableDefinition.setApplyNumberFormats(false);
        this.pivotTableDefinition.setApplyWidthHeightFormats(true);
        this.pivotTableDefinition.setApplyAlignmentFormats(false);
        this.pivotTableDefinition.setApplyPatternFormats(false);
        this.pivotTableDefinition.setApplyFontFormats(false);
        this.pivotTableDefinition.setApplyBorderFormats(false);
        this.pivotTableDefinition.setCacheId(this.pivotCache.getCTPivotCache().getCacheId());
        this.pivotTableDefinition.setName("PivotTable" + this.pivotTableDefinition.getCacheId());
        this.pivotTableDefinition.setDataCaption("Values");
        CTPivotTableStyle addNewPivotTableStyleInfo = this.pivotTableDefinition.addNewPivotTableStyleInfo();
        addNewPivotTableStyleInfo.setName("PivotStyleLight16");
        addNewPivotTableStyleInfo.setShowLastColumn(true);
        addNewPivotTableStyleInfo.setShowColStripes(false);
        addNewPivotTableStyleInfo.setShowRowStripes(false);
        addNewPivotTableStyleInfo.setShowColHeaders(true);
        addNewPivotTableStyleInfo.setShowRowHeaders(true);
    }

    /* access modifiers changed from: protected */
    public AreaReference getPivotArea() {
        return getPivotCacheDefinition().getPivotArea(getDataSheet().getWorkbook());
    }

    private void checkColumnIndex(int i) throws IndexOutOfBoundsException {
        AreaReference pivotArea = getPivotArea();
        int col = (pivotArea.getLastCell().getCol() - pivotArea.getFirstCell().getCol()) + 1;
        if (i < 0 || i >= col) {
            throw new IndexOutOfBoundsException("Column Index: " + i + ", Size: " + col);
        }
    }

    public void addRowLabel(int i) {
        CTRowFields cTRowFields;
        checkColumnIndex(i);
        AreaReference pivotArea = getPivotArea();
        int row = pivotArea.getLastCell().getRow() - pivotArea.getFirstCell().getRow();
        CTPivotFields pivotFields = this.pivotTableDefinition.getPivotFields();
        CTPivotField newInstance = CTPivotField.Factory.newInstance();
        CTItems addNewItems = newInstance.addNewItems();
        newInstance.setAxis(STAxis.AXIS_ROW);
        newInstance.setShowAll(false);
        for (int i2 = 0; i2 <= row; i2++) {
            addNewItems.addNewItem().setT(STItemType.DEFAULT);
        }
        addNewItems.setCount((long) addNewItems.sizeOfItemArray());
        pivotFields.setPivotFieldArray(i, newInstance);
        if (this.pivotTableDefinition.getRowFields() != null) {
            cTRowFields = this.pivotTableDefinition.getRowFields();
        } else {
            cTRowFields = this.pivotTableDefinition.addNewRowFields();
        }
        cTRowFields.addNewField().setX(i);
        cTRowFields.setCount((long) cTRowFields.sizeOfFieldArray());
    }

    public List<Integer> getRowLabelColumns() {
        if (this.pivotTableDefinition.getRowFields() == null) {
            return Collections.emptyList();
        }
        ArrayList arrayList = new ArrayList();
        for (CTField x : this.pivotTableDefinition.getRowFields().getFieldArray()) {
            arrayList.add(Integer.valueOf(x.getX()));
        }
        return arrayList;
    }

    public void addColLabel(int i, String str) {
        CTColFields cTColFields;
        checkColumnIndex(i);
        AreaReference pivotArea = getPivotArea();
        int row = pivotArea.getLastCell().getRow() - pivotArea.getFirstCell().getRow();
        CTPivotFields pivotFields = this.pivotTableDefinition.getPivotFields();
        CTPivotField newInstance = CTPivotField.Factory.newInstance();
        CTItems addNewItems = newInstance.addNewItems();
        newInstance.setAxis(STAxis.AXIS_COL);
        newInstance.setShowAll(false);
        if (str != null && !str.trim().isEmpty()) {
            newInstance.setNumFmtId((long) this.parentSheet.getWorkbook().createDataFormat().getFormat(str));
        }
        for (int i2 = 0; i2 <= row; i2++) {
            addNewItems.addNewItem().setT(STItemType.DEFAULT);
        }
        addNewItems.setCount((long) addNewItems.sizeOfItemArray());
        pivotFields.setPivotFieldArray(i, newInstance);
        if (this.pivotTableDefinition.getColFields() != null) {
            cTColFields = this.pivotTableDefinition.getColFields();
        } else {
            cTColFields = this.pivotTableDefinition.addNewColFields();
        }
        cTColFields.addNewField().setX(i);
        cTColFields.setCount((long) cTColFields.sizeOfFieldArray());
    }

    public void addColLabel(int i) {
        addColLabel(i, (String) null);
    }

    public List<Integer> getColLabelColumns() {
        if (this.pivotTableDefinition.getColFields() == null) {
            return Collections.emptyList();
        }
        ArrayList arrayList = new ArrayList();
        for (CTField x : this.pivotTableDefinition.getColFields().getFieldArray()) {
            arrayList.add(Integer.valueOf(x.getX()));
        }
        return arrayList;
    }

    public void addColumnLabel(DataConsolidateFunction dataConsolidateFunction, int i, String str, String str2) {
        CTColFields cTColFields;
        checkColumnIndex(i);
        addDataColumn(i, true);
        addDataField(dataConsolidateFunction, i, str, str2);
        if (this.pivotTableDefinition.getDataFields().getCount() == 2) {
            if (this.pivotTableDefinition.getColFields() != null) {
                cTColFields = this.pivotTableDefinition.getColFields();
            } else {
                cTColFields = this.pivotTableDefinition.addNewColFields();
            }
            cTColFields.addNewField().setX(-2);
            cTColFields.setCount((long) cTColFields.sizeOfFieldArray());
        }
    }

    public void addColumnLabel(DataConsolidateFunction dataConsolidateFunction, int i, String str) {
        addColumnLabel(dataConsolidateFunction, i, str, (String) null);
    }

    public void addColumnLabel(DataConsolidateFunction dataConsolidateFunction, int i) {
        addColumnLabel(dataConsolidateFunction, i, dataConsolidateFunction.getName(), (String) null);
    }

    private void addDataField(DataConsolidateFunction dataConsolidateFunction, int i, String str, String str2) {
        CTDataFields cTDataFields;
        checkColumnIndex(i);
        if (this.pivotTableDefinition.getDataFields() != null) {
            cTDataFields = this.pivotTableDefinition.getDataFields();
        } else {
            cTDataFields = this.pivotTableDefinition.addNewDataFields();
        }
        CTDataField addNewDataField = cTDataFields.addNewDataField();
        addNewDataField.setSubtotal(STDataConsolidateFunction.Enum.forInt(dataConsolidateFunction.getValue()));
        addNewDataField.setName(str);
        addNewDataField.setFld((long) i);
        if (str2 != null && !str2.trim().isEmpty()) {
            addNewDataField.setNumFmtId((long) this.parentSheet.getWorkbook().createDataFormat().getFormat(str2));
        }
        cTDataFields.setCount((long) cTDataFields.sizeOfDataFieldArray());
    }

    public void addDataColumn(int i, boolean z) {
        checkColumnIndex(i);
        CTPivotFields pivotFields = this.pivotTableDefinition.getPivotFields();
        CTPivotField newInstance = CTPivotField.Factory.newInstance();
        newInstance.setDataField(z);
        newInstance.setShowAll(false);
        pivotFields.setPivotFieldArray(i, newInstance);
    }

    public void addReportFilter(int i) {
        CTPageFields cTPageFields;
        checkColumnIndex(i);
        AreaReference pivotArea = getPivotArea();
        int row = pivotArea.getLastCell().getRow() - pivotArea.getFirstCell().getRow();
        CTLocation location = this.pivotTableDefinition.getLocation();
        AreaReference areaReference = new AreaReference(location.getRef(), SpreadsheetVersion.EXCEL2007);
        if (areaReference.getFirstCell().getRow() < 2) {
            location.setRef(new AreaReference(new CellReference(2, areaReference.getFirstCell().getCol()), new CellReference(3, areaReference.getFirstCell().getCol() + 1), SpreadsheetVersion.EXCEL2007).formatAsString());
        }
        CTPivotFields pivotFields = this.pivotTableDefinition.getPivotFields();
        CTPivotField newInstance = CTPivotField.Factory.newInstance();
        CTItems addNewItems = newInstance.addNewItems();
        newInstance.setAxis(STAxis.AXIS_PAGE);
        newInstance.setShowAll(false);
        for (int i2 = 0; i2 <= row; i2++) {
            addNewItems.addNewItem().setT(STItemType.DEFAULT);
        }
        addNewItems.setCount((long) addNewItems.sizeOfItemArray());
        pivotFields.setPivotFieldArray(i, newInstance);
        if (this.pivotTableDefinition.getPageFields() != null) {
            cTPageFields = this.pivotTableDefinition.getPageFields();
            this.pivotTableDefinition.setMultipleFieldFilters(true);
        } else {
            cTPageFields = this.pivotTableDefinition.addNewPageFields();
        }
        CTPageField addNewPageField = cTPageFields.addNewPageField();
        addNewPageField.setHier(-1);
        addNewPageField.setFld(i);
        cTPageFields.setCount((long) cTPageFields.sizeOfPageFieldArray());
        this.pivotTableDefinition.getLocation().setColPageCount(cTPageFields.getCount());
    }

    /* access modifiers changed from: protected */
    public void createSourceReferences(CellReference cellReference, Sheet sheet, PivotTableReferenceConfigurator pivotTableReferenceConfigurator) {
        CTLocation cTLocation;
        AreaReference areaReference = new AreaReference(cellReference, new CellReference(cellReference.getRow() + 1, cellReference.getCol() + 1), SpreadsheetVersion.EXCEL2007);
        if (this.pivotTableDefinition.getLocation() == null) {
            cTLocation = this.pivotTableDefinition.addNewLocation();
            cTLocation.setFirstDataCol(1);
            cTLocation.setFirstDataRow(1);
            cTLocation.setFirstHeaderRow(1);
        } else {
            cTLocation = this.pivotTableDefinition.getLocation();
        }
        cTLocation.setRef(areaReference.formatAsString());
        this.pivotTableDefinition.setLocation(cTLocation);
        CTCacheSource addNewCacheSource = getPivotCacheDefinition().getCTPivotCacheDefinition().addNewCacheSource();
        addNewCacheSource.setType(STSourceType.WORKSHEET);
        CTWorksheetSource addNewWorksheetSource = addNewCacheSource.addNewWorksheetSource();
        addNewWorksheetSource.setSheet(sheet.getSheetName());
        setDataSheet(sheet);
        pivotTableReferenceConfigurator.configureReference(addNewWorksheetSource);
        if (addNewWorksheetSource.getName() == null && addNewWorksheetSource.getRef() == null) {
            throw new IllegalArgumentException("Pivot table source area reference or name must be specified.");
        }
    }

    /* access modifiers changed from: protected */
    public void createDefaultDataColumns() {
        CTPivotFields cTPivotFields;
        if (this.pivotTableDefinition.getPivotFields() != null) {
            cTPivotFields = this.pivotTableDefinition.getPivotFields();
        } else {
            cTPivotFields = this.pivotTableDefinition.addNewPivotFields();
        }
        AreaReference pivotArea = getPivotArea();
        short col = pivotArea.getLastCell().getCol();
        for (int col2 = pivotArea.getFirstCell().getCol(); col2 <= col; col2++) {
            CTPivotField addNewPivotField = cTPivotFields.addNewPivotField();
            addNewPivotField.setDataField(false);
            addNewPivotField.setShowAll(false);
        }
        cTPivotFields.setCount((long) cTPivotFields.sizeOfPivotFieldArray());
    }
}
