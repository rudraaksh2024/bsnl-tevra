package org.apache.xmlbeans.impl.store;

import java.util.function.Consumer;
import org.apache.xmlbeans.impl.soap.SOAPFault;
import org.apache.xmlbeans.impl.store.DomImpl;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class DomImpl$$ExternalSyntheticLambda65 implements Consumer {
    public final /* synthetic */ SOAPFault f$0;
    public final /* synthetic */ String f$1;

    public /* synthetic */ DomImpl$$ExternalSyntheticLambda65(SOAPFault sOAPFault, String str) {
        this.f$0 = sOAPFault;
        this.f$1 = str;
    }

    public final void accept(Object obj) {
        ((DomImpl.Dom) obj).locale()._saaj.soapFault_setFaultString(this.f$0, this.f$1);
    }
}
