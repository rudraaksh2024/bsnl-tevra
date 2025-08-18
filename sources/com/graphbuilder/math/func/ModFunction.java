package com.graphbuilder.math.func;

public class ModFunction implements Function {
    public boolean acceptNumParam(int i) {
        return i == 2;
    }

    public String toString() {
        return "mod(x, y)";
    }

    public double of(double[] dArr, int i) {
        return dArr[0] % dArr[1];
    }
}
