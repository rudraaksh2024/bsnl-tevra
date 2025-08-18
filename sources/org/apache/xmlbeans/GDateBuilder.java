package org.apache.xmlbeans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
import org.apache.logging.log4j.message.ParameterizedMessage;
import org.apache.logging.log4j.util.ProcessIdUtil;
import org.apache.poi.common.usermodel.fonts.FontHeader;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.xddf.usermodel.Angles;

public final class GDateBuilder implements GDateSpecification, Serializable {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    static final BigInteger TEN = BigInteger.valueOf(10);
    private static final long serialVersionUID = 1;
    private int _CY;
    private int _D;
    private int _M;
    private int _bits;
    private BigDecimal _fs;
    private int _h;
    private int _m;
    private int _s;
    private int _tzh;
    private int _tzm;
    private int _tzsign;

    private static int _maxDayInMonth(int i) {
        if (i == 4 || i == 6 || i == 9 || i == 11) {
            return 30;
        }
        return i == 2 ? 29 : 31;
    }

    private static int _mod(long j, int i, long j2) {
        return (int) (j - (j2 * ((long) i)));
    }

    static int btcForFlags(int i) {
        int i2 = i & 30;
        if (i2 == 2) {
            return 18;
        }
        if (i2 == 4) {
            return 21;
        }
        if (i2 == 6) {
            return 17;
        }
        if (i2 == 8) {
            return 20;
        }
        if (i2 == 12) {
            return 19;
        }
        if (i2 == 14) {
            return 16;
        }
        if (i2 != 16) {
            return i2 != 30 ? 0 : 14;
        }
        return 15;
    }

    public boolean isImmutable() {
        return false;
    }

    public GDateBuilder() {
    }

    public Object clone() {
        return new GDateBuilder((GDateSpecification) this);
    }

    public GDate toGDate() {
        return new GDate((GDateSpecification) this);
    }

    public GDateBuilder(GDateSpecification gDateSpecification) {
        if (gDateSpecification.hasTimeZone()) {
            setTimeZone(gDateSpecification.getTimeZoneSign(), gDateSpecification.getTimeZoneHour(), gDateSpecification.getTimeZoneMinute());
        }
        if (gDateSpecification.hasTime()) {
            setTime(gDateSpecification.getHour(), gDateSpecification.getMinute(), gDateSpecification.getSecond(), gDateSpecification.getFraction());
        }
        if (gDateSpecification.hasDay()) {
            setDay(gDateSpecification.getDay());
        }
        if (gDateSpecification.hasMonth()) {
            setMonth(gDateSpecification.getMonth());
        }
        if (gDateSpecification.hasYear()) {
            setYear(gDateSpecification.getYear());
        }
    }

    public GDateBuilder(CharSequence charSequence) {
        this((GDateSpecification) new GDate(charSequence));
    }

    public GDateBuilder(Calendar calendar) {
        this((GDateSpecification) new GDate(calendar));
    }

    public GDateBuilder(int i, int i2, int i3, int i4, int i5, int i6, BigDecimal bigDecimal) {
        this._bits = 30;
        if (i != 0) {
            this._CY = i <= 0 ? i + 1 : i;
            this._M = i2;
            this._D = i3;
            this._h = i4;
            this._m = i5;
            this._s = i6;
            this._fs = bigDecimal == null ? GDate._zero : bigDecimal;
            if (!isValid()) {
                throw new IllegalArgumentException();
            }
            return;
        }
        throw new IllegalArgumentException();
    }

    public GDateBuilder(int i, int i2, int i3, int i4, int i5, int i6, BigDecimal bigDecimal, int i7, int i8, int i9) {
        this._bits = 31;
        if (i != 0) {
            this._CY = i <= 0 ? i + 1 : i;
            this._M = i2;
            this._D = i3;
            this._h = i4;
            this._m = i5;
            this._s = i6;
            this._fs = bigDecimal == null ? GDate._zero : bigDecimal;
            this._tzsign = i7;
            this._tzh = i8;
            this._tzm = i9;
            if (!isValid()) {
                throw new IllegalArgumentException();
            }
            return;
        }
        throw new IllegalArgumentException();
    }

    public GDateBuilder(Date date) {
        setDate(date);
    }

    public int getFlags() {
        return this._bits;
    }

    public final boolean hasTimeZone() {
        return (this._bits & 1) != 0;
    }

