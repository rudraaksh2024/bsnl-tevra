package org.apache.poi.hssf.record.chart;

import java.util.function.Supplier;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class AxisLineFormatRecord$$ExternalSyntheticLambda0 implements Supplier {
    public final /* synthetic */ AxisLineFormatRecord f$0;

    public /* synthetic */ AxisLineFormatRecord$$ExternalSyntheticLambda0(AxisLineFormatRecord axisLineFormatRecord) {
        this.f$0 = axisLineFormatRecord;
    }

    public final Object get() {
        return Short.valueOf(this.f$0.getAxisType());
    }
}
