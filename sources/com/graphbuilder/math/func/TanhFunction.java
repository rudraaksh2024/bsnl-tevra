package com.graphbuilder.math.func;

public class TanhFunction implements Function {
    public boolean acceptNumParam(int i) {
        return i == 1;
    }

    public String toString() {
        return "tanh(x)";
    }

    public double of(double[] dArr, int i) {
        double pow = Math.pow(2.718281828459045d, dArr[0] * 2.0d);
        return (pow - 1.0d) / (pow + 1.0d);
    }
}
