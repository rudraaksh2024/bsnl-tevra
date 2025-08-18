package com.graphbuilder.curve;

public class LagrangeCurve extends ParametricCurve {
    private static final ThreadLocal<SharedData> SHARED_DATA = new ThreadLocal<SharedData>() {
        /* access modifiers changed from: protected */
        public SharedData initialValue() {
            return new SharedData();
        }
    };
    private int baseIndex = 1;
    private int baseLength = 1;
    private boolean interpolateFirst = false;
    private boolean interpolateLast = false;
    private ValueVector knotVector = new ValueVector(new double[]{0.0d, 0.3333333333333333d, 0.6666666666666666d, 1.0d}, 4);
    private final SharedData sharedData = SHARED_DATA.get();

    public int getSampleLimit() {
        return 1;
    }

    private static class SharedData {
        /* access modifiers changed from: private */
        public double[][] pt;

        private SharedData() {
            this.pt = new double[0][];
        }
    }

    public LagrangeCurve(ControlPath controlPath, GroupIterator groupIterator) {
        super(controlPath, groupIterator);
    }

    public int getBaseIndex() {
        return this.baseIndex;
    }

    public void setBaseIndex(int i) {
        if (i >= 0) {
            this.baseIndex = i;
            return;
        }
        throw new IllegalArgumentException("base index >= 0 required.");
    }

    public int getBaseLength() {
        return this.baseLength;
    }

    public void setBaseLength(int i) {
        if (i > 0) {
            this.baseLength = i;
            return;
        }
        throw new IllegalArgumentException("base length > 0 required.");
    }

    public boolean getInterpolateFirst() {
        return this.interpolateFirst;
    }

    public boolean getInterpolateLast() {
        return this.interpolateLast;
    }

    public void setInterpolateFirst(boolean z) {
        this.interpolateFirst = z;
    }

    public void setInterpolateLast(boolean z) {
        this.interpolateLast = z;
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

    /* access modifiers changed from: protected */
    public void eval(double[] dArr) {
        double d = dArr[dArr.length - 1];
        int size = this.knotVector.size();
        for (int i = 0; i < size; i++) {
            double[] dArr2 = this.sharedData.pt[i];
            double L = L(d, i);
            for (int i2 = 0; i2 < dArr.length - 1; i2++) {
                dArr[i2] = dArr[i2] + (dArr2[i2] * L);
            }
        }
    }

    private double L(double d, int i) {
        int size = this.knotVector.size();
        double d2 = 1.0d;
        for (int i2 = 0; i2 < size; i2++) {
            double d3 = this.knotVector.get(i) - this.knotVector.get(i2);
            if (d3 != 0.0d) {
                d2 *= (d - this.knotVector.get(i2)) / d3;
            }
        }
        return d2;
    }

    public void appendTo(MultiPath multiPath) {
        int i = 0;
        if (!this.gi.isInRange(0, this.cp.numPoints())) {
            throw new IllegalArgumentException("Group iterator not in range");
        } else if (this.baseIndex + this.baseLength < this.knotVector.size()) {
            if (this.sharedData.pt.length < this.knotVector.size()) {
                double[][] unused = this.sharedData.pt = new double[(this.knotVector.size() * 2)][];
            }
            this.gi.set(0, 0);
            boolean z = false;
            if (this.baseIndex != 0 && this.interpolateFirst) {
                int i2 = 0;
                while (i2 < this.knotVector.size()) {
                    if (this.gi.hasNext()) {
                        this.sharedData.pt[i2] = this.cp.getPoint(this.gi.next()).getLocation();
                        i2++;
                    } else {
                        throw new IllegalArgumentException("Group iterator ended early");
                    }
                }
                z = doBCAA(multiPath, this.knotVector.get(0), this.knotVector.get(this.baseIndex), false);
            }
            this.gi.set(0, 0);
            int i3 = 0;
            int i4 = 0;
            while (true) {
                int index_i = this.gi.index_i();
                int count_j = this.gi.count_j();
                int i5 = 0;
                int i6 = 0;
                int i7 = 0;
                int i8 = 0;
                while (i5 < this.knotVector.size()) {
                    if (i6 == this.baseLength) {
                        i7 = this.gi.index_i();
                        i8 = this.gi.count_j();
                    }
                    if (!this.gi.hasNext()) {
                        break;
                    }
                    this.sharedData.pt[i5] = this.cp.getPoint(this.gi.next()).getLocation();
                    i6++;
                    i5++;
                }
                if (i5 < this.knotVector.size()) {
                    break;
                }
                this.gi.set(i7, i8);
                z = doBCAA(multiPath, this.knotVector.get(this.baseIndex), this.knotVector.get(this.baseIndex + this.baseLength), z);
                i3 = index_i;
                i4 = count_j;
            }
            if (this.baseIndex + this.baseLength < this.knotVector.size() - 1 && this.interpolateLast) {
                this.gi.set(i3, i4);
                while (i < this.knotVector.size()) {
                    if (!this.gi.hasNext()) {
                        System.out.println("not enough points to interpolate last");
                        return;
                    } else {
                        this.sharedData.pt[i] = this.cp.getPoint(this.gi.next()).getLocation();
                        i++;
                    }
                }
                double d = this.knotVector.get(this.baseIndex + this.baseLength);
                ValueVector valueVector = this.knotVector;
                doBCAA(multiPath, d, valueVector.get(valueVector.size() - 1), z);
            }
        } else {
            throw new IllegalArgumentException("baseIndex + baseLength >= knotVector.size");
        }
    }

    private boolean doBCAA(MultiPath multiPath, double d, double d2, boolean z) {
        double d3;
        double d4;
        if (d2 < d) {
            d3 = d;
            d4 = d2;
        } else {
            d4 = d;
            d3 = d2;
        }
        if (!z) {
            z = true;
            double[] dArr = new double[(multiPath.getDimension() + 1)];
            dArr[multiPath.getDimension()] = d4;
            eval(dArr);
            if (this.connect) {
                multiPath.lineTo(dArr);
            } else {
                multiPath.moveTo(dArr);
            }
        }
        BinaryCurveApproximationAlgorithm.genPts(this, d4, d3, multiPath);
        return z;
    }

    public void resetMemory() {
        if (this.sharedData.pt.length > 0) {
            double[][] unused = this.sharedData.pt = new double[0][];
        }
    }
}
