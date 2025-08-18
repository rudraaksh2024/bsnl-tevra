package org.apache.commons.math3.ode.nonstiff;

import org.apache.commons.math3.Field;
import org.apache.commons.math3.RealFieldElement;
import org.apache.commons.math3.ode.FieldEquationsMapper;
import org.apache.commons.math3.ode.FieldODEStateAndDerivative;

public abstract class EmbeddedRungeKuttaFieldIntegrator<T extends RealFieldElement<T>> extends AdaptiveStepsizeFieldIntegrator<T> implements FieldButcherArrayProvider<T> {
    private final T[][] a = getA();
    private final T[] b = getB();
    private final T[] c = getC();
    private final T exp;
    private final int fsal;
    private T maxGrowth;
    private T minReduction;
    private T safety;

    /* access modifiers changed from: protected */
    public abstract RungeKuttaFieldStepInterpolator<T> createInterpolator(boolean z, T[][] tArr, FieldODEStateAndDerivative<T> fieldODEStateAndDerivative, FieldODEStateAndDerivative<T> fieldODEStateAndDerivative2, FieldEquationsMapper<T> fieldEquationsMapper);

    /* access modifiers changed from: protected */
    public abstract T estimateError(T[][] tArr, T[] tArr2, T[] tArr3, T t);

