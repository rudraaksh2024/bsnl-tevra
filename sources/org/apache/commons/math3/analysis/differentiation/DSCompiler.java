package org.apache.commons.math3.analysis.differentiation;

import androidx.camera.view.PreviewView$1$$ExternalSyntheticBackportWithForwarding0;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicReference;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.MathArithmeticException;
import org.apache.commons.math3.exception.MathInternalError;
import org.apache.commons.math3.exception.NotPositiveException;
import org.apache.commons.math3.exception.NumberIsTooLargeException;
import org.apache.commons.math3.util.CombinatoricsUtils;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.MathArrays;

public class DSCompiler {
    private static AtomicReference<DSCompiler[][]> compilers = new AtomicReference<>((Object) null);
    private final int[][][] compIndirection;
    private final int[][] derivativesIndirection;
    private final int[] lowerIndirection;
    private final int[][][] multIndirection;
    private final int order;
    private final int parameters;
    private final int[][] sizes;

    private DSCompiler(int i, int i2, DSCompiler dSCompiler, DSCompiler dSCompiler2) throws NumberIsTooLargeException {
        this.parameters = i;
        this.order = i2;
        int[][] compileSizes = compileSizes(i, i2, dSCompiler);
        this.sizes = compileSizes;
        int[][] compileDerivativesIndirection = compileDerivativesIndirection(i, i2, dSCompiler, dSCompiler2);
        this.derivativesIndirection = compileDerivativesIndirection;
        int[] compileLowerIndirection = compileLowerIndirection(i, i2, dSCompiler, dSCompiler2);
        this.lowerIndirection = compileLowerIndirection;
        this.multIndirection = compileMultiplicationIndirection(i, i2, dSCompiler, dSCompiler2, compileLowerIndirection);
        this.compIndirection = compileCompositionIndirection(i, i2, dSCompiler, dSCompiler2, compileSizes, compileDerivativesIndirection);
    }

    public static DSCompiler getCompiler(int i, int i2) throws NumberIsTooLargeException {
        int i3;
        int i4;
        DSCompiler dSCompiler;
        DSCompiler dSCompiler2;
        DSCompiler[][] dSCompilerArr = compilers.get();
        if (dSCompilerArr != null && dSCompilerArr.length > i) {
            DSCompiler[] dSCompilerArr2 = dSCompilerArr[i];
            if (dSCompilerArr2.length > i2 && (dSCompiler2 = dSCompilerArr2[i2]) != null) {
                return dSCompiler2;
            }
        }
        if (dSCompilerArr == null) {
            i3 = 0;
        } else {
            i3 = dSCompilerArr.length;
        }
        int max = FastMath.max(i, i3);
        if (dSCompilerArr == null) {
            i4 = 0;
        } else {
            i4 = dSCompilerArr[0].length;
        }
        int[] iArr = new int[2];
        iArr[1] = FastMath.max(i2, i4) + 1;
        iArr[0] = max + 1;
        DSCompiler[][] dSCompilerArr3 = (DSCompiler[][]) Array.newInstance(DSCompiler.class, iArr);
        if (dSCompilerArr != null) {
            for (int i5 = 0; i5 < dSCompilerArr.length; i5++) {
                DSCompiler[] dSCompilerArr4 = dSCompilerArr[i5];
                System.arraycopy(dSCompilerArr4, 0, dSCompilerArr3[i5], 0, dSCompilerArr4.length);
            }
        }
        for (int i6 = 0; i6 <= i + i2; i6++) {
            for (int max2 = FastMath.max(0, i6 - i); max2 <= FastMath.min(i2, i6); max2++) {
                int i7 = i6 - max2;
                DSCompiler[] dSCompilerArr5 = dSCompilerArr3[i7];
                if (dSCompilerArr5[max2] == null) {
                    DSCompiler dSCompiler3 = null;
                    if (i7 == 0) {
                        dSCompiler = null;
                    } else {
                        dSCompiler = dSCompilerArr3[i7 - 1][max2];
                    }
                    if (max2 != 0) {
                        dSCompiler3 = dSCompilerArr5[max2 - 1];
                    }
                    dSCompilerArr5[max2] = new DSCompiler(i7, max2, dSCompiler, dSCompiler3);
                }
            }
        }
        PreviewView$1$$ExternalSyntheticBackportWithForwarding0.m(compilers, dSCompilerArr, dSCompilerArr3);
        return dSCompilerArr3[i][i2];
    }

    private static int[][] compileSizes(int i, int i2, DSCompiler dSCompiler) {
        int[] iArr = new int[2];
        iArr[1] = i2 + 1;
        int i3 = 0;
        iArr[0] = i + 1;
        int[][] iArr2 = (int[][]) Array.newInstance(Integer.TYPE, iArr);
        if (i == 0) {
            Arrays.fill(iArr2[0], 1);
        } else {
            System.arraycopy(dSCompiler.sizes, 0, iArr2, 0, i);
            iArr2[i][0] = 1;
            while (i3 < i2) {
                int[] iArr3 = iArr2[i];
                int i4 = i3 + 1;
                iArr3[i4] = iArr3[i3] + iArr2[i - 1][i4];
                i3 = i4;
            }
        }
        return iArr2;
    }

    private static int[][] compileDerivativesIndirection(int i, int i2, DSCompiler dSCompiler, DSCompiler dSCompiler2) {
        if (i == 0 || i2 == 0) {
            int[] iArr = new int[2];
            iArr[1] = i;
            iArr[0] = 1;
            return (int[][]) Array.newInstance(Integer.TYPE, iArr);
        }
        int length = dSCompiler.derivativesIndirection.length;
        int length2 = dSCompiler2.derivativesIndirection.length;
        int[] iArr2 = new int[2];
        iArr2[1] = i;
        iArr2[0] = length + length2;
        int[][] iArr3 = (int[][]) Array.newInstance(Integer.TYPE, iArr2);
        for (int i3 = 0; i3 < length; i3++) {
            System.arraycopy(dSCompiler.derivativesIndirection[i3], 0, iArr3[i3], 0, i - 1);
        }
        for (int i4 = 0; i4 < length2; i4++) {
            int i5 = length + i4;
            System.arraycopy(dSCompiler2.derivativesIndirection[i4], 0, iArr3[i5], 0, i);
            int[] iArr4 = iArr3[i5];
            int i6 = i - 1;
            iArr4[i6] = iArr4[i6] + 1;
        }
        return iArr3;
    }

