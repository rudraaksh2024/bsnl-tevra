package com.graphbuilder.math.func;

public class SinFunction implements Function {
    public boolean acceptNumParam(int i) {
        return i == 1;
    }

    public String toString() {
        return "sin(x)";
    }

    public double of(double[] dArr, int i) {
        return Math.sin(dArr[0]);
    }
}
