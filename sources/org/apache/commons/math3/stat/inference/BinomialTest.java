package org.apache.commons.math3.stat.inference;

public class BinomialTest {
    public boolean binomialTest(int i, int i2, double d, AlternativeHypothesis alternativeHypothesis, double d2) {
        return binomialTest(i, i2, d, alternativeHypothesis) < d2;
    }

    /* renamed from: org.apache.commons.math3.stat.inference.BinomialTest$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$org$apache$commons$math3$stat$inference$AlternativeHypothesis;

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|(3:5|6|8)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        static {
            /*
                org.apache.commons.math3.stat.inference.AlternativeHypothesis[] r0 = org.apache.commons.math3.stat.inference.AlternativeHypothesis.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$org$apache$commons$math3$stat$inference$AlternativeHypothesis = r0
                org.apache.commons.math3.stat.inference.AlternativeHypothesis r1 = org.apache.commons.math3.stat.inference.AlternativeHypothesis.GREATER_THAN     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$org$apache$commons$math3$stat$inference$AlternativeHypothesis     // Catch:{ NoSuchFieldError -> 0x001d }
                org.apache.commons.math3.stat.inference.AlternativeHypothesis r1 = org.apache.commons.math3.stat.inference.AlternativeHypothesis.LESS_THAN     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$org$apache$commons$math3$stat$inference$AlternativeHypothesis     // Catch:{ NoSuchFieldError -> 0x0028 }
                org.apache.commons.math3.stat.inference.AlternativeHypothesis r1 = org.apache.commons.math3.stat.inference.AlternativeHypothesis.TWO_SIDED     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.math3.stat.inference.BinomialTest.AnonymousClass1.<clinit>():void");
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:41:0x004f A[EDGE_INSN: B:41:0x004f->B:24:0x004f ?: BREAK  , SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public double binomialTest(int r9, int r10, double r11, org.apache.commons.math3.stat.inference.AlternativeHypothesis r13) {
        /*
            r8 = this;
            if (r9 < 0) goto L_0x00a8
            if (r10 < 0) goto L_0x009e
            r0 = 0
            int r8 = (r11 > r0 ? 1 : (r11 == r0 ? 0 : -1))
            r2 = 0
            r3 = 1
            if (r8 < 0) goto L_0x008c
            r4 = 4607182418800017408(0x3ff0000000000000, double:1.0)
            int r8 = (r11 > r4 ? 1 : (r11 == r4 ? 0 : -1))
            if (r8 > 0) goto L_0x008c
            r8 = 2
            if (r9 < r10) goto L_0x0076
            if (r13 == 0) goto L_0x0070
            org.apache.commons.math3.distribution.BinomialDistribution r6 = new org.apache.commons.math3.distribution.BinomialDistribution
            r7 = 0
            r6.<init>(r7, r9, r11)
            int[] r11 = org.apache.commons.math3.stat.inference.BinomialTest.AnonymousClass1.$SwitchMap$org$apache$commons$math3$stat$inference$AlternativeHypothesis
            int r12 = r13.ordinal()
            r11 = r11[r12]
            if (r11 == r3) goto L_0x0069
            if (r11 == r8) goto L_0x0064
            r12 = 3
            if (r11 != r12) goto L_0x0050
        L_0x002c:
            double r11 = r6.probability(r2)
            double r3 = r6.probability(r9)
            int r8 = (r11 > r3 ? 1 : (r11 == r3 ? 0 : -1))
            if (r8 != 0) goto L_0x0041
            r3 = 4611686018427387904(0x4000000000000000, double:2.0)
            double r11 = r11 * r3
            double r0 = r0 + r11
            int r2 = r2 + 1
        L_0x003e:
            int r9 = r9 + -1
            goto L_0x004b
        L_0x0041:
            int r8 = (r11 > r3 ? 1 : (r11 == r3 ? 0 : -1))
            if (r8 >= 0) goto L_0x0049
            double r0 = r0 + r11
            int r2 = r2 + 1
            goto L_0x004b
        L_0x0049:
            double r0 = r0 + r3
            goto L_0x003e
        L_0x004b:
            if (r2 > r10) goto L_0x004f
            if (r9 >= r10) goto L_0x002c
        L_0x004f:
            return r0
        L_0x0050:
            org.apache.commons.math3.exception.MathInternalError r9 = new org.apache.commons.math3.exception.MathInternalError
            org.apache.commons.math3.exception.util.LocalizedFormats r10 = org.apache.commons.math3.exception.util.LocalizedFormats.OUT_OF_RANGE_SIMPLE
            java.lang.Object[] r11 = new java.lang.Object[r12]
            r11[r2] = r13
            org.apache.commons.math3.stat.inference.AlternativeHypothesis r12 = org.apache.commons.math3.stat.inference.AlternativeHypothesis.TWO_SIDED
            r11[r3] = r12
            org.apache.commons.math3.stat.inference.AlternativeHypothesis r12 = org.apache.commons.math3.stat.inference.AlternativeHypothesis.LESS_THAN
            r11[r8] = r12
            r9.<init>(r10, r11)
            throw r9
        L_0x0064:
            double r8 = r6.cumulativeProbability(r10)
            return r8
        L_0x0069:
            int r10 = r10 - r3
            double r8 = r6.cumulativeProbability(r10)
            double r4 = r4 - r8
            return r4
        L_0x0070:
            org.apache.commons.math3.exception.NullArgumentException r8 = new org.apache.commons.math3.exception.NullArgumentException
            r8.<init>()
            throw r8
        L_0x0076:
            org.apache.commons.math3.exception.MathIllegalArgumentException r11 = new org.apache.commons.math3.exception.MathIllegalArgumentException
            org.apache.commons.math3.exception.util.LocalizedFormats r12 = org.apache.commons.math3.exception.util.LocalizedFormats.BINOMIAL_INVALID_PARAMETERS_ORDER
            java.lang.Object[] r8 = new java.lang.Object[r8]
            java.lang.Integer r9 = java.lang.Integer.valueOf(r9)
            r8[r2] = r9
            java.lang.Integer r9 = java.lang.Integer.valueOf(r10)
            r8[r3] = r9
            r11.<init>(r12, r8)
            throw r11
        L_0x008c:
            org.apache.commons.math3.exception.OutOfRangeException r8 = new org.apache.commons.math3.exception.OutOfRangeException
            java.lang.Double r9 = java.lang.Double.valueOf(r11)
            java.lang.Integer r10 = java.lang.Integer.valueOf(r2)
            java.lang.Integer r11 = java.lang.Integer.valueOf(r3)
            r8.<init>(r9, r10, r11)
            throw r8
        L_0x009e:
            org.apache.commons.math3.exception.NotPositiveException r8 = new org.apache.commons.math3.exception.NotPositiveException
            java.lang.Integer r9 = java.lang.Integer.valueOf(r10)
            r8.<init>(r9)
            throw r8
        L_0x00a8:
            org.apache.commons.math3.exception.NotPositiveException r8 = new org.apache.commons.math3.exception.NotPositiveException
            java.lang.Integer r9 = java.lang.Integer.valueOf(r9)
            r8.<init>(r9)
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.math3.stat.inference.BinomialTest.binomialTest(int, int, double, org.apache.commons.math3.stat.inference.AlternativeHypothesis):double");
    }
}
