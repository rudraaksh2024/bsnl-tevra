package org.apache.logging.log4j.util;

import java.util.Iterator;
import java.util.Locale;
import java.util.Objects;

public final class Strings {
    private static final String COMMA_DELIMITED_RE = "\\s*,\\s*";
    public static final String EMPTY = "";
    public static final String[] EMPTY_ARRAY = new String[0];
    public static final String LINE_SEPARATOR = PropertiesUtil.getProperties().getStringProperty("line.separator", "\n");
    private static final ThreadLocal<StringBuilder> tempStr = ThreadLocal.withInitial(new Strings$$ExternalSyntheticLambda0());

    public static /* synthetic */ StringBuilder $r8$lambda$z4Sacp0dHgNB96bTlXZdt5SF0q4() {
        return new StringBuilder();
    }

    public static String dquote(String str) {
        return "\"" + str + '\"';
    }

    public static boolean isBlank(String str) {
        if (str != null && !str.isEmpty()) {
            for (int i = 0; i < str.length(); i++) {
                if (!Character.isWhitespace(str.charAt(i))) {
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean isEmpty(CharSequence charSequence) {
        return charSequence == null || charSequence.length() == 0;
    }

    public static boolean isNotBlank(String str) {
        return !isBlank(str);
    }

    public static boolean isNotEmpty(CharSequence charSequence) {
        return !isEmpty(charSequence);
    }

    public static String join(Iterable<?> iterable, char c) {
        if (iterable == null) {
            return null;
        }
        return join(iterable.iterator(), c);
    }

    public static String join(Iterator<?> it, char c) {
        if (it == null) {
            return null;
        }
        if (!it.hasNext()) {
            return "";
        }
        Object next = it.next();
        if (!it.hasNext()) {
            return Objects.toString(next, "");
        }
        StringBuilder sb = new StringBuilder(256);
        if (next != null) {
            sb.append(next);
        }
        while (it.hasNext()) {
            sb.append(c);
            Object next2 = it.next();
            if (next2 != null) {
                sb.append(next2);
            }
        }
        return sb.toString();
    }

    public static String[] splitList(String str) {
        return str != null ? str.split(COMMA_DELIMITED_RE) : new String[0];
    }

    public static String left(String str, int i) {
        if (str == null) {
            return null;
        }
        if (i < 0) {
            return "";
        }
        if (str.length() <= i) {
            return str;
        }
        return str.substring(0, i);
    }

    public static String quote(String str) {
        return "'" + str + Chars.QUOTE;
    }

    public static String trimToNull(String str) {
        String trim = str == null ? null : str.trim();
        if (isEmpty(trim)) {
            return null;
        }
        return trim;
    }

    private Strings() {
    }

    public static String toRootLowerCase(String str) {
        return str.toLowerCase(Locale.ROOT);
    }

    public static String toRootUpperCase(String str) {
        return str.toUpperCase(Locale.ROOT);
    }

    public static String concat(String str, String str2) {
        if (isEmpty(str)) {
            return str2;
        }
        if (isEmpty(str2)) {
            return str;
        }
        StringBuilder sb = tempStr.get();
        try {
            return sb.append(str).append(str2).toString();
        } finally {
            sb.setLength(0);
        }
    }

    public static String repeat(String str, int i) {
        Objects.requireNonNull(str, "str");
        if (i >= 0) {
            StringBuilder sb = tempStr.get();
            int i2 = 0;
            while (i2 < i) {
                try {
                    sb.append(str);
                    i2++;
                } catch (Throwable th) {
                    sb.setLength(0);
                    throw th;
                }
            }
            String sb2 = sb.toString();
            sb.setLength(0);
            return sb2;
        }
        throw new IllegalArgumentException("count");
    }
}
