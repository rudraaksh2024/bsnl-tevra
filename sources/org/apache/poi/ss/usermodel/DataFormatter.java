package org.apache.poi.ss.usermodel;

import androidx.exifinterface.media.ExifInterface;
import java.beans.PropertyChangeSupport;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DateFormat;
import java.text.DateFormatSymbols;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.FieldPosition;
import java.text.Format;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.io.IOUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.util.Chars;
import org.apache.logging.log4j.util.ProcessIdUtil;
import org.apache.poi.openxml4j.opc.PackagingURIHelper;
import org.apache.poi.ss.format.CellFormat;
import org.apache.poi.ss.format.CellFormatResult;
import org.apache.poi.ss.formula.ConditionalFormattingEvaluator;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.util.LocaleUtil;

public class DataFormatter {
    private static final Logger LOG = LogManager.getLogger((Class<?>) DataFormatter.class);
    private static final Pattern alternateGrouping = Pattern.compile("([#0]([^.#0])[#0]{3})");
    private static final Pattern amPmPattern = Pattern.compile("(([AP])[M/P]*)", 2);
    private static final Pattern colorPattern = Pattern.compile("(\\[BLACK])|(\\[BLUE])|(\\[CYAN])|(\\[GREEN])|(\\[MAGENTA])|(\\[RED])|(\\[WHITE])|(\\[YELLOW])|(\\[COLOR\\s*\\d])|(\\[COLOR\\s*[0-5]\\d])", 2);
    private static final Pattern daysAsText = Pattern.compile("([d]{3,})", 2);
    private static final String defaultFractionFractionPartFormat = "#/##";
    private static final String defaultFractionWholePartFormat = "#";
    private static final Pattern fractionPattern = Pattern.compile("(?:([#\\d]+)\\s+)?(#+)\\s*/\\s*([#\\d]+)");
    private static final Pattern fractionStripper = Pattern.compile("(\"[^\"]*\")|([^ ?#\\d/]+)");
    private static final String invalidDateTimeString;
    private static final Pattern localePatternGroup = Pattern.compile("(\\[\\$[^-\\]]*-[0-9A-Z]+])");
    private static final Pattern numPattern = Pattern.compile("[0#]+");
    private static final Pattern rangeConditionalPattern = Pattern.compile(".*\\[\\s*(>|>=|<|<=|=)\\s*[0-9]*\\.*[0-9].*");
    private DateFormatSymbols dateSymbols;
    private DecimalFormatSymbols decimalSymbols;
    private DateFormat defaultDateformat;
    private Format defaultNumFormat;
    /* access modifiers changed from: private */
    public boolean emulateCSV;
    private final Map<String, Format> formats;
    private Format generalNumberFormat;
    private Locale locale;
    private boolean localeIsAdapting;
    private final PropertyChangeSupport pcs;
    private boolean use4DigitYearsInAllDateFormats;
    private boolean useCachedValuesForFormulaCells;

