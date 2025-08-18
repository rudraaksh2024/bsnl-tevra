package org.apache.poi.ss.formula.functions;

import java.util.Arrays;
import org.apache.poi.ss.formula.TwoDEval;
import org.apache.poi.ss.formula.eval.AreaEval;
import org.apache.poi.ss.formula.eval.BlankEval;
import org.apache.poi.ss.formula.eval.ErrorEval;
import org.apache.poi.ss.formula.eval.EvaluationException;
import org.apache.poi.ss.formula.eval.NumberEval;
import org.apache.poi.ss.formula.eval.NumericValueEval;
import org.apache.poi.ss.formula.eval.RefEval;
import org.apache.poi.ss.formula.eval.StringEval;
import org.apache.poi.ss.formula.eval.ValueEval;

public final class Sumproduct implements Function {
    public ValueEval evaluate(ValueEval[] valueEvalArr, int i, int i2) {
        if (valueEvalArr.length < 1) {
            return ErrorEval.VALUE_INVALID;
        }
        TwoDEval twoDEval = valueEvalArr[0];
        try {
            if (twoDEval instanceof NumericValueEval) {
                return evaluateSingleProduct(valueEvalArr);
            }
            if (twoDEval instanceof RefEval) {
                return evaluateSingleProduct(valueEvalArr);
            }
            if (twoDEval instanceof TwoDEval) {
                TwoDEval twoDEval2 = twoDEval;
                if (!twoDEval2.isRow() || !twoDEval2.isColumn()) {
                    return evaluateAreaSumProduct(valueEvalArr);
                }
                return evaluateSingleProduct(valueEvalArr);
            }
            throw new RuntimeException("Invalid arg type for SUMPRODUCT: (" + twoDEval.getClass().getName() + ")");
        } catch (EvaluationException e) {
            return e.getErrorEval();
        }
    }

    private static ValueEval evaluateSingleProduct(ValueEval[] valueEvalArr) throws EvaluationException {
        int length = valueEvalArr.length;
        double d = 1.0d;
        for (ValueEval scalarValue : valueEvalArr) {
            d *= getScalarValue(scalarValue);
        }
        return new NumberEval(d);
    }

    private static double getScalarValue(ValueEval valueEval) throws EvaluationException {
        if (valueEval instanceof RefEval) {
            RefEval refEval = (RefEval) valueEval;
            if (refEval.getNumberOfSheets() <= 1) {
                valueEval = refEval.getInnerValueEval(refEval.getFirstSheetIndex());
            } else {
                throw new EvaluationException(ErrorEval.VALUE_INVALID);
            }
        }
        if (valueEval != null) {
            if (valueEval instanceof AreaEval) {
                AreaEval areaEval = (AreaEval) valueEval;
                if (!areaEval.isColumn() || !areaEval.isRow()) {
                    throw new EvaluationException(ErrorEval.VALUE_INVALID);
                }
                valueEval = areaEval.getRelativeValue(0, 0);
            }
            return getProductTerm(valueEval, true);
        }
        throw new RuntimeException("parameter may not be null");
    }

    private static ValueEval evaluateAreaSumProduct(ValueEval[] valueEvalArr) throws EvaluationException {
        int length = valueEvalArr.length;
        try {
            TwoDEval[] twoDEvalArr = (TwoDEval[]) Arrays.copyOf(valueEvalArr, length, TwoDEval[].class);
            TwoDEval twoDEval = twoDEvalArr[0];
            int height = twoDEval.getHeight();
            int width = twoDEval.getWidth();
            if (!areasAllSameSize(twoDEvalArr, height, width)) {
                for (int i = 1; i < twoDEvalArr.length; i++) {
                    throwFirstError(twoDEvalArr[i]);
                }
                return ErrorEval.VALUE_INVALID;
            }
            double d = 0.0d;
            for (int i2 = 0; i2 < height; i2++) {
                for (int i3 = 0; i3 < width; i3++) {
                    double d2 = 1.0d;
                    for (int i4 = 0; i4 < length; i4++) {
                        d2 *= getProductTerm(twoDEvalArr[i4].getValue(i2, i3), false);
                    }
                    d += d2;
                }
            }
            return new NumberEval(d);
        } catch (ArrayStoreException unused) {
            return ErrorEval.VALUE_INVALID;
        }
    }

    private static void throwFirstError(TwoDEval twoDEval) throws EvaluationException {
        int height = twoDEval.getHeight();
        int width = twoDEval.getWidth();
        for (int i = 0; i < height; i++) {
            int i2 = 0;
            while (i2 < width) {
                ValueEval value = twoDEval.getValue(i, i2);
                if (!(value instanceof ErrorEval)) {
                    i2++;
                } else {
                    throw new EvaluationException((ErrorEval) value);
                }
            }
        }
    }

    private static boolean areasAllSameSize(TwoDEval[] twoDEvalArr, int i, int i2) {
        for (TwoDEval twoDEval : twoDEvalArr) {
            if (twoDEval.getHeight() != i || twoDEval.getWidth() != i2) {
                return false;
            }
        }
        return true;
    }

    private static double getProductTerm(ValueEval valueEval, boolean z) throws EvaluationException {
        if ((valueEval instanceof BlankEval) || valueEval == null) {
            if (!z) {
                return 0.0d;
            }
            throw new EvaluationException(ErrorEval.VALUE_INVALID);
        } else if (valueEval instanceof ErrorEval) {
            throw new EvaluationException((ErrorEval) valueEval);
        } else if (valueEval instanceof StringEval) {
            if (!z) {
                return 0.0d;
            }
            throw new EvaluationException(ErrorEval.VALUE_INVALID);
        } else if (valueEval instanceof NumericValueEval) {
            return ((NumericValueEval) valueEval).getNumberValue();
        } else {
            throw new RuntimeException("Unexpected value eval class (" + valueEval.getClass().getName() + ")");
        }
    }
}
