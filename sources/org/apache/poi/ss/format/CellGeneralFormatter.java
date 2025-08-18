package org.apache.poi.ss.format;

import java.util.Locale;
import org.apache.poi.util.LocaleUtil;

public class CellGeneralFormatter extends CellFormatter {
    public CellGeneralFormatter() {
        this(LocaleUtil.getUserLocale());
    }

    public CellGeneralFormatter(Locale locale) {
        super(locale, "General");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:35:0x0080, code lost:
        r11 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:?, code lost:
        r2.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x0085, code lost:
        r12 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x0086, code lost:
        r10.addSuppressed(r12);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x0089, code lost:
        throw r11;
     */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x0053  */
    /* JADX WARNING: Removed duplicated region for block: B:47:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void formatValue(java.lang.StringBuffer r11, java.lang.Object r12) {
        /*
            r10 = this;
            boolean r0 = r12 instanceof java.lang.Number
            if (r0 == 0) goto L_0x008a
            r0 = r12
            java.lang.Number r0 = (java.lang.Number) r0
            double r0 = r0.doubleValue()
            r2 = 0
            int r2 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            r3 = 48
            if (r2 != 0) goto L_0x0017
            r11.append(r3)
            return
        L_0x0017:
            double r4 = java.lang.Math.abs(r0)
            double r4 = java.lang.Math.log10(r4)
            r6 = 4621819117588971520(0x4024000000000000, double:10.0)
            int r2 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            r6 = 0
            r7 = 1
            if (r2 > 0) goto L_0x003b
            r8 = -4602115869219225600(0xc022000000000000, double:-9.0)
            int r2 = (r4 > r8 ? 1 : (r4 == r8 ? 0 : -1))
            if (r2 >= 0) goto L_0x002e
            goto L_0x003b
        L_0x002e:
            long r4 = (long) r0
            double r4 = (double) r4
            int r0 = (r4 > r0 ? 1 : (r4 == r0 ? 0 : -1))
            if (r0 == 0) goto L_0x0037
            java.lang.String r0 = "%1.9f"
            goto L_0x003d
        L_0x0037:
            java.lang.String r0 = "%1.0f"
            r1 = r6
            goto L_0x003e
        L_0x003b:
            java.lang.String r0 = "%1.5E"
        L_0x003d:
            r1 = r7
        L_0x003e:
            java.util.Formatter r2 = new java.util.Formatter
            java.util.Locale r4 = r10.locale
            r2.<init>(r11, r4)
            java.util.Locale r10 = r10.locale     // Catch:{ all -> 0x007e }
            java.lang.Object[] r4 = new java.lang.Object[r7]     // Catch:{ all -> 0x007e }
            r4[r6] = r12     // Catch:{ all -> 0x007e }
            r2.format(r10, r0, r4)     // Catch:{ all -> 0x007e }
            r2.close()
            if (r1 == 0) goto L_0x009f
            java.lang.String r10 = "E"
            boolean r12 = r0.endsWith(r10)
            if (r12 == 0) goto L_0x0060
            int r10 = r11.lastIndexOf(r10)
            goto L_0x0064
        L_0x0060:
            int r10 = r11.length()
        L_0x0064:
            int r10 = r10 - r7
        L_0x0065:
            char r12 = r11.charAt(r10)
            if (r12 != r3) goto L_0x0072
            int r12 = r10 + -1
            r11.deleteCharAt(r10)
            r10 = r12
            goto L_0x0065
        L_0x0072:
            char r12 = r11.charAt(r10)
            r0 = 46
            if (r12 != r0) goto L_0x009f
            r11.deleteCharAt(r10)
            goto L_0x009f
        L_0x007e:
            r10 = move-exception
            throw r10     // Catch:{ all -> 0x0080 }
        L_0x0080:
            r11 = move-exception
            r2.close()     // Catch:{ all -> 0x0085 }
            goto L_0x0089
        L_0x0085:
            r12 = move-exception
            r10.addSuppressed(r12)
        L_0x0089:
            throw r11
        L_0x008a:
            boolean r10 = r12 instanceof java.lang.Boolean
            if (r10 == 0) goto L_0x009c
            java.lang.String r10 = r12.toString()
            java.util.Locale r12 = java.util.Locale.ROOT
            java.lang.String r10 = r10.toUpperCase(r12)
            r11.append(r10)
            goto L_0x009f
        L_0x009c:
            r11.append(r12)
        L_0x009f:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.ss.format.CellGeneralFormatter.formatValue(java.lang.StringBuffer, java.lang.Object):void");
    }

    public void simpleValue(StringBuffer stringBuffer, Object obj) {
        formatValue(stringBuffer, obj);
    }
}
