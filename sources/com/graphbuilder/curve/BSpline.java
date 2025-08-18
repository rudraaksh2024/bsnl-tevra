package com.graphbuilder.curve;

public class BSpline extends ParametricCurve {
    public static final int NON_UNIFORM = 2;
    private static final ThreadLocal<SharedData> SHARED_DATA = new ThreadLocal<SharedData>() {
        /* access modifiers changed from: protected */
        public SharedData initialValue() {
            return new SharedData();
        }
    };
    public static final int UNIFORM_CLAMPED = 0;
    public static final int UNIFORM_UNCLAMPED = 1;
    private int degree = 4;
    private ValueVector knotVector = new ValueVector(new double[]{0.0d, 0.0d, 0.0d, 0.0d, 1.0d, 1.0d, 1.0d, 1.0d}, 8);
    private int knotVectorType = 0;
    private int sampleLimit = 1;
    private final SharedData sharedData = SHARED_DATA.get();
    private double t_max = 1.0d;
    private double t_min = 0.0d;
    private boolean useDefaultInterval = true;

    private static class SharedData {
        /* access modifiers changed from: private */
        public int[] a;
        /* access modifiers changed from: private */
        public int[] c;
        /* access modifiers changed from: private */
        public double[] knot;

        private SharedData() {
            this.a = new int[0];
            this.c = new int[0];
            this.knot = new double[0];
        }
    }

    public BSpline(ControlPath controlPath, GroupIterator groupIterator) {
        super(controlPath, groupIterator);
    }

