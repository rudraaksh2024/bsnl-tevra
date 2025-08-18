package org.etsi.uri.x01903.v13.impl;

import java.util.function.BiConsumer;
import org.etsi.uri.x01903.v13.CertIDType;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class CertIDListTypeImpl$$ExternalSyntheticLambda1 implements BiConsumer {
    public final /* synthetic */ CertIDListTypeImpl f$0;

    public /* synthetic */ CertIDListTypeImpl$$ExternalSyntheticLambda1(CertIDListTypeImpl certIDListTypeImpl) {
        this.f$0 = certIDListTypeImpl;
    }

    public final void accept(Object obj, Object obj2) {
        this.f$0.setCertArray(((Integer) obj).intValue(), (CertIDType) obj2);
    }
}
