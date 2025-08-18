package org.apache.xmlbeans.impl.store;

import java.util.function.Consumer;
import org.apache.xmlbeans.impl.store.DomImpl;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class DomImpl$$ExternalSyntheticLambda64 implements Consumer {
    public final /* synthetic */ Object f$0;

    public /* synthetic */ DomImpl$$ExternalSyntheticLambda64(Object obj) {
        this.f$0 = obj;
    }

    public final void accept(Object obj) {
        DomImpl.impl_saajCallback_setSaajData((DomImpl.Dom) obj, this.f$0);
    }
}
