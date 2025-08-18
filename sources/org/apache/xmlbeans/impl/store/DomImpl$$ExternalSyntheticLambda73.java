package org.apache.xmlbeans.impl.store;

import java.util.function.Function;
import org.apache.xmlbeans.impl.soap.Text;
import org.apache.xmlbeans.impl.store.DomImpl;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class DomImpl$$ExternalSyntheticLambda73 implements Function {
    public final /* synthetic */ Text f$0;

    public /* synthetic */ DomImpl$$ExternalSyntheticLambda73(Text text) {
        this.f$0 = text;
    }

    public final Object apply(Object obj) {
        return Boolean.valueOf(((DomImpl.Dom) obj).locale()._saaj.soapText_isComment(this.f$0));
    }
}
