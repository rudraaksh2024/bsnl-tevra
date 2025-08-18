package org.apache.logging.log4j.message;

import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;
import org.apache.logging.log4j.message.MapMessage;
import org.apache.logging.log4j.util.BiConsumer;
import org.apache.logging.log4j.util.Chars;
import org.apache.logging.log4j.util.EnglishEnums;
import org.apache.logging.log4j.util.IndexedReadOnlyStringMap;
import org.apache.logging.log4j.util.IndexedStringMap;
import org.apache.logging.log4j.util.MultiFormatStringBuilderFormattable;
import org.apache.logging.log4j.util.SortedArrayStringMap;
import org.apache.logging.log4j.util.StringBuilders;
import org.apache.logging.log4j.util.TriConsumer;

@AsynchronouslyFormattable
public class MapMessage<M extends MapMessage<M, V>, V> implements MultiFormatStringBuilderFormattable {
    private static final long serialVersionUID = -5031471831131487120L;
    private final IndexedStringMap data;

    public String getFormat() {
        return "";
    }

    public Throwable getThrowable() {
        return null;
    }

    /* access modifiers changed from: protected */
    public String toKey(String str) {
        return str;
    }

    /* access modifiers changed from: protected */
    public void validate(String str, byte b) {
    }

    /* access modifiers changed from: protected */
    public void validate(String str, char c) {
    }

    /* access modifiers changed from: protected */
    public void validate(String str, double d) {
    }

    /* access modifiers changed from: protected */
    public void validate(String str, float f) {
    }

    /* access modifiers changed from: protected */
    public void validate(String str, int i) {
    }

    /* access modifiers changed from: protected */
    public void validate(String str, long j) {
    }

    /* access modifiers changed from: protected */
    public void validate(String str, Object obj) {
    }

    /* access modifiers changed from: protected */
    public void validate(String str, String str2) {
    }

    /* access modifiers changed from: protected */
    public void validate(String str, short s) {
    }

    /* access modifiers changed from: protected */
    public void validate(String str, boolean z) {
    }

    public enum MapFormat {
        XML,
        JSON,
        JAVA,
        JAVA_UNQUOTED;

        public static MapFormat lookupIgnoreCase(String str) {
            MapFormat mapFormat = XML;
            if (mapFormat.name().equalsIgnoreCase(str)) {
                return mapFormat;
            }
            MapFormat mapFormat2 = JSON;
            if (mapFormat2.name().equalsIgnoreCase(str)) {
                return mapFormat2;
            }
            MapFormat mapFormat3 = JAVA;
            if (mapFormat3.name().equalsIgnoreCase(str)) {
                return mapFormat3;
            }
            MapFormat mapFormat4 = JAVA_UNQUOTED;
            if (mapFormat4.name().equalsIgnoreCase(str)) {
                return mapFormat4;
            }
            return null;
        }

        public static String[] names() {
            return new String[]{XML.name(), JSON.name(), JAVA.name(), JAVA_UNQUOTED.name()};
        }
    }

    public MapMessage() {
        this.data = new SortedArrayStringMap();
    }

    public MapMessage(int i) {
        this.data = new SortedArrayStringMap(i);
    }

    public MapMessage(Map<String, V> map) {
        this.data = new SortedArrayStringMap((Map<String, ?>) map);
    }

    public String[] getFormats() {
        return MapFormat.names();
    }

    public Object[] getParameters() {
        Object[] objArr = new Object[this.data.size()];
        for (int i = 0; i < this.data.size(); i++) {
            objArr[i] = this.data.getValueAt(i);
        }
        return objArr;
    }

    public Map<String, V> getData() {
        TreeMap treeMap = new TreeMap();
        for (int i = 0; i < this.data.size(); i++) {
            treeMap.put(this.data.getKeyAt(i), this.data.getValueAt(i));
        }
        return Collections.unmodifiableMap(treeMap);
    }

    public IndexedReadOnlyStringMap getIndexedReadOnlyStringMap() {
        return this.data;
    }

    public void clear() {
        this.data.clear();
    }

