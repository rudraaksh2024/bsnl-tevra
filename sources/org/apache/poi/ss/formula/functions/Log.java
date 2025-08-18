package org.apache.poi.ss.formula.functions;

import org.apache.poi.ss.formula.eval.ErrorEval;
import org.apache.poi.ss.formula.eval.EvaluationException;
import org.apache.poi.ss.formula.eval.NumberEval;
import org.apache.poi.ss.formula.eval.ValueEval;

public class Log {
    private static final double LOG_10_TO_BASE_e = Math.log(10.0d);
    private static final double TEN = 10.0d;

    public static ValueEval evaluate(ValueEval[] valueEvalArr, int i, int i2) {
        double d;
        if (valueEvalArr.length != 1 && valueEvalArr.length != 2) {
            return ErrorEval.VALUE_INVALID;
        }
        try {
            double singleOperandEvaluate = NumericFunction.singleOperandEvaluate(valueEvalArr[0], i, i2);
            if (valueEvalArr.length == 1) {
                d = Math.log(singleOperandEvaluate) / LOG_10_TO_BASE_e;
            } else {
                double singleOperandEvaluate2 = NumericFunction.singleOperandEvaluate(valueEvalArr[1], i, i2);
                double log = Math.log(singleOperandEvaluate);
                if (Double.compare(singleOperandEvaluate2, 2.718281828459045d) != 0) {
                    log /= Math.log(singleOperandEvaluate2);
                }
                d = log;
            }
            NumericFunction.checkValue(d);
            return new NumberEval(d);
        } catch (EvaluationException e) {
            return e.getErrorEval();
        }
    }
}
