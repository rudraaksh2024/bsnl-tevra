package org.etsi.uri.x01903.v13.impl;

import java.util.function.Consumer;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class SigPolicyQualifiersListTypeImpl$$ExternalSyntheticLambda3 implements Consumer {
    public final /* synthetic */ SigPolicyQualifiersListTypeImpl f$0;

    public /* synthetic */ SigPolicyQualifiersListTypeImpl$$ExternalSyntheticLambda3(SigPolicyQualifiersListTypeImpl sigPolicyQualifiersListTypeImpl) {
        this.f$0 = sigPolicyQualifiersListTypeImpl;
    }

    public final void accept(Object obj) {
        this.f$0.removeSigPolicyQualifier(((Integer) obj).intValue());
    }
}
