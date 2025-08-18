package com.graphbuilder.curve;

public class NaturalCubicSpline extends ParametricCurve {
    private static final ThreadLocal<SharedData> SHARED_DATA = new ThreadLocal<SharedData>() {
        /* access modifiers changed from: protected */
        public SharedData initialValue() {
            return new SharedData();
        }
    };
    private boolean closed = false;
    private final SharedData sharedData = SHARED_DATA.get();

    public int getSampleLimit() {
        return 1;
    }

    private static class SharedData {
        /* access modifiers changed from: private */
        public int ci;
        /* access modifiers changed from: private */
        public double[][] data;
        /* access modifiers changed from: private */
        public double[][] pt;

        private SharedData() {
            this.pt = new double[0][];
            this.data = new double[0][];
            this.ci = 0;
        }
    }

    public NaturalCubicSpline(ControlPath controlPath, GroupIterator groupIterator) {
        super(controlPath, groupIterator);
    }

    /* access modifiers changed from: protected */
    public void eval(double[] dArr) {
        int length = dArr.length - 1;
        double d = dArr[length];
        double d2 = d * d;
        double d3 = d2 * d;
        int i = 0;
        int i2 = 0;
        while (i < length) {
            int i3 = i2 + 1;
            int i4 = i3 + 1;
            int i5 = i4 + 1;
            dArr[i] = this.sharedData.data[i2][this.sharedData.ci] + (this.sharedData.data[i3][this.sharedData.ci] * d) + (this.sharedData.data[i4][this.sharedData.ci] * d2) + (this.sharedData.data[i5][this.sharedData.ci] * d3);
            i++;
            i2 = i5 + 1;
        }
    }

    private void precalc(int i, int i2, boolean z) {
        int i3 = i2;
        int i4 = i - 1;
        int i5 = i3 * 4;
        double[] dArr = this.sharedData.data[i5];
        double[] dArr2 = this.sharedData.data[i5 + 1];
        double[] dArr3 = this.sharedData.data[i5 + 2];
        double d = 4.0d;
        double d2 = 3.0d;
        char c = 1;
        if (z) {
            double[] dArr4 = this.sharedData.data[i5 + 3];
            int i6 = 0;
            int i7 = 0;
            while (i6 < i3) {
                dArr[c] = 0.25d;
                dArr4[c] = 0.25d;
                dArr2[0] = 0.75d * (this.sharedData.pt[c][i6] - this.sharedData.pt[i4][i6]);
                int i8 = i4 - 1;
                double d3 = (this.sharedData.pt[0][i6] - this.sharedData.pt[i8][i6]) * d2;
                double d4 = d;
                int i9 = 1;
                double d5 = 1.0d;
                while (i9 < i4) {
                    int i10 = i9 + 1;
                    double d6 = 1.0d / (d - dArr[i9]);
                    dArr[i10] = d6;
                    dArr4[i10] = (-d6) * dArr4[i9];
                    int i11 = i9 - 1;
                    dArr2[i9] = d6 * (((this.sharedData.pt[i10][i6] - this.sharedData.pt[i11][i6]) * 3.0d) - dArr2[i11]);
                    d4 -= dArr4[i9] * d5;
                    d3 -= dArr2[i11] * d5;
                    d5 *= -dArr[i9];
                    int i12 = i2;
                    i9 = i10;
                    d = 4.0d;
                }
                double d7 = d5 + 1.0d;
                double d8 = d4 - ((dArr[i4] + dArr4[i4]) * d7);
                double d9 = d3 - (d7 * dArr2[i8]);
                dArr2[i4] = d9;
                double d10 = d9 / d8;
                dArr3[i4] = d10;
                dArr3[i8] = dArr2[i8] - ((dArr[i4] + dArr4[i4]) * d10);
                for (int i13 = i4 - 2; i13 >= 0; i13--) {
                    int i14 = i13 + 1;
                    dArr3[i13] = (dArr2[i13] - (dArr[i14] * dArr3[i14])) - (dArr4[i14] * dArr3[i4]);
                }
                int i15 = i7 + 1;
                double[] dArr5 = this.sharedData.data[i7];
                int i16 = i15 + 1;
                double[] dArr6 = this.sharedData.data[i15];
                int i17 = i16 + 1;
                double[] dArr7 = this.sharedData.data[i16];
                i7 = i17 + 1;
                double[] dArr8 = this.sharedData.data[i17];
                int i18 = 0;
                while (i18 < i4) {
                    dArr5[i18] = this.sharedData.pt[i18][i6];
                    dArr6[i18] = dArr3[i18];
                    int i19 = i18 + 1;
                    dArr7[i18] = (((this.sharedData.pt[i19][i6] - this.sharedData.pt[i18][i6]) * 3.0d) - (dArr3[i18] * 2.0d)) - dArr3[i19];
                    dArr8[i18] = ((this.sharedData.pt[i18][i6] - this.sharedData.pt[i19][i6]) * 2.0d) + dArr3[i18] + dArr3[i19];
                    i18 = i19;
                }
                dArr5[i4] = this.sharedData.pt[i4][i6];
                dArr6[i4] = dArr3[i4];
                dArr7[i4] = (((this.sharedData.pt[0][i6] - this.sharedData.pt[i4][i6]) * 3.0d) - (dArr3[i4] * 2.0d)) - dArr3[0];
                dArr8[i4] = ((this.sharedData.pt[i4][i6] - this.sharedData.pt[0][i6]) * 2.0d) + dArr3[i4] + dArr3[0];
                i6++;
                i3 = i2;
                d = 4.0d;
                d2 = 3.0d;
                c = 1;
            }
            return;
        }
        int i20 = i2;
        int i21 = 0;
        int i22 = 0;
        while (i21 < i20) {
            dArr[0] = 0.5d;
            for (int i23 = 1; i23 < i4; i23++) {
                dArr[i23] = 1.0d / (4.0d - dArr[i23 - 1]);
            }
            int i24 = i4 - 1;
            dArr[i4] = 1.0d / (2.0d - dArr[i24]);
            dArr2[0] = dArr[0] * (this.sharedData.pt[1][i21] - this.sharedData.pt[0][i21]) * 3.0d;
            int i25 = 1;
            while (i25 < i4) {
                int i26 = i25 + 1;
                int i27 = i25 - 1;
                dArr2[i25] = dArr[i25] * (((this.sharedData.pt[i26][i21] - this.sharedData.pt[i27][i21]) * 3.0d) - dArr2[i27]);
                i25 = i26;
            }
            double d11 = dArr[i4] * (((this.sharedData.pt[i4][i21] - this.sharedData.pt[i24][i21]) * 3.0d) - dArr2[i24]);
            dArr2[i4] = d11;
            dArr3[i4] = d11;
            while (i24 >= 0) {
                dArr3[i24] = dArr2[i24] - (dArr[i24] * dArr3[i24 + 1]);
                i24--;
            }
            int i28 = i22 + 1;
            double[] dArr9 = this.sharedData.data[i22];
            int i29 = i28 + 1;
            double[] dArr10 = this.sharedData.data[i28];
            int i30 = i29 + 1;
            double[] dArr11 = this.sharedData.data[i29];
            int i31 = i30 + 1;
            double[] dArr12 = this.sharedData.data[i30];
            int i32 = 0;
            while (i32 < i4) {
                dArr9[i32] = this.sharedData.pt[i32][i21];
                dArr10[i32] = dArr3[i32];
                int i33 = i32 + 1;
                dArr11[i32] = (((this.sharedData.pt[i33][i21] - this.sharedData.pt[i32][i21]) * 3.0d) - (dArr3[i32] * 2.0d)) - dArr3[i33];
                dArr12[i32] = ((this.sharedData.pt[i32][i21] - this.sharedData.pt[i33][i21]) * 2.0d) + dArr3[i32] + dArr3[i33];
                i32 = i33;
            }
            dArr9[i4] = this.sharedData.pt[i4][i21];
            dArr10[i4] = 0.0d;
            dArr11[i4] = 0.0d;
            dArr12[i4] = 0.0d;
            i21++;
            i22 = i31;
        }
    }

