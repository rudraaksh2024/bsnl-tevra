package org.apache.commons.math3.linear;

import java.lang.reflect.Array;
import java.util.Arrays;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.util.FastMath;

public class QRDecomposition {
    private RealMatrix cachedH;
    private RealMatrix cachedQ;
    private RealMatrix cachedQT;
    private RealMatrix cachedR;
    private double[][] qrt;
    private double[] rDiag;
    private final double threshold;

    public QRDecomposition(RealMatrix realMatrix) {
        this(realMatrix, 0.0d);
    }

    public QRDecomposition(RealMatrix realMatrix, double d) {
        this.threshold = d;
        int rowDimension = realMatrix.getRowDimension();
        int columnDimension = realMatrix.getColumnDimension();
        this.qrt = realMatrix.transpose().getData();
        this.rDiag = new double[FastMath.min(rowDimension, columnDimension)];
        this.cachedQ = null;
        this.cachedQT = null;
        this.cachedR = null;
        this.cachedH = null;
        decompose(this.qrt);
    }

    /* access modifiers changed from: protected */
    public void decompose(double[][] dArr) {
        for (int i = 0; i < FastMath.min(dArr.length, dArr[0].length); i++) {
            performHouseholderReflection(i, dArr);
        }
    }

    /* access modifiers changed from: protected */
    public void performHouseholderReflection(int i, double[][] dArr) {
        double[] dArr2 = dArr[i];
        double d = 0.0d;
        for (int i2 = i; i2 < dArr2.length; i2++) {
            double d2 = dArr2[i2];
            d += d2 * d2;
        }
        double sqrt = dArr2[i] > 0.0d ? -FastMath.sqrt(d) : FastMath.sqrt(d);
        this.rDiag[i] = sqrt;
        if (sqrt != 0.0d) {
            dArr2[i] = dArr2[i] - sqrt;
            for (int i3 = i + 1; i3 < dArr.length; i3++) {
                double[] dArr3 = dArr[i3];
                double d3 = 0.0d;
                for (int i4 = i; i4 < dArr3.length; i4++) {
                    d3 -= dArr3[i4] * dArr2[i4];
                }
                double d4 = d3 / (dArr2[i] * sqrt);
                for (int i5 = i; i5 < dArr3.length; i5++) {
                    dArr3[i5] = dArr3[i5] - (dArr2[i5] * d4);
                }
            }
        }
    }

    public RealMatrix getR() {
        if (this.cachedR == null) {
            double[][] dArr = this.qrt;
            int length = dArr.length;
            int length2 = dArr[0].length;
            int[] iArr = new int[2];
            iArr[1] = length;
            iArr[0] = length2;
            double[][] dArr2 = (double[][]) Array.newInstance(Double.TYPE, iArr);
            for (int min = FastMath.min(length2, length) - 1; min >= 0; min--) {
                dArr2[min][min] = this.rDiag[min];
                for (int i = min + 1; i < length; i++) {
                    dArr2[min][i] = this.qrt[i][min];
                }
            }
            this.cachedR = MatrixUtils.createRealMatrix(dArr2);
        }
        return this.cachedR;
    }

    public RealMatrix getQ() {
        if (this.cachedQ == null) {
            this.cachedQ = getQT().transpose();
        }
        return this.cachedQ;
    }

    public RealMatrix getQT() {
        double d;
        if (this.cachedQT == null) {
            double[][] dArr = this.qrt;
            int length = dArr.length;
            int length2 = dArr[0].length;
            int[] iArr = new int[2];
            iArr[1] = length2;
            iArr[0] = length2;
            double[][] dArr2 = (double[][]) Array.newInstance(Double.TYPE, iArr);
            int i = length2 - 1;
            while (true) {
                d = 1.0d;
                if (i < FastMath.min(length2, length)) {
                    break;
                }
                dArr2[i][i] = 1.0d;
                i--;
            }
            int min = FastMath.min(length2, length) - 1;
            while (min >= 0) {
                double[] dArr3 = this.qrt[min];
                dArr2[min][min] = d;
                if (dArr3[min] != 0.0d) {
                    for (int i2 = min; i2 < length2; i2++) {
                        double d2 = 0.0d;
                        for (int i3 = min; i3 < length2; i3++) {
                            d2 -= dArr2[i2][i3] * dArr3[i3];
                        }
                        double d3 = d2 / (this.rDiag[min] * dArr3[min]);
                        for (int i4 = min; i4 < length2; i4++) {
                            double[] dArr4 = dArr2[i2];
                            dArr4[i4] = dArr4[i4] + ((-d3) * dArr3[i4]);
                        }
                    }
                }
                min--;
                d = 1.0d;
            }
            this.cachedQT = MatrixUtils.createRealMatrix(dArr2);
        }
        return this.cachedQT;
    }

    public RealMatrix getH() {
        int i;
        if (this.cachedH == null) {
            double[][] dArr = this.qrt;
            int length = dArr.length;
            int length2 = dArr[0].length;
            int[] iArr = new int[2];
            iArr[1] = length;
            iArr[0] = length2;
            double[][] dArr2 = (double[][]) Array.newInstance(Double.TYPE, iArr);
            int i2 = 0;
            while (i2 < length2) {
                int i3 = 0;
                while (true) {
                    i = i2 + 1;
                    if (i3 >= FastMath.min(i, length)) {
                        break;
                    }
                    dArr2[i2][i3] = this.qrt[i3][i2] / (-this.rDiag[i3]);
                    i3++;
                }
                i2 = i;
            }
            this.cachedH = MatrixUtils.createRealMatrix(dArr2);
        }
        return this.cachedH;
    }

    public DecompositionSolver getSolver() {
        return new Solver(this.qrt, this.rDiag, this.threshold);
    }

