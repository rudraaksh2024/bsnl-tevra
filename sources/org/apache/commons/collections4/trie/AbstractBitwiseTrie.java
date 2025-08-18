package org.apache.commons.collections4.trie;

import java.io.Serializable;
import java.util.AbstractMap;
import java.util.Map;
import org.apache.commons.collections4.Trie;

public abstract class AbstractBitwiseTrie<K, V> extends AbstractMap<K, V> implements Trie<K, V>, Serializable {
    private static final long serialVersionUID = 5826987063535505652L;
    private final KeyAnalyzer<? super K> keyAnalyzer;

    /* access modifiers changed from: package-private */
    public final K castKey(Object obj) {
        return obj;
    }

    protected AbstractBitwiseTrie(KeyAnalyzer<? super K> keyAnalyzer2) {
        if (keyAnalyzer2 != null) {
            this.keyAnalyzer = keyAnalyzer2;
            return;
        }
        throw new NullPointerException("keyAnalyzer");
    }

    /* access modifiers changed from: protected */
    public KeyAnalyzer<? super K> getKeyAnalyzer() {
        return this.keyAnalyzer;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("Trie[");
        sb.append(size()).append("]={\n");
        for (Map.Entry append : entrySet()) {
            sb.append("  ").append(append).append("\n");
        }
        sb.append("}\n");
        return sb.toString();
    }

    /* access modifiers changed from: package-private */
    public final int lengthInBits(K k) {
        if (k == null) {
            return 0;
        }
        return this.keyAnalyzer.lengthInBits(k);
    }

    /* access modifiers changed from: package-private */
    public final int bitsPerElement() {
        return this.keyAnalyzer.bitsPerElement();
    }

    /* access modifiers changed from: package-private */
    public final boolean isBitSet(K k, int i, int i2) {
        if (k == null) {
            return false;
        }
        return this.keyAnalyzer.isBitSet(k, i, i2);
    }

    /* access modifiers changed from: package-private */
    public final int bitIndex(K k, K k2) {
        return this.keyAnalyzer.bitIndex(k, 0, lengthInBits(k), k2, 0, lengthInBits(k2));
    }

    /* access modifiers changed from: package-private */
    public final boolean compareKeys(K k, K k2) {
        if (k == null) {
            return k2 == null;
        }
        if (k2 == null) {
            return false;
        }
        return this.keyAnalyzer.compare(k, k2) == 0;
    }

    static boolean compare(Object obj, Object obj2) {
        if (obj == null) {
            return obj2 == null;
        }
        return obj.equals(obj2);
    }

    static abstract class BasicEntry<K, V> implements Map.Entry<K, V>, Serializable {
        private static final long serialVersionUID = -944364551314110330L;
        protected K key;
        protected V value;

        public BasicEntry(K k) {
            this.key = k;
        }

        public BasicEntry(K k, V v) {
            this.key = k;
            this.value = v;
        }

        public V setKeyValue(K k, V v) {
            this.key = k;
            return setValue(v);
        }

        public K getKey() {
            return this.key;
        }

        public V getValue() {
            return this.value;
        }

        public V setValue(V v) {
            V v2 = this.value;
            this.value = v;
            return v2;
        }

        public int hashCode() {
            int i = 0;
            int hashCode = getKey() == null ? 0 : getKey().hashCode();
            if (getValue() != null) {
                i = getValue().hashCode();
            }
            return hashCode ^ i;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof Map.Entry)) {
                return false;
            }
            Map.Entry entry = (Map.Entry) obj;
            return AbstractBitwiseTrie.compare(this.key, entry.getKey()) && AbstractBitwiseTrie.compare(this.value, entry.getValue());
        }

        public String toString() {
            return this.key + "=" + this.value;
        }
    }
}
