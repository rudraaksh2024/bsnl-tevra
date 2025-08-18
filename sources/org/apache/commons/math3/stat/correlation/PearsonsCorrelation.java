package org.apache.commons.math3.stat.correlation;

import java.lang.reflect.Array;
import org.apache.commons.math3.distribution.TDistribution;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.MathIllegalArgumentException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.linear.BlockRealMatrix;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.stat.regression.SimpleRegression;
import org.apache.commons.math3.util.FastMath;

public class PearsonsCorrelation {
    private final RealMatrix correlationMatrix;
    private final int nObs;

    public PearsonsCorrelation() {
        this.correlationMatrix = null;
        this.nObs = 0;
    }

    public PearsonsCorrelation(double[][] dArr) {
        this((RealMatrix) new BlockRealMatrix(dArr));
    }

    public PearsonsCorrelation(RealMatrix realMatrix) {
        this.nObs = realMatrix.getRowDimension();
        this.correlationMatrix = computeCorrelationMatrix(realMatrix);
    }

    public PearsonsCorrelation(Covariance covariance) {
        RealMatrix covarianceMatrix = covariance.getCovarianceMatrix();
        if (covarianceMatrix != null) {
            this.nObs = covariance.getN();
            this.correlationMatrix = covarianceToCorrelation(covarianceMatrix);
            return;
        }
        throw new NullArgumentException(LocalizedFormats.COVARIANCE_MATRIX, new Object[0]);
    }

    public PearsonsCorrelation(RealMatrix realMatrix, int i) {
        this.nObs = i;
        this.correlationMatrix = covarianceToCorrelation(realMatrix);
    }

    public RealMatrix getCorrelationMatrix() {
        return this.correlationMatrix;
    }

    public RealMatrix getCorrelationStandardErrors() {
        int columnDimension = this.correlationMatrix.getColumnDimension();
        int[] iArr = new int[2];
        iArr[1] = columnDimension;
        iArr[0] = columnDimension;
        double[][] dArr = (double[][]) Array.newInstance(Double.TYPE, iArr);
        for (int i = 0; i < columnDimension; i++) {
            for (int i2 = 0; i2 < columnDimension; i2++) {
                double entry = this.correlationMatrix.getEntry(i, i2);
                dArr[i][i2] = FastMath.sqrt((1.0d - (entry * entry)) / ((double) (this.nObs - 2)));
            }
        }
        return new BlockRealMatrix(dArr);
    }

    public RealMatrix getCorrelationPValues() {
        TDistribution tDistribution = new TDistribution((double) (this.nObs - 2));
        int columnDimension = this.correlationMatrix.getColumnDimension();
        int[] iArr = new int[2];
        iArr[1] = columnDimension;
        iArr[0] = columnDimension;
        double[][] dArr = (double[][]) Array.newInstance(Double.TYPE, iArr);
        for (int i = 0; i < columnDimension; i++) {
            for (int i2 = 0; i2 < columnDimension; i2++) {
                if (i == i2) {
                    dArr[i][i2] = 0.0d;
                } else {
                    double entry = this.correlationMatrix.getEntry(i, i2);
                    dArr[i][i2] = tDistribution.cumulativeProbability(-FastMath.abs(entry * FastMath.sqrt(((double) (this.nObs - 2)) / (1.0d - (entry * entry))))) * 2.0d;
                }
            }
        }
        return new BlockRealMatrix(dArr);
    }

    public RealMatrix computeCorrelationMatrix(RealMatrix realMatrix) {
        checkSufficientData(realMatrix);
        int columnDimension = realMatrix.getColumnDimension();
        BlockRealMatrix blockRealMatrix = new BlockRealMatrix(columnDimension, columnDimension);
        for (int i = 0; i < columnDimension; i++) {
            for (int i2 = 0; i2 < i; i2++) {
                double correlation = correlation(realMatrix.getColumn(i), realMatrix.getColumn(i2));
                blockRealMatrix.setEntry(i, i2, correlation);
                blockRealMatrix.setEntry(i2, i, correlation);
            }
            blockRealMatrix.setEntry(i, i, 1.0d);
        }
        return blockRealMatrix;
    }

    public RealMatrix computeCorrelationMatrix(double[][] dArr) {
        return computeCorrelationMatrix((RealMatrix) new BlockRealMatrix(dArr));
    }

    public double correlation(double[] dArr, double[] dArr2) {
        SimpleRegression simpleRegression = new SimpleRegression();
        if (dArr.length == dArr2.length) {
            if (dArr.length >= 2) {
                for (int i = 0; i < dArr.length; i++) {
                    simpleRegression.addData(dArr[i], dArr2[i]);
                }
                return simpleRegression.getR();
            }
            throw new MathIllegalArgumentException(LocalizedFormats.INSUFFICIENT_DIMENSION, Integer.valueOf(dArr.length), 2);
        }
        throw new DimensionMismatchException(dArr.length, dArr2.length);
    }

    public RealMatrix covarianceToCorrelation(RealMatrix realMatrix) {
        int columnDimension = realMatrix.getColumnDimension();
        BlockRealMatrix blockRealMatrix = new BlockRealMatrix(columnDimension, columnDimension);
        for (int i = 0; i < columnDimension; i++) {
            double sqrt = FastMath.sqrt(realMatrix.getEntry(i, i));
            blockRealMatrix.setEntry(i, i, 1.0d);
            for (int i2 = 0; i2 < i; i2++) {
                double entry = realMatrix.getEntry(i, i2) / (FastMath.sqrt(realMatrix.getEntry(i2, i2)) * sqrt);
                blockRealMatrix.setEntry(i, i2, entry);
                blockRealMatrix.setEntry(i2, i, entry);
            }
        }
        return blockRealMatrix;
    }

    private void checkSufficientData(RealMatrix realMatrix) {
        int rowDimension = realMatrix.getRowDimension();
        int columnDimension = realMatrix.getColumnDimension();
        if (rowDimension < 2 || columnDimension < 2) {
            throw new MathIllegalArgumentException(LocalizedFormats.INSUFFICIENT_ROWS_AND_COLUMNS, Integer.valueOf(rowDimension), Integer.valueOf(columnDimension));
        }
    }
}
