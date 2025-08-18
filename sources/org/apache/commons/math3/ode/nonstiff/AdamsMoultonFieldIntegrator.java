package org.apache.commons.math3.ode.nonstiff;

import java.util.Arrays;
import org.apache.commons.math3.Field;
import org.apache.commons.math3.RealFieldElement;
import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.linear.FieldMatrixPreservingVisitor;
import org.apache.commons.math3.util.MathUtils;

public class AdamsMoultonFieldIntegrator<T extends RealFieldElement<T>> extends AdamsFieldIntegrator<T> {
    private static final String METHOD_NAME = "Adams-Moulton";

    public AdamsMoultonFieldIntegrator(Field<T> field, int i, double d, double d2, double d3, double d4) throws NumberIsTooSmallException {
        super(field, METHOD_NAME, i, i + 1, d, d2, d3, d4);
    }

    public AdamsMoultonFieldIntegrator(Field<T> field, int i, double d, double d2, double[] dArr, double[] dArr2) throws IllegalArgumentException {
        super(field, METHOD_NAME, i, i + 1, d, d2, dArr, dArr2);
    }

    /* JADX WARNING: type inference failed for: r11v19, types: [org.apache.commons.math3.FieldElement] */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x01f9, code lost:
        if (((org.apache.commons.math3.RealFieldElement) r7.subtract(r3)).getReal() >= 0.0d) goto L_0x020c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x020a, code lost:
        if (((org.apache.commons.math3.RealFieldElement) r7.subtract(r3)).getReal() <= 0.0d) goto L_0x020c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x020e, code lost:
        r7 = false;
     */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public org.apache.commons.math3.ode.FieldODEStateAndDerivative<T> integrate(org.apache.commons.math3.ode.FieldExpandableODE<T> r23, org.apache.commons.math3.ode.FieldODEState<T> r24, T r25) throws org.apache.commons.math3.exception.NumberIsTooSmallException, org.apache.commons.math3.exception.DimensionMismatchException, org.apache.commons.math3.exception.MaxCountExceededException, org.apache.commons.math3.exception.NoBracketingException {
        /*
            r22 = this;
            r0 = r22
            r1 = r23
            r2 = r24
            r3 = r25
            r0.sanityChecks(r2, r3)
            org.apache.commons.math3.RealFieldElement r4 = r24.getTime()
            org.apache.commons.math3.ode.FieldEquationsMapper r5 = r23.getMapper()
            org.apache.commons.math3.RealFieldElement[] r5 = r5.mapState(r2)
            org.apache.commons.math3.ode.FieldODEStateAndDerivative r4 = r0.initIntegration(r1, r4, r5, r3)
            r0.setStepStart(r4)
            org.apache.commons.math3.RealFieldElement r2 = r24.getTime()
            java.lang.Object r2 = r3.subtract(r2)
            org.apache.commons.math3.RealFieldElement r2 = (org.apache.commons.math3.RealFieldElement) r2
            double r6 = r2.getReal()
            r8 = 0
            int r2 = (r6 > r8 ? 1 : (r6 == r8 ? 0 : -1))
            r6 = 0
            if (r2 <= 0) goto L_0x0035
            r2 = 1
            goto L_0x0036
        L_0x0035:
            r2 = r6
        L_0x0036:
            org.apache.commons.math3.ode.FieldODEStateAndDerivative r7 = r22.getStepStart()
            r0.start(r1, r7, r3)
            org.apache.commons.math3.ode.FieldODEStateAndDerivative r7 = r22.getStepStart()
            org.apache.commons.math3.RealFieldElement r10 = r7.getTime()
            org.apache.commons.math3.RealFieldElement r11 = r22.getStepSize()
            java.lang.Object r10 = r10.add(r11)
            org.apache.commons.math3.RealFieldElement r10 = (org.apache.commons.math3.RealFieldElement) r10
            org.apache.commons.math3.RealFieldElement r11 = r22.getStepSize()
            org.apache.commons.math3.RealFieldElement[] r12 = r0.scaled
            org.apache.commons.math3.linear.Array2DRowFieldMatrix r13 = r0.nordsieck
            org.apache.commons.math3.ode.FieldODEStateAndDerivative r7 = org.apache.commons.math3.ode.nonstiff.AdamsFieldStepInterpolator.taylor(r7, r10, r11, r12, r13)
            r0.setIsLastStep(r6)
        L_0x005e:
            org.apache.commons.math3.Field r10 = r22.getField()
            int r11 = r5.length
            java.lang.Object[] r10 = org.apache.commons.math3.util.MathArrays.buildArray(r10, r11)
            org.apache.commons.math3.RealFieldElement[] r10 = (org.apache.commons.math3.RealFieldElement[]) r10
            org.apache.commons.math3.Field r11 = r22.getField()
            java.lang.Object r11 = r11.getZero()
            org.apache.commons.math3.RealFieldElement r11 = (org.apache.commons.math3.RealFieldElement) r11
            r12 = 4621819117588971520(0x4024000000000000, double:10.0)
            java.lang.Object r11 = r11.add(r12)
            org.apache.commons.math3.RealFieldElement r11 = (org.apache.commons.math3.RealFieldElement) r11
            r19 = r5
            r14 = r11
            r12 = 0
            r13 = 0
        L_0x0080:
            r4 = 4607182418800017408(0x3ff0000000000000, double:1.0)
            java.lang.Object r11 = r14.subtract(r4)
            org.apache.commons.math3.RealFieldElement r11 = (org.apache.commons.math3.RealFieldElement) r11
            double r16 = r11.getReal()
            int r11 = (r16 > r8 ? 1 : (r16 == r8 ? 0 : -1))
            if (r11 < 0) goto L_0x0116
            org.apache.commons.math3.RealFieldElement[] r13 = r7.getState()
            org.apache.commons.math3.RealFieldElement r11 = r7.getTime()
            org.apache.commons.math3.RealFieldElement[] r11 = r0.computeDerivatives(r11, r13)
            r12 = r6
        L_0x009d:
            int r14 = r10.length
            if (r12 >= r14) goto L_0x00b1
            org.apache.commons.math3.RealFieldElement r14 = r22.getStepSize()
            r15 = r11[r12]
            java.lang.Object r14 = r14.multiply(r15)
            org.apache.commons.math3.RealFieldElement r14 = (org.apache.commons.math3.RealFieldElement) r14
            r10[r12] = r14
            int r12 = r12 + 1
            goto L_0x009d
        L_0x00b1:
            org.apache.commons.math3.linear.Array2DRowFieldMatrix r11 = r0.nordsieck
            org.apache.commons.math3.linear.Array2DRowFieldMatrix r12 = r0.updateHighOrderDerivativesPhase1(r11)
            org.apache.commons.math3.RealFieldElement[] r11 = r0.scaled
            r0.updateHighOrderDerivativesPhase2(r11, r10, r12)
            org.apache.commons.math3.ode.nonstiff.AdamsMoultonFieldIntegrator$Corrector r11 = new org.apache.commons.math3.ode.nonstiff.AdamsMoultonFieldIntegrator$Corrector
            r15 = r19
            r11.<init>(r15, r10, r13)
            org.apache.commons.math3.FieldElement r11 = r12.walkInOptimizedOrder(r11)
            r14 = r11
            org.apache.commons.math3.RealFieldElement r14 = (org.apache.commons.math3.RealFieldElement) r14
            java.lang.Object r4 = r14.subtract(r4)
            org.apache.commons.math3.RealFieldElement r4 = (org.apache.commons.math3.RealFieldElement) r4
            double r4 = r4.getReal()
            int r4 = (r4 > r8 ? 1 : (r4 == r8 ? 0 : -1))
            if (r4 < 0) goto L_0x0110
            org.apache.commons.math3.RealFieldElement r4 = r0.computeStepGrowShrinkFactor(r14)
            org.apache.commons.math3.RealFieldElement r5 = r22.getStepSize()
            java.lang.Object r4 = r5.multiply(r4)
            org.apache.commons.math3.RealFieldElement r4 = (org.apache.commons.math3.RealFieldElement) r4
            org.apache.commons.math3.RealFieldElement r4 = r0.filterStep(r4, r2, r6)
            r0.rescale(r4)
            org.apache.commons.math3.ode.FieldODEStateAndDerivative r4 = r22.getStepStart()
            org.apache.commons.math3.ode.FieldODEStateAndDerivative r5 = r22.getStepStart()
            org.apache.commons.math3.RealFieldElement r5 = r5.getTime()
            org.apache.commons.math3.RealFieldElement r7 = r22.getStepSize()
            java.lang.Object r5 = r5.add(r7)
            org.apache.commons.math3.RealFieldElement r5 = (org.apache.commons.math3.RealFieldElement) r5
            org.apache.commons.math3.RealFieldElement r7 = r22.getStepSize()
            org.apache.commons.math3.RealFieldElement[] r11 = r0.scaled
            org.apache.commons.math3.linear.Array2DRowFieldMatrix r8 = r0.nordsieck
            org.apache.commons.math3.ode.FieldODEStateAndDerivative r4 = org.apache.commons.math3.ode.nonstiff.AdamsFieldStepInterpolator.taylor(r4, r5, r7, r11, r8)
            r7 = r4
        L_0x0110:
            r19 = r15
            r8 = 0
            goto L_0x0080
        L_0x0116:
            r15 = r19
            org.apache.commons.math3.RealFieldElement r4 = r7.getTime()
            org.apache.commons.math3.RealFieldElement[] r4 = r0.computeDerivatives(r4, r13)
            org.apache.commons.math3.Field r5 = r22.getField()
            int r8 = r15.length
            java.lang.Object[] r5 = org.apache.commons.math3.util.MathArrays.buildArray(r5, r8)
            org.apache.commons.math3.RealFieldElement[] r5 = (org.apache.commons.math3.RealFieldElement[]) r5
            r8 = r6
        L_0x012c:
            int r9 = r5.length
            if (r8 >= r9) goto L_0x0140
            org.apache.commons.math3.RealFieldElement r9 = r22.getStepSize()
            r11 = r4[r8]
            java.lang.Object r9 = r9.multiply(r11)
            org.apache.commons.math3.RealFieldElement r9 = (org.apache.commons.math3.RealFieldElement) r9
            r5[r8] = r9
            int r8 = r8 + 1
            goto L_0x012c
        L_0x0140:
            r0.updateHighOrderDerivativesPhase2(r10, r5, r12)
            org.apache.commons.math3.ode.FieldODEStateAndDerivative r8 = new org.apache.commons.math3.ode.FieldODEStateAndDerivative
            org.apache.commons.math3.RealFieldElement r7 = r7.getTime()
            r8.<init>(r7, r13, r4)
            org.apache.commons.math3.ode.nonstiff.AdamsFieldStepInterpolator r4 = new org.apache.commons.math3.ode.nonstiff.AdamsFieldStepInterpolator
            org.apache.commons.math3.RealFieldElement r11 = r22.getStepSize()
            org.apache.commons.math3.ode.FieldODEStateAndDerivative r7 = r22.getStepStart()
            org.apache.commons.math3.ode.FieldEquationsMapper r18 = r23.getMapper()
            r10 = r4
            r9 = r12
            r12 = r8
            r1 = r13
            r13 = r5
            r20 = r14
            r14 = r9
            r6 = r15
            r15 = r2
            r16 = r7
            r17 = r8
            r10.<init>(r11, r12, r13, r14, r15, r16, r17, r18)
            org.apache.commons.math3.ode.FieldODEStateAndDerivative r4 = r0.acceptStep(r4, r3)
            r0.setStepStart(r4)
            r0.scaled = r5
            r0.nordsieck = r9
            boolean r4 = r22.isLastStep()
            if (r4 != 0) goto L_0x0246
            int r4 = r6.length
            r5 = 0
            java.lang.System.arraycopy(r1, r5, r6, r5, r4)
            boolean r1 = r22.resetOccurred()
            if (r1 == 0) goto L_0x0191
            org.apache.commons.math3.ode.FieldODEStateAndDerivative r1 = r22.getStepStart()
            r4 = r23
            r0.start(r4, r1, r3)
            goto L_0x0193
        L_0x0191:
            r4 = r23
        L_0x0193:
            r14 = r20
            org.apache.commons.math3.RealFieldElement r1 = r0.computeStepGrowShrinkFactor(r14)
            org.apache.commons.math3.RealFieldElement r7 = r22.getStepSize()
            java.lang.Object r1 = r7.multiply(r1)
            org.apache.commons.math3.RealFieldElement r1 = (org.apache.commons.math3.RealFieldElement) r1
            org.apache.commons.math3.ode.FieldODEStateAndDerivative r7 = r22.getStepStart()
            org.apache.commons.math3.RealFieldElement r7 = r7.getTime()
            java.lang.Object r7 = r7.add(r1)
            org.apache.commons.math3.RealFieldElement r7 = (org.apache.commons.math3.RealFieldElement) r7
            if (r2 == 0) goto L_0x01c4
            java.lang.Object r7 = r7.subtract(r3)
            org.apache.commons.math3.RealFieldElement r7 = (org.apache.commons.math3.RealFieldElement) r7
            double r7 = r7.getReal()
            r9 = 0
            int r7 = (r7 > r9 ? 1 : (r7 == r9 ? 0 : -1))
            if (r7 < 0) goto L_0x01d6
            goto L_0x01d4
        L_0x01c4:
            r9 = 0
            java.lang.Object r7 = r7.subtract(r3)
            org.apache.commons.math3.RealFieldElement r7 = (org.apache.commons.math3.RealFieldElement) r7
            double r7 = r7.getReal()
            int r7 = (r7 > r9 ? 1 : (r7 == r9 ? 0 : -1))
            if (r7 > 0) goto L_0x01d6
        L_0x01d4:
            r7 = 1
            goto L_0x01d7
        L_0x01d6:
            r7 = r5
        L_0x01d7:
            org.apache.commons.math3.RealFieldElement r1 = r0.filterStep(r1, r2, r7)
            org.apache.commons.math3.ode.FieldODEStateAndDerivative r7 = r22.getStepStart()
            org.apache.commons.math3.RealFieldElement r7 = r7.getTime()
            java.lang.Object r7 = r7.add(r1)
            org.apache.commons.math3.RealFieldElement r7 = (org.apache.commons.math3.RealFieldElement) r7
            if (r2 == 0) goto L_0x01fc
            java.lang.Object r7 = r7.subtract(r3)
            org.apache.commons.math3.RealFieldElement r7 = (org.apache.commons.math3.RealFieldElement) r7
            double r7 = r7.getReal()
            r9 = 0
            int r7 = (r7 > r9 ? 1 : (r7 == r9 ? 0 : -1))
            if (r7 < 0) goto L_0x020e
            goto L_0x020c
        L_0x01fc:
            r9 = 0
            java.lang.Object r7 = r7.subtract(r3)
            org.apache.commons.math3.RealFieldElement r7 = (org.apache.commons.math3.RealFieldElement) r7
            double r7 = r7.getReal()
            int r7 = (r7 > r9 ? 1 : (r7 == r9 ? 0 : -1))
            if (r7 > 0) goto L_0x020e
        L_0x020c:
            r7 = 1
            goto L_0x020f
        L_0x020e:
            r7 = r5
        L_0x020f:
            if (r7 == 0) goto L_0x021f
            org.apache.commons.math3.ode.FieldODEStateAndDerivative r1 = r22.getStepStart()
            org.apache.commons.math3.RealFieldElement r1 = r1.getTime()
            java.lang.Object r1 = r3.subtract(r1)
            org.apache.commons.math3.RealFieldElement r1 = (org.apache.commons.math3.RealFieldElement) r1
        L_0x021f:
            r0.rescale(r1)
            org.apache.commons.math3.ode.FieldODEStateAndDerivative r1 = r22.getStepStart()
            org.apache.commons.math3.ode.FieldODEStateAndDerivative r7 = r22.getStepStart()
            org.apache.commons.math3.RealFieldElement r7 = r7.getTime()
            org.apache.commons.math3.RealFieldElement r8 = r22.getStepSize()
            java.lang.Object r7 = r7.add(r8)
            org.apache.commons.math3.RealFieldElement r7 = (org.apache.commons.math3.RealFieldElement) r7
            org.apache.commons.math3.RealFieldElement r8 = r22.getStepSize()
            org.apache.commons.math3.RealFieldElement[] r11 = r0.scaled
            org.apache.commons.math3.linear.Array2DRowFieldMatrix r12 = r0.nordsieck
            org.apache.commons.math3.ode.FieldODEStateAndDerivative r1 = org.apache.commons.math3.ode.nonstiff.AdamsFieldStepInterpolator.taylor(r1, r7, r8, r11, r12)
            r7 = r1
            goto L_0x024c
        L_0x0246:
            r4 = r23
            r5 = 0
            r9 = 0
            r7 = r8
        L_0x024c:
            boolean r1 = r22.isLastStep()
            if (r1 == 0) goto L_0x025e
            org.apache.commons.math3.ode.FieldODEStateAndDerivative r1 = r22.getStepStart()
            r2 = 0
            r0.setStepStart(r2)
            r0.setStepSize(r2)
            return r1
        L_0x025e:
            r1 = r4
            r8 = r9
            r21 = r6
            r6 = r5
            r5 = r21
            goto L_0x005e
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.math3.ode.nonstiff.AdamsMoultonFieldIntegrator.integrate(org.apache.commons.math3.ode.FieldExpandableODE, org.apache.commons.math3.ode.FieldODEState, org.apache.commons.math3.RealFieldElement):org.apache.commons.math3.ode.FieldODEStateAndDerivative");
    }

    private class Corrector implements FieldMatrixPreservingVisitor<T> {
        private final T[] after;
        private final T[] before;
        private final T[] previous;
        private final T[] scaled;

        Corrector(T[] tArr, T[] tArr2, T[] tArr3) {
            this.previous = tArr;
            this.scaled = tArr2;
            this.after = tArr3;
            this.before = (RealFieldElement[]) tArr3.clone();
        }

        public void start(int i, int i2, int i3, int i4, int i5, int i6) {
            Arrays.fill(this.after, AdamsMoultonFieldIntegrator.this.getField().getZero());
        }

        public void visit(int i, int i2, T t) {
            if ((i & 1) == 0) {
                T[] tArr = this.after;
                tArr[i2] = (RealFieldElement) tArr[i2].subtract(t);
                return;
            }
            T[] tArr2 = this.after;
            tArr2[i2] = (RealFieldElement) tArr2[i2].add(t);
        }

        public T end() {
            double d;
            RealFieldElement realFieldElement;
            RealFieldElement realFieldElement2 = (RealFieldElement) AdamsMoultonFieldIntegrator.this.getField().getZero();
            int i = 0;
            while (true) {
                T[] tArr = this.after;
                if (i >= tArr.length) {
                    return (RealFieldElement) ((RealFieldElement) realFieldElement2.divide((double) AdamsMoultonFieldIntegrator.this.mainSetDimension)).sqrt();
                }
                tArr[i] = (RealFieldElement) tArr[i].add(this.previous[i].add(this.scaled[i]));
                if (i < AdamsMoultonFieldIntegrator.this.mainSetDimension) {
                    RealFieldElement max = MathUtils.max((RealFieldElement) this.previous[i].abs(), (RealFieldElement) this.after[i].abs());
                    if (AdamsMoultonFieldIntegrator.this.vecAbsoluteTolerance == null) {
                        realFieldElement = (RealFieldElement) max.multiply(AdamsMoultonFieldIntegrator.this.scalRelativeTolerance);
                        d = AdamsMoultonFieldIntegrator.this.scalAbsoluteTolerance;
                    } else {
                        realFieldElement = (RealFieldElement) max.multiply(AdamsMoultonFieldIntegrator.this.vecRelativeTolerance[i]);
                        d = AdamsMoultonFieldIntegrator.this.vecAbsoluteTolerance[i];
                    }
                    RealFieldElement realFieldElement3 = (RealFieldElement) ((RealFieldElement) this.after[i].subtract(this.before[i])).divide((RealFieldElement) realFieldElement.add(d));
                    realFieldElement2 = (RealFieldElement) realFieldElement2.add(realFieldElement3.multiply(realFieldElement3));
                }
                i++;
            }
        }
    }
}
