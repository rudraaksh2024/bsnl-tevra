package org.apache.poi.xdgf.usermodel;

import java.util.HashMap;
import java.util.Map;
import org.apache.poi.openxml4j.opc.PackagePart;

public class XDGFPageContents extends XDGFBaseContents {
    protected Map<Long, XDGFMaster> _masters = new HashMap();
    protected XDGFPage _page;

    public XDGFPageContents(PackagePart packagePart) {
        super(packagePart);
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x0079, code lost:
        r2 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x007a, code lost:
        if (r0 != null) goto L_0x007c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:?, code lost:
        r0.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x0084, code lost:
        throw r2;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onDocumentRead() {
        /*
            r5 = this;
            org.apache.poi.openxml4j.opc.PackagePart r0 = r5.getPackagePart()     // Catch:{ IOException | XmlException -> 0x0087 }
            java.io.InputStream r0 = r0.getInputStream()     // Catch:{ IOException | XmlException -> 0x0087 }
            org.apache.xmlbeans.impl.schema.DocumentFactory<com.microsoft.schemas.office.visio.x2012.main.PageContentsDocument> r1 = com.microsoft.schemas.office.visio.x2012.main.PageContentsDocument.Factory     // Catch:{ all -> 0x0077 }
            java.lang.Object r1 = r1.parse((java.io.InputStream) r0)     // Catch:{ all -> 0x0077 }
            com.microsoft.schemas.office.visio.x2012.main.PageContentsDocument r1 = (com.microsoft.schemas.office.visio.x2012.main.PageContentsDocument) r1     // Catch:{ all -> 0x0077 }
            com.microsoft.schemas.office.visio.x2012.main.PageContentsType r1 = r1.getPageContents()     // Catch:{ all -> 0x0077 }
            r5._pageContents = r1     // Catch:{ all -> 0x0077 }
            if (r0 == 0) goto L_0x001b
            r0.close()     // Catch:{ IOException | XmlException -> 0x0087 }
        L_0x001b:
            java.util.List r0 = r5.getRelations()     // Catch:{ POIXMLException -> 0x0085 }
            java.util.Iterator r0 = r0.iterator()     // Catch:{ POIXMLException -> 0x0085 }
        L_0x0023:
            boolean r1 = r0.hasNext()     // Catch:{ POIXMLException -> 0x0085 }
            if (r1 == 0) goto L_0x0052
            java.lang.Object r1 = r0.next()     // Catch:{ POIXMLException -> 0x0085 }
            org.apache.poi.ooxml.POIXMLDocumentPart r1 = (org.apache.poi.ooxml.POIXMLDocumentPart) r1     // Catch:{ POIXMLException -> 0x0085 }
            boolean r2 = r1 instanceof org.apache.poi.xdgf.usermodel.XDGFMasterContents     // Catch:{ POIXMLException -> 0x0085 }
            if (r2 != 0) goto L_0x0034
            goto L_0x0023
        L_0x0034:
            org.apache.poi.xdgf.usermodel.XDGFMasterContents r1 = (org.apache.poi.xdgf.usermodel.XDGFMasterContents) r1     // Catch:{ POIXMLException -> 0x0085 }
            org.apache.poi.xdgf.usermodel.XDGFMaster r1 = r1.getMaster()     // Catch:{ POIXMLException -> 0x0085 }
            if (r1 == 0) goto L_0x004a
            java.util.Map<java.lang.Long, org.apache.poi.xdgf.usermodel.XDGFMaster> r2 = r5._masters     // Catch:{ POIXMLException -> 0x0085 }
            long r3 = r1.getID()     // Catch:{ POIXMLException -> 0x0085 }
            java.lang.Long r3 = java.lang.Long.valueOf(r3)     // Catch:{ POIXMLException -> 0x0085 }
            r2.put(r3, r1)     // Catch:{ POIXMLException -> 0x0085 }
            goto L_0x0023
        L_0x004a:
            org.apache.poi.ooxml.POIXMLException r0 = new org.apache.poi.ooxml.POIXMLException     // Catch:{ POIXMLException -> 0x0085 }
            java.lang.String r1 = "Master entry is missing in XDGFPageContents"
            r0.<init>((java.lang.String) r1)     // Catch:{ POIXMLException -> 0x0085 }
            throw r0     // Catch:{ POIXMLException -> 0x0085 }
        L_0x0052:
            super.onDocumentRead()     // Catch:{ POIXMLException -> 0x0085 }
            java.util.Map r0 = r5._shapes     // Catch:{ POIXMLException -> 0x0085 }
            java.util.Collection r0 = r0.values()     // Catch:{ POIXMLException -> 0x0085 }
            java.util.Iterator r0 = r0.iterator()     // Catch:{ POIXMLException -> 0x0085 }
        L_0x005f:
            boolean r1 = r0.hasNext()     // Catch:{ POIXMLException -> 0x0085 }
            if (r1 == 0) goto L_0x0076
            java.lang.Object r1 = r0.next()     // Catch:{ POIXMLException -> 0x0085 }
            org.apache.poi.xdgf.usermodel.XDGFShape r1 = (org.apache.poi.xdgf.usermodel.XDGFShape) r1     // Catch:{ POIXMLException -> 0x0085 }
            boolean r2 = r1.isTopmost()     // Catch:{ POIXMLException -> 0x0085 }
            if (r2 == 0) goto L_0x005f
            r2 = 0
            r1.setupMaster(r5, r2)     // Catch:{ POIXMLException -> 0x0085 }
            goto L_0x005f
        L_0x0076:
            return
        L_0x0077:
            r1 = move-exception
            throw r1     // Catch:{ all -> 0x0079 }
        L_0x0079:
            r2 = move-exception
            if (r0 == 0) goto L_0x0084
            r0.close()     // Catch:{ all -> 0x0080 }
            goto L_0x0084
        L_0x0080:
            r0 = move-exception
            r1.addSuppressed(r0)     // Catch:{ IOException | XmlException -> 0x0087 }
        L_0x0084:
            throw r2     // Catch:{ IOException | XmlException -> 0x0087 }
        L_0x0085:
            r0 = move-exception
            goto L_0x008e
        L_0x0087:
            r0 = move-exception
            org.apache.poi.ooxml.POIXMLException r1 = new org.apache.poi.ooxml.POIXMLException     // Catch:{ POIXMLException -> 0x0085 }
            r1.<init>((java.lang.Throwable) r0)     // Catch:{ POIXMLException -> 0x0085 }
            throw r1     // Catch:{ POIXMLException -> 0x0085 }
        L_0x008e:
            org.apache.poi.ooxml.POIXMLException r5 = org.apache.poi.xdgf.exceptions.XDGFException.wrap((org.apache.poi.ooxml.POIXMLDocumentPart) r5, (org.apache.poi.ooxml.POIXMLException) r0)
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.xdgf.usermodel.XDGFPageContents.onDocumentRead():void");
    }

    public XDGFPage getPage() {
        return this._page;
    }

    /* access modifiers changed from: protected */
    public void setPage(XDGFPage xDGFPage) {
        this._page = xDGFPage;
    }

    public XDGFMaster getMasterById(long j) {
        return this._masters.get(Long.valueOf(j));
    }
}
