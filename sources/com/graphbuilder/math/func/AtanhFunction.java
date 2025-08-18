package com.graphbuilder.math.func;

public class AtanhFunction implements Function {
    public boolean acceptNumParam(int i) {
        return i == 1;
    }

    public String toString() {
        return "atanh(x)";
    }

    public double of(double[] dArr, int i) {
        return (Math.log(dArr[0] + 1.0d) - Math.log(1.0d - dArr[0])) / 2.0d;
    }
}
