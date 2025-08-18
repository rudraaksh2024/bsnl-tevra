package org.apache.poi.common.usermodel.fonts;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Supplier;
import org.apache.poi.common.usermodel.GenericRecord;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.IOUtils;
import org.apache.poi.util.LittleEndianByteArrayInputStream;
import org.apache.poi.util.LittleEndianInput;
import org.apache.poi.util.LittleEndianInputStream;

public class FontHeader implements FontInfo, GenericRecord {
    private static final int[] FLAGS_MASKS = {1, 4, 16, 32, 64, 128, 268435456};
    private static final String[] FLAGS_NAMES = {"SUBSET", "TTCOMPRESSED", "FAILIFVARIATIONSIMULATED", "EMBEDEUDC", "VALIDATIONTESTS", "WEBOBJECT", "XORENCRYPTDATA"};
    private static final int[] FSTYPE_MASKS = {0, 2, 4, 8, 256, 512};
    private static final String[] FSTYPE_NAMES = {"INSTALLABLE_EMBEDDING", "RESTRICTED_LICENSE_EMBEDDING", "PREVIEW_PRINT_EMBEDDING", "EDITABLE_EMBEDDING", "NO_SUBSETTING", "BITMAP_EMBEDDING_ONLY"};
    public static final int REGULAR_WEIGHT = 400;
    private byte charset;
    private int checkSumAdjustment;
    private int codePageRange1;
    private int codePageRange2;
    private int eotSize;
    private String familyName;
    private int flags;
    private int fontDataSize;
    private int fsType;
    private String fullName;
    private byte italic;
    private int magic;
    private final byte[] panose = new byte[10];
    private String styleName;
    private int unicodeRange1;
    private int unicodeRange2;
    private int unicodeRange3;
    private int unicodeRange4;
    private int version;
    private String versionName;
    private int weight;

    public enum PanoseArmStyle {
        ANY,
        NO_FIT,
        STRAIGHT_ARMS_HORZ,
        STRAIGHT_ARMS_WEDGE,
        STRAIGHT_ARMS_VERT,
        STRAIGHT_ARMS_SINGLE_SERIF,
        STRAIGHT_ARMS_DOUBLE_SERIF,
        BENT_ARMS_HORZ,
        BENT_ARMS_WEDGE,
        BENT_ARMS_VERT,
        BENT_ARMS_SINGLE_SERIF,
        BENT_ARMS_DOUBLE_SERIF
    }

    public enum PanoseContrast {
        ANY,
        NO_FIT,
        NONE,
        VERY_LOW,
        LOW,
        MEDIUM_LOW,
        MEDIUM,
        MEDIUM_HIGH,
        HIGH,
        VERY_HIGH
    }

    public enum PanoseFamily {
        ANY,
        NO_FIT,
        TEXT_DISPLAY,
        SCRIPT,
        DECORATIVE,
        PICTORIAL
    }

    public enum PanoseLetterForm {
        ANY,
        NO_FIT,
        NORMAL_CONTACT,
        NORMAL_WEIGHTED,
        NORMAL_BOXED,
        NORMAL_FLATTENED,
        NORMAL_ROUNDED,
        NORMAL_OFF_CENTER,
        NORMAL_SQUARE,
        OBLIQUE_CONTACT,
        OBLIQUE_WEIGHTED,
        OBLIQUE_BOXED,
        OBLIQUE_FLATTENED,
        OBLIQUE_ROUNDED,
        OBLIQUE_OFF_CENTER,
        OBLIQUE_SQUARE
    }

    public enum PanoseMidLine {
        ANY,
        NO_FIT,
        STANDARD_TRIMMED,
        STANDARD_POINTED,
        STANDARD_SERIFED,
        HIGH_TRIMMED,
        HIGH_POINTED,
        HIGH_SERIFED,
        CONSTANT_TRIMMED,
        CONSTANT_POINTED,
        CONSTANT_SERIFED,
        LOW_TRIMMED,
        LOW_POINTED,
        LOW_SERIFED
    }

