package org.apache.xmlbeans.impl.util;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.URI;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import javax.xml.namespace.NamespaceContext;
import javax.xml.namespace.QName;
import org.apache.commons.math3.geometry.VectorFormat;
import org.apache.xmlbeans.GDate;
import org.apache.xmlbeans.GDateBuilder;
import org.apache.xmlbeans.GDateSpecification;
import org.apache.xmlbeans.XmlCalendar;
import org.apache.xmlbeans.XmlError;
import org.apache.xmlbeans.impl.common.InvalidLexicalValueException;

public final class XsTypeConverter {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final char[] CH_ZEROS = {'0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0'};
    private static final BigDecimal DECIMAL__ZERO = new BigDecimal(0.0d);
    private static final String EMPTY_PREFIX = "";
    private static final char NAMESPACE_SEP = ':';
    private static final String NAN_LEX = "NaN";
    private static final String NEG_INF_LEX = "-INF";
    private static final String POS_INF_LEX = "INF";
    private static final String[] URI_CHARS_REPLACED_WITH = {"%20", "%7b", "%7d", "%7c", "%5c", "%5e", "%5b", "%5d", "%60"};
    private static final String[] URI_CHARS_TO_BE_REPLACED = {" ", VectorFormat.DEFAULT_PREFIX, VectorFormat.DEFAULT_SUFFIX, "|", "\\", "^", "[", "]", "`"};

    public static String printBoolean(boolean z) {
        return z ? "true" : "false";
    }

    public static String printString(String str) {
        return str;
    }

    public static float lexFloat(CharSequence charSequence) throws NumberFormatException {
        char charAt;
        String charSequence2 = charSequence.toString();
        try {
            if (charSequence.length() > 0 && ((charAt = charSequence.charAt(charSequence.length() - 1)) == 'f' || charAt == 'F')) {
                if (charSequence.charAt(charSequence.length() - 2) != 'N') {
                    throw new NumberFormatException("Invalid char '" + charAt + "' in float.");
                }
            }
            return Float.parseFloat(charSequence2);
        } catch (NumberFormatException e) {
            if (charSequence2.equals(POS_INF_LEX)) {
                return Float.POSITIVE_INFINITY;
            }
            if (charSequence2.equals(NEG_INF_LEX)) {
                return Float.NEGATIVE_INFINITY;
            }
            if (charSequence2.equals(NAN_LEX)) {
                return Float.NaN;
            }
            throw e;
        }
    }

    public static float lexFloat(CharSequence charSequence, Collection collection) {
        try {
            return lexFloat(charSequence);
        } catch (NumberFormatException unused) {
            collection.add(XmlError.forMessage("invalid float: " + charSequence));
            return Float.NaN;
        }
    }

    public static String printFloat(float f) {
        if (f == Float.POSITIVE_INFINITY) {
            return POS_INF_LEX;
        }
        if (f == Float.NEGATIVE_INFINITY) {
            return NEG_INF_LEX;
        }
        if (Float.isNaN(f)) {
            return NAN_LEX;
        }
        return Float.toString(f);
    }

    public static double lexDouble(CharSequence charSequence) throws NumberFormatException {
        String charSequence2 = charSequence.toString();
        try {
            if (charSequence.length() > 0) {
                char charAt = charSequence.charAt(charSequence.length() - 1);
                if (charAt == 'd' || charAt == 'D') {
                    throw new NumberFormatException("Invalid char '" + charAt + "' in double.");
                }
            }
            return Double.parseDouble(charSequence2);
        } catch (NumberFormatException e) {
            if (charSequence2.equals(POS_INF_LEX)) {
                return Double.POSITIVE_INFINITY;
            }
            if (charSequence2.equals(NEG_INF_LEX)) {
                return Double.NEGATIVE_INFINITY;
            }
            if (charSequence2.equals(NAN_LEX)) {
                return Double.NaN;
            }
            throw e;
        }
    }

    public static double lexDouble(CharSequence charSequence, Collection collection) {
        try {
            return lexDouble(charSequence);
        } catch (NumberFormatException unused) {
            collection.add(XmlError.forMessage("invalid double: " + charSequence));
            return Double.NaN;
        }
    }

