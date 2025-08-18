package org.apache.poi.hssf.record;

import java.util.function.Supplier;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class OldCellRecord$$ExternalSyntheticLambda1 implements Supplier {
    public final /* synthetic */ OldCellRecord f$0;

    public /* synthetic */ OldCellRecord$$ExternalSyntheticLambda1(OldCellRecord oldCellRecord) {
        this.f$0 = oldCellRecord;
    }

    public final Object get() {
        return Short.valueOf(this.f$0.getColumn());
    }
}
