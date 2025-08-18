package org.apache.poi.hssf.record;

import java.util.function.Supplier;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class CalcCountRecord$$ExternalSyntheticLambda0 implements Supplier {
    public final /* synthetic */ CalcCountRecord f$0;

    public /* synthetic */ CalcCountRecord$$ExternalSyntheticLambda0(CalcCountRecord calcCountRecord) {
        this.f$0 = calcCountRecord;
    }

    public final Object get() {
        return Short.valueOf(this.f$0.getIterations());
    }
}
