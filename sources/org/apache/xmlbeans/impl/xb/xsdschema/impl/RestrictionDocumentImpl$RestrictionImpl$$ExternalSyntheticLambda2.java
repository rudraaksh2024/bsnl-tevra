package org.apache.xmlbeans.impl.xb.xsdschema.impl;

import java.util.function.BiConsumer;
import org.apache.xmlbeans.impl.xb.xsdschema.NoFixedFacet;
import org.apache.xmlbeans.impl.xb.xsdschema.impl.RestrictionDocumentImpl;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class RestrictionDocumentImpl$RestrictionImpl$$ExternalSyntheticLambda2 implements BiConsumer {
    public final /* synthetic */ RestrictionDocumentImpl.RestrictionImpl f$0;

    public /* synthetic */ RestrictionDocumentImpl$RestrictionImpl$$ExternalSyntheticLambda2(RestrictionDocumentImpl.RestrictionImpl restrictionImpl) {
        this.f$0 = restrictionImpl;
    }

    public final void accept(Object obj, Object obj2) {
        this.f$0.setEnumerationArray(((Integer) obj).intValue(), (NoFixedFacet) obj2);
    }
}
