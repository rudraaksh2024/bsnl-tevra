package com.graphbuilder.math.func;

public class LogFunction implements Function {
    public boolean acceptNumParam(int i) {
        return i == 1 || i == 2;
    }

    public String toString() {
        return "log(x):log(x, y)";
    }

    public double of(double[] dArr, int i) {
        if (i == 1) {
            return Math.log(dArr[0]) / Math.log(10.0d);
        }
        return Math.log(dArr[0]) / Math.log(dArr[1]);
    }
}
