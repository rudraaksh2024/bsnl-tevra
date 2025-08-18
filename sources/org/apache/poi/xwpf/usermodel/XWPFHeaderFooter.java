package org.apache.poi.xwpf.usermodel;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import org.apache.poi.ooxml.POIXMLDocumentPart;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.PackagePart;
import org.apache.poi.util.IOUtils;
import org.apache.poi.util.Internal;
import org.apache.xmlbeans.XmlCursor;
import org.apache.xmlbeans.XmlObject;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTHdrFtr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTP;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRow;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTbl;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTc;

public abstract class XWPFHeaderFooter extends POIXMLDocumentPart implements IBody {
    List<IBodyElement> bodyElements;
    XWPFDocument document;
    CTHdrFtr headerFooter;
    List<XWPFParagraph> paragraphs;
    List<XWPFPictureData> pictures;
    List<XWPFTable> tables;

    public POIXMLDocumentPart getOwner() {
        return this;
    }

    public POIXMLDocumentPart getPart() {
        return this;
    }

    XWPFHeaderFooter(XWPFDocument xWPFDocument, CTHdrFtr cTHdrFtr) {
        this.paragraphs = new ArrayList();
        this.tables = new ArrayList();
        this.pictures = new ArrayList();
        this.bodyElements = new ArrayList();
        xWPFDocument.getClass();
        this.document = xWPFDocument;
        this.headerFooter = cTHdrFtr;
        readHdrFtr();
    }

    protected XWPFHeaderFooter() {
        this.paragraphs = new ArrayList();
        this.tables = new ArrayList();
        this.pictures = new ArrayList();
        this.bodyElements = new ArrayList();
        this.headerFooter = CTHdrFtr.Factory.newInstance();
        readHdrFtr();
    }

    public XWPFHeaderFooter(POIXMLDocumentPart pOIXMLDocumentPart, PackagePart packagePart) {
        super(pOIXMLDocumentPart, packagePart);
        this.paragraphs = new ArrayList();
        this.tables = new ArrayList();
        this.pictures = new ArrayList();
        this.bodyElements = new ArrayList();
        XWPFDocument xWPFDocument = (XWPFDocument) getParent();
        this.document = xWPFDocument;
        xWPFDocument.getClass();
    }

    /* access modifiers changed from: protected */
    public void onDocumentRead() throws IOException {
        for (POIXMLDocumentPart next : getRelations()) {
            if (next instanceof XWPFPictureData) {
                XWPFPictureData xWPFPictureData = (XWPFPictureData) next;
                this.pictures.add(xWPFPictureData);
                this.document.registerPackagePictureData(xWPFPictureData);
            }
        }
    }

    @Internal
    public CTHdrFtr _getHdrFtr() {
        return this.headerFooter;
    }

    public List<IBodyElement> getBodyElements() {
        return Collections.unmodifiableList(this.bodyElements);
    }

    public List<XWPFParagraph> getParagraphs() {
        return Collections.unmodifiableList(this.paragraphs);
    }

    public List<XWPFTable> getTables() throws ArrayIndexOutOfBoundsException {
        return Collections.unmodifiableList(this.tables);
    }

    public String getText() {
        String text;
        StringBuilder sb = new StringBuilder(64);
        for (XWPFParagraph next : this.paragraphs) {
            if (!next.isEmpty() && (text = next.getText()) != null && text.length() > 0) {
                sb.append(text);
                sb.append(10);
            }
        }
        for (XWPFTable text2 : this.tables) {
            String text3 = text2.getText();
            if (text3 != null && text3.length() > 0) {
                sb.append(text3);
                sb.append(10);
            }
        }
        for (IBodyElement next2 : getBodyElements()) {
            if (next2 instanceof XWPFSDT) {
                sb.append(((XWPFSDT) next2).getContent().getText()).append(10);
            }
        }
        return sb.toString();
    }

    public void setHeaderFooter(CTHdrFtr cTHdrFtr) {
        this.headerFooter = cTHdrFtr;
        readHdrFtr();
    }

    public XWPFTable getTable(CTTbl cTTbl) {
        XWPFTable next;
        Iterator<XWPFTable> it = this.tables.iterator();
        while (it.hasNext() && (next = it.next()) != null) {
            if (next.getCTTbl().equals(cTTbl)) {
                return next;
            }
        }
        return null;
    }

    public XWPFParagraph getParagraph(CTP ctp) {
        for (XWPFParagraph next : this.paragraphs) {
            if (next.getCTP().equals(ctp)) {
                return next;
            }
        }
        return null;
    }

