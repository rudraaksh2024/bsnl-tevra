package org.etsi.uri.x01903.v13.impl;

import java.util.function.Function;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class CertificateValuesTypeImpl$$ExternalSyntheticLambda0 implements Function {
    public final /* synthetic */ CertificateValuesTypeImpl f$0;

    public /* synthetic */ CertificateValuesTypeImpl$$ExternalSyntheticLambda0(CertificateValuesTypeImpl certificateValuesTypeImpl) {
        this.f$0 = certificateValuesTypeImpl;
    }

    public final Object apply(Object obj) {
        return this.f$0.getEncapsulatedX509CertificateArray(((Integer) obj).intValue());
    }
}
