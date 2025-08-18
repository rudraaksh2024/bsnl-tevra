package com.microsoft.schemas.office.excel.impl;

import java.util.function.BiConsumer;
import org.openxmlformats.schemas.officeDocument.x2006.sharedTypes.STTrueFalseBlank;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class CTClientDataImpl$$ExternalSyntheticLambda77 implements BiConsumer {
    public final /* synthetic */ CTClientDataImpl f$0;

    public /* synthetic */ CTClientDataImpl$$ExternalSyntheticLambda77(CTClientDataImpl cTClientDataImpl) {
        this.f$0 = cTClientDataImpl;
    }

    public final void accept(Object obj, Object obj2) {
        this.f$0.setLockTextArray(((Integer) obj).intValue(), (STTrueFalseBlank.Enum) obj2);
    }
}
