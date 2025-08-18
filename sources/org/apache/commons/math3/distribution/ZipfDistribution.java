package org.apache.commons.math3.distribution;

import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.random.RandomGenerator;
import org.apache.commons.math3.random.Well19937c;
import org.apache.commons.math3.util.FastMath;

public class ZipfDistribution extends AbstractIntegerDistribution {
    private static final long serialVersionUID = -140627372283420404L;
    private final double exponent;
    private final int numberOfElements;
    private double numericalMean;
    private boolean numericalMeanIsCalculated;
    private double numericalVariance;
    private boolean numericalVarianceIsCalculated;
    private transient ZipfRejectionInversionSampler sampler;

    public int getSupportLowerBound() {
        return 1;
    }

    public boolean isSupportConnected() {
        return true;
    }

    public ZipfDistribution(int i, double d) {
        this(new Well19937c(), i, d);
    }

    public ZipfDistribution(RandomGenerator randomGenerator, int i, double d) throws NotStrictlyPositiveException {
        super(randomGenerator);
        this.numericalMean = Double.NaN;
        this.numericalMeanIsCalculated = false;
        this.numericalVariance = Double.NaN;
        this.numericalVarianceIsCalculated = false;
        if (i <= 0) {
            throw new NotStrictlyPositiveException(LocalizedFormats.DIMENSION, Integer.valueOf(i));
        } else if (d > 0.0d) {
            this.numberOfElements = i;
            this.exponent = d;
        } else {
            throw new NotStrictlyPositiveException(LocalizedFormats.EXPONENT, Double.valueOf(d));
        }
    }

    public int getNumberOfElements() {
        return this.numberOfElements;
    }

    public double getExponent() {
        return this.exponent;
    }

    public double probability(int i) {
        if (i <= 0 || i > this.numberOfElements) {
            return 0.0d;
        }
        return (1.0d / FastMath.pow((double) i, this.exponent)) / generalizedHarmonic(this.numberOfElements, this.exponent);
    }

    public double logProbability(int i) {
        if (i <= 0 || i > this.numberOfElements) {
            return Double.NEGATIVE_INFINITY;
        }
        double d = this.exponent;
        return ((-FastMath.log((double) i)) * d) - FastMath.log(generalizedHarmonic(this.numberOfElements, d));
    }

    public double cumulativeProbability(int i) {
        if (i <= 0) {
            return 0.0d;
        }
        if (i >= this.numberOfElements) {
            return 1.0d;
        }
        return generalizedHarmonic(i, this.exponent) / generalizedHarmonic(this.numberOfElements, this.exponent);
    }

    public double getNumericalMean() {
        if (!this.numericalMeanIsCalculated) {
            this.numericalMean = calculateNumericalMean();
            this.numericalMeanIsCalculated = true;
        }
        return this.numericalMean;
    }

    /* access modifiers changed from: protected */
    public double calculateNumericalMean() {
        int numberOfElements2 = getNumberOfElements();
        double exponent2 = getExponent();
        return generalizedHarmonic(numberOfElements2, exponent2 - 1.0d) / generalizedHarmonic(numberOfElements2, exponent2);
    }

    public double getNumericalVariance() {
        if (!this.numericalVarianceIsCalculated) {
            this.numericalVariance = calculateNumericalVariance();
            this.numericalVarianceIsCalculated = true;
        }
        return this.numericalVariance;
    }

    /* access modifiers changed from: protected */
    public double calculateNumericalVariance() {
        int numberOfElements2 = getNumberOfElements();
        double exponent2 = getExponent();
        double generalizedHarmonic = generalizedHarmonic(numberOfElements2, exponent2 - 2.0d);
        double generalizedHarmonic2 = generalizedHarmonic(numberOfElements2, exponent2 - 1.0d);
        double generalizedHarmonic3 = generalizedHarmonic(numberOfElements2, exponent2);
        return (generalizedHarmonic / generalizedHarmonic3) - ((generalizedHarmonic2 * generalizedHarmonic2) / (generalizedHarmonic3 * generalizedHarmonic3));
    }

