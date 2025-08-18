package org.apache.poi.ss.formula.atp;

import java.util.Calendar;
import org.apache.poi.common.usermodel.fonts.FontHeader;
import org.apache.poi.ss.formula.eval.ErrorEval;
import org.apache.poi.ss.formula.eval.EvaluationException;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.util.Internal;
import org.apache.poi.util.LocaleUtil;

@Internal
final class YearFracCalculator {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final int DAYS_PER_LEAP_YEAR = 366;
    private static final int DAYS_PER_NORMAL_YEAR = 365;
    private static final int LONG_FEB_LEN = 29;
    private static final int LONG_MONTH_LEN = 31;
    private static final int MS_PER_DAY = 86400000;
    private static final int MS_PER_HOUR = 3600000;
    private static final int SHORT_FEB_LEN = 28;
    private static final int SHORT_MONTH_LEN = 30;

    private static double basis2(int i, int i2) {
        return ((double) (i2 - i)) / 360.0d;
    }

    private static double basis3(double d, double d2) {
        return (d2 - d) / 365.0d;
    }

    private YearFracCalculator() {
    }

    public static double calculate(double d, double d2, int i) throws EvaluationException {
        if (i < 0 || i >= 5) {
            throw new EvaluationException(ErrorEval.NUM_ERROR);
        }
        int floor = (int) Math.floor(d);
        int floor2 = (int) Math.floor(d2);
        if (floor == floor2) {
            return 0.0d;
        }
        if (floor > floor2) {
            int i2 = floor2;
            floor2 = floor;
            floor = i2;
        }
        if (i == 0) {
            return basis0(floor, floor2);
        }
        if (i == 1) {
            return basis1(floor, floor2);
        }
        if (i == 2) {
            return basis2(floor, floor2);
        }
        if (i == 3) {
            return basis3((double) floor, (double) floor2);
        }
        if (i == 4) {
            return basis4(floor, floor2);
        }
        throw new IllegalStateException("cannot happen");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0034, code lost:
        if (isLastDayOfMonth(r6) != false) goto L_0x0014;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static double basis0(int r5, int r6) {
        /*
            org.apache.poi.ss.formula.atp.YearFracCalculator$SimpleDate r5 = createDate(r5)
            org.apache.poi.ss.formula.atp.YearFracCalculator$SimpleDate r6 = createDate(r6)
            int r0 = r5.day
            int r1 = r6.day
            r2 = 30
            r3 = 31
            if (r0 != r3) goto L_0x0017
            if (r1 != r3) goto L_0x0017
        L_0x0014:
            r0 = r2
            r1 = r0
            goto L_0x0037
        L_0x0017:
            if (r0 != r3) goto L_0x001b
        L_0x0019:
            r0 = r2
            goto L_0x0037
        L_0x001b:
            if (r0 != r2) goto L_0x0021
            if (r1 != r3) goto L_0x0021
            r1 = r2
            goto L_0x0037
        L_0x0021:
            int r3 = r5.month
            r4 = 2
            if (r3 != r4) goto L_0x0037
            boolean r3 = isLastDayOfMonth(r5)
            if (r3 == 0) goto L_0x0037
            int r0 = r6.month
            if (r0 != r4) goto L_0x0019
            boolean r0 = isLastDayOfMonth(r6)
            if (r0 == 0) goto L_0x0019
            goto L_0x0014
        L_0x0037:
            double r5 = calculateAdjusted(r5, r6, r0, r1)
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.ss.formula.atp.YearFracCalculator.basis0(int, int):double");
    }

    private static double basis1(int i, int i2) {
        double d;
        SimpleDate createDate = createDate(i);
        SimpleDate createDate2 = createDate(i2);
        if (isGreaterThanOneYear(createDate, createDate2)) {
            d = averageYearLength(createDate.year, createDate2.year);
        } else {
            d = shouldCountFeb29(createDate, createDate2) ? 366.0d : 365.0d;
        }
        return ((double) dateDiff(createDate.tsMilliseconds, createDate2.tsMilliseconds)) / d;
    }

    private static double basis4(int i, int i2) {
        SimpleDate createDate = createDate(i);
        SimpleDate createDate2 = createDate(i2);
        int i3 = createDate.day;
        int i4 = createDate2.day;
        if (i3 == 31) {
            i3 = 30;
        }
        if (i4 == 31) {
            i4 = 30;
        }
        return calculateAdjusted(createDate, createDate2, i3, i4);
    }

    private static double calculateAdjusted(SimpleDate simpleDate, SimpleDate simpleDate2, int i, int i2) {
        return (((((double) (simpleDate2.year - simpleDate.year)) * 360.0d) + (((double) (simpleDate2.month - simpleDate.month)) * 30.0d)) + (((double) (i2 - i)) * 1.0d)) / 360.0d;
    }

    private static boolean isLastDayOfMonth(SimpleDate simpleDate) {
        if (simpleDate.day >= 28 && simpleDate.day == getLastDayOfMonth(simpleDate)) {
            return true;
        }
        return false;
    }

    private static int getLastDayOfMonth(SimpleDate simpleDate) {
        switch (simpleDate.month) {
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                return 31;
            case 4:
            case 6:
            case 9:
            case 11:
                return 30;
            default:
                return isLeapYear(simpleDate.year) ? 29 : 28;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:5:0x0012, code lost:
        r4 = r4.month;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static boolean shouldCountFeb29(org.apache.poi.ss.formula.atp.YearFracCalculator.SimpleDate r4, org.apache.poi.ss.formula.atp.YearFracCalculator.SimpleDate r5) {
        /*
            int r0 = r4.year
            boolean r0 = isLeapYear(r0)
            r1 = 2
            r2 = 0
            r3 = 1
            if (r0 == 0) goto L_0x001a
            int r0 = r4.year
            int r5 = r5.year
            if (r0 != r5) goto L_0x0012
            return r3
        L_0x0012:
            int r4 = r4.month
            if (r4 == r3) goto L_0x0019
            if (r4 == r1) goto L_0x0019
            return r2
        L_0x0019:
            return r3
        L_0x001a:
            int r4 = r5.year
            boolean r4 = isLeapYear(r4)
            if (r4 == 0) goto L_0x0030
            int r4 = r5.month
            if (r4 == r3) goto L_0x0030
            if (r4 == r1) goto L_0x0029
            return r3
        L_0x0029:
            int r4 = r5.day
            r5 = 29
            if (r4 != r5) goto L_0x0030
            r2 = r3
        L_0x0030:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.ss.formula.atp.YearFracCalculator.shouldCountFeb29(org.apache.poi.ss.formula.atp.YearFracCalculator$SimpleDate, org.apache.poi.ss.formula.atp.YearFracCalculator$SimpleDate):boolean");
    }

    private static int dateDiff(long j, long j2) {
        long j3 = j2 - j;
        if (((int) ((j3 % DateUtil.DAY_MILLISECONDS) / 3600000)) == 0) {
            return (int) ((((double) j3) / 8.64E7d) + 0.5d);
        }
        throw new RuntimeException("Unexpected date diff between " + j + " and " + j2);
    }

    private static double averageYearLength(int i, int i2) {
        int i3 = 0;
        for (int i4 = i; i4 <= i2; i4++) {
            i3 += isLeapYear(i4) ? DAYS_PER_LEAP_YEAR : DAYS_PER_NORMAL_YEAR;
        }
        return ((double) i3) / (((double) (i2 - i)) + 1.0d);
    }

    private static boolean isLeapYear(int i) {
        if (i % 4 != 0) {
            return false;
        }
        if (i % FontHeader.REGULAR_WEIGHT == 0) {
            return true;
        }
        if (i % 100 != 0) {
            return true;
        }
        return false;
    }

    private static boolean isGreaterThanOneYear(SimpleDate simpleDate, SimpleDate simpleDate2) {
        if (simpleDate.year == simpleDate2.year) {
            return false;
        }
        if (simpleDate.year + 1 != simpleDate2.year) {
            return true;
        }
        if (simpleDate.month > simpleDate2.month) {
            return false;
        }
        if (simpleDate.month < simpleDate2.month) {
            return true;
        }
        if (simpleDate.day < simpleDate2.day) {
            return true;
        }
        return false;
    }

    private static SimpleDate createDate(int i) {
        Calendar localeCalendar = LocaleUtil.getLocaleCalendar(LocaleUtil.TIMEZONE_UTC);
        DateUtil.setCalendar(localeCalendar, i, 0, false, false);
        return new SimpleDate(localeCalendar);
    }

    private static final class SimpleDate {
        public static final int FEBRUARY = 2;
        public static final int JANUARY = 1;
        public final int day;
        public final int month;
        public final long tsMilliseconds;
        public final int year;

        public SimpleDate(Calendar calendar) {
            this.year = calendar.get(1);
            this.month = calendar.get(2) + 1;
            this.day = calendar.get(5);
            this.tsMilliseconds = calendar.getTimeInMillis();
        }
    }
}
