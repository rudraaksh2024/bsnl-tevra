package org.apache.poi.hssf.record;

import java.util.function.Supplier;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class DefaultRowHeightRecord$$ExternalSyntheticLambda1 implements Supplier {
    public final /* synthetic */ DefaultRowHeightRecord f$0;

    public /* synthetic */ DefaultRowHeightRecord$$ExternalSyntheticLambda1(DefaultRowHeightRecord defaultRowHeightRecord) {
        this.f$0 = defaultRowHeightRecord;
    }

    public final Object get() {
        return Short.valueOf(this.f$0.getRowHeight());
    }
}
