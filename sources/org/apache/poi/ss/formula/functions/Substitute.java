package org.apache.poi.ss.formula.functions;

import org.apache.poi.ss.formula.eval.ErrorEval;
import org.apache.poi.ss.formula.eval.EvaluationException;
import org.apache.poi.ss.formula.eval.StringEval;
import org.apache.poi.ss.formula.eval.ValueEval;

public final class Substitute extends Var3or4ArgFunction {
    public ValueEval evaluate(int i, int i2, ValueEval valueEval, ValueEval valueEval2, ValueEval valueEval3) {
        try {
            return new StringEval(replaceAllOccurrences(TextFunction.evaluateStringArg(valueEval, i, i2), TextFunction.evaluateStringArg(valueEval2, i, i2), TextFunction.evaluateStringArg(valueEval3, i, i2)));
        } catch (EvaluationException e) {
            return e.getErrorEval();
        }
    }

    public ValueEval evaluate(int i, int i2, ValueEval valueEval, ValueEval valueEval2, ValueEval valueEval3, ValueEval valueEval4) {
        try {
            String evaluateStringArg = TextFunction.evaluateStringArg(valueEval, i, i2);
            String evaluateStringArg2 = TextFunction.evaluateStringArg(valueEval2, i, i2);
            String evaluateStringArg3 = TextFunction.evaluateStringArg(valueEval3, i, i2);
            int evaluateIntArg = TextFunction.evaluateIntArg(valueEval4, i, i2);
            if (evaluateIntArg < 1) {
                return ErrorEval.VALUE_INVALID;
            }
            return new StringEval(replaceOneOccurrence(evaluateStringArg, evaluateStringArg2, evaluateStringArg3, evaluateIntArg));
        } catch (EvaluationException e) {
            return e.getErrorEval();
        }
    }

    private static String replaceAllOccurrences(String str, String str2, String str3) {
        if (str2.length() < 1) {
            return str;
        }
        StringBuilder sb = new StringBuilder();
        int i = 0;
        while (true) {
            int indexOf = str.indexOf(str2, i);
            if (indexOf < 0) {
                sb.append(str.substring(i));
                return sb.toString();
            }
            sb.append(str, i, indexOf);
            sb.append(str3);
            i = str2.length() + indexOf;
        }
    }

    private static String replaceOneOccurrence(String str, String str2, String str3, int i) {
        if (str2.length() < 1) {
            return str;
        }
        int i2 = 0;
        int i3 = 0;
        while (true) {
            int indexOf = str.indexOf(str2, i2);
            if (indexOf < 0) {
                return str;
            }
            i3++;
            if (i3 == i) {
                return str.substring(0, indexOf) + str3 + str.substring(indexOf + str2.length());
            }
            i2 = indexOf + str2.length();
        }
    }
}
