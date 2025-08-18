package com.graphbuilder.math.func;

public class AsinFunction implements Function {
    public boolean acceptNumParam(int i) {
        return i == 1;
    }

    public String toString() {
        return "asin(x)";
    }

    public double of(double[] dArr, int i) {
        return Math.asin(dArr[0]);
    }
}
