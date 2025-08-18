package com.graphbuilder.geom;

public final class Geom {
    public static final Object INTERSECT = new Object();
    public static final Object PARALLEL = new Object();

    public static double getTriangleAreaSq(double d, double d2, double d3, double d4, double d5, double d6) {
        double d7 = d - d3;
        double d8 = d2 - d4;
        double d9 = d3 - d5;
        double d10 = d4 - d6;
        double d11 = d5 - d;
        double d12 = d6 - d2;
        double d13 = ((d7 * d7) + (d8 * d8)) / 2.0d;
        double d14 = ((d9 * d9) + (d10 * d10)) / 2.0d;
        double d15 = ((d11 * d11) + (d12 * d12)) / 2.0d;
        if (d14 < d13) {
            double d16 = d13;
            d13 = d14;
            d14 = d16;
        }
        if (d15 >= d13) {
            double d17 = d13;
            d13 = d15;
            d15 = d17;
        }
        double d18 = ((d14 - d13) + d15) / 2.0d;
        return (d15 * d14) - (d18 * d18);
    }

    private Geom() {
    }

    public static double getAngle(double d, double d2, double d3, double d4) {
        double d5;
        double d6 = d3 - d;
        double d7 = d4 - d2;
        if (d6 != 0.0d) {
            d5 = Math.atan(d7 / d6);
            if (d5 < 0.0d) {
                d5 = -d5;
            }
        } else if (d7 == 0.0d) {
            return 0.0d;
        } else {
            d5 = 1.5707963267948966d;
        }
        return d3 >= d ? d4 < d2 ? 6.283185307179586d - d5 : d5 : d4 < d2 ? d5 + 3.141592653589793d : 3.141592653589793d - d5;
    }

    public static double getAngle(Point2d point2d, Point2d point2d2) {
        return getAngle(point2d.getX(), point2d.getY(), point2d2.getX(), point2d2.getY());
    }

    public static double ptLineDistSq(double d, double d2, double d3, double d4, double d5, double d6, double[] dArr) {
        double d7 = d3 - d;
        double d8 = d4 - d2;
        double d9 = (d7 * d7) + (d8 * d8);
        double d10 = 0.0d;
        if (d9 != 0.0d) {
            d10 = (((d5 - d) * d7) + ((d6 - d2) * d8)) / d9;
        }
        double d11 = (d7 * d10) + d;
        double d12 = (d8 * d10) + d2;
        if (dArr != null) {
            dArr[0] = d11;
            dArr[1] = d12;
            dArr[2] = d10;
        }
        double d13 = d5 - d11;
        double d14 = d6 - d12;
        return (d13 * d13) + (d14 * d14);
    }

    public static double ptSegDistSq(double d, double d2, double d3, double d4, double d5, double d6, double[] dArr) {
        double d7 = d3 - d;
        double d8 = d4 - d2;
        double d9 = (d7 * d7) + (d8 * d8);
        double d10 = 0.0d;
        double d11 = d9 != 0.0d ? (((d5 - d) * d7) + ((d6 - d2) * d8)) / d9 : 0.0d;
        if (d11 >= 0.0d) {
            d10 = 1.0d;
            if (d11 <= 1.0d) {
                d10 = d11;
            }
        }
        double d12 = (d7 * d10) + d;
        double d13 = (d8 * d10) + d2;
        if (dArr != null) {
            dArr[0] = d12;
            dArr[1] = d13;
            dArr[2] = d10;
        }
        double d14 = d5 - d12;
        double d15 = d6 - d13;
        return (d14 * d14) + (d15 * d15);
    }

    public static double ptLineDistSq(double[] dArr, double[] dArr2, double[] dArr3, double[] dArr4, int i) {
        double d;
        int i2 = i;
        for (int i3 = 0; i3 < i2; i3++) {
            dArr4[i3] = dArr2[i3] - dArr[i3];
        }
        double d2 = 0.0d;
        double d3 = 0.0d;
        for (int i4 = 0; i4 < i2; i4++) {
            double d4 = dArr4[i4];
            d3 += d4 * d4;
        }
        if (d3 != 0.0d) {
            double d5 = 0.0d;
            for (int i5 = 0; i5 < i2; i5++) {
                d5 += dArr4[i5] * (dArr3[i5] - dArr[i5]);
            }
            d = d5 / d3;
        } else {
            d = 0.0d;
        }
        for (int i6 = 0; i6 < i2; i6++) {
            dArr4[i6] = dArr[i6] + (dArr4[i6] * d);
        }
        dArr4[i2] = d;
        for (int i7 = 0; i7 < i2; i7++) {
            double d6 = dArr3[i7] - dArr4[i7];
            d2 += d6 * d6;
        }
        return d2;
    }

