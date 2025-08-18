package org.apache.poi.hssf.record;

import java.util.function.Supplier;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class NameRecord$$ExternalSyntheticLambda1 implements Supplier {
    public final /* synthetic */ NameRecord f$0;

    public /* synthetic */ NameRecord$$ExternalSyntheticLambda1(NameRecord nameRecord) {
        this.f$0 = nameRecord;
    }

    public final Object get() {
        return Byte.valueOf(this.f$0.getBuiltInName());
    }
}
