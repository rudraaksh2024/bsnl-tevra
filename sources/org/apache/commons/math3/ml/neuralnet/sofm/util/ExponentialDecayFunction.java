package org.apache.commons.math3.ml.neuralnet.sofm.util;

import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import org.apache.commons.math3.exception.NumberIsTooLargeException;
import org.apache.commons.math3.util.FastMath;

public class ExponentialDecayFunction {
    private final double a;
    private final double oneOverB;

    public ExponentialDecayFunction(double d, double d2, long j) {
        if (d <= 0.0d) {
            throw new NotStrictlyPositiveException(Double.valueOf(d));
        } else if (d2 <= 0.0d) {
            throw new NotStrictlyPositiveException(Double.valueOf(d2));
        } else if (d2 >= d) {
            throw new NumberIsTooLargeException(Double.valueOf(d2), Double.valueOf(d), false);
        } else if (j > 0) {
            this.a = d;
            this.oneOverB = (-FastMath.log(d2 / d)) / ((double) j);
        } else {
            throw new NotStrictlyPositiveException(Long.valueOf(j));
        }
    }

    public double value(long j) {
        return this.a * FastMath.exp(((double) (-j)) * this.oneOverB);
    }
}