    public static double ptSegDistSq(double[] dArr, double[] dArr2, double[] dArr3, double[] dArr4, int i) {
        double d;
        int i2 = i;
        for (int i3 = 0; i3 < i2; i3++) {
            dArr4[i3] = dArr2[i3] - dArr[i3];
        }
        double d2 = 0.0d;
        double d3 = 0.0d;
        for (int i4 = 0; i4 < i2; i4++) {
            double d4 = dArr4[i4];
            d3 += d4 * d4;
        }
        if (d3 != 0.0d) {
            double d5 = 0.0d;
            for (int i5 = 0; i5 < i2; i5++) {
                d5 += dArr4[i5] * (dArr3[i5] - dArr[i5]);
            }
            d = d5 / d3;
        } else {
            d = 0.0d;
        }
        if (d < 0.0d) {
            d = 0.0d;
        } else if (d > 1.0d) {
            d = 1.0d;
        }
        for (int i6 = 0; i6 < i2; i6++) {
            dArr4[i6] = dArr[i6] + (dArr4[i6] * d);
        }
        dArr4[i2] = d;
        for (int i7 = 0; i7 < i2; i7++) {
            double d6 = dArr3[i7] - dArr4[i7];
            d2 += d6 * d6;
        }
        return d2;
    }

    public static Object getLineLineIntersection(double d, double d2, double d3, double d4, double d5, double d6, double d7, double d8, double[] dArr) {
        double d9 = d3 - d;
        double d10 = d4 - d2;
        double d11 = d7 - d5;
        double d12 = d8 - d6;
        double d13 = (d9 * d12) - (d10 * d11);
        if (d13 == 0.0d) {
            return PARALLEL;
        }
        double d14 = (((d5 - d) * d12) - ((d6 - d2) * d11)) / d13;
        if (dArr != null) {
            dArr[0] = (d9 * d14) + d;
            dArr[1] = d2 + (d10 * d14);
            dArr[2] = d14;
        }
        return INTERSECT;
    }

    public static Object getLineSegIntersection(double d, double d2, double d3, double d4, double d5, double d6, double d7, double d8, double[] dArr) {
        double d9 = d3 - d;
        double d10 = d4 - d2;
        double d11 = d7 - d5;
        double d12 = d8 - d6;
        double d13 = (d9 * d12) - (d10 * d11);
        if (d13 == 0.0d) {
            return PARALLEL;
        }
        double d14 = (((d5 - d) * d10) - ((d6 - d2) * d9)) / d13;
        if (d14 < 0.0d || d14 > 1.0d) {
            return null;
        }
        if (dArr != null) {
            dArr[0] = d5 + (d11 * d14);
            dArr[1] = d6 + (d12 * d14);
            dArr[2] = d14;
        }
        return INTERSECT;
    }

    public static Object getSegSegIntersection(double d, double d2, double d3, double d4, double d5, double d6, double d7, double d8, double[] dArr) {
        double d9 = d3 - d;
        double d10 = d4 - d2;
        double d11 = d7 - d5;
        double d12 = d8 - d6;
        double d13 = (d9 * d12) - (d10 * d11);
        if (d13 == 0.0d) {
            return PARALLEL;
        }
        double d14 = d5 - d;
        double d15 = d6 - d2;
        double d16 = ((d12 * d14) - (d11 * d15)) / d13;
        if (d16 >= 0.0d && d16 <= 1.0d) {
            double d17 = ((d14 * d10) - (d15 * d9)) / d13;
            if (d17 >= 0.0d && d17 <= 1.0d) {
                if (dArr != null) {
                    dArr[0] = d + (d9 * d16);
                    dArr[1] = d2 + (d10 * d16);
                    dArr[2] = d16;
                }
                return INTERSECT;
            }
        }
        return null;
    }

    public static boolean getCircle(double d, double d2, double d3, double d4, double d5, double d6, double[] dArr) {
        double d7 = d3 - d;
        double d8 = d4 - d2;
        double d9 = d - d5;
        double d10 = d2 - d6;
        double d11 = (d7 * d10) - (d8 * d9);
        if (d11 == 0.0d) {
            return false;
        }
        double d12 = (((d5 - d3) * d9) + ((d6 - d4) * d10)) / d11;
        double d13 = d + ((d7 - (d12 * d8)) / 2.0d);
        double d14 = d2 + ((d8 + (d12 * d7)) / 2.0d);
        double d15 = d - d13;
        double d16 = d2 - d14;
        double d17 = (d15 * d15) + (d16 * d16);
        if (dArr != null) {
            dArr[0] = d13;
            dArr[1] = d14;
            dArr[2] = d17;
        }
        return true;
    }

    public static double getTriangleAreaSq(double d, double d2, double d3) {
        if (d < 0.0d) {
            throw new IllegalArgumentException("a >= 0 required");
        } else if (d2 < 0.0d) {
            throw new IllegalArgumentException("b >= 0 required");
        } else if (d3 < 0.0d) {
            throw new IllegalArgumentException("c >= 0 required");
        } else if (d > d2 + d3) {
            throw new IllegalArgumentException("a <= b + c required");
        } else if (d2 > d + d3) {
            throw new IllegalArgumentException("b <= a + c required");
        } else if (d3 <= d + d2) {
            if (d >= d3) {
                double d4 = d;
                d = d3;
                d3 = d4;
            }
            if (d2 < d) {
                double d5 = d;
                d = d2;
                d2 = d5;
            }
            if (d3 < d2) {
                double d6 = d2;
                d2 = d3;
                d3 = d6;
            }
            double d7 = d3 - d2;
            return (((((d2 + d) + d3) * (d - d7)) * (d7 + d)) * (d3 + (d2 - d))) / 16.0d;
        } else {
            throw new IllegalArgumentException("c <= a + b required");
        }
    }
}
