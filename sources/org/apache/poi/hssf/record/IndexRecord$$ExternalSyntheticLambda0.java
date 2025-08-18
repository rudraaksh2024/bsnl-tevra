package org.apache.poi.hssf.record;

import java.util.function.Supplier;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class IndexRecord$$ExternalSyntheticLambda0 implements Supplier {
    public final /* synthetic */ IndexRecord f$0;

    public /* synthetic */ IndexRecord$$ExternalSyntheticLambda0(IndexRecord indexRecord) {
        this.f$0 = indexRecord;
    }

    public final Object get() {
        return Integer.valueOf(this.f$0.getFirstRow());
    }
}
