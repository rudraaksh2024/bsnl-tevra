package org.apache.poi.xslf.usermodel;

import org.apache.poi.ooxml.POIXMLDocumentPart;
import org.openxmlformats.schemas.presentationml.x2006.main.CTCommentAuthor;
import org.openxmlformats.schemas.presentationml.x2006.main.CTCommentAuthorList;
import org.openxmlformats.schemas.presentationml.x2006.main.CmAuthorLstDocument;

public class XSLFCommentAuthors extends POIXMLDocumentPart {
    private final CTCommentAuthorList _authors;

    XSLFCommentAuthors() {
        this._authors = CmAuthorLstDocument.Factory.newInstance().addNewCmAuthorLst();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:?, code lost:
        r3.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x002a, code lost:
        r3 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x002b, code lost:
        r2.addSuppressed(r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x002e, code lost:
        throw r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0023, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0024, code lost:
        if (r3 != null) goto L_0x0026;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    XSLFCommentAuthors(org.apache.poi.openxml4j.opc.PackagePart r3) throws java.io.IOException, org.apache.xmlbeans.XmlException {
        /*
            r2 = this;
            r2.<init>((org.apache.poi.openxml4j.opc.PackagePart) r3)
            org.apache.poi.openxml4j.opc.PackagePart r3 = r2.getPackagePart()
            java.io.InputStream r3 = r3.getInputStream()
            org.apache.xmlbeans.impl.schema.DocumentFactory<org.openxmlformats.schemas.presentationml.x2006.main.CmAuthorLstDocument> r0 = org.openxmlformats.schemas.presentationml.x2006.main.CmAuthorLstDocument.Factory     // Catch:{ all -> 0x0021 }
            org.apache.xmlbeans.XmlOptions r1 = org.apache.poi.ooxml.POIXMLTypeLoader.DEFAULT_XML_OPTIONS     // Catch:{ all -> 0x0021 }
            java.lang.Object r0 = r0.parse((java.io.InputStream) r3, (org.apache.xmlbeans.XmlOptions) r1)     // Catch:{ all -> 0x0021 }
            org.openxmlformats.schemas.presentationml.x2006.main.CmAuthorLstDocument r0 = (org.openxmlformats.schemas.presentationml.x2006.main.CmAuthorLstDocument) r0     // Catch:{ all -> 0x0021 }
            org.openxmlformats.schemas.presentationml.x2006.main.CTCommentAuthorList r0 = r0.getCmAuthorLst()     // Catch:{ all -> 0x0021 }
            r2._authors = r0     // Catch:{ all -> 0x0021 }
            if (r3 == 0) goto L_0x0020
            r3.close()
        L_0x0020:
            return
        L_0x0021:
            r2 = move-exception
            throw r2     // Catch:{ all -> 0x0023 }
        L_0x0023:
            r0 = move-exception
            if (r3 == 0) goto L_0x002e
            r3.close()     // Catch:{ all -> 0x002a }
            goto L_0x002e
        L_0x002a:
            r3 = move-exception
            r2.addSuppressed(r3)
        L_0x002e:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.xslf.usermodel.XSLFCommentAuthors.<init>(org.apache.poi.openxml4j.opc.PackagePart):void");
    }

    public CTCommentAuthorList getCTCommentAuthorsList() {
        return this._authors;
    }

    public CTCommentAuthor getAuthorById(long j) {
        for (CTCommentAuthor cTCommentAuthor : this._authors.getCmAuthorArray()) {
            if (cTCommentAuthor.getId() == j) {
                return cTCommentAuthor;
            }
        }
        return null;
    }
}
