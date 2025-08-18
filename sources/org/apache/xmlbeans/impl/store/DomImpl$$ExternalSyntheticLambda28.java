package org.apache.xmlbeans.impl.store;

import java.util.function.Function;
import org.apache.xmlbeans.impl.store.DomImpl;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class DomImpl$$ExternalSyntheticLambda28 implements Function {
    public final /* synthetic */ boolean f$0;

    public /* synthetic */ DomImpl$$ExternalSyntheticLambda28(boolean z) {
        this.f$0 = z;
    }

    public final Object apply(Object obj) {
        return DomImpl.node_cloneNode((DomImpl.Dom) obj, this.f$0);
    }
}
