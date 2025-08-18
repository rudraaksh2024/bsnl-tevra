package org.apache.commons.math3.ode.nonstiff;

import org.apache.commons.math3.Field;
import org.apache.commons.math3.RealFieldElement;
import org.apache.commons.math3.ode.AbstractFieldIntegrator;
import org.apache.commons.math3.ode.FieldEquationsMapper;
import org.apache.commons.math3.ode.FieldODEStateAndDerivative;

public abstract class RungeKuttaFieldIntegrator<T extends RealFieldElement<T>> extends AbstractFieldIntegrator<T> implements FieldButcherArrayProvider<T> {
    private final T[][] a = getA();
    private final T[] b = getB();
    private final T[] c = getC();
    private final T step;

    /* access modifiers changed from: protected */
    public abstract RungeKuttaFieldStepInterpolator<T> createInterpolator(boolean z, T[][] tArr, FieldODEStateAndDerivative<T> fieldODEStateAndDerivative, FieldODEStateAndDerivative<T> fieldODEStateAndDerivative2, FieldEquationsMapper<T> fieldEquationsMapper);

    protected RungeKuttaFieldIntegrator(Field<T> field, String str, T t) {
        super(field, str);
        this.step = (RealFieldElement) t.abs();
    }

    /* access modifiers changed from: protected */
    public T fraction(int i, int i2) {
        return (RealFieldElement) ((RealFieldElement) ((RealFieldElement) getField().getZero()).add((double) i)).divide((double) i2);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:37:0x01fb, code lost:
        if (((org.apache.commons.math3.RealFieldElement) r0.subtract(r7)).getReal() >= 0.0d) goto L_0x020e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x020c, code lost:
        if (((org.apache.commons.math3.RealFieldElement) r0.subtract(r7)).getReal() <= 0.0d) goto L_0x020e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x0210, code lost:
        r0 = false;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public org.apache.commons.math3.ode.FieldODEStateAndDerivative<T> integrate(org.apache.commons.math3.ode.FieldExpandableODE<T> r20, org.apache.commons.math3.ode.FieldODEState<T> r21, T r22) throws org.apache.commons.math3.exception.NumberIsTooSmallException, org.apache.commons.math3.exception.DimensionMismatchException, org.apache.commons.math3.exception.MaxCountExceededException, org.apache.commons.math3.exception.NoBracketingException {
        /*
            r19 = this;
            r6 = r19
            r0 = r21
            r7 = r22
            r6.sanityChecks(r0, r7)
            org.apache.commons.math3.RealFieldElement r1 = r21.getTime()
            org.apache.commons.math3.ode.FieldEquationsMapper r2 = r20.getMapper()
            org.apache.commons.math3.RealFieldElement[] r8 = r2.mapState(r0)
            r9 = r20
            org.apache.commons.math3.ode.FieldODEStateAndDerivative r1 = r6.initIntegration(r9, r1, r8, r7)
            r6.setStepStart(r1)
            org.apache.commons.math3.RealFieldElement r0 = r21.getTime()
            java.lang.Object r0 = r7.subtract(r0)
            org.apache.commons.math3.RealFieldElement r0 = (org.apache.commons.math3.RealFieldElement) r0
            double r0 = r0.getReal()
            r10 = 0
            int r0 = (r0 > r10 ? 1 : (r0 == r10 ? 0 : -1))
            r12 = 0
            r13 = 1
            if (r0 <= 0) goto L_0x0036
            r14 = r13
            goto L_0x0037
        L_0x0036:
            r14 = r12
        L_0x0037:
            T[] r0 = r6.c
            int r0 = r0.length
            int r15 = r0 + 1
            org.apache.commons.math3.Field r0 = r19.getField()
            r1 = -1
            java.lang.Object[][] r0 = org.apache.commons.math3.util.MathArrays.buildArray(r0, r15, r1)
            r16 = r0
            org.apache.commons.math3.RealFieldElement[][] r16 = (org.apache.commons.math3.RealFieldElement[][]) r16
            org.apache.commons.math3.Field r0 = r19.getField()
            int r1 = r8.length
            java.lang.Object[] r0 = org.apache.commons.math3.util.MathArrays.buildArray(r0, r1)
            r5 = r0
            org.apache.commons.math3.RealFieldElement[] r5 = (org.apache.commons.math3.RealFieldElement[]) r5
            if (r14 == 0) goto L_0x008d
            org.apache.commons.math3.ode.FieldODEStateAndDerivative r0 = r19.getStepStart()
            org.apache.commons.math3.RealFieldElement r0 = r0.getTime()
            T r1 = r6.step
            java.lang.Object r0 = r0.add(r1)
            org.apache.commons.math3.RealFieldElement r0 = (org.apache.commons.math3.RealFieldElement) r0
            java.lang.Object r0 = r0.subtract(r7)
            org.apache.commons.math3.RealFieldElement r0 = (org.apache.commons.math3.RealFieldElement) r0
            double r0 = r0.getReal()
            int r0 = (r0 > r10 ? 1 : (r0 == r10 ? 0 : -1))
            if (r0 < 0) goto L_0x0087
            org.apache.commons.math3.ode.FieldODEStateAndDerivative r0 = r19.getStepStart()
            org.apache.commons.math3.RealFieldElement r0 = r0.getTime()
            java.lang.Object r0 = r7.subtract(r0)
            org.apache.commons.math3.RealFieldElement r0 = (org.apache.commons.math3.RealFieldElement) r0
            r6.setStepSize(r0)
            goto L_0x00c8
        L_0x0087:
            T r0 = r6.step
            r6.setStepSize(r0)
            goto L_0x00c8
        L_0x008d:
            org.apache.commons.math3.ode.FieldODEStateAndDerivative r0 = r19.getStepStart()
            org.apache.commons.math3.RealFieldElement r0 = r0.getTime()
            T r1 = r6.step
            java.lang.Object r0 = r0.subtract(r1)
            org.apache.commons.math3.RealFieldElement r0 = (org.apache.commons.math3.RealFieldElement) r0
            java.lang.Object r0 = r0.subtract(r7)
            org.apache.commons.math3.RealFieldElement r0 = (org.apache.commons.math3.RealFieldElement) r0
            double r0 = r0.getReal()
            int r0 = (r0 > r10 ? 1 : (r0 == r10 ? 0 : -1))
            if (r0 > 0) goto L_0x00bd
            org.apache.commons.math3.ode.FieldODEStateAndDerivative r0 = r19.getStepStart()
            org.apache.commons.math3.RealFieldElement r0 = r0.getTime()
            java.lang.Object r0 = r7.subtract(r0)
            org.apache.commons.math3.RealFieldElement r0 = (org.apache.commons.math3.RealFieldElement) r0
            r6.setStepSize(r0)
            goto L_0x00c8
        L_0x00bd:
            T r0 = r6.step
            java.lang.Object r0 = r0.negate()
            org.apache.commons.math3.RealFieldElement r0 = (org.apache.commons.math3.RealFieldElement) r0
            r6.setStepSize(r0)
        L_0x00c8:
            r6.setIsLastStep(r12)
        L_0x00cb:
            org.apache.commons.math3.ode.FieldEquationsMapper r0 = r20.getMapper()
            org.apache.commons.math3.ode.FieldODEStateAndDerivative r1 = r19.getStepStart()
            org.apache.commons.math3.RealFieldElement[] r0 = r0.mapState(r1)
            org.apache.commons.math3.ode.FieldEquationsMapper r1 = r20.getMapper()
            org.apache.commons.math3.ode.FieldODEStateAndDerivative r2 = r19.getStepStart()
            org.apache.commons.math3.RealFieldElement[] r1 = r1.mapDerivative(r2)
            r16[r12] = r1
            r1 = r13
        L_0x00e6:
            if (r1 >= r15) goto L_0x015b
            r2 = r12
        L_0x00e9:
            int r3 = r8.length
            if (r2 >= r3) goto L_0x0133
            r3 = r16[r12]
            r3 = r3[r2]
            T[][] r4 = r6.a
            int r17 = r1 + -1
            r4 = r4[r17]
            r4 = r4[r12]
            java.lang.Object r3 = r3.multiply(r4)
            org.apache.commons.math3.RealFieldElement r3 = (org.apache.commons.math3.RealFieldElement) r3
            r4 = r13
        L_0x00ff:
            if (r4 >= r1) goto L_0x011b
            r18 = r16[r4]
            r13 = r18[r2]
            T[][] r10 = r6.a
            r10 = r10[r17]
            r10 = r10[r4]
            java.lang.Object r10 = r13.multiply(r10)
            java.lang.Object r3 = r3.add(r10)
            org.apache.commons.math3.RealFieldElement r3 = (org.apache.commons.math3.RealFieldElement) r3
            int r4 = r4 + 1
            r10 = 0
            r13 = 1
            goto L_0x00ff
        L_0x011b:
            r4 = r0[r2]
            org.apache.commons.math3.RealFieldElement r10 = r19.getStepSize()
            java.lang.Object r3 = r10.multiply(r3)
            java.lang.Object r3 = r4.add(r3)
            org.apache.commons.math3.RealFieldElement r3 = (org.apache.commons.math3.RealFieldElement) r3
            r5[r2] = r3
            int r2 = r2 + 1
            r10 = 0
            r13 = 1
            goto L_0x00e9
        L_0x0133:
            org.apache.commons.math3.ode.FieldODEStateAndDerivative r2 = r19.getStepStart()
            org.apache.commons.math3.RealFieldElement r2 = r2.getTime()
            org.apache.commons.math3.RealFieldElement r3 = r19.getStepSize()
            T[] r4 = r6.c
            int r10 = r1 + -1
            r4 = r4[r10]
            java.lang.Object r3 = r3.multiply(r4)
            java.lang.Object r2 = r2.add(r3)
            org.apache.commons.math3.RealFieldElement r2 = (org.apache.commons.math3.RealFieldElement) r2
            org.apache.commons.math3.RealFieldElement[] r2 = r6.computeDerivatives(r2, r5)
            r16[r1] = r2
            int r1 = r1 + 1
            r10 = 0
            r13 = 1
            goto L_0x00e6
        L_0x015b:
            r1 = r12
        L_0x015c:
            int r2 = r8.length
            if (r1 >= r2) goto L_0x019a
            r2 = r16[r12]
            r2 = r2[r1]
            T[] r3 = r6.b
            r3 = r3[r12]
            java.lang.Object r2 = r2.multiply(r3)
            org.apache.commons.math3.RealFieldElement r2 = (org.apache.commons.math3.RealFieldElement) r2
            r3 = 1
        L_0x016e:
            if (r3 >= r15) goto L_0x0185
            r4 = r16[r3]
            r4 = r4[r1]
            T[] r10 = r6.b
            r10 = r10[r3]
            java.lang.Object r4 = r4.multiply(r10)
            java.lang.Object r2 = r2.add(r4)
            org.apache.commons.math3.RealFieldElement r2 = (org.apache.commons.math3.RealFieldElement) r2
            int r3 = r3 + 1
            goto L_0x016e
        L_0x0185:
            r3 = r0[r1]
            org.apache.commons.math3.RealFieldElement r4 = r19.getStepSize()
            java.lang.Object r2 = r4.multiply(r2)
            java.lang.Object r2 = r3.add(r2)
            org.apache.commons.math3.RealFieldElement r2 = (org.apache.commons.math3.RealFieldElement) r2
            r5[r1] = r2
            int r1 = r1 + 1
            goto L_0x015c
        L_0x019a:
            org.apache.commons.math3.ode.FieldODEStateAndDerivative r1 = r19.getStepStart()
            org.apache.commons.math3.RealFieldElement r1 = r1.getTime()
            org.apache.commons.math3.RealFieldElement r2 = r19.getStepSize()
            java.lang.Object r1 = r1.add(r2)
            org.apache.commons.math3.RealFieldElement r1 = (org.apache.commons.math3.RealFieldElement) r1
            org.apache.commons.math3.RealFieldElement[] r2 = r6.computeDerivatives(r1, r5)
            org.apache.commons.math3.ode.FieldODEStateAndDerivative r4 = new org.apache.commons.math3.ode.FieldODEStateAndDerivative
            r4.<init>(r1, r5, r2)
            int r1 = r8.length
            java.lang.System.arraycopy(r5, r12, r0, r12, r1)
            org.apache.commons.math3.ode.FieldODEStateAndDerivative r3 = r19.getStepStart()
            org.apache.commons.math3.ode.FieldEquationsMapper r10 = r20.getMapper()
            r0 = r19
            r1 = r14
            r2 = r16
            r11 = r5
            r5 = r10
            org.apache.commons.math3.ode.nonstiff.RungeKuttaFieldStepInterpolator r0 = r0.createInterpolator(r1, r2, r3, r4, r5)
            org.apache.commons.math3.ode.FieldODEStateAndDerivative r0 = r6.acceptStep(r0, r7)
            r6.setStepStart(r0)
            boolean r0 = r19.isLastStep()
            if (r0 != 0) goto L_0x0225
            org.apache.commons.math3.ode.FieldODEStateAndDerivative r0 = r19.getStepStart()
            org.apache.commons.math3.RealFieldElement r0 = r0.getTime()
            org.apache.commons.math3.RealFieldElement r1 = r19.getStepSize()
            java.lang.Object r0 = r0.add(r1)
            org.apache.commons.math3.RealFieldElement r0 = (org.apache.commons.math3.RealFieldElement) r0
            if (r14 == 0) goto L_0x01fe
            java.lang.Object r0 = r0.subtract(r7)
            org.apache.commons.math3.RealFieldElement r0 = (org.apache.commons.math3.RealFieldElement) r0
            double r0 = r0.getReal()
            r2 = 0
            int r0 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r0 < 0) goto L_0x0210
            goto L_0x020e
        L_0x01fe:
            r2 = 0
            java.lang.Object r0 = r0.subtract(r7)
            org.apache.commons.math3.RealFieldElement r0 = (org.apache.commons.math3.RealFieldElement) r0
            double r0 = r0.getReal()
            int r0 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r0 > 0) goto L_0x0210
        L_0x020e:
            r0 = 1
            goto L_0x0211
        L_0x0210:
            r0 = r12
        L_0x0211:
            if (r0 == 0) goto L_0x0227
            org.apache.commons.math3.ode.FieldODEStateAndDerivative r0 = r19.getStepStart()
            org.apache.commons.math3.RealFieldElement r0 = r0.getTime()
            java.lang.Object r0 = r7.subtract(r0)
            org.apache.commons.math3.RealFieldElement r0 = (org.apache.commons.math3.RealFieldElement) r0
            r6.setStepSize(r0)
            goto L_0x0227
        L_0x0225:
            r2 = 0
        L_0x0227:
            boolean r0 = r19.isLastStep()
            if (r0 == 0) goto L_0x0239
            org.apache.commons.math3.ode.FieldODEStateAndDerivative r0 = r19.getStepStart()
            r1 = 0
            r6.setStepStart(r1)
            r6.setStepSize(r1)
            return r0
        L_0x0239:
            r5 = r11
            r13 = 1
            r10 = r2
            goto L_0x00cb
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.math3.ode.nonstiff.RungeKuttaFieldIntegrator.integrate(org.apache.commons.math3.ode.FieldExpandableODE, org.apache.commons.math3.ode.FieldODEState, org.apache.commons.math3.RealFieldElement):org.apache.commons.math3.ode.FieldODEStateAndDerivative");
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v26, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v6, resolved type: org.apache.commons.math3.RealFieldElement} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public T[] singleStep(org.apache.commons.math3.ode.FirstOrderFieldDifferentialEquations<T> r18, T r19, T[] r20, T r21) {
        /*
            r17 = this;
            r0 = r17
            r1 = r18
            r2 = r19
            r3 = r20
            java.lang.Object r4 = r20.clone()
            org.apache.commons.math3.RealFieldElement[] r4 = (org.apache.commons.math3.RealFieldElement[]) r4
            T[] r5 = r0.c
            int r5 = r5.length
            r6 = 1
            int r5 = r5 + r6
            org.apache.commons.math3.Field r7 = r17.getField()
            r8 = -1
            java.lang.Object[][] r7 = org.apache.commons.math3.util.MathArrays.buildArray(r7, r5, r8)
            org.apache.commons.math3.RealFieldElement[][] r7 = (org.apache.commons.math3.RealFieldElement[][]) r7
            java.lang.Object r8 = r20.clone()
            org.apache.commons.math3.RealFieldElement[] r8 = (org.apache.commons.math3.RealFieldElement[]) r8
            r9 = r21
            java.lang.Object r9 = r9.subtract(r2)
            org.apache.commons.math3.RealFieldElement r9 = (org.apache.commons.math3.RealFieldElement) r9
            org.apache.commons.math3.RealFieldElement[] r10 = r1.computeDerivatives(r2, r4)
            r11 = 0
            r7[r11] = r10
            r10 = r6
        L_0x0034:
            if (r10 >= r5) goto L_0x0097
            r12 = r11
        L_0x0037:
            int r13 = r3.length
            if (r12 >= r13) goto L_0x007c
            r13 = r7[r11]
            r13 = r13[r12]
            T[][] r14 = r0.a
            int r15 = r10 + -1
            r14 = r14[r15]
            r14 = r14[r11]
            java.lang.Object r13 = r13.multiply(r14)
            org.apache.commons.math3.RealFieldElement r13 = (org.apache.commons.math3.RealFieldElement) r13
            r14 = r6
        L_0x004d:
            if (r14 >= r10) goto L_0x0069
            r16 = r7[r14]
            r6 = r16[r12]
            T[][] r11 = r0.a
            r11 = r11[r15]
            r11 = r11[r14]
            java.lang.Object r6 = r6.multiply(r11)
            java.lang.Object r6 = r13.add(r6)
            r13 = r6
            org.apache.commons.math3.RealFieldElement r13 = (org.apache.commons.math3.RealFieldElement) r13
            int r14 = r14 + 1
            r6 = 1
            r11 = 0
            goto L_0x004d
        L_0x0069:
            r6 = r4[r12]
            java.lang.Object r11 = r9.multiply(r13)
            java.lang.Object r6 = r6.add(r11)
            org.apache.commons.math3.RealFieldElement r6 = (org.apache.commons.math3.RealFieldElement) r6
            r8[r12] = r6
            int r12 = r12 + 1
            r6 = 1
            r11 = 0
            goto L_0x0037
        L_0x007c:
            T[] r6 = r0.c
            int r11 = r10 + -1
            r6 = r6[r11]
            java.lang.Object r6 = r9.multiply(r6)
            java.lang.Object r6 = r2.add(r6)
            org.apache.commons.math3.RealFieldElement r6 = (org.apache.commons.math3.RealFieldElement) r6
            org.apache.commons.math3.RealFieldElement[] r6 = r1.computeDerivatives(r6, r8)
            r7[r10] = r6
            int r10 = r10 + 1
            r6 = 1
            r11 = 0
            goto L_0x0034
        L_0x0097:
            r1 = 0
        L_0x0098:
            int r2 = r3.length
            if (r1 >= r2) goto L_0x00d3
            r2 = 0
            r6 = r7[r2]
            r6 = r6[r1]
            T[] r8 = r0.b
            r8 = r8[r2]
            java.lang.Object r6 = r6.multiply(r8)
            org.apache.commons.math3.RealFieldElement r6 = (org.apache.commons.math3.RealFieldElement) r6
            r8 = 1
        L_0x00ab:
            if (r8 >= r5) goto L_0x00c2
            r10 = r7[r8]
            r10 = r10[r1]
            T[] r11 = r0.b
            r11 = r11[r8]
            java.lang.Object r10 = r10.multiply(r11)
            java.lang.Object r6 = r6.add(r10)
            org.apache.commons.math3.RealFieldElement r6 = (org.apache.commons.math3.RealFieldElement) r6
            int r8 = r8 + 1
            goto L_0x00ab
        L_0x00c2:
            r8 = r4[r1]
            java.lang.Object r6 = r9.multiply(r6)
            java.lang.Object r6 = r8.add(r6)
            org.apache.commons.math3.RealFieldElement r6 = (org.apache.commons.math3.RealFieldElement) r6
            r4[r1] = r6
            int r1 = r1 + 1
            goto L_0x0098
        L_0x00d3:
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.math3.ode.nonstiff.RungeKuttaFieldIntegrator.singleStep(org.apache.commons.math3.ode.FirstOrderFieldDifferentialEquations, org.apache.commons.math3.RealFieldElement, org.apache.commons.math3.RealFieldElement[], org.apache.commons.math3.RealFieldElement):org.apache.commons.math3.RealFieldElement[]");
    }
}
