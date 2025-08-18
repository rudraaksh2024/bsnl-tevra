package org.apache.xmlbeans.impl.store;

import org.apache.xmlbeans.impl.soap.SOAPEnvelope;
import org.apache.xmlbeans.impl.store.DomImpl;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class DomImpl$$ExternalSyntheticLambda41 implements DomImpl.WrapSoapEx {
    public final /* synthetic */ DomImpl.Dom f$0;
    public final /* synthetic */ SOAPEnvelope f$1;

    public /* synthetic */ DomImpl$$ExternalSyntheticLambda41(DomImpl.Dom dom, SOAPEnvelope sOAPEnvelope) {
        this.f$0 = dom;
        this.f$1 = sOAPEnvelope;
    }

    public final Object get() {
        return this.f$0.locale()._saaj.soapEnvelope_addHeader(this.f$1);
    }
}