    private static int[] compileLowerIndirection(int i, int i2, DSCompiler dSCompiler, DSCompiler dSCompiler2) {
        if (i == 0 || i2 <= 1) {
            return new int[]{0};
        }
        int[] iArr = dSCompiler.lowerIndirection;
        int length = iArr.length;
        int length2 = dSCompiler2.lowerIndirection.length;
        int[] iArr2 = new int[(length + length2)];
        System.arraycopy(iArr, 0, iArr2, 0, length);
        for (int i3 = 0; i3 < length2; i3++) {
            iArr2[length + i3] = dSCompiler.getSize() + dSCompiler2.lowerIndirection[i3];
        }
        return iArr2;
    }

    private static int[][][] compileMultiplicationIndirection(int i, int i2, DSCompiler dSCompiler, DSCompiler dSCompiler2, int[] iArr) {
        DSCompiler dSCompiler3 = dSCompiler2;
        int i3 = 3;
        if (i == 0 || i2 == 0) {
            return new int[][][]{new int[][]{new int[]{1, 0, 0}}};
        }
        int[][][] iArr2 = dSCompiler.multIndirection;
        int length = iArr2.length;
        int length2 = dSCompiler3.multIndirection.length;
        int[][][] iArr3 = new int[(length + length2)][][];
        System.arraycopy(iArr2, 0, iArr3, 0, length);
        int i4 = 0;
        while (i4 < length2) {
            int[][] iArr4 = dSCompiler3.multIndirection[i4];
            ArrayList arrayList = new ArrayList(iArr4.length * 2);
            for (int i5 = 0; i5 < iArr4.length; i5++) {
                int[] iArr5 = new int[i3];
                int[] iArr6 = iArr4[i5];
                iArr5[0] = iArr6[0];
                iArr5[1] = iArr[iArr6[1]];
                iArr5[2] = iArr6[2] + length;
                arrayList.add(iArr5);
                int[] iArr7 = new int[i3];
                int[] iArr8 = iArr4[i5];
                iArr7[0] = iArr8[0];
                iArr7[1] = iArr8[1] + length;
                iArr7[2] = iArr[iArr8[2]];
                arrayList.add(iArr7);
            }
            ArrayList arrayList2 = new ArrayList(arrayList.size());
            for (int i6 = 0; i6 < arrayList.size(); i6++) {
                int[] iArr9 = (int[]) arrayList.get(i6);
                if (iArr9[0] > 0) {
                    for (int i7 = i6 + 1; i7 < arrayList.size(); i7++) {
                        int[] iArr10 = (int[]) arrayList.get(i7);
                        if (iArr9[1] == iArr10[1] && iArr9[2] == iArr10[2]) {
                            iArr9[0] = iArr9[0] + iArr10[0];
                            iArr10[0] = 0;
                        }
                    }
                    arrayList2.add(iArr9);
                }
            }
            iArr3[length + i4] = (int[][]) arrayList2.toArray(new int[arrayList2.size()][]);
            i4++;
            i3 = 3;
        }
        return iArr3;
    }

    private static int[][][] compileCompositionIndirection(int i, int i2, DSCompiler dSCompiler, DSCompiler dSCompiler2, int[][] iArr, int[][] iArr2) throws NumberIsTooLargeException {
        int i3 = i;
        int i4 = i2;
        DSCompiler dSCompiler3 = dSCompiler2;
        int[][] iArr3 = iArr;
        int i5 = 0;
        int i6 = 1;
        if (i3 == 0 || i4 == 0) {
            return new int[][][]{new int[][]{new int[]{1, 0}}};
        }
        int[][][] iArr4 = dSCompiler.compIndirection;
        int length = iArr4.length;
        int length2 = dSCompiler3.compIndirection.length;
        int[][][] iArr5 = new int[(length + length2)][][];
        System.arraycopy(iArr4, 0, iArr5, 0, length);
        int i7 = 0;
        while (i7 < length2) {
            ArrayList arrayList = new ArrayList();
            int[][] iArr6 = dSCompiler3.compIndirection[i7];
            int length3 = iArr6.length;
            int i8 = i5;
            while (i8 < length3) {
                int[] iArr7 = iArr6[i8];
                int length4 = iArr7.length + i6;
                int[] iArr8 = new int[length4];
                iArr8[i5] = iArr7[i5];
                iArr8[1] = iArr7[1] + 1;
                int[] iArr9 = new int[i3];
                int i9 = i3 - 1;
                iArr9[i9] = 1;
                int i10 = i8;
                iArr8[iArr7.length] = getPartialDerivativeIndex(i3, i4, iArr3, iArr9);
                int i11 = length3;
                int i12 = 2;
                while (i12 < iArr7.length) {
                    int i13 = i12;
                    iArr8[i13] = convertIndex(iArr7[i12], i, dSCompiler3.derivativesIndirection, i, i2, iArr);
                    i12 = i13 + 1;
                    iArr7 = iArr7;
                    arrayList = arrayList;
                    iArr6 = iArr6;
                    length2 = length2;
                    i7 = i7;
                    iArr5 = iArr5;
                    i10 = i10;
                }
                int i14 = i10;
                int[][] iArr10 = iArr6;
                int i15 = i7;
                int i16 = length2;
                int[][][] iArr11 = iArr5;
                int[] iArr12 = iArr7;
                ArrayList arrayList2 = arrayList;
                Arrays.sort(iArr8, 2, length4);
                arrayList2.add(iArr8);
                int i17 = 2;
                while (i17 < iArr12.length) {
                    int length5 = iArr12.length;
                    int[] iArr13 = new int[length5];
                    iArr13[0] = iArr12[0];
                    iArr13[1] = iArr12[1];
                    int i18 = 2;
                    while (i18 < iArr12.length) {
                        int i19 = i18;
                        int[] iArr14 = iArr12;
                        int[] iArr15 = iArr13;
                        int convertIndex = convertIndex(iArr12[i18], i, dSCompiler3.derivativesIndirection, i, i2, iArr);
                        iArr15[i19] = convertIndex;
                        if (i19 == i17) {
                            System.arraycopy(iArr2[convertIndex], 0, iArr9, 0, i3);
                            iArr9[i9] = iArr9[i9] + 1;
                            iArr15[i19] = getPartialDerivativeIndex(i3, i4, iArr3, iArr9);
                        }
                        i18 = i19 + 1;
                        dSCompiler3 = dSCompiler2;
                        iArr13 = iArr15;
                        iArr12 = iArr14;
                    }
                    int[] iArr16 = iArr12;
                    int[] iArr17 = iArr13;
                    Arrays.sort(iArr17, 2, length5);
                    arrayList2.add(iArr17);
                    i17++;
                    dSCompiler3 = dSCompiler2;
                    iArr12 = iArr16;
                }
                i8 = i14 + 1;
                dSCompiler3 = dSCompiler2;
                arrayList = arrayList2;
                length3 = i11;
                iArr6 = iArr10;
                length2 = i16;
                i7 = i15;
                i5 = 0;
                i6 = 1;
                iArr5 = iArr11;
            }
            int i20 = i7;
            int i21 = length2;
            int[][][] iArr18 = iArr5;
            ArrayList arrayList3 = arrayList;
            ArrayList arrayList4 = new ArrayList(arrayList3.size());
            for (int i22 = 0; i22 < arrayList3.size(); i22++) {
                int[] iArr19 = (int[]) arrayList3.get(i22);
                if (iArr19[0] > 0) {
                    for (int i23 = i22 + 1; i23 < arrayList3.size(); i23++) {
                        int[] iArr20 = (int[]) arrayList3.get(i23);
                        boolean z = iArr19.length == iArr20.length;
                        int i24 = 1;
                        while (z && i24 < iArr19.length) {
                            z &= iArr19[i24] == iArr20[i24];
                            i24++;
                        }
                        if (z) {
                            iArr19[0] = iArr19[0] + iArr20[0];
                            iArr20[0] = 0;
                        }
                    }
                    arrayList4.add(iArr19);
                }
            }
            iArr18[length + i20] = (int[][]) arrayList4.toArray(new int[arrayList4.size()][]);
            i7 = i20 + 1;
            iArr5 = iArr18;
            dSCompiler3 = dSCompiler2;
            length2 = i21;
            i5 = 0;
            i6 = 1;
        }
        return iArr5;
    }

