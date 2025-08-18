package org.apache.commons.math3.analysis.solvers;

import org.apache.commons.math3.Field;
import org.apache.commons.math3.RealFieldElement;
import org.apache.commons.math3.analysis.RealFieldUnivariateFunction;
import org.apache.commons.math3.exception.NoBracketingException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.util.IntegerSequence;

public class FieldBracketingNthOrderBrentSolver<T extends RealFieldElement<T>> implements BracketedRealFieldUnivariateSolver<T> {
    private static final int MAXIMAL_AGING = 2;
    private final T absoluteAccuracy;
    private IntegerSequence.Incrementor evaluations;
    private final Field<T> field;
    private final T functionValueAccuracy;
    private final int maximalOrder;
    private final T relativeAccuracy;

    public FieldBracketingNthOrderBrentSolver(T t, T t2, T t3, int i) throws NumberIsTooSmallException {
        if (i >= 2) {
            this.field = t.getField();
            this.maximalOrder = i;
            this.absoluteAccuracy = t2;
            this.relativeAccuracy = t;
            this.functionValueAccuracy = t3;
            this.evaluations = IntegerSequence.Incrementor.create();
            return;
        }
        throw new NumberIsTooSmallException(Integer.valueOf(i), 2, true);
    }

    public int getMaximalOrder() {
        return this.maximalOrder;
    }

    public int getMaxEvaluations() {
        return this.evaluations.getMaximalCount();
    }

    public int getEvaluations() {
        return this.evaluations.getCount();
    }

    public T getAbsoluteAccuracy() {
        return this.absoluteAccuracy;
    }

    public T getRelativeAccuracy() {
        return this.relativeAccuracy;
    }

    public T getFunctionValueAccuracy() {
        return this.functionValueAccuracy;
    }

