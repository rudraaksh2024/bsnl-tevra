package org.apache.poi.ss.formula.functions;

import org.apache.poi.ss.formula.eval.AreaEval;
import org.apache.poi.ss.formula.eval.ErrorEval;
import org.apache.poi.ss.formula.eval.NumberEval;
import org.apache.poi.ss.formula.eval.RefEval;
import org.apache.poi.ss.formula.eval.ValueEval;

public final class Column {
    public static ValueEval evaluate(ValueEval[] valueEvalArr, int i, int i2) {
        if (valueEvalArr.length > 1) {
            return ErrorEval.VALUE_INVALID;
        }
        if (valueEvalArr.length != 0) {
            AreaEval areaEval = valueEvalArr[0];
            if (areaEval instanceof AreaEval) {
                i2 = areaEval.getFirstColumn();
            } else if (!(areaEval instanceof RefEval)) {
                return ErrorEval.VALUE_INVALID;
            } else {
                i2 = ((RefEval) areaEval).getColumn();
            }
        }
        return new NumberEval(((double) i2) + 1.0d);
    }
}
