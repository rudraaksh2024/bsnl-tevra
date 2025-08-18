package org.apache.poi.hssf.record;

import java.util.function.Supplier;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class CalcModeRecord$$ExternalSyntheticLambda0 implements Supplier {
    public final /* synthetic */ CalcModeRecord f$0;

    public /* synthetic */ CalcModeRecord$$ExternalSyntheticLambda0(CalcModeRecord calcModeRecord) {
        this.f$0 = calcModeRecord;
    }

    public final Object get() {
        return Short.valueOf(this.f$0.getCalcMode());
    }
}
