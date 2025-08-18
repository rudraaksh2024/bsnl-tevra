package org.apache.xmlbeans.impl.store;

import java.util.function.Consumer;
import org.apache.xmlbeans.impl.soap.Node;
import org.apache.xmlbeans.impl.soap.SOAPElement;
import org.apache.xmlbeans.impl.store.DomImpl;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class DomImpl$$ExternalSyntheticLambda32 implements Consumer {
    public final /* synthetic */ Node f$0;
    public final /* synthetic */ SOAPElement f$1;

    public /* synthetic */ DomImpl$$ExternalSyntheticLambda32(Node node, SOAPElement sOAPElement) {
        this.f$0 = node;
        this.f$1 = sOAPElement;
    }

    public final void accept(Object obj) {
        ((DomImpl.Dom) obj).locale()._saaj.soapNode_setParentElement(this.f$0, this.f$1);
    }
}
