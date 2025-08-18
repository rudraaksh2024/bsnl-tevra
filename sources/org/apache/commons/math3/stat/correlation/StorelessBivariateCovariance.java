package org.apache.commons.math3.stat.correlation;

import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.exception.util.LocalizedFormats;

class StorelessBivariateCovariance {
    private boolean biasCorrected;
    private double covarianceNumerator;
    private double meanX;
    private double meanY;
    private double n;

    StorelessBivariateCovariance() {
        this(true);
    }

    StorelessBivariateCovariance(boolean z) {
        this.meanY = 0.0d;
        this.meanX = 0.0d;
        this.n = 0.0d;
        this.covarianceNumerator = 0.0d;
        this.biasCorrected = z;
    }

    public void increment(double d, double d2) {
        double d3 = this.n + 1.0d;
        this.n = d3;
        double d4 = this.meanX;
        double d5 = d - d4;
        double d6 = this.meanY;
        double d7 = d2 - d6;
        this.meanX = d4 + (d5 / d3);
        this.meanY = d6 + (d7 / d3);
        this.covarianceNumerator += ((d3 - 1.0d) / d3) * d5 * d7;
    }

    public void append(StorelessBivariateCovariance storelessBivariateCovariance) {
        StorelessBivariateCovariance storelessBivariateCovariance2 = storelessBivariateCovariance;
        double d = this.n;
        double d2 = storelessBivariateCovariance2.n + d;
        this.n = d2;
        double d3 = storelessBivariateCovariance2.meanX;
        double d4 = this.meanX;
        double d5 = d3 - d4;
        double d6 = storelessBivariateCovariance2.meanY;
        double d7 = this.meanY;
        double d8 = d6 - d7;
        double d9 = storelessBivariateCovariance2.n;
        this.meanX = d4 + ((d5 * d9) / d2);
        this.meanY = d7 + ((d8 * d9) / d2);
        this.covarianceNumerator += storelessBivariateCovariance2.covarianceNumerator + (((d * d9) / d2) * d5 * d8);
    }

    public double getN() {
        return this.n;
    }

    public double getResult() throws NumberIsTooSmallException {
        double d;
        double d2 = this.n;
        if (d2 >= 2.0d) {
            if (this.biasCorrected) {
                d = this.covarianceNumerator;
                d2 -= 1.0d;
            } else {
                d = this.covarianceNumerator;
            }
            return d / d2;
        }
        throw new NumberIsTooSmallException(LocalizedFormats.INSUFFICIENT_DIMENSION, Double.valueOf(this.n), 2, true);
    }
}
