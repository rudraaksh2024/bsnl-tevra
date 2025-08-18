package org.apache.poi.ss.format;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.poi.ss.format.CellFormatPart;

public class CellElapsedFormatter extends CellFormatter {
    private static final double HOUR__FACTOR = 0.041666666666666664d;
    private static final double MIN__FACTOR = 6.944444444444444E-4d;
    /* access modifiers changed from: private */
    public static final Pattern PERCENTS = Pattern.compile("%");
    private static final double SEC__FACTOR = 1.1574074074074073E-5d;
    private final String printfFmt;
    private final List<TimeSpec> specs;
    /* access modifiers changed from: private */
    public TimeSpec topmost;

    private static class TimeSpec {
        final double factor;
        final int len;
        double modBy = 0.0d;
        final int pos;
        final char type;

        public TimeSpec(char c, int i, int i2, double d) {
            this.type = c;
            this.pos = i;
            this.len = i2;
            this.factor = d;
        }

        public long valueFor(double d) {
            double d2;
            double d3 = this.modBy;
            if (d3 == 0.0d) {
                d2 = d / this.factor;
            } else {
                d2 = (d / this.factor) % d3;
            }
            return this.type == '0' ? Math.round(d2) : (long) d2;
        }
    }

    private class ElapsedPartHandler implements CellFormatPart.PartHandler {
        private ElapsedPartHandler() {
        }

        public String handlePart(Matcher matcher, String str, CellFormatType cellFormatType, StringBuffer stringBuffer) {
            int length = stringBuffer.length();
            char charAt = str.charAt(0);
            if (charAt == 10) {
                return "%n";
            }
            if (charAt == '\"') {
                str = str.substring(1, str.length() - 1);
            } else if (charAt != '*') {
                if (charAt != '0') {
                    if (charAt == '_') {
                        return null;
                    }
                    if (!(charAt == 'h' || charAt == 'm' || charAt == 's')) {
                        if (charAt != '[') {
                            if (charAt == '\\') {
                                str = str.substring(1);
                            }
                        } else if (str.length() >= 3) {
                            if (CellElapsedFormatter.this.topmost == null) {
                                String lowerCase = str.toLowerCase(Locale.ROOT);
                                int length2 = lowerCase.length() - 2;
                                CellElapsedFormatter cellElapsedFormatter = CellElapsedFormatter.this;
                                TimeSpec unused = cellElapsedFormatter.topmost = cellElapsedFormatter.assignSpec(lowerCase.charAt(1), length, length2);
                                return lowerCase.substring(1, length2 + 1);
                            }
                            throw new IllegalArgumentException("Duplicate '[' times in format");
                        }
                    }
                }
                String lowerCase2 = str.toLowerCase(Locale.ROOT);
                TimeSpec unused2 = CellElapsedFormatter.this.assignSpec(lowerCase2.charAt(0), length, lowerCase2.length());
                return lowerCase2;
            } else if (str.length() > 1) {
                str = CellFormatPart.expandChar(str);
            }
            return CellElapsedFormatter.PERCENTS.matcher(str).replaceAll("%%");
        }
    }

    public CellElapsedFormatter(String str) {
        super(str);
        ArrayList arrayList = new ArrayList();
        this.specs = arrayList;
        StringBuffer parseFormat = CellFormatPart.parseFormat(str, CellFormatType.ELAPSED, new ElapsedPartHandler());
        ListIterator listIterator = arrayList.listIterator(arrayList.size());
        while (listIterator.hasPrevious()) {
            TimeSpec timeSpec = (TimeSpec) listIterator.previous();
            parseFormat.replace(timeSpec.pos, timeSpec.pos + timeSpec.len, "%0" + timeSpec.len + "d");
            if (timeSpec.type != this.topmost.type) {
                timeSpec.modBy = modFor(timeSpec.type, timeSpec.len);
            }
        }
        this.printfFmt = parseFormat.toString();
    }

    /* access modifiers changed from: private */
    public TimeSpec assignSpec(char c, int i, int i2) {
        TimeSpec timeSpec = new TimeSpec(c, i, i2, factorFor(c, i2));
        this.specs.add(timeSpec);
        return timeSpec;
    }

    private static double factorFor(char c, int i) {
        if (c == '0') {
            return SEC__FACTOR / Math.pow(10.0d, (double) i);
        }
        if (c == 'h') {
            return HOUR__FACTOR;
        }
        if (c == 'm') {
            return MIN__FACTOR;
        }
        if (c == 's') {
            return SEC__FACTOR;
        }
        throw new IllegalArgumentException("Uknown elapsed time spec: " + c);
    }

    private static double modFor(char c, int i) {
        if (c == '0') {
            return Math.pow(10.0d, (double) i);
        }
        if (c == 'h') {
            return 24.0d;
        }
        if (c == 'm' || c == 's') {
            return 60.0d;
        }
        throw new IllegalArgumentException("Uknown elapsed time spec: " + c);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:15:0x004a, code lost:
        r6 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:?, code lost:
        r0.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x004f, code lost:
        r7 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0050, code lost:
        r5.addSuppressed(r7);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0053, code lost:
        throw r6;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void formatValue(java.lang.StringBuffer r6, java.lang.Object r7) {
        /*
            r5 = this;
            java.lang.Number r7 = (java.lang.Number) r7
            double r0 = r7.doubleValue()
            r2 = 0
            int r7 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r7 >= 0) goto L_0x0012
            r7 = 45
            r6.append(r7)
            double r0 = -r0
        L_0x0012:
            java.util.List<org.apache.poi.ss.format.CellElapsedFormatter$TimeSpec> r7 = r5.specs
            int r7 = r7.size()
            java.lang.Long[] r7 = new java.lang.Long[r7]
            r2 = 0
        L_0x001b:
            java.util.List<org.apache.poi.ss.format.CellElapsedFormatter$TimeSpec> r3 = r5.specs
            int r3 = r3.size()
            if (r2 >= r3) goto L_0x0038
            java.util.List<org.apache.poi.ss.format.CellElapsedFormatter$TimeSpec> r3 = r5.specs
            java.lang.Object r3 = r3.get(r2)
            org.apache.poi.ss.format.CellElapsedFormatter$TimeSpec r3 = (org.apache.poi.ss.format.CellElapsedFormatter.TimeSpec) r3
            long r3 = r3.valueFor(r0)
            java.lang.Long r3 = java.lang.Long.valueOf(r3)
            r7[r2] = r3
            int r2 = r2 + 1
            goto L_0x001b
        L_0x0038:
            java.util.Formatter r0 = new java.util.Formatter
            java.util.Locale r1 = java.util.Locale.ROOT
            r0.<init>(r6, r1)
            java.lang.String r5 = r5.printfFmt     // Catch:{ all -> 0x0048 }
            r0.format(r5, r7)     // Catch:{ all -> 0x0048 }
            r0.close()
            return
        L_0x0048:
            r5 = move-exception
            throw r5     // Catch:{ all -> 0x004a }
        L_0x004a:
            r6 = move-exception
            r0.close()     // Catch:{ all -> 0x004f }
            goto L_0x0053
        L_0x004f:
            r7 = move-exception
            r5.addSuppressed(r7)
        L_0x0053:
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.ss.format.CellElapsedFormatter.formatValue(java.lang.StringBuffer, java.lang.Object):void");
    }

    public void simpleValue(StringBuffer stringBuffer, Object obj) {
        formatValue(stringBuffer, obj);
    }
}
