package com.graphbuilder.curve;

import com.graphbuilder.geom.Geom;
import java.lang.reflect.Array;

public class MultiPath {
    public static final Object LINE_TO = new Object();
    public static final Object MOVE_TO = new Object();
    private final int dimension;
    private double flatness = 1.0d;
    private double[][] point;
    private int size = 0;
    private Object[] type;

    public MultiPath(int i) {
        double[][] dArr = (double[][]) Array.newInstance(Double.TYPE, new int[]{2, 0});
        this.point = dArr;
        this.type = new Object[dArr.length];
        if (i > 0) {
            this.dimension = i;
            return;
        }
        throw new IllegalArgumentException("dimension > 0 required");
    }

    public int getDimension() {
        return this.dimension;
    }

    public double getFlatness() {
        return this.flatness;
    }

    public void setFlatness(double d) {
        if (d > 0.0d) {
            this.flatness = d;
            return;
        }
        throw new IllegalArgumentException("flatness > 0 required");
    }

    public double[] get(int i) {
        return this.point[i];
    }

    public void set(int i, double[] dArr) {
        if (dArr == null) {
            throw new IllegalArgumentException("Point cannot be null.");
        } else if (dArr.length >= this.dimension) {
            double[][] dArr2 = this.point;
            if (dArr2[i] != null) {
                dArr2[i] = dArr;
                return;
            }
            throw new ArrayIndexOutOfBoundsException(i);
        } else {
            throw new IllegalArgumentException("p.length >= dimension required");
        }
    }

    public Object getType(int i) {
        Object obj = this.type[i];
        if (obj != null) {
            return obj;
        }
        throw new ArrayIndexOutOfBoundsException(i);
    }

    public void setType(int i, Object obj) {
        Object obj2 = MOVE_TO;
        if (obj == obj2 || obj == LINE_TO) {
            Object[] objArr = this.type;
            if (objArr[i] == null) {
                throw new ArrayIndexOutOfBoundsException(i);
            } else if (i != 0 || obj == obj2) {
                objArr[i] = obj;
            } else {
                throw new IllegalArgumentException("type[0] must always be MOVE_TO");
            }
        } else {
            throw new IllegalArgumentException("unknown type");
        }
    }

    public int getNumPoints() {
        return this.size;
    }

    public void setNumPoints(int i) {
        if (i == 0 || this.point[i - 1] != null) {
            this.size = i;
            return;
        }
        throw new ArrayIndexOutOfBoundsException(i);
    }

    public int getCapacity() {
        return this.point.length;
    }

    public void ensureCapacity(int i) {
        double[][] dArr = this.point;
        if (dArr.length < i) {
            int length = dArr.length * 2;
            if (length >= i) {
                i = length;
            }
            double[][] dArr2 = new double[i][];
            for (int i2 = 0; i2 < this.size; i2++) {
                dArr2[i2] = this.point[i2];
            }
            Object[] objArr = new Object[i];
            for (int i3 = 0; i3 < this.size; i3++) {
                objArr[i3] = this.type[i3];
            }
            this.point = dArr2;
            this.type = objArr;
        }
    }

    public void trimArray() {
        int i;
        int i2 = this.size;
        if (i2 < this.point.length) {
            double[][] dArr = new double[i2][];
            int i3 = 0;
            while (true) {
                i = this.size;
                if (i3 >= i) {
                    break;
                }
                dArr[i3] = this.point[i3];
                i3++;
            }
            Object[] objArr = new Object[i];
            for (int i4 = 0; i4 < this.size; i4++) {
                objArr[i4] = this.type[i4];
            }
            this.point = dArr;
            this.type = objArr;
        }
    }

    public void lineTo(double[] dArr) {
        append(dArr, LINE_TO);
    }

    public void moveTo(double[] dArr) {
        append(dArr, MOVE_TO);
    }

    private void append(double[] dArr, Object obj) {
        if (dArr == null) {
            throw new IllegalArgumentException("Point cannot be null.");
        } else if (dArr.length >= this.dimension) {
            int i = this.size;
            if (i == 0) {
                obj = MOVE_TO;
            }
            ensureCapacity(i + 1);
            double[][] dArr2 = this.point;
            int i2 = this.size;
            dArr2[i2] = dArr;
            this.type[i2] = obj;
            this.size = i2 + 1;
        } else {
            throw new IllegalArgumentException("p.length >= dimension required");
        }
    }

    public double getDistSq(double[] dArr) {
        if (dArr == null) {
            throw new IllegalArgumentException("Point cannot be null.");
        } else if (dArr.length >= this.dimension) {
            int numPoints = getNumPoints();
            double d = Double.MAX_VALUE;
            if (numPoints == 0) {
                return Double.MAX_VALUE;
            }
            double[] dArr2 = get(0);
            double[] dArr3 = new double[(this.dimension + 1)];
            for (int i = 1; i < numPoints; i++) {
                double[] dArr4 = get(i);
                if (getType(i) == LINE_TO) {
                    double ptSegDistSq = Geom.ptSegDistSq(dArr4, dArr2, dArr, dArr3, this.dimension);
                    if (ptSegDistSq < d) {
                        d = ptSegDistSq;
                    }
                }
            }
            return d;
        } else {
            throw new IllegalArgumentException("p.length >= dimension required");
        }
    }
}