    private double generalizedHarmonic(int i, double d) {
        double d2 = 0.0d;
        while (i > 0) {
            d2 += 1.0d / FastMath.pow((double) i, d);
            i--;
        }
        return d2;
    }

    public int getSupportUpperBound() {
        return getNumberOfElements();
    }

    public int sample() {
        if (this.sampler == null) {
            this.sampler = new ZipfRejectionInversionSampler(this.numberOfElements, this.exponent);
        }
        return this.sampler.sample(this.random);
    }

    static final class ZipfRejectionInversionSampler {
        private final double exponent;
        private final double hIntegralNumberOfElements;
        private final double hIntegralX1 = (hIntegral(1.5d) - 1.0d);
        private final int numberOfElements;
        private final double s;

        ZipfRejectionInversionSampler(int i, double d) {
            this.exponent = d;
            this.numberOfElements = i;
            this.hIntegralNumberOfElements = hIntegral(((double) i) + 0.5d);
            this.s = 2.0d - hIntegralInverse(hIntegral(2.5d) - h(2.0d));
        }

        /* access modifiers changed from: package-private */
        /* JADX WARNING: Removed duplicated region for block: B:0:0x0000 A[LOOP_START, MTH_ENTER_BLOCK] */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public int sample(org.apache.commons.math3.random.RandomGenerator r12) {
            /*
                r11 = this;
            L_0x0000:
                double r0 = r11.hIntegralNumberOfElements
                double r2 = r12.nextDouble()
                double r4 = r11.hIntegralX1
                double r6 = r11.hIntegralNumberOfElements
                double r4 = r4 - r6
                double r2 = r2 * r4
                double r0 = r0 + r2
                double r2 = r11.hIntegralInverse(r0)
                r4 = 4602678819172646912(0x3fe0000000000000, double:0.5)
                double r6 = r2 + r4
                int r6 = (int) r6
                r7 = 1
                if (r6 >= r7) goto L_0x001b
            L_0x0019:
                r6 = r7
                goto L_0x0020
            L_0x001b:
                int r7 = r11.numberOfElements
                if (r6 <= r7) goto L_0x0020
                goto L_0x0019
            L_0x0020:
                double r7 = (double) r6
                double r2 = r7 - r2
                double r9 = r11.s
                int r2 = (r2 > r9 ? 1 : (r2 == r9 ? 0 : -1))
                if (r2 <= 0) goto L_0x0037
                double r4 = r4 + r7
                double r2 = r11.hIntegral(r4)
                double r4 = r11.h(r7)
                double r2 = r2 - r4
                int r0 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
                if (r0 < 0) goto L_0x0000
            L_0x0037:
                return r6
            */
            throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.math3.distribution.ZipfDistribution.ZipfRejectionInversionSampler.sample(org.apache.commons.math3.random.RandomGenerator):int");
        }

        private double hIntegral(double d) {
            double log = FastMath.log(d);
            return helper2((1.0d - this.exponent) * log) * log;
        }

        private double h(double d) {
            return FastMath.exp((-this.exponent) * FastMath.log(d));
        }

        private double hIntegralInverse(double d) {
            double d2 = (1.0d - this.exponent) * d;
            if (d2 < -1.0d) {
                d2 = -1.0d;
            }
            return FastMath.exp(helper1(d2) * d);
        }

        static double helper1(double d) {
            return FastMath.abs(d) > 1.0E-8d ? FastMath.log1p(d) / d : 1.0d - (d * (0.5d - ((0.3333333333333333d - (0.25d * d)) * d)));
        }

        static double helper2(double d) {
            return FastMath.abs(d) > 1.0E-8d ? FastMath.expm1(d) / d : (0.5d * d * ((0.3333333333333333d * d * ((d * 0.25d) + 1.0d)) + 1.0d)) + 1.0d;
        }
    }
}