    static {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 255; i++) {
            sb.append('#');
        }
        invalidDateTimeString = sb.toString();
    }

    public DataFormatter() {
        this(false);
    }

    public DataFormatter(boolean z) {
        this(LocaleUtil.getUserLocale(), true, z);
    }

    public DataFormatter(Locale locale2) {
        this(locale2, false);
    }

    public DataFormatter(Locale locale2, boolean z) {
        this(locale2, false, z);
    }

    public DataFormatter(Locale locale2, boolean z, boolean z2) {
        this.formats = new HashMap();
        this.emulateCSV = false;
        this.use4DigitYearsInAllDateFormats = false;
        this.useCachedValuesForFormulaCells = false;
        this.localeIsAdapting = true;
        this.pcs = new PropertyChangeSupport(this);
        checkForLocaleChange(locale2);
        this.localeIsAdapting = z;
        this.emulateCSV = z2;
    }

    public void setEmulateCSV(boolean z) {
        this.emulateCSV = z;
    }

    public boolean isEmulateCSV() {
        return this.emulateCSV;
    }

    public void setUseCachedValuesForFormulaCells(boolean z) {
        this.useCachedValuesForFormulaCells = z;
    }

    public boolean useCachedValuesForFormulaCells() {
        return this.useCachedValuesForFormulaCells;
    }

    public void setUse4DigitYearsInAllDateFormats(boolean z) {
        this.use4DigitYearsInAllDateFormats = z;
    }

    public boolean use4DigitYearsInAllDateFormats() {
        return this.use4DigitYearsInAllDateFormats;
    }

    private Format getFormat(Cell cell, ConditionalFormattingEvaluator conditionalFormattingEvaluator) {
        ExcelNumberFormat from;
        if (cell == null || (from = ExcelNumberFormat.from(cell, conditionalFormattingEvaluator)) == null) {
            return null;
        }
        int idx = from.getIdx();
        String format = from.getFormat();
        if (format == null || format.trim().length() == 0) {
            return null;
        }
        return getFormat(cell.getNumericCellValue(), idx, format, isDate1904(cell));
    }

    private boolean isDate1904(Cell cell) {
        if (cell == null || !(cell.getSheet().getWorkbook() instanceof Date1904Support)) {
            return false;
        }
        return ((Date1904Support) cell.getSheet().getWorkbook()).isDate1904();
    }

    private Format getFormat(double d, int i, String str, boolean z) {
        Object obj;
        checkForLocaleChange();
        String replace = str.replace("\\%", "'%'");
        if (replace.contains(";") && (replace.indexOf(59) != replace.lastIndexOf(59) || rangeConditionalPattern.matcher(replace).matches())) {
            try {
                CellFormat instance = CellFormat.getInstance(this.locale, replace);
                if (d == 0.0d || !DateUtil.isADateFormat(i, replace)) {
                    obj = Double.valueOf(d);
                } else {
                    obj = DateUtil.getJavaDate(d, z);
                }
                return new CellFormatResultWrapper(this, instance.apply(obj), (AnonymousClass1) null);
            } catch (Exception e) {
                LOG.atWarn().withThrowable(e).log("Formatting failed for format {}, falling back", (Object) replace);
            }
        }
        if (this.emulateCSV && d == 0.0d && replace.contains(defaultFractionWholePartFormat) && !replace.contains("0")) {
            replace = replace.replace(defaultFractionWholePartFormat, "");
        }
        Format format = this.formats.get(replace);
        if (format != null) {
            return format;
        }
        if ("General".equalsIgnoreCase(replace) || "@".equals(replace)) {
            return this.generalNumberFormat;
        }
        Format createFormat = createFormat(d, i, replace);
        this.formats.put(replace, createFormat);
        return createFormat;
    }

    public Format createFormat(Cell cell) {
        return createFormat(cell.getNumericCellValue(), cell.getCellStyle().getDataFormat(), cell.getCellStyle().getDataFormatString());
    }

    private Format createFormat(double d, int i, String str) {
        String group;
        int indexOf;
        checkForLocaleChange();
        int i2 = 0;
        if (str != null) {
            Matcher matcher = colorPattern.matcher(str);
            while (matcher.find() && (indexOf = str.indexOf((group = matcher.group()))) != -1) {
                String str2 = str.substring(0, indexOf) + str.substring(indexOf + group.length());
                if (str2.equals(str)) {
                    break;
                }
                String str3 = str2;
                matcher = colorPattern.matcher(str2);
                str = str3;
            }
        }
        if (str != null) {
            Matcher matcher2 = localePatternGroup.matcher(str);
            while (matcher2.find()) {
                String group2 = matcher2.group();
                String substring = group2.substring(group2.indexOf(36) + 1, group2.indexOf(45));
                if (substring.indexOf(36) > -1) {
                    substring = substring.substring(0, substring.indexOf(36)) + IOUtils.DIR_SEPARATOR_WINDOWS + substring.substring(substring.indexOf(36));
                }
                str = matcher2.replaceAll(substring);
                matcher2 = localePatternGroup.matcher(str);
            }
        }
        if (str == null || str.trim().isEmpty()) {
            return getDefaultFormat(d);
        }
        if ("General".equalsIgnoreCase(str) || "@".equals(str)) {
            return this.generalNumberFormat;
        }
        if (DateUtil.isADateFormat(i, str) && DateUtil.isValidExcelDate(d)) {
            return createDateFormat(str, d);
        }
        if (str.contains("#/") || str.contains("?/")) {
            String[] split = str.split(";");
            int length = split.length;
            while (true) {
                String str4 = defaultFractionWholePartFormat;
                if (i2 >= length) {
                    return new FractionFormat(str4, defaultFractionFractionPartFormat);
                }
                Matcher matcher3 = fractionPattern.matcher(fractionStripper.matcher(split[i2].replace("?", str4)).replaceAll(" ").replaceAll(" +", " "));
                if (matcher3.find()) {
                    if (matcher3.group(1) == null) {
                        str4 = "";
                    }
                    return new FractionFormat(str4, matcher3.group(3));
                }
                i2++;
            }
        } else if (numPattern.matcher(str).find()) {
            return createNumberFormat(str, d);
        } else {
            if (this.emulateCSV) {
                return new ConstantStringFormat(cleanFormatForNumber(str));
            }
            return null;
        }
    }

    /* access modifiers changed from: package-private */
    public String adjustTo4DigitYearsIfConfigured(String str) {
        int indexOf;
        if (!this.use4DigitYearsInAllDateFormats || (indexOf = str.indexOf("yy")) < 0) {
            return str;
        }
        int indexOf2 = str.indexOf("yyy");
        if (str.indexOf("yyyy") == indexOf) {
            int i = indexOf + 4;
            return str.substring(0, i) + adjustTo4DigitYearsIfConfigured(str.substring(i));
        } else if (indexOf2 == indexOf) {
            return str;
        } else {
            int i2 = indexOf + 2;
            return str.substring(0, i2) + "yy" + adjustTo4DigitYearsIfConfigured(str.substring(i2));
        }
    }

    private Format createDateFormat(String str, double d) {
        int i;
        char c;
        String replace = adjustTo4DigitYearsIfConfigured(str).replace("\\-", ProcessIdUtil.DEFAULT_PROCESSID).replace("\\,", ",").replace("\\.", ".").replace("\\ ", " ").replace("\\/", PackagingURIHelper.FORWARD_SLASH_STRING).replace(";@", "").replace("\"/\"", PackagingURIHelper.FORWARD_SLASH_STRING).replace("\"\"", "'").replace("\\T", "'T'");
        Matcher matcher = amPmPattern.matcher(replace);
        boolean z = false;
        boolean z2 = false;
        while (matcher.find()) {
            replace = matcher.replaceAll("@");
            matcher = amPmPattern.matcher(replace);
            z2 = true;
        }
        String replace2 = replace.replace('@', 'a');
        Matcher matcher2 = daysAsText.matcher(replace2);
        if (matcher2.find()) {
            replace2 = matcher2.replaceAll(matcher2.group(0).toUpperCase(Locale.ROOT).replace('D', 'E'));
        }
        StringBuilder sb = new StringBuilder();
        char[] charArray = replace2.toCharArray();
        ArrayList<Integer> arrayList = new ArrayList<>();
        int i2 = 0;
        boolean z3 = false;
        boolean z4 = true;
        while (i2 < charArray.length) {
            char c2 = charArray[i2];
            if (c2 == '\'') {
                sb.append(c2);
                do {
                    i2++;
                    if (i2 >= charArray.length) {
                        break;
                    }
                    c = charArray[i2];
                    sb.append(c);
                } while (c != '\'');
            } else if (c2 == '[' && !z3) {
                sb.append(c2);
                z4 = z;
                i = 1;
                z3 = true;
                i2 += i;
                z = false;
            } else if (c2 == ']' && z3) {
                sb.append(c2);
                z3 = z;
            } else if (z3) {
                if (c2 == 'h' || c2 == 'H') {
                    sb.append('H');
                } else if (c2 == 'm' || c2 == 'M') {
                    sb.append('m');
                } else if (c2 == 's' || c2 == 'S') {
                    sb.append('s');
                } else {
                    sb.append(c2);
                }
            } else if (c2 == 'h' || c2 == 'H') {
                if (z2) {
                    sb.append('h');
                } else {
                    sb.append('H');
                }
                i = 1;
                z4 = false;
                i2 += i;
                z = false;
            } else if (c2 != 'm' && c2 != 'M') {
                if (c2 == 's' || c2 == 'S') {
                    sb.append('s');
                    for (Integer intValue : arrayList) {
                        int intValue2 = intValue.intValue();
                        if (sb.charAt(intValue2) == 'M') {
                            sb.replace(intValue2, intValue2 + 1, "m");
                        }
                    }
                    arrayList.clear();
                } else if (Character.isLetter(c2)) {
                    arrayList.clear();
                    if (c2 == 'y' || c2 == 'Y') {
                        sb.append('y');
                    } else if (c2 == 'd' || c2 == 'D') {
                        sb.append('d');
                    } else {
                        sb.append(c2);
                    }
                } else {
                    if (Character.isWhitespace(c2)) {
                        arrayList.clear();
                    }
                    sb.append(c2);
                }
                i = 1;
                z4 = true;
                i2 += i;
                z = false;
            } else if (z4) {
                sb.append('M');
                arrayList.add(Integer.valueOf(sb.length() - 1));
            } else {
                sb.append('m');
            }
            i = 1;
            i2 += i;
            z = false;
        }
        String sb2 = sb.toString();
        try {
            return new ExcelStyleDateFormatter(sb2, this.dateSymbols);
        } catch (IllegalArgumentException e) {
            LOG.atDebug().withThrowable(e).log("Formatting failed for format {}, falling back", (Object) sb2);
            return getDefaultFormat(d);
        }
    }

    private String cleanFormatForNumber(String str) {
        StringBuilder sb = new StringBuilder(str.replace("\\%", "'%'"));
        int i = 0;
        if (this.emulateCSV) {
            int i2 = 0;
            while (i2 < sb.length()) {
                char charAt = sb.charAt(i2);
                if ((charAt == '_' || charAt == '*' || charAt == '?') && (i2 <= 0 || sb.charAt(i2 - 1) != '\\')) {
                    if (charAt == '?') {
                        sb.setCharAt(i2, Chars.SPACE);
                    } else if (i2 < sb.length() - 1) {
                        if (charAt == '_') {
                            sb.setCharAt(i2 + 1, Chars.SPACE);
                        } else {
                            sb.deleteCharAt(i2 + 1);
                        }
                        sb.deleteCharAt(i2);
                        i2--;
                    }
                }
                i2++;
            }
        } else {
            int i3 = 0;
            while (i3 < sb.length()) {
                char charAt2 = sb.charAt(i3);
                if ((charAt2 == '_' || charAt2 == '*') && (i3 <= 0 || sb.charAt(i3 - 1) != '\\')) {
                    if (i3 < sb.length() - 1) {
                        sb.deleteCharAt(i3 + 1);
                    }
                    sb.deleteCharAt(i3);
                    i3--;
                }
                i3++;
            }
        }
        while (i < sb.length()) {
            char charAt3 = sb.charAt(i);
            if (charAt3 == '\\' || charAt3 == '\"') {
                sb.deleteCharAt(i);
            } else {
                if ((charAt3 == '+' || charAt3 == '-') && i > 0 && sb.charAt(i - 1) == 'E') {
                    sb.deleteCharAt(i);
                }
                i++;
            }
            i--;
            i++;
        }
        return sb.toString();
    }

    private static class InternalDecimalFormatWithScale extends Format {
        private static final BigDecimal ONE_THOUSAND = BigDecimal.valueOf(1000);
        private static final Pattern endsWithCommas = Pattern.compile("(,+)$");
        private final DecimalFormat df;
        private final BigDecimal divider;

        private static String trimTrailingCommas(String str) {
            return str.replaceAll(",+$", "");
        }

        public InternalDecimalFormatWithScale(String str, DecimalFormatSymbols decimalFormatSymbols) {
            DecimalFormat decimalFormat = new DecimalFormat(trimTrailingCommas(str), decimalFormatSymbols);
            this.df = decimalFormat;
            DataFormatter.setExcelStyleRoundingMode(decimalFormat);
            Matcher matcher = endsWithCommas.matcher(str);
            if (matcher.find()) {
                String group = matcher.group(1);
                BigDecimal bigDecimal = BigDecimal.ONE;
                for (int i = 0; i < group.length(); i++) {
                    bigDecimal = bigDecimal.multiply(ONE_THOUSAND);
                }
                this.divider = bigDecimal;
                return;
            }
            this.divider = null;
        }

        private Object scaleInput(Object obj) {
            BigDecimal bigDecimal = this.divider;
            if (bigDecimal == null) {
                return obj;
            }
            if (obj instanceof BigDecimal) {
                return ((BigDecimal) obj).divide(bigDecimal, RoundingMode.HALF_UP);
            }
            if (obj instanceof Double) {
                return Double.valueOf(((Double) obj).doubleValue() / this.divider.doubleValue());
            }
            throw new UnsupportedOperationException();
        }

        public StringBuffer format(Object obj, StringBuffer stringBuffer, FieldPosition fieldPosition) {
            return this.df.format(scaleInput(obj), stringBuffer, fieldPosition);
        }

        public Object parseObject(String str, ParsePosition parsePosition) {
            throw new UnsupportedOperationException();
        }
    }

    private Format createNumberFormat(String str, double d) {
        char charAt;
        String cleanFormatForNumber = cleanFormatForNumber(str);
        DecimalFormatSymbols decimalFormatSymbols = this.decimalSymbols;
        Matcher matcher = alternateGrouping.matcher(cleanFormatForNumber);
        if (matcher.find() && (charAt = matcher.group(2).charAt(0)) != ',') {
            decimalFormatSymbols = DecimalFormatSymbols.getInstance(this.locale);
            decimalFormatSymbols.setGroupingSeparator(charAt);
            String group = matcher.group(1);
            cleanFormatForNumber = cleanFormatForNumber.replace(group, group.replace(charAt, ','));
        }
        try {
            return new InternalDecimalFormatWithScale(cleanFormatForNumber, decimalFormatSymbols);
        } catch (IllegalArgumentException e) {
            LOG.atDebug().withThrowable(e).log("Formatting failed for format {}, falling back", (Object) str);
            return getDefaultFormat(d);
        }
    }

    public Format getDefaultFormat(Cell cell) {
        return getDefaultFormat(cell.getNumericCellValue());
    }

    private Format getDefaultFormat(double d) {
        checkForLocaleChange();
        Format format = this.defaultNumFormat;
        if (format != null) {
            return format;
        }
        return this.generalNumberFormat;
    }

    private String performDateFormatting(Date date, Format format) {
        String format2;
        if (format == null) {
            format = this.defaultDateformat;
        }
        synchronized (format) {
            format2 = format.format(date);
        }
        return format2;
    }

    private String getFormattedDateString(Cell cell, ConditionalFormattingEvaluator conditionalFormattingEvaluator) {
        String performDateFormatting;
        if (cell == null) {
            return null;
        }
        Format format = getFormat(cell, conditionalFormattingEvaluator);
        if (format == null) {
            if (this.defaultDateformat == null) {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", DateFormatSymbols.getInstance(LocaleUtil.getUserLocale()));
                simpleDateFormat.setTimeZone(LocaleUtil.getUserTimeZone());
                format = simpleDateFormat;
            } else {
                format = this.defaultNumFormat;
            }
        }
        synchronized (format) {
            if (format instanceof ExcelStyleDateFormatter) {
                ((ExcelStyleDateFormatter) format).setDateToBeFormatted(cell.getNumericCellValue());
            }
            performDateFormatting = performDateFormatting(cell.getDateCellValue(), format);
        }
        return performDateFormatting;
    }

    private String getFormattedNumberString(Cell cell, ConditionalFormattingEvaluator conditionalFormattingEvaluator) {
        if (cell == null) {
            return null;
        }
        Format format = getFormat(cell, conditionalFormattingEvaluator);
        double numericCellValue = cell.getNumericCellValue();
        if (format == null) {
            return String.valueOf(numericCellValue);
        }
        return format.format(Double.valueOf(numericCellValue)).replaceFirst("E(\\d)", "E+$1");
    }

    public String formatRawCellContents(double d, int i, String str) {
        return formatRawCellContents(d, i, str, false);
    }

    public String formatRawCellContents(double d, int i, String str, boolean z) {
        String str2;
        checkForLocaleChange();
        if (DateUtil.isADateFormat(i, str)) {
            if (DateUtil.isValidExcelDate(d)) {
                Format format = getFormat(d, i, str, z);
                if (format instanceof ExcelStyleDateFormatter) {
                    ((ExcelStyleDateFormatter) format).setDateToBeFormatted(d);
                }
                return performDateFormatting(DateUtil.getJavaDate(d, z), format);
            } else if (this.emulateCSV) {
                return invalidDateTimeString;
            }
        }
        Format format2 = getFormat(d, i, str, z);
        if (format2 == null) {
            return String.valueOf(d);
        }
        String text = NumberToTextConverter.toText(d);
        if (text.indexOf(69) > -1) {
            str2 = format2.format(Double.valueOf(d));
        } else {
            str2 = format2.format(new BigDecimal(text));
        }
        String lowerCase = str.toLowerCase(Locale.ROOT);
        return ((lowerCase.contains("general") || lowerCase.contains("e+0")) && str2.contains(ExifInterface.LONGITUDE_EAST) && !str2.contains("E-")) ? str2.replaceFirst(ExifInterface.LONGITUDE_EAST, "E+") : str2;
    }

    public String formatCellValue(Cell cell) {
        return formatCellValue(cell, (FormulaEvaluator) null);
    }

    public String formatCellValue(Cell cell, FormulaEvaluator formulaEvaluator) {
        return formatCellValue(cell, formulaEvaluator, (ConditionalFormattingEvaluator) null);
    }

    public String formatCellValue(Cell cell, FormulaEvaluator formulaEvaluator, ConditionalFormattingEvaluator conditionalFormattingEvaluator) {
        checkForLocaleChange();
        if (cell == null) {
            return "";
        }
        CellType cellType = cell.getCellType();
        if (cellType == CellType.FORMULA) {
            if (formulaEvaluator != null) {
                cellType = formulaEvaluator.evaluateFormulaCell(cell);
            } else if (!this.useCachedValuesForFormulaCells) {
                return cell.getCellFormula();
            } else {
                try {
                    cellType = cell.getCachedFormulaResultType();
                } catch (Exception unused) {
                    return cell.getCellFormula();
                }
            }
        }
        int i = AnonymousClass1.$SwitchMap$org$apache$poi$ss$usermodel$CellType[cellType.ordinal()];
        if (i != 1) {
            if (i == 2) {
                return cell.getRichStringCellValue().getString();
            }
            if (i == 3) {
                return cell.getBooleanCellValue() ? "TRUE" : "FALSE";
            }
            if (i == 4) {
                return "";
            }
            if (i == 5) {
                return FormulaError.forInt(cell.getErrorCellValue()).getString();
            }
            throw new RuntimeException("Unexpected celltype (" + cellType + ")");
        } else if (DateUtil.isCellDateFormatted(cell, conditionalFormattingEvaluator)) {
            return getFormattedDateString(cell, conditionalFormattingEvaluator);
        } else {
            return getFormattedNumberString(cell, conditionalFormattingEvaluator);
        }
    }

    /* renamed from: org.apache.poi.ss.usermodel.DataFormatter$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$org$apache$poi$ss$usermodel$CellType;

        /* JADX WARNING: Can't wrap try/catch for region: R(12:0|1|2|3|4|5|6|7|8|9|10|12) */
        /* JADX WARNING: Code restructure failed: missing block: B:13:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0033 */
        static {
            /*
                org.apache.poi.ss.usermodel.CellType[] r0 = org.apache.poi.ss.usermodel.CellType.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$org$apache$poi$ss$usermodel$CellType = r0
                org.apache.poi.ss.usermodel.CellType r1 = org.apache.poi.ss.usermodel.CellType.NUMERIC     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$org$apache$poi$ss$usermodel$CellType     // Catch:{ NoSuchFieldError -> 0x001d }
                org.apache.poi.ss.usermodel.CellType r1 = org.apache.poi.ss.usermodel.CellType.STRING     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$org$apache$poi$ss$usermodel$CellType     // Catch:{ NoSuchFieldError -> 0x0028 }
                org.apache.poi.ss.usermodel.CellType r1 = org.apache.poi.ss.usermodel.CellType.BOOLEAN     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = $SwitchMap$org$apache$poi$ss$usermodel$CellType     // Catch:{ NoSuchFieldError -> 0x0033 }
                org.apache.poi.ss.usermodel.CellType r1 = org.apache.poi.ss.usermodel.CellType.BLANK     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                int[] r0 = $SwitchMap$org$apache$poi$ss$usermodel$CellType     // Catch:{ NoSuchFieldError -> 0x003e }
                org.apache.poi.ss.usermodel.CellType r1 = org.apache.poi.ss.usermodel.CellType.ERROR     // Catch:{ NoSuchFieldError -> 0x003e }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x003e }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x003e }
            L_0x003e:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.ss.usermodel.DataFormatter.AnonymousClass1.<clinit>():void");
        }
    }

    public void setDefaultNumberFormat(Format format) {
        for (Map.Entry next : this.formats.entrySet()) {
            if (next.getValue() == this.generalNumberFormat) {
                next.setValue(format);
            }
        }
        this.defaultNumFormat = format;
    }

    public void addFormat(String str, Format format) {
        this.formats.put(str, format);
    }

    /* access modifiers changed from: private */
    public static DecimalFormat createIntegerOnlyFormat(String str) {
        DecimalFormat decimalFormat = new DecimalFormat(str, DecimalFormatSymbols.getInstance(Locale.ROOT));
        decimalFormat.setParseIntegerOnly(true);
        return decimalFormat;
    }

    public static void setExcelStyleRoundingMode(DecimalFormat decimalFormat) {
        setExcelStyleRoundingMode(decimalFormat, RoundingMode.HALF_UP);
    }

    public static void setExcelStyleRoundingMode(DecimalFormat decimalFormat, RoundingMode roundingMode) {
        decimalFormat.setRoundingMode(roundingMode);
    }

    public PropertyChangeSupport getLocaleChangedObservable() {
        return this.pcs;
    }

    private void checkForLocaleChange() {
        checkForLocaleChange(LocaleUtil.getUserLocale());
    }

    private void checkForLocaleChange(Locale locale2) {
        if (this.localeIsAdapting && !locale2.equals(this.locale)) {
            updateLocale(locale2);
            this.pcs.firePropertyChange("locale", this.locale, locale2);
        }
    }

    public void updateLocale(Locale locale2) {
        if (this.localeIsAdapting && !locale2.equals(this.locale)) {
            this.locale = locale2;
            this.dateSymbols = DateFormatSymbols.getInstance(locale2);
            this.decimalSymbols = DecimalFormatSymbols.getInstance(this.locale);
            this.generalNumberFormat = new ExcelGeneralNumberFormat(this.locale);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", this.dateSymbols);
            this.defaultDateformat = simpleDateFormat;
            simpleDateFormat.setTimeZone(LocaleUtil.getUserTimeZone());
            this.formats.clear();
            Format format = ZipPlusFourFormat.instance;
            addFormat("00000\\-0000", format);
            addFormat("00000-0000", format);
            Format format2 = PhoneFormat.instance;
            addFormat("[<=9999999]###\\-####;\\(###\\)\\ ###\\-####", format2);
            addFormat("[<=9999999]###-####;(###) ###-####", format2);
            addFormat("###\\-####;\\(###\\)\\ ###\\-####", format2);
            addFormat("###-####;(###) ###-####", format2);
            Format format3 = SSNFormat.instance;
            addFormat("000\\-00\\-0000", format3);
            addFormat("000-00-0000", format3);
        }
    }

    private static final class SSNFormat extends Format {
        private static final DecimalFormat df = DataFormatter.createIntegerOnlyFormat("000000000");
        public static final Format instance = new SSNFormat();

        private SSNFormat() {
        }

        public static String format(Number number) {
            String format = df.format(number);
            return format.substring(0, 3) + '-' + format.substring(3, 5) + '-' + format.substring(5, 9);
        }

        public StringBuffer format(Object obj, StringBuffer stringBuffer, FieldPosition fieldPosition) {
            return stringBuffer.append(format((Number) obj));
        }

        public Object parseObject(String str, ParsePosition parsePosition) {
            return df.parseObject(str, parsePosition);
        }
    }

    private static final class ZipPlusFourFormat extends Format {
        private static final DecimalFormat df = DataFormatter.createIntegerOnlyFormat("000000000");
        public static final Format instance = new ZipPlusFourFormat();

        private ZipPlusFourFormat() {
        }

        public static String format(Number number) {
            String format = df.format(number);
            return format.substring(0, 5) + '-' + format.substring(5, 9);
        }

        public StringBuffer format(Object obj, StringBuffer stringBuffer, FieldPosition fieldPosition) {
            return stringBuffer.append(format((Number) obj));
        }

        public Object parseObject(String str, ParsePosition parsePosition) {
            return df.parseObject(str, parsePosition);
        }
    }

    private static final class PhoneFormat extends Format {
        private static final DecimalFormat df = DataFormatter.createIntegerOnlyFormat("##########");
        public static final Format instance = new PhoneFormat();

        private PhoneFormat() {
        }

        public static String format(Number number) {
            String format = df.format(number);
            StringBuilder sb = new StringBuilder();
            int length = format.length();
            if (length <= 4) {
                return format;
            }
            int i = length - 4;
            String substring = format.substring(i, length);
            int i2 = length - 7;
            String substring2 = format.substring(Math.max(0, i2), i);
            String substring3 = format.substring(Math.max(0, length - 10), Math.max(0, i2));
            if (substring3.trim().length() > 0) {
                sb.append('(').append(substring3).append(") ");
            }
            if (substring2.trim().length() > 0) {
                sb.append(substring2).append('-');
            }
            sb.append(substring);
            return sb.toString();
        }

        public StringBuffer format(Object obj, StringBuffer stringBuffer, FieldPosition fieldPosition) {
            return stringBuffer.append(format((Number) obj));
        }

        public Object parseObject(String str, ParsePosition parsePosition) {
            return df.parseObject(str, parsePosition);
        }
    }

    private static final class ConstantStringFormat extends Format {
        private static final DecimalFormat df = DataFormatter.createIntegerOnlyFormat("##########");
        private final String str;

        public ConstantStringFormat(String str2) {
            this.str = str2;
        }

        public StringBuffer format(Object obj, StringBuffer stringBuffer, FieldPosition fieldPosition) {
            return stringBuffer.append(this.str);
        }

        public Object parseObject(String str2, ParsePosition parsePosition) {
            return df.parseObject(str2, parsePosition);
        }
    }

    private final class CellFormatResultWrapper extends Format {
        private final CellFormatResult result;

        public Object parseObject(String str, ParsePosition parsePosition) {
            return null;
        }

        /* synthetic */ CellFormatResultWrapper(DataFormatter dataFormatter, CellFormatResult cellFormatResult, AnonymousClass1 r3) {
            this(cellFormatResult);
        }

        private CellFormatResultWrapper(CellFormatResult cellFormatResult) {
            this.result = cellFormatResult;
        }

        public StringBuffer format(Object obj, StringBuffer stringBuffer, FieldPosition fieldPosition) {
            if (DataFormatter.this.emulateCSV) {
                return stringBuffer.append(this.result.text);
            }
            return stringBuffer.append(this.result.text.trim());
        }
    }
}
