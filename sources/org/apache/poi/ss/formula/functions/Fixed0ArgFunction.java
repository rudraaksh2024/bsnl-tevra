package org.apache.poi.ss.formula.functions;

import org.apache.poi.ss.formula.eval.ErrorEval;
import org.apache.poi.ss.formula.eval.ValueEval;
import org.apache.poi.util.Removal;

@Deprecated
@Removal(version = "6.0.0")
public abstract class Fixed0ArgFunction implements Function0Arg {
    public final ValueEval evaluate(ValueEval[] valueEvalArr, int i, int i2) {
        if (valueEvalArr.length != 0) {
            return ErrorEval.VALUE_INVALID;
        }
        return evaluate(i, i2);
    }
}
