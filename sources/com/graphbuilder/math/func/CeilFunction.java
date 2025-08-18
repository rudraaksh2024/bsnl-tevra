package com.graphbuilder.math.func;

public class CeilFunction implements Function {
    public boolean acceptNumParam(int i) {
        return i == 1;
    }

    public String toString() {
        return "ceil(x)";
    }

    public double of(double[] dArr, int i) {
        return Math.ceil(dArr[0]);
    }
}
