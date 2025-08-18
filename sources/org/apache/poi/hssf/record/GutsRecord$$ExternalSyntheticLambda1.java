package org.apache.poi.hssf.record;

import java.util.function.Supplier;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class GutsRecord$$ExternalSyntheticLambda1 implements Supplier {
    public final /* synthetic */ GutsRecord f$0;

    public /* synthetic */ GutsRecord$$ExternalSyntheticLambda1(GutsRecord gutsRecord) {
        this.f$0 = gutsRecord;
    }

    public final Object get() {
        return Short.valueOf(this.f$0.getTopColGutter());
    }
}
