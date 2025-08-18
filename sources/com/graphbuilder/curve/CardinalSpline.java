package com.graphbuilder.curve;

public class CardinalSpline extends ParametricCurve {
    private static final ThreadLocal<SharedData> SHARED_DATA = new ThreadLocal<SharedData>() {
        /* access modifiers changed from: protected */
        public SharedData initialValue() {
            return new SharedData();
        }
    };
    private double alpha = 0.5d;
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

    public CardinalSpline(ControlPath controlPath, GroupIterator groupIterator) {
        super(controlPath, groupIterator);
    }

    /* access modifiers changed from: protected */
    public void eval(double[] dArr) {
        double[] dArr2 = dArr;
        double d = dArr2[dArr2.length - 1];
        double d2 = d * d;
        double d3 = d2 * d;
        double d4 = 3.0d * d2;
        double d5 = ((d3 * 2.0d) - d4) + 1.0d;
        double d6 = (-2.0d * d3) + d4;
        double d7 = this.alpha;
        double d8 = ((d3 - (2.0d * d2)) + d) * d7;
        double d9 = d7 * (d3 - d2);
        for (int i = 0; i < dArr2.length - 1; i++) {
            dArr2[i] = (this.sharedData.pt[1][i] * d5) + (this.sharedData.pt[2][i] * d6) + ((this.sharedData.pt[2][i] - this.sharedData.pt[0][i]) * d8) + ((this.sharedData.pt[3][i] - this.sharedData.pt[1][i]) * d9);
        }
    }

    public double getAlpha() {
        return this.alpha;
    }

    public void setAlpha(double d) {
        this.alpha = d;
    }

    public void appendTo(MultiPath multiPath) {
        if (!this.gi.isInRange(0, this.cp.numPoints())) {
            throw new IllegalArgumentException("group iterator not in range");
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
            throw new IllegalArgumentException("more than 4 groups required");
        }
    }
}
