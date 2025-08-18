package org.apache.xmlbeans.impl.store;

import java.util.function.Function;
import org.apache.xmlbeans.impl.soap.SOAPBody;
import org.apache.xmlbeans.impl.store.DomImpl;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class DomImpl$$ExternalSyntheticLambda22 implements Function {
    public final /* synthetic */ SOAPBody f$0;

    public /* synthetic */ DomImpl$$ExternalSyntheticLambda22(SOAPBody sOAPBody) {
        this.f$0 = sOAPBody;
    }

    public final Object apply(Object obj) {
        return ((DomImpl.Dom) obj).locale()._saaj.soapBody_getFault(this.f$0);
    }
}
