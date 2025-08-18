package org.apache.commons.math3.analysis.interpolation;

import java.lang.reflect.Array;
import org.apache.commons.math3.analysis.BivariateFunction;
import org.apache.commons.math3.exception.OutOfRangeException;

/* compiled from: BicubicSplineInterpolatingFunction */
class BicubicSplineFunction implements BivariateFunction {
    private static final short N = 4;
    private final double[][] a;
    private final BivariateFunction partialDerivativeX;
    private final BivariateFunction partialDerivativeXX;
    private final BivariateFunction partialDerivativeXY;
    private final BivariateFunction partialDerivativeY;
    private final BivariateFunction partialDerivativeYY;

    BicubicSplineFunction(double[] dArr) {
        this(dArr, false);
    }

    BicubicSplineFunction(double[] dArr, boolean z) {
        int i;
        this.a = (double[][]) Array.newInstance(Double.TYPE, new int[]{4, 4});
        int i2 = 0;
        while (true) {
            i = 4;
            if (i2 >= 4) {
                break;
            }
            for (int i3 = 0; i3 < 4; i3++) {
                this.a[i2][i3] = dArr[(i2 * 4) + i3];
            }
            i2++;
        }
        if (z) {
            final double[][] dArr2 = (double[][]) Array.newInstance(Double.TYPE, new int[]{4, 4});
            final double[][] dArr3 = (double[][]) Array.newInstance(Double.TYPE, new int[]{4, 4});
            final double[][] dArr4 = (double[][]) Array.newInstance(Double.TYPE, new int[]{4, 4});
            final double[][] dArr5 = (double[][]) Array.newInstance(Double.TYPE, new int[]{4, 4});
            final double[][] dArr6 = (double[][]) Array.newInstance(Double.TYPE, new int[]{4, 4});
            int i4 = 0;
            while (i4 < i) {
                int i5 = 0;
                while (i5 < i) {
                    double d = this.a[i4][i5];
                    double[] dArr7 = dArr2[i4];
                    dArr7[i5] = ((double) i4) * d;
                    double[] dArr8 = dArr3[i4];
                    double d2 = (double) i5;
                    dArr8[i5] = d * d2;
                    dArr4[i4][i5] = ((double) (i4 - 1)) * dArr7[i5];
                    dArr5[i4][i5] = ((double) (i5 - 1)) * dArr8[i5];
                    dArr6[i4][i5] = d2 * dArr7[i5];
                    i5++;
                    dArr3 = dArr3;
                    i = 4;
                }
                double[][] dArr9 = dArr3;
                i4++;
                i = 4;
            }
            double[][] dArr10 = dArr3;
            this.partialDerivativeX = new BivariateFunction() {
                public double value(double d, double d2) {
                    double d3 = d2 * d2;
                    return BicubicSplineFunction.this.apply(new double[]{0.0d, 1.0d, d, d * d}, new double[]{1.0d, d2, d3, d3 * d2}, dArr2);
                }
            };
            this.partialDerivativeY = new BivariateFunction() {
                public double value(double d, double d2) {
                    double d3 = d * d;
                    return BicubicSplineFunction.this.apply(new double[]{1.0d, d, d3, d3 * d}, new double[]{0.0d, 1.0d, d2, d2 * d2}, dArr3);
                }
            };
            this.partialDerivativeXX = new BivariateFunction() {
                public double value(double d, double d2) {
                    double[] dArr = {0.0d, 0.0d, 1.0d, d};
                    double d3 = d2 * d2;
                    return BicubicSplineFunction.this.apply(dArr, new double[]{1.0d, d2, d3, d3 * d2}, dArr4);
                }
            };
            this.partialDerivativeYY = new BivariateFunction() {
                public double value(double d, double d2) {
                    double d3 = d * d;
                    return BicubicSplineFunction.this.apply(new double[]{1.0d, d, d3, d3 * d}, new double[]{0.0d, 0.0d, 1.0d, d2}, dArr5);
                }
            };
            this.partialDerivativeXY = new BivariateFunction() {
                public double value(double d, double d2) {
                    return BicubicSplineFunction.this.apply(new double[]{0.0d, 1.0d, d, d * d}, new double[]{0.0d, 1.0d, d2, d2 * d2}, dArr6);
                }
            };
            return;
        }
        this.partialDerivativeX = null;
        this.partialDerivativeY = null;
        this.partialDerivativeXX = null;
        this.partialDerivativeYY = null;
        this.partialDerivativeXY = null;
    }

    public double value(double d, double d2) {
        if (d < 0.0d || d > 1.0d) {
            throw new OutOfRangeException(Double.valueOf(d), 0, 1);
        } else if (d2 < 0.0d || d2 > 1.0d) {
            throw new OutOfRangeException(Double.valueOf(d2), 0, 1);
        } else {
            double d3 = d * d;
            double[] dArr = {1.0d, d, d3, d3 * d};
            double d4 = d2 * d2;
            return apply(dArr, new double[]{1.0d, d2, d4, d4 * d2}, this.a);
        }
    }

    /* access modifiers changed from: private */
    public double apply(double[] dArr, double[] dArr2, double[][] dArr3) {
        double d = 0.0d;
        for (int i = 0; i < 4; i++) {
            for (int i2 = 0; i2 < 4; i2++) {
                d += dArr3[i][i2] * dArr[i] * dArr2[i2];
            }
        }
        return d;
    }

    public BivariateFunction partialDerivativeX() {
        return this.partialDerivativeX;
    }

    public BivariateFunction partialDerivativeY() {
        return this.partialDerivativeY;
    }

    public BivariateFunction partialDerivativeXX() {
        return this.partialDerivativeXX;
    }

    public BivariateFunction partialDerivativeYY() {
        return this.partialDerivativeYY;
    }

    public BivariateFunction partialDerivativeXY() {
        return this.partialDerivativeXY;
    }
}
