package org.apache.commons.math3.stat.descriptive;

import java.io.Serializable;
import java.util.Arrays;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.MathIllegalStateException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.stat.descriptive.moment.GeometricMean;
import org.apache.commons.math3.stat.descriptive.moment.Mean;
import org.apache.commons.math3.stat.descriptive.moment.VectorialCovariance;
import org.apache.commons.math3.stat.descriptive.rank.Max;
import org.apache.commons.math3.stat.descriptive.rank.Min;
import org.apache.commons.math3.stat.descriptive.summary.Sum;
import org.apache.commons.math3.stat.descriptive.summary.SumOfLogs;
import org.apache.commons.math3.stat.descriptive.summary.SumOfSquares;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.MathArrays;
import org.apache.commons.math3.util.MathUtils;
import org.apache.commons.math3.util.Precision;

public class MultivariateSummaryStatistics implements StatisticalMultivariateSummary, Serializable {
    private static final long serialVersionUID = 2271900808994826718L;
    private VectorialCovariance covarianceImpl;
    private StorelessUnivariateStatistic[] geoMeanImpl;
    private int k;
    private StorelessUnivariateStatistic[] maxImpl;
    private StorelessUnivariateStatistic[] meanImpl;
    private StorelessUnivariateStatistic[] minImpl;
    private long n = 0;
    private StorelessUnivariateStatistic[] sumImpl;
    private StorelessUnivariateStatistic[] sumLogImpl;
    private StorelessUnivariateStatistic[] sumSqImpl;

    public MultivariateSummaryStatistics(int i, boolean z) {
        this.k = i;
        this.sumImpl = new StorelessUnivariateStatistic[i];
        this.sumSqImpl = new StorelessUnivariateStatistic[i];
        this.minImpl = new StorelessUnivariateStatistic[i];
        this.maxImpl = new StorelessUnivariateStatistic[i];
        this.sumLogImpl = new StorelessUnivariateStatistic[i];
        this.geoMeanImpl = new StorelessUnivariateStatistic[i];
        this.meanImpl = new StorelessUnivariateStatistic[i];
        for (int i2 = 0; i2 < i; i2++) {
            this.sumImpl[i2] = new Sum();
            this.sumSqImpl[i2] = new SumOfSquares();
            this.minImpl[i2] = new Min();
            this.maxImpl[i2] = new Max();
            this.sumLogImpl[i2] = new SumOfLogs();
            this.geoMeanImpl[i2] = new GeometricMean();
            this.meanImpl[i2] = new Mean();
        }
        this.covarianceImpl = new VectorialCovariance(i, z);
    }

    public void addValue(double[] dArr) throws DimensionMismatchException {
        checkDimension(dArr.length);
        for (int i = 0; i < this.k; i++) {
            double d = dArr[i];
            this.sumImpl[i].increment(d);
            this.sumSqImpl[i].increment(d);
            this.minImpl[i].increment(d);
            this.maxImpl[i].increment(d);
            this.sumLogImpl[i].increment(d);
            this.geoMeanImpl[i].increment(d);
            this.meanImpl[i].increment(d);
        }
        this.covarianceImpl.increment(dArr);
        this.n++;
    }

    public int getDimension() {
        return this.k;
    }

    public long getN() {
        return this.n;
    }

    private double[] getResults(StorelessUnivariateStatistic[] storelessUnivariateStatisticArr) {
        int length = storelessUnivariateStatisticArr.length;
        double[] dArr = new double[length];
        for (int i = 0; i < length; i++) {
            dArr[i] = storelessUnivariateStatisticArr[i].getResult();
        }
        return dArr;
    }

    public double[] getSum() {
        return getResults(this.sumImpl);
    }

    public double[] getSumSq() {
        return getResults(this.sumSqImpl);
    }

    public double[] getSumLog() {
        return getResults(this.sumLogImpl);
    }

    public double[] getMean() {
        return getResults(this.meanImpl);
    }