    public int getPartialDerivativeIndex(int... iArr) throws DimensionMismatchException, NumberIsTooLargeException {
        if (iArr.length == getFreeParameters()) {
            return getPartialDerivativeIndex(this.parameters, this.order, this.sizes, iArr);
        }
        throw new DimensionMismatchException(iArr.length, getFreeParameters());
    }

    private static int getPartialDerivativeIndex(int i, int i2, int[][] iArr, int... iArr2) throws NumberIsTooLargeException {
        int i3 = i - 1;
        int i4 = 0;
        int i5 = i2;
        int i6 = 0;
        while (i3 >= 0) {
            int i7 = iArr2[i3];
            i6 += i7;
            if (i6 <= i2) {
                while (true) {
                    int i8 = i7 - 1;
                    if (i7 <= 0) {
                        break;
                    }
                    i4 += iArr[i3][i5];
                    i7 = i8;
                    i5--;
                }
                i3--;
            } else {
                throw new NumberIsTooLargeException(Integer.valueOf(i6), Integer.valueOf(i2), true);
            }
        }
        return i4;
    }

    private static int convertIndex(int i, int i2, int[][] iArr, int i3, int i4, int[][] iArr2) throws NumberIsTooLargeException {
        int[] iArr3 = new int[i3];
        System.arraycopy(iArr[i], 0, iArr3, 0, FastMath.min(i2, i3));
        return getPartialDerivativeIndex(i3, i4, iArr2, iArr3);
    }

    public int[] getPartialDerivativeOrders(int i) {
        return this.derivativesIndirection[i];
    }

    public int getFreeParameters() {
        return this.parameters;
    }

    public int getOrder() {
        return this.order;
    }

    public int getSize() {
        return this.sizes[this.parameters][this.order];
    }

    public void linearCombination(double d, double[] dArr, int i, double d2, double[] dArr2, int i2, double[] dArr3, int i3) {
        for (int i4 = 0; i4 < getSize(); i4++) {
            dArr3[i3 + i4] = MathArrays.linearCombination(d, dArr[i + i4], d2, dArr2[i2 + i4]);
        }
    }

    public void linearCombination(double d, double[] dArr, int i, double d2, double[] dArr2, int i2, double d3, double[] dArr3, int i3, double[] dArr4, int i4) {
        for (int i5 = 0; i5 < getSize(); i5++) {
            dArr4[i4 + i5] = MathArrays.linearCombination(d, dArr[i + i5], d2, dArr2[i2 + i5], d3, dArr3[i3 + i5]);
        }
    }

    public void linearCombination(double d, double[] dArr, int i, double d2, double[] dArr2, int i2, double d3, double[] dArr3, int i3, double d4, double[] dArr4, int i4, double[] dArr5, int i5) {
        for (int i6 = 0; i6 < getSize(); i6++) {
            dArr5[i5 + i6] = MathArrays.linearCombination(d, dArr[i + i6], d2, dArr2[i2 + i6], d3, dArr3[i3 + i6], d4, dArr4[i4 + i6]);
        }
    }

    public void add(double[] dArr, int i, double[] dArr2, int i2, double[] dArr3, int i3) {
        for (int i4 = 0; i4 < getSize(); i4++) {
            dArr3[i3 + i4] = dArr[i + i4] + dArr2[i2 + i4];
        }
    }

