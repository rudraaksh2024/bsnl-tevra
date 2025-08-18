package org.apache.poi.hssf.usermodel;

import org.apache.poi.ddf.EscherBlipRecord;
import org.apache.poi.ddf.EscherRecordTypes;
import org.apache.poi.openxml4j.opc.ContentTypes;
import org.apache.poi.sl.image.ImageHeaderPNG;
import org.apache.poi.ss.usermodel.PictureData;

public class HSSFPictureData implements PictureData {
    public static final short FORMAT_MASK = -16;
    public static final short MSOBI_DIB = 31360;
    public static final short MSOBI_EMF = 15680;
    public static final short MSOBI_JPEG = 18080;
    public static final short MSOBI_PICT = 21536;
    public static final short MSOBI_PNG = 28160;
    public static final short MSOBI_WMF = 8544;
    private final EscherBlipRecord blip;

    public HSSFPictureData(EscherBlipRecord escherBlipRecord) {
        this.blip = escherBlipRecord;
    }

    public byte[] getData() {
        return new ImageHeaderPNG(this.blip.getPicturedata()).extractPNG();
    }

    public int getFormat() {
        return this.blip.getRecordId() - EscherRecordTypes.BLIP_START.typeID;
    }

    /* renamed from: org.apache.poi.hssf.usermodel.HSSFPictureData$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$org$apache$poi$ddf$EscherRecordTypes;

        /* JADX WARNING: Can't wrap try/catch for region: R(14:0|1|2|3|4|5|6|7|8|9|10|11|12|(3:13|14|16)) */
        /* JADX WARNING: Can't wrap try/catch for region: R(16:0|1|2|3|4|5|6|7|8|9|10|11|12|13|14|16) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x003e */
        /* JADX WARNING: Missing exception handler attribute for start block: B:13:0x0049 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0033 */
        static {
            /*
                org.apache.poi.ddf.EscherRecordTypes[] r0 = org.apache.poi.ddf.EscherRecordTypes.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$org$apache$poi$ddf$EscherRecordTypes = r0
                org.apache.poi.ddf.EscherRecordTypes r1 = org.apache.poi.ddf.EscherRecordTypes.BLIP_WMF     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$org$apache$poi$ddf$EscherRecordTypes     // Catch:{ NoSuchFieldError -> 0x001d }
                org.apache.poi.ddf.EscherRecordTypes r1 = org.apache.poi.ddf.EscherRecordTypes.BLIP_EMF     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$org$apache$poi$ddf$EscherRecordTypes     // Catch:{ NoSuchFieldError -> 0x0028 }
                org.apache.poi.ddf.EscherRecordTypes r1 = org.apache.poi.ddf.EscherRecordTypes.BLIP_PICT     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = $SwitchMap$org$apache$poi$ddf$EscherRecordTypes     // Catch:{ NoSuchFieldError -> 0x0033 }
                org.apache.poi.ddf.EscherRecordTypes r1 = org.apache.poi.ddf.EscherRecordTypes.BLIP_PNG     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                int[] r0 = $SwitchMap$org$apache$poi$ddf$EscherRecordTypes     // Catch:{ NoSuchFieldError -> 0x003e }
                org.apache.poi.ddf.EscherRecordTypes r1 = org.apache.poi.ddf.EscherRecordTypes.BLIP_JPEG     // Catch:{ NoSuchFieldError -> 0x003e }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x003e }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x003e }
            L_0x003e:
                int[] r0 = $SwitchMap$org$apache$poi$ddf$EscherRecordTypes     // Catch:{ NoSuchFieldError -> 0x0049 }
                org.apache.poi.ddf.EscherRecordTypes r1 = org.apache.poi.ddf.EscherRecordTypes.BLIP_DIB     // Catch:{ NoSuchFieldError -> 0x0049 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0049 }
                r2 = 6
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0049 }
            L_0x0049:
                int[] r0 = $SwitchMap$org$apache$poi$ddf$EscherRecordTypes     // Catch:{ NoSuchFieldError -> 0x0054 }
                org.apache.poi.ddf.EscherRecordTypes r1 = org.apache.poi.ddf.EscherRecordTypes.BLIP_TIFF     // Catch:{ NoSuchFieldError -> 0x0054 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0054 }
                r2 = 7
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0054 }
            L_0x0054:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.hssf.usermodel.HSSFPictureData.AnonymousClass1.<clinit>():void");
        }
    }

    public String suggestFileExtension() {
        switch (AnonymousClass1.$SwitchMap$org$apache$poi$ddf$EscherRecordTypes[EscherRecordTypes.forTypeID(this.blip.getRecordId()).ordinal()]) {
            case 1:
                return "wmf";
            case 2:
                return "emf";
            case 3:
                return ContentTypes.EXTENSION_PICT;
            case 4:
                return ContentTypes.EXTENSION_PNG;
            case 5:
                return ContentTypes.EXTENSION_JPG_2;
            case 6:
                return "dib";
            case 7:
                return "tif";
            default:
                return "";
        }
    }

    public String getMimeType() {
        switch (AnonymousClass1.$SwitchMap$org$apache$poi$ddf$EscherRecordTypes[EscherRecordTypes.forTypeID(this.blip.getRecordId()).ordinal()]) {
            case 1:
                return "image/x-wmf";
            case 2:
                return "image/x-emf";
            case 3:
                return ContentTypes.IMAGE_PICT;
            case 4:
                return ContentTypes.IMAGE_PNG;
            case 5:
                return ContentTypes.IMAGE_JPEG;
            case 6:
                return "image/bmp";
            case 7:
                return ContentTypes.IMAGE_TIFF;
            default:
                return "image/unknown";
        }
    }

    public int getPictureType() {
        switch (AnonymousClass1.$SwitchMap$org$apache$poi$ddf$EscherRecordTypes[EscherRecordTypes.forTypeID(this.blip.getRecordId()).ordinal()]) {
            case 1:
                return 3;
            case 2:
                return 2;
            case 3:
                return 4;
            case 4:
                return 6;
            case 5:
                return 5;
            case 6:
                return 7;
            default:
                return 0;
        }
    }
}
