package org.apache.commons.math3.analysis.solvers;

import org.apache.commons.math3.analysis.UnivariateFunction;
import org.apache.commons.math3.exception.NoBracketingException;
import org.apache.commons.math3.exception.NumberIsTooLargeException;
import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.exception.TooManyEvaluationsException;

public class BracketingNthOrderBrentSolver extends AbstractUnivariateSolver implements BracketedUnivariateSolver<UnivariateFunction> {
    private static final double DEFAULT_ABSOLUTE_ACCURACY = 1.0E-6d;
    private static final int DEFAULT_MAXIMAL_ORDER = 5;
    private static final int MAXIMAL_AGING = 2;
    private static final double REDUCTION_FACTOR = 0.0625d;
    private AllowedSolution allowed;
    private final int maximalOrder;

    public BracketingNthOrderBrentSolver() {
        this(1.0E-6d, 5);
    }

    public BracketingNthOrderBrentSolver(double d, int i) throws NumberIsTooSmallException {
        super(d);
        if (i >= 2) {
            this.maximalOrder = i;
            this.allowed = AllowedSolution.ANY_SIDE;
            return;
        }
        throw new NumberIsTooSmallException(Integer.valueOf(i), 2, true);
    }

    public BracketingNthOrderBrentSolver(double d, double d2, int i) throws NumberIsTooSmallException {
        super(d, d2);
        if (i >= 2) {
            this.maximalOrder = i;
            this.allowed = AllowedSolution.ANY_SIDE;
            return;
        }
        throw new NumberIsTooSmallException(Integer.valueOf(i), 2, true);
    }

    public BracketingNthOrderBrentSolver(double d, double d2, double d3, int i) throws NumberIsTooSmallException {
        super(d, d2, d3);
        if (i >= 2) {
            this.maximalOrder = i;
            this.allowed = AllowedSolution.ANY_SIDE;
            return;
        }
        throw new NumberIsTooSmallException(Integer.valueOf(i), 2, true);
    }