    public void subtract(double[] dArr, int i, double[] dArr2, int i2, double[] dArr3, int i3) {
        for (int i4 = 0; i4 < getSize(); i4++) {
            dArr3[i3 + i4] = dArr[i + i4] - dArr2[i2 + i4];
        }
    }

    public void multiply(double[] dArr, int i, double[] dArr2, int i2, double[] dArr3, int i3) {
        int i4 = 0;
        while (true) {
            int[][][] iArr = this.multIndirection;
            if (i4 < iArr.length) {
                int[][] iArr2 = iArr[i4];
                double d = 0.0d;
                for (int[] iArr3 : iArr2) {
                    d += ((double) iArr3[0]) * dArr[iArr3[1] + i] * dArr2[i2 + iArr3[2]];
                }
                dArr3[i3 + i4] = d;
                i4++;
            } else {
                return;
            }
        }
    }

    public void divide(double[] dArr, int i, double[] dArr2, int i2, double[] dArr3, int i3) {
        double[] dArr4 = new double[getSize()];
        int i4 = i;
        pow(dArr2, i4, -1, dArr4, 0);
        multiply(dArr, i4, dArr4, 0, dArr3, i3);
    }

    public void remainder(double[] dArr, int i, double[] dArr2, int i2, double[] dArr3, int i3) {
        double IEEEremainder = FastMath.IEEEremainder(dArr[i], dArr2[i2]);
        double rint = FastMath.rint((dArr[i] - IEEEremainder) / dArr2[i2]);
        dArr3[i3] = IEEEremainder;
        for (int i4 = 1; i4 < getSize(); i4++) {
            dArr3[i3 + i4] = dArr[i + i4] - (dArr2[i2 + i4] * rint);
        }
    }

    public void pow(double d, double[] dArr, int i, double[] dArr2, int i2) {
        int i3 = 1;
        int i4 = this.order + 1;
        double[] dArr3 = new double[i4];
        if (d == 0.0d) {
            double d2 = dArr[i];
            if (d2 == 0.0d) {
                dArr3[0] = 1.0d;
                double d3 = Double.POSITIVE_INFINITY;
                while (i3 < i4) {
                    d3 = -d3;
                    dArr3[i3] = d3;
                    i3++;
                }
            } else if (d2 < 0.0d) {
                Arrays.fill(dArr3, Double.NaN);
            }
        } else {
            dArr3[0] = FastMath.pow(d, dArr[i]);
            double log = FastMath.log(d);
            while (i3 < i4) {
                dArr3[i3] = dArr3[i3 - 1] * log;
                i3++;
            }
        }
        compose(dArr, i, dArr3, dArr2, i2);
    }

    public void pow(double[] dArr, int i, double d, double[] dArr2, int i2) {
        int i3 = this.order;
        double[] dArr3 = new double[(i3 + 1)];
        double pow = FastMath.pow(dArr[i], d - ((double) i3));
        for (int i4 = this.order; i4 > 0; i4--) {
            dArr3[i4] = pow;
            pow *= dArr[i];
        }
        dArr3[0] = pow;
        double d2 = d;
        for (int i5 = 1; i5 <= this.order; i5++) {
            dArr3[i5] = dArr3[i5] * d2;
            d2 *= d - ((double) i5);
        }
        compose(dArr, i, dArr3, dArr2, i2);
    }

    public void pow(double[] dArr, int i, int i2, double[] dArr2, int i3) {
        if (i2 == 0) {
            dArr2[i3] = 1.0d;
            Arrays.fill(dArr2, i3 + 1, i3 + getSize(), 0.0d);
            return;
        }
        int i4 = this.order;
        double[] dArr3 = new double[(i4 + 1)];
        if (i2 > 0) {
            int min = FastMath.min(i4, i2);
            double pow = FastMath.pow(dArr[i], i2 - min);
            while (min > 0) {
                dArr3[min] = pow;
                pow *= dArr[i];
                min--;
            }
            dArr3[0] = pow;
        } else {
            double d = 1.0d / dArr[i];
            double pow2 = FastMath.pow(d, -i2);
            for (int i5 = 0; i5 <= this.order; i5++) {
                dArr3[i5] = pow2;
                pow2 *= d;
            }
        }
        double d2 = (double) i2;
        for (int i6 = 1; i6 <= this.order; i6++) {
            dArr3[i6] = dArr3[i6] * d2;
            d2 *= (double) (i2 - i6);
        }
        compose(dArr, i, dArr3, dArr2, i3);
    }

    public void pow(double[] dArr, int i, double[] dArr2, int i2, double[] dArr3, int i3) {
        double[] dArr4 = new double[getSize()];
        log(dArr, i, dArr4, 0);
        double[] dArr5 = new double[getSize()];
        multiply(dArr4, 0, dArr2, i2, dArr5, 0);
        exp(dArr5, 0, dArr3, i3);
    }

    public void rootN(double[] dArr, int i, int i2, double[] dArr2, int i3) {
        double d;
        double d2;
        double d3;
        int i4 = i2;
        double[] dArr3 = new double[(this.order + 1)];
        if (i4 == 2) {
            double sqrt = FastMath.sqrt(dArr[i]);
            dArr3[0] = sqrt;
            d = 0.5d / sqrt;
        } else {
            if (i4 == 3) {
                d3 = FastMath.cbrt(dArr[i]);
                dArr3[0] = d3;
                d2 = 3.0d * d3;
            } else {
                d2 = (double) i4;
                double pow = FastMath.pow(dArr[i], 1.0d / d2);
                dArr3[0] = pow;
                d3 = FastMath.pow(pow, i4 - 1);
            }
            d = 1.0d / (d2 * d3);
        }
        double d4 = 1.0d / ((double) i4);
        double d5 = 1.0d / dArr[i];
        for (int i5 = 1; i5 <= this.order; i5++) {
            dArr3[i5] = d;
            d *= (d4 - ((double) i5)) * d5;
        }
        compose(dArr, i, dArr3, dArr2, i3);
    }

    public void exp(double[] dArr, int i, double[] dArr2, int i2) {
        double[] dArr3 = new double[(this.order + 1)];
        Arrays.fill(dArr3, FastMath.exp(dArr[i]));
        compose(dArr, i, dArr3, dArr2, i2);
    }

