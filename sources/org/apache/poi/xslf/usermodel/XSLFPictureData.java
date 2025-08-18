package org.apache.poi.xslf.usermodel;

import java.awt.Dimension;
import java.io.IOException;
import java.io.InputStream;
import org.apache.poi.ooxml.POIXMLDocumentPart;
import org.apache.poi.openxml4j.opc.PackagePart;
import org.apache.poi.sl.image.ImageHeaderBitmap;
import org.apache.poi.sl.image.ImageHeaderEMF;
import org.apache.poi.sl.image.ImageHeaderPICT;
import org.apache.poi.sl.image.ImageHeaderWMF;
import org.apache.poi.sl.usermodel.PictureData;
import org.apache.poi.util.IOUtils;
import org.apache.poi.util.LittleEndian;
import org.apache.poi.util.Units;

public final class XSLFPictureData extends POIXMLDocumentPart implements PictureData {
    private static final int DEFAULT_MAX_IMAGE_SIZE = 100000000;
    private static int MAX_IMAGE_SIZE = 100000000;
    private Long checksum;
    private int index = -1;
    private Dimension origSize;

    /* access modifiers changed from: protected */
    public void prepareForCommit() {
    }

    public static void setMaxImageSize(int i) {
        MAX_IMAGE_SIZE = i;
    }

    public static int getMaxImageSize() {
        return MAX_IMAGE_SIZE;
    }

    protected XSLFPictureData() {
    }

    public XSLFPictureData(PackagePart packagePart) {
        super(packagePart);
    }

