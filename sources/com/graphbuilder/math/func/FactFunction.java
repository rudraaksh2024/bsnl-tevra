package com.graphbuilder.math.func;

public class FactFunction implements Function {
    public boolean acceptNumParam(int i) {
        return i == 1;
    }

    public String toString() {
        return "fact(n)";
    }

    public double of(double[] dArr, int i) {
        double d = 1.0d;
        for (int i2 = (int) dArr[0]; i2 > 1; i2--) {
            d *= (double) i2;
        }
        return d;
    }
}
