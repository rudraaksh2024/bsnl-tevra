package org.apache.commons.math3.stat.regression;

import java.util.Arrays;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.MathArrays;
import org.apache.commons.math3.util.Precision;

public class MillerUpdatingRegression implements UpdatingMultipleLinearRegression {
    private final double[] d;
    private final double epsilon;
    private boolean hasIntercept;
    private final boolean[] lindep;
    private long nobs;
    private final int nvars;
    private final double[] r;
    private final double[] rhs;
    private final double[] rss;
    private boolean rss_set;
    private double sserr;
    private double sumsqy;
    private double sumy;
    private final double[] tol;
    private boolean tol_set;
    private final int[] vorder;
    private final double[] work_sing;
    private final double[] work_tolset;
    private final double[] x_sing;

    private MillerUpdatingRegression() {
        this(-1, false, Double.NaN);
    }

    public MillerUpdatingRegression(int i, boolean z, double d2) throws ModelSpecificationException {
        this.nobs = 0;
        this.sserr = 0.0d;
        this.rss_set = false;
        this.tol_set = false;
        this.sumy = 0.0d;
        this.sumsqy = 0.0d;
        if (i >= 1) {
            if (z) {
                this.nvars = i + 1;
            } else {
                this.nvars = i;
            }
            this.hasIntercept = z;
            this.nobs = 0;
            int i2 = this.nvars;
            this.d = new double[i2];
            this.rhs = new double[i2];
            this.r = new double[(((i2 - 1) * i2) / 2)];
            this.tol = new double[i2];
            this.rss = new double[i2];
            this.vorder = new int[i2];
            this.x_sing = new double[i2];
            this.work_sing = new double[i2];
            this.work_tolset = new double[i2];
            this.lindep = new boolean[i2];
            for (int i3 = 0; i3 < this.nvars; i3++) {
                this.vorder[i3] = i3;
            }
            if (d2 > 0.0d) {
                this.epsilon = d2;
            } else {
                this.epsilon = -d2;
            }
        } else {
            throw new ModelSpecificationException(LocalizedFormats.NO_REGRESSORS, new Object[0]);
        }
    }

    public MillerUpdatingRegression(int i, boolean z) throws ModelSpecificationException {
        this(i, z, Precision.EPSILON);
    }

    public boolean hasIntercept() {
        return this.hasIntercept;
    }

    public long getN() {
        return this.nobs;
    }

    public void addObservation(double[] dArr, double d2) throws ModelSpecificationException {
        boolean z = this.hasIntercept;
        if ((z || dArr.length == this.nvars) && (!z || dArr.length + 1 == this.nvars)) {
            if (!z) {
                include(MathArrays.copyOf(dArr, dArr.length), 1.0d, d2);
            } else {
                double[] dArr2 = new double[(dArr.length + 1)];
                System.arraycopy(dArr, 0, dArr2, 1, dArr.length);
                dArr2[0] = 1.0d;
                include(dArr2, 1.0d, d2);
            }
            this.nobs++;
            return;
        }
        throw new ModelSpecificationException(LocalizedFormats.INVALID_REGRESSION_OBSERVATION, Integer.valueOf(dArr.length), Integer.valueOf(this.nvars));
    }

    public void addObservations(double[][] dArr, double[] dArr2) throws ModelSpecificationException {
        int i = 0;
        if (dArr == null || dArr2 == null || dArr.length != dArr2.length) {
            LocalizedFormats localizedFormats = LocalizedFormats.DIMENSIONS_MISMATCH_SIMPLE;
            Object[] objArr = new Object[2];
            objArr[0] = Integer.valueOf(dArr == null ? 0 : dArr.length);
            if (dArr2 != null) {
                i = dArr2.length;
            }
            objArr[1] = Integer.valueOf(i);
            throw new ModelSpecificationException(localizedFormats, objArr);
        } else if (dArr.length == 0) {
            throw new ModelSpecificationException(LocalizedFormats.NO_DATA, new Object[0]);
        } else if (dArr[0].length + 1 <= dArr.length) {
            while (i < dArr.length) {
                addObservation(dArr[i], dArr2[i]);
                i++;
            }
        } else {
            throw new ModelSpecificationException(LocalizedFormats.NOT_ENOUGH_DATA_FOR_NUMBER_OF_PREDICTORS, Integer.valueOf(dArr.length), Integer.valueOf(dArr[0].length));
        }
    }

