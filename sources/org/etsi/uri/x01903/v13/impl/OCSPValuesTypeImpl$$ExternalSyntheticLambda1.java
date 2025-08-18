package org.etsi.uri.x01903.v13.impl;

import java.util.function.BiConsumer;
import org.etsi.uri.x01903.v13.EncapsulatedPKIDataType;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class OCSPValuesTypeImpl$$ExternalSyntheticLambda1 implements BiConsumer {
    public final /* synthetic */ OCSPValuesTypeImpl f$0;

    public /* synthetic */ OCSPValuesTypeImpl$$ExternalSyntheticLambda1(OCSPValuesTypeImpl oCSPValuesTypeImpl) {
        this.f$0 = oCSPValuesTypeImpl;
    }

    public final void accept(Object obj, Object obj2) {
        this.f$0.setEncapsulatedOCSPValueArray(((Integer) obj).intValue(), (EncapsulatedPKIDataType) obj2);
    }
}
