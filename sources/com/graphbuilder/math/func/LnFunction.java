package com.graphbuilder.math.func;

public class LnFunction implements Function {
    public boolean acceptNumParam(int i) {
        return i == 1;
    }

    public String toString() {
        return "ln(x)";
    }

    public double of(double[] dArr, int i) {
        return Math.log(dArr[0]);
    }
}
