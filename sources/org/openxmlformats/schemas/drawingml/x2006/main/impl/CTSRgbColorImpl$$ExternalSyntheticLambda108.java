package org.openxmlformats.schemas.drawingml.x2006.main.impl;

import java.util.function.BiConsumer;
import org.openxmlformats.schemas.drawingml.x2006.main.CTInverseGammaTransform;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class CTSRgbColorImpl$$ExternalSyntheticLambda108 implements BiConsumer {
    public final /* synthetic */ CTSRgbColorImpl f$0;

    public /* synthetic */ CTSRgbColorImpl$$ExternalSyntheticLambda108(CTSRgbColorImpl cTSRgbColorImpl) {
        this.f$0 = cTSRgbColorImpl;
    }

    public final void accept(Object obj, Object obj2) {
        this.f$0.setInvGammaArray(((Integer) obj).intValue(), (CTInverseGammaTransform) obj2);
    }
}
