package org.apache.poi.hssf.record.chart;

import java.util.function.Supplier;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class ValueRangeRecord$$ExternalSyntheticLambda3 implements Supplier {
    public final /* synthetic */ ValueRangeRecord f$0;

    public /* synthetic */ ValueRangeRecord$$ExternalSyntheticLambda3(ValueRangeRecord valueRangeRecord) {
        this.f$0 = valueRangeRecord;
    }

    public final Object get() {
        return Double.valueOf(this.f$0.getMinorIncrement());
    }
}
