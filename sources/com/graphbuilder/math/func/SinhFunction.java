package com.graphbuilder.math.func;

public class SinhFunction implements Function {
    public boolean acceptNumParam(int i) {
        return i == 1;
    }

    public String toString() {
        return "sinh(x)";
    }

    public double of(double[] dArr, int i) {
        return (Math.pow(2.718281828459045d, dArr[0]) - Math.pow(2.718281828459045d, -dArr[0])) / 2.0d;
    }
}
