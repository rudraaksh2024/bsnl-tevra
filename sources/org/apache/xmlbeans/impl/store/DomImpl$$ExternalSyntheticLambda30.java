package org.apache.xmlbeans.impl.store;

import java.util.function.Consumer;
import org.apache.xmlbeans.impl.soap.SOAPHeaderElement;
import org.apache.xmlbeans.impl.store.DomImpl;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class DomImpl$$ExternalSyntheticLambda30 implements Consumer {
    public final /* synthetic */ SOAPHeaderElement f$0;
    public final /* synthetic */ String f$1;

    public /* synthetic */ DomImpl$$ExternalSyntheticLambda30(SOAPHeaderElement sOAPHeaderElement, String str) {
        this.f$0 = sOAPHeaderElement;
        this.f$1 = str;
    }

    public final void accept(Object obj) {
        ((DomImpl.Dom) obj).locale()._saaj.soapHeaderElement_setActor(this.f$0, this.f$1);
    }
}
