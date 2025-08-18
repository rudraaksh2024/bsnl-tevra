package org.apache.xmlbeans.impl.xb.xsdschema.impl;

import java.util.function.BiConsumer;
import org.apache.xmlbeans.impl.xb.xsdschema.Facet;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class RestrictionTypeImpl$$ExternalSyntheticLambda62 implements BiConsumer {
    public final /* synthetic */ RestrictionTypeImpl f$0;

    public /* synthetic */ RestrictionTypeImpl$$ExternalSyntheticLambda62(RestrictionTypeImpl restrictionTypeImpl) {
        this.f$0 = restrictionTypeImpl;
    }

    public final void accept(Object obj, Object obj2) {
        this.f$0.setMinInclusiveArray(((Integer) obj).intValue(), (Facet) obj2);
    }
}
