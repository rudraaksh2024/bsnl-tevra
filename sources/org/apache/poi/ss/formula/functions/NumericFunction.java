package org.apache.poi.ss.formula.functions;

import java.math.BigDecimal;
import java.math.MathContext;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import org.apache.poi.ss.formula.eval.ErrorEval;
import org.apache.poi.ss.formula.eval.EvaluationException;
import org.apache.poi.ss.formula.eval.NumberEval;
import org.apache.poi.ss.formula.eval.OperandResolver;
import org.apache.poi.ss.formula.eval.StringEval;
import org.apache.poi.ss.formula.eval.ValueEval;
import org.apache.poi.util.LocaleUtil;

public abstract class NumericFunction implements Function {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    public static final Function ABS = oneDouble(new NumericFunction$$ExternalSyntheticLambda22());
    public static final Function ACOS = oneDouble(new NumericFunction$$ExternalSyntheticLambda4());
    public static final Function ACOSH = oneDouble(new NumericFunction$$ExternalSyntheticLambda16());
    public static final Function ASIN = oneDouble(new NumericFunction$$ExternalSyntheticLambda27());
    public static final Function ASINH = oneDouble(new NumericFunction$$ExternalSyntheticLambda28());
    public static final Function ATAN = oneDouble(new NumericFunction$$ExternalSyntheticLambda29());
    public static final Function ATAN2 = twoDouble(new NumericFunction$$ExternalSyntheticLambda8());
    public static final Function ATANH = oneDouble(new NumericFunction$$ExternalSyntheticLambda30());
    public static final Function CEILING = twoDouble(new NumericFunction$$ExternalSyntheticLambda9());
    public static final Function COMBIN = twoDouble(new NumericFunction$$ExternalSyntheticLambda10());
    public static final Function COS = oneDouble(new NumericFunction$$ExternalSyntheticLambda31());
    public static final Function COSH = oneDouble(new NumericFunction$$ExternalSyntheticLambda32());
    public static final Function DEGREES = oneDouble(new NumericFunction$$ExternalSyntheticLambda34());
    public static final Function DOLLAR = new NumericFunction$$ExternalSyntheticLambda33();
    public static final Function EVEN = oneDouble(new NumericFunction$$ExternalSyntheticLambda26());
    public static final Function EXP = oneDouble(new NumericFunction$$ExternalSyntheticLambda35());
    public static final Function FACT = oneDouble(new NumericFunction$$ExternalSyntheticLambda36());
    public static final Function FLOOR = twoDouble(new NumericFunction$$ExternalSyntheticLambda12());
    public static final Function INT = oneDouble(new NumericFunction$$ExternalSyntheticLambda37());
    public static final Function LN = oneDouble(new NumericFunction$$ExternalSyntheticLambda38());
    public static final Function LOG = new NumericFunction$$ExternalSyntheticLambda20();
    public static final Function LOG10 = oneDouble(new NumericFunction$$ExternalSyntheticLambda39());
    private static final double LOG_10_TO_BASE_e = Math.log(10.0d);
    public static final Function MOD = twoDouble(new NumericFunction$$ExternalSyntheticLambda13());
    public static final Function ODD = oneDouble(new NumericFunction$$ExternalSyntheticLambda25());
    private static final long PARITY_MASK = -2;
    public static final Function PI = new NumericFunction$$ExternalSyntheticLambda21();
    static final NumberEval PI_EVAL = new NumberEval(3.141592653589793d);
    public static final Function POISSON = new NumericFunction$$ExternalSyntheticLambda24();
    public static final Function POWER = twoDouble(new NumericFunction$$ExternalSyntheticLambda14());
    public static final Function RADIANS = oneDouble(new NumericFunction$$ExternalSyntheticLambda40());
    public static final Function RAND = new NumericFunction$$ExternalSyntheticLambda23();
    public static final Function ROUND = twoDouble(new NumericFunction$$ExternalSyntheticLambda15());
    public static final Function ROUNDDOWN = twoDouble(new NumericFunction$$ExternalSyntheticLambda17());
    public static final Function ROUNDUP = twoDouble(new NumericFunction$$ExternalSyntheticLambda18());
    public static final Function SIGN = oneDouble(new NumericFunction$$ExternalSyntheticLambda1());
    public static final Function SIN = oneDouble(new NumericFunction$$ExternalSyntheticLambda2());
    public static final Function SINH = oneDouble(new NumericFunction$$ExternalSyntheticLambda3());
    public static final Function SQRT = oneDouble(new NumericFunction$$ExternalSyntheticLambda5());
    public static final Function TAN = oneDouble(new NumericFunction$$ExternalSyntheticLambda6());
    public static final Function TANH = oneDouble(new NumericFunction$$ExternalSyntheticLambda7());
    private static final double TEN = 10.0d;
    public static final Function TRUNC = new NumericFunction$$ExternalSyntheticLambda19();
    private static final double ZERO = 0.0d;

