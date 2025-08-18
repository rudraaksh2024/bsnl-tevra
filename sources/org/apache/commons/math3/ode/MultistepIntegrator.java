package org.apache.commons.math3.ode;

import java.lang.reflect.Array;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.MathIllegalStateException;
import org.apache.commons.math3.exception.MaxCountExceededException;
import org.apache.commons.math3.exception.NoBracketingException;
import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.ode.nonstiff.AdaptiveStepsizeIntegrator;
import org.apache.commons.math3.ode.nonstiff.DormandPrince853Integrator;
import org.apache.commons.math3.ode.sampling.StepHandler;
import org.apache.commons.math3.ode.sampling.StepInterpolator;
import org.apache.commons.math3.util.FastMath;

public abstract class MultistepIntegrator extends AdaptiveStepsizeIntegrator {
    private double exp;
    private double maxGrowth;
    private double minReduction;
    private final int nSteps;
    protected Array2DRowRealMatrix nordsieck;
    private double safety;
    protected double[] scaled;
    private FirstOrderIntegrator starter;

    @Deprecated
    public interface NordsieckTransformer {
        Array2DRowRealMatrix initializeHighOrderDerivatives(double d, double[] dArr, double[][] dArr2, double[][] dArr3);
    }

    /* access modifiers changed from: protected */
    public abstract Array2DRowRealMatrix initializeHighOrderDerivatives(double d, double[] dArr, double[][] dArr2, double[][] dArr3);

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    protected MultistepIntegrator(String str, int i, int i2, double d, double d2, double d3, double d4) throws NumberIsTooSmallException {
        super(str, d, d2, d3, d4);
        int i3 = i;
        if (i3 >= 2) {
            this.starter = new DormandPrince853Integrator(d, d2, d3, d4);
            this.nSteps = i3;
            this.exp = -1.0d / ((double) i2);
            setSafety(0.9d);
            setMinReduction(0.2d);
            setMaxGrowth(FastMath.pow(2.0d, -this.exp));
            return;
        }
        throw new NumberIsTooSmallException(LocalizedFormats.INTEGRATION_METHOD_NEEDS_AT_LEAST_TWO_PREVIOUS_POINTS, Integer.valueOf(i), 2, true);
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    protected MultistepIntegrator(String str, int i, int i2, double d, double d2, double[] dArr, double[] dArr2) {
        super(str, d, d2, dArr, dArr2);
        this.starter = new DormandPrince853Integrator(d, d2, dArr, dArr2);
        this.nSteps = i;
        this.exp = -1.0d / ((double) i2);
        setSafety(0.9d);
        setMinReduction(0.2d);
        setMaxGrowth(FastMath.pow(2.0d, -this.exp));
    }

    public ODEIntegrator getStarterIntegrator() {
        return this.starter;
    }

    public void setStarterIntegrator(FirstOrderIntegrator firstOrderIntegrator) {
        this.starter = firstOrderIntegrator;
    }

    /* access modifiers changed from: protected */
    public void start(double d, double[] dArr, double d2) throws DimensionMismatchException, NumberIsTooSmallException, MaxCountExceededException, NoBracketingException {
        this.starter.clearEventHandlers();
        this.starter.clearStepHandlers();
        this.starter.addStepHandler(new NordsieckInitializer((this.nSteps + 3) / 2, dArr.length));
        try {
            FirstOrderIntegrator firstOrderIntegrator = this.starter;
            if (firstOrderIntegrator instanceof AbstractIntegrator) {
                ((AbstractIntegrator) firstOrderIntegrator).integrate(getExpandable(), d2);
            } else {
                firstOrderIntegrator.integrate(new FirstOrderDifferentialEquations() {
                    public int getDimension() {
                        return MultistepIntegrator.this.getExpandable().getTotalDimension();
                    }

                    public void computeDerivatives(double d, double[] dArr, double[] dArr2) {
                        MultistepIntegrator.this.getExpandable().computeDerivatives(d, dArr, dArr2);
                    }
                }, d, dArr, d2, new double[dArr.length]);
            }
            throw new MathIllegalStateException(LocalizedFormats.MULTISTEP_STARTER_STOPPED_EARLY, new Object[0]);
        } catch (InitializationCompletedMarkerException unused) {
            getCounter().increment(this.starter.getEvaluations());
            this.starter.clearStepHandlers();
        }
    }

    public double getMinReduction() {
        return this.minReduction;
    }

    public void setMinReduction(double d) {
        this.minReduction = d;
    }

    public double getMaxGrowth() {
        return this.maxGrowth;
    }

    public void setMaxGrowth(double d) {
        this.maxGrowth = d;
    }

    public double getSafety() {
        return this.safety;
    }

    public void setSafety(double d) {
        this.safety = d;
    }

    public int getNSteps() {
        return this.nSteps;
    }

    /* access modifiers changed from: protected */
    public double computeStepGrowShrinkFactor(double d) {
        return FastMath.min(this.maxGrowth, FastMath.max(this.minReduction, this.safety * FastMath.pow(d, this.exp)));
    }

    private class NordsieckInitializer implements StepHandler {
        private int count = 0;
        private final double[] t;
        private final double[][] y;
        private final double[][] yDot;

        public void init(double d, double[] dArr, double d2) {
        }

        NordsieckInitializer(int i, int i2) {
            this.t = new double[i];
            int[] iArr = new int[2];
            iArr[1] = i2;
            iArr[0] = i;
            this.y = (double[][]) Array.newInstance(Double.TYPE, iArr);
            int[] iArr2 = new int[2];
            iArr2[1] = i2;
            iArr2[0] = i;
            this.yDot = (double[][]) Array.newInstance(Double.TYPE, iArr2);
        }

        public void handleStep(StepInterpolator stepInterpolator, boolean z) throws MaxCountExceededException {
            double previousTime = stepInterpolator.getPreviousTime();
            double currentTime = stepInterpolator.getCurrentTime();
            if (this.count == 0) {
                stepInterpolator.setInterpolatedTime(previousTime);
                this.t[0] = previousTime;
                ExpandableStatefulODE expandable = MultistepIntegrator.this.getExpandable();
                EquationsMapper primaryMapper = expandable.getPrimaryMapper();
                primaryMapper.insertEquationData(stepInterpolator.getInterpolatedState(), this.y[this.count]);
                primaryMapper.insertEquationData(stepInterpolator.getInterpolatedDerivatives(), this.yDot[this.count]);
                int i = 0;
                for (EquationsMapper equationsMapper : expandable.getSecondaryMappers()) {
                    equationsMapper.insertEquationData(stepInterpolator.getInterpolatedSecondaryState(i), this.y[this.count]);
                    equationsMapper.insertEquationData(stepInterpolator.getInterpolatedSecondaryDerivatives(i), this.yDot[this.count]);
                    i++;
                }
            }
            this.count++;
            stepInterpolator.setInterpolatedTime(currentTime);
            this.t[this.count] = currentTime;
            ExpandableStatefulODE expandable2 = MultistepIntegrator.this.getExpandable();
            EquationsMapper primaryMapper2 = expandable2.getPrimaryMapper();
            primaryMapper2.insertEquationData(stepInterpolator.getInterpolatedState(), this.y[this.count]);
            primaryMapper2.insertEquationData(stepInterpolator.getInterpolatedDerivatives(), this.yDot[this.count]);
            int i2 = 0;
            for (EquationsMapper equationsMapper2 : expandable2.getSecondaryMappers()) {
                equationsMapper2.insertEquationData(stepInterpolator.getInterpolatedSecondaryState(i2), this.y[this.count]);
                equationsMapper2.insertEquationData(stepInterpolator.getInterpolatedSecondaryDerivatives(i2), this.yDot[this.count]);
                i2++;
            }
            int i3 = this.count;
            double[] dArr = this.t;
            if (i3 == dArr.length - 1) {
                MultistepIntegrator.this.stepStart = dArr[0];
                MultistepIntegrator multistepIntegrator = MultistepIntegrator.this;
                double[] dArr2 = this.t;
                multistepIntegrator.stepSize = (dArr2[dArr2.length - 1] - dArr2[0]) / ((double) (dArr2.length - 1));
                MultistepIntegrator.this.scaled = (double[]) this.yDot[0].clone();
                for (int i4 = 0; i4 < MultistepIntegrator.this.scaled.length; i4++) {
                    double[] dArr3 = MultistepIntegrator.this.scaled;
                    dArr3[i4] = dArr3[i4] * MultistepIntegrator.this.stepSize;
                }
                MultistepIntegrator multistepIntegrator2 = MultistepIntegrator.this;
                multistepIntegrator2.nordsieck = multistepIntegrator2.initializeHighOrderDerivatives(multistepIntegrator2.stepSize, this.t, this.y, this.yDot);
                throw new InitializationCompletedMarkerException();
            }
        }
    }

    private static class InitializationCompletedMarkerException extends RuntimeException {
        private static final long serialVersionUID = -1914085471038046418L;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        InitializationCompletedMarkerException() {
            super((Throwable) null);
            Throwable th = null;
        }
    }
}