    public enum PanoseProportion {
        ANY,
        NO_FIT,
        OLD_STYLE,
        MODERN,
        EVEN_WIDTH,
        EXPANDED,
        CONDENSED,
        VERY_EXPANDED,
        VERY_CONDENSED,
        MONOSPACED
    }

    public enum PanoseSerif {
        ANY,
        NO_FIT,
        COVE,
        OBTUSE_COVE,
        SQUARE_COVE,
        OBTUSE_SQUARE_COVE,
        SQUARE,
        THIN,
        BONE,
        EXAGGERATED,
        TRIANGLE,
        NORMAL_SANS,
        OBTUSE_SANS,
        PERP_SANS,
        FLARED,
        ROUNDED
    }

    public enum PanoseStroke {
        ANY,
        NO_FIT,
        GRADUAL_DIAG,
        GRADUAL_TRAN,
        GRADUAL_VERT,
        GRADUAL_HORZ,
        RAPID_VERT,
        RAPID_HORZ,
        INSTANT_VERT
    }

    public enum PanoseWeight {
        ANY,
        NO_FIT,
        VERY_LIGHT,
        LIGHT,
        THIN,
        BOOK,
        MEDIUM,
        DEMI,
        BOLD,
        HEAVY,
        BLACK,
        NORD
    }

    public enum PanoseXHeight {
        ANY,
        NO_FIT,
        CONSTANT_SMALL,
        CONSTANT_STD,
        CONSTANT_LARGE,
        DUCKING_SMALL,
        DUCKING_STD,
        DUCKING_LARGE
    }

    public void init(byte[] bArr, int i, int i2) {
        init(new LittleEndianByteArrayInputStream(bArr, i, i2));
    }

    public void init(LittleEndianInput littleEndianInput) {
        this.eotSize = littleEndianInput.readInt();
        this.fontDataSize = littleEndianInput.readInt();
        int readInt = littleEndianInput.readInt();
        this.version = readInt;
        if (readInt == 65536 || readInt == 131073 || readInt == 131074) {
            this.flags = littleEndianInput.readInt();
            littleEndianInput.readFully(this.panose);
            this.charset = littleEndianInput.readByte();
            this.italic = littleEndianInput.readByte();
            this.weight = littleEndianInput.readInt();
            this.fsType = littleEndianInput.readUShort();
            int readUShort = littleEndianInput.readUShort();
            this.magic = readUShort;
            if (readUShort == 20556) {
                this.unicodeRange1 = littleEndianInput.readInt();
                this.unicodeRange2 = littleEndianInput.readInt();
                this.unicodeRange3 = littleEndianInput.readInt();
                this.unicodeRange4 = littleEndianInput.readInt();
                this.codePageRange1 = littleEndianInput.readInt();
                this.codePageRange2 = littleEndianInput.readInt();
                this.checkSumAdjustment = littleEndianInput.readInt();
                littleEndianInput.readInt();
                littleEndianInput.readInt();
                littleEndianInput.readInt();
                littleEndianInput.readInt();
                this.familyName = readName(littleEndianInput);
                this.styleName = readName(littleEndianInput);
                this.versionName = readName(littleEndianInput);
                this.fullName = readName(littleEndianInput);
                return;
            }
            throw new RuntimeException("not a EOT font data stream");
        }
        throw new RuntimeException("not a EOT font data stream");
    }

    public InputStream bufferInit(InputStream inputStream) throws IOException {
        LittleEndianInputStream littleEndianInputStream = new LittleEndianInputStream(inputStream);
        littleEndianInputStream.mark(1000);
        init(littleEndianInputStream);
        littleEndianInputStream.reset();
        return littleEndianInputStream;
    }

