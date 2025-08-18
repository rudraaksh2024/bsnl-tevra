package org.apache.poi.sl.usermodel;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.exifinterface.media.ExifInterface;
import java.util.Locale;
import org.apache.logging.log4j.util.ProcessIdUtil;
import org.apache.poi.common.usermodel.fonts.FontHeader;

public enum AutoNumberingScheme {
    alphaLcParenBoth(8, 1),
    alphaUcParenBoth(10, 2),
    alphaLcParenRight(9, 3),
    alphaUcParenRight(11, 4),
    alphaLcPeriod(0, 5),
    alphaUcPeriod(1, 6),
    arabicParenBoth(12, 7),
    arabicParenRight(2, 8),
    arabicPeriod(3, 9),
    arabicPlain(13, 10),
    romanLcParenBoth(4, 11),
    romanUcParenBoth(14, 12),
    romanLcParenRight(5, 13),
    romanUcParenRight(15, 14),
    romanLcPeriod(6, 15),
    romanUcPeriod(7, 16),
    circleNumDbPlain(18, 17),
    circleNumWdBlackPlain(20, 18),
    circleNumWdWhitePlain(19, 19),
    arabicDbPeriod(29, 20),
    arabicDbPlain(28, 21),
    ea1ChsPeriod(17, 22),
    ea1ChsPlain(16, 23),
    ea1ChtPeriod(21, 24),
    ea1ChtPlain(20, 25),
    ea1JpnChsDbPeriod(38, 26),
    ea1JpnKorPlain(26, 27),
    ea1JpnKorPeriod(27, 28),
    arabic1Minus(23, 29),
    arabic2Minus(24, 30),
    hebrew2Minus(25, 31),
    thaiAlphaPeriod(30, 32),
    thaiAlphaParenRight(31, 33),
    thaiAlphaParenBoth(32, 34),
    thaiNumPeriod(33, 35),
    thaiNumParenRight(34, 36),
    thaiNumParenBoth(35, 37),
    hindiAlphaPeriod(36, 38),
    hindiNumPeriod(37, 39),
    hindiNumParenRight(39, 40),
    hindiAlpha1Period(39, 41);
    
    private static final String ALPHA_LIST = "abcdefghijklmnopqrstuvwxyz";
    private static final String ARABIC_LIST = "0123456789";
    private static final String CIRCLE_DB_LIST = "❶❷❸❹❺❻❼❽❾";
    private static final String WINGDINGS_BLACK_LIST = "";
    private static final String WINGDINGS_WHITE_LIST = "";
    public final int nativeId;
    public final int ooxmlId;

    private AutoNumberingScheme(int i, int i2) {
        this.nativeId = i;
        this.ooxmlId = i2;
    }

    public static AutoNumberingScheme forNativeID(int i) {
        for (AutoNumberingScheme autoNumberingScheme : values()) {
            if (autoNumberingScheme.nativeId == i) {
                return autoNumberingScheme;
            }
        }
        return null;
    }

    public static AutoNumberingScheme forOoxmlID(int i) {
        for (AutoNumberingScheme autoNumberingScheme : values()) {
            if (autoNumberingScheme.ooxmlId == i) {
                return autoNumberingScheme;
            }
        }
        return null;
    }

