package org.apache.xmlbeans.impl.xb.xsdschema.impl;

import java.util.function.BiConsumer;
import org.apache.xmlbeans.impl.xb.xsdschema.NumFacet;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class RestrictionTypeImpl$$ExternalSyntheticLambda35 implements BiConsumer {
    public final /* synthetic */ RestrictionTypeImpl f$0;

    public /* synthetic */ RestrictionTypeImpl$$ExternalSyntheticLambda35(RestrictionTypeImpl restrictionTypeImpl) {
        this.f$0 = restrictionTypeImpl;
    }

    public final void accept(Object obj, Object obj2) {
        this.f$0.setMinLengthArray(((Integer) obj).intValue(), (NumFacet) obj2);
    }
}
