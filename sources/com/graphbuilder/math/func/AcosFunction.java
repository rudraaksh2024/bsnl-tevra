package com.graphbuilder.math.func;

public class AcosFunction implements Function {
    public boolean acceptNumParam(int i) {
        return i == 1;
    }

    public String toString() {
        return "acos(x)";
    }

    public double of(double[] dArr, int i) {
        return Math.acos(dArr[0]);
    }
}
