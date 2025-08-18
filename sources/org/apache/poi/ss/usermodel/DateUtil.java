package org.apache.poi.ss.usermodel;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAccessor;
import java.time.temporal.TemporalQueries;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
import java.util.regex.Pattern;
import org.apache.commons.compress.archivers.tar.TarConstants;
import org.apache.logging.log4j.message.ParameterizedMessage;
import org.apache.poi.common.usermodel.fonts.FontHeader;
import org.apache.poi.ss.formula.ConditionalFormattingEvaluator;
import org.apache.poi.util.LocaleUtil;

public class DateUtil {
    private static final int BAD_DATE = -1;
    private static final BigDecimal BD_MILISEC_RND = BigDecimal.valueOf(500000.0d);
    private static final BigDecimal BD_NANOSEC_DAY = BigDecimal.valueOf(8.64E13d);
    private static final BigDecimal BD_SECOND_RND = BigDecimal.valueOf(5.0E8d);
    public static final long DAY_MILLISECONDS = 86400000;
    public static final int HOURS_PER_DAY = 24;
    public static final int MINUTES_PER_HOUR = 60;
    public static final int SECONDS_PER_DAY = 86400;
    public static final int SECONDS_PER_MINUTE = 60;
    private static final Pattern TIME_SEPARATOR_PATTERN = Pattern.compile(ParameterizedMessage.ERROR_MSG_SEPARATOR);
    private static final DateTimeFormatter dateTimeFormats = new DateTimeFormatterBuilder().appendPattern("[dd MMM[ yyyy]][[ ]h:m[:s][.SSS] a][[ ]H:m[:s][.SSS]]").appendPattern("[[yyyy ]dd-MMM[-yyyy]][[ ]h:m[:s][.SSS] a][[ ]H:m[:s][.SSS]]").appendPattern("[M/dd[/yyyy]][[ ]h:m[:s][.SSS] a][[ ]H:m[:s][.SSS]]").appendPattern("[[yyyy/]M/dd][[ ]h:m[:s][.SSS] a][[ ]H:m[:s][.SSS]]").parseDefaulting(ChronoField.YEAR_OF_ERA, (long) LocaleUtil.getLocaleCalendar().get(1)).toFormatter(LocaleUtil.getUserLocale());
    private static final Pattern date_ptrn1 = Pattern.compile("^\\[\\$-.*?]");
    private static final Pattern date_ptrn2 = Pattern.compile("^\\[[a-zA-Z]+]");
    private static final Pattern date_ptrn3a = Pattern.compile("[yYmMdDhHsS]");
    private static final Pattern date_ptrn3b = Pattern.compile("^[\\[\\]yYmMdDhHsS\\-T/年月日,. :\"\\\\]+0*[ampAMP/]*$");
    private static final Pattern date_ptrn4 = Pattern.compile("^\\[([hH]+|[mM]+|[sS]+)]");
    private static final Pattern date_ptrn5 = Pattern.compile("^\\[DBNum([123])]");
    private static final ThreadLocal<Boolean> lastCachedResult = new ThreadLocal<>();
    private static final ThreadLocal<Integer> lastFormatIndex = ThreadLocal.withInitial(new DateUtil$$ExternalSyntheticLambda0());
    private static final ThreadLocal<String> lastFormatString = new ThreadLocal<>();

    public static boolean isInternalDateFormat(int i) {
        switch (i) {
            case 14:
            case 15:
            case 16:
            case 17:
            case 18:
            case 19:
            case 20:
            case 21:
            case 22:
                return true;
            default:
                switch (i) {
                    case 45:
                    case 46:
                    case 47:
                        return true;
                    default:
                        return false;
                }
        }
    }

    public static boolean isValidExcelDate(double d) {
        return d > -4.9E-324d;
    }

    private DateUtil() {
    }

    public static LocalDateTime toLocalDateTime(Date date) {
        return date.toInstant().atZone(LocaleUtil.TIMEZONE_UTC.toZoneId()).toLocalDateTime();
    }

    public static LocalDateTime toLocalDateTime(Calendar calendar) {
        return calendar.toInstant().atZone(LocaleUtil.TIMEZONE_UTC.toZoneId()).toLocalDateTime();
    }

    public static double getExcelDate(LocalDate localDate) {
        return getExcelDate(localDate, false);
    }

    public static double getExcelDate(LocalDate localDate, boolean z) {
        return internalGetExcelDate(localDate.getYear(), localDate.getDayOfYear(), 0, 0, 0, 0, z);
    }

