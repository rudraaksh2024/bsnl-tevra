package org.apache.poi.ss.formula.functions;

import java.lang.reflect.Array;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.LUDecomposition;
import org.apache.commons.math3.linear.MatrixUtils;
import org.apache.poi.ss.formula.CacheAreaEval;
import org.apache.poi.ss.formula.eval.AreaEval;
import org.apache.poi.ss.formula.eval.ErrorEval;
import org.apache.poi.ss.formula.eval.EvaluationException;
import org.apache.poi.ss.formula.eval.NumberEval;
import org.apache.poi.ss.formula.eval.OperandResolver;
import org.apache.poi.ss.formula.eval.ValueEval;
import org.apache.poi.ss.formula.functions.MultiOperandNumericFunction;

public abstract class MatrixFunction implements Function {
    public static final Function MDETERM = new Mdeterm();
    public static final Function MINVERSE = new OneArrayArg() {
        private final MutableValueCollector instance = new MutableValueCollector(false, false);

        /* access modifiers changed from: protected */
        public double[] collectValues(ValueEval valueEval) throws EvaluationException {
            double[] collectValues = this.instance.collectValues(valueEval);
            if (!(valueEval instanceof AreaEval) || collectValues.length != 1) {
                return collectValues;
            }
            throw new EvaluationException(ErrorEval.VALUE_INVALID);
        }

        /* access modifiers changed from: protected */
        public double[][] evaluate(double[][] dArr) throws EvaluationException {
            if (dArr.length == dArr[0].length) {
                return MatrixUtils.inverse(new Array2DRowRealMatrix(dArr)).getData();
            }
            throw new EvaluationException(ErrorEval.VALUE_INVALID);
        }
    };
    public static final Function MMULT = new TwoArrayArg() {
        private final MutableValueCollector instance = new MutableValueCollector(false, false);

        /* access modifiers changed from: protected */
        public double[] collectValues(ValueEval valueEval) throws EvaluationException {
            double[] collectValues = this.instance.collectValues(valueEval);
            if (!(valueEval instanceof AreaEval) || collectValues.length != 1) {
                return collectValues;
            }
            throw new EvaluationException(ErrorEval.VALUE_INVALID);
        }

        /* access modifiers changed from: protected */
        public double[][] evaluate(double[][] dArr, double[][] dArr2) throws EvaluationException {
            Array2DRowRealMatrix array2DRowRealMatrix = new Array2DRowRealMatrix(dArr);
            Array2DRowRealMatrix array2DRowRealMatrix2 = new Array2DRowRealMatrix(dArr2);
            try {
                MatrixUtils.checkMultiplicationCompatible(array2DRowRealMatrix, array2DRowRealMatrix2);
                return array2DRowRealMatrix.multiply(array2DRowRealMatrix2).getData();
            } catch (DimensionMismatchException unused) {
                throw new EvaluationException(ErrorEval.VALUE_INVALID);
            }
        }
    };
    public static final Function TRANSPOSE = new OneArrayArg() {
        private final MutableValueCollector instance = new MutableValueCollector(false, true);

        /* access modifiers changed from: protected */
        public double[] collectValues(ValueEval valueEval) throws EvaluationException {
            return this.instance.collectValues(valueEval);
        }

        /* access modifiers changed from: protected */
        public double[][] evaluate(double[][] dArr) throws EvaluationException {
            return new Array2DRowRealMatrix(dArr).transpose().getData();
        }
    };

    public static void checkValues(double[] dArr) throws EvaluationException {
        for (double d : dArr) {
            if (Double.isNaN(d) || Double.isInfinite(d)) {
                throw new EvaluationException(ErrorEval.NUM_ERROR);
            }
        }
    }

    /* access modifiers changed from: protected */
    public final double singleOperandEvaluate(ValueEval valueEval, int i, int i2) throws EvaluationException {
        return OperandResolver.coerceValueToDouble(OperandResolver.getSingleValue(valueEval, i, i2));
    }

    /* access modifiers changed from: private */
    public static double[][] fillDoubleArray(double[] dArr, int i, int i2) throws EvaluationException {
        if (i < 1 || i2 < 1 || dArr.length < 1) {
            throw new EvaluationException(ErrorEval.VALUE_INVALID);
        }
        int[] iArr = new int[2];
        iArr[1] = i2;
        iArr[0] = i;
        double[][] dArr2 = (double[][]) Array.newInstance(Double.TYPE, iArr);
        int i3 = 0;
        int i4 = 0;
        for (double d : dArr) {
            if (i3 < dArr2.length) {
                if (i4 == dArr2[0].length) {
                    i3++;
                    i4 = 0;
                }
                if (i3 < dArr2.length) {
                    dArr2[i3][i4] = d;
                    i4++;
                }
            }
        }
        return dArr2;
    }

