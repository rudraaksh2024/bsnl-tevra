package org.apache.commons.math3.util;

public class Pair<K, V> {
    private final K key;
    private final V value;

    public Pair(K k, V v) {
        this.key = k;
        this.value = v;
    }

    public Pair(Pair<? extends K, ? extends V> pair) {
        this(pair.getKey(), pair.getValue());
    }

    public K getKey() {
        return this.key;
    }

    public V getValue() {
        return this.value;
    }

    public K getFirst() {
        return this.key;
    }

    public V getSecond() {
        return this.value;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Pair)) {
            return false;
        }
        Pair pair = (Pair) obj;
        K k = this.key;
        if (k != null ? k.equals(pair.key) : pair.key == null) {
            V v = this.value;
            if (v == null) {
                if (pair.value == null) {
                    return true;
                }
            } else if (v.equals(pair.value)) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        K k = this.key;
        int i = 0;
        int hashCode = k == null ? 0 : k.hashCode();
        V v = this.value;
        if (v != null) {
            i = v.hashCode();
        }
        return (i >>> 16) ^ ((hashCode * 37) + i);
    }

    public String toString() {
        return "[" + getKey() + ", " + getValue() + "]";
    }

    public static <K, V> Pair<K, V> create(K k, V v) {
        return new Pair<>(k, v);
    }
}
