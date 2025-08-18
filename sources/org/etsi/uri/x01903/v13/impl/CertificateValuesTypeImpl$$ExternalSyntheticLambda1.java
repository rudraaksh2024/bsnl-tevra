package org.etsi.uri.x01903.v13.impl;

import java.util.function.BiConsumer;
import org.etsi.uri.x01903.v13.EncapsulatedPKIDataType;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class CertificateValuesTypeImpl$$ExternalSyntheticLambda1 implements BiConsumer {
    public final /* synthetic */ CertificateValuesTypeImpl f$0;

    public /* synthetic */ CertificateValuesTypeImpl$$ExternalSyntheticLambda1(CertificateValuesTypeImpl certificateValuesTypeImpl) {
        this.f$0 = certificateValuesTypeImpl;
    }

    public final void accept(Object obj, Object obj2) {
        this.f$0.setEncapsulatedX509CertificateArray(((Integer) obj).intValue(), (EncapsulatedPKIDataType) obj2);
    }
}
