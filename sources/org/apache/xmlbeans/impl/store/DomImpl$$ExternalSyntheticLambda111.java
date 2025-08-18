package org.apache.xmlbeans.impl.store;

import org.apache.xmlbeans.impl.soap.SOAPElement;
import org.apache.xmlbeans.impl.store.DomImpl;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class DomImpl$$ExternalSyntheticLambda111 implements DomImpl.WrapSoapEx {
    public final /* synthetic */ DomImpl.Dom f$0;
    public final /* synthetic */ SOAPElement f$1;
    public final /* synthetic */ SOAPElement f$2;

    public /* synthetic */ DomImpl$$ExternalSyntheticLambda111(DomImpl.Dom dom, SOAPElement sOAPElement, SOAPElement sOAPElement2) {
        this.f$0 = dom;
        this.f$1 = sOAPElement;
        this.f$2 = sOAPElement2;
    }

    public final Object get() {
        return this.f$0.locale()._saaj.soapElement_addChildElement(this.f$1, this.f$2);
    }
}
