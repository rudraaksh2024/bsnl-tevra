package org.apache.xmlbeans.impl.store;

import java.util.function.Function;
import org.apache.xmlbeans.impl.soap.SOAPEnvelope;
import org.apache.xmlbeans.impl.store.DomImpl;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class DomImpl$$ExternalSyntheticLambda85 implements Function {
    public final /* synthetic */ SOAPEnvelope f$0;
    public final /* synthetic */ String f$1;
    public final /* synthetic */ String f$2;
    public final /* synthetic */ String f$3;

    public /* synthetic */ DomImpl$$ExternalSyntheticLambda85(SOAPEnvelope sOAPEnvelope, String str, String str2, String str3) {
        this.f$0 = sOAPEnvelope;
        this.f$1 = str;
        this.f$2 = str2;
        this.f$3 = str3;
    }

    public final Object apply(Object obj) {
        return ((DomImpl.Dom) obj).locale()._saaj.soapEnvelope_createName(this.f$0, this.f$1, this.f$2, this.f$3);
    }
}
