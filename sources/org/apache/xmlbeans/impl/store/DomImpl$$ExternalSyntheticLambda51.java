package org.apache.xmlbeans.impl.store;

import org.apache.xmlbeans.impl.soap.SOAPFault;
import org.apache.xmlbeans.impl.store.DomImpl;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class DomImpl$$ExternalSyntheticLambda51 implements DomImpl.WrapSoapEx {
    public final /* synthetic */ DomImpl.Dom f$0;
    public final /* synthetic */ SOAPFault f$1;
    public final /* synthetic */ String f$2;

    public /* synthetic */ DomImpl$$ExternalSyntheticLambda51(DomImpl.Dom dom, SOAPFault sOAPFault, String str) {
        this.f$0 = dom;
        this.f$1 = sOAPFault;
        this.f$2 = str;
    }

    public final Object get() {
        return this.f$0.locale()._saaj.soapFault_setFaultCode(this.f$1, this.f$2);
    }
}
