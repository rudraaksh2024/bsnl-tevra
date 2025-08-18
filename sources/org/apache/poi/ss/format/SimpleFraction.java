package org.apache.poi.ss.format;

import org.apache.poi.openxml4j.opc.PackagingURIHelper;

public class SimpleFraction {
    private final int denominator;
    private final int numerator;

    public static SimpleFraction buildFractionExactDenominator(double d, int i) {
        return new SimpleFraction((int) Math.round(d * ((double) i)), i);
    }

    public static SimpleFraction buildFractionMaxDenominator(double d, int i) {
        return buildFractionMaxDenominator(d, 0.0d, i, 100);
    }

    private static SimpleFraction buildFractionMaxDenominator(double d, double d2, int i, int i2) {
        long j;
        long j2;
        boolean z;
        long j3;
        double d3 = d;
        int i3 = i;
        int i4 = i2;
        long floor = (long) Math.floor(d);
        if (floor <= 2147483647L) {
            int i5 = 1;
            if (Math.abs(((double) floor) - d3) < d2) {
                return new SimpleFraction((int) floor, 1);
            }
            int i6 = 0;
            double d4 = d3;
            long j4 = 1;
            long j5 = 0;
            boolean z2 = false;
            long j6 = 1;
            long j7 = floor;
            while (true) {
                i6 += i5;
                long j8 = j7;
                double d5 = 1.0d / (d4 - ((double) floor));
                long floor2 = (long) Math.floor(d5);
                long j9 = floor;
                j = (floor2 * j8) + j6;
                long j10 = floor2;
                j2 = (floor2 * j4) + j5;
                if (d2 != 0.0d || i3 <= 0) {
                    z = z2;
                } else {
                    z = z2;
                    long j11 = (long) i3;
                    if (Math.abs(j2) > j11 && Math.abs(j4) < j11) {
                        return new SimpleFraction((int) j8, (int) j4);
                    }
                }
                long j12 = j8;
                if (j <= 2147483647L && j2 <= 2147483647L) {
                    long j13 = j4;
                    long j14 = j12;
                    double d6 = ((double) j) / ((double) j2);
                    if (i6 >= i4 || Math.abs(d6 - d3) <= d2 || j2 >= ((long) i3)) {
                        j4 = j13;
                        j3 = j14;
                        z = true;
                    } else {
                        j3 = j;
                        j4 = j2;
                        j5 = j13;
                        d4 = d5;
                        j6 = j14;
                        j9 = j10;
                    }
                    if (!z) {
                        j7 = j3;
                        floor = j9;
                        z2 = z;
                        i5 = 1;
                    } else if (i6 >= i4) {
                        throw new RuntimeException("Unable to convert " + d3 + " to fraction after " + i4 + " iterations");
                    } else if (j2 < ((long) i3)) {
                        return new SimpleFraction((int) j, (int) j2);
                    } else {
                        return new SimpleFraction((int) j3, (int) j4);
                    }
                }
            }
            throw new RuntimeException("Overflow trying to convert " + d3 + " to fraction (" + j + PackagingURIHelper.FORWARD_SLASH_STRING + j2 + ")");
        }
        throw new IllegalArgumentException("Overflow trying to convert " + d3 + " to fraction (" + floor + "/1)");
    }

    public SimpleFraction(int i, int i2) {
        this.numerator = i;
        this.denominator = i2;
    }

    public int getDenominator() {
        return this.denominator;
    }

    public int getNumerator() {
        return this.numerator;
    }
}
