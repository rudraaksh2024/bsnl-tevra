package org.apache.poi.hssf.record;

import java.util.function.Supplier;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class TopMarginRecord$$ExternalSyntheticLambda0 implements Supplier {
    public final /* synthetic */ TopMarginRecord f$0;

    public /* synthetic */ TopMarginRecord$$ExternalSyntheticLambda0(TopMarginRecord topMarginRecord) {
        this.f$0 = topMarginRecord;
    }

    public final Object get() {
        return Double.valueOf(this.f$0.getMargin());
    }
}
