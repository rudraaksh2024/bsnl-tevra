package org.apache.xmlbeans.impl.store;

import java.util.function.Consumer;
import org.apache.xmlbeans.impl.store.DomImpl;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class DomImpl$$ExternalSyntheticLambda99 implements Consumer {
    public final /* synthetic */ String f$0;

    public /* synthetic */ DomImpl$$ExternalSyntheticLambda99(String str) {
        this.f$0 = str;
    }

    public final void accept(Object obj) {
        DomImpl.node_setPrefix((DomImpl.Dom) obj, this.f$0);
    }
}
