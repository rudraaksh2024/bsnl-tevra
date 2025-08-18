package org.apache.poi.ss.formula.functions;

import org.apache.commons.math3.distribution.NormalDistribution;
import org.apache.poi.ss.formula.OperationEvaluationContext;
import org.apache.poi.ss.formula.eval.ErrorEval;
import org.apache.poi.ss.formula.eval.EvaluationException;
import org.apache.poi.ss.formula.eval.NumberEval;
import org.apache.poi.ss.formula.eval.OperandResolver;
import org.apache.poi.ss.formula.eval.ValueEval;

public final class NormInv extends Fixed3ArgFunction implements FreeRefFunction {
    public static final NormInv instance = new NormInv();

    static double inverse(double d, double d2, double d3) {
        return new NormalDistribution(d2, d3).inverseCumulativeProbability(d);
    }

    private NormInv() {
    }

    public ValueEval evaluate(int i, int i2, ValueEval valueEval, ValueEval valueEval2, ValueEval valueEval3) {
        try {
            Double evaluateValue = evaluateValue(valueEval, i, i2);
            if (evaluateValue == null) {
                return ErrorEval.VALUE_INVALID;
            }
            if (evaluateValue.doubleValue() > 0.0d) {
                if (evaluateValue.doubleValue() < 1.0d) {
                    Double evaluateValue2 = evaluateValue(valueEval2, i, i2);
                    if (evaluateValue2 == null) {
                        return ErrorEval.VALUE_INVALID;
                    }
                    Double evaluateValue3 = evaluateValue(valueEval3, i, i2);
                    if (evaluateValue3 == null) {
                        return ErrorEval.VALUE_INVALID;
                    }
                    if (evaluateValue3.doubleValue() <= 0.0d) {
                        return ErrorEval.NUM_ERROR;
                    }
                    return new NumberEval(inverse(evaluateValue.doubleValue(), evaluateValue2.doubleValue(), evaluateValue3.doubleValue()));
                }
            }
            return ErrorEval.NUM_ERROR;
        } catch (EvaluationException e) {
            return e.getErrorEval();
        }
    }

    public ValueEval evaluate(ValueEval[] valueEvalArr, OperationEvaluationContext operationEvaluationContext) {
        if (valueEvalArr.length != 3) {
            return ErrorEval.VALUE_INVALID;
        }
        return evaluate(operationEvaluationContext.getRowIndex(), operationEvaluationContext.getColumnIndex(), valueEvalArr[0], valueEvalArr[1], valueEvalArr[2]);
    }

    private static Double evaluateValue(ValueEval valueEval, int i, int i2) throws EvaluationException {
        return OperandResolver.parseDouble(OperandResolver.coerceValueToString(OperandResolver.getSingleValue(valueEval, i, i2)));
    }
}
