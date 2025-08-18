package org.apache.poi.xssf.usermodel;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import javax.xml.namespace.QName;
import org.apache.poi.ooxml.POIXMLDocument;
import org.apache.poi.ooxml.POIXMLDocumentPart;
import org.apache.poi.ooxml.POIXMLTypeLoader;
import org.apache.poi.ss.SpreadsheetVersion;
import org.apache.poi.ss.usermodel.Name;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.AreaReference;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.util.Internal;
import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlOptions;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCacheField;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCacheFields;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotCacheDefinition;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorksheetSource;

public class XSSFPivotCacheDefinition extends POIXMLDocumentPart {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private CTPivotCacheDefinition ctPivotCacheDefinition;

    public XSSFPivotCacheDefinition() {
        this.ctPivotCacheDefinition = CTPivotCacheDefinition.Factory.newInstance();
        createDefaultValues();
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
    protected XSSFPivotCacheDefinition(org.apache.poi.openxml4j.opc.PackagePart r2) throws java.io.IOException {
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
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.xssf.usermodel.XSSFPivotCacheDefinition.<init>(org.apache.poi.openxml4j.opc.PackagePart):void");
    }

    public void readFrom(InputStream inputStream) throws IOException {
        try {
            XmlOptions xmlOptions = new XmlOptions(POIXMLTypeLoader.DEFAULT_XML_OPTIONS);
            xmlOptions.setLoadReplaceDocumentElement((QName) null);
            this.ctPivotCacheDefinition = CTPivotCacheDefinition.Factory.parse(inputStream, xmlOptions);
        } catch (XmlException e) {
            throw new IOException(e.getLocalizedMessage(), e);
        }
    }

    @Internal
    public CTPivotCacheDefinition getCTPivotCacheDefinition() {
        return this.ctPivotCacheDefinition;
    }

    private void createDefaultValues() {
        this.ctPivotCacheDefinition.setCreatedVersion(3);
        this.ctPivotCacheDefinition.setMinRefreshableVersion(3);
        this.ctPivotCacheDefinition.setRefreshedVersion(3);
        this.ctPivotCacheDefinition.setRefreshedBy(POIXMLDocument.DOCUMENT_CREATOR);
        this.ctPivotCacheDefinition.setRefreshedDate((double) new Date().getTime());
        this.ctPivotCacheDefinition.setRefreshOnLoad(true);
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:11:?, code lost:
        r0.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0037, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0038, code lost:
        r5.addSuppressed(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x003b, code lost:
        throw r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0030, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0031, code lost:
        if (r0 != null) goto L_0x0033;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void commit() throws java.io.IOException {
        /*
            r5 = this;
            org.apache.poi.openxml4j.opc.PackagePart r0 = r5.getPackagePart()
            java.io.OutputStream r0 = r0.getOutputStream()
            org.apache.xmlbeans.XmlOptions r1 = new org.apache.xmlbeans.XmlOptions     // Catch:{ all -> 0x002e }
            org.apache.xmlbeans.XmlOptions r2 = org.apache.poi.ooxml.POIXMLTypeLoader.DEFAULT_XML_OPTIONS     // Catch:{ all -> 0x002e }
            r1.<init>(r2)     // Catch:{ all -> 0x002e }
            javax.xml.namespace.QName r2 = new javax.xml.namespace.QName     // Catch:{ all -> 0x002e }
            org.apache.xmlbeans.SchemaType r3 = org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotCacheDefinition.type     // Catch:{ all -> 0x002e }
            javax.xml.namespace.QName r3 = r3.getName()     // Catch:{ all -> 0x002e }
            java.lang.String r3 = r3.getNamespaceURI()     // Catch:{ all -> 0x002e }
            java.lang.String r4 = "pivotCacheDefinition"
            r2.<init>(r3, r4)     // Catch:{ all -> 0x002e }
            r1.setSaveSyntheticDocumentElement(r2)     // Catch:{ all -> 0x002e }
            org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotCacheDefinition r5 = r5.ctPivotCacheDefinition     // Catch:{ all -> 0x002e }
            r5.save((java.io.OutputStream) r0, (org.apache.xmlbeans.XmlOptions) r1)     // Catch:{ all -> 0x002e }
            if (r0 == 0) goto L_0x002d
            r0.close()
        L_0x002d:
            return
        L_0x002e:
            r5 = move-exception
            throw r5     // Catch:{ all -> 0x0030 }
        L_0x0030:
            r1 = move-exception
            if (r0 == 0) goto L_0x003b
            r0.close()     // Catch:{ all -> 0x0037 }
            goto L_0x003b
        L_0x0037:
            r0 = move-exception
            r5.addSuppressed(r0)
        L_0x003b:
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.xssf.usermodel.XSSFPivotCacheDefinition.commit():void");
    }

    public AreaReference getPivotArea(Workbook workbook) throws IllegalArgumentException {
        CTWorksheetSource worksheetSource = this.ctPivotCacheDefinition.getCacheSource().getWorksheetSource();
        String ref = worksheetSource.getRef();
        String name = worksheetSource.getName();
        if (ref == null && name == null) {
            throw new IllegalArgumentException("Pivot cache must reference an area, named range, or table.");
        } else if (ref != null) {
            return new AreaReference(ref, SpreadsheetVersion.EXCEL2007);
        } else {
            Name name2 = workbook.getName(name);
            if (name2 != null) {
                return new AreaReference(name2.getRefersToFormula(), SpreadsheetVersion.EXCEL2007);
            }
            for (XSSFTable next : ((XSSFSheet) workbook.getSheet(worksheetSource.getSheet())).getTables()) {
                if (name.equals(next.getName())) {
                    return new AreaReference(next.getStartCellReference(), next.getEndCellReference(), SpreadsheetVersion.EXCEL2007);
                }
            }
            throw new IllegalArgumentException("Name '" + name + "' was not found.");
        }
    }

    /* access modifiers changed from: protected */
    public void createCacheFields(Sheet sheet) {
        CTCacheFields cTCacheFields;
        AreaReference pivotArea = getPivotArea(sheet.getWorkbook());
        CellReference firstCell = pivotArea.getFirstCell();
        CellReference lastCell = pivotArea.getLastCell();
        short col = lastCell.getCol();
        Row row = sheet.getRow(firstCell.getRow());
        if (this.ctPivotCacheDefinition.getCacheFields() != null) {
            cTCacheFields = this.ctPivotCacheDefinition.getCacheFields();
        } else {
            cTCacheFields = this.ctPivotCacheDefinition.addNewCacheFields();
        }
        for (int col2 = firstCell.getCol(); col2 <= col; col2++) {
            CTCacheField addNewCacheField = cTCacheFields.addNewCacheField();
            if (col2 == col) {
                cTCacheFields.setCount((long) cTCacheFields.sizeOfCacheFieldArray());
            }
            addNewCacheField.setNumFmtId(0);
            addNewCacheField.setName(row.getCell(col2).getStringCellValue());
            addNewCacheField.addNewSharedItems();
        }
    }
}
