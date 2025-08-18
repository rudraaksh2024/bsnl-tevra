package org.apache.poi.ss.formula.atp;

import org.apache.poi.ss.formula.OperationEvaluationContext;
import org.apache.poi.ss.formula.eval.ErrorEval;
import org.apache.poi.ss.formula.eval.ValueEval;
import org.apache.poi.ss.formula.functions.FreeRefFunction;

final class Ifs implements FreeRefFunction {
    public static final FreeRefFunction instance = new Ifs();

    private Ifs() {
    }

    public ValueEval evaluate(ValueEval[] valueEvalArr, OperationEvaluationContext operationEvaluationContext) {
        if (valueEvalArr.length % 2 != 0) {
            return ErrorEval.VALUE_INVALID;
        }
        for (int i = 0; i < valueEvalArr.length; i += 2) {
            if (valueEvalArr[i].getBooleanValue()) {
                return valueEvalArr[i + 1];
            }
        }
        return ErrorEval.NA;
    }
}
