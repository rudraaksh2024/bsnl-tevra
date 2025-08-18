package org.apache.poi.hssf.record;

import java.util.function.Supplier;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class DSFRecord$$ExternalSyntheticLambda1 implements Supplier {
    public final /* synthetic */ DSFRecord f$0;

    public /* synthetic */ DSFRecord$$ExternalSyntheticLambda1(DSFRecord dSFRecord) {
        this.f$0 = dSFRecord;
    }

    public final Object get() {
        return Boolean.valueOf(this.f$0.isBiff5BookStreamPresent());
    }
}
