package org.apache.xmlbeans.impl.store;

import java.util.function.Function;
import org.apache.xmlbeans.impl.soap.SOAPPart;
import org.apache.xmlbeans.impl.store.DomImpl;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class DomImpl$$ExternalSyntheticLambda27 implements Function {
    public final /* synthetic */ SOAPPart f$0;

    public /* synthetic */ DomImpl$$ExternalSyntheticLambda27(SOAPPart sOAPPart) {
        this.f$0 = sOAPPart;
    }

    public final Object apply(Object obj) {
        return ((DomImpl.Dom) obj).locale()._saaj.soapPart_getAllMimeHeaders(this.f$0);
    }
}
