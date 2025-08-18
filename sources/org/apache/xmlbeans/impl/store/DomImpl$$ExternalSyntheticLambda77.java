package org.apache.xmlbeans.impl.store;

import java.util.function.Consumer;
import org.apache.xmlbeans.impl.soap.SOAPPart;
import org.apache.xmlbeans.impl.store.DomImpl;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class DomImpl$$ExternalSyntheticLambda77 implements Consumer {
    public final /* synthetic */ SOAPPart f$0;
    public final /* synthetic */ String f$1;

    public /* synthetic */ DomImpl$$ExternalSyntheticLambda77(SOAPPart sOAPPart, String str) {
        this.f$0 = sOAPPart;
        this.f$1 = str;
    }

    public final void accept(Object obj) {
        ((DomImpl.Dom) obj).locale()._saaj.soapPart_removeMimeHeader(this.f$0, this.f$1);
    }
}
