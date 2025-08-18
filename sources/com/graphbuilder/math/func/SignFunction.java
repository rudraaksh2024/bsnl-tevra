package com.graphbuilder.math.func;

public class SignFunction implements Function {
    public boolean acceptNumParam(int i) {
        return i == 1;
    }

    public String toString() {
        return "sign(x)";
    }

    public double of(double[] dArr, int i) {
        double d = dArr[0];
        if (d > 0.0d) {
            return 1.0d;
        }
        return d < 0.0d ? -1.0d : 0.0d;
    }
}