    public void expm1(double[] dArr, int i, double[] dArr2, int i2) {
        double[] dArr3 = new double[(this.order + 1)];
        dArr3[0] = FastMath.expm1(dArr[i]);
        Arrays.fill(dArr3, 1, this.order + 1, FastMath.exp(dArr[i]));
        compose(dArr, i, dArr3, dArr2, i2);
    }

    public void log(double[] dArr, int i, double[] dArr2, int i2) {
        double[] dArr3 = new double[(this.order + 1)];
        dArr3[0] = FastMath.log(dArr[i]);
        if (this.order > 0) {
            double d = 1.0d / dArr[i];
            double d2 = d;
            for (int i3 = 1; i3 <= this.order; i3++) {
                dArr3[i3] = d2;
                d2 *= ((double) (-i3)) * d;
            }
        }
        compose(dArr, i, dArr3, dArr2, i2);
    }

    public void log1p(double[] dArr, int i, double[] dArr2, int i2) {
        double[] dArr3 = new double[(this.order + 1)];
        dArr3[0] = FastMath.log1p(dArr[i]);
        if (this.order > 0) {
            double d = 1.0d / (dArr[i] + 1.0d);
            double d2 = d;
            for (int i3 = 1; i3 <= this.order; i3++) {
                dArr3[i3] = d2;
                d2 *= ((double) (-i3)) * d;
            }
        }
        compose(dArr, i, dArr3, dArr2, i2);
    }

    public void log10(double[] dArr, int i, double[] dArr2, int i2) {
        double[] dArr3 = new double[(this.order + 1)];
        dArr3[0] = FastMath.log10(dArr[i]);
        if (this.order > 0) {
            double d = 1.0d / dArr[i];
            double log = d / FastMath.log(10.0d);
            for (int i3 = 1; i3 <= this.order; i3++) {
                dArr3[i3] = log;
                log *= ((double) (-i3)) * d;
            }
        }
        compose(dArr, i, dArr3, dArr2, i2);
    }

    public void cos(double[] dArr, int i, double[] dArr2, int i2) {
        double[] dArr3 = new double[(this.order + 1)];
        dArr3[0] = FastMath.cos(dArr[i]);
        if (this.order > 0) {
            dArr3[1] = -FastMath.sin(dArr[i]);
            for (int i3 = 2; i3 <= this.order; i3++) {
                dArr3[i3] = -dArr3[i3 - 2];
            }
        }
        compose(dArr, i, dArr3, dArr2, i2);
    }

    public void sin(double[] dArr, int i, double[] dArr2, int i2) {
        double[] dArr3 = new double[(this.order + 1)];
        dArr3[0] = FastMath.sin(dArr[i]);
        if (this.order > 0) {
            dArr3[1] = FastMath.cos(dArr[i]);
            for (int i3 = 2; i3 <= this.order; i3++) {
                dArr3[i3] = -dArr3[i3 - 2];
            }
        }
        compose(dArr, i, dArr3, dArr2, i2);
    }

    public void tan(double[] dArr, int i, double[] dArr2, int i2) {
        double d;
        double[] dArr3;
        boolean z;
        int i3;
        double[] dArr4 = new double[(this.order + 1)];
        double tan = FastMath.tan(dArr[i]);
        boolean z2 = false;
        dArr4[0] = tan;
        int i4 = this.order;
        if (i4 > 0) {
            int i5 = 2;
            double[] dArr5 = new double[(i4 + 2)];
            dArr5[1] = 1.0d;
            double d2 = tan * tan;
            int i6 = 1;
            while (i6 <= this.order) {
                int i7 = i6 + 1;
                dArr5[i7] = ((double) i6) * dArr5[i6];
                double d3 = 0.0d;
                int i8 = i7;
                while (i8 >= 0) {
                    d3 = (d3 * d2) + dArr5[i8];
                    if (i8 > i5) {
                        int i9 = i8 - 1;
                        dArr3 = dArr4;
                        int i10 = i8 - 3;
                        d = d2;
                        dArr5[i8 - 2] = (((double) i9) * dArr5[i9]) + (((double) i10) * dArr5[i10]);
                        i3 = 2;
                    } else {
                        dArr3 = dArr4;
                        i3 = i5;
                        d = d2;
                        if (i8 == i3) {
                            z = false;
                            dArr5[0] = dArr5[1];
                            i8 -= 2;
                            i5 = i3;
                            z2 = z;
                            dArr4 = dArr3;
                            d2 = d;
                        }
                    }
                    z = false;
                    i8 -= 2;
                    i5 = i3;
                    z2 = z;
                    dArr4 = dArr3;
                    d2 = d;
                }
                double[] dArr6 = dArr4;
                double d4 = d2;
                boolean z3 = z2;
                int i11 = i5;
                if ((i6 & 1) == 0) {
                    d3 *= tan;
                }
                dArr6[i6] = d3;
                i5 = i11;
                z2 = z3;
                i6 = i7;
                dArr4 = dArr6;
                d2 = d4;
            }
        }
        compose(dArr, i, dArr4, dArr2, i2);
    }

