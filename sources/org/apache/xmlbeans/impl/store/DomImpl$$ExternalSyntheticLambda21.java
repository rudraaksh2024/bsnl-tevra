package org.apache.xmlbeans.impl.store;

import java.util.function.Consumer;
import org.apache.xmlbeans.impl.store.DomImpl;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class DomImpl$$ExternalSyntheticLambda21 implements Consumer {
    public final /* synthetic */ String f$0;

    public /* synthetic */ DomImpl$$ExternalSyntheticLambda21(String str) {
        this.f$0 = str;
    }

    public final void accept(Object obj) {
        DomImpl.node_setNodeValue((DomImpl.Dom) obj, this.f$0);
    }
}
