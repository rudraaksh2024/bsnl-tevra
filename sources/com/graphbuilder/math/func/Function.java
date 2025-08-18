package com.graphbuilder.math.func;

public interface Function {
    boolean acceptNumParam(int i);

    double of(double[] dArr, int i);
}
