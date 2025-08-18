package com.graphbuilder.math.func;

public class CosFunction implements Function {
    public boolean acceptNumParam(int i) {
        return i == 1;
    }

    public String toString() {
        return "cos(x)";
    }

    public double of(double[] dArr, int i) {
        return Math.cos(dArr[0]);
    }
}
