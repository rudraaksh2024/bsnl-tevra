package com.microsoft.schemas.vml.impl;

import com.microsoft.schemas.office.office.CTLock;
import java.util.function.BiConsumer;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class CTRectImpl$$ExternalSyntheticLambda77 implements BiConsumer {
    public final /* synthetic */ CTRectImpl f$0;

    public /* synthetic */ CTRectImpl$$ExternalSyntheticLambda77(CTRectImpl cTRectImpl) {
        this.f$0 = cTRectImpl;
    }

    public final void accept(Object obj, Object obj2) {
        this.f$0.setLockArray(((Integer) obj).intValue(), (CTLock) obj2);
    }
}
