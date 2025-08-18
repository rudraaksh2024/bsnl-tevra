package org.apache.xmlbeans.impl.store;

import java.util.function.Consumer;
import java.util.function.Supplier;
import org.apache.xmlbeans.impl.store.DomImpl;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class DomImpl$$ExternalSyntheticLambda94 implements Supplier {
    public final /* synthetic */ Consumer f$0;
    public final /* synthetic */ DomImpl.Dom f$1;

    public /* synthetic */ DomImpl$$ExternalSyntheticLambda94(Consumer consumer, DomImpl.Dom dom) {
        this.f$0 = consumer;
        this.f$1 = dom;
    }

    public final Object get() {
        return this.f$0.accept(this.f$1);
    }
}
