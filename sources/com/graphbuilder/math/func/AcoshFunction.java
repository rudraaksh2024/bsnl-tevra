package com.graphbuilder.math.func;

public class AcoshFunction implements Function {
    public boolean acceptNumParam(int i) {
        return i == 1;
    }

    public String toString() {
        return "acosh(x)";
    }

    public double of(double[] dArr, int i) {
        return Math.log(Math.sqrt((dArr[0] + 1.0d) / 2.0d) + Math.sqrt((dArr[0] - 1.0d) / 2.0d)) * 2.0d;
    }
}