    public static double getExcelDate(LocalDateTime localDateTime) {
        return getExcelDate(localDateTime, false);
    }

    public static double getExcelDate(LocalDateTime localDateTime, boolean z) {
        return internalGetExcelDate(localDateTime.getYear(), localDateTime.getDayOfYear(), localDateTime.getHour(), localDateTime.getMinute(), localDateTime.getSecond(), localDateTime.getNano() / 1000000, z);
    }

    public static double getExcelDate(Date date) {
        return getExcelDate(date, false);
    }

    public static double getExcelDate(Date date, boolean z) {
        Calendar localeCalendar = LocaleUtil.getLocaleCalendar();
        localeCalendar.setTime(date);
        return internalGetExcelDate(localeCalendar.get(1), localeCalendar.get(6), localeCalendar.get(11), localeCalendar.get(12), localeCalendar.get(13), localeCalendar.get(14), z);
    }

    public static double getExcelDate(Calendar calendar, boolean z) {
        return internalGetExcelDate(calendar.get(1), calendar.get(6), calendar.get(11), calendar.get(12), calendar.get(13), calendar.get(14), z);
    }

    private static double internalGetExcelDate(int i, int i2, int i3, int i4, int i5, int i6, boolean z) {
        if (!z && i < 1900) {
            return -1.0d;
        }
        if (z && i < 1904) {
            return -1.0d;
        }
        double absoluteDay = (((((((((double) i3) * 60.0d) + ((double) i4)) * 60.0d) + ((double) i5)) * 1000.0d) + ((double) i6)) / 8.64E7d) + ((double) absoluteDay(i, i2, z));
        if (z || absoluteDay < 60.0d) {
            return z ? absoluteDay - 1.0d : absoluteDay;
        }
        return absoluteDay + 1.0d;
    }

    public static Date getJavaDate(double d, TimeZone timeZone) {
        return getJavaDate(d, false, timeZone, false);
    }

    public static Date getJavaDate(double d) {
        return getJavaDate(d, false, (TimeZone) null, false);
    }

    public static Date getJavaDate(double d, boolean z, TimeZone timeZone) {
        return getJavaDate(d, z, timeZone, false);
    }

    public static Date getJavaDate(double d, boolean z, TimeZone timeZone, boolean z2) {
        Calendar javaCalendar = getJavaCalendar(d, z, timeZone, z2);
        if (javaCalendar == null) {
            return null;
        }
        return javaCalendar.getTime();
    }

    public static Date getJavaDate(double d, boolean z) {
        return getJavaDate(d, z, (TimeZone) null, false);
    }

    public static LocalDateTime getLocalDateTime(double d) {
        return getLocalDateTime(d, false, false);
    }

    public static LocalDateTime getLocalDateTime(double d, boolean z) {
        return getLocalDateTime(d, z, false);
    }

    public static LocalDateTime getLocalDateTime(double d, boolean z, boolean z2) {
        int i;
        int i2;
        if (!isValidExcelDate(d)) {
            return null;
        }
        BigDecimal valueOf = BigDecimal.valueOf(d);
        int intValue = valueOf.intValue();
        if (z) {
            i = 1904;
            i2 = 1;
        } else if (intValue < 61) {
            i = 1900;
            i2 = 0;
        } else {
            i2 = -1;
            i = 1900;
        }
        return LocalDateTime.of(i, 1, 1, 0, 0).plusDays(((long) (i2 + intValue)) - 1).plusNanos(valueOf.subtract(BigDecimal.valueOf((long) intValue)).multiply(BD_NANOSEC_DAY).add(z2 ? BD_SECOND_RND : BD_MILISEC_RND).longValue()).truncatedTo(z2 ? ChronoUnit.SECONDS : ChronoUnit.MILLIS);
    }

    public static void setCalendar(Calendar calendar, int i, int i2, boolean z, boolean z2) {
        int i3;
        int i4;
        if (z) {
            i4 = 1;
            i3 = 1904;
        } else {
            i3 = 1900;
            i4 = i < 61 ? 0 : -1;
        }
        calendar.set(i3, 0, i + i4, 0, 0, 0);
        calendar.set(14, i2);
        if (calendar.get(14) == 0) {
            calendar.clear(14);
        }
        if (z2) {
            calendar.add(14, 500);
            calendar.clear(14);
        }
    }

    public static Calendar getJavaCalendar(double d) {
        return getJavaCalendar(d, false, (TimeZone) null, false);
    }

