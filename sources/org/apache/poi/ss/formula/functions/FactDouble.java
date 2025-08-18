package org.apache.poi.ss.formula.functions;

import java.math.BigInteger;
import java.util.HashMap;
import org.apache.poi.ss.formula.OperationEvaluationContext;
import org.apache.poi.ss.formula.eval.ErrorEval;
import org.apache.poi.ss.formula.eval.EvaluationException;
import org.apache.poi.ss.formula.eval.NumberEval;
import org.apache.poi.ss.formula.eval.OperandResolver;
import org.apache.poi.ss.formula.eval.ValueEval;

public class FactDouble extends Fixed1ArgFunction implements FreeRefFunction {
    static HashMap<Integer, BigInteger> cache = new HashMap<>();
    public static final FreeRefFunction instance = new FactDouble();

    public ValueEval evaluate(int i, int i2, ValueEval valueEval) {
        try {
            int coerceValueToInt = OperandResolver.coerceValueToInt(valueEval);
            if (coerceValueToInt < 0) {
                return ErrorEval.NUM_ERROR;
            }
            return new NumberEval((double) factorial(coerceValueToInt).longValue());
        } catch (EvaluationException unused) {
            return ErrorEval.VALUE_INVALID;
        }
    }

    public static BigInteger factorial(int i) {
        if (i == 0 || i < 0) {
            return BigInteger.ONE;
        }
        if (cache.containsKey(Integer.valueOf(i))) {
            return cache.get(Integer.valueOf(i));
        }
        BigInteger multiply = BigInteger.valueOf((long) i).multiply(factorial(i - 2));
        cache.put(Integer.valueOf(i), multiply);
        return multiply;
    }

    public ValueEval evaluate(ValueEval[] valueEvalArr, OperationEvaluationContext operationEvaluationContext) {
        if (valueEvalArr.length != 1) {
            return ErrorEval.VALUE_INVALID;
        }
        return evaluate(operationEvaluationContext.getRowIndex(), operationEvaluationContext.getColumnIndex(), valueEvalArr[0]);
    }
}
