package org.apache.xmlbeans.impl.store;

import java.util.function.Function;
import org.apache.xmlbeans.impl.soap.SOAPElement;
import org.apache.xmlbeans.impl.store.DomImpl;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class DomImpl$$ExternalSyntheticLambda120 implements Function {
    public final /* synthetic */ SOAPElement f$0;

    public /* synthetic */ DomImpl$$ExternalSyntheticLambda120(SOAPElement sOAPElement) {
        this.f$0 = sOAPElement;
    }

    public final Object apply(Object obj) {
        return ((DomImpl.Dom) obj).locale()._saaj.soapElement_getEncodingStyle(this.f$0);
    }
}