    public void acos(double[] dArr, int i, double[] dArr2, int i2) {
        double d;
        double d2;
        int i3;
        DSCompiler dSCompiler = this;
        double[] dArr3 = new double[(dSCompiler.order + 1)];
        double d3 = dArr[i];
        dArr3[0] = FastMath.acos(d3);
        int i4 = dSCompiler.order;
        if (i4 > 0) {
            double[] dArr4 = new double[i4];
            dArr4[0] = -1.0d;
            double d4 = d3 * d3;
            double d5 = 1.0d / (1.0d - d4);
            double sqrt = FastMath.sqrt(d5);
            dArr3[1] = dArr4[0] * sqrt;
            int i5 = 2;
            int i6 = 2;
            while (i6 <= dSCompiler.order) {
                int i7 = i6 - 1;
                dArr4[i7] = ((double) i7) * dArr4[i6 - 2];
                double d6 = 0.0d;
                while (i7 >= 0) {
                    double d7 = (d6 * d4) + dArr4[i7];
                    if (i7 > i5) {
                        int i8 = i7 - 1;
                        d2 = d7;
                        d = d4;
                        dArr4[i7 - 2] = (((double) i8) * dArr4[i8]) + (((double) ((i6 * 2) - i7)) * dArr4[i7 - 3]);
                        i3 = 2;
                    } else {
                        d2 = d7;
                        d = d4;
                        i3 = i5;
                        if (i7 == i3) {
                            dArr4[0] = dArr4[1];
                            i7 -= 2;
                            i5 = i3;
                            d6 = d2;
                            d4 = d;
                        }
                    }
                    i7 -= 2;
                    i5 = i3;
                    d6 = d2;
                    d4 = d;
                }
                double d8 = d4;
                int i9 = i5;
                if ((i6 & 1) == 0) {
                    d6 *= d3;
                }
                sqrt *= d5;
                dArr3[i6] = d6 * sqrt;
                i6++;
                i5 = i9;
                d4 = d8;
                dSCompiler = this;
            }
        }
        compose(dArr, i, dArr3, dArr2, i2);
    }

    public void asin(double[] dArr, int i, double[] dArr2, int i2) {
        double d;
        double d2;
        int i3;
        DSCompiler dSCompiler = this;
        double[] dArr3 = new double[(dSCompiler.order + 1)];
        double d3 = dArr[i];
        dArr3[0] = FastMath.asin(d3);
        int i4 = dSCompiler.order;
        if (i4 > 0) {
            double[] dArr4 = new double[i4];
            dArr4[0] = 1.0d;
            double d4 = d3 * d3;
            double d5 = 1.0d / (1.0d - d4);
            double sqrt = FastMath.sqrt(d5);
            dArr3[1] = dArr4[0] * sqrt;
            int i5 = 2;
            int i6 = 2;
            while (i6 <= dSCompiler.order) {
                int i7 = i6 - 1;
                dArr4[i7] = ((double) i7) * dArr4[i6 - 2];
                double d6 = 0.0d;
                while (i7 >= 0) {
                    double d7 = (d6 * d4) + dArr4[i7];
                    if (i7 > i5) {
                        int i8 = i7 - 1;
                        d2 = d7;
                        d = d4;
                        dArr4[i7 - 2] = (((double) i8) * dArr4[i8]) + (((double) ((i6 * 2) - i7)) * dArr4[i7 - 3]);
                        i3 = 2;
                    } else {
                        d2 = d7;
                        d = d4;
                        i3 = i5;
                        if (i7 == i3) {
                            dArr4[0] = dArr4[1];
                            i7 -= 2;
                            i5 = i3;
                            d6 = d2;
                            d4 = d;
                        }
                    }
                    i7 -= 2;
                    i5 = i3;
                    d6 = d2;
                    d4 = d;
                }
                double d8 = d4;
                int i9 = i5;
                if ((i6 & 1) == 0) {
                    d6 *= d3;
                }
                sqrt *= d5;
                dArr3[i6] = d6 * sqrt;
                i6++;
                i5 = i9;
                d4 = d8;
                dSCompiler = this;
            }
        }
        compose(dArr, i, dArr3, dArr2, i2);
    }

    public void atan(double[] dArr, int i, double[] dArr2, int i2) {
        double d;
        double d2;
        int i3;
        DSCompiler dSCompiler = this;
        double[] dArr3 = new double[(dSCompiler.order + 1)];
        double d3 = dArr[i];
        dArr3[0] = FastMath.atan(d3);
        int i4 = dSCompiler.order;
        if (i4 > 0) {
            double[] dArr4 = new double[i4];
            dArr4[0] = 1.0d;
            double d4 = d3 * d3;
            double d5 = 1.0d / (d4 + 1.0d);
            dArr3[1] = 1.0d * d5;
            int i5 = 2;
            int i6 = 2;
            double d6 = d5;
            while (i6 <= dSCompiler.order) {
                int i7 = i6 - 1;
                double[] dArr5 = dArr3;
                dArr4[i7] = ((double) (-i6)) * dArr4[i6 - 2];
                double d7 = 0.0d;
                while (i7 >= 0) {
                    double d8 = (d7 * d4) + dArr4[i7];
                    if (i7 > i5) {
                        int i8 = i7 - 1;
                        d2 = d8;
                        d = d4;
                        dArr4[i7 - 2] = (((double) i8) * dArr4[i8]) + (((double) (i8 - (i6 * 2))) * dArr4[i7 - 3]);
                        i3 = 2;
                    } else {
                        d2 = d8;
                        i3 = i5;
                        d = d4;
                        if (i7 == i3) {
                            dArr4[0] = dArr4[1];
                            i7 -= 2;
                            i5 = i3;
                            d7 = d2;
                            d4 = d;
                        }
                    }
                    i7 -= 2;
                    i5 = i3;
                    d7 = d2;
                    d4 = d;
                }
                int i9 = i5;
                double d9 = d4;
                if ((i6 & 1) == 0) {
                    d7 *= d3;
                }
                d6 *= d5;
                dArr5[i6] = d7 * d6;
                i6++;
                dArr3 = dArr5;
                d4 = d9;
                i5 = i9;
                dSCompiler = this;
            }
        }
        compose(dArr, i, dArr3, dArr2, i2);
    }

