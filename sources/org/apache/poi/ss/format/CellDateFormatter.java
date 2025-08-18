package org.apache.poi.ss.format;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import org.apache.poi.ss.format.CellFormatPart;
import org.apache.poi.util.LocaleUtil;

public class CellDateFormatter extends CellFormatter {
    private static final Calendar EXCEL_EPOCH_CAL = LocaleUtil.getLocaleCalendar(1904, 0, 1);
    private static final int NUM_MILLISECONDS_IN_DAY = 86400000;
    private static CellDateFormatter SIMPLE_DATE;
    /* access modifiers changed from: private */
    public boolean amPmUpper;
    private final DateFormat dateFmt;
    /* access modifiers changed from: private */
    public String sFmt;
    /* access modifiers changed from: private */
    public boolean showAmPm;
    /* access modifiers changed from: private */
    public boolean showM;

    class DatePartHandler implements CellFormatPart.PartHandler {
        private int hLen;
        private int hStart = -1;
        private int mLen;
        private int mStart = -1;

        DatePartHandler() {
        }

        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v6, resolved type: boolean} */
        /* JADX WARNING: type inference failed for: r6v1 */
        /* JADX WARNING: type inference failed for: r6v7 */
        /* JADX WARNING: type inference failed for: r6v8, types: [int] */
        /* JADX WARNING: type inference failed for: r6v10 */
        /* JADX WARNING: Multi-variable type inference failed */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public java.lang.String handlePart(java.util.regex.Matcher r4, java.lang.String r5, org.apache.poi.ss.format.CellFormatType r6, java.lang.StringBuffer r7) {
            /*
                r3 = this;
                int r4 = r7.length()
                r6 = 0
                char r0 = r5.charAt(r6)
                r1 = 1
                r2 = -1
                switch(r0) {
                    case 48: goto L_0x00ca;
                    case 65: goto L_0x0091;
                    case 68: goto L_0x0072;
                    case 72: goto L_0x0061;
                    case 77: goto L_0x0047;
                    case 80: goto L_0x0091;
                    case 83: goto L_0x002b;
                    case 89: goto L_0x0010;
                    case 97: goto L_0x0091;
                    case 100: goto L_0x0072;
                    case 104: goto L_0x0061;
                    case 109: goto L_0x0047;
                    case 112: goto L_0x0091;
                    case 115: goto L_0x002b;
                    case 121: goto L_0x0010;
                    default: goto L_0x000e;
                }
            L_0x000e:
                goto L_0x00ff
            L_0x0010:
                r3.mStart = r2
                int r3 = r5.length()
                if (r3 != r1) goto L_0x001b
                java.lang.String r5 = "yy"
                goto L_0x0024
            L_0x001b:
                int r3 = r5.length()
                r4 = 3
                if (r3 != r4) goto L_0x0024
                java.lang.String r5 = "yyyy"
            L_0x0024:
                java.util.Locale r3 = java.util.Locale.ROOT
                java.lang.String r3 = r5.toLowerCase(r3)
                return r3
            L_0x002b:
                int r4 = r3.mStart
                if (r4 < 0) goto L_0x0040
            L_0x002f:
                int r4 = r3.mLen
                if (r6 >= r4) goto L_0x003e
                int r4 = r3.mStart
                int r4 = r4 + r6
                r0 = 109(0x6d, float:1.53E-43)
                r7.setCharAt(r4, r0)
                int r6 = r6 + 1
                goto L_0x002f
            L_0x003e:
                r3.mStart = r2
            L_0x0040:
                java.util.Locale r3 = java.util.Locale.ROOT
                java.lang.String r3 = r5.toLowerCase(r3)
                return r3
            L_0x0047:
                r3.mStart = r4
                int r4 = r5.length()
                r3.mLen = r4
                int r3 = r3.hStart
                if (r3 < 0) goto L_0x005a
                java.util.Locale r3 = java.util.Locale.ROOT
                java.lang.String r3 = r5.toLowerCase(r3)
                return r3
            L_0x005a:
                java.util.Locale r3 = java.util.Locale.ROOT
                java.lang.String r3 = r5.toUpperCase(r3)
                return r3
            L_0x0061:
                r3.mStart = r2
                r3.hStart = r4
                int r4 = r5.length()
                r3.hLen = r4
                java.util.Locale r3 = java.util.Locale.ROOT
                java.lang.String r3 = r5.toLowerCase(r3)
                return r3
            L_0x0072:
                r3.mStart = r2
                int r3 = r5.length()
                r4 = 2
                if (r3 > r4) goto L_0x0082
                java.util.Locale r3 = java.util.Locale.ROOT
                java.lang.String r3 = r5.toLowerCase(r3)
                return r3
            L_0x0082:
                java.util.Locale r3 = java.util.Locale.ROOT
                java.lang.String r3 = r5.toLowerCase(r3)
                r4 = 100
                r5 = 69
                java.lang.String r3 = r3.replace(r4, r5)
                return r3
            L_0x0091:
                int r4 = r5.length()
                if (r4 <= r1) goto L_0x00ff
                r3.mStart = r2
                org.apache.poi.ss.format.CellDateFormatter r4 = org.apache.poi.ss.format.CellDateFormatter.this
                boolean unused = r4.showAmPm = r1
                org.apache.poi.ss.format.CellDateFormatter r4 = org.apache.poi.ss.format.CellDateFormatter.this
                char r7 = r5.charAt(r1)
                java.lang.String r7 = org.apache.poi.util.StringUtil.toLowerCase(r7)
                java.lang.String r0 = "m"
                boolean r7 = r7.equals(r0)
                boolean unused = r4.showM = r7
                org.apache.poi.ss.format.CellDateFormatter r3 = org.apache.poi.ss.format.CellDateFormatter.this
                boolean r4 = r3.showM
                if (r4 != 0) goto L_0x00c3
                char r4 = r5.charAt(r6)
                boolean r4 = org.apache.poi.util.StringUtil.isUpperCase(r4)
                if (r4 == 0) goto L_0x00c4
            L_0x00c3:
                r6 = r1
            L_0x00c4:
                boolean unused = r3.amPmUpper = r6
                java.lang.String r3 = "a"
                return r3
            L_0x00ca:
                r3.mStart = r2
                int r4 = r5.length()
                org.apache.poi.ss.format.CellDateFormatter r3 = org.apache.poi.ss.format.CellDateFormatter.this
                java.lang.StringBuilder r6 = new java.lang.StringBuilder
                java.lang.String r7 = "%0"
                r6.<init>(r7)
                int r7 = r4 + 2
                java.lang.StringBuilder r6 = r6.append(r7)
                java.lang.String r7 = "."
                java.lang.StringBuilder r6 = r6.append(r7)
                java.lang.StringBuilder r4 = r6.append(r4)
                java.lang.String r6 = "f"
                java.lang.StringBuilder r4 = r4.append(r6)
                java.lang.String r4 = r4.toString()
                java.lang.String unused = r3.sFmt = r4
                r3 = 48
                r4 = 83
                java.lang.String r3 = r5.replace(r3, r4)
                return r3
            L_0x00ff:
                r3 = 0
                return r3
            */
            throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.ss.format.CellDateFormatter.DatePartHandler.handlePart(java.util.regex.Matcher, java.lang.String, org.apache.poi.ss.format.CellFormatType, java.lang.StringBuffer):java.lang.String");
        }

