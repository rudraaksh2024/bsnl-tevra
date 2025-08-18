package com.graphbuilder.math.func;

public class ExpFunction implements Function {
    public boolean acceptNumParam(int i) {
        return i == 1;
    }

    public String toString() {
        return "exp(x)";
    }

    public double of(double[] dArr, int i) {
        return Math.exp(dArr[0]);
    }
}
