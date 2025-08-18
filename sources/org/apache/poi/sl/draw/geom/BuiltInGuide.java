package org.apache.poi.sl.draw.geom;

import java.awt.geom.Rectangle2D;

enum BuiltInGuide implements Formula {
    _3cd4,
    _3cd8,
    _5cd8,
    _7cd8,
    _b,
    _cd2,
    _cd4,
    _cd8,
    _hc,
    _h,
    _hd2,
    _hd3,
    _hd4,
    _hd5,
    _hd6,
    _hd8,
    _l,
    _ls,
    _r,
    _ss,
    _ssd2,
    _ssd4,
    _ssd6,
    _ssd8,
    _ssd16,
    _ssd32,
    _t,
    _vc,
    _w,
    _wd2,
    _wd3,
    _wd4,
    _wd5,
    _wd6,
    _wd8,
    _wd10,
    _wd32;

    public String getName() {
        return name().substring(1);
    }

    public double evaluate(Context context) {
        Rectangle2D shapeAnchor = context.getShapeAnchor();
        double height = shapeAnchor.getHeight();
        double width = shapeAnchor.getWidth();
        double min = Math.min(width, height);
        switch (AnonymousClass1.$SwitchMap$org$apache$poi$sl$draw$geom$BuiltInGuide[ordinal()]) {
            case 1:
                return 1.62E7d;
            case 2:
                return 8100000.0d;
            case 3:
                return 1.35E7d;
            case 4:
                return 1.89E7d;
            case 5:
                return shapeAnchor.getY();
            case 6:
                return shapeAnchor.getMaxY();
            case 7:
                return shapeAnchor.getX();
            case 8:
                return shapeAnchor.getMaxX();
            case 9:
                return 1.08E7d;
            case 10:
                return 5400000.0d;
            case 11:
                return 2700000.0d;
            case 12:
                return shapeAnchor.getCenterX();
            case 13:
                return height;
            case 14:
                return height / 2.0d;
            case 15:
                return height / 3.0d;
            case 16:
                return height / 4.0d;
            case 17:
                return height / 5.0d;
            case 18:
                return height / 6.0d;
            case 19:
                return height / 8.0d;
            case 20:
                return Math.max(width, height);
            case 21:
                return min;
            case 22:
                return min / 2.0d;
            case 23:
                return min / 4.0d;
            case 24:
                return min / 6.0d;
            case 25:
                return min / 8.0d;
            case 26:
                return min / 16.0d;
            case 27:
                return min / 32.0d;
            case 28:
                return shapeAnchor.getCenterY();
            case 29:
                return width;
            case 30:
                return width / 2.0d;
            case 31:
                return width / 3.0d;
            case 32:
                return width / 4.0d;
            case 33:
                return width / 5.0d;
            case 34:
                return width / 6.0d;
            case 35:
                return width / 8.0d;
            case 36:
                return width / 10.0d;
            case 37:
                return width / 32.0d;
            default:
                return 0.0d;
        }
    }