    /* access modifiers changed from: protected */
    public void eval(double[] dArr) {
        int length = dArr.length - 1;
        double d = dArr[length];
        int groupSize = this.gi.getGroupSize();
        this.gi.set(0, 0);
        for (int i = 0; i < groupSize; i++) {
            double N = N(d, i);
            double[] location = this.cp.getPoint(this.gi.next()).getLocation();
            for (int i2 = 0; i2 < length; i2++) {
                dArr[i2] = dArr[i2] + (location[i2] * N);
            }
        }
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

    public int getDegree() {
        return this.degree - 1;
    }

    public void setDegree(int i) {
        if (i > 0) {
            this.degree = i + 1;
            return;
        }
        throw new IllegalArgumentException("Degree > 0 required.");
    }

    public ValueVector getKnotVector() {
        return this.knotVector;
    }

    public void setKnotVector(ValueVector valueVector) {
        if (valueVector != null) {
            this.knotVector = valueVector;
            return;
        }
        throw new IllegalArgumentException("Knot-vector cannot be null.");
    }

    public boolean getUseDefaultInterval() {
        return this.useDefaultInterval;
    }

    public void setUseDefaultInterval(boolean z) {
        this.useDefaultInterval = z;
    }

    public int getKnotVectorType() {
        return this.knotVectorType;
    }

    public void setKnotVectorType(int i) {
        if (i < 0 || i > 2) {
            throw new IllegalArgumentException("Unknown knot-vector type.");
        }
        this.knotVectorType = i;
    }

    /* JADX WARNING: Removed duplicated region for block: B:44:0x0148  */
    /* JADX WARNING: Removed duplicated region for block: B:47:0x0170  */
    /* JADX WARNING: Removed duplicated region for block: B:48:0x0174  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void appendTo(com.graphbuilder.curve.MultiPath r17) {
        /*
            r16 = this;
            r0 = r16
            r5 = r17
            com.graphbuilder.curve.GroupIterator r1 = r0.gi
            com.graphbuilder.curve.ControlPath r2 = r0.cp
            int r2 = r2.numPoints()
            r3 = 0
            boolean r1 = r1.isInRange(r3, r2)
            if (r1 == 0) goto L_0x0187
            com.graphbuilder.curve.GroupIterator r1 = r0.gi
            int r1 = r1.getGroupSize()
            int r2 = r0.degree
            int r4 = r1 - r2
            if (r4 < 0) goto L_0x017f
            int r1 = r1 + r2
            com.graphbuilder.curve.BSpline$SharedData r2 = r0.sharedData
            double[] r2 = r2.knot
            int r2 = r2.length
            if (r2 >= r1) goto L_0x0032
            com.graphbuilder.curve.BSpline$SharedData r2 = r0.sharedData
            int r6 = r1 * 2
            double[] r6 = new double[r6]
            double[] unused = r2.knot = r6
        L_0x0032:
            double r6 = r0.t_min
            double r8 = r0.t_max
            int r2 = r0.knotVectorType
            r10 = 2
            r11 = 1
            if (r2 != r10) goto L_0x00c6
            com.graphbuilder.curve.ValueVector r2 = r0.knotVector
            int r2 = r2.size()
            if (r2 != r1) goto L_0x00a1
            com.graphbuilder.curve.BSpline$SharedData r2 = r0.sharedData
            double[] r2 = r2.knot
            com.graphbuilder.curve.ValueVector r4 = r0.knotVector
            double r12 = r4.get(r3)
            r2[r3] = r12
            r2 = r11
        L_0x0053:
            if (r2 >= r1) goto L_0x013b
            com.graphbuilder.curve.BSpline$SharedData r3 = r0.sharedData
            double[] r3 = r3.knot
            com.graphbuilder.curve.ValueVector r4 = r0.knotVector
            double r12 = r4.get(r2)
            r3[r2] = r12
            com.graphbuilder.curve.BSpline$SharedData r3 = r0.sharedData
            double[] r3 = r3.knot
            r3 = r3[r2]
            com.graphbuilder.curve.BSpline$SharedData r12 = r0.sharedData
            double[] r12 = r12.knot
            int r13 = r2 + -1
            r12 = r12[r13]
            int r3 = (r3 > r12 ? 1 : (r3 == r12 ? 0 : -1))
            if (r3 < 0) goto L_0x007c
            int r2 = r2 + 1
            goto L_0x0053
        L_0x007c:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r3 = "Knot not in sorted order! (knot["
            r1.<init>(r3)
            java.lang.StringBuilder r1 = r1.append(r2)
            java.lang.String r3 = "] < knot["
            java.lang.StringBuilder r1 = r1.append(r3)
            java.lang.StringBuilder r1 = r1.append(r2)
            java.lang.String r2 = "-1])"
            java.lang.StringBuilder r1 = r1.append(r2)
            java.lang.String r1 = r1.toString()
            r0.<init>(r1)
            throw r0
        L_0x00a1:
            java.lang.IllegalArgumentException r2 = new java.lang.IllegalArgumentException
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            java.lang.String r4 = "knotVector.size("
            r3.<init>(r4)
            com.graphbuilder.curve.ValueVector r0 = r0.knotVector
            int r0 = r0.size()
            java.lang.StringBuilder r0 = r3.append(r0)
            java.lang.String r3 = ") != "
            java.lang.StringBuilder r0 = r0.append(r3)
            java.lang.StringBuilder r0 = r0.append(r1)
            java.lang.String r0 = r0.toString()
            r2.<init>(r0)
            throw r2
        L_0x00c6:
            r12 = 4607182418800017408(0x3ff0000000000000, double:1.0)
            if (r2 != r11) goto L_0x00f3
            int r2 = r1 + -1
            double r14 = (double) r2
            double r14 = r12 / r14
        L_0x00cf:
            if (r3 >= r1) goto L_0x00e0
            com.graphbuilder.curve.BSpline$SharedData r2 = r0.sharedData
            double[] r2 = r2.knot
            double r12 = (double) r3
            double r12 = r12 * r14
            r2[r3] = r12
            int r3 = r3 + 1
            r12 = 4607182418800017408(0x3ff0000000000000, double:1.0)
            goto L_0x00cf
        L_0x00e0:
            boolean r1 = r0.useDefaultInterval
            if (r1 == 0) goto L_0x013b
            int r1 = r0.degree
            int r2 = r1 + -1
            double r2 = (double) r2
            double r6 = r2 * r14
            int r1 = r1 - r11
            double r1 = (double) r1
            double r1 = r1 * r14
            r12 = 4607182418800017408(0x3ff0000000000000, double:1.0)
            double r8 = r12 - r1
            goto L_0x013b
        L_0x00f3:
            if (r2 != 0) goto L_0x013b
            int r2 = r4 + 1
            double r14 = (double) r2
            double r14 = r12 / r14
        L_0x00fa:
            int r2 = r0.degree
            r12 = 0
            if (r3 >= r2) goto L_0x010b
            com.graphbuilder.curve.BSpline$SharedData r2 = r0.sharedData
            double[] r2 = r2.knot
            r2[r3] = r12
            int r3 = r3 + 1
            goto L_0x00fa
        L_0x010b:
            r3 = r11
        L_0x010c:
            if (r3 > r4) goto L_0x0122
            com.graphbuilder.curve.BSpline$SharedData r12 = r0.sharedData
            double[] r12 = r12.knot
            int r13 = r2 + 1
            double r10 = (double) r3
            double r10 = r10 * r14
            r12[r2] = r10
            int r3 = r3 + 1
            r2 = r13
            r10 = 2
            r11 = 1
            r12 = 0
            goto L_0x010c
        L_0x0122:
            if (r2 >= r1) goto L_0x0131
            com.graphbuilder.curve.BSpline$SharedData r3 = r0.sharedData
            double[] r3 = r3.knot
            r10 = 4607182418800017408(0x3ff0000000000000, double:1.0)
            r3[r2] = r10
            int r2 = r2 + 1
            goto L_0x0122
        L_0x0131:
            r10 = 4607182418800017408(0x3ff0000000000000, double:1.0)
            boolean r1 = r0.useDefaultInterval
            if (r1 == 0) goto L_0x013b
            r3 = r10
            r1 = 0
            goto L_0x013d
        L_0x013b:
            r1 = r6
            r3 = r8
        L_0x013d:
            com.graphbuilder.curve.BSpline$SharedData r6 = r0.sharedData
            int[] r6 = r6.a
            int r6 = r6.length
            int r7 = r0.degree
            if (r6 >= r7) goto L_0x015b
            com.graphbuilder.curve.BSpline$SharedData r6 = r0.sharedData
            r8 = 2
            int r7 = r7 * r8
            int[] r7 = new int[r7]
            int[] unused = r6.a = r7
            com.graphbuilder.curve.BSpline$SharedData r6 = r0.sharedData
            int r7 = r0.degree
            int r7 = r7 * r8
            int[] r7 = new int[r7]
            int[] unused = r6.c = r7
        L_0x015b:
            int r6 = r17.getDimension()
            r7 = 1
            int r6 = r6 + r7
            double[] r6 = new double[r6]
            int r7 = r17.getDimension()
            r6[r7] = r1
            r0.eval(r6)
            boolean r7 = r0.connect
            if (r7 == 0) goto L_0x0174
            r5.lineTo(r6)
            goto L_0x0177
        L_0x0174:
            r5.moveTo(r6)
        L_0x0177:
            r0 = r16
            r5 = r17
            com.graphbuilder.curve.BinaryCurveApproximationAlgorithm.genPts(r0, r1, r3, r5)
            return
        L_0x017f:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.String r1 = "group iterator size - degree < 0"
            r0.<init>(r1)
            throw r0
        L_0x0187:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.String r1 = "Group iterator not in range"
            r0.<init>(r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.graphbuilder.curve.BSpline.appendTo(com.graphbuilder.curve.MultiPath):void");
    }

    /* access modifiers changed from: protected */
    public double N(double d, int i) {
        double d2;
        int i2 = 0;
        int i3 = 0;
        while (true) {
            d2 = 0.0d;
            if (i3 >= this.degree) {
                break;
            }
            int i4 = i + i3;
            double d3 = this.sharedData.knot[i4];
            double d4 = this.sharedData.knot[i4 + 1];
            if (d < d3 || d > d4 || d3 == d4) {
                i3++;
                i2 = 0;
            } else {
                int i5 = this.degree;
                int i6 = i5 - 2;
                for (int i7 = (i5 - i3) - 1; i7 >= 0; i7--) {
                    this.sharedData.a[i7] = i2;
                }
                if (i3 > 0) {
                    for (int i8 = i2; i8 < i3; i8++) {
                        this.sharedData.c[i8] = i8;
                    }
                    this.sharedData.c[i3] = Integer.MAX_VALUE;
                } else {
                    this.sharedData.c[i2] = i6;
                    this.sharedData.c[1] = this.degree;
                }
                int i9 = i2;
                while (true) {
                    int i10 = i9 + 1;
                    if (this.sharedData.c[i9] < this.sharedData.c[i10] - 1) {
                        int i11 = i6 - i3;
                        int i12 = i3 - 1;
                        int i13 = this.degree;
                        double d5 = 1.0d;
                        int i14 = i2;
                        int i15 = i6;
                        while (i15 >= 0) {
                            if (i12 < 0 || this.sharedData.c[i12] != i15) {
                                int i16 = i + this.sharedData.a[i11];
                                double d6 = this.sharedData.knot[i16];
                                d5 *= (d - d6) / (this.sharedData.knot[(i16 + i13) - 1] - d6);
                                i11--;
                            } else {
                                int i17 = i + i14;
                                double d7 = this.sharedData.knot[i17 + i13];
                                d5 *= (d7 - d) / (d7 - this.sharedData.knot[i17 + 1]);
                                i14++;
                                i12--;
                            }
                            i15--;
                            i13--;
                        }
                        if (i3 > 0) {
                            int i18 = 0;
                            boolean z = false;
                            while (true) {
                                int[] access$200 = this.sharedData.a;
                                access$200[i18] = access$200[i18] + 1;
                                if (this.sharedData.a[i18] <= i3) {
                                    break;
                                }
                                i18++;
                                z = true;
                            }
                            if (z) {
                                for (int i19 = i18 - 1; i19 >= 0; i19--) {
                                    this.sharedData.a[i19] = this.sharedData.a[i18];
                                }
                            }
                        }
                        d2 += d5;
                        int[] access$300 = this.sharedData.c;
                        access$300[i9] = access$300[i9] + 1;
                        if (this.sharedData.c[i9] > i6) {
                            break;
                        }
                        for (int i20 = 0; i20 < i9; i20++) {
                            this.sharedData.c[i20] = i20;
                        }
                        i2 = 0;
                        i9 = 0;
                    } else {
                        i9 = i10;
                    }
                }
            }
        }
        return d2;
    }

    public void resetMemory() {
        if (this.sharedData.a.length > 0) {
            int[] unused = this.sharedData.a = new int[0];
            int[] unused2 = this.sharedData.c = new int[0];
        }
        if (this.sharedData.knot.length > 0) {
            double[] unused3 = this.sharedData.knot = new double[0];
        }
    }
}
