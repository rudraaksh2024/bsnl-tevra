package org.apache.commons.collections4.functors;

import java.io.Serializable;
import org.apache.commons.collections4.Predicate;

public final class OrPredicate<T> implements PredicateDecorator<T>, Serializable {
    private static final long serialVersionUID = -8791518325735182855L;
    private final Predicate<? super T> iPredicate1;
    private final Predicate<? super T> iPredicate2;

    public static <T> Predicate<T> orPredicate(Predicate<? super T> predicate, Predicate<? super T> predicate2) {
        if (predicate != null && predicate2 != null) {
            return new OrPredicate(predicate, predicate2);
        }
        throw new NullPointerException("Predicate must not be null");
    }

    public OrPredicate(Predicate<? super T> predicate, Predicate<? super T> predicate2) {
        this.iPredicate1 = predicate;
        this.iPredicate2 = predicate2;
    }

    public boolean evaluate(T t) {
        return this.iPredicate1.evaluate(t) || this.iPredicate2.evaluate(t);
    }

    public Predicate<? super T>[] getPredicates() {
        return new Predicate[]{this.iPredicate1, this.iPredicate2};
    }
}
