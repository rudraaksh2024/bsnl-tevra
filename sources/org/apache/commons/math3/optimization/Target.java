package org.apache.commons.math3.optimization;

@Deprecated
public class Target implements OptimizationData {
    private final double[] target;

    public Target(double[] dArr) {
        this.target = (double[]) dArr.clone();
    }

    public double[] getTarget() {
        return (double[]) this.target.clone();
    }
}
