package org.w3.x2000.x09.xmldsig.impl;

import java.util.function.Consumer;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class SignedInfoTypeImpl$$ExternalSyntheticLambda3 implements Consumer {
    public final /* synthetic */ SignedInfoTypeImpl f$0;

    public /* synthetic */ SignedInfoTypeImpl$$ExternalSyntheticLambda3(SignedInfoTypeImpl signedInfoTypeImpl) {
        this.f$0 = signedInfoTypeImpl;
    }

    public final void accept(Object obj) {
        this.f$0.removeReference(((Integer) obj).intValue());
    }
}
