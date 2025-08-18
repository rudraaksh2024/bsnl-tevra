package org.apache.commons.math3.optim.univariate;

import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.optim.ConvergenceChecker;
import org.apache.commons.math3.util.FastMath;

public class BrentOptimizer extends UnivariateOptimizer {
    private static final double GOLDEN_SECTION = ((3.0d - FastMath.sqrt(5.0d)) * 0.5d);
    private static final double MIN_RELATIVE_TOLERANCE = (FastMath.ulp(1.0d) * 2.0d);
    private final double absoluteThreshold;
    private final double relativeThreshold;

    public BrentOptimizer(double d, double d2, ConvergenceChecker<UnivariatePointValuePair> convergenceChecker) {
        super(convergenceChecker);
        double d3 = MIN_RELATIVE_TOLERANCE;
        if (d < d3) {
            throw new NumberIsTooSmallException(Double.valueOf(d), Double.valueOf(d3), true);
        } else if (d2 > 0.0d) {
            this.relativeThreshold = d;
            this.absoluteThreshold = d2;
        } else {
            throw new NotStrictlyPositiveException(Double.valueOf(d2));
        }
    }

    public BrentOptimizer(double d, double d2) {
        this(d, d2, (ConvergenceChecker<UnivariatePointValuePair>) null);
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:53:0x010f  */
    /* JADX WARNING: Removed duplicated region for block: B:57:0x011a  */
    /* JADX WARNING: Removed duplicated region for block: B:60:0x0124  */
    /* JADX WARNING: Removed duplicated region for block: B:63:0x0129  */
    /* JADX WARNING: Removed duplicated region for block: B:64:0x012b  */
    /* JADX WARNING: Removed duplicated region for block: B:67:0x013b  */
    /* JADX WARNING: Removed duplicated region for block: B:70:0x014a  */
    /* JADX WARNING: Removed duplicated region for block: B:73:0x0152  */
    /* JADX WARNING: Removed duplicated region for block: B:78:0x0166  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public org.apache.commons.math3.optim.univariate.UnivariatePointValuePair doOptimize() {
        /*
            r50 = this;
            r0 = r50
            org.apache.commons.math3.optim.nonlinear.scalar.GoalType r1 = r50.getGoalType()
            org.apache.commons.math3.optim.nonlinear.scalar.GoalType r2 = org.apache.commons.math3.optim.nonlinear.scalar.GoalType.MINIMIZE
            if (r1 != r2) goto L_0x000c
            r1 = 1
            goto L_0x000d
        L_0x000c:
            r1 = 0
        L_0x000d:
            double r5 = r50.getMin()
            double r7 = r50.getStartValue()
            double r9 = r50.getMax()
            org.apache.commons.math3.optim.ConvergenceChecker r2 = r50.getConvergenceChecker()
            int r11 = (r5 > r9 ? 1 : (r5 == r9 ? 0 : -1))
            if (r11 >= 0) goto L_0x0022
            goto L_0x0027
        L_0x0022:
            r48 = r5
            r5 = r9
            r9 = r48
        L_0x0027:
            double r11 = r0.computeObjectiveValue(r7)
            if (r1 != 0) goto L_0x002e
            double r11 = -r11
        L_0x002e:
            org.apache.commons.math3.optim.univariate.UnivariatePointValuePair r13 = new org.apache.commons.math3.optim.univariate.UnivariatePointValuePair
            if (r1 == 0) goto L_0x0034
            r14 = r11
            goto L_0x0035
        L_0x0034:
            double r14 = -r11
        L_0x0035:
            r13.<init>(r7, r14)
            r16 = 0
            r18 = r11
            r20 = r18
            r22 = r20
            r3 = r13
            r4 = r16
            r24 = 0
            r26 = 0
            r11 = r7
            r16 = r9
            r9 = r11
        L_0x004b:
            double r28 = r5 + r16
            r30 = 4602678819172646912(0x3fe0000000000000, double:0.5)
            double r28 = r28 * r30
            double r14 = r0.relativeThreshold
            double r34 = org.apache.commons.math3.util.FastMath.abs((double) r7)
            double r14 = r14 * r34
            r34 = r2
            r35 = r3
            double r2 = r0.absoluteThreshold
            double r14 = r14 + r2
            r2 = 4611686018427387904(0x4000000000000000, double:2.0)
            double r36 = r14 * r2
            double r38 = r7 - r28
            double r38 = org.apache.commons.math3.util.FastMath.abs((double) r38)
            double r40 = r16 - r5
            double r40 = r40 * r30
            double r40 = r36 - r40
            int r38 = (r38 > r40 ? 1 : (r38 == r40 ? 0 : -1))
            if (r38 > 0) goto L_0x0077
            r38 = 1
            goto L_0x0079
        L_0x0077:
            r38 = 0
        L_0x0079:
            if (r38 != 0) goto L_0x01a5
            double r38 = org.apache.commons.math3.util.FastMath.abs((double) r24)
            int r4 = (r38 > r14 ? 1 : (r38 == r14 ? 0 : -1))
            if (r4 <= 0) goto L_0x00f5
            double r38 = r7 - r9
            double r40 = r18 - r20
            double r40 = r40 * r38
            double r42 = r7 - r11
            double r44 = r18 - r22
            double r44 = r44 * r42
            double r42 = r42 * r44
            double r38 = r38 * r40
            r46 = r11
            double r11 = r42 - r38
            double r44 = r44 - r40
            double r2 = r2 * r44
            r32 = 0
            int r4 = (r2 > r32 ? 1 : (r2 == r32 ? 0 : -1))
            if (r4 <= 0) goto L_0x00a3
            double r11 = -r11
            goto L_0x00a4
        L_0x00a3:
            double r2 = -r2
        L_0x00a4:
            double r38 = r5 - r7
            double r40 = r2 * r38
            int r4 = (r11 > r40 ? 1 : (r11 == r40 ? 0 : -1))
            if (r4 <= 0) goto L_0x00e6
            double r40 = r16 - r7
            double r40 = r40 * r2
            int r4 = (r11 > r40 ? 1 : (r11 == r40 ? 0 : -1))
            if (r4 >= 0) goto L_0x00e6
            double r40 = org.apache.commons.math3.util.FastMath.abs((double) r11)
            double r30 = r30 * r2
            double r30 = r30 * r24
            double r24 = org.apache.commons.math3.util.FastMath.abs((double) r30)
            int r4 = (r40 > r24 ? 1 : (r40 == r24 ? 0 : -1))
            if (r4 >= 0) goto L_0x00e6
            double r11 = r11 / r2
            double r2 = r7 + r11
            double r24 = r2 - r5
            int r4 = (r24 > r36 ? 1 : (r24 == r36 ? 0 : -1))
            if (r4 < 0) goto L_0x00d7
            double r2 = r16 - r2
            int r2 = (r2 > r36 ? 1 : (r2 == r36 ? 0 : -1))
            if (r2 >= 0) goto L_0x00d4
            goto L_0x00d7
        L_0x00d4:
            r24 = r26
            goto L_0x0105
        L_0x00d7:
            int r2 = (r7 > r28 ? 1 : (r7 == r28 ? 0 : -1))
            if (r2 > 0) goto L_0x00e0
            r24 = r26
            r26 = r14
            goto L_0x0107
        L_0x00e0:
            double r2 = -r14
            r24 = r26
            r26 = r2
            goto L_0x0107
        L_0x00e6:
            int r2 = (r7 > r28 ? 1 : (r7 == r28 ? 0 : -1))
            if (r2 >= 0) goto L_0x00ec
            double r38 = r16 - r7
        L_0x00ec:
            double r2 = GOLDEN_SECTION
            double r2 = r2 * r38
            r26 = r2
            r24 = r38
            goto L_0x0107
        L_0x00f5:
            r46 = r11
            int r2 = (r7 > r28 ? 1 : (r7 == r28 ? 0 : -1))
            if (r2 >= 0) goto L_0x00fe
            double r2 = r16 - r7
            goto L_0x0100
        L_0x00fe:
            double r2 = r5 - r7
        L_0x0100:
            double r11 = GOLDEN_SECTION
            double r11 = r11 * r2
            r24 = r2
        L_0x0105:
            r26 = r11
        L_0x0107:
            double r2 = org.apache.commons.math3.util.FastMath.abs((double) r26)
            int r2 = (r2 > r14 ? 1 : (r2 == r14 ? 0 : -1))
            if (r2 >= 0) goto L_0x011a
            r2 = 0
            int r4 = (r26 > r2 ? 1 : (r26 == r2 ? 0 : -1))
            if (r4 < 0) goto L_0x0117
            double r14 = r14 + r7
            goto L_0x011e
        L_0x0117:
            double r14 = r7 - r14
            goto L_0x011e
        L_0x011a:
            r2 = 0
            double r14 = r7 + r26
        L_0x011e:
            double r11 = r0.computeObjectiveValue(r14)
            if (r1 != 0) goto L_0x0125
            double r11 = -r11
        L_0x0125:
            org.apache.commons.math3.optim.univariate.UnivariatePointValuePair r4 = new org.apache.commons.math3.optim.univariate.UnivariatePointValuePair
            if (r1 == 0) goto L_0x012b
            r2 = r11
            goto L_0x012c
        L_0x012b:
            double r2 = -r11
        L_0x012c:
            r4.<init>(r14, r2)
            r2 = r35
            org.apache.commons.math3.optim.univariate.UnivariatePointValuePair r3 = r0.best(r2, r4, r1)
            org.apache.commons.math3.optim.univariate.UnivariatePointValuePair r13 = r0.best(r13, r3, r1)
            if (r34 == 0) goto L_0x014a
            int r3 = r50.getIterations()
            r28 = r5
            r5 = r34
            boolean r3 = r5.converged(r3, r2, r4)
            if (r3 == 0) goto L_0x014e
            return r13
        L_0x014a:
            r28 = r5
            r5 = r34
        L_0x014e:
            int r3 = (r11 > r18 ? 1 : (r11 == r18 ? 0 : -1))
            if (r3 > 0) goto L_0x0166
            int r3 = (r14 > r7 ? 1 : (r14 == r7 ? 0 : -1))
            if (r3 >= 0) goto L_0x0159
            r16 = r7
            goto L_0x015b
        L_0x0159:
            r28 = r7
        L_0x015b:
            r6 = r4
            r20 = r22
            r22 = r18
            r18 = r11
            r11 = r9
            r9 = r7
            r7 = r14
            goto L_0x019b
        L_0x0166:
            int r3 = (r14 > r7 ? 1 : (r14 == r7 ? 0 : -1))
            if (r3 >= 0) goto L_0x016d
            r28 = r14
            goto L_0x016f
        L_0x016d:
            r16 = r14
        L_0x016f:
            int r3 = (r11 > r22 ? 1 : (r11 == r22 ? 0 : -1))
            if (r3 <= 0) goto L_0x0194
            boolean r3 = org.apache.commons.math3.util.Precision.equals((double) r9, (double) r7)
            if (r3 == 0) goto L_0x017a
            goto L_0x0194
        L_0x017a:
            int r3 = (r11 > r20 ? 1 : (r11 == r20 ? 0 : -1))
            r6 = r4
            if (r3 <= 0) goto L_0x0190
            r3 = r46
            boolean r30 = org.apache.commons.math3.util.Precision.equals((double) r3, (double) r7)
            if (r30 != 0) goto L_0x0190
            boolean r30 = org.apache.commons.math3.util.Precision.equals((double) r3, (double) r9)
            if (r30 == 0) goto L_0x018e
            goto L_0x0190
        L_0x018e:
            r11 = r3
            goto L_0x019b
        L_0x0190:
            r20 = r11
            r11 = r14
            goto L_0x019b
        L_0x0194:
            r6 = r4
            r20 = r22
            r22 = r11
            r11 = r9
            r9 = r14
        L_0x019b:
            r50.incrementIterationCount()
            r4 = r2
            r2 = r5
            r3 = r6
            r5 = r28
            goto L_0x004b
        L_0x01a5:
            r2 = r35
            org.apache.commons.math3.optim.univariate.UnivariatePointValuePair r2 = r0.best(r4, r2, r1)
            org.apache.commons.math3.optim.univariate.UnivariatePointValuePair r0 = r0.best(r13, r2, r1)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.math3.optim.univariate.BrentOptimizer.doOptimize():org.apache.commons.math3.optim.univariate.UnivariatePointValuePair");
    }

    private UnivariatePointValuePair best(UnivariatePointValuePair univariatePointValuePair, UnivariatePointValuePair univariatePointValuePair2, boolean z) {
        if (univariatePointValuePair == null) {
            return univariatePointValuePair2;
        }
        if (univariatePointValuePair2 == null) {
            return univariatePointValuePair;
        }
        return z ? univariatePointValuePair.getValue() <= univariatePointValuePair2.getValue() ? univariatePointValuePair : univariatePointValuePair2 : univariatePointValuePair.getValue() >= univariatePointValuePair2.getValue() ? univariatePointValuePair : univariatePointValuePair2;
    }
}
