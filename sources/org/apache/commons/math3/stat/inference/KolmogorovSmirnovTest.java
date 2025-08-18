package org.apache.commons.math3.stat.inference;

import com.google.android.gms.location.DeviceOrientationRequest;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashSet;
import org.apache.commons.math3.distribution.EnumeratedRealDistribution;
import org.apache.commons.math3.distribution.RealDistribution;
import org.apache.commons.math3.exception.InsufficientDataException;
import org.apache.commons.math3.exception.MathArithmeticException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.exception.NumberIsTooLargeException;
import org.apache.commons.math3.exception.OutOfRangeException;
import org.apache.commons.math3.exception.TooManyIterationsException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.fraction.BigFraction;
import org.apache.commons.math3.linear.MatrixUtils;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.random.RandomGenerator;
import org.apache.commons.math3.random.Well19937c;
import org.apache.commons.math3.util.CombinatoricsUtils;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.MathArrays;

public class KolmogorovSmirnovTest {
    protected static final double KS_SUM_CAUCHY_CRITERION = 1.0E-20d;
    protected static final int LARGE_SAMPLE_PRODUCT = 10000;
    protected static final int MAXIMUM_PARTIAL_SUM_COUNT = 100000;
    @Deprecated
    protected static final int MONTE_CARLO_ITERATIONS = 1000000;
    protected static final double PG_SUM_RELATIVE_ERROR = 1.0E-10d;
    @Deprecated
    protected static final int SMALL_SAMPLE_PRODUCT = 200;
    private final RandomGenerator rng;

    public KolmogorovSmirnovTest() {
        this.rng = new Well19937c();
    }

    @Deprecated
    public KolmogorovSmirnovTest(RandomGenerator randomGenerator) {
        this.rng = randomGenerator;
    }

    public double kolmogorovSmirnovTest(RealDistribution realDistribution, double[] dArr, boolean z) {
        return 1.0d - cdf(kolmogorovSmirnovStatistic(realDistribution, dArr), dArr.length, z);
    }

    public double kolmogorovSmirnovStatistic(RealDistribution realDistribution, double[] dArr) {
        checkArray(dArr);
        int length = dArr.length;
        double d = (double) length;
        double[] dArr2 = new double[length];
        System.arraycopy(dArr, 0, dArr2, 0, length);
        Arrays.sort(dArr2);
        double d2 = 0.0d;
        for (int i = 1; i <= length; i++) {
            int i2 = i - 1;
            double cumulativeProbability = realDistribution.cumulativeProbability(dArr2[i2]);
            double max = FastMath.max(cumulativeProbability - (((double) i2) / d), (((double) i) / d) - cumulativeProbability);
            if (max > d2) {
                d2 = max;
            }
        }
        return d2;
    }

    public double kolmogorovSmirnovTest(double[] dArr, double[] dArr2, boolean z) {
        double[] dArr3;
        double[] dArr4;
        int i = ((((long) dArr.length) * ((long) dArr2.length)) > DeviceOrientationRequest.OUTPUT_PERIOD_MEDIUM ? 1 : ((((long) dArr.length) * ((long) dArr2.length)) == DeviceOrientationRequest.OUTPUT_PERIOD_MEDIUM ? 0 : -1));
        if (i >= 0 || !hasTies(dArr, dArr2)) {
            dArr4 = dArr;
            dArr3 = dArr2;
        } else {
            dArr4 = MathArrays.copyOf(dArr);
            dArr3 = MathArrays.copyOf(dArr2);
            fixTies(dArr4, dArr3);
        }
        if (i >= 0) {
            return approximateP(kolmogorovSmirnovStatistic(dArr, dArr2), dArr.length, dArr2.length);
        }
        return exactP(kolmogorovSmirnovStatistic(dArr4, dArr3), dArr.length, dArr2.length, z);
    }

    public double kolmogorovSmirnovTest(double[] dArr, double[] dArr2) {
        return kolmogorovSmirnovTest(dArr, dArr2, true);
    }

    public double kolmogorovSmirnovStatistic(double[] dArr, double[] dArr2) {
        return ((double) integralKolmogorovSmirnovStatistic(dArr, dArr2)) / ((double) (((long) dArr.length) * ((long) dArr2.length)));
    }

