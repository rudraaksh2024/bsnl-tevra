package com.graphbuilder.math.func;

public class RandFunction implements Function {
    public boolean acceptNumParam(int i) {
        return i == 0;
    }

    public String toString() {
        return "rand()";
    }

    public double of(double[] dArr, int i) {
        return Math.random();
    }
}
