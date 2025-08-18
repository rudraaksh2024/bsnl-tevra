package org.apache.poi.hssf.record;

import java.util.function.Supplier;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class DimensionsRecord$$ExternalSyntheticLambda1 implements Supplier {
    public final /* synthetic */ DimensionsRecord f$0;

    public /* synthetic */ DimensionsRecord$$ExternalSyntheticLambda1(DimensionsRecord dimensionsRecord) {
        this.f$0 = dimensionsRecord;
    }

    public final Object get() {
        return Integer.valueOf(this.f$0.getLastRow());
    }
}