    public void setClosed(boolean z) {
        this.closed = z;
    }

    public boolean getClosed() {
        return this.closed;
    }

    public void appendTo(MultiPath multiPath) {
        if (this.gi.isInRange(0, this.cp.numPoints())) {
            int groupSize = this.gi.getGroupSize();
            if (groupSize >= 2) {
                int dimension = multiPath.getDimension();
                int i = (dimension * 4) + 3 + 1;
                if (this.sharedData.data.length < i) {
                    double[][] dArr = new double[i][];
                    for (int i2 = 0; i2 < this.sharedData.data.length; i2++) {
                        dArr[i2] = this.sharedData.data[i2];
                    }
                    double[][] unused = this.sharedData.data = dArr;
                }
                if (this.sharedData.pt.length < groupSize) {
                    int i3 = groupSize * 2;
                    double[][] unused2 = this.sharedData.pt = new double[i3][];
                    for (int i4 = 0; i4 < this.sharedData.data.length; i4++) {
                        this.sharedData.data[i4] = new double[i3];
                    }
                }
                this.gi.set(0, 0);
                for (int i5 = 0; i5 < groupSize; i5++) {
                    this.sharedData.pt[i5] = this.cp.getPoint(this.gi.next()).getLocation();
                }
                precalc(groupSize, dimension, this.closed);
                int unused3 = this.sharedData.ci = 0;
                double[] dArr2 = new double[(dimension + 1)];
                eval(dArr2);
                if (this.connect) {
                    multiPath.lineTo(dArr2);
                } else {
                    multiPath.moveTo(dArr2);
                }
                for (int i6 = 0; i6 < groupSize; i6++) {
                    int unused4 = this.sharedData.ci = i6;
                    BinaryCurveApproximationAlgorithm.genPts(this, 0.0d, 1.0d, multiPath);
                }
                return;
            }
            throw new IllegalArgumentException("Group iterator size < 2");
        }
        throw new IllegalArgumentException("Group iterator not in range");
    }

    public void resetMemory() {
        if (this.sharedData.pt.length > 0) {
            double[][] unused = this.sharedData.pt = new double[0][];
        }
        if (this.sharedData.data.length > 0) {
            double[][] unused2 = this.sharedData.data = new double[0][];
        }
    }
}
