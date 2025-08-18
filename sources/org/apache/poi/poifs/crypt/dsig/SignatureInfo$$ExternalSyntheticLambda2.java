package org.apache.poi.poifs.crypt.dsig;

import java.util.function.BiConsumer;
import javax.xml.crypto.dsig.dom.DOMSignContext;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class SignatureInfo$$ExternalSyntheticLambda2 implements BiConsumer {
    public final /* synthetic */ DOMSignContext f$0;

    public /* synthetic */ SignatureInfo$$ExternalSyntheticLambda2(DOMSignContext dOMSignContext) {
        this.f$0 = dOMSignContext;
    }

    public final void accept(Object obj, Object obj2) {
        String unused = this.f$0.putNamespacePrefix((String) obj, (String) obj2);
    }
}
