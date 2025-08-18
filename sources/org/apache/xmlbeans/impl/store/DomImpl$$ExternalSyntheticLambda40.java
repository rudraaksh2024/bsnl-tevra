package org.apache.xmlbeans.impl.store;

import java.util.function.Function;
import org.apache.xmlbeans.impl.soap.SOAPPart;
import org.apache.xmlbeans.impl.store.DomImpl;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class DomImpl$$ExternalSyntheticLambda40 implements Function {
    public final /* synthetic */ SOAPPart f$0;
    public final /* synthetic */ String[] f$1;

    public /* synthetic */ DomImpl$$ExternalSyntheticLambda40(SOAPPart sOAPPart, String[] strArr) {
        this.f$0 = sOAPPart;
        this.f$1 = strArr;
    }

    public final Object apply(Object obj) {
        return ((DomImpl.Dom) obj).locale()._saaj.soapPart_getMatchingMimeHeaders(this.f$0, this.f$1);
    }
}
