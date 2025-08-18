package org.apache.poi.ss.usermodel;

import androidx.exifinterface.media.ExifInterface;
import java.math.RoundingMode;
import java.text.DateFormatSymbols;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.FieldPosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import org.apache.commons.compress.archivers.tar.TarConstants;
import org.apache.poi.util.LocaleUtil;

public class ExcelStyleDateFormatter extends SimpleDateFormat {
    public static final char HH_BRACKET_SYMBOL = '';
    public static final char H_BRACKET_SYMBOL = '';
    public static final char LL_BRACKET_SYMBOL = '';
    public static final char L_BRACKET_SYMBOL = '';
    public static final char MMMMM_START_SYMBOL = '';
    public static final char MMMMM_TRUNCATE_SYMBOL = '';
    public static final char MM_BRACKET_SYMBOL = '';
    public static final char M_BRACKET_SYMBOL = '';
    public static final char SS_BRACKET_SYMBOL = '';
    public static final char S_BRACKET_SYMBOL = '';
    private static final DecimalFormat format1digit;
    private static final DecimalFormat format2digits;
    private static final DecimalFormat format3digit;
    private static final DecimalFormat format4digits;
    private double dateToBeFormatted;

    static {
        DecimalFormatSymbols instance = DecimalFormatSymbols.getInstance(Locale.ROOT);
        DecimalFormat decimalFormat = new DecimalFormat("0", instance);
        format1digit = decimalFormat;
        DecimalFormat decimalFormat2 = new DecimalFormat(TarConstants.VERSION_POSIX, instance);
        format2digits = decimalFormat2;
        DecimalFormat decimalFormat3 = new DecimalFormat("0", instance);
        format3digit = decimalFormat3;
        DecimalFormat decimalFormat4 = new DecimalFormat(TarConstants.VERSION_POSIX, instance);
        format4digits = decimalFormat4;
        DataFormatter.setExcelStyleRoundingMode(decimalFormat, RoundingMode.DOWN);
        DataFormatter.setExcelStyleRoundingMode(decimalFormat2, RoundingMode.DOWN);
        DataFormatter.setExcelStyleRoundingMode(decimalFormat3);
        DataFormatter.setExcelStyleRoundingMode(decimalFormat4);
    }

    public ExcelStyleDateFormatter(String str) {
        super(processFormatPattern(str), LocaleUtil.getUserLocale());
        setTimeZone(LocaleUtil.getUserTimeZone());
    }

    public ExcelStyleDateFormatter(String str, DateFormatSymbols dateFormatSymbols) {
        super(processFormatPattern(str), dateFormatSymbols);
        setTimeZone(LocaleUtil.getUserTimeZone());
    }

    public ExcelStyleDateFormatter(String str, Locale locale) {
        super(processFormatPattern(str), locale);
        setTimeZone(LocaleUtil.getUserTimeZone());
    }

    private static String processFormatPattern(String str) {
        return str.replace("MMMMM", "MMM").replace("[H]", String.valueOf(H_BRACKET_SYMBOL)).replace("[HH]", String.valueOf(HH_BRACKET_SYMBOL)).replace("[m]", String.valueOf(M_BRACKET_SYMBOL)).replace("[mm]", String.valueOf(MM_BRACKET_SYMBOL)).replace("[s]", String.valueOf(S_BRACKET_SYMBOL)).replace("[ss]", String.valueOf(SS_BRACKET_SYMBOL)).replace(ExifInterface.GPS_DIRECTION_TRUE, "'T'").replace("''T''", "'T'").replaceAll("s.000", "s.SSS").replaceAll("s.00", "s.").replaceAll("s.0", "s.");
    }

    public void setDateToBeFormatted(double d) {
        this.dateToBeFormatted = d;
    }

    public StringBuffer format(Date date, StringBuffer stringBuffer, FieldPosition fieldPosition) {
        String stringBuffer2 = super.format(date, stringBuffer, fieldPosition).toString();
        if (stringBuffer2.indexOf(57345) != -1) {
            stringBuffer2 = stringBuffer2.replaceAll("(\\p{L}|\\p{P})[\\p{L}\\p{P}]+", "$1");
        }
        if (!(stringBuffer2.indexOf(57360) == -1 && stringBuffer2.indexOf(57361) == -1)) {
            double d = (double) (((float) this.dateToBeFormatted) * 24.0f);
            stringBuffer2 = stringBuffer2.replaceAll(String.valueOf(H_BRACKET_SYMBOL), format1digit.format(d)).replaceAll(String.valueOf(HH_BRACKET_SYMBOL), format2digits.format(d));
        }
        if (!(stringBuffer2.indexOf(57362) == -1 && stringBuffer2.indexOf(57363) == -1)) {
            double d2 = (double) (((float) this.dateToBeFormatted) * 24.0f * 60.0f);
            stringBuffer2 = stringBuffer2.replaceAll(String.valueOf(M_BRACKET_SYMBOL), format1digit.format(d2)).replaceAll(String.valueOf(MM_BRACKET_SYMBOL), format2digits.format(d2));
        }
        if (!(stringBuffer2.indexOf(57364) == -1 && stringBuffer2.indexOf(57365) == -1)) {
            double d3 = (double) ((float) (this.dateToBeFormatted * 24.0d * 60.0d * 60.0d));
            stringBuffer2 = stringBuffer2.replaceAll(String.valueOf(S_BRACKET_SYMBOL), format1digit.format(d3)).replaceAll(String.valueOf(SS_BRACKET_SYMBOL), format2digits.format(d3));
        }
        if (!(stringBuffer2.indexOf(57366) == -1 && stringBuffer2.indexOf(57367) == -1)) {
            double d4 = this.dateToBeFormatted;
            float floor = (float) ((d4 - Math.floor(d4)) * 24.0d * 60.0d * 60.0d);
            double d5 = (double) (floor - ((float) ((int) floor)));
            stringBuffer2 = stringBuffer2.replaceAll(String.valueOf(L_BRACKET_SYMBOL), format3digit.format(10.0d * d5)).replaceAll(String.valueOf(LL_BRACKET_SYMBOL), format4digits.format(d5 * 100.0d));
        }
        return new StringBuffer(stringBuffer2);
    }

    public boolean equals(Object obj) {
        if ((obj instanceof ExcelStyleDateFormatter) && this.dateToBeFormatted == ((ExcelStyleDateFormatter) obj).dateToBeFormatted) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return Double.valueOf(this.dateToBeFormatted).hashCode();
    }
}
