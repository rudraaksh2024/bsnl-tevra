package org.apache.xmlbeans.impl.store;

import java.util.function.Consumer;
import org.apache.xmlbeans.impl.soap.SOAPElement;
import org.apache.xmlbeans.impl.store.DomImpl;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class DomImpl$$ExternalSyntheticLambda98 implements Consumer {
    public final /* synthetic */ SOAPElement f$0;

    public /* synthetic */ DomImpl$$ExternalSyntheticLambda98(SOAPElement sOAPElement) {
        this.f$0 = sOAPElement;
    }

    public final void accept(Object obj) {
        ((DomImpl.Dom) obj).locale()._saaj.soapElement_removeContents(this.f$0);
    }
}
