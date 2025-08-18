package org.apache.poi.ss.formula.functions;

import org.apache.poi.ss.formula.eval.ErrorEval;
import org.apache.poi.ss.formula.eval.NumberEval;
import org.apache.poi.ss.formula.eval.RefListEval;
import org.apache.poi.ss.formula.eval.ValueEval;
import org.apache.poi.ss.formula.ptg.NumberPtg;
import org.apache.poi.ss.formula.ptg.Ptg;

public final class Areas implements Function {
    public ValueEval evaluate(ValueEval[] valueEvalArr, int i, int i2) {
        if (valueEvalArr.length == 0) {
            return ErrorEval.VALUE_INVALID;
        }
        try {
            RefListEval refListEval = valueEvalArr[0];
            return new NumberEval((Ptg) new NumberPtg((double) (refListEval instanceof RefListEval ? refListEval.getList().size() : 1)));
        } catch (Exception unused) {
            return ErrorEval.VALUE_INVALID;
        }
    }
}