    public double[] getStandardDeviation() {
        double[] dArr = new double[this.k];
        if (getN() < 1) {
            Arrays.fill(dArr, Double.NaN);
        } else if (getN() < 2) {
            Arrays.fill(dArr, 0.0d);
        } else {
            RealMatrix result = this.covarianceImpl.getResult();
            for (int i = 0; i < this.k; i++) {
                dArr[i] = FastMath.sqrt(result.getEntry(i, i));
            }
        }
        return dArr;
    }

    public RealMatrix getCovariance() {
        return this.covarianceImpl.getResult();
    }

    public double[] getMax() {
        return getResults(this.maxImpl);
    }

    public double[] getMin() {
        return getResults(this.minImpl);
    }

    public double[] getGeometricMean() {
        return getResults(this.geoMeanImpl);
    }

    public String toString() {
        String property = System.getProperty("line.separator");
        StringBuilder sb = new StringBuilder();
        sb.append("MultivariateSummaryStatistics:" + property);
        sb.append("n: " + getN() + property);
        StringBuilder sb2 = sb;
        String str = property;
        append(sb2, getMin(), "min: ", ", ", str);
        append(sb2, getMax(), "max: ", ", ", str);
        append(sb2, getMean(), "mean: ", ", ", str);
        append(sb2, getGeometricMean(), "geometric mean: ", ", ", str);
        append(sb2, getSumSq(), "sum of squares: ", ", ", str);
        append(sb2, getSumLog(), "sum of logarithms: ", ", ", str);
        append(sb2, getStandardDeviation(), "standard deviation: ", ", ", str);
        sb.append("covariance: " + getCovariance().toString() + property);
        return sb.toString();
    }

    private void append(StringBuilder sb, double[] dArr, String str, String str2, String str3) {
        sb.append(str);
        for (int i = 0; i < dArr.length; i++) {
            if (i > 0) {
                sb.append(str2);
            }
            sb.append(dArr[i]);
        }
        sb.append(str3);
    }

