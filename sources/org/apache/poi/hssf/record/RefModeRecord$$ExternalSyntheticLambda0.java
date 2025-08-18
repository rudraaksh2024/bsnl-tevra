package org.apache.poi.hssf.record;

import java.util.function.Supplier;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class RefModeRecord$$ExternalSyntheticLambda0 implements Supplier {
    public final /* synthetic */ RefModeRecord f$0;

    public /* synthetic */ RefModeRecord$$ExternalSyntheticLambda0(RefModeRecord refModeRecord) {
        this.f$0 = refModeRecord;
    }

    public final Object get() {
        return Short.valueOf(this.f$0.getMode());
    }
}