    private interface OneDoubleIf {
        double apply(double d);
    }

    private interface TwoDoubleIf {
        Object apply(double d, double d2);
    }

    /* access modifiers changed from: protected */
    public abstract double eval(ValueEval[] valueEvalArr, int i, int i2) throws EvaluationException;

    protected static double singleOperandEvaluate(ValueEval valueEval, int i, int i2) throws EvaluationException {
        if (valueEval != null) {
            double coerceValueToDouble = OperandResolver.coerceValueToDouble(OperandResolver.getSingleValue(valueEval, i, i2));
            checkValue(coerceValueToDouble);
            return coerceValueToDouble;
        }
        throw new IllegalArgumentException("arg must not be null");
    }

    public static void checkValue(double d) throws EvaluationException {
        if (Double.isNaN(d) || Double.isInfinite(d)) {
            throw new EvaluationException(ErrorEval.NUM_ERROR);
        }
    }

    public final ValueEval evaluate(ValueEval[] valueEvalArr, int i, int i2) {
        try {
            double eval = eval(valueEvalArr, i, i2);
            checkValue(eval);
            return new NumberEval(eval);
        } catch (EvaluationException e) {
            return e.getErrorEval();
        }
    }

    /* access modifiers changed from: private */
    public static ValueEval evaluateDollar(ValueEval[] valueEvalArr, int i, int i2) {
        if (valueEvalArr.length != 1 && valueEvalArr.length != 2) {
            return ErrorEval.VALUE_INVALID;
        }
        int i3 = 0;
        try {
            double singleOperandEvaluate = singleOperandEvaluate(valueEvalArr[0], i, i2);
            int singleOperandEvaluate2 = (int) (valueEvalArr.length == 1 ? 2.0d : singleOperandEvaluate(valueEvalArr[1], i, i2));
            if (singleOperandEvaluate2 > 127) {
                return ErrorEval.VALUE_INVALID;
            }
            if (singleOperandEvaluate2 < 0) {
                BigDecimal valueOf = BigDecimal.valueOf(Math.pow(10.0d, (double) (-singleOperandEvaluate2)));
                singleOperandEvaluate = BigDecimal.valueOf(singleOperandEvaluate).divide(valueOf, MathContext.DECIMAL128).toBigInteger().multiply(valueOf.toBigInteger()).doubleValue();
            }
            DecimalFormat decimalFormat = (DecimalFormat) NumberFormat.getCurrencyInstance(LocaleUtil.getUserLocale());
            if (singleOperandEvaluate2 >= 0) {
                i3 = singleOperandEvaluate2;
            }
            if (LocaleUtil.getUserLocale().getCountry().equalsIgnoreCase("US")) {
                decimalFormat.setNegativePrefix("(" + decimalFormat.getDecimalFormatSymbols().getCurrencySymbol());
                decimalFormat.setNegativeSuffix(")");
            }
            decimalFormat.setMinimumFractionDigits(i3);
            decimalFormat.setMaximumFractionDigits(i3);
            return new StringEval(decimalFormat.format(singleOperandEvaluate).replace(" ", " "));
        } catch (EvaluationException e) {
            return e.getErrorEval();
        }
    }

    static /* synthetic */ double lambda$static$1(double d) {
        return (double) Math.round(d - 0.5d);
    }

    static /* synthetic */ double lambda$static$2(double d) {
        return Math.log(d) / LOG_10_TO_BASE_e;
    }

    static /* synthetic */ Object lambda$static$3(double d, double d2) {
        return (d == 0.0d && d2 == 0.0d) ? ErrorEval.DIV_ZERO : Double.valueOf(Math.atan2(d2, d));
    }

    static /* synthetic */ Object lambda$static$4(double d, double d2) {
        return (d > 2.147483647E9d || d2 > 2.147483647E9d) ? ErrorEval.NUM_ERROR : Double.valueOf(MathX.nChooseK((int) d, (int) d2));
    }

