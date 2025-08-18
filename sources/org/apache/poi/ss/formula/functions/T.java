package org.apache.poi.ss.formula.functions;

import org.apache.poi.ss.formula.eval.AreaEval;
import org.apache.poi.ss.formula.eval.ErrorEval;
import org.apache.poi.ss.formula.eval.RefEval;
import org.apache.poi.ss.formula.eval.StringEval;
import org.apache.poi.ss.formula.eval.ValueEval;

public final class T extends Fixed1ArgFunction {
    public ValueEval evaluate(int i, int i2, ValueEval valueEval) {
        if (valueEval instanceof RefEval) {
            RefEval refEval = (RefEval) valueEval;
            valueEval = refEval.getInnerValueEval(refEval.getFirstSheetIndex());
        } else if (valueEval instanceof AreaEval) {
            valueEval = ((AreaEval) valueEval).getRelativeValue(0, 0);
        }
        if (!(valueEval instanceof StringEval) && !(valueEval instanceof ErrorEval)) {
            return StringEval.EMPTY_INSTANCE;
        }
        return valueEval;
    }
}