    private void include(double[] dArr, double d2, double d3) {
        double d4;
        double[] dArr2 = dArr;
        double d5 = d3;
        this.rss_set = false;
        this.sumy = smartAdd(d5, this.sumy);
        this.sumsqy = smartAdd(this.sumsqy, d5 * d5);
        int i = 0;
        int i2 = 0;
        double d6 = d5;
        double d7 = d2;
        while (i < dArr2.length) {
            double d8 = 0.0d;
            if (d7 != 0.0d) {
                double d9 = dArr2[i];
                if (d9 == 0.0d) {
                    i2 += (this.nvars - i) - 1;
                } else {
                    double d10 = this.d[i];
                    double d11 = d7 * d9;
                    int i3 = (d10 > 0.0d ? 1 : (d10 == 0.0d ? 0 : -1));
                    if (i3 != 0) {
                        double d12 = d11 * d9;
                        d4 = smartAdd(d10, d12);
                        if (FastMath.abs(d12 / d10) > Precision.EPSILON) {
                            d7 = (d7 * d10) / d4;
                        }
                        d8 = d7;
                    } else {
                        d4 = d11 * d9;
                    }
                    this.d[i] = d4;
                    int i4 = i + 1;
                    while (i4 < this.nvars) {
                        double d13 = d8;
                        double d14 = dArr2[i4];
                        double d15 = d6;
                        dArr2[i4] = smartAdd(d14, (-d9) * this.r[i2]);
                        if (i3 != 0) {
                            double[] dArr3 = this.r;
                            dArr3[i2] = smartAdd(dArr3[i2] * d10, d14 * d11) / d4;
                        } else {
                            this.r[i2] = d14 / d9;
                        }
                        i2++;
                        i4++;
                        d8 = d13;
                        d6 = d15;
                    }
                    double d16 = d8;
                    double d17 = d6;
                    double smartAdd = smartAdd(d17, (-d9) * this.rhs[i]);
                    if (i3 != 0) {
                        double[] dArr4 = this.rhs;
                        dArr4[i] = smartAdd(d10 * dArr4[i], d11 * d17) / d4;
                    } else {
                        this.rhs[i] = d17 / d9;
                    }
                    d6 = smartAdd;
                    d7 = d16;
                }
                i++;
            } else {
                return;
            }
        }
        this.sserr = smartAdd(this.sserr, d7 * d6 * d6);
    }

    private double smartAdd(double d2, double d3) {
        double abs = FastMath.abs(d2);
        double abs2 = FastMath.abs(d3);
        return abs > abs2 ? abs2 > abs * Precision.EPSILON ? d2 + d3 : d2 : abs > abs2 * Precision.EPSILON ? d2 + d3 : d3;
    }

    public void clear() {
        Arrays.fill(this.d, 0.0d);
        Arrays.fill(this.rhs, 0.0d);
        Arrays.fill(this.r, 0.0d);
        Arrays.fill(this.tol, 0.0d);
        Arrays.fill(this.rss, 0.0d);
        Arrays.fill(this.work_tolset, 0.0d);
        Arrays.fill(this.work_sing, 0.0d);
        Arrays.fill(this.x_sing, 0.0d);
        Arrays.fill(this.lindep, false);
        for (int i = 0; i < this.nvars; i++) {
            this.vorder[i] = i;
        }
        this.nobs = 0;
        this.sserr = 0.0d;
        this.sumy = 0.0d;
        this.sumsqy = 0.0d;
        this.rss_set = false;
        this.tol_set = false;
    }

    private void tolset() {
        double d2 = this.epsilon;
        for (int i = 0; i < this.nvars; i++) {
            this.work_tolset[i] = FastMath.sqrt(this.d[i]);
        }
        this.tol[0] = this.work_tolset[0] * d2;
        for (int i2 = 1; i2 < this.nvars; i2++) {
            int i3 = i2 - 1;
            double d3 = this.work_tolset[i2];
            for (int i4 = 0; i4 < i2; i4++) {
                d3 += FastMath.abs(this.r[i3]) * this.work_tolset[i4];
                i3 += (this.nvars - i4) - 2;
            }
            this.tol[i2] = d3 * d2;
        }
        this.tol_set = true;
    }

