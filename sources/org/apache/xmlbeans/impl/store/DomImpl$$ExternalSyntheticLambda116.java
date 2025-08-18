package org.apache.xmlbeans.impl.store;

import java.util.function.Function;
import org.apache.xmlbeans.impl.soap.Node;
import org.apache.xmlbeans.impl.store.DomImpl;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class DomImpl$$ExternalSyntheticLambda116 implements Function {
    public final /* synthetic */ Node f$0;

    public /* synthetic */ DomImpl$$ExternalSyntheticLambda116(Node node) {
        this.f$0 = node;
    }

    public final Object apply(Object obj) {
        return ((DomImpl.Dom) obj).locale()._saaj.soapNode_getValue(this.f$0);
    }
}
