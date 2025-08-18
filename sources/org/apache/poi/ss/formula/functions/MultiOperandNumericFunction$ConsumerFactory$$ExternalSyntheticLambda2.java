package org.apache.poi.ss.formula.functions;

import org.apache.poi.ss.formula.eval.BoolEval;
import org.apache.poi.ss.formula.functions.MultiOperandNumericFunction;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class MultiOperandNumericFunction$ConsumerFactory$$ExternalSyntheticLambda2 implements MultiOperandNumericFunction.EvalConsumer {
    public final void accept(Object obj, Object obj2) {
        ((MultiOperandNumericFunction.DoubleList) obj2).add(((BoolEval) obj).getNumberValue());
    }
}
