package org.apache.commons.math3.util;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.TreeSet;
import org.apache.commons.math3.Field;
import org.apache.commons.math3.distribution.UniformIntegerDistribution;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.MathArithmeticException;
import org.apache.commons.math3.exception.MathIllegalArgumentException;
import org.apache.commons.math3.exception.MathInternalError;
import org.apache.commons.math3.exception.NoDataException;
import org.apache.commons.math3.exception.NonMonotonicSequenceException;
import org.apache.commons.math3.exception.NotANumberException;
import org.apache.commons.math3.exception.NotPositiveException;
import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.exception.NumberIsTooLargeException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.random.RandomGenerator;
import org.apache.commons.math3.random.Well19937c;

public class MathArrays {

    public interface Function {
        double evaluate(double[] dArr);

        double evaluate(double[] dArr, int i, int i2);
    }

    public enum OrderDirection {
        INCREASING,
        DECREASING
    }

    public enum Position {
        HEAD,
        TAIL
    }

    private MathArrays() {
    }

    public static double[] scale(double d, double[] dArr) {
        double[] dArr2 = new double[dArr.length];
        for (int i = 0; i < dArr.length; i++) {
            dArr2[i] = dArr[i] * d;
        }
        return dArr2;
    }

    public static void scaleInPlace(double d, double[] dArr) {
        for (int i = 0; i < dArr.length; i++) {
            dArr[i] = dArr[i] * d;
        }
    }

    public static double[] ebeAdd(double[] dArr, double[] dArr2) throws DimensionMismatchException {
        checkEqualLength(dArr, dArr2);
        double[] dArr3 = (double[]) dArr.clone();
        for (int i = 0; i < dArr.length; i++) {
            dArr3[i] = dArr3[i] + dArr2[i];
        }
        return dArr3;
    }

    public static double[] ebeSubtract(double[] dArr, double[] dArr2) throws DimensionMismatchException {
        checkEqualLength(dArr, dArr2);
        double[] dArr3 = (double[]) dArr.clone();
        for (int i = 0; i < dArr.length; i++) {
            dArr3[i] = dArr3[i] - dArr2[i];
        }
        return dArr3;
    }

    public static double[] ebeMultiply(double[] dArr, double[] dArr2) throws DimensionMismatchException {
        checkEqualLength(dArr, dArr2);
        double[] dArr3 = (double[]) dArr.clone();
        for (int i = 0; i < dArr.length; i++) {
            dArr3[i] = dArr3[i] * dArr2[i];
        }
        return dArr3;
    }

    public static double[] ebeDivide(double[] dArr, double[] dArr2) throws DimensionMismatchException {
        checkEqualLength(dArr, dArr2);
        double[] dArr3 = (double[]) dArr.clone();
        for (int i = 0; i < dArr.length; i++) {
            dArr3[i] = dArr3[i] / dArr2[i];
        }
        return dArr3;
    }

    public static double distance1(double[] dArr, double[] dArr2) throws DimensionMismatchException {
        checkEqualLength(dArr, dArr2);
        double d = 0.0d;
        for (int i = 0; i < dArr.length; i++) {
            d += FastMath.abs(dArr[i] - dArr2[i]);
        }
        return d;
    }

    public static int distance1(int[] iArr, int[] iArr2) throws DimensionMismatchException {
        checkEqualLength(iArr, iArr2);
        int i = 0;
        for (int i2 = 0; i2 < iArr.length; i2++) {
            i += FastMath.abs(iArr[i2] - iArr2[i2]);
        }
        return i;
    }

    public static double distance(double[] dArr, double[] dArr2) throws DimensionMismatchException {
        checkEqualLength(dArr, dArr2);
        double d = 0.0d;
        for (int i = 0; i < dArr.length; i++) {
            double d2 = dArr[i] - dArr2[i];
            d += d2 * d2;
        }
        return FastMath.sqrt(d);
    }

    public static double cosAngle(double[] dArr, double[] dArr2) {
        return linearCombination(dArr, dArr2) / (safeNorm(dArr) * safeNorm(dArr2));
    }

    public static double distance(int[] iArr, int[] iArr2) throws DimensionMismatchException {
        checkEqualLength(iArr, iArr2);
        double d = 0.0d;
        for (int i = 0; i < iArr.length; i++) {
            double d2 = (double) (iArr[i] - iArr2[i]);
            d += d2 * d2;
        }
        return FastMath.sqrt(d);
    }

    public static double distanceInf(double[] dArr, double[] dArr2) throws DimensionMismatchException {
        checkEqualLength(dArr, dArr2);
        double d = 0.0d;
        for (int i = 0; i < dArr.length; i++) {
            d = FastMath.max(d, FastMath.abs(dArr[i] - dArr2[i]));
        }
        return d;
    }

