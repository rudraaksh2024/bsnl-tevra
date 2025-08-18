package org.apache.xmlbeans.impl.store;

import java.util.function.Function;
import org.apache.xmlbeans.impl.soap.SOAPHeaderElement;
import org.apache.xmlbeans.impl.store.DomImpl;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class DomImpl$$ExternalSyntheticLambda14 implements Function {
    public final /* synthetic */ SOAPHeaderElement f$0;

    public /* synthetic */ DomImpl$$ExternalSyntheticLambda14(SOAPHeaderElement sOAPHeaderElement) {
        this.f$0 = sOAPHeaderElement;
    }

    public final Object apply(Object obj) {
        return ((DomImpl.Dom) obj).locale()._saaj.soapHeaderElement_getActor(this.f$0);
    }
}
