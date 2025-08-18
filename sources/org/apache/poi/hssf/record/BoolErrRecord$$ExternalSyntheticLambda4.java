package org.apache.poi.hssf.record;

import java.util.function.Supplier;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class BoolErrRecord$$ExternalSyntheticLambda4 implements Supplier {
    public final /* synthetic */ BoolErrRecord f$0;

    public /* synthetic */ BoolErrRecord$$ExternalSyntheticLambda4(BoolErrRecord boolErrRecord) {
        this.f$0 = boolErrRecord;
    }

    public final Object get() {
        return Byte.valueOf(this.f$0.getErrorValue());
    }
}
