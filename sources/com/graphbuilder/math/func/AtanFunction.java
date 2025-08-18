package com.graphbuilder.math.func;

public class AtanFunction implements Function {
    public boolean acceptNumParam(int i) {
        return i == 1;
    }

    public String toString() {
        return "atan(x)";
    }

    public double of(double[] dArr, int i) {
        return Math.atan(dArr[0]);
    }
}
