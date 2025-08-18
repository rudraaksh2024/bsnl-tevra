package org.apache.xmlbeans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;
import org.apache.poi.xddf.usermodel.Angles;
import org.apache.xmlbeans.impl.common.NameUtil;

public final class GDate implements GDateSpecification, Serializable {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final TimeZone GMTZONE = TimeZone.getTimeZone("GMT");
    static final int MAX_YEAR = 292277265;
    private static final TimeZone[] MINUSZONE = {TimeZone.getTimeZone("GMT-00:00"), TimeZone.getTimeZone("GMT-01:00"), TimeZone.getTimeZone("GMT-02:00"), TimeZone.getTimeZone("GMT-03:00"), TimeZone.getTimeZone("GMT-04:00"), TimeZone.getTimeZone("GMT-05:00"), TimeZone.getTimeZone("GMT-06:00"), TimeZone.getTimeZone("GMT-07:00"), TimeZone.getTimeZone("GMT-08:00"), TimeZone.getTimeZone("GMT-09:00"), TimeZone.getTimeZone("GMT-10:00"), TimeZone.getTimeZone("GMT-11:00"), TimeZone.getTimeZone("GMT-12:00"), TimeZone.getTimeZone("GMT-13:00"), TimeZone.getTimeZone("GMT-14:00")};
    static final int MIN_YEAR = -292275295;
    private static final TimeZone[] PLUSZONE = {TimeZone.getTimeZone("GMT+00:00"), TimeZone.getTimeZone("GMT+01:00"), TimeZone.getTimeZone("GMT+02:00"), TimeZone.getTimeZone("GMT+03:00"), TimeZone.getTimeZone("GMT+04:00"), TimeZone.getTimeZone("GMT+05:00"), TimeZone.getTimeZone("GMT+06:00"), TimeZone.getTimeZone("GMT+07:00"), TimeZone.getTimeZone("GMT+08:00"), TimeZone.getTimeZone("GMT+09:00"), TimeZone.getTimeZone("GMT+10:00"), TimeZone.getTimeZone("GMT+11:00"), TimeZone.getTimeZone("GMT+12:00"), TimeZone.getTimeZone("GMT+13:00"), TimeZone.getTimeZone("GMT+14:00")};
    static final BigDecimal _one = BigDecimal.ONE;
    private static final char[] _onesDigit = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
    private static final char[] _tensDigit = {'0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '2', '2', '2', '2', '2', '2', '2', '2', '2', '2', '3', '3', '3', '3', '3', '3', '3', '3', '3', '3', '4', '4', '4', '4', '4', '4', '4', '4', '4', '4', '5', '5', '5', '5', '5', '5', '5', '5', '5', '5', '6', '6', '6', '6', '6', '6', '6', '6', '6', '6', '7', '7', '7', '7', '7', '7', '7', '7', '7', '7', '8', '8', '8', '8', '8', '8', '8', '8', '8', '8', '9', '9', '9', '9', '9', '9', '9', '9', '9', '9'};
    static final BigDecimal _zero = BigDecimal.ZERO;
    private static final long serialVersionUID = 1;
    private int _CY;
    private int _D;
    private int _M;
    private int _bits;
    private transient String _canonicalString;
    private BigDecimal _fs;
    private int _h;
    private int _m;
    private int _s;
    private transient String _string;
    private int _tzh;
    private int _tzm;
    private int _tzsign;

    static int digitVal(char c) {
        return c - '0';
    }

    static boolean isDigit(char c) {
        return ((char) (c + 65488)) <= 9;
    }

    static boolean isSpace(char c) {
        return c == 9 || c == 10 || c == 13 || c == ' ';
    }

    public final boolean isImmutable() {
        return true;
    }

    public GDate(CharSequence charSequence) {
        int i;
        int i2;
        boolean z;
        int twoDigit;
        int twoDigit2;
        int i3;
        CharSequence charSequence2 = charSequence;
        int length = charSequence.length();
        while (length > 0 && isSpace(charSequence2.charAt(length - 1))) {
            length--;
        }
        int i4 = 0;
        while (i < length && isSpace(charSequence2.charAt(i))) {
            i4 = i + 1;
        }
        int i5 = length - i;
        if (i5 >= 1 && charSequence2.charAt(length - 1) == 'Z') {
            this._bits |= 1;
            length--;
        } else if (i5 >= 6 && charSequence2.charAt(length - 3) == ':') {
            char charAt = charSequence2.charAt(length - 6);
            if (charAt != '+') {
                i3 = charAt == '-' ? -1 : i3;
            } else {
                i3 = 1;
            }
            int twoDigit3 = twoDigit(charSequence2, length - 5);
            int twoDigit4 = twoDigit(charSequence2, length - 2);
            if (twoDigit3 > 14) {
                throw new IllegalArgumentException("time zone hour must be two digits between -14 and +14");
            } else if (twoDigit4 <= 59) {
                this._bits |= 1;
                this._tzsign = i3;
                this._tzh = twoDigit3;
                this._tzm = twoDigit4;
                length -= 6;
            } else {
                throw new IllegalArgumentException("time zone minute must be two digits between 00 and 59");
            }
        }
        if (i < length && ((i2 = i + 2) >= length || charSequence2.charAt(i2) != ':')) {
            if (charSequence2.charAt(i) == '-') {
                i++;
                z = true;
            } else {
                z = false;
            }
            int i6 = -i;
            boolean z2 = i < length && digitVal(charSequence2.charAt(i)) == 0;
            int i7 = 0;
            while (true) {
                char charAt2 = i < length ? charSequence2.charAt(i) : 0;
                if (!isDigit(charAt2)) {
                    int i8 = i6 + i;
                    if (i8 <= 9) {
                        if (i8 >= 4) {
                            this._bits |= 2;
                            i7 = z ? -i7 : i7;
                            this._CY = i7;
                            if (i7 == 0) {
                                throw new IllegalArgumentException("year must not be zero");
                            }
                        } else if (i8 > 0) {
                            throw new IllegalArgumentException("year must be four digits (may pad with zeroes, e.g., 0560)");
                        }
                        int i9 = this._CY;
                        if (i9 > MAX_YEAR) {
                            throw new IllegalArgumentException("year value not supported: too big, must be less than 292277265");
                        } else if (i9 < MIN_YEAR) {
                            throw new IllegalArgumentException("year values not supported: too small, must be bigger than -292275295");
                        } else if (charAt2 == '-') {
                            int i10 = i + 1;
                            if (length - i10 >= 2 && (twoDigit2 = twoDigit(charSequence2, i10)) >= 1 && twoDigit2 <= 12) {
                                this._bits |= 4;
                                this._M = twoDigit2;
                                i10 += 2;
                            }
                            if ((i < length ? charSequence2.charAt(i) : 0) == '-') {
                                i++;
                                if (length - i >= 2 && (twoDigit = twoDigit(charSequence2, i)) >= 1 && twoDigit <= 31) {
                                    this._bits |= 8;
                                    this._D = twoDigit;
                                    i += 2;
                                }
                                if (!hasDay()) {
                                    if (hasMonth() && !hasYear()) {
                                        if ((i < length ? charSequence2.charAt(i) : 0) == '-') {
                                            i++;
                                        }
                                    }
                                    throw new IllegalArgumentException();
                                }
                            } else if (!hasMonth()) {
                                throw new IllegalArgumentException();
                            }
                        } else if (z && !hasYear()) {
                            throw new IllegalArgumentException();
                        }
                    } else {
                        throw new IllegalArgumentException("year too long (up to 9 digits)");
                    }
                } else if (!z2 || i + i6 < 4) {
                    i7 = (i7 * 10) + digitVal(charAt2);
                    i++;
                } else {
                    throw new IllegalArgumentException("year value starting with zero must be 4 or less digits: " + charSequence2);
                }
            }
        }
        if (i < length) {
            if (hasYear() || hasMonth() || hasDay()) {
                if (charSequence2.charAt(i) == 'T') {
                    i++;
                } else {
                    throw new IllegalArgumentException("date and time must be separated by 'T'");
                }
            }
            int i11 = i + 8;
            if (length >= i11 && charSequence2.charAt(i + 2) == ':' && charSequence2.charAt(i + 5) == ':') {
                int twoDigit5 = twoDigit(charSequence2, i);
                if (twoDigit5 <= 24) {
                    int twoDigit6 = twoDigit(charSequence2, i + 3);
                    if (twoDigit6 < 60) {
                        int twoDigit7 = twoDigit(charSequence2, i + 6);
                        if (twoDigit7 < 60) {
                            BigDecimal bigDecimal = _zero;
                            if (i11 < length) {
                                if (charSequence2.charAt(i11) == '.') {
                                    int i12 = i11 + 1;
                                    if (i12 < length) {
                                        while (i12 < length) {
                                            if (isDigit(charSequence2.charAt(i12))) {
                                                i12++;
                                            } else {
                                                throw new IllegalArgumentException();
                                            }
                                        }
                                        try {
                                            bigDecimal = new BigDecimal(charSequence2.subSequence(i11, length).toString());
                                        } catch (Throwable unused) {
                                            throw new IllegalArgumentException();
                                        }
                                    }
                                } else {
                                    throw new IllegalArgumentException();
                                }
                            }
                            this._bits |= 16;
                            this._h = twoDigit5;
                            this._m = twoDigit6;
                            this._s = twoDigit7;
                            this._fs = bigDecimal;
                        } else {
                            throw new IllegalArgumentException("second must be between 00 and 59");
                        }
                    } else {
                        throw new IllegalArgumentException("minute must be between 00 and 59");
                    }
                } else {
                    throw new IllegalArgumentException("hour must be between 00 and 23");
                }
            } else {
                throw new IllegalArgumentException();
            }
        }
        if (hasTime() && this._h == 24) {
            if (this._m != 0 || this._s != 0 || this._fs.compareTo(_zero) != 0) {
                throw new IllegalArgumentException("if hour is 24, minutes, seconds and fraction must be 0");
            } else if (hasDate()) {
                GDateBuilder gDateBuilder = new GDateBuilder(this._CY, this._M, this._D, this._h, this._m, this._s, this._fs, this._tzsign, this._tzh, this._tzm);
                gDateBuilder.normalize24h();
                this._D = gDateBuilder.getDay();
                this._M = gDateBuilder.getMonth();
                this._CY = gDateBuilder.getYear();
                this._h = 0;
            } else if (hasDay()) {
                this._D++;
                this._h = 0;
            }
        }
        if (!isValid()) {
            throw new IllegalArgumentException("invalid date");
        }
    }

    public GDate(int i, int i2, int i3, int i4, int i5, int i6, BigDecimal bigDecimal) {
        this._bits = 30;
        this._CY = i;
        this._M = i2;
        this._D = i3;
        this._h = i4;
        this._m = i5;
        this._s = i6;
        this._fs = bigDecimal == null ? _zero : bigDecimal;
        if (!isValid()) {
            throw new IllegalArgumentException();
        }
    }

    public GDate(int i, int i2, int i3, int i4, int i5, int i6, BigDecimal bigDecimal, int i7, int i8, int i9) {
        this._bits = 31;
        this._CY = i;
        this._M = i2;
        this._D = i3;
        this._h = i4;
        this._m = i5;
        this._s = i6;
        this._fs = bigDecimal == null ? _zero : bigDecimal;
        this._tzsign = i7;
        this._tzh = i8;
        this._tzm = i9;
        if (!isValid()) {
            throw new IllegalArgumentException();
        }
    }

    public GDate(Date date) {
        this((GDateSpecification) new GDateBuilder(date));
    }

    public GDate(Calendar calendar) {
        int i;
        boolean z;
        int i2;
        int i3;
        int i4;
        Calendar calendar2 = calendar;
        int i5 = 1;
        boolean isSet = calendar2.isSet(1);
        boolean isSet2 = calendar2.isSet(0);
        boolean isSet3 = calendar2.isSet(2);
        boolean isSet4 = calendar2.isSet(5);
        boolean isSet5 = calendar2.isSet(11);
        boolean isSet6 = calendar2.isSet(10);
        boolean isSet7 = calendar2.isSet(9);
        boolean isSet8 = calendar2.isSet(12);
        boolean isSet9 = calendar2.isSet(13);
        boolean isSet10 = calendar2.isSet(14);
        boolean isSet11 = calendar2.isSet(15);
        boolean isSet12 = calendar2.isSet(16);
        if (isSet) {
            int i6 = calendar2.get(1);
            if (isSet2 && (calendar2 instanceof GregorianCalendar) && calendar2.get(0) == 0) {
                i6 = -i6;
            }
            this._bits |= 2;
            this._CY = i6;
        }
        if (isSet3) {
            this._bits |= 4;
            this._M = calendar2.get(2) + 1;
        }
        if (isSet4) {
            this._bits |= 8;
            this._D = calendar2.get(5);
        }
        BigDecimal bigDecimal = _zero;
        if (isSet5) {
            i2 = calendar2.get(11);
            z = true;
            i = 12;
        } else if (!isSet6 || !isSet7) {
            i = 12;
            i2 = 0;
            z = false;
        } else {
            i = 12;
            i2 = calendar2.get(10) + (calendar2.get(9) * 12);
            z = true;
        }
        if (isSet8) {
            i3 = calendar2.get(i);
            z = true;
        } else {
            i3 = 0;
        }
        if (isSet9) {
            i4 = calendar2.get(13);
            z = true;
        } else {
            i4 = 0;
        }
        if (isSet10) {
            bigDecimal = BigDecimal.valueOf((long) calendar2.get(14), 3);
            z = true;
        }
        if (z) {
            this._bits |= 16;
            this._h = i2;
            this._m = i3;
            this._s = i4;
            this._fs = bigDecimal;
        }
        if (isSet11) {
            int i7 = calendar2.get(15);
            i7 = isSet12 ? i7 + calendar2.get(16) : i7;
            this._bits |= 1;
            if (i7 == 0) {
                this._tzsign = 0;
                this._tzh = 0;
                this._tzm = 0;
                String id = calendar.getTimeZone().getID();
                if (id != null && id.length() > 3) {
                    char charAt = id.charAt(3);
                    if (charAt == '+') {
                        this._tzsign = 1;
                    } else if (charAt == '-') {
                        this._tzsign = -1;
                    }
                }
            } else {
                i5 = i7 < 0 ? -1 : i5;
                this._tzsign = i5;
                int i8 = i7 * i5;
                int i9 = i8 / 3600000;
                this._tzh = i9;
                this._tzm = (i8 - (i9 * 3600000)) / Angles.OOXML_DEGREE;
            }
        }
    }

    public GDate(GDateSpecification gDateSpecification) {
        if (gDateSpecification.hasTimeZone()) {
            this._bits |= 1;
            this._tzsign = gDateSpecification.getTimeZoneSign();
            this._tzh = gDateSpecification.getTimeZoneHour();
            this._tzm = gDateSpecification.getTimeZoneMinute();
        }
        if (gDateSpecification.hasTime()) {
            this._bits |= 16;
            this._h = gDateSpecification.getHour();
            this._m = gDateSpecification.getMinute();
            this._s = gDateSpecification.getSecond();
            this._fs = gDateSpecification.getFraction();
        }
        if (gDateSpecification.hasDay()) {
            this._bits |= 8;
            this._D = gDateSpecification.getDay();
        }
        if (gDateSpecification.hasMonth()) {
            this._bits |= 4;
            this._M = gDateSpecification.getMonth();
        }
        if (gDateSpecification.hasYear()) {
            this._bits |= 2;
            this._CY = gDateSpecification.getYear();
        }
    }

    private static int twoDigit(CharSequence charSequence, int i) {
        char charAt = charSequence.charAt(i);
        char charAt2 = charSequence.charAt(i + 1);
        if (!isDigit(charAt) || !isDigit(charAt2)) {
            return 100;
        }
        return (digitVal(charAt) * 10) + digitVal(charAt2);
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

    public final BigDecimal getFraction() {
        return this._fs;
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

    public int getMillisecond() {
        BigDecimal bigDecimal = this._fs;
        if (bigDecimal == null) {
            return 0;
        }
        return bigDecimal.setScale(3, RoundingMode.DOWN).unscaledValue().intValue();
    }

    public String canonicalString() {
        ensureCanonicalString();
        return this._canonicalString;
    }

    public boolean isValid() {
        return GDateBuilder.isValidGDate(this);
    }

    public int getJulianDate() {
        return GDateBuilder.julianDateForGDate(this);
    }

    public XmlCalendar getCalendar() {
        return new XmlCalendar((GDateSpecification) this);
    }

    public Date getDate() {
        return GDateBuilder.dateForGDate(this);
    }

    public int compareToGDate(GDateSpecification gDateSpecification) {
        return GDateBuilder.compareGDate(this, gDateSpecification);
    }

    public int getBuiltinTypeCode() {
        return GDateBuilder.btcForFlags(this._bits);
    }

    public GDate add(GDurationSpecification gDurationSpecification) {
        GDateBuilder gDateBuilder = new GDateBuilder((GDateSpecification) this);
        gDateBuilder.addGDuration(gDurationSpecification);
        return gDateBuilder.toGDate();
    }

    public GDate subtract(GDurationSpecification gDurationSpecification) {
        GDateBuilder gDateBuilder = new GDateBuilder((GDateSpecification) this);
        gDateBuilder.subtractGDuration(gDurationSpecification);
        return gDateBuilder.toGDate();
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof GDate)) {
            return false;
        }
        ensureCanonicalString();
        return this._canonicalString.equals(((GDate) obj).canonicalString());
    }

    public int hashCode() {
        ensureCanonicalString();
        return this._canonicalString.hashCode();
    }

    private void ensureCanonicalString() {
        if (this._canonicalString == null) {
            boolean z = true;
            boolean z2 = hasTimeZone() && getTimeZoneSign() != 0 && hasTime() && hasDay() == hasMonth() && hasDay() == hasYear();
            if (!z2 && getFraction() != null && getFraction().scale() > 0) {
                if (getFraction().unscaledValue().mod(GDateBuilder.TEN).signum() != 0) {
                    z = false;
                }
                z2 = z;
            }
            if (!z2) {
                this._canonicalString = toString();
                return;
            }
            GDateBuilder gDateBuilder = new GDateBuilder((GDateSpecification) this);
            gDateBuilder.normalize();
            this._canonicalString = gDateBuilder.toString();
        }
    }

    public String toString() {
        if (this._string == null) {
            this._string = formatGDate(this);
        }
        return this._string;
    }

    private static int _padTwoAppend(char[] cArr, int i, int i2) {
        cArr[i] = _tensDigit[i2];
        cArr[i + 1] = _onesDigit[i2];
        return i + 2;
    }

    private static int _padFourAppend(char[] cArr, int i) {
        int i2;
        if (i < 0) {
            cArr[0] = '-';
            i = -i;
            i2 = 1;
        } else {
            i2 = 0;
        }
        if (i >= 10000) {
            String num = Integer.toString(i);
            num.getChars(0, num.length(), cArr, i2);
            return i2 + num.length();
        }
        int i3 = i / 100;
        int i4 = i - (i3 * 100);
        char[] cArr2 = _tensDigit;
        cArr[i2] = cArr2[i3];
        char[] cArr3 = _onesDigit;
        cArr[i2 + 1] = cArr3[i3];
        cArr[i2 + 2] = cArr2[i4];
        cArr[i2 + 3] = cArr3[i4];
        return i2 + 4;
    }

    static TimeZone timeZoneForGDate(GDateSpecification gDateSpecification) {
        if (!gDateSpecification.hasTimeZone()) {
            return TimeZone.getDefault();
        }
        if (gDateSpecification.getTimeZoneSign() == 0) {
            return GMTZONE;
        }
        if (gDateSpecification.getTimeZoneMinute() == 0 && gDateSpecification.getTimeZoneHour() <= 14 && gDateSpecification.getTimeZoneHour() >= 0) {
            return gDateSpecification.getTimeZoneSign() < 0 ? MINUSZONE[gDateSpecification.getTimeZoneHour()] : PLUSZONE[gDateSpecification.getTimeZoneHour()];
        }
        char[] cArr = new char[9];
        cArr[0] = 'G';
        cArr[1] = 'M';
        cArr[2] = 'T';
        cArr[3] = gDateSpecification.getTimeZoneSign() < 0 ? '-' : '+';
        _padTwoAppend(cArr, 4, gDateSpecification.getTimeZoneHour());
        cArr[6] = NameUtil.COLON;
        _padTwoAppend(cArr, 7, gDateSpecification.getTimeZoneMinute());
        return TimeZone.getTimeZone(new String(cArr));
    }

    static String formatGDate(GDateSpecification gDateSpecification) {
        int i;
        int i2;
        String bigDecimal;
        int indexOf;
        BigDecimal fraction = gDateSpecification.getFraction();
        if (fraction == null) {
            i = 0;
        } else {
            i = fraction.scale();
        }
        char[] cArr = new char[(i + 33)];
        char c = '-';
        if (gDateSpecification.hasYear() || gDateSpecification.hasMonth() || gDateSpecification.hasDay()) {
            if (gDateSpecification.hasYear()) {
                i2 = _padFourAppend(cArr, gDateSpecification.getYear());
            } else {
                cArr[0] = '-';
                i2 = 1;
            }
            if (gDateSpecification.hasMonth() || gDateSpecification.hasDay()) {
                int i3 = i2 + 1;
                cArr[i2] = '-';
                i2 = gDateSpecification.hasMonth() ? _padTwoAppend(cArr, i3, gDateSpecification.getMonth()) : i3;
                if (gDateSpecification.hasDay()) {
                    cArr[i2] = '-';
                    i2 = _padTwoAppend(cArr, i2 + 1, gDateSpecification.getDay());
                }
            }
            if (gDateSpecification.hasTime()) {
                cArr[i2] = 'T';
                i2++;
            }
        } else {
            i2 = 0;
        }
        if (gDateSpecification.hasTime()) {
            int _padTwoAppend = _padTwoAppend(cArr, i2, gDateSpecification.getHour());
            cArr[_padTwoAppend] = NameUtil.COLON;
            int _padTwoAppend2 = _padTwoAppend(cArr, _padTwoAppend + 1, gDateSpecification.getMinute());
            cArr[_padTwoAppend2] = NameUtil.COLON;
            i2 = _padTwoAppend(cArr, _padTwoAppend2 + 1, gDateSpecification.getSecond());
            if (fraction != null && !_zero.equals(fraction) && (indexOf = bigDecimal.indexOf(46)) >= 0) {
                bigDecimal.getChars(indexOf, (bigDecimal = fraction.toString()).length(), cArr, i2);
                i2 += bigDecimal.length() - indexOf;
            }
        }
        if (gDateSpecification.hasTimeZone()) {
            if (gDateSpecification.getTimeZoneSign() == 0) {
                cArr[i2] = 'Z';
                i2++;
            } else {
                int i4 = i2 + 1;
                if (gDateSpecification.getTimeZoneSign() > 0) {
                    c = '+';
                }
                cArr[i2] = c;
                int _padTwoAppend3 = _padTwoAppend(cArr, i4, gDateSpecification.getTimeZoneHour());
                cArr[_padTwoAppend3] = NameUtil.COLON;
                i2 = _padTwoAppend(cArr, _padTwoAppend3 + 1, gDateSpecification.getTimeZoneMinute());
            }
        }
        return new String(cArr, 0, i2);
    }
}