    public static Calendar getJavaCalendar(double d, boolean z) {
        return getJavaCalendar(d, z, (TimeZone) null, false);
    }

    public static Calendar getJavaCalendarUTC(double d, boolean z) {
        return getJavaCalendar(d, z, LocaleUtil.TIMEZONE_UTC, false);
    }

    public static Calendar getJavaCalendar(double d, boolean z, TimeZone timeZone) {
        return getJavaCalendar(d, z, timeZone, false);
    }

    public static Calendar getJavaCalendar(double d, boolean z, TimeZone timeZone, boolean z2) {
        Calendar calendar;
        if (!isValidExcelDate(d)) {
            return null;
        }
        int floor = (int) Math.floor(d);
        int i = (int) (((d - ((double) floor)) * 8.64E7d) + 0.5d);
        if (timeZone != null) {
            calendar = LocaleUtil.getLocaleCalendar(timeZone);
        } else {
            calendar = LocaleUtil.getLocaleCalendar();
        }
        setCalendar(calendar, floor, i, z, z2);
        return calendar;
    }

    static /* synthetic */ Integer lambda$static$0() {
        return -1;
    }

    private static boolean isCached(String str, int i) {
        return i == lastFormatIndex.get().intValue() && str.equals(lastFormatString.get());
    }

    private static void cache(String str, int i, boolean z) {
        if (str == null || "".equals(str)) {
            lastFormatString.remove();
        } else {
            lastFormatString.set(str);
        }
        if (i == -1) {
            lastFormatIndex.remove();
        } else {
            lastFormatIndex.set(Integer.valueOf(i));
        }
        lastCachedResult.set(Boolean.valueOf(z));
    }

    public static boolean isADateFormat(ExcelNumberFormat excelNumberFormat) {
        if (excelNumberFormat == null) {
            return false;
        }
        return isADateFormat(excelNumberFormat.getIdx(), excelNumberFormat.getFormat());
    }

    public static boolean isADateFormat(int i, String str) {
        if (isInternalDateFormat(i)) {
            cache(str, i, true);
            return true;
        } else if (str == null || str.length() == 0) {
            return false;
        } else {
            if (isCached(str, i)) {
                return lastCachedResult.get().booleanValue();
            }
            int length = str.length();
            StringBuilder sb = new StringBuilder(length);
            int i2 = 0;
            while (i2 < length) {
                char charAt = str.charAt(i2);
                if (i2 < length - 1) {
                    int i3 = i2 + 1;
                    char charAt2 = str.charAt(i3);
                    if (charAt == '\\') {
                        if (!(charAt2 == ' ' || charAt2 == '\\')) {
                            switch (charAt2) {
                                case ',':
                                case '-':
                                case '.':
                                    break;
                            }
                        }
                        i2++;
                    } else if (charAt == ';' && charAt2 == '@') {
                        i2 = i3;
                        i2++;
                    }
                }
                sb.append(charAt);
                i2++;
            }
            String sb2 = sb.toString();
            if (date_ptrn4.matcher(sb2).matches()) {
                cache(str, i, true);
                return true;
            }
            String replaceAll = date_ptrn2.matcher(date_ptrn1.matcher(date_ptrn5.matcher(sb2).replaceAll("")).replaceAll("")).replaceAll("");
            int indexOf = replaceAll.indexOf(59);
            if (indexOf > 0 && indexOf < replaceAll.length() - 1) {
                replaceAll = replaceAll.substring(0, indexOf);
            }
            if (!date_ptrn3a.matcher(replaceAll).find()) {
                return false;
            }
            boolean matches = date_ptrn3b.matcher(replaceAll).matches();
            cache(str, i, matches);
            return matches;
        }
    }

    public static boolean isCellDateFormatted(Cell cell) {
        return isCellDateFormatted(cell, (ConditionalFormattingEvaluator) null);
    }

    public static boolean isCellDateFormatted(Cell cell, ConditionalFormattingEvaluator conditionalFormattingEvaluator) {
        ExcelNumberFormat from;
        if (cell == null || !isValidExcelDate(cell.getNumericCellValue()) || (from = ExcelNumberFormat.from(cell, conditionalFormattingEvaluator)) == null) {
            return false;
        }
        return isADateFormat(from);
    }

    public static boolean isCellInternalDateFormatted(Cell cell) {
        if (cell != null && isValidExcelDate(cell.getNumericCellValue())) {
            return isInternalDateFormat(cell.getCellStyle().getDataFormat());
        }
        return false;
    }

