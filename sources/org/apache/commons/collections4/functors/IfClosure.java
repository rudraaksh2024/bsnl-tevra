package org.apache.commons.collections4.functors;

import java.io.Serializable;
import org.apache.commons.collections4.Closure;
import org.apache.commons.collections4.Predicate;

public class IfClosure<E> implements Closure<E>, Serializable {
    private static final long serialVersionUID = 3518477308466486130L;
    private final Closure<? super E> iFalseClosure;
    private final Predicate<? super E> iPredicate;
    private final Closure<? super E> iTrueClosure;

    public static <E> Closure<E> ifClosure(Predicate<? super E> predicate, Closure<? super E> closure) {
        return ifClosure(predicate, closure, NOPClosure.nopClosure());
    }

    public static <E> Closure<E> ifClosure(Predicate<? super E> predicate, Closure<? super E> closure, Closure<? super E> closure2) {
        if (predicate == null) {
            throw new NullPointerException("Predicate must not be null");
        } else if (closure != null && closure2 != null) {
            return new IfClosure(predicate, closure, closure2);
        } else {
            throw new NullPointerException("Closures must not be null");
        }
    }

    public IfClosure(Predicate<? super E> predicate, Closure<? super E> closure) {
        this(predicate, closure, NOPClosure.nopClosure());
    }

    public IfClosure(Predicate<? super E> predicate, Closure<? super E> closure, Closure<? super E> closure2) {
        this.iPredicate = predicate;
        this.iTrueClosure = closure;
        this.iFalseClosure = closure2;
    }

    public void execute(E e) {
        if (this.iPredicate.evaluate(e)) {
            this.iTrueClosure.execute(e);
        } else {
            this.iFalseClosure.execute(e);
        }
    }

    public Predicate<? super E> getPredicate() {
        return this.iPredicate;
    }

    public Closure<? super E> getTrueClosure() {
        return this.iTrueClosure;
    }

    public Closure<? super E> getFalseClosure() {
        return this.iFalseClosure;
    }
}
