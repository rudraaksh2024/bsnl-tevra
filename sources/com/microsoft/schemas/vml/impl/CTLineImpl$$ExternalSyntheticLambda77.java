package com.microsoft.schemas.vml.impl;

import com.microsoft.schemas.office.office.CTLock;
import java.util.function.BiConsumer;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class CTLineImpl$$ExternalSyntheticLambda77 implements BiConsumer {
    public final /* synthetic */ CTLineImpl f$0;

    public /* synthetic */ CTLineImpl$$ExternalSyntheticLambda77(CTLineImpl cTLineImpl) {
        this.f$0 = cTLineImpl;
    }

    public final void accept(Object obj, Object obj2) {
        this.f$0.setLockArray(((Integer) obj).intValue(), (CTLock) obj2);
    }
}
