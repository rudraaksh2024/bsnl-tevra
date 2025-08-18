package org.etsi.uri.x01903.v13.impl;

import java.util.function.Consumer;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class SignedDataObjectPropertiesTypeImpl$$ExternalSyntheticLambda13 implements Consumer {
    public final /* synthetic */ SignedDataObjectPropertiesTypeImpl f$0;

    public /* synthetic */ SignedDataObjectPropertiesTypeImpl$$ExternalSyntheticLambda13(SignedDataObjectPropertiesTypeImpl signedDataObjectPropertiesTypeImpl) {
        this.f$0 = signedDataObjectPropertiesTypeImpl;
    }

    public final void accept(Object obj) {
        this.f$0.removeIndividualDataObjectsTimeStamp(((Integer) obj).intValue());
    }
}
