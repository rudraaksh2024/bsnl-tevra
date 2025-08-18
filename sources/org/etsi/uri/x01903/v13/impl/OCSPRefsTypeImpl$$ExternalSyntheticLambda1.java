package org.etsi.uri.x01903.v13.impl;

import java.util.function.BiConsumer;
import org.etsi.uri.x01903.v13.OCSPRefType;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class OCSPRefsTypeImpl$$ExternalSyntheticLambda1 implements BiConsumer {
    public final /* synthetic */ OCSPRefsTypeImpl f$0;

    public /* synthetic */ OCSPRefsTypeImpl$$ExternalSyntheticLambda1(OCSPRefsTypeImpl oCSPRefsTypeImpl) {
        this.f$0 = oCSPRefsTypeImpl;
    }

    public final void accept(Object obj, Object obj2) {
        this.f$0.setOCSPRefArray(((Integer) obj).intValue(), (OCSPRefType) obj2);
    }
}
