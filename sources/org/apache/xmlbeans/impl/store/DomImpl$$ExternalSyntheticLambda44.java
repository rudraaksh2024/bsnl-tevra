package org.apache.xmlbeans.impl.store;

import java.util.function.Function;
import org.apache.xmlbeans.impl.soap.SOAPEnvelope;
import org.apache.xmlbeans.impl.store.DomImpl;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class DomImpl$$ExternalSyntheticLambda44 implements Function {
    public final /* synthetic */ SOAPEnvelope f$0;
    public final /* synthetic */ String f$1;

    public /* synthetic */ DomImpl$$ExternalSyntheticLambda44(SOAPEnvelope sOAPEnvelope, String str) {
        this.f$0 = sOAPEnvelope;
        this.f$1 = str;
    }

    public final Object apply(Object obj) {
        return ((DomImpl.Dom) obj).locale()._saaj.soapEnvelope_createName(this.f$0, this.f$1);
    }
}
