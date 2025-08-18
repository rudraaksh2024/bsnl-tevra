package org.apache.xmlbeans.impl.store;

import java.util.function.Function;
import org.apache.xmlbeans.impl.soap.SOAPElement;
import org.apache.xmlbeans.impl.store.DomImpl;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class DomImpl$$ExternalSyntheticLambda36 implements Function {
    public final /* synthetic */ SOAPElement f$0;
    public final /* synthetic */ String f$1;

    public /* synthetic */ DomImpl$$ExternalSyntheticLambda36(SOAPElement sOAPElement, String str) {
        this.f$0 = sOAPElement;
        this.f$1 = str;
    }

    public final Object apply(Object obj) {
        return ((DomImpl.Dom) obj).locale()._saaj.soapElement_addTextNode(this.f$0, this.f$1);
    }
}
