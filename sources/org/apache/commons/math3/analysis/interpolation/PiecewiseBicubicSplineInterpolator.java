package org.apache.commons.math3.analysis.interpolation;

import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.NoDataException;
import org.apache.commons.math3.exception.NonMonotonicSequenceException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.util.MathArrays;

public class PiecewiseBicubicSplineInterpolator implements BivariateGridInterpolator {
    public PiecewiseBicubicSplineInterpolatingFunction interpolate(double[] dArr, double[] dArr2, double[][] dArr3) throws DimensionMismatchException, NullArgumentException, NoDataException, NonMonotonicSequenceException {
        if (dArr == null || dArr2 == null || dArr3 == null || dArr3[0] == null) {
            throw new NullArgumentException();
        } else if (dArr.length == 0 || dArr2.length == 0 || dArr3.length == 0) {
            throw new NoDataException();
        } else {
            MathArrays.checkOrder(dArr);
            MathArrays.checkOrder(dArr2);
            return new PiecewiseBicubicSplineInterpolatingFunction(dArr, dArr2, dArr3);
        }
    }
}
