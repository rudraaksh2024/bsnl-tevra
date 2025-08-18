package com.graphbuilder.curve;

public class CatmullRomSpline extends ParametricCurve {
    private static final ThreadLocal<SharedData> SHARED_DATA = new ThreadLocal<SharedData>() {
        /* access modifiers changed from: protected */
        public SharedData initialValue() {
            return new SharedData();
        }
    };
    private final SharedData sharedData = SHARED_DATA.get();

    public int getSampleLimit() {
        return 1;
    }

    private static class SharedData {
        /* access modifiers changed from: private */
        public double[][] pt;

        private SharedData() {
            this.pt = new double[4][];
        }
    }

    public CatmullRomSpline(ControlPath controlPath, GroupIterator groupIterator) {
        super(controlPath, groupIterator);
    }

    /* access modifiers changed from: protected */
    public void eval(double[] dArr) {
        double[] dArr2 = dArr;
        double d = dArr2[dArr2.length - 1];
        double d2 = d * d;
        double d3 = d2 * d;
        for (int i = 0; i < dArr2.length - 1; i++) {
            dArr2[i] = (((((this.sharedData.pt[3][i] - this.sharedData.pt[0][i]) + ((this.sharedData.pt[1][i] - this.sharedData.pt[2][i]) * 3.0d)) * d3) + (((((this.sharedData.pt[0][i] + (this.sharedData.pt[2][i] * 2.0d)) * 2.0d) - (this.sharedData.pt[1][i] * 5.0d)) - this.sharedData.pt[3][i]) * d2) + ((this.sharedData.pt[2][i] - this.sharedData.pt[0][i]) * d)) * 0.5d) + this.sharedData.pt[1][i];
        }
    }

    public void appendTo(MultiPath multiPath) {
        if (!this.gi.isInRange(0, this.cp.numPoints())) {
            throw new IllegalArgumentException("Group iterator not in range");
        } else if (this.gi.getGroupSize() >= 4) {
            this.gi.set(0, 0);
            for (int i = 0; i < 4; i++) {
                this.sharedData.pt[i] = this.cp.getPoint(this.gi.next()).getLocation();
            }
            double[] dArr = new double[(multiPath.getDimension() + 1)];
            eval(dArr);
            if (this.connect) {
                multiPath.lineTo(dArr);
            } else {
                multiPath.moveTo(dArr);
            }
            this.gi.set(0, 0);
            while (true) {
                int index_i = this.gi.index_i();
                int count_j = this.gi.count_j();
                int i2 = 0;
                while (i2 < 4) {
                    if (this.gi.hasNext()) {
                        this.sharedData.pt[i2] = this.cp.getPoint(this.gi.next()).getLocation();
                        i2++;
                    } else {
                        return;
                    }
                }
                this.gi.set(index_i, count_j);
                this.gi.next();
                BinaryCurveApproximationAlgorithm.genPts(this, 0.0d, 1.0d, multiPath);
            }
        } else {
            throw new IllegalArgumentException("Group iterator size < 4");
        }
    }
}
