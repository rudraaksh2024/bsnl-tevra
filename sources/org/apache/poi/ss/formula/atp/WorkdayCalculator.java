package org.apache.poi.ss.formula.atp;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.util.LocaleUtil;

public class WorkdayCalculator {
    private static final Set<Integer> friSatWeekend;
    private static final Set<Integer> friWeekend;
    public static final WorkdayCalculator instance = new WorkdayCalculator();
    private static final Set<Integer> monTuesWeekend;
    private static final Set<Integer> monWeekend;
    private static final Set<Integer> satWeekend;
    private static final Set<Integer> standardWeekend;
    private static final Set<Integer> sunMonWeekend;
    private static final Set<Integer> sunWeekend;
    private static final Set<Integer> thursFriWeekend;
    private static final Set<Integer> thursWeekend;
    private static final Set<Integer> tuesWedsWeekend;
    private static final Set<Integer> tuesWeekend;
    private static final Set<Integer> wedsThursWeekend;
    private static final Set<Integer> wedsWeekend;
    private static final Map<Integer, Set<Integer>> weekendTypeMap;

    /* access modifiers changed from: protected */
    public boolean isInARange(double d, double d2, double d3) {
        return d3 >= d && d3 <= d2;
    }

    static {
        HashSet hashSet = new HashSet(Arrays.asList(new Integer[]{7, 1}));
        standardWeekend = hashSet;
        HashSet hashSet2 = new HashSet(Arrays.asList(new Integer[]{1, 2}));
        sunMonWeekend = hashSet2;
        HashSet hashSet3 = new HashSet(Arrays.asList(new Integer[]{2, 3}));
        monTuesWeekend = hashSet3;
        HashSet hashSet4 = new HashSet(Arrays.asList(new Integer[]{3, 4}));
        tuesWedsWeekend = hashSet4;
        HashSet hashSet5 = new HashSet(Arrays.asList(new Integer[]{4, 5}));
        wedsThursWeekend = hashSet5;
        HashSet hashSet6 = new HashSet(Arrays.asList(new Integer[]{5, 6}));
        thursFriWeekend = hashSet6;
        HashSet hashSet7 = new HashSet(Arrays.asList(new Integer[]{6, 7}));
        friSatWeekend = hashSet7;
        Set<Integer> singleton = Collections.singleton(2);
        monWeekend = singleton;
        Set<Integer> singleton2 = Collections.singleton(3);
        tuesWeekend = singleton2;
        Set<Integer> set = singleton2;
        Set<Integer> singleton3 = Collections.singleton(4);
        wedsWeekend = singleton3;
        Set<Integer> set2 = singleton3;
        Set<Integer> singleton4 = Collections.singleton(5);
        thursWeekend = singleton4;
        Set<Integer> set3 = singleton4;
        Set<Integer> singleton5 = Collections.singleton(6);
        friWeekend = singleton5;
        Set<Integer> set4 = singleton5;
        Set<Integer> singleton6 = Collections.singleton(7);
        satWeekend = singleton6;
        Set<Integer> set5 = singleton6;
        Set<Integer> singleton7 = Collections.singleton(1);
        sunWeekend = singleton7;
        Set<Integer> set6 = singleton;
        HashMap hashMap = new HashMap();
        weekendTypeMap = hashMap;
        hashMap.put(1, hashSet);
        hashMap.put(2, hashSet2);
        hashMap.put(3, hashSet3);
        hashMap.put(4, hashSet4);
        hashMap.put(5, hashSet5);
        hashMap.put(6, hashSet6);
        hashMap.put(7, hashSet7);
        hashMap.put(11, singleton7);
        hashMap.put(12, set6);
        hashMap.put(13, set);
        hashMap.put(14, set2);
        hashMap.put(15, set3);
        hashMap.put(16, set4);
        hashMap.put(17, set5);
    }

    private WorkdayCalculator() {
    }

    public Set<Integer> getValidWeekendTypes() {
        return weekendTypeMap.keySet();
    }

    public int calculateWorkdays(double d, double d2, double[] dArr) {
        int i;
        Set<Integer> set = standardWeekend;
        Integer[] numArr = (Integer[]) set.toArray(new Integer[set.size()]);
        int i2 = 0;
        if (numArr.length == 0) {
            i = 0;
        } else {
            i = pastDaysOfWeek(d, d2, numArr[0].intValue());
        }
        if (numArr.length > 1) {
            i2 = pastDaysOfWeek(d, d2, numArr[1].intValue());
        }
        return ((((int) ((d2 - d) + 1.0d)) - i) - i2) - calculateNonWeekendHolidays(d, d2, dArr);
    }

    public Date calculateWorkdays(double d, int i, double[] dArr) {
        return calculateWorkdays(d, i, 1, dArr);
    }

    public Date calculateWorkdays(double d, int i, int i2, double[] dArr) {
        Set orDefault = weekendTypeMap.getOrDefault(Integer.valueOf(i2), standardWeekend);
        Date javaDate = DateUtil.getJavaDate(d);
        int i3 = i < 0 ? -1 : 1;
        Calendar localeCalendar = LocaleUtil.getLocaleCalendar();
        localeCalendar.setTime(javaDate);
        double excelDate = DateUtil.getExcelDate(localeCalendar.getTime());
        while (i != 0) {
            localeCalendar.add(6, i3);
            excelDate += (double) i3;
            if (!isWeekend(localeCalendar, orDefault) && !isHoliday(excelDate, dArr)) {
                i -= i3;
            }
        }
        return localeCalendar.getTime();
    }

    /* access modifiers changed from: protected */
    public int pastDaysOfWeek(double d, double d2, int i) {
        int i2 = (d > d2 ? 1 : (d == d2 ? 0 : -1));
        if (d2 > d) {
            d = d2;
        }
        int floor = (int) Math.floor(d);
        int i3 = 0;
        for (int floor2 = (int) Math.floor(i2 < 0 ? d : d2); floor2 <= floor; floor2++) {
            Calendar localeCalendar = LocaleUtil.getLocaleCalendar();
            localeCalendar.setTime(DateUtil.getJavaDate((double) floor2));
            if (localeCalendar.get(7) == i) {
                i3++;
            }
        }
        return i2 <= 0 ? i3 : -i3;
    }

    /* access modifiers changed from: protected */
    public int calculateNonWeekendHolidays(double d, double d2, double[] dArr) {
        double[] dArr2 = dArr;
        int i = (d > d2 ? 1 : (d == d2 ? 0 : -1));
        double d3 = i < 0 ? d : d2;
        double d4 = d2 > d ? d2 : d;
        int i2 = 0;
        for (double d5 : dArr2) {
            double d6 = d5;
            if (isInARange(d3, d4, d5)) {
                if (!isWeekend(d6)) {
                    i2++;
                }
            }
        }
        return i <= 0 ? i2 : -i2;
    }

    /* access modifiers changed from: protected */
    public boolean isWeekend(double d) {
        Calendar localeCalendar = LocaleUtil.getLocaleCalendar();
        localeCalendar.setTime(DateUtil.getJavaDate(d));
        return isWeekend(localeCalendar);
    }

    private boolean isWeekend(Calendar calendar) {
        return isWeekend(calendar, standardWeekend);
    }

    private boolean isWeekend(Calendar calendar, Set<Integer> set) {
        return set.contains(Integer.valueOf(calendar.get(7)));
    }

    /* access modifiers changed from: protected */
    public boolean isHoliday(double d, double[] dArr) {
        for (double round : dArr) {
            if (Math.round(round) == Math.round(d)) {
                return true;
            }
        }
        return false;
    }
}
