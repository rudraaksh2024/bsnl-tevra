package com.graphbuilder.math.func;

public class TanFunction implements Function {
    public boolean acceptNumParam(int i) {
        return i == 1;
    }

    public String toString() {
        return "tan(x)";
    }

    public double of(double[] dArr, int i) {
        return Math.tan(dArr[0]);
    }
}
