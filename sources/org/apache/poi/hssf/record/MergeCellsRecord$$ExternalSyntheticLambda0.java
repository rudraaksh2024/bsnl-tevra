package org.apache.poi.hssf.record;

import java.util.function.Supplier;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class MergeCellsRecord$$ExternalSyntheticLambda0 implements Supplier {
    public final /* synthetic */ MergeCellsRecord f$0;

    public /* synthetic */ MergeCellsRecord$$ExternalSyntheticLambda0(MergeCellsRecord mergeCellsRecord) {
        this.f$0 = mergeCellsRecord;
    }

    public final Object get() {
        return Short.valueOf(this.f$0.getNumAreas());
    }
}
