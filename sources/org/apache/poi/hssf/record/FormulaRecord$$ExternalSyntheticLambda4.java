package org.apache.poi.hssf.record;

import java.util.function.Supplier;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class FormulaRecord$$ExternalSyntheticLambda4 implements Supplier {
    public final /* synthetic */ FormulaRecord f$0;

    public /* synthetic */ FormulaRecord$$ExternalSyntheticLambda4(FormulaRecord formulaRecord) {
        this.f$0 = formulaRecord;
    }

    public final Object get() {
        return Boolean.valueOf(this.f$0.isSharedFormula());
    }
}
