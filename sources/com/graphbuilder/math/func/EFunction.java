package com.graphbuilder.math.func;

public class EFunction implements Function {
    public boolean acceptNumParam(int i) {
        return i == 0;
    }

    public double of(double[] dArr, int i) {
        return 2.718281828459045d;
    }

    public String toString() {
        return "e()";
    }
}
