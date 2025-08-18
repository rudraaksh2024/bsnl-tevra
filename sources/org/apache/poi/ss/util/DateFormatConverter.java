package org.apache.poi.ss.util;

import androidx.exifinterface.media.ExifInterface;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import org.apache.commons.compress.archivers.tar.TarConstants;
import org.apache.commons.compress.compressors.CompressorStreamFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.util.Chars;
import org.apache.poi.util.LocaleID;
import org.apache.xmlbeans.impl.common.NameUtil;

public final class DateFormatConverter {
    private static final Logger LOG = LogManager.getLogger((Class<?>) DateFormatConverter.class);
    private static Map<String, String> tokenConversions = prepareTokenConversions();

    private DateFormatConverter() {
    }

    public static class DateFormatTokenizer {
        String format;
        int pos;

        public DateFormatTokenizer(String str) {
            this.format = str;
        }

        public String getNextToken() {
            if (this.pos >= this.format.length()) {
                return null;
            }
            int i = this.pos;
            char charAt = this.format.charAt(i);
            this.pos++;
            if (charAt == '\'') {
                while (this.pos < this.format.length() && this.format.charAt(this.pos) != '\'') {
                    this.pos++;
                }
                if (this.pos < this.format.length()) {
                    this.pos++;
                }
            } else {
                while (this.pos < this.format.length() && this.format.charAt(this.pos) == charAt) {
                    this.pos++;
                }
            }
            return this.format.substring(i, this.pos);
        }

        public static String[] tokenize(String str) {
            ArrayList arrayList = new ArrayList();
            DateFormatTokenizer dateFormatTokenizer = new DateFormatTokenizer(str);
            while (true) {
                String nextToken = dateFormatTokenizer.getNextToken();
                if (nextToken == null) {
                    return (String[]) arrayList.toArray(new String[0]);
                }
                arrayList.add(nextToken);
            }
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            DateFormatTokenizer dateFormatTokenizer = new DateFormatTokenizer(this.format);
            while (true) {
                String nextToken = dateFormatTokenizer.getNextToken();
                if (nextToken == null) {
                    return sb.toString();
                }
                if (sb.length() > 0) {
                    sb.append(", ");
                }
                sb.append("[").append(nextToken).append("]");
            }
        }
    }

    private static Map<String, String> prepareTokenConversions() {
        HashMap hashMap = new HashMap();
        hashMap.put("EEEE", "dddd");
        hashMap.put("EEE", "ddd");
        hashMap.put("EE", "ddd");
        hashMap.put(ExifInterface.LONGITUDE_EAST, "d");
        hashMap.put("Z", "");
        hashMap.put(CompressorStreamFactory.Z, "");
        hashMap.put("a", "am/pm");
        hashMap.put(ExifInterface.GPS_MEASUREMENT_IN_PROGRESS, "AM/PM");
        hashMap.put("K", "H");
        hashMap.put("KK", "HH");
        hashMap.put("k", "h");
        hashMap.put("kk", "hh");
        hashMap.put(ExifInterface.LATITUDE_SOUTH, "0");
        hashMap.put("SS", TarConstants.VERSION_POSIX);
        hashMap.put("SSS", "000");
        hashMap.put("y", "yyyy");
        return hashMap;
    }

    public static String getPrefixForLocale(Locale locale) {
        String languageTag = locale.toLanguageTag();
        if (Locale.ROOT.equals(locale) || "".equals(languageTag)) {
            return "";
        }
        LocaleID lookupByLanguageTag = LocaleID.lookupByLanguageTag(languageTag);
        if (lookupByLanguageTag == null) {
            String replace = languageTag.indexOf(95) > -1 ? languageTag.replace(NameUtil.USCORE, '-') : languageTag;
            int length = languageTag.length();
            while (lookupByLanguageTag == null) {
                length = replace.lastIndexOf(45, length - 1);
                if (length <= 0) {
                    break;
                }
                lookupByLanguageTag = LocaleID.lookupByLanguageTag(languageTag.substring(0, length));
            }
        }
        if (lookupByLanguageTag == null) {
            LOG.atError().log("Unable to find prefix for Locale '{}' or its parent locales.", (Object) languageTag);
            return "";
        }
        return String.format(Locale.ROOT, "[$-%04X]", new Object[]{Integer.valueOf(lookupByLanguageTag.getLcid())});
    }

    public static String convert(Locale locale, DateFormat dateFormat) {
        return convert(locale, ((SimpleDateFormat) dateFormat).toPattern());
    }

    public static String convert(Locale locale, String str) {
        StringBuilder sb = new StringBuilder();
        sb.append(getPrefixForLocale(locale));
        DateFormatTokenizer dateFormatTokenizer = new DateFormatTokenizer(str);
        while (true) {
            String nextToken = dateFormatTokenizer.getNextToken();
            if (nextToken == null) {
                sb.append(";@");
                return sb.toString().trim();
            } else if (nextToken.startsWith("'")) {
                sb.append(nextToken.replace(Chars.QUOTE, '\"'));
            } else if (!Character.isLetter(nextToken.charAt(0))) {
                sb.append(nextToken);
            } else {
                String str2 = tokenConversions.get(nextToken);
                if (str2 != null) {
                    nextToken = str2;
                }
                sb.append(nextToken);
            }
        }
    }

    public static String getJavaDatePattern(int i, Locale locale) {
        DateFormat dateInstance = DateFormat.getDateInstance(i, locale);
        if (dateInstance instanceof SimpleDateFormat) {
            return ((SimpleDateFormat) dateInstance).toPattern();
        }
        if (i == 0) {
            return "dddd, MMMM d, yyyy";
        }
        if (i != 1) {
            return i != 3 ? "MMM d, yyyy" : "d/MM/yy";
        }
        return "MMMM d, yyyy";
    }

    public static String getJavaTimePattern(int i, Locale locale) {
        DateFormat timeInstance = DateFormat.getTimeInstance(i, locale);
        if (timeInstance instanceof SimpleDateFormat) {
            return ((SimpleDateFormat) timeInstance).toPattern();
        }
        return i != 3 ? "h:mm:ss a" : "h:mm a";
    }

    public static String getJavaDateTimePattern(int i, Locale locale) {
        DateFormat dateTimeInstance = DateFormat.getDateTimeInstance(i, i, locale);
        if (dateTimeInstance instanceof SimpleDateFormat) {
            return ((SimpleDateFormat) dateTimeInstance).toPattern();
        }
        if (i == 0) {
            return "dddd, MMMM d, yyyy h:mm:ss a";
        }
        if (i != 1) {
            return i != 3 ? "MMM d, yyyy h:mm:ss a" : "M/d/yy h:mm a";
        }
        return "MMMM d, yyyy h:mm:ss a";
    }
}