    public abstract int getOrder();

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    protected EmbeddedRungeKuttaFieldIntegrator(Field<T> field, String str, int i, double d, double d2, double d3, double d4) {
        super(field, str, d, d2, d3, d4);
        this.fsal = i;
        this.exp = (RealFieldElement) ((RealFieldElement) field.getOne()).divide((double) (-getOrder()));
        setSafety((RealFieldElement) ((RealFieldElement) field.getZero()).add(0.9d));
        setMinReduction((RealFieldElement) ((RealFieldElement) field.getZero()).add(0.2d));
        setMaxGrowth((RealFieldElement) ((RealFieldElement) field.getZero()).add(10.0d));
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    protected EmbeddedRungeKuttaFieldIntegrator(Field<T> field, String str, int i, double d, double d2, double[] dArr, double[] dArr2) {
        super(field, str, d, d2, dArr, dArr2);
        this.fsal = i;
        this.exp = (RealFieldElement) ((RealFieldElement) field.getOne()).divide((double) (-getOrder()));
        setSafety((RealFieldElement) ((RealFieldElement) field.getZero()).add(0.9d));
        setMinReduction((RealFieldElement) ((RealFieldElement) field.getZero()).add(0.2d));
        setMaxGrowth((RealFieldElement) ((RealFieldElement) field.getZero()).add(10.0d));
    }

    /* access modifiers changed from: protected */
    public T fraction(int i, int i2) {
        return (RealFieldElement) ((RealFieldElement) ((RealFieldElement) getField().getOne()).multiply(i)).divide((double) i2);
    }

    /* access modifiers changed from: protected */
    public T fraction(double d, double d2) {
        return (RealFieldElement) ((RealFieldElement) ((RealFieldElement) getField().getOne()).multiply(d)).divide(d2);
    }

    public T getSafety() {
        return this.safety;
    }

    public void setSafety(T t) {
        this.safety = t;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:68:0x034e, code lost:
        if (((org.apache.commons.math3.RealFieldElement) r1.subtract(r7)).getReal() >= 0.0d) goto L_0x0361;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:70:0x035f, code lost:
        if (((org.apache.commons.math3.RealFieldElement) r1.subtract(r7)).getReal() <= 0.0d) goto L_0x0361;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:72:0x0363, code lost:
        r1 = false;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public org.apache.commons.math3.ode.FieldODEStateAndDerivative<T> integrate(org.apache.commons.math3.ode.FieldExpandableODE<T> r23, org.apache.commons.math3.ode.FieldODEState<T> r24, T r25) throws org.apache.commons.math3.exception.NumberIsTooSmallException, org.apache.commons.math3.exception.DimensionMismatchException, org.apache.commons.math3.exception.MaxCountExceededException, org.apache.commons.math3.exception.NoBracketingException {
        /*
            r22 = this;
            r6 = r22
            r0 = r24
            r7 = r25
            r6.sanityChecks(r0, r7)
            org.apache.commons.math3.RealFieldElement r1 = r24.getTime()
            org.apache.commons.math3.ode.FieldEquationsMapper r2 = r23.getMapper()
            org.apache.commons.math3.RealFieldElement[] r8 = r2.mapState(r0)
            r9 = r23
            org.apache.commons.math3.ode.FieldODEStateAndDerivative r1 = r6.initIntegration(r9, r1, r8, r7)
            r6.setStepStart(r1)
            org.apache.commons.math3.RealFieldElement r0 = r24.getTime()
            java.lang.Object r0 = r7.subtract(r0)
            org.apache.commons.math3.RealFieldElement r0 = (org.apache.commons.math3.RealFieldElement) r0
            double r0 = r0.getReal()
            r10 = 0
            int r0 = (r0 > r10 ? 1 : (r0 == r10 ? 0 : -1))
            r12 = 1
            r13 = 0
            if (r0 <= 0) goto L_0x0036
            r14 = r12
            goto L_0x0037
        L_0x0036:
            r14 = r13
        L_0x0037:
            T[] r0 = r6.c
            int r0 = r0.length
            int r15 = r0 + 1
            org.apache.commons.math3.Field r0 = r22.getField()
            r1 = -1
            java.lang.Object[][] r0 = org.apache.commons.math3.util.MathArrays.buildArray(r0, r15, r1)
            r5 = r0
            org.apache.commons.math3.RealFieldElement[][] r5 = (org.apache.commons.math3.RealFieldElement[][]) r5
            org.apache.commons.math3.Field r0 = r22.getField()
            int r1 = r8.length
            java.lang.Object[] r0 = org.apache.commons.math3.util.MathArrays.buildArray(r0, r1)
            r4 = r0
            org.apache.commons.math3.RealFieldElement[] r4 = (org.apache.commons.math3.RealFieldElement[]) r4
            org.apache.commons.math3.Field r0 = r22.getField()
            java.lang.Object r0 = r0.getZero()
            org.apache.commons.math3.RealFieldElement r0 = (org.apache.commons.math3.RealFieldElement) r0
            r6.setIsLastStep(r13)
            r1 = r8
            r2 = r12
        L_0x0063:
            org.apache.commons.math3.Field r3 = r22.getField()
            java.lang.Object r3 = r3.getZero()
            org.apache.commons.math3.RealFieldElement r3 = (org.apache.commons.math3.RealFieldElement) r3
            r12 = 4621819117588971520(0x4024000000000000, double:10.0)
            java.lang.Object r3 = r3.add(r12)
            org.apache.commons.math3.RealFieldElement r3 = (org.apache.commons.math3.RealFieldElement) r3
            r12 = r0
            r13 = r1
            r17 = r2
        L_0x0079:
            r1 = 4607182418800017408(0x3ff0000000000000, double:1.0)
            java.lang.Object r0 = r3.subtract(r1)
            org.apache.commons.math3.RealFieldElement r0 = (org.apache.commons.math3.RealFieldElement) r0
            double r18 = r0.getReal()
            int r0 = (r18 > r10 ? 1 : (r18 == r10 ? 0 : -1))
            if (r0 < 0) goto L_0x028a
            org.apache.commons.math3.ode.FieldEquationsMapper r0 = r23.getMapper()
            org.apache.commons.math3.ode.FieldODEStateAndDerivative r3 = r22.getStepStart()
            org.apache.commons.math3.RealFieldElement[] r13 = r0.mapState(r3)
            org.apache.commons.math3.ode.FieldEquationsMapper r0 = r23.getMapper()
            org.apache.commons.math3.ode.FieldODEStateAndDerivative r3 = r22.getStepStart()
            org.apache.commons.math3.RealFieldElement[] r0 = r0.mapDerivative(r3)
            r3 = 0
            r5[r3] = r0
            if (r17 == 0) goto L_0x011d
            org.apache.commons.math3.Field r0 = r22.getField()
            int r3 = r6.mainSetDimension
            java.lang.Object[] r0 = org.apache.commons.math3.util.MathArrays.buildArray(r0, r3)
            r3 = r0
            org.apache.commons.math3.RealFieldElement[] r3 = (org.apache.commons.math3.RealFieldElement[]) r3
            double[] r0 = r6.vecAbsoluteTolerance
            if (r0 != 0) goto L_0x00dc
            r0 = 0
        L_0x00b8:
            int r12 = r3.length
            if (r0 >= r12) goto L_0x0101
            r12 = r13[r0]
            java.lang.Object r12 = r12.abs()
            org.apache.commons.math3.RealFieldElement r12 = (org.apache.commons.math3.RealFieldElement) r12
            double r1 = r6.scalRelativeTolerance
            java.lang.Object r1 = r12.multiply(r1)
            org.apache.commons.math3.RealFieldElement r1 = (org.apache.commons.math3.RealFieldElement) r1
            double r10 = r6.scalAbsoluteTolerance
            java.lang.Object r1 = r1.add(r10)
            org.apache.commons.math3.RealFieldElement r1 = (org.apache.commons.math3.RealFieldElement) r1
            r3[r0] = r1
            int r0 = r0 + 1
            r1 = 4607182418800017408(0x3ff0000000000000, double:1.0)
            r10 = 0
            goto L_0x00b8
        L_0x00dc:
            r0 = 0
        L_0x00dd:
            int r1 = r3.length
            if (r0 >= r1) goto L_0x0101
            r1 = r13[r0]
            java.lang.Object r1 = r1.abs()
            org.apache.commons.math3.RealFieldElement r1 = (org.apache.commons.math3.RealFieldElement) r1
            double[] r2 = r6.vecRelativeTolerance
            r10 = r2[r0]
            java.lang.Object r1 = r1.multiply(r10)
            org.apache.commons.math3.RealFieldElement r1 = (org.apache.commons.math3.RealFieldElement) r1
            double[] r2 = r6.vecAbsoluteTolerance
            r10 = r2[r0]
            java.lang.Object r1 = r1.add(r10)
            org.apache.commons.math3.RealFieldElement r1 = (org.apache.commons.math3.RealFieldElement) r1
            r3[r0] = r1
            int r0 = r0 + 1
            goto L_0x00dd
        L_0x0101:
            int r2 = r22.getOrder()
            org.apache.commons.math3.ode.FieldODEStateAndDerivative r10 = r22.getStepStart()
            org.apache.commons.math3.ode.FieldEquationsMapper r11 = r23.getMapper()
            r0 = r22
            r1 = r14
            r12 = r4
            r4 = r10
            r10 = r5
            r5 = r11
            org.apache.commons.math3.RealFieldElement r0 = r0.initializeStep(r1, r2, r3, r4, r5)
            r11 = r12
            r17 = 0
            r12 = r0
            goto L_0x011f
        L_0x011d:
            r11 = r4
            r10 = r5
        L_0x011f:
            r6.setStepSize(r12)
            if (r14 == 0) goto L_0x0158
            org.apache.commons.math3.ode.FieldODEStateAndDerivative r0 = r22.getStepStart()
            org.apache.commons.math3.RealFieldElement r0 = r0.getTime()
            org.apache.commons.math3.RealFieldElement r1 = r22.getStepSize()
            java.lang.Object r0 = r0.add(r1)
            org.apache.commons.math3.RealFieldElement r0 = (org.apache.commons.math3.RealFieldElement) r0
            java.lang.Object r0 = r0.subtract(r7)
            org.apache.commons.math3.RealFieldElement r0 = (org.apache.commons.math3.RealFieldElement) r0
            double r0 = r0.getReal()
            r2 = 0
            int r0 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r0 < 0) goto L_0x018b
            org.apache.commons.math3.ode.FieldODEStateAndDerivative r0 = r22.getStepStart()
            org.apache.commons.math3.RealFieldElement r0 = r0.getTime()
            java.lang.Object r0 = r7.subtract(r0)
            org.apache.commons.math3.RealFieldElement r0 = (org.apache.commons.math3.RealFieldElement) r0
            r6.setStepSize(r0)
            goto L_0x018b
        L_0x0158:
            org.apache.commons.math3.ode.FieldODEStateAndDerivative r0 = r22.getStepStart()
            org.apache.commons.math3.RealFieldElement r0 = r0.getTime()
            org.apache.commons.math3.RealFieldElement r1 = r22.getStepSize()
            java.lang.Object r0 = r0.add(r1)
            org.apache.commons.math3.RealFieldElement r0 = (org.apache.commons.math3.RealFieldElement) r0
            java.lang.Object r0 = r0.subtract(r7)
            org.apache.commons.math3.RealFieldElement r0 = (org.apache.commons.math3.RealFieldElement) r0
            double r0 = r0.getReal()
            r2 = 0
            int r0 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r0 > 0) goto L_0x018b
            org.apache.commons.math3.ode.FieldODEStateAndDerivative r0 = r22.getStepStart()
            org.apache.commons.math3.RealFieldElement r0 = r0.getTime()
            java.lang.Object r0 = r7.subtract(r0)
            org.apache.commons.math3.RealFieldElement r0 = (org.apache.commons.math3.RealFieldElement) r0
            r6.setStepSize(r0)
        L_0x018b:
            r0 = 1
        L_0x018c:
            if (r0 >= r15) goto L_0x01ff
            r1 = 0
        L_0x018f:
            int r2 = r8.length
            if (r1 >= r2) goto L_0x01d8
            r2 = 0
            r3 = r10[r2]
            r3 = r3[r1]
            T[][] r4 = r6.a
            int r5 = r0 + -1
            r4 = r4[r5]
            r4 = r4[r2]
            java.lang.Object r2 = r3.multiply(r4)
            org.apache.commons.math3.RealFieldElement r2 = (org.apache.commons.math3.RealFieldElement) r2
            r3 = 1
        L_0x01a6:
            if (r3 >= r0) goto L_0x01c1
            r4 = r10[r3]
            r4 = r4[r1]
            T[][] r9 = r6.a
            r9 = r9[r5]
            r9 = r9[r3]
            java.lang.Object r4 = r4.multiply(r9)
            java.lang.Object r2 = r2.add(r4)
            org.apache.commons.math3.RealFieldElement r2 = (org.apache.commons.math3.RealFieldElement) r2
            int r3 = r3 + 1
            r9 = r23
            goto L_0x01a6
        L_0x01c1:
            r3 = r13[r1]
            org.apache.commons.math3.RealFieldElement r4 = r22.getStepSize()
            java.lang.Object r2 = r4.multiply(r2)
            java.lang.Object r2 = r3.add(r2)
            org.apache.commons.math3.RealFieldElement r2 = (org.apache.commons.math3.RealFieldElement) r2
            r11[r1] = r2
            int r1 = r1 + 1
            r9 = r23
            goto L_0x018f
        L_0x01d8:
            org.apache.commons.math3.ode.FieldODEStateAndDerivative r1 = r22.getStepStart()
            org.apache.commons.math3.RealFieldElement r1 = r1.getTime()
            org.apache.commons.math3.RealFieldElement r2 = r22.getStepSize()
            T[] r3 = r6.c
            int r4 = r0 + -1
            r3 = r3[r4]
            java.lang.Object r2 = r2.multiply(r3)
            java.lang.Object r1 = r1.add(r2)
            org.apache.commons.math3.RealFieldElement r1 = (org.apache.commons.math3.RealFieldElement) r1
            org.apache.commons.math3.RealFieldElement[] r1 = r6.computeDerivatives(r1, r11)
            r10[r0] = r1
            int r0 = r0 + 1
            r9 = r23
            goto L_0x018c
        L_0x01ff:
            r0 = 0
        L_0x0200:
            int r1 = r8.length
            if (r0 >= r1) goto L_0x023f
            r1 = 0
            r2 = r10[r1]
            r2 = r2[r0]
            T[] r3 = r6.b
            r3 = r3[r1]
            java.lang.Object r1 = r2.multiply(r3)
            org.apache.commons.math3.RealFieldElement r1 = (org.apache.commons.math3.RealFieldElement) r1
            r2 = 1
        L_0x0213:
            if (r2 >= r15) goto L_0x022a
            r3 = r10[r2]
            r3 = r3[r0]
            T[] r4 = r6.b
            r4 = r4[r2]
            java.lang.Object r3 = r3.multiply(r4)
            java.lang.Object r1 = r1.add(r3)
            org.apache.commons.math3.RealFieldElement r1 = (org.apache.commons.math3.RealFieldElement) r1
            int r2 = r2 + 1
            goto L_0x0213
        L_0x022a:
            r2 = r13[r0]
            org.apache.commons.math3.RealFieldElement r3 = r22.getStepSize()
            java.lang.Object r1 = r3.multiply(r1)
            java.lang.Object r1 = r2.add(r1)
            org.apache.commons.math3.RealFieldElement r1 = (org.apache.commons.math3.RealFieldElement) r1
            r11[r0] = r1
            int r0 = r0 + 1
            goto L_0x0200
        L_0x023f:
            org.apache.commons.math3.RealFieldElement r0 = r22.getStepSize()
            org.apache.commons.math3.RealFieldElement r3 = r6.estimateError(r10, r13, r11, r0)
            r0 = 4607182418800017408(0x3ff0000000000000, double:1.0)
            java.lang.Object r0 = r3.subtract(r0)
            org.apache.commons.math3.RealFieldElement r0 = (org.apache.commons.math3.RealFieldElement) r0
            double r0 = r0.getReal()
            r4 = 0
            int r0 = (r0 > r4 ? 1 : (r0 == r4 ? 0 : -1))
            if (r0 < 0) goto L_0x0282
            T r0 = r6.maxGrowth
            T r1 = r6.minReduction
            T r2 = r6.safety
            T r4 = r6.exp
            java.lang.Object r4 = r3.pow(r4)
            java.lang.Object r2 = r2.multiply(r4)
            org.apache.commons.math3.RealFieldElement r2 = (org.apache.commons.math3.RealFieldElement) r2
            org.apache.commons.math3.RealFieldElement r1 = org.apache.commons.math3.util.MathUtils.max(r1, r2)
            org.apache.commons.math3.RealFieldElement r0 = org.apache.commons.math3.util.MathUtils.min(r0, r1)
            org.apache.commons.math3.RealFieldElement r1 = r22.getStepSize()
            java.lang.Object r0 = r1.multiply(r0)
            org.apache.commons.math3.RealFieldElement r0 = (org.apache.commons.math3.RealFieldElement) r0
            r1 = 0
            org.apache.commons.math3.RealFieldElement r12 = r6.filterStep(r0, r14, r1)
        L_0x0282:
            r9 = r23
            r5 = r10
            r4 = r11
            r10 = 0
            goto L_0x0079
        L_0x028a:
            r11 = r4
            r10 = r5
            org.apache.commons.math3.ode.FieldODEStateAndDerivative r0 = r22.getStepStart()
            org.apache.commons.math3.RealFieldElement r0 = r0.getTime()
            org.apache.commons.math3.RealFieldElement r1 = r22.getStepSize()
            java.lang.Object r0 = r0.add(r1)
            org.apache.commons.math3.RealFieldElement r0 = (org.apache.commons.math3.RealFieldElement) r0
            int r1 = r6.fsal
            if (r1 < 0) goto L_0x02a5
            r1 = r10[r1]
            goto L_0x02a9
        L_0x02a5:
            org.apache.commons.math3.RealFieldElement[] r1 = r6.computeDerivatives(r0, r11)
        L_0x02a9:
            org.apache.commons.math3.ode.FieldODEStateAndDerivative r4 = new org.apache.commons.math3.ode.FieldODEStateAndDerivative
            r4.<init>(r0, r11, r1)
            int r0 = r8.length
            r9 = 0
            java.lang.System.arraycopy(r11, r9, r13, r9, r0)
            org.apache.commons.math3.ode.FieldODEStateAndDerivative r5 = r22.getStepStart()
            org.apache.commons.math3.ode.FieldEquationsMapper r16 = r23.getMapper()
            r0 = r22
            r1 = r14
            r2 = r10
            r9 = r3
            r3 = r5
            r5 = r16
            org.apache.commons.math3.ode.nonstiff.RungeKuttaFieldStepInterpolator r0 = r0.createInterpolator(r1, r2, r3, r4, r5)
            org.apache.commons.math3.ode.FieldODEStateAndDerivative r0 = r6.acceptStep(r0, r7)
            r6.setStepStart(r0)
            boolean r0 = r22.isLastStep()
            if (r0 != 0) goto L_0x0375
            T r0 = r6.maxGrowth
            T r1 = r6.minReduction
            T r2 = r6.safety
            T r3 = r6.exp
            java.lang.Object r3 = r9.pow(r3)
            java.lang.Object r2 = r2.multiply(r3)
            org.apache.commons.math3.RealFieldElement r2 = (org.apache.commons.math3.RealFieldElement) r2
            org.apache.commons.math3.RealFieldElement r1 = org.apache.commons.math3.util.MathUtils.max(r1, r2)
            org.apache.commons.math3.RealFieldElement r0 = org.apache.commons.math3.util.MathUtils.min(r0, r1)
            org.apache.commons.math3.RealFieldElement r1 = r22.getStepSize()
            java.lang.Object r0 = r1.multiply(r0)
            org.apache.commons.math3.RealFieldElement r0 = (org.apache.commons.math3.RealFieldElement) r0
            org.apache.commons.math3.ode.FieldODEStateAndDerivative r1 = r22.getStepStart()
            org.apache.commons.math3.RealFieldElement r1 = r1.getTime()
            java.lang.Object r1 = r1.add(r0)
            org.apache.commons.math3.RealFieldElement r1 = (org.apache.commons.math3.RealFieldElement) r1
            if (r14 == 0) goto L_0x0319
            java.lang.Object r1 = r1.subtract(r7)
            org.apache.commons.math3.RealFieldElement r1 = (org.apache.commons.math3.RealFieldElement) r1
            double r1 = r1.getReal()
            r3 = 0
            int r1 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
            if (r1 < 0) goto L_0x032b
            goto L_0x0329
        L_0x0319:
            r3 = 0
            java.lang.Object r1 = r1.subtract(r7)
            org.apache.commons.math3.RealFieldElement r1 = (org.apache.commons.math3.RealFieldElement) r1
            double r1 = r1.getReal()
            int r1 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
            if (r1 > 0) goto L_0x032b
        L_0x0329:
            r3 = 1
            goto L_0x032c
        L_0x032b:
            r3 = 0
        L_0x032c:
            org.apache.commons.math3.RealFieldElement r0 = r6.filterStep(r0, r14, r3)
            org.apache.commons.math3.ode.FieldODEStateAndDerivative r1 = r22.getStepStart()
            org.apache.commons.math3.RealFieldElement r1 = r1.getTime()
            java.lang.Object r1 = r1.add(r0)
            org.apache.commons.math3.RealFieldElement r1 = (org.apache.commons.math3.RealFieldElement) r1
            if (r14 == 0) goto L_0x0351
            java.lang.Object r1 = r1.subtract(r7)
            org.apache.commons.math3.RealFieldElement r1 = (org.apache.commons.math3.RealFieldElement) r1
            double r1 = r1.getReal()
            r3 = 0
            int r1 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
            if (r1 < 0) goto L_0x0363
            goto L_0x0361
        L_0x0351:
            r3 = 0
            java.lang.Object r1 = r1.subtract(r7)
            org.apache.commons.math3.RealFieldElement r1 = (org.apache.commons.math3.RealFieldElement) r1
            double r1 = r1.getReal()
            int r1 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
            if (r1 > 0) goto L_0x0363
        L_0x0361:
            r1 = 1
            goto L_0x0364
        L_0x0363:
            r1 = 0
        L_0x0364:
            if (r1 == 0) goto L_0x0378
            org.apache.commons.math3.ode.FieldODEStateAndDerivative r0 = r22.getStepStart()
            org.apache.commons.math3.RealFieldElement r0 = r0.getTime()
            java.lang.Object r0 = r7.subtract(r0)
            org.apache.commons.math3.RealFieldElement r0 = (org.apache.commons.math3.RealFieldElement) r0
            goto L_0x0378
        L_0x0375:
            r3 = 0
            r0 = r12
        L_0x0378:
            boolean r1 = r22.isLastStep()
            if (r1 == 0) goto L_0x0386
            org.apache.commons.math3.ode.FieldODEStateAndDerivative r0 = r22.getStepStart()
            r22.resetInternalState()
            return r0
        L_0x0386:
            r9 = r23
            r5 = r10
            r1 = r13
            r2 = r17
            r12 = 1
            r13 = 0
            r20 = r3
            r4 = r11
            r10 = r20
            goto L_0x0063
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.math3.ode.nonstiff.EmbeddedRungeKuttaFieldIntegrator.integrate(org.apache.commons.math3.ode.FieldExpandableODE, org.apache.commons.math3.ode.FieldODEState, org.apache.commons.math3.RealFieldElement):org.apache.commons.math3.ode.FieldODEStateAndDerivative");
    }

    public T getMinReduction() {
        return this.minReduction;
    }

    public void setMinReduction(T t) {
        this.minReduction = t;
    }

    public T getMaxGrowth() {
        return this.maxGrowth;
    }

    public void setMaxGrowth(T t) {
        this.maxGrowth = t;
    }
}
