package org.apache.poi.ss.formula.functions;

import org.apache.poi.ss.formula.eval.ValueEval;
import org.apache.poi.ss.formula.functions.NumericFunction;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class NumericFunction$$ExternalSyntheticLambda0 implements Function {
    public final /* synthetic */ NumericFunction.OneDoubleIf f$0;

    public /* synthetic */ NumericFunction$$ExternalSyntheticLambda0(NumericFunction.OneDoubleIf oneDoubleIf) {
        this.f$0 = oneDoubleIf;
    }

    public final ValueEval evaluate(ValueEval[] valueEvalArr, int i, int i2) {
        return NumericFunction.lambda$oneDouble$7(this.f$0, valueEvalArr, i, i2);
    }
}