    public void clear() {
        this.n = 0;
        for (int i = 0; i < this.k; i++) {
            this.minImpl[i].clear();
            this.maxImpl[i].clear();
            this.sumImpl[i].clear();
            this.sumLogImpl[i].clear();
            this.sumSqImpl[i].clear();
            this.geoMeanImpl[i].clear();
            this.meanImpl[i].clear();
        }
        this.covarianceImpl.clear();
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof MultivariateSummaryStatistics)) {
            return false;
        }
        MultivariateSummaryStatistics multivariateSummaryStatistics = (MultivariateSummaryStatistics) obj;
        if (!MathArrays.equalsIncludingNaN(multivariateSummaryStatistics.getGeometricMean(), getGeometricMean()) || !MathArrays.equalsIncludingNaN(multivariateSummaryStatistics.getMax(), getMax()) || !MathArrays.equalsIncludingNaN(multivariateSummaryStatistics.getMean(), getMean()) || !MathArrays.equalsIncludingNaN(multivariateSummaryStatistics.getMin(), getMin()) || !Precision.equalsIncludingNaN((float) multivariateSummaryStatistics.getN(), (float) getN()) || !MathArrays.equalsIncludingNaN(multivariateSummaryStatistics.getSum(), getSum()) || !MathArrays.equalsIncludingNaN(multivariateSummaryStatistics.getSumSq(), getSumSq()) || !MathArrays.equalsIncludingNaN(multivariateSummaryStatistics.getSumLog(), getSumLog()) || !multivariateSummaryStatistics.getCovariance().equals(getCovariance())) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return ((((((((((((((((((MathUtils.hash(getGeometricMean()) + 31) * 31) + MathUtils.hash(getGeometricMean())) * 31) + MathUtils.hash(getMax())) * 31) + MathUtils.hash(getMean())) * 31) + MathUtils.hash(getMin())) * 31) + MathUtils.hash((double) getN())) * 31) + MathUtils.hash(getSum())) * 31) + MathUtils.hash(getSumSq())) * 31) + MathUtils.hash(getSumLog())) * 31) + getCovariance().hashCode();
    }

    private void setImpl(StorelessUnivariateStatistic[] storelessUnivariateStatisticArr, StorelessUnivariateStatistic[] storelessUnivariateStatisticArr2) throws MathIllegalStateException, DimensionMismatchException {
        checkEmpty();
        checkDimension(storelessUnivariateStatisticArr.length);
        System.arraycopy(storelessUnivariateStatisticArr, 0, storelessUnivariateStatisticArr2, 0, storelessUnivariateStatisticArr.length);
    }

    public StorelessUnivariateStatistic[] getSumImpl() {
        return (StorelessUnivariateStatistic[]) this.sumImpl.clone();
    }

    public void setSumImpl(StorelessUnivariateStatistic[] storelessUnivariateStatisticArr) throws MathIllegalStateException, DimensionMismatchException {
        setImpl(storelessUnivariateStatisticArr, this.sumImpl);
    }

    public StorelessUnivariateStatistic[] getSumsqImpl() {
        return (StorelessUnivariateStatistic[]) this.sumSqImpl.clone();
    }

    public void setSumsqImpl(StorelessUnivariateStatistic[] storelessUnivariateStatisticArr) throws MathIllegalStateException, DimensionMismatchException {
        setImpl(storelessUnivariateStatisticArr, this.sumSqImpl);
    }

    public StorelessUnivariateStatistic[] getMinImpl() {
        return (StorelessUnivariateStatistic[]) this.minImpl.clone();
    }

    public void setMinImpl(StorelessUnivariateStatistic[] storelessUnivariateStatisticArr) throws MathIllegalStateException, DimensionMismatchException {
        setImpl(storelessUnivariateStatisticArr, this.minImpl);
    }

    public StorelessUnivariateStatistic[] getMaxImpl() {
        return (StorelessUnivariateStatistic[]) this.maxImpl.clone();
    }

    public void setMaxImpl(StorelessUnivariateStatistic[] storelessUnivariateStatisticArr) throws MathIllegalStateException, DimensionMismatchException {
        setImpl(storelessUnivariateStatisticArr, this.maxImpl);
    }

    public StorelessUnivariateStatistic[] getSumLogImpl() {
        return (StorelessUnivariateStatistic[]) this.sumLogImpl.clone();
    }

    public void setSumLogImpl(StorelessUnivariateStatistic[] storelessUnivariateStatisticArr) throws MathIllegalStateException, DimensionMismatchException {
        setImpl(storelessUnivariateStatisticArr, this.sumLogImpl);
    }

    public StorelessUnivariateStatistic[] getGeoMeanImpl() {
        return (StorelessUnivariateStatistic[]) this.geoMeanImpl.clone();
    }

    public void setGeoMeanImpl(StorelessUnivariateStatistic[] storelessUnivariateStatisticArr) throws MathIllegalStateException, DimensionMismatchException {
        setImpl(storelessUnivariateStatisticArr, this.geoMeanImpl);
    }

    public StorelessUnivariateStatistic[] getMeanImpl() {
        return (StorelessUnivariateStatistic[]) this.meanImpl.clone();
    }

    public void setMeanImpl(StorelessUnivariateStatistic[] storelessUnivariateStatisticArr) throws MathIllegalStateException, DimensionMismatchException {
        setImpl(storelessUnivariateStatisticArr, this.meanImpl);
    }

    private void checkEmpty() throws MathIllegalStateException {
        if (this.n > 0) {
            throw new MathIllegalStateException(LocalizedFormats.VALUES_ADDED_BEFORE_CONFIGURING_STATISTIC, Long.valueOf(this.n));
        }
    }

    private void checkDimension(int i) throws DimensionMismatchException {
        if (i != this.k) {
            throw new DimensionMismatchException(i, this.k);
        }
    }
}
