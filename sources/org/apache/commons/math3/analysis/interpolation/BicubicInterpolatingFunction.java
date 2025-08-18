package org.apache.commons.math3.analysis.interpolation;

import java.lang.reflect.Array;
import java.util.Arrays;
import org.apache.commons.math3.analysis.BivariateFunction;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.NoDataException;
import org.apache.commons.math3.exception.NonMonotonicSequenceException;
import org.apache.commons.math3.exception.OutOfRangeException;
import org.apache.commons.math3.util.MathArrays;

public class BicubicInterpolatingFunction implements BivariateFunction {
    private static final double[][] AINV = {new double[]{1.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d}, new double[]{0.0d, 0.0d, 0.0d, 0.0d, 1.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d}, new double[]{-3.0d, 3.0d, 0.0d, 0.0d, -2.0d, -1.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d}, new double[]{2.0d, -2.0d, 0.0d, 0.0d, 1.0d, 1.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d}, new double[]{0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 1.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d}, new double[]{0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 1.0d, 0.0d, 0.0d, 0.0d}, new double[]{0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, -3.0d, 3.0d, 0.0d, 0.0d, -2.0d, -1.0d, 0.0d, 0.0d}, new double[]{0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 2.0d, -2.0d, 0.0d, 0.0d, 1.0d, 1.0d, 0.0d, 0.0d}, new double[]{-3.0d, 0.0d, 3.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, -2.0d, 0.0d, -1.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d}, new double[]{0.0d, 0.0d, 0.0d, 0.0d, -3.0d, 0.0d, 3.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, -2.0d, 0.0d, -1.0d, 0.0d}, new double[]{9.0d, -9.0d, -9.0d, 9.0d, 6.0d, 3.0d, -6.0d, -3.0d, 6.0d, -6.0d, 3.0d, -3.0d, 4.0d, 2.0d, 2.0d, 1.0d}, new double[]{-6.0d, 6.0d, 6.0d, -6.0d, -3.0d, -3.0d, 3.0d, 3.0d, -4.0d, 4.0d, -2.0d, 2.0d, -2.0d, -2.0d, -1.0d, -1.0d}, new double[]{2.0d, 0.0d, -2.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 1.0d, 0.0d, 1.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d}, new double[]{0.0d, 0.0d, 0.0d, 0.0d, 2.0d, 0.0d, -2.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 1.0d, 0.0d, 1.0d, 0.0d}, new double[]{-6.0d, 6.0d, 6.0d, -6.0d, -4.0d, -2.0d, 4.0d, 2.0d, -3.0d, 3.0d, -3.0d, 3.0d, -2.0d, -1.0d, -2.0d, -1.0d}, new double[]{4.0d, -4.0d, -4.0d, 4.0d, 2.0d, 2.0d, -2.0d, -2.0d, 2.0d, -2.0d, 2.0d, -2.0d, 1.0d, 1.0d, 1.0d, 1.0d}};
    private static final int NUM_COEFF = 16;
    private final BicubicFunction[][] splines;
    private final double[] xval;
    private final double[] yval;

