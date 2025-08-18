package org.apache.poi.ss.formula.functions;

public class Address implements Function {
    public static final int REF_ABSOLUTE = 1;
    public static final int REF_RELATIVE = 4;
    public static final int REF_ROW_ABSOLUTE_COLUMN_RELATIVE = 2;
    public static final int REF_ROW_RELATIVE_RELATIVE_ABSOLUTE = 3;

    /* JADX WARNING: Removed duplicated region for block: B:27:0x004a A[Catch:{ EvaluationException -> 0x0083 }] */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x006a A[Catch:{ EvaluationException -> 0x0083 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public org.apache.poi.ss.formula.eval.ValueEval evaluate(org.apache.poi.ss.formula.eval.ValueEval[] r9, int r10, int r11) {
        /*
            r8 = this;
            int r8 = r9.length
            r0 = 2
            if (r8 < r0) goto L_0x0089
            int r8 = r9.length
            r1 = 5
            if (r8 <= r1) goto L_0x000a
            goto L_0x0089
        L_0x000a:
            r8 = 0
            r2 = r9[r8]     // Catch:{ EvaluationException -> 0x0083 }
            double r2 = org.apache.poi.ss.formula.functions.NumericFunction.singleOperandEvaluate(r2, r10, r11)     // Catch:{ EvaluationException -> 0x0083 }
            int r2 = (int) r2     // Catch:{ EvaluationException -> 0x0083 }
            r3 = 1
            r4 = r9[r3]     // Catch:{ EvaluationException -> 0x0083 }
            double r4 = org.apache.poi.ss.formula.functions.NumericFunction.singleOperandEvaluate(r4, r10, r11)     // Catch:{ EvaluationException -> 0x0083 }
            int r4 = (int) r4     // Catch:{ EvaluationException -> 0x0083 }
            int r5 = r9.length     // Catch:{ EvaluationException -> 0x0083 }
            if (r5 <= r0) goto L_0x002b
            r5 = r9[r0]     // Catch:{ EvaluationException -> 0x0083 }
            org.apache.poi.ss.formula.eval.MissingArgEval r6 = org.apache.poi.ss.formula.eval.MissingArgEval.instance     // Catch:{ EvaluationException -> 0x0083 }
            if (r5 == r6) goto L_0x002b
            r5 = r9[r0]     // Catch:{ EvaluationException -> 0x0083 }
            double r5 = org.apache.poi.ss.formula.functions.NumericFunction.singleOperandEvaluate(r5, r10, r11)     // Catch:{ EvaluationException -> 0x0083 }
            int r5 = (int) r5     // Catch:{ EvaluationException -> 0x0083 }
            goto L_0x002c
        L_0x002b:
            r5 = r3
        L_0x002c:
            r6 = 4
            if (r5 == r3) goto L_0x0044
            if (r5 == r0) goto L_0x0041
            r0 = 3
            if (r5 == r0) goto L_0x003f
            if (r5 != r6) goto L_0x0037
            goto L_0x0045
        L_0x0037:
            org.apache.poi.ss.formula.eval.EvaluationException r8 = new org.apache.poi.ss.formula.eval.EvaluationException     // Catch:{ EvaluationException -> 0x0083 }
            org.apache.poi.ss.formula.eval.ErrorEval r9 = org.apache.poi.ss.formula.eval.ErrorEval.VALUE_INVALID     // Catch:{ EvaluationException -> 0x0083 }
            r8.<init>(r9)     // Catch:{ EvaluationException -> 0x0083 }
            throw r8     // Catch:{ EvaluationException -> 0x0083 }
        L_0x003f:
            r0 = r3
            goto L_0x0046
        L_0x0041:
            r0 = r8
            r8 = r3
            goto L_0x0046
        L_0x0044:
            r8 = r3
        L_0x0045:
            r0 = r8
        L_0x0046:
            int r5 = r9.length     // Catch:{ EvaluationException -> 0x0083 }
            r7 = 0
            if (r5 != r1) goto L_0x005a
            r9 = r9[r6]     // Catch:{ EvaluationException -> 0x0083 }
            org.apache.poi.ss.formula.eval.ValueEval r9 = org.apache.poi.ss.formula.eval.OperandResolver.getSingleValue(r9, r10, r11)     // Catch:{ EvaluationException -> 0x0083 }
            org.apache.poi.ss.formula.eval.MissingArgEval r10 = org.apache.poi.ss.formula.eval.MissingArgEval.instance     // Catch:{ EvaluationException -> 0x0083 }
            if (r9 != r10) goto L_0x0055
            goto L_0x005a
        L_0x0055:
            java.lang.String r9 = org.apache.poi.ss.formula.eval.OperandResolver.coerceValueToString(r9)     // Catch:{ EvaluationException -> 0x0083 }
            r7 = r9
        L_0x005a:
            org.apache.poi.ss.util.CellReference r9 = new org.apache.poi.ss.util.CellReference     // Catch:{ EvaluationException -> 0x0083 }
            int r2 = r2 - r3
            int r4 = r4 - r3
            r9.<init>(r2, r4, r8, r0)     // Catch:{ EvaluationException -> 0x0083 }
            java.lang.StringBuilder r8 = new java.lang.StringBuilder     // Catch:{ EvaluationException -> 0x0083 }
            r10 = 32
            r8.<init>(r10)     // Catch:{ EvaluationException -> 0x0083 }
            if (r7 == 0) goto L_0x0072
            org.apache.poi.ss.formula.SheetNameFormatter.appendFormat(r8, r7)     // Catch:{ EvaluationException -> 0x0083 }
            r10 = 33
            r8.append(r10)     // Catch:{ EvaluationException -> 0x0083 }
        L_0x0072:
            java.lang.String r9 = r9.formatAsString()     // Catch:{ EvaluationException -> 0x0083 }
            r8.append(r9)     // Catch:{ EvaluationException -> 0x0083 }
            org.apache.poi.ss.formula.eval.StringEval r9 = new org.apache.poi.ss.formula.eval.StringEval     // Catch:{ EvaluationException -> 0x0083 }
            java.lang.String r8 = r8.toString()     // Catch:{ EvaluationException -> 0x0083 }
            r9.<init>((java.lang.String) r8)     // Catch:{ EvaluationException -> 0x0083 }
            return r9
        L_0x0083:
            r8 = move-exception
            org.apache.poi.ss.formula.eval.ErrorEval r8 = r8.getErrorEval()
            return r8
        L_0x0089:
            org.apache.poi.ss.formula.eval.ErrorEval r8 = org.apache.poi.ss.formula.eval.ErrorEval.VALUE_INVALID
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.ss.formula.functions.Address.evaluate(org.apache.poi.ss.formula.eval.ValueEval[], int, int):org.apache.poi.ss.formula.eval.ValueEval");
    }
}
