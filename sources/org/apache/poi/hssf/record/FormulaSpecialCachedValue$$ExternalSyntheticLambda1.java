package org.apache.poi.hssf.record;

import java.util.function.Supplier;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class FormulaSpecialCachedValue$$ExternalSyntheticLambda1 implements Supplier {
    public final /* synthetic */ FormulaSpecialCachedValue f$0;

    public /* synthetic */ FormulaSpecialCachedValue$$ExternalSyntheticLambda1(FormulaSpecialCachedValue formulaSpecialCachedValue) {
        this.f$0 = formulaSpecialCachedValue;
    }

    public final Object get() {
        return Integer.valueOf(this.f$0.getTypeCode());
    }
}
