package org.apache.poi.ss.formula.eval;

import org.apache.poi.ss.formula.functions.ArrayFunction;
import org.apache.poi.ss.formula.functions.Fixed1ArgFunction;
import org.apache.poi.ss.formula.functions.Function;

public final class UnaryMinusEval extends Fixed1ArgFunction implements ArrayFunction {
    public static final Function instance = new UnaryMinusEval();

    private UnaryMinusEval() {
    }

    /* renamed from: evaluate */
    public ValueEval m2252lambda$evaluateArray$0$orgapachepoissformulaevalUnaryMinusEval(int i, int i2, ValueEval valueEval) {
        try {
            double coerceValueToDouble = OperandResolver.coerceValueToDouble(OperandResolver.getSingleValue(valueEval, i, i2));
            if (coerceValueToDouble == 0.0d) {
                return NumberEval.ZERO;
            }
            return new NumberEval(-coerceValueToDouble);
        } catch (EvaluationException e) {
            return e.getErrorEval();
        }
    }

    public ValueEval evaluateArray(ValueEval[] valueEvalArr, int i, int i2) {
        if (valueEvalArr.length != 1) {
            return ErrorEval.VALUE_INVALID;
        }
        return evaluateOneArrayArg(valueEvalArr[0], i, i2, new UnaryMinusEval$$ExternalSyntheticLambda0(this, i, i2));
    }
}
