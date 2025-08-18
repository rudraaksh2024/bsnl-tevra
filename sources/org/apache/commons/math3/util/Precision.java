package org.apache.commons.math3.util;

import java.math.BigDecimal;
import org.apache.commons.math3.exception.MathArithmeticException;
import org.apache.commons.math3.exception.MathIllegalArgumentException;
import org.apache.commons.math3.exception.util.LocalizedFormats;

public class Precision {
    public static final double EPSILON = Double.longBitsToDouble(4368491638549381120L);
    private static final long EXPONENT_OFFSET = 1023;
    private static final long NEGATIVE_ZERO_DOUBLE_BITS = Double.doubleToRawLongBits(-0.0d);
    private static final int NEGATIVE_ZERO_FLOAT_BITS = Float.floatToRawIntBits(-0.0f);
    private static final double POSITIVE_ZERO = 0.0d;
    private static final long POSITIVE_ZERO_DOUBLE_BITS = Double.doubleToRawLongBits(0.0d);
    private static final int POSITIVE_ZERO_FLOAT_BITS = Float.floatToRawIntBits(0.0f);
    public static final double SAFE_MIN = Double.longBitsToDouble(IEEEDouble.FRAC_ASSUMED_HIGH_BIT);
    private static final long SGN_MASK = Long.MIN_VALUE;
    private static final int SGN_MASK_FLOAT = Integer.MIN_VALUE;

    public static double representableDelta(double d, double d2) {
        return (d2 + d) - d;
    }

    private Precision() {
    }

    public static int compareTo(double d, double d2, double d3) {
        if (equals(d, d2, d3)) {
            return 0;
        }
        return d < d2 ? -1 : 1;
    }

    public static int compareTo(double d, double d2, int i) {
        if (equals(d, d2, i)) {
            return 0;
        }
        return d < d2 ? -1 : 1;
    }

    public static boolean equals(float f, float f2) {
        return equals(f, f2, 1);
    }

    public static boolean equalsIncludingNaN(float f, float f2) {
        int i = (f > f ? 1 : (f == f ? 0 : -1));
        if (i == 0 && f2 == f2) {
            return equals(f, f2, 1);
        }
        return !(((f2 > f2 ? 1 : (f2 == f2 ? 0 : -1)) != 0) ^ (i != 0));
    }

    public static boolean equals(float f, float f2, float f3) {
        return equals(f, f2, 1) || FastMath.abs(f2 - f) <= f3;
    }