    /* access modifiers changed from: private */
    public static double[] extractDoubleArray(double[][] dArr) throws EvaluationException {
        if (dArr != null && dArr.length >= 1) {
            double[] dArr2 = dArr[0];
            if (dArr2.length >= 1) {
                double[] dArr3 = new double[(dArr.length * dArr2.length)];
                int i = 0;
                for (double[] dArr4 : dArr) {
                    int i2 = 0;
                    while (i2 < dArr[0].length) {
                        dArr3[i] = dArr4[i2];
                        i2++;
                        i++;
                    }
                }
                return dArr3;
            }
        }
        throw new EvaluationException(ErrorEval.VALUE_INVALID);
    }

    public static abstract class OneArrayArg extends Fixed1ArgFunction {
        /* access modifiers changed from: protected */
        public abstract double[] collectValues(ValueEval valueEval) throws EvaluationException;

        /* access modifiers changed from: protected */
        public abstract double[][] evaluate(double[][] dArr) throws EvaluationException;

        protected OneArrayArg() {
        }

        public ValueEval evaluate(int i, int i2, ValueEval valueEval) {
            if (valueEval instanceof AreaEval) {
                try {
                    double[][] evaluate = evaluate(MatrixFunction.fillDoubleArray(collectValues(valueEval), ((AreaEval) valueEval).getHeight(), ((AreaEval) valueEval).getWidth()));
                    int length = evaluate[0].length;
                    int length2 = evaluate.length;
                    double[] access$100 = MatrixFunction.extractDoubleArray(evaluate);
                    MatrixFunction.checkValues(access$100);
                    ValueEval[] valueEvalArr = new ValueEval[access$100.length];
                    for (int i3 = 0; i3 < access$100.length; i3++) {
                        valueEvalArr[i3] = new NumberEval(access$100[i3]);
                    }
                    if (access$100.length == 1) {
                        return valueEvalArr[0];
                    }
                    AreaEval areaEval = (AreaEval) valueEval;
                    return new CacheAreaEval(areaEval.getFirstRow(), areaEval.getFirstColumn(), (areaEval.getFirstRow() + length2) - 1, (areaEval.getFirstColumn() + length) - 1, valueEvalArr);
                } catch (EvaluationException e) {
                    return e.getErrorEval();
                }
            } else {
                try {
                    double[][] evaluate2 = evaluate(new double[][]{new double[]{NumericFunction.singleOperandEvaluate(valueEval, i, i2)}});
                    NumericFunction.checkValue(evaluate2[0][0]);
                    return new NumberEval(evaluate2[0][0]);
                } catch (EvaluationException e2) {
                    return e2.getErrorEval();
                }
            }
        }
    }

    public static abstract class TwoArrayArg extends Fixed2ArgFunction {
        /* access modifiers changed from: protected */
        public abstract double[] collectValues(ValueEval valueEval) throws EvaluationException;

        /* access modifiers changed from: protected */
        public abstract double[][] evaluate(double[][] dArr, double[][] dArr2) throws EvaluationException;

        protected TwoArrayArg() {
        }

