package org.apache.xmlbeans.impl.store;

import java.util.function.Function;
import org.apache.xmlbeans.impl.store.DomImpl;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class DomImpl$$ExternalSyntheticLambda29 implements Function {
    public final /* synthetic */ String f$0;

    public /* synthetic */ DomImpl$$ExternalSyntheticLambda29(String str) {
        this.f$0 = str;
    }

    public final Object apply(Object obj) {
        return DomImpl.attributes_removeNamedItem((DomImpl.Dom) obj, this.f$0);
    }
}
