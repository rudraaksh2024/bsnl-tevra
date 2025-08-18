package org.etsi.uri.x01903.v13.impl;

import java.util.function.Consumer;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class CRLRefsTypeImpl$$ExternalSyntheticLambda3 implements Consumer {
    public final /* synthetic */ CRLRefsTypeImpl f$0;

    public /* synthetic */ CRLRefsTypeImpl$$ExternalSyntheticLambda3(CRLRefsTypeImpl cRLRefsTypeImpl) {
        this.f$0 = cRLRefsTypeImpl;
    }

    public final void accept(Object obj) {
        this.f$0.removeCRLRef(((Integer) obj).intValue());
    }
}
