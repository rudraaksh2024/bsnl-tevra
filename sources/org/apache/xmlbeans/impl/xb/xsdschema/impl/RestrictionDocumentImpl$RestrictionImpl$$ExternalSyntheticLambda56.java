package org.apache.xmlbeans.impl.xb.xsdschema.impl;

import java.util.function.BiConsumer;
import org.apache.xmlbeans.impl.xb.xsdschema.Facet;
import org.apache.xmlbeans.impl.xb.xsdschema.impl.RestrictionDocumentImpl;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class RestrictionDocumentImpl$RestrictionImpl$$ExternalSyntheticLambda56 implements BiConsumer {
    public final /* synthetic */ RestrictionDocumentImpl.RestrictionImpl f$0;

    public /* synthetic */ RestrictionDocumentImpl$RestrictionImpl$$ExternalSyntheticLambda56(RestrictionDocumentImpl.RestrictionImpl restrictionImpl) {
        this.f$0 = restrictionImpl;
    }

    public final void accept(Object obj, Object obj2) {
        this.f$0.setMinInclusiveArray(((Integer) obj).intValue(), (Facet) obj2);
    }
}