    public static String printDouble(double d) {
        if (d == Double.POSITIVE_INFINITY) {
            return POS_INF_LEX;
        }
        if (d == Double.NEGATIVE_INFINITY) {
            return NEG_INF_LEX;
        }
        if (Double.isNaN(d)) {
            return NAN_LEX;
        }
        return Double.toString(d);
    }

    public static BigDecimal lexDecimal(CharSequence charSequence) throws NumberFormatException {
        return new BigDecimal(trimTrailingZeros(charSequence.toString()));
    }

    public static String printDecimal(BigDecimal bigDecimal) {
        char[] cArr;
        char[] cArr2;
        String bigInteger = bigDecimal.unscaledValue().toString();
        int scale = bigDecimal.scale();
        if (scale == 0 || (bigDecimal.longValue() == 0 && scale < 0)) {
            return bigInteger;
        }
        int i = bigDecimal.signum() < 0 ? 1 : 0;
        StringBuilder sb = new StringBuilder(bigInteger.length() + 1 + Math.abs(scale));
        if (i == 1) {
            sb.append('-');
        }
        if (scale > 0) {
            int length = scale - (bigInteger.length() - i);
            if (length >= 0) {
                sb.append("0.");
                while (true) {
                    cArr2 = CH_ZEROS;
                    if (length <= cArr2.length) {
                        break;
                    }
                    sb.append(cArr2);
                    length -= cArr2.length;
                }
                sb.append(cArr2, 0, length);
                sb.append(bigInteger.substring(i));
            } else {
                int i2 = i - length;
                sb.append(bigInteger.substring(i, i2));
                sb.append('.');
                sb.append(bigInteger.substring(i2));
            }
        } else {
            sb.append(bigInteger.substring(i));
            while (true) {
                cArr = CH_ZEROS;
                if (scale >= (-cArr.length)) {
                    break;
                }
                sb.append(cArr);
                scale += cArr.length;
            }
            sb.append(cArr, 0, -scale);
        }
        return sb.toString();
    }

    public static BigInteger lexInteger(CharSequence charSequence) throws NumberFormatException {
        if (charSequence.length() <= 1 || charSequence.charAt(0) != '+' || charSequence.charAt(1) != '-') {
            return new BigInteger(trimInitialPlus(charSequence.toString()));
        }
        throw new NumberFormatException("Illegal char sequence '+-'");
    }

    public static BigInteger lexInteger(CharSequence charSequence, Collection collection) {
        try {
            return lexInteger(charSequence);
        } catch (NumberFormatException unused) {
            collection.add(XmlError.forMessage("invalid long: " + charSequence));
            return BigInteger.ZERO;
        }
    }

    public static String printInteger(BigInteger bigInteger) {
        return bigInteger.toString();
    }

    public static long lexLong(CharSequence charSequence) throws NumberFormatException {
        return Long.parseLong(trimInitialPlus(charSequence.toString()));
    }

    public static long lexLong(CharSequence charSequence, Collection collection) {
        try {
            return lexLong(charSequence);
        } catch (NumberFormatException unused) {
            collection.add(XmlError.forMessage("invalid long: " + charSequence));
            return 0;
        }
    }

    public static String printLong(long j) {
        return Long.toString(j);
    }

    public static short lexShort(CharSequence charSequence) throws NumberFormatException {
        return parseShort(charSequence);
    }

    public static short lexShort(CharSequence charSequence, Collection collection) {
        try {
            return lexShort(charSequence);
        } catch (NumberFormatException unused) {
            collection.add(XmlError.forMessage("invalid short: " + charSequence));
            return 0;
        }
    }

    public static String printShort(short s) {
        return Short.toString(s);
    }

    public static int lexInt(CharSequence charSequence) throws NumberFormatException {
        return parseInt(charSequence);
    }

    public static int lexInt(CharSequence charSequence, Collection collection) {
        try {
            return lexInt(charSequence);
        } catch (NumberFormatException unused) {
            collection.add(XmlError.forMessage("invalid int:" + charSequence));
            return 0;
        }
    }

    public static String printInt(int i) {
        return Integer.toString(i);
    }

    public static byte lexByte(CharSequence charSequence) throws NumberFormatException {
        return parseByte(charSequence);
    }

    public static byte lexByte(CharSequence charSequence, Collection collection) {
        try {
            return lexByte(charSequence);
        } catch (NumberFormatException unused) {
            collection.add(XmlError.forMessage("invalid byte: " + charSequence));
            return 0;
        }
    }

