package com.microsoft.schemas.vml.impl;

import com.microsoft.schemas.vml.CTStroke;
import java.util.function.BiConsumer;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class CTShapeImpl$$ExternalSyntheticLambda2 implements BiConsumer {
    public final /* synthetic */ CTShapeImpl f$0;

    public /* synthetic */ CTShapeImpl$$ExternalSyntheticLambda2(CTShapeImpl cTShapeImpl) {
        this.f$0 = cTShapeImpl;
    }

    public final void accept(Object obj, Object obj2) {
        this.f$0.setStrokeArray(((Integer) obj).intValue(), (CTStroke) obj2);
    }
}
