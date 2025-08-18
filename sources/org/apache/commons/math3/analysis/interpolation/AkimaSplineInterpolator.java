package org.apache.commons.math3.analysis.interpolation;

import org.apache.commons.math3.analysis.polynomials.PolynomialFunction;
import org.apache.commons.math3.analysis.polynomials.PolynomialSplineFunction;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.NonMonotonicSequenceException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.MathArrays;
import org.apache.commons.math3.util.Precision;

public class AkimaSplineInterpolator implements UnivariateInterpolator {
    private static final int MINIMUM_NUMBER_POINTS = 5;

    public PolynomialSplineFunction interpolate(double[] dArr, double[] dArr2) throws DimensionMismatchException, NumberIsTooSmallException, NonMonotonicSequenceException {
        double[] dArr3 = dArr;
        double[] dArr4 = dArr2;
        if (dArr3 == null || dArr4 == null) {
            throw new NullArgumentException();
        } else if (dArr3.length != dArr4.length) {
            throw new DimensionMismatchException(dArr3.length, dArr4.length);
        } else if (dArr3.length >= 5) {
            MathArrays.checkOrder(dArr);
            int length = dArr3.length - 1;
            double[] dArr5 = new double[length];
            double[] dArr6 = new double[length];
            int i = 0;
            while (i < length) {
                int i2 = i + 1;
                dArr5[i] = (dArr4[i2] - dArr4[i]) / (dArr3[i2] - dArr3[i]);
                i = i2;
            }
            for (int i3 = 1; i3 < length; i3++) {
                dArr6[i3] = FastMath.abs(dArr5[i3] - dArr5[i3 - 1]);
            }
            int length2 = dArr3.length;
            double[] dArr7 = new double[length2];
            int i4 = 2;
            while (i4 < length2 - 2) {
                int i5 = i4 + 1;
                double d = dArr6[i5];
                int i6 = i4 - 1;
                double d2 = dArr6[i6];
                if (!Precision.equals(d, 0.0d) || !Precision.equals(d2, 0.0d)) {
                    dArr7[i4] = ((dArr5[i6] * d) + (dArr5[i4] * d2)) / (d + d2);
                } else {
                    double d3 = dArr3[i4];
                    double d4 = dArr3[i5];
                    double d5 = dArr3[i6];
                    dArr7[i4] = (((d4 - d3) * dArr5[i6]) + ((d3 - d5) * dArr5[i4])) / (d4 - d5);
                }
                i4 = i5;
            }
            dArr7[0] = differentiateThreePoint(dArr, dArr2, 0, 0, 1, 2);
            double[] dArr8 = dArr2;
            dArr7[1] = differentiateThreePoint(dArr, dArr8, 1, 0, 1, 2);
            dArr7[dArr3.length - 2] = differentiateThreePoint(dArr, dArr8, dArr3.length - 2, dArr3.length - 3, dArr3.length - 2, dArr3.length - 1);
            dArr7[dArr3.length - 1] = differentiateThreePoint(dArr, dArr8, dArr3.length - 1, dArr3.length - 3, dArr3.length - 2, dArr3.length - 1);
            return interpolateHermiteSorted(dArr3, dArr4, dArr7);
        } else {
            throw new NumberIsTooSmallException(LocalizedFormats.NUMBER_OF_POINTS, Integer.valueOf(dArr3.length), 5, true);
        }
    }

    private double differentiateThreePoint(double[] dArr, double[] dArr2, int i, int i2, int i3, int i4) {
        double d = dArr2[i2];
        double d2 = dArr2[i3];
        double d3 = dArr2[i4];
        double d4 = dArr[i];
        double d5 = dArr[i2];
        double d6 = dArr[i3] - d5;
        double d7 = dArr[i4] - d5;
        double d8 = d2 - d;
        double d9 = ((d3 - d) - ((d7 / d6) * d8)) / ((d7 * d7) - (d7 * d6));
        return (d9 * 2.0d * (d4 - d5)) + ((d8 - ((d9 * d6) * d6)) / d6);
    }

    private PolynomialSplineFunction interpolateHermiteSorted(double[] dArr, double[] dArr2, double[] dArr3) {
        double[] dArr4 = dArr;
        double[] dArr5 = dArr2;
        double[] dArr6 = dArr3;
        if (dArr4.length != dArr5.length) {
            throw new DimensionMismatchException(dArr4.length, dArr5.length);
        } else if (dArr4.length != dArr6.length) {
            throw new DimensionMismatchException(dArr4.length, dArr6.length);
        } else if (dArr4.length >= 2) {
            int length = dArr4.length - 1;
            PolynomialFunction[] polynomialFunctionArr = new PolynomialFunction[length];
            double[] dArr7 = new double[4];
            int i = 0;
            while (i < length) {
                int i2 = i + 1;
                double d = dArr4[i2] - dArr4[i];
                double d2 = dArr5[i];
                double d3 = dArr5[i2];
                double d4 = dArr6[i];
                double d5 = dArr6[i2];
                dArr7[0] = d2;
                dArr7[1] = dArr6[i];
                dArr7[2] = (((((d3 - d2) * 3.0d) / d) - (d4 * 2.0d)) - d5) / d;
                dArr7[3] = (((((d2 - d3) * 2.0d) / d) + d4) + d5) / (d * d);
                polynomialFunctionArr[i] = new PolynomialFunction(dArr7);
                i = i2;
            }
            return new PolynomialSplineFunction(dArr4, polynomialFunctionArr);
        } else {
            throw new NumberIsTooSmallException(LocalizedFormats.NUMBER_OF_POINTS, Integer.valueOf(dArr4.length), 2, true);
        }
    }
}
