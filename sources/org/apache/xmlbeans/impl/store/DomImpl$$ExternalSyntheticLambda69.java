package org.apache.xmlbeans.impl.store;

import org.apache.xmlbeans.impl.soap.SOAPBody;
import org.apache.xmlbeans.impl.store.DomImpl;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class DomImpl$$ExternalSyntheticLambda69 implements DomImpl.WrapSoapEx {
    public final /* synthetic */ DomImpl.Dom f$0;
    public final /* synthetic */ SOAPBody f$1;

    public /* synthetic */ DomImpl$$ExternalSyntheticLambda69(DomImpl.Dom dom, SOAPBody sOAPBody) {
        this.f$0 = dom;
        this.f$1 = sOAPBody;
    }

    public final Object get() {
        return this.f$0.locale()._saaj.soapBody_addFault(this.f$1);
    }
}
