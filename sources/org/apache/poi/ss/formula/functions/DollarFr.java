package org.apache.poi.ss.formula.functions;

import java.math.BigDecimal;
import java.math.MathContext;
import org.apache.poi.ss.formula.OperationEvaluationContext;
import org.apache.poi.ss.formula.eval.ErrorEval;
import org.apache.poi.ss.formula.eval.EvaluationException;
import org.apache.poi.ss.formula.eval.NumberEval;
import org.apache.poi.ss.formula.eval.OperandResolver;
import org.apache.poi.ss.formula.eval.ValueEval;

public final class DollarFr extends Fixed2ArgFunction implements FreeRefFunction {
    public static final FreeRefFunction instance = new DollarFr();

    public ValueEval evaluate(int i, int i2, ValueEval valueEval, ValueEval valueEval2) {
        boolean z;
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
            if (intValue < 0) {
                return ErrorEval.NUM_ERROR;
            }
            if (intValue == 0) {
                return ErrorEval.DIV_ZERO;
            }
            int length = String.valueOf(intValue).length();
            long longValue = evaluateValue.longValue();
            if (longValue < 0) {
                longValue = -longValue;
                evaluateValue = Double.valueOf(-evaluateValue.doubleValue());
                z = true;
            } else {
                z = false;
            }
            double d = (double) longValue;
            double doubleValue = evaluateValue.doubleValue() - d;
            if (doubleValue == 0.0d) {
                return new NumberEval(d);
            }
            BigDecimal add = BigDecimal.valueOf(doubleValue).multiply(BigDecimal.valueOf((long) intValue)).divide(BigDecimal.valueOf(Math.pow(10.0d, (double) length)), MathContext.DECIMAL128).add(BigDecimal.valueOf(longValue));
            if (z) {
                add = add.multiply(BigDecimal.valueOf(-1));
            }
            return new NumberEval(add.doubleValue());
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
