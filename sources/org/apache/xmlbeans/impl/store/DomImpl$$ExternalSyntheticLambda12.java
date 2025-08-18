package org.apache.xmlbeans.impl.store;

import java.util.function.Function;
import org.apache.xmlbeans.impl.store.DomImpl;
import org.w3c.dom.Node;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class DomImpl$$ExternalSyntheticLambda12 implements Function {
    public final /* synthetic */ Node f$0;
    public final /* synthetic */ boolean f$1;

    public /* synthetic */ DomImpl$$ExternalSyntheticLambda12(Node node, boolean z) {
        this.f$0 = node;
        this.f$1 = z;
    }

    public final Object apply(Object obj) {
        return DomImpl.document_importNode((DomImpl.Dom) obj, this.f$0, this.f$1);
    }
}
