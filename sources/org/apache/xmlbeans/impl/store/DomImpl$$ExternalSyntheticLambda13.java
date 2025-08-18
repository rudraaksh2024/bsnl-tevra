package org.apache.xmlbeans.impl.store;

import java.util.function.Function;
import org.apache.xmlbeans.impl.soap.Detail;
import org.apache.xmlbeans.impl.soap.Name;
import org.apache.xmlbeans.impl.store.DomImpl;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class DomImpl$$ExternalSyntheticLambda13 implements Function {
    public final /* synthetic */ Detail f$0;
    public final /* synthetic */ Name f$1;

    public /* synthetic */ DomImpl$$ExternalSyntheticLambda13(Detail detail, Name name) {
        this.f$0 = detail;
        this.f$1 = name;
    }

    public final Object apply(Object obj) {
        return ((DomImpl.Dom) obj).locale()._saaj.detail_addDetailEntry(this.f$0, this.f$1);
    }
}
