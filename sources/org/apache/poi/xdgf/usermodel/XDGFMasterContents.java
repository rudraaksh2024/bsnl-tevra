package org.apache.poi.xdgf.usermodel;

import org.apache.poi.openxml4j.opc.PackagePart;

public class XDGFMasterContents extends XDGFBaseContents {
    protected XDGFMaster _master;

    public XDGFMasterContents(PackagePart packagePart) {
        super(packagePart);
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0021, code lost:
        r2 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0022, code lost:
        if (r0 != null) goto L_0x0024;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:?, code lost:
        r0.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x002c, code lost:
        throw r2;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onDocumentRead() {
        /*
            r3 = this;
            org.apache.poi.openxml4j.opc.PackagePart r0 = r3.getPackagePart()     // Catch:{ IOException | XmlException -> 0x002f }
            java.io.InputStream r0 = r0.getInputStream()     // Catch:{ IOException | XmlException -> 0x002f }
            org.apache.xmlbeans.impl.schema.DocumentFactory<com.microsoft.schemas.office.visio.x2012.main.MasterContentsDocument> r1 = com.microsoft.schemas.office.visio.x2012.main.MasterContentsDocument.Factory     // Catch:{ all -> 0x001f }
            java.lang.Object r1 = r1.parse((java.io.InputStream) r0)     // Catch:{ all -> 0x001f }
            com.microsoft.schemas.office.visio.x2012.main.MasterContentsDocument r1 = (com.microsoft.schemas.office.visio.x2012.main.MasterContentsDocument) r1     // Catch:{ all -> 0x001f }
            com.microsoft.schemas.office.visio.x2012.main.PageContentsType r1 = r1.getMasterContents()     // Catch:{ all -> 0x001f }
            r3._pageContents = r1     // Catch:{ all -> 0x001f }
            if (r0 == 0) goto L_0x001b
            r0.close()     // Catch:{ IOException | XmlException -> 0x002f }
        L_0x001b:
            super.onDocumentRead()     // Catch:{ POIXMLException -> 0x002d }
            return
        L_0x001f:
            r1 = move-exception
            throw r1     // Catch:{ all -> 0x0021 }
        L_0x0021:
            r2 = move-exception
            if (r0 == 0) goto L_0x002c
            r0.close()     // Catch:{ all -> 0x0028 }
            goto L_0x002c
        L_0x0028:
            r0 = move-exception
            r1.addSuppressed(r0)     // Catch:{ IOException | XmlException -> 0x002f }
        L_0x002c:
            throw r2     // Catch:{ IOException | XmlException -> 0x002f }
        L_0x002d:
            r0 = move-exception
            goto L_0x0036
        L_0x002f:
            r0 = move-exception
            org.apache.poi.ooxml.POIXMLException r1 = new org.apache.poi.ooxml.POIXMLException     // Catch:{ POIXMLException -> 0x002d }
            r1.<init>((java.lang.Throwable) r0)     // Catch:{ POIXMLException -> 0x002d }
            throw r1     // Catch:{ POIXMLException -> 0x002d }
        L_0x0036:
            org.apache.poi.ooxml.POIXMLException r3 = org.apache.poi.xdgf.exceptions.XDGFException.wrap((org.apache.poi.ooxml.POIXMLDocumentPart) r3, (org.apache.poi.ooxml.POIXMLException) r0)
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.xdgf.usermodel.XDGFMasterContents.onDocumentRead():void");
    }

    public XDGFMaster getMaster() {
        return this._master;
    }

    /* access modifiers changed from: protected */
    public void setMaster(XDGFMaster xDGFMaster) {
        this._master = xDGFMaster;
    }
}
