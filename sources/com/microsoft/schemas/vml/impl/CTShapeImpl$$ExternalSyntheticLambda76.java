package com.microsoft.schemas.vml.impl;

import com.microsoft.schemas.office.office.CTSignatureLine;
import java.util.function.BiConsumer;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class CTShapeImpl$$ExternalSyntheticLambda76 implements BiConsumer {
    public final /* synthetic */ CTShapeImpl f$0;

    public /* synthetic */ CTShapeImpl$$ExternalSyntheticLambda76(CTShapeImpl cTShapeImpl) {
        this.f$0 = cTShapeImpl;
    }

    public final void accept(Object obj, Object obj2) {
        this.f$0.setSignaturelineArray(((Integer) obj).intValue(), (CTSignatureLine) obj2);
    }
}
