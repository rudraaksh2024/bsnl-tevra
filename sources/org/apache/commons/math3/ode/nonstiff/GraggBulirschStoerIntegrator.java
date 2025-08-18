package org.apache.commons.math3.ode.nonstiff;

import org.apache.commons.math3.analysis.solvers.UnivariateSolver;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.MaxCountExceededException;
import org.apache.commons.math3.ode.events.EventHandler;
import org.apache.commons.math3.ode.sampling.StepHandler;
import org.apache.commons.math3.util.FastMath;

public class GraggBulirschStoerIntegrator extends AdaptiveStepsizeIntegrator {
    private static final String METHOD_NAME = "Gragg-Bulirsch-Stoer";
    private double[][] coeff;
    private int[] costPerStep;
    private double[] costPerTimeUnit;
    private int maxChecks;
    private int maxIter;
    private int maxOrder;
    private int mudif;
    private double[] optimalStep;
    private double orderControl1;
    private double orderControl2;
    private boolean performTest;
    private int[] sequence;
    private double stabilityReduction;
    private double stepControl1;
    private double stepControl2;
    private double stepControl3;
    private double stepControl4;
    private boolean useInterpolationError;

    public GraggBulirschStoerIntegrator(double d, double d2, double d3, double d4) {
        super(METHOD_NAME, d, d2, d3, d4);
        setStabilityCheck(true, -1, -1, -1.0d);
        setControlFactors(-1.0d, -1.0d, -1.0d, -1.0d);
        setOrderControl(-1, -1.0d, -1.0d);
        setInterpolationControl(true, -1);
    }

    public GraggBulirschStoerIntegrator(double d, double d2, double[] dArr, double[] dArr2) {
        super(METHOD_NAME, d, d2, dArr, dArr2);
        setStabilityCheck(true, -1, -1, -1.0d);
        setControlFactors(-1.0d, -1.0d, -1.0d, -1.0d);
        setOrderControl(-1, -1.0d, -1.0d);
        setInterpolationControl(true, -1);
    }

    public void setStabilityCheck(boolean z, int i, int i2, double d) {
        this.performTest = z;
        if (i <= 0) {
            i = 2;
        }
        this.maxIter = i;
        if (i2 <= 0) {
            i2 = 1;
        }
        this.maxChecks = i2;
        if (d < 1.0E-4d || d > 0.9999d) {
            this.stabilityReduction = 0.5d;
        } else {
            this.stabilityReduction = d;
        }
    }

    public void setControlFactors(double d, double d2, double d3, double d4) {
        if (d < 1.0E-4d || d > 0.9999d) {
            this.stepControl1 = 0.65d;
        } else {
            this.stepControl1 = d;
        }
        if (d2 < 1.0E-4d || d2 > 0.9999d) {
            this.stepControl2 = 0.94d;
        } else {
            this.stepControl2 = d2;
        }
        if (d3 < 1.0E-4d || d3 > 0.9999d) {
            this.stepControl3 = 0.02d;
        } else {
            this.stepControl3 = d3;
        }
        if (d4 < 1.0001d || d4 > 999.9d) {
            this.stepControl4 = 4.0d;
        } else {
            this.stepControl4 = d4;
        }
    }

    public void setOrderControl(int i, double d, double d2) {
        if (i <= 6 || i % 2 != 0) {
            this.maxOrder = 18;
        }
        if (d < 1.0E-4d || d > 0.9999d) {
            this.orderControl1 = 0.8d;
        } else {
            this.orderControl1 = d;
        }
        if (d2 < 1.0E-4d || d2 > 0.9999d) {
            this.orderControl2 = 0.9d;
        } else {
            this.orderControl2 = d2;
        }
        initializeArrays();
    }

    public void addStepHandler(StepHandler stepHandler) {
        super.addStepHandler(stepHandler);
        initializeArrays();
    }

    public void addEventHandler(EventHandler eventHandler, double d, double d2, int i, UnivariateSolver univariateSolver) {
        super.addEventHandler(eventHandler, d, d2, i, univariateSolver);
        initializeArrays();
    }

    private void initializeArrays() {
        int i = this.maxOrder / 2;
        int[] iArr = this.sequence;
        if (iArr == null || iArr.length != i) {
            this.sequence = new int[i];
            this.costPerStep = new int[i];
            this.coeff = new double[i][];
            this.costPerTimeUnit = new double[i];
            this.optimalStep = new double[i];
        }
        for (int i2 = 0; i2 < i; i2++) {
            this.sequence[i2] = (i2 * 4) + 2;
        }
        this.costPerStep[0] = this.sequence[0] + 1;
        for (int i3 = 1; i3 < i; i3++) {
            int[] iArr2 = this.costPerStep;
            iArr2[i3] = iArr2[i3 - 1] + this.sequence[i3];
        }
        int i4 = 0;
        while (i4 < i) {
            this.coeff[i4] = i4 > 0 ? new double[i4] : null;
            for (int i5 = 0; i5 < i4; i5++) {
                int[] iArr3 = this.sequence;
                double d = ((double) iArr3[i4]) / ((double) iArr3[(i4 - i5) - 1]);
                this.coeff[i4][i5] = 1.0d / ((d * d) - 1.0d);
            }
            i4++;
        }
    }

    public void setInterpolationControl(boolean z, int i) {
        this.useInterpolationError = z;
        if (i <= 0 || i >= 7) {
            this.mudif = 4;
        } else {
            this.mudif = i;
        }
    }

