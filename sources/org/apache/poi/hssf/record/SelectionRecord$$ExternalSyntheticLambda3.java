package org.apache.poi.hssf.record;

import java.util.function.Supplier;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class SelectionRecord$$ExternalSyntheticLambda3 implements Supplier {
    public final /* synthetic */ SelectionRecord f$0;

    public /* synthetic */ SelectionRecord$$ExternalSyntheticLambda3(SelectionRecord selectionRecord) {
        this.f$0 = selectionRecord;
    }

    public final Object get() {
        return Integer.valueOf(this.f$0.getActiveCellRow());
    }
}
