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
import org.apache.commons.math3.random.RandomGenerator;
import org.apache.commons.math3.random.Well19937c;
import org.apache.commons.math3.util.Pair;

public class EnumeratedIntegerDistribution extends AbstractIntegerDistribution {
    private static final long serialVersionUID = 20130308;
    protected final EnumeratedDistribution<Integer> innerDistribution;

    public boolean isSupportConnected() {
        return true;
    }

    public EnumeratedIntegerDistribution(int[] iArr, double[] dArr) throws DimensionMismatchException, NotPositiveException, MathArithmeticException, NotFiniteNumberException, NotANumberException {
        this(new Well19937c(), iArr, dArr);
    }

    public EnumeratedIntegerDistribution(RandomGenerator randomGenerator, int[] iArr, double[] dArr) throws DimensionMismatchException, NotPositiveException, MathArithmeticException, NotFiniteNumberException, NotANumberException {
        super(randomGenerator);
        this.innerDistribution = new EnumeratedDistribution<>(randomGenerator, createDistribution(iArr, dArr));
    }

    public EnumeratedIntegerDistribution(RandomGenerator randomGenerator, int[] iArr) {
        super(randomGenerator);
        HashMap hashMap = new HashMap();
        int i = 0;
        for (int i2 : iArr) {
            Integer num = (Integer) hashMap.get(Integer.valueOf(i2));
            if (num == null) {
                num = 0;
            }
            hashMap.put(Integer.valueOf(i2), Integer.valueOf(num.intValue() + 1));
        }
        int size = hashMap.size();
        double length = (double) iArr.length;
        int[] iArr2 = new int[size];
        double[] dArr = new double[size];
        for (Map.Entry entry : hashMap.entrySet()) {
            iArr2[i] = ((Integer) entry.getKey()).intValue();
            dArr[i] = ((double) ((Integer) entry.getValue()).intValue()) / length;
            i++;
        }
        this.innerDistribution = new EnumeratedDistribution<>(randomGenerator, createDistribution(iArr2, dArr));
    }

    public EnumeratedIntegerDistribution(int[] iArr) {
        this((RandomGenerator) new Well19937c(), iArr);
    }

    private static List<Pair<Integer, Double>> createDistribution(int[] iArr, double[] dArr) {
        if (iArr.length == dArr.length) {
            ArrayList arrayList = new ArrayList(iArr.length);
            for (int i = 0; i < iArr.length; i++) {
                arrayList.add(new Pair(Integer.valueOf(iArr[i]), Double.valueOf(dArr[i])));
            }
            return arrayList;
        }
        throw new DimensionMismatchException(dArr.length, iArr.length);
    }

    public double probability(int i) {
        return this.innerDistribution.probability(Integer.valueOf(i));
    }

    public double cumulativeProbability(int i) {
        double d = 0.0d;
        for (Pair next : this.innerDistribution.getPmf()) {
            if (((Integer) next.getKey()).intValue() <= i) {
                d += ((Double) next.getValue()).doubleValue();
            }
        }
        return d;
    }

    public double getNumericalMean() {
        double d = 0.0d;
        for (Pair next : this.innerDistribution.getPmf()) {
            d += ((Double) next.getValue()).doubleValue() * ((double) ((Integer) next.getKey()).intValue());
        }
        return d;
    }

    public double getNumericalVariance() {
        double d = 0.0d;
        double d2 = 0.0d;
        for (Pair next : this.innerDistribution.getPmf()) {
            d2 += ((Double) next.getValue()).doubleValue() * ((double) ((Integer) next.getKey()).intValue());
            d += ((Double) next.getValue()).doubleValue() * ((double) ((Integer) next.getKey()).intValue()) * ((double) ((Integer) next.getKey()).intValue());
        }
        return d - (d2 * d2);
    }

    public int getSupportLowerBound() {
        int i = Integer.MAX_VALUE;
        for (Pair next : this.innerDistribution.getPmf()) {
            if (((Integer) next.getKey()).intValue() < i && ((Double) next.getValue()).doubleValue() > 0.0d) {
                i = ((Integer) next.getKey()).intValue();
            }
        }
        return i;
    }

    public int getSupportUpperBound() {
        int i = Integer.MIN_VALUE;
        for (Pair next : this.innerDistribution.getPmf()) {
            if (((Integer) next.getKey()).intValue() > i && ((Double) next.getValue()).doubleValue() > 0.0d) {
                i = ((Integer) next.getKey()).intValue();
            }
        }
        return i;
    }

    public int sample() {
        return this.innerDistribution.sample().intValue();
    }
}
