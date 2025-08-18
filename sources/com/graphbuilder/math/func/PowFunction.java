package com.graphbuilder.math.func;

public class PowFunction implements Function {
    public boolean acceptNumParam(int i) {
        return i == 2;
    }

    public String toString() {
        return "pow(x, y)";
    }

    public double of(double[] dArr, int i) {
        return Math.pow(dArr[0], dArr[1]);
    }
}
