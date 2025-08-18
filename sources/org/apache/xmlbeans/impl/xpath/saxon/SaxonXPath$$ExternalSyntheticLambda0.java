package org.apache.xmlbeans.impl.xpath.saxon;

import java.util.function.BiConsumer;
import net.sf.saxon.sxpath.IndependentContext;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class SaxonXPath$$ExternalSyntheticLambda0 implements BiConsumer {
    public final /* synthetic */ IndependentContext f$0;

    public /* synthetic */ SaxonXPath$$ExternalSyntheticLambda0(IndependentContext independentContext) {
        this.f$0 = independentContext;
    }

    public final void accept(Object obj, Object obj2) {
        this.f$0.declareNamespace((String) obj, (String) obj2);
    }
}
