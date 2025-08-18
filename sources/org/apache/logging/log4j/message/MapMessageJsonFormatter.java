package org.apache.logging.log4j.message;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import org.apache.logging.log4j.util.IndexedStringMap;
import org.apache.logging.log4j.util.PropertiesUtil;
import org.apache.logging.log4j.util.StringBuilderFormattable;
import org.apache.logging.log4j.util.StringBuilders;

enum MapMessageJsonFormatter {
    ;
    
    private static final char COLON = ':';
    private static final char COMMA = ',';
    private static final char DQUOTE = '\"';
    private static final char LBRACE = '[';
    private static final char LCURLY = '{';
    public static final int MAX_DEPTH = 0;
    private static final char RBRACE = ']';
    private static final char RCURLY = '}';

    static {
        MAX_DEPTH = readMaxDepth();
    }

    private static int readMaxDepth() {
        int integerProperty = PropertiesUtil.getProperties().getIntegerProperty("log4j2.mapMessage.jsonFormatter.maxDepth", 8);
        if (integerProperty >= 0) {
            return integerProperty;
        }
        throw new IllegalArgumentException("was expecting a positive maxDepth, found: " + integerProperty);
    }

    static void format(StringBuilder sb, Object obj) {
        format(sb, obj, 0);
    }

    private static void format(StringBuilder sb, Object obj, int i) {
        if (i >= MAX_DEPTH) {
            throw new IllegalArgumentException("maxDepth has been exceeded");
        } else if (obj == null) {
            sb.append("null");
        } else if (obj instanceof IndexedStringMap) {
            formatIndexedStringMap(sb, (IndexedStringMap) obj, i);
        } else if (obj instanceof Map) {
            formatMap(sb, (Map) obj, i);
        } else if (obj instanceof List) {
            formatList(sb, (List) obj, i);
        } else if (obj instanceof Collection) {
            formatCollection(sb, (Collection) obj, i);
        } else if (obj instanceof Number) {
            formatNumber(sb, (Number) obj);
        } else if (obj instanceof Boolean) {
            formatBoolean(sb, ((Boolean) obj).booleanValue());
        } else if (obj instanceof StringBuilderFormattable) {
            formatFormattable(sb, (StringBuilderFormattable) obj);
        } else if (obj instanceof char[]) {
            formatCharArray(sb, (char[]) obj);
        } else if (obj instanceof boolean[]) {
            formatBooleanArray(sb, (boolean[]) obj);
        } else if (obj instanceof byte[]) {
            formatByteArray(sb, (byte[]) obj);
        } else if (obj instanceof short[]) {
            formatShortArray(sb, (short[]) obj);
        } else if (obj instanceof int[]) {
            formatIntArray(sb, (int[]) obj);
        } else if (obj instanceof long[]) {
            formatLongArray(sb, (long[]) obj);
        } else if (obj instanceof float[]) {
            formatFloatArray(sb, (float[]) obj);
        } else if (obj instanceof double[]) {
            formatDoubleArray(sb, (double[]) obj);
        } else if (obj instanceof Object[]) {
            formatObjectArray(sb, (Object[]) obj, i);
        } else {
            formatString(sb, obj);
        }
    }

    private static void formatIndexedStringMap(StringBuilder sb, IndexedStringMap indexedStringMap, int i) {
        sb.append(LCURLY);
        int i2 = i + 1;
        for (int i3 = 0; i3 < indexedStringMap.size(); i3++) {
            String keyAt = indexedStringMap.getKeyAt(i3);
            Object valueAt = indexedStringMap.getValueAt(i3);
            if (i3 > 0) {
                sb.append(COMMA);
            }
            sb.append('\"');
            int length = sb.length();
            sb.append(keyAt);
            StringBuilders.escapeJson(sb, length);
            sb.append('\"').append(':');
            format(sb, valueAt, i2);
        }
        sb.append(RCURLY);
    }

    private static void formatMap(StringBuilder sb, Map<Object, Object> map, int i) {
        sb.append(LCURLY);
        map.forEach(new MapMessageJsonFormatter$$ExternalSyntheticLambda1(new boolean[]{true}, sb, i + 1));
        sb.append(RCURLY);
    }

    private static void formatList(StringBuilder sb, List<Object> list, int i) {
        sb.append(LBRACE);
        int i2 = i + 1;
        for (int i3 = 0; i3 < list.size(); i3++) {
            if (i3 > 0) {
                sb.append(COMMA);
            }
            format(sb, list.get(i3), i2);
        }
        sb.append(RBRACE);
    }

