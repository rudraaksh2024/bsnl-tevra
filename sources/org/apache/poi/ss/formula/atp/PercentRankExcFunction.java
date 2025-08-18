package org.apache.poi.ss.formula.atp;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import org.apache.poi.ss.formula.OperationEvaluationContext;
import org.apache.poi.ss.formula.eval.BlankEval;
import org.apache.poi.ss.formula.eval.ErrorEval;
import org.apache.poi.ss.formula.eval.EvaluationException;
import org.apache.poi.ss.formula.eval.MissingArgEval;
import org.apache.poi.ss.formula.eval.NumberEval;
import org.apache.poi.ss.formula.eval.OperandResolver;
import org.apache.poi.ss.formula.eval.ValueEval;
import org.apache.poi.ss.formula.functions.FreeRefFunction;
import org.apache.poi.ss.formula.functions.PercentRank;

final class PercentRankExcFunction implements FreeRefFunction {
    public static final FreeRefFunction instance = new PercentRankExcFunction(ArgumentsEvaluator.instance);
    private ArgumentsEvaluator evaluator;

    private PercentRankExcFunction(ArgumentsEvaluator argumentsEvaluator) {
        this.evaluator = argumentsEvaluator;
    }

    public ValueEval evaluate(ValueEval[] valueEvalArr, OperationEvaluationContext operationEvaluationContext) {
        return evaluate(valueEvalArr, operationEvaluationContext.getRowIndex(), operationEvaluationContext.getColumnIndex());
    }

    private ValueEval evaluate(ValueEval[] valueEvalArr, int i, int i2) {
        int i3;
        if (valueEvalArr.length < 2) {
            return ErrorEval.VALUE_INVALID;
        }
        try {
            double coerceValueToDouble = OperandResolver.coerceValueToDouble(OperandResolver.getSingleValue(valueEvalArr[1], i, i2));
            ArrayList arrayList = new ArrayList();
            try {
                for (ValueEval next : PercentRank.getValues(valueEvalArr[0], i, i2)) {
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
        double d2;
        double d3;
        int i2 = i;
        double d4 = Double.MIN_VALUE;
        if (z) {
            double d5 = Double.MAX_VALUE;
            double d6 = Double.MAX_VALUE;
            double d7 = Double.MIN_VALUE;
            for (Double next : list) {
                if (next.doubleValue() <= d && next.doubleValue() > d7) {
                    d7 = next.doubleValue();
                }
                if (next.doubleValue() > d && next.doubleValue() < d6) {
                    d6 = next.doubleValue();
                }
                if (next.doubleValue() < d5) {
                    d5 = next.doubleValue();
                }
                if (next.doubleValue() > d4) {
                    d4 = next.doubleValue();
                }
            }
            if (d < d5 || d > d4) {
                return ErrorEval.NA;
            }
            d2 = d6;
            d3 = d7;
        } else {
            d3 = Double.MIN_VALUE;
            d2 = Double.MAX_VALUE;
        }
        if (!z || d3 == d || d2 == d) {
            int i3 = 0;
            for (Double doubleValue : list) {
                if (doubleValue.doubleValue() < d) {
                    i3++;
                }
            }
            return new NumberEval(PercentRank.round(BigDecimal.valueOf(((double) (i3 + 1)) / ((double) (list.size() + 1))), i2));
        }
        int i4 = i2 < 5 ? 8 : i2 + 3;
        ValueEval calculateRank = calculateRank(list, d3, i4, false);
        if (!(calculateRank instanceof NumberEval)) {
            return calculateRank;
        }
        ValueEval calculateRank2 = calculateRank(list, d2, i4, false);
        if (!(calculateRank2 instanceof NumberEval)) {
            return calculateRank2;
        }
        return PercentRank.interpolate(d, d3, d2, (NumberEval) calculateRank, (NumberEval) calculateRank2, i);
    }
}
