package org.apache.commons.math3.analysis.integration.gauss;

import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.Pair;

public class HermiteRuleFactory extends BaseRuleFactory<Double> {
    private static final double H0 = 0.7511255444649425d;
    private static final double H1 = 1.0622519320271968d;
    private static final double SQRT_PI = 1.772453850905516d;

    /* access modifiers changed from: protected */
    public Pair<Double[], Double[]> computeRule(int i) throws DimensionMismatchException {
        double d;
        double d2;
        double d3;
        double d4;
        int i2 = i;
        Double valueOf = Double.valueOf(0.0d);
        int i3 = 1;
        if (i2 == 1) {
            return new Pair<>(new Double[]{valueOf}, new Double[]{Double.valueOf(SQRT_PI)});
        }
        int i4 = i2 - 1;
        Double[] dArr = (Double[]) getRuleInternal(i4).getFirst();
        Double[] dArr2 = new Double[i2];
        Double[] dArr3 = new Double[i2];
        double sqrt = FastMath.sqrt((double) (i4 * 2));
        double sqrt2 = FastMath.sqrt((double) (i2 * 2));
        int i5 = i2 / 2;
        int i6 = 0;
        while (true) {
            d = H0;
            if (i6 >= i5) {
                break;
            }
            if (i6 == 0) {
                d2 = -sqrt;
            } else {
                d2 = dArr[i6 - 1].doubleValue();
            }
            if (i5 == i3) {
                d3 = -0.5d;
            } else {
                d3 = dArr[i6].doubleValue();
            }
            double d5 = d2 * H1;
            int i7 = i3;
            double d6 = 0.7511255444649425d;
            while (i7 < i2) {
                int i8 = i7 + 1;
                double d7 = sqrt;
                double d8 = (double) i8;
                double sqrt3 = FastMath.sqrt(2.0d / d8);
                d6 = d5;
                sqrt = d7;
                d5 = ((sqrt3 * d2) * d5) - (FastMath.sqrt(((double) i7) / d8) * d6);
                i7 = i8;
            }
            double d9 = sqrt;
            double d10 = (d2 + d3) * 0.5d;
            double d11 = 0.7511255444649425d;
            boolean z = false;
            while (!z) {
                z = d3 - d2 <= Math.ulp(d10);
                double d12 = d10 * H1;
                double d13 = 0.7511255444649425d;
                int i9 = 1;
                while (i9 < i2) {
                    int i10 = i9 + 1;
                    double d14 = d2;
                    double d15 = (double) i10;
                    i9 = i10;
                    d13 = d12;
                    d12 = ((FastMath.sqrt(2.0d / d15) * d10) * d12) - (FastMath.sqrt(((double) i9) / d15) * d13);
                    d2 = d14;
                }
                double d16 = d2;
                if (!z) {
                    if (d5 * d12 < 0.0d) {
                        d3 = d10;
                        d4 = d16;
                    } else {
                        d4 = d10;
                        d5 = d12;
                    }
                    d10 = (d4 + d3) * 0.5d;
                    d11 = d13;
                } else {
                    d11 = d13;
                    d4 = d16;
                }
            }
            double d17 = d11 * sqrt2;
            double d18 = 2.0d / (d17 * d17);
            dArr2[i6] = Double.valueOf(d10);
            dArr3[i6] = Double.valueOf(d18);
            int i11 = i4 - i6;
            dArr2[i11] = Double.valueOf(-d10);
            dArr3[i11] = Double.valueOf(d18);
            i6++;
            sqrt = d9;
            i3 = 1;
        }
        if (i2 % 2 != 0) {
            for (int i12 = 1; i12 < i2; i12 += 2) {
                d *= -FastMath.sqrt(((double) i12) / ((double) (i12 + 1)));
            }
            double d19 = sqrt2 * d;
            dArr2[i5] = valueOf;
            dArr3[i5] = Double.valueOf(2.0d / (d19 * d19));
        }
        return new Pair<>(dArr2, dArr3);
    }
}
