package com.graphbuilder.math;

public final class PascalsTriangle {
    private static final ThreadLocal<SharedData> SHARED_DATA = new ThreadLocal<SharedData>() {
        /* access modifiers changed from: protected */
        public SharedData initialValue() {
            return new SharedData();
        }
    };
    private final SharedData sharedData = SHARED_DATA.get();

    private static class SharedData {
        /* access modifiers changed from: private */
        public double[][] pt;

        private SharedData() {
            this.pt = new double[][]{new double[]{1.0d}};
        }
    }

    public double nCr(int i, int i2) {
        double[][] dArr;
        if (i < 0 || i2 < 0 || i2 > i) {
            return 0.0d;
        }
        if (i >= this.sharedData.pt.length) {
            int length = this.sharedData.pt.length * 2;
            double[][] dArr2 = null;
            if (i > length) {
                dArr = new double[(i + 1)][];
            } else {
                dArr = new double[(length + 1)][];
            }
            for (int i3 = 0; i3 < this.sharedData.pt.length; i3++) {
                dArr[i3] = this.sharedData.pt[i3];
            }
            for (int length2 = this.sharedData.pt.length; length2 < dArr.length; length2++) {
                double[] dArr3 = new double[((length2 / 2) + 1)];
                dArr[length2] = dArr3;
                dArr3[0] = 1.0d;
                int i4 = 1;
                while (true) {
                    double[] dArr4 = dArr[length2];
                    if (i4 >= dArr4.length) {
                        break;
                    }
                    double[] dArr5 = dArr[length2 - 1];
                    double d = dArr5[i4 - 1];
                    dArr4[i4] = i4 < dArr5.length ? d + dArr5[i4] : d * 2.0d;
                    i4++;
                }
            }
            double[][] unused = this.sharedData.pt = dArr;
        }
        if (i2 * 2 > i) {
            i2 = i - i2;
        }
        return this.sharedData.pt[i][i2];
    }

    public void reset() {
        double[][] unused = this.sharedData.pt = new double[][]{new double[]{1.0d}};
    }
}
