package org.apache.commons.math3.linear;

import java.lang.reflect.Array;
import org.apache.commons.math3.complex.Complex;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.MathArithmeticException;
import org.apache.commons.math3.exception.MathUnsupportedOperationException;
import org.apache.commons.math3.exception.MaxCountExceededException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.Precision;
import org.apache.poi.ss.formula.ptg.IntPtg;

public class EigenDecomposition {
    private static final double EPSILON = 1.0E-12d;
    private RealMatrix cachedD;
    private RealMatrix cachedV;
    private RealMatrix cachedVt;
    private ArrayRealVector[] eigenvectors;
    private double[] imagEigenvalues;
    private final boolean isSymmetric;
    private double[] main;
    private byte maxIter;
    private double[] realEigenvalues;
    private double[] secondary;
    private TriDiagonalTransformer transformer;

    public EigenDecomposition(RealMatrix realMatrix) throws MathArithmeticException {
        this.maxIter = IntPtg.sid;
        boolean isSymmetric2 = MatrixUtils.isSymmetric(realMatrix, ((double) (realMatrix.getRowDimension() * 10 * realMatrix.getColumnDimension())) * Precision.EPSILON);
        this.isSymmetric = isSymmetric2;
        if (isSymmetric2) {
            transformToTridiagonal(realMatrix);
            findEigenVectors(this.transformer.getQ().getData());
            return;
        }
        findEigenVectorsFromSchur(transformToSchur(realMatrix));
    }

    @Deprecated
    public EigenDecomposition(RealMatrix realMatrix, double d) throws MathArithmeticException {
        this(realMatrix);
    }

    public EigenDecomposition(double[] dArr, double[] dArr2) {
        this.maxIter = IntPtg.sid;
        this.isSymmetric = true;
        this.main = (double[]) dArr.clone();
        this.secondary = (double[]) dArr2.clone();
        this.transformer = null;
        int length = dArr.length;
        int[] iArr = new int[2];
        iArr[1] = length;
        iArr[0] = length;
        double[][] dArr3 = (double[][]) Array.newInstance(Double.TYPE, iArr);
        for (int i = 0; i < length; i++) {
            dArr3[i][i] = 1.0d;
        }
        findEigenVectors(dArr3);
    }

    @Deprecated
    public EigenDecomposition(double[] dArr, double[] dArr2, double d) {
        this(dArr, dArr2);
    }

    public RealMatrix getV() {
        if (this.cachedV == null) {
            int length = this.eigenvectors.length;
            this.cachedV = MatrixUtils.createRealMatrix(length, length);
            for (int i = 0; i < length; i++) {
                this.cachedV.setColumnVector(i, this.eigenvectors[i]);
            }
        }
        return this.cachedV;
    }

    public RealMatrix getD() {
        if (this.cachedD == null) {
            this.cachedD = MatrixUtils.createRealDiagonalMatrix(this.realEigenvalues);
            int i = 0;
            while (true) {
                double[] dArr = this.imagEigenvalues;
                if (i >= dArr.length) {
                    break;
                }
                if (Precision.compareTo(dArr[i], 0.0d, 1.0E-12d) > 0) {
                    this.cachedD.setEntry(i, i + 1, this.imagEigenvalues[i]);
                } else if (Precision.compareTo(this.imagEigenvalues[i], 0.0d, 1.0E-12d) < 0) {
                    this.cachedD.setEntry(i, i - 1, this.imagEigenvalues[i]);
                }
                i++;
            }
        }
        return this.cachedD;
    }

    public RealMatrix getVT() {
        if (this.cachedVt == null) {
            int length = this.eigenvectors.length;
            this.cachedVt = MatrixUtils.createRealMatrix(length, length);
            for (int i = 0; i < length; i++) {
                this.cachedVt.setRowVector(i, this.eigenvectors[i]);
            }
        }
        return this.cachedVt;
    }

    public boolean hasComplexEigenvalues() {
        int i = 0;
        while (true) {
            double[] dArr = this.imagEigenvalues;
            if (i >= dArr.length) {
                return false;
            }
            if (!Precision.equals(dArr[i], 0.0d, 1.0E-12d)) {
                return true;
            }
            i++;
        }
    }

    public double[] getRealEigenvalues() {
        return (double[]) this.realEigenvalues.clone();
    }

    public double getRealEigenvalue(int i) {
        return this.realEigenvalues[i];
    }

    public double[] getImagEigenvalues() {
        return (double[]) this.imagEigenvalues.clone();
    }

    public double getImagEigenvalue(int i) {
        return this.imagEigenvalues[i];
    }

    public RealVector getEigenvector(int i) {
        return this.eigenvectors[i].copy();
    }

    public double getDeterminant() {
        double d = 1.0d;
        for (double d2 : this.realEigenvalues) {
            d *= d2;
        }
        return d;
    }

