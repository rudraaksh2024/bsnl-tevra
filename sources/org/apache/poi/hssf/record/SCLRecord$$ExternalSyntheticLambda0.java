package org.apache.poi.hssf.record;

import java.util.function.Supplier;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class SCLRecord$$ExternalSyntheticLambda0 implements Supplier {
    public final /* synthetic */ SCLRecord f$0;

    public /* synthetic */ SCLRecord$$ExternalSyntheticLambda0(SCLRecord sCLRecord) {
        this.f$0 = sCLRecord;
    }

    public final Object get() {
        return Short.valueOf(this.f$0.getNumerator());
    }
}
