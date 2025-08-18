package org.apache.xmlbeans.impl.store;

import java.util.function.Function;
import org.apache.xmlbeans.impl.store.DomImpl;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class DomImpl$$ExternalSyntheticLambda97 implements Function {
    public final /* synthetic */ String f$0;
    public final /* synthetic */ String f$1;

    public /* synthetic */ DomImpl$$ExternalSyntheticLambda97(String str, String str2) {
        this.f$0 = str;
        this.f$1 = str2;
    }

    public final Object apply(Object obj) {
        return DomImpl.document_getElementsByTagNameNS((DomImpl.Dom) obj, this.f$0, this.f$1);
    }
}
