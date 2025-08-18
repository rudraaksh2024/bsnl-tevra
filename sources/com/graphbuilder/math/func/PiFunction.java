package com.graphbuilder.math.func;

public class PiFunction implements Function {
    public boolean acceptNumParam(int i) {
        return i == 0;
    }

    public double of(double[] dArr, int i) {
        return 3.141592653589793d;
    }

    public String toString() {
        return "pi()";
    }
}
