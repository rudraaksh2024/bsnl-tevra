package org.apache.xmlbeans.impl.store;

import java.util.function.Consumer;
import javax.xml.transform.Source;
import org.apache.xmlbeans.impl.soap.SOAPPart;
import org.apache.xmlbeans.impl.store.DomImpl;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class DomImpl$$ExternalSyntheticLambda34 implements Consumer {
    public final /* synthetic */ SOAPPart f$0;
    public final /* synthetic */ Source f$1;

    public /* synthetic */ DomImpl$$ExternalSyntheticLambda34(SOAPPart sOAPPart, Source source) {
        this.f$0 = sOAPPart;
        this.f$1 = source;
    }

    public final void accept(Object obj) {
        ((DomImpl.Dom) obj).locale()._saaj.soapPart_setContent(this.f$0, this.f$1);
    }
}