        /* JADX WARNING: Code restructure failed: missing block: B:17:0x0049, code lost:
            r9 = move-exception;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:20:0x004e, code lost:
            return r9.getErrorEval();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:40:0x00a9, code lost:
            r9 = move-exception;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:42:0x00ae, code lost:
            return r9.getErrorEval();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:45:0x00b1, code lost:
            return org.apache.poi.ss.formula.eval.ErrorEval.VALUE_INVALID;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:46:0x00b2, code lost:
            r9 = move-exception;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:48:0x00b7, code lost:
            return r9.getErrorEval();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:6:0x001d, code lost:
            r9 = move-exception;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:9:0x0022, code lost:
            return r9.getErrorEval();
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Removed duplicated region for block: B:44:? A[ExcHandler: IllegalArgumentException (unused java.lang.IllegalArgumentException), SYNTHETIC, Splitter:B:0:0x0000] */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public org.apache.poi.ss.formula.eval.ValueEval evaluate(int r10, int r11, org.apache.poi.ss.formula.eval.ValueEval r12, org.apache.poi.ss.formula.eval.ValueEval r13) {
            /*
                r9 = this;
                boolean r0 = r12 instanceof org.apache.poi.ss.formula.eval.AreaEval     // Catch:{ EvaluationException -> 0x00b2, IllegalArgumentException -> 0x00af }
                r1 = 0
                r2 = 1
                if (r0 == 0) goto L_0x0023
                double[] r0 = r9.collectValues(r12)     // Catch:{ EvaluationException -> 0x001d, IllegalArgumentException -> 0x00af }
                r3 = r12
                org.apache.poi.ss.formula.eval.AreaEval r3 = (org.apache.poi.ss.formula.eval.AreaEval) r3     // Catch:{ EvaluationException -> 0x001d, IllegalArgumentException -> 0x00af }
                int r3 = r3.getHeight()     // Catch:{ EvaluationException -> 0x001d, IllegalArgumentException -> 0x00af }
                r4 = r12
                org.apache.poi.ss.formula.eval.AreaEval r4 = (org.apache.poi.ss.formula.eval.AreaEval) r4     // Catch:{ EvaluationException -> 0x001d, IllegalArgumentException -> 0x00af }
                int r4 = r4.getWidth()     // Catch:{ EvaluationException -> 0x001d, IllegalArgumentException -> 0x00af }
                double[][] r0 = org.apache.poi.ss.formula.functions.MatrixFunction.fillDoubleArray(r0, r3, r4)     // Catch:{ EvaluationException -> 0x001d, IllegalArgumentException -> 0x00af }
                goto L_0x002f
            L_0x001d:
                r9 = move-exception
                org.apache.poi.ss.formula.eval.ErrorEval r9 = r9.getErrorEval()     // Catch:{ EvaluationException -> 0x00b2, IllegalArgumentException -> 0x00af }
                return r9
            L_0x0023:
                double r3 = org.apache.poi.ss.formula.functions.NumericFunction.singleOperandEvaluate(r12, r10, r11)     // Catch:{ EvaluationException -> 0x00a9, IllegalArgumentException -> 0x00af }
                double[][] r0 = new double[r2][]     // Catch:{ EvaluationException -> 0x00a9, IllegalArgumentException -> 0x00af }
                double[] r5 = new double[r2]     // Catch:{ EvaluationException -> 0x00a9, IllegalArgumentException -> 0x00af }
                r5[r1] = r3     // Catch:{ EvaluationException -> 0x00a9, IllegalArgumentException -> 0x00af }
                r0[r1] = r5     // Catch:{ EvaluationException -> 0x00a9, IllegalArgumentException -> 0x00af }
            L_0x002f:
                boolean r3 = r13 instanceof org.apache.poi.ss.formula.eval.AreaEval     // Catch:{ EvaluationException -> 0x00b2, IllegalArgumentException -> 0x00af }
                if (r3 == 0) goto L_0x004f
                double[] r10 = r9.collectValues(r13)     // Catch:{ EvaluationException -> 0x0049, IllegalArgumentException -> 0x00af }
                r11 = r13
                org.apache.poi.ss.formula.eval.AreaEval r11 = (org.apache.poi.ss.formula.eval.AreaEval) r11     // Catch:{ EvaluationException -> 0x0049, IllegalArgumentException -> 0x00af }
                int r11 = r11.getHeight()     // Catch:{ EvaluationException -> 0x0049, IllegalArgumentException -> 0x00af }
                org.apache.poi.ss.formula.eval.AreaEval r13 = (org.apache.poi.ss.formula.eval.AreaEval) r13     // Catch:{ EvaluationException -> 0x0049, IllegalArgumentException -> 0x00af }
                int r13 = r13.getWidth()     // Catch:{ EvaluationException -> 0x0049, IllegalArgumentException -> 0x00af }
                double[][] r10 = org.apache.poi.ss.formula.functions.MatrixFunction.fillDoubleArray(r10, r11, r13)     // Catch:{ EvaluationException -> 0x0049, IllegalArgumentException -> 0x00af }
                goto L_0x005c
            L_0x0049:
                r9 = move-exception
                org.apache.poi.ss.formula.eval.ErrorEval r9 = r9.getErrorEval()     // Catch:{ EvaluationException -> 0x00b2, IllegalArgumentException -> 0x00af }
                return r9
            L_0x004f:
                double r10 = org.apache.poi.ss.formula.functions.NumericFunction.singleOperandEvaluate(r13, r10, r11)     // Catch:{ EvaluationException -> 0x00a3, IllegalArgumentException -> 0x00af }
                double[][] r13 = new double[r2][]     // Catch:{ EvaluationException -> 0x00a3, IllegalArgumentException -> 0x00af }
                double[] r3 = new double[r2]     // Catch:{ EvaluationException -> 0x00a3, IllegalArgumentException -> 0x00af }
                r3[r1] = r10     // Catch:{ EvaluationException -> 0x00a3, IllegalArgumentException -> 0x00af }
                r13[r1] = r3     // Catch:{ EvaluationException -> 0x00a3, IllegalArgumentException -> 0x00af }
                r10 = r13
            L_0x005c:
                double[][] r9 = r9.evaluate(r0, r10)     // Catch:{ EvaluationException -> 0x00b2, IllegalArgumentException -> 0x00af }
                r10 = r9[r1]     // Catch:{ EvaluationException -> 0x00b2, IllegalArgumentException -> 0x00af }
                int r10 = r10.length     // Catch:{ EvaluationException -> 0x00b2, IllegalArgumentException -> 0x00af }
                int r11 = r9.length     // Catch:{ EvaluationException -> 0x00b2, IllegalArgumentException -> 0x00af }
                double[] r9 = org.apache.poi.ss.formula.functions.MatrixFunction.extractDoubleArray(r9)     // Catch:{ EvaluationException -> 0x00b2, IllegalArgumentException -> 0x00af }
                org.apache.poi.ss.formula.functions.MatrixFunction.checkValues(r9)     // Catch:{ EvaluationException -> 0x00b2, IllegalArgumentException -> 0x00af }
                int r13 = r9.length
                org.apache.poi.ss.formula.eval.ValueEval[] r8 = new org.apache.poi.ss.formula.eval.ValueEval[r13]
                r13 = r1
            L_0x006f:
                int r0 = r9.length
                if (r13 >= r0) goto L_0x007e
                org.apache.poi.ss.formula.eval.NumberEval r0 = new org.apache.poi.ss.formula.eval.NumberEval
                r3 = r9[r13]
                r0.<init>((double) r3)
                r8[r13] = r0
                int r13 = r13 + 1
                goto L_0x006f
            L_0x007e:
                int r9 = r9.length
                if (r9 != r2) goto L_0x0084
                r9 = r8[r1]
                return r9
            L_0x0084:
                org.apache.poi.ss.formula.CacheAreaEval r9 = new org.apache.poi.ss.formula.CacheAreaEval
                org.apache.poi.ss.formula.eval.AreaEval r12 = (org.apache.poi.ss.formula.eval.AreaEval) r12
                int r4 = r12.getFirstRow()
                int r5 = r12.getFirstColumn()
                int r13 = r12.getFirstRow()
                int r13 = r13 + r11
                int r6 = r13 + -1
                int r11 = r12.getFirstColumn()
                int r11 = r11 + r10
                int r7 = r11 + -1
                r3 = r9
                r3.<init>(r4, r5, r6, r7, r8)
                return r9
            L_0x00a3:
                r9 = move-exception
                org.apache.poi.ss.formula.eval.ErrorEval r9 = r9.getErrorEval()     // Catch:{ EvaluationException -> 0x00b2, IllegalArgumentException -> 0x00af }
                return r9
            L_0x00a9:
                r9 = move-exception
                org.apache.poi.ss.formula.eval.ErrorEval r9 = r9.getErrorEval()     // Catch:{ EvaluationException -> 0x00b2, IllegalArgumentException -> 0x00af }
                return r9
            L_0x00af:
                org.apache.poi.ss.formula.eval.ErrorEval r9 = org.apache.poi.ss.formula.eval.ErrorEval.VALUE_INVALID
                return r9
            L_0x00b2:
                r9 = move-exception
                org.apache.poi.ss.formula.eval.ErrorEval r9 = r9.getErrorEval()
                return r9
            */
            throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.ss.formula.functions.MatrixFunction.TwoArrayArg.evaluate(int, int, org.apache.poi.ss.formula.eval.ValueEval, org.apache.poi.ss.formula.eval.ValueEval):org.apache.poi.ss.formula.eval.ValueEval");
        }
    }