    public int getMaximalOrder() {
        return this.maximalOrder;
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x012c  */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x012f  */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x013f A[LOOP:1: B:31:0x0102->B:44:0x013f, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x0148 A[EDGE_INSN: B:99:0x0148->B:45:0x0148 ?: BREAK  
    EDGE_INSN: B:100:0x0148->B:45:0x0148 ?: BREAK  ] */
    /* JADX WARNING: Removed duplicated region for block: B:47:0x014e  */
    /* JADX WARNING: Removed duplicated region for block: B:48:0x0159  */
    /* JADX WARNING: Removed duplicated region for block: B:52:0x016a  */
    /* JADX WARNING: Removed duplicated region for block: B:96:0x0169 A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public double doSolve() throws org.apache.commons.math3.exception.TooManyEvaluationsException, org.apache.commons.math3.exception.NumberIsTooLargeException, org.apache.commons.math3.exception.NoBracketingException {
        /*
            r38 = this;
            r7 = r38
            int r0 = r7.maximalOrder
            int r8 = r0 + 1
            double[] r9 = new double[r8]
            r10 = 1
            int r0 = r0 + r10
            double[] r11 = new double[r0]
            double r0 = r38.getMin()
            r12 = 0
            r9[r12] = r0
            double r0 = r38.getStartValue()
            r9[r10] = r0
            double r5 = r38.getMax()
            r13 = 2
            r9[r13] = r5
            r1 = r9[r12]
            r3 = r9[r10]
            r0 = r38
            r0.verifySequence(r1, r3, r5)
            r0 = r9[r10]
            double r0 = r7.computeObjectiveValue(r0)
            r11[r10] = r0
            r14 = 0
            boolean r0 = org.apache.commons.math3.util.Precision.equals((double) r0, (double) r14, (int) r10)
            if (r0 == 0) goto L_0x003c
            r0 = r9[r10]
            return r0
        L_0x003c:
            r0 = r9[r12]
            double r0 = r7.computeObjectiveValue(r0)
            r11[r12] = r0
            boolean r0 = org.apache.commons.math3.util.Precision.equals((double) r0, (double) r14, (int) r10)
            if (r0 == 0) goto L_0x004d
            r0 = r9[r12]
            return r0
        L_0x004d:
            r0 = r11[r12]
            r2 = r11[r10]
            double r0 = r0 * r2
            int r0 = (r0 > r14 ? 1 : (r0 == r14 ? 0 : -1))
            if (r0 >= 0) goto L_0x0059
            r0 = r10
            r1 = r13
            goto L_0x0075
        L_0x0059:
            r0 = r9[r13]
            double r0 = r7.computeObjectiveValue(r0)
            r11[r13] = r0
            boolean r0 = org.apache.commons.math3.util.Precision.equals((double) r0, (double) r14, (int) r10)
            if (r0 == 0) goto L_0x006a
            r0 = r9[r13]
            return r0
        L_0x006a:
            r0 = r11[r10]
            r2 = r11[r13]
            double r0 = r0 * r2
            int r0 = (r0 > r14 ? 1 : (r0 == r14 ? 0 : -1))
            if (r0 >= 0) goto L_0x021a
            r0 = r13
            r1 = 3
        L_0x0075:
            double[] r5 = new double[r8]
            int r2 = r0 + -1
            r3 = r9[r2]
            r16 = r11[r2]
            double r18 = org.apache.commons.math3.util.FastMath.abs((double) r16)
            r20 = r9[r0]
            r22 = r11[r0]
            double r24 = org.apache.commons.math3.util.FastMath.abs((double) r22)
            r14 = r24
            r36 = r3
            r4 = r0
            r3 = r1
            r0 = r12
            r1 = r18
            r18 = r16
            r16 = r36
        L_0x0096:
            double r26 = r38.getAbsoluteAccuracy()
            double r28 = r38.getRelativeAccuracy()
            double r6 = org.apache.commons.math3.util.FastMath.abs((double) r16)
            r30 = r11
            double r10 = org.apache.commons.math3.util.FastMath.abs((double) r20)
            double r6 = org.apache.commons.math3.util.FastMath.max((double) r6, (double) r10)
            double r28 = r28 * r6
            double r26 = r26 + r28
            double r10 = r20 - r16
            int r6 = (r10 > r26 ? 1 : (r10 == r26 ? 0 : -1))
            if (r6 <= 0) goto L_0x01d8
            double r6 = org.apache.commons.math3.util.FastMath.max((double) r1, (double) r14)
            double r26 = r38.getFunctionValueAccuracy()
            int r6 = (r6 > r26 ? 1 : (r6 == r26 ? 0 : -1))
            if (r6 >= 0) goto L_0x00c4
            goto L_0x01d8
        L_0x00c4:
            if (r0 < r13) goto L_0x00e5
            int r26 = r0 + -2
            r27 = 1
            int r28 = r27 << r26
            int r13 = r28 + -1
            double r6 = (double) r13
            int r13 = r26 + 1
            r26 = r1
            r2 = r0
            double r0 = (double) r13
            double r33 = r6 * r18
            r31 = 4589168020290535424(0x3fb0000000000000, double:0.0625)
            double r31 = r31 * r0
            double r31 = r31 * r22
        L_0x00dd:
            double r33 = r33 - r31
            double r6 = r6 + r0
            double r0 = r33 / r6
            r31 = r0
            goto L_0x0100
        L_0x00e5:
            r26 = r1
            r2 = r0
            r0 = r13
            if (r12 < r0) goto L_0x00fe
            int r0 = r12 + -2
            int r1 = r0 + 1
            double r6 = (double) r1
            r1 = 1
            int r0 = r1 << r0
            int r0 = r0 - r1
            double r0 = (double) r0
            double r33 = r0 * r22
            r31 = 4589168020290535424(0x3fb0000000000000, double:0.0625)
            double r31 = r31 * r6
            double r31 = r31 * r18
            goto L_0x00dd
        L_0x00fe:
            r31 = 0
        L_0x0100:
            r13 = r3
            r7 = 0
        L_0x0102:
            int r0 = r13 - r7
            java.lang.System.arraycopy(r9, r7, r5, r7, r0)
            r28 = r2
            r0 = r38
            r1 = r31
            r6 = r3
            r3 = r5
            r33 = r14
            r14 = r4
            r4 = r30
            r15 = r5
            r5 = r7
            r35 = r15
            r15 = r6
            r6 = r13
            double r0 = r0.guessX(r1, r3, r4, r5, r6)
            int r2 = (r0 > r16 ? 1 : (r0 == r16 ? 0 : -1))
            if (r2 <= 0) goto L_0x0126
            int r2 = (r0 > r20 ? 1 : (r0 == r20 ? 0 : -1))
            if (r2 < 0) goto L_0x0133
        L_0x0126:
            int r4 = r14 - r7
            int r0 = r13 - r14
            if (r4 < r0) goto L_0x012f
            int r7 = r7 + 1
            goto L_0x0131
        L_0x012f:
            int r13 = r13 + -1
        L_0x0131:
            r0 = 9221120237041090560(0x7ff8000000000000, double:NaN)
        L_0x0133:
            boolean r2 = java.lang.Double.isNaN(r0)
            if (r2 == 0) goto L_0x0148
            int r2 = r13 - r7
            r3 = 1
            if (r2 > r3) goto L_0x013f
            goto L_0x0148
        L_0x013f:
            r4 = r14
            r3 = r15
            r2 = r28
            r14 = r33
            r5 = r35
            goto L_0x0102
        L_0x0148:
            boolean r2 = java.lang.Double.isNaN(r0)
            if (r2 == 0) goto L_0x0159
            r0 = 4602678819172646912(0x3fe0000000000000, double:0.5)
            double r10 = r10 * r0
            double r0 = r16 + r10
            int r7 = r14 + -1
            r2 = r38
            r4 = r14
            goto L_0x015c
        L_0x0159:
            r2 = r38
            r4 = r13
        L_0x015c:
            double r5 = r2.computeObjectiveValue(r0)
            r3 = 1
            r10 = 0
            boolean r13 = org.apache.commons.math3.util.Precision.equals((double) r5, (double) r10, (int) r3)
            if (r13 == 0) goto L_0x016a
            return r0
        L_0x016a:
            r3 = 2
            if (r15 <= r3) goto L_0x017d
            int r3 = r4 - r7
            if (r3 == r15) goto L_0x017d
            r4 = 0
            java.lang.System.arraycopy(r9, r7, r9, r4, r3)
            r10 = r30
            java.lang.System.arraycopy(r10, r7, r10, r4, r3)
            int r4 = r14 - r7
            goto L_0x0198
        L_0x017d:
            r10 = r30
            if (r15 != r8) goto L_0x0196
            int r3 = r15 + -1
            int r4 = r8 + 1
            r7 = 2
            int r4 = r4 / r7
            if (r14 < r4) goto L_0x0194
            r4 = 1
            r7 = 0
            java.lang.System.arraycopy(r9, r4, r9, r7, r3)
            java.lang.System.arraycopy(r10, r4, r10, r7, r3)
            int r4 = r14 + -1
            goto L_0x0198
        L_0x0194:
            r4 = r14
            goto L_0x0198
        L_0x0196:
            r4 = r14
            r3 = r15
        L_0x0198:
            int r7 = r4 + 1
            int r11 = r3 - r4
            java.lang.System.arraycopy(r9, r4, r9, r7, r11)
            r9[r4] = r0
            java.lang.System.arraycopy(r10, r4, r10, r7, r11)
            r10[r4] = r5
            r11 = 1
            int r3 = r3 + r11
            double r13 = r5 * r18
            r24 = 0
            int r11 = (r13 > r24 ? 1 : (r13 == r24 ? 0 : -1))
            if (r11 > 0) goto L_0x01be
            double r11 = org.apache.commons.math3.util.FastMath.abs((double) r5)
            int r7 = r28 + 1
            r20 = r0
            r22 = r5
            r0 = r7
            r14 = r11
            r12 = 0
            goto L_0x01ce
        L_0x01be:
            double r13 = org.apache.commons.math3.util.FastMath.abs((double) r5)
            int r12 = r12 + 1
            r16 = r0
            r18 = r5
            r4 = r7
            r26 = r13
            r14 = r33
            r0 = 0
        L_0x01ce:
            r7 = r2
            r11 = r10
            r1 = r26
            r5 = r35
            r10 = 1
            r13 = 2
            goto L_0x0096
        L_0x01d8:
            r26 = r1
            r33 = r14
            r2 = r38
            int[] r0 = org.apache.commons.math3.analysis.solvers.BracketingNthOrderBrentSolver.AnonymousClass1.$SwitchMap$org$apache$commons$math3$analysis$solvers$AllowedSolution
            org.apache.commons.math3.analysis.solvers.AllowedSolution r1 = r2.allowed
            int r1 = r1.ordinal()
            r0 = r0[r1]
            r1 = 1
            if (r0 == r1) goto L_0x0212
            r1 = 2
            if (r0 == r1) goto L_0x0211
            r1 = 3
            if (r0 == r1) goto L_0x0210
            r1 = 4
            if (r0 == r1) goto L_0x0206
            r1 = 5
            if (r0 != r1) goto L_0x0200
            r0 = 0
            int r0 = (r18 > r0 ? 1 : (r18 == r0 ? 0 : -1))
            if (r0 >= 0) goto L_0x01ff
            r16 = r20
        L_0x01ff:
            return r16
        L_0x0200:
            org.apache.commons.math3.exception.MathInternalError r0 = new org.apache.commons.math3.exception.MathInternalError
            r0.<init>()
            throw r0
        L_0x0206:
            r0 = 0
            int r0 = (r18 > r0 ? 1 : (r18 == r0 ? 0 : -1))
            if (r0 > 0) goto L_0x020d
            goto L_0x020f
        L_0x020d:
            r16 = r20
        L_0x020f:
            return r16
        L_0x0210:
            return r20
        L_0x0211:
            return r16
        L_0x0212:
            int r0 = (r26 > r33 ? 1 : (r26 == r33 ? 0 : -1))
            if (r0 >= 0) goto L_0x0217
            goto L_0x0219
        L_0x0217:
            r16 = r20
        L_0x0219:
            return r16
        L_0x021a:
            r10 = r11
            org.apache.commons.math3.exception.NoBracketingException r11 = new org.apache.commons.math3.exception.NoBracketingException
            r0 = 0
            r1 = r9[r0]
            r3 = 2
            r4 = r9[r3]
            r6 = r10[r0]
            r8 = r10[r3]
            r0 = r11
            r3 = r4
            r5 = r6
            r7 = r8
            r0.<init>(r1, r3, r5, r7)
            throw r11
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.math3.analysis.solvers.BracketingNthOrderBrentSolver.doSolve():double");
    }

    /* renamed from: org.apache.commons.math3.analysis.solvers.BracketingNthOrderBrentSolver$1  reason: invalid class name */
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
            throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.math3.analysis.solvers.BracketingNthOrderBrentSolver.AnonymousClass1.<clinit>():void");
        }
    }

    private double guessX(double d, double[] dArr, double[] dArr2, int i, int i2) {
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
                dArr[i3] = (dArr[i3] - dArr[i3 - 1]) / (dArr2[i3] - dArr2[i3 - i6]);
                i3--;
            }
            i4 = i5;
        }
        double d2 = 0.0d;
        while (i3 >= i) {
            d2 = (d2 * (d - dArr2[i3])) + dArr[i3];
            i3--;
        }
        return d2;
    }

    public double solve(int i, UnivariateFunction univariateFunction, double d, double d2, AllowedSolution allowedSolution) throws TooManyEvaluationsException, NumberIsTooLargeException, NoBracketingException {
        this.allowed = allowedSolution;
        return super.solve(i, univariateFunction, d, d2);
    }

    public double solve(int i, UnivariateFunction univariateFunction, double d, double d2, double d3, AllowedSolution allowedSolution) throws TooManyEvaluationsException, NumberIsTooLargeException, NoBracketingException {
        this.allowed = allowedSolution;
        return super.solve(i, univariateFunction, d, d2, d3);
    }
}
