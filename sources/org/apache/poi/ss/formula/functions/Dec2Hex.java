package org.apache.poi.ss.formula.functions;

import java.util.Locale;
import org.apache.poi.ss.formula.OperationEvaluationContext;
import org.apache.poi.ss.formula.eval.ErrorEval;
import org.apache.poi.ss.formula.eval.EvaluationException;
import org.apache.poi.ss.formula.eval.OperandResolver;
import org.apache.poi.ss.formula.eval.StringEval;
import org.apache.poi.ss.formula.eval.ValueEval;

public final class Dec2Hex extends Var1or2ArgFunction implements FreeRefFunction {
    private static final int DEFAULT_PLACES_VALUE = 10;
    private static final long MAX_VALUE = Long.parseLong("549755813887");
    private static final long MIN_VALUE = Long.parseLong("-549755813888");
    public static final FreeRefFunction instance = new Dec2Hex();

    public ValueEval evaluate(int i, int i2, ValueEval valueEval, ValueEval valueEval2) {
        int i3;
        String str;
        try {
            Double parseDouble = OperandResolver.parseDouble(OperandResolver.coerceValueToString(OperandResolver.getSingleValue(valueEval, i, i2)));
            if (parseDouble == null) {
                return ErrorEval.VALUE_INVALID;
            }
            if (parseDouble.longValue() < MIN_VALUE || parseDouble.longValue() > MAX_VALUE) {
                return ErrorEval.NUM_ERROR;
            }
            if (parseDouble.doubleValue() < 0.0d) {
                i3 = 10;
            } else if (valueEval2 != null) {
                try {
                    Double parseDouble2 = OperandResolver.parseDouble(OperandResolver.coerceValueToString(OperandResolver.getSingleValue(valueEval2, i, i2)));
                    if (parseDouble2 == null) {
                        return ErrorEval.VALUE_INVALID;
                    }
                    i3 = parseDouble2.intValue();
                    if (i3 < 0) {
                        return ErrorEval.NUM_ERROR;
                    }
                } catch (EvaluationException e) {
                    return e.getErrorEval();
                }
            } else {
                i3 = 0;
            }
            if (i3 != 0) {
                str = String.format(Locale.ROOT, "%0" + i3 + "X", new Object[]{Integer.valueOf(parseDouble.intValue())});
            } else {
                str = Long.toHexString(parseDouble.longValue());
            }
            if (parseDouble.doubleValue() < 0.0d) {
                str = "FF" + str.substring(2);
            }
            return new StringEval(str.toUpperCase(Locale.ROOT));
        } catch (EvaluationException e2) {
            return e2.getErrorEval();
        }
    }

    public ValueEval evaluate(int i, int i2, ValueEval valueEval) {
        return evaluate(i, i2, valueEval, (ValueEval) null);
    }

    public ValueEval evaluate(ValueEval[] valueEvalArr, OperationEvaluationContext operationEvaluationContext) {
        if (valueEvalArr.length == 1) {
            return evaluate(operationEvaluationContext.getRowIndex(), operationEvaluationContext.getColumnIndex(), valueEvalArr[0]);
        }
        if (valueEvalArr.length == 2) {
            return evaluate(operationEvaluationContext.getRowIndex(), operationEvaluationContext.getColumnIndex(), valueEvalArr[0], valueEvalArr[1]);
        }
        return ErrorEval.VALUE_INVALID;
    }
}