    /* renamed from: org.apache.poi.sl.usermodel.AutoNumberingScheme$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$org$apache$poi$sl$usermodel$AutoNumberingScheme = null;

        /* JADX WARNING: Can't wrap try/catch for region: R(84:0|1|2|3|4|5|6|7|8|9|10|11|12|13|14|15|16|17|18|19|20|21|22|23|24|25|26|27|28|29|30|31|32|33|34|35|36|37|38|39|40|41|42|43|44|45|46|47|48|49|50|51|52|53|54|55|56|57|58|59|60|61|62|63|64|65|66|67|68|69|70|71|72|73|74|75|76|77|78|79|80|81|82|84) */
        /* JADX WARNING: Code restructure failed: missing block: B:85:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x003e */
        /* JADX WARNING: Missing exception handler attribute for start block: B:13:0x0049 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:15:0x0054 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:17:0x0060 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:19:0x006c */
        /* JADX WARNING: Missing exception handler attribute for start block: B:21:0x0078 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:23:0x0084 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:25:0x0090 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:27:0x009c */
        /* JADX WARNING: Missing exception handler attribute for start block: B:29:0x00a8 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:31:0x00b4 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:33:0x00c0 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:35:0x00cc */
        /* JADX WARNING: Missing exception handler attribute for start block: B:37:0x00d8 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:39:0x00e4 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:41:0x00f0 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:43:0x00fc */
        /* JADX WARNING: Missing exception handler attribute for start block: B:45:0x0108 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:47:0x0114 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:49:0x0120 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:51:0x012c */
        /* JADX WARNING: Missing exception handler attribute for start block: B:53:0x0138 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:55:0x0144 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:57:0x0150 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:59:0x015c */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:61:0x0168 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:63:0x0174 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:65:0x0180 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:67:0x018c */
        /* JADX WARNING: Missing exception handler attribute for start block: B:69:0x0198 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:71:0x01a4 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:73:0x01b0 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:75:0x01bc */
        /* JADX WARNING: Missing exception handler attribute for start block: B:77:0x01c8 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:79:0x01d4 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:81:0x01e0 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0033 */
        static {
            /*
                org.apache.poi.sl.usermodel.AutoNumberingScheme[] r0 = org.apache.poi.sl.usermodel.AutoNumberingScheme.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$org$apache$poi$sl$usermodel$AutoNumberingScheme = r0
                org.apache.poi.sl.usermodel.AutoNumberingScheme r1 = org.apache.poi.sl.usermodel.AutoNumberingScheme.alphaLcPeriod     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$org$apache$poi$sl$usermodel$AutoNumberingScheme     // Catch:{ NoSuchFieldError -> 0x001d }
                org.apache.poi.sl.usermodel.AutoNumberingScheme r1 = org.apache.poi.sl.usermodel.AutoNumberingScheme.alphaUcPeriod     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$org$apache$poi$sl$usermodel$AutoNumberingScheme     // Catch:{ NoSuchFieldError -> 0x0028 }
                org.apache.poi.sl.usermodel.AutoNumberingScheme r1 = org.apache.poi.sl.usermodel.AutoNumberingScheme.arabicParenRight     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = $SwitchMap$org$apache$poi$sl$usermodel$AutoNumberingScheme     // Catch:{ NoSuchFieldError -> 0x0033 }
                org.apache.poi.sl.usermodel.AutoNumberingScheme r1 = org.apache.poi.sl.usermodel.AutoNumberingScheme.arabicPeriod     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                int[] r0 = $SwitchMap$org$apache$poi$sl$usermodel$AutoNumberingScheme     // Catch:{ NoSuchFieldError -> 0x003e }
                org.apache.poi.sl.usermodel.AutoNumberingScheme r1 = org.apache.poi.sl.usermodel.AutoNumberingScheme.romanLcParenBoth     // Catch:{ NoSuchFieldError -> 0x003e }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x003e }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x003e }
            L_0x003e:
                int[] r0 = $SwitchMap$org$apache$poi$sl$usermodel$AutoNumberingScheme     // Catch:{ NoSuchFieldError -> 0x0049 }
                org.apache.poi.sl.usermodel.AutoNumberingScheme r1 = org.apache.poi.sl.usermodel.AutoNumberingScheme.romanLcParenRight     // Catch:{ NoSuchFieldError -> 0x0049 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0049 }
                r2 = 6
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0049 }
            L_0x0049:
                int[] r0 = $SwitchMap$org$apache$poi$sl$usermodel$AutoNumberingScheme     // Catch:{ NoSuchFieldError -> 0x0054 }
                org.apache.poi.sl.usermodel.AutoNumberingScheme r1 = org.apache.poi.sl.usermodel.AutoNumberingScheme.romanLcPeriod     // Catch:{ NoSuchFieldError -> 0x0054 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0054 }
                r2 = 7
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0054 }
            L_0x0054:
                int[] r0 = $SwitchMap$org$apache$poi$sl$usermodel$AutoNumberingScheme     // Catch:{ NoSuchFieldError -> 0x0060 }
                org.apache.poi.sl.usermodel.AutoNumberingScheme r1 = org.apache.poi.sl.usermodel.AutoNumberingScheme.romanUcPeriod     // Catch:{ NoSuchFieldError -> 0x0060 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0060 }
                r2 = 8
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0060 }
            L_0x0060:
                int[] r0 = $SwitchMap$org$apache$poi$sl$usermodel$AutoNumberingScheme     // Catch:{ NoSuchFieldError -> 0x006c }
                org.apache.poi.sl.usermodel.AutoNumberingScheme r1 = org.apache.poi.sl.usermodel.AutoNumberingScheme.alphaLcParenBoth     // Catch:{ NoSuchFieldError -> 0x006c }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x006c }
                r2 = 9
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x006c }
            L_0x006c:
                int[] r0 = $SwitchMap$org$apache$poi$sl$usermodel$AutoNumberingScheme     // Catch:{ NoSuchFieldError -> 0x0078 }
                org.apache.poi.sl.usermodel.AutoNumberingScheme r1 = org.apache.poi.sl.usermodel.AutoNumberingScheme.alphaLcParenRight     // Catch:{ NoSuchFieldError -> 0x0078 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0078 }
                r2 = 10
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0078 }
            L_0x0078:
                int[] r0 = $SwitchMap$org$apache$poi$sl$usermodel$AutoNumberingScheme     // Catch:{ NoSuchFieldError -> 0x0084 }
                org.apache.poi.sl.usermodel.AutoNumberingScheme r1 = org.apache.poi.sl.usermodel.AutoNumberingScheme.alphaUcParenBoth     // Catch:{ NoSuchFieldError -> 0x0084 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0084 }
                r2 = 11
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0084 }
            L_0x0084:
                int[] r0 = $SwitchMap$org$apache$poi$sl$usermodel$AutoNumberingScheme     // Catch:{ NoSuchFieldError -> 0x0090 }
                org.apache.poi.sl.usermodel.AutoNumberingScheme r1 = org.apache.poi.sl.usermodel.AutoNumberingScheme.alphaUcParenRight     // Catch:{ NoSuchFieldError -> 0x0090 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0090 }
                r2 = 12
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0090 }
            L_0x0090:
                int[] r0 = $SwitchMap$org$apache$poi$sl$usermodel$AutoNumberingScheme     // Catch:{ NoSuchFieldError -> 0x009c }
                org.apache.poi.sl.usermodel.AutoNumberingScheme r1 = org.apache.poi.sl.usermodel.AutoNumberingScheme.arabicParenBoth     // Catch:{ NoSuchFieldError -> 0x009c }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x009c }
                r2 = 13
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x009c }
            L_0x009c:
                int[] r0 = $SwitchMap$org$apache$poi$sl$usermodel$AutoNumberingScheme     // Catch:{ NoSuchFieldError -> 0x00a8 }
                org.apache.poi.sl.usermodel.AutoNumberingScheme r1 = org.apache.poi.sl.usermodel.AutoNumberingScheme.arabicPlain     // Catch:{ NoSuchFieldError -> 0x00a8 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x00a8 }
                r2 = 14
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x00a8 }
            L_0x00a8:
                int[] r0 = $SwitchMap$org$apache$poi$sl$usermodel$AutoNumberingScheme     // Catch:{ NoSuchFieldError -> 0x00b4 }
                org.apache.poi.sl.usermodel.AutoNumberingScheme r1 = org.apache.poi.sl.usermodel.AutoNumberingScheme.romanUcParenBoth     // Catch:{ NoSuchFieldError -> 0x00b4 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x00b4 }
                r2 = 15
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x00b4 }
            L_0x00b4:
                int[] r0 = $SwitchMap$org$apache$poi$sl$usermodel$AutoNumberingScheme     // Catch:{ NoSuchFieldError -> 0x00c0 }
                org.apache.poi.sl.usermodel.AutoNumberingScheme r1 = org.apache.poi.sl.usermodel.AutoNumberingScheme.romanUcParenRight     // Catch:{ NoSuchFieldError -> 0x00c0 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x00c0 }
                r2 = 16
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x00c0 }
            L_0x00c0:
                int[] r0 = $SwitchMap$org$apache$poi$sl$usermodel$AutoNumberingScheme     // Catch:{ NoSuchFieldError -> 0x00cc }
                org.apache.poi.sl.usermodel.AutoNumberingScheme r1 = org.apache.poi.sl.usermodel.AutoNumberingScheme.ea1ChsPlain     // Catch:{ NoSuchFieldError -> 0x00cc }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x00cc }
                r2 = 17
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x00cc }
            L_0x00cc:
                int[] r0 = $SwitchMap$org$apache$poi$sl$usermodel$AutoNumberingScheme     // Catch:{ NoSuchFieldError -> 0x00d8 }
                org.apache.poi.sl.usermodel.AutoNumberingScheme r1 = org.apache.poi.sl.usermodel.AutoNumberingScheme.ea1ChsPeriod     // Catch:{ NoSuchFieldError -> 0x00d8 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x00d8 }
                r2 = 18
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x00d8 }
            L_0x00d8:
                int[] r0 = $SwitchMap$org$apache$poi$sl$usermodel$AutoNumberingScheme     // Catch:{ NoSuchFieldError -> 0x00e4 }
                org.apache.poi.sl.usermodel.AutoNumberingScheme r1 = org.apache.poi.sl.usermodel.AutoNumberingScheme.circleNumDbPlain     // Catch:{ NoSuchFieldError -> 0x00e4 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x00e4 }
                r2 = 19
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x00e4 }
            L_0x00e4:
                int[] r0 = $SwitchMap$org$apache$poi$sl$usermodel$AutoNumberingScheme     // Catch:{ NoSuchFieldError -> 0x00f0 }
                org.apache.poi.sl.usermodel.AutoNumberingScheme r1 = org.apache.poi.sl.usermodel.AutoNumberingScheme.circleNumWdWhitePlain     // Catch:{ NoSuchFieldError -> 0x00f0 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x00f0 }
                r2 = 20
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x00f0 }
            L_0x00f0:
                int[] r0 = $SwitchMap$org$apache$poi$sl$usermodel$AutoNumberingScheme     // Catch:{ NoSuchFieldError -> 0x00fc }
                org.apache.poi.sl.usermodel.AutoNumberingScheme r1 = org.apache.poi.sl.usermodel.AutoNumberingScheme.circleNumWdBlackPlain     // Catch:{ NoSuchFieldError -> 0x00fc }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x00fc }
                r2 = 21
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x00fc }
            L_0x00fc:
                int[] r0 = $SwitchMap$org$apache$poi$sl$usermodel$AutoNumberingScheme     // Catch:{ NoSuchFieldError -> 0x0108 }
                org.apache.poi.sl.usermodel.AutoNumberingScheme r1 = org.apache.poi.sl.usermodel.AutoNumberingScheme.ea1ChtPlain     // Catch:{ NoSuchFieldError -> 0x0108 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0108 }
                r2 = 22
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0108 }
            L_0x0108:
                int[] r0 = $SwitchMap$org$apache$poi$sl$usermodel$AutoNumberingScheme     // Catch:{ NoSuchFieldError -> 0x0114 }
                org.apache.poi.sl.usermodel.AutoNumberingScheme r1 = org.apache.poi.sl.usermodel.AutoNumberingScheme.ea1ChtPeriod     // Catch:{ NoSuchFieldError -> 0x0114 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0114 }
                r2 = 23
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0114 }
            L_0x0114:
                int[] r0 = $SwitchMap$org$apache$poi$sl$usermodel$AutoNumberingScheme     // Catch:{ NoSuchFieldError -> 0x0120 }
                org.apache.poi.sl.usermodel.AutoNumberingScheme r1 = org.apache.poi.sl.usermodel.AutoNumberingScheme.arabic1Minus     // Catch:{ NoSuchFieldError -> 0x0120 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0120 }
                r2 = 24
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0120 }
            L_0x0120:
                int[] r0 = $SwitchMap$org$apache$poi$sl$usermodel$AutoNumberingScheme     // Catch:{ NoSuchFieldError -> 0x012c }
                org.apache.poi.sl.usermodel.AutoNumberingScheme r1 = org.apache.poi.sl.usermodel.AutoNumberingScheme.arabic2Minus     // Catch:{ NoSuchFieldError -> 0x012c }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x012c }
                r2 = 25
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x012c }
            L_0x012c:
                int[] r0 = $SwitchMap$org$apache$poi$sl$usermodel$AutoNumberingScheme     // Catch:{ NoSuchFieldError -> 0x0138 }
                org.apache.poi.sl.usermodel.AutoNumberingScheme r1 = org.apache.poi.sl.usermodel.AutoNumberingScheme.hebrew2Minus     // Catch:{ NoSuchFieldError -> 0x0138 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0138 }
                r2 = 26
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0138 }
            L_0x0138:
                int[] r0 = $SwitchMap$org$apache$poi$sl$usermodel$AutoNumberingScheme     // Catch:{ NoSuchFieldError -> 0x0144 }
                org.apache.poi.sl.usermodel.AutoNumberingScheme r1 = org.apache.poi.sl.usermodel.AutoNumberingScheme.ea1JpnKorPlain     // Catch:{ NoSuchFieldError -> 0x0144 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0144 }
                r2 = 27
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0144 }
            L_0x0144:
                int[] r0 = $SwitchMap$org$apache$poi$sl$usermodel$AutoNumberingScheme     // Catch:{ NoSuchFieldError -> 0x0150 }
                org.apache.poi.sl.usermodel.AutoNumberingScheme r1 = org.apache.poi.sl.usermodel.AutoNumberingScheme.ea1JpnKorPeriod     // Catch:{ NoSuchFieldError -> 0x0150 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0150 }
                r2 = 28
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0150 }
            L_0x0150:
                int[] r0 = $SwitchMap$org$apache$poi$sl$usermodel$AutoNumberingScheme     // Catch:{ NoSuchFieldError -> 0x015c }
                org.apache.poi.sl.usermodel.AutoNumberingScheme r1 = org.apache.poi.sl.usermodel.AutoNumberingScheme.arabicDbPlain     // Catch:{ NoSuchFieldError -> 0x015c }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x015c }
                r2 = 29
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x015c }
            L_0x015c:
                int[] r0 = $SwitchMap$org$apache$poi$sl$usermodel$AutoNumberingScheme     // Catch:{ NoSuchFieldError -> 0x0168 }
                org.apache.poi.sl.usermodel.AutoNumberingScheme r1 = org.apache.poi.sl.usermodel.AutoNumberingScheme.arabicDbPeriod     // Catch:{ NoSuchFieldError -> 0x0168 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0168 }
                r2 = 30
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0168 }
            L_0x0168:
                int[] r0 = $SwitchMap$org$apache$poi$sl$usermodel$AutoNumberingScheme     // Catch:{ NoSuchFieldError -> 0x0174 }
                org.apache.poi.sl.usermodel.AutoNumberingScheme r1 = org.apache.poi.sl.usermodel.AutoNumberingScheme.thaiAlphaPeriod     // Catch:{ NoSuchFieldError -> 0x0174 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0174 }
                r2 = 31
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0174 }
            L_0x0174:
                int[] r0 = $SwitchMap$org$apache$poi$sl$usermodel$AutoNumberingScheme     // Catch:{ NoSuchFieldError -> 0x0180 }
                org.apache.poi.sl.usermodel.AutoNumberingScheme r1 = org.apache.poi.sl.usermodel.AutoNumberingScheme.thaiAlphaParenRight     // Catch:{ NoSuchFieldError -> 0x0180 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0180 }
                r2 = 32
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0180 }
            L_0x0180:
                int[] r0 = $SwitchMap$org$apache$poi$sl$usermodel$AutoNumberingScheme     // Catch:{ NoSuchFieldError -> 0x018c }
                org.apache.poi.sl.usermodel.AutoNumberingScheme r1 = org.apache.poi.sl.usermodel.AutoNumberingScheme.thaiAlphaParenBoth     // Catch:{ NoSuchFieldError -> 0x018c }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x018c }
                r2 = 33
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x018c }
            L_0x018c:
                int[] r0 = $SwitchMap$org$apache$poi$sl$usermodel$AutoNumberingScheme     // Catch:{ NoSuchFieldError -> 0x0198 }
                org.apache.poi.sl.usermodel.AutoNumberingScheme r1 = org.apache.poi.sl.usermodel.AutoNumberingScheme.thaiNumPeriod     // Catch:{ NoSuchFieldError -> 0x0198 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0198 }
                r2 = 34
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0198 }
            L_0x0198:
                int[] r0 = $SwitchMap$org$apache$poi$sl$usermodel$AutoNumberingScheme     // Catch:{ NoSuchFieldError -> 0x01a4 }
                org.apache.poi.sl.usermodel.AutoNumberingScheme r1 = org.apache.poi.sl.usermodel.AutoNumberingScheme.thaiNumParenRight     // Catch:{ NoSuchFieldError -> 0x01a4 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x01a4 }
                r2 = 35
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x01a4 }
            L_0x01a4:
                int[] r0 = $SwitchMap$org$apache$poi$sl$usermodel$AutoNumberingScheme     // Catch:{ NoSuchFieldError -> 0x01b0 }
                org.apache.poi.sl.usermodel.AutoNumberingScheme r1 = org.apache.poi.sl.usermodel.AutoNumberingScheme.thaiNumParenBoth     // Catch:{ NoSuchFieldError -> 0x01b0 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x01b0 }
                r2 = 36
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x01b0 }
            L_0x01b0:
                int[] r0 = $SwitchMap$org$apache$poi$sl$usermodel$AutoNumberingScheme     // Catch:{ NoSuchFieldError -> 0x01bc }
                org.apache.poi.sl.usermodel.AutoNumberingScheme r1 = org.apache.poi.sl.usermodel.AutoNumberingScheme.hindiAlphaPeriod     // Catch:{ NoSuchFieldError -> 0x01bc }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x01bc }
                r2 = 37
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x01bc }
            L_0x01bc:
                int[] r0 = $SwitchMap$org$apache$poi$sl$usermodel$AutoNumberingScheme     // Catch:{ NoSuchFieldError -> 0x01c8 }
                org.apache.poi.sl.usermodel.AutoNumberingScheme r1 = org.apache.poi.sl.usermodel.AutoNumberingScheme.hindiNumPeriod     // Catch:{ NoSuchFieldError -> 0x01c8 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x01c8 }
                r2 = 38
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x01c8 }
            L_0x01c8:
                int[] r0 = $SwitchMap$org$apache$poi$sl$usermodel$AutoNumberingScheme     // Catch:{ NoSuchFieldError -> 0x01d4 }
                org.apache.poi.sl.usermodel.AutoNumberingScheme r1 = org.apache.poi.sl.usermodel.AutoNumberingScheme.ea1JpnChsDbPeriod     // Catch:{ NoSuchFieldError -> 0x01d4 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x01d4 }
                r2 = 39
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x01d4 }
            L_0x01d4:
                int[] r0 = $SwitchMap$org$apache$poi$sl$usermodel$AutoNumberingScheme     // Catch:{ NoSuchFieldError -> 0x01e0 }
                org.apache.poi.sl.usermodel.AutoNumberingScheme r1 = org.apache.poi.sl.usermodel.AutoNumberingScheme.hindiNumParenRight     // Catch:{ NoSuchFieldError -> 0x01e0 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x01e0 }
                r2 = 40
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x01e0 }
            L_0x01e0:
                int[] r0 = $SwitchMap$org$apache$poi$sl$usermodel$AutoNumberingScheme     // Catch:{ NoSuchFieldError -> 0x01ec }
                org.apache.poi.sl.usermodel.AutoNumberingScheme r1 = org.apache.poi.sl.usermodel.AutoNumberingScheme.hindiAlpha1Period     // Catch:{ NoSuchFieldError -> 0x01ec }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x01ec }
                r2 = 41
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x01ec }
            L_0x01ec:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.sl.usermodel.AutoNumberingScheme.AnonymousClass1.<clinit>():void");
        }
    }

    public String getDescription() {
        switch (AnonymousClass1.$SwitchMap$org$apache$poi$sl$usermodel$AutoNumberingScheme[ordinal()]) {
            case 1:
                return "Lowercase Latin character followed by a period. Example: a., b., c., ...";
            case 2:
                return "Uppercase Latin character followed by a period. Example: A., B., C., ...";
            case 3:
                return "Arabic numeral followed by a closing parenthesis. Example: 1), 2), 3), ...";
            case 4:
                return "Arabic numeral followed by a period. Example: 1., 2., 3., ...";
            case 5:
                return "Lowercase Roman numeral enclosed in parentheses. Example: (i), (ii), (iii), ...";
            case 6:
                return "Lowercase Roman numeral followed by a closing parenthesis. Example: i), ii), iii), ...";
            case 7:
                return "Lowercase Roman numeral followed by a period. Example: i., ii., iii., ...";
            case 8:
                return "Uppercase Roman numeral followed by a period. Example: I., II., III., ...";
            case 9:
                return "Lowercase alphabetic character enclosed in parentheses. Example: (a), (b), (c), ...";
            case 10:
                return "Lowercase alphabetic character followed by a closing parenthesis. Example: a), b), c), ...";
            case 11:
                return "Uppercase alphabetic character enclosed in parentheses. Example: (A), (B), (C), ...";
            case 12:
                return "Uppercase alphabetic character followed by a closing parenthesis. Example: A), B), C), ...";
            case 13:
                return "Arabic numeral enclosed in parentheses. Example: (1), (2), (3), ...";
            case 14:
                return "Arabic numeral. Example: 1, 2, 3, ...";
            case 15:
                return "Uppercase Roman numeral enclosed in parentheses. Example: (I), (II), (III), ...";
            case 16:
                return "Uppercase Roman numeral followed by a closing parenthesis. Example: I), II), III), ...";
            case 17:
                return "Simplified Chinese.";
            case 18:
                return "Simplified Chinese with single-byte period.";
            case 19:
                return "Double byte circle numbers.";
            case 20:
                return "Wingdings white circle numbers.";
            case 21:
                return "Wingdings black circle numbers.";
            case 22:
                return "Traditional Chinese.";
            case 23:
                return "Traditional Chinese with single-byte period.";
            case 24:
                return "Bidi Arabic 1 (AraAlpha) with ANSI minus symbol.";
            case 25:
                return "Bidi Arabic 2 (AraAbjad) with ANSI minus symbol.";
            case 26:
                return "Bidi Hebrew 2 with ANSI minus symbol.";
            case 27:
                return "Japanese/Korean.";
            case 28:
                return "Japanese/Korean with single-byte period.";
            case 29:
                return "Double-byte Arabic numbers.";
            case 30:
                return "Double-byte Arabic numbers with double-byte period.";
            case 31:
                return "Thai alphabetic character followed by a period.";
            case 32:
                return "Thai alphabetic character followed by a closing parenthesis.";
            case 33:
                return "Thai alphabetic character enclosed by parentheses.";
            case 34:
                return "Thai numeral followed by a period.";
            case 35:
                return "Thai numeral followed by a closing parenthesis.";
            case 36:
                return "Thai numeral enclosed in parentheses.";
            case 37:
                return "Hindi alphabetic character followed by a period.";
            case 38:
                return "Hindi numeric character followed by a period.";
            case 39:
                return "Japanese with double-byte period.";
            case 40:
                return "Hindi numeric character followed by a closing parenthesis.";
            case 41:
                return "Hindi alphabetic character followed by a period.";
            default:
                return "Unknown Numbered Scheme";
        }
    }

    public String format(int i) {
        return formatSeperator(formatCase(formatIndex(i)));
    }

    private String formatSeperator(String str) {
        String lowerCase = name().toLowerCase(Locale.ROOT);
        if (lowerCase.contains("plain")) {
            return str;
        }
        if (lowerCase.contains("parenright")) {
            return str + ")";
        }
        if (lowerCase.contains("parenboth")) {
            return "(" + str + ")";
        }
        if (lowerCase.contains(TypedValues.CycleType.S_WAVE_PERIOD)) {
            return str + ".";
        }
        return lowerCase.contains("minus") ? str + ProcessIdUtil.DEFAULT_PROCESSID : str;
    }

    private String formatCase(String str) {
        String lowerCase = name().toLowerCase(Locale.ROOT);
        if (lowerCase.contains("lc")) {
            return str.toLowerCase(Locale.ROOT);
        }
        return lowerCase.contains("uc") ? str.toUpperCase(Locale.ROOT) : str;
    }

    private String formatIndex(int i) {
        String lowerCase = name().toLowerCase(Locale.ROOT);
        if (lowerCase.startsWith("roman")) {
            return formatRomanIndex(i);
        }
        if (lowerCase.startsWith("arabic") && !lowerCase.contains("db")) {
            return getIndexedList(i, ARABIC_LIST, false);
        }
        if (lowerCase.startsWith("alpha")) {
            return getIndexedList(i, ALPHA_LIST, true);
        }
        if (lowerCase.contains("WdWhite")) {
            if (i == 10) {
                return "";
            }
            return getIndexedList(i, WINGDINGS_WHITE_LIST, false);
        } else if (lowerCase.contains("WdBlack")) {
            if (i == 10) {
                return "";
            }
            return getIndexedList(i, WINGDINGS_BLACK_LIST, false);
        } else if (!lowerCase.contains("NumDb")) {
            return "?";
        } else {
            if (i == 10) {
                return "❿";
            }
            return getIndexedList(i, CIRCLE_DB_LIST, true);
        }
    }

    private static String getIndexedList(int i, String str, boolean z) {
        StringBuilder sb = new StringBuilder();
        addIndexedChar(i, str, z, sb);
        return sb.toString();
    }

    private static void addIndexedChar(int i, String str, boolean z, StringBuilder sb) {
        if (z) {
            i--;
        }
        int length = str.length();
        if (i >= length) {
            addIndexedChar(i / length, str, z, sb);
        }
        sb.append(str.charAt(i % length));
    }

    private String formatRomanIndex(int i) {
        int[] iArr = {1000, TypedValues.Custom.TYPE_INT, 500, FontHeader.REGULAR_WEIGHT, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] strArr = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", ExifInterface.GPS_MEASUREMENT_INTERRUPTED, "IV", "I"};
        String[][] strArr2 = {new String[]{"XLV", "VL"}, new String[]{"XCV", "VC"}, new String[]{"CDL", "LD"}, new String[]{"CML", "LM"}, new String[]{"CMVC", "LMVL"}, new String[]{"CDXC", "LDXL"}, new String[]{"CDVC", "LDVL"}, new String[]{"CMXC", "LMXL"}, new String[]{"XCIX", "VCIV"}, new String[]{"XLIX", "VLIV"}, new String[]{"XLIX", "IL"}, new String[]{"XCIX", "IC"}, new String[]{"CDXC", "XD"}, new String[]{"CDVC", "XDV"}, new String[]{"CDIC", "XDIX"}, new String[]{"LMVL", "XMV"}, new String[]{"CMIC", "XMIX"}, new String[]{"CMXC", "XM"}, new String[]{"XDV", "VD"}, new String[]{"XDIX", "VDIV"}, new String[]{"XMV", "VM"}, new String[]{"XMIX", "VMIV"}, new String[]{"VDIV", "ID"}, new String[]{"VMIV", "IM"}};
        StringBuilder sb = new StringBuilder();
        for (int i2 = 0; i2 < 13; i2++) {
            while (true) {
                int i3 = iArr[i2];
                if (i < i3) {
                    break;
                }
                i -= i3;
                sb.append(strArr[i2]);
            }
        }
        String sb2 = sb.toString();
        for (int i4 = 0; i4 < 24; i4++) {
            String[] strArr3 = strArr2[i4];
            sb2 = sb2.replace(strArr3[0], strArr3[1]);
        }
        return sb2;
    }
}
