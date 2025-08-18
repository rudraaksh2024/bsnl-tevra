package org.apache.poi.xssf.usermodel;

import java.io.IOException;
import java.io.InputStream;
import javax.xml.namespace.QName;
import org.apache.poi.ooxml.POIXMLDocumentPart;
import org.apache.poi.ooxml.POIXMLTypeLoader;
import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlOptions;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotCache;

public class XSSFPivotCache extends POIXMLDocumentPart {
    private CTPivotCache ctPivotCache;

    public XSSFPivotCache() {
        this.ctPivotCache = CTPivotCache.Factory.newInstance();
    }

    public XSSFPivotCache(CTPivotCache cTPivotCache) {
        this.ctPivotCache = cTPivotCache;
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
    protected XSSFPivotCache(org.apache.poi.openxml4j.opc.PackagePart r2) throws java.io.IOException {
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
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.xssf.usermodel.XSSFPivotCache.<init>(org.apache.poi.openxml4j.opc.PackagePart):void");
    }

    /* access modifiers changed from: protected */
    public void readFrom(InputStream inputStream) throws IOException {
        try {
            XmlOptions xmlOptions = new XmlOptions(POIXMLTypeLoader.DEFAULT_XML_OPTIONS);
            xmlOptions.setLoadReplaceDocumentElement((QName) null);
            this.ctPivotCache = CTPivotCache.Factory.parse(inputStream, xmlOptions);
        } catch (XmlException e) {
            throw new IOException(e.getLocalizedMessage());
        }
    }

    public CTPivotCache getCTPivotCache() {
        return this.ctPivotCache;
    }
}
