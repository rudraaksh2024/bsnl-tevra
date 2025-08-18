package org.apache.commons.math3.fitting;

import java.util.Collection;
import org.apache.commons.math3.analysis.function.HarmonicOscillator;
import org.apache.commons.math3.exception.MathIllegalStateException;
import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.exception.ZeroException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.fitting.AbstractCurveFitter;
import org.apache.commons.math3.fitting.leastsquares.LeastSquaresBuilder;
import org.apache.commons.math3.fitting.leastsquares.LeastSquaresProblem;
import org.apache.commons.math3.linear.DiagonalMatrix;
import org.apache.commons.math3.util.FastMath;

public class HarmonicCurveFitter extends AbstractCurveFitter {
    private static final HarmonicOscillator.Parametric FUNCTION = new HarmonicOscillator.Parametric();
    private final double[] initialGuess;
    private final int maxIter;

    private HarmonicCurveFitter(double[] dArr, int i) {
        this.initialGuess = dArr;
        this.maxIter = i;
    }

    public static HarmonicCurveFitter create() {
        return new HarmonicCurveFitter((double[]) null, Integer.MAX_VALUE);
    }

    public HarmonicCurveFitter withStartPoint(double[] dArr) {
        return new HarmonicCurveFitter((double[]) dArr.clone(), this.maxIter);
    }

    public HarmonicCurveFitter withMaxIterations(int i) {
        return new HarmonicCurveFitter(this.initialGuess, i);
    }

    /* access modifiers changed from: protected */
    public LeastSquaresProblem getProblem(Collection<WeightedObservedPoint> collection) {
        int size = collection.size();
        double[] dArr = new double[size];
        double[] dArr2 = new double[size];
        int i = 0;
        for (WeightedObservedPoint next : collection) {
            dArr[i] = next.getY();
            dArr2[i] = next.getWeight();
            i++;
        }
        AbstractCurveFitter.TheoreticalValuesFunction theoreticalValuesFunction = new AbstractCurveFitter.TheoreticalValuesFunction(FUNCTION, collection);
        double[] dArr3 = this.initialGuess;
        if (dArr3 == null) {
            dArr3 = new ParameterGuesser(collection).guess();
        }
        return new LeastSquaresBuilder().maxEvaluations(Integer.MAX_VALUE).maxIterations(this.maxIter).start(dArr3).target(dArr).weight(new DiagonalMatrix(dArr2)).model(theoreticalValuesFunction.getModelFunction(), theoreticalValuesFunction.getModelFunctionJacobian()).build();
    }

    public static class ParameterGuesser {
        private final double a;
        private final double omega;
        private final double phi;

        public ParameterGuesser(Collection<WeightedObservedPoint> collection) {
            if (collection.size() >= 4) {
                WeightedObservedPoint[] weightedObservedPointArr = (WeightedObservedPoint[]) sortObservations(collection).toArray(new WeightedObservedPoint[0]);
                double[] guessAOmega = guessAOmega(weightedObservedPointArr);
                this.a = guessAOmega[0];
                this.omega = guessAOmega[1];
                this.phi = guessPhi(weightedObservedPointArr);
                return;
            }
            throw new NumberIsTooSmallException(LocalizedFormats.INSUFFICIENT_OBSERVED_POINTS_IN_SAMPLE, Integer.valueOf(collection.size()), 4, true);
        }

        public double[] guess() {
            return new double[]{this.a, this.omega, this.phi};
        }

        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v14, resolved type: java.lang.Object} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v5, resolved type: org.apache.commons.math3.fitting.WeightedObservedPoint} */
        /* JADX WARNING: Multi-variable type inference failed */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        private java.util.List<org.apache.commons.math3.fitting.WeightedObservedPoint> sortObservations(java.util.Collection<org.apache.commons.math3.fitting.WeightedObservedPoint> r9) {
            /*
                r8 = this;
                java.util.ArrayList r8 = new java.util.ArrayList
                r8.<init>(r9)
                r9 = 0
                java.lang.Object r9 = r8.get(r9)
                org.apache.commons.math3.fitting.WeightedObservedPoint r9 = (org.apache.commons.math3.fitting.WeightedObservedPoint) r9
                int r0 = r8.size()
                r1 = 1
            L_0x0011:
                if (r1 >= r0) goto L_0x005d
                java.lang.Object r2 = r8.get(r1)
                org.apache.commons.math3.fitting.WeightedObservedPoint r2 = (org.apache.commons.math3.fitting.WeightedObservedPoint) r2
                double r3 = r2.getX()
                double r5 = r9.getX()
                int r9 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
                if (r9 >= 0) goto L_0x0059
                int r9 = r1 + -1
                java.lang.Object r3 = r8.get(r9)
                org.apache.commons.math3.fitting.WeightedObservedPoint r3 = (org.apache.commons.math3.fitting.WeightedObservedPoint) r3
            L_0x002d:
                if (r9 < 0) goto L_0x004d
                double r4 = r2.getX()
                double r6 = r3.getX()
                int r4 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
                if (r4 >= 0) goto L_0x004d
                int r4 = r9 + 1
                r8.set(r4, r3)
                int r4 = r9 + -1
                if (r9 == 0) goto L_0x004b
                java.lang.Object r9 = r8.get(r4)
                r3 = r9
                org.apache.commons.math3.fitting.WeightedObservedPoint r3 = (org.apache.commons.math3.fitting.WeightedObservedPoint) r3
            L_0x004b:
                r9 = r4
                goto L_0x002d
            L_0x004d:
                int r9 = r9 + 1
                r8.set(r9, r2)
                java.lang.Object r9 = r8.get(r1)
                org.apache.commons.math3.fitting.WeightedObservedPoint r9 = (org.apache.commons.math3.fitting.WeightedObservedPoint) r9
                goto L_0x005a
            L_0x0059:
                r9 = r2
            L_0x005a:
                int r1 = r1 + 1
                goto L_0x0011
            L_0x005d:
                return r8
            */
            throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.math3.fitting.HarmonicCurveFitter.ParameterGuesser.sortObservations(java.util.Collection):java.util.List");
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
