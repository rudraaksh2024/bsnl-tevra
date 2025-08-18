package org.apache.commons.math3.optimization.univariate;

import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.optimization.ConvergenceChecker;
import org.apache.commons.math3.util.FastMath;

@Deprecated
public class BrentOptimizer extends BaseAbstractUnivariateOptimizer {
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
    /* JADX WARNING: Removed duplicated region for block: B:53:0x0111  */
    /* JADX WARNING: Removed duplicated region for block: B:57:0x011c  */
    /* JADX WARNING: Removed duplicated region for block: B:60:0x0126  */
    /* JADX WARNING: Removed duplicated region for block: B:63:0x012b  */
    /* JADX WARNING: Removed duplicated region for block: B:64:0x012d  */
    /* JADX WARNING: Removed duplicated region for block: B:70:0x014c  */
    /* JADX WARNING: Removed duplicated region for block: B:98:0x014b A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public org.apache.commons.math3.optimization.univariate.UnivariatePointValuePair doOptimize() {
        /*
            r51 = this;
            r0 = r51
            org.apache.commons.math3.optimization.GoalType r1 = r51.getGoalType()
            org.apache.commons.math3.optimization.GoalType r2 = org.apache.commons.math3.optimization.GoalType.MINIMIZE
            if (r1 != r2) goto L_0x000c
            r1 = 1
            goto L_0x000d
        L_0x000c:
            r1 = 0
        L_0x000d:
            double r5 = r51.getMin()
            double r7 = r51.getStartValue()
            double r9 = r51.getMax()
            org.apache.commons.math3.optimization.ConvergenceChecker r2 = r51.getConvergenceChecker()
            int r11 = (r5 > r9 ? 1 : (r5 == r9 ? 0 : -1))
            if (r11 >= 0) goto L_0x0022
            goto L_0x0027
        L_0x0022:
            r49 = r5
            r5 = r9
            r9 = r49
        L_0x0027:
            double r11 = r0.computeObjectiveValue(r7)
            if (r1 != 0) goto L_0x002e
            double r11 = -r11
        L_0x002e:
            org.apache.commons.math3.optimization.univariate.UnivariatePointValuePair r13 = new org.apache.commons.math3.optimization.univariate.UnivariatePointValuePair
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
            r28 = 0
            r11 = r7
            r16 = r9
            r9 = r11
        L_0x004d:
            double r29 = r5 + r16
            r31 = 4602678819172646912(0x3fe0000000000000, double:0.5)
            double r29 = r29 * r31
            double r14 = r0.relativeThreshold
            double r35 = org.apache.commons.math3.util.FastMath.abs((double) r7)
            double r14 = r14 * r35
            r35 = r2
            r36 = r3
            double r2 = r0.absoluteThreshold
            double r14 = r14 + r2
            r2 = 4611686018427387904(0x4000000000000000, double:2.0)
            double r37 = r14 * r2
            double r39 = r7 - r29
            double r39 = org.apache.commons.math3.util.FastMath.abs((double) r39)
            double r41 = r16 - r5
            double r41 = r41 * r31
            double r41 = r37 - r41
            int r39 = (r39 > r41 ? 1 : (r39 == r41 ? 0 : -1))
            if (r39 > 0) goto L_0x0079
            r39 = 1
            goto L_0x007b
        L_0x0079:
            r39 = 0
        L_0x007b:
            if (r39 != 0) goto L_0x01ae
            double r39 = org.apache.commons.math3.util.FastMath.abs((double) r24)
            int r4 = (r39 > r14 ? 1 : (r39 == r14 ? 0 : -1))
            if (r4 <= 0) goto L_0x00f7
            double r39 = r7 - r9
            double r41 = r18 - r20
            double r41 = r41 * r39
            double r43 = r7 - r11
            double r45 = r18 - r22
            double r45 = r45 * r43
            double r43 = r43 * r45
            double r39 = r39 * r41
            r47 = r11
            double r11 = r43 - r39
            double r45 = r45 - r41
            double r2 = r2 * r45
            r33 = 0
            int r4 = (r2 > r33 ? 1 : (r2 == r33 ? 0 : -1))
            if (r4 <= 0) goto L_0x00a5
            double r11 = -r11
            goto L_0x00a6
        L_0x00a5:
            double r2 = -r2
        L_0x00a6:
            double r39 = r5 - r7
            double r41 = r2 * r39
            int r4 = (r11 > r41 ? 1 : (r11 == r41 ? 0 : -1))
            if (r4 <= 0) goto L_0x00e8
            double r41 = r16 - r7
            double r41 = r41 * r2
            int r4 = (r11 > r41 ? 1 : (r11 == r41 ? 0 : -1))
            if (r4 >= 0) goto L_0x00e8
            double r41 = org.apache.commons.math3.util.FastMath.abs((double) r11)
            double r31 = r31 * r2
            double r31 = r31 * r24
            double r24 = org.apache.commons.math3.util.FastMath.abs((double) r31)
            int r4 = (r41 > r24 ? 1 : (r41 == r24 ? 0 : -1))
            if (r4 >= 0) goto L_0x00e8
            double r11 = r11 / r2
            double r2 = r7 + r11
            double r24 = r2 - r5
            int r4 = (r24 > r37 ? 1 : (r24 == r37 ? 0 : -1))
            if (r4 < 0) goto L_0x00d9
            double r2 = r16 - r2
            int r2 = (r2 > r37 ? 1 : (r2 == r37 ? 0 : -1))
            if (r2 >= 0) goto L_0x00d6
            goto L_0x00d9
        L_0x00d6:
            r24 = r26
            goto L_0x0107
        L_0x00d9:
            int r2 = (r7 > r29 ? 1 : (r7 == r29 ? 0 : -1))
            if (r2 > 0) goto L_0x00e2
            r24 = r26
            r26 = r14
            goto L_0x0109
        L_0x00e2:
            double r2 = -r14
            r24 = r26
            r26 = r2
            goto L_0x0109
        L_0x00e8:
            int r2 = (r7 > r29 ? 1 : (r7 == r29 ? 0 : -1))
            if (r2 >= 0) goto L_0x00ee
            double r39 = r16 - r7
        L_0x00ee:
            double r2 = GOLDEN_SECTION
            double r2 = r2 * r39
            r26 = r2
            r24 = r39
            goto L_0x0109
        L_0x00f7:
            r47 = r11
            int r2 = (r7 > r29 ? 1 : (r7 == r29 ? 0 : -1))
            if (r2 >= 0) goto L_0x0100
            double r2 = r16 - r7
            goto L_0x0102
        L_0x0100:
            double r2 = r5 - r7
        L_0x0102:
            double r11 = GOLDEN_SECTION
            double r11 = r11 * r2
            r24 = r2
        L_0x0107:
            r26 = r11
        L_0x0109:
            double r2 = org.apache.commons.math3.util.FastMath.abs((double) r26)
            int r2 = (r2 > r14 ? 1 : (r2 == r14 ? 0 : -1))
            if (r2 >= 0) goto L_0x011c
            r2 = 0
            int r4 = (r26 > r2 ? 1 : (r26 == r2 ? 0 : -1))
            if (r4 < 0) goto L_0x0119
            double r14 = r14 + r7
            goto L_0x0120
        L_0x0119:
            double r14 = r7 - r14
            goto L_0x0120
        L_0x011c:
            r2 = 0
            double r14 = r7 + r26
        L_0x0120:
            double r11 = r0.computeObjectiveValue(r14)
            if (r1 != 0) goto L_0x0127
            double r11 = -r11
        L_0x0127:
            org.apache.commons.math3.optimization.univariate.UnivariatePointValuePair r4 = new org.apache.commons.math3.optimization.univariate.UnivariatePointValuePair
            if (r1 == 0) goto L_0x012d
            r2 = r11
            goto L_0x012e
        L_0x012d:
            double r2 = -r11
        L_0x012e:
            r4.<init>(r14, r2)
            r2 = r36
            org.apache.commons.math3.optimization.univariate.UnivariatePointValuePair r3 = r0.best(r2, r4, r1)
            org.apache.commons.math3.optimization.univariate.UnivariatePointValuePair r13 = r0.best(r13, r3, r1)
            r3 = r35
            r49 = r5
            r5 = r28
            r28 = r49
            if (r35 == 0) goto L_0x014c
            boolean r6 = r3.converged(r5, r2, r4)
            if (r6 == 0) goto L_0x014c
            return r13
        L_0x014c:
            int r6 = (r11 > r18 ? 1 : (r11 == r18 ? 0 : -1))
            if (r6 > 0) goto L_0x0166
            int r6 = (r14 > r7 ? 1 : (r14 == r7 ? 0 : -1))
            if (r6 >= 0) goto L_0x0157
            r16 = r7
            goto L_0x0159
        L_0x0157:
            r28 = r7
        L_0x0159:
            r35 = r3
            r6 = r4
            r20 = r22
            r22 = r18
            r18 = r11
            r11 = r9
            r9 = r7
            r7 = r14
            goto L_0x01a0
        L_0x0166:
            int r6 = (r14 > r7 ? 1 : (r14 == r7 ? 0 : -1))
            if (r6 >= 0) goto L_0x016d
            r28 = r14
            goto L_0x016f
        L_0x016d:
            r16 = r14
        L_0x016f:
            int r6 = (r11 > r22 ? 1 : (r11 == r22 ? 0 : -1))
            if (r6 <= 0) goto L_0x0197
            boolean r6 = org.apache.commons.math3.util.Precision.equals((double) r9, (double) r7)
            if (r6 == 0) goto L_0x017a
            goto L_0x0197
        L_0x017a:
            int r6 = (r11 > r20 ? 1 : (r11 == r20 ? 0 : -1))
            r35 = r3
            if (r6 <= 0) goto L_0x0192
            r6 = r4
            r3 = r47
            boolean r30 = org.apache.commons.math3.util.Precision.equals((double) r3, (double) r7)
            if (r30 != 0) goto L_0x0193
            boolean r30 = org.apache.commons.math3.util.Precision.equals((double) r3, (double) r9)
            if (r30 == 0) goto L_0x0190
            goto L_0x0193
        L_0x0190:
            r11 = r3
            goto L_0x01a0
        L_0x0192:
            r6 = r4
        L_0x0193:
            r20 = r11
            r11 = r14
            goto L_0x01a0
        L_0x0197:
            r35 = r3
            r6 = r4
            r20 = r22
            r22 = r11
            r11 = r9
            r9 = r14
        L_0x01a0:
            int r3 = r5 + 1
            r4 = r2
            r2 = r35
            r49 = r28
            r28 = r3
            r3 = r6
            r5 = r49
            goto L_0x004d
        L_0x01ae:
            r2 = r36
            org.apache.commons.math3.optimization.univariate.UnivariatePointValuePair r2 = r0.best(r4, r2, r1)
            org.apache.commons.math3.optimization.univariate.UnivariatePointValuePair r0 = r0.best(r13, r2, r1)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.math3.optimization.univariate.BrentOptimizer.doOptimize():org.apache.commons.math3.optimization.univariate.UnivariatePointValuePair");
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
