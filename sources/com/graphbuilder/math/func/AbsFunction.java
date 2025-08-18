package com.graphbuilder.math.func;

public class AbsFunction implements Function {
    public boolean acceptNumParam(int i) {
        return i == 1;
    }

    public String toString() {
        return "abs(x)";
    }

    public double of(double[] dArr, int i) {
        return Math.abs(dArr[0]);
    }
}
