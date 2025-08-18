package org.apache.poi.ss.formula.functions;

import java.util.Arrays;
import org.apache.poi.ss.formula.eval.ErrorEval;
import org.apache.poi.ss.formula.eval.EvaluationException;

public class Mirr extends MultiOperandNumericFunction {
    /* access modifiers changed from: protected */
    public int getMaxNumOperands() {
        return 3;
    }

    public Mirr() {
        super(false, false);
    }

    /* access modifiers changed from: protected */
    public double evaluate(double[] dArr) throws EvaluationException {
        double d = dArr[dArr.length - 1];
        double d2 = dArr[dArr.length - 2];
        double[] copyOf = Arrays.copyOf(dArr, dArr.length - 2);
        int length = copyOf.length;
        boolean z = true;
        for (int i = 0; i < length; i++) {
            z &= copyOf[i] < 0.0d;
        }
        if (z) {
            return -1.0d;
        }
        int length2 = copyOf.length;
        boolean z2 = true;
        for (int i2 = 0; i2 < length2; i2++) {
            z2 &= copyOf[i2] > 0.0d;
        }
        if (!z2) {
            return mirr(copyOf, d, d2);
        }
        throw new EvaluationException(ErrorEval.DIV_ZERO);
    }

    private static double mirr(double[] dArr, double d, double d2) {
        double[] dArr2 = dArr;
        double d3 = 1.0d;
        double length = ((double) dArr2.length) - 1.0d;
        int length2 = dArr2.length;
        double d4 = 0.0d;
        int i = 0;
        double d5 = 0.0d;
        int i2 = 0;
        int i3 = 0;
        while (i2 < length2) {
            double d6 = dArr2[i2];
            if (d6 < d4) {
                d5 += d6 / Math.pow((d + d3) + d2, (double) i3);
                i3++;
            }
            i2++;
            d3 = 1.0d;
            d4 = 0.0d;
        }
        int length3 = dArr2.length;
        double d7 = 0.0d;
        while (i < length3) {
            double d8 = dArr2[i];
            double d9 = d5;
            if (d8 > 0.0d) {
                d7 += d8 * Math.pow(d + 1.0d, length - ((double) i3));
                i3++;
            }
            i++;
            d5 = d9;
        }
        double d10 = d5;
        if (d7 == 0.0d || d10 == 0.0d) {
            return 0.0d;
        }
        return Math.pow((-d7) / d10, 1.0d / length) - 1.0d;
    }
}
