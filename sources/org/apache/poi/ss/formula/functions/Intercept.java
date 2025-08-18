package org.apache.poi.ss.formula.functions;

import org.apache.poi.ss.formula.eval.ValueEval;
import org.apache.poi.ss.formula.functions.LinearRegressionFunction;

public final class Intercept extends Fixed2ArgFunction {
    private final LinearRegressionFunction func = new LinearRegressionFunction(LinearRegressionFunction.FUNCTION.INTERCEPT);

    public ValueEval evaluate(int i, int i2, ValueEval valueEval, ValueEval valueEval2) {
        return this.func.evaluate(i, i2, valueEval, valueEval2);
    }
}
