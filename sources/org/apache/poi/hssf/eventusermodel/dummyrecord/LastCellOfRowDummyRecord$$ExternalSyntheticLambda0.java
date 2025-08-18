package org.apache.poi.hssf.eventusermodel.dummyrecord;

import java.util.function.Supplier;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class LastCellOfRowDummyRecord$$ExternalSyntheticLambda0 implements Supplier {
    public final /* synthetic */ LastCellOfRowDummyRecord f$0;

    public /* synthetic */ LastCellOfRowDummyRecord$$ExternalSyntheticLambda0(LastCellOfRowDummyRecord lastCellOfRowDummyRecord) {
        this.f$0 = lastCellOfRowDummyRecord;
    }

    public final Object get() {
        return Integer.valueOf(this.f$0.getRow());
    }
}
