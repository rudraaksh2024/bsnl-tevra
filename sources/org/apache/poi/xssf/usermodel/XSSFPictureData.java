package org.apache.poi.xssf.usermodel;

import org.apache.poi.ooxml.POIXMLDocumentPart;
import org.apache.poi.ooxml.POIXMLRelation;
import org.apache.poi.openxml4j.opc.PackagePart;
import org.apache.poi.ss.usermodel.PictureData;

public class XSSFPictureData extends POIXMLDocumentPart implements PictureData {
    private static final int DEFAULT_MAX_IMAGE_SIZE = 100000000;
    private static int MAX_IMAGE_SIZE = 100000000;
    protected static final POIXMLRelation[] RELATIONS;

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
        pOIXMLRelationArr[2] = XSSFRelation.IMAGE_EMF;
        pOIXMLRelationArr[3] = XSSFRelation.IMAGE_WMF;
        pOIXMLRelationArr[4] = XSSFRelation.IMAGE_PICT;
        pOIXMLRelationArr[5] = XSSFRelation.IMAGE_JPEG;
        pOIXMLRelationArr[6] = XSSFRelation.IMAGE_PNG;
        pOIXMLRelationArr[7] = XSSFRelation.IMAGE_DIB;
        pOIXMLRelationArr[8] = XSSFRelation.IMAGE_GIF;
        pOIXMLRelationArr[9] = XSSFRelation.IMAGE_TIFF;
        pOIXMLRelationArr[10] = XSSFRelation.IMAGE_EPS;
        pOIXMLRelationArr[11] = XSSFRelation.IMAGE_BMP;
        pOIXMLRelationArr[12] = XSSFRelation.IMAGE_WPG;
    }

    protected XSSFPictureData() {
    }

    protected XSSFPictureData(PackagePart packagePart) {
        super(packagePart);
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
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.xssf.usermodel.XSSFPictureData.getData():byte[]");
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

    public String getMimeType() {
        return getPackagePart().getContentType();
    }
}