    public static int distanceInf(int[] iArr, int[] iArr2) throws DimensionMismatchException {
        checkEqualLength(iArr, iArr2);
        int i = 0;
        for (int i2 = 0; i2 < iArr.length; i2++) {
            i = FastMath.max(i, FastMath.abs(iArr[i2] - iArr2[i2]));
        }
        return i;
    }

    public static <T extends Comparable<? super T>> boolean isMonotonic(T[] tArr, OrderDirection orderDirection, boolean z) {
        T t = tArr[0];
        int length = tArr.length;
        for (int i = 1; i < length; i++) {
            int i2 = AnonymousClass3.$SwitchMap$org$apache$commons$math3$util$MathArrays$OrderDirection[orderDirection.ordinal()];
            if (i2 == 1) {
                int compareTo = t.compareTo(tArr[i]);
                if (z) {
                    if (compareTo >= 0) {
                        return false;
                    }
                } else if (compareTo > 0) {
                    return false;
                }
            } else if (i2 == 2) {
                int compareTo2 = tArr[i].compareTo(t);
                if (z) {
                    if (compareTo2 >= 0) {
                        return false;
                    }
                } else if (compareTo2 > 0) {
                    return false;
                }
            } else {
                throw new MathInternalError();
            }
            t = tArr[i];
        }
        return true;
    }

    public static boolean isMonotonic(double[] dArr, OrderDirection orderDirection, boolean z) {
        return checkOrder(dArr, orderDirection, z, false);
    }

    public static boolean checkEqualLength(double[] dArr, double[] dArr2, boolean z) {
        if (dArr.length == dArr2.length) {
            return true;
        }
        if (!z) {
            return false;
        }
        throw new DimensionMismatchException(dArr.length, dArr2.length);
    }

    public static void checkEqualLength(double[] dArr, double[] dArr2) {
        checkEqualLength(dArr, dArr2, true);
    }

    public static boolean checkEqualLength(int[] iArr, int[] iArr2, boolean z) {
        if (iArr.length == iArr2.length) {
            return true;
        }
        if (!z) {
            return false;
        }
        throw new DimensionMismatchException(iArr.length, iArr2.length);
    }

    public static void checkEqualLength(int[] iArr, int[] iArr2) {
        checkEqualLength(iArr, iArr2, true);
    }

    public static boolean checkOrder(double[] dArr, OrderDirection orderDirection, boolean z, boolean z2) throws NonMonotonicSequenceException {
        double d = dArr[0];
        int length = dArr.length;
        int i = 1;
        while (i < length) {
            int i2 = AnonymousClass3.$SwitchMap$org$apache$commons$math3$util$MathArrays$OrderDirection[orderDirection.ordinal()];
            if (i2 != 1) {
                if (i2 != 2) {
                    throw new MathInternalError();
                } else if (z) {
                    if (dArr[i] >= d) {
                        break;
                    }
                } else if (dArr[i] > d) {
                    break;
                }
            } else if (z) {
                if (dArr[i] <= d) {
                    break;
                }
            } else if (dArr[i] < d) {
                break;
            }
            d = dArr[i];
            i++;
        }
        if (i == length) {
            return true;
        }
        if (!z2) {
            return false;
        }
        throw new NonMonotonicSequenceException(Double.valueOf(dArr[i]), Double.valueOf(d), i, orderDirection, z);
    }

    public static void checkOrder(double[] dArr, OrderDirection orderDirection, boolean z) throws NonMonotonicSequenceException {
        checkOrder(dArr, orderDirection, z, true);
    }

    public static void checkOrder(double[] dArr) throws NonMonotonicSequenceException {
        checkOrder(dArr, OrderDirection.INCREASING, true);
    }

    public static void checkRectangular(long[][] jArr) throws NullArgumentException, DimensionMismatchException {
        MathUtils.checkNotNull(jArr);
        int i = 1;
        while (i < jArr.length) {
            if (jArr[i].length == jArr[0].length) {
                i++;
            } else {
                throw new DimensionMismatchException(LocalizedFormats.DIFFERENT_ROWS_LENGTHS, jArr[i].length, jArr[0].length);
            }
        }
    }

    public static void checkPositive(double[] dArr) throws NotStrictlyPositiveException {
        int i = 0;
        while (i < dArr.length) {
            if (dArr[i] > 0.0d) {
                i++;
            } else {
                throw new NotStrictlyPositiveException(Double.valueOf(dArr[i]));
            }
        }
    }

