package org.apache.commons.math3.stat.descriptive;

import java.io.Serializable;
import org.apache.commons.math3.exception.MathIllegalStateException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.stat.descriptive.moment.GeometricMean;
import org.apache.commons.math3.stat.descriptive.moment.Mean;
import org.apache.commons.math3.stat.descriptive.moment.SecondMoment;
import org.apache.commons.math3.stat.descriptive.moment.Variance;
import org.apache.commons.math3.stat.descriptive.rank.Max;
import org.apache.commons.math3.stat.descriptive.rank.Min;
import org.apache.commons.math3.stat.descriptive.summary.Sum;
import org.apache.commons.math3.stat.descriptive.summary.SumOfLogs;
import org.apache.commons.math3.stat.descriptive.summary.SumOfSquares;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.MathUtils;
import org.apache.commons.math3.util.Precision;

public class SummaryStatistics implements StatisticalSummary, Serializable {
    private static final long serialVersionUID = -2021321786743555871L;
    private GeometricMean geoMean = new GeometricMean(this.sumLog);
    private StorelessUnivariateStatistic geoMeanImpl;
    private Max max = new Max();
    private StorelessUnivariateStatistic maxImpl;
    private Mean mean = new Mean((FirstMoment) this.secondMoment);
    private StorelessUnivariateStatistic meanImpl;
    private Min min = new Min();
    private StorelessUnivariateStatistic minImpl;
    private long n = 0;
    private SecondMoment secondMoment = new SecondMoment();
    private Sum sum = new Sum();
    private StorelessUnivariateStatistic sumImpl;
    private SumOfLogs sumLog = new SumOfLogs();
    private StorelessUnivariateStatistic sumLogImpl;
    private SumOfSquares sumsq = new SumOfSquares();
    private StorelessUnivariateStatistic sumsqImpl;
    private Variance variance;
    private StorelessUnivariateStatistic varianceImpl;

    public SummaryStatistics() {
        Variance variance2 = new Variance(this.secondMoment);
        this.variance = variance2;
        this.sumImpl = this.sum;
        this.sumsqImpl = this.sumsq;
        this.minImpl = this.min;
        this.maxImpl = this.max;
        this.sumLogImpl = this.sumLog;
        this.geoMeanImpl = this.geoMean;
        this.meanImpl = this.mean;
        this.varianceImpl = variance2;
    }

    public SummaryStatistics(SummaryStatistics summaryStatistics) throws NullArgumentException {
        Variance variance2 = new Variance(this.secondMoment);
        this.variance = variance2;
        this.sumImpl = this.sum;
        this.sumsqImpl = this.sumsq;
        this.minImpl = this.min;
        this.maxImpl = this.max;
        this.sumLogImpl = this.sumLog;
        this.geoMeanImpl = this.geoMean;
        this.meanImpl = this.mean;
        this.varianceImpl = variance2;
        copy(summaryStatistics, this);
    }

    public StatisticalSummary getSummary() {
        return new StatisticalSummaryValues(getMean(), getVariance(), getN(), getMax(), getMin(), getSum());
    }

    public void addValue(double d) {
        this.sumImpl.increment(d);
        this.sumsqImpl.increment(d);
        this.minImpl.increment(d);
        this.maxImpl.increment(d);
        this.sumLogImpl.increment(d);
        this.secondMoment.increment(d);
        StorelessUnivariateStatistic storelessUnivariateStatistic = this.meanImpl;
        if (storelessUnivariateStatistic != this.mean) {
            storelessUnivariateStatistic.increment(d);
        }
        StorelessUnivariateStatistic storelessUnivariateStatistic2 = this.varianceImpl;
        if (storelessUnivariateStatistic2 != this.variance) {
            storelessUnivariateStatistic2.increment(d);
        }
        StorelessUnivariateStatistic storelessUnivariateStatistic3 = this.geoMeanImpl;
        if (storelessUnivariateStatistic3 != this.geoMean) {
            storelessUnivariateStatistic3.increment(d);
        }
        this.n++;
    }

