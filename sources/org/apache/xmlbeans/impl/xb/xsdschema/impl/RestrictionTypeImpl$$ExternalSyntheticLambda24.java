package org.apache.xmlbeans.impl.xb.xsdschema.impl;

import java.util.function.BiConsumer;
import org.apache.xmlbeans.impl.xb.xsdschema.NoFixedFacet;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class RestrictionTypeImpl$$ExternalSyntheticLambda24 implements BiConsumer {
    public final /* synthetic */ RestrictionTypeImpl f$0;

    public /* synthetic */ RestrictionTypeImpl$$ExternalSyntheticLambda24(RestrictionTypeImpl restrictionTypeImpl) {
        this.f$0 = restrictionTypeImpl;
    }

    public final void accept(Object obj, Object obj2) {
        this.f$0.setEnumerationArray(((Integer) obj).intValue(), (NoFixedFacet) obj2);
    }
}
