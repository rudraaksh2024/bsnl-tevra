package com.graphbuilder.curve;

public class CubicBSpline extends ParametricCurve {
    private static final ThreadLocal<SharedData> SHARED_DATA = new ThreadLocal<SharedData>() {
        /* access modifiers changed from: protected */
        public SharedData initialValue() {
            return new SharedData();
        }
    };
    private boolean interpolateEndpoints = false;
    private final SharedData sharedData = SHARED_DATA.get();

    public int getSampleLimit() {
        return 1;
    }

    private static class SharedData {
        /* access modifiers changed from: private */
        public double[] b;
        /* access modifiers changed from: private */
        public int numPoints;
        /* access modifiers changed from: private */
        public double[][] pt;
        /* access modifiers changed from: private */
        public int section;

        private SharedData() {
            this.section = 0;
            this.numPoints = 0;
            this.pt = new double[4][];
            this.b = new double[4];
        }

        static /* synthetic */ int access$308(SharedData sharedData) {
            int i = sharedData.section;
            sharedData.section = i + 1;
            return i;
        }
    }

    public CubicBSpline(ControlPath controlPath, GroupIterator groupIterator) {
        super(controlPath, groupIterator);
    }

    /* access modifiers changed from: protected */
    public void eval(double[] dArr) {
        double[] dArr2 = dArr;
        double d = dArr2[dArr2.length - 1];
        double d2 = d * d;
        double d3 = d2 * d;
        double d4 = 1.0d - d;
        double d5 = d4 * d4;
        double d6 = d5 * d4;
        if (this.sharedData.numPoints == 4) {
            this.sharedData.b[0] = d6;
            this.sharedData.b[1] = d5 * 3.0d * d;
            this.sharedData.b[2] = d4 * 3.0d * d2;
            this.sharedData.b[3] = d3;
        } else if (this.sharedData.numPoints == 5) {
            if (this.sharedData.section == 0) {
                this.sharedData.b[0] = d6;
                this.sharedData.b[1] = (((7.0d * d3) / 4.0d) - ((9.0d * d2) / 2.0d)) + (d * 3.0d);
                this.sharedData.b[2] = (-d3) + ((d2 * 3.0d) / 2.0d);
                this.sharedData.b[3] = d3 / 4.0d;
            } else {
                this.sharedData.b[0] = d6 / 4.0d;
                this.sharedData.b[1] = (-d6) + ((d5 * 3.0d) / 2.0d);
                this.sharedData.b[2] = (((d6 * 7.0d) / 4.0d) - ((d5 * 9.0d) / 2.0d)) + (d4 * 3.0d);
                this.sharedData.b[3] = d3;
            }
        } else if (this.sharedData.numPoints == 6) {
            if (this.sharedData.section == 0) {
                this.sharedData.b[0] = d6;
                this.sharedData.b[1] = (((7.0d * d3) / 4.0d) - ((9.0d * d2) / 2.0d)) + (d * 3.0d);
                this.sharedData.b[2] = ((-11.0d * d3) / 12.0d) + ((d2 * 3.0d) / 2.0d);
                this.sharedData.b[3] = d3 / 6.0d;
            } else if (this.sharedData.section == 1) {
                this.sharedData.b[0] = d6 / 4.0d;
                this.sharedData.b[1] = (((7.0d * d3) / 12.0d) - ((5.0d * d2) / 4.0d)) + (d / 4.0d) + 0.5833333333333334d;
                this.sharedData.b[2] = ((-7.0d * d3) / 12.0d) + (d2 / 2.0d) + (d / 2.0d) + 0.16666666666666666d;
                this.sharedData.b[3] = d3 / 4.0d;
            } else {
                this.sharedData.b[0] = d6 / 6.0d;
                this.sharedData.b[1] = ((-11.0d * d6) / 12.0d) + ((d5 * 3.0d) / 2.0d);
                this.sharedData.b[2] = (((d6 * 7.0d) / 4.0d) - ((d5 * 9.0d) / 2.0d)) + (d4 * 3.0d);
                this.sharedData.b[3] = d3;
            }
        } else if (this.sharedData.section == 0) {
            this.sharedData.b[0] = d6;
            this.sharedData.b[1] = (((7.0d * d3) / 4.0d) - ((9.0d * d2) / 2.0d)) + (d * 3.0d);
            this.sharedData.b[2] = ((-11.0d * d3) / 12.0d) + ((d2 * 3.0d) / 2.0d);
            this.sharedData.b[3] = d3 / 6.0d;
        } else if (this.sharedData.section == 1) {
            this.sharedData.b[0] = d6 / 4.0d;
            this.sharedData.b[1] = (((7.0d * d3) / 12.0d) - ((5.0d * d2) / 4.0d)) + (d / 4.0d) + 0.5833333333333334d;
            this.sharedData.b[2] = ((-d3) / 2.0d) + (d2 / 2.0d) + (d / 2.0d) + 0.16666666666666666d;
            this.sharedData.b[3] = d3 / 6.0d;
        } else if (this.sharedData.section == 2) {
            this.sharedData.b[0] = d6 / 6.0d;
            this.sharedData.b[1] = ((d3 / 2.0d) - d2) + 0.6666666666666666d;
            this.sharedData.b[2] = ((((-d3) + d2) + d) / 2.0d) + 0.16666666666666666d;
            this.sharedData.b[3] = d3 / 6.0d;
        } else if (this.sharedData.section == 3) {
            this.sharedData.b[0] = d6 / 6.0d;
            this.sharedData.b[1] = ((-d6) / 2.0d) + (d5 / 2.0d) + (d4 / 2.0d) + 0.16666666666666666d;
            this.sharedData.b[2] = (((d6 * 7.0d) / 12.0d) - ((d5 * 5.0d) / 4.0d)) + (d4 / 4.0d) + 0.5833333333333334d;
            this.sharedData.b[3] = d3 / 4.0d;
        } else {
            this.sharedData.b[0] = d6 / 6.0d;
            this.sharedData.b[1] = ((-11.0d * d6) / 12.0d) + ((d5 * 3.0d) / 2.0d);
            this.sharedData.b[2] = (((d6 * 7.0d) / 4.0d) - ((d5 * 9.0d) / 2.0d)) + (d4 * 3.0d);
            this.sharedData.b[3] = d3;
        }
        for (int i = 0; i < 4; i++) {
            for (int i2 = 0; i2 < dArr2.length - 1; i2++) {
                dArr2[i2] = dArr2[i2] + (this.sharedData.pt[i][i2] * this.sharedData.b[i]);
            }
        }
    }

