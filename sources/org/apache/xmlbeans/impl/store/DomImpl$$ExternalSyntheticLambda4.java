package org.apache.xmlbeans.impl.store;

import java.util.function.Function;
import org.apache.xmlbeans.impl.store.DomImpl;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class DomImpl$$ExternalSyntheticLambda4 implements Function {
    public final /* synthetic */ DomImpl.Dom f$0;

    public /* synthetic */ DomImpl$$ExternalSyntheticLambda4(DomImpl.Dom dom) {
        this.f$0 = dom;
    }

    public final Object apply(Object obj) {
        return DomImpl.node_removeChild((DomImpl.Dom) obj, this.f$0);
    }
}
