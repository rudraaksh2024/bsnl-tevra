package org.apache.poi.ss.formula.eval;

import org.apache.poi.ss.formula.functions.ArrayFunction;
import org.apache.poi.ss.formula.functions.Fixed1ArgFunction;
import org.apache.poi.ss.formula.functions.Function;

public final class UnaryPlusEval extends Fixed1ArgFunction implements ArrayFunction {
    public static final Function instance = new UnaryPlusEval();

    private UnaryPlusEval() {
    }

    /* renamed from: evaluate */
    public ValueEval m2253lambda$evaluateArray$0$orgapachepoissformulaevalUnaryPlusEval(int i, int i2, ValueEval valueEval) {
        try {
            ValueEval singleValue = OperandResolver.getSingleValue(valueEval, i, i2);
            if (singleValue instanceof StringEval) {
                return singleValue;
            }
            return new NumberEval(OperandResolver.coerceValueToDouble(singleValue));
        } catch (EvaluationException e) {
            return e.getErrorEval();
        }
    }

    public ValueEval evaluateArray(ValueEval[] valueEvalArr, int i, int i2) {
        if (valueEvalArr.length != 1) {
            return ErrorEval.VALUE_INVALID;
        }
        return evaluateOneArrayArg(valueEvalArr[0], i, i2, new UnaryPlusEval$$ExternalSyntheticLambda0(this, i, i2));
    }
}