    public static final class MutableValueCollector extends MultiOperandNumericFunction {
        public MutableValueCollector(boolean z, boolean z2) {
            super(z, z2);
        }

        public double[] collectValues(ValueEval... valueEvalArr) throws EvaluationException {
            return getNumberArray(valueEvalArr);
        }

        /* access modifiers changed from: protected */
        public double evaluate(double[] dArr) {
            throw new IllegalStateException("should not be called");
        }
    }

    private static class Mdeterm extends OneArrayArg {
        private final MutableValueCollector instance;

        public Mdeterm() {
            MutableValueCollector mutableValueCollector = new MutableValueCollector(false, false);
            this.instance = mutableValueCollector;
            mutableValueCollector.setBlankEvalPolicy(MultiOperandNumericFunction.Policy.ERROR);
        }

        /* access modifiers changed from: protected */
        public double[] collectValues(ValueEval valueEval) throws EvaluationException {
            double[] collectValues = this.instance.collectValues(valueEval);
            if (!(valueEval instanceof AreaEval) || collectValues.length != 1) {
                return this.instance.collectValues(valueEval);
            }
            throw new EvaluationException(ErrorEval.VALUE_INVALID);
        }

        /* access modifiers changed from: protected */
        public double[][] evaluate(double[][] dArr) throws EvaluationException {
            if (dArr.length == dArr[0].length) {
                double[][] dArr2 = (double[][]) Array.newInstance(Double.TYPE, new int[]{1, 1});
                dArr2[0][0] = new LUDecomposition(new Array2DRowRealMatrix(dArr)).getDeterminant();
                return dArr2;
            }
            throw new EvaluationException(ErrorEval.VALUE_INVALID);
        }
    }
}
