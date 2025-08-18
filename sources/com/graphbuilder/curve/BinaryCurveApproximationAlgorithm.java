package com.graphbuilder.curve;

public final class BinaryCurveApproximationAlgorithm {
    private BinaryCurveApproximationAlgorithm() {
    }

    /* JADX WARNING: Removed duplicated region for block: B:24:0x00b6  */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x00d3  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void genPts(com.graphbuilder.curve.ParametricCurve r27, double r28, double r30, com.graphbuilder.curve.MultiPath r32) {
        /*
            r0 = r27
            r1 = r32
            int r2 = (r28 > r30 ? 1 : (r28 == r30 ? 0 : -1))
            if (r2 > 0) goto L_0x0109
            int r2 = r32.getDimension()
            r3 = 10
            double[][] r3 = new double[r3][]
            int r4 = r2 + 1
            double[] r5 = new double[r4]
            r5[r2] = r28
            r0.eval(r5)
            double[] r6 = new double[r4]
            r6[r2] = r30
            r0.eval(r6)
            r7 = 0
            r3[r7] = r6
            int r6 = r27.getSampleLimit()
            double[][] r8 = new double[r6][]
            double r9 = r32.getFlatness()
            double r11 = r32.getFlatness()
            double r9 = r9 * r11
            double[] r11 = new double[r4]
            r13 = r28
            r15 = r30
            r7 = 1
        L_0x0039:
            double r17 = r13 + r15
            r19 = 4611686018427387904(0x4000000000000000, double:2.0)
            double r17 = r17 / r19
            double[] r12 = new double[r4]
            r12[r2] = r17
            r0.eval(r12)
            int r21 = r7 + -1
            r28 = r15
            r15 = r3[r21]
            double r15 = com.graphbuilder.geom.Geom.ptSegDistSq(r5, r15, r12, r11, r2)
            boolean r21 = java.lang.Double.isNaN(r15)
            if (r21 != 0) goto L_0x00e6
            boolean r21 = java.lang.Double.isInfinite(r15)
            if (r21 != 0) goto L_0x00e6
            int r15 = (r15 > r9 ? 1 : (r15 == r9 ? 0 : -1))
            if (r15 >= 0) goto L_0x00af
            r15 = 0
            r30 = r15
            r15 = 0
        L_0x0065:
            if (r15 >= r6) goto L_0x0088
            double r21 = r13 + r17
            double r21 = r21 / r19
            r23 = r13
            double[] r13 = new double[r4]
            r8[r15] = r13
            r13[r2] = r21
            r0.eval(r13)
            double r13 = com.graphbuilder.geom.Geom.ptSegDistSq(r5, r12, r13, r11, r2)
            int r13 = (r13 > r9 ? 1 : (r13 == r9 ? 0 : -1))
            if (r13 < 0) goto L_0x007f
            goto L_0x008c
        L_0x007f:
            int r15 = r15 + 1
            r30 = r21
            r17 = r30
            r13 = r23
            goto L_0x0065
        L_0x0088:
            r23 = r13
            r21 = r30
        L_0x008c:
            if (r15 != r6) goto L_0x0092
            r15 = r28
            r13 = 1
            goto L_0x00b4
        L_0x0092:
            double[][] r3 = checkSpace(r3, r7)
            int r13 = r7 + 1
            r3[r7] = r12
            r7 = r13
            r13 = 0
        L_0x009c:
            if (r13 > r15) goto L_0x00ac
            double[][] r3 = checkSpace(r3, r7)
            int r14 = r7 + 1
            r16 = r8[r13]
            r3[r7] = r16
            int r13 = r13 + 1
            r7 = r14
            goto L_0x009c
        L_0x00ac:
            r15 = r21
            goto L_0x00b3
        L_0x00af:
            r23 = r13
            r15 = r28
        L_0x00b3:
            r13 = 0
        L_0x00b4:
            if (r13 == 0) goto L_0x00d3
            r1.lineTo(r5)
            r1.lineTo(r12)
            int r7 = r7 + -1
            r5 = r3[r7]
            if (r7 != 0) goto L_0x00c6
            r1.lineTo(r5)
            return
        L_0x00c6:
            int r12 = r7 + -1
            r12 = r3[r12]
            r12 = r12[r2]
            r25 = r12
            r13 = r15
            r15 = r25
            goto L_0x0039
        L_0x00d3:
            int r13 = (r15 > r17 ? 1 : (r15 == r17 ? 0 : -1))
            if (r13 <= 0) goto L_0x00e2
            double[][] r3 = checkSpace(r3, r7)
            int r13 = r7 + 1
            r3[r7] = r12
            r7 = r13
            r15 = r17
        L_0x00e2:
            r13 = r23
            goto L_0x0039
        L_0x00e6:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = "NaN or infinity resulted from calling the eval method of the "
            r1.<init>(r2)
            java.lang.Class r0 = r27.getClass()
            java.lang.String r0 = r0.getName()
            java.lang.StringBuilder r0 = r1.append(r0)
            java.lang.String r1 = " class."
            java.lang.StringBuilder r0 = r0.append(r1)
            java.lang.String r0 = r0.toString()
            java.lang.RuntimeException r1 = new java.lang.RuntimeException
            r1.<init>(r0)
            throw r1
        L_0x0109:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.String r1 = "t_min <= t_max required."
            r0.<init>(r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.graphbuilder.curve.BinaryCurveApproximationAlgorithm.genPts(com.graphbuilder.curve.ParametricCurve, double, double, com.graphbuilder.curve.MultiPath):void");
    }

    private static double[][] checkSpace(double[][] dArr, int i) {
        if (i != dArr.length) {
            return dArr;
        }
        double[][] dArr2 = new double[(i * 2)][];
        for (int i2 = 0; i2 < i; i2++) {
            dArr2[i2] = dArr[i2];
        }
        return dArr2;
    }
}
