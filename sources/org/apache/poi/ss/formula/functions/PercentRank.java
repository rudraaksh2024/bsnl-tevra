package org.apache.poi.ss.formula.functions;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.apache.poi.ss.formula.eval.AreaEval;
import org.apache.poi.ss.formula.eval.BlankEval;
import org.apache.poi.ss.formula.eval.ErrorEval;
import org.apache.poi.ss.formula.eval.EvaluationException;
import org.apache.poi.ss.formula.eval.MissingArgEval;
import org.apache.poi.ss.formula.eval.NumberEval;
import org.apache.poi.ss.formula.eval.OperandResolver;
import org.apache.poi.ss.formula.eval.ValueEval;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.util.Internal;

public final class PercentRank implements Function {
    public static final Function instance = new PercentRank();

    private PercentRank() {
    }

    public ValueEval evaluate(ValueEval[] valueEvalArr, int i, int i2) {
        int i3;
        if (valueEvalArr.length < 2) {
            return ErrorEval.VALUE_INVALID;
        }
        try {
            double coerceValueToDouble = OperandResolver.coerceValueToDouble(OperandResolver.getSingleValue(valueEvalArr[1], i, i2));
            ArrayList arrayList = new ArrayList();
            try {
                for (ValueEval next : getValues(valueEvalArr[0], i, i2)) {
                    if (!(next instanceof BlankEval)) {
                        if (!(next instanceof MissingArgEval)) {
                            arrayList.add(Double.valueOf(OperandResolver.coerceValueToDouble(next)));
                        }
                    }
                }
                if (arrayList.isEmpty()) {
                    return ErrorEval.NUM_ERROR;
                }
                if (valueEvalArr.length > 2) {
                    try {
                        i3 = OperandResolver.coerceValueToInt(OperandResolver.getSingleValue(valueEvalArr[2], i, i2));
                        if (i3 < 1) {
                            return ErrorEval.NUM_ERROR;
                        }
                    } catch (EvaluationException e) {
                        return e.getErrorEval();
                    }
                } else {
                    i3 = 3;
                }
                return calculateRank(arrayList, coerceValueToDouble, i3, true);
            } catch (EvaluationException e2) {
                ErrorEval errorEval = e2.getErrorEval();
                if (errorEval != ErrorEval.NA) {
                    return errorEval;
                }
                return ErrorEval.NUM_ERROR;
            }
        } catch (EvaluationException e3) {
            ErrorEval errorEval2 = e3.getErrorEval();
            if (errorEval2 == ErrorEval.NUM_ERROR) {
                return errorEval2;
            }
            return ErrorEval.NUM_ERROR;
        }
    }

    private ValueEval calculateRank(List<Double> list, double d, int i, boolean z) {
        int i2 = i;
        double d2 = Double.MIN_VALUE;
        double d3 = Double.MAX_VALUE;
        if (z) {
            for (Double next : list) {
                if (next.doubleValue() <= d && next.doubleValue() > d2) {
                    d2 = next.doubleValue();
                }
                if (next.doubleValue() > d && next.doubleValue() < d3) {
                    d3 = next.doubleValue();
                }
            }
        }
        double d4 = d3;
        double d5 = d2;
        if (!z || d5 == d || d4 == d) {
            int i3 = 0;
            int i4 = 0;
            for (Double next2 : list) {
                if (next2.doubleValue() < d) {
                    i4++;
                } else if (next2.doubleValue() > d) {
                    i3++;
                }
            }
            if (i3 == list.size() || i4 == list.size()) {
                return ErrorEval.NA;
            }
            int i5 = i3 + i4;
            if (i5 == 0) {
                return new NumberEval(0.0d);
            }
            return new NumberEval(round(BigDecimal.valueOf(((double) i4) / ((double) i5)), i2));
        }
        int i6 = i2 < 5 ? 8 : i2 + 3;
        ValueEval calculateRank = calculateRank(list, d5, i6, false);
        if (!(calculateRank instanceof NumberEval)) {
            return calculateRank;
        }
        ValueEval calculateRank2 = calculateRank(list, d4, i6, false);
        if (!(calculateRank2 instanceof NumberEval)) {
            return calculateRank2;
        }
        return interpolate(d, d5, d4, (NumberEval) calculateRank, (NumberEval) calculateRank2, i);
    }

    @Internal
    public static NumberEval interpolate(double d, double d2, double d3, NumberEval numberEval, NumberEval numberEval2, int i) {
        double d4 = d3 - d2;
        double d5 = d - d2;
        return new NumberEval(round(BigDecimal.valueOf(numberEval.getNumberValue()).add(new BigDecimal(NumberToTextConverter.toText(numberEval2.getNumberValue() - numberEval.getNumberValue())).multiply(BigDecimal.valueOf(d5 / d4))), i));
    }

    @Internal
    public static double round(BigDecimal bigDecimal, int i) {
        return bigDecimal.setScale(i + 3, RoundingMode.HALF_UP).setScale(i, RoundingMode.DOWN).doubleValue();
    }

    @Internal
    public static List<ValueEval> getValues(ValueEval valueEval, int i, int i2) throws EvaluationException {
        if (!(valueEval instanceof AreaEval)) {
            return Collections.singletonList(OperandResolver.getSingleValue(valueEval, i, i2));
        }
        AreaEval areaEval = (AreaEval) valueEval;
        ArrayList arrayList = new ArrayList();
        for (int firstRow = areaEval.getFirstRow(); firstRow <= areaEval.getLastRow(); firstRow++) {
            for (int firstColumn = areaEval.getFirstColumn(); firstColumn <= areaEval.getLastColumn(); firstColumn++) {
                arrayList.add(OperandResolver.getSingleValue(areaEval.getAbsoluteValue(firstRow, firstColumn), firstRow, firstColumn));
            }
        }
        return arrayList;
    }
}
