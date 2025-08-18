package org.apache.poi.xslf.usermodel;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import org.apache.poi.ooxml.POIXMLDocumentPart;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTableStyleList;

public class XSLFTableStyles extends POIXMLDocumentPart implements Iterable<XSLFTableStyle> {
    private List<XSLFTableStyle> _styles;
    private CTTableStyleList _tblStyleLst;

    public XSLFTableStyles() {
    }

    /* JADX WARNING: Code restructure failed: missing block: B:13:0x004b, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x004c, code lost:
        if (r4 != null) goto L_0x004e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:?, code lost:
        r4.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0052, code lost:
        r4 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0053, code lost:
        r3.addSuppressed(r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0056, code lost:
        throw r0;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public XSLFTableStyles(org.apache.poi.openxml4j.opc.PackagePart r4) throws java.io.IOException, org.apache.xmlbeans.XmlException {
        /*
            r3 = this;
            r3.<init>((org.apache.poi.openxml4j.opc.PackagePart) r4)
            org.apache.poi.openxml4j.opc.PackagePart r4 = r3.getPackagePart()
            java.io.InputStream r4 = r4.getInputStream()
            org.apache.xmlbeans.impl.schema.DocumentFactory<org.openxmlformats.schemas.drawingml.x2006.main.TblStyleLstDocument> r0 = org.openxmlformats.schemas.drawingml.x2006.main.TblStyleLstDocument.Factory     // Catch:{ all -> 0x0049 }
            java.lang.Object r0 = r0.parse((java.io.InputStream) r4)     // Catch:{ all -> 0x0049 }
            org.openxmlformats.schemas.drawingml.x2006.main.TblStyleLstDocument r0 = (org.openxmlformats.schemas.drawingml.x2006.main.TblStyleLstDocument) r0     // Catch:{ all -> 0x0049 }
            if (r4 == 0) goto L_0x0018
            r4.close()
        L_0x0018:
            org.openxmlformats.schemas.drawingml.x2006.main.CTTableStyleList r4 = r0.getTblStyleLst()
            r3._tblStyleLst = r4
            java.util.List r4 = r4.getTblStyleList()
            java.util.ArrayList r0 = new java.util.ArrayList
            int r1 = r4.size()
            r0.<init>(r1)
            r3._styles = r0
            java.util.Iterator r4 = r4.iterator()
        L_0x0031:
            boolean r0 = r4.hasNext()
            if (r0 == 0) goto L_0x0048
            java.lang.Object r0 = r4.next()
            org.openxmlformats.schemas.drawingml.x2006.main.CTTableStyle r0 = (org.openxmlformats.schemas.drawingml.x2006.main.CTTableStyle) r0
            java.util.List<org.apache.poi.xslf.usermodel.XSLFTableStyle> r1 = r3._styles
            org.apache.poi.xslf.usermodel.XSLFTableStyle r2 = new org.apache.poi.xslf.usermodel.XSLFTableStyle
            r2.<init>(r0)
            r1.add(r2)
            goto L_0x0031
        L_0x0048:
            return
        L_0x0049:
            r3 = move-exception
            throw r3     // Catch:{ all -> 0x004b }
        L_0x004b:
            r0 = move-exception
            if (r4 == 0) goto L_0x0056
            r4.close()     // Catch:{ all -> 0x0052 }
            goto L_0x0056
        L_0x0052:
            r4 = move-exception
            r3.addSuppressed(r4)
        L_0x0056:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.xslf.usermodel.XSLFTableStyles.<init>(org.apache.poi.openxml4j.opc.PackagePart):void");
    }

    public CTTableStyleList getXmlObject() {
        return this._tblStyleLst;
    }

    public Iterator<XSLFTableStyle> iterator() {
        return this._styles.iterator();
    }

    public List<XSLFTableStyle> getStyles() {
        return Collections.unmodifiableList(this._styles);
    }
}
