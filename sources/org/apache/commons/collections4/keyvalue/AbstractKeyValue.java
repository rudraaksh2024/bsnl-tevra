package org.apache.commons.collections4.keyvalue;

import org.apache.commons.collections4.KeyValue;
import org.apache.logging.log4j.util.Chars;

public abstract class AbstractKeyValue<K, V> implements KeyValue<K, V> {
    private K key;
    private V value;

    protected AbstractKeyValue(K k, V v) {
        this.key = k;
        this.value = v;
    }

    public K getKey() {
        return this.key;
    }

    /* access modifiers changed from: protected */
    public K setKey(K k) {
        K k2 = this.key;
        this.key = k;
        return k2;
    }

    public V getValue() {
        return this.value;
    }

    /* access modifiers changed from: protected */
    public V setValue(V v) {
        V v2 = this.value;
        this.value = v;
        return v2;
    }

    public String toString() {
        return new StringBuilder().append(getKey()).append(Chars.EQ).append(getValue()).toString();
    }
}
