package org.apache.poi.ss.formula;

import org.apache.logging.log4j.util.Supplier;
import org.apache.poi.ss.formula.ptg.Ptg;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class WorkbookEvaluator$$ExternalSyntheticLambda1 implements Supplier {
    public final /* synthetic */ String f$0;
    public final /* synthetic */ OperationEvaluationContext f$1;
    public final /* synthetic */ Ptg[] f$2;

    public /* synthetic */ WorkbookEvaluator$$ExternalSyntheticLambda1(String str, OperationEvaluationContext operationEvaluationContext, Ptg[] ptgArr) {
        this.f$0 = str;
        this.f$1 = operationEvaluationContext;
        this.f$2 = ptgArr;
    }

    public final Object get() {
        return WorkbookEvaluator.lambda$evaluateFormula$1(this.f$0, this.f$1, this.f$2);
    }
}