    /* renamed from: org.apache.poi.sl.draw.geom.BuiltInGuide$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$org$apache$poi$sl$draw$geom$BuiltInGuide = null;

        /* JADX WARNING: Can't wrap try/catch for region: R(74:0|1|2|3|4|5|6|7|8|9|10|11|12|13|14|15|16|17|18|19|20|21|22|23|24|25|26|27|28|29|30|31|32|33|34|35|36|37|38|39|40|41|42|43|44|45|46|47|48|49|50|51|52|53|54|55|56|57|58|59|60|61|62|63|64|65|66|67|68|69|70|71|72|(3:73|74|76)) */
        /* JADX WARNING: Can't wrap try/catch for region: R(76:0|1|2|3|4|5|6|7|8|9|10|11|12|13|14|15|16|17|18|19|20|21|22|23|24|25|26|27|28|29|30|31|32|33|34|35|36|37|38|39|40|41|42|43|44|45|46|47|48|49|50|51|52|53|54|55|56|57|58|59|60|61|62|63|64|65|66|67|68|69|70|71|72|73|74|76) */
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
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0033 */
        static {
            /*
                org.apache.poi.sl.draw.geom.BuiltInGuide[] r0 = org.apache.poi.sl.draw.geom.BuiltInGuide.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$org$apache$poi$sl$draw$geom$BuiltInGuide = r0
                org.apache.poi.sl.draw.geom.BuiltInGuide r1 = org.apache.poi.sl.draw.geom.BuiltInGuide._3cd4     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$org$apache$poi$sl$draw$geom$BuiltInGuide     // Catch:{ NoSuchFieldError -> 0x001d }
                org.apache.poi.sl.draw.geom.BuiltInGuide r1 = org.apache.poi.sl.draw.geom.BuiltInGuide._3cd8     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$org$apache$poi$sl$draw$geom$BuiltInGuide     // Catch:{ NoSuchFieldError -> 0x0028 }
                org.apache.poi.sl.draw.geom.BuiltInGuide r1 = org.apache.poi.sl.draw.geom.BuiltInGuide._5cd8     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = $SwitchMap$org$apache$poi$sl$draw$geom$BuiltInGuide     // Catch:{ NoSuchFieldError -> 0x0033 }
                org.apache.poi.sl.draw.geom.BuiltInGuide r1 = org.apache.poi.sl.draw.geom.BuiltInGuide._7cd8     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                int[] r0 = $SwitchMap$org$apache$poi$sl$draw$geom$BuiltInGuide     // Catch:{ NoSuchFieldError -> 0x003e }
                org.apache.poi.sl.draw.geom.BuiltInGuide r1 = org.apache.poi.sl.draw.geom.BuiltInGuide._t     // Catch:{ NoSuchFieldError -> 0x003e }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x003e }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x003e }
            L_0x003e:
                int[] r0 = $SwitchMap$org$apache$poi$sl$draw$geom$BuiltInGuide     // Catch:{ NoSuchFieldError -> 0x0049 }
                org.apache.poi.sl.draw.geom.BuiltInGuide r1 = org.apache.poi.sl.draw.geom.BuiltInGuide._b     // Catch:{ NoSuchFieldError -> 0x0049 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0049 }
                r2 = 6
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0049 }
            L_0x0049:
                int[] r0 = $SwitchMap$org$apache$poi$sl$draw$geom$BuiltInGuide     // Catch:{ NoSuchFieldError -> 0x0054 }
                org.apache.poi.sl.draw.geom.BuiltInGuide r1 = org.apache.poi.sl.draw.geom.BuiltInGuide._l     // Catch:{ NoSuchFieldError -> 0x0054 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0054 }
                r2 = 7
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0054 }
            L_0x0054:
                int[] r0 = $SwitchMap$org$apache$poi$sl$draw$geom$BuiltInGuide     // Catch:{ NoSuchFieldError -> 0x0060 }
                org.apache.poi.sl.draw.geom.BuiltInGuide r1 = org.apache.poi.sl.draw.geom.BuiltInGuide._r     // Catch:{ NoSuchFieldError -> 0x0060 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0060 }
                r2 = 8
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0060 }
            L_0x0060:
                int[] r0 = $SwitchMap$org$apache$poi$sl$draw$geom$BuiltInGuide     // Catch:{ NoSuchFieldError -> 0x006c }
                org.apache.poi.sl.draw.geom.BuiltInGuide r1 = org.apache.poi.sl.draw.geom.BuiltInGuide._cd2     // Catch:{ NoSuchFieldError -> 0x006c }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x006c }
                r2 = 9
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x006c }
            L_0x006c:
                int[] r0 = $SwitchMap$org$apache$poi$sl$draw$geom$BuiltInGuide     // Catch:{ NoSuchFieldError -> 0x0078 }
                org.apache.poi.sl.draw.geom.BuiltInGuide r1 = org.apache.poi.sl.draw.geom.BuiltInGuide._cd4     // Catch:{ NoSuchFieldError -> 0x0078 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0078 }
                r2 = 10
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0078 }
            L_0x0078:
                int[] r0 = $SwitchMap$org$apache$poi$sl$draw$geom$BuiltInGuide     // Catch:{ NoSuchFieldError -> 0x0084 }
                org.apache.poi.sl.draw.geom.BuiltInGuide r1 = org.apache.poi.sl.draw.geom.BuiltInGuide._cd8     // Catch:{ NoSuchFieldError -> 0x0084 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0084 }
                r2 = 11
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0084 }
            L_0x0084:
                int[] r0 = $SwitchMap$org$apache$poi$sl$draw$geom$BuiltInGuide     // Catch:{ NoSuchFieldError -> 0x0090 }
                org.apache.poi.sl.draw.geom.BuiltInGuide r1 = org.apache.poi.sl.draw.geom.BuiltInGuide._hc     // Catch:{ NoSuchFieldError -> 0x0090 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0090 }
                r2 = 12
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0090 }
            L_0x0090:
                int[] r0 = $SwitchMap$org$apache$poi$sl$draw$geom$BuiltInGuide     // Catch:{ NoSuchFieldError -> 0x009c }
                org.apache.poi.sl.draw.geom.BuiltInGuide r1 = org.apache.poi.sl.draw.geom.BuiltInGuide._h     // Catch:{ NoSuchFieldError -> 0x009c }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x009c }
                r2 = 13
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x009c }
            L_0x009c:
                int[] r0 = $SwitchMap$org$apache$poi$sl$draw$geom$BuiltInGuide     // Catch:{ NoSuchFieldError -> 0x00a8 }
                org.apache.poi.sl.draw.geom.BuiltInGuide r1 = org.apache.poi.sl.draw.geom.BuiltInGuide._hd2     // Catch:{ NoSuchFieldError -> 0x00a8 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x00a8 }
                r2 = 14
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x00a8 }
            L_0x00a8:
                int[] r0 = $SwitchMap$org$apache$poi$sl$draw$geom$BuiltInGuide     // Catch:{ NoSuchFieldError -> 0x00b4 }
                org.apache.poi.sl.draw.geom.BuiltInGuide r1 = org.apache.poi.sl.draw.geom.BuiltInGuide._hd3     // Catch:{ NoSuchFieldError -> 0x00b4 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x00b4 }
                r2 = 15
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x00b4 }
            L_0x00b4:
                int[] r0 = $SwitchMap$org$apache$poi$sl$draw$geom$BuiltInGuide     // Catch:{ NoSuchFieldError -> 0x00c0 }
                org.apache.poi.sl.draw.geom.BuiltInGuide r1 = org.apache.poi.sl.draw.geom.BuiltInGuide._hd4     // Catch:{ NoSuchFieldError -> 0x00c0 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x00c0 }
                r2 = 16
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x00c0 }
            L_0x00c0:
                int[] r0 = $SwitchMap$org$apache$poi$sl$draw$geom$BuiltInGuide     // Catch:{ NoSuchFieldError -> 0x00cc }
                org.apache.poi.sl.draw.geom.BuiltInGuide r1 = org.apache.poi.sl.draw.geom.BuiltInGuide._hd5     // Catch:{ NoSuchFieldError -> 0x00cc }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x00cc }
                r2 = 17
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x00cc }
            L_0x00cc:
                int[] r0 = $SwitchMap$org$apache$poi$sl$draw$geom$BuiltInGuide     // Catch:{ NoSuchFieldError -> 0x00d8 }
                org.apache.poi.sl.draw.geom.BuiltInGuide r1 = org.apache.poi.sl.draw.geom.BuiltInGuide._hd6     // Catch:{ NoSuchFieldError -> 0x00d8 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x00d8 }
                r2 = 18
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x00d8 }
            L_0x00d8:
                int[] r0 = $SwitchMap$org$apache$poi$sl$draw$geom$BuiltInGuide     // Catch:{ NoSuchFieldError -> 0x00e4 }
                org.apache.poi.sl.draw.geom.BuiltInGuide r1 = org.apache.poi.sl.draw.geom.BuiltInGuide._hd8     // Catch:{ NoSuchFieldError -> 0x00e4 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x00e4 }
                r2 = 19
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x00e4 }
            L_0x00e4:
                int[] r0 = $SwitchMap$org$apache$poi$sl$draw$geom$BuiltInGuide     // Catch:{ NoSuchFieldError -> 0x00f0 }
                org.apache.poi.sl.draw.geom.BuiltInGuide r1 = org.apache.poi.sl.draw.geom.BuiltInGuide._ls     // Catch:{ NoSuchFieldError -> 0x00f0 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x00f0 }
                r2 = 20
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x00f0 }
            L_0x00f0:
                int[] r0 = $SwitchMap$org$apache$poi$sl$draw$geom$BuiltInGuide     // Catch:{ NoSuchFieldError -> 0x00fc }
                org.apache.poi.sl.draw.geom.BuiltInGuide r1 = org.apache.poi.sl.draw.geom.BuiltInGuide._ss     // Catch:{ NoSuchFieldError -> 0x00fc }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x00fc }
                r2 = 21
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x00fc }
            L_0x00fc:
                int[] r0 = $SwitchMap$org$apache$poi$sl$draw$geom$BuiltInGuide     // Catch:{ NoSuchFieldError -> 0x0108 }
                org.apache.poi.sl.draw.geom.BuiltInGuide r1 = org.apache.poi.sl.draw.geom.BuiltInGuide._ssd2     // Catch:{ NoSuchFieldError -> 0x0108 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0108 }
                r2 = 22
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0108 }
            L_0x0108:
                int[] r0 = $SwitchMap$org$apache$poi$sl$draw$geom$BuiltInGuide     // Catch:{ NoSuchFieldError -> 0x0114 }
                org.apache.poi.sl.draw.geom.BuiltInGuide r1 = org.apache.poi.sl.draw.geom.BuiltInGuide._ssd4     // Catch:{ NoSuchFieldError -> 0x0114 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0114 }
                r2 = 23
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0114 }
            L_0x0114:
                int[] r0 = $SwitchMap$org$apache$poi$sl$draw$geom$BuiltInGuide     // Catch:{ NoSuchFieldError -> 0x0120 }
                org.apache.poi.sl.draw.geom.BuiltInGuide r1 = org.apache.poi.sl.draw.geom.BuiltInGuide._ssd6     // Catch:{ NoSuchFieldError -> 0x0120 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0120 }
                r2 = 24
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0120 }
            L_0x0120:
                int[] r0 = $SwitchMap$org$apache$poi$sl$draw$geom$BuiltInGuide     // Catch:{ NoSuchFieldError -> 0x012c }
                org.apache.poi.sl.draw.geom.BuiltInGuide r1 = org.apache.poi.sl.draw.geom.BuiltInGuide._ssd8     // Catch:{ NoSuchFieldError -> 0x012c }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x012c }
                r2 = 25
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x012c }
            L_0x012c:
                int[] r0 = $SwitchMap$org$apache$poi$sl$draw$geom$BuiltInGuide     // Catch:{ NoSuchFieldError -> 0x0138 }
                org.apache.poi.sl.draw.geom.BuiltInGuide r1 = org.apache.poi.sl.draw.geom.BuiltInGuide._ssd16     // Catch:{ NoSuchFieldError -> 0x0138 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0138 }
                r2 = 26
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0138 }
            L_0x0138:
                int[] r0 = $SwitchMap$org$apache$poi$sl$draw$geom$BuiltInGuide     // Catch:{ NoSuchFieldError -> 0x0144 }
                org.apache.poi.sl.draw.geom.BuiltInGuide r1 = org.apache.poi.sl.draw.geom.BuiltInGuide._ssd32     // Catch:{ NoSuchFieldError -> 0x0144 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0144 }
                r2 = 27
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0144 }
            L_0x0144:
                int[] r0 = $SwitchMap$org$apache$poi$sl$draw$geom$BuiltInGuide     // Catch:{ NoSuchFieldError -> 0x0150 }
                org.apache.poi.sl.draw.geom.BuiltInGuide r1 = org.apache.poi.sl.draw.geom.BuiltInGuide._vc     // Catch:{ NoSuchFieldError -> 0x0150 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0150 }
                r2 = 28
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0150 }
            L_0x0150:
                int[] r0 = $SwitchMap$org$apache$poi$sl$draw$geom$BuiltInGuide     // Catch:{ NoSuchFieldError -> 0x015c }
                org.apache.poi.sl.draw.geom.BuiltInGuide r1 = org.apache.poi.sl.draw.geom.BuiltInGuide._w     // Catch:{ NoSuchFieldError -> 0x015c }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x015c }
                r2 = 29
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x015c }
            L_0x015c:
                int[] r0 = $SwitchMap$org$apache$poi$sl$draw$geom$BuiltInGuide     // Catch:{ NoSuchFieldError -> 0x0168 }
                org.apache.poi.sl.draw.geom.BuiltInGuide r1 = org.apache.poi.sl.draw.geom.BuiltInGuide._wd2     // Catch:{ NoSuchFieldError -> 0x0168 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0168 }
                r2 = 30
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0168 }
            L_0x0168:
                int[] r0 = $SwitchMap$org$apache$poi$sl$draw$geom$BuiltInGuide     // Catch:{ NoSuchFieldError -> 0x0174 }
                org.apache.poi.sl.draw.geom.BuiltInGuide r1 = org.apache.poi.sl.draw.geom.BuiltInGuide._wd3     // Catch:{ NoSuchFieldError -> 0x0174 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0174 }
                r2 = 31
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0174 }
            L_0x0174:
                int[] r0 = $SwitchMap$org$apache$poi$sl$draw$geom$BuiltInGuide     // Catch:{ NoSuchFieldError -> 0x0180 }
                org.apache.poi.sl.draw.geom.BuiltInGuide r1 = org.apache.poi.sl.draw.geom.BuiltInGuide._wd4     // Catch:{ NoSuchFieldError -> 0x0180 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0180 }
                r2 = 32
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0180 }
            L_0x0180:
                int[] r0 = $SwitchMap$org$apache$poi$sl$draw$geom$BuiltInGuide     // Catch:{ NoSuchFieldError -> 0x018c }
                org.apache.poi.sl.draw.geom.BuiltInGuide r1 = org.apache.poi.sl.draw.geom.BuiltInGuide._wd5     // Catch:{ NoSuchFieldError -> 0x018c }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x018c }
                r2 = 33
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x018c }
            L_0x018c:
                int[] r0 = $SwitchMap$org$apache$poi$sl$draw$geom$BuiltInGuide     // Catch:{ NoSuchFieldError -> 0x0198 }
                org.apache.poi.sl.draw.geom.BuiltInGuide r1 = org.apache.poi.sl.draw.geom.BuiltInGuide._wd6     // Catch:{ NoSuchFieldError -> 0x0198 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0198 }
                r2 = 34
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0198 }
            L_0x0198:
                int[] r0 = $SwitchMap$org$apache$poi$sl$draw$geom$BuiltInGuide     // Catch:{ NoSuchFieldError -> 0x01a4 }
                org.apache.poi.sl.draw.geom.BuiltInGuide r1 = org.apache.poi.sl.draw.geom.BuiltInGuide._wd8     // Catch:{ NoSuchFieldError -> 0x01a4 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x01a4 }
                r2 = 35
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x01a4 }
            L_0x01a4:
                int[] r0 = $SwitchMap$org$apache$poi$sl$draw$geom$BuiltInGuide     // Catch:{ NoSuchFieldError -> 0x01b0 }
                org.apache.poi.sl.draw.geom.BuiltInGuide r1 = org.apache.poi.sl.draw.geom.BuiltInGuide._wd10     // Catch:{ NoSuchFieldError -> 0x01b0 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x01b0 }
                r2 = 36
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x01b0 }
            L_0x01b0:
                int[] r0 = $SwitchMap$org$apache$poi$sl$draw$geom$BuiltInGuide     // Catch:{ NoSuchFieldError -> 0x01bc }
                org.apache.poi.sl.draw.geom.BuiltInGuide r1 = org.apache.poi.sl.draw.geom.BuiltInGuide._wd32     // Catch:{ NoSuchFieldError -> 0x01bc }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x01bc }
                r2 = 37
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x01bc }
            L_0x01bc:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.sl.draw.geom.BuiltInGuide.AnonymousClass1.<clinit>():void");
        }
    }
}
