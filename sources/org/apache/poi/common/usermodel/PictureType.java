package org.apache.poi.common.usermodel;

import org.apache.poi.openxml4j.opc.ContentTypes;
import org.apache.poi.poifs.filesystem.FileMagic;

public enum PictureType {
    EMF("image/x-emf", ".emf"),
    WMF("image/x-wmf", ".wmf"),
    PICT(ContentTypes.IMAGE_PICT, ".pict"),
    JPEG(ContentTypes.IMAGE_JPEG, ".jpg"),
    PNG(ContentTypes.IMAGE_PNG, ".png"),
    DIB("image/dib", ".dib"),
    GIF(ContentTypes.IMAGE_GIF, ".gif"),
    TIFF(ContentTypes.IMAGE_TIFF, ".tif"),
    EPS("image/x-eps", ".eps"),
    BMP("image/x-ms-bmp", ".bmp"),
    WPG("image/x-wpg", ".wpg"),
    WDP("image/vnd.ms-photo", ".wdp"),
    SVG("image/svg+xml", ".svg"),
    UNKNOWN("", ".dat"),
    ERROR("", ".dat"),
    CMYKJPEG(ContentTypes.IMAGE_JPEG, ".jpg"),
    CLIENT("", ".dat");
    
    public final String contentType;
    public final String extension;

    private PictureType(String str, String str2) {
        this.contentType = str;
        this.extension = str2;
    }

    public String getContentType() {
        return this.contentType;
    }

    public String getExtension() {
        return this.extension;
    }

    /* renamed from: org.apache.poi.common.usermodel.PictureType$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$org$apache$poi$poifs$filesystem$FileMagic = null;

        /* JADX WARNING: Can't wrap try/catch for region: R(18:0|1|2|3|4|5|6|7|8|9|10|11|12|13|14|15|16|(3:17|18|20)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x003e */
        /* JADX WARNING: Missing exception handler attribute for start block: B:13:0x0049 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:15:0x0054 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:17:0x0060 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0033 */
        static {
            /*
                org.apache.poi.poifs.filesystem.FileMagic[] r0 = org.apache.poi.poifs.filesystem.FileMagic.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$org$apache$poi$poifs$filesystem$FileMagic = r0
                org.apache.poi.poifs.filesystem.FileMagic r1 = org.apache.poi.poifs.filesystem.FileMagic.BMP     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$org$apache$poi$poifs$filesystem$FileMagic     // Catch:{ NoSuchFieldError -> 0x001d }
                org.apache.poi.poifs.filesystem.FileMagic r1 = org.apache.poi.poifs.filesystem.FileMagic.GIF     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$org$apache$poi$poifs$filesystem$FileMagic     // Catch:{ NoSuchFieldError -> 0x0028 }
                org.apache.poi.poifs.filesystem.FileMagic r1 = org.apache.poi.poifs.filesystem.FileMagic.JPEG     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = $SwitchMap$org$apache$poi$poifs$filesystem$FileMagic     // Catch:{ NoSuchFieldError -> 0x0033 }
                org.apache.poi.poifs.filesystem.FileMagic r1 = org.apache.poi.poifs.filesystem.FileMagic.PNG     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                int[] r0 = $SwitchMap$org$apache$poi$poifs$filesystem$FileMagic     // Catch:{ NoSuchFieldError -> 0x003e }
                org.apache.poi.poifs.filesystem.FileMagic r1 = org.apache.poi.poifs.filesystem.FileMagic.XML     // Catch:{ NoSuchFieldError -> 0x003e }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x003e }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x003e }
            L_0x003e:
                int[] r0 = $SwitchMap$org$apache$poi$poifs$filesystem$FileMagic     // Catch:{ NoSuchFieldError -> 0x0049 }
                org.apache.poi.poifs.filesystem.FileMagic r1 = org.apache.poi.poifs.filesystem.FileMagic.WMF     // Catch:{ NoSuchFieldError -> 0x0049 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0049 }
                r2 = 6
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0049 }
            L_0x0049:
                int[] r0 = $SwitchMap$org$apache$poi$poifs$filesystem$FileMagic     // Catch:{ NoSuchFieldError -> 0x0054 }
                org.apache.poi.poifs.filesystem.FileMagic r1 = org.apache.poi.poifs.filesystem.FileMagic.EMF     // Catch:{ NoSuchFieldError -> 0x0054 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0054 }
                r2 = 7
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0054 }
            L_0x0054:
                int[] r0 = $SwitchMap$org$apache$poi$poifs$filesystem$FileMagic     // Catch:{ NoSuchFieldError -> 0x0060 }
                org.apache.poi.poifs.filesystem.FileMagic r1 = org.apache.poi.poifs.filesystem.FileMagic.TIFF     // Catch:{ NoSuchFieldError -> 0x0060 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0060 }
                r2 = 8
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0060 }
            L_0x0060:
                int[] r0 = $SwitchMap$org$apache$poi$poifs$filesystem$FileMagic     // Catch:{ NoSuchFieldError -> 0x006c }
                org.apache.poi.poifs.filesystem.FileMagic r1 = org.apache.poi.poifs.filesystem.FileMagic.UNKNOWN     // Catch:{ NoSuchFieldError -> 0x006c }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x006c }
                r2 = 9
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x006c }
            L_0x006c:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.common.usermodel.PictureType.AnonymousClass1.<clinit>():void");
        }
    }

    public static PictureType valueOf(FileMagic fileMagic) {
        switch (AnonymousClass1.$SwitchMap$org$apache$poi$poifs$filesystem$FileMagic[fileMagic.ordinal()]) {
            case 1:
                return BMP;
            case 2:
                return GIF;
            case 3:
                return JPEG;
            case 4:
                return PNG;
            case 5:
                return SVG;
            case 6:
                return WMF;
            case 7:
                return EMF;
            case 8:
                return TIFF;
            default:
                return UNKNOWN;
        }
    }
}
