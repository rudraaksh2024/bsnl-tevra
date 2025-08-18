package org.apache.poi.ss.formula.functions;

import java.util.Iterator;
import org.apache.poi.ss.formula.eval.AreaEval;
import org.apache.poi.ss.formula.eval.ErrorEval;
import org.apache.poi.ss.formula.eval.EvaluationException;
import org.apache.poi.ss.formula.eval.NumberEval;
import org.apache.poi.ss.formula.eval.OperandResolver;
import org.apache.poi.ss.formula.eval.RefEval;
import org.apache.poi.ss.formula.eval.RefListEval;
import org.apache.poi.ss.formula.eval.ValueEval;

public class Rank extends Var2or3ArgFunction {
    public ValueEval evaluate(int i, int i2, ValueEval valueEval, ValueEval valueEval2) {
        try {
            double coerceValueToDouble = OperandResolver.coerceValueToDouble(OperandResolver.getSingleValue(valueEval, i, i2));
            if (Double.isNaN(coerceValueToDouble) || Double.isInfinite(coerceValueToDouble)) {
                throw new EvaluationException(ErrorEval.NUM_ERROR);
            } else if (valueEval2 instanceof RefListEval) {
                return eval(coerceValueToDouble, (RefListEval) valueEval2, true);
            } else {
                return eval(coerceValueToDouble, convertRangeArg(valueEval2), true);
            }
        } catch (EvaluationException e) {
            return e.getErrorEval();
        }
    }

    public ValueEval evaluate(int i, int i2, ValueEval valueEval, ValueEval valueEval2, ValueEval valueEval3) {
        try {
            double coerceValueToDouble = OperandResolver.coerceValueToDouble(OperandResolver.getSingleValue(valueEval, i, i2));
            if (Double.isNaN(coerceValueToDouble) || Double.isInfinite(coerceValueToDouble)) {
                throw new EvaluationException(ErrorEval.NUM_ERROR);
            }
            int coerceValueToInt = OperandResolver.coerceValueToInt(OperandResolver.getSingleValue(valueEval3, i, i2));
            boolean z = true;
            if (coerceValueToInt != 0) {
                if (coerceValueToInt == 1) {
                    z = false;
                } else {
                    throw new EvaluationException(ErrorEval.NUM_ERROR);
                }
            }
            if (valueEval2 instanceof RefListEval) {
                return eval(coerceValueToDouble, (RefListEval) valueEval2, z);
            }
            return eval(coerceValueToDouble, convertRangeArg(valueEval2), z);
        } catch (EvaluationException e) {
            return e.getErrorEval();
        }
    }

    private static ValueEval eval(double d, AreaEval areaEval, boolean z) {
        int height = areaEval.getHeight();
        int width = areaEval.getWidth();
        int i = 1;
        for (int i2 = 0; i2 < height; i2++) {
            for (int i3 = 0; i3 < width; i3++) {
                Double value = getValue(areaEval, i2, i3);
                if (value != null && ((z && value.doubleValue() > d) || (!z && value.doubleValue() < d))) {
                    i++;
                }
            }
        }
        return new NumberEval((double) i);
    }

    private static ValueEval eval(double d, RefListEval refListEval, boolean z) {
        Iterator<ValueEval> it = refListEval.getList().iterator();
        int i = 1;
        while (it.hasNext()) {
            ValueEval next = it.next();
            if (next instanceof RefEval) {
                RefEval refEval = (RefEval) next;
                next = refEval.getInnerValueEval(refEval.getFirstSheetIndex());
            }
            if (next instanceof NumberEval) {
                double numberValue = ((NumberEval) next).getNumberValue();
                if ((z && numberValue > d) || (!z && numberValue < d)) {
                    i++;
                }
            }
        }
        return new NumberEval((double) i);
    }

    private static Double getValue(AreaEval areaEval, int i, int i2) {
        ValueEval relativeValue = areaEval.getRelativeValue(i, i2);
        if (relativeValue instanceof NumberEval) {
            return Double.valueOf(((NumberEval) relativeValue).getNumberValue());
        }
        return null;
    }

    private static AreaEval convertRangeArg(ValueEval valueEval) throws EvaluationException {
        if (valueEval instanceof AreaEval) {
            return (AreaEval) valueEval;
        }
        if (valueEval instanceof RefEval) {
            return ((RefEval) valueEval).offset(0, 0, 0, 0);
        }
        throw new EvaluationException(ErrorEval.VALUE_INVALID);
    }
}
