package org.apache.poi.ss.formula.functions;

import org.apache.commons.math3.stat.descriptive.moment.GeometricMean;
import org.apache.poi.ss.formula.eval.ErrorEval;
import org.apache.poi.ss.formula.eval.EvaluationException;
import org.apache.poi.ss.formula.eval.NumberEval;
import org.apache.poi.ss.formula.eval.OperandResolver;
import org.apache.poi.ss.formula.eval.ValueEval;
import org.apache.poi.ss.formula.functions.MultiOperandNumericFunction;

public abstract class AggregateFunction extends MultiOperandNumericFunction {
    public static final Function AVEDEV = new AggregateFunction() {
        /* access modifiers changed from: protected */
        public double evaluate(double[] dArr) {
            return StatsLib.avedev(dArr);
        }
    };
    public static final Function AVERAGE = new AggregateFunction() {
        /* access modifiers changed from: protected */
        public double evaluate(double[] dArr) throws EvaluationException {
            if (dArr.length >= 1) {
                return MathX.average(dArr);
            }
            throw new EvaluationException(ErrorEval.DIV_ZERO);
        }
    };
    public static final Function DEVSQ = new AggregateFunction() {
        /* access modifiers changed from: protected */
        public double evaluate(double[] dArr) {
            return StatsLib.devsq(dArr);
        }
    };
    public static final Function GEOMEAN = new Geomean();
    public static final Function LARGE = new LargeSmall(true);
    public static final Function MAX = new AggregateFunction() {
        /* access modifiers changed from: protected */
        public double evaluate(double[] dArr) {
            if (dArr.length > 0) {
                return MathX.max(dArr);
            }
            return 0.0d;
        }
    };
    public static final Function MEDIAN = new AggregateFunction() {
        /* access modifiers changed from: protected */
        public double evaluate(double[] dArr) {
            return StatsLib.median(dArr);
        }
    };
    public static final Function MIN = new AggregateFunction() {
        /* access modifiers changed from: protected */
        public double evaluate(double[] dArr) {
            if (dArr.length > 0) {
                return MathX.min(dArr);
            }
            return 0.0d;
        }
    };
    public static final Function PERCENTILE = new Percentile();
    public static final Function PRODUCT = new Product();
    public static final Function SMALL = new LargeSmall(false);
    public static final Function STDEV = new AggregateFunction() {
        /* access modifiers changed from: protected */
        public double evaluate(double[] dArr) throws EvaluationException {
            if (dArr.length >= 1) {
                return StatsLib.stdev(dArr);
            }
            throw new EvaluationException(ErrorEval.DIV_ZERO);
        }
    };
    public static final Function SUM = new AggregateFunction() {
        /* access modifiers changed from: protected */
        public double evaluate(double[] dArr) {
            return MathX.sum(dArr);
        }
    };
    public static final Function SUMSQ = new AggregateFunction() {
        /* access modifiers changed from: protected */
        public double evaluate(double[] dArr) {
            return MathX.sumsq(dArr);
        }
    };
    public static final Function VAR = new AggregateFunction() {
        /* access modifiers changed from: protected */
        public double evaluate(double[] dArr) throws EvaluationException {
            if (dArr.length >= 1) {
                return StatsLib.var(dArr);
            }
            throw new EvaluationException(ErrorEval.DIV_ZERO);
        }
    };
    public static final Function VARP = new AggregateFunction() {
        /* access modifiers changed from: protected */
        public double evaluate(double[] dArr) throws EvaluationException {
            if (dArr.length >= 1) {
                return StatsLib.varp(dArr);
            }
            throw new EvaluationException(ErrorEval.DIV_ZERO);
        }
    };

    private static final class LargeSmall extends Fixed2ArgFunction {
        private final boolean _isLarge;

        protected LargeSmall(boolean z) {
            this._isLarge = z;
        }

