package org.apache.poi.hssf.record;

import java.util.function.Supplier;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class BoolErrRecord$$ExternalSyntheticLambda3 implements Supplier {
    public final /* synthetic */ BoolErrRecord f$0;

    public /* synthetic */ BoolErrRecord$$ExternalSyntheticLambda3(BoolErrRecord boolErrRecord) {
        this.f$0 = boolErrRecord;
    }

    public final Object get() {
        return Boolean.valueOf(this.f$0.isError());
    }
}
