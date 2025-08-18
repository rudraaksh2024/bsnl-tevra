package org.apache.commons.math3.ode.nonstiff;

import org.apache.commons.math3.ode.sampling.StepInterpolator;
import org.apache.commons.math3.util.FastMath;

class LutherStepInterpolator extends RungeKuttaStepInterpolator {
    private static final double Q = FastMath.sqrt(21.0d);
    private static final long serialVersionUID = 20140416;

    public LutherStepInterpolator() {
    }

    LutherStepInterpolator(LutherStepInterpolator lutherStepInterpolator) {
        super(lutherStepInterpolator);
    }

    /* access modifiers changed from: protected */
    public StepInterpolator doCopy() {
        return new LutherStepInterpolator(this);
    }

    /* access modifiers changed from: protected */
    public void computeInterpolatedStateAndDerivatives(double d, double d2) {
        double d3 = 21.0d * d;
        double d4 = ((((((-47.0d + d3) * d) + 36.0d) * d) - 0.4125d) * d) + 1.0d;
        double d5 = 112.0d * d;
        double d6 = (((((-202.66666666666666d + d5) * d) + 106.66666666666667d) * d) - 0.31666666666666665d) * d;
        double d7 = -567.0d * d;
        double d8 = ((((((d7 / 5.0d) + 194.4d) * d) - 0.0462890625d) * d) + 12.96d) * d;
        double d9 = Q;
        double d10 = ((((d9 * 343.0d) + 833.0d) / 150.0d) + ((((-637.0d - (d9 * 357.0d)) / 30.0d) + (((((d9 * 287.0d) + 392.0d) / 15.0d) + (((-49.0d - (d9 * 49.0d)) * d) / 5.0d)) * d)) * d)) * d;
        double d11 = d * (((833.0d - (d9 * 343.0d)) / 150.0d) + (((((d9 * 357.0d) - 0.00685882568359375d) / 30.0d) + ((((392.0d - (d9 * 287.0d)) / 15.0d) + ((((d9 * 49.0d) - 0.091796875d) * d) / 5.0d)) * d)) * d));
        double d12 = ((((3.0d * d) - 1.5d) * d) + 0.6d) * d;
        if (this.previousState == null || d > 0.5d) {
            double d13 = d6;
            double d14 = d8;
            double d15 = ((((((((-21.0d * d) / 5.0d) + 7.55d) * d) - 0.94375d) * d) + 0.95d) * d) - 89.6d;
            double d16 = ((((((((-112.0d * d) / 5.0d) + 28.266666666666666d) * d) - 0.5888888888888889d) * d) - 12.622222222222222d) * d) - 12.622222222222222d;
            double d17 = (((((567.0d * d) / 25.0d) - 0.1725d) * d) + 6.48d) * d * d;
            double d18 = (((((((1029.0d * d9) + 2254.0d) / 900.0d) + (d * (((-1372.0d - (847.0d * d9)) / 300.0d) + ((((d9 * 49.0d) + 49.0d) * d) / 25.0d)))) * d) - 15.28888888888889d) * d) - 15.28888888888889d;
            double d19 = ((((((2254.0d - (1029.0d * d9)) / 900.0d) + (d * ((((847.0d * d9) - 0.00324249267578125d) / 300.0d) + ((d * (49.0d - (d9 * 49.0d))) / 25.0d)))) * d) - 15.28888888888889d) * d) - 15.28888888888889d;
            double d20 = (((((-0.75d * d) + 0.25d) * d) - 89.6d) * d) - 89.6d;
            int i = 0;
            while (i < this.interpolatedState.length) {
                double d21 = this.yDotK[0][i];
                double d22 = this.yDotK[1][i];
                double d23 = this.yDotK[2][i];
                double d24 = this.yDotK[3][i];
                double d25 = this.yDotK[4][i];
                double d26 = this.yDotK[5][i];
                double d27 = this.yDotK[6][i];
                double d28 = d4;
                double d29 = d22 * 0.0d;
                this.interpolatedState[i] = this.currentState[i] + (((d15 * d21) + d29 + (d16 * d23) + (d17 * d24) + (d18 * d25) + (d19 * d26) + (d20 * d27)) * d2);
                this.interpolatedDerivatives[i] = (d21 * d28) + d29 + (d13 * d23) + (d14 * d24) + (d25 * d10) + (d26 * d11) + (d27 * d12);
                i++;
                d4 = d28;
            }
            return;
        }
        double d30 = (((((((d3 / 5.0d) - 0.3828125d) * d) + 12.0d) * d) - 0.825d) * d) + 1.0d;
        double d31 = ((((((d5 / 5.0d) - 0.08854166666666667d) * d) + 35.55555555555556d) * d) - 0.6333333333333333d) * d;
        double d32 = ((((((d7 / 25.0d) + 48.6d) * d) - 0.12421875d) * d) + 6.48d) * d;
        double d33 = ((((d9 * 343.0d) + 833.0d) / 300.0d) + ((((-637.0d - (d9 * 357.0d)) / 90.0d) + (((((d9 * 287.0d) + 392.0d) / 60.0d) + (((-49.0d - (d9 * 49.0d)) * d) / 25.0d)) * d)) * d)) * d;
        double d34 = d * (((833.0d - (343.0d * d9)) / 300.0d) + (d * ((((357.0d * d9) - 0.00685882568359375d) / 90.0d) + (d * (((392.0d - (287.0d * d9)) / 60.0d) + ((((d9 * 49.0d) - 0.091796875d) * d) / 25.0d))))));
        double d35 = ((((0.75d * d) - 4.0d) * d) + 0.3d) * d;
        int i2 = 0;
        while (i2 < this.interpolatedState.length) {
            double d36 = this.yDotK[0][i2];
            double d37 = this.yDotK[1][i2];
            double d38 = this.yDotK[2][i2];
            double d39 = this.yDotK[3][i2];
            double d40 = this.yDotK[4][i2];
            double d41 = this.yDotK[5][i2];
            double d42 = this.yDotK[6][i2];
            double d43 = d8;
            double d44 = d6;
            double d45 = d37 * 0.0d;
            this.interpolatedState[i2] = this.previousState[i2] + (this.h * d * ((d30 * d36) + d45 + (d31 * d38) + (d32 * d39) + (d33 * d40) + (d34 * d41) + (d35 * d42)));
            this.interpolatedDerivatives[i2] = (d36 * d4) + d45 + (d44 * d38) + (d43 * d39) + (d40 * d10) + (d41 * d11) + (d42 * d12);
            i2++;
            d8 = d43;
            d6 = d44;
        }
    }
}
