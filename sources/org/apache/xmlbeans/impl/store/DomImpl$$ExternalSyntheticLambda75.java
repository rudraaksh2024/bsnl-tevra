package org.apache.xmlbeans.impl.store;

import java.util.function.Function;
import org.apache.xmlbeans.impl.soap.SOAPElement;
import org.apache.xmlbeans.impl.store.DomImpl;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class DomImpl$$ExternalSyntheticLambda75 implements Function {
    public final /* synthetic */ SOAPElement f$0;
    public final /* synthetic */ String f$1;

    public /* synthetic */ DomImpl$$ExternalSyntheticLambda75(SOAPElement sOAPElement, String str) {
        this.f$0 = sOAPElement;
        this.f$1 = str;
    }

    public final Object apply(Object obj) {
        return Boolean.valueOf(((DomImpl.Dom) obj).locale()._saaj.soapElement_removeNamespaceDeclaration(this.f$0, this.f$1));
    }
}
