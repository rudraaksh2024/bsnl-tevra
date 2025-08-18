package org.apache.commons.math3.distribution;

import java.io.Serializable;
import org.apache.commons.math3.exception.MathArithmeticException;
import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.fraction.BigFraction;
import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.FieldMatrix;
import org.apache.commons.math3.util.FastMath;

public class KolmogorovSmirnovDistribution implements Serializable {
    private static final long serialVersionUID = -4670676796862967187L;
    private int n;

    public KolmogorovSmirnovDistribution(int i) throws NotStrictlyPositiveException {
        if (i > 0) {
            this.n = i;
            return;
        }
        throw new NotStrictlyPositiveException(LocalizedFormats.NOT_POSITIVE_NUMBER_OF_SAMPLES, Integer.valueOf(i));
    }

    public double cdf(double d) throws MathArithmeticException {
        return cdf(d, false);
    }

    public double cdfExact(double d) throws MathArithmeticException {
        return cdf(d, true);
    }

    public double cdf(double d, boolean z) throws MathArithmeticException {
        int i = this.n;
        double d2 = 1.0d;
        double d3 = 1.0d / ((double) i);
        double d4 = 0.5d * d3;
        if (d <= d4) {
            return 0.0d;
        }
        if (d4 < d && d <= d3) {
            double d5 = (d * 2.0d) - d3;
            for (int i2 = 1; i2 <= this.n; i2++) {
                d2 *= ((double) i2) * d5;
            }
            return d2;
        } else if (1.0d - d3 <= d && d < 1.0d) {
            return 1.0d - (FastMath.pow(1.0d - d, i) * 2.0d);
        } else {
            if (1.0d <= d) {
                return 1.0d;
            }
            return z ? exactK(d) : roundedK(d);
        }
    }

    private double exactK(double d) throws MathArithmeticException {
        FieldMatrix<BigFraction> power = createH(d).power(this.n);
        int ceil = ((int) FastMath.ceil(((double) this.n) * d)) - 1;
        BigFraction entry = power.getEntry(ceil, ceil);
        for (int i = 1; i <= this.n; i++) {
            entry = entry.multiply(i).divide(this.n);
        }
        return entry.bigDecimalValue(20, 4).doubleValue();
    }

