package org.apache.xmlbeans.impl.store;

import java.util.Locale;
import java.util.function.Consumer;
import org.apache.xmlbeans.impl.soap.SOAPFault;
import org.apache.xmlbeans.impl.store.DomImpl;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class DomImpl$$ExternalSyntheticLambda24 implements Consumer {
    public final /* synthetic */ SOAPFault f$0;
    public final /* synthetic */ String f$1;
    public final /* synthetic */ Locale f$2;

    public /* synthetic */ DomImpl$$ExternalSyntheticLambda24(SOAPFault sOAPFault, String str, Locale locale) {
        this.f$0 = sOAPFault;
        this.f$1 = str;
        this.f$2 = locale;
    }

    public final void accept(Object obj) {
        ((DomImpl.Dom) obj).locale()._saaj.soapFault_setFaultString(this.f$0, this.f$1, this.f$2);
    }
}
