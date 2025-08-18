package org.apache.xmlbeans.impl.xb.xsdschema.impl;

import java.util.function.Consumer;
import org.apache.xmlbeans.impl.xb.xsdschema.impl.RestrictionDocumentImpl;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class RestrictionDocumentImpl$RestrictionImpl$$ExternalSyntheticLambda42 implements Consumer {
    public final /* synthetic */ RestrictionDocumentImpl.RestrictionImpl f$0;

    public /* synthetic */ RestrictionDocumentImpl$RestrictionImpl$$ExternalSyntheticLambda42(RestrictionDocumentImpl.RestrictionImpl restrictionImpl) {
        this.f$0 = restrictionImpl;
    }

    public final void accept(Object obj) {
        this.f$0.removeMaxInclusive(((Integer) obj).intValue());
    }
}
