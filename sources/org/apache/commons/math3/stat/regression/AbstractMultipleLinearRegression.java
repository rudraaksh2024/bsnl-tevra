package org.apache.commons.math3.stat.regression;

import java.lang.reflect.Array;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.InsufficientDataException;
import org.apache.commons.math3.exception.MathIllegalArgumentException;
import org.apache.commons.math3.exception.NoDataException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.ArrayRealVector;
import org.apache.commons.math3.linear.NonSquareMatrixException;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.linear.RealVector;
import org.apache.commons.math3.stat.descriptive.moment.Variance;
import org.apache.commons.math3.util.FastMath;

public abstract class AbstractMultipleLinearRegression implements MultipleLinearRegression {
    private boolean noIntercept = false;
    private RealMatrix xMatrix;
    private RealVector yVector;

    /* access modifiers changed from: protected */
    public abstract RealVector calculateBeta();

    /* access modifiers changed from: protected */
    public abstract RealMatrix calculateBetaVariance();

    /* access modifiers changed from: protected */
    public RealMatrix getX() {
        return this.xMatrix;
    }

    /* access modifiers changed from: protected */
    public RealVector getY() {
        return this.yVector;
    }

    public boolean isNoIntercept() {
        return this.noIntercept;
    }

    public void setNoIntercept(boolean z) {
        this.noIntercept = z;
    }

    public void newSampleData(double[] dArr, int i, int i2) {
        if (dArr != null) {
            int i3 = i2 + 1;
            int i4 = i * i3;
            if (dArr.length != i4) {
                throw new DimensionMismatchException(dArr.length, i4);
            } else if (i > i2) {
                double[] dArr2 = new double[i];
                if (!this.noIntercept) {
                    i2 = i3;
                }
                int[] iArr = new int[2];
                iArr[1] = i2;
                iArr[0] = i;
                double[][] dArr3 = (double[][]) Array.newInstance(Double.TYPE, iArr);
                int i5 = 0;
                int i6 = 0;
                while (i5 < i) {
                    int i7 = i6 + 1;
                    dArr2[i5] = dArr[i6];
                    boolean z = this.noIntercept;
                    if (!z) {
                        dArr3[i5][0] = 1.0d;
                    }
                    int i8 = !z;
                    while (i8 < i2) {
                        dArr3[i5][i8] = dArr[i7];
                        i8++;
                        i7++;
                    }
                    i5++;
                    i6 = i7;
                }
                this.xMatrix = new Array2DRowRealMatrix(dArr3);
                this.yVector = new ArrayRealVector(dArr2);
            } else {
                throw new InsufficientDataException(LocalizedFormats.INSUFFICIENT_OBSERVED_POINTS_IN_SAMPLE, Integer.valueOf(i), Integer.valueOf(i3));
            }
        } else {
            throw new NullArgumentException();
        }
    }

    /* access modifiers changed from: protected */
    public void newYSampleData(double[] dArr) {
        if (dArr == null) {
            throw new NullArgumentException();
        } else if (dArr.length != 0) {
            this.yVector = new ArrayRealVector(dArr);
        } else {
            throw new NoDataException();
        }
    }

    /* access modifiers changed from: protected */
    public void newXSampleData(double[][] dArr) {
        if (dArr == null) {
            throw new NullArgumentException();
        } else if (dArr.length == 0) {
            throw new NoDataException();
        } else if (this.noIntercept) {
            this.xMatrix = new Array2DRowRealMatrix(dArr, true);
        } else {
            int length = dArr[0].length;
            int length2 = dArr.length;
            int[] iArr = new int[2];
            iArr[1] = length + 1;
            iArr[0] = length2;
            double[][] dArr2 = (double[][]) Array.newInstance(Double.TYPE, iArr);
            int i = 0;
            while (i < dArr.length) {
                double[] dArr3 = dArr[i];
                if (dArr3.length == length) {
                    double[] dArr4 = dArr2[i];
                    dArr4[0] = 1.0d;
                    System.arraycopy(dArr3, 0, dArr4, 1, length);
                    i++;
                } else {
                    throw new DimensionMismatchException(dArr[i].length, length);
                }
            }
            this.xMatrix = new Array2DRowRealMatrix(dArr2, false);
        }
    }

    /* access modifiers changed from: protected */
    public void validateSampleData(double[][] dArr, double[] dArr2) throws MathIllegalArgumentException {
        if (dArr == null || dArr2 == null) {
            throw new NullArgumentException();
        } else if (dArr.length != dArr2.length) {
            throw new DimensionMismatchException(dArr2.length, dArr.length);
        } else if (dArr.length == 0) {
            throw new NoDataException();
        } else if (dArr[0].length + 1 > dArr.length) {
            throw new MathIllegalArgumentException(LocalizedFormats.NOT_ENOUGH_DATA_FOR_NUMBER_OF_PREDICTORS, Integer.valueOf(dArr.length), Integer.valueOf(dArr[0].length));
        }
    }

    /* access modifiers changed from: protected */
    public void validateCovarianceData(double[][] dArr, double[][] dArr2) {
        if (dArr.length != dArr2.length) {
            throw new DimensionMismatchException(dArr.length, dArr2.length);
        } else if (dArr2.length > 0 && dArr2.length != dArr2[0].length) {
            throw new NonSquareMatrixException(dArr2.length, dArr2[0].length);
        }
    }

    public double[] estimateRegressionParameters() {
        return calculateBeta().toArray();
    }

    public double[] estimateResiduals() {
        return this.yVector.subtract(this.xMatrix.operate(calculateBeta())).toArray();
    }

    public double[][] estimateRegressionParametersVariance() {
        return calculateBetaVariance().getData();
    }

    public double[] estimateRegressionParametersStandardErrors() {
        double[][] estimateRegressionParametersVariance = estimateRegressionParametersVariance();
        double calculateErrorVariance = calculateErrorVariance();
        int length = estimateRegressionParametersVariance[0].length;
        double[] dArr = new double[length];
        for (int i = 0; i < length; i++) {
            dArr[i] = FastMath.sqrt(estimateRegressionParametersVariance[i][i] * calculateErrorVariance);
        }
        return dArr;
    }

    public double estimateRegressandVariance() {
        return calculateYVariance();
    }

    public double estimateErrorVariance() {
        return calculateErrorVariance();
    }

    public double estimateRegressionStandardError() {
        return FastMath.sqrt(estimateErrorVariance());
    }

    /* access modifiers changed from: protected */
    public double calculateYVariance() {
        return new Variance().evaluate(this.yVector.toArray());
    }

    /* access modifiers changed from: protected */
    public double calculateErrorVariance() {
        RealVector calculateResiduals = calculateResiduals();
        return calculateResiduals.dotProduct(calculateResiduals) / ((double) (this.xMatrix.getRowDimension() - this.xMatrix.getColumnDimension()));
    }

    /* access modifiers changed from: protected */
    public RealVector calculateResiduals() {
        return this.yVector.subtract(this.xMatrix.operate(calculateBeta()));
    }
}
