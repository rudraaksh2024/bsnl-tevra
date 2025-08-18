package org.apache.poi.ss.formula.functions;

import org.apache.poi.ss.formula.eval.ErrorEval;
import org.apache.poi.ss.formula.eval.EvaluationException;
import org.apache.poi.ss.formula.eval.OperandResolver;
import org.apache.poi.ss.formula.eval.StringEval;
import org.apache.poi.ss.formula.eval.ValueEval;

public class Rept extends Fixed2ArgFunction {
    public ValueEval evaluate(int i, int i2, ValueEval valueEval, ValueEval valueEval2) {
        try {
            String coerceValueToString = OperandResolver.coerceValueToString(OperandResolver.getSingleValue(valueEval, i, i2));
            try {
                int coerceValueToDouble = (int) OperandResolver.coerceValueToDouble(valueEval2);
                StringBuilder sb = new StringBuilder(coerceValueToString.length() * coerceValueToDouble);
                for (int i3 = 0; i3 < coerceValueToDouble; i3++) {
                    sb.append(coerceValueToString);
                }
                if (sb.toString().length() > 32767) {
                    return ErrorEval.VALUE_INVALID;
                }
                return new StringEval(sb.toString());
            } catch (EvaluationException unused) {
                return ErrorEval.VALUE_INVALID;
            }
        } catch (EvaluationException e) {
            return e.getErrorEval();
        }
    }
}
