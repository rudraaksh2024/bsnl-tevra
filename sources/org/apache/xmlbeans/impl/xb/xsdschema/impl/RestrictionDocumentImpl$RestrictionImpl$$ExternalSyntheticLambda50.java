package org.apache.xmlbeans.impl.xb.xsdschema.impl;

import java.util.function.Function;
import org.apache.xmlbeans.impl.xb.xsdschema.impl.RestrictionDocumentImpl;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class RestrictionDocumentImpl$RestrictionImpl$$ExternalSyntheticLambda50 implements Function {
    public final /* synthetic */ RestrictionDocumentImpl.RestrictionImpl f$0;

    public /* synthetic */ RestrictionDocumentImpl$RestrictionImpl$$ExternalSyntheticLambda50(RestrictionDocumentImpl.RestrictionImpl restrictionImpl) {
        this.f$0 = restrictionImpl;
    }

    public final Object apply(Object obj) {
        return this.f$0.getMinExclusiveArray(((Integer) obj).intValue());
    }
}