    private static class Solver implements DecompositionSolver {
        private final double[][] qrt;
        private final double[] rDiag;
        private final double threshold;

        private Solver(double[][] dArr, double[] dArr2, double d) {
            this.qrt = dArr;
            this.rDiag = dArr2;
            this.threshold = d;
        }

        public boolean isNonSingular() {
            for (double abs : this.rDiag) {
                if (FastMath.abs(abs) <= this.threshold) {
                    return false;
                }
            }
            return true;
        }

        public RealVector solve(RealVector realVector) {
            double[][] dArr = this.qrt;
            int length = dArr.length;
            int length2 = dArr[0].length;
            if (realVector.getDimension() != length2) {
                throw new DimensionMismatchException(realVector.getDimension(), length2);
            } else if (isNonSingular()) {
                double[] dArr2 = new double[length];
                double[] array = realVector.toArray();
                for (int i = 0; i < FastMath.min(length2, length); i++) {
                    double[] dArr3 = this.qrt[i];
                    double d = 0.0d;
                    for (int i2 = i; i2 < length2; i2++) {
                        d += array[i2] * dArr3[i2];
                    }
                    double d2 = d / (this.rDiag[i] * dArr3[i]);
                    for (int i3 = i; i3 < length2; i3++) {
                        array[i3] = array[i3] + (dArr3[i3] * d2);
                    }
                }
                for (int length3 = this.rDiag.length - 1; length3 >= 0; length3--) {
                    double d3 = array[length3] / this.rDiag[length3];
                    array[length3] = d3;
                    double[] dArr4 = this.qrt[length3];
                    dArr2[length3] = d3;
                    for (int i4 = 0; i4 < length3; i4++) {
                        array[i4] = array[i4] - (dArr4[i4] * d3);
                    }
                }
                return new ArrayRealVector(dArr2, false);
            } else {
                throw new SingularMatrixException();
            }
        }

        public RealMatrix solve(RealMatrix realMatrix) {
            double d;
            double[][] dArr = this.qrt;
            int length = dArr.length;
            int i = 0;
            int length2 = dArr[0].length;
            if (realMatrix.getRowDimension() != length2) {
                throw new DimensionMismatchException(realMatrix.getRowDimension(), length2);
            } else if (isNonSingular()) {
                int columnDimension = realMatrix.getColumnDimension();
                int i2 = ((columnDimension + 52) - 1) / 52;
                double[][] createBlocksLayout = BlockRealMatrix.createBlocksLayout(length, columnDimension);
                int rowDimension = realMatrix.getRowDimension();
                int[] iArr = new int[2];
                iArr[1] = 52;
                iArr[0] = rowDimension;
                double[][] dArr2 = (double[][]) Array.newInstance(Double.TYPE, iArr);
                double[] dArr3 = new double[52];
                int i3 = 0;
                while (i3 < i2) {
                    int i4 = i3 * 52;
                    int min = FastMath.min(i4 + 52, columnDimension);
                    int i5 = min - i4;
                    int i6 = min - 1;
                    int i7 = i3;
                    realMatrix.copySubMatrix(0, length2 - 1, i4, i6, dArr2);
                    int i8 = i;
                    while (true) {
                        d = 1.0d;
                        if (i8 >= FastMath.min(length2, length)) {
                            break;
                        }
                        double[] dArr4 = this.qrt[i8];
                        double d2 = 1.0d / (this.rDiag[i8] * dArr4[i8]);
                        Arrays.fill(dArr3, i, i5, 0.0d);
                        int i9 = i8;
                        while (i9 < length2) {
                            double d3 = dArr4[i9];
                            double[] dArr5 = dArr2[i9];
                            while (i < i5) {
                                dArr3[i] = dArr3[i] + (dArr5[i] * d3);
                                i++;
                            }
                            i9++;
                            i = 0;
                        }
                        for (int i10 = 0; i10 < i5; i10++) {
                            dArr3[i10] = dArr3[i10] * d2;
                        }
                        for (int i11 = i8; i11 < length2; i11++) {
                            double d4 = dArr4[i11];
                            double[] dArr6 = dArr2[i11];
                            for (int i12 = 0; i12 < i5; i12++) {
                                dArr6[i12] = dArr6[i12] + (dArr3[i12] * d4);
                            }
                        }
                        i8++;
                        i = 0;
                    }
                    int length3 = this.rDiag.length - 1;
                    while (length3 >= 0) {
                        int i13 = length3 / 52;
                        int i14 = i13 * 52;
                        double d5 = d / this.rDiag[length3];
                        double[] dArr7 = dArr2[length3];
                        double[] dArr8 = createBlocksLayout[(i13 * i2) + i7];
                        int i15 = (length3 - i14) * i5;
                        int i16 = 0;
                        while (i16 < i5) {
                            double d6 = dArr7[i16] * d5;
                            dArr7[i16] = d6;
                            dArr8[i15] = d6;
                            i16++;
                            i15++;
                        }
                        double[] dArr9 = this.qrt[length3];
                        for (int i17 = 0; i17 < length3; i17++) {
                            double d7 = dArr9[i17];
                            double[] dArr10 = dArr2[i17];
                            for (int i18 = 0; i18 < i5; i18++) {
                                dArr10[i18] = dArr10[i18] - (dArr7[i18] * d7);
                            }
                        }
                        length3--;
                        d = 1.0d;
                    }
                    i3 = i7 + 1;
                    i = 0;
                }
                return new BlockRealMatrix(length, columnDimension, createBlocksLayout, false);
            } else {
                throw new SingularMatrixException();
            }
        }

        public RealMatrix getInverse() {
            return solve(MatrixUtils.createRealIdentityMatrix(this.qrt[0].length));
        }
    }
}
