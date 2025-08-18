package org.apache.commons.math3.fitting;

import org.apache.commons.math3.analysis.function.HarmonicOscillator;
import org.apache.commons.math3.exception.MathIllegalStateException;
import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.exception.ZeroException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.optim.nonlinear.vector.MultivariateVectorOptimizer;
import org.apache.commons.math3.util.FastMath;

@Deprecated
public class HarmonicFitter extends CurveFitter<HarmonicOscillator.Parametric> {
    public HarmonicFitter(MultivariateVectorOptimizer multivariateVectorOptimizer) {
        super(multivariateVectorOptimizer);
    }

    public double[] fit(double[] dArr) {
        return fit(new HarmonicOscillator.Parametric(), dArr);
    }

    public double[] fit() {
        return fit(new ParameterGuesser(getObservations()).guess());
    }

    public static class ParameterGuesser {
        private final double a;
        private final double omega;
        private final double phi;

        public ParameterGuesser(WeightedObservedPoint[] weightedObservedPointArr) {
            if (weightedObservedPointArr.length >= 4) {
                WeightedObservedPoint[] sortObservations = sortObservations(weightedObservedPointArr);
                double[] guessAOmega = guessAOmega(sortObservations);
                this.a = guessAOmega[0];
                this.omega = guessAOmega[1];
                this.phi = guessPhi(sortObservations);
                return;
            }
            throw new NumberIsTooSmallException(LocalizedFormats.INSUFFICIENT_OBSERVED_POINTS_IN_SAMPLE, Integer.valueOf(weightedObservedPointArr.length), 4, true);
        }

        public double[] guess() {
            return new double[]{this.a, this.omega, this.phi};
        }

        private WeightedObservedPoint[] sortObservations(WeightedObservedPoint[] weightedObservedPointArr) {
            WeightedObservedPoint[] weightedObservedPointArr2 = (WeightedObservedPoint[]) weightedObservedPointArr.clone();
            WeightedObservedPoint weightedObservedPoint = weightedObservedPointArr2[0];
            for (int i = 1; i < weightedObservedPointArr2.length; i++) {
                WeightedObservedPoint weightedObservedPoint2 = weightedObservedPointArr2[i];
                if (weightedObservedPoint2.getX() < weightedObservedPoint.getX()) {
                    int i2 = i - 1;
                    WeightedObservedPoint weightedObservedPoint3 = weightedObservedPointArr2[i2];
                    while (i2 >= 0 && weightedObservedPoint2.getX() < weightedObservedPoint3.getX()) {
                        weightedObservedPointArr2[i2 + 1] = weightedObservedPoint3;
                        int i3 = i2 - 1;
                        if (i2 != 0) {
                            weightedObservedPoint3 = weightedObservedPointArr2[i3];
                        }
                        i2 = i3;
                    }
                    weightedObservedPointArr2[i2 + 1] = weightedObservedPoint2;
                    weightedObservedPoint = weightedObservedPointArr2[i];
                } else {
                    weightedObservedPoint = weightedObservedPoint2;
                }
            }
            return weightedObservedPointArr2;
        }

        private double[] guessAOmega(WeightedObservedPoint[] weightedObservedPointArr) {
            WeightedObservedPoint[] weightedObservedPointArr2 = weightedObservedPointArr;
            double[] dArr = new double[2];
            double x = weightedObservedPointArr2[0].getX();
            double y = weightedObservedPointArr2[0].getY();
            double d = x;
            double d2 = 0.0d;
            double d3 = 0.0d;
            double d4 = 0.0d;
            double d5 = 0.0d;
            double d6 = 0.0d;
            double d7 = 0.0d;
            double d8 = 0.0d;
            int i = 1;
            while (i < weightedObservedPointArr2.length) {
                double x2 = weightedObservedPointArr2[i].getX();
                double y2 = weightedObservedPointArr2[i].getY();
                double d9 = x2 - d;
                double d10 = y2 - y;
                double d11 = x2 - x;
                d7 += ((((y * y) + (y * y2)) + (y2 * y2)) * d9) / 3.0d;
                d8 += (d10 * d10) / d9;
                d6 += d11 * d11;
                d2 += d7 * d7;
                d4 += d11 * d7;
                d3 += d11 * d8;
                d5 += d7 * d8;
                i++;
                d = x2;
                y = y2;
            }
            double d12 = (d3 * d4) - (d5 * d6);
            double d13 = (d6 * d2) - (d4 * d4);
            double d14 = ((d2 * d3) - (d4 * d5)) / d12;
            if (d14 >= 0.0d) {
                double d15 = d12 / d13;
                if (d15 >= 0.0d) {
                    if (d12 != 0.0d) {
                        dArr[0] = FastMath.sqrt(d14);
                        dArr[1] = FastMath.sqrt(d15);
                        return dArr;
                    }
                    throw new MathIllegalStateException(LocalizedFormats.ZERO_DENOMINATOR, new Object[0]);
                }
            }
            double x3 = weightedObservedPointArr2[weightedObservedPointArr2.length - 1].getX() - weightedObservedPointArr2[0].getX();
            if (x3 != 0.0d) {
                dArr[1] = 6.283185307179586d / x3;
                double d16 = Double.POSITIVE_INFINITY;
                double d17 = Double.NEGATIVE_INFINITY;
                for (int i2 = 1; i2 < weightedObservedPointArr2.length; i2++) {
                    double y3 = weightedObservedPointArr2[i2].getY();
                    if (y3 < d16) {
                        d16 = y3;
                    }
                    if (y3 > d17) {
                        d17 = y3;
                    }
                }
                dArr[0] = (d17 - d16) * 0.5d;
                return dArr;
            }
            throw new ZeroException();
        }

        private double guessPhi(WeightedObservedPoint[] weightedObservedPointArr) {
            WeightedObservedPoint[] weightedObservedPointArr2 = weightedObservedPointArr;
            double x = weightedObservedPointArr2[0].getX();
            double y = weightedObservedPointArr2[0].getY();
            double d = 0.0d;
            int i = 1;
            double d2 = 0.0d;
            while (i < weightedObservedPointArr2.length) {
                double x2 = weightedObservedPointArr2[i].getX();
                double y2 = weightedObservedPointArr2[i].getY();
                double d3 = (y2 - y) / (x2 - x);
                double d4 = this.omega * x2;
                double cos = FastMath.cos(d4);
                double sin = FastMath.sin(d4);
                double d5 = x2;
                double d6 = this.omega;
                d2 += ((d6 * y2) * cos) - (d3 * sin);
                d += (d6 * y2 * sin) + (d3 * cos);
                i++;
                y = y2;
                x = d5;
            }
            return FastMath.atan2(-d, d2);
        }
    }
}
