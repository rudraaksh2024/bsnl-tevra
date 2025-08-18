package org.apache.poi.hssf.record;

import java.util.function.Supplier;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class RowRecord$$ExternalSyntheticLambda7 implements Supplier {
    public final /* synthetic */ RowRecord f$0;

    public /* synthetic */ RowRecord$$ExternalSyntheticLambda7(RowRecord rowRecord) {
        this.f$0 = rowRecord;
    }

    public final Object get() {
        return Short.valueOf(this.f$0.getOutlineLevel());
    }
}