    public static boolean equalsIncludingNaN(float f, float f2, float f3) {
        return equalsIncludingNaN(f, f2) || FastMath.abs(f2 - f) <= f3;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0031, code lost:
        if (r0 > (r8 - r1)) goto L_0x002e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:3:0x0016, code lost:
        if (org.apache.commons.math3.util.FastMath.abs(r0 - r1) <= r8) goto L_0x0018;
     */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x0043  */
    /* JADX WARNING: Removed duplicated region for block: B:19:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean equals(float r6, float r7, int r8) {
        /*
            int r0 = java.lang.Float.floatToRawIntBits(r6)
            int r1 = java.lang.Float.floatToRawIntBits(r7)
            r2 = r0 ^ r1
            r3 = -2147483648(0xffffffff80000000, float:-0.0)
            r2 = r2 & r3
            r3 = 1
            r4 = 0
            if (r2 != 0) goto L_0x001a
            int r0 = r0 - r1
            int r0 = org.apache.commons.math3.util.FastMath.abs((int) r0)
            if (r0 > r8) goto L_0x002e
        L_0x0018:
            r8 = r3
            goto L_0x0034
        L_0x001a:
            if (r0 >= r1) goto L_0x0023
            int r2 = POSITIVE_ZERO_FLOAT_BITS
            int r1 = r1 - r2
            int r2 = NEGATIVE_ZERO_FLOAT_BITS
            int r0 = r0 - r2
            goto L_0x002c
        L_0x0023:
            int r2 = POSITIVE_ZERO_FLOAT_BITS
            int r0 = r0 - r2
            int r2 = NEGATIVE_ZERO_FLOAT_BITS
            int r1 = r1 - r2
            r5 = r1
            r1 = r0
            r0 = r5
        L_0x002c:
            if (r1 <= r8) goto L_0x0030
        L_0x002e:
            r8 = r4
            goto L_0x0034
        L_0x0030:
            int r8 = r8 - r1
            if (r0 > r8) goto L_0x002e
            goto L_0x0018
        L_0x0034:
            if (r8 == 0) goto L_0x0043
            boolean r6 = java.lang.Float.isNaN(r6)
            if (r6 != 0) goto L_0x0043
            boolean r6 = java.lang.Float.isNaN(r7)
            if (r6 != 0) goto L_0x0043
            goto L_0x0044
        L_0x0043:
            r3 = r4
        L_0x0044:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.math3.util.Precision.equals(float, float, int):boolean");
    }

    public static boolean equalsIncludingNaN(float f, float f2, int i) {
        int i2 = (f > f ? 1 : (f == f ? 0 : -1));
        if (i2 == 0 && f2 == f2) {
            return equals(f, f2, i);
        }
        return !(((f2 > f2 ? 1 : (f2 == f2 ? 0 : -1)) != 0) ^ (i2 != 0));
    }

    public static boolean equals(double d, double d2) {
        return equals(d, d2, 1);
    }

    public static boolean equalsIncludingNaN(double d, double d2) {
        int i = (d > d ? 1 : (d == d ? 0 : -1));
        if (i == 0 && d2 == d2) {
            return equals(d, d2, 1);
        }
        return !((i != 0) ^ ((d2 > d2 ? 1 : (d2 == d2 ? 0 : -1)) != 0));
    }

    public static boolean equals(double d, double d2, double d3) {
        return equals(d, d2, 1) || FastMath.abs(d2 - d) <= d3;
    }

    public static boolean equalsWithRelativeTolerance(double d, double d2, double d3) {
        if (equals(d, d2, 1)) {
            return true;
        }
        if (FastMath.abs((d - d2) / FastMath.max(FastMath.abs(d), FastMath.abs(d2))) <= d3) {
            return true;
        }
        return false;
    }

    public static boolean equalsIncludingNaN(double d, double d2, double d3) {
        return equalsIncludingNaN(d, d2) || FastMath.abs(d2 - d) <= d3;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:13:0x003f, code lost:
        if (r0 > (r7 - r2)) goto L_0x003a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:3:0x001d, code lost:
        if (org.apache.commons.math3.util.FastMath.abs(r0 - r2) <= ((long) r15)) goto L_0x001f;
     */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x0051  */
    /* JADX WARNING: Removed duplicated region for block: B:21:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean equals(double r11, double r13, int r15) {
        /*
            long r0 = java.lang.Double.doubleToRawLongBits(r11)
            long r2 = java.lang.Double.doubleToRawLongBits(r13)
            long r4 = r0 ^ r2
            r6 = -9223372036854775808
            long r4 = r4 & r6
            r6 = 0
            int r4 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            r5 = 1
            r6 = 0
            if (r4 != 0) goto L_0x0021
            long r0 = r0 - r2
            long r0 = org.apache.commons.math3.util.FastMath.abs((long) r0)
            long r2 = (long) r15
            int r15 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r15 > 0) goto L_0x003a
        L_0x001f:
            r15 = r5
            goto L_0x0042
        L_0x0021:
            int r4 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r4 >= 0) goto L_0x002c
            long r7 = POSITIVE_ZERO_DOUBLE_BITS
            long r2 = r2 - r7
            long r7 = NEGATIVE_ZERO_DOUBLE_BITS
            long r0 = r0 - r7
            goto L_0x0035
        L_0x002c:
            long r7 = POSITIVE_ZERO_DOUBLE_BITS
            long r0 = r0 - r7
            long r7 = NEGATIVE_ZERO_DOUBLE_BITS
            long r2 = r2 - r7
            r9 = r0
            r0 = r2
            r2 = r9
        L_0x0035:
            long r7 = (long) r15
            int r15 = (r2 > r7 ? 1 : (r2 == r7 ? 0 : -1))
            if (r15 <= 0) goto L_0x003c
        L_0x003a:
            r15 = r6
            goto L_0x0042
        L_0x003c:
            long r7 = r7 - r2
            int r15 = (r0 > r7 ? 1 : (r0 == r7 ? 0 : -1))
            if (r15 > 0) goto L_0x003a
            goto L_0x001f
        L_0x0042:
            if (r15 == 0) goto L_0x0051
            boolean r11 = java.lang.Double.isNaN(r11)
            if (r11 != 0) goto L_0x0051
            boolean r11 = java.lang.Double.isNaN(r13)
            if (r11 != 0) goto L_0x0051
            goto L_0x0052
        L_0x0051:
            r5 = r6
        L_0x0052:
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.math3.util.Precision.equals(double, double, int):boolean");
    }

    public static boolean equalsIncludingNaN(double d, double d2, int i) {
        int i2 = (d > d ? 1 : (d == d ? 0 : -1));
        if (i2 == 0 && d2 == d2) {
            return equals(d, d2, i);
        }
        return !(((d2 > d2 ? 1 : (d2 == d2 ? 0 : -1)) != 0) ^ (i2 != 0));
    }

    public static double round(double d, int i) {
        return round(d, i, 4);
    }

    public static double round(double d, int i, int i2) {
        try {
            double doubleValue = new BigDecimal(Double.toString(d)).setScale(i, i2).doubleValue();
            return doubleValue == 0.0d ? d * 0.0d : doubleValue;
        } catch (NumberFormatException unused) {
            if (Double.isInfinite(d)) {
                return d;
            }
            return Double.NaN;
        }
    }

    public static float round(float f, int i) {
        return round(f, i, 4);
    }

    public static float round(float f, int i, int i2) throws MathArithmeticException, MathIllegalArgumentException {
        float copySign = FastMath.copySign(1.0f, f);
        float pow = ((float) FastMath.pow(10.0d, i)) * copySign;
        return ((float) roundUnscaled((double) (f * pow), (double) copySign, i2)) / pow;
    }

    private static double roundUnscaled(double d, double d2, int i) throws MathArithmeticException, MathIllegalArgumentException {
        switch (i) {
            case 0:
                return d != FastMath.floor(d) ? FastMath.ceil(FastMath.nextAfter(d, Double.POSITIVE_INFINITY)) : d;
            case 1:
                return FastMath.floor(FastMath.nextAfter(d, Double.NEGATIVE_INFINITY));
            case 2:
                if (d2 == -1.0d) {
                    return FastMath.floor(FastMath.nextAfter(d, Double.NEGATIVE_INFINITY));
                }
                return FastMath.ceil(FastMath.nextAfter(d, Double.POSITIVE_INFINITY));
            case 3:
                if (d2 == -1.0d) {
                    return FastMath.ceil(FastMath.nextAfter(d, Double.POSITIVE_INFINITY));
                }
                return FastMath.floor(FastMath.nextAfter(d, Double.NEGATIVE_INFINITY));
            case 4:
                double nextAfter = FastMath.nextAfter(d, Double.POSITIVE_INFINITY);
                if (nextAfter - FastMath.floor(nextAfter) >= 0.5d) {
                    return FastMath.ceil(nextAfter);
                }
                return FastMath.floor(nextAfter);
            case 5:
                double nextAfter2 = FastMath.nextAfter(d, Double.NEGATIVE_INFINITY);
                if (nextAfter2 - FastMath.floor(nextAfter2) > 0.5d) {
                    return FastMath.ceil(nextAfter2);
                }
                return FastMath.floor(nextAfter2);
            case 6:
                double floor = d - FastMath.floor(d);
                if (floor > 0.5d) {
                    return FastMath.ceil(d);
                }
                if (floor < 0.5d) {
                    return FastMath.floor(d);
                }
                if (FastMath.floor(d) / 2.0d == FastMath.floor(FastMath.floor(d) / 2.0d)) {
                    return FastMath.floor(d);
                }
                return FastMath.ceil(d);
            case 7:
                if (d == FastMath.floor(d)) {
                    return d;
                }
                throw new MathArithmeticException();
            default:
                throw new MathIllegalArgumentException(LocalizedFormats.INVALID_ROUNDING_METHOD, Integer.valueOf(i), "ROUND_CEILING", 2, "ROUND_DOWN", 1, "ROUND_FLOOR", 3, "ROUND_HALF_DOWN", 5, "ROUND_HALF_EVEN", 6, "ROUND_HALF_UP", 4, "ROUND_UNNECESSARY", 7, "ROUND_UP", 0);
        }
    }
}
