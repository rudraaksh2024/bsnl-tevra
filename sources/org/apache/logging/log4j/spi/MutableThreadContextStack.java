package org.apache.logging.log4j.spi;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import org.apache.logging.log4j.ThreadContext;
import org.apache.logging.log4j.util.Chars;
import org.apache.logging.log4j.util.StringBuilderFormattable;

public class MutableThreadContextStack implements ThreadContextStack, StringBuilderFormattable {
    private static final long serialVersionUID = 50505011;
    private boolean frozen;
    private final List<String> list;

    public MutableThreadContextStack() {
        this((List<String>) new ArrayList());
    }

    public MutableThreadContextStack(List<String> list2) {
        this.list = new ArrayList(list2);
    }

    private MutableThreadContextStack(MutableThreadContextStack mutableThreadContextStack) {
        this.list = new ArrayList(mutableThreadContextStack.list);
    }

    private void checkInvariants() {
        if (this.frozen) {
            throw new UnsupportedOperationException("context stack has been frozen");
        }
    }

    public String pop() {
        checkInvariants();
        if (this.list.isEmpty()) {
            return null;
        }
        return this.list.remove(this.list.size() - 1);
    }

    public String peek() {
        if (this.list.isEmpty()) {
            return null;
        }
        return this.list.get(this.list.size() - 1);
    }

    public void push(String str) {
        checkInvariants();
        this.list.add(str);
    }

    public int getDepth() {
        return this.list.size();
    }

    public List<String> asList() {
        return this.list;
    }

    public void trim(int i) {
        checkInvariants();
        if (i < 0) {
            throw new IllegalArgumentException("Maximum stack depth cannot be negative");
        } else if (this.list != null) {
            ArrayList arrayList = new ArrayList(this.list.size());
            int min = Math.min(i, this.list.size());
            for (int i2 = 0; i2 < min; i2++) {
                arrayList.add(this.list.get(i2));
            }
            this.list.clear();
            this.list.addAll(arrayList);
        }
    }

    public ThreadContextStack copy() {
        return new MutableThreadContextStack(this);
    }

    public void clear() {
        checkInvariants();
        this.list.clear();
    }

    public int size() {
        return this.list.size();
    }

    public boolean isEmpty() {
        return this.list.isEmpty();
    }

    public boolean contains(Object obj) {
        return this.list.contains(obj);
    }

    public Iterator<String> iterator() {
        return this.list.iterator();
    }

    public Object[] toArray() {
        return this.list.toArray();
    }

    public <T> T[] toArray(T[] tArr) {
        return this.list.toArray(tArr);
    }

    public boolean add(String str) {
        checkInvariants();
        return this.list.add(str);
    }

    public boolean remove(Object obj) {
        checkInvariants();
        return this.list.remove(obj);
    }

    public boolean containsAll(Collection<?> collection) {
        return this.list.containsAll(collection);
    }

    public boolean addAll(Collection<? extends String> collection) {
        checkInvariants();
        return this.list.addAll(collection);
    }

    public boolean removeAll(Collection<?> collection) {
        checkInvariants();
        return this.list.removeAll(collection);
    }

    public boolean retainAll(Collection<?> collection) {
        checkInvariants();
        return this.list.retainAll(collection);
    }

    public String toString() {
        return String.valueOf(this.list);
    }

    public void formatTo(StringBuilder sb) {
        sb.append('[');
        for (int i = 0; i < this.list.size(); i++) {
            if (i > 0) {
                sb.append(',').append(Chars.SPACE);
            }
            sb.append(this.list.get(i));
        }
        sb.append(']');
    }

    public int hashCode() {
        return Objects.hashCode(this.list) + 31;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof ThreadContextStack)) {
            return false;
        }
        return Objects.equals(this.list, ((ThreadContextStack) obj).asList());
    }

    public ThreadContext.ContextStack getImmutableStackOrNull() {
        return copy();
    }

    public void freeze() {
        this.frozen = true;
    }

    public boolean isFrozen() {
        return this.frozen;
    }
}
