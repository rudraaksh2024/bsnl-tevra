package org.apache.xmlbeans.impl.xpath.saxon;

import java.util.function.BiConsumer;
import net.sf.saxon.query.StaticQueryContext;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class SaxonXQuery$$ExternalSyntheticLambda0 implements BiConsumer {
    public final /* synthetic */ StaticQueryContext f$0;

    public /* synthetic */ SaxonXQuery$$ExternalSyntheticLambda0(StaticQueryContext staticQueryContext) {
        this.f$0 = staticQueryContext;
    }

    public final void accept(Object obj, Object obj2) {
        this.f$0.declareNamespace((String) obj, (String) obj2);
    }
}
