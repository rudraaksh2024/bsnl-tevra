package org.apache.xmlbeans.impl.store;

import java.util.function.Consumer;
import org.apache.xmlbeans.impl.soap.SOAPElement;
import org.apache.xmlbeans.impl.store.DomImpl;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class DomImpl$$ExternalSyntheticLambda84 implements Consumer {
    public final /* synthetic */ SOAPElement f$0;
    public final /* synthetic */ String f$1;

    public /* synthetic */ DomImpl$$ExternalSyntheticLambda84(SOAPElement sOAPElement, String str) {
        this.f$0 = sOAPElement;
        this.f$1 = str;
    }

    public final void accept(Object obj) {
        ((DomImpl.Dom) obj).locale()._saaj.soapElement_setEncodingStyle(this.f$0, this.f$1);
    }
}
