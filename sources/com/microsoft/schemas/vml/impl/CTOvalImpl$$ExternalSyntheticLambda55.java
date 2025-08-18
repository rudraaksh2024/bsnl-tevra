package com.microsoft.schemas.vml.impl;

import com.microsoft.schemas.office.office.CTLock;
import java.util.function.BiConsumer;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class CTOvalImpl$$ExternalSyntheticLambda55 implements BiConsumer {
    public final /* synthetic */ CTOvalImpl f$0;

    public /* synthetic */ CTOvalImpl$$ExternalSyntheticLambda55(CTOvalImpl cTOvalImpl) {
        this.f$0 = cTOvalImpl;
    }

    public final void accept(Object obj, Object obj2) {
        this.f$0.setLockArray(((Integer) obj).intValue(), (CTLock) obj2);
    }
}
