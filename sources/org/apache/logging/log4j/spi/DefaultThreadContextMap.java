package org.apache.logging.log4j.spi;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import org.apache.logging.log4j.util.BiConsumer;
import org.apache.logging.log4j.util.PropertiesUtil;
import org.apache.logging.log4j.util.ReadOnlyStringMap;
import org.apache.logging.log4j.util.TriConsumer;

public class DefaultThreadContextMap implements ThreadContextMap, ReadOnlyStringMap {
    public static final String INHERITABLE_MAP = "isThreadContextMapInheritable";
    private static boolean inheritableMap = false;
    private static final long serialVersionUID = 8218007901108944053L;
    private final ThreadLocal<Map<String, String>> localMap;
    private final boolean useMap;

    static {
        init();
    }

    static ThreadLocal<Map<String, String>> createThreadLocalMap(final boolean z) {
        if (inheritableMap) {
            return new InheritableThreadLocal<Map<String, String>>() {
                /* access modifiers changed from: protected */
                public Map<String, String> childValue(Map<String, String> map) {
                    if (map == null || !z) {
                        return null;
                    }
                    return Collections.unmodifiableMap(new HashMap(map));
                }
            };
        }
        return new ThreadLocal<>();
    }

    static void init() {
        inheritableMap = PropertiesUtil.getProperties().getBooleanProperty("isThreadContextMapInheritable");
    }

    public DefaultThreadContextMap() {
        this(true);
    }

    public DefaultThreadContextMap(boolean z) {
        this.useMap = z;
        this.localMap = createThreadLocalMap(z);
    }

    public void put(String str, String str2) {
        if (this.useMap) {
            Map map = this.localMap.get();
            HashMap hashMap = map == null ? new HashMap(1) : new HashMap(map);
            hashMap.put(str, str2);
            this.localMap.set(Collections.unmodifiableMap(hashMap));
        }
    }

    public void putAll(Map<String, String> map) {
        if (this.useMap) {
            Map map2 = this.localMap.get();
            HashMap hashMap = map2 == null ? new HashMap(map.size()) : new HashMap(map2);
            for (Map.Entry next : map.entrySet()) {
                hashMap.put(next.getKey(), next.getValue());
            }
            this.localMap.set(Collections.unmodifiableMap(hashMap));
        }
    }

    public String get(String str) {
        Map map = this.localMap.get();
        if (map == null) {
            return null;
        }
        return (String) map.get(str);
    }

    public void remove(String str) {
        Map map = this.localMap.get();
        if (map != null) {
            HashMap hashMap = new HashMap(map);
            hashMap.remove(str);
            this.localMap.set(Collections.unmodifiableMap(hashMap));
        }
    }

    public void removeAll(Iterable<String> iterable) {
        Map map = this.localMap.get();
        if (map != null) {
            HashMap hashMap = new HashMap(map);
            for (String remove : iterable) {
                hashMap.remove(remove);
            }
            this.localMap.set(Collections.unmodifiableMap(hashMap));
        }
    }

    public void clear() {
        this.localMap.remove();
    }

    public Map<String, String> toMap() {
        return getCopy();
    }

    public boolean containsKey(String str) {
        Map map = this.localMap.get();
        return map != null && map.containsKey(str);
    }

    public <V> void forEach(BiConsumer<String, ? super V> biConsumer) {
        Map map = this.localMap.get();
        if (map != null) {
            for (Map.Entry entry : map.entrySet()) {
                biConsumer.accept(entry.getKey(), entry.getValue());
            }
        }
    }

    public <V, S> void forEach(TriConsumer<String, ? super V, S> triConsumer, S s) {
        Map map = this.localMap.get();
        if (map != null) {
            for (Map.Entry entry : map.entrySet()) {
                triConsumer.accept(entry.getKey(), entry.getValue(), s);
            }
        }
    }

    public <V> V getValue(String str) {
        Map map = this.localMap.get();
        if (map == null) {
            return null;
        }
        return (String) map.get(str);
    }

    public Map<String, String> getCopy() {
        Map map = this.localMap.get();
        return map == null ? new HashMap() : new HashMap(map);
    }

    public Map<String, String> getImmutableMapOrNull() {
        return this.localMap.get();
    }

    public boolean isEmpty() {
        Map map = this.localMap.get();
        return map == null || map.isEmpty();
    }

    public int size() {
        Map map = this.localMap.get();
        if (map == null) {
            return 0;
        }
        return map.size();
    }

    public String toString() {
        Map map = this.localMap.get();
        if (map == null) {
            return "{}";
        }
        return map.toString();
    }

    public int hashCode() {
        int i;
        Map map = this.localMap.get();
        if (map == null) {
            i = 0;
        } else {
            i = map.hashCode();
        }
        return ((i + 31) * 31) + Boolean.valueOf(this.useMap).hashCode();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if ((!(obj instanceof DefaultThreadContextMap) || this.useMap == ((DefaultThreadContextMap) obj).useMap) && (obj instanceof ThreadContextMap)) {
            return Objects.equals(this.localMap.get(), ((ThreadContextMap) obj).getImmutableMapOrNull());
        }
        return false;
    }
}