    public static String printByte(byte b) {
        return Byte.toString(b);
    }

    public static boolean lexBoolean(CharSequence charSequence) {
        int length = charSequence.length();
        if (length == 1) {
            char charAt = charSequence.charAt(0);
            if ('0' == charAt) {
                return false;
            }
            if ('1' == charAt) {
                return true;
            }
        } else if (length != 4) {
            if (length == 5 && 'f' == charSequence.charAt(0) && 'a' == charSequence.charAt(1) && 'l' == charSequence.charAt(2) && 's' == charSequence.charAt(3) && 'e' == charSequence.charAt(4)) {
                return false;
            }
        } else if ('t' == charSequence.charAt(0) && 'r' == charSequence.charAt(1) && 'u' == charSequence.charAt(2) && 'e' == charSequence.charAt(3)) {
            return true;
        }
        throw new InvalidLexicalValueException("invalid boolean: " + charSequence);
    }

    public static boolean lexBoolean(CharSequence charSequence, Collection<XmlError> collection) {
        try {
            return lexBoolean(charSequence);
        } catch (InvalidLexicalValueException e) {
            collection.add(XmlError.forMessage(e.getMessage()));
            return false;
        }
    }

    public static QName lexQName(CharSequence charSequence, NamespaceContext namespaceContext) {
        boolean z;
        String str;
        String str2;
        int i = 0;
        while (true) {
            if (i >= charSequence.length()) {
                z = false;
                break;
            } else if (charSequence.charAt(i) == ':') {
                z = true;
                break;
            } else {
                i++;
            }
        }
        String str3 = "";
        if (z) {
            str2 = charSequence.subSequence(0, i).toString();
            str = charSequence.subSequence(i + 1, charSequence.length()).toString();
            if (i == 0) {
                throw new InvalidLexicalValueException("invalid xsd:QName '" + charSequence.toString() + "'");
            }
        } else {
            str = charSequence.toString();
            str2 = str3;
        }
        String namespaceURI = namespaceContext.getNamespaceURI(str2);
        if (namespaceURI != null) {
            str3 = namespaceURI;
        } else if (str2 != null && str2.length() > 0) {
            throw new InvalidLexicalValueException("Can't resolve prefix: " + str2);
        }
        return new QName(str3, str);
    }

    public static QName lexQName(String str, Collection collection, NamespaceContext namespaceContext) {
        try {
            return lexQName(str, namespaceContext);
        } catch (InvalidLexicalValueException e) {
            collection.add(XmlError.forMessage(e.getMessage()));
            return new QName((String) null, str.substring(str.indexOf(58)));
        }
    }

    public static String printQName(QName qName, NamespaceContext namespaceContext, Collection collection) {
        String str;
        String namespaceURI = qName.getNamespaceURI();
        if (namespaceURI.length() > 0) {
            str = namespaceContext.getPrefix(namespaceURI);
            if (str == null) {
                collection.add(XmlError.forMessage("NamespaceContext does not provide prefix for namespaceURI " + namespaceURI));
            }
        } else {
            str = null;
        }
        return getQNameString(namespaceURI, qName.getLocalPart(), str);
    }

    public static String getQNameString(String str, String str2, String str3) {
        return (str3 == null || str == null || str.length() <= 0 || str3.length() <= 0) ? str2 : str3 + ':' + str2;
    }

    public static GDate lexGDate(CharSequence charSequence) {
        return new GDate(charSequence);
    }

    public static GDate lexGDate(String str, Collection collection) {
        try {
            return lexGDate(str);
        } catch (IllegalArgumentException e) {
            collection.add(XmlError.forMessage(e.getMessage()));
            return new GDateBuilder().toGDate();
        }
    }

    public static String printGDate(GDate gDate, Collection collection) {
        return gDate.toString();
    }

    public static XmlCalendar lexDateTime(CharSequence charSequence) {
        return getGDateValue(charSequence, 14).getCalendar();
    }

    public static String printDateTime(Calendar calendar) {
        return printDateTime(calendar, 14);
    }

    public static String printTime(Calendar calendar) {
        return printDateTime(calendar, 15);
    }

    public static String printDate(Calendar calendar) {
        return printDateTime(calendar, 16);
    }

    public static String printDate(Date date) {
        return getGDateValue(date, 16).toString();
    }