        public ValueEval evaluate(int i, int i2, ValueEval valueEval, ValueEval valueEval2) {
            try {
                double coerceValueToDouble = OperandResolver.coerceValueToDouble(OperandResolver.getSingleValue(valueEval2, i, i2));
                if (coerceValueToDouble < 1.0d) {
                    return ErrorEval.NUM_ERROR;
                }
                int ceil = (int) Math.ceil(coerceValueToDouble);
                try {
                    double[] collectValues = ValueCollector.collectValues(valueEval);
                    if (ceil > collectValues.length) {
                        return ErrorEval.NUM_ERROR;
                    }
                    double kthLargest = this._isLarge ? StatsLib.kthLargest(collectValues, ceil) : StatsLib.kthSmallest(collectValues, ceil);
                    NumericFunction.checkValue(kthLargest);
                    return new NumberEval(kthLargest);
                } catch (EvaluationException e) {
                    return e.getErrorEval();
                }
            } catch (EvaluationException unused) {
                return ErrorEval.VALUE_INVALID;
            }
        }
    }

    private static final class Percentile extends Fixed2ArgFunction {
        protected Percentile() {
        }

        public ValueEval evaluate(int i, int i2, ValueEval valueEval, ValueEval valueEval2) {
            double d;
            try {
                double coerceValueToDouble = OperandResolver.coerceValueToDouble(OperandResolver.getSingleValue(valueEval2, i, i2));
                if (coerceValueToDouble < 0.0d || coerceValueToDouble > 1.0d) {
                    return ErrorEval.NUM_ERROR;
                }
                try {
                    double[] collectValues = ValueCollector.collectValues(valueEval);
                    int length = collectValues.length;
                    if (length != 0) {
                        if (length <= 8191) {
                            double d2 = (((double) (length - 1)) * coerceValueToDouble) + 1.0d;
                            if (d2 == 1.0d) {
                                d = StatsLib.kthSmallest(collectValues, 1);
                            } else if (Double.compare(d2, (double) length) == 0) {
                                d = StatsLib.kthLargest(collectValues, 1);
                            } else {
                                int i3 = (int) d2;
                                d = StatsLib.kthSmallest(collectValues, i3) + ((d2 - ((double) i3)) * (StatsLib.kthSmallest(collectValues, i3 + 1) - StatsLib.kthSmallest(collectValues, i3)));
                            }
                            NumericFunction.checkValue(d);
                            return new NumberEval(d);
                        }
                    }
                    return ErrorEval.NUM_ERROR;
                } catch (EvaluationException e) {
                    return e.getErrorEval();
                }
            } catch (EvaluationException unused) {
                return ErrorEval.VALUE_INVALID;
            }
        }
    }

    static final class ValueCollector extends MultiOperandNumericFunction {
        private static final ValueCollector instance = new ValueCollector();

        public ValueCollector() {
            super(false, false);
        }

        public static double[] collectValues(ValueEval... valueEvalArr) throws EvaluationException {
            return instance.getNumberArray(valueEvalArr);
        }

        /* access modifiers changed from: protected */
        public double evaluate(double[] dArr) {
            throw new IllegalStateException("should not be called");
        }
    }

    protected AggregateFunction() {
        super(false, false);
    }

    static Function subtotalInstance(Function function, final boolean z) {
        return new AggregateFunction((AggregateFunction) function) {
            final /* synthetic */ AggregateFunction val$arg;

            public boolean isSubtotalCounted() {
                return false;
            }

            {
                this.val$arg = r1;
            }

            /* access modifiers changed from: protected */
            public double evaluate(double[] dArr) throws EvaluationException {
                return this.val$arg.evaluate(dArr);
            }

            public boolean isHiddenRowCounted() {
                return z;
            }
        };
    }

    private static class Product extends AggregateFunction {
        Product() {
            setMissingArgPolicy(MultiOperandNumericFunction.Policy.SKIP);
        }

        /* access modifiers changed from: protected */
        public double evaluate(double[] dArr) throws EvaluationException {
            return MathX.product(dArr);
        }
    }

    private static class Geomean extends AggregateFunction {
        Geomean() {
            setMissingArgPolicy(MultiOperandNumericFunction.Policy.COERCE);
        }

        /* access modifiers changed from: protected */
        public double evaluate(double[] dArr) throws EvaluationException {
            int length = dArr.length;
            int i = 0;
            while (i < length) {
                if (dArr[i] > 0.0d) {
                    i++;
                } else {
                    throw new EvaluationException(ErrorEval.NUM_ERROR);
                }
            }
            return new GeometricMean().evaluate(dArr, 0, dArr.length);
        }
    }
}
