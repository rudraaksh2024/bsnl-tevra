package org.apache.poi.hssf.record.chart;

import java.util.function.Supplier;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class AxisOptionsRecord$$ExternalSyntheticLambda4 implements Supplier {
    public final /* synthetic */ AxisOptionsRecord f$0;

    public /* synthetic */ AxisOptionsRecord$$ExternalSyntheticLambda4(AxisOptionsRecord axisOptionsRecord) {
        this.f$0 = axisOptionsRecord;
    }

    public final Object get() {
        return Short.valueOf(this.f$0.getMinorUnitValue());
    }
}
