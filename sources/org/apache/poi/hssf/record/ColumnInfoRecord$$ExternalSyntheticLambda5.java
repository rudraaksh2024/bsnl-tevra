package org.apache.poi.hssf.record;

import java.util.function.Supplier;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class ColumnInfoRecord$$ExternalSyntheticLambda5 implements Supplier {
    public final /* synthetic */ ColumnInfoRecord f$0;

    public /* synthetic */ ColumnInfoRecord$$ExternalSyntheticLambda5(ColumnInfoRecord columnInfoRecord) {
        this.f$0 = columnInfoRecord;
    }

    public final Object get() {
        return Boolean.valueOf(this.f$0.getHidden());
    }
}
