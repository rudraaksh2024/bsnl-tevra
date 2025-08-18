package org.apache.poi.hssf.record;

import java.util.function.Supplier;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class FnGroupCountRecord$$ExternalSyntheticLambda0 implements Supplier {
    public final /* synthetic */ FnGroupCountRecord f$0;

    public /* synthetic */ FnGroupCountRecord$$ExternalSyntheticLambda0(FnGroupCountRecord fnGroupCountRecord) {
        this.f$0 = fnGroupCountRecord;
    }

    public final Object get() {
        return Short.valueOf(this.f$0.getCount());
    }
}
