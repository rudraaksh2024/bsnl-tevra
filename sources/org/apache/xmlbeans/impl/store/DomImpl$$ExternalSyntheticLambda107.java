package org.apache.xmlbeans.impl.store;

import java.util.function.Function;
import java.util.function.Supplier;
import org.apache.xmlbeans.impl.store.DomImpl;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class DomImpl$$ExternalSyntheticLambda107 implements Supplier {
    public final /* synthetic */ Function f$0;
    public final /* synthetic */ DomImpl.Dom f$1;

    public /* synthetic */ DomImpl$$ExternalSyntheticLambda107(Function function, DomImpl.Dom dom) {
        this.f$0 = function;
        this.f$1 = dom;
    }

    public final Object get() {
        return this.f$0.apply(this.f$1);
    }
}
