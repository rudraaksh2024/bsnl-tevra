package org.apache.poi.hssf.record;

import java.util.function.Supplier;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class PrecisionRecord$$ExternalSyntheticLambda0 implements Supplier {
    public final /* synthetic */ PrecisionRecord f$0;

    public /* synthetic */ PrecisionRecord$$ExternalSyntheticLambda0(PrecisionRecord precisionRecord) {
        this.f$0 = precisionRecord;
    }

    public final Object get() {
        return Boolean.valueOf(this.f$0.getFullPrecision());
    }
}
