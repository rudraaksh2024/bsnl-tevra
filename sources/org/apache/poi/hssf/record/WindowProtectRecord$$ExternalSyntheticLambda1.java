package org.apache.poi.hssf.record;

import java.util.function.Supplier;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class WindowProtectRecord$$ExternalSyntheticLambda1 implements Supplier {
    public final /* synthetic */ WindowProtectRecord f$0;

    public /* synthetic */ WindowProtectRecord$$ExternalSyntheticLambda1(WindowProtectRecord windowProtectRecord) {
        this.f$0 = windowProtectRecord;
    }

    public final Object get() {
        return Boolean.valueOf(this.f$0.getProtect());
    }
}
