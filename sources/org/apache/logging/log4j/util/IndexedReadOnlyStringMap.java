package org.apache.logging.log4j.util;

public interface IndexedReadOnlyStringMap extends ReadOnlyStringMap {
    String getKeyAt(int i);

    <V> V getValueAt(int i);

    int indexOfKey(String str);
}
