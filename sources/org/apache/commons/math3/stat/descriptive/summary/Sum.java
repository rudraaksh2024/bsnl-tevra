package org.apache.commons.math3.stat.descriptive.summary;

import java.io.Serializable;
import org.apache.commons.math3.exception.MathIllegalArgumentException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.stat.descriptive.AbstractStorelessUnivariateStatistic;
import org.apache.commons.math3.util.MathUtils;

public class Sum extends AbstractStorelessUnivariateStatistic implements Serializable {
    private static final long serialVersionUID = -8231831954703408316L;
    private long n;
    private double value;

    public Sum() {
        this.n = 0;
        this.value = 0.0d;
    }

    public Sum(Sum sum) throws NullArgumentException {
        copy(sum, this);
    }

    public void increment(double d) {
        this.value += d;
        this.n++;
    }

    public double getResult() {
        return this.value;
    }

    public long getN() {
        return this.n;
    }

    public void clear() {
        this.value = 0.0d;
        this.n = 0;
    }

    public double evaluate(double[] dArr, int i, int i2) throws MathIllegalArgumentException {
        if (!test(dArr, i, i2, true)) {
            return Double.NaN;
        }
        double d = 0.0d;
        for (int i3 = i; i3 < i + i2; i3++) {
            d += dArr[i3];
        }
        return d;
    }

    public double evaluate(double[] dArr, double[] dArr2, int i, int i2) throws MathIllegalArgumentException {
        if (!test(dArr, dArr2, i, i2, true)) {
            return Double.NaN;
        }
        double d = 0.0d;
        for (int i3 = i; i3 < i + i2; i3++) {
            d += dArr[i3] * dArr2[i3];
        }
        return d;
    }

    public double evaluate(double[] dArr, double[] dArr2) throws MathIllegalArgumentException {
        return evaluate(dArr, dArr2, 0, dArr.length);
    }

    public Sum copy() {
        Sum sum = new Sum();
        copy(this, sum);
        return sum;
    }

    public static void copy(Sum sum, Sum sum2) throws NullArgumentException {
        MathUtils.checkNotNull(sum);
        MathUtils.checkNotNull(sum2);
        sum2.setData(sum.getDataRef());
        sum2.n = sum.n;
        sum2.value = sum.value;
    }
}
