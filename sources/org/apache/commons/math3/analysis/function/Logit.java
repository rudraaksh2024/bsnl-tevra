package org.apache.commons.math3.analysis.function;

import org.apache.commons.math3.analysis.DifferentiableUnivariateFunction;
import org.apache.commons.math3.analysis.FunctionUtils;
import org.apache.commons.math3.analysis.ParametricUnivariateFunction;
import org.apache.commons.math3.analysis.UnivariateFunction;
import org.apache.commons.math3.analysis.differentiation.DerivativeStructure;
import org.apache.commons.math3.analysis.differentiation.UnivariateDifferentiableFunction;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.exception.OutOfRangeException;
import org.apache.commons.math3.util.FastMath;

public class Logit implements UnivariateDifferentiableFunction, DifferentiableUnivariateFunction {
    private final double hi;
    private final double lo;

    public Logit() {
        this(0.0d, 1.0d);
    }

    public Logit(double d, double d2) {
        this.lo = d;
        this.hi = d2;
    }

    public double value(double d) throws OutOfRangeException {
        return value(d, this.lo, this.hi);
    }

    @Deprecated
    public UnivariateFunction derivative() {
        return FunctionUtils.toDifferentiableUnivariateFunction(this).derivative();
    }

    public static class Parametric implements ParametricUnivariateFunction {
        public double value(double d, double... dArr) throws NullArgumentException, DimensionMismatchException {
            validateParameters(dArr);
            return Logit.value(d, dArr[0], dArr[1]);
        }

        public double[] gradient(double d, double... dArr) throws NullArgumentException, DimensionMismatchException {
            validateParameters(dArr);
            return new double[]{1.0d / (dArr[0] - d), 1.0d / (dArr[1] - d)};
        }

        private void validateParameters(double[] dArr) throws NullArgumentException, DimensionMismatchException {
            if (dArr == null) {
                throw new NullArgumentException();
            } else if (dArr.length != 2) {
                throw new DimensionMismatchException(dArr.length, 2);
            }
        }
    }

    /* access modifiers changed from: private */
    public static double value(double d, double d2, double d3) throws OutOfRangeException {
        if (d >= d2 && d <= d3) {
            return FastMath.log((d - d2) / (d3 - d));
        }
        throw new OutOfRangeException(Double.valueOf(d), Double.valueOf(d2), Double.valueOf(d3));
    }

    public DerivativeStructure value(DerivativeStructure derivativeStructure) throws OutOfRangeException {
        double value = derivativeStructure.getValue();
        if (value < this.lo || value > this.hi) {
            throw new OutOfRangeException(Double.valueOf(value), Double.valueOf(this.lo), Double.valueOf(this.hi));
        }
        int order = derivativeStructure.getOrder() + 1;
        double[] dArr = new double[order];
        double log = FastMath.log((value - this.lo) / (this.hi - value));
        dArr[0] = log;
        if (Double.isInfinite(log)) {
            if (order > 1) {
                dArr[1] = Double.POSITIVE_INFINITY;
            }
            for (int i = 2; i < order; i++) {
                dArr[i] = dArr[i - 2];
            }
        } else {
            double d = 1.0d / (value - this.lo);
            double d2 = 1.0d / (this.hi - value);
            double d3 = d;
            double d4 = d2;
            for (int i2 = 1; i2 < order; i2++) {
                dArr[i2] = d3 + d4;
                d3 *= ((double) (-i2)) * d;
                d4 *= ((double) i2) * d2;
            }
        }
        return derivativeStructure.compose(dArr);
    }
}