    public boolean containsKey(String str) {
        return this.data.containsKey(str);
    }

    public void put(String str, String str2) {
        if (str2 != null) {
            String key = toKey(str);
            validate(key, str2);
            this.data.putValue(key, str2);
            return;
        }
        throw new IllegalArgumentException("No value provided for key " + str);
    }

    public void putAll(Map<String, String> map) {
        for (Map.Entry next : map.entrySet()) {
            this.data.putValue((String) next.getKey(), next.getValue());
        }
    }

    public String get(String str) {
        return ParameterFormatter.deepToString(this.data.getValue(str));
    }

    public String remove(String str) {
        String str2 = get(str);
        this.data.remove(str);
        return str2;
    }

    public String asString() {
        MapFormat mapFormat = null;
        return format((MapFormat) null, new StringBuilder()).toString();
    }

    public String asString(String str) {
        try {
            return format((MapFormat) EnglishEnums.valueOf(MapFormat.class, str), new StringBuilder()).toString();
        } catch (IllegalArgumentException unused) {
            return asString();
        }
    }

    public <CV> void forEach(BiConsumer<String, ? super CV> biConsumer) {
        this.data.forEach(biConsumer);
    }

    public <CV, S> void forEach(TriConsumer<String, ? super CV, S> triConsumer, S s) {
        this.data.forEach(triConsumer, s);
    }

    private StringBuilder format(MapFormat mapFormat, StringBuilder sb) {
        if (mapFormat == null) {
            appendMap(sb);
        } else {
            int i = AnonymousClass1.$SwitchMap$org$apache$logging$log4j$message$MapMessage$MapFormat[mapFormat.ordinal()];
            if (i == 1) {
                asXml(sb);
            } else if (i == 2) {
                asJson(sb);
            } else if (i == 3) {
                asJava(sb);
            } else if (i != 4) {
                appendMap(sb);
            } else {
                asJavaUnquoted(sb);
            }
        }
        return sb;
    }

    /* renamed from: org.apache.logging.log4j.message.MapMessage$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$org$apache$logging$log4j$message$MapMessage$MapFormat;

        /* JADX WARNING: Can't wrap try/catch for region: R(8:0|1|2|3|4|5|6|(3:7|8|10)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        static {
            /*
                org.apache.logging.log4j.message.MapMessage$MapFormat[] r0 = org.apache.logging.log4j.message.MapMessage.MapFormat.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$org$apache$logging$log4j$message$MapMessage$MapFormat = r0
                org.apache.logging.log4j.message.MapMessage$MapFormat r1 = org.apache.logging.log4j.message.MapMessage.MapFormat.XML     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$org$apache$logging$log4j$message$MapMessage$MapFormat     // Catch:{ NoSuchFieldError -> 0x001d }
                org.apache.logging.log4j.message.MapMessage$MapFormat r1 = org.apache.logging.log4j.message.MapMessage.MapFormat.JSON     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$org$apache$logging$log4j$message$MapMessage$MapFormat     // Catch:{ NoSuchFieldError -> 0x0028 }
                org.apache.logging.log4j.message.MapMessage$MapFormat r1 = org.apache.logging.log4j.message.MapMessage.MapFormat.JAVA     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = $SwitchMap$org$apache$logging$log4j$message$MapMessage$MapFormat     // Catch:{ NoSuchFieldError -> 0x0033 }
                org.apache.logging.log4j.message.MapMessage$MapFormat r1 = org.apache.logging.log4j.message.MapMessage.MapFormat.JAVA_UNQUOTED     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: org.apache.logging.log4j.message.MapMessage.AnonymousClass1.<clinit>():void");
        }
    }

    public void asXml(StringBuilder sb) {
        sb.append("<Map>\n");
        for (int i = 0; i < this.data.size(); i++) {
            sb.append("  <Entry key=\"").append(this.data.getKeyAt(i)).append("\">");
            int length = sb.length();
            ParameterFormatter.recursiveDeepToString(this.data.getValueAt(i), sb);
            StringBuilders.escapeXml(sb, length);
            sb.append("</Entry>\n");
        }
        sb.append("</Map>");
    }

    public String getFormattedMessage() {
        return asString();
    }

    public String getFormattedMessage(String[] strArr) {
        return format(getFormat(strArr), new StringBuilder()).toString();
    }

    private MapFormat getFormat(String[] strArr) {
        if (!(strArr == null || strArr.length == 0)) {
            for (String lookupIgnoreCase : strArr) {
                MapFormat lookupIgnoreCase2 = MapFormat.lookupIgnoreCase(lookupIgnoreCase);
                if (lookupIgnoreCase2 != null) {
                    return lookupIgnoreCase2;
                }
            }
        }
        return null;
    }

    /* access modifiers changed from: protected */
    public void appendMap(StringBuilder sb) {
        for (int i = 0; i < this.data.size(); i++) {
            if (i > 0) {
                sb.append(Chars.SPACE);
            }
            sb.append(this.data.getKeyAt(i)).append(Chars.EQ).append('\"');
            ParameterFormatter.recursiveDeepToString(this.data.getValueAt(i), sb);
            sb.append('\"');
        }
    }