    private String readName(LittleEndianInput littleEndianInput) {
        littleEndianInput.readShort();
        int readUShort = littleEndianInput.readUShort();
        byte[] safelyAllocate = IOUtils.safelyAllocate((long) readUShort, 1000);
        littleEndianInput.readFully(safelyAllocate);
        return new String(safelyAllocate, 0, readUShort, StandardCharsets.UTF_16LE).trim();
    }

    public boolean isItalic() {
        return this.italic != 0;
    }

    public int getWeight() {
        return this.weight;
    }

    public boolean isBold() {
        return getWeight() > 400;
    }

    public byte getCharsetByte() {
        return this.charset;
    }

    public FontCharset getCharset() {
        return FontCharset.valueOf((int) getCharsetByte());
    }

    public FontPitch getPitch() {
        int i = AnonymousClass1.$SwitchMap$org$apache$poi$common$usermodel$fonts$FontHeader$PanoseFamily[getPanoseFamily().ordinal()];
        if (i == 3 || i == 4) {
            return getPanoseProportion() == PanoseProportion.MONOSPACED ? FontPitch.FIXED : FontPitch.VARIABLE;
        }
        if (i == 5 || i == 6) {
            return getPanoseProportion() == PanoseProportion.MODERN ? FontPitch.FIXED : FontPitch.VARIABLE;
        }
        return FontPitch.VARIABLE;
    }

    public FontFamily getFamily() {
        int i = AnonymousClass1.$SwitchMap$org$apache$poi$common$usermodel$fonts$FontHeader$PanoseFamily[getPanoseFamily().ordinal()];
        if (i == 1 || i == 2) {
            return FontFamily.FF_DONTCARE;
        }
        if (i == 3) {
            switch (AnonymousClass1.$SwitchMap$org$apache$poi$common$usermodel$fonts$FontHeader$PanoseSerif[getPanoseSerif().ordinal()]) {
                case 1:
                case 2:
                case 3:
                case 4:
                case 5:
                case 6:
                    return FontFamily.FF_SWISS;
                default:
                    return FontFamily.FF_ROMAN;
            }
        } else if (i == 5) {
            return FontFamily.FF_SCRIPT;
        } else {
            if (i != 6) {
                return FontFamily.FF_DECORATIVE;
            }
            return FontFamily.FF_MODERN;
        }
    }

    /* renamed from: org.apache.poi.common.usermodel.fonts.FontHeader$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$org$apache$poi$common$usermodel$fonts$FontHeader$PanoseFamily;
        static final /* synthetic */ int[] $SwitchMap$org$apache$poi$common$usermodel$fonts$FontHeader$PanoseSerif;

