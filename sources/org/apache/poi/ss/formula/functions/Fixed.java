package org.apache.poi.ss.formula.functions;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;
import org.apache.poi.ss.formula.eval.BoolEval;
import org.apache.poi.ss.formula.eval.ErrorEval;
import org.apache.poi.ss.formula.eval.EvaluationException;
import org.apache.poi.ss.formula.eval.NumberEval;
import org.apache.poi.ss.formula.eval.OperandResolver;
import org.apache.poi.ss.formula.eval.StringEval;
import org.apache.poi.ss.formula.eval.ValueEval;

public final class Fixed implements Function1Arg, Function2Arg, Function3Arg {
    public ValueEval evaluate(int i, int i2, ValueEval valueEval, ValueEval valueEval2, ValueEval valueEval3) {
        return fixed(valueEval, valueEval2, valueEval3, i, i2);
    }

    public ValueEval evaluate(int i, int i2, ValueEval valueEval, ValueEval valueEval2) {
        return fixed(valueEval, valueEval2, BoolEval.FALSE, i, i2);
    }

    public ValueEval evaluate(int i, int i2, ValueEval valueEval) {
        return fixed(valueEval, new NumberEval(2.0d), BoolEval.FALSE, i, i2);
    }

    public ValueEval evaluate(ValueEval[] valueEvalArr, int i, int i2) {
        int length = valueEvalArr.length;
        if (length == 1) {
            return fixed(valueEvalArr[0], new NumberEval(2.0d), BoolEval.FALSE, i, i2);
        } else if (length == 2) {
            return fixed(valueEvalArr[0], valueEvalArr[1], BoolEval.FALSE, i, i2);
        } else if (length != 3) {
            return ErrorEval.VALUE_INVALID;
        } else {
            return fixed(valueEvalArr[0], valueEvalArr[1], valueEvalArr[2], i, i2);
        }
    }

    private ValueEval fixed(ValueEval valueEval, ValueEval valueEval2, ValueEval valueEval3, int i, int i2) {
        boolean z;
        try {
            BigDecimal valueOf = BigDecimal.valueOf(OperandResolver.coerceValueToDouble(OperandResolver.getSingleValue(valueEval, i, i2)));
            int coerceValueToInt = OperandResolver.coerceValueToInt(OperandResolver.getSingleValue(valueEval2, i, i2));
            Boolean coerceValueToBoolean = OperandResolver.coerceValueToBoolean(OperandResolver.getSingleValue(valueEval3, i, i2), false);
            BigDecimal scale = valueOf.setScale(coerceValueToInt, RoundingMode.HALF_UP);
            DecimalFormat decimalFormat = (DecimalFormat) NumberFormat.getNumberInstance(Locale.US);
            if (coerceValueToBoolean != null) {
                if (coerceValueToBoolean.booleanValue()) {
                    z = false;
                    decimalFormat.setGroupingUsed(z);
                    decimalFormat.setMinimumFractionDigits(Math.max(coerceValueToInt, 0));
                    decimalFormat.setMaximumFractionDigits(Math.max(coerceValueToInt, 0));
                    return new StringEval(decimalFormat.format(scale.doubleValue()));
                }
            }
            z = true;
            decimalFormat.setGroupingUsed(z);
            decimalFormat.setMinimumFractionDigits(Math.max(coerceValueToInt, 0));
            decimalFormat.setMaximumFractionDigits(Math.max(coerceValueToInt, 0));
            return new StringEval(decimalFormat.format(scale.doubleValue()));
        } catch (EvaluationException e) {
            return e.getErrorEval();
        }
    }
}
