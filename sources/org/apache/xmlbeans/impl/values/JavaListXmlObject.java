package org.apache.xmlbeans.impl.values;

import java.util.AbstractList;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import org.apache.xmlbeans.XmlObject;

public class JavaListXmlObject<T extends XmlObject> extends AbstractList<T> {
    private final Function<Integer, T> adder;
    private final Function<Integer, T> getter;
    private final Consumer<Integer> remover;
    private final BiConsumer<Integer, T> setter;
    private final Supplier<Integer> sizer;

    public JavaListXmlObject(Function<Integer, T> function, BiConsumer<Integer, T> biConsumer, Function<Integer, T> function2, Consumer<Integer> consumer, Supplier<Integer> supplier) {
        this.getter = function;
        this.setter = biConsumer;
        this.adder = function2;
        this.remover = consumer;
        this.sizer = supplier;
    }

    public T get(int i) {
        Function<Integer, T> function = this.getter;
        if (function != null) {
            return (XmlObject) function.apply(Integer.valueOf(i));
        }
        throw new IllegalStateException("XmlBean generated using partial methods - no getter available");
    }

    public T set(int i, T t) {
        if (this.setter != null) {
            T t2 = get(i);
            this.setter.accept(Integer.valueOf(i), t);
            return t2;
        }
        throw new IllegalStateException("XmlBean generated using partial methods - no setter available");
    }

    public void add(int i, T t) {
        Function<Integer, T> function = this.adder;
        if (function != null) {
            ((XmlObject) function.apply(Integer.valueOf(i))).set(t);
            return;
        }
        throw new IllegalStateException("XmlBean generated using partial methods - no add method available");
    }

    public T remove(int i) {
        if (this.remover != null) {
            T t = get(i);
            this.remover.accept(Integer.valueOf(i));
            return t;
        }
        throw new IllegalStateException("XmlBean generated using partial methods - no remove method available");
    }

    public int size() {
        Supplier<Integer> supplier = this.sizer;
        if (supplier != null) {
            return supplier.get().intValue();
        }
        throw new IllegalStateException("XmlBean generated using partial methods - no size-of method available");
    }
}
