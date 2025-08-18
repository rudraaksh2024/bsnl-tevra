package org.apache.poi.sl.draw.geom;

import java.util.regex.Pattern;

public interface GuideIf extends Formula {
    public static final Pattern WHITESPACE = Pattern.compile("\\s+");

    public enum Op {
        muldiv,
        addsub,
        adddiv,
        ifelse,
        val,
        abs,
        sqrt,
        max,
        min,
        at2,
        sin,
        cos,
        tan,
        cat2,
        sat2,
        pin,
        mod
    }

    String getFmla();

    String getName();

    void setFmla(String str);

    void setName(String str);

    double evaluate(Context context) {
        return evaluateGuide(context);
    }

    double evaluateGuide(Context context) {
        Op op;
        String[] split = WHITESPACE.split(getFmla());
        String str = split[0];
        str.hashCode();
        char c = 65535;
        switch (str.hashCode()) {
            case 1349:
                if (str.equals("*/")) {
                    c = 0;
                    break;
                }
                break;
            case 1378:
                if (str.equals("+-")) {
                    c = 1;
                    break;
                }
                break;
            case 1380:
                if (str.equals("+/")) {
                    c = 2;
                    break;
                }
                break;
            case 2011:
                if (str.equals("?:")) {
                    c = 3;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
                op = Op.muldiv;
                break;
            case 1:
                op = Op.addsub;
                break;
            case 2:
                op = Op.adddiv;
                break;
            case 3:
                op = Op.ifelse;
                break;
            default:
                op = Op.valueOf(split[0]);
                break;
        }
        double value = split.length > 1 ? context.getValue(split[1]) : 0.0d;
        double value2 = split.length > 2 ? context.getValue(split[2]) : 0.0d;
        double value3 = split.length > 3 ? context.getValue(split[3]) : 0.0d;
        switch (AnonymousClass1.$SwitchMap$org$apache$poi$sl$draw$geom$GuideIf$Op[op.ordinal()]) {
            case 1:
                return Math.abs(value);
            case 2:
                if (value3 == 0.0d) {
                    return 0.0d;
                }
                return (value + value2) / value3;
            case 3:
                return (value + value2) - value3;
            case 4:
                return Math.toDegrees(Math.atan2(value2, value)) * 60000.0d;
            case 5:
                return value * Math.cos(Math.toRadians(value2 / 60000.0d));
            case 6:
                return value * Math.cos(Math.atan2(value3, value2));
            case 7:
                return value > 0.0d ? value2 : value3;
            case 8:
                return value;
            case 9:
                return Math.max(value, value2);
            case 10:
                return Math.min(value, value2);
            case 11:
                return Math.sqrt((value * value) + (value2 * value2) + (value3 * value3));
            case 12:
                if (value3 == 0.0d) {
                    return 0.0d;
                }
                return (value * value2) / value3;
            case 13:
                return Math.max(value, Math.min(value2, value3));
            case 14:
                return value * Math.sin(Math.atan2(value3, value2));
            case 15:
                return value * Math.sin(Math.toRadians(value2 / 60000.0d));
            case 16:
                return Math.sqrt(value);
            case 17:
                return value * Math.tan(Math.toRadians(value2 / 60000.0d));
            default:
                return 0.0d;
        }
    }

    /* renamed from: org.apache.poi.sl.draw.geom.GuideIf$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$org$apache$poi$sl$draw$geom$GuideIf$Op;

        /* JADX WARNING: Can't wrap try/catch for region: R(36:0|1|2|3|4|5|6|7|8|9|10|11|12|13|14|15|16|17|18|19|20|21|22|23|24|25|26|27|28|29|30|31|32|33|34|36) */
        /* JADX WARNING: Code restructure failed: missing block: B:37:?, code lost:
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
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0033 */
        static {
            /*
                org.apache.poi.sl.draw.geom.GuideIf$Op[] r0 = org.apache.poi.sl.draw.geom.GuideIf.Op.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$org$apache$poi$sl$draw$geom$GuideIf$Op = r0
                org.apache.poi.sl.draw.geom.GuideIf$Op r1 = org.apache.poi.sl.draw.geom.GuideIf.Op.abs     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$org$apache$poi$sl$draw$geom$GuideIf$Op     // Catch:{ NoSuchFieldError -> 0x001d }
                org.apache.poi.sl.draw.geom.GuideIf$Op r1 = org.apache.poi.sl.draw.geom.GuideIf.Op.adddiv     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$org$apache$poi$sl$draw$geom$GuideIf$Op     // Catch:{ NoSuchFieldError -> 0x0028 }
                org.apache.poi.sl.draw.geom.GuideIf$Op r1 = org.apache.poi.sl.draw.geom.GuideIf.Op.addsub     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = $SwitchMap$org$apache$poi$sl$draw$geom$GuideIf$Op     // Catch:{ NoSuchFieldError -> 0x0033 }
                org.apache.poi.sl.draw.geom.GuideIf$Op r1 = org.apache.poi.sl.draw.geom.GuideIf.Op.at2     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                int[] r0 = $SwitchMap$org$apache$poi$sl$draw$geom$GuideIf$Op     // Catch:{ NoSuchFieldError -> 0x003e }
                org.apache.poi.sl.draw.geom.GuideIf$Op r1 = org.apache.poi.sl.draw.geom.GuideIf.Op.cos     // Catch:{ NoSuchFieldError -> 0x003e }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x003e }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x003e }
            L_0x003e:
                int[] r0 = $SwitchMap$org$apache$poi$sl$draw$geom$GuideIf$Op     // Catch:{ NoSuchFieldError -> 0x0049 }
                org.apache.poi.sl.draw.geom.GuideIf$Op r1 = org.apache.poi.sl.draw.geom.GuideIf.Op.cat2     // Catch:{ NoSuchFieldError -> 0x0049 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0049 }
                r2 = 6
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0049 }
            L_0x0049:
                int[] r0 = $SwitchMap$org$apache$poi$sl$draw$geom$GuideIf$Op     // Catch:{ NoSuchFieldError -> 0x0054 }
                org.apache.poi.sl.draw.geom.GuideIf$Op r1 = org.apache.poi.sl.draw.geom.GuideIf.Op.ifelse     // Catch:{ NoSuchFieldError -> 0x0054 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0054 }
                r2 = 7
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0054 }
            L_0x0054:
                int[] r0 = $SwitchMap$org$apache$poi$sl$draw$geom$GuideIf$Op     // Catch:{ NoSuchFieldError -> 0x0060 }
                org.apache.poi.sl.draw.geom.GuideIf$Op r1 = org.apache.poi.sl.draw.geom.GuideIf.Op.val     // Catch:{ NoSuchFieldError -> 0x0060 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0060 }
                r2 = 8
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0060 }
            L_0x0060:
                int[] r0 = $SwitchMap$org$apache$poi$sl$draw$geom$GuideIf$Op     // Catch:{ NoSuchFieldError -> 0x006c }
                org.apache.poi.sl.draw.geom.GuideIf$Op r1 = org.apache.poi.sl.draw.geom.GuideIf.Op.max     // Catch:{ NoSuchFieldError -> 0x006c }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x006c }
                r2 = 9
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x006c }
            L_0x006c:
                int[] r0 = $SwitchMap$org$apache$poi$sl$draw$geom$GuideIf$Op     // Catch:{ NoSuchFieldError -> 0x0078 }
                org.apache.poi.sl.draw.geom.GuideIf$Op r1 = org.apache.poi.sl.draw.geom.GuideIf.Op.min     // Catch:{ NoSuchFieldError -> 0x0078 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0078 }
                r2 = 10
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0078 }
            L_0x0078:
                int[] r0 = $SwitchMap$org$apache$poi$sl$draw$geom$GuideIf$Op     // Catch:{ NoSuchFieldError -> 0x0084 }
                org.apache.poi.sl.draw.geom.GuideIf$Op r1 = org.apache.poi.sl.draw.geom.GuideIf.Op.mod     // Catch:{ NoSuchFieldError -> 0x0084 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0084 }
                r2 = 11
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0084 }
            L_0x0084:
                int[] r0 = $SwitchMap$org$apache$poi$sl$draw$geom$GuideIf$Op     // Catch:{ NoSuchFieldError -> 0x0090 }
                org.apache.poi.sl.draw.geom.GuideIf$Op r1 = org.apache.poi.sl.draw.geom.GuideIf.Op.muldiv     // Catch:{ NoSuchFieldError -> 0x0090 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0090 }
                r2 = 12
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0090 }
            L_0x0090:
                int[] r0 = $SwitchMap$org$apache$poi$sl$draw$geom$GuideIf$Op     // Catch:{ NoSuchFieldError -> 0x009c }
                org.apache.poi.sl.draw.geom.GuideIf$Op r1 = org.apache.poi.sl.draw.geom.GuideIf.Op.pin     // Catch:{ NoSuchFieldError -> 0x009c }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x009c }
                r2 = 13
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x009c }
            L_0x009c:
                int[] r0 = $SwitchMap$org$apache$poi$sl$draw$geom$GuideIf$Op     // Catch:{ NoSuchFieldError -> 0x00a8 }
                org.apache.poi.sl.draw.geom.GuideIf$Op r1 = org.apache.poi.sl.draw.geom.GuideIf.Op.sat2     // Catch:{ NoSuchFieldError -> 0x00a8 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x00a8 }
                r2 = 14
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x00a8 }
            L_0x00a8:
                int[] r0 = $SwitchMap$org$apache$poi$sl$draw$geom$GuideIf$Op     // Catch:{ NoSuchFieldError -> 0x00b4 }
                org.apache.poi.sl.draw.geom.GuideIf$Op r1 = org.apache.poi.sl.draw.geom.GuideIf.Op.sin     // Catch:{ NoSuchFieldError -> 0x00b4 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x00b4 }
                r2 = 15
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x00b4 }
            L_0x00b4:
                int[] r0 = $SwitchMap$org$apache$poi$sl$draw$geom$GuideIf$Op     // Catch:{ NoSuchFieldError -> 0x00c0 }
                org.apache.poi.sl.draw.geom.GuideIf$Op r1 = org.apache.poi.sl.draw.geom.GuideIf.Op.sqrt     // Catch:{ NoSuchFieldError -> 0x00c0 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x00c0 }
                r2 = 16
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x00c0 }
            L_0x00c0:
                int[] r0 = $SwitchMap$org$apache$poi$sl$draw$geom$GuideIf$Op     // Catch:{ NoSuchFieldError -> 0x00cc }
                org.apache.poi.sl.draw.geom.GuideIf$Op r1 = org.apache.poi.sl.draw.geom.GuideIf.Op.tan     // Catch:{ NoSuchFieldError -> 0x00cc }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x00cc }
                r2 = 17
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x00cc }
            L_0x00cc:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.sl.draw.geom.GuideIf.AnonymousClass1.<clinit>():void");
        }
    }
}
