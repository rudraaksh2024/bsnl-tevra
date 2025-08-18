package org.apache.commons.math3.ode.nonstiff;

public abstract class EmbeddedRungeKuttaIntegrator extends AdaptiveStepsizeIntegrator {
    private final double[][] a;
    private final double[] b;
    private final double[] c;
    private final double exp = (-1.0d / ((double) getOrder()));
    private final boolean fsal;
    private double maxGrowth;
    private double minReduction;
    private final RungeKuttaStepInterpolator prototype;
    private double safety;

    /* access modifiers changed from: protected */
    public abstract double estimateError(double[][] dArr, double[] dArr2, double[] dArr3, double d);

    public abstract int getOrder();

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    protected EmbeddedRungeKuttaIntegrator(String str, boolean z, double[] dArr, double[][] dArr2, double[] dArr3, RungeKuttaStepInterpolator rungeKuttaStepInterpolator, double d, double d2, double d3, double d4) {
        super(str, d, d2, d3, d4);
        this.fsal = z;
        this.c = dArr;
        this.a = dArr2;
        this.b = dArr3;
        this.prototype = rungeKuttaStepInterpolator;
        setSafety(0.9d);
        setMinReduction(0.2d);
        setMaxGrowth(10.0d);
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    protected EmbeddedRungeKuttaIntegrator(String str, boolean z, double[] dArr, double[][] dArr2, double[] dArr3, RungeKuttaStepInterpolator rungeKuttaStepInterpolator, double d, double d2, double[] dArr4, double[] dArr5) {
        super(str, d, d2, dArr4, dArr5);
        this.fsal = z;
        this.c = dArr;
        this.a = dArr2;
        this.b = dArr3;
        this.prototype = rungeKuttaStepInterpolator;
        setSafety(0.9d);
        setMinReduction(0.2d);
        setMaxGrowth(10.0d);
    }

    public double getSafety() {
        return this.safety;
    }

    public void setSafety(double d) {
        this.safety = d;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v7, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r16v0, resolved type: double[][]} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void integrate(org.apache.commons.math3.ode.ExpandableStatefulODE r30, double r31) throws org.apache.commons.math3.exception.NumberIsTooSmallException, org.apache.commons.math3.exception.DimensionMismatchException, org.apache.commons.math3.exception.MaxCountExceededException, org.apache.commons.math3.exception.NoBracketingException {
        /*
            r29 = this;
            r10 = r29
            r11 = r30
            r29.sanityChecks(r30, r31)
            r29.setEquations(r30)
            double r0 = r30.getTime()
            int r0 = (r31 > r0 ? 1 : (r31 == r0 ? 0 : -1))
            r12 = 1
            r13 = 0
            if (r0 <= 0) goto L_0x0016
            r14 = r12
            goto L_0x0017
        L_0x0016:
            r14 = r13
        L_0x0017:
            double[] r15 = r30.getCompleteState()
            java.lang.Object r0 = r15.clone()
            r9 = r0
            double[] r9 = (double[]) r9
            double[] r0 = r10.c
            int r0 = r0.length
            int r8 = r0 + 1
            int r0 = r9.length
            r1 = 2
            int[] r1 = new int[r1]
            r1[r12] = r0
            r1[r13] = r8
            java.lang.Class r0 = java.lang.Double.TYPE
            java.lang.Object r0 = java.lang.reflect.Array.newInstance(r0, r1)
            r16 = r0
            double[][] r16 = (double[][]) r16
            java.lang.Object r0 = r15.clone()
            r7 = r0
            double[] r7 = (double[]) r7
            int r0 = r9.length
            double[] r6 = new double[r0]
            org.apache.commons.math3.ode.nonstiff.RungeKuttaStepInterpolator r0 = r10.prototype
            org.apache.commons.math3.ode.sampling.StepInterpolator r0 = r0.copy()
            r5 = r0
            org.apache.commons.math3.ode.nonstiff.RungeKuttaStepInterpolator r5 = (org.apache.commons.math3.ode.nonstiff.RungeKuttaStepInterpolator) r5
            org.apache.commons.math3.ode.EquationsMapper r17 = r30.getPrimaryMapper()
            org.apache.commons.math3.ode.EquationsMapper[] r18 = r30.getSecondaryMappers()
            r0 = r5
            r1 = r29
            r2 = r7
            r3 = r16
            r4 = r14
            r12 = r5
            r5 = r17
            r20 = r6
            r6 = r18
            r0.reinitialize(r1, r2, r3, r4, r5, r6)
            double r0 = r30.getTime()
            r12.storeTime(r0)
            double r0 = r30.getTime()
            r10.stepStart = r0
            double r1 = r30.getTime()
            r0 = r29
            r3 = r15
            r4 = r31
            r0.initIntegration(r1, r3, r4)
            r10.isLastStep = r13
            r0 = 0
            r2 = 1
        L_0x0083:
            r12.shift()
            r3 = 4621819117588971520(0x4024000000000000, double:10.0)
            r17 = r0
            r6 = r2
            r4 = r3
        L_0x008c:
            r21 = 4607182418800017408(0x3ff0000000000000, double:1.0)
            int r0 = (r4 > r21 ? 1 : (r4 == r21 ? 0 : -1))
            if (r0 < 0) goto L_0x01f6
            if (r6 != 0) goto L_0x0098
            boolean r0 = r10.fsal
            if (r0 != 0) goto L_0x009f
        L_0x0098:
            double r0 = r10.stepStart
            r2 = r16[r13]
            r10.computeDerivatives(r0, r9, r2)
        L_0x009f:
            if (r6 == 0) goto L_0x0102
            int r0 = r10.mainSetDimension
            double[] r3 = new double[r0]
            double[] r1 = r10.vecAbsoluteTolerance
            if (r1 != 0) goto L_0x00c6
            r1 = r13
        L_0x00aa:
            if (r1 >= r0) goto L_0x00c3
            double r4 = r10.scalAbsoluteTolerance
            r23 = r14
            double r13 = r10.scalRelativeTolerance
            r17 = r9[r1]
            double r17 = org.apache.commons.math3.util.FastMath.abs((double) r17)
            double r13 = r13 * r17
            double r4 = r4 + r13
            r3[r1] = r4
            int r1 = r1 + 1
            r14 = r23
            r13 = 0
            goto L_0x00aa
        L_0x00c3:
            r23 = r14
            goto L_0x00e1
        L_0x00c6:
            r23 = r14
            r1 = 0
        L_0x00c9:
            if (r1 >= r0) goto L_0x00e1
            double[] r2 = r10.vecAbsoluteTolerance
            r4 = r2[r1]
            double[] r2 = r10.vecRelativeTolerance
            r13 = r2[r1]
            r17 = r9[r1]
            double r17 = org.apache.commons.math3.util.FastMath.abs((double) r17)
            double r13 = r13 * r17
            double r4 = r4 + r13
            r3[r1] = r4
            int r1 = r1 + 1
            goto L_0x00c9
        L_0x00e1:
            int r2 = r29.getOrder()
            double r4 = r10.stepStart
            r0 = 0
            r13 = r16[r0]
            r14 = 1
            r17 = r16[r14]
            r0 = r29
            r1 = r23
            r6 = r9
            r19 = r7
            r7 = r13
            r13 = r8
            r8 = r19
            r14 = r9
            r9 = r17
            double r0 = r0.initializeStep(r1, r2, r3, r4, r6, r7, r8, r9)
            r7 = r0
            r6 = 0
            goto L_0x010a
        L_0x0102:
            r19 = r7
            r13 = r8
            r23 = r14
            r14 = r9
            r7 = r17
        L_0x010a:
            r10.stepSize = r7
            if (r23 == 0) goto L_0x011e
            double r0 = r10.stepStart
            double r2 = r10.stepSize
            double r0 = r0 + r2
            int r0 = (r0 > r31 ? 1 : (r0 == r31 ? 0 : -1))
            if (r0 < 0) goto L_0x012d
            double r0 = r10.stepStart
            double r0 = r31 - r0
            r10.stepSize = r0
            goto L_0x012d
        L_0x011e:
            double r0 = r10.stepStart
            double r2 = r10.stepSize
            double r0 = r0 + r2
            int r0 = (r0 > r31 ? 1 : (r0 == r31 ? 0 : -1))
            if (r0 > 0) goto L_0x012d
            double r0 = r10.stepStart
            double r0 = r31 - r0
            r10.stepSize = r0
        L_0x012d:
            r0 = 1
        L_0x012e:
            if (r0 >= r13) goto L_0x017f
            r1 = 0
        L_0x0131:
            int r2 = r15.length
            if (r1 >= r2) goto L_0x0165
            double[][] r2 = r10.a
            int r3 = r0 + -1
            r2 = r2[r3]
            r4 = 0
            r17 = r2[r4]
            r2 = r16[r4]
            r4 = r2[r1]
            double r17 = r17 * r4
            r2 = 1
        L_0x0144:
            if (r2 >= r0) goto L_0x0157
            double[][] r4 = r10.a
            r4 = r4[r3]
            r4 = r4[r2]
            r9 = r16[r2]
            r24 = r9[r1]
            double r4 = r4 * r24
            double r17 = r17 + r4
            int r2 = r2 + 1
            goto L_0x0144
        L_0x0157:
            r2 = r14[r1]
            double r4 = r10.stepSize
            double r4 = r4 * r17
            double r2 = r2 + r4
            r9 = r19
            r9[r1] = r2
            int r1 = r1 + 1
            goto L_0x0131
        L_0x0165:
            r9 = r19
            double r1 = r10.stepStart
            double[] r3 = r10.c
            int r4 = r0 + -1
            r3 = r3[r4]
            r17 = r6
            double r5 = r10.stepSize
            double r3 = r3 * r5
            double r1 = r1 + r3
            r3 = r16[r0]
            r10.computeDerivatives(r1, r9, r3)
            int r0 = r0 + 1
            r6 = r17
            goto L_0x012e
        L_0x017f:
            r17 = r6
            r9 = r19
            r0 = 0
        L_0x0184:
            int r1 = r15.length
            if (r0 >= r1) goto L_0x01ad
            double[] r1 = r10.b
            r2 = 0
            r3 = r1[r2]
            r1 = r16[r2]
            r1 = r1[r0]
            double r3 = r3 * r1
            r1 = 1
        L_0x0192:
            if (r1 >= r13) goto L_0x01a2
            double[] r2 = r10.b
            r5 = r2[r1]
            r2 = r16[r1]
            r18 = r2[r0]
            double r5 = r5 * r18
            double r3 = r3 + r5
            int r1 = r1 + 1
            goto L_0x0192
        L_0x01a2:
            r1 = r14[r0]
            double r5 = r10.stepSize
            double r5 = r5 * r3
            double r1 = r1 + r5
            r9[r0] = r1
            int r0 = r0 + 1
            goto L_0x0184
        L_0x01ad:
            double r4 = r10.stepSize
            r0 = r29
            r1 = r16
            r2 = r14
            r3 = r9
            double r4 = r0.estimateError(r1, r2, r3, r4)
            int r0 = (r4 > r21 ? 1 : (r4 == r21 ? 0 : -1))
            if (r0 < 0) goto L_0x01ea
            double r0 = r10.maxGrowth
            double r2 = r10.minReduction
            double r6 = r10.safety
            r19 = r9
            double r8 = r10.exp
            double r8 = org.apache.commons.math3.util.FastMath.pow((double) r4, (double) r8)
            double r6 = r6 * r8
            double r2 = org.apache.commons.math3.util.FastMath.max((double) r2, (double) r6)
            double r0 = org.apache.commons.math3.util.FastMath.min((double) r0, (double) r2)
            double r2 = r10.stepSize
            double r2 = r2 * r0
            r1 = r23
            r9 = 0
            double r2 = r10.filterStep(r2, r1, r9)
            r8 = r13
            r6 = r17
            r7 = r19
            r17 = r2
            r13 = r9
            r9 = r14
            r14 = r1
            goto L_0x008c
        L_0x01ea:
            r6 = r17
            r17 = r7
            r7 = r9
            r8 = r13
            r9 = r14
            r14 = r23
            r13 = 0
            goto L_0x008c
        L_0x01f6:
            r19 = r7
            r1 = r14
            r14 = r9
            r9 = r13
            r13 = r8
            double r2 = r10.stepStart
            double r7 = r10.stepSize
            double r2 = r2 + r7
            r12.storeTime(r2)
            int r0 = r15.length
            r7 = r19
            java.lang.System.arraycopy(r7, r9, r14, r9, r0)
            int r8 = r13 + -1
            r0 = r16[r8]
            int r2 = r15.length
            r8 = r20
            java.lang.System.arraycopy(r0, r9, r8, r9, r2)
            r0 = r29
            r3 = r1
            r1 = r12
            r2 = r14
            r26 = r3
            r3 = r8
            r27 = r4
            r4 = r31
            double r0 = r0.acceptStep(r1, r2, r3, r4)
            r10.stepStart = r0
            int r0 = r14.length
            java.lang.System.arraycopy(r14, r9, r7, r9, r0)
            boolean r0 = r10.isLastStep
            if (r0 != 0) goto L_0x028c
            double r0 = r10.stepStart
            r12.storeTime(r0)
            boolean r0 = r10.fsal
            if (r0 == 0) goto L_0x023d
            r0 = r16[r9]
            int r1 = r15.length
            java.lang.System.arraycopy(r8, r9, r0, r9, r1)
        L_0x023d:
            double r0 = r10.maxGrowth
            double r2 = r10.minReduction
            double r4 = r10.safety
            r19 = r6
            r20 = r7
            double r6 = r10.exp
            r9 = r27
            double r6 = org.apache.commons.math3.util.FastMath.pow((double) r9, (double) r6)
            double r4 = r4 * r6
            double r2 = org.apache.commons.math3.util.FastMath.max((double) r2, (double) r4)
            double r0 = org.apache.commons.math3.util.FastMath.min((double) r0, (double) r2)
            r2 = r29
            double r3 = r2.stepSize
            double r3 = r3 * r0
            double r0 = r2.stepStart
            double r0 = r0 + r3
            r5 = r26
            if (r5 == 0) goto L_0x0269
            int r0 = (r0 > r31 ? 1 : (r0 == r31 ? 0 : -1))
            if (r0 < 0) goto L_0x026f
            goto L_0x026d
        L_0x0269:
            int r0 = (r0 > r31 ? 1 : (r0 == r31 ? 0 : -1))
            if (r0 > 0) goto L_0x026f
        L_0x026d:
            r0 = 1
            goto L_0x0270
        L_0x026f:
            r0 = 0
        L_0x0270:
            double r0 = r2.filterStep(r3, r5, r0)
            double r3 = r2.stepStart
            double r3 = r3 + r0
            if (r5 == 0) goto L_0x027e
            int r3 = (r3 > r31 ? 1 : (r3 == r31 ? 0 : -1))
            if (r3 < 0) goto L_0x0284
            goto L_0x0282
        L_0x027e:
            int r3 = (r3 > r31 ? 1 : (r3 == r31 ? 0 : -1))
            if (r3 > 0) goto L_0x0284
        L_0x0282:
            r3 = 1
            goto L_0x0285
        L_0x0284:
            r3 = 0
        L_0x0285:
            if (r3 == 0) goto L_0x0295
            double r0 = r2.stepStart
            double r0 = r31 - r0
            goto L_0x0295
        L_0x028c:
            r19 = r6
            r20 = r7
            r2 = r10
            r5 = r26
            r0 = r17
        L_0x0295:
            boolean r3 = r2.isLastStep
            if (r3 == 0) goto L_0x02a5
            double r0 = r2.stepStart
            r11.setTime(r0)
            r11.setCompleteState(r14)
            r29.resetInternalState()
            return
        L_0x02a5:
            r10 = r2
            r9 = r14
            r2 = r19
            r7 = r20
            r14 = r5
            r20 = r8
            r8 = r13
            r13 = 0
            goto L_0x0083
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.math3.ode.nonstiff.EmbeddedRungeKuttaIntegrator.integrate(org.apache.commons.math3.ode.ExpandableStatefulODE, double):void");
    }

    public double getMinReduction() {
        return this.minReduction;
    }

    public void setMinReduction(double d) {
        this.minReduction = d;
    }

    public double getMaxGrowth() {
        return this.maxGrowth;
    }

    public void setMaxGrowth(double d) {
        this.maxGrowth = d;
    }
}
