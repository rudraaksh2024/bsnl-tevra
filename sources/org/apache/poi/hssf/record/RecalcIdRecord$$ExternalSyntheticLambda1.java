package org.apache.poi.hssf.record;

import java.util.function.Supplier;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class RecalcIdRecord$$ExternalSyntheticLambda1 implements Supplier {
    public final /* synthetic */ RecalcIdRecord f$0;

    public /* synthetic */ RecalcIdRecord$$ExternalSyntheticLambda1(RecalcIdRecord recalcIdRecord) {
        this.f$0 = recalcIdRecord;
    }

    public final Object get() {
        return Integer.valueOf(this.f$0.getEngineId());
    }
}
