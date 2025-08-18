package org.apache.commons.collections4.collection;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.iterators.EmptyIterator;
import org.apache.commons.collections4.iterators.IteratorChain;
import org.apache.commons.collections4.list.UnmodifiableList;

public class CompositeCollection<E> implements Collection<E>, Serializable {
    private static final long serialVersionUID = 8417515734108306801L;
    private final List<Collection<E>> all = new ArrayList();
    private CollectionMutator<E> mutator;

    public interface CollectionMutator<E> extends Serializable {
        boolean add(CompositeCollection<E> compositeCollection, List<Collection<E>> list, E e);

        boolean addAll(CompositeCollection<E> compositeCollection, List<Collection<E>> list, Collection<? extends E> collection);

        boolean remove(CompositeCollection<E> compositeCollection, List<Collection<E>> list, Object obj);
    }

    public CompositeCollection() {
    }

    public CompositeCollection(Collection<E> collection) {
        addComposited(collection);
    }

    public CompositeCollection(Collection<E> collection, Collection<E> collection2) {
        addComposited(collection, collection2);
    }

    public CompositeCollection(Collection<E>... collectionArr) {
        addComposited(collectionArr);
    }

    public int size() {
        int i = 0;
        for (Collection<E> size : this.all) {
            i += size.size();
        }
        return i;
    }

    public boolean isEmpty() {
        for (Collection<E> isEmpty : this.all) {
            if (!isEmpty.isEmpty()) {
                return false;
            }
        }
        return true;
    }

    public boolean contains(Object obj) {
        for (Collection<E> contains : this.all) {
            if (contains.contains(obj)) {
                return true;
            }
        }
        return false;
    }

    public Iterator<E> iterator() {
        if (this.all.isEmpty()) {
            return EmptyIterator.emptyIterator();
        }
        IteratorChain iteratorChain = new IteratorChain();
        for (Collection<E> it : this.all) {
            iteratorChain.addIterator(it.iterator());
        }
        return iteratorChain;
    }

    public Object[] toArray() {
        Object[] objArr = new Object[size()];
        Iterator it = iterator();
        int i = 0;
        while (it.hasNext()) {
            objArr[i] = it.next();
            i++;
        }
        return objArr;
    }

    public <T> T[] toArray(T[] tArr) {
        int size = size();
        if (tArr.length < size) {
            tArr = (Object[]) Array.newInstance(tArr.getClass().getComponentType(), size);
        }
        int i = 0;
        for (Collection<E> it : this.all) {
            for (E e : it) {
                tArr[i] = e;
                i++;
            }
        }
        if (tArr.length > size) {
            tArr[size] = null;
        }
        return (Object[]) tArr;
    }

    public boolean add(E e) {
        CollectionMutator<E> collectionMutator = this.mutator;
        if (collectionMutator != null) {
            return collectionMutator.add(this, this.all, e);
        }
        throw new UnsupportedOperationException("add() is not supported on CompositeCollection without a CollectionMutator strategy");
    }

    public boolean remove(Object obj) {
        CollectionMutator<E> collectionMutator = this.mutator;
        if (collectionMutator != null) {
            return collectionMutator.remove(this, this.all, obj);
        }
        throw new UnsupportedOperationException("remove() is not supported on CompositeCollection without a CollectionMutator strategy");
    }

    public boolean containsAll(Collection<?> collection) {
        if (collection == null) {
            return false;
        }
        for (Object contains : collection) {
            if (!contains(contains)) {
                return false;
            }
        }
        return true;
    }

    public boolean addAll(Collection<? extends E> collection) {
        CollectionMutator<E> collectionMutator = this.mutator;
        if (collectionMutator != null) {
            return collectionMutator.addAll(this, this.all, collection);
        }
        throw new UnsupportedOperationException("addAll() is not supported on CompositeCollection without a CollectionMutator strategy");
    }

    public boolean removeAll(Collection<?> collection) {
        boolean z = false;
        if (CollectionUtils.isEmpty(collection)) {
            return false;
        }
        for (Collection<E> removeAll : this.all) {
            z |= removeAll.removeAll(collection);
        }
        return z;
    }

    public boolean removeIf(Predicate<? super E> predicate) {
        boolean z = false;
        if (Objects.isNull(predicate)) {
            return false;
        }
        for (Collection<E> removeIf : this.all) {
            z |= removeIf.removeIf(predicate);
        }
        return z;
    }

    public boolean retainAll(Collection<?> collection) {
        boolean z = false;
        if (collection != null) {
            for (Collection<E> retainAll : this.all) {
                z |= retainAll.retainAll(collection);
            }
        }
        return z;
    }

    public void clear() {
        for (Collection<E> clear : this.all) {
            clear.clear();
        }
    }

    public void setMutator(CollectionMutator<E> collectionMutator) {
        this.mutator = collectionMutator;
    }

    public void addComposited(Collection<E> collection) {
        if (collection != null) {
            this.all.add(collection);
        }
    }

    public void addComposited(Collection<E> collection, Collection<E> collection2) {
        if (collection != null) {
            this.all.add(collection);
        }
        if (collection2 != null) {
            this.all.add(collection2);
        }
    }

    public void addComposited(Collection<E>... collectionArr) {
        for (Collection<E> collection : collectionArr) {
            if (collection != null) {
                this.all.add(collection);
            }
        }
    }

    public void removeComposited(Collection<E> collection) {
        this.all.remove(collection);
    }

    public Collection<E> toCollection() {
        return new ArrayList(this);
    }

    public List<Collection<E>> getCollections() {
        return UnmodifiableList.unmodifiableList(this.all);
    }

    /* access modifiers changed from: protected */
    public CollectionMutator<E> getMutator() {
        return this.mutator;
    }
}