    public InputStream getInputStream() throws IOException {
        return getPackagePart().getInputStream();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0014, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0015, code lost:
        if (r2 != null) goto L_0x0017;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:?, code lost:
        r2.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x001f, code lost:
        throw r1;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public byte[] getData() {
        /*
            r2 = this;
            java.io.InputStream r2 = r2.getInputStream()     // Catch:{ IOException -> 0x0020 }
            int r0 = getMaxImageSize()     // Catch:{ all -> 0x0012 }
            byte[] r0 = org.apache.poi.util.IOUtils.toByteArrayWithMaxLength(r2, r0)     // Catch:{ all -> 0x0012 }
            if (r2 == 0) goto L_0x0011
            r2.close()     // Catch:{ IOException -> 0x0020 }
        L_0x0011:
            return r0
        L_0x0012:
            r0 = move-exception
            throw r0     // Catch:{ all -> 0x0014 }
        L_0x0014:
            r1 = move-exception
            if (r2 == 0) goto L_0x001f
            r2.close()     // Catch:{ all -> 0x001b }
            goto L_0x001f
        L_0x001b:
            r2 = move-exception
            r0.addSuppressed(r2)     // Catch:{ IOException -> 0x0020 }
        L_0x001f:
            throw r1     // Catch:{ IOException -> 0x0020 }
        L_0x0020:
            r2 = move-exception
            org.apache.poi.ooxml.POIXMLException r0 = new org.apache.poi.ooxml.POIXMLException
            r0.<init>((java.lang.Throwable) r2)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.xslf.usermodel.XSLFPictureData.getData():byte[]");
    }

    public String getFileName() {
        String name = getPackagePart().getPartName().getName();
        return name.substring(name.lastIndexOf(47) + 1);
    }

    public String suggestFileExtension() {
        return getPackagePart().getPartName().getExtension();
    }

    public byte[] getChecksum() {
        cacheProperties();
        byte[] bArr = new byte[8];
        LittleEndian.putLong(bArr, 0, this.checksum.longValue());
        return bArr;
    }

    public Dimension getImageDimension() {
        cacheProperties();
        return this.origSize;
    }

    public Dimension getImageDimensionInPixels() {
        Dimension imageDimension = getImageDimension();
        return new Dimension(Units.pointsToPixel(imageDimension.getWidth()), Units.pointsToPixel(imageDimension.getHeight()));
    }

    /* access modifiers changed from: protected */
    public void cacheProperties() {
        if (this.origSize == null || this.checksum == null) {
            byte[] data = getData();
            this.checksum = Long.valueOf(IOUtils.calculateChecksum(data));
            PictureData.PictureType type = getType();
            if (type == null) {
                this.origSize = new Dimension(1, 1);
                return;
            }
            int i = AnonymousClass1.$SwitchMap$org$apache$poi$sl$usermodel$PictureData$PictureType[type.ordinal()];
            if (i == 1) {
                this.origSize = new ImageHeaderEMF(data, 0).getSize();
            } else if (i == 2) {
                this.origSize = new ImageHeaderWMF(data, 0).getSize();
            } else if (i != 3) {
                this.origSize = new ImageHeaderBitmap(data, 0).getSize();
            } else {
                this.origSize = new ImageHeaderPICT(data, 0).getSize();
            }
        }
    }

    /* renamed from: org.apache.poi.xslf.usermodel.XSLFPictureData$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$org$apache$poi$sl$usermodel$PictureData$PictureType;

        /* JADX WARNING: Can't wrap try/catch for region: R(26:0|1|2|3|4|5|6|7|8|9|10|11|12|13|14|15|16|17|18|19|20|21|22|23|24|(3:25|26|28)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x003e */
        /* JADX WARNING: Missing exception handler attribute for start block: B:13:0x0049 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:15:0x0054 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:17:0x0060 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:19:0x006c */
        /* JADX WARNING: Missing exception handler attribute for start block: B:21:0x0078 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:23:0x0084 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:25:0x0090 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0033 */
        static {
            /*
                org.apache.poi.sl.usermodel.PictureData$PictureType[] r0 = org.apache.poi.sl.usermodel.PictureData.PictureType.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$org$apache$poi$sl$usermodel$PictureData$PictureType = r0
                org.apache.poi.sl.usermodel.PictureData$PictureType r1 = org.apache.poi.sl.usermodel.PictureData.PictureType.EMF     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$org$apache$poi$sl$usermodel$PictureData$PictureType     // Catch:{ NoSuchFieldError -> 0x001d }
                org.apache.poi.sl.usermodel.PictureData$PictureType r1 = org.apache.poi.sl.usermodel.PictureData.PictureType.WMF     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$org$apache$poi$sl$usermodel$PictureData$PictureType     // Catch:{ NoSuchFieldError -> 0x0028 }
                org.apache.poi.sl.usermodel.PictureData$PictureType r1 = org.apache.poi.sl.usermodel.PictureData.PictureType.PICT     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = $SwitchMap$org$apache$poi$sl$usermodel$PictureData$PictureType     // Catch:{ NoSuchFieldError -> 0x0033 }
                org.apache.poi.sl.usermodel.PictureData$PictureType r1 = org.apache.poi.sl.usermodel.PictureData.PictureType.JPEG     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                int[] r0 = $SwitchMap$org$apache$poi$sl$usermodel$PictureData$PictureType     // Catch:{ NoSuchFieldError -> 0x003e }
                org.apache.poi.sl.usermodel.PictureData$PictureType r1 = org.apache.poi.sl.usermodel.PictureData.PictureType.PNG     // Catch:{ NoSuchFieldError -> 0x003e }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x003e }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x003e }
            L_0x003e:
                int[] r0 = $SwitchMap$org$apache$poi$sl$usermodel$PictureData$PictureType     // Catch:{ NoSuchFieldError -> 0x0049 }
                org.apache.poi.sl.usermodel.PictureData$PictureType r1 = org.apache.poi.sl.usermodel.PictureData.PictureType.DIB     // Catch:{ NoSuchFieldError -> 0x0049 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0049 }
                r2 = 6
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0049 }
            L_0x0049:
                int[] r0 = $SwitchMap$org$apache$poi$sl$usermodel$PictureData$PictureType     // Catch:{ NoSuchFieldError -> 0x0054 }
                org.apache.poi.sl.usermodel.PictureData$PictureType r1 = org.apache.poi.sl.usermodel.PictureData.PictureType.GIF     // Catch:{ NoSuchFieldError -> 0x0054 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0054 }
                r2 = 7
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0054 }
            L_0x0054:
                int[] r0 = $SwitchMap$org$apache$poi$sl$usermodel$PictureData$PictureType     // Catch:{ NoSuchFieldError -> 0x0060 }
                org.apache.poi.sl.usermodel.PictureData$PictureType r1 = org.apache.poi.sl.usermodel.PictureData.PictureType.EPS     // Catch:{ NoSuchFieldError -> 0x0060 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0060 }
                r2 = 8
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0060 }
            L_0x0060:
                int[] r0 = $SwitchMap$org$apache$poi$sl$usermodel$PictureData$PictureType     // Catch:{ NoSuchFieldError -> 0x006c }
                org.apache.poi.sl.usermodel.PictureData$PictureType r1 = org.apache.poi.sl.usermodel.PictureData.PictureType.BMP     // Catch:{ NoSuchFieldError -> 0x006c }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x006c }
                r2 = 9
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x006c }
            L_0x006c:
                int[] r0 = $SwitchMap$org$apache$poi$sl$usermodel$PictureData$PictureType     // Catch:{ NoSuchFieldError -> 0x0078 }
                org.apache.poi.sl.usermodel.PictureData$PictureType r1 = org.apache.poi.sl.usermodel.PictureData.PictureType.WPG     // Catch:{ NoSuchFieldError -> 0x0078 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0078 }
                r2 = 10
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0078 }
            L_0x0078:
                int[] r0 = $SwitchMap$org$apache$poi$sl$usermodel$PictureData$PictureType     // Catch:{ NoSuchFieldError -> 0x0084 }
                org.apache.poi.sl.usermodel.PictureData$PictureType r1 = org.apache.poi.sl.usermodel.PictureData.PictureType.WDP     // Catch:{ NoSuchFieldError -> 0x0084 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0084 }
                r2 = 11
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0084 }
            L_0x0084:
                int[] r0 = $SwitchMap$org$apache$poi$sl$usermodel$PictureData$PictureType     // Catch:{ NoSuchFieldError -> 0x0090 }
                org.apache.poi.sl.usermodel.PictureData$PictureType r1 = org.apache.poi.sl.usermodel.PictureData.PictureType.TIFF     // Catch:{ NoSuchFieldError -> 0x0090 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0090 }
                r2 = 12
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0090 }
            L_0x0090:
                int[] r0 = $SwitchMap$org$apache$poi$sl$usermodel$PictureData$PictureType     // Catch:{ NoSuchFieldError -> 0x009c }
                org.apache.poi.sl.usermodel.PictureData$PictureType r1 = org.apache.poi.sl.usermodel.PictureData.PictureType.SVG     // Catch:{ NoSuchFieldError -> 0x009c }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x009c }
                r2 = 13
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x009c }
            L_0x009c:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.xslf.usermodel.XSLFPictureData.AnonymousClass1.<clinit>():void");
        }
    }

    public String getContentType() {
        return getPackagePart().getContentType();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0020, code lost:
        r3 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0021, code lost:
        if (r0 != null) goto L_0x0023;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:?, code lost:
        r0.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0027, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0028, code lost:
        r2.addSuppressed(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x002b, code lost:
        throw r3;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void setData(byte[] r3) throws java.io.IOException {
        /*
            r2 = this;
            org.apache.poi.openxml4j.opc.PackagePart r0 = r2.getPackagePart()
            java.io.OutputStream r0 = r0.getOutputStream()
            r0.write(r3)     // Catch:{ all -> 0x001e }
            if (r0 == 0) goto L_0x0010
            r0.close()
        L_0x0010:
            long r0 = org.apache.poi.util.IOUtils.calculateChecksum((byte[]) r3)
            java.lang.Long r3 = java.lang.Long.valueOf(r0)
            r2.checksum = r3
            r3 = 0
            r2.origSize = r3
            return
        L_0x001e:
            r2 = move-exception
            throw r2     // Catch:{ all -> 0x0020 }
        L_0x0020:
            r3 = move-exception
            if (r0 == 0) goto L_0x002b
            r0.close()     // Catch:{ all -> 0x0027 }
            goto L_0x002b
        L_0x0027:
            r0 = move-exception
            r2.addSuppressed(r0)
        L_0x002b:
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.xslf.usermodel.XSLFPictureData.setData(byte[]):void");
    }

    public PictureData.PictureType getType() {
        String contentType = getContentType();
        if (XSLFRelation.IMAGE_EMF.getContentType().equals(contentType)) {
            return PictureData.PictureType.EMF;
        }
        if (XSLFRelation.IMAGE_WMF.getContentType().equals(contentType)) {
            return PictureData.PictureType.WMF;
        }
        if (XSLFRelation.IMAGE_PICT.getContentType().equals(contentType)) {
            return PictureData.PictureType.PICT;
        }
        if (XSLFRelation.IMAGE_JPEG.getContentType().equals(contentType)) {
            return PictureData.PictureType.JPEG;
        }
        if (XSLFRelation.IMAGE_PNG.getContentType().equals(contentType)) {
            return PictureData.PictureType.PNG;
        }
        if (XSLFRelation.IMAGE_DIB.getContentType().equals(contentType)) {
            return PictureData.PictureType.DIB;
        }
        if (XSLFRelation.IMAGE_GIF.getContentType().equals(contentType)) {
            return PictureData.PictureType.GIF;
        }
        if (XSLFRelation.IMAGE_EPS.getContentType().equals(contentType)) {
            return PictureData.PictureType.EPS;
        }
        if (XSLFRelation.IMAGE_BMP.getContentType().equals(contentType)) {
            return PictureData.PictureType.BMP;
        }
        if (XSLFRelation.IMAGE_WPG.getContentType().equals(contentType)) {
            return PictureData.PictureType.WPG;
        }
        if (XSLFRelation.IMAGE_WDP.getContentType().equals(contentType)) {
            return PictureData.PictureType.WDP;
        }
        if (XSLFRelation.IMAGE_TIFF.getContentType().equals(contentType)) {
            return PictureData.PictureType.TIFF;
        }
        if (XSLFRelation.IMAGE_SVG.getContentType().equals(contentType)) {
            return PictureData.PictureType.SVG;
        }
        return null;
    }

    static XSLFRelation getRelationForType(PictureData.PictureType pictureType) {
        switch (AnonymousClass1.$SwitchMap$org$apache$poi$sl$usermodel$PictureData$PictureType[pictureType.ordinal()]) {
            case 1:
                return XSLFRelation.IMAGE_EMF;
            case 2:
                return XSLFRelation.IMAGE_WMF;
            case 3:
                return XSLFRelation.IMAGE_PICT;
            case 4:
                return XSLFRelation.IMAGE_JPEG;
            case 5:
                return XSLFRelation.IMAGE_PNG;
            case 6:
                return XSLFRelation.IMAGE_DIB;
            case 7:
                return XSLFRelation.IMAGE_GIF;
            case 8:
                return XSLFRelation.IMAGE_EPS;
            case 9:
                return XSLFRelation.IMAGE_BMP;
            case 10:
                return XSLFRelation.IMAGE_WPG;
            case 11:
                return XSLFRelation.IMAGE_WDP;
            case 12:
                return XSLFRelation.IMAGE_TIFF;
            case 13:
                return XSLFRelation.IMAGE_SVG;
            default:
                return null;
        }
    }

    public int getIndex() {
        return this.index;
    }

    public void setIndex(int i) {
        this.index = i;
    }
}