    private double roundedK(double d) throws MathArithmeticException {
        int ceil = (int) FastMath.ceil(((double) this.n) * d);
        FieldMatrix<BigFraction> createH = createH(d);
        int rowDimension = createH.getRowDimension();
        Array2DRowRealMatrix array2DRowRealMatrix = new Array2DRowRealMatrix(rowDimension, rowDimension);
        for (int i = 0; i < rowDimension; i++) {
            for (int i2 = 0; i2 < rowDimension; i2++) {
                array2DRowRealMatrix.setEntry(i, i2, createH.getEntry(i, i2).doubleValue());
            }
        }
        int i3 = 1;
        int i4 = ceil - 1;
        double entry = array2DRowRealMatrix.power(this.n).getEntry(i4, i4);
        while (true) {
            int i5 = this.n;
            if (i3 > i5) {
                return entry;
            }
            entry *= ((double) i3) / ((double) i5);
            i3++;
        }
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(20:2|3|4|5|8|(4:10|(3:12|(2:14|46)(2:15|45)|16)|44|17)|43|18|(1:20)|47|21|(1:23)|48|24|(1:26)|27|(3:29|(2:30|(3:32|(3:34|(1:36)|52)(1:51)|37)(1:50))|38)|49|39|40) */
    /* JADX WARNING: Code restructure failed: missing block: B:6:0x0039, code lost:
        r7 = new org.apache.commons.math3.fraction.BigFraction(r13, 1.0E-5d, 10000);
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:4:0x002a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private org.apache.commons.math3.linear.FieldMatrix<org.apache.commons.math3.fraction.BigFraction> createH(double r14) throws org.apache.commons.math3.exception.NumberIsTooLargeException, org.apache.commons.math3.fraction.FractionConversionException {
        /*
            r13 = this;
            int r0 = r13.n
            double r0 = (double) r0
            double r0 = r0 * r14
            double r0 = org.apache.commons.math3.util.FastMath.ceil(r0)
            int r0 = (int) r0
            int r1 = r0 * 2
            r2 = 1
            int r1 = r1 - r2
            double r3 = (double) r0
            int r13 = r13.n
            double r5 = (double) r13
            double r5 = r5 * r14
            double r13 = r3 - r5
            r3 = 4607182418800017408(0x3ff0000000000000, double:1.0)
            int r15 = (r13 > r3 ? 1 : (r13 == r3 ? 0 : -1))
            r0 = 0
            if (r15 >= 0) goto L_0x00f7
            org.apache.commons.math3.fraction.BigFraction r15 = new org.apache.commons.math3.fraction.BigFraction     // Catch:{ FractionConversionException -> 0x002a }
            r10 = 4307583784117748259(0x3bc79ca10c924223, double:1.0E-20)
            r12 = 10000(0x2710, float:1.4013E-41)
            r7 = r15
            r8 = r13
            r7.<init>(r8, r10, r12)     // Catch:{ FractionConversionException -> 0x002a }
            goto L_0x0047
        L_0x002a:
            org.apache.commons.math3.fraction.BigFraction r15 = new org.apache.commons.math3.fraction.BigFraction     // Catch:{ FractionConversionException -> 0x0039 }
            r10 = 4457293557087583675(0x3ddb7cdfd9d7bdbb, double:1.0E-10)
            r12 = 10000(0x2710, float:1.4013E-41)
            r7 = r15
            r8 = r13
            r7.<init>(r8, r10, r12)     // Catch:{ FractionConversionException -> 0x0039 }
            goto L_0x0047
        L_0x0039:
            org.apache.commons.math3.fraction.BigFraction r15 = new org.apache.commons.math3.fraction.BigFraction
            r10 = 4532020583610935537(0x3ee4f8b588e368f1, double:1.0E-5)
            r12 = 10000(0x2710, float:1.4013E-41)
            r7 = r15
            r8 = r13
            r7.<init>(r8, r10, r12)
        L_0x0047:
            r13 = 2
            int[] r14 = new int[r13]
            r14[r2] = r1
            r14[r0] = r1
            java.lang.Class<org.apache.commons.math3.fraction.BigFraction> r3 = org.apache.commons.math3.fraction.BigFraction.class
            java.lang.Object r14 = java.lang.reflect.Array.newInstance(r3, r14)
            org.apache.commons.math3.fraction.BigFraction[][] r14 = (org.apache.commons.math3.fraction.BigFraction[][]) r14
            r3 = r0
        L_0x0057:
            if (r3 >= r1) goto L_0x0074
            r4 = r0
        L_0x005a:
            if (r4 >= r1) goto L_0x0071
            int r5 = r3 - r4
            int r5 = r5 + r2
            if (r5 >= 0) goto L_0x0068
            r5 = r14[r3]
            org.apache.commons.math3.fraction.BigFraction r6 = org.apache.commons.math3.fraction.BigFraction.ZERO
            r5[r4] = r6
            goto L_0x006e
        L_0x0068:
            r5 = r14[r3]
            org.apache.commons.math3.fraction.BigFraction r6 = org.apache.commons.math3.fraction.BigFraction.ONE
            r5[r4] = r6
        L_0x006e:
            int r4 = r4 + 1
            goto L_0x005a
        L_0x0071:
            int r3 = r3 + 1
            goto L_0x0057
        L_0x0074:
            org.apache.commons.math3.fraction.BigFraction[] r3 = new org.apache.commons.math3.fraction.BigFraction[r1]
            r3[r0] = r15
            r4 = r2
        L_0x0079:
            if (r4 >= r1) goto L_0x0088
            int r5 = r4 + -1
            r5 = r3[r5]
            org.apache.commons.math3.fraction.BigFraction r5 = r15.multiply((org.apache.commons.math3.fraction.BigFraction) r5)
            r3[r4] = r5
            int r4 = r4 + 1
            goto L_0x0079
        L_0x0088:
            r4 = r0
        L_0x0089:
            if (r4 >= r1) goto L_0x00ab
            r5 = r14[r4]
            r6 = r5[r0]
            r7 = r3[r4]
            org.apache.commons.math3.fraction.BigFraction r6 = r6.subtract((org.apache.commons.math3.fraction.BigFraction) r7)
            r5[r0] = r6
            int r5 = r1 + -1
            r5 = r14[r5]
            r6 = r5[r4]
            int r7 = r1 - r4
            int r7 = r7 - r2
            r7 = r3[r7]
            org.apache.commons.math3.fraction.BigFraction r6 = r6.subtract((org.apache.commons.math3.fraction.BigFraction) r7)
            r5[r4] = r6
            int r4 = r4 + 1
            goto L_0x0089
        L_0x00ab:
            org.apache.commons.math3.fraction.BigFraction r3 = org.apache.commons.math3.fraction.BigFraction.ONE_HALF
            int r3 = r15.compareTo((org.apache.commons.math3.fraction.BigFraction) r3)
            if (r3 != r2) goto L_0x00cb
            int r3 = r1 + -1
            r3 = r14[r3]
            r4 = r3[r0]
            org.apache.commons.math3.fraction.BigFraction r15 = r15.multiply((int) r13)
            org.apache.commons.math3.fraction.BigFraction r15 = r15.subtract((int) r2)
            org.apache.commons.math3.fraction.BigFraction r15 = r15.pow((int) r1)
            org.apache.commons.math3.fraction.BigFraction r15 = r4.add((org.apache.commons.math3.fraction.BigFraction) r15)
            r3[r0] = r15
        L_0x00cb:
            r15 = r0
        L_0x00cc:
            if (r15 >= r1) goto L_0x00ed
            r3 = r0
        L_0x00cf:
            int r4 = r15 + 1
            if (r3 >= r4) goto L_0x00eb
            int r4 = r15 - r3
            int r4 = r4 + r2
            if (r4 <= 0) goto L_0x00e8
            r5 = r13
        L_0x00d9:
            if (r5 > r4) goto L_0x00e8
            r6 = r14[r15]
            r7 = r6[r3]
            org.apache.commons.math3.fraction.BigFraction r7 = r7.divide((int) r5)
            r6[r3] = r7
            int r5 = r5 + 1
            goto L_0x00d9
        L_0x00e8:
            int r3 = r3 + 1
            goto L_0x00cf
        L_0x00eb:
            r15 = r4
            goto L_0x00cc
        L_0x00ed:
            org.apache.commons.math3.linear.Array2DRowFieldMatrix r13 = new org.apache.commons.math3.linear.Array2DRowFieldMatrix
            org.apache.commons.math3.fraction.BigFractionField r15 = org.apache.commons.math3.fraction.BigFractionField.getInstance()
            r13.<init>(r15, (T[][]) r14)
            return r13
        L_0x00f7:
            org.apache.commons.math3.exception.NumberIsTooLargeException r15 = new org.apache.commons.math3.exception.NumberIsTooLargeException
            java.lang.Double r13 = java.lang.Double.valueOf(r13)
            java.lang.Double r14 = java.lang.Double.valueOf(r3)
            r15.<init>(r13, r14, r0)
            throw r15
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.math3.distribution.KolmogorovSmirnovDistribution.createH(double):org.apache.commons.math3.linear.FieldMatrix");
    }
}
