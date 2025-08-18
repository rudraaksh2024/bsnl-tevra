package org.apache.commons.collections4.bag;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Collection;
import java.util.Iterator;
import org.apache.commons.collections4.SortedBag;

public final class CollectionSortedBag<E> extends AbstractSortedBagDecorator<E> {
    private static final long serialVersionUID = -2560033712679053143L;

    public static <E> SortedBag<E> collectionSortedBag(SortedBag<E> sortedBag) {
        return new CollectionSortedBag(sortedBag);
    }

    public CollectionSortedBag(SortedBag<E> sortedBag) {
        super(sortedBag);
    }

    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.defaultWriteObject();
        objectOutputStream.writeObject(decorated());
    }

    private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        objectInputStream.defaultReadObject();
        setCollection((Collection) objectInputStream.readObject());
    }

    public boolean containsAll(Collection<?> collection) {
        for (Object contains : collection) {
            if (!contains(contains)) {
                return false;
            }
        }
        return true;
    }

    public boolean add(E e) {
        return add(e, 1);
    }

    public boolean addAll(Collection<? extends E> collection) {
        Iterator<? extends E> it = collection.iterator();
        while (true) {
            boolean z = false;
            while (true) {
                if (!it.hasNext()) {
                    return z;
                }
                boolean add = add(it.next(), 1);
                if (z || add) {
                    z = true;
                }
            }
        }
    }

    public boolean remove(Object obj) {
        return remove(obj, 1);
    }

    public boolean removeAll(Collection<?> collection) {
        if (collection == null) {
            return decorated().removeAll((Collection<?>) null);
        }
        Iterator<?> it = collection.iterator();
        while (true) {
            boolean z = false;
            while (true) {
                if (!it.hasNext()) {
                    return z;
                }
                Object next = it.next();
                boolean remove = remove(next, getCount(next));
                if (z || remove) {
                    z = true;
                }
            }
        }
    }

    public boolean retainAll(Collection<?> collection) {
        if (collection == null) {
            return decorated().retainAll((Collection<?>) null);
        }
        Iterator it = iterator();
        boolean z = false;
        while (it.hasNext()) {
            if (!collection.contains(it.next())) {
                it.remove();
                z = true;
            }
        }
        return z;
    }

    public boolean add(E e, int i) {
        decorated().add(e, i);
        return true;
    }
}
