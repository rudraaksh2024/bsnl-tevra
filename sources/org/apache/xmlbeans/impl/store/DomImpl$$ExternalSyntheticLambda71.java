package org.apache.xmlbeans.impl.store;

import java.util.function.Function;
import org.apache.xmlbeans.impl.soap.SOAPHeader;
import org.apache.xmlbeans.impl.store.DomImpl;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class DomImpl$$ExternalSyntheticLambda71 implements Function {
    public final /* synthetic */ SOAPHeader f$0;

    public /* synthetic */ DomImpl$$ExternalSyntheticLambda71(SOAPHeader sOAPHeader) {
        this.f$0 = sOAPHeader;
    }

    public final Object apply(Object obj) {
        return ((DomImpl.Dom) obj).locale()._saaj.soapHeader_extractAllHeaderElements(this.f$0);
    }
}