    public RealMatrix getSquareRoot() {
        if (this.isSymmetric) {
            double[] dArr = new double[this.realEigenvalues.length];
            int i = 0;
            while (true) {
                double[] dArr2 = this.realEigenvalues;
                if (i < dArr2.length) {
                    double d = dArr2[i];
                    if (d > 0.0d) {
                        dArr[i] = FastMath.sqrt(d);
                        i++;
                    } else {
                        throw new MathUnsupportedOperationException();
                    }
                } else {
                    RealMatrix createRealDiagonalMatrix = MatrixUtils.createRealDiagonalMatrix(dArr);
                    RealMatrix v = getV();
                    return v.multiply(createRealDiagonalMatrix).multiply(getVT());
                }
            }
        } else {
            throw new MathUnsupportedOperationException();
        }
    }

    public DecompositionSolver getSolver() {
        if (!hasComplexEigenvalues()) {
            return new Solver(this.realEigenvalues, this.imagEigenvalues, this.eigenvectors);
        }
        throw new MathUnsupportedOperationException();
    }

    private static class Solver implements DecompositionSolver {
        private final ArrayRealVector[] eigenvectors;
        private double[] imagEigenvalues;
        private double[] realEigenvalues;

        private Solver(double[] dArr, double[] dArr2, ArrayRealVector[] arrayRealVectorArr) {
            this.realEigenvalues = dArr;
            this.imagEigenvalues = dArr2;
            this.eigenvectors = arrayRealVectorArr;
        }

        public RealVector solve(RealVector realVector) {
            if (isNonSingular()) {
                int length = this.realEigenvalues.length;
                if (realVector.getDimension() == length) {
                    double[] dArr = new double[length];
                    for (int i = 0; i < length; i++) {
                        ArrayRealVector arrayRealVector = this.eigenvectors[i];
                        double[] dataRef = arrayRealVector.getDataRef();
                        double dotProduct = arrayRealVector.dotProduct(realVector) / this.realEigenvalues[i];
                        for (int i2 = 0; i2 < length; i2++) {
                            dArr[i2] = dArr[i2] + (dataRef[i2] * dotProduct);
                        }
                    }
                    return new ArrayRealVector(dArr, false);
                }
                throw new DimensionMismatchException(realVector.getDimension(), length);
            }
            throw new SingularMatrixException();
        }

        public RealMatrix solve(RealMatrix realMatrix) {
            if (isNonSingular()) {
                int length = this.realEigenvalues.length;
                if (realMatrix.getRowDimension() == length) {
                    int columnDimension = realMatrix.getColumnDimension();
                    int[] iArr = new int[2];
                    iArr[1] = columnDimension;
                    iArr[0] = length;
                    double[][] dArr = (double[][]) Array.newInstance(Double.TYPE, iArr);
                    double[] dArr2 = new double[length];
                    for (int i = 0; i < columnDimension; i++) {
                        for (int i2 = 0; i2 < length; i2++) {
                            dArr2[i2] = realMatrix.getEntry(i2, i);
                            dArr[i2][i] = 0.0d;
                        }
                        RealMatrix realMatrix2 = realMatrix;
                        for (int i3 = 0; i3 < length; i3++) {
                            ArrayRealVector arrayRealVector = this.eigenvectors[i3];
                            double[] dataRef = arrayRealVector.getDataRef();
                            double d = 0.0d;
                            for (int i4 = 0; i4 < length; i4++) {
                                d += arrayRealVector.getEntry(i4) * dArr2[i4];
                            }
                            double d2 = d / this.realEigenvalues[i3];
                            for (int i5 = 0; i5 < length; i5++) {
                                double[] dArr3 = dArr[i5];
                                dArr3[i] = dArr3[i] + (dataRef[i5] * d2);
                            }
                        }
                    }
                    return new Array2DRowRealMatrix(dArr, false);
                }
                RealMatrix realMatrix3 = realMatrix;
                throw new DimensionMismatchException(realMatrix.getRowDimension(), length);
            }
            throw new SingularMatrixException();
        }

        public boolean isNonSingular() {
            double d = 0.0d;
            for (int i = 0; i < this.realEigenvalues.length; i++) {
                d = FastMath.max(d, eigenvalueNorm(i));
            }
            if (d == 0.0d) {
                return false;
            }
            for (int i2 = 0; i2 < this.realEigenvalues.length; i2++) {
                if (Precision.equals(eigenvalueNorm(i2) / d, 0.0d, 1.0E-12d)) {
                    return false;
                }
            }
            return true;
        }

        private double eigenvalueNorm(int i) {
            double d = this.realEigenvalues[i];
            double d2 = this.imagEigenvalues[i];
            return FastMath.sqrt((d * d) + (d2 * d2));
        }

        public RealMatrix getInverse() {
            if (isNonSingular()) {
                int length = this.realEigenvalues.length;
                int[] iArr = new int[2];
                iArr[1] = length;
                iArr[0] = length;
                double[][] dArr = (double[][]) Array.newInstance(Double.TYPE, iArr);
                for (int i = 0; i < length; i++) {
                    double[] dArr2 = dArr[i];
                    for (int i2 = 0; i2 < length; i2++) {
                        double d = 0.0d;
                        for (int i3 = 0; i3 < length; i3++) {
                            double[] dataRef = this.eigenvectors[i3].getDataRef();
                            d += (dataRef[i] * dataRef[i2]) / this.realEigenvalues[i3];
                        }
                        dArr2[i2] = d;
                    }
                }
                return MatrixUtils.createRealMatrix(dArr);
            }
            throw new SingularMatrixException();
        }
    }