    public static void checkNotNaN(double[] dArr) throws NotANumberException {
        int i = 0;
        while (i < dArr.length) {
            if (!Double.isNaN(dArr[i])) {
                i++;
            } else {
                throw new NotANumberException();
            }
        }
    }

    public static void checkNonNegative(long[] jArr) throws NotPositiveException {
        int i = 0;
        while (i < jArr.length) {
            if (jArr[i] >= 0) {
                i++;
            } else {
                throw new NotPositiveException(Long.valueOf(jArr[i]));
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0024, code lost:
        r1 = r1 + 1;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void checkNonNegative(long[][] r7) throws org.apache.commons.math3.exception.NotPositiveException {
        /*
            r0 = 0
            r1 = r0
        L_0x0002:
            int r2 = r7.length
            if (r1 >= r2) goto L_0x0027
            r2 = r0
        L_0x0006:
            r3 = r7[r1]
            int r4 = r3.length
            if (r2 >= r4) goto L_0x0024
            r3 = r3[r2]
            r5 = 0
            int r3 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r3 < 0) goto L_0x0016
            int r2 = r2 + 1
            goto L_0x0006
        L_0x0016:
            org.apache.commons.math3.exception.NotPositiveException r0 = new org.apache.commons.math3.exception.NotPositiveException
            r7 = r7[r1]
            r1 = r7[r2]
            java.lang.Long r7 = java.lang.Long.valueOf(r1)
            r0.<init>(r7)
            throw r0
        L_0x0024:
            int r1 = r1 + 1
            goto L_0x0002
        L_0x0027:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.math3.util.MathArrays.checkNonNegative(long[][]):void");
    }

    public static double safeNorm(double[] dArr) {
        double[] dArr2 = dArr;
        double length = 1.304E19d / ((double) dArr2.length);
        double d = 0.0d;
        double d2 = 0.0d;
        double d3 = 0.0d;
        double d4 = 0.0d;
        double d5 = 0.0d;
        for (double abs : dArr2) {
            double abs2 = FastMath.abs(abs);
            if (abs2 >= 3.834E-20d && abs2 <= length) {
                d2 += abs2 * abs2;
            } else if (abs2 > 3.834E-20d) {
                if (abs2 > d3) {
                    double d6 = d3 / abs2;
                    d = (d * d6 * d6) + 1.0d;
                    d3 = abs2;
                } else {
                    double d7 = abs2 / d3;
                    d += d7 * d7;
                }
            } else if (abs2 > d4) {
                double d8 = d4 / abs2;
                d5 = (d5 * d8 * d8) + 1.0d;
                d4 = abs2;
            } else {
                if (abs2 != 0.0d) {
                    double d9 = abs2 / d4;
                    d5 += d9 * d9;
                }
            }
        }
        if (d != 0.0d) {
            return d3 * Math.sqrt(d + ((d2 / d3) / d3));
        }
        if (d2 == 0.0d) {
            return d4 * Math.sqrt(d5);
        }
        if (d2 >= d4) {
            return Math.sqrt(d2 * (((d4 / d2) * d4 * d5) + 1.0d));
        }
        return Math.sqrt(d4 * ((d2 / d4) + (d5 * d4)));
    }

    private static class PairDoubleInteger {
        private final double key;
        private final int value;

        PairDoubleInteger(double d, int i) {
            this.key = d;
            this.value = i;
        }

        public double getKey() {
            return this.key;
        }

        public int getValue() {
            return this.value;
        }
    }

    public static void sortInPlace(double[] dArr, double[]... dArr2) throws DimensionMismatchException, NullArgumentException {
        sortInPlace(dArr, OrderDirection.INCREASING, dArr2);
    }

    public static void sortInPlace(double[] dArr, OrderDirection orderDirection, double[]... dArr2) throws NullArgumentException, DimensionMismatchException {
        if (dArr != null) {
            int length = dArr.length;
            int i = 0;
            while (i < r0) {
                double[] dArr3 = dArr2[i];
                if (dArr3 == null) {
                    throw new NullArgumentException();
                } else if (dArr3.length == length) {
                    i++;
                } else {
                    throw new DimensionMismatchException(dArr3.length, length);
                }
            }
            ArrayList arrayList = new ArrayList(length);
            for (int i2 = 0; i2 < length; i2++) {
                arrayList.add(new PairDoubleInteger(dArr[i2], i2));
            }
            Collections.sort(arrayList, orderDirection == OrderDirection.INCREASING ? new Comparator<PairDoubleInteger>() {
                public int compare(PairDoubleInteger pairDoubleInteger, PairDoubleInteger pairDoubleInteger2) {
                    return Double.compare(pairDoubleInteger.getKey(), pairDoubleInteger2.getKey());
                }
            } : new Comparator<PairDoubleInteger>() {
                public int compare(PairDoubleInteger pairDoubleInteger, PairDoubleInteger pairDoubleInteger2) {
                    return Double.compare(pairDoubleInteger2.getKey(), pairDoubleInteger.getKey());
                }
            });
            int[] iArr = new int[length];
            for (int i3 = 0; i3 < length; i3++) {
                PairDoubleInteger pairDoubleInteger = (PairDoubleInteger) arrayList.get(i3);
                dArr[i3] = pairDoubleInteger.getKey();
                iArr[i3] = pairDoubleInteger.getValue();
            }
            for (double[] dArr4 : dArr2) {
                double[] dArr5 = (double[]) dArr4.clone();
                for (int i4 = 0; i4 < length; i4++) {
                    dArr4[i4] = dArr5[iArr[i4]];
                }
            }
            return;
        }
        throw new NullArgumentException();
    }

    public static int[] copyOf(int[] iArr) {
        return copyOf(iArr, iArr.length);
    }

    public static double[] copyOf(double[] dArr) {
        return copyOf(dArr, dArr.length);
    }

    public static int[] copyOf(int[] iArr, int i) {
        int[] iArr2 = new int[i];
        System.arraycopy(iArr, 0, iArr2, 0, FastMath.min(i, iArr.length));
        return iArr2;
    }

    public static double[] copyOf(double[] dArr, int i) {
        double[] dArr2 = new double[i];
        System.arraycopy(dArr, 0, dArr2, 0, FastMath.min(i, dArr.length));
        return dArr2;
    }

    public static double[] copyOfRange(double[] dArr, int i, int i2) {
        int i3 = i2 - i;
        double[] dArr2 = new double[i3];
        System.arraycopy(dArr, i, dArr2, 0, FastMath.min(i3, dArr.length - i));
        return dArr2;
    }

    public static double linearCombination(double[] dArr, double[] dArr2) throws DimensionMismatchException {
        double[] dArr3 = dArr;
        checkEqualLength(dArr, dArr2);
        int length = dArr3.length;
        int i = 1;
        if (length == 1) {
            return dArr3[0] * dArr2[0];
        }
        double[] dArr4 = new double[length];
        double d = 0.0d;
        double d2 = 0.0d;
        for (int i2 = 0; i2 < length; i2++) {
            double d3 = dArr3[i2];
            double longBitsToDouble = Double.longBitsToDouble(Double.doubleToRawLongBits(d3) & -134217728);
            double d4 = d3 - longBitsToDouble;
            double d5 = dArr2[i2];
            double longBitsToDouble2 = Double.longBitsToDouble(Double.doubleToRawLongBits(d5) & -134217728);
            double d6 = d5 - longBitsToDouble2;
            double d7 = d3 * d5;
            dArr4[i2] = d7;
            d2 += (d4 * d6) - (((d7 - (longBitsToDouble * longBitsToDouble2)) - (d4 * longBitsToDouble2)) - (longBitsToDouble * d6));
        }
        double d8 = dArr4[0];
        double d9 = dArr4[1];
        double d10 = d8 + d9;
        double d11 = d10 - d9;
        double d12 = (d9 - (d10 - d11)) + (d8 - d11);
        int i3 = length - 1;
        while (i < i3) {
            i++;
            double d13 = dArr4[i];
            double d14 = d10 + d13;
            double d15 = d14 - d13;
            d12 += (d13 - (d14 - d15)) + (d10 - d15);
            d10 = d14;
        }
        double d16 = d10 + d2 + d12;
        if (!Double.isNaN(d16)) {
            return d16;
        }
        for (int i4 = 0; i4 < length; i4++) {
            d += dArr3[i4] * dArr2[i4];
        }
        return d;
    }

    public static double linearCombination(double d, double d2, double d3, double d4) {
        double longBitsToDouble = Double.longBitsToDouble(Double.doubleToRawLongBits(d) & -134217728);
        double d5 = d - longBitsToDouble;
        double longBitsToDouble2 = Double.longBitsToDouble(Double.doubleToRawLongBits(d2) & -134217728);
        double d6 = d2 - longBitsToDouble2;
        double d7 = d * d2;
        double longBitsToDouble3 = Double.longBitsToDouble(Double.doubleToRawLongBits(d3) & -134217728);
        double d8 = d3 - longBitsToDouble3;
        double longBitsToDouble4 = Double.longBitsToDouble(-134217728 & Double.doubleToRawLongBits(d4));
        double d9 = d4 - longBitsToDouble4;
        double d10 = d3 * d4;
        double d11 = d7 + d10;
        double d12 = d11 - d10;
        double d13 = ((d5 * d6) - (((d7 - (longBitsToDouble * longBitsToDouble2)) - (d5 * longBitsToDouble2)) - (longBitsToDouble * d6))) + ((d8 * d9) - (((d10 - (longBitsToDouble3 * longBitsToDouble4)) - (d8 * longBitsToDouble4)) - (longBitsToDouble3 * d9))) + (d10 - (d11 - d12)) + (d7 - d12) + d11;
        return Double.isNaN(d13) ? d11 : d13;
    }

    public static double linearCombination(double d, double d2, double d3, double d4, double d5, double d6) {
        double longBitsToDouble = Double.longBitsToDouble(Double.doubleToRawLongBits(d) & -134217728);
        double d7 = d - longBitsToDouble;
        double longBitsToDouble2 = Double.longBitsToDouble(Double.doubleToRawLongBits(d2) & -134217728);
        double d8 = d2 - longBitsToDouble2;
        double d9 = d * d2;
        double d10 = (d7 * d8) - (((d9 - (longBitsToDouble * longBitsToDouble2)) - (d7 * longBitsToDouble2)) - (longBitsToDouble * d8));
        double longBitsToDouble3 = Double.longBitsToDouble(Double.doubleToRawLongBits(d3) & -134217728);
        double d11 = d3 - longBitsToDouble3;
        double longBitsToDouble4 = Double.longBitsToDouble(Double.doubleToRawLongBits(d4) & -134217728);
        double d12 = d4 - longBitsToDouble4;
        double d13 = d3 * d4;
        double d14 = (d11 * d12) - (((d13 - (longBitsToDouble3 * longBitsToDouble4)) - (d11 * longBitsToDouble4)) - (longBitsToDouble3 * d12));
        double longBitsToDouble5 = Double.longBitsToDouble(Double.doubleToRawLongBits(d5) & -134217728);
        double d15 = d5 - longBitsToDouble5;
        double longBitsToDouble6 = Double.longBitsToDouble(-134217728 & Double.doubleToRawLongBits(d6));
        double d16 = d6 - longBitsToDouble6;
        double d17 = d5 * d6;
        double d18 = d9 + d13;
        double d19 = d18 - d13;
        double d20 = d18 + d17;
        double d21 = d20 - d17;
        double d22 = d10 + d14 + ((d15 * d16) - (((d17 - (longBitsToDouble5 * longBitsToDouble6)) - (d15 * longBitsToDouble6)) - (longBitsToDouble5 * d16))) + (d13 - (d18 - d19)) + (d9 - d19) + (d17 - (d20 - d21)) + (d18 - d21) + d20;
        return Double.isNaN(d22) ? d20 : d22;
    }

    public static double linearCombination(double d, double d2, double d3, double d4, double d5, double d6, double d7, double d8) {
        double longBitsToDouble = Double.longBitsToDouble(Double.doubleToRawLongBits(d) & -134217728);
        double d9 = d - longBitsToDouble;
        double longBitsToDouble2 = Double.longBitsToDouble(Double.doubleToRawLongBits(d2) & -134217728);
        double d10 = d2 - longBitsToDouble2;
        double d11 = d * d2;
        double d12 = (d9 * d10) - (((d11 - (longBitsToDouble * longBitsToDouble2)) - (d9 * longBitsToDouble2)) - (longBitsToDouble * d10));
        double longBitsToDouble3 = Double.longBitsToDouble(Double.doubleToRawLongBits(d3) & -134217728);
        double d13 = d3 - longBitsToDouble3;
        double longBitsToDouble4 = Double.longBitsToDouble(Double.doubleToRawLongBits(d4) & -134217728);
        double d14 = d4 - longBitsToDouble4;
        double d15 = d3 * d4;
        double d16 = (d13 * d14) - (((d15 - (longBitsToDouble3 * longBitsToDouble4)) - (d13 * longBitsToDouble4)) - (longBitsToDouble3 * d14));
        double longBitsToDouble5 = Double.longBitsToDouble(Double.doubleToRawLongBits(d5) & -134217728);
        double d17 = d5 - longBitsToDouble5;
        double longBitsToDouble6 = Double.longBitsToDouble(Double.doubleToRawLongBits(d6) & -134217728);
        double d18 = d6 - longBitsToDouble6;
        double d19 = d5 * d6;
        double d20 = (d17 * d18) - (((d19 - (longBitsToDouble5 * longBitsToDouble6)) - (d17 * longBitsToDouble6)) - (longBitsToDouble5 * d18));
        double longBitsToDouble7 = Double.longBitsToDouble(Double.doubleToRawLongBits(d7) & -134217728);
        double d21 = d7 - longBitsToDouble7;
        double longBitsToDouble8 = Double.longBitsToDouble(-134217728 & Double.doubleToRawLongBits(d8));
        double d22 = d8 - longBitsToDouble8;
        double d23 = d7 * d8;
        double d24 = d11 + d15;
        double d25 = d24 - d15;
        double d26 = d24 + d19;
        double d27 = d26 - d19;
        double d28 = (d19 - (d26 - d27)) + (d24 - d27);
        double d29 = d26 + d23;
        double d30 = d29 - d23;
        double d31 = d12 + d16 + d20 + ((d21 * d22) - (((d23 - (longBitsToDouble7 * longBitsToDouble8)) - (d21 * longBitsToDouble8)) - (longBitsToDouble7 * d22))) + (d15 - (d24 - d25)) + (d11 - d25) + d28 + (d23 - (d29 - d30)) + (d26 - d30) + d29;
        return Double.isNaN(d31) ? d29 : d31;
    }

    public static boolean equals(float[] fArr, float[] fArr2) {
        boolean z = false;
        if (fArr == null || fArr2 == null) {
            boolean z2 = fArr == null;
            if (fArr2 == null) {
                z = true;
            }
            return !(z2 ^ z);
        } else if (fArr.length != fArr2.length) {
            return false;
        } else {
            for (int i = 0; i < fArr.length; i++) {
                if (!Precision.equals(fArr[i], fArr2[i])) {
                    return false;
                }
            }
            return true;
        }
    }

    public static boolean equalsIncludingNaN(float[] fArr, float[] fArr2) {
        boolean z = false;
        if (fArr == null || fArr2 == null) {
            boolean z2 = fArr == null;
            if (fArr2 == null) {
                z = true;
            }
            return !(z2 ^ z);
        } else if (fArr.length != fArr2.length) {
            return false;
        } else {
            for (int i = 0; i < fArr.length; i++) {
                if (!Precision.equalsIncludingNaN(fArr[i], fArr2[i])) {
                    return false;
                }
            }
            return true;
        }
    }

    public static boolean equals(double[] dArr, double[] dArr2) {
        boolean z = false;
        if (dArr == null || dArr2 == null) {
            boolean z2 = dArr == null;
            if (dArr2 == null) {
                z = true;
            }
            return !(z2 ^ z);
        } else if (dArr.length != dArr2.length) {
            return false;
        } else {
            for (int i = 0; i < dArr.length; i++) {
                if (!Precision.equals(dArr[i], dArr2[i])) {
                    return false;
                }
            }
            return true;
        }
    }

    public static boolean equalsIncludingNaN(double[] dArr, double[] dArr2) {
        boolean z = false;
        if (dArr == null || dArr2 == null) {
            boolean z2 = dArr == null;
            if (dArr2 == null) {
                z = true;
            }
            return !(z2 ^ z);
        } else if (dArr.length != dArr2.length) {
            return false;
        } else {
            for (int i = 0; i < dArr.length; i++) {
                if (!Precision.equalsIncludingNaN(dArr[i], dArr2[i])) {
                    return false;
                }
            }
            return true;
        }
    }

    public static double[] normalizeArray(double[] dArr, double d) throws MathIllegalArgumentException, MathArithmeticException {
        if (Double.isInfinite(d)) {
            throw new MathIllegalArgumentException(LocalizedFormats.NORMALIZE_INFINITE, new Object[0]);
        } else if (!Double.isNaN(d)) {
            int length = dArr.length;
            double[] dArr2 = new double[length];
            int i = 0;
            double d2 = 0.0d;
            while (i < length) {
                if (!Double.isInfinite(dArr[i])) {
                    if (!Double.isNaN(dArr[i])) {
                        d2 += dArr[i];
                    }
                    i++;
                } else {
                    throw new MathIllegalArgumentException(LocalizedFormats.INFINITE_ARRAY_ELEMENT, Double.valueOf(dArr[i]), Integer.valueOf(i));
                }
            }
            if (d2 != 0.0d) {
                for (int i2 = 0; i2 < length; i2++) {
                    if (Double.isNaN(dArr[i2])) {
                        dArr2[i2] = Double.NaN;
                    } else {
                        dArr2[i2] = (dArr[i2] * d) / d2;
                    }
                }
                return dArr2;
            }
            throw new MathArithmeticException(LocalizedFormats.ARRAY_SUMS_TO_ZERO, new Object[0]);
        } else {
            throw new MathIllegalArgumentException(LocalizedFormats.NORMALIZE_NAN, new Object[0]);
        }
    }

    public static <T> T[] buildArray(Field<T> field, int i) {
        T[] tArr = (Object[]) Array.newInstance(field.getRuntimeClass(), i);
        Arrays.fill(tArr, field.getZero());
        return tArr;
    }

    public static <T> T[][] buildArray(Field<T> field, int i, int i2) {
        if (i2 < 0) {
            return (Object[][]) Array.newInstance(buildArray(field, 0).getClass(), i);
        }
        T[][] tArr = (Object[][]) Array.newInstance(field.getRuntimeClass(), new int[]{i, i2});
        for (int i3 = 0; i3 < i; i3++) {
            Arrays.fill(tArr[i3], field.getZero());
        }
        return tArr;
    }

    public static double[] convolve(double[] dArr, double[] dArr2) throws NullArgumentException, NoDataException {
        double[] dArr3 = dArr;
        double[] dArr4 = dArr2;
        MathUtils.checkNotNull(dArr);
        MathUtils.checkNotNull(dArr2);
        int length = dArr3.length;
        int length2 = dArr4.length;
        if (length == 0 || length2 == 0) {
            throw new NoDataException();
        }
        int i = (length + length2) - 1;
        double[] dArr5 = new double[i];
        int i2 = 0;
        while (i2 < i) {
            int i3 = i2 + 1;
            int max = FastMath.max(0, i3 - length);
            int i4 = i2 - max;
            double d = 0.0d;
            while (max < length2 && i4 >= 0) {
                d += dArr3[i4] * dArr4[max];
                max++;
                i4--;
            }
            dArr5[i2] = d;
            i2 = i3;
        }
        return dArr5;
    }

    public static void shuffle(int[] iArr, int i, Position position) {
        shuffle(iArr, i, position, new Well19937c());
    }

    /* renamed from: org.apache.commons.math3.util.MathArrays$3  reason: invalid class name */
    static /* synthetic */ class AnonymousClass3 {
        static final /* synthetic */ int[] $SwitchMap$org$apache$commons$math3$util$MathArrays$OrderDirection;
        static final /* synthetic */ int[] $SwitchMap$org$apache$commons$math3$util$MathArrays$Position;

        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x002e */
        static {
            /*
                org.apache.commons.math3.util.MathArrays$Position[] r0 = org.apache.commons.math3.util.MathArrays.Position.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$org$apache$commons$math3$util$MathArrays$Position = r0
                r1 = 1
                org.apache.commons.math3.util.MathArrays$Position r2 = org.apache.commons.math3.util.MathArrays.Position.TAIL     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r2 = r2.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r0[r2] = r1     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                r0 = 2
                int[] r2 = $SwitchMap$org$apache$commons$math3$util$MathArrays$Position     // Catch:{ NoSuchFieldError -> 0x001d }
                org.apache.commons.math3.util.MathArrays$Position r3 = org.apache.commons.math3.util.MathArrays.Position.HEAD     // Catch:{ NoSuchFieldError -> 0x001d }
                int r3 = r3.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2[r3] = r0     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                org.apache.commons.math3.util.MathArrays$OrderDirection[] r2 = org.apache.commons.math3.util.MathArrays.OrderDirection.values()
                int r2 = r2.length
                int[] r2 = new int[r2]
                $SwitchMap$org$apache$commons$math3$util$MathArrays$OrderDirection = r2
                org.apache.commons.math3.util.MathArrays$OrderDirection r3 = org.apache.commons.math3.util.MathArrays.OrderDirection.INCREASING     // Catch:{ NoSuchFieldError -> 0x002e }
                int r3 = r3.ordinal()     // Catch:{ NoSuchFieldError -> 0x002e }
                r2[r3] = r1     // Catch:{ NoSuchFieldError -> 0x002e }
            L_0x002e:
                int[] r1 = $SwitchMap$org$apache$commons$math3$util$MathArrays$OrderDirection     // Catch:{ NoSuchFieldError -> 0x0038 }
                org.apache.commons.math3.util.MathArrays$OrderDirection r2 = org.apache.commons.math3.util.MathArrays.OrderDirection.DECREASING     // Catch:{ NoSuchFieldError -> 0x0038 }
                int r2 = r2.ordinal()     // Catch:{ NoSuchFieldError -> 0x0038 }
                r1[r2] = r0     // Catch:{ NoSuchFieldError -> 0x0038 }
            L_0x0038:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.math3.util.MathArrays.AnonymousClass3.<clinit>():void");
        }
    }

    public static void shuffle(int[] iArr, int i, Position position, RandomGenerator randomGenerator) {
        int i2;
        int i3 = AnonymousClass3.$SwitchMap$org$apache$commons$math3$util$MathArrays$Position[position.ordinal()];
        if (i3 == 1) {
            for (int length = iArr.length - 1; length >= i; length--) {
                if (length == i) {
                    i2 = i;
                } else {
                    i2 = new UniformIntegerDistribution(randomGenerator, i, length).sample();
                }
                int i4 = iArr[i2];
                iArr[i2] = iArr[length];
                iArr[length] = i4;
            }
        } else if (i3 == 2) {
            int i5 = 0;
            while (i5 <= i) {
                int sample = i5 == i ? i : new UniformIntegerDistribution(randomGenerator, i5, i).sample();
                int i6 = iArr[sample];
                iArr[sample] = iArr[i5];
                iArr[i5] = i6;
                i5++;
            }
        } else {
            throw new MathInternalError();
        }
    }

    public static void shuffle(int[] iArr, RandomGenerator randomGenerator) {
        shuffle(iArr, 0, Position.TAIL, randomGenerator);
    }

    public static void shuffle(int[] iArr) {
        shuffle(iArr, new Well19937c());
    }

    public static int[] natural(int i) {
        return sequence(i, 0, 1);
    }

    public static int[] sequence(int i, int i2, int i3) {
        int[] iArr = new int[i];
        for (int i4 = 0; i4 < i; i4++) {
            iArr[i4] = (i4 * i3) + i2;
        }
        return iArr;
    }

    public static boolean verifyValues(double[] dArr, int i, int i2) throws MathIllegalArgumentException {
        return verifyValues(dArr, i, i2, false);
    }

    public static boolean verifyValues(double[] dArr, int i, int i2, boolean z) throws MathIllegalArgumentException {
        if (dArr == null) {
            throw new NullArgumentException(LocalizedFormats.INPUT_ARRAY, new Object[0]);
        } else if (i < 0) {
            throw new NotPositiveException(LocalizedFormats.START_POSITION, Integer.valueOf(i));
        } else if (i2 >= 0) {
            int i3 = i + i2;
            if (i3 <= dArr.length) {
                return i2 != 0 || z;
            }
            throw new NumberIsTooLargeException(LocalizedFormats.SUBARRAY_ENDS_AFTER_ARRAY_END, Integer.valueOf(i3), Integer.valueOf(dArr.length), true);
        } else {
            throw new NotPositiveException(LocalizedFormats.LENGTH, Integer.valueOf(i2));
        }
    }

    public static boolean verifyValues(double[] dArr, double[] dArr2, int i, int i2) throws MathIllegalArgumentException {
        return verifyValues(dArr, dArr2, i, i2, false);
    }

    public static boolean verifyValues(double[] dArr, double[] dArr2, int i, int i2, boolean z) throws MathIllegalArgumentException {
        if (dArr2 == null || dArr == null) {
            throw new NullArgumentException(LocalizedFormats.INPUT_ARRAY, new Object[0]);
        }
        checkEqualLength(dArr2, dArr);
        int i3 = i;
        boolean z2 = false;
        while (i3 < i + i2) {
            double d = dArr2[i3];
            if (Double.isNaN(d)) {
                throw new MathIllegalArgumentException(LocalizedFormats.NAN_ELEMENT_AT_INDEX, Integer.valueOf(i3));
            } else if (Double.isInfinite(d)) {
                throw new MathIllegalArgumentException(LocalizedFormats.INFINITE_ARRAY_ELEMENT, Double.valueOf(d), Integer.valueOf(i3));
            } else if (d >= 0.0d) {
                if (!z2 && d > 0.0d) {
                    z2 = true;
                }
                i3++;
            } else {
                throw new MathIllegalArgumentException(LocalizedFormats.NEGATIVE_ELEMENT_AT_INDEX, Integer.valueOf(i3), Double.valueOf(d));
            }
        }
        if (z2) {
            return verifyValues(dArr, i, i2, z);
        }
        throw new MathIllegalArgumentException(LocalizedFormats.WEIGHT_AT_LEAST_ONE_NON_ZERO, new Object[0]);
    }

    public static double[] concatenate(double[]... dArr) {
        int i = 0;
        for (double[] length : dArr) {
            i += length.length;
        }
        double[] dArr2 = new double[i];
        int i2 = 0;
        for (double[] dArr3 : dArr) {
            int length2 = dArr3.length;
            System.arraycopy(dArr3, 0, dArr2, i2, length2);
            i2 += length2;
        }
        return dArr2;
    }

    public static double[] unique(double[] dArr) {
        TreeSet treeSet = new TreeSet();
        int i = 0;
        for (double valueOf : dArr) {
            treeSet.add(Double.valueOf(valueOf));
        }
        int size = treeSet.size();
        double[] dArr2 = new double[size];
        Iterator it = treeSet.iterator();
        while (it.hasNext()) {
            i++;
            dArr2[size - i] = ((Double) it.next()).doubleValue();
        }
        return dArr2;
    }
}
