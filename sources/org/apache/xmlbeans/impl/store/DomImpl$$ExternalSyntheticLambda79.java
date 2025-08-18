package org.apache.xmlbeans.impl.store;

import java.util.function.Function;
import org.apache.xmlbeans.impl.soap.SOAPPart;
import org.apache.xmlbeans.impl.store.DomImpl;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class DomImpl$$ExternalSyntheticLambda79 implements Function {
    public final /* synthetic */ SOAPPart f$0;

    public /* synthetic */ DomImpl$$ExternalSyntheticLambda79(SOAPPart sOAPPart) {
        this.f$0 = sOAPPart;
    }

    public final Object apply(Object obj) {
        return ((DomImpl.Dom) obj).locale()._saaj.soapPart_getContent(this.f$0);
    }
}
