package org.apache.poi.hssf.record;

import java.util.function.Supplier;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class UnknownRecord$$ExternalSyntheticLambda1 implements Supplier {
    public final /* synthetic */ UnknownRecord f$0;

    public /* synthetic */ UnknownRecord$$ExternalSyntheticLambda1(UnknownRecord unknownRecord) {
        this.f$0 = unknownRecord;
    }

    public final Object get() {
        return Short.valueOf(this.f$0.getSid());
    }
}
