package com.graphbuilder.math.func;

public class MinFunction implements Function {
    public boolean acceptNumParam(int i) {
        return i >= 0;
    }

    public String toString() {
        return "min(x1, x2, ..., xn)";
    }

    public double of(double[] dArr, int i) {
        if (i == 0) {
            return Double.MIN_VALUE;
        }
        double d = Double.MAX_VALUE;
        for (int i2 = 0; i2 < i; i2++) {
            double d2 = dArr[i2];
            if (d2 < d) {
                d = d2;
            }
        }
        return d;
    }
}
