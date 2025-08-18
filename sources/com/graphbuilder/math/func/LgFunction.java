package com.graphbuilder.math.func;

public class LgFunction implements Function {
    public boolean acceptNumParam(int i) {
        return i == 1;
    }

    public String toString() {
        return "lg(x)";
    }

    public double of(double[] dArr, int i) {
        return Math.log(dArr[0]) / Math.log(2.0d);
    }
}
