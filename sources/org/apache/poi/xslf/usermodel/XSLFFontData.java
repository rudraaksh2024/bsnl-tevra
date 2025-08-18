package org.apache.poi.xslf.usermodel;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.apache.poi.ooxml.POIXMLDocumentPart;
import org.apache.poi.openxml4j.opc.PackagePart;

public class XSLFFontData extends POIXMLDocumentPart {
    /* access modifiers changed from: protected */
    public void prepareForCommit() {
    }

    protected XSLFFontData() {
    }

    public XSLFFontData(PackagePart packagePart) {
        super(packagePart);
    }

    public InputStream getInputStream() throws IOException {
        return getPackagePart().getInputStream();
    }

    public OutputStream getOutputStream() throws IOException {
        PackagePart packagePart = getPackagePart();
        packagePart.clear();
        return packagePart.getOutputStream();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:?, code lost:
        r1.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x001a, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x001b, code lost:
        r2.addSuppressed(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x001e, code lost:
        throw r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0013, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0014, code lost:
        if (r1 != null) goto L_0x0016;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void setData(byte[] r2) throws java.io.IOException {
        /*
            r1 = this;
            org.apache.poi.openxml4j.opc.PackagePart r1 = r1.getPackagePart()
            java.io.OutputStream r1 = r1.getOutputStream()
            r1.write(r2)     // Catch:{ all -> 0x0011 }
            if (r1 == 0) goto L_0x0010
            r1.close()
        L_0x0010:
            return
        L_0x0011:
            r2 = move-exception
            throw r2     // Catch:{ all -> 0x0013 }
        L_0x0013:
            r0 = move-exception
            if (r1 == 0) goto L_0x001e
            r1.close()     // Catch:{ all -> 0x001a }
            goto L_0x001e
        L_0x001a:
            r1 = move-exception
            r2.addSuppressed(r1)
        L_0x001e:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.xslf.usermodel.XSLFFontData.setData(byte[]):void");
    }
}
