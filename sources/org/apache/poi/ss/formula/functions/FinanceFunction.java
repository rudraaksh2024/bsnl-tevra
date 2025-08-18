package org.apache.poi.ss.formula.functions;

import org.apache.poi.ss.formula.eval.BoolEval;
import org.apache.poi.ss.formula.eval.ErrorEval;
import org.apache.poi.ss.formula.eval.EvaluationException;
import org.apache.poi.ss.formula.eval.MissingArgEval;
import org.apache.poi.ss.formula.eval.NumberEval;
import org.apache.poi.ss.formula.eval.ValueEval;

public abstract class FinanceFunction implements Function3Arg, Function4Arg {
    private static final ValueEval DEFAULT_ARG3 = NumberEval.ZERO;
    private static final ValueEval DEFAULT_ARG4 = BoolEval.FALSE;
    public static final Function FV = new FinanceFunction() {
        /* access modifiers changed from: protected */
        public double evaluate(double d, double d2, double d3, double d4, boolean z) {
            return FinanceLib.fv(d, d2, d3, d4, z);
        }
    };
    public static final Function NPER = new FinanceFunction() {
        /* access modifiers changed from: protected */
        public double evaluate(double d, double d2, double d3, double d4, boolean z) {
            return FinanceLib.nper(d, d2, d3, d4, z);
        }
    };
    public static final Function PMT = new FinanceFunction() {
        /* access modifiers changed from: protected */
        public double evaluate(double d, double d2, double d3, double d4, boolean z) {
            return FinanceLib.pmt(d, d2, d3, d4, z);
        }
    };
    public static final Function PV = new FinanceFunction() {
        /* access modifiers changed from: protected */
        public double evaluate(double d, double d2, double d3, double d4, boolean z) {
            return FinanceLib.pv(d, d2, d3, d4, z);
        }
    };

    /* access modifiers changed from: protected */
    public abstract double evaluate(double d, double d2, double d3, double d4, boolean z) throws EvaluationException;

    protected FinanceFunction() {
    }

    public ValueEval evaluate(int i, int i2, ValueEval valueEval, ValueEval valueEval2, ValueEval valueEval3) {
        return evaluate(i, i2, valueEval, valueEval2, valueEval3, DEFAULT_ARG3);
    }

    public ValueEval evaluate(int i, int i2, ValueEval valueEval, ValueEval valueEval2, ValueEval valueEval3, ValueEval valueEval4) {
        return evaluate(i, i2, valueEval, valueEval2, valueEval3, valueEval4, DEFAULT_ARG4);
    }

    public ValueEval evaluate(int i, int i2, ValueEval valueEval, ValueEval valueEval2, ValueEval valueEval3, ValueEval valueEval4, ValueEval valueEval5) {
        int i3 = i;
        int i4 = i2;
        ValueEval valueEval6 = valueEval;
        try {
            double evaluate = evaluate(NumericFunction.singleOperandEvaluate(valueEval, i, i2), NumericFunction.singleOperandEvaluate(valueEval2, i, i2), NumericFunction.singleOperandEvaluate(valueEval3, i, i2), NumericFunction.singleOperandEvaluate(valueEval4, i, i2), NumericFunction.singleOperandEvaluate(valueEval5, i, i2) != 0.0d);
            NumericFunction.checkValue(evaluate);
            return new NumberEval(evaluate);
        } catch (EvaluationException e) {
            return e.getErrorEval();
        }
    }

    public ValueEval evaluate(ValueEval[] valueEvalArr, int i, int i2) {
        ValueEval[] valueEvalArr2 = valueEvalArr;
        int length = valueEvalArr2.length;
        if (length == 3) {
            return evaluate(i, i2, valueEvalArr2[0], valueEvalArr2[1], valueEvalArr2[2], DEFAULT_ARG3, DEFAULT_ARG4);
        } else if (length == 4) {
            ValueEval valueEval = valueEvalArr2[3];
            if (valueEval == MissingArgEval.instance) {
                valueEval = DEFAULT_ARG3;
            }
            return evaluate(i, i2, valueEvalArr2[0], valueEvalArr2[1], valueEvalArr2[2], valueEval, DEFAULT_ARG4);
        } else if (length != 5) {
            return ErrorEval.VALUE_INVALID;
        } else {
            ValueEval valueEval2 = valueEvalArr2[3];
            if (valueEval2 == MissingArgEval.instance) {
                valueEval2 = DEFAULT_ARG3;
            }
            ValueEval valueEval3 = valueEval2;
            ValueEval valueEval4 = valueEvalArr2[4];
            if (valueEval4 == MissingArgEval.instance) {
                valueEval4 = DEFAULT_ARG4;
            }
            return evaluate(i, i2, valueEvalArr2[0], valueEvalArr2[1], valueEvalArr2[2], valueEval3, valueEval4);
        }
    }

    /* access modifiers changed from: protected */
    public double evaluate(double[] dArr) throws EvaluationException {
        double d;
        double d2;
        double[] dArr2 = dArr;
        int length = dArr2.length;
        if (length != 3) {
            if (length == 4) {
                d2 = 0.0d;
            } else if (length == 5) {
                d2 = dArr2[4];
            } else {
                throw new IllegalStateException("Wrong number of arguments");
            }
            d = dArr2[3];
        } else {
            d2 = 0.0d;
            d = 0.0d;
        }
        return evaluate(dArr2[0], dArr2[1], dArr2[2], d, d2 != 0.0d);
    }
}