        public void updatePositions(int i, int i2) {
            int i3 = this.hStart;
            if (i < i3) {
                this.hStart = i3 + i2;
            }
            int i4 = this.mStart;
            if (i < i4) {
                this.mStart = i4 + i2;
            }
        }

        public void finish(StringBuffer stringBuffer) {
            if (this.hStart >= 0 && !CellDateFormatter.this.showAmPm) {
                for (int i = 0; i < this.hLen; i++) {
                    stringBuffer.setCharAt(this.hStart + i, 'H');
                }
            }
        }
    }

    public CellDateFormatter(String str) {
        this(LocaleUtil.getUserLocale(), str);
    }

    public CellDateFormatter(Locale locale, String str) {
        super(str);
        DatePartHandler datePartHandler = new DatePartHandler();
        StringBuffer parseFormat = CellFormatPart.parseFormat(str, CellFormatType.DATE, datePartHandler);
        datePartHandler.finish(parseFormat);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(parseFormat.toString(), locale);
        this.dateFmt = simpleDateFormat;
        simpleDateFormat.setTimeZone(LocaleUtil.getUserTimeZone());
    }

    /* JADX WARNING: Incorrect type for immutable var: ssa=int, code=java.lang.Throwable, for r11v0, types: [int] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void formatValue(java.lang.StringBuffer r19, java.lang.Object r20) {
        /*
            r18 = this;
            r1 = r18
            r0 = r19
            monitor-enter(r18)
            r2 = 0
            if (r20 != 0) goto L_0x000e
            java.lang.Double r4 = java.lang.Double.valueOf(r2)     // Catch:{ all -> 0x00ff }
            goto L_0x0010
        L_0x000e:
            r4 = r20
        L_0x0010:
            boolean r5 = r4 instanceof java.lang.Number     // Catch:{ all -> 0x00ff }
            r6 = 4652007308841189376(0x408f400000000000, double:1000.0)
            if (r5 == 0) goto L_0x005c
            java.lang.Number r4 = (java.lang.Number) r4     // Catch:{ all -> 0x00ff }
            double r4 = r4.doubleValue()     // Catch:{ all -> 0x00ff }
            r8 = 4725570615333879808(0x4194997000000000, double:8.64E7)
            double r4 = r4 * r8
            long r4 = java.lang.Math.round(r4)     // Catch:{ all -> 0x00ff }
            double r4 = (double) r4     // Catch:{ all -> 0x00ff }
            int r2 = (r4 > r2 ? 1 : (r4 == r2 ? 0 : -1))
            if (r2 != 0) goto L_0x0035
            java.util.Calendar r2 = EXCEL_EPOCH_CAL     // Catch:{ all -> 0x00ff }
            java.util.Date r4 = r2.getTime()     // Catch:{ all -> 0x00ff }
            goto L_0x005c
        L_0x0035:
            java.util.Calendar r2 = EXCEL_EPOCH_CAL     // Catch:{ all -> 0x00ff }
            java.lang.Object r2 = r2.clone()     // Catch:{ all -> 0x00ff }
            java.util.Calendar r2 = (java.util.Calendar) r2     // Catch:{ all -> 0x00ff }
            java.lang.String r3 = r1.sFmt     // Catch:{ all -> 0x00ff }
            if (r3 != 0) goto L_0x0049
            double r8 = r4 / r6
            long r8 = java.lang.Math.round(r8)     // Catch:{ all -> 0x00ff }
            double r8 = (double) r8     // Catch:{ all -> 0x00ff }
            goto L_0x004b
        L_0x0049:
            double r8 = r4 / r6
        L_0x004b:
            int r3 = (int) r8     // Catch:{ all -> 0x00ff }
            r8 = 13
            r2.add(r8, r3)     // Catch:{ all -> 0x00ff }
            double r4 = r4 % r6
            int r3 = (int) r4     // Catch:{ all -> 0x00ff }
            r4 = 14
            r2.add(r4, r3)     // Catch:{ all -> 0x00ff }
            java.util.Date r4 = r2.getTime()     // Catch:{ all -> 0x00ff }
        L_0x005c:
            java.text.DateFormat r2 = r1.dateFmt     // Catch:{ all -> 0x00ff }
            java.text.AttributedCharacterIterator r2 = r2.formatToCharacterIterator(r4)     // Catch:{ all -> 0x00ff }
            char r3 = r2.first()     // Catch:{ all -> 0x00ff }
            r5 = 0
            r8 = r5
            r9 = r8
        L_0x0069:
            r10 = 65535(0xffff, float:9.1834E-41)
            if (r3 == r10) goto L_0x00fd
            java.text.DateFormat$Field r10 = java.text.DateFormat.Field.MILLISECOND     // Catch:{ all -> 0x00ff }
            java.lang.Object r10 = r2.getAttribute(r10)     // Catch:{ all -> 0x00ff }
            r11 = 1
            if (r10 == 0) goto L_0x00bf
            if (r8 != 0) goto L_0x00f7
            r3 = r4
            java.util.Date r3 = (java.util.Date) r3     // Catch:{ all -> 0x00ff }
            int r8 = r19.length()     // Catch:{ all -> 0x00ff }
            java.util.Formatter r10 = new java.util.Formatter     // Catch:{ all -> 0x00ff }
            java.util.Locale r12 = java.util.Locale.ROOT     // Catch:{ all -> 0x00ff }
            r10.<init>(r0, r12)     // Catch:{ all -> 0x00ff }
            long r12 = r3.getTime()     // Catch:{ all -> 0x00b0 }
            r14 = 1000(0x3e8, double:4.94E-321)
            long r12 = r12 % r14
            r16 = 0
            int r3 = (r12 > r16 ? 1 : (r12 == r16 ? 0 : -1))
            if (r3 >= 0) goto L_0x0095
            long r12 = r12 + r14
        L_0x0095:
            java.util.Locale r3 = r1.locale     // Catch:{ all -> 0x00b0 }
            java.lang.String r14 = r1.sFmt     // Catch:{ all -> 0x00b0 }
            java.lang.Object[] r15 = new java.lang.Object[r11]     // Catch:{ all -> 0x00b0 }
            double r12 = (double) r12     // Catch:{ all -> 0x00b0 }
            double r12 = r12 / r6
            java.lang.Double r12 = java.lang.Double.valueOf(r12)     // Catch:{ all -> 0x00b0 }
            r15[r5] = r12     // Catch:{ all -> 0x00b0 }
            r10.format(r3, r14, r15)     // Catch:{ all -> 0x00b0 }
            r10.close()     // Catch:{ all -> 0x00ff }
            int r3 = r8 + 2
            r0.delete(r8, r3)     // Catch:{ all -> 0x00ff }
            r8 = r11
            goto L_0x00f7
        L_0x00b0:
            r0 = move-exception
            r2 = r0
            throw r2     // Catch:{ all -> 0x00b3 }
        L_0x00b3:
            r0 = move-exception
            r3 = r0
            r10.close()     // Catch:{ all -> 0x00b9 }
            goto L_0x00be
        L_0x00b9:
            r0 = move-exception
            r4 = r0
            r2.addSuppressed(r4)     // Catch:{ all -> 0x00ff }
        L_0x00be:
            throw r3     // Catch:{ all -> 0x00ff }
        L_0x00bf:
            java.text.DateFormat$Field r10 = java.text.DateFormat.Field.AM_PM     // Catch:{ all -> 0x00ff }
            java.lang.Object r10 = r2.getAttribute(r10)     // Catch:{ all -> 0x00ff }
            if (r10 == 0) goto L_0x00f4
            if (r9 != 0) goto L_0x00f7
            boolean r9 = r1.showAmPm     // Catch:{ all -> 0x00ff }
            if (r9 == 0) goto L_0x00f2
            boolean r9 = r1.amPmUpper     // Catch:{ all -> 0x00ff }
            if (r9 == 0) goto L_0x00e2
            java.lang.String r3 = org.apache.poi.util.StringUtil.toUpperCase(r3)     // Catch:{ all -> 0x00ff }
            r0.append(r3)     // Catch:{ all -> 0x00ff }
            boolean r3 = r1.showM     // Catch:{ all -> 0x00ff }
            if (r3 == 0) goto L_0x00f2
            r3 = 77
            r0.append(r3)     // Catch:{ all -> 0x00ff }
            goto L_0x00f2
        L_0x00e2:
            java.lang.String r3 = org.apache.poi.util.StringUtil.toLowerCase(r3)     // Catch:{ all -> 0x00ff }
            r0.append(r3)     // Catch:{ all -> 0x00ff }
            boolean r3 = r1.showM     // Catch:{ all -> 0x00ff }
            if (r3 == 0) goto L_0x00f2
            r3 = 109(0x6d, float:1.53E-43)
            r0.append(r3)     // Catch:{ all -> 0x00ff }
        L_0x00f2:
            r9 = r11
            goto L_0x00f7
        L_0x00f4:
            r0.append(r3)     // Catch:{ all -> 0x00ff }
        L_0x00f7:
            char r3 = r2.next()     // Catch:{ all -> 0x00ff }
            goto L_0x0069
        L_0x00fd:
            monitor-exit(r18)
            return
        L_0x00ff:
            r0 = move-exception
            monitor-exit(r18)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.ss.format.CellDateFormatter.formatValue(java.lang.StringBuffer, java.lang.Object):void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:6:0x000d, code lost:
        if (r0.equals(r0) == false) goto L_0x000f;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void simpleValue(java.lang.StringBuffer r3, java.lang.Object r4) {
        /*
            r2 = this;
            java.lang.Class<org.apache.poi.ss.format.CellDateFormatter> r2 = org.apache.poi.ss.format.CellDateFormatter.class
            monitor-enter(r2)
            org.apache.poi.ss.format.CellDateFormatter r0 = SIMPLE_DATE     // Catch:{ all -> 0x001f }
            if (r0 == 0) goto L_0x000f
            java.util.Calendar r0 = EXCEL_EPOCH_CAL     // Catch:{ all -> 0x001f }
            boolean r0 = r0.equals(r0)     // Catch:{ all -> 0x001f }
            if (r0 != 0) goto L_0x0018
        L_0x000f:
            org.apache.poi.ss.format.CellDateFormatter r0 = new org.apache.poi.ss.format.CellDateFormatter     // Catch:{ all -> 0x001f }
            java.lang.String r1 = "mm/d/y"
            r0.<init>(r1)     // Catch:{ all -> 0x001f }
            SIMPLE_DATE = r0     // Catch:{ all -> 0x001f }
        L_0x0018:
            monitor-exit(r2)     // Catch:{ all -> 0x001f }
            org.apache.poi.ss.format.CellDateFormatter r2 = SIMPLE_DATE
            r2.formatValue(r3, r4)
            return
        L_0x001f:
            r3 = move-exception
            monitor-exit(r2)     // Catch:{ all -> 0x001f }
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.ss.format.CellDateFormatter.simpleValue(java.lang.StringBuffer, java.lang.Object):void");
    }
}
