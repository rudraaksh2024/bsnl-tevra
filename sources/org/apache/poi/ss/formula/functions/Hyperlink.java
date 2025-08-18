package org.apache.poi.ss.formula.functions;

import org.apache.poi.ss.formula.eval.ValueEval;

public final class Hyperlink extends Var1or2ArgFunction {
    public ValueEval evaluate(int i, int i2, ValueEval valueEval) {
        return valueEval;
    }

    public ValueEval evaluate(int i, int i2, ValueEval valueEval, ValueEval valueEval2) {
        return valueEval2;
    }
}
