package org.apache.poi.hssf.record;

import java.util.function.Supplier;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class DeltaRecord$$ExternalSyntheticLambda0 implements Supplier {
    public final /* synthetic */ DeltaRecord f$0;

    public /* synthetic */ DeltaRecord$$ExternalSyntheticLambda0(DeltaRecord deltaRecord) {
        this.f$0 = deltaRecord;
    }

    public final Object get() {
        return Double.valueOf(this.f$0.getMaxChange());
    }
}
