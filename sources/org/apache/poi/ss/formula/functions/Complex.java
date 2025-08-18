package org.apache.poi.ss.formula.functions;

import java.util.Locale;
import org.apache.poi.ss.formula.OperationEvaluationContext;
import org.apache.poi.ss.formula.eval.ErrorEval;
import org.apache.poi.ss.formula.eval.EvaluationException;
import org.apache.poi.ss.formula.eval.OperandResolver;
import org.apache.poi.ss.formula.eval.StringEval;
import org.apache.poi.ss.formula.eval.ValueEval;

public class Complex extends Var2or3ArgFunction implements FreeRefFunction {
    public static final String DEFAULT_SUFFIX = "i";
    public static final String SUPPORTED_SUFFIX = "j";
    public static final FreeRefFunction instance = new Complex();

    public ValueEval evaluate(int i, int i2, ValueEval valueEval, ValueEval valueEval2) {
        return evaluate(i, i2, valueEval, valueEval2, new StringEval(DEFAULT_SUFFIX));
    }

    public ValueEval evaluate(int i, int i2, ValueEval valueEval, ValueEval valueEval2, ValueEval valueEval3) {
        try {
            try {
                double coerceValueToDouble = OperandResolver.coerceValueToDouble(OperandResolver.getSingleValue(valueEval, i, i2));
                try {
                    try {
                        double coerceValueToDouble2 = OperandResolver.coerceValueToDouble(OperandResolver.getSingleValue(valueEval2, i, i2));
                        String coerceValueToString = OperandResolver.coerceValueToString(valueEval3);
                        if (coerceValueToString.length() == 0) {
                            coerceValueToString = DEFAULT_SUFFIX;
                        }
                        if (coerceValueToString.equals(DEFAULT_SUFFIX.toUpperCase(Locale.ROOT)) || coerceValueToString.equals(SUPPORTED_SUFFIX.toUpperCase(Locale.ROOT))) {
                            return ErrorEval.VALUE_INVALID;
                        }
                        if (!coerceValueToString.equals(DEFAULT_SUFFIX) && !coerceValueToString.equals(SUPPORTED_SUFFIX)) {
                            return ErrorEval.VALUE_INVALID;
                        }
                        StringBuilder sb = new StringBuilder();
                        if (coerceValueToDouble != 0.0d) {
                            if (isDoubleAnInt(coerceValueToDouble)) {
                                sb.append((int) coerceValueToDouble);
                            } else {
                                sb.append(coerceValueToDouble);
                            }
                        }
                        int i3 = (coerceValueToDouble2 > 0.0d ? 1 : (coerceValueToDouble2 == 0.0d ? 0 : -1));
                        if (i3 != 0) {
                            if (sb.length() != 0 && i3 > 0) {
                                sb.append("+");
                            }
                            if (!(coerceValueToDouble2 == 1.0d || coerceValueToDouble2 == -1.0d)) {
                                if (isDoubleAnInt(coerceValueToDouble2)) {
                                    sb.append((int) coerceValueToDouble2);
                                } else {
                                    sb.append(coerceValueToDouble2);
                                }
                            }
                            sb.append(coerceValueToString);
                        }
                        return new StringEval(sb.toString());
                    } catch (EvaluationException unused) {
                        return ErrorEval.VALUE_INVALID;
                    }
                } catch (EvaluationException e) {
                    return e.getErrorEval();
                }
            } catch (EvaluationException unused2) {
                return ErrorEval.VALUE_INVALID;
            }
        } catch (EvaluationException e2) {
            return e2.getErrorEval();
        }
    }

    private boolean isDoubleAnInt(double d) {
        return d == Math.floor(d) && !Double.isInfinite(d);
    }

    public ValueEval evaluate(ValueEval[] valueEvalArr, OperationEvaluationContext operationEvaluationContext) {
        if (valueEvalArr.length == 2) {
            return evaluate(operationEvaluationContext.getRowIndex(), operationEvaluationContext.getColumnIndex(), valueEvalArr[0], valueEvalArr[1]);
        }
        if (valueEvalArr.length != 3) {
            return ErrorEval.VALUE_INVALID;
        }
        return evaluate(operationEvaluationContext.getRowIndex(), operationEvaluationContext.getColumnIndex(), valueEvalArr[0], valueEvalArr[1], valueEvalArr[2]);
    }
}
