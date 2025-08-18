package org.etsi.uri.x01903.v13.impl;

import java.util.function.BiConsumer;
import org.etsi.uri.x01903.v13.EncapsulatedPKIDataType;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class CRLValuesTypeImpl$$ExternalSyntheticLambda1 implements BiConsumer {
    public final /* synthetic */ CRLValuesTypeImpl f$0;

    public /* synthetic */ CRLValuesTypeImpl$$ExternalSyntheticLambda1(CRLValuesTypeImpl cRLValuesTypeImpl) {
        this.f$0 = cRLValuesTypeImpl;
    }

    public final void accept(Object obj, Object obj2) {
        this.f$0.setEncapsulatedCRLValueArray(((Integer) obj).intValue(), (EncapsulatedPKIDataType) obj2);
    }
}
