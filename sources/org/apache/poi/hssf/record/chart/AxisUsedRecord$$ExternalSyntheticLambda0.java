package org.apache.poi.hssf.record.chart;

import java.util.function.Supplier;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class AxisUsedRecord$$ExternalSyntheticLambda0 implements Supplier {
    public final /* synthetic */ AxisUsedRecord f$0;

    public /* synthetic */ AxisUsedRecord$$ExternalSyntheticLambda0(AxisUsedRecord axisUsedRecord) {
        this.f$0 = axisUsedRecord;
    }

    public final Object get() {
        return Short.valueOf(this.f$0.getNumAxis());
    }
}
