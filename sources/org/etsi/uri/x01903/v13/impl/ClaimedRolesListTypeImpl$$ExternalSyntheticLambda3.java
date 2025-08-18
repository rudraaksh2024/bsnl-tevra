package org.etsi.uri.x01903.v13.impl;

import java.util.function.Consumer;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class ClaimedRolesListTypeImpl$$ExternalSyntheticLambda3 implements Consumer {
    public final /* synthetic */ ClaimedRolesListTypeImpl f$0;

    public /* synthetic */ ClaimedRolesListTypeImpl$$ExternalSyntheticLambda3(ClaimedRolesListTypeImpl claimedRolesListTypeImpl) {
        this.f$0 = claimedRolesListTypeImpl;
    }

    public final void accept(Object obj) {
        this.f$0.removeClaimedRole(((Integer) obj).intValue());
    }
}