    static /* synthetic */ Object lambda$static$5(double d, double d2) {
        if (d2 == 0.0d) {
            return d == 0.0d ? Double.valueOf(0.0d) : ErrorEval.DIV_ZERO;
        }
        return Double.valueOf(MathX.floor(d, d2));
    }

    static /* synthetic */ Object lambda$static$6(double d, double d2) {
        return d2 == 0.0d ? ErrorEval.DIV_ZERO : Double.valueOf(MathX.mod(d, d2));
    }

    /* access modifiers changed from: private */
    public static ValueEval evaluateTrunc(ValueEval[] valueEvalArr, int i, int i2) {
        if (valueEvalArr.length != 1 && valueEvalArr.length != 2) {
            return ErrorEval.VALUE_INVALID;
        }
        try {
            double roundDown = MathX.roundDown(singleOperandEvaluate(valueEvalArr[0], i, i2), valueEvalArr.length == 1 ? 0.0d : singleOperandEvaluate(valueEvalArr[1], i, i2));
            checkValue(roundDown);
            return new NumberEval(roundDown);
        } catch (EvaluationException e) {
            return e.getErrorEval();
        }
    }

    /* access modifiers changed from: private */
    public static ValueEval evaluatePI(ValueEval[] valueEvalArr, int i, int i2) {
        return valueEvalArr.length != 0 ? ErrorEval.VALUE_INVALID : PI_EVAL;
    }

    /* access modifiers changed from: private */
    public static ValueEval evaluateRand(ValueEval[] valueEvalArr, int i, int i2) {
        return valueEvalArr.length != 0 ? ErrorEval.VALUE_INVALID : new NumberEval(Math.random());
    }

    /* access modifiers changed from: private */
    public static double evaluateOdd(double d) {
        if (d == 0.0d) {
            return 1.0d;
        }
        double abs = Math.abs(d) + 1.0d;
        long j = ((long) abs) & -2;
        return ((double) MathX.sign(d)) * ((double) (Double.compare((double) j, abs) == 0 ? j - 1 : j + 1));
    }

    /* access modifiers changed from: private */
    public static double evaluateEven(double d) {
        if (d == 0.0d) {
            return 0.0d;
        }
        double abs = Math.abs(d);
        long j = ((long) abs) & -2;
        double sign = (double) MathX.sign(d);
        if (Double.compare((double) j, abs) != 0) {
            j += 2;
        }
        return sign * ((double) j);
    }

    private static Function oneDouble(OneDoubleIf oneDoubleIf) {
        return new NumericFunction$$ExternalSyntheticLambda0(oneDoubleIf);
    }

    static /* synthetic */ ValueEval lambda$oneDouble$7(OneDoubleIf oneDoubleIf, ValueEval[] valueEvalArr, int i, int i2) {
        if (valueEvalArr.length != 1) {
            return ErrorEval.VALUE_INVALID;
        }
        try {
            double apply = oneDoubleIf.apply(singleOperandEvaluate(valueEvalArr[0], i, i2));
            if (!Double.isNaN(apply)) {
                if (!Double.isInfinite(apply)) {
                    return new NumberEval(apply);
                }
            }
            return ErrorEval.NUM_ERROR;
        } catch (EvaluationException e) {
            return e.getErrorEval();
        }
    }

    private static Function twoDouble(TwoDoubleIf twoDoubleIf) {
        return new NumericFunction$$ExternalSyntheticLambda11(twoDoubleIf);
    }

    static /* synthetic */ ValueEval lambda$twoDouble$8(TwoDoubleIf twoDoubleIf, ValueEval[] valueEvalArr, int i, int i2) {
        if (valueEvalArr.length != 2) {
            return ErrorEval.VALUE_INVALID;
        }
        try {
            Object apply = twoDoubleIf.apply(singleOperandEvaluate(valueEvalArr[0], i, i2), singleOperandEvaluate(valueEvalArr[1], i, i2));
            if (apply instanceof ErrorEval) {
                return (ErrorEval) apply;
            }
            double doubleValue = ((Double) apply).doubleValue();
            if (!Double.isNaN(doubleValue)) {
                if (!Double.isInfinite(doubleValue)) {
                    return new NumberEval(doubleValue);
                }
            }
            return ErrorEval.NUM_ERROR;
        } catch (EvaluationException e) {
            return e.getErrorEval();
        }
    }
}
