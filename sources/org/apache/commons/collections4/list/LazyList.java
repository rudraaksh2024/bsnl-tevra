package org.apache.commons.collections4.list;

import java.util.List;
import java.util.Objects;
import org.apache.commons.collections4.Factory;
import org.apache.commons.collections4.Transformer;

public class LazyList<E> extends AbstractSerializableListDecorator<E> {
    private static final long serialVersionUID = -3677737457567429713L;
    private final Factory<? extends E> factory;
    private final Transformer<Integer, ? extends E> transformer;

    public static <E> LazyList<E> lazyList(List<E> list, Factory<? extends E> factory2) {
        return new LazyList<>(list, factory2);
    }

    public static <E> LazyList<E> lazyList(List<E> list, Transformer<Integer, ? extends E> transformer2) {
        return new LazyList<>(list, transformer2);
    }

    protected LazyList(List<E> list, Factory<? extends E> factory2) {
        super(list);
        this.factory = (Factory) Objects.requireNonNull(factory2);
        this.transformer = null;
    }

    protected LazyList(List<E> list, Transformer<Integer, ? extends E> transformer2) {
        super(list);
        this.factory = null;
        this.transformer = (Transformer) Objects.requireNonNull(transformer2);
    }

    public E get(int i) {
        int size = decorated().size();
        if (i < size) {
            E e = decorated().get(i);
            if (e != null) {
                return e;
            }
            E element = element(i);
            decorated().set(i, element);
            return element;
        }
        while (size < i) {
            decorated().add((Object) null);
            size++;
        }
        E element2 = element(i);
        decorated().add(element2);
        return element2;
    }

    public List<E> subList(int i, int i2) {
        List subList = decorated().subList(i, i2);
        Factory<? extends E> factory2 = this.factory;
        if (factory2 != null) {
            return new LazyList(subList, factory2);
        }
        Transformer<Integer, ? extends E> transformer2 = this.transformer;
        if (transformer2 != null) {
            return new LazyList(subList, transformer2);
        }
        throw new IllegalStateException("Factory and Transformer are both null!");
    }

    private E element(int i) {
        Factory<? extends E> factory2 = this.factory;
        if (factory2 != null) {
            return factory2.create();
        }
        Transformer<Integer, ? extends E> transformer2 = this.transformer;
        if (transformer2 != null) {
            return transformer2.transform(Integer.valueOf(i));
        }
        throw new IllegalStateException("Factory and Transformer are both null!");
    }
}
