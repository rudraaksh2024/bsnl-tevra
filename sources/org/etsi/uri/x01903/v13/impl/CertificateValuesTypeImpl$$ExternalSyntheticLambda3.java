package org.etsi.uri.x01903.v13.impl;

import java.util.function.Consumer;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class CertificateValuesTypeImpl$$ExternalSyntheticLambda3 implements Consumer {
    public final /* synthetic */ CertificateValuesTypeImpl f$0;

    public /* synthetic */ CertificateValuesTypeImpl$$ExternalSyntheticLambda3(CertificateValuesTypeImpl certificateValuesTypeImpl) {
        this.f$0 = certificateValuesTypeImpl;
    }

    public final void accept(Object obj) {
        this.f$0.removeEncapsulatedX509Certificate(((Integer) obj).intValue());
    }
}
