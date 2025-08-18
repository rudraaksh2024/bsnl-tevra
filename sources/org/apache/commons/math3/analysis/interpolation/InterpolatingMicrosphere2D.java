package org.apache.commons.math3.analysis.interpolation;

import org.apache.commons.math3.util.FastMath;

public class InterpolatingMicrosphere2D extends InterpolatingMicrosphere {
    private static final int DIMENSION = 2;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public InterpolatingMicrosphere2D(int i, double d, double d2, double d3) {
        super(2, i, d, d2, d3);
        int i2 = i;
        for (int i3 = 0; i3 < i2; i3++) {
            double d4 = (((double) i3) * 6.283185307179586d) / ((double) i2);
            double[] dArr = {FastMath.cos(d4), FastMath.sin(d4)};
            add(dArr, false);
        }
    }

    protected InterpolatingMicrosphere2D(InterpolatingMicrosphere2D interpolatingMicrosphere2D) {
        super(interpolatingMicrosphere2D);
    }

    public InterpolatingMicrosphere2D copy() {
        return new InterpolatingMicrosphere2D(this);
    }
}