    private void transformToTridiagonal(RealMatrix realMatrix) {
        TriDiagonalTransformer triDiagonalTransformer = new TriDiagonalTransformer(realMatrix);
        this.transformer = triDiagonalTransformer;
        this.main = triDiagonalTransformer.getMainDiagonalRef();
        this.secondary = this.transformer.getSecondaryDiagonalRef();
    }

    private void findEigenVectors(double[][] dArr) {
        int i;
        int i2;
        double d;
        double d2;
        double d3;
        double d4;
        double d5;
        double[][] dArr2 = (double[][]) dArr.clone();
        int length = this.main.length;
        this.realEigenvalues = new double[length];
        this.imagEigenvalues = new double[length];
        double[] dArr3 = new double[length];
        int i3 = 0;
        while (true) {
            i = length - 1;
            if (i3 >= i) {
                break;
            }
            this.realEigenvalues[i3] = this.main[i3];
            dArr3[i3] = this.secondary[i3];
            i3++;
        }
        this.realEigenvalues[i] = this.main[i];
        dArr3[i] = 0.0d;
        double d6 = 0.0d;
        for (int i4 = 0; i4 < length; i4++) {
            if (FastMath.abs(this.realEigenvalues[i4]) > d6) {
                d6 = FastMath.abs(this.realEigenvalues[i4]);
            }
            if (FastMath.abs(dArr3[i4]) > d6) {
                d6 = FastMath.abs(dArr3[i4]);
            }
        }
        if (d6 != 0.0d) {
            for (int i5 = 0; i5 < length; i5++) {
                if (FastMath.abs(this.realEigenvalues[i5]) <= Precision.EPSILON * d6) {
                    this.realEigenvalues[i5] = 0.0d;
                }
                if (FastMath.abs(dArr3[i5]) <= Precision.EPSILON * d6) {
                    dArr3[i5] = 0.0d;
                }
            }
        }
        for (int i6 = 0; i6 < length; i6++) {
            int i7 = 0;
            do {
                i2 = i6;
                while (i2 < i) {
                    int i8 = i2 + 1;
                    double abs = FastMath.abs(this.realEigenvalues[i2]) + FastMath.abs(this.realEigenvalues[i8]);
                    if (FastMath.abs(dArr3[i2]) + abs == abs) {
                        break;
                    }
                    i2 = i8;
                }
                if (i2 != i6) {
                    if (i7 != this.maxIter) {
                        i7++;
                        double[] dArr4 = this.realEigenvalues;
                        double d7 = (dArr4[i6 + 1] - dArr4[i6]) / (dArr3[i6] * 2.0d);
                        double sqrt = FastMath.sqrt((d7 * d7) + 1.0d);
                        if (d7 < 0.0d) {
                            double[] dArr5 = this.realEigenvalues;
                            d2 = dArr5[i2] - dArr5[i6];
                            d = dArr3[i6];
                            d3 = d7 - sqrt;
                        } else {
                            double[] dArr6 = this.realEigenvalues;
                            d2 = dArr6[i2] - dArr6[i6];
                            d = dArr3[i6];
                            d3 = d7 + sqrt;
                        }
                        double d8 = d2 + (d / d3);
                        int i9 = i2 - 1;
                        double d9 = 0.0d;
                        double d10 = 1.0d;
                        double d11 = 1.0d;
                        while (true) {
                            if (i9 < i6) {
                                break;
                            }
                            double d12 = dArr3[i9];
                            double d13 = d10 * d12;
                            double d14 = d11 * d12;
                            if (FastMath.abs(d13) >= FastMath.abs(d8)) {
                                double d15 = d8 / d13;
                                double sqrt2 = FastMath.sqrt((d15 * d15) + 1.0d);
                                dArr3[i9 + 1] = d13 * sqrt2;
                                d10 = 1.0d / sqrt2;
                                double d16 = d15 * d10;
                                d4 = sqrt2;
                                d5 = d16;
                            } else {
                                double d17 = d13 / d8;
                                d4 = FastMath.sqrt((d17 * d17) + 1.0d);
                                dArr3[i9 + 1] = d8 * d4;
                                d5 = 1.0d / d4;
                                d10 = d17 * d5;
                            }
                            int i10 = i9 + 1;
                            if (dArr3[i10] == 0.0d) {
                                double[] dArr7 = this.realEigenvalues;
                                dArr7[i10] = dArr7[i10] - d9;
                                dArr3[i2] = 0.0d;
                                break;
                            }
                            double[] dArr8 = this.realEigenvalues;
                            double d18 = dArr8[i10] - d9;
                            double d19 = ((dArr8[i9] - d18) * d10) + (d5 * 2.0d * d14);
                            double d20 = d10 * d19;
                            dArr8[i10] = d18 + d20;
                            d8 = (d5 * d19) - d14;
                            for (int i11 = 0; i11 < length; i11++) {
                                double[] dArr9 = dArr2[i11];
                                double d21 = dArr9[i10];
                                dArr9[i10] = (dArr9[i9] * d10) + (d5 * d21);
                                dArr9[i9] = (dArr9[i9] * d5) - (d21 * d10);
                            }
                            i9--;
                            sqrt = d19;
                            d11 = d5;
                            d9 = d20;
                        }
                        if (d4 != 0.0d || i9 < i6) {
                            double[] dArr10 = this.realEigenvalues;
                            dArr10[i6] = dArr10[i6] - d9;
                            dArr3[i6] = d8;
                            dArr3[i2] = 0.0d;
                            continue;
                        }
                    } else {
                        throw new MaxCountExceededException(LocalizedFormats.CONVERGENCE_FAILED, Byte.valueOf(this.maxIter), new Object[0]);
                    }
                }
            } while (i2 != i6);
        }
        int i12 = 0;
        while (i12 < length) {
            double d22 = this.realEigenvalues[i12];
            int i13 = i12 + 1;
            int i14 = i12;
            for (int i15 = i13; i15 < length; i15++) {
                double d23 = this.realEigenvalues[i15];
                if (d23 > d22) {
                    i14 = i15;
                    d22 = d23;
                }
            }
            if (i14 != i12) {
                double[] dArr11 = this.realEigenvalues;
                dArr11[i14] = dArr11[i12];
                dArr11[i12] = d22;
                for (int i16 = 0; i16 < length; i16++) {
                    double[] dArr12 = dArr2[i16];
                    double d24 = dArr12[i12];
                    dArr12[i12] = dArr12[i14];
                    dArr12[i14] = d24;
                }
            }
            i12 = i13;
        }
        double d25 = 0.0d;
        for (int i17 = 0; i17 < length; i17++) {
            if (FastMath.abs(this.realEigenvalues[i17]) > d25) {
                d25 = FastMath.abs(this.realEigenvalues[i17]);
            }
        }
        if (d25 != 0.0d) {
            for (int i18 = 0; i18 < length; i18++) {
                if (FastMath.abs(this.realEigenvalues[i18]) < Precision.EPSILON * d25) {
                    this.realEigenvalues[i18] = 0.0d;
                }
            }
        }
        this.eigenvectors = new ArrayRealVector[length];
        double[] dArr13 = new double[length];
        for (int i19 = 0; i19 < length; i19++) {
            for (int i20 = 0; i20 < length; i20++) {
                dArr13[i20] = dArr2[i20][i19];
            }
            this.eigenvectors[i19] = new ArrayRealVector(dArr13);
        }
    }