    /* access modifiers changed from: protected */
    public void asJson(StringBuilder sb) {
        MapMessageJsonFormatter.format(sb, this.data);
    }

    /* access modifiers changed from: protected */
    public void asJavaUnquoted(StringBuilder sb) {
        asJava(sb, false);
    }

    /* access modifiers changed from: protected */
    public void asJava(StringBuilder sb) {
        asJava(sb, true);
    }

    private void asJava(StringBuilder sb, boolean z) {
        sb.append('{');
        for (int i = 0; i < this.data.size(); i++) {
            if (i > 0) {
                sb.append(", ");
            }
            sb.append(this.data.getKeyAt(i)).append(Chars.EQ);
            if (z) {
                sb.append('\"');
            }
            ParameterFormatter.recursiveDeepToString(this.data.getValueAt(i), sb);
            if (z) {
                sb.append('\"');
            }
        }
        sb.append('}');
    }

    public M newInstance(Map<String, V> map) {
        return new MapMessage(map);
    }

    public String toString() {
        return asString();
    }

    public void formatTo(StringBuilder sb) {
        MapFormat mapFormat = null;
        format((MapFormat) null, sb);
    }

    public void formatTo(String[] strArr, StringBuilder sb) {
        format(getFormat(strArr), sb);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return this.data.equals(((MapMessage) obj).data);
    }

    public int hashCode() {
        return this.data.hashCode();
    }

    public M with(String str, boolean z) {
        String key = toKey(str);
        validate(key, z);
        this.data.putValue(key, Boolean.valueOf(z));
        return this;
    }

    public M with(String str, byte b) {
        String key = toKey(str);
        validate(key, b);
        this.data.putValue(key, Byte.valueOf(b));
        return this;
    }

    public M with(String str, char c) {
        String key = toKey(str);
        validate(key, c);
        this.data.putValue(key, Character.valueOf(c));
        return this;
    }

    public M with(String str, double d) {
        String key = toKey(str);
        validate(key, d);
        this.data.putValue(key, Double.valueOf(d));
        return this;
    }

    public M with(String str, float f) {
        String key = toKey(str);
        validate(key, f);
        this.data.putValue(key, Float.valueOf(f));
        return this;
    }

    public M with(String str, int i) {
        String key = toKey(str);
        validate(key, i);
        this.data.putValue(key, Integer.valueOf(i));
        return this;
    }

    public M with(String str, long j) {
        String key = toKey(str);
        validate(key, j);
        this.data.putValue(key, Long.valueOf(j));
        return this;
    }

    public M with(String str, Object obj) {
        String key = toKey(str);
        validate(key, obj);
        this.data.putValue(key, obj);
        return this;
    }

    public M with(String str, short s) {
        String key = toKey(str);
        validate(key, s);
        this.data.putValue(key, Short.valueOf(s));
        return this;
    }

    public M with(String str, String str2) {
        put(toKey(str), str2);
        return this;
    }
}
