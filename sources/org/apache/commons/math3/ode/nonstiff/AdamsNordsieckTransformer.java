package org.apache.commons.math3.ode.nonstiff;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.math3.fraction.BigFraction;
import org.apache.commons.math3.linear.Array2DRowFieldMatrix;
import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.ArrayFieldVector;
import org.apache.commons.math3.linear.FieldDecompositionSolver;
import org.apache.commons.math3.linear.FieldLUDecomposition;
import org.apache.commons.math3.linear.FieldMatrix;
import org.apache.commons.math3.linear.MatrixUtils;
import org.apache.commons.math3.linear.QRDecomposition;
import org.apache.commons.math3.linear.RealMatrix;

public class AdamsNordsieckTransformer {
    private static final Map<Integer, AdamsNordsieckTransformer> CACHE = new HashMap();
    private final double[] c1;
    private final Array2DRowRealMatrix update;

    private AdamsNordsieckTransformer(int i) {
        int i2 = i - 1;
        FieldMatrix<BigFraction> buildP = buildP(i2);
        FieldDecompositionSolver solver = new FieldLUDecomposition(buildP).getSolver();
        BigFraction[] bigFractionArr = new BigFraction[i2];
        Arrays.fill(bigFractionArr, BigFraction.ONE);
        BigFraction[] bigFractionArr2 = (BigFraction[]) solver.solve(new ArrayFieldVector((T[]) bigFractionArr, false)).toArray();
        BigFraction[][] bigFractionArr3 = (BigFraction[][]) buildP.getData();
        for (int length = bigFractionArr3.length - 1; length > 0; length--) {
            bigFractionArr3[length] = bigFractionArr3[length - 1];
        }
        BigFraction[] bigFractionArr4 = new BigFraction[i2];
        bigFractionArr3[0] = bigFractionArr4;
        Arrays.fill(bigFractionArr4, BigFraction.ZERO);
        this.update = MatrixUtils.bigFractionMatrixToRealMatrix(solver.solve(new Array2DRowFieldMatrix((T[][]) bigFractionArr3, false)));
        this.c1 = new double[i2];
        for (int i3 = 0; i3 < i2; i3++) {
            this.c1[i3] = bigFractionArr2[i3].doubleValue();
        }
    }

    public static AdamsNordsieckTransformer getInstance(int i) {
        AdamsNordsieckTransformer adamsNordsieckTransformer;
        Map<Integer, AdamsNordsieckTransformer> map = CACHE;
        synchronized (map) {
            adamsNordsieckTransformer = map.get(Integer.valueOf(i));
            if (adamsNordsieckTransformer == null) {
                adamsNordsieckTransformer = new AdamsNordsieckTransformer(i);
                map.put(Integer.valueOf(i), adamsNordsieckTransformer);
            }
        }
        return adamsNordsieckTransformer;
    }

    @Deprecated
    public int getNSteps() {
        return this.c1.length;
    }

    private FieldMatrix<BigFraction> buildP(int i) {
        int[] iArr = new int[2];
        iArr[1] = i;
        iArr[0] = i;
        BigFraction[][] bigFractionArr = (BigFraction[][]) Array.newInstance(BigFraction.class, iArr);
        for (int i2 = 1; i2 <= bigFractionArr.length; i2++) {
            BigFraction[] bigFractionArr2 = bigFractionArr[i2 - 1];
            int i3 = -i2;
            int i4 = 1;
            int i5 = i3;
            while (i4 <= bigFractionArr2.length) {
                int i6 = i4 - 1;
                i4++;
                bigFractionArr2[i6] = new BigFraction(i5 * i4);
                i5 *= i3;
            }
        }
        return new Array2DRowFieldMatrix((T[][]) bigFractionArr, false);
    }

    public Array2DRowRealMatrix initializeHighOrderDerivatives(double d, double[] dArr, double[][] dArr2, double[][] dArr3) {
        double d2;
        double[][] dArr4 = dArr2;
        double[] dArr5 = this.c1;
        int i = 1;
        int[] iArr = new int[2];
        iArr[1] = dArr5.length + 1;
        char c = 0;
        iArr[0] = dArr5.length + 1;
        double[][] dArr6 = (double[][]) Array.newInstance(Double.TYPE, iArr);
        int[] iArr2 = new int[2];
        iArr2[1] = dArr4[0].length;
        iArr2[0] = this.c1.length + 1;
        double[][] dArr7 = (double[][]) Array.newInstance(Double.TYPE, iArr2);
        double[] dArr8 = dArr4[0];
        double[] dArr9 = dArr3[0];
        int i2 = 1;
        while (i2 < dArr4.length) {
            double d3 = dArr[i2] - dArr[c];
            double d4 = d3 / d;
            double d5 = 1.0d / d;
            int i3 = i2 * 2;
            int i4 = i3 - 2;
            double[] dArr10 = dArr6[i4];
            int i5 = i3 - i;
            double[] dArr11 = null;
            double[] dArr12 = i5 < dArr6.length ? dArr6[i5] : null;
            double[][] dArr13 = dArr6;
            double[] dArr14 = dArr9;
            int i6 = 0;
            while (i6 < dArr10.length) {
                d5 *= d4;
                dArr10[i6] = d3 * d5;
                if (dArr12 != null) {
                    d2 = d4;
                    dArr12[i6] = ((double) (i6 + 2)) * d5;
                } else {
                    d2 = d4;
                }
                i6++;
                d4 = d2;
            }
            double[] dArr15 = dArr4[i2];
            double[] dArr16 = dArr3[i2];
            double[] dArr17 = dArr7[i4];
            if (i5 < dArr7.length) {
                dArr11 = dArr7[i5];
            }
            for (int i7 = 0; i7 < dArr15.length; i7++) {
                dArr17[i7] = (dArr15[i7] - dArr8[i7]) - (dArr14[i7] * d3);
                if (dArr11 != null) {
                    dArr11[i7] = dArr16[i7] - dArr14[i7];
                }
            }
            i2++;
            dArr9 = dArr14;
            dArr6 = dArr13;
            c = 0;
            i = 1;
        }
        double[][] dArr18 = dArr6;
        RealMatrix solve = new QRDecomposition(new Array2DRowRealMatrix(dArr6, false)).getSolver().solve((RealMatrix) new Array2DRowRealMatrix(dArr7, false));
        Array2DRowRealMatrix array2DRowRealMatrix = new Array2DRowRealMatrix(solve.getRowDimension() - 1, solve.getColumnDimension());
        for (int i8 = 0; i8 < array2DRowRealMatrix.getRowDimension(); i8++) {
            for (int i9 = 0; i9 < array2DRowRealMatrix.getColumnDimension(); i9++) {
                array2DRowRealMatrix.setEntry(i8, i9, solve.getEntry(i8, i9));
            }
        }
        return array2DRowRealMatrix;
    }

    public Array2DRowRealMatrix updateHighOrderDerivativesPhase1(Array2DRowRealMatrix array2DRowRealMatrix) {
        return this.update.multiply(array2DRowRealMatrix);
    }

    public void updateHighOrderDerivativesPhase2(double[] dArr, double[] dArr2, Array2DRowRealMatrix array2DRowRealMatrix) {
        double[][] dataRef = array2DRowRealMatrix.getDataRef();
        for (int i = 0; i < dataRef.length; i++) {
            double[] dArr3 = dataRef[i];
            double d = this.c1[i];
            for (int i2 = 0; i2 < dArr3.length; i2++) {
                dArr3[i2] = dArr3[i2] + ((dArr[i2] - dArr2[i2]) * d);
            }
        }
    }
}
