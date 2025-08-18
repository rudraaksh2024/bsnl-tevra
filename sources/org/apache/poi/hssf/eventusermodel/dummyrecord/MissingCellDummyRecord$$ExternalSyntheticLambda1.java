package org.apache.poi.hssf.eventusermodel.dummyrecord;

import java.util.function.Supplier;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class MissingCellDummyRecord$$ExternalSyntheticLambda1 implements Supplier {
    public final /* synthetic */ MissingCellDummyRecord f$0;

    public /* synthetic */ MissingCellDummyRecord$$ExternalSyntheticLambda1(MissingCellDummyRecord missingCellDummyRecord) {
        this.f$0 = missingCellDummyRecord;
    }

    public final Object get() {
        return Integer.valueOf(this.f$0.getColumn());
    }
}
