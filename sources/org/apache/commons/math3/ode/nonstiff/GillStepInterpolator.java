package org.apache.commons.math3.ode.nonstiff;

import org.apache.commons.math3.ode.sampling.StepInterpolator;
import org.apache.commons.math3.util.FastMath;

class GillStepInterpolator extends RungeKuttaStepInterpolator {
    private static final double ONE_MINUS_INV_SQRT_2 = (1.0d - FastMath.sqrt(0.5d));
    private static final double ONE_PLUS_INV_SQRT_2 = (FastMath.sqrt(0.5d) + 1.0d);
    private static final long serialVersionUID = 20111120;

    public GillStepInterpolator() {
    }

    GillStepInterpolator(GillStepInterpolator gillStepInterpolator) {
        super(gillStepInterpolator);
    }

    /* access modifiers changed from: protected */
    public StepInterpolator doCopy() {
        return new GillStepInterpolator(this);
    }

    /* access modifiers changed from: protected */
    public void computeInterpolatedStateAndDerivatives(double d, double d2) {
        double d3 = d * 2.0d;
        double d4 = d3 * d3;
        double d5 = ((d3 - 3.0d) * d) + 1.0d;
        double d6 = (1.0d - d) * d3;
        double d7 = ONE_MINUS_INV_SQRT_2;
        double d8 = d6 * d7;
        double d9 = ONE_PLUS_INV_SQRT_2;
        double d10 = d6 * d9;
        double d11 = (d3 - 1.0d) * d;
        if (this.previousState == null || d > 0.5d) {
            double d12 = d2 / 6.0d;
            double d13 = ((d3 + 2.0d) - d4) * d12;
            double d14 = ((1.0d - (5.0d * d)) + d4) * d12;
            double d15 = d7 * d13;
            double d16 = d13 * d9;
            double d17 = d12 * (d + 1.0d + d4);
            for (int i = 0; i < this.interpolatedState.length; i++) {
                double d18 = this.yDotK[0][i];
                double d19 = this.yDotK[1][i];
                double d20 = this.yDotK[2][i];
                double d21 = this.yDotK[3][i];
                this.interpolatedState[i] = (((this.currentState[i] - (d14 * d18)) - (d15 * d19)) - (d16 * d20)) - (d17 * d21);
                this.interpolatedDerivatives[i] = (d18 * d5) + (d19 * d8) + (d20 * d10) + (d21 * d11);
            }
            return;
        }
        double d22 = (this.h * d) / 6.0d;
        double d23 = ((d * 6.0d) - d4) * d22;
        double d24 = ((6.0d - (9.0d * d)) + d4) * d22;
        double d25 = d7 * d23;
        double d26 = d23 * d9;
        double d27 = d22 * ((-3.0d * d) + d4);
        for (int i2 = 0; i2 < this.interpolatedState.length; i2++) {
            double d28 = this.yDotK[0][i2];
            double d29 = this.yDotK[1][i2];
            double d30 = this.yDotK[2][i2];
            double d31 = this.yDotK[3][i2];
            this.interpolatedState[i2] = this.previousState[i2] + (d24 * d28) + (d25 * d29) + (d26 * d30) + (d27 * d31);
            this.interpolatedDerivatives[i2] = (d28 * d5) + (d29 * d8) + (d30 * d10) + (d31 * d11);
        }
    }
}
