package org.etsi.uri.x01903.v13.impl;

import java.util.function.BiConsumer;
import org.etsi.uri.x01903.v13.CRLRefType;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class CRLRefsTypeImpl$$ExternalSyntheticLambda1 implements BiConsumer {
    public final /* synthetic */ CRLRefsTypeImpl f$0;

    public /* synthetic */ CRLRefsTypeImpl$$ExternalSyntheticLambda1(CRLRefsTypeImpl cRLRefsTypeImpl) {
        this.f$0 = cRLRefsTypeImpl;
    }

    public final void accept(Object obj, Object obj2) {
        this.f$0.setCRLRefArray(((Integer) obj).intValue(), (CRLRefType) obj2);
    }
}
