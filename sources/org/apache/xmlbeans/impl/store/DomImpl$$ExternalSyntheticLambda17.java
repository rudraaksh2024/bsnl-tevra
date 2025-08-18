package org.apache.xmlbeans.impl.store;

import java.util.function.Consumer;
import org.apache.xmlbeans.impl.soap.SOAPPart;
import org.apache.xmlbeans.impl.store.DomImpl;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class DomImpl$$ExternalSyntheticLambda17 implements Consumer {
    public final /* synthetic */ SOAPPart f$0;

    public /* synthetic */ DomImpl$$ExternalSyntheticLambda17(SOAPPart sOAPPart) {
        this.f$0 = sOAPPart;
    }

    public final void accept(Object obj) {
        ((DomImpl.Dom) obj).locale()._saaj.soapPart_removeAllMimeHeaders(this.f$0);
    }
}
