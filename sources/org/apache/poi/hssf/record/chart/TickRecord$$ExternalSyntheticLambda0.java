package org.apache.poi.hssf.record.chart;

import java.util.function.Supplier;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class TickRecord$$ExternalSyntheticLambda0 implements Supplier {
    public final /* synthetic */ TickRecord f$0;

    public /* synthetic */ TickRecord$$ExternalSyntheticLambda0(TickRecord tickRecord) {
        this.f$0 = tickRecord;
    }

    public final Object get() {
        return Byte.valueOf(this.f$0.getMajorTickType());
    }
}
