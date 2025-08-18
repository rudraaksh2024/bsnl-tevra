package org.apache.xmlbeans.impl.store;

import java.util.function.Function;
import org.apache.xmlbeans.impl.soap.Name;
import org.apache.xmlbeans.impl.soap.SOAPHeader;
import org.apache.xmlbeans.impl.store.DomImpl;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class DomImpl$$ExternalSyntheticLambda74 implements Function {
    public final /* synthetic */ SOAPHeader f$0;
    public final /* synthetic */ Name f$1;

    public /* synthetic */ DomImpl$$ExternalSyntheticLambda74(SOAPHeader sOAPHeader, Name name) {
        this.f$0 = sOAPHeader;
        this.f$1 = name;
    }

    public final Object apply(Object obj) {
        return ((DomImpl.Dom) obj).locale()._saaj.soapHeader_addHeaderElement(this.f$0, this.f$1);
    }
}
