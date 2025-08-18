package org.apache.commons.math3.optim.univariate;

import org.apache.commons.math3.exception.NumberIsTooLargeException;
import org.apache.commons.math3.exception.OutOfRangeException;
import org.apache.commons.math3.optim.OptimizationData;

public class SearchInterval implements OptimizationData {
    private final double lower;
    private final double start;
    private final double upper;

    public SearchInterval(double d, double d2, double d3) {
        if (d >= d2) {
            throw new NumberIsTooLargeException(Double.valueOf(d), Double.valueOf(d2), false);
        } else if (d3 < d || d3 > d2) {
            throw new OutOfRangeException(Double.valueOf(d3), Double.valueOf(d), Double.valueOf(d2));
        } else {
            this.lower = d;
            this.upper = d2;
            this.start = d3;
        }
    }

    public SearchInterval(double d, double d2) {
        this(d, d2, (d + d2) * 0.5d);
    }

    public double getMin() {
        return this.lower;
    }

    public double getMax() {
        return this.upper;
    }

    public double getStartValue() {
        return this.start;
    }
}
