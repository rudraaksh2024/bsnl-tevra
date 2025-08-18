package org.apache.poi.ss.formula.functions;

import java.math.BigDecimal;
import java.math.RoundingMode;
import org.apache.poi.ss.util.NumberToTextConverter;

final class MathX {
    public static double factorial(int i) {
        if (i < 0) {
            return Double.NaN;
        }
        if (i > 170) {
            return Double.POSITIVE_INFINITY;
        }
        double d = 1.0d;
        for (int i2 = 1; i2 <= i; i2++) {
            d *= (double) i2;
        }
        return d;
    }

    public static short sign(double d) {
        return (short) (d == 0.0d ? 0 : d < 0.0d ? -1 : 1);
    }

    private MathX() {
    }

    public static double round(double d, int i) {
        return round(d, i, RoundingMode.HALF_UP);
    }

    public static double round(double d, double d2) {
        return round(d, (int) d2);
    }

    public static double roundUp(double d, int i) {
        return round(d, i, RoundingMode.UP);
    }

    public static double roundUp(double d, double d2) {
        return roundUp(d, (int) d2);
    }

    public static double roundDown(double d, int i) {
        return round(d, i, RoundingMode.DOWN);
    }

    public static double roundDown(double d, double d2) {
        return roundDown(d, (int) d2);
    }

    private static double round(double d, int i, RoundingMode roundingMode) {
        if (Double.isNaN(d) || Double.isInfinite(d)) {
            return Double.NaN;
        }
        return new BigDecimal(NumberToTextConverter.toText(d)).setScale(i, roundingMode).doubleValue();
    }

    public static double average(double[] dArr) {
        double d = 0.0d;
        for (double d2 : dArr) {
            d += d2;
        }
        return d / ((double) dArr.length);
    }

    public static double sum(double[] dArr) {
        double d = 0.0d;
        for (double d2 : dArr) {
            d += d2;
        }
        return d;
    }

    public static double sumsq(double[] dArr) {
        double d = 0.0d;
        for (double d2 : dArr) {
            d += d2 * d2;
        }
        return d;
    }

    public static double product(double[] dArr) {
        if (dArr == null || dArr.length <= 0) {
            return 0.0d;
        }
        double d = 1.0d;
        for (double d2 : dArr) {
            d *= d2;
        }
        return d;
    }

    public static double min(double[] dArr) {
        double d = Double.POSITIVE_INFINITY;
        for (double min : dArr) {
            d = Math.min(d, min);
        }
        return d;
    }

    public static double max(double[] dArr) {
        double d = Double.NEGATIVE_INFINITY;
        for (double max : dArr) {
            d = Math.max(d, max);
        }
        return d;
    }

    public static double floor(double d, double d2) {
        int i = (d2 > 0.0d ? 1 : (d2 == 0.0d ? 0 : -1));
        if (i == 0 && d != 0.0d) {
            return Double.NaN;
        }
        if (d == 0.0d || i == 0) {
            return 0.0d;
        }
        return Math.floor(d / d2) * d2;
    }

    public static double ceiling(double d, double d2) {
        int i = (d > 0.0d ? 1 : (d == 0.0d ? 0 : -1));
        if (i > 0 && d2 < 0.0d) {
            return Double.NaN;
        }
        if (i == 0 || d2 == 0.0d) {
            return 0.0d;
        }
        return Math.ceil(d / d2) * d2;
    }

    public static double factorial(double d) {
        return factorial((int) d);
    }

    public static double mod(double d, double d2) {
        if (d2 == 0.0d) {
            return Double.NaN;
        }
        return sign(d) == sign(d2) ? d % d2 : ((d % d2) + d2) % d2;
    }

    public static double acosh(double d) {
        return Math.log(Math.sqrt(Math.pow(d, 2.0d) - 1.0d) + d);
    }

    public static double asinh(double d) {
        return Math.log(Math.sqrt((d * d) + 1.0d) + d);
    }

    public static double atanh(double d) {
        return Math.log((d + 1.0d) / (1.0d - d)) / 2.0d;
    }

    public static double cosh(double d) {
        return (Math.pow(2.718281828459045d, d) + Math.pow(2.718281828459045d, -d)) / 2.0d;
    }

    public static double sinh(double d) {
        return (Math.pow(2.718281828459045d, d) - Math.pow(2.718281828459045d, -d)) / 2.0d;
    }

    public static double tanh(double d) {
        double pow = Math.pow(2.718281828459045d, d);
        double pow2 = Math.pow(2.718281828459045d, -d);
        return (pow - pow2) / (pow + pow2);
    }

    public static double nChooseK(int i, int i2) {
        if (i < 0 || i2 < 0 || i < i2) {
            return Double.NaN;
        }
        int i3 = i - i2;
        int min = Math.min(i3, i2);
        int max = Math.max(i3, i2);
        double d = 1.0d;
        while (max < i) {
            max++;
            d *= (double) max;
        }
        return d / factorial(min);
    }
}
