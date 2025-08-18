package org.apache.poi.hpsf;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.commons.collections4.bidimap.TreeBidiMap;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.util.Unbox;
import org.apache.poi.util.CodePageUtil;

public class CustomProperties implements Map<String, Object> {
    private static final Logger LOG = LogManager.getLogger((Class<?>) CustomProperties.class);
    private int codepage = -1;
    private final TreeBidiMap<Long, String> dictionary = new TreeBidiMap<>();
    private boolean isPure = true;
    private final HashMap<Long, CustomProperty> props = new HashMap<>();

    public CustomProperty put(String str, CustomProperty customProperty) {
        if (str == null) {
            this.isPure = false;
            return null;
        } else if (str.equals(customProperty.getName())) {
            checkCodePage(str);
            this.props.remove(this.dictionary.getKey((Object) str));
            this.dictionary.put(Long.valueOf(customProperty.getID()), str);
            return this.props.put(Long.valueOf(customProperty.getID()), customProperty);
        } else {
            throw new IllegalArgumentException("Parameter \"name\" (" + str + ") and custom property's name (" + customProperty.getName() + ") do not match.");
        }
    }

    public Object put(String str, Object obj) {
        int i;
        if (obj instanceof String) {
            i = 30;
        } else if (obj instanceof Short) {
            i = 2;
        } else if (obj instanceof Integer) {
            i = 3;
        } else if (obj instanceof Long) {
            i = 20;
        } else if (obj instanceof Float) {
            i = 4;
        } else if (obj instanceof Double) {
            i = 5;
        } else if (obj instanceof Boolean) {
            i = 11;
        } else {
            if (obj instanceof BigInteger) {
                BigInteger bigInteger = (BigInteger) obj;
                if (bigInteger.bitLength() <= 64 && bigInteger.compareTo(BigInteger.ZERO) >= 0) {
                    i = 21;
                }
            }
            if (obj instanceof Date) {
                i = 64;
            } else {
                throw new IllegalStateException("unsupported datatype - currently String,Short,Integer,Long,Float,Double,Boolean,BigInteger(unsigned long),Date can be processed.");
            }
        }
        return put(new CustomProperty(new Property(-1, (long) i, obj), str));
    }

    public Object get(Object obj) {
        CustomProperty customProperty = this.props.get(this.dictionary.getKey(obj));
        if (customProperty != null) {
            return customProperty.getValue();
        }
        return null;
    }

    public CustomProperty remove(Object obj) {
        return this.props.remove(this.dictionary.removeValue(obj));
    }

    public int size() {
        return this.props.size();
    }

    public boolean isEmpty() {
        return this.props.isEmpty();
    }

    public void clear() {
        this.props.clear();
    }

    public int hashCode() {
        return this.props.hashCode();
    }

    public boolean equals(Object obj) {
        return (obj instanceof CustomProperties) && this.props.equals(((CustomProperties) obj).props);
    }

    public void putAll(Map<? extends String, ?> map) {
        for (Map.Entry next : map.entrySet()) {
            put((String) next.getKey(), next.getValue());
        }
    }

    public List<CustomProperty> properties() {
        ArrayList arrayList = new ArrayList(this.props.size());
        arrayList.addAll(this.props.values());
        return Collections.unmodifiableList(arrayList);
    }

    public Collection<Object> values() {
        ArrayList arrayList = new ArrayList(this.props.size());
        for (CustomProperty value : this.props.values()) {
            arrayList.add(value.getValue());
        }
        return Collections.unmodifiableCollection(arrayList);
    }

    public Set<Map.Entry<String, Object>> entrySet() {
        LinkedHashMap linkedHashMap = new LinkedHashMap(this.props.size());
        for (CustomProperty next : this.props.values()) {
            linkedHashMap.put(next.getName(), next.getValue());
        }
        return Collections.unmodifiableSet(linkedHashMap.entrySet());
    }

    public Set<String> keySet() {
        return Collections.unmodifiableSet(this.dictionary.values());
    }

    public Set<String> nameSet() {
        return Collections.unmodifiableSet(this.dictionary.values());
    }

    public Set<Long> idSet() {
        return Collections.unmodifiableSet(this.dictionary.keySet());
    }

    public void setCodepage(int i) {
        this.codepage = i;
    }

    public int getCodepage() {
        return this.codepage;
    }

    /* access modifiers changed from: package-private */
    public Map<Long, String> getDictionary() {
        return this.dictionary;
    }

    public boolean containsKey(Object obj) {
        return ((obj instanceof Long) && this.dictionary.containsKey(obj)) || this.dictionary.containsValue(obj);
    }

    public boolean containsValue(Object obj) {
        if (obj instanceof CustomProperty) {
            return this.props.containsValue(obj);
        }
        for (CustomProperty value : this.props.values()) {
            if (value.getValue() == obj) {
                return true;
            }
        }
        return false;
    }

    public boolean isPure() {
        return this.isPure;
    }

    public void setPure(boolean z) {
        this.isPure = z;
    }

    private Object put(CustomProperty customProperty) throws ClassCastException {
        Long l;
        String name = customProperty.getName();
        if (name == null) {
            l = null;
        } else {
            l = this.dictionary.getKey((Object) name);
        }
        if (l != null) {
            customProperty.setID(l.longValue());
        } else {
            customProperty.setID(Math.max(this.dictionary.isEmpty() ? 0 : this.dictionary.lastKey().longValue(), 31) + 1);
        }
        return put(name, customProperty);
    }

    private void checkCodePage(String str) {
        String str2;
        int codepage2 = getCodepage();
        if (codepage2 == -1) {
            codepage2 = 1252;
        }
        if (codepage2 != 1200) {
            try {
                str2 = CodePageUtil.codepageToEncoding(codepage2, false);
            } catch (UnsupportedEncodingException unused) {
                LOG.atError().log("Codepage '{}' can't be found.", (Object) Unbox.box(codepage2));
                str2 = "";
            }
            if (str2.isEmpty() || !Charset.forName(str2).newEncoder().canEncode(str)) {
                LOG.atDebug().log("Charset '{}' can't encode '{}' - switching to unicode.", str2, str);
                setCodepage(1200);
            }
        }
    }
}
