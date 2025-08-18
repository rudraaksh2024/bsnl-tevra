package org.apache.poi.xdgf.usermodel;

import com.microsoft.schemas.office.visio.x2012.main.MastersType;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import org.apache.poi.openxml4j.opc.PackagePart;
import org.apache.poi.util.Internal;
import org.apache.poi.xdgf.xml.XDGFXMLDocumentPart;

public class XDGFMasters extends XDGFXMLDocumentPart {
    protected Map<Long, XDGFMaster> _masters = new HashMap();
    MastersType _mastersObject;

    public XDGFMasters(PackagePart packagePart) {
        super(packagePart);
    }

    /* access modifiers changed from: protected */
    @Internal
    public MastersType getXmlObject() {
        return this._mastersObject;
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x00c5, code lost:
        r2 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x00c6, code lost:
        if (r0 != null) goto L_0x00c8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:?, code lost:
        r0.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x00d0, code lost:
        throw r2;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onDocumentRead() {
        /*
            r6 = this;
            org.apache.poi.openxml4j.opc.PackagePart r0 = r6.getPackagePart()     // Catch:{ IOException | XmlException -> 0x00d3 }
            java.io.InputStream r0 = r0.getInputStream()     // Catch:{ IOException | XmlException -> 0x00d3 }
            org.apache.xmlbeans.impl.schema.DocumentFactory<com.microsoft.schemas.office.visio.x2012.main.MastersDocument> r1 = com.microsoft.schemas.office.visio.x2012.main.MastersDocument.Factory     // Catch:{ all -> 0x00c3 }
            java.lang.Object r1 = r1.parse((java.io.InputStream) r0)     // Catch:{ all -> 0x00c3 }
            com.microsoft.schemas.office.visio.x2012.main.MastersDocument r1 = (com.microsoft.schemas.office.visio.x2012.main.MastersDocument) r1     // Catch:{ all -> 0x00c3 }
            com.microsoft.schemas.office.visio.x2012.main.MastersType r1 = r1.getMasters()     // Catch:{ all -> 0x00c3 }
            r6._mastersObject = r1     // Catch:{ all -> 0x00c3 }
            if (r0 == 0) goto L_0x001b
            r0.close()     // Catch:{ IOException | XmlException -> 0x00d3 }
        L_0x001b:
            java.util.HashMap r0 = new java.util.HashMap     // Catch:{ POIXMLException -> 0x00d1 }
            r0.<init>()     // Catch:{ POIXMLException -> 0x00d1 }
            com.microsoft.schemas.office.visio.x2012.main.MastersType r1 = r6._mastersObject     // Catch:{ POIXMLException -> 0x00d1 }
            com.microsoft.schemas.office.visio.x2012.main.MasterType[] r1 = r1.getMasterArray()     // Catch:{ POIXMLException -> 0x00d1 }
            int r2 = r1.length     // Catch:{ POIXMLException -> 0x00d1 }
            r3 = 0
        L_0x0028:
            if (r3 >= r2) goto L_0x003a
            r4 = r1[r3]     // Catch:{ POIXMLException -> 0x00d1 }
            com.microsoft.schemas.office.visio.x2012.main.RelType r5 = r4.getRel()     // Catch:{ POIXMLException -> 0x00d1 }
            java.lang.String r5 = r5.getId()     // Catch:{ POIXMLException -> 0x00d1 }
            r0.put(r5, r4)     // Catch:{ POIXMLException -> 0x00d1 }
            int r3 = r3 + 1
            goto L_0x0028
        L_0x003a:
            java.util.List r1 = r6.getRelationParts()     // Catch:{ POIXMLException -> 0x00d1 }
            java.util.Iterator r1 = r1.iterator()     // Catch:{ POIXMLException -> 0x00d1 }
        L_0x0042:
            boolean r2 = r1.hasNext()     // Catch:{ POIXMLException -> 0x00d1 }
            if (r2 == 0) goto L_0x00c2
            java.lang.Object r2 = r1.next()     // Catch:{ POIXMLException -> 0x00d1 }
            org.apache.poi.ooxml.POIXMLDocumentPart$RelationPart r2 = (org.apache.poi.ooxml.POIXMLDocumentPart.RelationPart) r2     // Catch:{ POIXMLException -> 0x00d1 }
            org.apache.poi.ooxml.POIXMLDocumentPart r3 = r2.getDocumentPart()     // Catch:{ POIXMLException -> 0x00d1 }
            org.apache.poi.openxml4j.opc.PackageRelationship r2 = r2.getRelationship()     // Catch:{ POIXMLException -> 0x00d1 }
            java.lang.String r2 = r2.getId()     // Catch:{ POIXMLException -> 0x00d1 }
            java.lang.Object r4 = r0.get(r2)     // Catch:{ POIXMLException -> 0x00d1 }
            com.microsoft.schemas.office.visio.x2012.main.MasterType r4 = (com.microsoft.schemas.office.visio.x2012.main.MasterType) r4     // Catch:{ POIXMLException -> 0x00d1 }
            if (r4 == 0) goto L_0x00a3
            boolean r5 = r3 instanceof org.apache.poi.xdgf.usermodel.XDGFMasterContents     // Catch:{ POIXMLException -> 0x00d1 }
            if (r5 == 0) goto L_0x0080
            org.apache.poi.xdgf.usermodel.XDGFMasterContents r3 = (org.apache.poi.xdgf.usermodel.XDGFMasterContents) r3     // Catch:{ POIXMLException -> 0x00d1 }
            r3.onDocumentRead()     // Catch:{ POIXMLException -> 0x00d1 }
            org.apache.poi.xdgf.usermodel.XDGFMaster r2 = new org.apache.poi.xdgf.usermodel.XDGFMaster     // Catch:{ POIXMLException -> 0x00d1 }
            org.apache.poi.xdgf.usermodel.XDGFDocument r5 = r6._document     // Catch:{ POIXMLException -> 0x00d1 }
            r2.<init>(r4, r3, r5)     // Catch:{ POIXMLException -> 0x00d1 }
            java.util.Map<java.lang.Long, org.apache.poi.xdgf.usermodel.XDGFMaster> r3 = r6._masters     // Catch:{ POIXMLException -> 0x00d1 }
            long r4 = r2.getID()     // Catch:{ POIXMLException -> 0x00d1 }
            java.lang.Long r4 = java.lang.Long.valueOf(r4)     // Catch:{ POIXMLException -> 0x00d1 }
            r3.put(r4, r2)     // Catch:{ POIXMLException -> 0x00d1 }
            goto L_0x0042
        L_0x0080:
            org.apache.poi.ooxml.POIXMLException r0 = new org.apache.poi.ooxml.POIXMLException     // Catch:{ POIXMLException -> 0x00d1 }
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ POIXMLException -> 0x00d1 }
            r1.<init>()     // Catch:{ POIXMLException -> 0x00d1 }
            java.lang.String r4 = "Unexpected masters relationship for "
            java.lang.StringBuilder r1 = r1.append(r4)     // Catch:{ POIXMLException -> 0x00d1 }
            java.lang.StringBuilder r1 = r1.append(r2)     // Catch:{ POIXMLException -> 0x00d1 }
            java.lang.String r2 = ": "
            java.lang.StringBuilder r1 = r1.append(r2)     // Catch:{ POIXMLException -> 0x00d1 }
            java.lang.StringBuilder r1 = r1.append(r3)     // Catch:{ POIXMLException -> 0x00d1 }
            java.lang.String r1 = r1.toString()     // Catch:{ POIXMLException -> 0x00d1 }
            r0.<init>((java.lang.String) r1)     // Catch:{ POIXMLException -> 0x00d1 }
            throw r0     // Catch:{ POIXMLException -> 0x00d1 }
        L_0x00a3:
            org.apache.poi.ooxml.POIXMLException r0 = new org.apache.poi.ooxml.POIXMLException     // Catch:{ POIXMLException -> 0x00d1 }
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ POIXMLException -> 0x00d1 }
            r1.<init>()     // Catch:{ POIXMLException -> 0x00d1 }
            java.lang.String r3 = "Master relationship for "
            java.lang.StringBuilder r1 = r1.append(r3)     // Catch:{ POIXMLException -> 0x00d1 }
            java.lang.StringBuilder r1 = r1.append(r2)     // Catch:{ POIXMLException -> 0x00d1 }
            java.lang.String r2 = " not found"
            java.lang.StringBuilder r1 = r1.append(r2)     // Catch:{ POIXMLException -> 0x00d1 }
            java.lang.String r1 = r1.toString()     // Catch:{ POIXMLException -> 0x00d1 }
            r0.<init>((java.lang.String) r1)     // Catch:{ POIXMLException -> 0x00d1 }
            throw r0     // Catch:{ POIXMLException -> 0x00d1 }
        L_0x00c2:
            return
        L_0x00c3:
            r1 = move-exception
            throw r1     // Catch:{ all -> 0x00c5 }
        L_0x00c5:
            r2 = move-exception
            if (r0 == 0) goto L_0x00d0
            r0.close()     // Catch:{ all -> 0x00cc }
            goto L_0x00d0
        L_0x00cc:
            r0 = move-exception
            r1.addSuppressed(r0)     // Catch:{ IOException | XmlException -> 0x00d3 }
        L_0x00d0:
            throw r2     // Catch:{ IOException | XmlException -> 0x00d3 }
        L_0x00d1:
            r0 = move-exception
            goto L_0x00da
        L_0x00d3:
            r0 = move-exception
            org.apache.poi.ooxml.POIXMLException r1 = new org.apache.poi.ooxml.POIXMLException     // Catch:{ POIXMLException -> 0x00d1 }
            r1.<init>((java.lang.Throwable) r0)     // Catch:{ POIXMLException -> 0x00d1 }
            throw r1     // Catch:{ POIXMLException -> 0x00d1 }
        L_0x00da:
            org.apache.poi.ooxml.POIXMLException r6 = org.apache.poi.xdgf.exceptions.XDGFException.wrap((org.apache.poi.ooxml.POIXMLDocumentPart) r6, (org.apache.poi.ooxml.POIXMLException) r0)
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.xdgf.usermodel.XDGFMasters.onDocumentRead():void");
    }

    public Collection<XDGFMaster> getMastersList() {
        return Collections.unmodifiableCollection(this._masters.values());
    }

    public XDGFMaster getMasterById(long j) {
        return this._masters.get(Long.valueOf(j));
    }
}
