package org.apache.poi.hssf.record;

import java.util.function.Supplier;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class BOFRecord$$ExternalSyntheticLambda5 implements Supplier {
    public final /* synthetic */ BOFRecord f$0;

    public /* synthetic */ BOFRecord$$ExternalSyntheticLambda5(BOFRecord bOFRecord) {
        this.f$0 = bOFRecord;
    }

    public final Object get() {
        return Integer.valueOf(this.f$0.getHistoryBitMask());
    }
}
