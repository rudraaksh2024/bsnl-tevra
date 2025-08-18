package org.apache.poi.xwpf.usermodel;

import java.io.IOException;
import org.apache.poi.ooxml.POIXMLDocumentPart;
import org.apache.poi.openxml4j.opc.PackagePart;
import org.apache.xmlbeans.XmlCursor;
import org.apache.xmlbeans.XmlObject;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTHdrFtr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTP;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTbl;

public class XWPFHeader extends XWPFHeaderFooter {
    public XWPFHeader() {
    }

    public XWPFHeader(POIXMLDocumentPart pOIXMLDocumentPart, PackagePart packagePart) throws IOException {
        super(pOIXMLDocumentPart, packagePart);
    }

    public XWPFHeader(XWPFDocument xWPFDocument, CTHdrFtr cTHdrFtr) {
        super(xWPFDocument, cTHdrFtr);
        XmlCursor newCursor = this.headerFooter.newCursor();
        try {
            newCursor.selectPath("./*");
            while (newCursor.toNextSelection()) {
                XmlObject object = newCursor.getObject();
                if (object instanceof CTP) {
                    this.paragraphs.add(new XWPFParagraph((CTP) object, this));
                }
                if (object instanceof CTTbl) {
                    this.tables.add(new XWPFTable((CTTbl) object, this));
                }
            }
        } finally {
            newCursor.dispose();
        }
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:11:?, code lost:
        r1.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0039, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x003a, code lost:
        r4.addSuppressed(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x003d, code lost:
        throw r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0032, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0033, code lost:
        if (r1 != null) goto L_0x0035;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void commit() throws java.io.IOException {
        /*
            r4 = this;
            org.apache.xmlbeans.XmlOptions r0 = new org.apache.xmlbeans.XmlOptions
            org.apache.xmlbeans.XmlOptions r1 = org.apache.poi.ooxml.POIXMLTypeLoader.DEFAULT_XML_OPTIONS
            r0.<init>(r1)
            javax.xml.namespace.QName r1 = new javax.xml.namespace.QName
            org.apache.xmlbeans.SchemaType r2 = org.openxmlformats.schemas.wordprocessingml.x2006.main.CTNumbering.type
            javax.xml.namespace.QName r2 = r2.getName()
            java.lang.String r2 = r2.getNamespaceURI()
            java.lang.String r3 = "hdr"
            r1.<init>(r2, r3)
            r0.setSaveSyntheticDocumentElement(r1)
            org.apache.poi.openxml4j.opc.PackagePart r1 = r4.getPackagePart()
            java.io.OutputStream r1 = r1.getOutputStream()
            org.openxmlformats.schemas.wordprocessingml.x2006.main.CTHdrFtr r4 = super._getHdrFtr()     // Catch:{ all -> 0x0030 }
            r4.save((java.io.OutputStream) r1, (org.apache.xmlbeans.XmlOptions) r0)     // Catch:{ all -> 0x0030 }
            if (r1 == 0) goto L_0x002f
            r1.close()
        L_0x002f:
            return
        L_0x0030:
            r4 = move-exception
            throw r4     // Catch:{ all -> 0x0032 }
        L_0x0032:
            r0 = move-exception
            if (r1 == 0) goto L_0x003d
            r1.close()     // Catch:{ all -> 0x0039 }
            goto L_0x003d
        L_0x0039:
            r1 = move-exception
            r4.addSuppressed(r1)
        L_0x003d:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.xwpf.usermodel.XWPFHeader.commit():void");
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0078, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0079, code lost:
        if (r0 != null) goto L_0x007b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:?, code lost:
        r0.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x0083, code lost:
        throw r1;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onDocumentRead() throws java.io.IOException {
        /*
            r5 = this;
            super.onDocumentRead()
            org.apache.poi.openxml4j.opc.PackagePart r0 = r5.getPackagePart()     // Catch:{ XmlException -> 0x0084 }
            java.io.InputStream r0 = r0.getInputStream()     // Catch:{ XmlException -> 0x0084 }
            org.apache.xmlbeans.impl.schema.DocumentFactory<org.openxmlformats.schemas.wordprocessingml.x2006.main.HdrDocument> r1 = org.openxmlformats.schemas.wordprocessingml.x2006.main.HdrDocument.Factory     // Catch:{ all -> 0x0076 }
            org.apache.xmlbeans.XmlOptions r2 = org.apache.poi.ooxml.POIXMLTypeLoader.DEFAULT_XML_OPTIONS     // Catch:{ all -> 0x0076 }
            java.lang.Object r1 = r1.parse((java.io.InputStream) r0, (org.apache.xmlbeans.XmlOptions) r2)     // Catch:{ all -> 0x0076 }
            org.openxmlformats.schemas.wordprocessingml.x2006.main.HdrDocument r1 = (org.openxmlformats.schemas.wordprocessingml.x2006.main.HdrDocument) r1     // Catch:{ all -> 0x0076 }
            org.openxmlformats.schemas.wordprocessingml.x2006.main.CTHdrFtr r1 = r1.getHdr()     // Catch:{ all -> 0x0076 }
            r5.headerFooter = r1     // Catch:{ all -> 0x0076 }
            org.openxmlformats.schemas.wordprocessingml.x2006.main.CTHdrFtr r1 = r5.headerFooter     // Catch:{ all -> 0x0076 }
            org.apache.xmlbeans.XmlCursor r1 = r1.newCursor()     // Catch:{ all -> 0x0076 }
            java.lang.String r2 = "./*"
            r1.selectPath(r2)     // Catch:{ all -> 0x0076 }
        L_0x0026:
            boolean r2 = r1.toNextSelection()     // Catch:{ all -> 0x0076 }
            if (r2 == 0) goto L_0x006d
            org.apache.xmlbeans.XmlObject r2 = r1.getObject()     // Catch:{ all -> 0x0076 }
            boolean r3 = r2 instanceof org.openxmlformats.schemas.wordprocessingml.x2006.main.CTP     // Catch:{ all -> 0x0076 }
            if (r3 == 0) goto L_0x0046
            org.apache.poi.xwpf.usermodel.XWPFParagraph r3 = new org.apache.poi.xwpf.usermodel.XWPFParagraph     // Catch:{ all -> 0x0076 }
            r4 = r2
            org.openxmlformats.schemas.wordprocessingml.x2006.main.CTP r4 = (org.openxmlformats.schemas.wordprocessingml.x2006.main.CTP) r4     // Catch:{ all -> 0x0076 }
            r3.<init>(r4, r5)     // Catch:{ all -> 0x0076 }
            java.util.List r4 = r5.paragraphs     // Catch:{ all -> 0x0076 }
            r4.add(r3)     // Catch:{ all -> 0x0076 }
            java.util.List r4 = r5.bodyElements     // Catch:{ all -> 0x0076 }
            r4.add(r3)     // Catch:{ all -> 0x0076 }
        L_0x0046:
            boolean r3 = r2 instanceof org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTbl     // Catch:{ all -> 0x0076 }
            if (r3 == 0) goto L_0x005c
            org.apache.poi.xwpf.usermodel.XWPFTable r3 = new org.apache.poi.xwpf.usermodel.XWPFTable     // Catch:{ all -> 0x0076 }
            r4 = r2
            org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTbl r4 = (org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTbl) r4     // Catch:{ all -> 0x0076 }
            r3.<init>(r4, r5)     // Catch:{ all -> 0x0076 }
            java.util.List r4 = r5.tables     // Catch:{ all -> 0x0076 }
            r4.add(r3)     // Catch:{ all -> 0x0076 }
            java.util.List r4 = r5.bodyElements     // Catch:{ all -> 0x0076 }
            r4.add(r3)     // Catch:{ all -> 0x0076 }
        L_0x005c:
            boolean r3 = r2 instanceof org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtBlock     // Catch:{ all -> 0x0076 }
            if (r3 == 0) goto L_0x0026
            org.apache.poi.xwpf.usermodel.XWPFSDT r3 = new org.apache.poi.xwpf.usermodel.XWPFSDT     // Catch:{ all -> 0x0076 }
            org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtBlock r2 = (org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtBlock) r2     // Catch:{ all -> 0x0076 }
            r3.<init>((org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtBlock) r2, (org.apache.poi.xwpf.usermodel.IBody) r5)     // Catch:{ all -> 0x0076 }
            java.util.List r2 = r5.bodyElements     // Catch:{ all -> 0x0076 }
            r2.add(r3)     // Catch:{ all -> 0x0076 }
            goto L_0x0026
        L_0x006d:
            r1.dispose()     // Catch:{ all -> 0x0076 }
            if (r0 == 0) goto L_0x0075
            r0.close()     // Catch:{ XmlException -> 0x0084 }
        L_0x0075:
            return
        L_0x0076:
            r5 = move-exception
            throw r5     // Catch:{ all -> 0x0078 }
        L_0x0078:
            r1 = move-exception
            if (r0 == 0) goto L_0x0083
            r0.close()     // Catch:{ all -> 0x007f }
            goto L_0x0083
        L_0x007f:
            r0 = move-exception
            r5.addSuppressed(r0)     // Catch:{ XmlException -> 0x0084 }
        L_0x0083:
            throw r1     // Catch:{ XmlException -> 0x0084 }
        L_0x0084:
            r5 = move-exception
            org.apache.poi.ooxml.POIXMLException r0 = new org.apache.poi.ooxml.POIXMLException
            r0.<init>((java.lang.Throwable) r5)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.xwpf.usermodel.XWPFHeader.onDocumentRead():void");
    }

    public BodyType getPartType() {
        return BodyType.HEADER;
    }
}
