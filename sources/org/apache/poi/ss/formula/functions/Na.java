package org.apache.poi.ss.formula.functions;

import org.apache.poi.ss.formula.eval.ErrorEval;
import org.apache.poi.ss.formula.eval.ValueEval;

public final class Na {
    public static ValueEval evaluate(ValueEval[] valueEvalArr, int i, int i2) {
        return valueEvalArr.length != 0 ? ErrorEval.VALUE_INVALID : ErrorEval.NA;
    }
}