    public final boolean hasYear() {
        return (this._bits & 2) != 0;
    }

    public final boolean hasMonth() {
        return (this._bits & 4) != 0;
    }

    public final boolean hasDay() {
        return (this._bits & 8) != 0;
    }

    public final boolean hasTime() {
        return (this._bits & 16) != 0;
    }

    public final boolean hasDate() {
        return (this._bits & 14) == 14;
    }

    public final int getYear() {
        int i = this._CY;
        return i > 0 ? i : i - 1;
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

    public final BigDecimal getFraction() {
        return this._fs;
    }

    public final int getMillisecond() {
        if (this._fs == null || GDate._zero.equals(this._fs)) {
            return 0;
        }
        return this._fs.setScale(3, RoundingMode.HALF_UP).unscaledValue().intValue();
    }

    public final int getTimeZoneSign() {
        return this._tzsign;
    }

    public final int getTimeZoneHour() {
        return this._tzh;
    }

    public final int getTimeZoneMinute() {
        return this._tzm;
    }

    public void setYear(int i) {
        if (i < -292275295 || i > 292277265) {
            throw new IllegalArgumentException("year out of range");
        } else if (i != 0) {
            this._bits |= 2;
            if (i <= 0) {
                i++;
            }
            this._CY = i;
        } else {
            throw new IllegalArgumentException("year cannot be 0");
        }
    }

    public void setMonth(int i) {
        if (i < 1 || i > 12) {
            throw new IllegalArgumentException("month out of range");
        }
        this._bits |= 4;
        this._M = i;
    }

    public void setDay(int i) {
        if (i < 1 || i > 31) {
            throw new IllegalArgumentException("day out of range");
        }
        this._bits |= 8;
        this._D = i;
    }

    public void setTime(int i, int i2, int i3, BigDecimal bigDecimal) {
        if (i < 0 || i > 24) {
            throw new IllegalArgumentException("hour out of range");
        } else if (i2 < 0 || i2 > 59) {
            throw new IllegalArgumentException("minute out of range");
        } else if (i3 < 0 || i3 > 59) {
            throw new IllegalArgumentException("second out of range");
        } else if (bigDecimal != null && (bigDecimal.signum() < 0 || GDate._one.compareTo(bigDecimal) <= 0)) {
            throw new IllegalArgumentException("fraction out of range");
        } else if (i != 24 || (i2 == 0 && i3 == 0 && (bigDecimal == null || GDate._zero.compareTo(bigDecimal) == 0))) {
            this._bits |= 16;
            this._h = i;
            this._m = i2;
            this._s = i3;
            if (bigDecimal == null) {
                bigDecimal = GDate._zero;
            }
            this._fs = bigDecimal;
        } else {
            throw new IllegalArgumentException("when hour is 24, min sec and fracton must be 0");
        }
    }

    public void setTimeZone(int i, int i2, int i3) {
        if (!(i == 0 && i2 == 0 && i3 == 0) && (!(i == -1 || i == 1) || i2 < 0 || i3 < 0 || (!(i2 == 14 && i3 == 0) && (i2 >= 14 || i3 >= 60)))) {
            throw new IllegalArgumentException("time zone out of range (-14:00 to +14:00). (" + (i < 0 ? ProcessIdUtil.DEFAULT_PROCESSID : "+") + i2 + ParameterizedMessage.ERROR_MSG_SEPARATOR + i3 + ")");
        }
        this._bits = 1 | this._bits;
        this._tzsign = i;
        this._tzh = i2;
        this._tzm = i3;
    }

    public void setTimeZone(int i) {
        if (i < -840 || i > 840) {
            throw new IllegalArgumentException("time zone out of range (-840 to 840 minutes). (" + i + ")");
        }
        int compare = Integer.compare(i, 0);
        int i2 = i * compare;
        int i3 = i2 / 60;
        setTimeZone(compare, i3, i2 - (i3 * 60));
    }

    public void clearYear() {
        this._bits &= -3;
        this._CY = 0;
    }

    public void clearMonth() {
        this._bits &= -5;
        this._M = 0;
    }

    public void clearDay() {
        this._bits &= -9;
        this._D = 0;
    }

    public void clearTime() {
        this._bits &= -17;
        this._h = 0;
        this._m = 0;
        this._s = 0;
        this._fs = null;
    }

    public void clearTimeZone() {
        this._bits &= -2;
        this._tzsign = 0;
        this._tzh = 0;
        this._tzm = 0;
    }

    public boolean isValid() {
        return isValidGDate(this);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:29:0x0068, code lost:
        if (r0 > _maxDayInMonthFor(r3, r5.getMonth())) goto L_0x0079;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x0077, code lost:
        if (r5.getDay() > _maxDayInMonth(r5.getMonth())) goto L_0x0079;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static boolean isValidGDate(org.apache.xmlbeans.GDateSpecification r5) {
        /*
            boolean r0 = r5.hasYear()
            r1 = 0
            if (r0 == 0) goto L_0x000e
            int r0 = r5.getYear()
            if (r0 != 0) goto L_0x000e
            return r1
        L_0x000e:
            boolean r0 = r5.hasMonth()
            r2 = 1
            if (r0 == 0) goto L_0x0024
            int r0 = r5.getMonth()
            if (r0 < r2) goto L_0x0023
            int r0 = r5.getMonth()
            r3 = 12
            if (r0 <= r3) goto L_0x0024
        L_0x0023:
            return r1
        L_0x0024:
            boolean r0 = r5.hasDay()
            if (r0 == 0) goto L_0x007a
            int r0 = r5.getDay()
            if (r0 < r2) goto L_0x0079
            int r0 = r5.getDay()
            r3 = 31
            if (r0 > r3) goto L_0x0079
            int r0 = r5.getDay()
            r3 = 28
            if (r0 <= r3) goto L_0x007a
            boolean r0 = r5.hasMonth()
            if (r0 == 0) goto L_0x007a
            boolean r0 = r5.hasYear()
            if (r0 == 0) goto L_0x006b
            int r0 = r5.getDay()
            int r3 = r5.getYear()
            if (r3 <= 0) goto L_0x005b
            int r3 = r5.getYear()
            goto L_0x0060
        L_0x005b:
            int r3 = r5.getYear()
            int r3 = r3 + r2
        L_0x0060:
            int r4 = r5.getMonth()
            int r3 = _maxDayInMonthFor(r3, r4)
            if (r0 <= r3) goto L_0x007a
            goto L_0x0079
        L_0x006b:
            int r0 = r5.getDay()
            int r3 = r5.getMonth()
            int r3 = _maxDayInMonth(r3)
            if (r0 <= r3) goto L_0x007a
        L_0x0079:
            return r1
        L_0x007a:
            boolean r0 = r5.hasTime()
            if (r0 == 0) goto L_0x00df
            int r0 = r5.getHour()
            if (r0 < 0) goto L_0x00be
            int r0 = r5.getHour()
            r3 = 23
            if (r0 > r3) goto L_0x00be
            int r0 = r5.getMinute()
            if (r0 < 0) goto L_0x00be
            int r0 = r5.getMinute()
            r3 = 59
            if (r0 > r3) goto L_0x00be
            int r0 = r5.getSecond()
            if (r0 < 0) goto L_0x00be
            int r0 = r5.getSecond()
            if (r0 > r3) goto L_0x00be
            java.math.BigDecimal r0 = r5.getFraction()
            int r0 = r0.signum()
            if (r0 < 0) goto L_0x00be
            java.math.BigDecimal r0 = r5.getFraction()
            java.math.BigDecimal r3 = org.apache.xmlbeans.GDate._one
            int r0 = r0.compareTo(r3)
            if (r0 < 0) goto L_0x00df
        L_0x00be:
            int r0 = r5.getHour()
            r3 = 24
            if (r0 != r3) goto L_0x00de
            int r0 = r5.getMinute()
            if (r0 != 0) goto L_0x00de
            int r0 = r5.getSecond()
            if (r0 != 0) goto L_0x00de
            java.math.BigDecimal r0 = r5.getFraction()
            java.math.BigDecimal r3 = org.apache.xmlbeans.GDate._zero
            int r0 = r0.compareTo(r3)
            if (r0 == 0) goto L_0x00df
        L_0x00de:
            return r1
        L_0x00df:
            boolean r0 = r5.hasTimeZone()
            if (r0 == 0) goto L_0x012d
            int r0 = r5.getTimeZoneSign()
            if (r0 != 0) goto L_0x00f7
            int r0 = r5.getTimeZoneHour()
            if (r0 != 0) goto L_0x00f7
            int r0 = r5.getTimeZoneMinute()
            if (r0 == 0) goto L_0x012d
        L_0x00f7:
            int r0 = r5.getTimeZoneSign()
            r3 = -1
            if (r0 == r3) goto L_0x0104
            int r0 = r5.getTimeZoneSign()
            if (r0 != r2) goto L_0x012c
        L_0x0104:
            int r0 = r5.getTimeZoneHour()
            if (r0 < 0) goto L_0x012c
            int r0 = r5.getTimeZoneMinute()
            if (r0 < 0) goto L_0x012c
            int r0 = r5.getTimeZoneHour()
            r3 = 14
            if (r0 != r3) goto L_0x011e
            int r0 = r5.getTimeZoneMinute()
            if (r0 == 0) goto L_0x012d
        L_0x011e:
            int r0 = r5.getTimeZoneHour()
            if (r0 >= r3) goto L_0x012c
            int r5 = r5.getTimeZoneMinute()
            r0 = 60
            if (r5 < r0) goto L_0x012d
        L_0x012c:
            return r1
        L_0x012d:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.xmlbeans.GDateBuilder.isValidGDate(org.apache.xmlbeans.GDateSpecification):boolean");
    }

    public void normalize() {
        BigDecimal bigDecimal;
        if (hasDay() != hasMonth() || hasDay() != hasYear() || !hasTimeZone() || !hasTime()) {
            _normalizeTimeAndDate();
        } else {
            normalizeToTimeZone(0, 0, 0);
        }
        if (hasTime() && (bigDecimal = this._fs) != null && bigDecimal.scale() > 0) {
            if (this._fs.signum() == 0) {
                this._fs = GDate._zero;
                return;
            }
            String bigInteger = this._fs.unscaledValue().toString();
            int length = bigInteger.length();
            while (length > 0 && bigInteger.charAt(length - 1) == '0') {
                length--;
            }
            if (length < bigInteger.length()) {
                BigDecimal bigDecimal2 = this._fs;
                this._fs = bigDecimal2.setScale((bigDecimal2.scale() - bigInteger.length()) + length, RoundingMode.UNNECESSARY);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void normalize24h() {
        if (hasTime() && getHour() == 24) {
            _normalizeTimeAndDate();
        }
    }

    private void _normalizeTimeAndDate() {
        long _normalizeTime = hasTime() ? _normalizeTime() : 0;
        if (hasDay()) {
            this._D = (int) (((long) this._D) + _normalizeTime);
        }
        if (hasDate()) {
            _normalizeDate();
        } else if (hasMonth()) {
            int i = this._M;
            if (i < 1 || i > 12) {
                long j = (long) i;
                this._M = _modulo(j, 1, 13);
                if (hasYear()) {
                    this._CY += (int) _fQuotient(j, 1, 13);
                }
            }
        }
    }

    public void normalizeToTimeZone(int i, int i2, int i3) {
        if (!(i == 0 && i2 == 0 && i3 == 0) && (!(i == -1 || i == 1) || i2 < 0 || i3 < 0 || (!(i2 == 14 && i3 == 0) && (i2 >= 14 || i3 >= 60)))) {
            throw new IllegalArgumentException("time zone must be between -14:00 and +14:00");
        } else if (!hasTimeZone() || !hasTime()) {
            throw new IllegalStateException("cannot normalize time zone without both time and timezone");
        } else if (hasDay() == hasMonth() && hasDay() == hasYear()) {
            int i4 = this._tzsign;
            this._tzsign = i;
            this._tzh = i2;
            this._tzm = i3;
            addDuration(1, 0, 0, 0, (i * i2) - (this._tzh * i4), (i * i3) - (i4 * this._tzm), 0, (BigDecimal) null);
        } else {
            throw new IllegalStateException("cannot do date math without a complete date");
        }
    }

    public void normalizeToTimeZone(int i) {
        if (i < -840 || i > 840) {
            throw new IllegalArgumentException("time zone out of range (-840 to 840 minutes). (" + i + ")");
        }
        int compare = Integer.compare(i, 0);
        int i2 = i * compare;
        int i3 = i2 / 60;
        normalizeToTimeZone(compare, i3, i2 - (i3 * 60));
    }

    public void addGDuration(GDurationSpecification gDurationSpecification) {
        addDuration(gDurationSpecification.getSign(), gDurationSpecification.getYear(), gDurationSpecification.getMonth(), gDurationSpecification.getDay(), gDurationSpecification.getHour(), gDurationSpecification.getMinute(), gDurationSpecification.getSecond(), gDurationSpecification.getFraction());
    }

    public void subtractGDuration(GDurationSpecification gDurationSpecification) {
        addDuration(-gDurationSpecification.getSign(), gDurationSpecification.getYear(), gDurationSpecification.getMonth(), gDurationSpecification.getDay(), gDurationSpecification.getHour(), gDurationSpecification.getMinute(), gDurationSpecification.getSecond(), gDurationSpecification.getFraction());
    }

    private void _normalizeDate() {
        int i;
        int i2 = this._M;
        if (i2 < 1 || i2 > 12 || (i = this._D) < 1 || i > _maxDayInMonthFor(this._CY, i2)) {
            long j = (long) this._M;
            this._M = _modulo(j, 1, 13);
            this._CY += (int) _fQuotient(j, 1, 13);
            this._D = 1;
            setJulianDate(getJulianDate() + (this._D - 1));
        }
    }

    private long _normalizeTime() {
        long j;
        int i;
        int i2;
        int i3;
        BigDecimal bigDecimal = this._fs;
        if (bigDecimal == null || (bigDecimal.signum() >= 0 && this._fs.compareTo(GDate._one) < 0)) {
            j = 0;
        } else {
            BigDecimal scale = this._fs.setScale(0, RoundingMode.FLOOR);
            this._fs = this._fs.subtract(scale);
            j = scale.longValue();
        }
        if (j == 0 && (i = this._s) >= 0 && i <= 59 && (i2 = this._m) >= 0 && i2 <= 50 && (i3 = this._h) >= 0 && i3 <= 23) {
            return j;
        }
        long j2 = ((long) this._s) + j;
        long _fQuotient = _fQuotient(j2, 60);
        this._s = _mod(j2, 60, _fQuotient);
        long j3 = ((long) this._m) + _fQuotient;
        long _fQuotient2 = _fQuotient(j3, 60);
        this._m = _mod(j3, 60, _fQuotient2);
        long j4 = ((long) this._h) + _fQuotient2;
        long _fQuotient3 = _fQuotient(j4, 24);
        this._h = _mod(j4, 24, _fQuotient3);
        return _fQuotient3;
    }

    public void addDuration(int i, int i2, int i3, int i4, int i5, int i6, int i7, BigDecimal bigDecimal) {
        long j;
        int _maxDayInMonthFor;
        boolean z = false;
        boolean z2 = (i5 == 0 && i6 == 0 && i7 == 0 && (bigDecimal == null || bigDecimal.signum() == 0)) ? false : true;
        if (!z2 || hasTime()) {
            if (hasDay() && (i4 != 0 || z2)) {
                z = true;
            }
            if (!z || hasDate()) {
                if (!(i3 == 0 && i2 == 0)) {
                    if (hasDay()) {
                        _normalizeDate();
                    }
                    long j2 = (long) (this._M + (i3 * i));
                    this._M = _modulo(j2, 1, 13);
                    this._CY = this._CY + (i2 * i) + ((int) _fQuotient(j2, 1, 13));
                    if (hasDay() && this._D > (_maxDayInMonthFor = _maxDayInMonthFor(this._CY, this._M))) {
                        this._D = _maxDayInMonthFor;
                    }
                }
                if (z2) {
                    if (!(bigDecimal == null || bigDecimal.signum() == 0)) {
                        if (this._fs.signum() == 0 && i == 1) {
                            this._fs = bigDecimal;
                        } else {
                            BigDecimal bigDecimal2 = this._fs;
                            this._fs = i == 1 ? bigDecimal2.add(bigDecimal) : bigDecimal2.subtract(bigDecimal);
                        }
                    }
                    this._s += i7 * i;
                    this._m += i6 * i;
                    this._h += i5 * i;
                    j = _normalizeTime();
                } else {
                    j = 0;
                }
                if (z) {
                    this._D = (int) (((long) this._D) + (((long) i) * ((long) i4)) + j);
                    _normalizeDate();
                    return;
                }
                return;
            }
            throw new IllegalStateException("cannot do date math without a complete date");
        }
        throw new IllegalStateException("cannot do time math without a complete time");
    }

    private static int _maxDayInMonthFor(int i, int i2) {
        if (i2 == 4 || i2 == 6 || i2 == 9 || i2 == 11) {
            return 30;
        }
        if (i2 == 2) {
            return _isLeapYear(i) ? 29 : 28;
        }
        return 31;
    }

    public final int getJulianDate() {
        return julianDateForGDate(this);
    }

    public void setJulianDate(int i) {
        if (i >= 0) {
            int i2 = i + 68569;
            int i3 = (i2 * 4) / 146097;
            int i4 = i2 - (((146097 * i3) + 3) / 4);
            int i5 = ((i4 + 1) * 4000) / 1461001;
            this._CY = i5;
            int i6 = (i4 - ((i5 * 1461) / 4)) + 31;
            int i7 = (i6 * 80) / 2447;
            this._M = i7;
            this._D = i6 - ((i7 * 2447) / 80);
            int i8 = i7 / 11;
            this._M = (i7 + 2) - (i8 * 12);
            this._CY = ((i3 - 49) * 100) + i5 + i8;
            this._bits |= 14;
            return;
        }
        throw new IllegalArgumentException("date before year -4713");
    }

    public void setDate(Date date) {
        int i;
        int offset = TimeZone.getDefault().getOffset(date.getTime());
        if (offset < 0) {
            offset = -offset;
            i = -1;
        } else {
            i = 1;
        }
        int i2 = offset / Angles.OOXML_DEGREE;
        int i3 = i2 / 60;
        int i4 = i3 * 60;
        int i5 = i2 - i4;
        setTimeZone(i, i3, i5);
        setTime(0, 0, 0, GDate._zero);
        this._bits |= 14;
        this._CY = 1970;
        this._M = 1;
        this._D = 1;
        addGDuration(new GDuration(1, 0, 0, 0, 0, 0, 0, BigDecimal.valueOf(date.getTime() + ((long) (i * (i4 + i5) * 60 * 1000)), 3)));
        if (this._fs.signum() == 0) {
            this._fs = GDate._zero;
        }
    }

    public void setGDate(GDateSpecification gDateSpecification) {
        this._bits = gDateSpecification.getFlags() & 31;
        int year = gDateSpecification.getYear();
        if (year <= 0) {
            year++;
        }
        this._CY = year;
        this._M = gDateSpecification.getMonth();
        this._D = gDateSpecification.getDay();
        this._h = gDateSpecification.getHour();
        this._m = gDateSpecification.getMinute();
        this._s = gDateSpecification.getSecond();
        this._fs = gDateSpecification.getFraction();
        this._tzsign = gDateSpecification.getTimeZoneSign();
        this._tzh = gDateSpecification.getTimeZoneHour();
        this._tzm = gDateSpecification.getTimeZoneMinute();
    }

    public XmlCalendar getCalendar() {
        return new XmlCalendar((GDateSpecification) this);
    }

    public Date getDate() {
        return dateForGDate(this);
    }

    static int julianDateForGDate(GDateSpecification gDateSpecification) {
        if (gDateSpecification.hasDate()) {
            int day = gDateSpecification.getDay();
            int month = gDateSpecification.getMonth();
            int year = gDateSpecification.getYear();
            if (year <= 0) {
                year++;
            }
            int i = (month - 14) / 12;
            int i2 = (((day - 32075) + ((((year + 4800) + i) * 1461) / 4)) + ((((month - 2) - (i * 12)) * 367) / 12)) - (((((year + 4900) + i) / 100) * 3) / 4);
            if (i2 >= 0) {
                return i2;
            }
            throw new IllegalStateException("date too far in the past (year allowed to -4713)");
        }
        throw new IllegalStateException("cannot do date math without a complete date");
    }

    static Date dateForGDate(GDateSpecification gDateSpecification) {
        long j;
        long julianDateForGDate = ((((long) julianDateForGDate(gDateSpecification)) - 2440588) * DateUtil.DAY_MILLISECONDS) + ((long) gDateSpecification.getMillisecond()) + (((long) gDateSpecification.getSecond()) * 1000) + (((long) (gDateSpecification.getMinute() * 60)) * 1000) + (((long) (gDateSpecification.getHour() * 60 * 60)) * 1000);
        if (gDateSpecification.hasTimeZone()) {
            julianDateForGDate -= ((long) ((gDateSpecification.getTimeZoneMinute() * gDateSpecification.getTimeZoneSign()) * 60)) * 1000;
            j = ((long) (gDateSpecification.getTimeZoneHour() * gDateSpecification.getTimeZoneSign() * 60 * 60)) * 1000;
        } else {
            j = (long) TimeZone.getDefault().getOffset(julianDateForGDate);
        }
        return new Date(julianDateForGDate - j);
    }

    private static boolean _isLeapYear(int i) {
        return i % 4 == 0 && (i % 100 != 0 || i % FontHeader.REGULAR_WEIGHT == 0);
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

    private static int _modulo(long j, int i, int i2) {
        long j2 = j - ((long) i);
        int i3 = i2 - i;
        return _mod(j2, i3, _fQuotient(j2, i3)) + i;
    }

    private static long _fQuotient(long j, int i, int i2) {
        return _fQuotient(j - ((long) i), i2 - i);
    }

    private void _setToFirstMoment() {
        if (!hasYear()) {
            setYear(1584);
        }
        if (!hasMonth()) {
            setMonth(1);
        }
        if (!hasDay()) {
            setDay(1);
        }
        if (!hasTime()) {
            setTime(0, 0, 0, GDate._zero);
        }
    }

    public final int compareToGDate(GDateSpecification gDateSpecification) {
        return compareGDate(this, gDateSpecification);
    }

    static int compareGDate(GDateSpecification gDateSpecification, GDateSpecification gDateSpecification2) {
        int flags = gDateSpecification.getFlags() ^ gDateSpecification2.getFlags();
        if ((flags & 31) == 0) {
            if (gDateSpecification.hasTimeZone() && !(gDateSpecification2.getTimeZoneHour() == gDateSpecification.getTimeZoneHour() && gDateSpecification2.getTimeZoneMinute() == gDateSpecification.getTimeZoneMinute() && gDateSpecification2.getTimeZoneSign() == gDateSpecification.getTimeZoneSign())) {
                GDateBuilder gDateBuilder = new GDateBuilder(gDateSpecification2);
                int flags2 = gDateSpecification.getFlags() & 14;
                if (!(flags2 == 0 || flags2 == 14) || !gDateSpecification.hasTime()) {
                    GDateBuilder gDateBuilder2 = gDateBuilder;
                    gDateBuilder._setToFirstMoment();
                    GDateBuilder gDateBuilder3 = new GDateBuilder(gDateSpecification);
                    GDateBuilder gDateBuilder4 = gDateBuilder3;
                    gDateBuilder3._setToFirstMoment();
                    gDateSpecification = gDateBuilder3;
                }
                GDateBuilder gDateBuilder5 = gDateBuilder;
                gDateBuilder.normalizeToTimeZone(gDateSpecification.getTimeZoneSign(), gDateSpecification.getTimeZoneHour(), gDateSpecification.getTimeZoneMinute());
                gDateSpecification2 = gDateBuilder;
            }
            return fieldwiseCompare(gDateSpecification, gDateSpecification2);
        } else if ((flags & 30) != 0) {
            return 2;
        } else {
            if (!gDateSpecification.hasTimeZone()) {
                int compareGDate = compareGDate(gDateSpecification2, gDateSpecification);
                if (compareGDate == 2) {
                    return 2;
                }
                return -compareGDate;
            }
            GDateBuilder gDateBuilder6 = new GDateBuilder(gDateSpecification);
            if ((gDateSpecification.getFlags() & 14) == 12) {
                if (gDateSpecification.getDay() == 28 && gDateSpecification.getMonth() == 2) {
                    if (gDateSpecification2.getDay() == 1 && gDateSpecification2.getMonth() == 3) {
                        gDateBuilder6.setDay(29);
                    }
                } else if (gDateSpecification2.getDay() == 28 && gDateSpecification2.getMonth() == 2 && gDateSpecification.getDay() == 1 && gDateSpecification.getMonth() == 3) {
                    gDateBuilder6.setMonth(2);
                    gDateBuilder6.setDay(29);
                }
            }
            gDateBuilder6._setToFirstMoment();
            GDateBuilder gDateBuilder7 = new GDateBuilder(gDateSpecification2);
            gDateBuilder7._setToFirstMoment();
            gDateBuilder7.setTimeZone(1, 14, 0);
            gDateBuilder7.normalizeToTimeZone(gDateSpecification.getTimeZoneSign(), gDateSpecification.getTimeZoneHour(), gDateSpecification.getTimeZoneMinute());
            if (fieldwiseCompare(gDateBuilder6, gDateBuilder7) == -1) {
                return -1;
            }
            gDateBuilder7.setGDate(gDateSpecification2);
            gDateBuilder7._setToFirstMoment();
            gDateBuilder7.setTimeZone(-1, 14, 0);
            gDateBuilder7.normalizeToTimeZone(gDateSpecification.getTimeZoneSign(), gDateSpecification.getTimeZoneHour(), gDateSpecification.getTimeZoneMinute());
            if (fieldwiseCompare(gDateBuilder6, gDateBuilder7) == 1) {
                return 1;
            }
            return 2;
        }
    }

    private static int fieldwiseCompare(GDateSpecification gDateSpecification, GDateSpecification gDateSpecification2) {
        if (gDateSpecification.hasYear()) {
            int year = gDateSpecification2.getYear();
            int year2 = gDateSpecification.getYear();
            if (year2 < year) {
                return -1;
            }
            if (year2 > year) {
                return 1;
            }
        }
        if (gDateSpecification.hasMonth()) {
            int month = gDateSpecification2.getMonth();
            int month2 = gDateSpecification.getMonth();
            if (month2 < month) {
                return -1;
            }
            if (month2 > month) {
                return 1;
            }
        }
        if (gDateSpecification.hasDay()) {
            int day = gDateSpecification2.getDay();
            int day2 = gDateSpecification.getDay();
            if (day2 < day) {
                return -1;
            }
            if (day2 > day) {
                return 1;
            }
        }
        if (!gDateSpecification.hasTime()) {
            return 0;
        }
        int hour = gDateSpecification2.getHour();
        int hour2 = gDateSpecification.getHour();
        if (hour2 < hour) {
            return -1;
        }
        if (hour2 > hour) {
            return 1;
        }
        int minute = gDateSpecification2.getMinute();
        int minute2 = gDateSpecification.getMinute();
        if (minute2 < minute) {
            return -1;
        }
        if (minute2 > minute) {
            return 1;
        }
        int second = gDateSpecification2.getSecond();
        int second2 = gDateSpecification.getSecond();
        if (second2 < second) {
            return -1;
        }
        if (second2 > second) {
            return 1;
        }
        BigDecimal fraction = gDateSpecification2.getFraction();
        BigDecimal fraction2 = gDateSpecification.getFraction();
        if (fraction2 == null && fraction == null) {
            return 0;
        }
        if (fraction2 == null) {
            fraction2 = GDate._zero;
        }
        if (fraction == null) {
            fraction = GDate._zero;
        }
        return fraction2.compareTo(fraction);
    }

    public final int getBuiltinTypeCode() {
        return btcForFlags(this._bits);
    }

    public void setBuiltinTypeCode(int i) {
        switch (i) {
            case 14:
                return;
            case 15:
                clearYear();
                clearMonth();
                clearDay();
                return;
            case 16:
                clearTime();
                return;
            case 17:
                clearDay();
                clearTime();
                return;
            case 18:
                clearMonth();
                clearDay();
                clearTime();
                return;
            case 19:
                clearYear();
                clearTime();
                return;
            case 20:
                clearYear();
                clearMonth();
                clearTime();
                return;
            case 21:
                clearYear();
                clearDay();
                clearTime();
                return;
            default:
                throw new IllegalArgumentException("codeType must be one of SchemaType BTC_  DATE TIME related types.");
        }
    }

    public String canonicalString() {
        boolean z = true;
        boolean z2 = hasTimeZone() && getTimeZoneSign() != 0 && hasTime() && hasDay() == hasMonth() && hasDay() == hasYear();
        if (!z2 && getFraction() != null && getFraction().scale() > 0) {
            if (getFraction().unscaledValue().mod(TEN).signum() != 0) {
                z = false;
            }
            z2 = z;
        }
        if (!z2) {
            return toString();
        }
        GDateBuilder gDateBuilder = new GDateBuilder((GDateSpecification) this);
        gDateBuilder.normalize();
        return gDateBuilder.toString();
    }

    public final String toString() {
        return GDate.formatGDate(this);
    }
}
