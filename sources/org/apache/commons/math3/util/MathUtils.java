package org.apache.commons.math3.util;

import java.util.Arrays;
import org.apache.commons.math3.RealFieldElement;
import org.apache.commons.math3.exception.MathArithmeticException;
import org.apache.commons.math3.exception.NotFiniteNumberException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.exception.util.Localizable;
import org.apache.commons.math3.exception.util.LocalizedFormats;

public final class MathUtils {
    public static final double PI_SQUARED = 9.869604401089358d;
    public static final double TWO_PI = 6.283185307179586d;

    private MathUtils() {
    }

    public static int hash(double d) {
        return new Double(d).hashCode();
    }

    public static boolean equals(double d, double d2) {
        return new Double(d).equals(new Double(d2));
    }

    public static int hash(double[] dArr) {
        return Arrays.hashCode(dArr);
    }

    public static double normalizeAngle(double d, double d2) {
        return d - (FastMath.floor(((3.141592653589793d + d) - d2) / 6.283185307179586d) * 6.283185307179586d);
    }

    public static <T extends RealFieldElement<T>> T max(T t, T t2) {
        return ((RealFieldElement) t.subtract(t2)).getReal() >= 0.0d ? t : t2;
    }

    public static <T extends RealFieldElement<T>> T min(T t, T t2) {
        return ((RealFieldElement) t.subtract(t2)).getReal() >= 0.0d ? t2 : t;
    }

    public static double reduce(double d, double d2, double d3) {
        double abs = FastMath.abs(d2);
        return (d - (abs * FastMath.floor((d - d3) / abs))) - d3;
    }

    public static byte copySign(byte b, byte b2) throws MathArithmeticException {
        if ((b >= 0 && b2 >= 0) || (b < 0 && b2 < 0)) {
            return b;
        }
        if (b2 < 0 || b != Byte.MIN_VALUE) {
            return (byte) (-b);
        }
        throw new MathArithmeticException(LocalizedFormats.OVERFLOW, new Object[0]);
    }

    public static short copySign(short s, short s2) throws MathArithmeticException {
        if ((s >= 0 && s2 >= 0) || (s < 0 && s2 < 0)) {
            return s;
        }
        if (s2 < 0 || s != Short.MIN_VALUE) {
            return (short) (-s);
        }
        throw new MathArithmeticException(LocalizedFormats.OVERFLOW, new Object[0]);
    }

    public static int copySign(int i, int i2) throws MathArithmeticException {
        if ((i >= 0 && i2 >= 0) || (i < 0 && i2 < 0)) {
            return i;
        }
        if (i2 < 0 || i != Integer.MIN_VALUE) {
            return -i;
        }
        throw new MathArithmeticException(LocalizedFormats.OVERFLOW, new Object[0]);
    }

    public static long copySign(long j, long j2) throws MathArithmeticException {
        int i = (j > 0 ? 1 : (j == 0 ? 0 : -1));
        if ((i >= 0 && j2 >= 0) || (i < 0 && j2 < 0)) {
            return j;
        }
        if (j2 < 0 || j != Long.MIN_VALUE) {
            return -j;
        }
        throw new MathArithmeticException(LocalizedFormats.OVERFLOW, new Object[0]);
    }

    public static void checkFinite(double d) throws NotFiniteNumberException {
        if (Double.isInfinite(d) || Double.isNaN(d)) {
            throw new NotFiniteNumberException(Double.valueOf(d), new Object[0]);
        }
    }

    public static void checkFinite(double[] dArr) throws NotFiniteNumberException {
        for (int i = 0; i < dArr.length; i++) {
            double d = dArr[i];
            if (Double.isInfinite(d) || Double.isNaN(d)) {
                throw new NotFiniteNumberException(LocalizedFormats.ARRAY_ELEMENT, Double.valueOf(d), Integer.valueOf(i));
            }
        }
    }

    public static void checkNotNull(Object obj, Localizable localizable, Object... objArr) throws NullArgumentException {
        if (obj == null) {
            throw new NullArgumentException(localizable, objArr);
        }
    }

    public static void checkNotNull(Object obj) throws NullArgumentException {
        if (obj == null) {
            throw new NullArgumentException();
        }
    }
}
