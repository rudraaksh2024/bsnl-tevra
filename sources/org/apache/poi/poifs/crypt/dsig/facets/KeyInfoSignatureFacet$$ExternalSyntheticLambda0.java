package org.apache.poi.poifs.crypt.dsig.facets;

import java.util.function.BiConsumer;
import javax.xml.crypto.dsig.dom.DOMSignContext;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class KeyInfoSignatureFacet$$ExternalSyntheticLambda0 implements BiConsumer {
    public final /* synthetic */ DOMSignContext f$0;

    public /* synthetic */ KeyInfoSignatureFacet$$ExternalSyntheticLambda0(DOMSignContext dOMSignContext) {
        this.f$0 = dOMSignContext;
    }

    public final void accept(Object obj, Object obj2) {
        String unused = this.f$0.putNamespacePrefix((String) obj, (String) obj2);
    }
}
