package org.apache.poi.hssf.record;

import java.util.function.Supplier;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class CRNCountRecord$$ExternalSyntheticLambda0 implements Supplier {
    public final /* synthetic */ CRNCountRecord f$0;

    public /* synthetic */ CRNCountRecord$$ExternalSyntheticLambda0(CRNCountRecord cRNCountRecord) {
        this.f$0 = cRNCountRecord;
    }

    public final Object get() {
        return Integer.valueOf(this.f$0.getNumberOfCRNs());
    }
}
