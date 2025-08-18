package org.apache.poi.hssf.record;

import java.util.function.Supplier;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class CellRecord$$ExternalSyntheticLambda1 implements Supplier {
    public final /* synthetic */ CellRecord f$0;

    public /* synthetic */ CellRecord$$ExternalSyntheticLambda1(CellRecord cellRecord) {
        this.f$0 = cellRecord;
    }

    public final Object get() {
        return Short.valueOf(this.f$0.getColumn());
    }
}