    public XWPFParagraph getParagraphArray(int i) {
        if (i < 0 || i >= this.paragraphs.size()) {
            return null;
        }
        return this.paragraphs.get(i);
    }

    public List<XWPFParagraph> getListParagraph() {
        return this.paragraphs;
    }

    public List<XWPFPictureData> getAllPictures() {
        return Collections.unmodifiableList(this.pictures);
    }

    public List<XWPFPictureData> getAllPackagePictures() {
        return this.document.getAllPackagePictures();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:15:0x003d, code lost:
        r3 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x003e, code lost:
        if (r0 != null) goto L_0x0040;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:?, code lost:
        r0.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0048, code lost:
        throw r3;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.String addPictureData(byte[] r3, int r4) throws org.apache.poi.openxml4j.exceptions.InvalidFormatException {
        /*
            r2 = this;
            org.apache.poi.xwpf.usermodel.XWPFDocument r0 = r2.document
            org.apache.poi.xwpf.usermodel.XWPFPictureData r0 = r0.findPackagePictureData(r3, r4)
            org.apache.poi.ooxml.POIXMLRelation[] r1 = org.apache.poi.xwpf.usermodel.XWPFPictureData.RELATIONS
            r1 = r1[r4]
            if (r0 != 0) goto L_0x0050
            org.apache.poi.xwpf.usermodel.XWPFDocument r0 = r2.document
            int r4 = r0.getNextPicNameNumber(r4)
            org.apache.poi.xwpf.usermodel.XWPFFactory r0 = org.apache.poi.xwpf.usermodel.XWPFFactory.getInstance()
            org.apache.poi.ooxml.POIXMLDocumentPart r4 = r2.createRelationship(r1, r0, r4)
            org.apache.poi.xwpf.usermodel.XWPFPictureData r4 = (org.apache.poi.xwpf.usermodel.XWPFPictureData) r4
            org.apache.poi.openxml4j.opc.PackagePart r0 = r4.getPackagePart()
            java.io.OutputStream r0 = r0.getOutputStream()     // Catch:{ IOException -> 0x0049 }
            r0.write(r3)     // Catch:{ all -> 0x003b }
            if (r0 == 0) goto L_0x002c
            r0.close()     // Catch:{ IOException -> 0x0049 }
        L_0x002c:
            org.apache.poi.xwpf.usermodel.XWPFDocument r3 = r2.document
            r3.registerPackagePictureData(r4)
            java.util.List<org.apache.poi.xwpf.usermodel.XWPFPictureData> r3 = r2.pictures
            r3.add(r4)
            java.lang.String r2 = r2.getRelationId(r4)
            return r2
        L_0x003b:
            r2 = move-exception
            throw r2     // Catch:{ all -> 0x003d }
        L_0x003d:
            r3 = move-exception
            if (r0 == 0) goto L_0x0048
            r0.close()     // Catch:{ all -> 0x0044 }
            goto L_0x0048
        L_0x0044:
            r4 = move-exception
            r2.addSuppressed(r4)     // Catch:{ IOException -> 0x0049 }
        L_0x0048:
            throw r3     // Catch:{ IOException -> 0x0049 }
        L_0x0049:
            r2 = move-exception
            org.apache.poi.ooxml.POIXMLException r3 = new org.apache.poi.ooxml.POIXMLException
            r3.<init>((java.lang.Throwable) r2)
            throw r3
        L_0x0050:
            java.util.List r3 = r2.getRelations()
            boolean r3 = r3.contains(r0)
            if (r3 != 0) goto L_0x006f
            r3 = 0
            org.apache.poi.xwpf.usermodel.XWPFRelation r4 = org.apache.poi.xwpf.usermodel.XWPFRelation.IMAGES
            org.apache.poi.ooxml.POIXMLDocumentPart$RelationPart r3 = r2.addRelation(r3, r4, r0)
            java.util.List<org.apache.poi.xwpf.usermodel.XWPFPictureData> r2 = r2.pictures
            r2.add(r0)
            org.apache.poi.openxml4j.opc.PackageRelationship r2 = r3.getRelationship()
            java.lang.String r2 = r2.getId()
            return r2
        L_0x006f:
            java.lang.String r2 = r2.getRelationId(r0)
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.xwpf.usermodel.XWPFHeaderFooter.addPictureData(byte[], int):java.lang.String");
    }

    public String addPictureData(InputStream inputStream, int i) throws InvalidFormatException, IOException {
        return addPictureData(IOUtils.toByteArrayWithMaxLength(inputStream, XWPFPictureData.getMaxImageSize()), i);
    }

    public XWPFPictureData getPictureDataByID(String str) {
        POIXMLDocumentPart relationById = getRelationById(str);
        if (relationById == null || !(relationById instanceof XWPFPictureData)) {
            return null;
        }
        return (XWPFPictureData) relationById;
    }

    public XWPFParagraph createParagraph() {
        XWPFParagraph xWPFParagraph = new XWPFParagraph(this.headerFooter.addNewP(), this);
        this.paragraphs.add(xWPFParagraph);
        this.bodyElements.add(xWPFParagraph);
        return xWPFParagraph;
    }

    public XWPFTable createTable(int i, int i2) {
        XWPFTable xWPFTable = new XWPFTable(this.headerFooter.addNewTbl(), this, i, i2);
        this.tables.add(xWPFTable);
        this.bodyElements.add(xWPFTable);
        return xWPFTable;
    }

    /* JADX INFO: finally extract failed */
    public void removeParagraph(XWPFParagraph xWPFParagraph) {
        if (this.paragraphs.contains(xWPFParagraph)) {
            XmlCursor newCursor = xWPFParagraph.getCTP().newCursor();
            try {
                newCursor.removeXml();
                newCursor.dispose();
                this.paragraphs.remove(xWPFParagraph);
                this.bodyElements.remove(xWPFParagraph);
            } catch (Throwable th) {
                newCursor.dispose();
                throw th;
            }
        }
    }

    /* JADX INFO: finally extract failed */
    public void removeTable(XWPFTable xWPFTable) {
        if (this.tables.contains(xWPFTable)) {
            XmlCursor newCursor = xWPFTable.getCTTbl().newCursor();
            try {
                newCursor.removeXml();
                newCursor.dispose();
                this.tables.remove(xWPFTable);
                this.bodyElements.remove(xWPFTable);
            } catch (Throwable th) {
                newCursor.dispose();
                throw th;
            }
        }
    }

    /* JADX INFO: finally extract failed */
    public void clearHeaderFooter() {
        XmlCursor newCursor = this.headerFooter.newCursor();
        try {
            newCursor.removeXmlContents();
            newCursor.dispose();
            this.paragraphs.clear();
            this.tables.clear();
            this.bodyElements.clear();
        } catch (Throwable th) {
            newCursor.dispose();
            throw th;
        }
    }

    public XWPFParagraph insertNewParagraph(XmlCursor xmlCursor) {
        boolean z;
        XmlObject xmlObject = null;
        if (!isCursorInHdrF(xmlCursor)) {
            return null;
        }
        xmlCursor.beginElement("p", CTP.type.getName().getNamespaceURI());
        xmlCursor.toParent();
        CTP ctp = (CTP) xmlCursor.getObject();
        XWPFParagraph xWPFParagraph = new XWPFParagraph(ctp, this);
        while (true) {
            z = xmlObject instanceof CTP;
            if (z || !xmlCursor.toPrevSibling()) {
                int i = 0;
            } else {
                xmlObject = xmlCursor.getObject();
            }
        }
        int i2 = 0;
        if (!z || xmlObject == ctp) {
            this.paragraphs.add(0, xWPFParagraph);
        } else {
            this.paragraphs.add(this.paragraphs.indexOf(getParagraph((CTP) xmlObject)) + 1, xWPFParagraph);
        }
        XmlCursor newCursor = ctp.newCursor();
        try {
            xmlCursor.toCursor(newCursor);
            while (xmlCursor.toPrevSibling()) {
                XmlObject object = xmlCursor.getObject();
                if ((object instanceof CTP) || (object instanceof CTTbl)) {
                    i2++;
                }
            }
            this.bodyElements.add(i2, xWPFParagraph);
            XmlCursor newCursor2 = ctp.newCursor();
            try {
                xmlCursor.toCursor(newCursor2);
                xmlCursor.toEndToken();
                return xWPFParagraph;
            } finally {
                newCursor2.dispose();
            }
        } finally {
            newCursor.dispose();
        }
    }

    /* JADX INFO: finally extract failed */
    public XWPFTable insertNewTbl(XmlCursor xmlCursor) {
        boolean z;
        XmlObject xmlObject = null;
        if (!isCursorInHdrF(xmlCursor)) {
            return null;
        }
        xmlCursor.beginElement("tbl", CTTbl.type.getName().getNamespaceURI());
        xmlCursor.toParent();
        CTTbl cTTbl = (CTTbl) xmlCursor.getObject();
        XWPFTable xWPFTable = new XWPFTable(cTTbl, this);
        xmlCursor.removeXmlContents();
        while (true) {
            z = xmlObject instanceof CTTbl;
            if (z || !xmlCursor.toPrevSibling()) {
                int i = 0;
            } else {
                xmlObject = xmlCursor.getObject();
            }
        }
        int i2 = 0;
        if (!z) {
            this.tables.add(0, xWPFTable);
        } else {
            this.tables.add(this.tables.indexOf(getTable((CTTbl) xmlObject)) + 1, xWPFTable);
        }
        XmlCursor newCursor = cTTbl.newCursor();
        while (newCursor.toPrevSibling()) {
            try {
                XmlObject object = newCursor.getObject();
                if ((object instanceof CTP) || (object instanceof CTTbl)) {
                    i2++;
                }
            } catch (Throwable th) {
                newCursor.dispose();
                throw th;
            }
        }
        newCursor.dispose();
        this.bodyElements.add(i2, xWPFTable);
        XmlCursor newCursor2 = cTTbl.newCursor();
        try {
            xmlCursor.toCursor(newCursor2);
            xmlCursor.toEndToken();
            return xWPFTable;
        } finally {
            newCursor2.dispose();
        }
    }

    private boolean isCursorInHdrF(XmlCursor xmlCursor) {
        XmlCursor newCursor = xmlCursor.newCursor();
        try {
            newCursor.toParent();
            return newCursor.getObject() == this.headerFooter;
        } finally {
            newCursor.dispose();
        }
    }

    public XWPFTable getTableArray(int i) {
        if (i < 0 || i >= this.tables.size()) {
            return null;
        }
        return this.tables.get(i);
    }

    public void insertTable(int i, XWPFTable xWPFTable) {
        this.bodyElements.add(i, xWPFTable);
        CTTbl[] tblArray = this.headerFooter.getTblArray();
        int length = tblArray.length;
        int i2 = 0;
        int i3 = 0;
        while (i2 < length && tblArray[i2] != xWPFTable.getCTTbl()) {
            i3++;
            i2++;
        }
        this.tables.add(i3, xWPFTable);
    }

    public void readHdrFtr() {
        this.bodyElements = new ArrayList();
        this.paragraphs = new ArrayList();
        this.tables = new ArrayList();
        XmlCursor newCursor = this.headerFooter.newCursor();
        try {
            newCursor.selectPath("./*");
            while (newCursor.toNextSelection()) {
                XmlObject object = newCursor.getObject();
                if (object instanceof CTP) {
                    XWPFParagraph xWPFParagraph = new XWPFParagraph((CTP) object, this);
                    this.paragraphs.add(xWPFParagraph);
                    this.bodyElements.add(xWPFParagraph);
                }
                if (object instanceof CTTbl) {
                    XWPFTable xWPFTable = new XWPFTable((CTTbl) object, this);
                    this.tables.add(xWPFTable);
                    this.bodyElements.add(xWPFTable);
                }
            }
        } finally {
            newCursor.dispose();
        }
    }

    public XWPFTableCell getTableCell(CTTc cTTc) {
        XWPFTable table;
        XmlCursor newCursor = cTTc.newCursor();
        try {
            newCursor.toParent();
            XmlObject object = newCursor.getObject();
            if (!(object instanceof CTRow)) {
                return null;
            }
            CTRow cTRow = (CTRow) object;
            newCursor.toParent();
            XmlObject object2 = newCursor.getObject();
            newCursor.dispose();
            if ((object2 instanceof CTTbl) && (table = getTable((CTTbl) object2)) != null) {
                return table.getRow(cTRow).getTableCell(cTTc);
            }
            return null;
        } finally {
            newCursor.dispose();
        }
    }

    public XWPFDocument getXWPFDocument() {
        XWPFDocument xWPFDocument = this.document;
        if (xWPFDocument != null) {
            return xWPFDocument;
        }
        return (XWPFDocument) getParent();
    }

    public void setXWPFDocument(XWPFDocument xWPFDocument) {
        this.document = xWPFDocument;
    }

    /* access modifiers changed from: protected */
    public void prepareForCommit() {
        if (this.bodyElements.isEmpty()) {
            createParagraph();
        }
        for (XWPFTable xWPFTable : this.tables) {
            for (XWPFTableRow tableCells : xWPFTable.tableRows) {
                for (XWPFTableCell next : tableCells.getTableCells()) {
                    if (next.getBodyElements().isEmpty()) {
                        next.addParagraph();
                    }
                }
            }
        }
        super.prepareForCommit();
    }
}
