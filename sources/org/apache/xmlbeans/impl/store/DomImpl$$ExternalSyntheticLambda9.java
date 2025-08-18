package org.apache.xmlbeans.impl.store;

import java.util.function.Function;
import org.apache.xmlbeans.impl.soap.SOAPElement;
import org.apache.xmlbeans.impl.store.DomImpl;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class DomImpl$$ExternalSyntheticLambda9 implements Function {
    public final /* synthetic */ SOAPElement f$0;
    public final /* synthetic */ String f$1;
    public final /* synthetic */ String f$2;

    public /* synthetic */ DomImpl$$ExternalSyntheticLambda9(SOAPElement sOAPElement, String str, String str2) {
        this.f$0 = sOAPElement;
        this.f$1 = str;
        this.f$2 = str2;
    }

    public final Object apply(Object obj) {
        return ((DomImpl.Dom) obj).locale()._saaj.soapElement_addNamespaceDeclaration(this.f$0, this.f$1, this.f$2);
    }
}
