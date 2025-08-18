package com.graphbuilder.curve;

import com.graphbuilder.math.PascalsTriangle;

public class BezierCurve extends ParametricCurve {
    private static final ThreadLocal<SharedData> SHARED_DATA = new ThreadLocal<SharedData>() {
        /* access modifiers changed from: protected */
        public SharedData initialValue() {
            return new SharedData();
        }
    };
    private final PascalsTriangle pascalsTriangle = new PascalsTriangle();
    private int sampleLimit = 1;
    private final SharedData sharedData = SHARED_DATA.get();
    private double t_max = 1.0d;
    private double t_min = 0.0d;

    private static class SharedData {
        /* access modifiers changed from: private */
        public double[] a;

        private SharedData() {
            this.a = new double[0];
        }
    }

    public BezierCurve(ControlPath controlPath, GroupIterator groupIterator) {
        super(controlPath, groupIterator);
    }

    public void eval(double[] dArr) {
        double[] dArr2 = dArr;
        double d = dArr2[dArr2.length - 1];
        int groupSize = this.gi.getGroupSize();
        if (groupSize > this.sharedData.a.length) {
            double[] unused = this.sharedData.a = new double[(groupSize * 2)];
        }
        int i = groupSize - 1;
        double d2 = 1.0d;
        this.sharedData.a[i] = 1.0d;
        double d3 = 1.0d - d;
        for (int i2 = groupSize - 2; i2 >= 0; i2--) {
            this.sharedData.a[i2] = this.sharedData.a[i2 + 1] * d3;
        }
        this.gi.set(0, 0);
        for (int i3 = 0; i3 < groupSize; i3++) {
            double nCr = this.pascalsTriangle.nCr(i, i3);
            if (!Double.isInfinite(nCr) && !Double.isNaN(nCr)) {
                double d4 = this.sharedData.a[i3] * d2 * nCr;
                double[] location = this.cp.getPoint(this.gi.next()).getLocation();
                for (int i4 = 0; i4 < dArr2.length - 1; i4++) {
                    dArr2[i4] = dArr2[i4] + (location[i4] * d4);
                }
            }
            d2 *= d;
        }
    }

    public int getSampleLimit() {
        return this.sampleLimit;
    }

    public void setSampleLimit(int i) {
        if (i >= 0) {
            this.sampleLimit = i;
            return;
        }
        throw new IllegalArgumentException("Sample-limit >= 0 required.");
    }

    public void setInterval(double d, double d2) {
        if (d <= d2) {
            this.t_min = d;
            this.t_max = d2;
            return;
        }
        throw new IllegalArgumentException("t_min <= t_max required.");
    }

    public double t_min() {
        return this.t_min;
    }

    public double t_max() {
        return this.t_max;
    }

    public void appendTo(MultiPath multiPath) {
        if (this.gi.isInRange(0, this.cp.numPoints())) {
            int dimension = multiPath.getDimension();
            double[] dArr = new double[(dimension + 1)];
            dArr[dimension] = this.t_min;
            eval(dArr);
            if (this.connect) {
                multiPath.lineTo(dArr);
            } else {
                multiPath.moveTo(dArr);
            }
            BinaryCurveApproximationAlgorithm.genPts(this, this.t_min, this.t_max, multiPath);
            return;
        }
        throw new IllegalArgumentException("group iterator not in range");
    }

    public void resetMemory() {
        if (this.sharedData.a.length > 0) {
            double[] unused = this.sharedData.a = new double[0];
        }
    }
}