    public static String printDateTime(Calendar calendar, int i) {
        return getGDateValue(calendar, i).toString();
    }

    public static String printDateTime(Date date) {
        return getGDateValue(date, 14).toString();
    }

    public static CharSequence printHexBinary(byte[] bArr) {
        return HexBin.bytesToString(bArr);
    }

    public static GDateSpecification getGDateValue(Date date, int i) {
        GDateBuilder gDateBuilder = new GDateBuilder(date);
        gDateBuilder.setBuiltinTypeCode(i);
        return gDateBuilder.toGDate();
    }

    public static GDateSpecification getGDateValue(Calendar calendar, int i) {
        GDateBuilder gDateBuilder = new GDateBuilder(calendar);
        gDateBuilder.setBuiltinTypeCode(i);
        return gDateBuilder.toGDate();
    }

    public static GDateSpecification getGDateValue(CharSequence charSequence, int i) {
        GDateBuilder gDateBuilder = new GDateBuilder(charSequence);
        gDateBuilder.setBuiltinTypeCode(i);
        return gDateBuilder.toGDate();
    }

    private static String trimInitialPlus(String str) {
        return (str.length() <= 0 || str.charAt(0) != '+') ? str : str.substring(1);
    }

    private static String trimTrailingZeros(String str) {
        int lastIndexOf;
        int length = str.length() - 1;
        if (str.charAt(length) != '0' || (lastIndexOf = str.lastIndexOf(46)) < 0) {
            return str;
        }
        while (length > lastIndexOf) {
            if (str.charAt(length) != '0') {
                return str.substring(0, length + 1);
            }
            length--;
        }
        return str.substring(0, lastIndexOf);
    }

    private static int parseInt(CharSequence charSequence) {
        return parseIntXsdNumber(charSequence, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    private static short parseShort(CharSequence charSequence) {
        return (short) parseIntXsdNumber(charSequence, -32768, 32767);
    }

    private static byte parseByte(CharSequence charSequence) {
        return (byte) parseIntXsdNumber(charSequence, -128, 127);
    }

    private static int parseIntXsdNumber(CharSequence charSequence, int i, int i2) {
        int i3;
        int i4;
        int i5;
        int i6;
        int i7;
        int length = charSequence.length();
        int i8 = 1;
        if (length >= 1) {
            int i9 = 0;
            char charAt = charSequence.charAt(0);
            if (charAt == '-') {
                i3 = i / 10;
                i4 = -(i % 10);
                i5 = 1;
            } else {
                if (charAt == '+') {
                    i7 = -(i2 / 10);
                    i6 = i2 % 10;
                    i5 = 1;
                } else {
                    i7 = -(i2 / 10);
                    i6 = i2 % 10;
                    i5 = 0;
                }
                i8 = -1;
                int i10 = i6;
                i3 = i7;
                i4 = i10;
            }
            int i11 = 0;
            while (i9 < length - i5) {
                int digit = Character.digit(charSequence.charAt(i9 + i5), 10);
                if (digit < 0) {
                    throw new NumberFormatException("For input string: \"" + charSequence.toString() + "\"");
                } else if (i11 < i3 || (i11 == i3 && digit > i4)) {
                    throw new NumberFormatException("For input string: \"" + charSequence.toString() + "\"");
                } else {
                    i11 = (i11 * 10) - digit;
                    i9++;
                }
            }
            return i8 * i11;
        }
        throw new NumberFormatException("For input string: \"" + charSequence.toString() + "\"");
    }

    public static CharSequence lexAnyURI(CharSequence charSequence) {
        StringBuilder sb = new StringBuilder(charSequence.toString());
        for (int i = 0; i < URI_CHARS_TO_BE_REPLACED.length; i++) {
            int i2 = 0;
            while (true) {
                int indexOf = sb.indexOf(URI_CHARS_TO_BE_REPLACED[i], i2);
                if (indexOf < 0) {
                    break;
                }
                sb.replace(indexOf, indexOf + 1, URI_CHARS_REPLACED_WITH[i]);
                i2 = indexOf + 3;
            }
        }
        try {
            URI.create(sb.toString());
            return charSequence;
        } catch (IllegalArgumentException e) {
            throw new InvalidLexicalValueException("invalid anyURI value: " + charSequence, (Throwable) e);
        }
    }
}
