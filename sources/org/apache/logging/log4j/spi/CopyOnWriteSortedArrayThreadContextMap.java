package org.apache.logging.log4j.spi;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import org.apache.logging.log4j.util.PropertiesUtil;
import org.apache.logging.log4j.util.ReadOnlyStringMap;
import org.apache.logging.log4j.util.SortedArrayStringMap;
import org.apache.logging.log4j.util.StringMap;

class CopyOnWriteSortedArrayThreadContextMap implements ReadOnlyThreadContextMap, ObjectThreadContextMap, CopyOnWrite {
    protected static final int DEFAULT_INITIAL_CAPACITY = 16;
    private static final StringMap EMPTY_CONTEXT_DATA;
    public static final String INHERITABLE_MAP = "isThreadContextMapInheritable";
    protected static final String PROPERTY_NAME_INITIAL_CAPACITY = "log4j2.ThreadContext.initial.capacity";
    private static volatile boolean inheritableMap;
    private static volatile int initialCapacity;
    private final ThreadLocal<StringMap> localMap = createThreadLocalMap();

    static {
        SortedArrayStringMap sortedArrayStringMap = new SortedArrayStringMap(1);
        EMPTY_CONTEXT_DATA = sortedArrayStringMap;
        sortedArrayStringMap.freeze();
        init();
    }

    static void init() {
        PropertiesUtil properties = PropertiesUtil.getProperties();
        initialCapacity = properties.getIntegerProperty(PROPERTY_NAME_INITIAL_CAPACITY, 16);
        inheritableMap = properties.getBooleanProperty("isThreadContextMapInheritable");
    }

    private ThreadLocal<StringMap> createThreadLocalMap() {
        return inheritableMap ? new InheritableThreadLocal<StringMap>() {
            /* access modifiers changed from: protected */
            public StringMap childValue(StringMap stringMap) {
                if (stringMap == null) {
                    return null;
                }
                StringMap createStringMap = CopyOnWriteSortedArrayThreadContextMap.this.createStringMap(stringMap);
                createStringMap.freeze();
                return createStringMap;
            }
        } : new ThreadLocal<>();
    }

    /* access modifiers changed from: protected */
    public StringMap createStringMap() {
        return new SortedArrayStringMap(initialCapacity);
    }

    /* access modifiers changed from: protected */
    public StringMap createStringMap(ReadOnlyStringMap readOnlyStringMap) {
        return new SortedArrayStringMap(readOnlyStringMap);
    }

    public void put(String str, String str2) {
        putValue(str, str2);
    }

    public void putValue(String str, Object obj) {
        StringMap stringMap = this.localMap.get();
        StringMap createStringMap = stringMap == null ? createStringMap() : createStringMap(stringMap);
        createStringMap.putValue(str, obj);
        createStringMap.freeze();
        this.localMap.set(createStringMap);
    }

    public void putAll(Map<String, String> map) {
        if (map != null && !map.isEmpty()) {
            StringMap stringMap = this.localMap.get();
            StringMap createStringMap = stringMap == null ? createStringMap() : createStringMap(stringMap);
            for (Map.Entry next : map.entrySet()) {
                createStringMap.putValue((String) next.getKey(), next.getValue());
            }
            createStringMap.freeze();
            this.localMap.set(createStringMap);
        }
    }

    public <V> void putAllValues(Map<String, V> map) {
        if (map != null && !map.isEmpty()) {
            StringMap stringMap = this.localMap.get();
            StringMap createStringMap = stringMap == null ? createStringMap() : createStringMap(stringMap);
            for (Map.Entry next : map.entrySet()) {
                createStringMap.putValue((String) next.getKey(), next.getValue());
            }
            createStringMap.freeze();
            this.localMap.set(createStringMap);
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
            StringMap createStringMap = createStringMap(stringMap);
            createStringMap.remove(str);
            createStringMap.freeze();
            this.localMap.set(createStringMap);
        }
    }

    public void removeAll(Iterable<String> iterable) {
        StringMap stringMap = this.localMap.get();
        if (stringMap != null) {
            StringMap createStringMap = createStringMap(stringMap);
            for (String remove : iterable) {
                createStringMap.remove(remove);
            }
            createStringMap.freeze();
            this.localMap.set(createStringMap);
        }
    }

    public void clear() {
        this.localMap.remove();
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
        return stringMap == null ? EMPTY_CONTEXT_DATA : stringMap;
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
