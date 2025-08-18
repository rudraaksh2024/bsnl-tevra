package com.graphbuilder.math.func;

public class SqrtFunction implements Function {
    public boolean acceptNumParam(int i) {
        return i == 1;
    }

    public String toString() {
        return "sqrt(x)";
    }

    public double of(double[] dArr, int i) {
        return Math.sqrt(dArr[0]);
    }
}
