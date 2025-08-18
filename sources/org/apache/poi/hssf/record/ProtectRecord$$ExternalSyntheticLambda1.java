package org.apache.poi.hssf.record;

import java.util.function.Supplier;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class ProtectRecord$$ExternalSyntheticLambda1 implements Supplier {
    public final /* synthetic */ ProtectRecord f$0;

    public /* synthetic */ ProtectRecord$$ExternalSyntheticLambda1(ProtectRecord protectRecord) {
        this.f$0 = protectRecord;
    }

    public final Object get() {
        return Boolean.valueOf(this.f$0.getProtect());
    }
}
