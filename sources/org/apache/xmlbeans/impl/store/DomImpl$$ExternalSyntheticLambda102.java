package org.apache.xmlbeans.impl.store;

import java.util.function.Consumer;
import org.apache.xmlbeans.impl.store.DomImpl;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class DomImpl$$ExternalSyntheticLambda102 implements Consumer {
    public final /* synthetic */ String f$0;
    public final /* synthetic */ String f$1;
    public final /* synthetic */ String f$2;

    public /* synthetic */ DomImpl$$ExternalSyntheticLambda102(String str, String str2, String str3) {
        this.f$0 = str;
        this.f$1 = str2;
        this.f$2 = str3;
    }

    public final void accept(Object obj) {
        DomImpl.element_setAttributeNS((DomImpl.Dom) obj, this.f$0, this.f$1, this.f$2);
    }
}
