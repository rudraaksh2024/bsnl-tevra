package org.apache.commons.math3.distribution;

import org.apache.commons.math3.exception.OutOfRangeException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.random.RandomGenerator;
import org.apache.commons.math3.random.Well19937c;
import org.apache.commons.math3.util.FastMath;

public class GeometricDistribution extends AbstractIntegerDistribution {
    private static final long serialVersionUID = 20130507;
    private final double log1mProbabilityOfSuccess;
    private final double logProbabilityOfSuccess;
    private final double probabilityOfSuccess;

    public int getSupportLowerBound() {
        return 0;
    }

    public int getSupportUpperBound() {
        return Integer.MAX_VALUE;
    }

    public boolean isSupportConnected() {
        return true;
    }

    public GeometricDistribution(double d) {
        this(new Well19937c(), d);
    }

    public GeometricDistribution(RandomGenerator randomGenerator, double d) {
        super(randomGenerator);
        if (d <= 0.0d || d > 1.0d) {
            throw new OutOfRangeException(LocalizedFormats.OUT_OF_RANGE_LEFT, Double.valueOf(d), 0, 1);
        }
        this.probabilityOfSuccess = d;
        this.logProbabilityOfSuccess = FastMath.log(d);
        this.log1mProbabilityOfSuccess = FastMath.log1p(-d);
    }

    public double getProbabilityOfSuccess() {
        return this.probabilityOfSuccess;
    }

    public double probability(int i) {
        if (i < 0) {
            return 0.0d;
        }
        return FastMath.exp(this.log1mProbabilityOfSuccess * ((double) i)) * this.probabilityOfSuccess;
    }

    public double logProbability(int i) {
        if (i < 0) {
            return Double.NEGATIVE_INFINITY;
        }
        return (((double) i) * this.log1mProbabilityOfSuccess) + this.logProbabilityOfSuccess;
    }

    public double cumulativeProbability(int i) {
        if (i < 0) {
            return 0.0d;
        }
        return -FastMath.expm1(this.log1mProbabilityOfSuccess * ((double) (i + 1)));
    }

    public double getNumericalMean() {
        double d = this.probabilityOfSuccess;
        return (1.0d - d) / d;
    }

    public double getNumericalVariance() {
        double d = this.probabilityOfSuccess;
        return (1.0d - d) / (d * d);
    }

    public int inverseCumulativeProbability(double d) throws OutOfRangeException {
        int i;
        if (d < 0.0d || d > 1.0d) {
            throw new OutOfRangeException(Double.valueOf(d), 0, 1);
        } else if (i == 0) {
            return Integer.MAX_VALUE;
        } else {
            if (d == 0.0d) {
                return 0;
            }
            return Math.max(0, (int) Math.ceil((FastMath.log1p(-d) / this.log1mProbabilityOfSuccess) - 1.0d));
        }
    }
}
