package org.apache.commons.math3.optimization.direct;

import org.apache.commons.math3.analysis.MultivariateFunction;
import org.apache.commons.math3.analysis.UnivariateFunction;
import org.apache.commons.math3.analysis.function.Logit;
import org.apache.commons.math3.analysis.function.Sigmoid;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.MathUtils;

@Deprecated
public class MultivariateFunctionMappingAdapter implements MultivariateFunction {
    private final MultivariateFunction bounded;
    private final Mapper[] mappers;

    private interface Mapper {
        double boundedToUnbounded(double d);

        double unboundedToBounded(double d);
    }

    public MultivariateFunctionMappingAdapter(MultivariateFunction multivariateFunction, double[] dArr, double[] dArr2) {
        MathUtils.checkNotNull(dArr);
        MathUtils.checkNotNull(dArr2);
        if (dArr.length == dArr2.length) {
            int i = 0;
            while (i < dArr.length) {
                if (dArr2[i] >= dArr[i]) {
                    i++;
                } else {
                    throw new NumberIsTooSmallException(Double.valueOf(dArr2[i]), Double.valueOf(dArr[i]), true);
                }
            }
            this.bounded = multivariateFunction;
            this.mappers = new Mapper[dArr.length];
            for (int i2 = 0; i2 < this.mappers.length; i2++) {
                if (Double.isInfinite(dArr[i2])) {
                    if (Double.isInfinite(dArr2[i2])) {
                        this.mappers[i2] = new NoBoundsMapper();
                    } else {
                        this.mappers[i2] = new UpperBoundMapper(dArr2[i2]);
                    }
                } else if (Double.isInfinite(dArr2[i2])) {
                    this.mappers[i2] = new LowerBoundMapper(dArr[i2]);
                } else {
                    this.mappers[i2] = new LowerUpperBoundMapper(dArr[i2], dArr2[i2]);
                }
            }
            return;
        }
        throw new DimensionMismatchException(dArr.length, dArr2.length);
    }

    public double[] unboundedToBounded(double[] dArr) {
        double[] dArr2 = new double[this.mappers.length];
        int i = 0;
        while (true) {
            Mapper[] mapperArr = this.mappers;
            if (i >= mapperArr.length) {
                return dArr2;
            }
            dArr2[i] = mapperArr[i].unboundedToBounded(dArr[i]);
            i++;
        }
    }

    public double[] boundedToUnbounded(double[] dArr) {
        double[] dArr2 = new double[this.mappers.length];
        int i = 0;
        while (true) {
            Mapper[] mapperArr = this.mappers;
            if (i >= mapperArr.length) {
                return dArr2;
            }
            dArr2[i] = mapperArr[i].boundedToUnbounded(dArr[i]);
            i++;
        }
    }

    public double value(double[] dArr) {
        return this.bounded.value(unboundedToBounded(dArr));
    }

    private static class NoBoundsMapper implements Mapper {
        public double boundedToUnbounded(double d) {
            return d;
        }

        public double unboundedToBounded(double d) {
            return d;
        }

        NoBoundsMapper() {
        }
    }

    private static class LowerBoundMapper implements Mapper {
        private final double lower;

        LowerBoundMapper(double d) {
            this.lower = d;
        }

        public double unboundedToBounded(double d) {
            return this.lower + FastMath.exp(d);
        }

        public double boundedToUnbounded(double d) {
            return FastMath.log(d - this.lower);
        }
    }

    private static class UpperBoundMapper implements Mapper {
        private final double upper;

        UpperBoundMapper(double d) {
            this.upper = d;
        }

        public double unboundedToBounded(double d) {
            return this.upper - FastMath.exp(-d);
        }

        public double boundedToUnbounded(double d) {
            return -FastMath.log(this.upper - d);
        }
    }

    private static class LowerUpperBoundMapper implements Mapper {
        private final UnivariateFunction boundingFunction;
        private final UnivariateFunction unboundingFunction;

        LowerUpperBoundMapper(double d, double d2) {
            this.boundingFunction = new Sigmoid(d, d2);
            this.unboundingFunction = new Logit(d, d2);
        }

        public double unboundedToBounded(double d) {
            return this.boundingFunction.value(d);
        }

        public double boundedToUnbounded(double d) {
            return this.unboundingFunction.value(d);
        }
    }
}