    public void atan2(double[] dArr, int i, double[] dArr2, int i2, double[] dArr3, int i3) {
        double[] dArr4 = new double[getSize()];
        multiply(dArr2, i2, dArr2, i2, dArr4, 0);
        int size = getSize();
        double[] dArr5 = new double[size];
        double[] dArr6 = dArr5;
        multiply(dArr, i, dArr, i, dArr6, 0);
        add(dArr4, 0, dArr5, 0, dArr6, 0);
        rootN(dArr5, 0, 2, dArr4, 0);
        if (dArr2[i2] >= 0.0d) {
            add(dArr4, 0, dArr2, i2, dArr5, 0);
            divide(dArr, i, dArr5, 0, dArr4, 0);
            atan(dArr4, 0, dArr5, 0);
            for (int i4 = 0; i4 < size; i4++) {
                dArr3[i3 + i4] = dArr5[i4] * 2.0d;
            }
        } else {
            subtract(dArr4, 0, dArr2, i2, dArr5, 0);
            divide(dArr, i, dArr5, 0, dArr4, 0);
            atan(dArr4, 0, dArr5, 0);
            double d = dArr5[0];
            dArr3[i3] = (d <= 0.0d ? -3.141592653589793d : 3.141592653589793d) - (d * 2.0d);
            for (int i5 = 1; i5 < size; i5++) {
                dArr3[i3 + i5] = dArr5[i5] * -2.0d;
            }
        }
        dArr3[i3] = FastMath.atan2(dArr[i], dArr2[i2]);
    }

    public void cosh(double[] dArr, int i, double[] dArr2, int i2) {
        double[] dArr3 = new double[(this.order + 1)];
        dArr3[0] = FastMath.cosh(dArr[i]);
        if (this.order > 0) {
            dArr3[1] = FastMath.sinh(dArr[i]);
            for (int i3 = 2; i3 <= this.order; i3++) {
                dArr3[i3] = dArr3[i3 - 2];
            }
        }
        compose(dArr, i, dArr3, dArr2, i2);
    }

    public void sinh(double[] dArr, int i, double[] dArr2, int i2) {
        double[] dArr3 = new double[(this.order + 1)];
        dArr3[0] = FastMath.sinh(dArr[i]);
        if (this.order > 0) {
            dArr3[1] = FastMath.cosh(dArr[i]);
            for (int i3 = 2; i3 <= this.order; i3++) {
                dArr3[i3] = dArr3[i3 - 2];
            }
        }
        compose(dArr, i, dArr3, dArr2, i2);
    }

    public void tanh(double[] dArr, int i, double[] dArr2, int i2) {
        double d;
        double[] dArr3;
        boolean z;
        int i3;
        double[] dArr4 = new double[(this.order + 1)];
        double tanh = FastMath.tanh(dArr[i]);
        boolean z2 = false;
        dArr4[0] = tanh;
        int i4 = this.order;
        if (i4 > 0) {
            int i5 = 2;
            double[] dArr5 = new double[(i4 + 2)];
            dArr5[1] = 1.0d;
            double d2 = tanh * tanh;
            int i6 = 1;
            while (i6 <= this.order) {
                int i7 = i6 + 1;
                dArr5[i7] = ((double) (-i6)) * dArr5[i6];
                double d3 = 0.0d;
                int i8 = i7;
                while (i8 >= 0) {
                    d3 = (d3 * d2) + dArr5[i8];
                    if (i8 > i5) {
                        int i9 = i8 - 1;
                        dArr3 = dArr4;
                        int i10 = i8 - 3;
                        d = d2;
                        dArr5[i8 - 2] = (((double) i9) * dArr5[i9]) - (((double) i10) * dArr5[i10]);
                        i3 = 2;
                    } else {
                        dArr3 = dArr4;
                        i3 = i5;
                        d = d2;
                        if (i8 == i3) {
                            z = false;
                            dArr5[0] = dArr5[1];
                            i8 -= 2;
                            i5 = i3;
                            z2 = z;
                            dArr4 = dArr3;
                            d2 = d;
                        }
                    }
                    z = false;
                    i8 -= 2;
                    i5 = i3;
                    z2 = z;
                    dArr4 = dArr3;
                    d2 = d;
                }
                double[] dArr6 = dArr4;
                double d4 = d2;
                boolean z3 = z2;
                int i11 = i5;
                if ((i6 & 1) == 0) {
                    d3 *= tanh;
                }
                dArr6[i6] = d3;
                i5 = i11;
                z2 = z3;
                i6 = i7;
                dArr4 = dArr6;
                d2 = d4;
            }
        }
        compose(dArr, i, dArr4, dArr2, i2);
    }

    public void acosh(double[] dArr, int i, double[] dArr2, int i2) {
        double d;
        double d2;
        int i3;
        DSCompiler dSCompiler = this;
        double[] dArr3 = new double[(dSCompiler.order + 1)];
        double d3 = dArr[i];
        dArr3[0] = FastMath.acosh(d3);
        int i4 = dSCompiler.order;
        if (i4 > 0) {
            double[] dArr4 = new double[i4];
            dArr4[0] = 1.0d;
            double d4 = d3 * d3;
            double d5 = 1.0d / (d4 - 1.0d);
            double sqrt = FastMath.sqrt(d5);
            dArr3[1] = dArr4[0] * sqrt;
            int i5 = 2;
            int i6 = 2;
            while (i6 <= dSCompiler.order) {
                int i7 = i6 - 1;
                double[] dArr5 = dArr3;
                dArr4[i7] = ((double) (1 - i6)) * dArr4[i6 - 2];
                double d6 = 0.0d;
                while (i7 >= 0) {
                    double d7 = (d6 * d4) + dArr4[i7];
                    if (i7 > i5) {
                        d2 = d7;
                        d = d4;
                        dArr4[i7 - 2] = (((double) (1 - i7)) * dArr4[i7 - 1]) + (((double) (i7 - (i6 * 2))) * dArr4[i7 - 3]);
                        i3 = 2;
                    } else {
                        d2 = d7;
                        d = d4;
                        i3 = i5;
                        if (i7 == i3) {
                            dArr4[0] = -dArr4[1];
                            i7 -= 2;
                            i5 = i3;
                            d6 = d2;
                            d4 = d;
                        }
                    }
                    i7 -= 2;
                    i5 = i3;
                    d6 = d2;
                    d4 = d;
                }
                double d8 = d4;
                int i8 = i5;
                if ((i6 & 1) == 0) {
                    d6 *= d3;
                }
                sqrt *= d5;
                dArr5[i6] = d6 * sqrt;
                i6++;
                i5 = i8;
                dArr3 = dArr5;
                d4 = d8;
                dSCompiler = this;
            }
        }
        compose(dArr, i, dArr3, dArr2, i2);
    }

