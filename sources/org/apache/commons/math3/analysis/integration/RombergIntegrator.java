package org.apache.commons.math3.analysis.integration;

import org.apache.commons.math3.exception.MaxCountExceededException;
import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import org.apache.commons.math3.exception.NumberIsTooLargeException;
import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.exception.TooManyEvaluationsException;
import org.apache.commons.math3.util.FastMath;

public class RombergIntegrator extends BaseAbstractUnivariateIntegrator {
    public static final int ROMBERG_MAX_ITERATIONS_COUNT = 32;

    public RombergIntegrator(double d, double d2, int i, int i2) throws NotStrictlyPositiveException, NumberIsTooSmallException, NumberIsTooLargeException {
        super(d, d2, i, i2);
        if (i2 > 32) {
            throw new NumberIsTooLargeException(Integer.valueOf(i2), 32, false);
        }
    }

    public RombergIntegrator(int i, int i2) throws NotStrictlyPositiveException, NumberIsTooSmallException, NumberIsTooLargeException {
        super(i, i2);
        if (i2 > 32) {
            throw new NumberIsTooLargeException(Integer.valueOf(i2), 32, false);
        }
    }

    public RombergIntegrator() {
        super(3, 32);
    }

    /* access modifiers changed from: protected */
    public double doIntegrate() throws TooManyEvaluationsException, MaxCountExceededException {
        double d;
        int maximalIterationCount = getMaximalIterationCount() + 1;
        double[] dArr = new double[maximalIterationCount];
        double[] dArr2 = new double[maximalIterationCount];
        TrapezoidIntegrator trapezoidIntegrator = new TrapezoidIntegrator();
        dArr2[0] = trapezoidIntegrator.stage(this, 0);
        incrementCount();
        double d2 = dArr2[0];
        while (true) {
            int iterations = getIterations();
            dArr[0] = trapezoidIntegrator.stage(this, iterations);
            incrementCount();
            for (int i = 1; i <= iterations; i++) {
                int i2 = i - 1;
                double d3 = dArr[i2];
                dArr[i] = d3 + ((d3 - dArr2[i2]) / ((double) ((1 << (i * 2)) - 1)));
            }
            d = dArr[iterations];
            if (iterations >= getMinimalIterationCount()) {
                double abs = FastMath.abs(d - d2);
                if (abs <= getRelativeAccuracy() * (FastMath.abs(d2) + FastMath.abs(d)) * 0.5d || abs <= getAbsoluteAccuracy()) {
                    return d;
                }
            }
            d2 = d;
            double[] dArr3 = dArr;
            dArr = dArr2;
            dArr2 = dArr3;
        }
        return d;
    }
}
