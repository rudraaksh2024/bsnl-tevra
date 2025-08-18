package org.apache.poi.xssf.model;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.apache.poi.ooxml.POIXMLDocumentPart;
import org.apache.poi.ooxml.POIXMLTypeLoader;
import org.apache.xmlbeans.XmlException;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCalcCell;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCalcChain;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CalcChainDocument;

public class CalculationChain extends POIXMLDocumentPart {
    private CTCalcChain chain;

    public CalculationChain() {
        this.chain = CTCalcChain.Factory.newInstance();
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
    public CalculationChain(org.apache.poi.openxml4j.opc.PackagePart r2) throws java.io.IOException {
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
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.xssf.model.CalculationChain.<init>(org.apache.poi.openxml4j.opc.PackagePart):void");
    }

    public void readFrom(InputStream inputStream) throws IOException {
        try {
            this.chain = CalcChainDocument.Factory.parse(inputStream, POIXMLTypeLoader.DEFAULT_XML_OPTIONS).getCalcChain();
        } catch (XmlException e) {
            throw new IOException(e.getLocalizedMessage());
        }
    }

    public void writeTo(OutputStream outputStream) throws IOException {
        CalcChainDocument newInstance = CalcChainDocument.Factory.newInstance();
        newInstance.setCalcChain(this.chain);
        newInstance.save(outputStream, POIXMLTypeLoader.DEFAULT_XML_OPTIONS);
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:11:?, code lost:
        r0.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x001a, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x001b, code lost:
        r2.addSuppressed(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x001e, code lost:
        throw r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0013, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0014, code lost:
        if (r0 != null) goto L_0x0016;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void commit() throws java.io.IOException {
        /*
            r2 = this;
            org.apache.poi.openxml4j.opc.PackagePart r0 = r2.getPackagePart()
            java.io.OutputStream r0 = r0.getOutputStream()
            r2.writeTo(r0)     // Catch:{ all -> 0x0011 }
            if (r0 == 0) goto L_0x0010
            r0.close()
        L_0x0010:
            return
        L_0x0011:
            r2 = move-exception
            throw r2     // Catch:{ all -> 0x0013 }
        L_0x0013:
            r1 = move-exception
            if (r0 == 0) goto L_0x001e
            r0.close()     // Catch:{ all -> 0x001a }
            goto L_0x001e
        L_0x001a:
            r0 = move-exception
            r2.addSuppressed(r0)
        L_0x001e:
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.xssf.model.CalculationChain.commit():void");
    }

    public CTCalcChain getCTCalcChain() {
        return this.chain;
    }

    public void removeItem(int i, String str) {
        CTCalcCell[] cArray = this.chain.getCArray();
        int i2 = -1;
        int i3 = 0;
        while (i3 < cArray.length) {
            if (cArray[i3].isSetI()) {
                i2 = cArray[i3].getI();
            }
            if (i2 != i || !cArray[i3].getR().equals(str)) {
                i3++;
            } else {
                if (cArray[i3].isSetI() && i3 < cArray.length - 1) {
                    int i4 = i3 + 1;
                    if (!cArray[i4].isSetI()) {
                        cArray[i4].setI(i2);
                    }
                }
                this.chain.removeC(i3);
                return;
            }
        }
    }
}