    private double[] regcf(int i) throws ModelSpecificationException {
        if (i < 1) {
            throw new ModelSpecificationException(LocalizedFormats.NO_REGRESSORS, new Object[0]);
        } else if (i <= this.nvars) {
            if (!this.tol_set) {
                tolset();
            }
            double[] dArr = new double[i];
            boolean z = false;
            for (int i2 = i - 1; i2 > -1; i2--) {
                if (FastMath.sqrt(this.d[i2]) < this.tol[i2]) {
                    dArr[i2] = 0.0d;
                    this.d[i2] = 0.0d;
                    z = true;
                } else {
                    dArr[i2] = this.rhs[i2];
                    int i3 = this.nvars;
                    int i4 = ((((i3 + i3) - i2) - 1) * i2) / 2;
                    for (int i5 = i2 + 1; i5 < i; i5++) {
                        dArr[i2] = smartAdd(dArr[i2], (-this.r[i4]) * dArr[i5]);
                        i4++;
                    }
                }
            }
            if (z) {
                for (int i6 = 0; i6 < i; i6++) {
                    if (this.lindep[i6]) {
                        dArr[i6] = Double.NaN;
                    }
                }
            }
            return dArr;
        } else {
            throw new ModelSpecificationException(LocalizedFormats.TOO_MANY_REGRESSORS, Integer.valueOf(i), Integer.valueOf(this.nvars));
        }
    }

    private void singcheck() {
        for (int i = 0; i < this.nvars; i++) {
            this.work_sing[i] = FastMath.sqrt(this.d[i]);
        }
        for (int i2 = 0; i2 < this.nvars; i2++) {
            double d2 = this.tol[i2];
            int i3 = i2 - 1;
            int i4 = i3;
            for (int i5 = 0; i5 < i3; i5++) {
                if (FastMath.abs(this.r[i4]) * this.work_sing[i5] < d2) {
                    this.r[i4] = 0.0d;
                }
                i4 += (this.nvars - i5) - 2;
            }
            boolean[] zArr = this.lindep;
            zArr[i2] = false;
            if (this.work_sing[i2] < d2) {
                zArr[i2] = true;
                if (i2 < this.nvars - 1) {
                    Arrays.fill(this.x_sing, 0.0d);
                    int i6 = this.nvars;
                    int i7 = ((((i6 + i6) - i2) - 1) * i2) / 2;
                    int i8 = i2 + 1;
                    while (i8 < this.nvars) {
                        double[] dArr = this.x_sing;
                        double[] dArr2 = this.r;
                        dArr[i8] = dArr2[i7];
                        dArr2[i7] = 0.0d;
                        i8++;
                        i7++;
                    }
                    double[] dArr3 = this.rhs;
                    double d3 = dArr3[i2];
                    double[] dArr4 = this.d;
                    double d4 = dArr4[i2];
                    dArr4[i2] = 0.0d;
                    dArr3[i2] = 0.0d;
                    include(this.x_sing, d4, d3);
                } else {
                    double d5 = this.sserr;
                    double d6 = this.d[i2];
                    double d7 = this.rhs[i2];
                    this.sserr = d5 + (d6 * d7 * d7);
                }
            }
        }
    }

    private void ss() {
        double d2 = this.sserr;
        double[] dArr = this.rss;
        int i = this.nvars;
        dArr[i - 1] = d2;
        for (int i2 = i - 1; i2 > 0; i2--) {
            double d3 = this.d[i2];
            double d4 = this.rhs[i2];
            d2 += d3 * d4 * d4;
            this.rss[i2 - 1] = d2;
        }
        this.rss_set = true;
    }

    private double[] cov(int i) {
        double d2;
        double d3;
        int i2 = i;
        if (this.nobs <= ((long) i2)) {
            return null;
        }
        int i3 = 0;
        double d4 = 0.0d;
        int i4 = 0;
        while (true) {
            d2 = 1.0d;
            if (i4 >= i2) {
                break;
            }
            if (!this.lindep[i4]) {
                d4 += 1.0d;
            }
            i4++;
        }
        int i5 = i2 - 1;
        double d5 = this.rss[i5] / (((double) this.nobs) - d4);
        double[] dArr = new double[((i2 * i5) / 2)];
        inverse(dArr, i2);
        double[] dArr2 = new double[(((i2 + 1) * i2) / 2)];
        Arrays.fill(dArr2, Double.NaN);
        int i6 = 0;
        while (i3 < i2) {
            if (!this.lindep[i3]) {
                int i7 = i3;
                int i8 = i6;
                while (i7 < i2) {
                    if (!this.lindep[i7]) {
                        int i9 = (i6 + i7) - i3;
                        if (i3 == i7) {
                            d3 = d2 / this.d[i7];
                        } else {
                            d3 = dArr[i9 - 1] / this.d[i7];
                        }
                        int i10 = i7 + 1;
                        for (int i11 = i10; i11 < i2; i11++) {
                            if (!this.lindep[i11]) {
                                d3 += (dArr[i9] * dArr[i8]) / this.d[i11];
                            }
                            i9++;
                            i8++;
                        }
                        dArr2[((i10 * i7) / 2) + i3] = d3 * d5;
                    } else {
                        i8 += (i2 - i7) - 1;
                    }
                    i7++;
                    d2 = 1.0d;
                }
            }
            i6 += (i2 - i3) - 1;
            i3++;
            d2 = 1.0d;
        }
        return dArr2;
    }

