package org.apache.commons.math3.analysis.interpolation;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.MaxCountExceededException;
import org.apache.commons.math3.exception.NotPositiveException;
import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import org.apache.commons.math3.exception.OutOfRangeException;
import org.apache.commons.math3.random.UnitSphereRandomVectorGenerator;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.MathArrays;

public class InterpolatingMicrosphere {
    private final double background;
    private final double darkThreshold;
    private final int dimension;
    private final double maxDarkFraction;
    private final List<Facet> microsphere;
    private final List<FacetData> microsphereData;
    private final int size;

    protected InterpolatingMicrosphere(int i, int i2, double d, double d2, double d3) {
        if (i <= 0) {
            throw new NotStrictlyPositiveException(Integer.valueOf(i));
        } else if (i2 <= 0) {
            throw new NotStrictlyPositiveException(Integer.valueOf(i2));
        } else if (d < 0.0d || d > 1.0d) {
            throw new OutOfRangeException(Double.valueOf(d), 0, 1);
        } else if (d2 >= 0.0d) {
            this.dimension = i;
            this.size = i2;
            this.maxDarkFraction = d;
            this.darkThreshold = d2;
            this.background = d3;
            this.microsphere = new ArrayList(i2);
            this.microsphereData = new ArrayList(i2);
        } else {
            throw new NotPositiveException(Double.valueOf(d2));
        }
    }

    public InterpolatingMicrosphere(int i, int i2, double d, double d2, double d3, UnitSphereRandomVectorGenerator unitSphereRandomVectorGenerator) {
        this(i, i2, d, d2, d3);
        for (int i3 = 0; i3 < i2; i3++) {
            add(unitSphereRandomVectorGenerator.nextVector(), false);
        }
    }

    protected InterpolatingMicrosphere(InterpolatingMicrosphere interpolatingMicrosphere) {
        this.dimension = interpolatingMicrosphere.dimension;
        int i = interpolatingMicrosphere.size;
        this.size = i;
        this.maxDarkFraction = interpolatingMicrosphere.maxDarkFraction;
        this.darkThreshold = interpolatingMicrosphere.darkThreshold;
        this.background = interpolatingMicrosphere.background;
        this.microsphere = interpolatingMicrosphere.microsphere;
        this.microsphereData = new ArrayList(i);
        for (FacetData next : interpolatingMicrosphere.microsphereData) {
            this.microsphereData.add(new FacetData(next.illumination(), next.sample()));
        }
    }

    public InterpolatingMicrosphere copy() {
        return new InterpolatingMicrosphere(this);
    }

    public int getDimension() {
        return this.dimension;
    }

    public int getSize() {
        return this.size;
    }

    public double value(double[] dArr, double[][] dArr2, double[] dArr3, double d, double d2) {
        double[][] dArr4 = dArr2;
        double d3 = d;
        if (d3 >= 0.0d) {
            clear();
            int length = dArr4.length;
            for (int i = 0; i < length; i++) {
                double[] dArr5 = dArr;
                double[] ebeSubtract = MathArrays.ebeSubtract(dArr4[i], dArr);
                double safeNorm = MathArrays.safeNorm(ebeSubtract);
                if (FastMath.abs(safeNorm) < d2) {
                    return dArr3[i];
                }
                illuminate(ebeSubtract, dArr3[i], FastMath.pow(safeNorm, -d3));
            }
            return interpolate();
        }
        throw new NotPositiveException(Double.valueOf(d));
    }

    /* access modifiers changed from: protected */
    public void add(double[] dArr, boolean z) {
        if (this.microsphere.size() >= this.size) {
            throw new MaxCountExceededException(Integer.valueOf(this.size));
        } else if (dArr.length <= this.dimension) {
            List<Facet> list = this.microsphere;
            if (z) {
                dArr = (double[]) dArr.clone();
            }
            list.add(new Facet(dArr));
            this.microsphereData.add(new FacetData(0.0d, 0.0d));
        } else {
            throw new DimensionMismatchException(dArr.length, this.dimension);
        }
    }

    private double interpolate() {
        int i = 0;
        double d = 0.0d;
        double d2 = 0.0d;
        for (FacetData next : this.microsphereData) {
            double illumination = next.illumination();
            if (illumination != 0.0d) {
                d += next.sample() * illumination;
                d2 += illumination;
            } else {
                i++;
            }
        }
        return ((double) i) / ((double) this.size) <= this.maxDarkFraction ? d / d2 : this.background;
    }

    private void illuminate(double[] dArr, double d, double d2) {
        for (int i = 0; i < this.size; i++) {
            double cosAngle = MathArrays.cosAngle(this.microsphere.get(i).getNormal(), dArr);
            if (cosAngle > 0.0d) {
                double d3 = cosAngle * d2;
                if (d3 > this.darkThreshold && d3 > this.microsphereData.get(i).illumination()) {
                    this.microsphereData.set(i, new FacetData(d3, d));
                }
            }
        }
    }

    private void clear() {
        for (int i = 0; i < this.size; i++) {
            this.microsphereData.set(i, new FacetData(0.0d, 0.0d));
        }
    }

    private static class Facet {
        private final double[] normal;

        Facet(double[] dArr) {
            this.normal = dArr;
        }

        public double[] getNormal() {
            return this.normal;
        }
    }

    private static class FacetData {
        private final double illumination;
        private final double sample;

        FacetData(double d, double d2) {
            this.illumination = d;
            this.sample = d2;
        }

        public double illumination() {
            return this.illumination;
        }

        public double sample() {
            return this.sample;
        }
    }
}