    private SchurTransformer transformToSchur(RealMatrix realMatrix) {
        SchurTransformer schurTransformer = new SchurTransformer(realMatrix);
        double[][] data = schurTransformer.getT().getData();
        this.realEigenvalues = new double[data.length];
        this.imagEigenvalues = new double[data.length];
        int i = 0;
        while (true) {
            double[] dArr = this.realEigenvalues;
            if (i >= dArr.length) {
                return schurTransformer;
            }
            if (i != dArr.length - 1) {
                int i2 = i + 1;
                if (!Precision.equals(data[i2][i], 0.0d, 1.0E-12d)) {
                    double[] dArr2 = data[i2];
                    double d = dArr2[i2];
                    double[] dArr3 = data[i];
                    double d2 = (dArr3[i] - d) * 0.5d;
                    double sqrt = FastMath.sqrt(FastMath.abs((d2 * d2) + (dArr2[i] * dArr3[i2])));
                    double[] dArr4 = this.realEigenvalues;
                    double d3 = d + d2;
                    dArr4[i] = d3;
                    double[] dArr5 = this.imagEigenvalues;
                    dArr5[i] = sqrt;
                    dArr4[i2] = d3;
                    dArr5[i2] = -sqrt;
                    i = i2;
                    i++;
                }
            }
            this.realEigenvalues[i] = data[i][i];
            i++;
        }
    }

