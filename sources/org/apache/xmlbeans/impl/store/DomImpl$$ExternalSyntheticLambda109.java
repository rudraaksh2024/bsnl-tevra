package org.apache.xmlbeans.impl.store;

import java.util.function.Consumer;
import org.apache.xmlbeans.impl.soap.SOAPPart;
import org.apache.xmlbeans.impl.store.DomImpl;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class DomImpl$$ExternalSyntheticLambda109 implements Consumer {
    public final /* synthetic */ SOAPPart f$0;
    public final /* synthetic */ String f$1;
    public final /* synthetic */ String f$2;

    public /* synthetic */ DomImpl$$ExternalSyntheticLambda109(SOAPPart sOAPPart, String str, String str2) {
        this.f$0 = sOAPPart;
        this.f$1 = str;
        this.f$2 = str2;
    }

    public final void accept(Object obj) {
        ((DomImpl.Dom) obj).locale()._saaj.soapPart_setMimeHeader(this.f$0, this.f$1, this.f$2);
    }
}
