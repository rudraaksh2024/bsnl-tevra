package org.apache.poi.xssf.model;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import org.apache.poi.ooxml.POIXMLDocumentPart;
import org.apache.poi.ooxml.POIXMLTypeLoader;
import org.apache.poi.openxml4j.opc.PackageRelationship;
import org.apache.poi.openxml4j.opc.PackageRelationshipTypes;
import org.apache.poi.openxml4j.opc.TargetMode;
import org.apache.poi.ss.usermodel.Name;
import org.apache.poi.util.Internal;
import org.apache.poi.util.Removal;
import org.apache.xmlbeans.XmlException;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTExternalBook;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTExternalCell;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTExternalDefinedName;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTExternalLink;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTExternalRow;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTExternalSheetData;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTExternalSheetDataSet;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTExternalSheetName;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTExternalSheetNames;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.ExternalLinkDocument;

public class ExternalLinksTable extends POIXMLDocumentPart {
    private CTExternalLink link;

    public ExternalLinksTable() {
        CTExternalLink newInstance = CTExternalLink.Factory.newInstance();
        this.link = newInstance;
        newInstance.addNewExternalBook();
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
    public ExternalLinksTable(org.apache.poi.openxml4j.opc.PackagePart r2) throws java.io.IOException {
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
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.xssf.model.ExternalLinksTable.<init>(org.apache.poi.openxml4j.opc.PackagePart):void");
    }

    public void readFrom(InputStream inputStream) throws IOException {
        try {
            this.link = ExternalLinkDocument.Factory.parse(inputStream, POIXMLTypeLoader.DEFAULT_XML_OPTIONS).getExternalLink();
        } catch (XmlException e) {
            throw new IOException(e.getLocalizedMessage());
        }
    }

    public void writeTo(OutputStream outputStream) throws IOException {
        ExternalLinkDocument newInstance = ExternalLinkDocument.Factory.newInstance();
        newInstance.setExternalLink(this.link);
        newInstance.save(outputStream, POIXMLTypeLoader.DEFAULT_XML_OPTIONS);
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:11:?, code lost:
        r0.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x001a, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x001b, code lost:
        r2.addSuppressed(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x001e, code lost:
        throw r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0013, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0014, code lost:
        if (r0 != null) goto L_0x0016;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void commit() throws java.io.IOException {
        /*
            r2 = this;
            org.apache.poi.openxml4j.opc.PackagePart r0 = r2.getPackagePart()
            java.io.OutputStream r0 = r0.getOutputStream()
            r2.writeTo(r0)     // Catch:{ all -> 0x0011 }
            if (r0 == 0) goto L_0x0010
            r0.close()
        L_0x0010:
            return
        L_0x0011:
            r2 = move-exception
            throw r2     // Catch:{ all -> 0x0013 }
        L_0x0013:
            r1 = move-exception
            if (r0 == 0) goto L_0x001e
            r0.close()     // Catch:{ all -> 0x001a }
            goto L_0x001e
        L_0x001a:
            r0 = move-exception
            r2.addSuppressed(r0)
        L_0x001e:
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.xssf.model.ExternalLinksTable.commit():void");
    }

    @Internal
    @Removal(version = "6.0.0")
    public CTExternalLink getCTExternalLink() {
        return this.link;
    }

    public String getLinkedFileName() {
        PackageRelationship relationship = getPackagePart().getRelationship(this.link.getExternalBook().getId());
        if (relationship == null || relationship.getTargetMode() != TargetMode.EXTERNAL) {
            return null;
        }
        return relationship.getTargetURI().toString();
    }

    public void setLinkedFileName(String str) {
        String id = this.link.getExternalBook().getId();
        if (id != null && !id.isEmpty()) {
            getPackagePart().removeRelationship(id);
        }
        this.link.getExternalBook().setId(getPackagePart().addExternalRelationship(str, PackageRelationshipTypes.EXTERNAL_LINK_PATH).getId());
    }

    public List<String> getSheetNames() {
        CTExternalSheetName[] sheetNameArray = this.link.getExternalBook().getSheetNames().getSheetNameArray();
        ArrayList arrayList = new ArrayList(sheetNameArray.length);
        for (CTExternalSheetName val : sheetNameArray) {
            arrayList.add(val.getVal());
        }
        return arrayList;
    }

    public List<Name> getDefinedNames() {
        CTExternalDefinedName[] definedNameArray = this.link.getExternalBook().getDefinedNames().getDefinedNameArray();
        ArrayList arrayList = new ArrayList(definedNameArray.length);
        for (CTExternalDefinedName externalName : definedNameArray) {
            arrayList.add(new ExternalName(externalName));
        }
        return arrayList;
    }

    public void cacheData(String str, long j, String str2, String str3) {
        synchronized (this.link.getExternalBook().monitor()) {
            getCell(getRow(getSheetData(getSheetNameIndex(str)), j), str2).setV(str3);
        }
    }

    private int getSheetNameIndex(String str) {
        CTExternalBook externalBook = this.link.getExternalBook();
        CTExternalSheetNames sheetNames = externalBook.getSheetNames();
        if (sheetNames == null) {
            sheetNames = externalBook.addNewSheetNames();
        }
        int i = 0;
        while (true) {
            if (i >= sheetNames.sizeOfSheetNameArray()) {
                i = -1;
                break;
            } else if (sheetNames.getSheetNameArray(i).getVal().equals(str)) {
                break;
            } else {
                i++;
            }
        }
        if (i != -1) {
            return i;
        }
        sheetNames.addNewSheetName().setVal(str);
        return sheetNames.sizeOfSheetNameArray() - 1;
    }

    private CTExternalSheetData getSheetData(int i) {
        CTExternalSheetData cTExternalSheetData;
        CTExternalSheetDataSet sheetDataSet = this.link.getExternalBook().getSheetDataSet();
        if (sheetDataSet == null) {
            sheetDataSet = this.link.getExternalBook().addNewSheetDataSet();
        }
        CTExternalSheetData[] sheetDataArray = sheetDataSet.getSheetDataArray();
        int length = sheetDataArray.length;
        int i2 = 0;
        while (true) {
            if (i2 >= length) {
                cTExternalSheetData = null;
                break;
            }
            cTExternalSheetData = sheetDataArray[i2];
            if (cTExternalSheetData.getSheetId() == ((long) i)) {
                break;
            }
            i2++;
        }
        if (cTExternalSheetData != null) {
            return cTExternalSheetData;
        }
        CTExternalSheetData addNewSheetData = sheetDataSet.addNewSheetData();
        addNewSheetData.setSheetId((long) i);
        return addNewSheetData;
    }

    private CTExternalRow getRow(CTExternalSheetData cTExternalSheetData, long j) {
        for (CTExternalRow cTExternalRow : cTExternalSheetData.getRowArray()) {
            if (cTExternalRow.getR() == j) {
                return cTExternalRow;
            }
        }
        CTExternalRow addNewRow = cTExternalSheetData.addNewRow();
        addNewRow.setR(j);
        return addNewRow;
    }

    private CTExternalCell getCell(CTExternalRow cTExternalRow, String str) {
        for (CTExternalCell cTExternalCell : cTExternalRow.getCellArray()) {
            if (cTExternalCell.getR().equals(str)) {
                return cTExternalCell;
            }
        }
        CTExternalCell addNewCell = cTExternalRow.addNewCell();
        addNewCell.setR(str);
        return addNewCell;
    }

    protected class ExternalName implements Name {
        private final CTExternalDefinedName name;

        public String getComment() {
            return null;
        }

        public boolean isDeleted() {
            return false;
        }

        public boolean isFunctionName() {
            return false;
        }

        public boolean isHidden() {
            return false;
        }

        protected ExternalName(CTExternalDefinedName cTExternalDefinedName) {
            this.name = cTExternalDefinedName;
        }

        public String getNameName() {
            return this.name.getName();
        }

        public void setNameName(String str) {
            this.name.setName(str);
        }

        public String getSheetName() {
            int sheetIndex = getSheetIndex();
            if (sheetIndex >= 0) {
                return ExternalLinksTable.this.getSheetNames().get(sheetIndex);
            }
            return null;
        }

        public int getSheetIndex() {
            if (this.name.isSetSheetId()) {
                return (int) this.name.getSheetId();
            }
            return -1;
        }

        public void setSheetIndex(int i) {
            this.name.setSheetId((long) i);
        }

        public String getRefersToFormula() {
            return this.name.getRefersTo().substring(1);
        }

        public void setRefersToFormula(String str) {
            this.name.setRefersTo("=" + str);
        }

        public void setComment(String str) {
            throw new IllegalStateException("Not Supported");
        }

        public void setFunction(boolean z) {
            throw new IllegalStateException("Not Supported");
        }
    }
}
