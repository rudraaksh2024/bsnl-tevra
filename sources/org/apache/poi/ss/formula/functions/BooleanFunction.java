package org.apache.poi.ss.formula.functions;

import org.apache.poi.ss.formula.TwoDEval;
import org.apache.poi.ss.formula.eval.BoolEval;
import org.apache.poi.ss.formula.eval.ErrorEval;
import org.apache.poi.ss.formula.eval.EvaluationException;
import org.apache.poi.ss.formula.eval.MissingArgEval;
import org.apache.poi.ss.formula.eval.OperandResolver;
import org.apache.poi.ss.formula.eval.RefEval;
import org.apache.poi.ss.formula.eval.ValueEval;

public abstract class BooleanFunction implements Function, ArrayFunction {
    public static final Function AND = new BooleanFunction() {
        /* access modifiers changed from: protected */
        public boolean getInitialResultValue() {
            return true;
        }

        /* access modifiers changed from: protected */
        public boolean partialEvaluate(boolean z, boolean z2) {
            return z && z2;
        }
    };
    public static final Function FALSE = new BooleanFunction$$ExternalSyntheticLambda1();
    public static final Function NOT = new BooleanFunction$$ExternalSyntheticLambda3();
    public static final Function OR = new BooleanFunction() {
        /* access modifiers changed from: protected */
        public boolean getInitialResultValue() {
            return false;
        }

        /* access modifiers changed from: protected */
        public boolean partialEvaluate(boolean z, boolean z2) {
            return z || z2;
        }
    };
    public static final Function TRUE = new BooleanFunction$$ExternalSyntheticLambda2();

    /* access modifiers changed from: protected */
    public abstract boolean getInitialResultValue();

    /* access modifiers changed from: protected */
    public abstract boolean partialEvaluate(boolean z, boolean z2);

    public final ValueEval evaluate(ValueEval[] valueEvalArr, int i, int i2) {
        if (valueEvalArr.length < 1) {
            return ErrorEval.VALUE_INVALID;
        }
        try {
            return BoolEval.valueOf(calculate(valueEvalArr));
        } catch (EvaluationException e) {
            return e.getErrorEval();
        }
    }

    private boolean calculate(ValueEval[] valueEvalArr) throws EvaluationException {
        Boolean bool;
        boolean initialResultValue = getInitialResultValue();
        boolean z = false;
        for (TwoDEval twoDEval : valueEvalArr) {
            if (twoDEval instanceof TwoDEval) {
                TwoDEval twoDEval2 = twoDEval;
                int height = twoDEval2.getHeight();
                int width = twoDEval2.getWidth();
                for (int i = 0; i < height; i++) {
                    for (int i2 = 0; i2 < width; i2++) {
                        Boolean coerceValueToBoolean = OperandResolver.coerceValueToBoolean(twoDEval2.getValue(i, i2), true);
                        if (coerceValueToBoolean != null) {
                            initialResultValue = partialEvaluate(initialResultValue, coerceValueToBoolean.booleanValue());
                            z = true;
                        }
                    }
                }
            } else if (twoDEval instanceof RefEval) {
                RefEval refEval = (RefEval) twoDEval;
                int lastSheetIndex = refEval.getLastSheetIndex();
                for (int firstSheetIndex = refEval.getFirstSheetIndex(); firstSheetIndex <= lastSheetIndex; firstSheetIndex++) {
                    Boolean coerceValueToBoolean2 = OperandResolver.coerceValueToBoolean(refEval.getInnerValueEval(firstSheetIndex), true);
                    if (coerceValueToBoolean2 != null) {
                        initialResultValue = partialEvaluate(initialResultValue, coerceValueToBoolean2.booleanValue());
                        z = true;
                    }
                }
            } else {
                if (twoDEval == MissingArgEval.instance) {
                    bool = false;
                } else {
                    bool = OperandResolver.coerceValueToBoolean(twoDEval, false);
                }
                if (bool != null) {
                    initialResultValue = partialEvaluate(initialResultValue, bool.booleanValue());
                    z = true;
                }
            }
        }
        if (z) {
            return initialResultValue;
        }
        throw new EvaluationException(ErrorEval.VALUE_INVALID);
    }

    public ValueEval evaluateArray(ValueEval[] valueEvalArr, int i, int i2) {
        return evaluate(valueEvalArr, i, i2);
    }

    /* access modifiers changed from: private */
    public static ValueEval evaluateFalse(ValueEval[] valueEvalArr, int i, int i2) {
        return valueEvalArr.length != 0 ? ErrorEval.VALUE_INVALID : BoolEval.FALSE;
    }

    /* access modifiers changed from: private */
    public static ValueEval evaluateTrue(ValueEval[] valueEvalArr, int i, int i2) {
        return valueEvalArr.length != 0 ? ErrorEval.VALUE_INVALID : BoolEval.TRUE;
    }

    /* access modifiers changed from: private */
    public static ValueEval evaluateNot(ValueEval[] valueEvalArr, int i, int i2) {
        if (valueEvalArr.length != 1) {
            return ErrorEval.VALUE_INVALID;
        }
        return ArrayFunction._evaluateOneArrayArg(valueEvalArr[0], i, i2, new BooleanFunction$$ExternalSyntheticLambda0(i, i2));
    }

    static /* synthetic */ ValueEval lambda$evaluateNot$0(int i, int i2, ValueEval valueEval) {
        try {
            ValueEval singleValue = OperandResolver.getSingleValue(valueEval, i, i2);
            boolean z = false;
            Boolean coerceValueToBoolean = OperandResolver.coerceValueToBoolean(singleValue, false);
            if (!(coerceValueToBoolean != null && coerceValueToBoolean.booleanValue())) {
                z = true;
            }
            return BoolEval.valueOf(z);
        } catch (EvaluationException e) {
            return e.getErrorEval();
        }
    }
}
