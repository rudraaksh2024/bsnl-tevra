package org.apache.xmlbeans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;

public class GDurationBuilder implements GDurationSpecification, Serializable {
    private static final GDate[] _compDate = {new GDate(1696, 9, 1, 0, 0, 0, (BigDecimal) null, 0, 0, 0), new GDate(1697, 2, 1, 0, 0, 0, (BigDecimal) null, 0, 0, 0), new GDate(1903, 3, 1, 0, 0, 0, (BigDecimal) null, 0, 0, 0), new GDate(1903, 7, 1, 0, 0, 0, (BigDecimal) null, 0, 0, 0)};
    private static final long serialVersionUID = 1;
    private int _CY;
    private int _D;
    private int _M;
    private BigDecimal _fs;
    private int _h;
    private int _m;
    private int _s;
    private int _sign;

    private static int _mod(long j, int i, long j2) {
        return (int) (j - (j2 * ((long) i)));
    }

    public final boolean isImmutable() {
        return true;
    }

    public GDurationBuilder() {
        this._sign = 1;
        this._fs = GDate._zero;
    }

    public GDurationBuilder(String str) {
        this((GDurationSpecification) new GDuration((CharSequence) str));
    }

    public GDurationBuilder(int i, int i2, int i3, int i4, int i5, int i6, int i7, BigDecimal bigDecimal) {
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

    public GDurationBuilder(GDurationSpecification gDurationSpecification) {
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
        return new GDurationBuilder((GDurationSpecification) this);
    }

    public GDuration toGDuration() {
        return new GDuration((GDurationSpecification) this);
    }

    public void addGDuration(GDurationSpecification gDurationSpecification) {
        _add(gDurationSpecification, this._sign * gDurationSpecification.getSign());
    }

    public void subtractGDuration(GDurationSpecification gDurationSpecification) {
        _add(gDurationSpecification, (-this._sign) * gDurationSpecification.getSign());
    }

    private void _add(GDurationSpecification gDurationSpecification, int i) {
        BigDecimal bigDecimal;
        this._CY += gDurationSpecification.getYear() * i;
        this._M += gDurationSpecification.getMonth() * i;
        this._D += gDurationSpecification.getDay() * i;
        this._h += gDurationSpecification.getHour() * i;
        this._m += gDurationSpecification.getMinute() * i;
        this._s += gDurationSpecification.getSecond() * i;
        if (gDurationSpecification.getFraction().signum() != 0) {
            if (this._fs.signum() == 0 && i == 1) {
                this._fs = gDurationSpecification.getFraction();
                return;
            }
            if (i > 0) {
                bigDecimal = this._fs.add(gDurationSpecification.getFraction());
            } else {
                bigDecimal = this._fs.subtract(gDurationSpecification.getFraction());
            }
            this._fs = bigDecimal;
        }
    }

    public final void setSign(int i) {
        if (i == 1 || i == -1) {
            this._sign = i;
            return;
        }
        throw new IllegalArgumentException();
    }

    public void setYear(int i) {
        this._CY = i;
    }

    public void setMonth(int i) {
        this._M = i;
    }

    public void setDay(int i) {
        this._D = i;
    }

    public void setHour(int i) {
        this._h = i;
    }

    public void setMinute(int i) {
        this._m = i;
    }

    public void setSecond(int i) {
        this._s = i;
    }

    public void setFraction(BigDecimal bigDecimal) {
        if (bigDecimal == null) {
            bigDecimal = GDate._zero;
        }
        this._fs = bigDecimal;
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
        return isValidDuration(this);
    }

    public void normalize() {
        _normalizeImpl(true);
    }

    private static long _fQuotient(long j, int i) {
        boolean z = true;
        boolean z2 = j < 0;
        if (i >= 0) {
            z = false;
        }
        if (z2 == z) {
            return j / ((long) i);
        }
        long j2 = (long) i;
        return -(((j2 - j) - 1) / j2);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:51:0x00cc, code lost:
        r7 = r6._CY;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void _normalizeImpl(boolean r7) {
        /*
            r6 = this;
            int r0 = r6._M
            if (r0 < 0) goto L_0x0008
            r1 = 11
            if (r0 <= r1) goto L_0x001c
        L_0x0008:
            long r0 = (long) r0
            r2 = 12
            long r3 = _fQuotient(r0, r2)
            int r0 = _mod(r0, r2, r3)
            r6._M = r0
            int r0 = r6._CY
            long r0 = (long) r0
            long r0 = r0 + r3
            int r0 = (int) r0
            r6._CY = r0
        L_0x001c:
            java.math.BigDecimal r0 = r6._fs
            r1 = 0
            r2 = 0
            if (r0 == 0) goto L_0x0049
            int r0 = r0.signum()
            if (r0 < 0) goto L_0x0033
            java.math.BigDecimal r0 = r6._fs
            java.math.BigDecimal r4 = org.apache.xmlbeans.GDate._one
            int r0 = r0.compareTo(r4)
            if (r0 < 0) goto L_0x0049
        L_0x0033:
            java.math.BigDecimal r0 = r6._fs
            java.math.RoundingMode r4 = java.math.RoundingMode.FLOOR
            java.math.BigDecimal r0 = r0.setScale(r1, r4)
            java.math.BigDecimal r4 = r6._fs
            java.math.BigDecimal r4 = r4.subtract(r0)
            r6._fs = r4
            int r0 = r0.intValue()
            long r4 = (long) r0
            goto L_0x004a
        L_0x0049:
            r4 = r2
        L_0x004a:
            int r0 = (r4 > r2 ? 1 : (r4 == r2 ? 0 : -1))
            if (r0 != 0) goto L_0x0066
            int r0 = r6._s
            if (r0 < 0) goto L_0x0066
            r2 = 59
            if (r0 > r2) goto L_0x0066
            int r0 = r6._m
            if (r0 < 0) goto L_0x0066
            r2 = 50
            if (r0 > r2) goto L_0x0066
            int r0 = r6._h
            if (r0 < 0) goto L_0x0066
            r2 = 23
            if (r0 <= r2) goto L_0x009b
        L_0x0066:
            int r0 = r6._s
            long r2 = (long) r0
            long r2 = r2 + r4
            r0 = 60
            long r4 = _fQuotient(r2, r0)
            int r2 = _mod(r2, r0, r4)
            r6._s = r2
            int r2 = r6._m
            long r2 = (long) r2
            long r2 = r2 + r4
            long r4 = _fQuotient(r2, r0)
            int r0 = _mod(r2, r0, r4)
            r6._m = r0
            int r0 = r6._h
            long r2 = (long) r0
            long r2 = r2 + r4
            r0 = 24
            long r4 = _fQuotient(r2, r0)
            int r0 = _mod(r2, r0, r4)
            r6._h = r0
            int r0 = r6._D
            long r2 = (long) r0
            long r2 = r2 + r4
            int r0 = (int) r2
            r6._D = r0
        L_0x009b:
            int r0 = r6._CY
            r2 = 1
            if (r0 != 0) goto L_0x00c0
            int r0 = r6._M
            if (r0 != 0) goto L_0x00c0
            int r0 = r6._D
            if (r0 != 0) goto L_0x00c0
            int r0 = r6._h
            if (r0 != 0) goto L_0x00c0
            int r0 = r6._m
            if (r0 != 0) goto L_0x00c0
            int r0 = r6._s
            if (r0 != 0) goto L_0x00c0
            java.math.BigDecimal r0 = r6._fs
            if (r0 == 0) goto L_0x00be
            int r0 = r0.signum()
            if (r0 != 0) goto L_0x00c0
        L_0x00be:
            r6._sign = r2
        L_0x00c0:
            if (r7 == 0) goto L_0x0120
            int r7 = r6._D
            if (r7 < 0) goto L_0x00ca
            int r0 = r6._CY
            if (r0 >= 0) goto L_0x0120
        L_0x00ca:
            if (r7 > 0) goto L_0x00da
            int r7 = r6._CY
            if (r7 < 0) goto L_0x00d6
            if (r7 != 0) goto L_0x00da
            int r7 = r6._M
            if (r7 != 0) goto L_0x00da
        L_0x00d6:
            int r7 = r6._sign
            int r7 = -r7
            goto L_0x00de
        L_0x00da:
            int r7 = r6._getTotalSignSlowly()
        L_0x00de:
            r0 = 2
            if (r7 != r0) goto L_0x00eb
            int r7 = r6._CY
            if (r7 >= 0) goto L_0x00e9
            int r7 = r6._sign
            int r7 = -r7
            goto L_0x00eb
        L_0x00e9:
            int r7 = r6._sign
        L_0x00eb:
            if (r7 != 0) goto L_0x00ee
            goto L_0x00ef
        L_0x00ee:
            r2 = r7
        L_0x00ef:
            int r7 = r6._sign
            if (r2 == r7) goto L_0x011d
            r6._sign = r2
            int r7 = r6._CY
            int r7 = -r7
            r6._CY = r7
            int r7 = r6._M
            int r7 = -r7
            r6._M = r7
            int r7 = r6._D
            int r7 = -r7
            r6._D = r7
            int r7 = r6._h
            int r7 = -r7
            r6._h = r7
            int r7 = r6._m
            int r7 = -r7
            r6._m = r7
            int r7 = r6._s
            int r7 = -r7
            r6._s = r7
            java.math.BigDecimal r7 = r6._fs
            if (r7 == 0) goto L_0x011d
            java.math.BigDecimal r7 = r7.negate()
            r6._fs = r7
        L_0x011d:
            r6._normalizeImpl(r1)
        L_0x0120:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.xmlbeans.GDurationBuilder._normalizeImpl(boolean):void");
    }

    static boolean isValidDuration(GDurationSpecification gDurationSpecification) {
        if ((gDurationSpecification.getSign() == 1 || gDurationSpecification.getSign() == -1) && gDurationSpecification.getYear() >= 0 && gDurationSpecification.getMonth() >= 0 && gDurationSpecification.getDay() >= 0 && gDurationSpecification.getHour() >= 0 && gDurationSpecification.getMinute() >= 0 && gDurationSpecification.getSecond() >= 0 && gDurationSpecification.getFraction().signum() >= 0) {
            return true;
        }
        return false;
    }

    public final int compareToGDuration(GDurationSpecification gDurationSpecification) {
        return compareDurations(this, gDurationSpecification);
    }

    public String toString() {
        return formatDuration(this);
    }

    static int compareDurations(GDurationSpecification gDurationSpecification, GDurationSpecification gDurationSpecification2) {
        if (gDurationSpecification.getFraction().signum() == 0 && gDurationSpecification2.getFraction().signum() == 0) {
            int sign = gDurationSpecification.getSign();
            int sign2 = gDurationSpecification2.getSign();
            long j = (long) sign;
            long year = ((((long) gDurationSpecification.getYear()) * 12) + ((long) gDurationSpecification.getMonth())) * j;
            long j2 = (long) sign2;
            long year2 = ((((long) gDurationSpecification2.getYear()) * 12) + ((long) gDurationSpecification2.getMonth())) * j2;
            long day = j * ((((((((long) gDurationSpecification.getDay()) * 24) + ((long) gDurationSpecification.getHour())) * 60) + ((long) gDurationSpecification.getMinute())) * 60) + ((long) gDurationSpecification.getSecond()));
            long day2 = j2 * ((((((((long) gDurationSpecification2.getDay()) * 24) + ((long) gDurationSpecification2.getHour())) * 60) + ((long) gDurationSpecification2.getMinute())) * 60) + ((long) gDurationSpecification2.getSecond()));
            int i = (year > year2 ? 1 : (year == year2 ? 0 : -1));
            if (i == 0) {
                int i2 = (day > day2 ? 1 : (day == day2 ? 0 : -1));
                if (i2 == 0) {
                    return 0;
                }
                return i2 < 0 ? -1 : 1;
            } else if (i < 0 && day - day2 < 2419200) {
                return -1;
            } else {
                if (i > 0 && day2 - day < 2419200) {
                    return 1;
                }
            }
        }
        GDurationBuilder gDurationBuilder = new GDurationBuilder(gDurationSpecification);
        gDurationBuilder.subtractGDuration(gDurationSpecification2);
        return gDurationBuilder._getTotalSignSlowly();
    }

    private int _getTotalSignSlowly() {
        GDateBuilder gDateBuilder = new GDateBuilder();
        int i = 0;
        int i2 = 0;
        int i3 = 0;
        for (GDate gDate : _compDate) {
            gDateBuilder.setGDate(gDate);
            gDateBuilder.addGDuration(this);
            int compareToGDate = gDateBuilder.compareToGDate(gDate);
            if (compareToGDate == -1) {
                i2++;
            } else if (compareToGDate == 0) {
                i3++;
            } else if (compareToGDate == 1) {
                i++;
            }
        }
        GDate[] gDateArr = _compDate;
        if (i == gDateArr.length) {
            return 1;
        }
        if (i2 == gDateArr.length) {
            return -1;
        }
        if (i3 == gDateArr.length) {
            return 0;
        }
        return 2;
    }

    static String formatDuration(GDurationSpecification gDurationSpecification) {
        StringBuilder sb = new StringBuilder(30);
        if (gDurationSpecification.getSign() < 0) {
            sb.append('-');
        }
        sb.append('P');
        if (gDurationSpecification.getYear() != 0) {
            sb.append(gDurationSpecification.getYear());
            sb.append('Y');
        }
        if (gDurationSpecification.getMonth() != 0) {
            sb.append(gDurationSpecification.getMonth());
            sb.append('M');
        }
        if (gDurationSpecification.getDay() != 0) {
            sb.append(gDurationSpecification.getDay());
            sb.append('D');
        }
        if (!(gDurationSpecification.getHour() == 0 && gDurationSpecification.getMinute() == 0 && gDurationSpecification.getSecond() == 0 && gDurationSpecification.getFraction().signum() == 0)) {
            sb.append('T');
        }
        if (gDurationSpecification.getHour() != 0) {
            sb.append(gDurationSpecification.getHour());
            sb.append('H');
        }
        if (gDurationSpecification.getMinute() != 0) {
            sb.append(gDurationSpecification.getMinute());
            sb.append('M');
        }
        if (gDurationSpecification.getFraction().signum() != 0) {
            BigDecimal fraction = gDurationSpecification.getFraction();
            if (gDurationSpecification.getSecond() != 0) {
                fraction = fraction.add(BigDecimal.valueOf((long) gDurationSpecification.getSecond()));
            }
            sb.append(stripTrailingZeros(toPlainString(fraction)));
            sb.append('S');
        } else if (gDurationSpecification.getSecond() != 0) {
            sb.append(gDurationSpecification.getSecond());
            sb.append('S');
        } else if (sb.length() <= 2) {
            sb.append("T0S");
        }
        return sb.toString();
    }

    public static String toPlainString(BigDecimal bigDecimal) {
        BigInteger unscaledValue = bigDecimal.unscaledValue();
        int scale = bigDecimal.scale();
        String bigInteger = unscaledValue.toString();
        if (scale == 0) {
            return bigInteger;
        }
        int i = 0;
        int i2 = bigInteger.charAt(0) == '-' ? 1 : 0;
        int length = (bigInteger.length() - scale) - i2;
        int length2 = bigInteger.length() + 2;
        if (length <= 0) {
            i = (-length) + 1;
        }
        StringBuilder sb = new StringBuilder(length2 + i);
        if (length <= 0) {
            if (i2 != 0) {
                sb.append('-');
            }
            sb.append("0.");
            while (length < 0) {
                sb.append('0');
                length++;
            }
            sb.append(bigInteger.substring(i2));
        } else if (length < bigInteger.length()) {
            sb.append(bigInteger);
            sb.insert(length + i2, '.');
        } else {
            sb.append(bigInteger);
            if (!unscaledValue.equals(BigInteger.ZERO)) {
                for (int length3 = bigInteger.length(); length3 < length; length3++) {
                    sb.append('0');
                }
            }
        }
        return sb.toString();
    }

    public static String stripTrailingZeros(String str) {
        boolean z;
        int length = str.length() - 1;
        int i = length;
        while (length >= 0 && str.charAt(length) == '0') {
            length--;
            i--;
        }
        while (true) {
            if (length < 0) {
                z = false;
                break;
            } else if (str.charAt(length) == 'E') {
                return str;
            } else {
                if (str.charAt(length) == '.') {
                    z = true;
                    break;
                }
                length--;
            }
        }
        return z ? str.substring(0, i + 1) : str;
    }
}
