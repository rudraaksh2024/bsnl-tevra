package org.apache.logging.log4j.util;

public interface StringMap extends ReadOnlyStringMap {
    void clear();

    boolean equals(Object obj);

    void freeze();

    int hashCode();

    boolean isFrozen();

    void putAll(ReadOnlyStringMap readOnlyStringMap);

    void putValue(String str, Object obj);

    void remove(String str);
}