    private void rescale(double[] dArr, double[] dArr2, double[] dArr3) {
        int i = 0;
        if (this.vecAbsoluteTolerance == null) {
            while (i < dArr3.length) {
                dArr3[i] = this.scalAbsoluteTolerance + (this.scalRelativeTolerance * FastMath.max(FastMath.abs(dArr[i]), FastMath.abs(dArr2[i])));
                i++;
            }
            return;
        }
        while (i < dArr3.length) {
            dArr3[i] = this.vecAbsoluteTolerance[i] + (this.vecRelativeTolerance[i] * FastMath.max(FastMath.abs(dArr[i]), FastMath.abs(dArr2[i])));
            i++;
        }
    }

    private boolean tryStep(double d, double[] dArr, double d2, int i, double[] dArr2, double[][] dArr3, double[] dArr4, double[] dArr5, double[] dArr6) throws MaxCountExceededException, DimensionMismatchException {
        double d3;
        GraggBulirschStoerIntegrator graggBulirschStoerIntegrator = this;
        double[] dArr7 = dArr;
        int i2 = i;
        double[] dArr8 = dArr2;
        double[] dArr9 = dArr5;
        int i3 = graggBulirschStoerIntegrator.sequence[i2];
        double d4 = d2 / ((double) i3);
        double d5 = 2.0d * d4;
        double d6 = d + d4;
        int i4 = 0;
        for (int i5 = 0; i5 < dArr7.length; i5++) {
            dArr6[i5] = dArr7[i5];
            dArr9[i5] = dArr7[i5] + (dArr3[0][i5] * d4);
        }
        graggBulirschStoerIntegrator.computeDerivatives(d6, dArr9, dArr3[1]);
        int i6 = 1;
        while (i6 < i3) {
            if (i6 * 2 == i3) {
                System.arraycopy(dArr9, i4, dArr4, i4, dArr7.length);
            } else {
                double[] dArr10 = dArr4;
            }
            d6 += d4;
            for (int i7 = i4; i7 < dArr7.length; i7++) {
                double d7 = dArr9[i7];
                dArr9[i7] = dArr6[i7] + (dArr3[i6][i7] * d5);
                dArr6[i7] = d7;
            }
            int i8 = i6 + 1;
            graggBulirschStoerIntegrator.computeDerivatives(d6, dArr9, dArr3[i8]);
            if (!graggBulirschStoerIntegrator.performTest || i6 > graggBulirschStoerIntegrator.maxChecks || i2 >= graggBulirschStoerIntegrator.maxIter) {
                d3 = d5;
            } else {
                d3 = d5;
                double d8 = 0.0d;
                for (int i9 = 0; i9 < dArr8.length; i9++) {
                    double d9 = dArr3[0][i9] / dArr8[i9];
                    d8 += d9 * d9;
                }
                double d10 = 0.0d;
                for (int i10 = 0; i10 < dArr8.length; i10++) {
                    double d11 = (dArr3[i8][i10] - dArr3[0][i10]) / dArr8[i10];
                    d10 += d11 * d11;
                }
                if (d10 > FastMath.max(1.0E-15d, d8) * 4.0d) {
                    return false;
                }
            }
            i2 = i;
            dArr8 = dArr2;
            i6 = i8;
            d5 = d3;
            i4 = 0;
            graggBulirschStoerIntegrator = this;
        }
        int i11 = i4;
        while (i4 < dArr7.length) {
            dArr9[i4] = (dArr6[i4] + dArr9[i4] + (dArr3[i3][i4] * d4)) * 0.5d;
            i4++;
        }
        return true;
    }

