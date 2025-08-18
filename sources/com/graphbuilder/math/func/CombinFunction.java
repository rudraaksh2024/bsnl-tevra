package com.graphbuilder.math.func;

import com.graphbuilder.math.PascalsTriangle;

public class CombinFunction implements Function {
    private final PascalsTriangle pascalsTriangle = new PascalsTriangle();

    public boolean acceptNumParam(int i) {
        return i == 2;
    }

    public String toString() {
        return "combin(n, r)";
    }

    public double of(double[] dArr, int i) {
        return this.pascalsTriangle.nCr((int) dArr[0], (int) dArr[1]);
    }
}
