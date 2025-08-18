package org.apache.poi.xslf.usermodel;

import org.apache.poi.ooxml.POIXMLDocumentPart;
import org.openxmlformats.schemas.presentationml.x2006.main.CTComment;
import org.openxmlformats.schemas.presentationml.x2006.main.CTCommentList;
import org.openxmlformats.schemas.presentationml.x2006.main.CmLstDocument;

public class XSLFComments extends POIXMLDocumentPart {
    private final CmLstDocument doc;

    XSLFComments() {
        this.doc = CmLstDocument.Factory.newInstance();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:?, code lost:
        r3.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0026, code lost:
        r3 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0027, code lost:
        r2.addSuppressed(r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x002a, code lost:
        throw r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x001f, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0020, code lost:
        if (r3 != null) goto L_0x0022;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    XSLFComments(org.apache.poi.openxml4j.opc.PackagePart r3) throws java.io.IOException, org.apache.xmlbeans.XmlException {
        /*
            r2 = this;
            r2.<init>((org.apache.poi.openxml4j.opc.PackagePart) r3)
            org.apache.poi.openxml4j.opc.PackagePart r3 = r2.getPackagePart()
            java.io.InputStream r3 = r3.getInputStream()
            org.apache.xmlbeans.impl.schema.DocumentFactory<org.openxmlformats.schemas.presentationml.x2006.main.CmLstDocument> r0 = org.openxmlformats.schemas.presentationml.x2006.main.CmLstDocument.Factory     // Catch:{ all -> 0x001d }
            org.apache.xmlbeans.XmlOptions r1 = org.apache.poi.ooxml.POIXMLTypeLoader.DEFAULT_XML_OPTIONS     // Catch:{ all -> 0x001d }
            java.lang.Object r0 = r0.parse((java.io.InputStream) r3, (org.apache.xmlbeans.XmlOptions) r1)     // Catch:{ all -> 0x001d }
            org.openxmlformats.schemas.presentationml.x2006.main.CmLstDocument r0 = (org.openxmlformats.schemas.presentationml.x2006.main.CmLstDocument) r0     // Catch:{ all -> 0x001d }
            r2.doc = r0     // Catch:{ all -> 0x001d }
            if (r3 == 0) goto L_0x001c
            r3.close()
        L_0x001c:
            return
        L_0x001d:
            r2 = move-exception
            throw r2     // Catch:{ all -> 0x001f }
        L_0x001f:
            r0 = move-exception
            if (r3 == 0) goto L_0x002a
            r3.close()     // Catch:{ all -> 0x0026 }
            goto L_0x002a
        L_0x0026:
            r3 = move-exception
            r2.addSuppressed(r3)
        L_0x002a:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.xslf.usermodel.XSLFComments.<init>(org.apache.poi.openxml4j.opc.PackagePart):void");
    }

    public CTCommentList getCTCommentsList() {
        return this.doc.getCmLst();
    }

    public int getNumberOfComments() {
        return this.doc.getCmLst().sizeOfCmArray();
    }

    public CTComment getCommentAt(int i) {
        return this.doc.getCmLst().getCmArray(i);
    }
}
