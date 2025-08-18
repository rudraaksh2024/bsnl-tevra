package org.apache.commons.math3.distribution;

import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.random.RandomGenerator;
import org.apache.commons.math3.random.Well19937c;
import org.apache.commons.math3.special.Beta;
import org.apache.commons.math3.special.Gamma;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.Precision;

public class BetaDistribution extends AbstractRealDistribution {
    public static final double DEFAULT_INVERSE_ABSOLUTE_ACCURACY = 1.0E-9d;
    private static final long serialVersionUID = -1221965979403477668L;
    private final double alpha;
    private final double beta;
    private final double solverAbsoluteAccuracy;
    private double z;

    public double getSupportLowerBound() {
        return 0.0d;
    }

    public double getSupportUpperBound() {
        return 1.0d;
    }

    public boolean isSupportConnected() {
        return true;
    }

    public boolean isSupportLowerBoundInclusive() {
        return false;
    }

    public boolean isSupportUpperBoundInclusive() {
        return false;
    }

    public BetaDistribution(double d, double d2) {
        this(d, d2, 1.0E-9d);
    }

    public BetaDistribution(double d, double d2, double d3) {
        this(new Well19937c(), d, d2, d3);
    }

    public BetaDistribution(RandomGenerator randomGenerator, double d, double d2) {
        this(randomGenerator, d, d2, 1.0E-9d);
    }

    public BetaDistribution(RandomGenerator randomGenerator, double d, double d2, double d3) {
        super(randomGenerator);
        this.alpha = d;
        this.beta = d2;
        this.z = Double.NaN;
        this.solverAbsoluteAccuracy = d3;
    }

    public double getAlpha() {
        return this.alpha;
    }

    public double getBeta() {
        return this.beta;
    }

    private void recomputeZ() {
        if (Double.isNaN(this.z)) {
            this.z = (Gamma.logGamma(this.alpha) + Gamma.logGamma(this.beta)) - Gamma.logGamma(this.alpha + this.beta);
        }
    }

    public double density(double d) {
        double logDensity = logDensity(d);
        if (logDensity == Double.NEGATIVE_INFINITY) {
            return 0.0d;
        }
        return FastMath.exp(logDensity);
    }

    public double logDensity(double d) {
        int i;
        recomputeZ();
        if (d < 0.0d || d > 1.0d) {
            return Double.NEGATIVE_INFINITY;
        }
        if (d == 0.0d) {
            if (this.alpha >= 1.0d) {
                return Double.NEGATIVE_INFINITY;
            }
            throw new NumberIsTooSmallException(LocalizedFormats.CANNOT_COMPUTE_BETA_DENSITY_AT_0_FOR_SOME_ALPHA, Double.valueOf(this.alpha), 1, false);
        } else if (i != 0) {
            return (((this.alpha - 1.0d) * FastMath.log(d)) + ((this.beta - 1.0d) * FastMath.log1p(-d))) - this.z;
        } else if (this.beta >= 1.0d) {
            return Double.NEGATIVE_INFINITY;
        } else {
            throw new NumberIsTooSmallException(LocalizedFormats.CANNOT_COMPUTE_BETA_DENSITY_AT_1_FOR_SOME_BETA, Double.valueOf(this.beta), 1, false);
        }
    }

    public double cumulativeProbability(double d) {
        if (d <= 0.0d) {
            return 0.0d;
        }
        if (d >= 1.0d) {
            return 1.0d;
        }
        return Beta.regularizedBeta(d, this.alpha, this.beta);
    }

    /* access modifiers changed from: protected */
    public double getSolverAbsoluteAccuracy() {
        return this.solverAbsoluteAccuracy;
    }

    public double getNumericalMean() {
        double alpha2 = getAlpha();
        return alpha2 / (getBeta() + alpha2);
    }

    public double getNumericalVariance() {
        double alpha2 = getAlpha();
        double beta2 = getBeta();
        double d = alpha2 + beta2;
        return (alpha2 * beta2) / ((d * d) * (d + 1.0d));
    }

    public double sample() {
        return ChengBetaSampler.sample(this.random, this.alpha, this.beta);
    }

    private static final class ChengBetaSampler {
        private ChengBetaSampler() {
        }

        static double sample(RandomGenerator randomGenerator, double d, double d2) {
            double min = FastMath.min(d, d2);
            double max = FastMath.max(d, d2);
            if (min > 1.0d) {
                return algorithmBB(randomGenerator, d, min, max);
            }
            return algorithmBC(randomGenerator, d, max, min);
        }

        private static double algorithmBB(RandomGenerator randomGenerator, double d, double d2, double d3) {
            double exp;
            double d4 = d2;
            double d5 = d4 + d3;
            double sqrt = FastMath.sqrt((d5 - 2.0d) / (((2.0d * d4) * d3) - d5));
            double d6 = (1.0d / sqrt) + d4;
            while (true) {
                double nextDouble = randomGenerator.nextDouble();
                double nextDouble2 = randomGenerator.nextDouble();
                double log = (FastMath.log(nextDouble) - FastMath.log1p(-nextDouble)) * sqrt;
                exp = FastMath.exp(log) * d4;
                double d7 = nextDouble * nextDouble * nextDouble2;
                double d8 = (log * d6) - 1.3862944d;
                double d9 = (d4 + d8) - exp;
                if (d9 + 2.609438d < 5.0d * d7) {
                    double log2 = FastMath.log(d7);
                    if (d9 >= log2 || d8 + ((FastMath.log(d5) - FastMath.log(d3 + exp)) * d5) >= log2) {
                        break;
                    }
                    double d10 = d;
                } else {
                    break;
                }
            }
            double min = FastMath.min(exp, Double.MAX_VALUE);
            return Precision.equals(d4, d) ? min / (d3 + min) : d3 / (d3 + min);
        }

        private static double algorithmBC(RandomGenerator randomGenerator, double d, double d2, double d3) {
            double d4;
            double d5 = d2;
            double d6 = d5 + d3;
            double d7 = 1.0d / d3;
            double d8 = (1.0d + d5) - d3;
            double d9 = (((0.0416667d * d3) + 0.0138889d) * d8) / ((d5 * d7) - 0.777778d);
            double d10 = (((0.25d / d8) + 0.5d) * d3) + 0.25d;
            while (true) {
                double nextDouble = randomGenerator.nextDouble();
                double nextDouble2 = randomGenerator.nextDouble();
                double d11 = nextDouble * nextDouble2;
                double d12 = nextDouble * d11;
                if (nextDouble < 0.5d) {
                    if (((nextDouble2 * 0.25d) + d12) - d11 >= d9) {
                        continue;
                    }
                } else if (d12 <= 0.25d) {
                    d4 = FastMath.exp(d7 * (FastMath.log(nextDouble) - FastMath.log1p(-nextDouble))) * d5;
                    break;
                } else if (d12 >= d10) {
                    continue;
                }
                double log = (FastMath.log(nextDouble) - FastMath.log1p(-nextDouble)) * d7;
                double exp = FastMath.exp(log) * d5;
                if ((((FastMath.log(d6) - FastMath.log(d3 + exp)) + log) * d6) - 1.3862944d >= FastMath.log(d12)) {
                    d4 = exp;
                    break;
                }
                double d13 = d;
            }
            double min = FastMath.min(d4, Double.MAX_VALUE);
            return Precision.equals(d5, d) ? min / (d3 + min) : d3 / (d3 + min);
        }
    }
}