    private long integralKolmogorovSmirnovStatistic(double[] dArr, double[] dArr2) {
        checkArray(dArr);
        checkArray(dArr2);
        double[] copyOf = MathArrays.copyOf(dArr);
        double[] copyOf2 = MathArrays.copyOf(dArr2);
        Arrays.sort(copyOf);
        Arrays.sort(copyOf2);
        int length = copyOf.length;
        int length2 = copyOf2.length;
        int i = 0;
        long j = 0;
        int i2 = 0;
        long j2 = 0;
        do {
            double d = Double.compare(copyOf[i], copyOf2[i2]) <= 0 ? copyOf[i] : copyOf2[i2];
            while (i < length && Double.compare(copyOf[i], d) == 0) {
                i++;
                j2 += (long) length2;
            }
            while (i2 < length2 && Double.compare(copyOf2[i2], d) == 0) {
                i2++;
                j2 -= (long) length;
            }
            if (j2 > j) {
                j = j2;
            } else {
                long j3 = -j2;
                if (j3 > j) {
                    j = j3;
                }
            }
            if (i >= length) {
                break;
            }
        } while (i2 < length2);
        return j;
    }

    public double kolmogorovSmirnovTest(RealDistribution realDistribution, double[] dArr) {
        return kolmogorovSmirnovTest(realDistribution, dArr, false);
    }

    public boolean kolmogorovSmirnovTest(RealDistribution realDistribution, double[] dArr, double d) {
        if (d > 0.0d && d <= 0.5d) {
            return kolmogorovSmirnovTest(realDistribution, dArr) < d;
        }
        throw new OutOfRangeException(LocalizedFormats.OUT_OF_BOUND_SIGNIFICANCE_LEVEL, Double.valueOf(d), 0, Double.valueOf(0.5d));
    }

    public double bootstrap(double[] dArr, double[] dArr2, int i, boolean z) {
        int length = dArr.length;
        int length2 = dArr2.length;
        double[] dArr3 = new double[(length + length2)];
        System.arraycopy(dArr, 0, dArr3, 0, length);
        System.arraycopy(dArr2, 0, dArr3, length, length2);
        EnumeratedRealDistribution enumeratedRealDistribution = new EnumeratedRealDistribution(this.rng, dArr3);
        long integralKolmogorovSmirnovStatistic = integralKolmogorovSmirnovStatistic(dArr, dArr2);
        int i2 = 0;
        int i3 = 0;
        for (int i4 = 0; i4 < i; i4++) {
            int i5 = (integralKolmogorovSmirnovStatistic(enumeratedRealDistribution.sample(length), enumeratedRealDistribution.sample(length2)) > integralKolmogorovSmirnovStatistic ? 1 : (integralKolmogorovSmirnovStatistic(enumeratedRealDistribution.sample(length), enumeratedRealDistribution.sample(length2)) == integralKolmogorovSmirnovStatistic ? 0 : -1));
            if (i5 > 0) {
                i2++;
            } else if (i5 == 0) {
                i3++;
            }
        }
        if (!z) {
            i2 += i3;
        }
        return ((double) i2) / ((double) i);
    }

    public double bootstrap(double[] dArr, double[] dArr2, int i) {
        return bootstrap(dArr, dArr2, i, true);
    }

    public double cdf(double d, int i) throws MathArithmeticException {
        return cdf(d, i, false);
    }

    public double cdfExact(double d, int i) throws MathArithmeticException {
        return cdf(d, i, true);
    }

    public double cdf(double d, int i, boolean z) throws MathArithmeticException {
        double d2 = (double) i;
        double d3 = 1.0d;
        double d4 = 1.0d / d2;
        double d5 = 0.5d * d4;
        if (d <= d5) {
            return 0.0d;
        }
        if (d5 < d && d <= d4) {
            double d6 = (d * 2.0d) - d4;
            for (int i2 = 1; i2 <= i; i2++) {
                d3 *= ((double) i2) * d6;
            }
            return d3;
        } else if (1.0d - d4 <= d && d < 1.0d) {
            return 1.0d - (Math.pow(1.0d - d, d2) * 2.0d);
        } else {
            if (1.0d <= d) {
                return 1.0d;
            }
            if (z) {
                return exactK(d, i);
            }
            if (i <= 140) {
                return roundedK(d, i);
            }
            return pelzGood(d, i);
        }
    }

