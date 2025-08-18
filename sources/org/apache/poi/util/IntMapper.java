package org.apache.poi.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Spliterator;
import org.apache.poi.common.Duplicatable;

public class IntMapper<T> implements Duplicatable, Iterable<T> {
    private static final int _default_size = 10;
    private final List<T> elements;
    private final Map<T, Integer> valueKeyMap;

    public IntMapper() {
        this(10);
    }

    public IntMapper(int i) {
        this.elements = new ArrayList(i);
        this.valueKeyMap = new HashMap(i);
    }

    public IntMapper(IntMapper<T> intMapper) {
        this.elements = new ArrayList(intMapper.elements);
        this.valueKeyMap = new HashMap(intMapper.valueKeyMap);
    }

    public boolean add(T t) {
        int size = this.elements.size();
        this.elements.add(t);
        this.valueKeyMap.put(t, Integer.valueOf(size));
        return true;
    }

    public int size() {
        return this.elements.size();
    }

    public T get(int i) {
        return this.elements.get(i);
    }

    public int getIndex(T t) {
        return this.valueKeyMap.getOrDefault(t, -1).intValue();
    }

    public Iterator<T> iterator() {
        return this.elements.iterator();
    }

    public Spliterator<T> spliterator() {
        return this.elements.spliterator();
    }

    public IntMapper<T> copy() {
        return new IntMapper<>(this);
    }

    public List<T> getElements() {
        return this.elements;
    }
}