    public long getN() {
        return this.n;
    }

    public double getSum() {
        return this.sumImpl.getResult();
    }

    public double getSumsq() {
        return this.sumsqImpl.getResult();
    }

    public double getMean() {
        return this.meanImpl.getResult();
    }

    public double getStandardDeviation() {
        if (getN() <= 0) {
            return Double.NaN;
        }
        if (getN() > 1) {
            return FastMath.sqrt(getVariance());
        }
        return 0.0d;
    }

    public double getQuadraticMean() {
        long n2 = getN();
        if (n2 > 0) {
            return FastMath.sqrt(getSumsq() / ((double) n2));
        }
        return Double.NaN;
    }

    public double getVariance() {
        return this.varianceImpl.getResult();
    }

    public double getPopulationVariance() {
        Variance variance2 = new Variance(this.secondMoment);
        variance2.setBiasCorrected(false);
        return variance2.getResult();
    }

    public double getMax() {
        return this.maxImpl.getResult();
    }

    public double getMin() {
        return this.minImpl.getResult();
    }

    public double getGeometricMean() {
        return this.geoMeanImpl.getResult();
    }

    public double getSumOfLogs() {
        return this.sumLogImpl.getResult();
    }

    public double getSecondMoment() {
        return this.secondMoment.getResult();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("SummaryStatistics:\nn: ");
        sb.append(getN()).append("\nmin: ");
        sb.append(getMin()).append("\nmax: ");
        sb.append(getMax()).append("\nsum: ");
        sb.append(getSum()).append("\nmean: ");
        sb.append(getMean()).append("\ngeometric mean: ");
        sb.append(getGeometricMean()).append("\nvariance: ");
        sb.append(getVariance()).append("\npopulation variance: ");
        sb.append(getPopulationVariance()).append("\nsecond moment: ");
        sb.append(getSecondMoment()).append("\nsum of squares: ");
        sb.append(getSumsq()).append("\nstandard deviation: ");
        sb.append(getStandardDeviation()).append("\nsum of logs: ");
        sb.append(getSumOfLogs()).append("\n");
        return sb.toString();
    }

