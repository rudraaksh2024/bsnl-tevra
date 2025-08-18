package org.etsi.uri.x01903.v13.impl;

import java.util.function.BiConsumer;
import org.etsi.uri.x01903.v13.IncludeType;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class GenericTimeStampTypeImpl$$ExternalSyntheticLambda7 implements BiConsumer {
    public final /* synthetic */ GenericTimeStampTypeImpl f$0;

    public /* synthetic */ GenericTimeStampTypeImpl$$ExternalSyntheticLambda7(GenericTimeStampTypeImpl genericTimeStampTypeImpl) {
        this.f$0 = genericTimeStampTypeImpl;
    }

    public final void accept(Object obj, Object obj2) {
        this.f$0.setIncludeArray(((Integer) obj).intValue(), (IncludeType) obj2);
    }
}
