package org.etsi.uri.x01903.v13.impl;

import java.util.function.BiConsumer;
import org.etsi.uri.x01903.v13.AnyType;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class GenericTimeStampTypeImpl$$ExternalSyntheticLambda2 implements BiConsumer {
    public final /* synthetic */ GenericTimeStampTypeImpl f$0;

    public /* synthetic */ GenericTimeStampTypeImpl$$ExternalSyntheticLambda2(GenericTimeStampTypeImpl genericTimeStampTypeImpl) {
        this.f$0 = genericTimeStampTypeImpl;
    }

    public final void accept(Object obj, Object obj2) {
        this.f$0.setXMLTimeStampArray(((Integer) obj).intValue(), (AnyType) obj2);
    }
}
