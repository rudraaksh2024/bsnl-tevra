package com.microsoft.schemas.vml.impl;

import com.microsoft.schemas.office.office.CTInk;
import java.util.function.BiConsumer;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class CTShapeImpl$$ExternalSyntheticLambda24 implements BiConsumer {
    public final /* synthetic */ CTShapeImpl f$0;

    public /* synthetic */ CTShapeImpl$$ExternalSyntheticLambda24(CTShapeImpl cTShapeImpl) {
        this.f$0 = cTShapeImpl;
    }

    public final void accept(Object obj, Object obj2) {
        this.f$0.setInkArray(((Integer) obj).intValue(), (CTInk) obj2);
    }
}