    private Complex cdiv(double d, double d2, double d3, double d4) {
        return new Complex(d, d2).divide(new Complex(d3, d4));
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v0, resolved type: double[][]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v0, resolved type: double[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v2, resolved type: double} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v14, resolved type: double[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v15, resolved type: double} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v6, resolved type: double[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v7, resolved type: double} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r32v0, resolved type: double} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v18, resolved type: java.lang.Object[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v3, resolved type: double} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v8, resolved type: double} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v9, resolved type: double} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v4, resolved type: double[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r18v5, resolved type: double} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v8, resolved type: double} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v24, resolved type: double[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v25, resolved type: double} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v9, resolved type: double[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v10, resolved type: double} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v11, resolved type: double[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v7, resolved type: double} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v9, resolved type: double} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v29, resolved type: double[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r22v6, resolved type: double} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v30, resolved type: double[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r34v2, resolved type: double} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v13, resolved type: double[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v14, resolved type: double[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v23, resolved type: double[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v13, resolved type: double} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v12, resolved type: double} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v15, resolved type: double} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v16, resolved type: double[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v17, resolved type: double[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v42, resolved type: double[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v17, resolved type: double[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v12, resolved type: double} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v14, resolved type: double} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v15, resolved type: double} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v18, resolved type: double} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v28, resolved type: double[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v29, resolved type: double[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r18v12, resolved type: double[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r18v13, resolved type: double} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r22v7, resolved type: double[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r34v4, resolved type: double} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r22v8, resolved type: double} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v46, resolved type: double[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v22, resolved type: double} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v17, resolved type: double} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v32, resolved type: double[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v33, resolved type: double[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v49, resolved type: double[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v34, resolved type: double[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v30, resolved type: double} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v32, resolved type: double} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v20, resolved type: double} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v50, resolved type: java.lang.Object[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v16, resolved type: double[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v17, resolved type: double} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v29, resolved type: double[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v30, resolved type: double} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v6, resolved type: double[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v7, resolved type: double} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v33, resolved type: double[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v9, resolved type: double} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v33, resolved type: double[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r18v16, resolved type: double} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v35, resolved type: double[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v36, resolved type: double[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v38, resolved type: double[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v39, resolved type: double[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r18v18, resolved type: double[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r18v19, resolved type: double} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r22v18, resolved type: double[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r22v19, resolved type: double} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v36, resolved type: double[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v37, resolved type: double} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void findEigenVectorsFromSchur(org.apache.commons.math3.linear.SchurTransformer r47) throws org.apache.commons.math3.exception.MathArithmeticException {
        /*
            r46 = this;
            r9 = r46
            org.apache.commons.math3.linear.RealMatrix r0 = r47.getT()
            double[][] r10 = r0.getData()
            org.apache.commons.math3.linear.RealMatrix r0 = r47.getP()
            double[][] r11 = r0.getData()
            int r12 = r10.length
            r13 = 0
            r15 = 0
            r16 = r13
            r0 = r15
        L_0x0019:
            if (r0 >= r12) goto L_0x0033
            int r1 = r0 + -1
            int r1 = org.apache.commons.math3.util.FastMath.max((int) r1, (int) r15)
        L_0x0021:
            if (r1 >= r12) goto L_0x0030
            r2 = r10[r0]
            r2 = r2[r1]
            double r2 = org.apache.commons.math3.util.FastMath.abs((double) r2)
            double r16 = r16 + r2
            int r1 = r1 + 1
            goto L_0x0021
        L_0x0030:
            int r0 = r0 + 1
            goto L_0x0019
        L_0x0033:
            r3 = 0
            r5 = 4427486594234968593(0x3d719799812dea11, double:1.0E-12)
            r1 = r16
            boolean r0 = org.apache.commons.math3.util.Precision.equals((double) r1, (double) r3, (double) r5)
            if (r0 != 0) goto L_0x03e7
            int r7 = r12 + -1
            r8 = r7
            r18 = r13
            r20 = r18
            r22 = r20
        L_0x004b:
            if (r8 < 0) goto L_0x0394
            double[] r0 = r9.realEigenvalues
            r24 = r0[r8]
            double[] r0 = r9.imagEigenvalues
            r5 = r0[r8]
            boolean r0 = org.apache.commons.math3.util.Precision.equals((double) r5, (double) r13)
            r26 = 4607182418800017408(0x3ff0000000000000, double:1.0)
            if (r0 == 0) goto L_0x0141
            r0 = r10[r8]
            r0[r8] = r26
            int r0 = r8 + -1
            r3 = r8
            r1 = r18
        L_0x0066:
            if (r0 < 0) goto L_0x0137
            r4 = r10[r0]
            r4 = r4[r0]
            double r4 = r4 - r24
            r6 = r3
        L_0x006f:
            if (r6 > r8) goto L_0x0080
            r18 = r10[r0]
            r18 = r18[r6]
            r22 = r10[r6]
            r22 = r22[r8]
            double r18 = r18 * r22
            double r13 = r13 + r18
            int r6 = r6 + 1
            goto L_0x006f
        L_0x0080:
            double[] r6 = r9.imagEigenvalues
            r30 = r6[r0]
            r32 = 0
            r34 = 4427486594234968593(0x3d719799812dea11, double:1.0E-12)
            int r6 = org.apache.commons.math3.util.Precision.compareTo((double) r30, (double) r32, (double) r34)
            if (r6 >= 0) goto L_0x009c
            r20 = r4
            r47 = r7
            r30 = r11
            r31 = r12
            r1 = r13
            goto L_0x0129
        L_0x009c:
            double[] r3 = r9.imagEigenvalues
            r47 = r7
            r6 = r3[r0]
            r30 = r11
            r31 = r12
            r11 = 0
            boolean r3 = org.apache.commons.math3.util.Precision.equals((double) r6, (double) r11)
            if (r3 == 0) goto L_0x00c4
            int r3 = (r4 > r11 ? 1 : (r4 == r11 ? 0 : -1))
            if (r3 == 0) goto L_0x00b9
            r3 = r10[r0]
            double r6 = -r13
            double r6 = r6 / r4
            r3[r8] = r6
            goto L_0x010b
        L_0x00b9:
            r3 = r10[r0]
            double r4 = -r13
            double r6 = org.apache.commons.math3.util.Precision.EPSILON
            double r6 = r6 * r16
            double r4 = r4 / r6
            r3[r8] = r4
            goto L_0x010b
        L_0x00c4:
            r3 = r10[r0]
            int r6 = r0 + 1
            r11 = r3[r6]
            r7 = r10[r6]
            r18 = r7[r0]
            double[] r7 = r9.realEigenvalues
            r22 = r7[r0]
            double r32 = r22 - r24
            double r22 = r22 - r24
            double r32 = r32 * r22
            double[] r7 = r9.imagEigenvalues
            r22 = r7[r0]
            double r22 = r22 * r22
            double r32 = r32 + r22
            double r22 = r11 * r1
            double r34 = r20 * r13
            double r22 = r22 - r34
            double r22 = r22 / r32
            r3[r8] = r22
            double r32 = org.apache.commons.math3.util.FastMath.abs((double) r11)
            double r34 = org.apache.commons.math3.util.FastMath.abs((double) r20)
            int r3 = (r32 > r34 ? 1 : (r32 == r34 ? 0 : -1))
            if (r3 <= 0) goto L_0x0100
            r3 = r10[r6]
            double r6 = -r13
            double r4 = r4 * r22
            double r6 = r6 - r4
            double r6 = r6 / r11
            r3[r8] = r6
            goto L_0x010b
        L_0x0100:
            r3 = r10[r6]
            double r4 = -r1
            double r18 = r18 * r22
            double r4 = r4 - r18
            double r4 = r4 / r20
            r3[r8] = r4
        L_0x010b:
            r3 = r10[r0]
            r3 = r3[r8]
            double r3 = org.apache.commons.math3.util.FastMath.abs((double) r3)
            double r5 = org.apache.commons.math3.util.Precision.EPSILON
            double r5 = r5 * r3
            double r5 = r5 * r3
            int r5 = (r5 > r26 ? 1 : (r5 == r26 ? 0 : -1))
            if (r5 <= 0) goto L_0x0128
            r5 = r0
        L_0x011c:
            if (r5 > r8) goto L_0x0128
            r6 = r10[r5]
            r11 = r6[r8]
            double r11 = r11 / r3
            r6[r8] = r11
            int r5 = r5 + 1
            goto L_0x011c
        L_0x0128:
            r3 = r0
        L_0x0129:
            int r0 = r0 + -1
            r7 = r47
            r22 = r13
            r11 = r30
            r12 = r31
            r13 = 0
            goto L_0x0066
        L_0x0137:
            r30 = r11
            r31 = r12
            r18 = r1
            r36 = r7
            goto L_0x0386
        L_0x0141:
            r47 = r7
            r30 = r11
            r31 = r12
            r0 = r13
            int r2 = (r5 > r0 ? 1 : (r5 == r0 ? 0 : -1))
            if (r2 >= 0) goto L_0x0384
            int r11 = r8 + -1
            r0 = r10[r8]
            r0 = r0[r11]
            double r0 = org.apache.commons.math3.util.FastMath.abs((double) r0)
            r2 = r10[r11]
            r2 = r2[r8]
            double r2 = org.apache.commons.math3.util.FastMath.abs((double) r2)
            int r0 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r0 <= 0) goto L_0x017c
            r0 = r10[r11]
            r1 = r10[r8]
            r2 = r1[r11]
            double r2 = r5 / r2
            r0[r11] = r2
            r2 = r1[r8]
            double r2 = r2 - r24
            double r2 = -r2
            r12 = r1[r11]
            double r2 = r2 / r12
            r0[r8] = r2
            r12 = r47
            r32 = r5
            r13 = r8
            goto L_0x01a5
        L_0x017c:
            r1 = 0
            r0 = r10[r11]
            r3 = r0[r8]
            double r3 = -r3
            r12 = r0[r11]
            double r12 = r12 - r24
            r0 = r46
            r32 = r5
            r5 = r12
            r12 = r47
            r13 = r8
            r7 = r32
            org.apache.commons.math3.complex.Complex r0 = r0.cdiv(r1, r3, r5, r7)
            r1 = r10[r11]
            double r2 = r0.getReal()
            r1[r11] = r2
            r1 = r10[r11]
            double r2 = r0.getImaginary()
            r1[r13] = r2
        L_0x01a5:
            r0 = r10[r13]
            r1 = 0
            r0[r11] = r1
            r0[r13] = r26
            int r8 = r13 + -2
            r14 = r8
            r0 = r11
            r7 = r18
            r5 = r22
        L_0x01b5:
            if (r14 < 0) goto L_0x037b
            r15 = r0
            r1 = 0
            r3 = 0
        L_0x01bc:
            if (r15 > r13) goto L_0x01d3
            r18 = r10[r14]
            r18 = r18[r15]
            r22 = r10[r15]
            r34 = r22[r11]
            double r34 = r34 * r18
            double r3 = r3 + r34
            r22 = r22[r13]
            double r18 = r18 * r22
            double r1 = r1 + r18
            int r15 = r15 + 1
            goto L_0x01bc
        L_0x01d3:
            r15 = r10[r14]
            r18 = r15[r14]
            double r18 = r18 - r24
            double[] r15 = r9.imagEigenvalues
            r34 = r15[r14]
            r36 = 0
            r38 = 4427486594234968593(0x3d719799812dea11, double:1.0E-12)
            int r15 = org.apache.commons.math3.util.Precision.compareTo((double) r34, (double) r36, (double) r38)
            if (r15 >= 0) goto L_0x01f2
            r5 = r3
            r36 = r12
            r7 = r13
            r20 = r18
            goto L_0x0372
        L_0x01f2:
            double[] r0 = r9.imagEigenvalues
            r22 = r5
            r5 = r0[r14]
            r34 = r7
            r7 = 0
            boolean r0 = org.apache.commons.math3.util.Precision.equals((double) r5, (double) r7)
            if (r0 == 0) goto L_0x0230
            double r3 = -r3
            double r5 = -r1
            r0 = r46
            r1 = r3
            r3 = r5
            r7 = r22
            r5 = r18
            r42 = r7
            r40 = r34
            r7 = r32
            org.apache.commons.math3.complex.Complex r0 = r0.cdiv(r1, r3, r5, r7)
            r1 = r10[r14]
            double r2 = r0.getReal()
            r1[r11] = r2
            r1 = r10[r14]
            double r2 = r0.getImaginary()
            r1[r13] = r2
            r36 = r12
            r37 = r13
            r18 = r40
            r12 = r42
            goto L_0x033c
        L_0x0230:
            r42 = r22
            r40 = r34
            r0 = r10[r14]
            int r15 = r14 + 1
            r22 = r0[r15]
            r0 = r10[r15]
            r34 = r0[r14]
            double[] r0 = r9.realEigenvalues
            r5 = r0[r14]
            double r7 = r5 - r24
            double r36 = r5 - r24
            double r7 = r7 * r36
            double[] r0 = r9.imagEigenvalues
            r36 = r0[r14]
            double r36 = r36 * r36
            double r7 = r7 + r36
            double r36 = r32 * r32
            double r7 = r7 - r36
            double r5 = r5 - r24
            r36 = 4611686018427387904(0x4000000000000000, double:2.0)
            double r5 = r5 * r36
            double r5 = r5 * r32
            r36 = r12
            r37 = r13
            r12 = 0
            boolean r0 = org.apache.commons.math3.util.Precision.equals((double) r7, (double) r12)
            if (r0 == 0) goto L_0x0290
            boolean r0 = org.apache.commons.math3.util.Precision.equals((double) r5, (double) r12)
            if (r0 == 0) goto L_0x0290
            double r7 = org.apache.commons.math3.util.Precision.EPSILON
            double r7 = r7 * r16
            double r28 = org.apache.commons.math3.util.FastMath.abs((double) r18)
            double r38 = org.apache.commons.math3.util.FastMath.abs((double) r32)
            double r28 = r28 + r38
            double r38 = org.apache.commons.math3.util.FastMath.abs((double) r22)
            double r28 = r28 + r38
            double r38 = org.apache.commons.math3.util.FastMath.abs((double) r34)
            double r28 = r28 + r38
            double r38 = org.apache.commons.math3.util.FastMath.abs((double) r20)
            double r28 = r28 + r38
            double r7 = r7 * r28
        L_0x0290:
            r12 = r42
            double r38 = r22 * r12
            double r42 = r20 * r3
            double r38 = r38 - r42
            double r42 = r32 * r1
            double r38 = r38 + r42
            r42 = r12
            r12 = r40
            double r40 = r22 * r12
            double r44 = r20 * r1
            double r40 = r40 - r44
            double r44 = r32 * r3
            double r40 = r40 - r44
            r0 = r46
            r44 = r12
            r12 = r1
            r1 = r38
            r38 = r12
            r12 = r3
            r3 = r40
            r40 = r5
            r5 = r7
            r7 = r40
            org.apache.commons.math3.complex.Complex r0 = r0.cdiv(r1, r3, r5, r7)
            r1 = r10[r14]
            double r2 = r0.getReal()
            r1[r11] = r2
            r1 = r10[r14]
            double r2 = r0.getImaginary()
            r1[r37] = r2
            double r0 = org.apache.commons.math3.util.FastMath.abs((double) r22)
            double r2 = org.apache.commons.math3.util.FastMath.abs((double) r20)
            double r4 = org.apache.commons.math3.util.FastMath.abs((double) r32)
            double r2 = r2 + r4
            int r0 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r0 <= 0) goto L_0x030a
            r0 = r10[r15]
            double r1 = -r12
            r3 = r10[r14]
            r4 = r3[r11]
            double r4 = r4 * r18
            double r1 = r1 - r4
            r4 = r3[r37]
            double r5 = r32 * r4
            double r1 = r1 + r5
            double r1 = r1 / r22
            r0[r11] = r1
            r1 = r38
            double r1 = -r1
            r4 = r3[r37]
            double r18 = r18 * r4
            double r1 = r1 - r18
            r3 = r3[r11]
            double r5 = r32 * r3
            double r1 = r1 - r5
            double r1 = r1 / r22
            r0[r37] = r1
            r12 = r42
            r18 = r44
            goto L_0x033c
        L_0x030a:
            r12 = r42
            double r0 = -r12
            r2 = r10[r14]
            r3 = r2[r11]
            double r3 = r3 * r34
            double r3 = r0 - r3
            r7 = r44
            double r0 = -r7
            r5 = r2[r37]
            double r34 = r34 * r5
            double r5 = r0 - r34
            r0 = r46
            r1 = r3
            r3 = r5
            r5 = r20
            r18 = r7
            r7 = r32
            org.apache.commons.math3.complex.Complex r0 = r0.cdiv(r1, r3, r5, r7)
            r1 = r10[r15]
            double r2 = r0.getReal()
            r1[r11] = r2
            r1 = r10[r15]
            double r2 = r0.getImaginary()
            r1[r37] = r2
        L_0x033c:
            r0 = r10[r14]
            r0 = r0[r11]
            double r0 = org.apache.commons.math3.util.FastMath.abs((double) r0)
            r2 = r10[r14]
            r2 = r2[r37]
            double r2 = org.apache.commons.math3.util.FastMath.abs((double) r2)
            double r0 = org.apache.commons.math3.util.FastMath.max((double) r0, (double) r2)
            double r2 = org.apache.commons.math3.util.Precision.EPSILON
            double r2 = r2 * r0
            double r2 = r2 * r0
            int r2 = (r2 > r26 ? 1 : (r2 == r26 ? 0 : -1))
            if (r2 <= 0) goto L_0x036c
            r2 = r14
            r7 = r37
        L_0x035b:
            if (r2 > r7) goto L_0x036e
            r3 = r10[r2]
            r4 = r3[r11]
            double r4 = r4 / r0
            r3[r11] = r4
            r4 = r3[r7]
            double r4 = r4 / r0
            r3[r7] = r4
            int r2 = r2 + 1
            goto L_0x035b
        L_0x036c:
            r7 = r37
        L_0x036e:
            r5 = r12
            r0 = r14
            r1 = r18
        L_0x0372:
            int r14 = r14 + -1
            r13 = r7
            r12 = r36
            r15 = 0
            r7 = r1
            goto L_0x01b5
        L_0x037b:
            r18 = r7
            r36 = r12
            r7 = r13
            r12 = r5
            r22 = r12
            goto L_0x0387
        L_0x0384:
            r36 = r47
        L_0x0386:
            r7 = r8
        L_0x0387:
            int r8 = r7 + -1
            r11 = r30
            r12 = r31
            r7 = r36
            r13 = 0
            r15 = 0
            goto L_0x004b
        L_0x0394:
            r36 = r7
            r30 = r11
            r31 = r12
        L_0x039a:
            if (r7 < 0) goto L_0x03c3
            r12 = r36
            r0 = 0
        L_0x039f:
            if (r0 > r12) goto L_0x03be
            r1 = 0
            r3 = 0
        L_0x03a4:
            int r4 = org.apache.commons.math3.util.FastMath.min((int) r7, (int) r12)
            if (r3 > r4) goto L_0x03b7
            r4 = r30[r0]
            r4 = r4[r3]
            r6 = r10[r3]
            r13 = r6[r7]
            double r4 = r4 * r13
            double r1 = r1 + r4
            int r3 = r3 + 1
            goto L_0x03a4
        L_0x03b7:
            r3 = r30[r0]
            r3[r7] = r1
            int r0 = r0 + 1
            goto L_0x039f
        L_0x03be:
            int r7 = r7 + -1
            r36 = r12
            goto L_0x039a
        L_0x03c3:
            r0 = r31
            org.apache.commons.math3.linear.ArrayRealVector[] r1 = new org.apache.commons.math3.linear.ArrayRealVector[r0]
            r9.eigenvectors = r1
            double[] r1 = new double[r0]
            r2 = 0
        L_0x03cc:
            if (r2 >= r0) goto L_0x03e6
            r3 = 0
        L_0x03cf:
            if (r3 >= r0) goto L_0x03da
            r4 = r30[r3]
            r4 = r4[r2]
            r1[r3] = r4
            int r3 = r3 + 1
            goto L_0x03cf
        L_0x03da:
            org.apache.commons.math3.linear.ArrayRealVector[] r3 = r9.eigenvectors
            org.apache.commons.math3.linear.ArrayRealVector r4 = new org.apache.commons.math3.linear.ArrayRealVector
            r4.<init>((double[]) r1)
            r3[r2] = r4
            int r2 = r2 + 1
            goto L_0x03cc
        L_0x03e6:
            return
        L_0x03e7:
            org.apache.commons.math3.exception.MathArithmeticException r0 = new org.apache.commons.math3.exception.MathArithmeticException
            org.apache.commons.math3.exception.util.LocalizedFormats r1 = org.apache.commons.math3.exception.util.LocalizedFormats.ZERO_NORM
            r2 = 0
            java.lang.Object[] r2 = new java.lang.Object[r2]
            r0.<init>(r1, r2)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.math3.linear.EigenDecomposition.findEigenVectorsFromSchur(org.apache.commons.math3.linear.SchurTransformer):void");
    }
}
