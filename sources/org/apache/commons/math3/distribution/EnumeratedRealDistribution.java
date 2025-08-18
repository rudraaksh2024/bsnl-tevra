package org.apache.commons.math3.distribution;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.MathArithmeticException;
import org.apache.commons.math3.exception.NotANumberException;
import org.apache.commons.math3.exception.NotFiniteNumberException;
import org.apache.commons.math3.exception.NotPositiveException;
import org.apache.commons.math3.exception.OutOfRangeException;
import org.apache.commons.math3.random.RandomGenerator;
import org.apache.commons.math3.random.Well19937c;
import org.apache.commons.math3.util.Pair;

public class EnumeratedRealDistribution extends AbstractRealDistribution {
    private static final long serialVersionUID = 20130308;
    protected final EnumeratedDistribution<Double> innerDistribution;

    public boolean isSupportConnected() {
        return true;
    }

    public boolean isSupportLowerBoundInclusive() {
        return true;
    }

    public boolean isSupportUpperBoundInclusive() {
        return true;
    }

    public EnumeratedRealDistribution(double[] dArr, double[] dArr2) throws DimensionMismatchException, NotPositiveException, MathArithmeticException, NotFiniteNumberException, NotANumberException {
        this(new Well19937c(), dArr, dArr2);
    }

    public EnumeratedRealDistribution(RandomGenerator randomGenerator, double[] dArr, double[] dArr2) throws DimensionMismatchException, NotPositiveException, MathArithmeticException, NotFiniteNumberException, NotANumberException {
        super(randomGenerator);
        this.innerDistribution = new EnumeratedDistribution<>(randomGenerator, createDistribution(dArr, dArr2));
    }

    public EnumeratedRealDistribution(RandomGenerator randomGenerator, double[] dArr) {
        super(randomGenerator);
        HashMap hashMap = new HashMap();
        int i = 0;
        for (double d : dArr) {
            Integer num = (Integer) hashMap.get(Double.valueOf(d));
            if (num == null) {
                num = 0;
            }
            hashMap.put(Double.valueOf(d), Integer.valueOf(num.intValue() + 1));
        }
        int size = hashMap.size();
        double length = (double) dArr.length;
        double[] dArr2 = new double[size];
        double[] dArr3 = new double[size];
        for (Map.Entry entry : hashMap.entrySet()) {
            dArr2[i] = ((Double) entry.getKey()).doubleValue();
            dArr3[i] = ((double) ((Integer) entry.getValue()).intValue()) / length;
            i++;
        }
        this.innerDistribution = new EnumeratedDistribution<>(randomGenerator, createDistribution(dArr2, dArr3));
    }

    public EnumeratedRealDistribution(double[] dArr) {
        this((RandomGenerator) new Well19937c(), dArr);
    }

    private static List<Pair<Double, Double>> createDistribution(double[] dArr, double[] dArr2) {
        if (dArr.length == dArr2.length) {
            ArrayList arrayList = new ArrayList(dArr.length);
            for (int i = 0; i < dArr.length; i++) {
                arrayList.add(new Pair(Double.valueOf(dArr[i]), Double.valueOf(dArr2[i])));
            }
            return arrayList;
        }
        throw new DimensionMismatchException(dArr2.length, dArr.length);
    }

    public double probability(double d) {
        return this.innerDistribution.probability(Double.valueOf(d));
    }

    public double density(double d) {
        return probability(d);
    }

    public double cumulativeProbability(double d) {
        double d2 = 0.0d;
        for (Pair next : this.innerDistribution.getPmf()) {
            if (((Double) next.getKey()).doubleValue() <= d) {
                d2 += ((Double) next.getValue()).doubleValue();
            }
        }
        return d2;
    }

    public double inverseCumulativeProbability(double d) throws OutOfRangeException {
        if (d < 0.0d || d > 1.0d) {
            throw new OutOfRangeException(Double.valueOf(d), 0, 1);
        }
        double supportLowerBound = getSupportLowerBound();
        double d2 = 0.0d;
        for (Pair next : this.innerDistribution.getPmf()) {
            if (((Double) next.getValue()).doubleValue() != 0.0d) {
                d2 += ((Double) next.getValue()).doubleValue();
                supportLowerBound = ((Double) next.getKey()).doubleValue();
                if (d2 >= d) {
                    break;
                }
            }
        }
        return supportLowerBound;
    }

    public double getNumericalMean() {
        double d = 0.0d;
        for (Pair next : this.innerDistribution.getPmf()) {
            d += ((Double) next.getValue()).doubleValue() * ((Double) next.getKey()).doubleValue();
        }
        return d;
    }

    public double getNumericalVariance() {
        double d = 0.0d;
        double d2 = 0.0d;
        for (Pair next : this.innerDistribution.getPmf()) {
            d2 += ((Double) next.getValue()).doubleValue() * ((Double) next.getKey()).doubleValue();
            d += ((Double) next.getValue()).doubleValue() * ((Double) next.getKey()).doubleValue() * ((Double) next.getKey()).doubleValue();
        }
        return d - (d2 * d2);
    }

    public double getSupportLowerBound() {
        double d = Double.POSITIVE_INFINITY;
        for (Pair next : this.innerDistribution.getPmf()) {
            if (((Double) next.getKey()).doubleValue() < d && ((Double) next.getValue()).doubleValue() > 0.0d) {
                d = ((Double) next.getKey()).doubleValue();
            }
        }
        return d;
    }

    public double getSupportUpperBound() {
        double d = Double.NEGATIVE_INFINITY;
        for (Pair next : this.innerDistribution.getPmf()) {
            if (((Double) next.getKey()).doubleValue() > d && ((Double) next.getValue()).doubleValue() > 0.0d) {
                d = ((Double) next.getKey()).doubleValue();
            }
        }
        return d;
    }

    public double sample() {
        return this.innerDistribution.sample().doubleValue();
    }
}
