package org.apache.commons.math3.ode.nonstiff;

import org.apache.commons.math3.Field;
import org.apache.commons.math3.RealFieldElement;
import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.linear.FieldMatrix;

public class AdamsBashforthFieldIntegrator<T extends RealFieldElement<T>> extends AdamsFieldIntegrator<T> {
    private static final String METHOD_NAME = "Adams-Bashforth";

    public AdamsBashforthFieldIntegrator(Field<T> field, int i, double d, double d2, double d3, double d4) throws NumberIsTooSmallException {
        super(field, METHOD_NAME, i, i, d, d2, d3, d4);
    }

    public AdamsBashforthFieldIntegrator(Field<T> field, int i, double d, double d2, double[] dArr, double[] dArr2) throws IllegalArgumentException {
        super(field, METHOD_NAME, i, i, d, d2, dArr, dArr2);
    }

    private T errorEstimation(T[] tArr, T[] tArr2, T[] tArr3, FieldMatrix<T> fieldMatrix) {
        double d;
        RealFieldElement realFieldElement;
        RealFieldElement realFieldElement2 = (RealFieldElement) getField().getZero();
        for (int i = 0; i < this.mainSetDimension; i++) {
            RealFieldElement realFieldElement3 = (RealFieldElement) tArr2[i].abs();
            if (this.vecAbsoluteTolerance == null) {
                realFieldElement = (RealFieldElement) realFieldElement3.multiply(this.scalRelativeTolerance);
                d = this.scalAbsoluteTolerance;
            } else {
                realFieldElement = (RealFieldElement) realFieldElement3.multiply(this.vecRelativeTolerance[i]);
                d = this.vecAbsoluteTolerance[i];
            }
            RealFieldElement realFieldElement4 = (RealFieldElement) realFieldElement.add(d);
            RealFieldElement realFieldElement5 = (RealFieldElement) getField().getZero();
            int i2 = fieldMatrix.getRowDimension() % 2 == 0 ? -1 : 1;
            for (int rowDimension = fieldMatrix.getRowDimension() - 1; rowDimension >= 0; rowDimension--) {
                realFieldElement5 = (RealFieldElement) realFieldElement5.add(((RealFieldElement) fieldMatrix.getEntry(rowDimension, i)).multiply(i2));
                i2 = -i2;
            }
            RealFieldElement realFieldElement6 = (RealFieldElement) ((RealFieldElement) ((RealFieldElement) tArr2[i].subtract(tArr[i])).add((RealFieldElement) realFieldElement5.subtract(tArr3[i]))).divide(realFieldElement4);
            realFieldElement2 = (RealFieldElement) realFieldElement2.add(realFieldElement6.multiply(realFieldElement6));
        }
        return (RealFieldElement) ((RealFieldElement) realFieldElement2.divide((double) this.mainSetDimension)).sqrt();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:33:0x01c0, code lost:
        if (((org.apache.commons.math3.RealFieldElement) r7.subtract(r3)).getReal() >= 0.0d) goto L_0x01d3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x01d1, code lost:
        if (((org.apache.commons.math3.RealFieldElement) r7.subtract(r3)).getReal() <= 0.0d) goto L_0x01d3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x01d5, code lost:
        r7 = false;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public org.apache.commons.math3.ode.FieldODEStateAndDerivative<T> integrate(org.apache.commons.math3.ode.FieldExpandableODE<T> r22, org.apache.commons.math3.ode.FieldODEState<T> r23, T r24) throws org.apache.commons.math3.exception.NumberIsTooSmallException, org.apache.commons.math3.exception.DimensionMismatchException, org.apache.commons.math3.exception.MaxCountExceededException, org.apache.commons.math3.exception.NoBracketingException {
        /*
            r21 = this;
            r0 = r21
            r1 = r22
            r2 = r23
            r3 = r24
            r0.sanityChecks(r2, r3)
            org.apache.commons.math3.RealFieldElement r4 = r23.getTime()
            org.apache.commons.math3.ode.FieldEquationsMapper r5 = r22.getMapper()
            org.apache.commons.math3.RealFieldElement[] r5 = r5.mapState(r2)
            org.apache.commons.math3.ode.FieldODEStateAndDerivative r4 = r0.initIntegration(r1, r4, r5, r3)
            r0.setStepStart(r4)
            org.apache.commons.math3.RealFieldElement r2 = r23.getTime()
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
            org.apache.commons.math3.ode.FieldODEStateAndDerivative r7 = r21.getStepStart()
            r0.start(r1, r7, r3)
            org.apache.commons.math3.ode.FieldODEStateAndDerivative r7 = r21.getStepStart()
            org.apache.commons.math3.RealFieldElement r10 = r7.getTime()
            org.apache.commons.math3.RealFieldElement r11 = r21.getStepSize()
            java.lang.Object r10 = r10.add(r11)
            org.apache.commons.math3.RealFieldElement r10 = (org.apache.commons.math3.RealFieldElement) r10
            org.apache.commons.math3.RealFieldElement r11 = r21.getStepSize()
            org.apache.commons.math3.RealFieldElement[] r12 = r0.scaled
            org.apache.commons.math3.linear.Array2DRowFieldMatrix r13 = r0.nordsieck
            org.apache.commons.math3.ode.FieldODEStateAndDerivative r7 = org.apache.commons.math3.ode.nonstiff.AdamsFieldStepInterpolator.taylor(r7, r10, r11, r12, r13)
            r0.setIsLastStep(r6)
        L_0x005e:
            org.apache.commons.math3.Field r10 = r21.getField()
            int r11 = r5.length
            java.lang.Object[] r10 = org.apache.commons.math3.util.MathArrays.buildArray(r10, r11)
            r15 = r10
            org.apache.commons.math3.RealFieldElement[] r15 = (org.apache.commons.math3.RealFieldElement[]) r15
            org.apache.commons.math3.Field r10 = r21.getField()
            java.lang.Object r10 = r10.getZero()
            org.apache.commons.math3.RealFieldElement r10 = (org.apache.commons.math3.RealFieldElement) r10
            r11 = 4621819117588971520(0x4024000000000000, double:10.0)
            java.lang.Object r10 = r10.add(r11)
            org.apache.commons.math3.RealFieldElement r10 = (org.apache.commons.math3.RealFieldElement) r10
            r19 = r5
            r13 = r10
            r11 = 0
            r12 = 0
        L_0x0081:
            r4 = 4607182418800017408(0x3ff0000000000000, double:1.0)
            java.lang.Object r10 = r13.subtract(r4)
            org.apache.commons.math3.RealFieldElement r10 = (org.apache.commons.math3.RealFieldElement) r10
            double r16 = r10.getReal()
            int r10 = (r16 > r8 ? 1 : (r16 == r8 ? 0 : -1))
            if (r10 < 0) goto L_0x010f
            org.apache.commons.math3.RealFieldElement[] r11 = r7.getState()
            org.apache.commons.math3.RealFieldElement r10 = r7.getTime()
            org.apache.commons.math3.RealFieldElement[] r10 = r0.computeDerivatives(r10, r11)
            r12 = r6
        L_0x009e:
            int r13 = r15.length
            if (r12 >= r13) goto L_0x00b2
            org.apache.commons.math3.RealFieldElement r13 = r21.getStepSize()
            r14 = r10[r12]
            java.lang.Object r13 = r13.multiply(r14)
            org.apache.commons.math3.RealFieldElement r13 = (org.apache.commons.math3.RealFieldElement) r13
            r15[r12] = r13
            int r12 = r12 + 1
            goto L_0x009e
        L_0x00b2:
            org.apache.commons.math3.linear.Array2DRowFieldMatrix r10 = r0.nordsieck
            org.apache.commons.math3.linear.Array2DRowFieldMatrix r12 = r0.updateHighOrderDerivativesPhase1(r10)
            org.apache.commons.math3.RealFieldElement[] r10 = r0.scaled
            r0.updateHighOrderDerivativesPhase2(r10, r15, r12)
            r14 = r19
            org.apache.commons.math3.RealFieldElement r13 = r0.errorEstimation(r14, r11, r15, r12)
            java.lang.Object r4 = r13.subtract(r4)
            org.apache.commons.math3.RealFieldElement r4 = (org.apache.commons.math3.RealFieldElement) r4
            double r4 = r4.getReal()
            int r4 = (r4 > r8 ? 1 : (r4 == r8 ? 0 : -1))
            if (r4 < 0) goto L_0x0109
            org.apache.commons.math3.RealFieldElement r4 = r0.computeStepGrowShrinkFactor(r13)
            org.apache.commons.math3.RealFieldElement r5 = r21.getStepSize()
            java.lang.Object r4 = r5.multiply(r4)
            org.apache.commons.math3.RealFieldElement r4 = (org.apache.commons.math3.RealFieldElement) r4
            org.apache.commons.math3.RealFieldElement r4 = r0.filterStep(r4, r2, r6)
            r0.rescale(r4)
            org.apache.commons.math3.ode.FieldODEStateAndDerivative r4 = r21.getStepStart()
            org.apache.commons.math3.ode.FieldODEStateAndDerivative r5 = r21.getStepStart()
            org.apache.commons.math3.RealFieldElement r5 = r5.getTime()
            org.apache.commons.math3.RealFieldElement r7 = r21.getStepSize()
            java.lang.Object r5 = r5.add(r7)
            org.apache.commons.math3.RealFieldElement r5 = (org.apache.commons.math3.RealFieldElement) r5
            org.apache.commons.math3.RealFieldElement r7 = r21.getStepSize()
            org.apache.commons.math3.RealFieldElement[] r10 = r0.scaled
            org.apache.commons.math3.linear.Array2DRowFieldMatrix r8 = r0.nordsieck
            org.apache.commons.math3.ode.FieldODEStateAndDerivative r4 = org.apache.commons.math3.ode.nonstiff.AdamsFieldStepInterpolator.taylor(r4, r5, r7, r10, r8)
            r7 = r4
        L_0x0109:
            r19 = r14
            r8 = 0
            goto L_0x0081
        L_0x010f:
            r14 = r19
            org.apache.commons.math3.ode.nonstiff.AdamsFieldStepInterpolator r4 = new org.apache.commons.math3.ode.nonstiff.AdamsFieldStepInterpolator
            org.apache.commons.math3.RealFieldElement r5 = r21.getStepSize()
            org.apache.commons.math3.ode.FieldODEStateAndDerivative r8 = r21.getStepStart()
            org.apache.commons.math3.ode.FieldEquationsMapper r18 = r22.getMapper()
            r10 = r4
            r9 = r11
            r11 = r5
            r5 = r12
            r12 = r7
            r20 = r13
            r13 = r15
            r6 = r14
            r14 = r5
            r1 = r15
            r15 = r2
            r16 = r8
            r17 = r7
            r10.<init>(r11, r12, r13, r14, r15, r16, r17, r18)
            org.apache.commons.math3.ode.FieldODEStateAndDerivative r4 = r0.acceptStep(r4, r3)
            r0.setStepStart(r4)
            r0.scaled = r1
            r0.nordsieck = r5
            boolean r1 = r21.isLastStep()
            if (r1 != 0) goto L_0x020d
            int r1 = r6.length
            r4 = 0
            java.lang.System.arraycopy(r9, r4, r6, r4, r1)
            boolean r1 = r21.resetOccurred()
            if (r1 == 0) goto L_0x0158
            org.apache.commons.math3.ode.FieldODEStateAndDerivative r1 = r21.getStepStart()
            r5 = r22
            r0.start(r5, r1, r3)
            goto L_0x015a
        L_0x0158:
            r5 = r22
        L_0x015a:
            r13 = r20
            org.apache.commons.math3.RealFieldElement r1 = r0.computeStepGrowShrinkFactor(r13)
            org.apache.commons.math3.RealFieldElement r7 = r21.getStepSize()
            java.lang.Object r1 = r7.multiply(r1)
            org.apache.commons.math3.RealFieldElement r1 = (org.apache.commons.math3.RealFieldElement) r1
            org.apache.commons.math3.ode.FieldODEStateAndDerivative r7 = r21.getStepStart()
            org.apache.commons.math3.RealFieldElement r7 = r7.getTime()
            java.lang.Object r7 = r7.add(r1)
            org.apache.commons.math3.RealFieldElement r7 = (org.apache.commons.math3.RealFieldElement) r7
            if (r2 == 0) goto L_0x018b
            java.lang.Object r7 = r7.subtract(r3)
            org.apache.commons.math3.RealFieldElement r7 = (org.apache.commons.math3.RealFieldElement) r7
            double r7 = r7.getReal()
            r9 = 0
            int r7 = (r7 > r9 ? 1 : (r7 == r9 ? 0 : -1))
            if (r7 < 0) goto L_0x019d
            goto L_0x019b
        L_0x018b:
            r9 = 0
            java.lang.Object r7 = r7.subtract(r3)
            org.apache.commons.math3.RealFieldElement r7 = (org.apache.commons.math3.RealFieldElement) r7
            double r7 = r7.getReal()
            int r7 = (r7 > r9 ? 1 : (r7 == r9 ? 0 : -1))
            if (r7 > 0) goto L_0x019d
        L_0x019b:
            r7 = 1
            goto L_0x019e
        L_0x019d:
            r7 = r4
        L_0x019e:
            org.apache.commons.math3.RealFieldElement r1 = r0.filterStep(r1, r2, r7)
            org.apache.commons.math3.ode.FieldODEStateAndDerivative r7 = r21.getStepStart()
            org.apache.commons.math3.RealFieldElement r7 = r7.getTime()
            java.lang.Object r7 = r7.add(r1)
            org.apache.commons.math3.RealFieldElement r7 = (org.apache.commons.math3.RealFieldElement) r7
            if (r2 == 0) goto L_0x01c3
            java.lang.Object r7 = r7.subtract(r3)
            org.apache.commons.math3.RealFieldElement r7 = (org.apache.commons.math3.RealFieldElement) r7
            double r7 = r7.getReal()
            r9 = 0
            int r7 = (r7 > r9 ? 1 : (r7 == r9 ? 0 : -1))
            if (r7 < 0) goto L_0x01d5
            goto L_0x01d3
        L_0x01c3:
            r9 = 0
            java.lang.Object r7 = r7.subtract(r3)
            org.apache.commons.math3.RealFieldElement r7 = (org.apache.commons.math3.RealFieldElement) r7
            double r7 = r7.getReal()
            int r7 = (r7 > r9 ? 1 : (r7 == r9 ? 0 : -1))
            if (r7 > 0) goto L_0x01d5
        L_0x01d3:
            r7 = 1
            goto L_0x01d6
        L_0x01d5:
            r7 = r4
        L_0x01d6:
            if (r7 == 0) goto L_0x01e6
            org.apache.commons.math3.ode.FieldODEStateAndDerivative r1 = r21.getStepStart()
            org.apache.commons.math3.RealFieldElement r1 = r1.getTime()
            java.lang.Object r1 = r3.subtract(r1)
            org.apache.commons.math3.RealFieldElement r1 = (org.apache.commons.math3.RealFieldElement) r1
        L_0x01e6:
            r0.rescale(r1)
            org.apache.commons.math3.ode.FieldODEStateAndDerivative r1 = r21.getStepStart()
            org.apache.commons.math3.ode.FieldODEStateAndDerivative r7 = r21.getStepStart()
            org.apache.commons.math3.RealFieldElement r7 = r7.getTime()
            org.apache.commons.math3.RealFieldElement r8 = r21.getStepSize()
            java.lang.Object r7 = r7.add(r8)
            org.apache.commons.math3.RealFieldElement r7 = (org.apache.commons.math3.RealFieldElement) r7
            org.apache.commons.math3.RealFieldElement r8 = r21.getStepSize()
            org.apache.commons.math3.RealFieldElement[] r11 = r0.scaled
            org.apache.commons.math3.linear.Array2DRowFieldMatrix r12 = r0.nordsieck
            org.apache.commons.math3.ode.FieldODEStateAndDerivative r1 = org.apache.commons.math3.ode.nonstiff.AdamsFieldStepInterpolator.taylor(r1, r7, r8, r11, r12)
            r7 = r1
            goto L_0x0212
        L_0x020d:
            r5 = r22
            r4 = 0
            r9 = 0
        L_0x0212:
            boolean r1 = r21.isLastStep()
            if (r1 == 0) goto L_0x0224
            org.apache.commons.math3.ode.FieldODEStateAndDerivative r1 = r21.getStepStart()
            r2 = 0
            r0.setStepStart(r2)
            r0.setStepSize(r2)
            return r1
        L_0x0224:
            r1 = r5
            r5 = r6
            r8 = r9
            r6 = r4
            goto L_0x005e
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.math3.ode.nonstiff.AdamsBashforthFieldIntegrator.integrate(org.apache.commons.math3.ode.FieldExpandableODE, org.apache.commons.math3.ode.FieldODEState, org.apache.commons.math3.RealFieldElement):org.apache.commons.math3.ode.FieldODEStateAndDerivative");
    }
}
