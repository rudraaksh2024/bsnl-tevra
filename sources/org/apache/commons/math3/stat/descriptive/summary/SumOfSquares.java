package org.apache.commons.math3.stat.descriptive.summary;

import java.io.Serializable;
import org.apache.commons.math3.exception.MathIllegalArgumentException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.stat.descriptive.AbstractStorelessUnivariateStatistic;
import org.apache.commons.math3.util.MathUtils;

public class SumOfSquares extends AbstractStorelessUnivariateStatistic implements Serializable {
    private static final long serialVersionUID = 1460986908574398008L;
    private long n;
    private double value;

    public SumOfSquares() {
        this.n = 0;
        this.value = 0.0d;
    }

    public SumOfSquares(SumOfSquares sumOfSquares) throws NullArgumentException {
        copy(sumOfSquares, this);
    }

    public void increment(double d) {
        this.value += d * d;
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
            double d2 = dArr[i3];
            d += d2 * d2;
        }
        return d;
    }

    public SumOfSquares copy() {
        SumOfSquares sumOfSquares = new SumOfSquares();
        copy(this, sumOfSquares);
        return sumOfSquares;
    }

    public static void copy(SumOfSquares sumOfSquares, SumOfSquares sumOfSquares2) throws NullArgumentException {
        MathUtils.checkNotNull(sumOfSquares);
        MathUtils.checkNotNull(sumOfSquares2);
        sumOfSquares2.setData(sumOfSquares.getDataRef());
        sumOfSquares2.n = sumOfSquares.n;
        sumOfSquares2.value = sumOfSquares.value;
    }
}
