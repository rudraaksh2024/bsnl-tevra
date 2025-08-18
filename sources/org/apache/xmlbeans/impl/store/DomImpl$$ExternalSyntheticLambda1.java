package org.apache.xmlbeans.impl.store;

import org.apache.xmlbeans.impl.soap.Name;
import org.apache.xmlbeans.impl.soap.SOAPFault;
import org.apache.xmlbeans.impl.store.DomImpl;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class DomImpl$$ExternalSyntheticLambda1 implements DomImpl.WrapSoapEx {
    public final /* synthetic */ DomImpl.Dom f$0;
    public final /* synthetic */ SOAPFault f$1;
    public final /* synthetic */ Name f$2;

    public /* synthetic */ DomImpl$$ExternalSyntheticLambda1(DomImpl.Dom dom, SOAPFault sOAPFault, Name name) {
        this.f$0 = dom;
        this.f$1 = sOAPFault;
        this.f$2 = name;
    }

    public final Object get() {
        return this.f$0.locale()._saaj.soapFault_setFaultCode(this.f$1, this.f$2);
    }
}
