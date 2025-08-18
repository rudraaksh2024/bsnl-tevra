package org.apache.xmlbeans.impl.store;

import java.util.function.Function;
import org.apache.xmlbeans.impl.soap.SOAPFault;
import org.apache.xmlbeans.impl.store.DomImpl;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class DomImpl$$ExternalSyntheticLambda49 implements Function {
    public final /* synthetic */ SOAPFault f$0;

    public /* synthetic */ DomImpl$$ExternalSyntheticLambda49(SOAPFault sOAPFault) {
        this.f$0 = sOAPFault;
    }

    public final Object apply(Object obj) {
        return ((DomImpl.Dom) obj).locale()._saaj.soapFault_getDetail(this.f$0);
    }
}
