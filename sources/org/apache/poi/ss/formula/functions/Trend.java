package org.apache.poi.ss.formula.functions;

import java.lang.reflect.Array;
import org.apache.poi.ss.formula.CacheAreaEval;
import org.apache.poi.ss.formula.eval.AreaEval;
import org.apache.poi.ss.formula.eval.ErrorEval;
import org.apache.poi.ss.formula.eval.EvaluationException;
import org.apache.poi.ss.formula.eval.MissingArgEval;
import org.apache.poi.ss.formula.eval.NumberEval;
import org.apache.poi.ss.formula.eval.NumericValueEval;
import org.apache.poi.ss.formula.eval.RefEval;
import org.apache.poi.ss.formula.eval.ValueEval;

public final class Trend implements Function {

    private static final class TrendResults {
        /* access modifiers changed from: private */
        public final int resultHeight;
        /* access modifiers changed from: private */
        public final int resultWidth;
        /* access modifiers changed from: private */
        public final double[] vals;

        public TrendResults(double[] dArr, int i, int i2) {
            this.vals = dArr;
            this.resultWidth = i;
            this.resultHeight = i2;
        }
    }

    public ValueEval evaluate(ValueEval[] valueEvalArr, int i, int i2) {
        if (valueEvalArr.length < 1 || valueEvalArr.length > 4) {
            return ErrorEval.VALUE_INVALID;
        }
        try {
            TrendResults newY = getNewY(valueEvalArr);
            ValueEval[] valueEvalArr2 = new ValueEval[newY.vals.length];
            for (int i3 = 0; i3 < newY.vals.length; i3++) {
                valueEvalArr2[i3] = new NumberEval(newY.vals[i3]);
            }
            if (newY.vals.length == 1) {
                return valueEvalArr2[0];
            }
            return new CacheAreaEval(i, i2, (newY.resultHeight + i) - 1, (newY.resultWidth + i2) - 1, valueEvalArr2);
        } catch (EvaluationException e) {
            return e.getErrorEval();
        }
    }

    private static double[][] evalToArray(ValueEval valueEval) throws EvaluationException {
        if (valueEval instanceof MissingArgEval) {
            return (double[][]) Array.newInstance(Double.TYPE, new int[]{0, 0});
        }
        if (valueEval instanceof RefEval) {
            RefEval refEval = (RefEval) valueEval;
            if (refEval.getNumberOfSheets() <= 1) {
                valueEval = refEval.getInnerValueEval(refEval.getFirstSheetIndex());
            } else {
                throw new EvaluationException(ErrorEval.VALUE_INVALID);
            }
        }
        if (valueEval == null) {
            throw new RuntimeException("Parameter may not be null.");
        } else if (valueEval instanceof AreaEval) {
            AreaEval areaEval = (AreaEval) valueEval;
            int width = areaEval.getWidth();
            int height = areaEval.getHeight();
            int[] iArr = new int[2];
            iArr[1] = width;
            iArr[0] = height;
            double[][] dArr = (double[][]) Array.newInstance(Double.TYPE, iArr);
            for (int i = 0; i < height; i++) {
                int i2 = 0;
                while (i2 < width) {
                    ValueEval relativeValue = areaEval.getRelativeValue(i, i2);
                    if (relativeValue instanceof NumericValueEval) {
                        dArr[i][i2] = ((NumericValueEval) relativeValue).getNumberValue();
                        i2++;
                    } else {
                        throw new EvaluationException(ErrorEval.VALUE_INVALID);
                    }
                }
            }
            return dArr;
        } else if (valueEval instanceof NumericValueEval) {
            double[][] dArr2 = (double[][]) Array.newInstance(Double.TYPE, new int[]{1, 1});
            dArr2[0][0] = ((NumericValueEval) valueEval).getNumberValue();
            return dArr2;
        } else {
            throw new EvaluationException(ErrorEval.VALUE_INVALID);
        }
    }

