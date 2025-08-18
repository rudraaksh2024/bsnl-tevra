package org.apache.poi.xdgf.usermodel;

import com.microsoft.schemas.office.visio.x2012.main.PagesType;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.apache.poi.openxml4j.opc.PackagePart;
import org.apache.poi.util.Internal;
import org.apache.poi.xdgf.xml.XDGFXMLDocumentPart;

public class XDGFPages extends XDGFXMLDocumentPart {
    List<XDGFPage> _pages = new ArrayList();
    PagesType _pagesObject;

    public XDGFPages(PackagePart packagePart) {
        super(packagePart);
    }

    /* access modifiers changed from: package-private */
    @Internal
    public PagesType getXmlObject() {
        return this._pagesObject;
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0092, code lost:
        r2 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0093, code lost:
        if (r0 != null) goto L_0x0095;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:?, code lost:
        r0.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x009d, code lost:
        throw r2;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onDocumentRead() {
        /*
            r7 = this;
            org.apache.poi.openxml4j.opc.PackagePart r0 = r7.getPackagePart()     // Catch:{ IOException | XmlException -> 0x00a0 }
            java.io.InputStream r0 = r0.getInputStream()     // Catch:{ IOException | XmlException -> 0x00a0 }
            org.apache.xmlbeans.impl.schema.DocumentFactory<com.microsoft.schemas.office.visio.x2012.main.PagesDocument> r1 = com.microsoft.schemas.office.visio.x2012.main.PagesDocument.Factory     // Catch:{ all -> 0x0090 }
            java.lang.Object r1 = r1.parse((java.io.InputStream) r0)     // Catch:{ all -> 0x0090 }
            com.microsoft.schemas.office.visio.x2012.main.PagesDocument r1 = (com.microsoft.schemas.office.visio.x2012.main.PagesDocument) r1     // Catch:{ all -> 0x0090 }
            com.microsoft.schemas.office.visio.x2012.main.PagesType r1 = r1.getPages()     // Catch:{ all -> 0x0090 }
            r7._pagesObject = r1     // Catch:{ all -> 0x0090 }
            if (r0 == 0) goto L_0x001b
            r0.close()     // Catch:{ IOException | XmlException -> 0x00a0 }
        L_0x001b:
            com.microsoft.schemas.office.visio.x2012.main.PagesType r0 = r7._pagesObject     // Catch:{ POIXMLException -> 0x009e }
            com.microsoft.schemas.office.visio.x2012.main.PageType[] r0 = r0.getPageArray()     // Catch:{ POIXMLException -> 0x009e }
            int r1 = r0.length     // Catch:{ POIXMLException -> 0x009e }
            r2 = 0
        L_0x0023:
            if (r2 >= r1) goto L_0x008f
            r3 = r0[r2]     // Catch:{ POIXMLException -> 0x009e }
            com.microsoft.schemas.office.visio.x2012.main.RelType r4 = r3.getRel()     // Catch:{ POIXMLException -> 0x009e }
            java.lang.String r4 = r4.getId()     // Catch:{ POIXMLException -> 0x009e }
            org.apache.poi.ooxml.POIXMLDocumentPart r5 = r7.getRelationById(r4)     // Catch:{ POIXMLException -> 0x009e }
            if (r5 == 0) goto L_0x0070
            boolean r6 = r5 instanceof org.apache.poi.xdgf.usermodel.XDGFPageContents     // Catch:{ POIXMLException -> 0x009e }
            if (r6 == 0) goto L_0x004d
            org.apache.poi.xdgf.usermodel.XDGFPageContents r5 = (org.apache.poi.xdgf.usermodel.XDGFPageContents) r5     // Catch:{ POIXMLException -> 0x009e }
            org.apache.poi.xdgf.usermodel.XDGFPage r4 = new org.apache.poi.xdgf.usermodel.XDGFPage     // Catch:{ POIXMLException -> 0x009e }
            org.apache.poi.xdgf.usermodel.XDGFDocument r6 = r7._document     // Catch:{ POIXMLException -> 0x009e }
            r4.<init>(r3, r5, r6, r7)     // Catch:{ POIXMLException -> 0x009e }
            r5.onDocumentRead()     // Catch:{ POIXMLException -> 0x009e }
            java.util.List<org.apache.poi.xdgf.usermodel.XDGFPage> r3 = r7._pages     // Catch:{ POIXMLException -> 0x009e }
            r3.add(r4)     // Catch:{ POIXMLException -> 0x009e }
            int r2 = r2 + 1
            goto L_0x0023
        L_0x004d:
            org.apache.poi.ooxml.POIXMLException r0 = new org.apache.poi.ooxml.POIXMLException     // Catch:{ POIXMLException -> 0x009e }
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ POIXMLException -> 0x009e }
            r1.<init>()     // Catch:{ POIXMLException -> 0x009e }
            java.lang.String r2 = "Unexpected pages relationship for "
            java.lang.StringBuilder r1 = r1.append(r2)     // Catch:{ POIXMLException -> 0x009e }
            java.lang.StringBuilder r1 = r1.append(r4)     // Catch:{ POIXMLException -> 0x009e }
            java.lang.String r2 = ": "
            java.lang.StringBuilder r1 = r1.append(r2)     // Catch:{ POIXMLException -> 0x009e }
            java.lang.StringBuilder r1 = r1.append(r5)     // Catch:{ POIXMLException -> 0x009e }
            java.lang.String r1 = r1.toString()     // Catch:{ POIXMLException -> 0x009e }
            r0.<init>((java.lang.String) r1)     // Catch:{ POIXMLException -> 0x009e }
            throw r0     // Catch:{ POIXMLException -> 0x009e }
        L_0x0070:
            org.apache.poi.ooxml.POIXMLException r0 = new org.apache.poi.ooxml.POIXMLException     // Catch:{ POIXMLException -> 0x009e }
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ POIXMLException -> 0x009e }
            r1.<init>()     // Catch:{ POIXMLException -> 0x009e }
            java.lang.String r2 = "PageSettings relationship for "
            java.lang.StringBuilder r1 = r1.append(r2)     // Catch:{ POIXMLException -> 0x009e }
            java.lang.StringBuilder r1 = r1.append(r4)     // Catch:{ POIXMLException -> 0x009e }
            java.lang.String r2 = " not found"
            java.lang.StringBuilder r1 = r1.append(r2)     // Catch:{ POIXMLException -> 0x009e }
            java.lang.String r1 = r1.toString()     // Catch:{ POIXMLException -> 0x009e }
            r0.<init>((java.lang.String) r1)     // Catch:{ POIXMLException -> 0x009e }
            throw r0     // Catch:{ POIXMLException -> 0x009e }
        L_0x008f:
            return
        L_0x0090:
            r1 = move-exception
            throw r1     // Catch:{ all -> 0x0092 }
        L_0x0092:
            r2 = move-exception
            if (r0 == 0) goto L_0x009d
            r0.close()     // Catch:{ all -> 0x0099 }
            goto L_0x009d
        L_0x0099:
            r0 = move-exception
            r1.addSuppressed(r0)     // Catch:{ IOException | XmlException -> 0x00a0 }
        L_0x009d:
            throw r2     // Catch:{ IOException | XmlException -> 0x00a0 }
        L_0x009e:
            r0 = move-exception
            goto L_0x00a7
        L_0x00a0:
            r0 = move-exception
            org.apache.poi.ooxml.POIXMLException r1 = new org.apache.poi.ooxml.POIXMLException     // Catch:{ POIXMLException -> 0x009e }
            r1.<init>((java.lang.Throwable) r0)     // Catch:{ POIXMLException -> 0x009e }
            throw r1     // Catch:{ POIXMLException -> 0x009e }
        L_0x00a7:
            org.apache.poi.ooxml.POIXMLException r7 = org.apache.poi.xdgf.exceptions.XDGFException.wrap((org.apache.poi.ooxml.POIXMLDocumentPart) r7, (org.apache.poi.ooxml.POIXMLException) r0)
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.xdgf.usermodel.XDGFPages.onDocumentRead():void");
    }

    public List<XDGFPage> getPageList() {
        return Collections.unmodifiableList(this._pages);
    }
}
