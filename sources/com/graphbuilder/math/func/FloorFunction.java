package com.graphbuilder.math.func;

public class FloorFunction implements Function {
    public boolean acceptNumParam(int i) {
        return i == 1;
    }

    public String toString() {
        return "floor(x)";
    }

    public double of(double[] dArr, int i) {
        return Math.floor(dArr[0]);
    }
}
