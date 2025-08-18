package org.w3.x2000.x09.xmldsig.impl;

import java.util.function.BiConsumer;
import org.w3.x2000.x09.xmldsig.ReferenceType;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class SignedInfoTypeImpl$$ExternalSyntheticLambda1 implements BiConsumer {
    public final /* synthetic */ SignedInfoTypeImpl f$0;

    public /* synthetic */ SignedInfoTypeImpl$$ExternalSyntheticLambda1(SignedInfoTypeImpl signedInfoTypeImpl) {
        this.f$0 = signedInfoTypeImpl;
    }

    public final void accept(Object obj, Object obj2) {
        this.f$0.setReferenceArray(((Integer) obj).intValue(), (ReferenceType) obj2);
    }
}
