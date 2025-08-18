package org.apache.xmlbeans;

import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Objects;
import org.apache.logging.log4j.util.Chars;

public class XmlSimpleList<T> implements List<T>, Serializable {
    private static final long serialVersionUID = 1;
    /* access modifiers changed from: private */
    public final List<T> underlying;

    public XmlSimpleList(List<T> list) {
        this.underlying = list;
    }

    public int size() {
        return this.underlying.size();
    }

    public boolean isEmpty() {
        return this.underlying.isEmpty();
    }

    public boolean contains(Object obj) {
        return this.underlying.contains(obj);
    }

    public boolean containsAll(Collection collection) {
        return this.underlying.containsAll(collection);
    }

    public Object[] toArray() {
        return this.underlying.toArray(new Object[0]);
    }

    public <X> X[] toArray(X[] xArr) {
        return this.underlying.toArray(xArr);
    }

    public boolean add(Object obj) {
        throw new UnsupportedOperationException();
    }

    public boolean addAll(Collection collection) {
        throw new UnsupportedOperationException();
    }

    public boolean remove(Object obj) {
        throw new UnsupportedOperationException();
    }

    public boolean removeAll(Collection collection) {
        throw new UnsupportedOperationException();
    }

    public boolean retainAll(Collection collection) {
        throw new UnsupportedOperationException();
    }

    public void clear() {
        throw new UnsupportedOperationException();
    }

    public T get(int i) {
        return this.underlying.get(i);
    }

    public T set(int i, T t) {
        throw new UnsupportedOperationException();
    }

    public void add(int i, Object obj) {
        throw new UnsupportedOperationException();
    }

    public T remove(int i) {
        throw new UnsupportedOperationException();
    }

    public int indexOf(Object obj) {
        return this.underlying.indexOf(obj);
    }

    public int lastIndexOf(Object obj) {
        return this.underlying.lastIndexOf(obj);
    }

    public boolean addAll(int i, Collection collection) {
        throw new UnsupportedOperationException();
    }

    public List<T> subList(int i, int i2) {
        return new XmlSimpleList(this.underlying.subList(i, i2));
    }

    public Iterator<T> iterator() {
        return new Iterator<T>() {
            final Iterator<T> i;

            {
                this.i = XmlSimpleList.this.underlying.iterator();
            }

            public boolean hasNext() {
                return this.i.hasNext();
            }

            public T next() {
                return this.i.next();
            }

            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }

    public ListIterator<T> listIterator() {
        return listIterator(0);
    }

    public ListIterator<T> listIterator(int i) {
        return new ListIterator<T>(i) {
            final ListIterator<T> i;
            final /* synthetic */ int val$index;

            {
                this.val$index = r2;
                this.i = XmlSimpleList.this.underlying.listIterator(r2);
            }

            public boolean hasNext() {
                return this.i.hasNext();
            }

            public T next() {
                return this.i.next();
            }

            public boolean hasPrevious() {
                return this.i.hasPrevious();
            }

            public T previous() {
                return this.i.previous();
            }

            public int nextIndex() {
                return this.i.nextIndex();
            }

            public int previousIndex() {
                return this.i.previousIndex();
            }

            public void remove() {
                throw new UnsupportedOperationException();
            }

            public void set(Object obj) {
                throw new UnsupportedOperationException();
            }

            public void add(Object obj) {
                throw new UnsupportedOperationException();
            }
        };
    }

    private String stringValue(Object obj) {
        if (obj instanceof SimpleValue) {
            return ((SimpleValue) obj).getStringValue();
        }
        return obj.toString();
    }

    public String toString() {
        int size = this.underlying.size();
        if (size == 0) {
            return "";
        }
        String stringValue = stringValue(this.underlying.get(0));
        if (size == 1) {
            return stringValue;
        }
        StringBuilder sb = new StringBuilder(stringValue);
        for (int i = 1; i < size; i++) {
            sb.append(Chars.SPACE);
            sb.append(stringValue(this.underlying.get(i)));
        }
        return sb.toString();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof XmlSimpleList)) {
            return false;
        }
        List<T> list = ((XmlSimpleList) obj).underlying;
        int size = this.underlying.size();
        if (size != list.size()) {
            return false;
        }
        for (int i = 0; i < size; i++) {
            if (!Objects.equals(this.underlying.get(i), list.get(i))) {
                return false;
            }
        }
        return true;
    }

    public int hashCode() {
        int i = 0;
        for (T hashCode : this.underlying) {
            i = (i * 19) + hashCode.hashCode();
        }
        return i;
    }
}
