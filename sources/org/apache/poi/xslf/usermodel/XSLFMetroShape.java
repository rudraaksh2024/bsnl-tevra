package org.apache.poi.xslf.usermodel;

import org.apache.poi.sl.usermodel.MetroShapeProvider;
import org.apache.poi.util.Internal;

@Internal
public class XSLFMetroShape implements MetroShapeProvider {
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x0047, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x0048, code lost:
        if (r4 != null) goto L_0x004a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:?, code lost:
        r4.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x004e, code lost:
        r4 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:?, code lost:
        r0.addSuppressed(r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x0052, code lost:
        throw r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x0055, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x0056, code lost:
        if (r3 != null) goto L_0x0058;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:?, code lost:
        r3.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:43:0x0060, code lost:
        throw r0;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public org.apache.poi.xslf.usermodel.XSLFShape parseShape(byte[] r4) throws java.io.IOException {
        /*
            r3 = this;
            org.apache.commons.io.input.UnsynchronizedByteArrayInputStream r3 = new org.apache.commons.io.input.UnsynchronizedByteArrayInputStream     // Catch:{ InvalidFormatException | XmlException -> 0x0061 }
            r3.<init>(r4)     // Catch:{ InvalidFormatException | XmlException -> 0x0061 }
            org.apache.poi.openxml4j.opc.OPCPackage r3 = org.apache.poi.openxml4j.opc.OPCPackage.open((java.io.InputStream) r3)     // Catch:{ InvalidFormatException | XmlException -> 0x0061 }
            java.lang.String r4 = "/drs/shapexml.xml"
            org.apache.poi.openxml4j.opc.PackagePartName r4 = org.apache.poi.openxml4j.opc.PackagingURIHelper.createPartName((java.lang.String) r4)     // Catch:{ all -> 0x0053 }
            org.apache.poi.openxml4j.opc.PackagePart r4 = r3.getPart((org.apache.poi.openxml4j.opc.PackagePartName) r4)     // Catch:{ all -> 0x0053 }
            r0 = 0
            if (r4 != 0) goto L_0x001c
            if (r3 == 0) goto L_0x001b
            r3.close()     // Catch:{ InvalidFormatException | XmlException -> 0x0061 }
        L_0x001b:
            return r0
        L_0x001c:
            java.io.InputStream r4 = r4.getInputStream()     // Catch:{ all -> 0x0053 }
            org.apache.xmlbeans.impl.schema.DocumentFactory<org.openxmlformats.schemas.presentationml.x2006.main.CTGroupShape> r1 = org.openxmlformats.schemas.presentationml.x2006.main.CTGroupShape.Factory     // Catch:{ all -> 0x0045 }
            org.apache.xmlbeans.XmlOptions r2 = org.apache.poi.ooxml.POIXMLTypeLoader.DEFAULT_XML_OPTIONS     // Catch:{ all -> 0x0045 }
            java.lang.Object r1 = r1.parse((java.io.InputStream) r4, (org.apache.xmlbeans.XmlOptions) r2)     // Catch:{ all -> 0x0045 }
            org.openxmlformats.schemas.presentationml.x2006.main.CTGroupShape r1 = (org.openxmlformats.schemas.presentationml.x2006.main.CTGroupShape) r1     // Catch:{ all -> 0x0045 }
            org.apache.poi.xslf.usermodel.XSLFGroupShape r2 = new org.apache.poi.xslf.usermodel.XSLFGroupShape     // Catch:{ all -> 0x0045 }
            r2.<init>(r1, r0)     // Catch:{ all -> 0x0045 }
            java.util.List r0 = r2.getShapes()     // Catch:{ all -> 0x0045 }
            r1 = 0
            java.lang.Object r0 = r0.get(r1)     // Catch:{ all -> 0x0045 }
            org.apache.poi.xslf.usermodel.XSLFShape r0 = (org.apache.poi.xslf.usermodel.XSLFShape) r0     // Catch:{ all -> 0x0045 }
            if (r4 == 0) goto L_0x003f
            r4.close()     // Catch:{ all -> 0x0053 }
        L_0x003f:
            if (r3 == 0) goto L_0x0044
            r3.close()     // Catch:{ InvalidFormatException | XmlException -> 0x0061 }
        L_0x0044:
            return r0
        L_0x0045:
            r0 = move-exception
            throw r0     // Catch:{ all -> 0x0047 }
        L_0x0047:
            r1 = move-exception
            if (r4 == 0) goto L_0x0052
            r4.close()     // Catch:{ all -> 0x004e }
            goto L_0x0052
        L_0x004e:
            r4 = move-exception
            r0.addSuppressed(r4)     // Catch:{ all -> 0x0053 }
        L_0x0052:
            throw r1     // Catch:{ all -> 0x0053 }
        L_0x0053:
            r4 = move-exception
            throw r4     // Catch:{ all -> 0x0055 }
        L_0x0055:
            r0 = move-exception
            if (r3 == 0) goto L_0x0060
            r3.close()     // Catch:{ all -> 0x005c }
            goto L_0x0060
        L_0x005c:
            r3 = move-exception
            r4.addSuppressed(r3)     // Catch:{ InvalidFormatException | XmlException -> 0x0061 }
        L_0x0060:
            throw r0     // Catch:{ InvalidFormatException | XmlException -> 0x0061 }
        L_0x0061:
            r3 = move-exception
            java.io.IOException r4 = new java.io.IOException
            java.lang.String r0 = "can't parse metro shape"
            r4.<init>(r0, r3)
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.xslf.usermodel.XSLFMetroShape.parseShape(byte[]):org.apache.poi.xslf.usermodel.XSLFShape");
    }
}
