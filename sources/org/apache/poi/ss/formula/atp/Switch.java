package org.apache.poi.ss.formula.atp;

import org.apache.poi.ss.formula.OperationEvaluationContext;
import org.apache.poi.ss.formula.eval.BoolEval;
import org.apache.poi.ss.formula.eval.ErrorEval;
import org.apache.poi.ss.formula.eval.EvaluationException;
import org.apache.poi.ss.formula.eval.OperandResolver;
import org.apache.poi.ss.formula.eval.RelationalOperationEval;
import org.apache.poi.ss.formula.eval.ValueEval;
import org.apache.poi.ss.formula.functions.FreeRefFunction;

public final class Switch implements FreeRefFunction {
    public static final FreeRefFunction instance = new Switch();

    private Switch() {
    }

    public ValueEval evaluate(ValueEval[] valueEvalArr, OperationEvaluationContext operationEvaluationContext) {
        if (valueEvalArr.length < 3) {
            return ErrorEval.NA;
        }
        try {
            ValueEval singleValue = OperandResolver.getSingleValue(valueEvalArr[0], operationEvaluationContext.getRowIndex(), operationEvaluationContext.getColumnIndex());
            int i = 1;
            while (i < valueEvalArr.length) {
                try {
                    ValueEval singleValue2 = OperandResolver.getSingleValue(valueEvalArr[i], operationEvaluationContext.getRowIndex(), operationEvaluationContext.getColumnIndex());
                    ValueEval valueEval = valueEvalArr[i + 1];
                    ValueEval evaluate = RelationalOperationEval.EqualEval.evaluate(new ValueEval[]{singleValue, singleValue2}, operationEvaluationContext.getRowIndex(), operationEvaluationContext.getColumnIndex());
                    if ((evaluate instanceof BoolEval) && ((BoolEval) evaluate).getBooleanValue()) {
                        return valueEval;
                    }
                    i += 2;
                    if (i == valueEvalArr.length - 1) {
                        return valueEvalArr[valueEvalArr.length - 1];
                    }
                } catch (EvaluationException unused) {
                    return ErrorEval.NA;
                }
            }
            return ErrorEval.NA;
        } catch (Exception unused2) {
            return ErrorEval.NA;
        }
    }
}
