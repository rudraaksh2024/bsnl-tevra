package org.apache.poi.hssf.record;

import java.util.function.Supplier;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class BlankRecord$$ExternalSyntheticLambda2 implements Supplier {
    public final /* synthetic */ BlankRecord f$0;

    public /* synthetic */ BlankRecord$$ExternalSyntheticLambda2(BlankRecord blankRecord) {
        this.f$0 = blankRecord;
    }

    public final Object get() {
        return Short.valueOf(this.f$0.getXFIndex());
    }
}
