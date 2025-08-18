package org.apache.xmlbeans.impl.store;

import java.util.function.Function;
import org.apache.xmlbeans.impl.store.DomImpl;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class DomImpl$$ExternalSyntheticLambda58 implements Function {
    public final /* synthetic */ DomImpl.Dom f$0;
    public final /* synthetic */ String f$1;

    public /* synthetic */ DomImpl$$ExternalSyntheticLambda58(DomImpl.Dom dom, String str) {
        this.f$0 = dom;
        this.f$1 = str;
    }

    public final Object apply(Object obj) {
        return DomImpl.attributes_getNamedItem(this.f$0, this.f$1);
    }
}
