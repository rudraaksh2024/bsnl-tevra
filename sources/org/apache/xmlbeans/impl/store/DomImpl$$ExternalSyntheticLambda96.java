package org.apache.xmlbeans.impl.store;

import java.util.function.Function;
import org.apache.xmlbeans.impl.soap.Name;
import org.apache.xmlbeans.impl.soap.SOAPElement;
import org.apache.xmlbeans.impl.store.DomImpl;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class DomImpl$$ExternalSyntheticLambda96 implements Function {
    public final /* synthetic */ SOAPElement f$0;
    public final /* synthetic */ Name f$1;

    public /* synthetic */ DomImpl$$ExternalSyntheticLambda96(SOAPElement sOAPElement, Name name) {
        this.f$0 = sOAPElement;
        this.f$1 = name;
    }

    public final Object apply(Object obj) {
        return ((DomImpl.Dom) obj).locale()._saaj.soapElement_getAttributeValue(this.f$0, this.f$1);
    }
}
