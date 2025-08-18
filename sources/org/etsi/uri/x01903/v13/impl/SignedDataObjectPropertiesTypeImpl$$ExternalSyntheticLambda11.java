package org.etsi.uri.x01903.v13.impl;

import java.util.function.BiConsumer;
import org.etsi.uri.x01903.v13.XAdESTimeStampType;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class SignedDataObjectPropertiesTypeImpl$$ExternalSyntheticLambda11 implements BiConsumer {
    public final /* synthetic */ SignedDataObjectPropertiesTypeImpl f$0;

    public /* synthetic */ SignedDataObjectPropertiesTypeImpl$$ExternalSyntheticLambda11(SignedDataObjectPropertiesTypeImpl signedDataObjectPropertiesTypeImpl) {
        this.f$0 = signedDataObjectPropertiesTypeImpl;
    }

    public final void accept(Object obj, Object obj2) {
        this.f$0.setIndividualDataObjectsTimeStampArray(((Integer) obj).intValue(), (XAdESTimeStampType) obj2);
    }
}