    private void inverse(double[] dArr, int i) {
        int i2 = i - 1;
        int i3 = ((i * i2) / 2) - 1;
        Arrays.fill(dArr, Double.NaN);
        while (i2 > 0) {
            if (!this.lindep[i2]) {
                int i4 = this.nvars;
                int i5 = ((i2 - 1) * ((i4 + i4) - i2)) / 2;
                for (int i6 = i; i6 > i2; i6--) {
                    double d2 = 0.0d;
                    int i7 = i3;
                    int i8 = i5;
                    for (int i9 = i2; i9 < i6 - 1; i9++) {
                        i7 += (i - i9) - 1;
                        if (!this.lindep[i9]) {
                            d2 += (-this.r[i8]) * dArr[i7];
                        }
                        i8++;
                    }
                    dArr[i3] = d2 - this.r[i8];
                    i3--;
                }
            } else {
                i3 -= i - i2;
            }
            i2--;
        }
    }

    public double[] getPartialCorrelations(int i) {
        int i2;
        int i3 = i;
        int i4 = this.nvars;
        double[] dArr = new double[((((i4 - i3) + 1) * (i4 - i3)) / 2)];
        int i5 = -i3;
        int i6 = i3 + 1;
        int i7 = -i6;
        double[] dArr2 = new double[(i4 - i3)];
        double[] dArr3 = new double[((i4 - i3) - 1)];
        int i8 = ((i4 - i3) * ((i4 - i3) - 1)) / 2;
        if (i3 < -1 || i3 >= i4) {
            return null;
        }
        int i9 = (i4 - 1) - i3;
        int length = this.r.length - ((i9 * (i9 + 1)) / 2);
        double d2 = this.d[i3];
        double d3 = 0.0d;
        if (d2 > 0.0d) {
            dArr2[i3 + i5] = 1.0d / FastMath.sqrt(d2);
        }
        while (i6 < this.nvars) {
            int i10 = ((length + i6) - 1) - i3;
            double d4 = this.d[i6];
            int i11 = i3;
            while (i11 < i6) {
                double d5 = this.d[i11];
                double d6 = this.r[i10];
                d4 += d5 * d6 * d6;
                i10 += (this.nvars - i11) - 2;
                i11++;
                i8 = i8;
                d3 = 0.0d;
            }
            int i12 = i8;
            if (d4 > d3) {
                dArr2[i6 + i5] = 1.0d / FastMath.sqrt(d4);
            } else {
                dArr2[i6 + i5] = d3;
            }
            i6++;
            i8 = i12;
            d3 = 0.0d;
        }
        int i13 = i8;
        double d7 = this.sserr;
        for (int i14 = i3; i14 < this.nvars; i14++) {
            double d8 = this.d[i14];
            double d9 = this.rhs[i14];
            d7 += d8 * d9 * d9;
        }
        double d10 = 0.0d;
        if (d7 > 0.0d) {
            d7 = 1.0d / FastMath.sqrt(d7);
        }
        int i15 = i3;
        while (i15 < this.nvars) {
            Arrays.fill(dArr3, d10);
            int i16 = ((length + i15) - i3) - 1;
            int i17 = i3;
            double d11 = d10;
            while (i17 < i15) {
                int i18 = i16 + 1;
                int i19 = i15 + 1;
                while (true) {
                    i2 = this.nvars;
                    if (i19 >= i2) {
                        break;
                    }
                    int i20 = i19 + i7;
                    double d12 = dArr3[i20];
                    int i21 = length;
                    double d13 = this.d[i17];
                    double[] dArr4 = this.r;
                    dArr3[i20] = d12 + (d13 * dArr4[i16] * dArr4[i18]);
                    i18++;
                    i19++;
                    length = i21;
                }
                d11 += this.d[i17] * this.r[i16] * this.rhs[i17];
                i16 += (i2 - i17) - 2;
                i17++;
                length = length;
            }
            int i22 = length;
            int i23 = i16 + 1;
            int i24 = i15 + 1;
            int i25 = i24;
            while (i25 < this.nvars) {
                int i26 = i25 + i7;
                double d14 = dArr3[i26] + (this.d[i15] * this.r[i23]);
                dArr3[i26] = d14;
                i23++;
                dArr[(((((i25 - 1) - i3) * (i25 - i3)) / 2) + i15) - i3] = d14 * dArr2[i15 + i5] * dArr2[i25 + i5];
                i25++;
                i7 = i7;
            }
            double d15 = d11 + (this.d[i15] * this.rhs[i15]);
            int i27 = i15 + i5;
            dArr[i27 + i13] = d15 * dArr2[i27] * d7;
            i15 = i24;
            i7 = i7;
            length = i22;
            d10 = 0.0d;
        }
        return dArr;
    }

