package org.apache.poi.hssf.record;

import java.util.function.Supplier;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class SaveRecalcRecord$$ExternalSyntheticLambda0 implements Supplier {
    public final /* synthetic */ SaveRecalcRecord f$0;

    public /* synthetic */ SaveRecalcRecord$$ExternalSyntheticLambda0(SaveRecalcRecord saveRecalcRecord) {
        this.f$0 = saveRecalcRecord;
    }

    public final Object get() {
        return Boolean.valueOf(this.f$0.getRecalc());
    }
}
