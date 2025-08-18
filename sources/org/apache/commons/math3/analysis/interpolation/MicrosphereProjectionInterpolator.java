package org.apache.commons.math3.analysis.interpolation;

import org.apache.commons.math3.analysis.MultivariateFunction;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.NoDataException;
import org.apache.commons.math3.exception.NotPositiveException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.random.UnitSphereRandomVectorGenerator;

public class MicrosphereProjectionInterpolator implements MultivariateInterpolator {
    /* access modifiers changed from: private */
    public final double exponent;
    private final InterpolatingMicrosphere microsphere;
    /* access modifiers changed from: private */
    public final double noInterpolationTolerance;
    private final boolean sharedSphere;

    public MicrosphereProjectionInterpolator(int i, int i2, double d, double d2, double d3, double d4, boolean z, double d5) {
        this(new InterpolatingMicrosphere(i, i2, d, d2, d3, new UnitSphereRandomVectorGenerator(i)), d4, z, d5);
    }

    public MicrosphereProjectionInterpolator(InterpolatingMicrosphere interpolatingMicrosphere, double d, boolean z, double d2) throws NotPositiveException {
        if (d >= 0.0d) {
            this.microsphere = interpolatingMicrosphere;
            this.exponent = d;
            this.sharedSphere = z;
            this.noInterpolationTolerance = d2;
            return;
        }
        throw new NotPositiveException(Double.valueOf(d));
    }

    public MultivariateFunction interpolate(final double[][] dArr, final double[] dArr2) throws DimensionMismatchException, NoDataException, NullArgumentException {
        if (dArr == null || dArr2 == null) {
            throw new NullArgumentException();
        } else if (dArr.length == 0) {
            throw new NoDataException();
        } else if (dArr.length != dArr2.length) {
            throw new DimensionMismatchException(dArr.length, dArr2.length);
        } else if (dArr[0] != null) {
            int dimension = this.microsphere.getDimension();
            if (dimension == dArr[0].length) {
                final InterpolatingMicrosphere copy = this.sharedSphere ? this.microsphere : this.microsphere.copy();
                return new MultivariateFunction() {
                    public double value(double[] dArr) {
                        return copy.value(dArr, dArr, dArr2, MicrosphereProjectionInterpolator.this.exponent, MicrosphereProjectionInterpolator.this.noInterpolationTolerance);
                    }
                };
            }
            throw new DimensionMismatchException(dArr[0].length, dimension);
        } else {
            throw new NullArgumentException();
        }
    }
}