    private static double[][] getDefaultArrayOneD(int i) {
        int[] iArr = new int[2];
        iArr[1] = 1;
        iArr[0] = i;
        double[][] dArr = (double[][]) Array.newInstance(Double.TYPE, iArr);
        for (int i2 = 0; i2 < i; i2++) {
            dArr[i2][0] = ((double) i2) + 1.0d;
        }
        return dArr;
    }

    private static double[] flattenArray(double[][] dArr) {
        if (dArr.length < 1) {
            return new double[0];
        }
        double[] dArr2 = new double[(dArr.length * dArr[0].length)];
        for (int i = 0; i < dArr.length; i++) {
            double[] dArr3 = dArr[i];
            double[] dArr4 = dArr[0];
            System.arraycopy(dArr3, 0, dArr2, dArr4.length * i, dArr4.length);
        }
        return dArr2;
    }

    private static double[][] flattenArrayToRow(double[][] dArr) {
        if (dArr.length < 1) {
            return (double[][]) Array.newInstance(Double.TYPE, new int[]{0, 0});
        }
        int[] iArr = new int[2];
        iArr[1] = 1;
        iArr[0] = dArr.length * dArr[0].length;
        double[][] dArr2 = (double[][]) Array.newInstance(Double.TYPE, iArr);
        for (int i = 0; i < dArr.length; i++) {
            int i2 = 0;
            while (true) {
                double[] dArr3 = dArr[0];
                if (i2 >= dArr3.length) {
                    break;
                }
                dArr2[(dArr3.length * i) + i2][0] = dArr[i][i2];
                i2++;
            }
        }
        return dArr2;
    }

    private static double[][] switchRowsColumns(double[][] dArr) {
        int length = dArr[0].length;
        int[] iArr = new int[2];
        iArr[1] = dArr.length;
        iArr[0] = length;
        double[][] dArr2 = (double[][]) Array.newInstance(Double.TYPE, iArr);
        for (int i = 0; i < dArr.length; i++) {
            for (int i2 = 0; i2 < dArr[0].length; i2++) {
                dArr2[i2][i] = dArr[i][i2];
            }
        }
        return dArr2;
    }

