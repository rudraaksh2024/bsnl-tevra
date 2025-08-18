package com.microsoft.schemas.vml.impl;

import com.microsoft.schemas.office.office.CTSignatureLine;
import java.util.function.BiConsumer;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class CTShapetypeImpl$$ExternalSyntheticLambda72 implements BiConsumer {
    public final /* synthetic */ CTShapetypeImpl f$0;

    public /* synthetic */ CTShapetypeImpl$$ExternalSyntheticLambda72(CTShapetypeImpl cTShapetypeImpl) {
        this.f$0 = cTShapetypeImpl;
    }

    public final void accept(Object obj, Object obj2) {
        this.f$0.setSignaturelineArray(((Integer) obj).intValue(), (CTSignatureLine) obj2);
    }
}
