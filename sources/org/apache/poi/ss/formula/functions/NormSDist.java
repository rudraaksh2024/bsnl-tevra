package org.apache.poi.ss.formula.functions;

import org.apache.poi.ss.formula.OperationEvaluationContext;
import org.apache.poi.ss.formula.eval.ErrorEval;
import org.apache.poi.ss.formula.eval.EvaluationException;
import org.apache.poi.ss.formula.eval.NumberEval;
import org.apache.poi.ss.formula.eval.OperandResolver;
import org.apache.poi.ss.formula.eval.ValueEval;

public final class NormSDist extends Fixed1ArgFunction implements FreeRefFunction {
    public static final NormSDist instance = new NormSDist();

    private NormSDist() {
    }

    public ValueEval evaluate(int i, int i2, ValueEval valueEval) {
        try {
            Double evaluateValue = evaluateValue(valueEval, i, i2);
            if (evaluateValue == null) {
                return ErrorEval.VALUE_INVALID;
            }
            if (evaluateValue.doubleValue() < 0.0d) {
                return ErrorEval.NUM_ERROR;
            }
            return new NumberEval(NormDist.probability(evaluateValue.doubleValue(), 0.0d, 1.0d, true));
        } catch (EvaluationException e) {
            return e.getErrorEval();
        }
    }

    public ValueEval evaluate(ValueEval[] valueEvalArr, OperationEvaluationContext operationEvaluationContext) {
        if (valueEvalArr.length == 1) {
            return evaluate(operationEvaluationContext.getRowIndex(), operationEvaluationContext.getColumnIndex(), valueEvalArr[0]);
        }
        return ErrorEval.VALUE_INVALID;
    }

    private static Double evaluateValue(ValueEval valueEval, int i, int i2) throws EvaluationException {
        return OperandResolver.parseDouble(OperandResolver.coerceValueToString(OperandResolver.getSingleValue(valueEval, i, i2)));
    }
}
