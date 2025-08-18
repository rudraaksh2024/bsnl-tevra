package org.apache.poi.xwpf.usermodel;

import java.io.IOException;
import java.util.Arrays;
import org.apache.poi.ooxml.POIXMLDocumentPart;
import org.apache.poi.ooxml.POIXMLRelation;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.openxml4j.opc.PackagePart;

public class XWPFPictureData extends POIXMLDocumentPart {
    private static final int DEFAULT_MAX_IMAGE_SIZE = 100000000;
    private static int MAX_IMAGE_SIZE = 100000000;
    protected static final POIXMLRelation[] RELATIONS;
    private Long checksum;

    /* access modifiers changed from: protected */
    public void prepareForCommit() {
    }

    public static void setMaxImageSize(int i) {
        MAX_IMAGE_SIZE = i;
    }

    public static int getMaxImageSize() {
        return MAX_IMAGE_SIZE;
    }

    static {
        POIXMLRelation[] pOIXMLRelationArr = new POIXMLRelation[13];
        RELATIONS = pOIXMLRelationArr;
        pOIXMLRelationArr[2] = XWPFRelation.IMAGE_EMF;
        pOIXMLRelationArr[3] = XWPFRelation.IMAGE_WMF;
        pOIXMLRelationArr[4] = XWPFRelation.IMAGE_PICT;
        pOIXMLRelationArr[5] = XWPFRelation.IMAGE_JPEG;
        pOIXMLRelationArr[6] = XWPFRelation.IMAGE_PNG;
        pOIXMLRelationArr[7] = XWPFRelation.IMAGE_DIB;
        pOIXMLRelationArr[8] = XWPFRelation.IMAGE_GIF;
        pOIXMLRelationArr[9] = XWPFRelation.IMAGE_TIFF;
        pOIXMLRelationArr[10] = XWPFRelation.IMAGE_EPS;
        pOIXMLRelationArr[11] = XWPFRelation.IMAGE_BMP;
        pOIXMLRelationArr[12] = XWPFRelation.IMAGE_WPG;
    }

    protected XWPFPictureData() {
    }

    public XWPFPictureData(PackagePart packagePart) {
        super(packagePart);
    }

