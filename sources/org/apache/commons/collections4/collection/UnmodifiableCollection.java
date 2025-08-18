package org.apache.commons.collections4.collection;

import java.util.Collection;
import java.util.Iterator;
import java.util.function.Predicate;
import org.apache.commons.collections4.Unmodifiable;
import org.apache.commons.collections4.iterators.UnmodifiableIterator;

public final class UnmodifiableCollection<E> extends AbstractCollectionDecorator<E> implements Unmodifiable {
    private static final long serialVersionUID = -239892006883819945L;

    public static <T> Collection<T> unmodifiableCollection(Collection<? extends T> collection) {
        if (collection instanceof Unmodifiable) {
            return collection;
        }
        return new UnmodifiableCollection(collection);
    }

    private UnmodifiableCollection(Collection<? extends E> collection) {
        super(collection);
    }

    public Iterator<E> iterator() {
        return UnmodifiableIterator.unmodifiableIterator(decorated().iterator());
    }

    public boolean add(E e) {
        throw new UnsupportedOperationException();
    }

    public boolean addAll(Collection<? extends E> collection) {
        throw new UnsupportedOperationException();
    }

    public void clear() {
        throw new UnsupportedOperationException();
    }

    public boolean remove(Object obj) {
        throw new UnsupportedOperationException();
    }

    public boolean removeIf(Predicate<? super E> predicate) {
        throw new UnsupportedOperationException();
    }

    public boolean removeAll(Collection<?> collection) {
        throw new UnsupportedOperationException();
    }

    public boolean retainAll(Collection<?> collection) {
        throw new UnsupportedOperationException();
    }
}