    private double exactK(double d, int i) throws MathArithmeticException {
        int ceil = ((int) Math.ceil(((double) i) * d)) - 1;
        BigFraction entry = createExactH(d, i).power(i).getEntry(ceil, ceil);
        for (int i2 = 1; i2 <= i; i2++) {
            entry = entry.multiply(i2).divide(i);
        }
        return entry.bigDecimalValue(20, 4).doubleValue();
    }

    private double roundedK(double d, int i) {
        double d2 = (double) i;
        int ceil = ((int) Math.ceil(d2 * d)) - 1;
        double entry = createRoundedH(d, i).power(i).getEntry(ceil, ceil);
        for (int i2 = 1; i2 <= i; i2++) {
            entry *= ((double) i2) / d2;
        }
        return entry;
    }

    public double pelzGood(double d, int i) {
        double d2;
        double d3;
        int i2;
        int i3;
        double d4;
        double d5 = (double) i;
        double sqrt = FastMath.sqrt(d5);
        double d6 = d * sqrt;
        double d7 = d * d * d5;
        double d8 = d7 * d7;
        double d9 = d8 * d7;
        double d10 = 9.869604401089358d / (8.0d * d7);
        double d11 = 0.0d;
        double d12 = d8 * d8;
        double d13 = 0.0d;
        int i4 = 1;
        while (true) {
            if (i4 >= 100000) {
                d2 = d5;
                d3 = d9;
                i2 = 100000;
                break;
            }
            d2 = d5;
            double d14 = (double) ((i4 * 2) - 1);
            d3 = d9;
            double exp = FastMath.exp((-d10) * d14 * d14);
            d13 += exp;
            if (exp <= d13 * 1.0E-10d) {
                i2 = 100000;
                break;
            }
            i4++;
            d5 = d2;
            d9 = d3;
        }
        if (i4 != i2) {
            double sqrt2 = (d13 * FastMath.sqrt(6.283185307179586d)) / d6;
            double d15 = d7 * 2.0d;
            double d16 = 0.0d;
            int i5 = 0;
            while (true) {
                if (i5 >= i2) {
                    break;
                }
                double d17 = ((double) i5) + 0.5d;
                double d18 = d17 * d17;
                double exp2 = ((d18 * 9.869604401089358d) - d7) * FastMath.exp((d18 * -9.869604401089358d) / d15);
                d16 += exp2;
                if (FastMath.abs(exp2) < FastMath.abs(d16) * 1.0E-10d) {
                    i2 = 100000;
                    break;
                }
                i5++;
                i2 = 100000;
            }
            if (i5 != i2) {
                double sqrt3 = FastMath.sqrt(1.5707963267948966d);
                double d19 = sqrt2 + ((d16 * sqrt3) / ((3.0d * d8) * sqrt));
                double d20 = 2.0d * d8;
                double d21 = 6.0d * d3;
                double d22 = d7 * 5.0d;
                double d23 = 0.0d;
                int i6 = 0;
                while (true) {
                    i3 = 100000;
                    d4 = sqrt;
                    if (i6 >= 100000) {
                        break;
                    }
                    double d24 = ((double) i6) + 0.5d;
                    double d25 = d24 * d24;
                    double exp3 = (d21 + d20 + ((d20 - d22) * 9.869604401089358d * d25) + ((1.0d - d15) * 97.40909103400243d * d25 * d25)) * FastMath.exp((d25 * -9.869604401089358d) / d15);
                    d23 += exp3;
                    if (FastMath.abs(exp3) < FastMath.abs(d23) * 1.0E-10d) {
                        i3 = 100000;
                        break;
                    }
                    i6++;
                    sqrt = d4;
                }
                if (i6 != i3) {
                    double d26 = 0.0d;
                    int i7 = 1;
                    while (true) {
                        if (i7 >= i3) {
                            break;
                        }
                        double d27 = (double) (i7 * i7);
                        double exp4 = d27 * 9.869604401089358d * FastMath.exp((d27 * -9.869604401089358d) / d15);
                        d26 += exp4;
                        if (FastMath.abs(exp4) < FastMath.abs(d26) * 1.0E-10d) {
                            i3 = 100000;
                            break;
                        }
                        i7++;
                        i3 = 100000;
                    }
                    if (i7 != i3) {
                        double d28 = d19 + ((sqrt3 / d2) * ((d23 / ((((36.0d * d7) * d7) * d7) * d6)) - (d26 / ((18.0d * d7) * d6))));
                        double d29 = 0.0d;
                        int i8 = 100000;
                        int i9 = 0;
                        while (true) {
                            if (i9 >= i8) {
                                break;
                            }
                            double d30 = ((double) i9) + 0.5d;
                            double d31 = d30 * d30;
                            double d32 = d31 * d31;
                            double d33 = d12;
                            double exp5 = (((((((d32 * d31) * 961.3891935753043d) * (5.0d - (d7 * 30.0d))) + ((d32 * 97.40909103400243d) * ((-60.0d * d7) + (212.0d * d8)))) + ((d31 * 9.869604401089358d) * ((135.0d * d8) - (96.0d * d3)))) - (d3 * 30.0d)) - (90.0d * d33)) * FastMath.exp((d31 * -9.869604401089358d) / d15);
                            d29 += exp5;
                            if (FastMath.abs(exp5) < FastMath.abs(d29) * 1.0E-10d) {
                                i8 = 100000;
                                break;
                            }
                            i9++;
                            d12 = d33;
                            i8 = 100000;
                        }
                        if (i9 != i8) {
                            int i10 = 1;
                            while (true) {
                                if (i10 >= i8) {
                                    break;
                                }
                                double d34 = (double) (i10 * i10);
                                double exp6 = ((d34 * d34 * -97.40909103400243d) + (29.608813203268074d * d34 * d7)) * FastMath.exp((d34 * -9.869604401089358d) / d15);
                                d11 += exp6;
                                if (FastMath.abs(exp6) < FastMath.abs(d11) * 1.0E-10d) {
                                    i8 = 100000;
                                    break;
                                }
                                i10++;
                                i8 = 100000;
                            }
                            if (i10 != i8) {
                                return d28 + ((sqrt3 / (d4 * d2)) * ((d29 / ((3240.0d * d3) * d8)) + (d11 / (108.0d * d3))));
                            }
                            throw new TooManyIterationsException(100000);
                        }
                        throw new TooManyIterationsException(Integer.valueOf(i8));
                    }
                    throw new TooManyIterationsException(Integer.valueOf(i3));
                }
                throw new TooManyIterationsException(Integer.valueOf(i3));
            }
            throw new TooManyIterationsException(Integer.valueOf(i2));
        }
        throw new TooManyIterationsException(Integer.valueOf(i2));
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(20:2|3|4|5|8|(4:10|(3:12|(2:14|46)(2:15|45)|16)|44|17)|43|18|(1:20)|47|21|(1:23)|48|24|(1:26)|27|(3:29|(2:30|(3:32|(3:34|(1:36)|52)(1:51)|37)(1:50))|38)|49|39|40) */
    /* JADX WARNING: Code restructure failed: missing block: B:6:0x0033, code lost:
        r4 = new org.apache.commons.math3.fraction.BigFraction(r0, 1.0E-5d, 10000);
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:4:0x0024 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private org.apache.commons.math3.linear.FieldMatrix<org.apache.commons.math3.fraction.BigFraction> createExactH(double r11, int r13) throws org.apache.commons.math3.exception.NumberIsTooLargeException, org.apache.commons.math3.fraction.FractionConversionException {
        /*
            r10 = this;
            double r0 = (double) r13
            double r0 = r0 * r11
            double r10 = java.lang.Math.ceil(r0)
            int r10 = (int) r10
            int r11 = r10 * 2
            r12 = 1
            int r11 = r11 - r12
            double r2 = (double) r10
            double r0 = r2 - r0
            r2 = 4607182418800017408(0x3ff0000000000000, double:1.0)
            int r10 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            r13 = 0
            if (r10 >= 0) goto L_0x00f1
            org.apache.commons.math3.fraction.BigFraction r10 = new org.apache.commons.math3.fraction.BigFraction     // Catch:{ FractionConversionException -> 0x0024 }
            r7 = 4307583784117748259(0x3bc79ca10c924223, double:1.0E-20)
            r9 = 10000(0x2710, float:1.4013E-41)
            r4 = r10
            r5 = r0
            r4.<init>(r5, r7, r9)     // Catch:{ FractionConversionException -> 0x0024 }
            goto L_0x0041
        L_0x0024:
            org.apache.commons.math3.fraction.BigFraction r10 = new org.apache.commons.math3.fraction.BigFraction     // Catch:{ FractionConversionException -> 0x0033 }
            r7 = 4457293557087583675(0x3ddb7cdfd9d7bdbb, double:1.0E-10)
            r9 = 10000(0x2710, float:1.4013E-41)
            r4 = r10
            r5 = r0
            r4.<init>(r5, r7, r9)     // Catch:{ FractionConversionException -> 0x0033 }
            goto L_0x0041
        L_0x0033:
            org.apache.commons.math3.fraction.BigFraction r10 = new org.apache.commons.math3.fraction.BigFraction
            r7 = 4532020583610935537(0x3ee4f8b588e368f1, double:1.0E-5)
            r9 = 10000(0x2710, float:1.4013E-41)
            r4 = r10
            r5 = r0
            r4.<init>(r5, r7, r9)
        L_0x0041:
            r0 = 2
            int[] r1 = new int[r0]
            r1[r12] = r11
            r1[r13] = r11
            java.lang.Class<org.apache.commons.math3.fraction.BigFraction> r2 = org.apache.commons.math3.fraction.BigFraction.class
            java.lang.Object r1 = java.lang.reflect.Array.newInstance(r2, r1)
            org.apache.commons.math3.fraction.BigFraction[][] r1 = (org.apache.commons.math3.fraction.BigFraction[][]) r1
            r2 = r13
        L_0x0051:
            if (r2 >= r11) goto L_0x006e
            r3 = r13
        L_0x0054:
            if (r3 >= r11) goto L_0x006b
            int r4 = r2 - r3
            int r4 = r4 + r12
            if (r4 >= 0) goto L_0x0062
            r4 = r1[r2]
            org.apache.commons.math3.fraction.BigFraction r5 = org.apache.commons.math3.fraction.BigFraction.ZERO
            r4[r3] = r5
            goto L_0x0068
        L_0x0062:
            r4 = r1[r2]
            org.apache.commons.math3.fraction.BigFraction r5 = org.apache.commons.math3.fraction.BigFraction.ONE
            r4[r3] = r5
        L_0x0068:
            int r3 = r3 + 1
            goto L_0x0054
        L_0x006b:
            int r2 = r2 + 1
            goto L_0x0051
        L_0x006e:
            org.apache.commons.math3.fraction.BigFraction[] r2 = new org.apache.commons.math3.fraction.BigFraction[r11]
            r2[r13] = r10
            r3 = r12
        L_0x0073:
            if (r3 >= r11) goto L_0x0082
            int r4 = r3 + -1
            r4 = r2[r4]
            org.apache.commons.math3.fraction.BigFraction r4 = r10.multiply((org.apache.commons.math3.fraction.BigFraction) r4)
            r2[r3] = r4
            int r3 = r3 + 1
            goto L_0x0073
        L_0x0082:
            r3 = r13
        L_0x0083:
            if (r3 >= r11) goto L_0x00a5
            r4 = r1[r3]
            r5 = r4[r13]
            r6 = r2[r3]
            org.apache.commons.math3.fraction.BigFraction r5 = r5.subtract((org.apache.commons.math3.fraction.BigFraction) r6)
            r4[r13] = r5
            int r4 = r11 + -1
            r4 = r1[r4]
            r5 = r4[r3]
            int r6 = r11 - r3
            int r6 = r6 - r12
            r6 = r2[r6]
            org.apache.commons.math3.fraction.BigFraction r5 = r5.subtract((org.apache.commons.math3.fraction.BigFraction) r6)
            r4[r3] = r5
            int r3 = r3 + 1
            goto L_0x0083
        L_0x00a5:
            org.apache.commons.math3.fraction.BigFraction r2 = org.apache.commons.math3.fraction.BigFraction.ONE_HALF
            int r2 = r10.compareTo((org.apache.commons.math3.fraction.BigFraction) r2)
            if (r2 != r12) goto L_0x00c5
            int r2 = r11 + -1
            r2 = r1[r2]
            r3 = r2[r13]
            org.apache.commons.math3.fraction.BigFraction r10 = r10.multiply((int) r0)
            org.apache.commons.math3.fraction.BigFraction r10 = r10.subtract((int) r12)
            org.apache.commons.math3.fraction.BigFraction r10 = r10.pow((int) r11)
            org.apache.commons.math3.fraction.BigFraction r10 = r3.add((org.apache.commons.math3.fraction.BigFraction) r10)
            r2[r13] = r10
        L_0x00c5:
            r10 = r13
        L_0x00c6:
            if (r10 >= r11) goto L_0x00e7
            r2 = r13
        L_0x00c9:
            int r3 = r10 + 1
            if (r2 >= r3) goto L_0x00e5
            int r3 = r10 - r2
            int r3 = r3 + r12
            if (r3 <= 0) goto L_0x00e2
            r4 = r0
        L_0x00d3:
            if (r4 > r3) goto L_0x00e2
            r5 = r1[r10]
            r6 = r5[r2]
            org.apache.commons.math3.fraction.BigFraction r6 = r6.divide((int) r4)
            r5[r2] = r6
            int r4 = r4 + 1
            goto L_0x00d3
        L_0x00e2:
            int r2 = r2 + 1
            goto L_0x00c9
        L_0x00e5:
            r10 = r3
            goto L_0x00c6
        L_0x00e7:
            org.apache.commons.math3.linear.Array2DRowFieldMatrix r10 = new org.apache.commons.math3.linear.Array2DRowFieldMatrix
            org.apache.commons.math3.fraction.BigFractionField r11 = org.apache.commons.math3.fraction.BigFractionField.getInstance()
            r10.<init>(r11, (T[][]) r1)
            return r10
        L_0x00f1:
            org.apache.commons.math3.exception.NumberIsTooLargeException r10 = new org.apache.commons.math3.exception.NumberIsTooLargeException
            java.lang.Double r11 = java.lang.Double.valueOf(r0)
            java.lang.Double r12 = java.lang.Double.valueOf(r2)
            r10.<init>(r11, r12, r13)
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.createExactH(double, int):org.apache.commons.math3.linear.FieldMatrix");
    }

    private RealMatrix createRoundedH(double d, int i) throws NumberIsTooLargeException {
        int i2;
        double d2 = ((double) i) * d;
        int ceil = (int) Math.ceil(d2);
        int i3 = (ceil * 2) - 1;
        double d3 = ((double) ceil) - d2;
        if (d3 < 1.0d) {
            int[] iArr = new int[2];
            iArr[1] = i3;
            iArr[0] = i3;
            double[][] dArr = (double[][]) Array.newInstance(Double.TYPE, iArr);
            for (int i4 = 0; i4 < i3; i4++) {
                for (int i5 = 0; i5 < i3; i5++) {
                    if ((i4 - i5) + 1 < 0) {
                        dArr[i4][i5] = 0.0d;
                    } else {
                        dArr[i4][i5] = 1.0d;
                    }
                }
            }
            double[] dArr2 = new double[i3];
            dArr2[0] = d3;
            for (int i6 = 1; i6 < i3; i6++) {
                dArr2[i6] = dArr2[i6 - 1] * d3;
            }
            for (int i7 = 0; i7 < i3; i7++) {
                double[] dArr3 = dArr[i7];
                dArr3[0] = dArr3[0] - dArr2[i7];
                double[] dArr4 = dArr[i3 - 1];
                dArr4[i7] = dArr4[i7] - dArr2[(i3 - i7) - 1];
            }
            if (Double.compare(d3, 0.5d) > 0) {
                double[] dArr5 = dArr[i3 - 1];
                dArr5[0] = dArr5[0] + FastMath.pow((d3 * 2.0d) - 1.0d, i3);
            }
            int i8 = 0;
            while (i8 < i3) {
                int i9 = 0;
                while (true) {
                    i2 = i8 + 1;
                    if (i9 >= i2) {
                        break;
                    }
                    int i10 = (i8 - i9) + 1;
                    if (i10 > 0) {
                        for (int i11 = 2; i11 <= i10; i11++) {
                            double[] dArr6 = dArr[i8];
                            dArr6[i9] = dArr6[i9] / ((double) i11);
                        }
                    }
                    i9++;
                }
                i8 = i2;
            }
            return MatrixUtils.createRealMatrix(dArr);
        }
        throw new NumberIsTooLargeException(Double.valueOf(d3), Double.valueOf(1.0d), false);
    }

    private void checkArray(double[] dArr) {
        if (dArr == null) {
            throw new NullArgumentException(LocalizedFormats.NULL_NOT_ALLOWED, new Object[0]);
        } else if (dArr.length < 2) {
            throw new InsufficientDataException(LocalizedFormats.INSUFFICIENT_OBSERVED_POINTS_IN_SAMPLE, Integer.valueOf(dArr.length), 2);
        }
    }

    public double ksSum(double d, double d2, int i) {
        int i2 = i;
        if (d == 0.0d) {
            return 0.0d;
        }
        double d3 = -2.0d * d * d;
        double d4 = 0.5d;
        double d5 = 1.0d;
        long j = 1;
        int i3 = -1;
        while (d5 > d2 && j < ((long) i2)) {
            double d6 = (double) j;
            d5 = FastMath.exp(d3 * d6 * d6);
            d4 += ((double) i3) * d5;
            i3 *= -1;
            j++;
        }
        if (j != ((long) i2)) {
            return d4 * 2.0d;
        }
        throw new TooManyIterationsException(Integer.valueOf(i));
    }

    private static long calculateIntegralD(double d, int i, int i2, boolean z) {
        double d2 = (double) (((long) i) * ((long) i2));
        long ceil = (long) FastMath.ceil((d - 1.0E-12d) * d2);
        return (!z || ((long) FastMath.floor((d + 1.0E-12d) * d2)) != ceil) ? ceil : ceil + 1;
    }

    public double exactP(double d, int i, int i2, boolean z) {
        return 1.0d - (n(i2, i, i2, i, calculateIntegralD(d, i2, i, z), z) / CombinatoricsUtils.binomialCoefficientDouble(i + i2, i2));
    }

    public double approximateP(double d, int i, int i2) {
        double d2 = (double) i2;
        double d3 = (double) i;
        return 1.0d - ksSum(d * FastMath.sqrt((d2 * d3) / (d2 + d3)), KS_SUM_CAUCHY_CRITERION, 100000);
    }

    static void fillBooleanArrayRandomlyWithFixedNumberTrueValues(boolean[] zArr, int i, RandomGenerator randomGenerator) {
        Arrays.fill(zArr, true);
        while (i < zArr.length) {
            int i2 = i + 1;
            int nextInt = randomGenerator.nextInt(i2);
            if (zArr[nextInt]) {
                i = nextInt;
            }
            zArr[i] = false;
            i = i2;
        }
    }

    public double monteCarloP(double d, int i, int i2, boolean z, int i3) {
        return integralMonteCarloP(calculateIntegralD(d, i, i2, z), i, i2, i3);
    }

    private double integralMonteCarloP(long j, int i, int i2, int i3) {
        int max = FastMath.max(i, i2);
        int min = FastMath.min(i, i2);
        int i4 = max + min;
        boolean[] zArr = new boolean[i4];
        int i5 = 0;
        for (int i6 = 0; i6 < i3; i6++) {
            fillBooleanArrayRandomlyWithFixedNumberTrueValues(zArr, max, this.rng);
            long j2 = 0;
            int i7 = 0;
            while (true) {
                if (i7 >= i4) {
                    break;
                } else if (zArr[i7]) {
                    j2 += (long) min;
                    if (j2 >= j) {
                        break;
                    }
                    i7++;
                } else {
                    j2 -= (long) max;
                    if (j2 <= (-j)) {
                        break;
                    }
                    i7++;
                }
            }
            i5++;
        }
        return ((double) i5) / ((double) i3);
    }

    /* JADX WARNING: Removed duplicated region for block: B:16:0x0051 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x0052  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static void fixTies(double[] r13, double[] r14) {
        /*
            r0 = 2
            double[][] r0 = new double[r0][]
            r1 = 0
            r0[r1] = r13
            r2 = 1
            r0[r2] = r14
            double[] r0 = org.apache.commons.math3.util.MathArrays.concatenate(r0)
            double[] r0 = org.apache.commons.math3.util.MathArrays.unique(r0)
            int r3 = r0.length
            int r4 = r13.length
            int r5 = r14.length
            int r4 = r4 + r5
            if (r3 != r4) goto L_0x0018
            return
        L_0x0018:
            r3 = r0[r1]
            r5 = 4607182418800017408(0x3ff0000000000000, double:1.0)
            r7 = r2
        L_0x001d:
            int r8 = r0.length
            if (r7 >= r8) goto L_0x002c
            r8 = r0[r7]
            double r3 = r3 - r8
            int r10 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r10 >= 0) goto L_0x0028
            r5 = r3
        L_0x0028:
            int r7 = r7 + 1
            r3 = r8
            goto L_0x001d
        L_0x002c:
            r3 = 4611686018427387904(0x4000000000000000, double:2.0)
            double r11 = r5 / r3
            org.apache.commons.math3.distribution.UniformRealDistribution r0 = new org.apache.commons.math3.distribution.UniformRealDistribution
            org.apache.commons.math3.random.JDKRandomGenerator r8 = new org.apache.commons.math3.random.JDKRandomGenerator
            r3 = 100
            r8.<init>(r3)
            double r9 = -r11
            r7 = r0
            r7.<init>((org.apache.commons.math3.random.RandomGenerator) r8, (double) r9, (double) r11)
        L_0x003e:
            jitter(r13, r0)
            jitter(r14, r0)
            boolean r3 = hasTies(r13, r14)
            int r1 = r1 + r2
            if (r3 == 0) goto L_0x004f
            r4 = 1000(0x3e8, float:1.401E-42)
            if (r1 < r4) goto L_0x003e
        L_0x004f:
            if (r3 != 0) goto L_0x0052
            return
        L_0x0052:
            org.apache.commons.math3.exception.MathInternalError r13 = new org.apache.commons.math3.exception.MathInternalError
            r13.<init>()
            throw r13
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.fixTies(double[], double[]):void");
    }

    private static boolean hasTies(double[] dArr, double[] dArr2) {
        HashSet hashSet = new HashSet();
        for (double valueOf : dArr) {
            if (!hashSet.add(Double.valueOf(valueOf))) {
                return true;
            }
        }
        for (double valueOf2 : dArr2) {
            if (!hashSet.add(Double.valueOf(valueOf2))) {
                return true;
            }
        }
        return false;
    }

    private static void jitter(double[] dArr, RealDistribution realDistribution) {
        for (int i = 0; i < dArr.length; i++) {
            dArr[i] = dArr[i] + realDistribution.sample();
        }
    }

    private static int c(int i, int i2, int i3, int i4, long j, boolean z) {
        if (z) {
            return FastMath.abs((((long) i) * ((long) i4)) - (((long) i2) * ((long) i3))) <= j ? 1 : 0;
        }
        if (FastMath.abs((((long) i) * ((long) i4)) - (((long) i2) * ((long) i3))) < j) {
            return 1;
        }
        return 0;
    }

    private static double n(int i, int i2, int i3, int i4, long j, boolean z) {
        int i5 = i4;
        double[] dArr = new double[i5];
        int i6 = 0;
        while (i6 < i5) {
            int i7 = i6 + 1;
            dArr[i6] = (double) c(0, i7, i3, i4, j, z);
            i6 = i7;
        }
        double d = 0.0d;
        int i8 = i;
        int i9 = 1;
        while (i9 <= i8) {
            int i10 = i2;
            double c = (double) c(i9, 0, i3, i4, j, z);
            for (int i11 = 1; i11 <= i10; i11++) {
                int i12 = i11 - 1;
                c = (c + dArr[i12]) * ((double) c(i9, i11, i3, i4, j, z));
                dArr[i12] = c;
            }
            i9++;
            d = c;
        }
        return d;
    }
}
