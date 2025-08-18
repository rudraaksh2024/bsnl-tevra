package org.apache.xmlbeans.impl.store;

import org.apache.xmlbeans.impl.soap.SOAPFault;
import org.apache.xmlbeans.impl.store.DomImpl;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class DomImpl$$ExternalSyntheticLambda123 implements DomImpl.WrapSoapEx {
    public final /* synthetic */ DomImpl.Dom f$0;
    public final /* synthetic */ SOAPFault f$1;

    public /* synthetic */ DomImpl$$ExternalSyntheticLambda123(DomImpl.Dom dom, SOAPFault sOAPFault) {
        this.f$0 = dom;
        this.f$1 = sOAPFault;
    }

    public final Object get() {
        return this.f$0.locale()._saaj.soapFault_addDetail(this.f$1);
    }
}
