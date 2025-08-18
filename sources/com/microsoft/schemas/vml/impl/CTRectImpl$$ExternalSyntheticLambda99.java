package com.microsoft.schemas.vml.impl;

import com.microsoft.schemas.office.office.CTSignatureLine;
import java.util.function.BiConsumer;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class CTRectImpl$$ExternalSyntheticLambda99 implements BiConsumer {
    public final /* synthetic */ CTRectImpl f$0;

    public /* synthetic */ CTRectImpl$$ExternalSyntheticLambda99(CTRectImpl cTRectImpl) {
        this.f$0 = cTRectImpl;
    }

    public final void accept(Object obj, Object obj2) {
        this.f$0.setSignaturelineArray(((Integer) obj).intValue(), (CTSignatureLine) obj2);
    }
}
