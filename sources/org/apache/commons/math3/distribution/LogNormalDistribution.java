package org.apache.commons.math3.distribution;

import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import org.apache.commons.math3.exception.NumberIsTooLargeException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.random.RandomGenerator;
import org.apache.commons.math3.random.Well19937c;
import org.apache.commons.math3.special.Erf;
import org.apache.commons.math3.util.FastMath;

public class LogNormalDistribution extends AbstractRealDistribution {
    public static final double DEFAULT_INVERSE_ABSOLUTE_ACCURACY = 1.0E-9d;
    private static final double SQRT2 = FastMath.sqrt(2.0d);
    private static final double SQRT2PI = FastMath.sqrt(6.283185307179586d);
    private static final long serialVersionUID = 20120112;
    private final double logShapePlusHalfLog2Pi;
    private final double scale;
    private final double shape;
    private final double solverAbsoluteAccuracy;

    public double getSupportLowerBound() {
        return 0.0d;
    }

    public double getSupportUpperBound() {
        return Double.POSITIVE_INFINITY;
    }

    public boolean isSupportConnected() {
        return true;
    }

    public boolean isSupportLowerBoundInclusive() {
        return true;
    }

    public boolean isSupportUpperBoundInclusive() {
        return false;
    }

    public LogNormalDistribution() {
        this(0.0d, 1.0d);
    }

    public LogNormalDistribution(double d, double d2) throws NotStrictlyPositiveException {
        this(d, d2, 1.0E-9d);
    }

    public LogNormalDistribution(double d, double d2, double d3) throws NotStrictlyPositiveException {
        this(new Well19937c(), d, d2, d3);
    }

    public LogNormalDistribution(RandomGenerator randomGenerator, double d, double d2) throws NotStrictlyPositiveException {
        this(randomGenerator, d, d2, 1.0E-9d);
    }

    public LogNormalDistribution(RandomGenerator randomGenerator, double d, double d2, double d3) throws NotStrictlyPositiveException {
        super(randomGenerator);
        if (d2 > 0.0d) {
            this.scale = d;
            this.shape = d2;
            this.logShapePlusHalfLog2Pi = FastMath.log(d2) + (FastMath.log(6.283185307179586d) * 0.5d);
            this.solverAbsoluteAccuracy = d3;
            return;
        }
        throw new NotStrictlyPositiveException(LocalizedFormats.SHAPE, Double.valueOf(d2));
    }

    public double getScale() {
        return this.scale;
    }

    public double getShape() {
        return this.shape;
    }

    public double density(double d) {
        if (d <= 0.0d) {
            return 0.0d;
        }
        double log = (FastMath.log(d) - this.scale) / this.shape;
        return FastMath.exp((-0.5d * log) * log) / ((this.shape * SQRT2PI) * d);
    }

    public double logDensity(double d) {
        if (d <= 0.0d) {
            return Double.NEGATIVE_INFINITY;
        }
        double log = FastMath.log(d);
        double d2 = (log - this.scale) / this.shape;
        return ((-0.5d * d2) * d2) - (this.logShapePlusHalfLog2Pi + log);
    }

    public double cumulativeProbability(double d) {
        if (d <= 0.0d) {
            return 0.0d;
        }
        double log = FastMath.log(d) - this.scale;
        double abs = FastMath.abs(log);
        double d2 = this.shape;
        if (abs <= 40.0d * d2) {
            return (Erf.erf(log / (d2 * SQRT2)) * 0.5d) + 0.5d;
        }
        if (log < 0.0d) {
            return 0.0d;
        }
        return 1.0d;
    }

    @Deprecated
    public double cumulativeProbability(double d, double d2) throws NumberIsTooLargeException {
        return probability(d, d2);
    }

    public double probability(double d, double d2) throws NumberIsTooLargeException {
        if (d > d2) {
            throw new NumberIsTooLargeException(LocalizedFormats.LOWER_ENDPOINT_ABOVE_UPPER_ENDPOINT, Double.valueOf(d), Double.valueOf(d2), true);
        } else if (d <= 0.0d || d2 <= 0.0d) {
            return super.probability(d, d2);
        } else {
            double d3 = this.shape * SQRT2;
            return Erf.erf((FastMath.log(d) - this.scale) / d3, (FastMath.log(d2) - this.scale) / d3) * 0.5d;
        }
    }

    /* access modifiers changed from: protected */
    public double getSolverAbsoluteAccuracy() {
        return this.solverAbsoluteAccuracy;
    }

    public double getNumericalMean() {
        double d = this.shape;
        return FastMath.exp(this.scale + ((d * d) / 2.0d));
    }

    public double getNumericalVariance() {
        double d = this.shape;
        double d2 = d * d;
        return FastMath.expm1(d2) * FastMath.exp((this.scale * 2.0d) + d2);
    }

    public double sample() {
        return FastMath.exp(this.scale + (this.shape * this.random.nextGaussian()));
    }
}
