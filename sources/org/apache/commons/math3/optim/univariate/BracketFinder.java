package org.apache.commons.math3.optim.univariate;

import org.apache.commons.math3.analysis.UnivariateFunction;
import org.apache.commons.math3.exception.MaxCountExceededException;
import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import org.apache.commons.math3.exception.TooManyEvaluationsException;
import org.apache.commons.math3.util.IntegerSequence;

public class BracketFinder {
    private static final double EPS_MIN = 1.0E-21d;
    private static final double GOLD = 1.618034d;
    private IntegerSequence.Incrementor evaluations;
    private double fHi;
    private double fLo;
    private double fMid;
    private final double growLimit;
    private double hi;
    private double lo;
    private double mid;

    public BracketFinder() {
        this(100.0d, 500);
    }

    public BracketFinder(double d, int i) {
        if (d <= 0.0d) {
            throw new NotStrictlyPositiveException(Double.valueOf(d));
        } else if (i > 0) {
            this.growLimit = d;
            this.evaluations = IntegerSequence.Incrementor.create().withMaximalCount(i);
        } else {
            throw new NotStrictlyPositiveException(Integer.valueOf(i));
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:29:0x00a1, code lost:
        r1 = r16;
        r32 = r6;
        r6 = r10;
        r10 = r32;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:55:0x0137, code lost:
        r6 = r6;
        r14 = r4;
        r4 = r8;
        r1 = r16;
     */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x00ac  */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x00b1  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void search(org.apache.commons.math3.analysis.UnivariateFunction r35, org.apache.commons.math3.optim.nonlinear.scalar.GoalType r36, double r37, double r39) {
        /*
            r34 = this;
            r0 = r34
            r1 = r35
            org.apache.commons.math3.util.IntegerSequence$Incrementor r2 = r0.evaluations
            r3 = 0
            org.apache.commons.math3.util.IntegerSequence$Incrementor r2 = r2.withStart(r3)
            r0.evaluations = r2
            org.apache.commons.math3.optim.nonlinear.scalar.GoalType r2 = org.apache.commons.math3.optim.nonlinear.scalar.GoalType.MINIMIZE
            r4 = r36
            if (r4 != r2) goto L_0x0014
            r3 = 1
        L_0x0014:
            r4 = r37
            double r6 = r0.eval(r1, r4)
            r8 = r39
            double r10 = r0.eval(r1, r8)
            if (r3 == 0) goto L_0x0027
            int r2 = (r6 > r10 ? 1 : (r6 == r10 ? 0 : -1))
            if (r2 >= 0) goto L_0x0031
            goto L_0x002b
        L_0x0027:
            int r2 = (r6 > r10 ? 1 : (r6 == r10 ? 0 : -1))
            if (r2 <= 0) goto L_0x0031
        L_0x002b:
            r32 = r6
            r6 = r10
            r10 = r32
            goto L_0x0036
        L_0x0031:
            r32 = r4
            r4 = r8
            r8 = r32
        L_0x0036:
            double r12 = r4 - r8
            r14 = 4609965796492119705(0x3ff9e3779e9d0e99, double:1.618034)
            double r12 = r12 * r14
            double r12 = r12 + r4
            double r16 = r0.eval(r1, r12)
        L_0x0043:
            if (r3 == 0) goto L_0x004a
            int r2 = (r16 > r10 ? 1 : (r16 == r10 ? 0 : -1))
            if (r2 >= 0) goto L_0x0137
            goto L_0x004e
        L_0x004a:
            int r2 = (r16 > r10 ? 1 : (r16 == r10 ? 0 : -1))
            if (r2 <= 0) goto L_0x0137
        L_0x004e:
            double r18 = r4 - r8
            double r20 = r10 - r16
            double r20 = r20 * r18
            double r22 = r4 - r12
            double r24 = r10 - r6
            double r24 = r24 * r22
            double r26 = r24 - r20
            double r28 = org.apache.commons.math3.util.FastMath.abs((double) r26)
            r30 = 4292743757239851855(0x3b92e3b40a0e9b4f, double:1.0E-21)
            int r2 = (r28 > r30 ? 1 : (r28 == r30 ? 0 : -1))
            if (r2 >= 0) goto L_0x006f
            r26 = 4297247356867222351(0x3ba2e3b40a0e9b4f, double:2.0E-21)
            goto L_0x0073
        L_0x006f:
            r28 = 4611686018427387904(0x4000000000000000, double:2.0)
            double r26 = r26 * r28
        L_0x0073:
            double r22 = r22 * r24
            double r18 = r18 * r20
            double r22 = r22 - r18
            double r22 = r22 / r26
            double r14 = r4 - r22
            r38 = r6
            double r6 = r0.growLimit
            double r18 = r12 - r4
            double r6 = r6 * r18
            double r6 = r6 + r4
            double r20 = r14 - r12
            double r22 = r4 - r14
            double r22 = r22 * r20
            r24 = 0
            int r2 = (r22 > r24 ? 1 : (r22 == r24 ? 0 : -1))
            if (r2 <= 0) goto L_0x00cb
            double r6 = r0.eval(r1, r14)
            if (r3 == 0) goto L_0x009d
            int r2 = (r6 > r16 ? 1 : (r6 == r16 ? 0 : -1))
            if (r2 >= 0) goto L_0x00aa
            goto L_0x00a1
        L_0x009d:
            int r2 = (r6 > r16 ? 1 : (r6 == r16 ? 0 : -1))
            if (r2 <= 0) goto L_0x00aa
        L_0x00a1:
            r1 = r16
            r32 = r6
            r6 = r10
            r10 = r32
            goto L_0x013f
        L_0x00aa:
            if (r3 == 0) goto L_0x00b1
            int r2 = (r6 > r10 ? 1 : (r6 == r10 ? 0 : -1))
            if (r2 <= 0) goto L_0x00bd
            goto L_0x00b5
        L_0x00b1:
            int r2 = (r6 > r10 ? 1 : (r6 == r10 ? 0 : -1))
            if (r2 >= 0) goto L_0x00bd
        L_0x00b5:
            r1 = r6
            r12 = r14
            r6 = r38
            r14 = r4
            r4 = r8
            goto L_0x013f
        L_0x00bd:
            r6 = 4609965796492119705(0x3ff9e3779e9d0e99, double:1.618034)
            double r18 = r18 * r6
            double r6 = r12 + r18
            double r8 = r0.eval(r1, r6)
            goto L_0x00d9
        L_0x00cb:
            double r8 = r14 - r6
            double r22 = r6 - r12
            double r22 = r22 * r8
            int r2 = (r22 > r24 ? 1 : (r22 == r24 ? 0 : -1))
            if (r2 < 0) goto L_0x00ea
            double r8 = r0.eval(r1, r6)
        L_0x00d9:
            r22 = 4609965796492119705(0x3ff9e3779e9d0e99, double:1.618034)
        L_0x00de:
            r32 = r10
            r10 = r16
            r16 = r8
            r8 = r4
            r4 = r12
            r12 = r6
        L_0x00e7:
            r6 = r32
            goto L_0x0133
        L_0x00ea:
            double r6 = r12 - r14
            double r8 = r8 * r6
            int r2 = (r8 > r24 ? 1 : (r8 == r24 ? 0 : -1))
            if (r2 <= 0) goto L_0x0125
            double r6 = r0.eval(r1, r14)
            if (r3 == 0) goto L_0x00fc
            int r2 = (r6 > r16 ? 1 : (r6 == r16 ? 0 : -1))
            if (r2 >= 0) goto L_0x0116
            goto L_0x0100
        L_0x00fc:
            int r2 = (r6 > r16 ? 1 : (r6 == r16 ? 0 : -1))
            if (r2 <= 0) goto L_0x0116
        L_0x0100:
            r22 = 4609965796492119705(0x3ff9e3779e9d0e99, double:1.618034)
            double r20 = r20 * r22
            double r4 = r14 + r20
            double r8 = r0.eval(r1, r4)
            r10 = r6
            r6 = r16
            r16 = r8
            r8 = r12
            r12 = r4
            r4 = r14
            goto L_0x0133
        L_0x0116:
            r22 = 4609965796492119705(0x3ff9e3779e9d0e99, double:1.618034)
            r8 = r4
            r4 = r12
            r12 = r14
            r32 = r10
            r10 = r16
            r16 = r6
            goto L_0x00e7
        L_0x0125:
            r22 = 4609965796492119705(0x3ff9e3779e9d0e99, double:1.618034)
            double r18 = r18 * r22
            double r6 = r12 + r18
            double r8 = r0.eval(r1, r6)
            goto L_0x00de
        L_0x0133:
            r14 = r22
            goto L_0x0043
        L_0x0137:
            r38 = r6
            r6 = r38
            r14 = r4
            r4 = r8
            r1 = r16
        L_0x013f:
            r0.lo = r4
            r0.fLo = r6
            r0.mid = r14
            r0.fMid = r10
            r0.hi = r12
            r0.fHi = r1
            int r3 = (r4 > r12 ? 1 : (r4 == r12 ? 0 : -1))
            if (r3 <= 0) goto L_0x0157
            r0.lo = r12
            r0.hi = r4
            r0.fLo = r1
            r0.fHi = r6
        L_0x0157:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.math3.optim.univariate.BracketFinder.search(org.apache.commons.math3.analysis.UnivariateFunction, org.apache.commons.math3.optim.nonlinear.scalar.GoalType, double, double):void");
    }

    public int getMaxEvaluations() {
        return this.evaluations.getMaximalCount();
    }

    public int getEvaluations() {
        return this.evaluations.getCount();
    }

    public double getLo() {
        return this.lo;
    }

    public double getFLo() {
        return this.fLo;
    }

    public double getHi() {
        return this.hi;
    }

    public double getFHi() {
        return this.fHi;
    }

    public double getMid() {
        return this.mid;
    }

    public double getFMid() {
        return this.fMid;
    }

    private double eval(UnivariateFunction univariateFunction, double d) {
        try {
            this.evaluations.increment();
            return univariateFunction.value(d);
        } catch (MaxCountExceededException e) {
            throw new TooManyEvaluationsException(e.getMax());
        }
    }
}
