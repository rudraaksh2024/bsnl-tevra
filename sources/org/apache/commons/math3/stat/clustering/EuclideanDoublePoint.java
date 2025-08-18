package org.apache.commons.math3.stat.clustering;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import org.apache.commons.math3.util.MathArrays;

@Deprecated
public class EuclideanDoublePoint implements Clusterable<EuclideanDoublePoint>, Serializable {
    private static final long serialVersionUID = 8026472786091227632L;
    private final double[] point;

    public EuclideanDoublePoint(double[] dArr) {
        this.point = dArr;
    }

    public EuclideanDoublePoint centroidOf(Collection<EuclideanDoublePoint> collection) {
        int i;
        int length = getPoint().length;
        double[] dArr = new double[length];
        Iterator<EuclideanDoublePoint> it = collection.iterator();
        while (true) {
            i = 0;
            if (!it.hasNext()) {
                break;
            }
            EuclideanDoublePoint next = it.next();
            while (i < length) {
                dArr[i] = dArr[i] + next.getPoint()[i];
                i++;
            }
        }
        while (i < length) {
            dArr[i] = dArr[i] / ((double) collection.size());
            i++;
        }
        return new EuclideanDoublePoint(dArr);
    }

    public double distanceFrom(EuclideanDoublePoint euclideanDoublePoint) {
        return MathArrays.distance(this.point, euclideanDoublePoint.getPoint());
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof EuclideanDoublePoint)) {
            return false;
        }
        return Arrays.equals(this.point, ((EuclideanDoublePoint) obj).point);
    }

    public double[] getPoint() {
        return this.point;
    }

    public int hashCode() {
        return Arrays.hashCode(this.point);
    }

    public String toString() {
        return Arrays.toString(this.point);
    }
}