    private void extrapolate(int i, int i2, double[][] dArr, double[] dArr2) {
        int i3 = 1;
        while (true) {
            if (i3 >= i2) {
                break;
            }
            for (int i4 = 0; i4 < dArr2.length; i4++) {
                int i5 = i2 - i3;
                double[] dArr3 = dArr[i5 - 1];
                double d = dArr[i5][i4];
                dArr3[i4] = d + (this.coeff[i2 + i][i3 - 1] * (d - dArr3[i4]));
            }
            i3++;
        }
        for (int i6 = 0; i6 < dArr2.length; i6++) {
            double d2 = dArr[0][i6];
            dArr2[i6] = d2 + (this.coeff[i2 + i][i2 - 1] * (d2 - dArr2[i6]));
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v18, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r18v0, resolved type: double[][]} */
    /* JADX WARNING: Code restructure failed: missing block: B:80:0x02b2, code lost:
        if (r12.isLastStep == false) goto L_0x0355;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:82:0x02b8, code lost:
        if (r0 > 1.0d) goto L_0x0355;
     */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:175:0x04e1  */
    /* JADX WARNING: Removed duplicated region for block: B:200:0x0585  */
    /* JADX WARNING: Removed duplicated region for block: B:202:0x0590  */
    /* JADX WARNING: Removed duplicated region for block: B:203:0x05a8  */
    /* JADX WARNING: Removed duplicated region for block: B:212:0x0604  */
    /* JADX WARNING: Removed duplicated region for block: B:215:0x061a  */
    /* JADX WARNING: Removed duplicated region for block: B:217:0x061d  */
    /* JADX WARNING: Removed duplicated region for block: B:218:0x0622  */
    /* JADX WARNING: Removed duplicated region for block: B:223:0x0636 A[LOOP:3: B:23:0x0137->B:223:0x0636, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:227:0x0628 A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void integrate(org.apache.commons.math3.ode.ExpandableStatefulODE r50, double r51) throws org.apache.commons.math3.exception.NumberIsTooSmallException, org.apache.commons.math3.exception.DimensionMismatchException, org.apache.commons.math3.exception.MaxCountExceededException, org.apache.commons.math3.exception.NoBracketingException {
        /*
            r49 = this;
            r12 = r49
            r13 = r50
            r49.sanityChecks(r50, r51)
            r49.setEquations(r50)
            double r0 = r50.getTime()
            int r0 = (r51 > r0 ? 1 : (r51 == r0 ? 0 : -1))
            r15 = 1
            if (r0 <= 0) goto L_0x0015
            r11 = r15
            goto L_0x0016
        L_0x0015:
            r11 = 0
        L_0x0016:
            double[] r10 = r50.getCompleteState()
            java.lang.Object r0 = r10.clone()
            double[] r0 = (double[]) r0
            int r1 = r0.length
            double[] r9 = new double[r1]
            int r1 = r0.length
            double[] r8 = new double[r1]
            int r1 = r0.length
            double[] r7 = new double[r1]
            int r1 = r0.length
            double[] r6 = new double[r1]
            int[] r1 = r12.sequence
            int r2 = r1.length
            int r2 = r2 - r15
            double[][] r5 = new double[r2][]
            int r1 = r1.length
            int r1 = r1 - r15
            double[][] r4 = new double[r1][]
            r1 = 0
        L_0x0037:
            int[] r2 = r12.sequence
            int r3 = r2.length
            int r3 = r3 - r15
            if (r1 >= r3) goto L_0x004a
            int r2 = r0.length
            double[] r2 = new double[r2]
            r5[r1] = r2
            int r2 = r0.length
            double[] r2 = new double[r2]
            r4[r1] = r2
            int r1 = r1 + 1
            goto L_0x0037
        L_0x004a:
            int r1 = r2.length
            double[][][] r3 = new double[r1][][]
            r1 = 0
        L_0x004e:
            int[] r2 = r12.sequence
            int r14 = r2.length
            if (r1 >= r14) goto L_0x0073
            r2 = r2[r1]
            int r2 = r2 + r15
            double[][] r2 = new double[r2][]
            r3[r1] = r2
            r14 = 0
            r2[r14] = r9
            r2 = 0
        L_0x005e:
            int[] r14 = r12.sequence
            r14 = r14[r1]
            if (r2 >= r14) goto L_0x006f
            r14 = r3[r1]
            int r2 = r2 + 1
            int r15 = r10.length
            double[] r15 = new double[r15]
            r14[r2] = r15
            r15 = 1
            goto L_0x005e
        L_0x006f:
            int r1 = r1 + 1
            r15 = 1
            goto L_0x004e
        L_0x0073:
            if (r0 == r10) goto L_0x007a
            int r1 = r10.length
            r2 = 0
            java.lang.System.arraycopy(r10, r2, r0, r2, r1)
        L_0x007a:
            int r1 = r10.length
            double[] r14 = new double[r1]
            int[] r1 = r12.sequence
            int r1 = r1.length
            r15 = 2
            int r1 = r1 * r15
            r2 = 1
            int r1 = r1 + r2
            r16 = r3
            int r3 = r10.length
            r17 = r4
            int[] r4 = new int[r15]
            r4[r2] = r3
            r2 = 0
            r4[r2] = r1
            java.lang.Class r1 = java.lang.Double.TYPE
            java.lang.Object r1 = java.lang.reflect.Array.newInstance(r1, r4)
            r18 = r1
            double[][] r18 = (double[][]) r18
            int r1 = r12.mainSetDimension
            double[] r4 = new double[r1]
            r12.rescale(r0, r0, r4)
            double[] r1 = r12.vecRelativeTolerance
            if (r1 != 0) goto L_0x00a8
            double r1 = r12.scalRelativeTolerance
            goto L_0x00af
        L_0x00a8:
            double[] r1 = r12.vecRelativeTolerance
            r2 = 0
            r19 = r1[r2]
            r1 = r19
        L_0x00af:
            r19 = r4
            r3 = 4457293557087583675(0x3ddb7cdfd9d7bdbb, double:1.0E-10)
            double r1 = org.apache.commons.math3.util.FastMath.max((double) r3, (double) r1)
            double r1 = org.apache.commons.math3.util.FastMath.log10(r1)
            int[] r3 = r12.sequence
            int r3 = r3.length
            int r3 = r3 - r15
            r20 = 4603579539098121011(0x3fe3333333333333, double:0.6)
            double r1 = r1 * r20
            r20 = 4602678819172646912(0x3fe0000000000000, double:0.5)
            double r1 = r20 - r1
            double r1 = org.apache.commons.math3.util.FastMath.floor(r1)
            int r1 = (int) r1
            int r1 = org.apache.commons.math3.util.FastMath.min((int) r3, (int) r1)
            r2 = 1
            int r22 = org.apache.commons.math3.util.FastMath.max((int) r2, (int) r1)
            org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerStepInterpolator r4 = new org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerStepInterpolator
            org.apache.commons.math3.ode.EquationsMapper r23 = r50.getPrimaryMapper()
            org.apache.commons.math3.ode.EquationsMapper[] r24 = r50.getSecondaryMappers()
            r1 = r4
            r2 = r0
            r3 = r9
            r13 = r4
            r15 = r17
            r4 = r8
            r15 = r5
            r5 = r14
            r26 = r6
            r6 = r18
            r27 = r7
            r7 = r11
            r28 = r14
            r14 = r8
            r8 = r23
            r23 = r14
            r14 = r9
            r9 = r24
            r1.<init>(r2, r3, r4, r5, r6, r7, r8, r9)
            double r1 = r50.getTime()
            r13.storeTime(r1)
            double r1 = r50.getTime()
            r12.stepStart = r1
            double r1 = r50.getTime()
            r9 = r0
            r0 = r49
            r3 = r10
            r4 = r51
            r0.initIntegration(r1, r3, r4)
            double[] r0 = r12.costPerTimeUnit
            r29 = 0
            r1 = 0
            r0[r1] = r29
            r12.isLastStep = r1
            r0 = 9218868437227405311(0x7fefffffffffffff, double:1.7976931348623157E308)
            r31 = r0
            r34 = r22
            r1 = r29
            r0 = 1
            r22 = 0
            r24 = 1
            r33 = 0
        L_0x0137:
            if (r0 == 0) goto L_0x0164
            r13.shift()
            if (r22 != 0) goto L_0x0143
            double r3 = r12.stepStart
            r12.computeDerivatives(r3, r9, r14)
        L_0x0143:
            if (r24 == 0) goto L_0x015f
            int r0 = r34 * 2
            r1 = 1
            int r2 = r0 + 1
            double r4 = r12.stepStart
            r0 = r49
            r1 = r11
            r3 = r19
            r6 = r9
            r7 = r14
            r8 = r27
            r35 = r9
            r9 = r26
            double r0 = r0.initializeStep(r1, r2, r3, r4, r6, r7, r8, r9)
            r1 = r0
            goto L_0x0161
        L_0x015f:
            r35 = r9
        L_0x0161:
            r36 = 0
            goto L_0x0168
        L_0x0164:
            r35 = r9
            r36 = r0
        L_0x0168:
            r12.stepSize = r1
            if (r11 == 0) goto L_0x0175
            double r3 = r12.stepStart
            double r5 = r12.stepSize
            double r3 = r3 + r5
            int r0 = (r3 > r51 ? 1 : (r3 == r51 ? 0 : -1))
            if (r0 > 0) goto L_0x0180
        L_0x0175:
            if (r11 != 0) goto L_0x0186
            double r3 = r12.stepStart
            double r5 = r12.stepSize
            double r3 = r3 + r5
            int r0 = (r3 > r51 ? 1 : (r3 == r51 ? 0 : -1))
            if (r0 >= 0) goto L_0x0186
        L_0x0180:
            double r3 = r12.stepStart
            double r3 = r51 - r3
            r12.stepSize = r3
        L_0x0186:
            double r3 = r12.stepStart
            double r5 = r12.stepSize
            double r3 = r3 + r5
            if (r11 == 0) goto L_0x0192
            int r0 = (r3 > r51 ? 1 : (r3 == r51 ? 0 : -1))
            if (r0 < 0) goto L_0x0198
            goto L_0x0196
        L_0x0192:
            int r0 = (r3 > r51 ? 1 : (r3 == r51 ? 0 : -1))
            if (r0 > 0) goto L_0x0198
        L_0x0196:
            r0 = 1
            goto L_0x0199
        L_0x0198:
            r0 = 0
        L_0x0199:
            r12.isLastStep = r0
            r9 = -1
            r6 = r9
            r38 = r31
            r8 = r34
            r34 = 1
            r37 = 0
            r31 = r1
        L_0x01a7:
            if (r34 == 0) goto L_0x0390
            int r7 = r6 + 1
            double r1 = r12.stepStart
            double r4 = r12.stepSize
            r42 = r16[r7]
            if (r7 != 0) goto L_0x01b9
            r0 = 0
            r3 = r18[r0]
            r43 = r3
            goto L_0x01bf
        L_0x01b9:
            int r0 = r7 + -1
            r0 = r15[r0]
            r43 = r0
        L_0x01bf:
            if (r7 != 0) goto L_0x01c4
            r44 = r23
            goto L_0x01ca
        L_0x01c4:
            int r0 = r7 + -1
            r0 = r17[r0]
            r44 = r0
        L_0x01ca:
            r0 = r49
            r3 = r35
            r45 = r13
            r46 = r14
            r13 = 4607182418800017408(0x3ff0000000000000, double:1.0)
            r6 = r7
            r13 = r7
            r7 = r19
            r14 = r8
            r8 = r42
            r42 = r15
            r15 = r9
            r9 = r43
            r47 = r10
            r10 = r44
            r15 = r11
            r11 = r27
            boolean r0 = r0.tryStep(r1, r3, r4, r6, r7, r8, r9, r10, r11)
            if (r0 != 0) goto L_0x020c
            double r0 = r12.stepSize
            double r2 = r12.stabilityReduction
            double r0 = r0 * r2
            r2 = 0
            double r0 = r12.filterStep(r0, r15, r2)
            double r31 = org.apache.commons.math3.util.FastMath.abs((double) r0)
            r34 = r2
            r6 = r13
            r8 = r14
            r11 = r15
            r15 = r42
            r13 = r45
            r14 = r46
            r10 = r47
            r9 = -1
        L_0x0209:
            r37 = 1
            goto L_0x01a7
        L_0x020c:
            r2 = 0
            if (r13 <= 0) goto L_0x0382
            r8 = r17
            r7 = r23
            r12.extrapolate(r2, r13, r8, r7)
            r10 = r19
            r9 = r35
            r12.rescale(r9, r7, r10)
            r0 = r2
            r3 = r29
        L_0x0220:
            int r1 = r12.mainSetDimension
            if (r0 >= r1) goto L_0x0238
            r5 = r7[r0]
            r1 = r8[r2]
            r1 = r1[r0]
            double r5 = r5 - r1
            double r1 = org.apache.commons.math3.util.FastMath.abs((double) r5)
            r5 = r10[r0]
            double r1 = r1 / r5
            double r1 = r1 * r1
            double r3 = r3 + r1
            int r0 = r0 + 1
            r2 = 0
            goto L_0x0220
        L_0x0238:
            int r0 = r12.mainSetDimension
            double r0 = (double) r0
            double r3 = r3 / r0
            double r0 = org.apache.commons.math3.util.FastMath.sqrt(r3)
            r2 = 4831355200913801216(0x430c6bf526340000, double:1.0E15)
            int r2 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r2 > 0) goto L_0x035c
            r2 = 1
            if (r13 <= r2) goto L_0x0252
            int r3 = (r0 > r38 ? 1 : (r0 == r38 ? 0 : -1))
            if (r3 <= 0) goto L_0x0252
            goto L_0x035c
        L_0x0252:
            r3 = 4616189618054758400(0x4010000000000000, double:4.0)
            double r3 = r3 * r0
            r5 = 4607182418800017408(0x3ff0000000000000, double:1.0)
            double r38 = org.apache.commons.math3.util.FastMath.max((double) r3, (double) r5)
            int r3 = r13 * 2
            int r3 = r3 + r2
            double r2 = (double) r3
            double r2 = r5 / r2
            double r5 = r12.stepControl2
            r17 = r8
            r35 = r9
            double r8 = r12.stepControl1
            double r8 = r0 / r8
            double r8 = org.apache.commons.math3.util.FastMath.pow((double) r8, (double) r2)
            double r5 = r5 / r8
            double r8 = r12.stepControl3
            double r2 = org.apache.commons.math3.util.FastMath.pow((double) r8, (double) r2)
            double r8 = r12.stepControl4
            double r8 = r2 / r8
            r40 = 4607182418800017408(0x3ff0000000000000, double:1.0)
            double r2 = r40 / r2
            double r2 = org.apache.commons.math3.util.FastMath.min((double) r2, (double) r5)
            double r2 = org.apache.commons.math3.util.FastMath.max((double) r8, (double) r2)
            double[] r4 = r12.optimalStep
            double r5 = r12.stepSize
            double r5 = r5 * r2
            r2 = 1
            double r5 = r12.filterStep(r5, r15, r2)
            double r2 = org.apache.commons.math3.util.FastMath.abs((double) r5)
            r4[r13] = r2
            double[] r2 = r12.costPerTimeUnit
            int[] r3 = r12.costPerStep
            r3 = r3[r13]
            double r3 = (double) r3
            double[] r5 = r12.optimalStep
            r8 = r5[r13]
            double r3 = r3 / r8
            r2[r13] = r3
            int r6 = r13 - r14
            r8 = -1
            if (r6 == r8) goto L_0x0317
            if (r6 == 0) goto L_0x02da
            r3 = 1
            if (r6 == r3) goto L_0x02bb
            if (r24 != 0) goto L_0x02b4
            boolean r2 = r12.isLastStep
            if (r2 == 0) goto L_0x0355
        L_0x02b4:
            r3 = 4607182418800017408(0x3ff0000000000000, double:1.0)
            int r0 = (r0 > r3 ? 1 : (r0 == r3 ? 0 : -1))
            if (r0 > 0) goto L_0x0355
            goto L_0x02e0
        L_0x02bb:
            r3 = 4607182418800017408(0x3ff0000000000000, double:1.0)
            int r0 = (r0 > r3 ? 1 : (r0 == r3 ? 0 : -1))
            if (r0 <= 0) goto L_0x02e0
            r0 = 1
            if (r14 <= r0) goto L_0x02d5
            int r0 = r14 + -1
            r0 = r2[r0]
            double r3 = r12.orderControl1
            r31 = r2[r14]
            double r3 = r3 * r31
            int r0 = (r0 > r3 ? 1 : (r0 == r3 ? 0 : -1))
            if (r0 >= 0) goto L_0x02d5
            int r0 = r14 + -1
            r14 = r0
        L_0x02d5:
            r31 = r5[r14]
            r37 = 1
            goto L_0x02e0
        L_0x02da:
            r3 = 4607182418800017408(0x3ff0000000000000, double:1.0)
            int r3 = (r0 > r3 ? 1 : (r0 == r3 ? 0 : -1))
            if (r3 > 0) goto L_0x02e7
        L_0x02e0:
            r19 = r10
            r8 = r14
            r34 = 0
            goto L_0x0358
        L_0x02e7:
            int[] r3 = r12.sequence
            int r4 = r13 + 1
            r4 = r3[r4]
            double r8 = (double) r4
            r4 = 0
            r3 = r3[r4]
            double r3 = (double) r3
            double r8 = r8 / r3
            double r8 = r8 * r8
            int r0 = (r0 > r8 ? 1 : (r0 == r8 ? 0 : -1))
            if (r0 <= 0) goto L_0x0313
            r0 = 1
            if (r14 <= r0) goto L_0x030b
            int r8 = r14 + -1
            r0 = r2[r8]
            double r3 = r12.orderControl1
            r8 = r2[r14]
            double r3 = r3 * r8
            int r0 = (r0 > r3 ? 1 : (r0 == r3 ? 0 : -1))
            if (r0 >= 0) goto L_0x030b
            int r8 = r14 + -1
            goto L_0x030c
        L_0x030b:
            r8 = r14
        L_0x030c:
            r31 = r5[r8]
            r34 = 0
            r37 = 1
            goto L_0x0314
        L_0x0313:
            r8 = r14
        L_0x0314:
            r19 = r10
            goto L_0x0358
        L_0x0317:
            r6 = 1
            if (r14 <= r6) goto L_0x0355
            if (r33 != 0) goto L_0x0355
            r8 = 4607182418800017408(0x3ff0000000000000, double:1.0)
            int r6 = (r0 > r8 ? 1 : (r0 == r8 ? 0 : -1))
            if (r6 > 0) goto L_0x0323
            goto L_0x02e0
        L_0x0323:
            int[] r6 = r12.sequence
            r8 = r6[r14]
            double r8 = (double) r8
            int r11 = r14 + 1
            r11 = r6[r11]
            r19 = r10
            double r10 = (double) r11
            double r8 = r8 * r10
            r10 = 0
            r6 = r6[r10]
            int r6 = r6 * r6
            double r10 = (double) r6
            double r8 = r8 / r10
            double r8 = r8 * r8
            int r0 = (r0 > r8 ? 1 : (r0 == r8 ? 0 : -1))
            if (r0 <= 0) goto L_0x0357
            r0 = 1
            if (r13 <= r0) goto L_0x034d
            int r0 = r13 + -1
            r0 = r2[r0]
            double r8 = r12.orderControl1
            double r8 = r8 * r3
            int r0 = (r0 > r8 ? 1 : (r0 == r8 ? 0 : -1))
            if (r0 >= 0) goto L_0x034d
            int r0 = r13 + -1
            r8 = r0
            goto L_0x034e
        L_0x034d:
            r8 = r13
        L_0x034e:
            r31 = r5[r8]
            r34 = 0
            r37 = 1
            goto L_0x0358
        L_0x0355:
            r19 = r10
        L_0x0357:
            r8 = r14
        L_0x0358:
            r23 = r7
            r6 = r13
            goto L_0x0384
        L_0x035c:
            r17 = r8
            r35 = r9
            r19 = r10
            double r0 = r12.stepSize
            double r2 = r12.stabilityReduction
            double r0 = r0 * r2
            r2 = 0
            double r0 = r12.filterStep(r0, r15, r2)
            double r31 = org.apache.commons.math3.util.FastMath.abs((double) r0)
            r23 = r7
            r6 = r13
            r8 = r14
            r11 = r15
            r15 = r42
            r13 = r45
            r14 = r46
            r10 = r47
            r9 = -1
            r34 = 0
            goto L_0x0209
        L_0x0382:
            r6 = r13
            r8 = r14
        L_0x0384:
            r11 = r15
            r15 = r42
            r13 = r45
            r14 = r46
            r10 = r47
            r9 = -1
            goto L_0x01a7
        L_0x0390:
            r47 = r10
            r45 = r13
            r46 = r14
            r42 = r15
            r7 = r23
            r14 = r8
            r15 = r11
            if (r37 != 0) goto L_0x03a9
            double r0 = r12.stepStart
            double r2 = r12.stepSize
            double r0 = r0 + r2
            r8 = r28
            r12.computeDerivatives(r0, r7, r8)
            goto L_0x03ab
        L_0x03a9:
            r8 = r28
        L_0x03ab:
            double r0 = r49.getMaxStep()
            if (r37 != 0) goto L_0x04cd
            r2 = 1
        L_0x03b2:
            if (r2 > r6) goto L_0x03bf
            r3 = 0
            r4 = r18[r3]
            r9 = r42
            r12.extrapolate(r3, r2, r9, r4)
            int r2 = r2 + 1
            goto L_0x03b2
        L_0x03bf:
            r9 = r42
            int r2 = r6 * 2
            int r3 = r12.mudif
            int r2 = r2 - r3
            int r2 = r2 + 3
            r3 = 0
        L_0x03c9:
            if (r3 >= r2) goto L_0x0480
            int r4 = r3 / 2
            int[] r5 = r12.sequence
            r5 = r5[r4]
            double r10 = (double) r5
            double r10 = r10 * r20
            double r10 = org.apache.commons.math3.util.FastMath.pow((double) r10, (int) r3)
            r5 = r16[r4]
            int r5 = r5.length
            r13 = 2
            int r5 = r5 / r13
            r42 = r0
            r23 = r15
            r15 = r47
            r13 = 0
        L_0x03e4:
            int r0 = r15.length
            if (r13 >= r0) goto L_0x03fa
            int r0 = r3 + 1
            r0 = r18[r0]
            r1 = r16[r4]
            int r24 = r5 + r3
            r1 = r1[r24]
            r47 = r1[r13]
            double r47 = r47 * r10
            r0[r13] = r47
            int r13 = r13 + 1
            goto L_0x03e4
        L_0x03fa:
            r0 = 1
        L_0x03fb:
            int r1 = r6 - r4
            if (r0 > r1) goto L_0x0436
            int[] r1 = r12.sequence
            int r5 = r0 + r4
            r1 = r1[r5]
            double r10 = (double) r1
            double r10 = r10 * r20
            double r10 = org.apache.commons.math3.util.FastMath.pow((double) r10, (int) r3)
            r1 = r16[r5]
            int r1 = r1.length
            r13 = 2
            int r1 = r1 / r13
            r24 = r14
            r13 = 0
        L_0x0414:
            int r14 = r15.length
            if (r13 >= r14) goto L_0x042a
            int r14 = r0 + -1
            r14 = r9[r14]
            r28 = r16[r5]
            int r34 = r1 + r3
            r28 = r28[r34]
            r47 = r28[r13]
            double r47 = r47 * r10
            r14[r13] = r47
            int r13 = r13 + 1
            goto L_0x0414
        L_0x042a:
            int r1 = r3 + 1
            r1 = r18[r1]
            r12.extrapolate(r4, r0, r9, r1)
            int r0 = r0 + 1
            r14 = r24
            goto L_0x03fb
        L_0x0436:
            r24 = r14
            r0 = 0
        L_0x0439:
            int r1 = r15.length
            if (r0 >= r1) goto L_0x044a
            int r1 = r3 + 1
            r1 = r18[r1]
            r4 = r1[r0]
            double r10 = r12.stepSize
            double r4 = r4 * r10
            r1[r0] = r4
            int r0 = r0 + 1
            goto L_0x0439
        L_0x044a:
            int r3 = r3 + 1
            int r0 = r3 / 2
        L_0x044e:
            if (r0 > r6) goto L_0x0476
            r1 = r16[r0]
            int r1 = r1.length
            r4 = 1
            int r1 = r1 - r4
        L_0x0455:
            int r4 = r3 * 2
            if (r1 < r4) goto L_0x0473
            r4 = 0
        L_0x045a:
            int r5 = r15.length
            if (r4 >= r5) goto L_0x0470
            r5 = r16[r0]
            r10 = r5[r1]
            r13 = r10[r4]
            int r11 = r1 + -2
            r5 = r5[r11]
            r47 = r5[r4]
            double r13 = r13 - r47
            r10[r4] = r13
            int r4 = r4 + 1
            goto L_0x045a
        L_0x0470:
            int r1 = r1 + -1
            goto L_0x0455
        L_0x0473:
            int r0 = r0 + 1
            goto L_0x044e
        L_0x0476:
            r47 = r15
            r15 = r23
            r14 = r24
            r0 = r42
            goto L_0x03c9
        L_0x0480:
            r42 = r0
            r24 = r14
            r23 = r15
            r15 = r47
            if (r2 < 0) goto L_0x04c8
            r4 = r45
            org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerStepInterpolator r4 = (org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerStepInterpolator) r4
            double r0 = r12.stepSize
            r10 = r45
            r10.computeCoefficients(r2, r0)
            boolean r0 = r12.useInterpolationError
            r11 = r19
            if (r0 == 0) goto L_0x04db
            double r0 = r10.estimateError(r11)
            double r3 = r12.stepSize
            int r2 = r2 + 4
            double r13 = (double) r2
            r40 = 4607182418800017408(0x3ff0000000000000, double:1.0)
            double r13 = r40 / r13
            double r13 = org.apache.commons.math3.util.FastMath.pow((double) r0, (double) r13)
            r19 = r6
            r5 = 4576918229304087675(0x3f847ae147ae147b, double:0.01)
            double r5 = org.apache.commons.math3.util.FastMath.max((double) r13, (double) r5)
            double r3 = r3 / r5
            double r2 = org.apache.commons.math3.util.FastMath.abs((double) r3)
            r4 = 4621819117588971520(0x4024000000000000, double:10.0)
            int r0 = (r0 > r4 ? 1 : (r0 == r4 ? 0 : -1))
            r13 = r2
            if (r0 <= 0) goto L_0x04df
            r31 = r13
            r37 = 1
            goto L_0x04df
        L_0x04c8:
            r11 = r19
            r10 = r45
            goto L_0x04db
        L_0x04cd:
            r24 = r14
            r23 = r15
            r11 = r19
            r9 = r42
            r10 = r45
            r15 = r47
            r42 = r0
        L_0x04db:
            r19 = r6
            r13 = r42
        L_0x04df:
            if (r37 != 0) goto L_0x0604
            double r0 = r12.stepStart
            double r2 = r12.stepSize
            double r0 = r0 + r2
            r10.storeTime(r0)
            r0 = r49
            r1 = r10
            r2 = r7
            r3 = r8
            r4 = r51
            double r0 = r0.acceptStep(r1, r2, r3, r4)
            r12.stepStart = r0
            double r0 = r12.stepStart
            r10.storeTime(r0)
            int r0 = r15.length
            r1 = r35
            r2 = 0
            java.lang.System.arraycopy(r7, r2, r1, r2, r0)
            int r0 = r15.length
            r3 = r46
            java.lang.System.arraycopy(r8, r2, r3, r2, r0)
            r0 = r19
            r2 = 1
            if (r0 != r2) goto L_0x051d
            r46 = r3
            r5 = r7
            r28 = r8
            r42 = r9
            r4 = r24
            if (r33 == 0) goto L_0x0519
            goto L_0x051a
        L_0x0519:
            r2 = 2
        L_0x051a:
            r3 = 2
            goto L_0x058e
        L_0x051d:
            r4 = r24
            if (r0 > r4) goto L_0x0555
            double[] r5 = r12.costPerTimeUnit
            int r6 = r0 + -1
            r31 = r5[r6]
            r46 = r3
            double r2 = r12.orderControl1
            r34 = r5[r0]
            double r2 = r2 * r34
            int r2 = (r31 > r2 ? 1 : (r31 == r2 ? 0 : -1))
            if (r2 >= 0) goto L_0x0535
            r2 = r6
            goto L_0x054f
        L_0x0535:
            double r2 = r12.orderControl2
            double r2 = r2 * r31
            int r2 = (r34 > r2 ? 1 : (r34 == r2 ? 0 : -1))
            if (r2 >= 0) goto L_0x054e
            int r6 = r0 + 1
            int[] r2 = r12.sequence
            int r2 = r2.length
            r3 = 2
            int r2 = r2 - r3
            int r2 = org.apache.commons.math3.util.FastMath.min((int) r6, (int) r2)
            r5 = r7
            r28 = r8
            r42 = r9
            goto L_0x058e
        L_0x054e:
            r2 = r0
        L_0x054f:
            r5 = r7
            r28 = r8
            r42 = r9
            goto L_0x051a
        L_0x0555:
            r46 = r3
            r3 = 2
            int r6 = r0 + -1
            if (r0 <= r3) goto L_0x0571
            double[] r2 = r12.costPerTimeUnit
            int r3 = r0 + -2
            r31 = r2[r3]
            r5 = r7
            r28 = r8
            double r7 = r12.orderControl1
            r34 = r2[r6]
            double r7 = r7 * r34
            int r2 = (r31 > r7 ? 1 : (r31 == r7 ? 0 : -1))
            if (r2 >= 0) goto L_0x0574
            r2 = r3
            goto L_0x0575
        L_0x0571:
            r5 = r7
            r28 = r8
        L_0x0574:
            r2 = r6
        L_0x0575:
            double[] r3 = r12.costPerTimeUnit
            r6 = r3[r0]
            r42 = r9
            double r8 = r12.orderControl2
            r31 = r3[r2]
            double r8 = r8 * r31
            int r3 = (r6 > r8 ? 1 : (r6 == r8 ? 0 : -1))
            if (r3 >= 0) goto L_0x051a
            int[] r2 = r12.sequence
            int r2 = r2.length
            r3 = 2
            int r2 = r2 - r3
            int r2 = org.apache.commons.math3.util.FastMath.min((int) r0, (int) r2)
        L_0x058e:
            if (r33 == 0) goto L_0x05a8
            int r2 = org.apache.commons.math3.util.FastMath.min((int) r2, (int) r0)
            double r6 = r12.stepSize
            double r6 = org.apache.commons.math3.util.FastMath.abs((double) r6)
            double[] r0 = r12.optimalStep
            r8 = r0[r2]
            double r6 = org.apache.commons.math3.util.FastMath.min((double) r6, (double) r8)
            r31 = r6
            r8 = r23
            r0 = 0
            goto L_0x05fb
        L_0x05a8:
            if (r2 > r0) goto L_0x05b4
            double[] r0 = r12.optimalStep
            r6 = r0[r2]
            r19 = r2
            r8 = r23
            r0 = 0
            goto L_0x05f7
        L_0x05b4:
            if (r0 >= r4) goto L_0x05e0
            double[] r4 = r12.costPerTimeUnit
            r6 = r4[r0]
            double r8 = r12.orderControl2
            int r19 = r0 + -1
            r24 = r4[r19]
            double r8 = r8 * r24
            int r4 = (r6 > r8 ? 1 : (r6 == r8 ? 0 : -1))
            if (r4 >= 0) goto L_0x05e0
            double[] r4 = r12.optimalStep
            r6 = r4[r0]
            int[] r4 = r12.costPerStep
            int r8 = r2 + 1
            r8 = r4[r8]
            double r8 = (double) r8
            double r6 = r6 * r8
            r0 = r4[r0]
            double r8 = (double) r0
            double r6 = r6 / r8
            r8 = r23
            r0 = 0
            double r6 = r12.filterStep(r6, r8, r0)
            r19 = r2
            goto L_0x05f7
        L_0x05e0:
            r8 = r23
            double[] r4 = r12.optimalStep
            r6 = r4[r0]
            int[] r4 = r12.costPerStep
            r9 = r4[r2]
            r19 = r2
            double r2 = (double) r9
            double r6 = r6 * r2
            r0 = r4[r0]
            double r2 = (double) r0
            double r6 = r6 / r2
            r0 = 0
            double r6 = r12.filterStep(r6, r8, r0)
        L_0x05f7:
            r31 = r6
            r2 = r19
        L_0x05fb:
            r34 = r2
            r2 = r31
            r22 = 1
            r36 = 1
            goto L_0x0614
        L_0x0604:
            r5 = r7
            r28 = r8
            r42 = r9
            r8 = r23
            r4 = r24
            r1 = r35
            r0 = 0
            r34 = r4
            r2 = r31
        L_0x0614:
            double r2 = org.apache.commons.math3.util.FastMath.min((double) r2, (double) r13)
            if (r8 != 0) goto L_0x061b
            double r2 = -r2
        L_0x061b:
            if (r37 == 0) goto L_0x0622
            r12.isLastStep = r0
            r33 = 1
            goto L_0x0624
        L_0x0622:
            r33 = r0
        L_0x0624:
            boolean r4 = r12.isLastStep
            if (r4 == 0) goto L_0x0636
            double r2 = r12.stepStart
            r4 = r50
            r4.setTime(r2)
            r4.setCompleteState(r1)
            r49.resetInternalState()
            return
        L_0x0636:
            r4 = r50
            r24 = r0
            r9 = r1
            r1 = r2
            r23 = r5
            r13 = r10
            r19 = r11
            r10 = r15
            r0 = r36
            r31 = r38
            r15 = r42
            r14 = r46
            r11 = r8
            goto L_0x0137
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerIntegrator.integrate(org.apache.commons.math3.ode.ExpandableStatefulODE, double):void");
    }
}
