package org.apache.commons.math3.geometry.euclidean.oned;

import org.apache.commons.math3.geometry.partitioning.AbstractSubHyperplane;
import org.apache.commons.math3.geometry.partitioning.Hyperplane;
import org.apache.commons.math3.geometry.partitioning.Region;
import org.apache.commons.math3.geometry.partitioning.SubHyperplane;

public class SubOrientedPoint extends AbstractSubHyperplane<Euclidean1D, Euclidean1D> {
    public double getSize() {
        return 0.0d;
    }

    public boolean isEmpty() {
        return false;
    }

    public SubOrientedPoint(Hyperplane<Euclidean1D> hyperplane, Region<Euclidean1D> region) {
        super(hyperplane, region);
    }

    /* access modifiers changed from: protected */
    public AbstractSubHyperplane<Euclidean1D, Euclidean1D> buildNew(Hyperplane<Euclidean1D> hyperplane, Region<Euclidean1D> region) {
        return new SubOrientedPoint(hyperplane, region);
    }

    public SubHyperplane.SplitSubHyperplane<Euclidean1D> split(Hyperplane<Euclidean1D> hyperplane) {
        double offset = hyperplane.getOffset(((OrientedPoint) getHyperplane()).getLocation());
        if (offset < -1.0E-10d) {
            return new SubHyperplane.SplitSubHyperplane<>((SubHyperplane) null, this);
        }
        if (offset > 1.0E-10d) {
            return new SubHyperplane.SplitSubHyperplane<>(this, (SubOrientedPoint) null);
        }
        return new SubHyperplane.SplitSubHyperplane<>((SubHyperplane) null, (SubHyperplane) null);
    }
}