    public T solve(int i, RealFieldUnivariateFunction<T> realFieldUnivariateFunction, T t, T t2, AllowedSolution allowedSolution) throws NullArgumentException, NoBracketingException {
        return solve(i, realFieldUnivariateFunction, t, t2, (RealFieldElement) ((RealFieldElement) t.add(t2)).divide(2.0d), allowedSolution);
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r20v0, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v1, resolved type: org.apache.commons.math3.RealFieldElement} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:54:0x021b A[LOOP:1: B:39:0x01ab->B:54:0x021b, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:55:0x0235 A[EDGE_INSN: B:109:0x0235->B:55:0x0235 ?: BREAK  
    EDGE_INSN: B:110:0x0235->B:55:0x0235 ?: BREAK  ] */
    /* JADX WARNING: Removed duplicated region for block: B:76:0x02b4  */
    /* JADX WARNING: Removed duplicated region for block: B:77:0x02c5  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public T solve(int r34, org.apache.commons.math3.analysis.RealFieldUnivariateFunction<T> r35, T r36, T r37, T r38, org.apache.commons.math3.analysis.solvers.AllowedSolution r39) throws org.apache.commons.math3.exception.NullArgumentException, org.apache.commons.math3.exception.NoBracketingException {
        /*
            r33 = this;
            r6 = r33
            r7 = r35
            org.apache.commons.math3.util.MathUtils.checkNotNull(r35)
            org.apache.commons.math3.util.IntegerSequence$Incrementor r0 = r6.evaluations
            r1 = r34
            org.apache.commons.math3.util.IntegerSequence$Incrementor r0 = r0.withMaximalCount(r1)
            r8 = 0
            org.apache.commons.math3.util.IntegerSequence$Incrementor r0 = r0.withStart(r8)
            r6.evaluations = r0
            org.apache.commons.math3.Field<T> r0 = r6.field
            java.lang.Object r0 = r0.getZero()
            r9 = r0
            org.apache.commons.math3.RealFieldElement r9 = (org.apache.commons.math3.RealFieldElement) r9
            r0 = 9221120237041090560(0x7ff8000000000000, double:NaN)
            java.lang.Object r0 = r9.add(r0)
            r10 = r0
            org.apache.commons.math3.RealFieldElement r10 = (org.apache.commons.math3.RealFieldElement) r10
            org.apache.commons.math3.Field<T> r0 = r6.field
            int r1 = r6.maximalOrder
            r11 = 1
            int r1 = r1 + r11
            java.lang.Object[] r0 = org.apache.commons.math3.util.MathArrays.buildArray(r0, r1)
            r12 = r0
            org.apache.commons.math3.RealFieldElement[] r12 = (org.apache.commons.math3.RealFieldElement[]) r12
            org.apache.commons.math3.Field<T> r0 = r6.field
            int r1 = r6.maximalOrder
            int r1 = r1 + r11
            java.lang.Object[] r0 = org.apache.commons.math3.util.MathArrays.buildArray(r0, r1)
            r13 = r0
            org.apache.commons.math3.RealFieldElement[] r13 = (org.apache.commons.math3.RealFieldElement[]) r13
            r12[r8] = r36
            r12[r11] = r38
            r14 = 2
            r12[r14] = r37
            org.apache.commons.math3.util.IntegerSequence$Incrementor r0 = r6.evaluations
            r0.increment()
            r0 = r12[r11]
            org.apache.commons.math3.RealFieldElement r0 = r7.value(r0)
            r13[r11] = r0
            double r0 = r0.getReal()
            r4 = 0
            boolean r0 = org.apache.commons.math3.util.Precision.equals((double) r0, (double) r4, (int) r11)
            if (r0 == 0) goto L_0x0064
            r0 = r12[r11]
            return r0
        L_0x0064:
            org.apache.commons.math3.util.IntegerSequence$Incrementor r0 = r6.evaluations
            r0.increment()
            r0 = r12[r8]
            org.apache.commons.math3.RealFieldElement r0 = r7.value(r0)
            r13[r8] = r0
            double r0 = r0.getReal()
            boolean r0 = org.apache.commons.math3.util.Precision.equals((double) r0, (double) r4, (int) r11)
            if (r0 == 0) goto L_0x007e
            r0 = r12[r8]
            return r0
        L_0x007e:
            r0 = r13[r8]
            r1 = r13[r11]
            java.lang.Object r0 = r0.multiply(r1)
            org.apache.commons.math3.RealFieldElement r0 = (org.apache.commons.math3.RealFieldElement) r0
            double r0 = r0.getReal()
            int r0 = (r0 > r4 ? 1 : (r0 == r4 ? 0 : -1))
            if (r0 >= 0) goto L_0x0093
            r0 = r11
            r1 = r14
            goto L_0x00c1
        L_0x0093:
            org.apache.commons.math3.util.IntegerSequence$Incrementor r0 = r6.evaluations
            r0.increment()
            r0 = r12[r14]
            org.apache.commons.math3.RealFieldElement r0 = r7.value(r0)
            r13[r14] = r0
            double r0 = r0.getReal()
            boolean r0 = org.apache.commons.math3.util.Precision.equals((double) r0, (double) r4, (int) r11)
            if (r0 == 0) goto L_0x00ad
            r0 = r12[r14]
            return r0
        L_0x00ad:
            r0 = r13[r11]
            r1 = r13[r14]
            java.lang.Object r0 = r0.multiply(r1)
            org.apache.commons.math3.RealFieldElement r0 = (org.apache.commons.math3.RealFieldElement) r0
            double r0 = r0.getReal()
            int r0 = (r0 > r4 ? 1 : (r0 == r4 ? 0 : -1))
            if (r0 >= 0) goto L_0x0346
            r0 = r14
            r1 = 3
        L_0x00c1:
            org.apache.commons.math3.Field<T> r2 = r6.field
            int r3 = r12.length
            java.lang.Object[] r2 = org.apache.commons.math3.util.MathArrays.buildArray(r2, r3)
            r3 = r2
            org.apache.commons.math3.RealFieldElement[] r3 = (org.apache.commons.math3.RealFieldElement[]) r3
            int r2 = r0 + -1
            r16 = r12[r2]
            r2 = r13[r2]
            java.lang.Object r17 = r16.abs()
            r15 = r17
            org.apache.commons.math3.RealFieldElement r15 = (org.apache.commons.math3.RealFieldElement) r15
            java.lang.Object r17 = r2.abs()
            org.apache.commons.math3.RealFieldElement r17 = (org.apache.commons.math3.RealFieldElement) r17
            r18 = r12[r0]
            r19 = r13[r0]
            java.lang.Object r20 = r18.abs()
            r8 = r20
            org.apache.commons.math3.RealFieldElement r8 = (org.apache.commons.math3.RealFieldElement) r8
            java.lang.Object r20 = r19.abs()
            org.apache.commons.math3.RealFieldElement r20 = (org.apache.commons.math3.RealFieldElement) r20
            r11 = r16
            r14 = r17
            r21 = r18
            r22 = r19
            r23 = r20
            r24 = 0
            r25 = 0
            r32 = r2
            r2 = r0
            r0 = r32
        L_0x0104:
            java.lang.Object r17 = r15.subtract(r8)
            org.apache.commons.math3.RealFieldElement r17 = (org.apache.commons.math3.RealFieldElement) r17
            double r17 = r17.getReal()
            int r17 = (r17 > r4 ? 1 : (r17 == r4 ? 0 : -1))
            if (r17 >= 0) goto L_0x0114
            r5 = r8
            goto L_0x0115
        L_0x0114:
            r5 = r15
        L_0x0115:
            r4 = r23
            java.lang.Object r17 = r14.subtract(r4)
            org.apache.commons.math3.RealFieldElement r17 = (org.apache.commons.math3.RealFieldElement) r17
            double r17 = r17.getReal()
            r19 = 0
            int r17 = (r17 > r19 ? 1 : (r17 == r19 ? 0 : -1))
            if (r17 >= 0) goto L_0x012d
            r17 = r1
            r18 = r2
            r1 = r4
            goto L_0x0132
        L_0x012d:
            r17 = r1
            r18 = r2
            r1 = r14
        L_0x0132:
            T r2 = r6.absoluteAccuracy
            r19 = r4
            T r4 = r6.relativeAccuracy
            java.lang.Object r4 = r4.multiply(r5)
            java.lang.Object r2 = r2.add(r4)
            org.apache.commons.math3.RealFieldElement r2 = (org.apache.commons.math3.RealFieldElement) r2
            r5 = r21
            java.lang.Object r4 = r5.subtract(r11)
            org.apache.commons.math3.RealFieldElement r4 = (org.apache.commons.math3.RealFieldElement) r4
            java.lang.Object r2 = r4.subtract(r2)
            org.apache.commons.math3.RealFieldElement r2 = (org.apache.commons.math3.RealFieldElement) r2
            double r26 = r2.getReal()
            r28 = 0
            int r2 = (r26 > r28 ? 1 : (r26 == r28 ? 0 : -1))
            if (r2 <= 0) goto L_0x02f4
            T r2 = r6.functionValueAccuracy
            java.lang.Object r1 = r1.subtract(r2)
            org.apache.commons.math3.RealFieldElement r1 = (org.apache.commons.math3.RealFieldElement) r1
            double r1 = r1.getReal()
            int r1 = (r1 > r28 ? 1 : (r1 == r28 ? 0 : -1))
            if (r1 >= 0) goto L_0x016c
            goto L_0x02f4
        L_0x016c:
            r1 = 4625196817309499392(0x4030000000000000, double:16.0)
            r20 = r5
            r4 = r24
            r5 = 2
            if (r4 < r5) goto L_0x018c
            r5 = r22
            java.lang.Object r1 = r5.divide(r1)
            org.apache.commons.math3.RealFieldElement r1 = (org.apache.commons.math3.RealFieldElement) r1
            java.lang.Object r1 = r1.negate()
            org.apache.commons.math3.RealFieldElement r1 = (org.apache.commons.math3.RealFieldElement) r1
            r22 = r1
            r21 = r4
            r37 = r8
            r8 = r25
            goto L_0x01a8
        L_0x018c:
            r21 = r4
            r4 = r5
            r37 = r8
            r5 = r22
            r8 = r25
            if (r8 < r4) goto L_0x01a6
            java.lang.Object r1 = r0.divide(r1)
            org.apache.commons.math3.RealFieldElement r1 = (org.apache.commons.math3.RealFieldElement) r1
            java.lang.Object r1 = r1.negate()
            org.apache.commons.math3.RealFieldElement r1 = (org.apache.commons.math3.RealFieldElement) r1
            r22 = r1
            goto L_0x01a8
        L_0x01a6:
            r22 = r9
        L_0x01a8:
            r23 = r17
            r4 = 0
        L_0x01ab:
            int r1 = r23 - r4
            java.lang.System.arraycopy(r12, r4, r3, r4, r1)
            r2 = r0
            r0 = r33
            r24 = r9
            r9 = r17
            r1 = r22
            r17 = r10
            r38 = r15
            r10 = r18
            r15 = r2
            r2 = r3
            r18 = r3
            r3 = r13
            r25 = r14
            r26 = r15
            r30 = r19
            r14 = r28
            r19 = r4
            r31 = r20
            r20 = r5
            r5 = r23
            org.apache.commons.math3.RealFieldElement r0 = r0.guessX(r1, r2, r3, r4, r5)
            java.lang.Object r1 = r0.subtract(r11)
            org.apache.commons.math3.RealFieldElement r1 = (org.apache.commons.math3.RealFieldElement) r1
            double r1 = r1.getReal()
            int r1 = (r1 > r14 ? 1 : (r1 == r14 ? 0 : -1))
            if (r1 <= 0) goto L_0x01fa
            r1 = r31
            java.lang.Object r2 = r0.subtract(r1)
            org.apache.commons.math3.RealFieldElement r2 = (org.apache.commons.math3.RealFieldElement) r2
            double r2 = r2.getReal()
            int r2 = (r2 > r14 ? 1 : (r2 == r14 ? 0 : -1))
            if (r2 < 0) goto L_0x01f7
            goto L_0x01fc
        L_0x01f7:
            r4 = r19
            goto L_0x020b
        L_0x01fa:
            r1 = r31
        L_0x01fc:
            int r2 = r10 - r19
            int r0 = r23 - r10
            if (r2 < r0) goto L_0x0205
            int r4 = r19 + 1
            goto L_0x0209
        L_0x0205:
            int r23 = r23 + -1
            r4 = r19
        L_0x0209:
            r0 = r17
        L_0x020b:
            double r2 = r0.getReal()
            boolean r2 = java.lang.Double.isNaN(r2)
            if (r2 == 0) goto L_0x0235
            int r2 = r23 - r4
            r3 = 1
            if (r2 > r3) goto L_0x021b
            goto L_0x0235
        L_0x021b:
            r28 = r14
            r3 = r18
            r5 = r20
            r14 = r25
            r0 = r26
            r19 = r30
            r15 = r38
            r20 = r1
            r18 = r10
            r10 = r17
            r17 = r9
            r9 = r24
            goto L_0x01ab
        L_0x0235:
            double r2 = r0.getReal()
            boolean r2 = java.lang.Double.isNaN(r2)
            if (r2 == 0) goto L_0x0255
            java.lang.Object r0 = r1.subtract(r11)
            org.apache.commons.math3.RealFieldElement r0 = (org.apache.commons.math3.RealFieldElement) r0
            r2 = 4611686018427387904(0x4000000000000000, double:2.0)
            java.lang.Object r0 = r0.divide(r2)
            java.lang.Object r0 = r11.add(r0)
            org.apache.commons.math3.RealFieldElement r0 = (org.apache.commons.math3.RealFieldElement) r0
            int r4 = r10 + -1
            r2 = r10
            goto L_0x0257
        L_0x0255:
            r2 = r23
        L_0x0257:
            org.apache.commons.math3.util.IntegerSequence$Incrementor r3 = r6.evaluations
            r3.increment()
            org.apache.commons.math3.RealFieldElement r3 = r7.value(r0)
            double r5 = r3.getReal()
            r7 = 1
            boolean r5 = org.apache.commons.math3.util.Precision.equals((double) r5, (double) r14, (int) r7)
            if (r5 == 0) goto L_0x026c
            return r0
        L_0x026c:
            r5 = 2
            if (r9 <= r5) goto L_0x027d
            int r2 = r2 - r4
            if (r2 == r9) goto L_0x027d
            r5 = 0
            java.lang.System.arraycopy(r12, r4, r12, r5, r2)
            java.lang.System.arraycopy(r13, r4, r13, r5, r2)
            int r4 = r10 - r4
        L_0x027b:
            r10 = r4
            goto L_0x0294
        L_0x027d:
            int r2 = r12.length
            if (r9 != r2) goto L_0x0293
            int r2 = r9 + -1
            int r4 = r12.length
            r5 = 1
            int r4 = r4 + r5
            r6 = 2
            int r4 = r4 / r6
            if (r10 < r4) goto L_0x0294
            r4 = 0
            java.lang.System.arraycopy(r12, r5, r12, r4, r2)
            java.lang.System.arraycopy(r13, r5, r13, r4, r2)
            int r4 = r10 + -1
            goto L_0x027b
        L_0x0293:
            r2 = r9
        L_0x0294:
            int r4 = r10 + 1
            int r5 = r2 - r10
            java.lang.System.arraycopy(r12, r10, r12, r4, r5)
            r12[r10] = r0
            java.lang.System.arraycopy(r13, r10, r13, r4, r5)
            r13[r10] = r3
            r5 = 1
            int r2 = r2 + r5
            r5 = r26
            java.lang.Object r6 = r3.multiply(r5)
            org.apache.commons.math3.RealFieldElement r6 = (org.apache.commons.math3.RealFieldElement) r6
            double r6 = r6.getReal()
            int r6 = (r6 > r14 ? 1 : (r6 == r14 ? 0 : -1))
            if (r6 > 0) goto L_0x02c5
            java.lang.Object r1 = r3.abs()
            org.apache.commons.math3.RealFieldElement r1 = (org.apache.commons.math3.RealFieldElement) r1
            int r4 = r21 + 1
            r21 = r0
            r23 = r1
            r22 = r3
            r0 = r5
            r1 = 0
            goto L_0x02db
        L_0x02c5:
            java.lang.Object r5 = r3.abs()
            org.apache.commons.math3.RealFieldElement r5 = (org.apache.commons.math3.RealFieldElement) r5
            int r25 = r8 + 1
            r11 = r0
            r21 = r1
            r0 = r3
            r10 = r4
            r22 = r20
            r1 = r25
            r23 = r30
            r4 = 0
            r25 = r5
        L_0x02db:
            r6 = r33
            r7 = r35
            r8 = r37
            r3 = r18
            r9 = r24
            r24 = r4
            r4 = r14
            r14 = r25
            r15 = r38
            r25 = r1
            r1 = r2
            r2 = r10
            r10 = r17
            goto L_0x0104
        L_0x02f4:
            r1 = r5
            r25 = r14
            r30 = r19
            r14 = r28
            r5 = r0
            int[] r0 = org.apache.commons.math3.analysis.solvers.FieldBracketingNthOrderBrentSolver.AnonymousClass1.$SwitchMap$org$apache$commons$math3$analysis$solvers$AllowedSolution
            int r2 = r39.ordinal()
            r0 = r0[r2]
            r2 = 1
            if (r0 == r2) goto L_0x0331
            r2 = 2
            if (r0 == r2) goto L_0x0330
            r2 = 3
            if (r0 == r2) goto L_0x032f
            r2 = 4
            if (r0 == r2) goto L_0x0324
            r2 = 5
            if (r0 != r2) goto L_0x031d
            double r2 = r5.getReal()
            int r0 = (r2 > r14 ? 1 : (r2 == r14 ? 0 : -1))
            if (r0 >= 0) goto L_0x031c
            r11 = r1
        L_0x031c:
            return r11
        L_0x031d:
            org.apache.commons.math3.exception.MathInternalError r0 = new org.apache.commons.math3.exception.MathInternalError
            r1 = 0
            r0.<init>(r1)
            throw r0
        L_0x0324:
            double r2 = r5.getReal()
            int r0 = (r2 > r14 ? 1 : (r2 == r14 ? 0 : -1))
            if (r0 > 0) goto L_0x032d
            goto L_0x032e
        L_0x032d:
            r11 = r1
        L_0x032e:
            return r11
        L_0x032f:
            return r1
        L_0x0330:
            return r11
        L_0x0331:
            r0 = r25
            r2 = r30
            java.lang.Object r0 = r0.subtract(r2)
            org.apache.commons.math3.RealFieldElement r0 = (org.apache.commons.math3.RealFieldElement) r0
            double r2 = r0.getReal()
            int r0 = (r2 > r14 ? 1 : (r2 == r14 ? 0 : -1))
            if (r0 >= 0) goto L_0x0344
            goto L_0x0345
        L_0x0344:
            r11 = r1
        L_0x0345:
            return r11
        L_0x0346:
            org.apache.commons.math3.exception.NoBracketingException r9 = new org.apache.commons.math3.exception.NoBracketingException
            r0 = 0
            r1 = r12[r0]
            double r1 = r1.getReal()
            r3 = 2
            r4 = r12[r3]
            double r4 = r4.getReal()
            r0 = r13[r0]
            double r6 = r0.getReal()
            r0 = r13[r3]
            double r10 = r0.getReal()
            r0 = r9
            r3 = r4
            r5 = r6
            r7 = r10
            r0.<init>(r1, r3, r5, r7)
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.math3.analysis.solvers.FieldBracketingNthOrderBrentSolver.solve(int, org.apache.commons.math3.analysis.RealFieldUnivariateFunction, org.apache.commons.math3.RealFieldElement, org.apache.commons.math3.RealFieldElement, org.apache.commons.math3.RealFieldElement, org.apache.commons.math3.analysis.solvers.AllowedSolution):org.apache.commons.math3.RealFieldElement");
    }

    /* renamed from: org.apache.commons.math3.analysis.solvers.FieldBracketingNthOrderBrentSolver$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$org$apache$commons$math3$analysis$solvers$AllowedSolution;

        /* JADX WARNING: Can't wrap try/catch for region: R(12:0|1|2|3|4|5|6|7|8|9|10|12) */
        /* JADX WARNING: Code restructure failed: missing block: B:13:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0033 */
        static {
            /*
                org.apache.commons.math3.analysis.solvers.AllowedSolution[] r0 = org.apache.commons.math3.analysis.solvers.AllowedSolution.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$org$apache$commons$math3$analysis$solvers$AllowedSolution = r0
                org.apache.commons.math3.analysis.solvers.AllowedSolution r1 = org.apache.commons.math3.analysis.solvers.AllowedSolution.ANY_SIDE     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$org$apache$commons$math3$analysis$solvers$AllowedSolution     // Catch:{ NoSuchFieldError -> 0x001d }
                org.apache.commons.math3.analysis.solvers.AllowedSolution r1 = org.apache.commons.math3.analysis.solvers.AllowedSolution.LEFT_SIDE     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$org$apache$commons$math3$analysis$solvers$AllowedSolution     // Catch:{ NoSuchFieldError -> 0x0028 }
                org.apache.commons.math3.analysis.solvers.AllowedSolution r1 = org.apache.commons.math3.analysis.solvers.AllowedSolution.RIGHT_SIDE     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = $SwitchMap$org$apache$commons$math3$analysis$solvers$AllowedSolution     // Catch:{ NoSuchFieldError -> 0x0033 }
                org.apache.commons.math3.analysis.solvers.AllowedSolution r1 = org.apache.commons.math3.analysis.solvers.AllowedSolution.BELOW_SIDE     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                int[] r0 = $SwitchMap$org$apache$commons$math3$analysis$solvers$AllowedSolution     // Catch:{ NoSuchFieldError -> 0x003e }
                org.apache.commons.math3.analysis.solvers.AllowedSolution r1 = org.apache.commons.math3.analysis.solvers.AllowedSolution.ABOVE_SIDE     // Catch:{ NoSuchFieldError -> 0x003e }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x003e }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x003e }
            L_0x003e:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.math3.analysis.solvers.FieldBracketingNthOrderBrentSolver.AnonymousClass1.<clinit>():void");
        }
    }

    private T guessX(T t, T[] tArr, T[] tArr2, int i, int i2) {
        int i3;
        int i4 = i;
        while (true) {
            i3 = i2 - 1;
            if (i4 >= i3) {
                break;
            }
            int i5 = i4 + 1;
            int i6 = i5 - i;
            while (i3 > i4) {
                tArr[i3] = (RealFieldElement) ((RealFieldElement) tArr[i3].subtract(tArr[i3 - 1])).divide(tArr2[i3].subtract(tArr2[i3 - i6]));
                i3--;
            }
            i4 = i5;
        }
        T t2 = (RealFieldElement) this.field.getZero();
        while (i3 >= i) {
            t2 = (RealFieldElement) tArr[i3].add(t2.multiply(t.subtract(tArr2[i3])));
            i3--;
        }
        return t2;
    }
}
