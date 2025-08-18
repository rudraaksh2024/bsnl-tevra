package org.apache.commons.math3.ode.nonstiff;

import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.MaxCountExceededException;
import org.apache.commons.math3.exception.NoBracketingException;
import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.ode.EquationsMapper;
import org.apache.commons.math3.ode.ExpandableStatefulODE;
import org.apache.commons.math3.ode.sampling.NordsieckStepInterpolator;
import org.apache.commons.math3.util.FastMath;

public class AdamsBashforthIntegrator extends AdamsIntegrator {
    private static final String METHOD_NAME = "Adams-Bashforth";

    public AdamsBashforthIntegrator(int i, double d, double d2, double d3, double d4) throws NumberIsTooSmallException {
        super(METHOD_NAME, i, i, d, d2, d3, d4);
    }

    public AdamsBashforthIntegrator(int i, double d, double d2, double[] dArr, double[] dArr2) throws IllegalArgumentException {
        super(METHOD_NAME, i, i, d, d2, dArr, dArr2);
    }

    private double errorEstimation(double[] dArr, double[] dArr2, double[] dArr3, RealMatrix realMatrix) {
        double d;
        double d2;
        double d3 = 0.0d;
        for (int i = 0; i < this.mainSetDimension; i++) {
            double abs = FastMath.abs(dArr2[i]);
            if (this.vecAbsoluteTolerance == null) {
                d2 = this.scalAbsoluteTolerance;
                d = this.scalRelativeTolerance;
            } else {
                d2 = this.vecAbsoluteTolerance[i];
                d = this.vecRelativeTolerance[i];
            }
            double d4 = d2 + (d * abs);
            int i2 = realMatrix.getRowDimension() % 2 == 0 ? -1 : 1;
            double d5 = 0.0d;
            for (int rowDimension = realMatrix.getRowDimension() - 1; rowDimension >= 0; rowDimension--) {
                d5 += ((double) i2) * realMatrix.getEntry(rowDimension, i);
                i2 = -i2;
            }
            RealMatrix realMatrix2 = realMatrix;
            double d6 = ((dArr2[i] - dArr[i]) + (d5 - dArr3[i])) / d4;
            d3 += d6 * d6;
        }
        return FastMath.sqrt(d3 / ((double) this.mainSetDimension));
    }

    public void integrate(ExpandableStatefulODE expandableStatefulODE, double d) throws NumberIsTooSmallException, DimensionMismatchException, MaxCountExceededException, NoBracketingException {
        ExpandableStatefulODE expandableStatefulODE2 = expandableStatefulODE;
        sanityChecks(expandableStatefulODE, d);
        setEquations(expandableStatefulODE);
        boolean z = d > expandableStatefulODE.getTime();
        double[] completeState = expandableStatefulODE.getCompleteState();
        double[] dArr = new double[completeState.length];
        NordsieckStepInterpolator nordsieckStepInterpolator = new NordsieckStepInterpolator();
        nordsieckStepInterpolator.reinitialize(completeState, z, expandableStatefulODE.getPrimaryMapper(), expandableStatefulODE.getSecondaryMappers());
        double[] dArr2 = completeState;
        double d2 = d;
        initIntegration(expandableStatefulODE.getTime(), dArr2, d2);
        start(expandableStatefulODE.getTime(), dArr2, d2);
        NordsieckStepInterpolator nordsieckStepInterpolator2 = nordsieckStepInterpolator;
        nordsieckStepInterpolator.reinitialize(this.stepStart, this.stepSize, this.scaled, this.nordsieck);
        nordsieckStepInterpolator2.storeTime(this.stepStart);
        double d3 = this.stepSize;
        nordsieckStepInterpolator2.rescale(d3);
        this.isLastStep = false;
        while (true) {
            nordsieckStepInterpolator2.shift();
            double[] dArr3 = new double[completeState.length];
            int length = completeState.length;
            double[] dArr4 = new double[length];
            Array2DRowRealMatrix array2DRowRealMatrix = null;
            double d4 = 10.0d;
            while (d4 >= 1.0d) {
                double d5 = this.stepStart + d3;
                nordsieckStepInterpolator2.storeTime(d5);
                ExpandableStatefulODE expandable = getExpandable();
                expandable.getPrimaryMapper().insertEquationData(nordsieckStepInterpolator2.getInterpolatedState(), dArr3);
                EquationsMapper[] secondaryMappers = expandable.getSecondaryMappers();
                int length2 = secondaryMappers.length;
                int i = 0;
                int i2 = 0;
                while (i2 < length2) {
                    secondaryMappers[i2].insertEquationData(nordsieckStepInterpolator2.getInterpolatedSecondaryState(i), dArr3);
                    i++;
                    i2++;
                    length2 = length2;
                    secondaryMappers = secondaryMappers;
                }
                computeDerivatives(d5, dArr3, dArr);
                for (int i3 = 0; i3 < length; i3++) {
                    dArr4[i3] = dArr[i3] * d3;
                }
                array2DRowRealMatrix = updateHighOrderDerivativesPhase1(this.nordsieck);
                updateHighOrderDerivativesPhase2(this.scaled, dArr4, array2DRowRealMatrix);
                d4 = errorEstimation(completeState, dArr3, dArr4, array2DRowRealMatrix);
                if (d4 >= 1.0d) {
                    d3 = filterStep(d3 * computeStepGrowShrinkFactor(d4), z, false);
                    nordsieckStepInterpolator2.rescale(d3);
                }
                ExpandableStatefulODE expandableStatefulODE3 = expandableStatefulODE;
            }
            this.stepSize = d3;
            boolean z2 = z;
            double d6 = this.stepStart + this.stepSize;
            double d7 = d4;
            nordsieckStepInterpolator2.reinitialize(d6, this.stepSize, dArr4, array2DRowRealMatrix);
            nordsieckStepInterpolator2.storeTime(d6);
            System.arraycopy(dArr3, 0, completeState, 0, completeState.length);
            double d8 = d3;
            this.stepStart = acceptStep(nordsieckStepInterpolator2, completeState, dArr, d);
            this.scaled = dArr4;
            this.nordsieck = array2DRowRealMatrix;
            nordsieckStepInterpolator2.reinitialize(d6, this.stepSize, this.scaled, this.nordsieck);
            if (!this.isLastStep) {
                nordsieckStepInterpolator2.storeTime(this.stepStart);
                if (this.resetOccurred) {
                    start(this.stepStart, completeState, d);
                    nordsieckStepInterpolator2.reinitialize(this.stepStart, this.stepSize, this.scaled, this.nordsieck);
                }
                double computeStepGrowShrinkFactor = this.stepSize * computeStepGrowShrinkFactor(d7);
                double d9 = this.stepStart + computeStepGrowShrinkFactor;
                d3 = filterStep(computeStepGrowShrinkFactor, z2, !z2 ? d9 <= d : d9 >= d);
                double d10 = this.stepStart + d3;
                if (!z2 ? d10 <= d : d10 >= d) {
                    d3 = d - this.stepStart;
                }
                nordsieckStepInterpolator2.rescale(d3);
            } else {
                d3 = d8;
            }
            if (this.isLastStep) {
                ExpandableStatefulODE expandableStatefulODE4 = expandableStatefulODE;
                expandableStatefulODE4.setTime(this.stepStart);
                expandableStatefulODE4.setCompleteState(completeState);
                resetInternalState();
                return;
            }
            z = z2;
            ExpandableStatefulODE expandableStatefulODE5 = expandableStatefulODE;
        }
    }
}
