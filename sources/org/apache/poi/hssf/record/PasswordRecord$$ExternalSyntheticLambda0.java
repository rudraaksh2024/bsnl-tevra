package org.apache.poi.hssf.record;

import java.util.function.Supplier;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class PasswordRecord$$ExternalSyntheticLambda0 implements Supplier {
    public final /* synthetic */ PasswordRecord f$0;

    public /* synthetic */ PasswordRecord$$ExternalSyntheticLambda0(PasswordRecord passwordRecord) {
        this.f$0 = passwordRecord;
    }

    public final Object get() {
        return Integer.valueOf(this.f$0.getPassword());
    }
}
