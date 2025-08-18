package org.apache.commons.math3.optimization.fitting;

import org.apache.commons.math3.analysis.polynomials.PolynomialFunction;
import org.apache.commons.math3.optimization.DifferentiableMultivariateVectorOptimizer;

@Deprecated
public class PolynomialFitter extends CurveFitter<PolynomialFunction.Parametric> {
    @Deprecated
    private final int degree;

    @Deprecated
    public PolynomialFitter(int i, DifferentiableMultivariateVectorOptimizer differentiableMultivariateVectorOptimizer) {
        super(differentiableMultivariateVectorOptimizer);
        this.degree = i;
    }

    public PolynomialFitter(DifferentiableMultivariateVectorOptimizer differentiableMultivariateVectorOptimizer) {
        super(differentiableMultivariateVectorOptimizer);
        this.degree = -1;
    }

    @Deprecated
    public double[] fit() {
        return fit(new PolynomialFunction.Parametric(), new double[(this.degree + 1)]);
    }

    public double[] fit(int i, double[] dArr) {
        return fit(i, new PolynomialFunction.Parametric(), dArr);
    }

    public double[] fit(double[] dArr) {
        return fit(new PolynomialFunction.Parametric(), dArr);
    }
}
