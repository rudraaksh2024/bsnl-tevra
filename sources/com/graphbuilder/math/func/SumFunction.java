package com.graphbuilder.math.func;

public class SumFunction implements Function {
    public boolean acceptNumParam(int i) {
        return i > 0;
    }

    public String toString() {
        return "sum(x1, x2, ..., xn)";
    }

    public double of(double[] dArr, int i) {
        double d = 0.0d;
        for (int i2 = 0; i2 < i; i2++) {
            d += dArr[i2];
        }
        return d;
    }
}
