package org.apache.commons.math3.stat.interval;

import org.apache.commons.math3.exception.MathIllegalArgumentException;
import org.apache.commons.math3.exception.util.LocalizedFormats;

public class ConfidenceInterval {
    private double confidenceLevel;
    private double lowerBound;
    private double upperBound;

    public ConfidenceInterval(double d, double d2, double d3) {
        checkParameters(d, d2, d3);
        this.lowerBound = d;
        this.upperBound = d2;
        this.confidenceLevel = d3;
    }

    public double getLowerBound() {
        return this.lowerBound;
    }

    public double getUpperBound() {
        return this.upperBound;
    }

    public double getConfidenceLevel() {
        return this.confidenceLevel;
    }

    public String toString() {
        return "[" + this.lowerBound + ";" + this.upperBound + "] (confidence level:" + this.confidenceLevel + ")";
    }

    private void checkParameters(double d, double d2, double d3) {
        if (d >= d2) {
            throw new MathIllegalArgumentException(LocalizedFormats.LOWER_BOUND_NOT_BELOW_UPPER_BOUND, Double.valueOf(d), Double.valueOf(d2));
        } else if (d3 <= 0.0d || d3 >= 1.0d) {
            throw new MathIllegalArgumentException(LocalizedFormats.OUT_OF_BOUNDS_CONFIDENCE_LEVEL, Double.valueOf(d3), 0, 1);
        }
    }
}