    public BicubicInterpolatingFunction(double[] dArr, double[] dArr2, double[][] dArr3, double[][] dArr4, double[][] dArr5, double[][] dArr6) throws DimensionMismatchException, NoDataException, NonMonotonicSequenceException {
        double[][] dArr7 = dArr3;
        double[][] dArr8 = dArr4;
        double[][] dArr9 = dArr5;
        double[][] dArr10 = dArr6;
        int length = dArr.length;
        int length2 = dArr2.length;
        if (!(length == 0 || length2 == 0 || dArr7.length == 0)) {
            int i = 0;
            if (dArr7[0].length != 0) {
                if (length != dArr7.length) {
                    throw new DimensionMismatchException(length, dArr7.length);
                } else if (length != dArr8.length) {
                    throw new DimensionMismatchException(length, dArr8.length);
                } else if (length != dArr9.length) {
                    throw new DimensionMismatchException(length, dArr9.length);
                } else if (length == dArr10.length) {
                    MathArrays.checkOrder(dArr);
                    MathArrays.checkOrder(dArr2);
                    this.xval = (double[]) dArr.clone();
                    this.yval = (double[]) dArr2.clone();
                    char c = 1;
                    int i2 = length - 1;
                    int i3 = length2 - 1;
                    int[] iArr = new int[2];
                    iArr[1] = i3;
                    iArr[0] = i2;
                    this.splines = (BicubicFunction[][]) Array.newInstance(BicubicFunction.class, iArr);
                    int i4 = 0;
                    while (i4 < i2) {
                        if (dArr7[i4].length != length2) {
                            throw new DimensionMismatchException(dArr7[i4].length, length2);
                        } else if (dArr8[i4].length != length2) {
                            throw new DimensionMismatchException(dArr8[i4].length, length2);
                        } else if (dArr9[i4].length != length2) {
                            throw new DimensionMismatchException(dArr9[i4].length, length2);
                        } else if (dArr10[i4].length == length2) {
                            int i5 = i4 + 1;
                            double[] dArr11 = this.xval;
                            double d = dArr11[i5] - dArr11[i4];
                            int i6 = i;
                            while (i6 < i3) {
                                int i7 = i6 + 1;
                                double[] dArr12 = this.yval;
                                double d2 = dArr12[i7] - dArr12[i6];
                                double d3 = d * d2;
                                double[] dArr13 = new double[16];
                                double[] dArr14 = dArr7[i4];
                                dArr13[i] = dArr14[i6];
                                double[] dArr15 = dArr7[i5];
                                dArr13[c] = dArr15[i6];
                                dArr13[2] = dArr14[i7];
                                dArr13[3] = dArr15[i7];
                                double[] dArr16 = dArr8[i4];
                                dArr13[4] = dArr16[i6] * d;
                                double[] dArr17 = dArr8[i5];
                                dArr13[5] = dArr17[i6] * d;
                                dArr13[6] = dArr16[i7] * d;
                                dArr13[7] = dArr17[i7] * d;
                                double[] dArr18 = dArr9[i4];
                                dArr13[8] = dArr18[i6] * d2;
                                double[] dArr19 = dArr9[i5];
                                dArr13[9] = dArr19[i6] * d2;
                                dArr13[10] = dArr18[i7] * d2;
                                dArr13[11] = dArr19[i7] * d2;
                                double[] dArr20 = dArr10[i4];
                                dArr13[12] = dArr20[i6] * d3;
                                double[] dArr21 = dArr10[i5];
                                dArr13[13] = dArr21[i6] * d3;
                                dArr13[14] = dArr20[i7] * d3;
                                dArr13[15] = dArr21[i7] * d3;
                                this.splines[i4][i6] = new BicubicFunction(computeSplineCoefficients(dArr13));
                                i6 = i7;
                                c = 1;
                                i = 0;
                            }
                            i4 = i5;
                        } else {
                            throw new DimensionMismatchException(dArr10[i4].length, length2);
                        }
                    }
                    return;
                } else {
                    throw new DimensionMismatchException(length, dArr10.length);
                }
            }
        }
        throw new NoDataException();
    }

    public double value(double d, double d2) throws OutOfRangeException {
        int searchIndex = searchIndex(d, this.xval);
        int searchIndex2 = searchIndex(d2, this.yval);
        double[] dArr = this.xval;
        double d3 = dArr[searchIndex];
        double[] dArr2 = this.yval;
        double d4 = dArr2[searchIndex2];
        return this.splines[searchIndex][searchIndex2].value((d - d3) / (dArr[searchIndex + 1] - d3), (d2 - d4) / (dArr2[searchIndex2 + 1] - d4));
    }

    public boolean isValidPoint(double d, double d2) {
        double[] dArr = this.xval;
        if (d >= dArr[0] && d <= dArr[dArr.length - 1]) {
            double[] dArr2 = this.yval;
            return d2 >= dArr2[0] && d2 <= dArr2[dArr2.length - 1];
        }
    }

    private int searchIndex(double d, double[] dArr) {
        int binarySearch = Arrays.binarySearch(dArr, d);
        if (binarySearch == -1 || binarySearch == (-dArr.length) - 1) {
            throw new OutOfRangeException(Double.valueOf(d), Double.valueOf(dArr[0]), Double.valueOf(dArr[dArr.length - 1]));
        } else if (binarySearch < 0) {
            return (-binarySearch) - 2;
        } else {
            int length = dArr.length - 1;
            return binarySearch == length ? length - 1 : binarySearch;
        }
    }

    private double[] computeSplineCoefficients(double[] dArr) {
        double[] dArr2 = new double[16];
        for (int i = 0; i < 16; i++) {
            double[] dArr3 = AINV[i];
            double d = 0.0d;
            for (int i2 = 0; i2 < 16; i2++) {
                d += dArr3[i2] * dArr[i2];
            }
            dArr2[i] = d;
        }
        return dArr2;
    }
}