    /* access modifiers changed from: protected */
    public void onDocumentRead() throws IOException {
        super.onDocumentRead();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0018, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0019, code lost:
        if (r2 != null) goto L_0x001b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:?, code lost:
        r2.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0023, code lost:
        throw r1;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public byte[] getData() {
        /*
            r2 = this;
            org.apache.poi.openxml4j.opc.PackagePart r2 = r2.getPackagePart()     // Catch:{ IOException -> 0x0024 }
            java.io.InputStream r2 = r2.getInputStream()     // Catch:{ IOException -> 0x0024 }
            int r0 = getMaxImageSize()     // Catch:{ all -> 0x0016 }
            byte[] r0 = org.apache.poi.util.IOUtils.toByteArrayWithMaxLength(r2, r0)     // Catch:{ all -> 0x0016 }
            if (r2 == 0) goto L_0x0015
            r2.close()     // Catch:{ IOException -> 0x0024 }
        L_0x0015:
            return r0
        L_0x0016:
            r0 = move-exception
            throw r0     // Catch:{ all -> 0x0018 }
        L_0x0018:
            r1 = move-exception
            if (r2 == 0) goto L_0x0023
            r2.close()     // Catch:{ all -> 0x001f }
            goto L_0x0023
        L_0x001f:
            r2 = move-exception
            r0.addSuppressed(r2)     // Catch:{ IOException -> 0x0024 }
        L_0x0023:
            throw r1     // Catch:{ IOException -> 0x0024 }
        L_0x0024:
            r2 = move-exception
            org.apache.poi.ooxml.POIXMLException r0 = new org.apache.poi.ooxml.POIXMLException
            r0.<init>((java.lang.Throwable) r2)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.xwpf.usermodel.XWPFPictureData.getData():byte[]");
    }

    public String getFileName() {
        String name = getPackagePart().getPartName().getName();
        return name.substring(name.lastIndexOf(47) + 1);
    }

    public String suggestFileExtension() {
        return getPackagePart().getPartName().getExtension();
    }

    public int getPictureType() {
        String contentType = getPackagePart().getContentType();
        int i = 0;
        while (true) {
            POIXMLRelation[] pOIXMLRelationArr = RELATIONS;
            if (i >= pOIXMLRelationArr.length) {
                return 0;
            }
            POIXMLRelation pOIXMLRelation = pOIXMLRelationArr[i];
            if (pOIXMLRelation != null && pOIXMLRelation.getContentType().equals(contentType)) {
                return i;
            }
            i++;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:12:0x001e, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x001f, code lost:
        if (r0 != null) goto L_0x0021;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:?, code lost:
        r0.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0029, code lost:
        throw r1;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Long getChecksum() {
        /*
            r3 = this;
            java.lang.Long r0 = r3.checksum
            if (r0 != 0) goto L_0x0031
            org.apache.poi.openxml4j.opc.PackagePart r0 = r3.getPackagePart()     // Catch:{ IOException -> 0x002a }
            java.io.InputStream r0 = r0.getInputStream()     // Catch:{ IOException -> 0x002a }
            long r1 = org.apache.poi.util.IOUtils.calculateChecksum((java.io.InputStream) r0)     // Catch:{ all -> 0x001c }
            java.lang.Long r1 = java.lang.Long.valueOf(r1)     // Catch:{ all -> 0x001c }
            r3.checksum = r1     // Catch:{ all -> 0x001c }
            if (r0 == 0) goto L_0x0031
            r0.close()     // Catch:{ IOException -> 0x002a }
            goto L_0x0031
        L_0x001c:
            r3 = move-exception
            throw r3     // Catch:{ all -> 0x001e }
        L_0x001e:
            r1 = move-exception
            if (r0 == 0) goto L_0x0029
            r0.close()     // Catch:{ all -> 0x0025 }
            goto L_0x0029
        L_0x0025:
            r0 = move-exception
            r3.addSuppressed(r0)     // Catch:{ IOException -> 0x002a }
        L_0x0029:
            throw r1     // Catch:{ IOException -> 0x002a }
        L_0x002a:
            r3 = move-exception
            org.apache.poi.ooxml.POIXMLException r0 = new org.apache.poi.ooxml.POIXMLException
            r0.<init>((java.lang.Throwable) r3)
            throw r0
        L_0x0031:
            java.lang.Long r3 = r3.checksum
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.xwpf.usermodel.XWPFPictureData.getChecksum():java.lang.Long");
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || !(obj instanceof XWPFPictureData)) {
            return false;
        }
        XWPFPictureData xWPFPictureData = (XWPFPictureData) obj;
        PackagePart packagePart = xWPFPictureData.getPackagePart();
        PackagePart packagePart2 = getPackagePart();
        if ((packagePart != null && packagePart2 == null) || (packagePart == null && packagePart2 != null)) {
            return false;
        }
        if (packagePart2 != null) {
            OPCPackage oPCPackage = packagePart.getPackage();
            OPCPackage oPCPackage2 = packagePart2.getPackage();
            if ((oPCPackage != null && oPCPackage2 == null) || (oPCPackage == null && oPCPackage2 != null)) {
                return false;
            }
            if (oPCPackage2 != null && !oPCPackage2.equals(oPCPackage)) {
                return false;
            }
        }
        Long checksum2 = xWPFPictureData.getChecksum();
        Long checksum3 = getChecksum();
        if (checksum3 == null) {
            if (checksum2 != null) {
                return false;
            }
        } else if (!checksum3.equals(checksum2)) {
            return false;
        }
        return Arrays.equals(getData(), xWPFPictureData.getData());
    }

    public int hashCode() {
        Long checksum2 = getChecksum();
        return checksum2 == null ? super.hashCode() : checksum2.hashCode();
    }
}
