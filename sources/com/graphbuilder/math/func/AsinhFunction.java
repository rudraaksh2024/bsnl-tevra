package com.graphbuilder.math.func;

public class AsinhFunction implements Function {
    public boolean acceptNumParam(int i) {
        return i == 1;
    }

    public String toString() {
        return "asinh(x)";
    }

    public double of(double[] dArr, int i) {
        double d = dArr[0];
        return Math.log(d + Math.sqrt((d * d) + 1.0d));
    }
}
