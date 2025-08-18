package org.apache.commons.math3.analysis.interpolation;

import java.lang.reflect.Array;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.NoDataException;
import org.apache.commons.math3.exception.NonMonotonicSequenceException;
import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.util.MathArrays;

@Deprecated
public class TricubicSplineInterpolator implements TrivariateGridInterpolator {
    private int nextIndex(int i, int i2) {
        int i3 = i + 1;
        return i3 < i2 ? i3 : i3 - 1;
    }

    private int previousIndex(int i) {
        int i2 = i - 1;
        if (i2 >= 0) {
            return i2;
        }
        return 0;
    }

    public TricubicSplineInterpolatingFunction interpolate(double[] dArr, double[] dArr2, double[] dArr3, double[][][] dArr4) throws NoDataException, NumberIsTooSmallException, DimensionMismatchException, NonMonotonicSequenceException {
        double[] dArr5 = dArr;
        double[] dArr6 = dArr2;
        double[] dArr7 = dArr3;
        double[][][] dArr8 = dArr4;
        if (dArr5.length == 0 || dArr6.length == 0 || dArr7.length == 0 || dArr8.length == 0) {
            throw new NoDataException();
        } else if (dArr5.length == dArr8.length) {
            MathArrays.checkOrder(dArr);
            MathArrays.checkOrder(dArr2);
            MathArrays.checkOrder(dArr3);
            int length = dArr5.length;
            int length2 = dArr6.length;
            int length3 = dArr7.length;
            int i = 3;
            int[] iArr = new int[3];
            iArr[2] = length2;
            iArr[1] = length;
            int i2 = 0;
            iArr[0] = length3;
            double[][][] dArr9 = (double[][][]) Array.newInstance(Double.TYPE, iArr);
            int[] iArr2 = new int[3];
            iArr2[2] = length;
            iArr2[1] = length3;
            iArr2[0] = length2;
            double[][][] dArr10 = (double[][][]) Array.newInstance(Double.TYPE, iArr2);
            int i3 = 0;
            while (i3 < length) {
                if (dArr8[i3].length == length2) {
                    int i4 = i2;
                    while (i4 < length2) {
                        if (dArr8[i3][i4].length == length3) {
                            for (int i5 = 0; i5 < length3; i5++) {
                                double d = dArr8[i3][i4][i5];
                                dArr9[i5][i3][i4] = d;
                                dArr10[i4][i5][i3] = d;
                            }
                            i4++;
                        } else {
                            throw new DimensionMismatchException(dArr8[i3][i4].length, length3);
                        }
                    }
                    i3++;
                    i2 = 0;
                } else {
                    throw new DimensionMismatchException(dArr8[i3].length, length2);
                }
            }
            BicubicSplineInterpolator bicubicSplineInterpolator = new BicubicSplineInterpolator(true);
            BicubicSplineInterpolatingFunction[] bicubicSplineInterpolatingFunctionArr = new BicubicSplineInterpolatingFunction[length];
            for (int i6 = 0; i6 < length; i6++) {
                bicubicSplineInterpolatingFunctionArr[i6] = bicubicSplineInterpolator.interpolate(dArr6, dArr7, dArr8[i6]);
            }
            BicubicSplineInterpolatingFunction[] bicubicSplineInterpolatingFunctionArr2 = new BicubicSplineInterpolatingFunction[length2];
            for (int i7 = 0; i7 < length2; i7++) {
                bicubicSplineInterpolatingFunctionArr2[i7] = bicubicSplineInterpolator.interpolate(dArr7, dArr5, dArr10[i7]);
            }
            BicubicSplineInterpolatingFunction[] bicubicSplineInterpolatingFunctionArr3 = new BicubicSplineInterpolatingFunction[length3];
            for (int i8 = 0; i8 < length3; i8++) {
                bicubicSplineInterpolatingFunctionArr3[i8] = bicubicSplineInterpolator.interpolate(dArr5, dArr6, dArr9[i8]);
            }
            int[] iArr3 = new int[3];
            iArr3[2] = length3;
            iArr3[1] = length2;
            iArr3[0] = length;
            double[][][] dArr11 = (double[][][]) Array.newInstance(Double.TYPE, iArr3);
            int[] iArr4 = new int[3];
            iArr4[2] = length3;
            iArr4[1] = length2;
            iArr4[0] = length;
            double[][][] dArr12 = (double[][][]) Array.newInstance(Double.TYPE, iArr4);
            int[] iArr5 = new int[3];
            iArr5[2] = length3;
            iArr5[1] = length2;
            iArr5[0] = length;
            double[][][] dArr13 = (double[][][]) Array.newInstance(Double.TYPE, iArr5);
            int i9 = 0;
            while (i9 < length3) {
                BicubicSplineInterpolatingFunction bicubicSplineInterpolatingFunction = bicubicSplineInterpolatingFunctionArr3[i9];
                BicubicSplineInterpolatingFunction[] bicubicSplineInterpolatingFunctionArr4 = bicubicSplineInterpolatingFunctionArr3;
                int i10 = 0;
                while (i10 < length) {
                    double d2 = dArr5[i10];
                    int i11 = 0;
                    while (i11 < length2) {
                        int i12 = length;
                        double d3 = dArr6[i11];
                        dArr11[i10][i11][i9] = bicubicSplineInterpolatingFunction.partialDerivativeX(d2, d3);
                        dArr12[i10][i11][i9] = bicubicSplineInterpolatingFunction.partialDerivativeY(d2, d3);
                        dArr13[i10][i11][i9] = bicubicSplineInterpolatingFunction.partialDerivativeXY(d2, d3);
                        i11++;
                        length2 = length2;
                        length = i12;
                    }
                    int i13 = length;
                    int i14 = length2;
                    i10++;
                    double[] dArr14 = dArr3;
                    double[][][] dArr15 = dArr4;
                }
                int i15 = length;
                int i16 = length2;
                i9++;
                double[] dArr16 = dArr3;
                double[][][] dArr17 = dArr4;
                bicubicSplineInterpolatingFunctionArr3 = bicubicSplineInterpolatingFunctionArr4;
                i = 3;
            }
            int i17 = length;
            int i18 = length2;
            int i19 = i;
            int[] iArr6 = new int[i19];
            iArr6[2] = length3;
            iArr6[1] = i18;
            iArr6[0] = i17;
            double[][][] dArr18 = (double[][][]) Array.newInstance(Double.TYPE, iArr6);
            int[] iArr7 = new int[i19];
            iArr7[2] = length3;
            iArr7[1] = i18;
            iArr7[0] = i17;
            double[][][] dArr19 = (double[][][]) Array.newInstance(Double.TYPE, iArr7);
            int i20 = i17;
            int i21 = 0;
            while (i21 < i20) {
                BicubicSplineInterpolatingFunction bicubicSplineInterpolatingFunction2 = bicubicSplineInterpolatingFunctionArr[i21];
                int i22 = i18;
                int i23 = 0;
                while (i23 < i22) {
                    BicubicSplineInterpolatingFunction[] bicubicSplineInterpolatingFunctionArr5 = bicubicSplineInterpolatingFunctionArr;
                    double d4 = dArr6[i23];
                    double[][][] dArr20 = dArr13;
                    int i24 = 0;
                    while (i24 < length3) {
                        double d5 = dArr3[i24];
                        dArr18[i21][i23][i24] = bicubicSplineInterpolatingFunction2.partialDerivativeY(d4, d5);
                        dArr19[i21][i23][i24] = bicubicSplineInterpolatingFunction2.partialDerivativeXY(d4, d5);
                        i24++;
                        double[] dArr21 = dArr;
                        double[] dArr22 = dArr2;
                    }
                    i23++;
                    double[] dArr23 = dArr;
                    dArr6 = dArr2;
                    bicubicSplineInterpolatingFunctionArr = bicubicSplineInterpolatingFunctionArr5;
                    dArr13 = dArr20;
                }
                double[][][] dArr24 = dArr13;
                BicubicSplineInterpolatingFunction[] bicubicSplineInterpolatingFunctionArr6 = bicubicSplineInterpolatingFunctionArr;
                i21++;
                double[] dArr25 = dArr;
                dArr6 = dArr2;
                i18 = i22;
            }
            int i25 = i18;
            double[][][] dArr26 = dArr13;
            int[] iArr8 = new int[3];
            iArr8[2] = length3;
            iArr8[1] = i25;
            iArr8[0] = i20;
            double[][][] dArr27 = (double[][][]) Array.newInstance(Double.TYPE, iArr8);
            for (int i26 = 0; i26 < i25; i26++) {
                BicubicSplineInterpolatingFunction bicubicSplineInterpolatingFunction3 = bicubicSplineInterpolatingFunctionArr2[i26];
                for (int i27 = 0; i27 < length3; i27++) {
                    double d6 = dArr3[i27];
                    int i28 = 0;
                    while (i28 < i20) {
                        dArr27[i28][i26][i27] = bicubicSplineInterpolatingFunction3.partialDerivativeXY(d6, dArr[i28]);
                        i28++;
                        bicubicSplineInterpolatingFunctionArr2 = bicubicSplineInterpolatingFunctionArr2;
                        dArr19 = dArr19;
                    }
                    double[] dArr28 = dArr;
                    double[][][] dArr29 = dArr19;
                    BicubicSplineInterpolatingFunction[] bicubicSplineInterpolatingFunctionArr7 = bicubicSplineInterpolatingFunctionArr2;
                }
                double[] dArr30 = dArr;
                double[][][] dArr31 = dArr19;
                BicubicSplineInterpolatingFunction[] bicubicSplineInterpolatingFunctionArr8 = bicubicSplineInterpolatingFunctionArr2;
            }
            double[] dArr32 = dArr;
            double[][][] dArr33 = dArr19;
            int[] iArr9 = new int[3];
            iArr9[2] = length3;
            iArr9[1] = i25;
            int i29 = 0;
            iArr9[0] = i20;
            double[][][] dArr34 = (double[][][]) Array.newInstance(Double.TYPE, iArr9);
            int i30 = 0;
            while (i30 < i20) {
                int nextIndex = nextIndex(i30, i20);
                int previousIndex = previousIndex(i30);
                int i31 = i29;
                while (i31 < i25) {
                    int nextIndex2 = nextIndex(i31, i25);
                    int previousIndex2 = previousIndex(i31);
                    while (i29 < length3) {
                        int nextIndex3 = nextIndex(i29, length3);
                        int previousIndex3 = previousIndex(i29);
                        double[] dArr35 = dArr34[i30][i31];
                        int i32 = i25;
                        double[][][] dArr36 = dArr4;
                        double[][] dArr37 = dArr36[nextIndex];
                        double[] dArr38 = dArr37[nextIndex2];
                        double d7 = dArr38[nextIndex3];
                        double[] dArr39 = dArr37[previousIndex2];
                        double d8 = d7 - dArr39[nextIndex3];
                        double[][] dArr40 = dArr36[previousIndex];
                        double[] dArr41 = dArr40[nextIndex2];
                        double[] dArr42 = dArr40[previousIndex2];
                        dArr35[i29] = ((((((d8 - dArr41[nextIndex3]) + dArr42[nextIndex3]) - dArr38[previousIndex3]) + dArr39[previousIndex3]) + dArr41[previousIndex3]) - dArr42[previousIndex3]) / (((dArr32[nextIndex] - dArr32[previousIndex]) * (dArr2[nextIndex2] - dArr2[previousIndex2])) * (dArr3[nextIndex3] - dArr3[previousIndex3]));
                        i29++;
                        i25 = i32;
                    }
                    int i33 = i25;
                    double[][][] dArr43 = dArr4;
                    i31++;
                    i25 = i33;
                    i29 = 0;
                }
                int i34 = i25;
                double[][][] dArr44 = dArr4;
                i30++;
                i25 = i34;
                i29 = 0;
            }
            double[][][] dArr45 = dArr4;
            return new TricubicSplineInterpolatingFunction(dArr, dArr2, dArr3, dArr4, dArr11, dArr12, dArr18, dArr26, dArr27, dArr33, dArr34);
        } else {
            throw new DimensionMismatchException(dArr5.length, dArr8.length);
        }
    }
}