    /* JADX WARNING: Removed duplicated region for block: B:40:0x00e3  */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x0131  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void vmove(int r26, int r27) {
        /*
            r25 = this;
            r0 = r25
            r1 = r26
            r2 = r27
            if (r1 != r2) goto L_0x0009
            return
        L_0x0009:
            boolean r3 = r0.rss_set
            if (r3 != 0) goto L_0x0010
            r25.ss()
        L_0x0010:
            r3 = 1
            if (r1 >= r2) goto L_0x0017
            int r2 = r2 - r1
            r4 = r2
            r2 = r3
            goto L_0x0022
        L_0x0017:
            int r4 = r1 + -1
            int r2 = r1 - r2
            r1 = -1
            r24 = r2
            r2 = r1
            r1 = r4
            r4 = r24
        L_0x0022:
            r6 = 0
            r7 = 0
        L_0x0024:
            if (r6 >= r4) goto L_0x017e
            int r8 = r0.nvars
            int r9 = r8 + r8
            int r9 = r9 - r1
            int r9 = r9 - r3
            int r9 = r9 * r1
            int r9 = r9 / 2
            int r8 = r8 + r9
            int r8 = r8 - r1
            int r8 = r8 - r3
            int r10 = r1 + 1
            double[] r11 = r0.d
            r12 = r11[r1]
            r14 = r11[r10]
            r11 = r6
            double r5 = r0.epsilon
            int r16 = (r12 > r5 ? 1 : (r12 == r5 ? 0 : -1))
            if (r16 > 0) goto L_0x004b
            int r5 = (r14 > r5 ? 1 : (r14 == r5 ? 0 : -1))
            if (r5 <= 0) goto L_0x0046
            goto L_0x004b
        L_0x0046:
            r12 = r3
            r19 = r4
            goto L_0x0136
        L_0x004b:
            double[] r5 = r0.r
            r5 = r5[r9]
            double r16 = org.apache.commons.math3.util.FastMath.abs((double) r5)
            double r18 = org.apache.commons.math3.util.FastMath.sqrt(r12)
            double r16 = r16 * r18
            double[] r3 = r0.tol
            r19 = r3[r10]
            int r3 = (r16 > r19 ? 1 : (r16 == r19 ? 0 : -1))
            r16 = 0
            r19 = r4
            if (r3 >= 0) goto L_0x0067
            r5 = r16
        L_0x0067:
            double r3 = r0.epsilon
            int r3 = (r12 > r3 ? 1 : (r12 == r3 ? 0 : -1))
            if (r3 < 0) goto L_0x00b1
            double r3 = org.apache.commons.math3.util.FastMath.abs((double) r5)
            r27 = r7
            r20 = r8
            double r7 = r0.epsilon
            int r3 = (r3 > r7 ? 1 : (r3 == r7 ? 0 : -1))
            if (r3 >= 0) goto L_0x007c
            goto L_0x00b3
        L_0x007c:
            int r3 = (r14 > r7 ? 1 : (r14 == r7 ? 0 : -1))
            if (r3 >= 0) goto L_0x00ac
            double[] r3 = r0.d
            double r7 = r12 * r5
            double r7 = r7 * r5
            r3[r1] = r7
            double[] r3 = r0.r
            r7 = 4607182418800017408(0x3ff0000000000000, double:1.0)
            double r7 = r7 / r5
            r3[r9] = r7
            int r3 = r9 + 1
        L_0x0090:
            int r4 = r0.nvars
            int r4 = r4 + r9
            int r4 = r4 - r1
            r7 = 1
            int r4 = r4 - r7
            if (r3 >= r4) goto L_0x00a2
            double[] r4 = r0.r
            r7 = r4[r3]
            double r7 = r7 / r5
            r4[r3] = r7
            int r3 = r3 + 1
            goto L_0x0090
        L_0x00a2:
            double[] r3 = r0.rhs
            r7 = r3[r1]
            double r7 = r7 / r5
            r3[r1] = r7
            r8 = r20
            goto L_0x00e0
        L_0x00ac:
            r7 = r27
            r8 = r20
            goto L_0x00e1
        L_0x00b1:
            r20 = r8
        L_0x00b3:
            double[] r3 = r0.d
            r3[r1] = r14
            r3[r10] = r12
            double[] r3 = r0.r
            r3[r9] = r16
            int r3 = r1 + 2
            r8 = r20
        L_0x00c1:
            int r4 = r0.nvars
            if (r3 >= r4) goto L_0x00d6
            int r9 = r9 + 1
            double[] r4 = r0.r
            r5 = r4[r9]
            r16 = r4[r8]
            r4[r9] = r16
            r4[r8] = r5
            int r8 = r8 + 1
            int r3 = r3 + 1
            goto L_0x00c1
        L_0x00d6:
            double[] r3 = r0.rhs
            r5 = r3[r1]
            r16 = r3[r10]
            r3[r1] = r16
            r3[r10] = r5
        L_0x00e0:
            r7 = 1
        L_0x00e1:
            if (r7 != 0) goto L_0x0131
            double r3 = r12 * r5
            double r16 = r3 * r5
            double r16 = r14 + r16
            double r14 = r14 / r16
            double r3 = r3 / r16
            double r12 = r12 * r14
            r27 = r7
            double[] r7 = r0.d
            r7[r1] = r16
            r7[r10] = r12
            double[] r7 = r0.r
            r7[r9] = r3
            int r7 = r1 + 2
        L_0x00fc:
            int r12 = r0.nvars
            if (r7 >= r12) goto L_0x011d
            r12 = 1
            int r9 = r9 + r12
            double[] r12 = r0.r
            r16 = r12[r9]
            r20 = r12[r8]
            double r20 = r20 * r14
            double r22 = r3 * r16
            double r20 = r20 + r22
            r12[r9] = r20
            r20 = r12[r8]
            double r20 = r20 * r5
            double r16 = r16 - r20
            r12[r8] = r16
            r12 = 1
            int r8 = r8 + r12
            int r7 = r7 + 1
            goto L_0x00fc
        L_0x011d:
            r12 = 1
            double[] r7 = r0.rhs
            r8 = r7[r1]
            r16 = r7[r10]
            double r14 = r14 * r16
            double r3 = r3 * r8
            double r14 = r14 + r3
            r7[r1] = r14
            r3 = r7[r10]
            double r5 = r5 * r3
            double r8 = r8 - r5
            r7[r10] = r8
            goto L_0x0134
        L_0x0131:
            r27 = r7
            r12 = 1
        L_0x0134:
            r7 = r27
        L_0x0136:
            if (r1 <= 0) goto L_0x0151
            r4 = r1
            r3 = 0
        L_0x013a:
            if (r3 >= r1) goto L_0x0151
            double[] r5 = r0.r
            r8 = r5[r4]
            int r6 = r4 + -1
            r13 = r5[r6]
            r5[r4] = r13
            r5[r6] = r8
            int r5 = r0.nvars
            int r5 = r5 - r3
            int r5 = r5 + -2
            int r4 = r4 + r5
            int r3 = r3 + 1
            goto L_0x013a
        L_0x0151:
            int[] r3 = r0.vorder
            r4 = r3[r1]
            r5 = r3[r10]
            r3[r1] = r5
            r3[r10] = r4
            double[] r3 = r0.tol
            r4 = r3[r1]
            r8 = r3[r10]
            r3[r1] = r8
            r3[r10] = r4
            double[] r3 = r0.rss
            r4 = r3[r10]
            double[] r6 = r0.d
            r8 = r6[r10]
            double[] r6 = r0.rhs
            r13 = r6[r10]
            double r8 = r8 * r13
            double r8 = r8 * r13
            double r4 = r4 + r8
            r3[r1] = r4
            int r1 = r1 + r2
            int r6 = r11 + 1
            r3 = r12
            r4 = r19
            goto L_0x0024
        L_0x017e:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.math3.stat.regression.MillerUpdatingRegression.vmove(int, int):void");
    }

    private int reorderRegressors(int[] iArr, int i) {
        if (iArr.length < 1 || iArr.length > (this.nvars + 1) - i) {
            return -1;
        }
        int i2 = i;
        int i3 = i2;
        while (i2 < this.nvars) {
            int i4 = this.vorder[i2];
            int i5 = 0;
            while (true) {
                if (i5 >= iArr.length) {
                    break;
                } else if (i4 != iArr[i5] || i2 <= i3) {
                    i5++;
                } else {
                    vmove(i2, i3);
                    i3++;
                    if (i3 >= iArr.length + i) {
                        return 0;
                    }
                }
            }
            i2++;
        }
        return 0;
    }

    public double getDiagonalOfHatMatrix(double[] dArr) {
        double[] dArr2 = dArr;
        int i = this.nvars;
        double[] dArr3 = new double[i];
        if (dArr2.length > i) {
            return Double.NaN;
        }
        if (this.hasIntercept) {
            double[] dArr4 = new double[(dArr2.length + 1)];
            dArr4[0] = 1.0d;
            System.arraycopy(dArr2, 0, dArr4, 1, dArr2.length);
            dArr2 = dArr4;
        }
        double d2 = 0.0d;
        for (int i2 = 0; i2 < dArr2.length; i2++) {
            if (FastMath.sqrt(this.d[i2]) < this.tol[i2]) {
                dArr3[i2] = 0.0d;
            } else {
                int i3 = i2 - 1;
                double d3 = dArr2[i2];
                for (int i4 = 0; i4 < i2; i4++) {
                    d3 = smartAdd(d3, (-dArr3[i4]) * this.r[i3]);
                    i3 += (this.nvars - i4) - 2;
                }
                dArr3[i2] = d3;
                d2 = smartAdd(d2, (d3 * d3) / this.d[i2]);
            }
        }
        return d2;
    }

    public int[] getOrderOfRegressors() {
        return MathArrays.copyOf(this.vorder);
    }

    public RegressionResults regress() throws ModelSpecificationException {
        return regress(this.nvars);
    }

    public RegressionResults regress(int i) throws ModelSpecificationException {
        boolean z;
        int i2;
        int i3 = i;
        if (this.nobs <= ((long) i3)) {
            throw new ModelSpecificationException(LocalizedFormats.NOT_ENOUGH_DATA_FOR_NUMBER_OF_PREDICTORS, Long.valueOf(this.nobs), Integer.valueOf(i));
        } else if (i3 <= this.nvars) {
            tolset();
            singcheck();
            double[] regcf = regcf(i);
            ss();
            double[] cov = cov(i);
            int i4 = 0;
            int i5 = 0;
            while (true) {
                boolean[] zArr = this.lindep;
                if (i4 >= zArr.length) {
                    break;
                }
                if (!zArr[i4]) {
                    i5++;
                }
                i4++;
            }
            int i6 = 0;
            while (true) {
                if (i6 >= i3) {
                    z = false;
                    break;
                } else if (this.vorder[i6] != i6) {
                    z = true;
                    break;
                } else {
                    i6++;
                }
            }
            if (!z) {
                return new RegressionResults(regcf, new double[][]{cov}, true, this.nobs, i5, this.sumy, this.sumsqy, this.sserr, this.hasIntercept, false);
            }
            double[] dArr = new double[regcf.length];
            double[] dArr2 = new double[cov.length];
            int[] iArr = new int[regcf.length];
            for (int i7 = 0; i7 < this.nvars; i7++) {
                for (int i8 = 0; i8 < i3; i8++) {
                    if (this.vorder[i8] == i7) {
                        dArr[i7] = regcf[i8];
                        iArr[i7] = i8;
                    }
                }
            }
            int i9 = 0;
            for (int i10 = 0; i10 < regcf.length; i10++) {
                int i11 = iArr[i10];
                int i12 = 0;
                while (i12 <= i10) {
                    int i13 = iArr[i12];
                    if (i11 > i13) {
                        i2 = (((i11 + 1) * i11) / 2) + i13;
                    } else {
                        i2 = ((i13 * (i13 + 1)) / 2) + i11;
                    }
                    dArr2[i9] = cov[i2];
                    i12++;
                    i9++;
                }
            }
            return new RegressionResults(dArr, new double[][]{dArr2}, true, this.nobs, i5, this.sumy, this.sumsqy, this.sserr, this.hasIntercept, false);
        } else {
            throw new ModelSpecificationException(LocalizedFormats.TOO_MANY_REGRESSORS, Integer.valueOf(i), Integer.valueOf(this.nvars));
        }
    }

    public RegressionResults regress(int[] iArr) throws ModelSpecificationException {
        boolean z;
        int i;
        int[] iArr2 = iArr;
        int length = iArr2.length;
        int i2 = this.nvars;
        if (length > i2) {
            throw new ModelSpecificationException(LocalizedFormats.TOO_MANY_REGRESSORS, Integer.valueOf(iArr2.length), Integer.valueOf(this.nvars));
        } else if (this.nobs > ((long) i2)) {
            Arrays.sort(iArr);
            int i3 = 0;
            int i4 = 0;
            while (i3 < iArr2.length) {
                if (i3 < this.nvars) {
                    if (i3 > 0 && iArr2[i3] == iArr2[i3 - 1]) {
                        iArr2[i3] = -1;
                        i4++;
                    }
                    i3++;
                } else {
                    throw new ModelSpecificationException(LocalizedFormats.INDEX_LARGER_THAN_MAX, Integer.valueOf(i3), Integer.valueOf(this.nvars));
                }
            }
            if (i4 > 0) {
                int[] iArr3 = new int[(iArr2.length - i4)];
                int i5 = 0;
                for (int i6 : iArr2) {
                    if (i6 > -1) {
                        iArr3[i5] = i6;
                        i5++;
                    }
                }
                iArr2 = iArr3;
            }
            reorderRegressors(iArr2, 0);
            tolset();
            singcheck();
            double[] regcf = regcf(iArr2.length);
            ss();
            double[] cov = cov(iArr2.length);
            int i7 = 0;
            int i8 = 0;
            while (true) {
                boolean[] zArr = this.lindep;
                if (i7 >= zArr.length) {
                    break;
                }
                if (!zArr[i7]) {
                    i8++;
                }
                i7++;
            }
            int i9 = 0;
            while (true) {
                if (i9 >= this.nvars) {
                    z = false;
                    break;
                } else if (this.vorder[i9] != iArr2[i9]) {
                    z = true;
                    break;
                } else {
                    i9++;
                }
            }
            if (!z) {
                return new RegressionResults(regcf, new double[][]{cov}, true, this.nobs, i8, this.sumy, this.sumsqy, this.sserr, this.hasIntercept, false);
            }
            double[] dArr = new double[regcf.length];
            int[] iArr4 = new int[regcf.length];
            for (int i10 = 0; i10 < iArr2.length; i10++) {
                int i11 = 0;
                while (true) {
                    int[] iArr5 = this.vorder;
                    if (i11 >= iArr5.length) {
                        break;
                    }
                    if (iArr5[i11] == iArr2[i10]) {
                        dArr[i10] = regcf[i11];
                        iArr4[i10] = i11;
                    }
                    i11++;
                }
            }
            double[] dArr2 = new double[cov.length];
            int i12 = 0;
            for (int i13 = 0; i13 < regcf.length; i13++) {
                int i14 = iArr4[i13];
                int i15 = 0;
                while (i15 <= i13) {
                    int i16 = iArr4[i15];
                    if (i14 > i16) {
                        i = (((i14 + 1) * i14) / 2) + i16;
                    } else {
                        i = ((i16 * (i16 + 1)) / 2) + i14;
                    }
                    dArr2[i12] = cov[i];
                    i15++;
                    i12++;
                }
            }
            return new RegressionResults(dArr, new double[][]{dArr2}, true, this.nobs, i8, this.sumy, this.sumsqy, this.sserr, this.hasIntercept, false);
        } else {
            throw new ModelSpecificationException(LocalizedFormats.NOT_ENOUGH_DATA_FOR_NUMBER_OF_PREDICTORS, Long.valueOf(this.nobs), Integer.valueOf(this.nvars));
        }
    }
}
