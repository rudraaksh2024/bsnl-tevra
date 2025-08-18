package org.apache.commons.math3.analysis.integration.gauss;

import org.apache.commons.math3.analysis.UnivariateFunction;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.NonMonotonicSequenceException;
import org.apache.commons.math3.util.MathArrays;
import org.apache.commons.math3.util.Pair;

public class GaussIntegrator {
    private final double[] points;
    private final double[] weights;

    public GaussIntegrator(double[] dArr, double[] dArr2) throws NonMonotonicSequenceException, DimensionMismatchException {
        if (dArr.length == dArr2.length) {
            MathArrays.checkOrder(dArr, MathArrays.OrderDirection.INCREASING, true, true);
            this.points = (double[]) dArr.clone();
            this.weights = (double[]) dArr2.clone();
            return;
        }
        throw new DimensionMismatchException(dArr.length, dArr2.length);
    }

    public GaussIntegrator(Pair<double[], double[]> pair) throws NonMonotonicSequenceException {
        this(pair.getFirst(), pair.getSecond());
    }

    public double integrate(UnivariateFunction univariateFunction) {
        double d = 0.0d;
        int i = 0;
        double d2 = 0.0d;
        while (true) {
            double[] dArr = this.points;
            if (i >= dArr.length) {
                return d;
            }
            double value = (this.weights[i] * univariateFunction.value(dArr[i])) - d2;
            double d3 = d + value;
            i++;
            double d4 = (d3 - d) - value;
            d = d3;
            d2 = d4;
        }
    }

    public int getNumberOfPoints() {
        return this.points.length;
    }

    public double getPoint(int i) {
        return this.points[i];
    }

    public double getWeight(int i) {
        return this.weights[i];
    }
}
