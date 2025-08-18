package org.apache.poi.ss.formula.eval;

import java.math.BigDecimal;
import java.math.MathContext;
import org.apache.poi.ss.formula.functions.ArrayFunction;
import org.apache.poi.ss.formula.functions.Fixed2ArgFunction;
import org.apache.poi.ss.formula.functions.Function;
import org.apache.poi.ss.util.NumberToTextConverter;

public abstract class TwoOperandNumericOperation extends Fixed2ArgFunction implements ArrayFunction {
    public static final Function AddEval = new TwoOperandNumericOperation() {
        /* access modifiers changed from: protected */
        public double evaluate(double d, double d2) {
            return d + d2;
        }
    };
    public static final Function DivideEval = new TwoOperandNumericOperation() {
        /* access modifiers changed from: protected */
        public double evaluate(double d, double d2) throws EvaluationException {
            if (d2 != 0.0d) {
                return new BigDecimal(NumberToTextConverter.toText(d)).divide(new BigDecimal(NumberToTextConverter.toText(d2)), MathContext.DECIMAL128).doubleValue();
            }
            throw new EvaluationException(ErrorEval.DIV_ZERO);
        }
    };
    public static final Function MultiplyEval = new TwoOperandNumericOperation() {
        /* access modifiers changed from: protected */
        public double evaluate(double d, double d2) {
            return new BigDecimal(NumberToTextConverter.toText(d)).multiply(new BigDecimal(NumberToTextConverter.toText(d2))).doubleValue();
        }
    };
    public static final Function PowerEval = new TwoOperandNumericOperation() {
        /* access modifiers changed from: protected */
        public double evaluate(double d, double d2) {
            if (d >= 0.0d || Math.abs(d2) <= 0.0d || Math.abs(d2) >= 1.0d) {
                return Math.pow(d, d2);
            }
            return Math.pow(d * -1.0d, d2) * -1.0d;
        }
    };
    public static final Function SubtractEval = new SubtractEvalClass();

    private static final class SubtractEvalClass extends TwoOperandNumericOperation {
        /* access modifiers changed from: protected */
        public double evaluate(double d, double d2) {
            return d - d2;
        }
    }

    /* access modifiers changed from: protected */
    public abstract double evaluate(double d, double d2) throws EvaluationException;

    /* access modifiers changed from: protected */
    public final double singleOperandEvaluate(ValueEval valueEval, int i, int i2) throws EvaluationException {
        return OperandResolver.coerceValueToDouble(OperandResolver.getSingleValue(valueEval, i, i2));
    }

    public ValueEval evaluateArray(ValueEval[] valueEvalArr, int i, int i2) {
        if (valueEvalArr.length != 2) {
            return ErrorEval.VALUE_INVALID;
        }
        return evaluateTwoArrayArgs(valueEvalArr[0], valueEvalArr[1], i, i2, new TwoOperandNumericOperation$$ExternalSyntheticLambda0(this));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$evaluateArray$0$org-apache-poi-ss-formula-eval-TwoOperandNumericOperation  reason: not valid java name */
    public /* synthetic */ ValueEval m2251lambda$evaluateArray$0$orgapachepoissformulaevalTwoOperandNumericOperation(ValueEval valueEval, ValueEval valueEval2) {
        try {
            return new NumberEval(evaluate(OperandResolver.coerceValueToDouble(valueEval), OperandResolver.coerceValueToDouble(valueEval2)));
        } catch (EvaluationException e) {
            return e.getErrorEval();
        }
    }

    public ValueEval evaluate(int i, int i2, ValueEval valueEval, ValueEval valueEval2) {
        try {
            double evaluate = evaluate(singleOperandEvaluate(valueEval, i, i2), singleOperandEvaluate(valueEval2, i, i2));
            if (evaluate != 0.0d || (this instanceof SubtractEvalClass)) {
                return (Double.isNaN(evaluate) || Double.isInfinite(evaluate)) ? ErrorEval.NUM_ERROR : new NumberEval(evaluate);
            }
            return NumberEval.ZERO;
        } catch (EvaluationException e) {
            return e.getErrorEval();
        }
    }
}
