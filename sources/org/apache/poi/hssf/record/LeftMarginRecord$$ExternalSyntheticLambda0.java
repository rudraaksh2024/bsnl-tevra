package org.apache.poi.hssf.record;

import java.util.function.Supplier;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class LeftMarginRecord$$ExternalSyntheticLambda0 implements Supplier {
    public final /* synthetic */ LeftMarginRecord f$0;

    public /* synthetic */ LeftMarginRecord$$ExternalSyntheticLambda0(LeftMarginRecord leftMarginRecord) {
        this.f$0 = leftMarginRecord;
    }

    public final Object get() {
        return Double.valueOf(this.f$0.getMargin());
    }
}
