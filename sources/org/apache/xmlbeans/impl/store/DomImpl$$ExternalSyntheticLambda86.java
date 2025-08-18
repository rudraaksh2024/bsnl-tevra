package org.apache.xmlbeans.impl.store;

import java.util.function.Function;
import org.apache.xmlbeans.impl.soap.Detail;
import org.apache.xmlbeans.impl.store.DomImpl;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class DomImpl$$ExternalSyntheticLambda86 implements Function {
    public final /* synthetic */ Detail f$0;

    public /* synthetic */ DomImpl$$ExternalSyntheticLambda86(Detail detail) {
        this.f$0 = detail;
    }

    public final Object apply(Object obj) {
        return ((DomImpl.Dom) obj).locale()._saaj.detail_getDetailEntries(this.f$0);
    }
}
