package org.apache.poi.hssf.record;

import java.util.function.Supplier;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class CFHeaderBase$$ExternalSyntheticLambda2 implements Supplier {
    public final /* synthetic */ CFHeaderBase f$0;

    public /* synthetic */ CFHeaderBase$$ExternalSyntheticLambda2(CFHeaderBase cFHeaderBase) {
        this.f$0 = cFHeaderBase;
    }

    public final Object get() {
        return Boolean.valueOf(this.f$0.getNeedRecalculation());
    }
}
