package org.apache.poi.hssf.record;

import java.util.function.Supplier;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class DimensionsRecord$$ExternalSyntheticLambda3 implements Supplier {
    public final /* synthetic */ DimensionsRecord f$0;

    public /* synthetic */ DimensionsRecord$$ExternalSyntheticLambda3(DimensionsRecord dimensionsRecord) {
        this.f$0 = dimensionsRecord;
    }

    public final Object get() {
        return Short.valueOf(this.f$0.getLastCol());
    }
}
