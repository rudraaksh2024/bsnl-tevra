package org.apache.commons.math3.transform;

import java.io.Serializable;
import org.apache.commons.math3.analysis.FunctionUtils;
import org.apache.commons.math3.analysis.UnivariateFunction;
import org.apache.commons.math3.exception.MathIllegalArgumentException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.util.ArithmeticUtils;

public class FastHadamardTransformer implements RealTransformer, Serializable {
    static final long serialVersionUID = 20120211;

    public double[] transform(double[] dArr, TransformType transformType) {
        if (transformType == TransformType.FORWARD) {
            return fht(dArr);
        }
        return TransformUtils.scaleArray(fht(dArr), 1.0d / ((double) dArr.length));
    }

    public double[] transform(UnivariateFunction univariateFunction, double d, double d2, int i, TransformType transformType) {
        return transform(FunctionUtils.sample(univariateFunction, d, d2, i), transformType);
    }

    public int[] transform(int[] iArr) {
        return fht(iArr);
    }

    /* access modifiers changed from: protected */
    public double[] fht(double[] dArr) throws MathIllegalArgumentException {
        int length = dArr.length;
        int i = length / 2;
        if (ArithmeticUtils.isPowerOfTwo((long) length)) {
            double[] dArr2 = new double[length];
            double[] dArr3 = (double[]) dArr.clone();
            int i2 = 1;
            while (true) {
                double[] dArr4 = dArr2;
                dArr2 = dArr3;
                dArr3 = dArr4;
                if (i2 >= length) {
                    return dArr2;
                }
                for (int i3 = 0; i3 < i; i3++) {
                    int i4 = i3 * 2;
                    dArr3[i3] = dArr2[i4] + dArr2[i4 + 1];
                }
                for (int i5 = i; i5 < length; i5++) {
                    int i6 = (i5 * 2) - length;
                    dArr3[i5] = dArr2[i6] - dArr2[i6 + 1];
                }
                i2 <<= 1;
            }
        } else {
            throw new MathIllegalArgumentException(LocalizedFormats.NOT_POWER_OF_TWO, Integer.valueOf(length));
        }
    }

    /* access modifiers changed from: protected */
    public int[] fht(int[] iArr) throws MathIllegalArgumentException {
        int length = iArr.length;
        int i = length / 2;
        if (ArithmeticUtils.isPowerOfTwo((long) length)) {
            int[] iArr2 = new int[length];
            int[] iArr3 = (int[]) iArr.clone();
            int i2 = 1;
            while (true) {
                int[] iArr4 = iArr2;
                iArr2 = iArr3;
                iArr3 = iArr4;
                if (i2 >= length) {
                    return iArr2;
                }
                for (int i3 = 0; i3 < i; i3++) {
                    int i4 = i3 * 2;
                    iArr3[i3] = iArr2[i4] + iArr2[i4 + 1];
                }
                for (int i5 = i; i5 < length; i5++) {
                    int i6 = (i5 * 2) - length;
                    iArr3[i5] = iArr2[i6] - iArr2[i6 + 1];
                }
                i2 <<= 1;
            }
        } else {
            throw new MathIllegalArgumentException(LocalizedFormats.NOT_POWER_OF_TWO, Integer.valueOf(length));
        }
    }
}
