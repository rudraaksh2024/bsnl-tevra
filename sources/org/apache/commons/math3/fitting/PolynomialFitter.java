package org.apache.commons.math3.fitting;

import org.apache.commons.math3.analysis.polynomials.PolynomialFunction;
import org.apache.commons.math3.optim.nonlinear.vector.MultivariateVectorOptimizer;

@Deprecated
public class PolynomialFitter extends CurveFitter<PolynomialFunction.Parametric> {
    public PolynomialFitter(MultivariateVectorOptimizer multivariateVectorOptimizer) {
        super(multivariateVectorOptimizer);
    }

    public double[] fit(int i, double[] dArr) {
        return fit(i, new PolynomialFunction.Parametric(), dArr);
    }

    public double[] fit(double[] dArr) {
        return fit(new PolynomialFunction.Parametric(), dArr);
    }
}
