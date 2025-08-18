package org.apache.xmlbeans.impl.store;

import java.util.function.Consumer;
import org.apache.xmlbeans.impl.soap.Node;
import org.apache.xmlbeans.impl.store.DomImpl;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class DomImpl$$ExternalSyntheticLambda80 implements Consumer {
    public final /* synthetic */ Node f$0;

    public /* synthetic */ DomImpl$$ExternalSyntheticLambda80(Node node) {
        this.f$0 = node;
    }

    public final void accept(Object obj) {
        ((DomImpl.Dom) obj).locale()._saaj.soapNode_detachNode(this.f$0);
    }
}
