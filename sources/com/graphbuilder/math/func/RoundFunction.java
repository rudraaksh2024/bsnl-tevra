package com.graphbuilder.math.func;

public class RoundFunction implements Function {
    public boolean acceptNumParam(int i) {
        return i == 1;
    }

    public String toString() {
        return "round(x)";
    }

    public double of(double[] dArr, int i) {
        double d = dArr[0];
        return (d >= 9.223372036854776E18d || d <= -9.223372036854776E18d) ? d : (double) Math.round(d);
    }
}
