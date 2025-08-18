package org.apache.poi.ss.formula.functions;

import org.apache.poi.ss.formula.OperationEvaluationContext;
import org.apache.poi.ss.formula.eval.ErrorEval;
import org.apache.poi.ss.formula.eval.EvaluationException;
import org.apache.poi.ss.formula.eval.NumberEval;
import org.apache.poi.ss.formula.eval.OperandResolver;
import org.apache.poi.ss.formula.eval.ValueEval;

public final class TDistRt extends Fixed2ArgFunction implements FreeRefFunction {
    public static final TDistRt instance = new TDistRt();

    public ValueEval evaluate(int i, int i2, ValueEval valueEval, ValueEval valueEval2) {
        try {
            Double evaluateValue = evaluateValue(valueEval, i, i2);
            if (evaluateValue == null) {
                return ErrorEval.VALUE_INVALID;
            }
            Double evaluateValue2 = evaluateValue(valueEval2, i, i2);
            if (evaluateValue2 == null) {
                return ErrorEval.VALUE_INVALID;
            }
            int intValue = evaluateValue2.intValue();
            if (intValue < 1) {
                return ErrorEval.NUM_ERROR;
            }
            return new NumberEval(TDist.tdistOneTail(evaluateValue.doubleValue(), intValue));
        } catch (EvaluationException e) {
            return e.getErrorEval();
        }
    }

    public ValueEval evaluate(ValueEval[] valueEvalArr, OperationEvaluationContext operationEvaluationContext) {
        if (valueEvalArr.length == 2) {
            return evaluate(operationEvaluationContext.getRowIndex(), operationEvaluationContext.getColumnIndex(), valueEvalArr[0], valueEvalArr[1]);
        }
        return ErrorEval.VALUE_INVALID;
    }

    private static Double evaluateValue(ValueEval valueEval, int i, int i2) throws EvaluationException {
        return OperandResolver.parseDouble(OperandResolver.coerceValueToString(OperandResolver.getSingleValue(valueEval, i, i2)));
    }
}
