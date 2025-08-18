package org.apache.xmlbeans;

import java.io.Serializable;
import java.math.BigDecimal;

public final class GDuration implements GDurationSpecification, Serializable {
    private static final int SEEN_DAY = 3;
    private static final int SEEN_HOUR = 4;
    private static final int SEEN_MINUTE = 5;
    private static final int SEEN_MONTH = 2;
    private static final int SEEN_NOTHING = 0;
    private static final int SEEN_SECOND = 6;
    private static final int SEEN_YEAR = 1;
    private static final long serialVersionUID = 1;
    private int _CY;
    private int _D;
    private int _M;
    private BigDecimal _fs;
    private int _h;
    private int _m;
    private int _s;
    private int _sign;

    public final boolean isImmutable() {
        return true;
    }

    public GDuration() {
        this._sign = 1;
        this._fs = GDate._zero;
    }

    /* JADX WARNING: Removed duplicated region for block: B:104:0x0122 A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:69:0x00ef  */
    /* JADX WARNING: Removed duplicated region for block: B:72:0x00f7  */
    /* JADX WARNING: Removed duplicated region for block: B:77:0x0105  */
    /* JADX WARNING: Removed duplicated region for block: B:82:0x0113  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public GDuration(java.lang.CharSequence r17) {
        /*
            r16 = this;
            r0 = r16
            r1 = r17
            r16.<init>()
            int r2 = r17.length()
        L_0x000b:
            if (r2 <= 0) goto L_0x001c
            int r3 = r2 + -1
            char r3 = r1.charAt(r3)
            boolean r3 = org.apache.xmlbeans.GDate.isSpace(r3)
            if (r3 == 0) goto L_0x001c
            int r2 = r2 + -1
            goto L_0x000b
        L_0x001c:
            r4 = 0
        L_0x001d:
            if (r4 >= r2) goto L_0x002c
            char r5 = r1.charAt(r4)
            boolean r5 = org.apache.xmlbeans.GDate.isSpace(r5)
            if (r5 == 0) goto L_0x002c
            int r4 = r4 + 1
            goto L_0x001d
        L_0x002c:
            r5 = 1
            r0._sign = r5
            if (r4 >= r2) goto L_0x003e
            char r6 = r1.charAt(r4)
            r7 = 45
            if (r6 != r7) goto L_0x003e
            r6 = -1
            r0._sign = r6
            int r4 = r4 + 1
        L_0x003e:
            if (r4 >= r2) goto L_0x0170
            char r6 = r1.charAt(r4)
            r7 = 80
            if (r6 != r7) goto L_0x0170
            int r4 = r4 + r5
            java.math.BigDecimal r6 = org.apache.xmlbeans.GDate._zero
            r0._fs = r6
            r6 = 0
            r7 = 0
        L_0x004f:
            if (r4 >= r2) goto L_0x0158
            char r8 = r1.charAt(r4)
            r9 = 84
            java.lang.String r10 = "illegal duration"
            r11 = 3
            if (r8 != r9) goto L_0x0081
            if (r7 != 0) goto L_0x0079
            if (r6 > r11) goto L_0x0071
            int r4 = r4 + 1
            if (r4 >= r2) goto L_0x006b
            char r8 = r1.charAt(r4)
            r7 = r5
            r6 = r11
            goto L_0x0081
        L_0x006b:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            r0.<init>(r10)
            throw r0
        L_0x0071:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.String r1 = "T in duration must precede time fields"
            r0.<init>(r1)
            throw r0
        L_0x0079:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.String r1 = "duration must have no more than one T'"
            r0.<init>(r1)
            throw r0
        L_0x0081:
            boolean r9 = org.apache.xmlbeans.GDate.isDigit(r8)
            if (r9 == 0) goto L_0x0133
            int r8 = org.apache.xmlbeans.GDate.digitVal(r8)
        L_0x008b:
            int r4 = r4 + r5
            if (r4 >= r2) goto L_0x0093
            char r9 = r1.charAt(r4)
            goto L_0x0094
        L_0x0093:
            r9 = 0
        L_0x0094:
            boolean r12 = org.apache.xmlbeans.GDate.isDigit(r9)
            if (r12 != 0) goto L_0x012a
            r12 = 46
            r13 = 83
            if (r9 != r12) goto L_0x00c9
            r12 = r4
        L_0x00a1:
            int r12 = r12 + r5
            if (r12 >= r2) goto L_0x00ae
            char r9 = r1.charAt(r12)
            boolean r14 = org.apache.xmlbeans.GDate.isDigit(r9)
            if (r14 != 0) goto L_0x00a1
        L_0x00ae:
            java.math.BigDecimal r14 = new java.math.BigDecimal
            java.lang.CharSequence r4 = r1.subSequence(r4, r12)
            java.lang.String r4 = r4.toString()
            r14.<init>(r4)
            r0._fs = r14
            if (r12 >= r2) goto L_0x00c3
            if (r9 != r13) goto L_0x00c3
            r4 = r12
            goto L_0x00c9
        L_0x00c3:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            r0.<init>(r10)
            throw r0
        L_0x00c9:
            r10 = 77
            r12 = 5
            r14 = 4
            r15 = 2
            java.lang.String r3 = "time in duration must follow T"
            if (r6 == 0) goto L_0x00dd
            if (r6 == r5) goto L_0x00e5
            if (r6 == r15) goto L_0x00eb
            if (r6 == r11) goto L_0x00f3
            if (r6 == r14) goto L_0x0103
            if (r6 != r12) goto L_0x0122
            goto L_0x0111
        L_0x00dd:
            r6 = 89
            if (r9 != r6) goto L_0x00e5
            r0._CY = r8
            r6 = r5
            goto L_0x0119
        L_0x00e5:
            if (r9 != r10) goto L_0x00eb
            r0._M = r8
            r6 = r15
            goto L_0x0119
        L_0x00eb:
            r6 = 68
            if (r9 != r6) goto L_0x00f3
            r0._D = r8
            r6 = r11
            goto L_0x0119
        L_0x00f3:
            r6 = 72
            if (r9 != r6) goto L_0x0103
            if (r7 == 0) goto L_0x00fd
            r0._h = r8
            r6 = r14
            goto L_0x0119
        L_0x00fd:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            r0.<init>(r3)
            throw r0
        L_0x0103:
            if (r9 != r10) goto L_0x0111
            if (r7 == 0) goto L_0x010b
            r0._m = r8
            r6 = r12
            goto L_0x0119
        L_0x010b:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            r0.<init>(r3)
            throw r0
        L_0x0111:
            if (r9 != r13) goto L_0x0122
            if (r7 == 0) goto L_0x011c
            r0._s = r8
            r3 = 6
            r6 = r3
        L_0x0119:
            int r4 = r4 + r5
            goto L_0x004f
        L_0x011c:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            r0.<init>(r3)
            throw r0
        L_0x0122:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.String r1 = "duration must specify Y M D T H M S in order"
            r0.<init>(r1)
            throw r0
        L_0x012a:
            int r8 = r8 * 10
            int r3 = org.apache.xmlbeans.GDate.digitVal(r9)
            int r8 = r8 + r3
            goto L_0x008b
        L_0x0133:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = "illegal duration at char["
            r1.<init>(r2)
            java.lang.StringBuilder r1 = r1.append(r4)
            java.lang.String r2 = "]: '"
            java.lang.StringBuilder r1 = r1.append(r2)
            java.lang.StringBuilder r1 = r1.append(r8)
            java.lang.String r2 = "'"
            java.lang.StringBuilder r1 = r1.append(r2)
            java.lang.String r1 = r1.toString()
            r0.<init>(r1)
            throw r0
        L_0x0158:
            if (r6 == 0) goto L_0x015b
            return
        L_0x015b:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            java.lang.String r3 = "duration must contain at least one number and its designator: "
            r2.<init>(r3)
            java.lang.StringBuilder r1 = r2.append(r1)
            java.lang.String r1 = r1.toString()
            r0.<init>(r1)
            throw r0
        L_0x0170:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.String r1 = "duration must begin with P"
            r0.<init>(r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.xmlbeans.GDuration.<init>(java.lang.CharSequence):void");
    }

    public GDuration(int i, int i2, int i3, int i4, int i5, int i6, int i7, BigDecimal bigDecimal) {
        if (i == 1 || i == -1) {
            this._sign = i;
            this._CY = i2;
            this._M = i3;
            this._D = i4;
            this._h = i5;
            this._m = i6;
            this._s = i7;
            this._fs = bigDecimal == null ? GDate._zero : bigDecimal;
            return;
        }
        throw new IllegalArgumentException();
    }

    public GDuration(GDurationSpecification gDurationSpecification) {
        this._sign = gDurationSpecification.getSign();
        this._CY = gDurationSpecification.getYear();
        this._M = gDurationSpecification.getMonth();
        this._D = gDurationSpecification.getDay();
        this._h = gDurationSpecification.getHour();
        this._m = gDurationSpecification.getMinute();
        this._s = gDurationSpecification.getSecond();
        this._fs = gDurationSpecification.getFraction();
    }

    public Object clone() {
        return new GDuration((GDurationSpecification) this);
    }

    public final int getSign() {
        return this._sign;
    }

    public final int getYear() {
        return this._CY;
    }

    public final int getMonth() {
        return this._M;
    }

    public final int getDay() {
        return this._D;
    }

    public final int getHour() {
        return this._h;
    }

    public final int getMinute() {
        return this._m;
    }

    public final int getSecond() {
        return this._s;
    }

    public BigDecimal getFraction() {
        return this._fs;
    }

    public boolean isValid() {
        return GDurationBuilder.isValidDuration(this);
    }

    public final int compareToGDuration(GDurationSpecification gDurationSpecification) {
        return GDurationBuilder.compareDurations(this, gDurationSpecification);
    }

    public String toString() {
        return GDurationBuilder.formatDuration(this);
    }

    public GDuration add(GDurationSpecification gDurationSpecification) {
        return _add(gDurationSpecification, this._sign * gDurationSpecification.getSign());
    }

    public GDuration subtract(GDurationSpecification gDurationSpecification) {
        return _add(gDurationSpecification, (-this._sign) * gDurationSpecification.getSign());
    }

    private GDuration _add(GDurationSpecification gDurationSpecification, int i) {
        BigDecimal bigDecimal;
        GDuration gDuration = new GDuration((GDurationSpecification) this);
        gDuration._CY += gDurationSpecification.getYear() * i;
        gDuration._M += gDurationSpecification.getMonth() * i;
        gDuration._D += gDurationSpecification.getDay() * i;
        gDuration._h += gDurationSpecification.getHour() * i;
        gDuration._m += gDurationSpecification.getMinute() * i;
        gDuration._s += gDurationSpecification.getSecond() * i;
        if (gDurationSpecification.getFraction().signum() == 0) {
            return gDuration;
        }
        if (gDuration._fs.signum() == 0 && i == 1) {
            gDuration._fs = gDurationSpecification.getFraction();
        } else {
            if (i > 0) {
                bigDecimal = gDuration._fs.add(gDurationSpecification.getFraction());
            } else {
                bigDecimal = gDuration._fs.subtract(gDurationSpecification.getFraction());
            }
            gDuration._fs = bigDecimal;
        }
        return gDuration;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof GDuration)) {
            return false;
        }
        GDuration gDuration = (GDuration) obj;
        if (this._sign == gDuration.getSign() && this._CY == gDuration.getYear() && this._M == gDuration.getMonth() && this._D == gDuration.getDay() && this._h == gDuration.getHour() && this._m == gDuration.getMinute() && this._s == gDuration.getSecond() && this._fs.equals(gDuration.getFraction())) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return this._s + (this._m * 67) + (this._h * 3607) + (this._D * 86407) + (this._M * 2678407) + (this._CY * 32140807) + (this._sign * 11917049);
    }
}