    private static void formatCollection(StringBuilder sb, Collection<Object> collection, int i) {
        sb.append(LBRACE);
        collection.forEach(new MapMessageJsonFormatter$$ExternalSyntheticLambda0(new boolean[]{true}, sb, i + 1));
        sb.append(RBRACE);
    }

    private static void formatNumber(StringBuilder sb, Number number) {
        if (number instanceof BigDecimal) {
            sb.append(((BigDecimal) number).toString());
        } else if (number instanceof Double) {
            sb.append(((Double) number).doubleValue());
        } else if (number instanceof Float) {
            sb.append(((Float) number).floatValue());
        } else if ((number instanceof Byte) || (number instanceof Short) || (number instanceof Integer) || (number instanceof Long)) {
            sb.append(number.longValue());
        } else {
            long longValue = number.longValue();
            double doubleValue = number.doubleValue();
            if (Double.compare((double) longValue, doubleValue) == 0) {
                sb.append(longValue);
            } else {
                sb.append(doubleValue);
            }
        }
    }

    private static void formatBoolean(StringBuilder sb, boolean z) {
        sb.append(z);
    }

    private static void formatFormattable(StringBuilder sb, StringBuilderFormattable stringBuilderFormattable) {
        sb.append('\"');
        int length = sb.length();
        stringBuilderFormattable.formatTo(sb);
        StringBuilders.escapeJson(sb, length);
        sb.append('\"');
    }

    private static void formatCharArray(StringBuilder sb, char[] cArr) {
        sb.append(LBRACE);
        for (int i = 0; i < cArr.length; i++) {
            if (i > 0) {
                sb.append(COMMA);
            }
            char c = cArr[i];
            sb.append('\"');
            int length = sb.length();
            sb.append(c);
            StringBuilders.escapeJson(sb, length);
            sb.append('\"');
        }
        sb.append(RBRACE);
    }

    private static void formatBooleanArray(StringBuilder sb, boolean[] zArr) {
        sb.append(LBRACE);
        for (int i = 0; i < zArr.length; i++) {
            if (i > 0) {
                sb.append(COMMA);
            }
            sb.append(zArr[i]);
        }
        sb.append(RBRACE);
    }

    private static void formatByteArray(StringBuilder sb, byte[] bArr) {
        sb.append(LBRACE);
        for (int i = 0; i < bArr.length; i++) {
            if (i > 0) {
                sb.append(COMMA);
            }
            sb.append(bArr[i]);
        }
        sb.append(RBRACE);
    }

    private static void formatShortArray(StringBuilder sb, short[] sArr) {
        sb.append(LBRACE);
        for (int i = 0; i < sArr.length; i++) {
            if (i > 0) {
                sb.append(COMMA);
            }
            sb.append(sArr[i]);
        }
        sb.append(RBRACE);
    }

    private static void formatIntArray(StringBuilder sb, int[] iArr) {
        sb.append(LBRACE);
        for (int i = 0; i < iArr.length; i++) {
            if (i > 0) {
                sb.append(COMMA);
            }
            sb.append(iArr[i]);
        }
        sb.append(RBRACE);
    }

    private static void formatLongArray(StringBuilder sb, long[] jArr) {
        sb.append(LBRACE);
        for (int i = 0; i < jArr.length; i++) {
            if (i > 0) {
                sb.append(COMMA);
            }
            sb.append(jArr[i]);
        }
        sb.append(RBRACE);
    }

    private static void formatFloatArray(StringBuilder sb, float[] fArr) {
        sb.append(LBRACE);
        for (int i = 0; i < fArr.length; i++) {
            if (i > 0) {
                sb.append(COMMA);
            }
            sb.append(fArr[i]);
        }
        sb.append(RBRACE);
    }

    private static void formatDoubleArray(StringBuilder sb, double[] dArr) {
        sb.append(LBRACE);
        for (int i = 0; i < dArr.length; i++) {
            if (i > 0) {
                sb.append(COMMA);
            }
            sb.append(dArr[i]);
        }
        sb.append(RBRACE);
    }

    private static void formatObjectArray(StringBuilder sb, Object[] objArr, int i) {
        sb.append(LBRACE);
        int i2 = i + 1;
        for (int i3 = 0; i3 < objArr.length; i3++) {
            if (i3 > 0) {
                sb.append(COMMA);
            }
            format(sb, objArr[i3], i2);
        }
        sb.append(RBRACE);
    }

    private static void formatString(StringBuilder sb, Object obj) {
        sb.append('\"');
        int length = sb.length();
        sb.append(String.valueOf(obj));
        StringBuilders.escapeJson(sb, length);
        sb.append('\"');
    }
}
