package org.apache.xmlbeans.impl.store;

import org.apache.xmlbeans.impl.soap.SOAPElement;
import org.apache.xmlbeans.impl.store.DomImpl;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class DomImpl$$ExternalSyntheticLambda60 implements DomImpl.WrapSoapEx {
    public final /* synthetic */ DomImpl.Dom f$0;
    public final /* synthetic */ SOAPElement f$1;
    public final /* synthetic */ String f$2;

    public /* synthetic */ DomImpl$$ExternalSyntheticLambda60(DomImpl.Dom dom, SOAPElement sOAPElement, String str) {
        this.f$0 = dom;
        this.f$1 = sOAPElement;
        this.f$2 = str;
    }

    public final Object get() {
        return this.f$0.locale()._saaj.soapElement_addChildElement(this.f$1, this.f$2);
    }
}