    private static boolean isAllColumnsSame(double[][] dArr) {
        if (dArr.length == 0) {
            return false;
        }
        int length = dArr[0].length;
        boolean[] zArr = new boolean[length];
        for (int i = 0; i < dArr[0].length; i++) {
            double d = Double.NaN;
            int i2 = 0;
            while (true) {
                if (i2 >= dArr.length) {
                    break;
                }
                double d2 = dArr[i2][i];
                if (i2 > 0 && d2 != d) {
                    zArr[i] = true;
                    break;
                }
                i2++;
                d = d2;
            }
        }
        for (int i3 = 0; i3 < length; i3++) {
            if (zArr[i3]) {
                return false;
            }
        }
        return true;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v15, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v8, resolved type: double[][]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v17, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v6, resolved type: double[][]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v20, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v8, resolved type: double[][]} */
    /* JADX WARNING: Code restructure failed: missing block: B:51:0x00f0, code lost:
        if (r7.length < 1) goto L_0x010b;
     */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:126:0x01de  */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x0094  */
    /* JADX WARNING: Removed duplicated region for block: B:69:0x0116  */
    /* JADX WARNING: Removed duplicated region for block: B:70:0x011c  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static org.apache.poi.ss.formula.functions.Trend.TrendResults getNewY(org.apache.poi.ss.formula.eval.ValueEval[] r19) throws org.apache.poi.ss.formula.eval.EvaluationException {
        /*
            r0 = r19
            int r1 = r0.length
            r2 = 2
            r3 = 1
            r4 = 0
            if (r1 == r3) goto L_0x006e
            if (r1 == r2) goto L_0x0053
            r5 = 3
            if (r1 == r5) goto L_0x0040
            r6 = 4
            if (r1 != r6) goto L_0x0038
            r1 = r0[r4]
            double[][] r1 = evalToArray(r1)
            r6 = r0[r3]
            double[][] r6 = evalToArray(r6)
            r7 = r0[r2]
            double[][] r7 = evalToArray(r7)
            r0 = r0[r5]
            boolean r5 = r0 instanceof org.apache.poi.ss.formula.eval.BoolEval
            if (r5 == 0) goto L_0x0030
            org.apache.poi.ss.formula.eval.BoolEval r0 = (org.apache.poi.ss.formula.eval.BoolEval) r0
            boolean r0 = r0.getBooleanValue()
            r0 = r0 ^ r3
            goto L_0x0091
        L_0x0030:
            org.apache.poi.ss.formula.eval.EvaluationException r0 = new org.apache.poi.ss.formula.eval.EvaluationException
            org.apache.poi.ss.formula.eval.ErrorEval r1 = org.apache.poi.ss.formula.eval.ErrorEval.VALUE_INVALID
            r0.<init>(r1)
            throw r0
        L_0x0038:
            org.apache.poi.ss.formula.eval.EvaluationException r0 = new org.apache.poi.ss.formula.eval.EvaluationException
            org.apache.poi.ss.formula.eval.ErrorEval r1 = org.apache.poi.ss.formula.eval.ErrorEval.VALUE_INVALID
            r0.<init>(r1)
            throw r0
        L_0x0040:
            r1 = r0[r4]
            double[][] r1 = evalToArray(r1)
            r5 = r0[r3]
            double[][] r6 = evalToArray(r5)
            r0 = r0[r2]
            double[][] r7 = evalToArray(r0)
            goto L_0x0090
        L_0x0053:
            r1 = r0[r4]
            double[][] r1 = evalToArray(r1)
            r0 = r0[r3]
            double[][] r6 = evalToArray(r0)
            int[] r0 = new int[r2]
            r0 = {0, 0} // fill-array
            java.lang.Class r5 = java.lang.Double.TYPE
            java.lang.Object r0 = java.lang.reflect.Array.newInstance(r5, r0)
            r7 = r0
            double[][] r7 = (double[][]) r7
            goto L_0x0090
        L_0x006e:
            r0 = r0[r4]
            double[][] r1 = evalToArray(r0)
            int[] r0 = new int[r2]
            r0 = {0, 0} // fill-array
            java.lang.Class r5 = java.lang.Double.TYPE
            java.lang.Object r0 = java.lang.reflect.Array.newInstance(r5, r0)
            r6 = r0
            double[][] r6 = (double[][]) r6
            int[] r0 = new int[r2]
            r0 = {0, 0} // fill-array
            java.lang.Class r5 = java.lang.Double.TYPE
            java.lang.Object r0 = java.lang.reflect.Array.newInstance(r5, r0)
            r7 = r0
            double[][] r7 = (double[][]) r7
        L_0x0090:
            r0 = r4
        L_0x0091:
            int r5 = r1.length
            if (r5 < r3) goto L_0x01de
            double[] r5 = flattenArray(r1)
            int r8 = r7.length
            if (r8 <= 0) goto L_0x009d
            r2 = r7
            goto L_0x00aa
        L_0x009d:
            int[] r2 = new int[r2]
            r2 = {1, 1} // fill-array
            java.lang.Class r8 = java.lang.Double.TYPE
            java.lang.Object r2 = java.lang.reflect.Array.newInstance(r8, r2)
            double[][] r2 = (double[][]) r2
        L_0x00aa:
            int r8 = r5.length
            java.lang.String r9 = "Sample size too small"
            if (r8 == r3) goto L_0x01d8
            int r8 = r1.length
            if (r8 == r3) goto L_0x00e7
            r8 = r1[r4]
            int r8 = r8.length
            if (r8 != r3) goto L_0x00b8
            goto L_0x00e7
        L_0x00b8:
            int r8 = r6.length
            if (r8 >= r3) goto L_0x00c5
            int r8 = r5.length
            double[][] r8 = getDefaultArrayOneD(r8)
            int r10 = r7.length
            if (r10 >= r3) goto L_0x00cd
            r2 = r1
            goto L_0x00cd
        L_0x00c5:
            double[][] r8 = flattenArrayToRow(r6)
            int r10 = r7.length
            if (r10 >= r3) goto L_0x00cd
            r2 = r6
        L_0x00cd:
            int r10 = r7.length
            if (r10 <= 0) goto L_0x00d5
            double[][] r10 = flattenArrayToRow(r7)
            goto L_0x00d6
        L_0x00d5:
            r10 = r7
        L_0x00d6:
            int r11 = r5.length
            int r12 = r8.length
            if (r11 != r12) goto L_0x00df
            int r1 = r1.length
            int r11 = r6.length
            if (r1 != r11) goto L_0x00df
            goto L_0x011e
        L_0x00df:
            org.apache.poi.ss.formula.eval.EvaluationException r0 = new org.apache.poi.ss.formula.eval.EvaluationException
            org.apache.poi.ss.formula.eval.ErrorEval r1 = org.apache.poi.ss.formula.eval.ErrorEval.REF_INVALID
            r0.<init>(r1)
            throw r0
        L_0x00e7:
            int r8 = r6.length
            if (r8 >= r3) goto L_0x00f5
            int r8 = r5.length
            double[][] r8 = getDefaultArrayOneD(r8)
            int r10 = r7.length
            if (r10 >= r3) goto L_0x00f3
            goto L_0x010b
        L_0x00f3:
            r1 = r2
            goto L_0x010b
        L_0x00f5:
            r8 = r6[r4]
            int r8 = r8.length
            if (r8 <= r3) goto L_0x0102
            int r1 = r1.length
            if (r1 != r3) goto L_0x0102
            double[][] r1 = switchRowsColumns(r6)
            goto L_0x0103
        L_0x0102:
            r1 = r6
        L_0x0103:
            int r8 = r7.length
            if (r8 >= r3) goto L_0x0109
            r8 = r1
            r1 = r6
            goto L_0x010b
        L_0x0109:
            r8 = r1
            goto L_0x00f3
        L_0x010b:
            int r2 = r7.length
            if (r2 <= 0) goto L_0x011c
            int r2 = r8.length
            if (r2 == r3) goto L_0x0116
            r2 = r8[r4]
            int r2 = r2.length
            if (r2 != r3) goto L_0x011c
        L_0x0116:
            double[][] r10 = flattenArrayToRow(r7)
            r2 = r1
            goto L_0x011e
        L_0x011c:
            r2 = r1
            r10 = r7
        L_0x011e:
            int r1 = r7.length
            if (r1 >= r3) goto L_0x0123
            r10 = r8
            goto L_0x0137
        L_0x0123:
            int r1 = r7.length
            if (r1 != r3) goto L_0x0137
            r1 = r7[r4]
            int r1 = r1.length
            if (r1 <= r3) goto L_0x0137
            int r1 = r6.length
            if (r1 <= r3) goto L_0x0137
            r1 = r6[r4]
            int r1 = r1.length
            if (r1 != r3) goto L_0x0137
            double[][] r10 = switchRowsColumns(r7)
        L_0x0137:
            r1 = r10[r4]
            int r1 = r1.length
            r6 = r8[r4]
            int r7 = r6.length
            if (r1 != r7) goto L_0x01d0
            int r1 = r6.length
            int r6 = r8.length
            if (r1 >= r6) goto L_0x01ca
            int r1 = r2.length
            r2 = r2[r4]
            int r2 = r2.length
            boolean r6 = isAllColumnsSame(r8)
            r11 = 0
            if (r6 == 0) goto L_0x0167
            int r0 = r10.length
            double[] r0 = new double[r0]
            java.util.stream.DoubleStream r3 = java.util.Arrays.stream(r5)
            java.util.OptionalDouble r3 = r3.average()
            double r3 = r3.orElse(r11)
            java.util.Arrays.fill(r0, r3)
            org.apache.poi.ss.formula.functions.Trend$TrendResults r3 = new org.apache.poi.ss.formula.functions.Trend$TrendResults
            r3.<init>(r0, r2, r1)
            return r3
        L_0x0167:
            org.apache.commons.math3.stat.regression.OLSMultipleLinearRegression r6 = new org.apache.commons.math3.stat.regression.OLSMultipleLinearRegression
            r6.<init>()
            if (r0 == 0) goto L_0x0171
            r6.setNoIntercept(r3)
        L_0x0171:
            r6.newSampleData(r5, r8)     // Catch:{ IllegalArgumentException -> 0x01c2 }
            double[] r5 = r6.estimateRegressionParameters()     // Catch:{ SingularMatrixException -> 0x01ba }
            int r6 = r10.length
            double[] r6 = new double[r6]
            r7 = r4
        L_0x017c:
            int r8 = r10.length
            if (r7 >= r8) goto L_0x01b4
            r6[r7] = r11
            if (r0 == 0) goto L_0x0197
            r8 = r4
        L_0x0184:
            int r9 = r5.length
            if (r8 >= r9) goto L_0x01b1
            r13 = r6[r7]
            r15 = r5[r8]
            r9 = r10[r7]
            r17 = r9[r8]
            double r15 = r15 * r17
            double r13 = r13 + r15
            r6[r7] = r13
            int r8 = r8 + 1
            goto L_0x0184
        L_0x0197:
            r8 = r5[r4]
            r6[r7] = r8
            r8 = r3
        L_0x019c:
            int r9 = r5.length
            if (r8 >= r9) goto L_0x01b1
            r13 = r6[r7]
            r15 = r5[r8]
            r9 = r10[r7]
            int r17 = r8 + -1
            r17 = r9[r17]
            double r15 = r15 * r17
            double r13 = r13 + r15
            r6[r7] = r13
            int r8 = r8 + 1
            goto L_0x019c
        L_0x01b1:
            int r7 = r7 + 1
            goto L_0x017c
        L_0x01b4:
            org.apache.poi.ss.formula.functions.Trend$TrendResults r0 = new org.apache.poi.ss.formula.functions.Trend$TrendResults
            r0.<init>(r6, r2, r1)
            return r0
        L_0x01ba:
            org.apache.poi.ss.formula.eval.NotImplementedException r0 = new org.apache.poi.ss.formula.eval.NotImplementedException
            java.lang.String r1 = "Singular matrix in input"
            r0.<init>(r1)
            throw r0
        L_0x01c2:
            org.apache.poi.ss.formula.eval.EvaluationException r0 = new org.apache.poi.ss.formula.eval.EvaluationException
            org.apache.poi.ss.formula.eval.ErrorEval r1 = org.apache.poi.ss.formula.eval.ErrorEval.REF_INVALID
            r0.<init>(r1)
            throw r0
        L_0x01ca:
            org.apache.poi.ss.formula.eval.NotImplementedException r0 = new org.apache.poi.ss.formula.eval.NotImplementedException
            r0.<init>(r9)
            throw r0
        L_0x01d0:
            org.apache.poi.ss.formula.eval.EvaluationException r0 = new org.apache.poi.ss.formula.eval.EvaluationException
            org.apache.poi.ss.formula.eval.ErrorEval r1 = org.apache.poi.ss.formula.eval.ErrorEval.REF_INVALID
            r0.<init>(r1)
            throw r0
        L_0x01d8:
            org.apache.poi.ss.formula.eval.NotImplementedException r0 = new org.apache.poi.ss.formula.eval.NotImplementedException
            r0.<init>(r9)
            throw r0
        L_0x01de:
            org.apache.poi.ss.formula.eval.EvaluationException r0 = new org.apache.poi.ss.formula.eval.EvaluationException
            org.apache.poi.ss.formula.eval.ErrorEval r1 = org.apache.poi.ss.formula.eval.ErrorEval.VALUE_INVALID
            r0.<init>(r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.ss.formula.functions.Trend.getNewY(org.apache.poi.ss.formula.eval.ValueEval[]):org.apache.poi.ss.formula.functions.Trend$TrendResults");
    }
}
