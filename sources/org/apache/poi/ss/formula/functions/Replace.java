package org.apache.poi.ss.formula.functions;

import org.apache.poi.ss.formula.eval.ErrorEval;
import org.apache.poi.ss.formula.eval.EvaluationException;
import org.apache.poi.ss.formula.eval.StringEval;
import org.apache.poi.ss.formula.eval.ValueEval;

public final class Replace extends Fixed4ArgFunction {
    public ValueEval evaluate(int i, int i2, ValueEval valueEval, ValueEval valueEval2, ValueEval valueEval3, ValueEval valueEval4) {
        try {
            String evaluateStringArg = TextFunction.evaluateStringArg(valueEval, i, i2);
            int evaluateIntArg = TextFunction.evaluateIntArg(valueEval2, i, i2);
            int evaluateIntArg2 = TextFunction.evaluateIntArg(valueEval3, i, i2);
            String evaluateStringArg2 = TextFunction.evaluateStringArg(valueEval4, i, i2);
            if (evaluateIntArg < 1 || evaluateIntArg2 < 0) {
                return ErrorEval.VALUE_INVALID;
            }
            StringBuilder sb = new StringBuilder(evaluateStringArg);
            if (evaluateIntArg <= evaluateStringArg.length() && evaluateIntArg2 != 0) {
                int i3 = evaluateIntArg - 1;
                sb.delete(i3, evaluateIntArg2 + i3);
            }
            if (evaluateIntArg > sb.length()) {
                sb.append(evaluateStringArg2);
            } else {
                sb.insert(evaluateIntArg - 1, evaluateStringArg2);
            }
            return new StringEval(sb.toString());
        } catch (EvaluationException e) {
            return e.getErrorEval();
        }
    }
}
