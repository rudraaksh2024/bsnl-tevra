package com.microsoft.schemas.vml.impl;

import com.microsoft.schemas.office.office.CTSignatureLine;
import java.util.function.BiConsumer;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class CTLineImpl$$ExternalSyntheticLambda99 implements BiConsumer {
    public final /* synthetic */ CTLineImpl f$0;

    public /* synthetic */ CTLineImpl$$ExternalSyntheticLambda99(CTLineImpl cTLineImpl) {
        this.f$0 = cTLineImpl;
    }

    public final void accept(Object obj, Object obj2) {
        this.f$0.setSignaturelineArray(((Integer) obj).intValue(), (CTSignatureLine) obj2);
    }
}
