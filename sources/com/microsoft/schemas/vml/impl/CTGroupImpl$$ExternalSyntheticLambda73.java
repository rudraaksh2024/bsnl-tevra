package com.microsoft.schemas.vml.impl;

import com.microsoft.schemas.office.office.CTLock;
import java.util.function.BiConsumer;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class CTGroupImpl$$ExternalSyntheticLambda73 implements BiConsumer {
    public final /* synthetic */ CTGroupImpl f$0;

    public /* synthetic */ CTGroupImpl$$ExternalSyntheticLambda73(CTGroupImpl cTGroupImpl) {
        this.f$0 = cTGroupImpl;
    }

    public final void accept(Object obj, Object obj2) {
        this.f$0.setLockArray(((Integer) obj).intValue(), (CTLock) obj2);
    }
}
