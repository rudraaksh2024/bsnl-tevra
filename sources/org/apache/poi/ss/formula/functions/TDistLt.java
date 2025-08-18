package org.apache.poi.ss.formula.functions;

import org.apache.commons.math3.distribution.TDistribution;
import org.apache.commons.math3.random.RandomGenerator;
import org.apache.poi.ss.formula.OperationEvaluationContext;
import org.apache.poi.ss.formula.eval.ErrorEval;
import org.apache.poi.ss.formula.eval.EvaluationException;
import org.apache.poi.ss.formula.eval.NumberEval;
import org.apache.poi.ss.formula.eval.OperandResolver;
import org.apache.poi.ss.formula.eval.ValueEval;

public final class TDistLt extends Fixed3ArgFunction implements FreeRefFunction {
    public static final TDistLt instance = new TDistLt();

    private static double tdistCumulative(double d, int i) {
        return new TDistribution((RandomGenerator) null, (double) i).cumulativeProbability(d);
    }

    private static double tdistDensity(double d, int i) {
        return new TDistribution((RandomGenerator) null, (double) i).density(d);
    }

    public ValueEval evaluate(int i, int i2, ValueEval valueEval, ValueEval valueEval2, ValueEval valueEval3) {
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
            Boolean evaluateBoolean = evaluateBoolean(valueEval3, i, i2);
            if (evaluateBoolean == null) {
                return ErrorEval.VALUE_INVALID;
            }
            if (evaluateBoolean.booleanValue()) {
                return new NumberEval(tdistCumulative(evaluateValue.doubleValue(), intValue));
            }
            return new NumberEval(tdistDensity(evaluateValue.doubleValue(), intValue));
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

    private static Boolean evaluateBoolean(ValueEval valueEval, int i, int i2) throws EvaluationException {
        return OperandResolver.coerceValueToBoolean(OperandResolver.getSingleValue(valueEval, i, i2), false);
    }
}