        /* JADX WARNING: Can't wrap try/catch for region: R(28:0|(2:1|2)|3|(2:5|6)|7|9|10|11|(2:13|14)|15|(2:17|18)|19|21|22|23|25|26|27|28|29|30|31|32|33|34|35|36|38) */
        /* JADX WARNING: Can't wrap try/catch for region: R(29:0|1|2|3|(2:5|6)|7|9|10|11|(2:13|14)|15|(2:17|18)|19|21|22|23|25|26|27|28|29|30|31|32|33|34|35|36|38) */
        /* JADX WARNING: Can't wrap try/catch for region: R(30:0|1|2|3|(2:5|6)|7|9|10|11|13|14|15|(2:17|18)|19|21|22|23|25|26|27|28|29|30|31|32|33|34|35|36|38) */
        /* JADX WARNING: Can't wrap try/catch for region: R(32:0|1|2|3|5|6|7|9|10|11|13|14|15|17|18|19|21|22|23|25|26|27|28|29|30|31|32|33|34|35|36|38) */
        /* JADX WARNING: Code restructure failed: missing block: B:39:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:27:0x005a */
        /* JADX WARNING: Missing exception handler attribute for start block: B:29:0x0064 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:31:0x006e */
        /* JADX WARNING: Missing exception handler attribute for start block: B:33:0x0078 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:35:0x0082 */
        static {
            /*
                org.apache.poi.common.usermodel.fonts.FontHeader$PanoseSerif[] r0 = org.apache.poi.common.usermodel.fonts.FontHeader.PanoseSerif.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$org$apache$poi$common$usermodel$fonts$FontHeader$PanoseSerif = r0
                r1 = 1
                org.apache.poi.common.usermodel.fonts.FontHeader$PanoseSerif r2 = org.apache.poi.common.usermodel.fonts.FontHeader.PanoseSerif.TRIANGLE     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r2 = r2.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r0[r2] = r1     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                r0 = 2
                int[] r2 = $SwitchMap$org$apache$poi$common$usermodel$fonts$FontHeader$PanoseSerif     // Catch:{ NoSuchFieldError -> 0x001d }
                org.apache.poi.common.usermodel.fonts.FontHeader$PanoseSerif r3 = org.apache.poi.common.usermodel.fonts.FontHeader.PanoseSerif.NORMAL_SANS     // Catch:{ NoSuchFieldError -> 0x001d }
                int r3 = r3.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2[r3] = r0     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                r2 = 3
                int[] r3 = $SwitchMap$org$apache$poi$common$usermodel$fonts$FontHeader$PanoseSerif     // Catch:{ NoSuchFieldError -> 0x0028 }
                org.apache.poi.common.usermodel.fonts.FontHeader$PanoseSerif r4 = org.apache.poi.common.usermodel.fonts.FontHeader.PanoseSerif.OBTUSE_SANS     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r3[r4] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                r3 = 4
                int[] r4 = $SwitchMap$org$apache$poi$common$usermodel$fonts$FontHeader$PanoseSerif     // Catch:{ NoSuchFieldError -> 0x0033 }
                org.apache.poi.common.usermodel.fonts.FontHeader$PanoseSerif r5 = org.apache.poi.common.usermodel.fonts.FontHeader.PanoseSerif.PERP_SANS     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r5 = r5.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r4[r5] = r3     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                r4 = 5
                int[] r5 = $SwitchMap$org$apache$poi$common$usermodel$fonts$FontHeader$PanoseSerif     // Catch:{ NoSuchFieldError -> 0x003e }
                org.apache.poi.common.usermodel.fonts.FontHeader$PanoseSerif r6 = org.apache.poi.common.usermodel.fonts.FontHeader.PanoseSerif.FLARED     // Catch:{ NoSuchFieldError -> 0x003e }
                int r6 = r6.ordinal()     // Catch:{ NoSuchFieldError -> 0x003e }
                r5[r6] = r4     // Catch:{ NoSuchFieldError -> 0x003e }
            L_0x003e:
                r5 = 6
                int[] r6 = $SwitchMap$org$apache$poi$common$usermodel$fonts$FontHeader$PanoseSerif     // Catch:{ NoSuchFieldError -> 0x0049 }
                org.apache.poi.common.usermodel.fonts.FontHeader$PanoseSerif r7 = org.apache.poi.common.usermodel.fonts.FontHeader.PanoseSerif.ROUNDED     // Catch:{ NoSuchFieldError -> 0x0049 }
                int r7 = r7.ordinal()     // Catch:{ NoSuchFieldError -> 0x0049 }
                r6[r7] = r5     // Catch:{ NoSuchFieldError -> 0x0049 }
            L_0x0049:
                org.apache.poi.common.usermodel.fonts.FontHeader$PanoseFamily[] r6 = org.apache.poi.common.usermodel.fonts.FontHeader.PanoseFamily.values()
                int r6 = r6.length
                int[] r6 = new int[r6]
                $SwitchMap$org$apache$poi$common$usermodel$fonts$FontHeader$PanoseFamily = r6
                org.apache.poi.common.usermodel.fonts.FontHeader$PanoseFamily r7 = org.apache.poi.common.usermodel.fonts.FontHeader.PanoseFamily.ANY     // Catch:{ NoSuchFieldError -> 0x005a }
                int r7 = r7.ordinal()     // Catch:{ NoSuchFieldError -> 0x005a }
                r6[r7] = r1     // Catch:{ NoSuchFieldError -> 0x005a }
            L_0x005a:
                int[] r1 = $SwitchMap$org$apache$poi$common$usermodel$fonts$FontHeader$PanoseFamily     // Catch:{ NoSuchFieldError -> 0x0064 }
                org.apache.poi.common.usermodel.fonts.FontHeader$PanoseFamily r6 = org.apache.poi.common.usermodel.fonts.FontHeader.PanoseFamily.NO_FIT     // Catch:{ NoSuchFieldError -> 0x0064 }
                int r6 = r6.ordinal()     // Catch:{ NoSuchFieldError -> 0x0064 }
                r1[r6] = r0     // Catch:{ NoSuchFieldError -> 0x0064 }
            L_0x0064:
                int[] r0 = $SwitchMap$org$apache$poi$common$usermodel$fonts$FontHeader$PanoseFamily     // Catch:{ NoSuchFieldError -> 0x006e }
                org.apache.poi.common.usermodel.fonts.FontHeader$PanoseFamily r1 = org.apache.poi.common.usermodel.fonts.FontHeader.PanoseFamily.TEXT_DISPLAY     // Catch:{ NoSuchFieldError -> 0x006e }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x006e }
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x006e }
            L_0x006e:
                int[] r0 = $SwitchMap$org$apache$poi$common$usermodel$fonts$FontHeader$PanoseFamily     // Catch:{ NoSuchFieldError -> 0x0078 }
                org.apache.poi.common.usermodel.fonts.FontHeader$PanoseFamily r1 = org.apache.poi.common.usermodel.fonts.FontHeader.PanoseFamily.DECORATIVE     // Catch:{ NoSuchFieldError -> 0x0078 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0078 }
                r0[r1] = r3     // Catch:{ NoSuchFieldError -> 0x0078 }
            L_0x0078:
                int[] r0 = $SwitchMap$org$apache$poi$common$usermodel$fonts$FontHeader$PanoseFamily     // Catch:{ NoSuchFieldError -> 0x0082 }
                org.apache.poi.common.usermodel.fonts.FontHeader$PanoseFamily r1 = org.apache.poi.common.usermodel.fonts.FontHeader.PanoseFamily.SCRIPT     // Catch:{ NoSuchFieldError -> 0x0082 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0082 }
                r0[r1] = r4     // Catch:{ NoSuchFieldError -> 0x0082 }
            L_0x0082:
                int[] r0 = $SwitchMap$org$apache$poi$common$usermodel$fonts$FontHeader$PanoseFamily     // Catch:{ NoSuchFieldError -> 0x008c }
                org.apache.poi.common.usermodel.fonts.FontHeader$PanoseFamily r1 = org.apache.poi.common.usermodel.fonts.FontHeader.PanoseFamily.PICTORIAL     // Catch:{ NoSuchFieldError -> 0x008c }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x008c }
                r0[r1] = r5     // Catch:{ NoSuchFieldError -> 0x008c }
            L_0x008c:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.common.usermodel.fonts.FontHeader.AnonymousClass1.<clinit>():void");
        }
    }

    public String getFamilyName() {
        return this.familyName;
    }

    public String getStyleName() {
        return this.styleName;
    }

    public String getVersionName() {
        return this.versionName;
    }

    public String getFullName() {
        return this.fullName;
    }

    public byte[] getPanose() {
        return this.panose;
    }

    public String getTypeface() {
        return getFamilyName();
    }

    public int getFlags() {
        return this.flags;
    }

    public Map<String, Supplier<?>> getGenericProperties() {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("eotSize", new FontHeader$$ExternalSyntheticLambda11(this));
        linkedHashMap.put("fontDataSize", new FontHeader$$ExternalSyntheticLambda3(this));
        linkedHashMap.put("version", new FontHeader$$ExternalSyntheticLambda14(this));
        linkedHashMap.put("flags", GenericRecordUtil.getBitsAsString((Supplier<Number>) new FontHeader$$ExternalSyntheticLambda15(this), FLAGS_MASKS, FLAGS_NAMES));
        linkedHashMap.put("panose.familyType", new FontHeader$$ExternalSyntheticLambda16(this));
        linkedHashMap.put("panose.serifType", new FontHeader$$ExternalSyntheticLambda17(this));
        linkedHashMap.put("panose.weight", new FontHeader$$ExternalSyntheticLambda18(this));
        linkedHashMap.put("panose.proportion", new FontHeader$$ExternalSyntheticLambda19(this));
        linkedHashMap.put("panose.contrast", new FontHeader$$ExternalSyntheticLambda20(this));
        linkedHashMap.put("panose.stroke", new FontHeader$$ExternalSyntheticLambda21(this));
        linkedHashMap.put("panose.armStyle", new FontHeader$$ExternalSyntheticLambda22(this));
        linkedHashMap.put("panose.letterForm", new FontHeader$$ExternalSyntheticLambda32(this));
        linkedHashMap.put("panose.midLine", new FontHeader$$ExternalSyntheticLambda33(this));
        linkedHashMap.put("panose.xHeight", new FontHeader$$ExternalSyntheticLambda34(this));
        linkedHashMap.put("charset", new FontHeader$$ExternalSyntheticLambda35(this));
        linkedHashMap.put("italic", new FontHeader$$ExternalSyntheticLambda36(this));
        linkedHashMap.put("weight", new FontHeader$$ExternalSyntheticLambda37(this));
        linkedHashMap.put("fsType", GenericRecordUtil.getBitsAsString((Supplier<Number>) new FontHeader$$ExternalSyntheticLambda38(this), FSTYPE_MASKS, FSTYPE_NAMES));
        linkedHashMap.put("unicodeRange1", new FontHeader$$ExternalSyntheticLambda1(this));
        linkedHashMap.put("unicodeRange2", new FontHeader$$ExternalSyntheticLambda2(this));
        linkedHashMap.put("unicodeRange3", new FontHeader$$ExternalSyntheticLambda4(this));
        linkedHashMap.put("unicodeRange4", new FontHeader$$ExternalSyntheticLambda5(this));
        linkedHashMap.put("codePageRange1", new FontHeader$$ExternalSyntheticLambda6(this));
        linkedHashMap.put("codePageRange2", new FontHeader$$ExternalSyntheticLambda7(this));
        linkedHashMap.put("checkSumAdjustment", new FontHeader$$ExternalSyntheticLambda8(this));
        linkedHashMap.put("familyName", new FontHeader$$ExternalSyntheticLambda9(this));
        linkedHashMap.put("styleName", new FontHeader$$ExternalSyntheticLambda10(this));
        linkedHashMap.put("versionName", new FontHeader$$ExternalSyntheticLambda12(this));
        linkedHashMap.put("fullName", new FontHeader$$ExternalSyntheticLambda13(this));
        return Collections.unmodifiableMap(linkedHashMap);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$0$org-apache-poi-common-usermodel-fonts-FontHeader  reason: not valid java name */
    public /* synthetic */ Object m1927lambda$getGenericProperties$0$orgapachepoicommonusermodelfontsFontHeader() {
        return Integer.valueOf(this.eotSize);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$1$org-apache-poi-common-usermodel-fonts-FontHeader  reason: not valid java name */
    public /* synthetic */ Object m1928lambda$getGenericProperties$1$orgapachepoicommonusermodelfontsFontHeader() {
        return Integer.valueOf(this.fontDataSize);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$2$org-apache-poi-common-usermodel-fonts-FontHeader  reason: not valid java name */
    public /* synthetic */ Object m1930lambda$getGenericProperties$2$orgapachepoicommonusermodelfontsFontHeader() {
        return Integer.valueOf(this.version);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$3$org-apache-poi-common-usermodel-fonts-FontHeader  reason: not valid java name */
    public /* synthetic */ Number m1931lambda$getGenericProperties$3$orgapachepoicommonusermodelfontsFontHeader() {
        return Integer.valueOf(this.fsType);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$4$org-apache-poi-common-usermodel-fonts-FontHeader  reason: not valid java name */
    public /* synthetic */ Object m1932lambda$getGenericProperties$4$orgapachepoicommonusermodelfontsFontHeader() {
        return Integer.valueOf(this.unicodeRange1);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$5$org-apache-poi-common-usermodel-fonts-FontHeader  reason: not valid java name */
    public /* synthetic */ Object m1933lambda$getGenericProperties$5$orgapachepoicommonusermodelfontsFontHeader() {
        return Integer.valueOf(this.unicodeRange2);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$6$org-apache-poi-common-usermodel-fonts-FontHeader  reason: not valid java name */
    public /* synthetic */ Object m1934lambda$getGenericProperties$6$orgapachepoicommonusermodelfontsFontHeader() {
        return Integer.valueOf(this.unicodeRange3);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$7$org-apache-poi-common-usermodel-fonts-FontHeader  reason: not valid java name */
    public /* synthetic */ Object m1935lambda$getGenericProperties$7$orgapachepoicommonusermodelfontsFontHeader() {
        return Integer.valueOf(this.unicodeRange4);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$8$org-apache-poi-common-usermodel-fonts-FontHeader  reason: not valid java name */
    public /* synthetic */ Object m1936lambda$getGenericProperties$8$orgapachepoicommonusermodelfontsFontHeader() {
        return Integer.valueOf(this.codePageRange1);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$9$org-apache-poi-common-usermodel-fonts-FontHeader  reason: not valid java name */
    public /* synthetic */ Object m1937lambda$getGenericProperties$9$orgapachepoicommonusermodelfontsFontHeader() {
        return Integer.valueOf(this.codePageRange2);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$10$org-apache-poi-common-usermodel-fonts-FontHeader  reason: not valid java name */
    public /* synthetic */ Object m1929lambda$getGenericProperties$10$orgapachepoicommonusermodelfontsFontHeader() {
        return Integer.valueOf(this.checkSumAdjustment);
    }

    public PanoseFamily getPanoseFamily() {
        return (PanoseFamily) GenericRecordUtil.safeEnum(PanoseFamily.values(), new FontHeader$$ExternalSyntheticLambda25(this)).get();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$getPanoseFamily$11$org-apache-poi-common-usermodel-fonts-FontHeader  reason: not valid java name */
    public /* synthetic */ Number m1940lambda$getPanoseFamily$11$orgapachepoicommonusermodelfontsFontHeader() {
        return Byte.valueOf(this.panose[0]);
    }

    public PanoseSerif getPanoseSerif() {
        return (PanoseSerif) GenericRecordUtil.safeEnum(PanoseSerif.values(), new FontHeader$$ExternalSyntheticLambda23(this)).get();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$getPanoseSerif$12$org-apache-poi-common-usermodel-fonts-FontHeader  reason: not valid java name */
    public /* synthetic */ Number m1944lambda$getPanoseSerif$12$orgapachepoicommonusermodelfontsFontHeader() {
        return Byte.valueOf(this.panose[1]);
    }

    public PanoseWeight getPanoseWeight() {
        return (PanoseWeight) GenericRecordUtil.safeEnum(PanoseWeight.values(), new FontHeader$$ExternalSyntheticLambda27(this)).get();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$getPanoseWeight$13$org-apache-poi-common-usermodel-fonts-FontHeader  reason: not valid java name */
    public /* synthetic */ Number m1946lambda$getPanoseWeight$13$orgapachepoicommonusermodelfontsFontHeader() {
        return Byte.valueOf(this.panose[2]);
    }

    public PanoseProportion getPanoseProportion() {
        return (PanoseProportion) GenericRecordUtil.safeEnum(PanoseProportion.values(), new FontHeader$$ExternalSyntheticLambda29(this)).get();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$getPanoseProportion$14$org-apache-poi-common-usermodel-fonts-FontHeader  reason: not valid java name */
    public /* synthetic */ Number m1943lambda$getPanoseProportion$14$orgapachepoicommonusermodelfontsFontHeader() {
        return Byte.valueOf(this.panose[3]);
    }

    public PanoseContrast getPanoseContrast() {
        return (PanoseContrast) GenericRecordUtil.safeEnum(PanoseContrast.values(), new FontHeader$$ExternalSyntheticLambda30(this)).get();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$getPanoseContrast$15$org-apache-poi-common-usermodel-fonts-FontHeader  reason: not valid java name */
    public /* synthetic */ Number m1939lambda$getPanoseContrast$15$orgapachepoicommonusermodelfontsFontHeader() {
        return Byte.valueOf(this.panose[4]);
    }

    public PanoseStroke getPanoseStroke() {
        return (PanoseStroke) GenericRecordUtil.safeEnum(PanoseStroke.values(), new FontHeader$$ExternalSyntheticLambda0(this)).get();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$getPanoseStroke$16$org-apache-poi-common-usermodel-fonts-FontHeader  reason: not valid java name */
    public /* synthetic */ Number m1945lambda$getPanoseStroke$16$orgapachepoicommonusermodelfontsFontHeader() {
        return Byte.valueOf(this.panose[5]);
    }

    public PanoseArmStyle getPanoseArmStyle() {
        return (PanoseArmStyle) GenericRecordUtil.safeEnum(PanoseArmStyle.values(), new FontHeader$$ExternalSyntheticLambda31(this)).get();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$getPanoseArmStyle$17$org-apache-poi-common-usermodel-fonts-FontHeader  reason: not valid java name */
    public /* synthetic */ Number m1938lambda$getPanoseArmStyle$17$orgapachepoicommonusermodelfontsFontHeader() {
        return Byte.valueOf(this.panose[6]);
    }

    public PanoseLetterForm getPanoseLetterForm() {
        return (PanoseLetterForm) GenericRecordUtil.safeEnum(PanoseLetterForm.values(), new FontHeader$$ExternalSyntheticLambda24(this)).get();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$getPanoseLetterForm$18$org-apache-poi-common-usermodel-fonts-FontHeader  reason: not valid java name */
    public /* synthetic */ Number m1941lambda$getPanoseLetterForm$18$orgapachepoicommonusermodelfontsFontHeader() {
        return Byte.valueOf(this.panose[7]);
    }

    public PanoseMidLine getPanoseMidLine() {
        return (PanoseMidLine) GenericRecordUtil.safeEnum(PanoseMidLine.values(), new FontHeader$$ExternalSyntheticLambda28(this)).get();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$getPanoseMidLine$19$org-apache-poi-common-usermodel-fonts-FontHeader  reason: not valid java name */
    public /* synthetic */ Number m1942lambda$getPanoseMidLine$19$orgapachepoicommonusermodelfontsFontHeader() {
        return Byte.valueOf(this.panose[8]);
    }

    public PanoseXHeight getPanoseXHeight() {
        return (PanoseXHeight) GenericRecordUtil.safeEnum(PanoseXHeight.values(), new FontHeader$$ExternalSyntheticLambda26(this)).get();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$getPanoseXHeight$20$org-apache-poi-common-usermodel-fonts-FontHeader  reason: not valid java name */
    public /* synthetic */ Number m1947lambda$getPanoseXHeight$20$orgapachepoicommonusermodelfontsFontHeader() {
        return Byte.valueOf(this.panose[9]);
    }
}
