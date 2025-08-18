package org.apache.commons.math3.distribution;

import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.EigenDecomposition;
import org.apache.commons.math3.linear.NonPositiveDefiniteMatrixException;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.linear.SingularMatrixException;
import org.apache.commons.math3.random.RandomGenerator;
import org.apache.commons.math3.random.Well19937c;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.MathArrays;

public class MultivariateNormalDistribution extends AbstractMultivariateRealDistribution {
    private final RealMatrix covarianceMatrix;
    private final double covarianceMatrixDeterminant;
    private final RealMatrix covarianceMatrixInverse;
    private final double[] means;
    private final RealMatrix samplingMatrix;

    public MultivariateNormalDistribution(double[] dArr, double[][] dArr2) throws SingularMatrixException, DimensionMismatchException, NonPositiveDefiniteMatrixException {
        this(new Well19937c(), dArr, dArr2);
    }

    public MultivariateNormalDistribution(RandomGenerator randomGenerator, double[] dArr, double[][] dArr2) throws SingularMatrixException, DimensionMismatchException, NonPositiveDefiniteMatrixException {
        super(randomGenerator, dArr.length);
        int length = dArr.length;
        if (dArr2.length == length) {
            int i = 0;
            while (i < length) {
                if (length == dArr2[i].length) {
                    i++;
                } else {
                    throw new DimensionMismatchException(dArr2[i].length, length);
                }
            }
            this.means = MathArrays.copyOf(dArr);
            Array2DRowRealMatrix array2DRowRealMatrix = new Array2DRowRealMatrix(dArr2);
            this.covarianceMatrix = array2DRowRealMatrix;
            EigenDecomposition eigenDecomposition = new EigenDecomposition(array2DRowRealMatrix);
            this.covarianceMatrixInverse = eigenDecomposition.getSolver().getInverse();
            this.covarianceMatrixDeterminant = eigenDecomposition.getDeterminant();
            double[] realEigenvalues = eigenDecomposition.getRealEigenvalues();
            int i2 = 0;
            while (i2 < realEigenvalues.length) {
                if (realEigenvalues[i2] >= 0.0d) {
                    i2++;
                } else {
                    throw new NonPositiveDefiniteMatrixException(realEigenvalues[i2], i2, 0.0d);
                }
            }
            Array2DRowRealMatrix array2DRowRealMatrix2 = new Array2DRowRealMatrix(length, length);
            for (int i3 = 0; i3 < length; i3++) {
                array2DRowRealMatrix2.setColumn(i3, eigenDecomposition.getEigenvector(i3).toArray());
            }
            RealMatrix transpose = array2DRowRealMatrix2.transpose();
            for (int i4 = 0; i4 < length; i4++) {
                double sqrt = FastMath.sqrt(realEigenvalues[i4]);
                for (int i5 = 0; i5 < length; i5++) {
                    transpose.multiplyEntry(i4, i5, sqrt);
                }
            }
            this.samplingMatrix = array2DRowRealMatrix2.multiply(transpose);
            return;
        }
        throw new DimensionMismatchException(dArr2.length, length);
    }

    public double[] getMeans() {
        return MathArrays.copyOf(this.means);
    }

    public RealMatrix getCovariances() {
        return this.covarianceMatrix.copy();
    }

    public double density(double[] dArr) throws DimensionMismatchException {
        int dimension = getDimension();
        if (dArr.length == dimension) {
            return FastMath.pow(6.283185307179586d, ((double) dimension) * -0.5d) * FastMath.pow(this.covarianceMatrixDeterminant, -0.5d) * getExponentTerm(dArr);
        }
        throw new DimensionMismatchException(dArr.length, dimension);
    }

    public double[] getStandardDeviations() {
        int dimension = getDimension();
        double[] dArr = new double[dimension];
        double[][] data = this.covarianceMatrix.getData();
        for (int i = 0; i < dimension; i++) {
            dArr[i] = FastMath.sqrt(data[i][i]);
        }
        return dArr;
    }

    public double[] sample() {
        int dimension = getDimension();
        double[] dArr = new double[dimension];
        for (int i = 0; i < dimension; i++) {
            dArr[i] = this.random.nextGaussian();
        }
        double[] operate = this.samplingMatrix.operate(dArr);
        for (int i2 = 0; i2 < dimension; i2++) {
            operate[i2] = operate[i2] + this.means[i2];
        }
        return operate;
    }

    private double getExponentTerm(double[] dArr) {
        int length = dArr.length;
        double[] dArr2 = new double[length];
        for (int i = 0; i < length; i++) {
            dArr2[i] = dArr[i] - getMeans()[i];
        }
        double[] preMultiply = this.covarianceMatrixInverse.preMultiply(dArr2);
        double d = 0.0d;
        for (int i2 = 0; i2 < preMultiply.length; i2++) {
            d += preMultiply[i2] * dArr2[i2];
        }
        return FastMath.exp(d * -0.5d);
    }
}
