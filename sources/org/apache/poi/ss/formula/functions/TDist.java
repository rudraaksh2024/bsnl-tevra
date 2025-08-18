package org.apache.poi.ss.formula.functions;

import org.apache.commons.math3.distribution.TDistribution;
import org.apache.commons.math3.random.RandomGenerator;
import org.apache.poi.ss.formula.OperationEvaluationContext;
import org.apache.poi.ss.formula.eval.ErrorEval;
import org.apache.poi.ss.formula.eval.EvaluationException;
import org.apache.poi.ss.formula.eval.NumberEval;
import org.apache.poi.ss.formula.eval.OperandResolver;
import org.apache.poi.ss.formula.eval.ValueEval;

public final class TDist extends Fixed3ArgFunction implements FreeRefFunction {
    public static final TDist instance = new TDist();

    static double tdistOneTail(double d, int i) {
        return 1.0d - new TDistribution((RandomGenerator) null, (double) i).cumulativeProbability(d);
    }

    static double tdistTwoTails(double d, int i) {
        return tdistOneTail(d, i) * 2.0d;
    }

    public ValueEval evaluate(int i, int i2, ValueEval valueEval, ValueEval valueEval2, ValueEval valueEval3) {
        try {
            Double evaluateValue = evaluateValue(valueEval, i, i2);
            if (evaluateValue == null) {
                return ErrorEval.VALUE_INVALID;
            }
            if (evaluateValue.doubleValue() < 0.0d) {
                return ErrorEval.NUM_ERROR;
            }
            Double evaluateValue2 = evaluateValue(valueEval2, i, i2);
            if (evaluateValue2 == null) {
                return ErrorEval.VALUE_INVALID;
            }
            int intValue = evaluateValue2.intValue();
            if (intValue < 1) {
                return ErrorEval.NUM_ERROR;
            }
            Double evaluateValue3 = evaluateValue(valueEval3, i, i2);
            if (evaluateValue3 == null) {
                return ErrorEval.VALUE_INVALID;
            }
            int intValue2 = evaluateValue3.intValue();
            if (intValue2 != 1 && intValue2 != 2) {
                return ErrorEval.NUM_ERROR;
            }
            if (intValue2 == 2) {
                return new NumberEval(tdistTwoTails(evaluateValue.doubleValue(), intValue));
            }
            return new NumberEval(tdistOneTail(evaluateValue.doubleValue(), intValue));
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