    public void clear() {
        this.n = 0;
        this.minImpl.clear();
        this.maxImpl.clear();
        this.sumImpl.clear();
        this.sumLogImpl.clear();
        this.sumsqImpl.clear();
        this.geoMeanImpl.clear();
        this.secondMoment.clear();
        StorelessUnivariateStatistic storelessUnivariateStatistic = this.meanImpl;
        if (storelessUnivariateStatistic != this.mean) {
            storelessUnivariateStatistic.clear();
        }
        StorelessUnivariateStatistic storelessUnivariateStatistic2 = this.varianceImpl;
        if (storelessUnivariateStatistic2 != this.variance) {
            storelessUnivariateStatistic2.clear();
        }
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof SummaryStatistics)) {
            return false;
        }
        SummaryStatistics summaryStatistics = (SummaryStatistics) obj;
        if (!Precision.equalsIncludingNaN(summaryStatistics.getGeometricMean(), getGeometricMean()) || !Precision.equalsIncludingNaN(summaryStatistics.getMax(), getMax()) || !Precision.equalsIncludingNaN(summaryStatistics.getMean(), getMean()) || !Precision.equalsIncludingNaN(summaryStatistics.getMin(), getMin()) || !Precision.equalsIncludingNaN((float) summaryStatistics.getN(), (float) getN()) || !Precision.equalsIncludingNaN(summaryStatistics.getSum(), getSum()) || !Precision.equalsIncludingNaN(summaryStatistics.getSumsq(), getSumsq()) || !Precision.equalsIncludingNaN(summaryStatistics.getVariance(), getVariance())) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return ((((((((((((((((MathUtils.hash(getGeometricMean()) + 31) * 31) + MathUtils.hash(getGeometricMean())) * 31) + MathUtils.hash(getMax())) * 31) + MathUtils.hash(getMean())) * 31) + MathUtils.hash(getMin())) * 31) + MathUtils.hash((double) getN())) * 31) + MathUtils.hash(getSum())) * 31) + MathUtils.hash(getSumsq())) * 31) + MathUtils.hash(getVariance());
    }

    public StorelessUnivariateStatistic getSumImpl() {
        return this.sumImpl;
    }

    public void setSumImpl(StorelessUnivariateStatistic storelessUnivariateStatistic) throws MathIllegalStateException {
        checkEmpty();
        this.sumImpl = storelessUnivariateStatistic;
    }

    public StorelessUnivariateStatistic getSumsqImpl() {
        return this.sumsqImpl;
    }

    public void setSumsqImpl(StorelessUnivariateStatistic storelessUnivariateStatistic) throws MathIllegalStateException {
        checkEmpty();
        this.sumsqImpl = storelessUnivariateStatistic;
    }

    public StorelessUnivariateStatistic getMinImpl() {
        return this.minImpl;
    }

    public void setMinImpl(StorelessUnivariateStatistic storelessUnivariateStatistic) throws MathIllegalStateException {
        checkEmpty();
        this.minImpl = storelessUnivariateStatistic;
    }

    public StorelessUnivariateStatistic getMaxImpl() {
        return this.maxImpl;
    }

    public void setMaxImpl(StorelessUnivariateStatistic storelessUnivariateStatistic) throws MathIllegalStateException {
        checkEmpty();
        this.maxImpl = storelessUnivariateStatistic;
    }

    public StorelessUnivariateStatistic getSumLogImpl() {
        return this.sumLogImpl;
    }

    public void setSumLogImpl(StorelessUnivariateStatistic storelessUnivariateStatistic) throws MathIllegalStateException {
        checkEmpty();
        this.sumLogImpl = storelessUnivariateStatistic;
        this.geoMean.setSumLogImpl(storelessUnivariateStatistic);
    }

    public StorelessUnivariateStatistic getGeoMeanImpl() {
        return this.geoMeanImpl;
    }

    public void setGeoMeanImpl(StorelessUnivariateStatistic storelessUnivariateStatistic) throws MathIllegalStateException {
        checkEmpty();
        this.geoMeanImpl = storelessUnivariateStatistic;
    }

    public StorelessUnivariateStatistic getMeanImpl() {
        return this.meanImpl;
    }

    public void setMeanImpl(StorelessUnivariateStatistic storelessUnivariateStatistic) throws MathIllegalStateException {
        checkEmpty();
        this.meanImpl = storelessUnivariateStatistic;
    }

    public StorelessUnivariateStatistic getVarianceImpl() {
        return this.varianceImpl;
    }

    public void setVarianceImpl(StorelessUnivariateStatistic storelessUnivariateStatistic) throws MathIllegalStateException {
        checkEmpty();
        this.varianceImpl = storelessUnivariateStatistic;
    }

    private void checkEmpty() throws MathIllegalStateException {
        if (this.n > 0) {
            throw new MathIllegalStateException(LocalizedFormats.VALUES_ADDED_BEFORE_CONFIGURING_STATISTIC, Long.valueOf(this.n));
        }
    }

    public SummaryStatistics copy() {
        SummaryStatistics summaryStatistics = new SummaryStatistics();
        copy(this, summaryStatistics);
        return summaryStatistics;
    }

    public static void copy(SummaryStatistics summaryStatistics, SummaryStatistics summaryStatistics2) throws NullArgumentException {
        MathUtils.checkNotNull(summaryStatistics);
        MathUtils.checkNotNull(summaryStatistics2);
        summaryStatistics2.maxImpl = summaryStatistics.maxImpl.copy();
        summaryStatistics2.minImpl = summaryStatistics.minImpl.copy();
        summaryStatistics2.sumImpl = summaryStatistics.sumImpl.copy();
        summaryStatistics2.sumLogImpl = summaryStatistics.sumLogImpl.copy();
        summaryStatistics2.sumsqImpl = summaryStatistics.sumsqImpl.copy();
        summaryStatistics2.secondMoment = summaryStatistics.secondMoment.copy();
        summaryStatistics2.n = summaryStatistics.n;
        if (summaryStatistics.getVarianceImpl() instanceof Variance) {
            summaryStatistics2.varianceImpl = new Variance(summaryStatistics2.secondMoment);
        } else {
            summaryStatistics2.varianceImpl = summaryStatistics.varianceImpl.copy();
        }
        StorelessUnivariateStatistic storelessUnivariateStatistic = summaryStatistics.meanImpl;
        if (storelessUnivariateStatistic instanceof Mean) {
            summaryStatistics2.meanImpl = new Mean((FirstMoment) summaryStatistics2.secondMoment);
        } else {
            summaryStatistics2.meanImpl = storelessUnivariateStatistic.copy();
        }
        if (summaryStatistics.getGeoMeanImpl() instanceof GeometricMean) {
            summaryStatistics2.geoMeanImpl = new GeometricMean((SumOfLogs) summaryStatistics2.sumLogImpl);
        } else {
            summaryStatistics2.geoMeanImpl = summaryStatistics.geoMeanImpl.copy();
        }
        GeometricMean geometricMean = summaryStatistics.geoMean;
        if (geometricMean == summaryStatistics.geoMeanImpl) {
            summaryStatistics2.geoMean = (GeometricMean) summaryStatistics2.geoMeanImpl;
        } else {
            GeometricMean.copy(geometricMean, summaryStatistics2.geoMean);
        }
        Max max2 = summaryStatistics.max;
        if (max2 == summaryStatistics.maxImpl) {
            summaryStatistics2.max = (Max) summaryStatistics2.maxImpl;
        } else {
            Max.copy(max2, summaryStatistics2.max);
        }
        Mean mean2 = summaryStatistics.mean;
        if (mean2 == summaryStatistics.meanImpl) {
            summaryStatistics2.mean = (Mean) summaryStatistics2.meanImpl;
        } else {
            Mean.copy(mean2, summaryStatistics2.mean);
        }
        Min min2 = summaryStatistics.min;
        if (min2 == summaryStatistics.minImpl) {
            summaryStatistics2.min = (Min) summaryStatistics2.minImpl;
        } else {
            Min.copy(min2, summaryStatistics2.min);
        }
        Sum sum2 = summaryStatistics.sum;
        if (sum2 == summaryStatistics.sumImpl) {
            summaryStatistics2.sum = (Sum) summaryStatistics2.sumImpl;
        } else {
            Sum.copy(sum2, summaryStatistics2.sum);
        }
        Variance variance2 = summaryStatistics.variance;
        if (variance2 == summaryStatistics.varianceImpl) {
            summaryStatistics2.variance = (Variance) summaryStatistics2.varianceImpl;
        } else {
            Variance.copy(variance2, summaryStatistics2.variance);
        }
        SumOfLogs sumOfLogs = summaryStatistics.sumLog;
        if (sumOfLogs == summaryStatistics.sumLogImpl) {
            summaryStatistics2.sumLog = (SumOfLogs) summaryStatistics2.sumLogImpl;
        } else {
            SumOfLogs.copy(sumOfLogs, summaryStatistics2.sumLog);
        }
        SumOfSquares sumOfSquares = summaryStatistics.sumsq;
        if (sumOfSquares == summaryStatistics.sumsqImpl) {
            summaryStatistics2.sumsq = (SumOfSquares) summaryStatistics2.sumsqImpl;
        } else {
            SumOfSquares.copy(sumOfSquares, summaryStatistics2.sumsq);
        }
    }
}
