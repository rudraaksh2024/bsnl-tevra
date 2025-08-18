package org.apache.xmlbeans.impl.store;

import java.util.function.Function;
import org.apache.xmlbeans.impl.soap.SOAPHeader;
import org.apache.xmlbeans.impl.store.DomImpl;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class DomImpl$$ExternalSyntheticLambda56 implements Function {
    public final /* synthetic */ SOAPHeader f$0;
    public final /* synthetic */ String f$1;

    public /* synthetic */ DomImpl$$ExternalSyntheticLambda56(SOAPHeader sOAPHeader, String str) {
        this.f$0 = sOAPHeader;
        this.f$1 = str;
    }

    public final Object apply(Object obj) {
        return ((DomImpl.Dom) obj).locale()._saaj.soapHeader_examineMustUnderstandHeaderElements(this.f$0, this.f$1);
    }
}
