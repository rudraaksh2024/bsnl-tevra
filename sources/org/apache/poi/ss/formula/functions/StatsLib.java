package org.apache.poi.ss.formula.functions;

import java.util.Arrays;

final class StatsLib {
    private StatsLib() {
    }

    public static double avedev(double[] dArr) {
        double d = 0.0d;
        double d2 = 0.0d;
        for (double d3 : dArr) {
            d2 += d3;
        }
        double length = d2 / ((double) dArr.length);
        for (double d4 : dArr) {
            d += Math.abs(d4 - length);
        }
        return d / ((double) dArr.length);
    }

    public static double stdev(double[] dArr) {
        if (dArr == null || dArr.length <= 1) {
            return Double.NaN;
        }
        return Math.sqrt(devsq(dArr) / ((double) (dArr.length - 1)));
    }

    public static double var(double[] dArr) {
        if (dArr == null || dArr.length <= 1) {
            return Double.NaN;
        }
        return devsq(dArr) / ((double) (dArr.length - 1));
    }

    public static double varp(double[] dArr) {
        if (dArr == null || dArr.length <= 1) {
            return Double.NaN;
        }
        return devsq(dArr) / ((double) dArr.length);
    }

    public static double median(double[] dArr) {
        if (dArr == null || dArr.length < 1) {
            return Double.NaN;
        }
        int length = dArr.length;
        Arrays.sort(dArr);
        int i = length % 2;
        int i2 = length / 2;
        return i == 0 ? (dArr[i2] + dArr[i2 - 1]) / 2.0d : dArr[i2];
    }

    public static double devsq(double[] dArr) {
        if (dArr == null || dArr.length < 1) {
            return Double.NaN;
        }
        double d = 0.0d;
        for (double d2 : dArr) {
            d += d2;
        }
        double d3 = d / ((double) r0);
        double d4 = 0.0d;
        for (double d5 : dArr) {
            d4 += (d5 - d3) * (d5 - d3);
        }
        if (r0 == 1) {
            return 0.0d;
        }
        return d4;
    }

    public static double kthLargest(double[] dArr, int i) {
        int i2 = i - 1;
        if (dArr == null || dArr.length <= i2 || i2 < 0) {
            return Double.NaN;
        }
        Arrays.sort(dArr);
        return dArr[(dArr.length - i2) - 1];
    }

    public static double kthSmallest(double[] dArr, int i) {
        int i2 = i - 1;
        if (dArr == null || dArr.length <= i2 || i2 < 0) {
            return Double.NaN;
        }
        Arrays.sort(dArr);
        return dArr[i2];
    }
}
