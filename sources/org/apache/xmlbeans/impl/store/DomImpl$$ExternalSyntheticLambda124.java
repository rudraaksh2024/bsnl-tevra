package org.apache.xmlbeans.impl.store;

import java.util.Locale;
import org.apache.xmlbeans.impl.soap.Name;
import org.apache.xmlbeans.impl.soap.SOAPBody;
import org.apache.xmlbeans.impl.store.DomImpl;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class DomImpl$$ExternalSyntheticLambda124 implements DomImpl.WrapSoapEx {
    public final /* synthetic */ DomImpl.Dom f$0;
    public final /* synthetic */ SOAPBody f$1;
    public final /* synthetic */ Name f$2;
    public final /* synthetic */ String f$3;
    public final /* synthetic */ Locale f$4;

    public /* synthetic */ DomImpl$$ExternalSyntheticLambda124(DomImpl.Dom dom, SOAPBody sOAPBody, Name name, String str, Locale locale) {
        this.f$0 = dom;
        this.f$1 = sOAPBody;
        this.f$2 = name;
        this.f$3 = str;
        this.f$4 = locale;
    }

    public final Object get() {
        return this.f$0.locale()._saaj.soapBody_addFault(this.f$1, this.f$2, this.f$3, this.f$4);
    }
}
