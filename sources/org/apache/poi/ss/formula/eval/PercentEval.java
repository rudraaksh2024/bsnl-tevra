package org.apache.poi.ss.formula.eval;

import org.apache.poi.ss.formula.functions.Fixed1ArgFunction;
import org.apache.poi.ss.formula.functions.Function;

public final class PercentEval extends Fixed1ArgFunction {
    public static final Function instance = new PercentEval();

    private PercentEval() {
    }

    public ValueEval evaluate(int i, int i2, ValueEval valueEval) {
        try {
            double coerceValueToDouble = OperandResolver.coerceValueToDouble(OperandResolver.getSingleValue(valueEval, i, i2));
            if (coerceValueToDouble == 0.0d) {
                return NumberEval.ZERO;
            }
            return new NumberEval(coerceValueToDouble / 100.0d);
        } catch (EvaluationException e) {
            return e.getErrorEval();
        }
    }
}
