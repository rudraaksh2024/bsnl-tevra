package org.apache.logging.log4j.spi;

import java.util.HashMap;
import java.util.Map;

public class NoOpThreadContextMap implements ThreadContextMap {
    public void clear() {
    }

    public boolean containsKey(String str) {
        return false;
    }

    public String get(String str) {
        return null;
    }

    public Map<String, String> getImmutableMapOrNull() {
        return null;
    }

    public boolean isEmpty() {
        return true;
    }

    public void put(String str, String str2) {
    }

    public void remove(String str) {
    }

    public Map<String, String> getCopy() {
        return new HashMap();
    }
}