    public void setInterpolateEndpoints(boolean z) {
        this.interpolateEndpoints = z;
    }

    public boolean getInterpolateEndpoints() {
        return this.interpolateEndpoints;
    }

    public void appendTo(MultiPath multiPath) {
        if (this.gi.isInRange(0, this.cp.numPoints())) {
            int groupSize = this.gi.getGroupSize();
            if (groupSize >= 4) {
                if (this.interpolateEndpoints) {
                    int unused = this.sharedData.numPoints = groupSize;
                    int unused2 = this.sharedData.section = 0;
                } else {
                    int unused3 = this.sharedData.numPoints = -1;
                    int unused4 = this.sharedData.section = 2;
                }
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
                int i2 = 3;
                int i3 = 0;
                int i4 = 0;
                while (true) {
                    BinaryCurveApproximationAlgorithm.genPts(this, 0.0d, 1.0d, multiPath);
                    i2++;
                    if (i2 != groupSize) {
                        this.gi.set(i3, i4);
                        this.gi.next();
                        i3 = this.gi.index_i();
                        i4 = this.gi.count_j();
                        for (int i5 = 0; i5 < 4; i5++) {
                            this.sharedData.pt[i5] = this.cp.getPoint(this.gi.next()).getLocation();
                        }
                        if (this.interpolateEndpoints) {
                            if (groupSize < 7) {
                                SharedData.access$308(this.sharedData);
                            } else {
                                if (this.sharedData.section != 2) {
                                    SharedData.access$308(this.sharedData);
                                }
                                if (this.sharedData.section == 2 && i2 == groupSize - 2) {
                                    SharedData.access$308(this.sharedData);
                                }
                            }
                        }
                    } else {
                        return;
                    }
                }
            } else {
                throw new IllegalArgumentException("Group iterator size < 4");
            }
        } else {
            throw new IllegalArgumentException("Group iterator not in range");
        }
    }
}
