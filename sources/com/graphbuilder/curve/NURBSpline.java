package com.graphbuilder.curve;

public class NURBSpline extends BSpline {
    private static final ThreadLocal<SharedData> SHARED_DATA = new ThreadLocal<SharedData>() {
        /* access modifiers changed from: protected */
        public SharedData initialValue() {
            return new SharedData();
        }
    };
    private final SharedData sharedData = SHARED_DATA.get();
    private boolean useWeightVector = true;
    private ValueVector weightVector = new ValueVector(new double[]{1.0d, 1.0d, 1.0d, 1.0d}, 4);

    private static class SharedData {
        /* access modifiers changed from: private */
        public double[] nw;
        /* access modifiers changed from: private */
        public double[] weight;

        private SharedData() {
            this.nw = new double[0];
            this.weight = new double[0];
        }
    }

    public NURBSpline(ControlPath controlPath, GroupIterator groupIterator) {
        super(controlPath, groupIterator);
    }

    /* access modifiers changed from: protected */
    public void eval(double[] dArr) {
        double[] dArr2 = dArr;
        int length = dArr2.length - 1;
        double d = dArr2[length];
        int groupSize = this.gi.getGroupSize();
        double d2 = 0.0d;
        double d3 = 0.0d;
        for (int i = 0; i < groupSize; i++) {
            this.sharedData.nw[i] = N(d, i) * this.sharedData.weight[i];
            d3 += this.sharedData.nw[i];
        }
        if (d3 == 0.0d) {
            d3 = 1.0d;
        }
        int i2 = 0;
        while (i2 < length) {
            this.gi.set(0, 0);
            double d4 = d2;
            for (int i3 = 0; i3 < groupSize; i3++) {
                d4 += this.sharedData.nw[i3] * this.cp.getPoint(this.gi.next()).getLocation()[i2];
            }
            dArr2[i2] = d4 / d3;
            i2++;
            d2 = 0.0d;
        }
    }

    public ValueVector getWeightVector() {
        return this.weightVector;
    }

    public void setWeightVector(ValueVector valueVector) {
        if (valueVector != null) {
            this.weightVector = valueVector;
            return;
        }
        throw new IllegalArgumentException("Weight-vector cannot be null.");
    }

    public boolean getUseWeightVector() {
        return this.useWeightVector;
    }

    public void setUseWeightVector(boolean z) {
        this.useWeightVector = z;
    }

    public void appendTo(MultiPath multiPath) {
        int i = 0;
        if (this.gi.isInRange(0, this.cp.numPoints())) {
            int groupSize = this.gi.getGroupSize();
            if (this.sharedData.nw.length < groupSize) {
                int i2 = groupSize * 2;
                double[] unused = this.sharedData.nw = new double[i2];
                double[] unused2 = this.sharedData.weight = new double[i2];
            }
            if (!this.useWeightVector) {
                while (i < groupSize) {
                    this.sharedData.weight[i] = 1.0d;
                    i++;
                }
            } else if (this.weightVector.size() == groupSize) {
                while (i < groupSize) {
                    this.sharedData.weight[i] = this.weightVector.get(i);
                    if (this.sharedData.weight[i] >= 0.0d) {
                        i++;
                    } else {
                        throw new IllegalArgumentException("Negative weight not allowed");
                    }
                }
            } else {
                throw new IllegalArgumentException("weightVector.size(" + this.weightVector.size() + ") != group iterator size(" + groupSize + ")");
            }
            super.appendTo(multiPath);
            return;
        }
        throw new IllegalArgumentException("Group iterator not in range");
    }

    public void resetMemory() {
        super.resetMemory();
        if (this.sharedData.nw.length > 0) {
            double[] unused = this.sharedData.nw = new double[0];
            double[] unused2 = this.sharedData.weight = new double[0];
        }
    }
}
