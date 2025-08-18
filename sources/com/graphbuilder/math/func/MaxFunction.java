package com.graphbuilder.math.func;

public class MaxFunction implements Function {
    public boolean acceptNumParam(int i) {
        return i >= 0;
    }

    public String toString() {
        return "max(x1, x2, ..., xn)";
    }

    public double of(double[] dArr, int i) {
        if (i == 0) {
            return Double.MAX_VALUE;
        }
        double d = -1.7976931348623157E308d;
        for (int i2 = 0; i2 < i; i2++) {
            double d2 = dArr[i2];
            if (d2 > d) {
                d = d2;
            }
        }
        return d;
    }
}
