package org.apache.poi.hssf.record;

import java.util.function.Supplier;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class ObjectProtectRecord$$ExternalSyntheticLambda0 implements Supplier {
    public final /* synthetic */ ObjectProtectRecord f$0;

    public /* synthetic */ ObjectProtectRecord$$ExternalSyntheticLambda0(ObjectProtectRecord objectProtectRecord) {
        this.f$0 = objectProtectRecord;
    }

    public final Object get() {
        return Boolean.valueOf(this.f$0.getProtect());
    }
}
