package org.apache.poi.hssf.record;

import java.util.function.Supplier;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class RightMarginRecord$$ExternalSyntheticLambda0 implements Supplier {
    public final /* synthetic */ RightMarginRecord f$0;

    public /* synthetic */ RightMarginRecord$$ExternalSyntheticLambda0(RightMarginRecord rightMarginRecord) {
        this.f$0 = rightMarginRecord;
    }

    public final Object get() {
        return Double.valueOf(this.f$0.getMargin());
    }
}