    protected static int absoluteDay(Calendar calendar, boolean z) {
        return absoluteDay(calendar.get(1), calendar.get(6), z);
    }

    protected static int absoluteDay(LocalDateTime localDateTime, boolean z) {
        return absoluteDay(localDateTime.getYear(), localDateTime.getDayOfYear(), z);
    }

    private static int absoluteDay(int i, int i2, boolean z) {
        return i2 + daysInPriorYears(i, z);
    }

    static int daysInPriorYears(int i, boolean z) {
        int i2 = 1900;
        if ((z || i >= 1900) && (!z || i >= 1904)) {
            int i3 = i - 1;
            int i4 = (((i3 / 4) - (i3 / 100)) + (i3 / FontHeader.REGULAR_WEIGHT)) - 460;
            if (z) {
                i2 = 1904;
            }
            return ((i - i2) * 365) + i4;
        }
        throw new IllegalArgumentException("'year' must be 1900 or greater");
    }

    private static Calendar dayStart(Calendar calendar) {
        calendar.get(11);
        calendar.set(11, 0);
        calendar.set(12, 0);
        calendar.set(13, 0);
        calendar.set(14, 0);
        calendar.get(11);
        return calendar;
    }

    private static final class FormatException extends Exception {
        public FormatException(String str) {
            super(str);
        }
    }

    public static double convertTime(String str) {
        try {
            return convertTimeInternal(str);
        } catch (FormatException e) {
            throw new IllegalArgumentException("Bad time format '" + str + "' expected 'HH:MM' or 'HH:MM:SS' - " + e.getMessage());
        }
    }

    private static double convertTimeInternal(String str) throws FormatException {
        String str2;
        int length = str.length();
        if (length < 4 || length > 8) {
            throw new FormatException("Bad length");
        }
        String[] split = TIME_SEPARATOR_PATTERN.split(str);
        int length2 = split.length;
        if (length2 == 2) {
            str2 = TarConstants.VERSION_POSIX;
        } else if (length2 == 3) {
            str2 = split[2];
        } else {
            throw new FormatException("Expected 2 or 3 fields but got (" + split.length + ")");
        }
        String str3 = split[0];
        String str4 = split[1];
        int parseInt = parseInt(str3, "hour", 24);
        return (((double) parseInt(str2, "second", 60)) + ((((double) parseInt(str4, "minute", 60)) + (((double) parseInt) * 60.0d)) * 60.0d)) / 86400.0d;
    }

    public static Date parseYYYYMMDDDate(String str) {
        try {
            return parseYYYYMMDDDateInternal(str);
        } catch (FormatException e) {
            throw new IllegalArgumentException("Bad time format " + str + " expected 'YYYY/MM/DD' - " + e.getMessage());
        }
    }

    private static Date parseYYYYMMDDDateInternal(String str) throws FormatException {
        if (str.length() == 10) {
            String substring = str.substring(0, 4);
            String substring2 = str.substring(5, 7);
            String substring3 = str.substring(8, 10);
            return LocaleUtil.getLocaleCalendar(parseInt(substring, "year", -32768, 32767), parseInt(substring2, "month", 1, 12) - 1, parseInt(substring3, "day", 1, 31)).getTime();
        }
        throw new FormatException("Bad length");
    }

    private static int parseInt(String str, String str2, int i) throws FormatException {
        return parseInt(str, str2, 0, i - 1);
    }

    private static int parseInt(String str, String str2, int i, int i2) throws FormatException {
        try {
            int parseInt = Integer.parseInt(str);
            if (parseInt >= i && parseInt <= i2) {
                return parseInt;
            }
            throw new FormatException(str2 + " value (" + parseInt + ") is outside the allowable range(0.." + i2 + ")");
        } catch (NumberFormatException unused) {
            throw new FormatException("Bad int format '" + str + "' for " + str2 + " field");
        }
    }

    public static Double parseDateTime(String str) {
        TemporalAccessor parse = dateTimeFormats.parse(str.replaceAll("\\s+", " "));
        LocalTime localTime = (LocalTime) parse.query(TemporalQueries.localTime());
        LocalDate localDate = (LocalDate) parse.query(TemporalQueries.localDate());
        if (localTime == null && localDate == null) {
            return null;
        }
        double excelDate = localDate != null ? getExcelDate(Date.from(localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant())) : 0.0d;
        if (localTime != null) {
            excelDate += (((double) localTime.toSecondOfDay()) * 1.0d) / 86400.0d;
        }
        return Double.valueOf(excelDate);
    }
}
