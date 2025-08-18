package org.apache.poi.ss.usermodel;

import java.math.BigDecimal;
import java.text.FieldPosition;
import java.text.Format;
import java.text.ParsePosition;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.openxml4j.opc.PackagingURIHelper;
import org.apache.poi.ss.format.SimpleFraction;
import org.apache.poi.ss.formula.eval.NotImplementedException;

public class FractionFormat extends Format {
    private static final Pattern DENOM_FORMAT_PATTERN = Pattern.compile("(?:(#+)|(\\d+))");
    private static final Logger LOGGER = LogManager.getLogger((Class<?>) FractionFormat.class);
    private static final int MAX_DENOM_POW = 4;
    private final int exactDenom;
    private final int maxDenom;
    private final String wholePartFormatString;

    public FractionFormat(String str, String str2) {
        int i;
        this.wholePartFormatString = str;
        Matcher matcher = DENOM_FORMAT_PATTERN.matcher(str2);
        int i2 = 100;
        int i3 = -1;
        if (!matcher.find()) {
            i = -1;
        } else if (matcher.group(2) != null) {
            try {
                int parseInt = Integer.parseInt(matcher.group(2));
                i3 = parseInt == 0 ? -1 : parseInt;
                i = -1;
            } catch (NumberFormatException e) {
                throw new IllegalStateException(e);
            }
        } else if (matcher.group(1) != null) {
            int length = matcher.group(1).length();
            i = (int) Math.pow(10.0d, (double) (length > 4 ? 4 : length));
        } else {
            i = -1;
            i3 = 100;
        }
        this.exactDenom = (i3 > 0 || i > 0) ? i3 : i2;
        this.maxDenom = i;
    }

    public String format(Number number) {
        SimpleFraction simpleFraction;
        BigDecimal bigDecimal = new BigDecimal(number.doubleValue());
        boolean z = bigDecimal.compareTo(BigDecimal.ZERO) < 0;
        BigDecimal abs = bigDecimal.abs();
        BigDecimal bigDecimal2 = new BigDecimal(abs.toBigInteger());
        BigDecimal remainder = abs.remainder(BigDecimal.ONE);
        if (bigDecimal2.add(remainder).compareTo(BigDecimal.ZERO) == 0) {
            return "0";
        }
        if (remainder.compareTo(BigDecimal.ZERO) == 0) {
            StringBuilder sb = new StringBuilder();
            if (z) {
                sb.append('-');
            }
            sb.append(bigDecimal2);
            return sb.toString();
        }
        try {
            if (this.exactDenom > 0) {
                simpleFraction = SimpleFraction.buildFractionExactDenominator(remainder.doubleValue(), this.exactDenom);
            } else {
                simpleFraction = SimpleFraction.buildFractionMaxDenominator(remainder.doubleValue(), this.maxDenom);
            }
            StringBuilder sb2 = new StringBuilder();
            if (z) {
                sb2.append('-');
            }
            String str = this.wholePartFormatString;
            if (str == null || str.isEmpty()) {
                int denominator = simpleFraction.getDenominator();
                sb2.append(bigDecimal2.multiply(BigDecimal.valueOf((long) denominator)).add(BigDecimal.valueOf((long) simpleFraction.getNumerator())).toBigInteger()).append(PackagingURIHelper.FORWARD_SLASH_STRING).append(denominator);
                return sb2.toString();
            } else if (simpleFraction.getNumerator() == 0) {
                sb2.append(bigDecimal2);
                return sb2.toString();
            } else if (simpleFraction.getNumerator() == simpleFraction.getDenominator()) {
                sb2.append(bigDecimal2.add(BigDecimal.ONE));
                return sb2.toString();
            } else {
                if (bigDecimal2.compareTo(BigDecimal.ZERO) > 0) {
                    sb2.append(bigDecimal2).append(" ");
                }
                sb2.append(simpleFraction.getNumerator()).append(PackagingURIHelper.FORWARD_SLASH_STRING).append(simpleFraction.getDenominator());
                return sb2.toString();
            }
        } catch (RuntimeException e) {
            LOGGER.atWarn().withThrowable(e).log("Can't format fraction");
            return Double.toString(bigDecimal.doubleValue());
        }
    }

    public StringBuffer format(Object obj, StringBuffer stringBuffer, FieldPosition fieldPosition) {
        return stringBuffer.append(format((Number) obj));
    }

    public Object parseObject(String str, ParsePosition parsePosition) {
        throw new NotImplementedException("Reverse parsing not supported");
    }
}
