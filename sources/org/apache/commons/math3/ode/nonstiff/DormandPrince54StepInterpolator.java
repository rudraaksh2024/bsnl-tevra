package org.apache.commons.math3.ode.nonstiff;

import org.apache.commons.math3.ode.AbstractIntegrator;
import org.apache.commons.math3.ode.EquationsMapper;
import org.apache.commons.math3.ode.sampling.StepInterpolator;

class DormandPrince54StepInterpolator extends RungeKuttaStepInterpolator {
    private static final double A70 = 0.09114583333333333d;
    private static final double A72 = 0.44923629829290207d;
    private static final double A73 = 0.6510416666666666d;
    private static final double A74 = -0.322376179245283d;
    private static final double A75 = 0.13095238095238096d;
    private static final double D0 = -1.1270175653862835d;
    private static final double D2 = 2.675424484351598d;
    private static final double D3 = -5.685526961588504d;
    private static final double D4 = 3.5219323679207912d;
    private static final double D5 = -1.7672812570757455d;
    private static final double D6 = 2.382468931778144d;
    private static final long serialVersionUID = 20111120;
    private double[] v1;
    private double[] v2;
    private double[] v3;
    private double[] v4;
    private boolean vectorsInitialized;

    public DormandPrince54StepInterpolator() {
        this.v1 = null;
        this.v2 = null;
        this.v3 = null;
        this.v4 = null;
        this.vectorsInitialized = false;
    }

    DormandPrince54StepInterpolator(DormandPrince54StepInterpolator dormandPrince54StepInterpolator) {
        super(dormandPrince54StepInterpolator);
        double[] dArr = dormandPrince54StepInterpolator.v1;
        if (dArr == null) {
            this.v1 = null;
            this.v2 = null;
            this.v3 = null;
            this.v4 = null;
            this.vectorsInitialized = false;
            return;
        }
        this.v1 = (double[]) dArr.clone();
        this.v2 = (double[]) dormandPrince54StepInterpolator.v2.clone();
        this.v3 = (double[]) dormandPrince54StepInterpolator.v3.clone();
        this.v4 = (double[]) dormandPrince54StepInterpolator.v4.clone();
        this.vectorsInitialized = dormandPrince54StepInterpolator.vectorsInitialized;
    }

    /* access modifiers changed from: protected */
    public StepInterpolator doCopy() {
        return new DormandPrince54StepInterpolator(this);
    }

    public void reinitialize(AbstractIntegrator abstractIntegrator, double[] dArr, double[][] dArr2, boolean z, EquationsMapper equationsMapper, EquationsMapper[] equationsMapperArr) {
        super.reinitialize(abstractIntegrator, dArr, dArr2, z, equationsMapper, equationsMapperArr);
        this.v1 = null;
        this.v2 = null;
        this.v3 = null;
        this.v4 = null;
        this.vectorsInitialized = false;
    }

    public void storeTime(double d) {
        super.storeTime(d);
        this.vectorsInitialized = false;
    }

    /* access modifiers changed from: protected */
    public void computeInterpolatedStateAndDerivatives(double d, double d2) {
        char c = 0;
        if (!this.vectorsInitialized) {
            if (this.v1 == null) {
                this.v1 = new double[this.interpolatedState.length];
                this.v2 = new double[this.interpolatedState.length];
                this.v3 = new double[this.interpolatedState.length];
                this.v4 = new double[this.interpolatedState.length];
            }
            int i = 0;
            while (i < this.interpolatedState.length) {
                double d3 = this.yDotK[c][i];
                double d4 = this.yDotK[2][i];
                double d5 = this.yDotK[3][i];
                double d6 = this.yDotK[4][i];
                double d7 = this.yDotK[5][i];
                double d8 = this.yDotK[6][i];
                double[] dArr = this.v1;
                double d9 = (A70 * d3) + (A72 * d4) + (A73 * d5) + (A74 * d6) + (A75 * d7);
                dArr[i] = d9;
                double d10 = d3 - d9;
                this.v2[i] = d10;
                this.v3[i] = (dArr[i] - d10) - d8;
                this.v4[i] = (d3 * D0) + (d4 * D2) + (d5 * D3) + (d6 * D4) + (d7 * D5) + (d8 * D6);
                i++;
                c = 0;
            }
            this.vectorsInitialized = true;
        }
        double d11 = 1.0d - d;
        double d12 = d * 2.0d;
        double d13 = 1.0d - d12;
        double d14 = (2.0d - (d * 3.0d)) * d;
        double d15 = d12 * (((d12 - 3.0d) * d) + 1.0d);
        if (this.previousState == null || d > 0.5d) {
            double d16 = d14;
            double d17 = d15;
            double d18 = d13;
            for (int i2 = 0; i2 < this.interpolatedState.length; i2++) {
                this.interpolatedState[i2] = this.currentState[i2] - ((this.v1[i2] - ((this.v2[i2] + ((this.v3[i2] + (this.v4[i2] * d11)) * d)) * d)) * d2);
                this.interpolatedDerivatives[i2] = this.v1[i2] + (d18 * this.v2[i2]) + (this.v3[i2] * d16) + (this.v4[i2] * d17);
            }
            return;
        }
        int i3 = 0;
        while (i3 < this.interpolatedState.length) {
            double d19 = d15;
            double d20 = d14;
            double d21 = d13;
            this.interpolatedState[i3] = this.previousState[i3] + (this.h * d * (this.v1[i3] + ((this.v2[i3] + ((this.v3[i3] + (this.v4[i3] * d11)) * d)) * d11)));
            this.interpolatedDerivatives[i3] = this.v1[i3] + (d21 * this.v2[i3]) + (this.v3[i3] * d20) + (this.v4[i3] * d19);
            i3++;
            d15 = d19;
            d14 = d20;
            d13 = d21;
        }
    }
}