    public void asinh(double[] dArr, int i, double[] dArr2, int i2) {
        double d;
        double d2;
        int i3;
        DSCompiler dSCompiler = this;
        double[] dArr3 = new double[(dSCompiler.order + 1)];
        double d3 = dArr[i];
        dArr3[0] = FastMath.asinh(d3);
        int i4 = dSCompiler.order;
        if (i4 > 0) {
            double[] dArr4 = new double[i4];
            dArr4[0] = 1.0d;
            double d4 = d3 * d3;
            double d5 = 1.0d / (d4 + 1.0d);
            double sqrt = FastMath.sqrt(d5);
            dArr3[1] = dArr4[0] * sqrt;
            int i5 = 2;
            int i6 = 2;
            while (i6 <= dSCompiler.order) {
                int i7 = i6 - 1;
                double[] dArr5 = dArr3;
                dArr4[i7] = ((double) (1 - i6)) * dArr4[i6 - 2];
                double d6 = 0.0d;
                while (i7 >= 0) {
                    double d7 = (d6 * d4) + dArr4[i7];
                    if (i7 > i5) {
                        int i8 = i7 - 1;
                        d2 = d7;
                        d = d4;
                        dArr4[i7 - 2] = (((double) i8) * dArr4[i8]) + (((double) (i7 - (i6 * 2))) * dArr4[i7 - 3]);
                        i3 = 2;
                    } else {
                        d2 = d7;
                        d = d4;
                        i3 = i5;
                        if (i7 == i3) {
                            dArr4[0] = dArr4[1];
                            i7 -= 2;
                            i5 = i3;
                            d6 = d2;
                            d4 = d;
                        }
                    }
                    i7 -= 2;
                    i5 = i3;
                    d6 = d2;
                    d4 = d;
                }
                double d8 = d4;
                int i9 = i5;
                if ((i6 & 1) == 0) {
                    d6 *= d3;
                }
                sqrt *= d5;
                dArr5[i6] = d6 * sqrt;
                i6++;
                i5 = i9;
                dArr3 = dArr5;
                d4 = d8;
                dSCompiler = this;
            }
        }
        compose(dArr, i, dArr3, dArr2, i2);
    }

    public void atanh(double[] dArr, int i, double[] dArr2, int i2) {
        double d;
        int i3;
        int i4;
        DSCompiler dSCompiler = this;
        double[] dArr3 = new double[(dSCompiler.order + 1)];
        double d2 = dArr[i];
        dArr3[0] = FastMath.atanh(d2);
        int i5 = dSCompiler.order;
        if (i5 > 0) {
            double[] dArr4 = new double[i5];
            dArr4[0] = 1.0d;
            double d3 = d2 * d2;
            double d4 = 1.0d / (1.0d - d3);
            dArr3[1] = 1.0d * d4;
            int i6 = 2;
            int i7 = 2;
            double d5 = d4;
            while (i7 <= dSCompiler.order) {
                int i8 = i7 - 1;
                dArr4[i8] = ((double) i7) * dArr4[i7 - 2];
                double d6 = 0.0d;
                while (i8 >= 0) {
                    double d7 = (d6 * d3) + dArr4[i8];
                    if (i8 > i6) {
                        int i9 = i8 - 1;
                        d = d7;
                        i3 = i7;
                        dArr4[i8 - 2] = (((double) i9) * dArr4[i9]) + (((double) (((i7 * 2) - i8) + 1)) * dArr4[i8 - 3]);
                        i4 = 2;
                    } else {
                        d = d7;
                        i4 = i6;
                        i3 = i7;
                        if (i8 == i4) {
                            dArr4[0] = dArr4[1];
                            i8 -= 2;
                            i6 = i4;
                            i7 = i3;
                            d6 = d;
                        }
                    }
                    i8 -= 2;
                    i6 = i4;
                    i7 = i3;
                    d6 = d;
                }
                int i10 = i6;
                int i11 = i7;
                if ((i11 & 1) == 0) {
                    d6 *= d2;
                }
                d5 *= d4;
                dArr3[i11] = d6 * d5;
                i6 = i10;
                dSCompiler = this;
                i7 = i11 + 1;
            }
        }
        compose(dArr, i, dArr3, dArr2, i2);
    }

    public void compose(double[] dArr, int i, double[] dArr2, double[] dArr3, int i2) {
        int i3 = 0;
        while (true) {
            int[][][] iArr = this.compIndirection;
            if (i3 < iArr.length) {
                int[][] iArr2 = iArr[i3];
                double d = 0.0d;
                for (int[] iArr3 : iArr2) {
                    double d2 = ((double) iArr3[0]) * dArr2[iArr3[1]];
                    for (int i4 = 2; i4 < iArr3.length; i4++) {
                        d2 *= dArr[iArr3[i4] + i];
                    }
                    d += d2;
                }
                dArr3[i2 + i3] = d;
                i3++;
            } else {
                return;
            }
        }
    }

    public double taylor(double[] dArr, int i, double... dArr2) throws MathArithmeticException {
        double d = 0.0d;
        for (int size = getSize() - 1; size >= 0; size--) {
            int[] partialDerivativeOrders = getPartialDerivativeOrders(size);
            double d2 = dArr[i + size];
            for (int i2 = 0; i2 < partialDerivativeOrders.length; i2++) {
                int i3 = partialDerivativeOrders[i2];
                if (i3 > 0) {
                    try {
                        d2 *= FastMath.pow(dArr2[i2], i3) / ((double) CombinatoricsUtils.factorial(partialDerivativeOrders[i2]));
                    } catch (NotPositiveException e) {
                        throw new MathInternalError(e);
                    }
                }
            }
            d += d2;
        }
        return d;
    }

    public void checkCompatibility(DSCompiler dSCompiler) throws DimensionMismatchException {
        if (this.parameters != dSCompiler.parameters) {
            throw new DimensionMismatchException(this.parameters, dSCompiler.parameters);
        } else if (this.order != dSCompiler.order) {
            throw new DimensionMismatchException(this.order, dSCompiler.order);
        }
    }
}
