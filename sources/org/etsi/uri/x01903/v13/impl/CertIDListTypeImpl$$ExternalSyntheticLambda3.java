package org.etsi.uri.x01903.v13.impl;

import java.util.function.Consumer;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class CertIDListTypeImpl$$ExternalSyntheticLambda3 implements Consumer {
    public final /* synthetic */ CertIDListTypeImpl f$0;

    public /* synthetic */ CertIDListTypeImpl$$ExternalSyntheticLambda3(CertIDListTypeImpl certIDListTypeImpl) {
        this.f$0 = certIDListTypeImpl;
    }

    public final void accept(Object obj) {
        this.f$0.removeCert(((Integer) obj).intValue());
    }
}
