package org.apache.commons.math3.ode.nonstiff;

import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.MaxCountExceededException;
import org.apache.commons.math3.exception.NoBracketingException;
import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.ode.AbstractIntegrator;
import org.apache.commons.math3.ode.ExpandableStatefulODE;
import org.apache.commons.math3.ode.FirstOrderDifferentialEquations;
import org.apache.commons.math3.util.FastMath;

public abstract class RungeKuttaIntegrator extends AbstractIntegrator {
    private final double[][] a;
    private final double[] b;
    private final double[] c;
    private final RungeKuttaStepInterpolator prototype;
    private final double step;

    protected RungeKuttaIntegrator(String str, double[] dArr, double[][] dArr2, double[] dArr3, RungeKuttaStepInterpolator rungeKuttaStepInterpolator, double d) {
        super(str);
        this.c = dArr;
        this.a = dArr2;
        this.b = dArr3;
        this.prototype = rungeKuttaStepInterpolator;
        this.step = FastMath.abs(d);
    }

    public void integrate(ExpandableStatefulODE expandableStatefulODE, double d) throws NumberIsTooSmallException, DimensionMismatchException, MaxCountExceededException, NoBracketingException {
        ExpandableStatefulODE expandableStatefulODE2 = expandableStatefulODE;
        sanityChecks(expandableStatefulODE, d);
        setEquations(expandableStatefulODE);
        int i = 0;
        boolean z = d > expandableStatefulODE.getTime();
        double[] completeState = expandableStatefulODE.getCompleteState();
        double[] dArr = (double[]) completeState.clone();
        int length = this.c.length + 1;
        double[][] dArr2 = new double[length][];
        for (int i2 = 0; i2 < length; i2++) {
            dArr2[i2] = new double[completeState.length];
        }
        double[] dArr3 = (double[]) completeState.clone();
        double[] dArr4 = new double[completeState.length];
        RungeKuttaStepInterpolator rungeKuttaStepInterpolator = (RungeKuttaStepInterpolator) this.prototype.copy();
        RungeKuttaStepInterpolator rungeKuttaStepInterpolator2 = rungeKuttaStepInterpolator;
        double[] dArr5 = dArr4;
        double[] dArr6 = dArr3;
        rungeKuttaStepInterpolator.reinitialize(this, dArr3, dArr2, z, expandableStatefulODE.getPrimaryMapper(), expandableStatefulODE.getSecondaryMappers());
        rungeKuttaStepInterpolator2.storeTime(expandableStatefulODE.getTime());
        this.stepStart = expandableStatefulODE.getTime();
        if (z) {
            double d2 = this.stepStart;
            double d3 = this.step;
            if (d2 + d3 >= d) {
                this.stepSize = d - this.stepStart;
            } else {
                this.stepSize = d3;
            }
        } else {
            double d4 = this.stepStart;
            double d5 = this.step;
            if (d4 - d5 <= d) {
                this.stepSize = d - this.stepStart;
            } else {
                this.stepSize = -d5;
            }
        }
        initIntegration(expandableStatefulODE.getTime(), completeState, d);
        this.isLastStep = false;
        while (true) {
            rungeKuttaStepInterpolator2.shift();
            computeDerivatives(this.stepStart, dArr, dArr2[i]);
            int i3 = 1;
            while (i3 < length) {
                int i4 = i;
                while (i4 < completeState.length) {
                    int i5 = i3 - 1;
                    double d6 = this.a[i5][i] * dArr2[i][i4];
                    for (int i6 = 1; i6 < i3; i6++) {
                        d6 += this.a[i5][i6] * dArr2[i6][i4];
                    }
                    dArr6[i4] = dArr[i4] + (this.stepSize * d6);
                    i4++;
                    z = z;
                    i = 0;
                }
                boolean z2 = z;
                computeDerivatives(this.stepStart + (this.c[i3 - 1] * this.stepSize), dArr6, dArr2[i3]);
                i3++;
                i = 0;
            }
            boolean z3 = z;
            double[] dArr7 = dArr6;
            for (int i7 = 0; i7 < completeState.length; i7++) {
                double d7 = this.b[0] * dArr2[0][i7];
                for (int i8 = 1; i8 < length; i8++) {
                    d7 += this.b[i8] * dArr2[i8][i7];
                }
                dArr7[i7] = dArr[i7] + (this.stepSize * d7);
            }
            rungeKuttaStepInterpolator2.storeTime(this.stepStart + this.stepSize);
            System.arraycopy(dArr7, 0, dArr, 0, completeState.length);
            double[] dArr8 = dArr5;
            System.arraycopy(dArr2[length - 1], 0, dArr8, 0, completeState.length);
            this.stepStart = acceptStep(rungeKuttaStepInterpolator2, dArr, dArr8, d);
            if (!this.isLastStep) {
                rungeKuttaStepInterpolator2.storeTime(this.stepStart);
                double d8 = this.stepStart + this.stepSize;
                if (!z3 ? d8 <= d : d8 >= d) {
                    this.stepSize = d - this.stepStart;
                }
            }
            if (this.isLastStep) {
                expandableStatefulODE2.setTime(this.stepStart);
                expandableStatefulODE2.setCompleteState(dArr);
                this.stepStart = Double.NaN;
                this.stepSize = Double.NaN;
                return;
            }
            dArr5 = dArr8;
            dArr6 = dArr7;
            i = 0;
            z = z3;
        }
    }

    public double[] singleStep(FirstOrderDifferentialEquations firstOrderDifferentialEquations, double d, double[] dArr, double d2) {
        FirstOrderDifferentialEquations firstOrderDifferentialEquations2 = firstOrderDifferentialEquations;
        double d3 = d;
        double[] dArr2 = dArr;
        double[] dArr3 = (double[]) dArr.clone();
        int i = 1;
        int length = this.c.length + 1;
        double[][] dArr4 = new double[length][];
        for (int i2 = 0; i2 < length; i2++) {
            dArr4[i2] = new double[dArr2.length];
        }
        double[] dArr5 = (double[]) dArr.clone();
        double d4 = d2 - d3;
        firstOrderDifferentialEquations2.computeDerivatives(d3, dArr3, dArr4[0]);
        int i3 = 1;
        while (i3 < length) {
            int i4 = 0;
            while (i4 < dArr2.length) {
                int i5 = i3 - 1;
                double d5 = this.a[i5][0] * dArr4[0][i4];
                for (int i6 = i; i6 < i3; i6++) {
                    d5 += this.a[i5][i6] * dArr4[i6][i4];
                }
                dArr5[i4] = dArr3[i4] + (d5 * d4);
                i4++;
                i = 1;
            }
            firstOrderDifferentialEquations2.computeDerivatives((this.c[i3 - 1] * d4) + d3, dArr5, dArr4[i3]);
            i3++;
            i = 1;
        }
        for (int i7 = 0; i7 < dArr2.length; i7++) {
            double d6 = this.b[0] * dArr4[0][i7];
            for (int i8 = 1; i8 < length; i8++) {
                d6 += this.b[i8] * dArr4[i8][i7];
            }
            dArr3[i7] = dArr3[i7] + (d6 * d4);
        }
        return dArr3;
    }
}
