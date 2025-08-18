package org.apache.commons.math3.geometry;

import java.text.FieldPosition;
import java.text.NumberFormat;
import java.text.ParsePosition;
import java.util.Locale;
import org.apache.commons.math3.exception.MathParseException;
import org.apache.commons.math3.geometry.Space;
import org.apache.commons.math3.util.CompositeFormat;

public abstract class VectorFormat<S extends Space> {
    public static final String DEFAULT_PREFIX = "{";
    public static final String DEFAULT_SEPARATOR = "; ";
    public static final String DEFAULT_SUFFIX = "}";
    private final NumberFormat format;
    private final String prefix;
    private final String separator;
    private final String suffix;
    private final String trimmedPrefix;
    private final String trimmedSeparator;
    private final String trimmedSuffix;

    public abstract StringBuffer format(Vector<S> vector, StringBuffer stringBuffer, FieldPosition fieldPosition);

    public abstract Vector<S> parse(String str) throws MathParseException;

    public abstract Vector<S> parse(String str, ParsePosition parsePosition);

    protected VectorFormat() {
        this(DEFAULT_PREFIX, DEFAULT_SUFFIX, DEFAULT_SEPARATOR, CompositeFormat.getDefaultNumberFormat());
    }

    protected VectorFormat(NumberFormat numberFormat) {
        this(DEFAULT_PREFIX, DEFAULT_SUFFIX, DEFAULT_SEPARATOR, numberFormat);
    }

    protected VectorFormat(String str, String str2, String str3) {
        this(str, str2, str3, CompositeFormat.getDefaultNumberFormat());
    }

    protected VectorFormat(String str, String str2, String str3, NumberFormat numberFormat) {
        this.prefix = str;
        this.suffix = str2;
        this.separator = str3;
        this.trimmedPrefix = str.trim();
        this.trimmedSuffix = str2.trim();
        this.trimmedSeparator = str3.trim();
        this.format = numberFormat;
    }

    public static Locale[] getAvailableLocales() {
        return NumberFormat.getAvailableLocales();
    }

    public String getPrefix() {
        return this.prefix;
    }

    public String getSuffix() {
        return this.suffix;
    }

    public String getSeparator() {
        return this.separator;
    }

    public NumberFormat getFormat() {
        return this.format;
    }

    public String format(Vector<S> vector) {
        return format(vector, new StringBuffer(), new FieldPosition(0)).toString();
    }

    /* access modifiers changed from: protected */
    public StringBuffer format(StringBuffer stringBuffer, FieldPosition fieldPosition, double... dArr) {
        fieldPosition.setBeginIndex(0);
        fieldPosition.setEndIndex(0);
        stringBuffer.append(this.prefix);
        for (int i = 0; i < dArr.length; i++) {
            if (i > 0) {
                stringBuffer.append(this.separator);
            }
            CompositeFormat.formatDouble(dArr[i], this.format, stringBuffer, fieldPosition);
        }
        stringBuffer.append(this.suffix);
        return stringBuffer;
    }

    /* access modifiers changed from: protected */
    public double[] parseCoordinates(int i, String str, ParsePosition parsePosition) {
        int index = parsePosition.getIndex();
        double[] dArr = new double[i];
        CompositeFormat.parseAndIgnoreWhitespace(str, parsePosition);
        if (!CompositeFormat.parseFixedstring(str, this.trimmedPrefix, parsePosition)) {
            return null;
        }
        for (int i2 = 0; i2 < i; i2++) {
            CompositeFormat.parseAndIgnoreWhitespace(str, parsePosition);
            if (i2 > 0 && !CompositeFormat.parseFixedstring(str, this.trimmedSeparator, parsePosition)) {
                return null;
            }
            CompositeFormat.parseAndIgnoreWhitespace(str, parsePosition);
            Number parseNumber = CompositeFormat.parseNumber(str, this.format, parsePosition);
            if (parseNumber == null) {
                parsePosition.setIndex(index);
                return null;
            }
            dArr[i2] = parseNumber.doubleValue();
        }
        CompositeFormat.parseAndIgnoreWhitespace(str, parsePosition);
        if (!CompositeFormat.parseFixedstring(str, this.trimmedSuffix, parsePosition)) {
            return null;
        }
        return dArr;
    }
}
