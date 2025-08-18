package org.apache.xmlbeans.impl.store;

import java.util.function.Function;
import org.apache.xmlbeans.impl.soap.SOAPBody;
import org.apache.xmlbeans.impl.store.DomImpl;
import org.w3c.dom.Document;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class DomImpl$$ExternalSyntheticLambda113 implements Function {
    public final /* synthetic */ SOAPBody f$0;
    public final /* synthetic */ Document f$1;

    public /* synthetic */ DomImpl$$ExternalSyntheticLambda113(SOAPBody sOAPBody, Document document) {
        this.f$0 = sOAPBody;
        this.f$1 = document;
    }

    public final Object apply(Object obj) {
        return ((DomImpl.Dom) obj).locale()._saaj.soapBody_addDocument(this.f$0, this.f$1);
    }
}
