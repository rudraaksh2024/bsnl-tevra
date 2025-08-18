package org.apache.xmlbeans.impl.store;

import java.util.function.Function;
import org.apache.xmlbeans.impl.soap.Name;
import org.apache.xmlbeans.impl.soap.SOAPBody;
import org.apache.xmlbeans.impl.store.DomImpl;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class DomImpl$$ExternalSyntheticLambda81 implements Function {
    public final /* synthetic */ SOAPBody f$0;
    public final /* synthetic */ Name f$1;

    public /* synthetic */ DomImpl$$ExternalSyntheticLambda81(SOAPBody sOAPBody, Name name) {
        this.f$0 = sOAPBody;
        this.f$1 = name;
    }

    public final Object apply(Object obj) {
        return ((DomImpl.Dom) obj).locale()._saaj.soapBody_addBodyElement(this.f$0, this.f$1);
    }
}
