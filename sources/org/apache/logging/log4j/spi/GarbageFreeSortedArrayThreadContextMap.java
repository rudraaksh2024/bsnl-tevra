package org.apache.logging.log4j.spi;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import org.apache.logging.log4j.util.PropertiesUtil;
import org.apache.logging.log4j.util.ReadOnlyStringMap;
import org.apache.logging.log4j.util.SortedArrayStringMap;
import org.apache.logging.log4j.util.StringMap;

class GarbageFreeSortedArrayThreadContextMap implements ReadOnlyThreadContextMap, ObjectThreadContextMap {
    protected static final int DEFAULT_INITIAL_CAPACITY = 16;
    public static final String INHERITABLE_MAP = "isThreadContextMapInheritable";
    protected static final String PROPERTY_NAME_INITIAL_CAPACITY = "log4j2.ThreadContext.initial.capacity";
    private static volatile boolean inheritableMap;
    private static volatile int initialCapacity;
    protected final ThreadLocal<StringMap> localMap = createThreadLocalMap();

    static void init() {
        PropertiesUtil properties = PropertiesUtil.getProperties();
        initialCapacity = properties.getIntegerProperty(PROPERTY_NAME_INITIAL_CAPACITY, 16);
        inheritableMap = properties.getBooleanProperty("isThreadContextMapInheritable");
    }

    static {
        init();
    }

    private ThreadLocal<StringMap> createThreadLocalMap() {
        if (inheritableMap) {
            return new InheritableThreadLocal<StringMap>() {
                /* access modifiers changed from: protected */
                public StringMap childValue(StringMap stringMap) {
                    if (stringMap != null) {
                        return GarbageFreeSortedArrayThreadContextMap.this.createStringMap(stringMap);
                    }
                    return null;
                }
            };
        }
        return new ThreadLocal<>();
    }

    /* access modifiers changed from: protected */
    public StringMap createStringMap() {
        return new SortedArrayStringMap(initialCapacity);
    }

    /* access modifiers changed from: protected */
    public StringMap createStringMap(ReadOnlyStringMap readOnlyStringMap) {
        return new SortedArrayStringMap(readOnlyStringMap);
    }

    private StringMap getThreadLocalMap() {
        StringMap stringMap = this.localMap.get();
        if (stringMap != null) {
            return stringMap;
        }
        StringMap createStringMap = createStringMap();
        this.localMap.set(createStringMap);
        return createStringMap;
    }

    public void put(String str, String str2) {
        getThreadLocalMap().putValue(str, str2);
    }

    public void putValue(String str, Object obj) {
        getThreadLocalMap().putValue(str, obj);
    }

    public void putAll(Map<String, String> map) {
        if (map != null && !map.isEmpty()) {
            StringMap threadLocalMap = getThreadLocalMap();
            for (Map.Entry next : map.entrySet()) {
                threadLocalMap.putValue((String) next.getKey(), next.getValue());
            }
        }
    }

    public <V> void putAllValues(Map<String, V> map) {
        if (map != null && !map.isEmpty()) {
            StringMap threadLocalMap = getThreadLocalMap();
            for (Map.Entry next : map.entrySet()) {
                threadLocalMap.putValue((String) next.getKey(), next.getValue());
            }
        }
    }

    public String get(String str) {
        return (String) getValue(str);
    }

    public <V> V getValue(String str) {
        StringMap stringMap = this.localMap.get();
        if (stringMap == null) {
            return null;
        }
        return stringMap.getValue(str);
    }

    public void remove(String str) {
        StringMap stringMap = this.localMap.get();
        if (stringMap != null) {
            stringMap.remove(str);
        }
    }

    public void removeAll(Iterable<String> iterable) {
        StringMap stringMap = this.localMap.get();
        if (stringMap != null) {
            for (String remove : iterable) {
                stringMap.remove(remove);
            }
        }
    }

    public void clear() {
        StringMap stringMap = this.localMap.get();
        if (stringMap != null) {
            stringMap.clear();
        }
    }

    public boolean containsKey(String str) {
        StringMap stringMap = this.localMap.get();
        return stringMap != null && stringMap.containsKey(str);
    }

    public Map<String, String> getCopy() {
        StringMap stringMap = this.localMap.get();
        return stringMap == null ? new HashMap() : stringMap.toMap();
    }

    public StringMap getReadOnlyContextData() {
        StringMap stringMap = this.localMap.get();
        if (stringMap != null) {
            return stringMap;
        }
        StringMap createStringMap = createStringMap();
        this.localMap.set(createStringMap);
        return createStringMap;
    }

    public Map<String, String> getImmutableMapOrNull() {
        StringMap stringMap = this.localMap.get();
        if (stringMap == null) {
            return null;
        }
        return Collections.unmodifiableMap(stringMap.toMap());
    }

    public boolean isEmpty() {
        StringMap stringMap = this.localMap.get();
        return stringMap == null || stringMap.isEmpty();
    }

    public String toString() {
        StringMap stringMap = this.localMap.get();
        if (stringMap == null) {
            return "{}";
        }
        return stringMap.toString();
    }

    public int hashCode() {
        int i;
        StringMap stringMap = this.localMap.get();
        if (stringMap == null) {
            i = 0;
        } else {
            i = stringMap.hashCode();
        }
        return 31 + i;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && (obj instanceof ThreadContextMap)) {
            return Objects.equals(getImmutableMapOrNull(), ((ThreadContextMap) obj).getImmutableMapOrNull());
        }
        return false;
    }
}
