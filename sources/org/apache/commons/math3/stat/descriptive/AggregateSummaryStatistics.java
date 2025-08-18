package org.apache.commons.math3.stat.descriptive;

import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import org.apache.commons.math3.exception.NullArgumentException;

public class AggregateSummaryStatistics implements StatisticalSummary, Serializable {
    private static final long serialVersionUID = -8207112444016386906L;
    private final SummaryStatistics statistics;
    private final SummaryStatistics statisticsPrototype;

    public AggregateSummaryStatistics() {
        this(new SummaryStatistics());
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public AggregateSummaryStatistics(SummaryStatistics summaryStatistics) throws NullArgumentException {
        this(summaryStatistics, summaryStatistics == null ? null : new SummaryStatistics(summaryStatistics));
    }

    public AggregateSummaryStatistics(SummaryStatistics summaryStatistics, SummaryStatistics summaryStatistics2) {
        this.statisticsPrototype = summaryStatistics == null ? new SummaryStatistics() : summaryStatistics;
        this.statistics = summaryStatistics2 == null ? new SummaryStatistics() : summaryStatistics2;
    }

    public double getMax() {
        double max;
        synchronized (this.statistics) {
            max = this.statistics.getMax();
        }
        return max;
    }

    public double getMean() {
        double mean;
        synchronized (this.statistics) {
            mean = this.statistics.getMean();
        }
        return mean;
    }

    public double getMin() {
        double min;
        synchronized (this.statistics) {
            min = this.statistics.getMin();
        }
        return min;
    }

    public long getN() {
        long n;
        synchronized (this.statistics) {
            n = this.statistics.getN();
        }
        return n;
    }

    public double getStandardDeviation() {
        double standardDeviation;
        synchronized (this.statistics) {
            standardDeviation = this.statistics.getStandardDeviation();
        }
        return standardDeviation;
    }

    public double getSum() {
        double sum;
        synchronized (this.statistics) {
            sum = this.statistics.getSum();
        }
        return sum;
    }

    public double getVariance() {
        double variance;
        synchronized (this.statistics) {
            variance = this.statistics.getVariance();
        }
        return variance;
    }

    public double getSumOfLogs() {
        double sumOfLogs;
        synchronized (this.statistics) {
            sumOfLogs = this.statistics.getSumOfLogs();
        }
        return sumOfLogs;
    }

    public double getGeometricMean() {
        double geometricMean;
        synchronized (this.statistics) {
            geometricMean = this.statistics.getGeometricMean();
        }
        return geometricMean;
    }

    public double getSumsq() {
        double sumsq;
        synchronized (this.statistics) {
            sumsq = this.statistics.getSumsq();
        }
        return sumsq;
    }

    public double getSecondMoment() {
        double secondMoment;
        synchronized (this.statistics) {
            secondMoment = this.statistics.getSecondMoment();
        }
        return secondMoment;
    }

    public StatisticalSummary getSummary() {
        StatisticalSummaryValues statisticalSummaryValues;
        synchronized (this.statistics) {
            statisticalSummaryValues = new StatisticalSummaryValues(getMean(), getVariance(), getN(), getMax(), getMin(), getSum());
        }
        return statisticalSummaryValues;
    }

    public SummaryStatistics createContributingStatistics() {
        AggregatingSummaryStatistics aggregatingSummaryStatistics = new AggregatingSummaryStatistics(this.statistics);
        SummaryStatistics.copy(this.statisticsPrototype, aggregatingSummaryStatistics);
        return aggregatingSummaryStatistics;
    }

    public static StatisticalSummaryValues aggregate(Collection<? extends StatisticalSummary> collection) {
        double d;
        double d2;
        if (collection == null) {
            return null;
        }
        Iterator<? extends StatisticalSummary> it = collection.iterator();
        if (!it.hasNext()) {
            return null;
        }
        StatisticalSummary statisticalSummary = (StatisticalSummary) it.next();
        long n = statisticalSummary.getN();
        double min = statisticalSummary.getMin();
        double sum = statisticalSummary.getSum();
        double max = statisticalSummary.getMax();
        double variance = statisticalSummary.getVariance() * (((double) n) - 1.0d);
        double d3 = min;
        double d4 = sum;
        double d5 = max;
        double mean = statisticalSummary.getMean();
        while (it.hasNext()) {
            StatisticalSummary statisticalSummary2 = (StatisticalSummary) it.next();
            if (statisticalSummary2.getMin() < d3 || Double.isNaN(d3)) {
                d3 = statisticalSummary2.getMin();
            }
            if (statisticalSummary2.getMax() > d5 || Double.isNaN(d5)) {
                d5 = statisticalSummary2.getMax();
            }
            d4 += statisticalSummary2.getSum();
            double d6 = (double) n;
            double n2 = (double) statisticalSummary2.getN();
            long j = (long) (d6 + n2);
            double mean2 = statisticalSummary2.getMean() - mean;
            double d7 = (double) j;
            mean = d4 / d7;
            variance = variance + (statisticalSummary2.getVariance() * (n2 - 1.0d)) + ((((mean2 * mean2) * d6) * n2) / d7);
            n = j;
        }
        if (n == 0) {
            d2 = Double.NaN;
        } else if (n == 1) {
            d2 = 0.0d;
        } else {
            d = variance / ((double) (n - 1));
            return new StatisticalSummaryValues(mean, d, n, d5, d3, d4);
        }
        d = d2;
        return new StatisticalSummaryValues(mean, d, n, d5, d3, d4);
    }

    private static class AggregatingSummaryStatistics extends SummaryStatistics {
        private static final long serialVersionUID = 1;
        private final SummaryStatistics aggregateStatistics;

        AggregatingSummaryStatistics(SummaryStatistics summaryStatistics) {
            this.aggregateStatistics = summaryStatistics;
        }

        public void addValue(double d) {
            super.addValue(d);
            synchronized (this.aggregateStatistics) {
                this.aggregateStatistics.addValue(d);
            }
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof AggregatingSummaryStatistics)) {
                return false;
            }
            AggregatingSummaryStatistics aggregatingSummaryStatistics = (AggregatingSummaryStatistics) obj;
            if (!super.equals(aggregatingSummaryStatistics) || !this.aggregateStatistics.equals(aggregatingSummaryStatistics.aggregateStatistics)) {
                return false;
            }
            return true;
        }

        public int hashCode() {
            return super.hashCode() + 123 + this.aggregateStatistics.hashCode();
        }
    }
}
