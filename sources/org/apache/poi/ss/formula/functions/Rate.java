package org.apache.poi.ss.formula.functions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.formula.eval.ErrorEval;
import org.apache.poi.ss.formula.eval.EvaluationException;
import org.apache.poi.ss.formula.eval.NumberEval;
import org.apache.poi.ss.formula.eval.OperandResolver;
import org.apache.poi.ss.formula.eval.ValueEval;

public class Rate implements Function {
    private static final Logger LOG = LogManager.getLogger((Class<?>) Rate.class);

    public ValueEval evaluate(ValueEval[] valueEvalArr, int i, int i2) {
        ValueEval[] valueEvalArr2 = valueEvalArr;
        int i3 = i;
        int i4 = i2;
        if (valueEvalArr2.length < 3) {
            return ErrorEval.VALUE_INVALID;
        }
        try {
            ValueEval singleValue = OperandResolver.getSingleValue(valueEvalArr2[0], i3, i4);
            ValueEval singleValue2 = OperandResolver.getSingleValue(valueEvalArr2[1], i3, i4);
            ValueEval singleValue3 = OperandResolver.getSingleValue(valueEvalArr2[2], i3, i4);
            ValueEval valueEval = null;
            ValueEval singleValue4 = valueEvalArr2.length >= 4 ? OperandResolver.getSingleValue(valueEvalArr2[3], i3, i4) : null;
            ValueEval singleValue5 = valueEvalArr2.length >= 5 ? OperandResolver.getSingleValue(valueEvalArr2[4], i3, i4) : null;
            if (valueEvalArr2.length >= 6) {
                valueEval = OperandResolver.getSingleValue(valueEvalArr2[5], i3, i4);
            }
            double calculateRate = calculateRate(OperandResolver.coerceValueToDouble(singleValue), OperandResolver.coerceValueToDouble(singleValue2), OperandResolver.coerceValueToDouble(singleValue3), valueEvalArr2.length >= 4 ? OperandResolver.coerceValueToDouble(singleValue4) : 0.0d, valueEvalArr2.length >= 5 ? OperandResolver.coerceValueToDouble(singleValue5) : 0.0d, valueEvalArr2.length >= 6 ? OperandResolver.coerceValueToDouble(valueEval) : 0.1d);
            checkValue(calculateRate);
            return new NumberEval(calculateRate);
        } catch (EvaluationException e) {
            LOG.atError().withThrowable(e).log("Can't evaluate rate function");
            return e.getErrorEval();
        }
    }

    private double calculateRate(double d, double d2, double d3, double d4, double d5, double d6) {
        double d7;
        double d8;
        double d9 = 0.0d;
        if (Math.abs(d6) < 1.0E-7d) {
            d7 = 0.0d;
        } else {
            d7 = Math.exp(Math.log(d6 + 1.0d) * d);
        }
        double d10 = d6;
        double d11 = (d3 * d7) + (((1.0d / d6) + d5) * d2 * (d7 - 1.0d)) + d4;
        double d12 = d3 + (d2 * d) + d4;
        double d13 = 0.0d;
        while (Math.abs(d12 - d11) > 1.0E-7d && d9 < ((double) 20)) {
            double d14 = ((d13 * d11) - (d12 * d10)) / (d11 - d12);
            if (Math.abs(d14) < 1.0E-7d) {
                d8 = (((d * d14) + 1.0d) * d3) + (((d14 * d5) + 1.0d) * d2 * d) + d4;
            } else {
                double exp = Math.exp(Math.log(d14 + 1.0d) * d);
                d8 = (d3 * exp) + (((1.0d / d14) + d5) * d2 * (exp - 1.0d)) + d4;
            }
            d9 += 1.0d;
            double d15 = d8;
            d12 = d11;
            d11 = d15;
            double d16 = d10;
            d10 = d14;
            d13 = d16;
        }
        return d10;
    }

    static void checkValue(double d) throws EvaluationException {
        if (Double.isNaN(d) || Double.isInfinite(d)) {
            throw new EvaluationException(ErrorEval.NUM_ERROR);
        }
    }
}
