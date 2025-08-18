package org.apache.poi.ss.formula.functions;

import org.apache.poi.ss.formula.eval.AreaEval;
import org.apache.poi.ss.formula.eval.ErrorEval;
import org.apache.poi.ss.formula.eval.NumberEval;
import org.apache.poi.ss.formula.eval.RefEval;
import org.apache.poi.ss.formula.eval.ValueEval;

public final class RowFunc {
    public static ValueEval evaluate(ValueEval[] valueEvalArr, int i, int i2) {
        if (valueEvalArr.length > 1) {
            return ErrorEval.VALUE_INVALID;
        }
        if (valueEvalArr.length != 0) {
            AreaEval areaEval = valueEvalArr[0];
            if (areaEval instanceof AreaEval) {
                i = areaEval.getFirstRow();
            } else if (!(areaEval instanceof RefEval)) {
                return ErrorEval.VALUE_INVALID;
            } else {
                i = ((RefEval) areaEval).getRow();
            }
        }
        return new NumberEval(((double) i) + 1.0d);
    }
}
