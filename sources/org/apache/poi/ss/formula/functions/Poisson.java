package org.apache.poi.ss.formula.functions;

import org.apache.poi.ss.formula.eval.BoolEval;
import org.apache.poi.ss.formula.eval.ErrorEval;
import org.apache.poi.ss.formula.eval.EvaluationException;
import org.apache.poi.ss.formula.eval.NumberEval;
import org.apache.poi.ss.formula.eval.ValueEval;

public class Poisson {
    private static final double DEFAULT_RETURN_RESULT = 1.0d;
    private static final long[] FACTORIALS = {1, 1, 2, 6, 24, 120, 720, 5040, 40320, 362880, 3628800, 39916800, 479001600, 6227020800L, 87178291200L, 1307674368000L, 20922789888000L, 355687428096000L, 6402373705728000L, 121645100408832000L, 2432902008176640000L};

    private static boolean isDefaultResult(double d, double d2) {
        return d == 0.0d && d2 == 0.0d;
    }

    private static void checkArgument(double d) throws EvaluationException {
        NumericFunction.checkValue(d);
        if (d < 0.0d) {
            throw new EvaluationException(ErrorEval.NUM_ERROR);
        }
    }

    private static double probability(int i, double d) {
        return (Math.pow(d, (double) i) * Math.exp(-d)) / ((double) factorial(i));
    }

    private static double cumulativeProbability(int i, double d) {
        double d2 = 0.0d;
        for (int i2 = 0; i2 <= i; i2++) {
            d2 += probability(i2, d);
        }
        return d2;
    }

    private static long factorial(int i) {
        if (i >= 0 && i <= 20) {
            return FACTORIALS[i];
        }
        throw new IllegalArgumentException("Valid argument should be in the range [0..20]");
    }

    public static ValueEval evaluate(ValueEval[] valueEvalArr, int i, int i2) {
        if (valueEvalArr.length != 3) {
            return ErrorEval.VALUE_INVALID;
        }
        ValueEval valueEval = valueEvalArr[0];
        ValueEval valueEval2 = valueEvalArr[1];
        BoolEval boolEval = valueEvalArr[2];
        try {
            double singleOperandEvaluate = NumericFunction.singleOperandEvaluate(valueEval, i, i2);
            double singleOperandEvaluate2 = NumericFunction.singleOperandEvaluate(valueEval2, i, i2);
            if (isDefaultResult(singleOperandEvaluate, singleOperandEvaluate2)) {
                return new NumberEval((double) DEFAULT_RETURN_RESULT);
            }
            checkArgument(singleOperandEvaluate);
            checkArgument(singleOperandEvaluate2);
            double cumulativeProbability = boolEval.getBooleanValue() ? cumulativeProbability((int) singleOperandEvaluate, singleOperandEvaluate2) : probability((int) singleOperandEvaluate, singleOperandEvaluate2);
            NumericFunction.checkValue(cumulativeProbability);
            return new NumberEval(cumulativeProbability);
        } catch (EvaluationException e) {
            return e.getErrorEval();
        }
    }
}
