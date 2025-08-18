package org.apache.xmlbeans.impl.store;

import java.util.function.Function;
import org.apache.xmlbeans.impl.store.DomImpl;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class DomImpl$$ExternalSyntheticLambda46 implements Function {
    public final /* synthetic */ DomImpl.Dom f$0;
    public final /* synthetic */ DomImpl.Dom f$1;

    public /* synthetic */ DomImpl$$ExternalSyntheticLambda46(DomImpl.Dom dom, DomImpl.Dom dom2) {
        this.f$0 = dom;
        this.f$1 = dom2;
    }

    public final Object apply(Object obj) {
        return DomImpl.node_replaceChild((DomImpl.Dom) obj, this.f$0, this.f$1);
    }
}
