package org.apache.commons.math3.ode;

import org.apache.commons.math3.Field;
import org.apache.commons.math3.RealFieldElement;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.MathIllegalStateException;
import org.apache.commons.math3.exception.MaxCountExceededException;
import org.apache.commons.math3.exception.NoBracketingException;
import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.linear.Array2DRowFieldMatrix;
import org.apache.commons.math3.ode.nonstiff.AdaptiveStepsizeFieldIntegrator;
import org.apache.commons.math3.ode.nonstiff.DormandPrince853FieldIntegrator;
import org.apache.commons.math3.ode.sampling.FieldStepHandler;
import org.apache.commons.math3.ode.sampling.FieldStepInterpolator;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.MathArrays;
import org.apache.commons.math3.util.MathUtils;

public abstract class MultistepFieldIntegrator<T extends RealFieldElement<T>> extends AdaptiveStepsizeFieldIntegrator<T> {
    private double exp;
    private double maxGrowth;
    private double minReduction;
    private final int nSteps;
    protected Array2DRowFieldMatrix<T> nordsieck;
    private double safety;
    protected T[] scaled;
    private FirstOrderFieldIntegrator<T> starter;

    /* access modifiers changed from: protected */
    public abstract Array2DRowFieldMatrix<T> initializeHighOrderDerivatives(T t, T[] tArr, T[][] tArr2, T[][] tArr3);

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    protected MultistepFieldIntegrator(Field<T> field, String str, int i, int i2, double d, double d2, double d3, double d4) throws NumberIsTooSmallException {
        super(field, str, d, d2, d3, d4);
        int i3 = i;
        if (i3 >= 2) {
            this.starter = new DormandPrince853FieldIntegrator(field, d, d2, d3, d4);
            this.nSteps = i3;
            this.exp = -1.0d / ((double) i2);
            setSafety(0.9d);
            setMinReduction(0.2d);
            setMaxGrowth(FastMath.pow(2.0d, -this.exp));
            return;
        }
        throw new NumberIsTooSmallException(LocalizedFormats.INTEGRATION_METHOD_NEEDS_AT_LEAST_TWO_PREVIOUS_POINTS, Integer.valueOf(i), 2, true);
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    protected MultistepFieldIntegrator(org.apache.commons.math3.Field<T> r11, java.lang.String r12, int r13, int r14, double r15, double r17, double[] r19, double[] r20) {
        /*
            r10 = this;
            r9 = r10
            r0 = r10
            r1 = r11
            r2 = r12
            r3 = r15
            r5 = r17
            r7 = r19
            r8 = r20
            r0.<init>(r1, (java.lang.String) r2, (double) r3, (double) r5, (double[]) r7, (double[]) r8)
            org.apache.commons.math3.ode.nonstiff.DormandPrince853FieldIntegrator r8 = new org.apache.commons.math3.ode.nonstiff.DormandPrince853FieldIntegrator
            r0 = r8
            r2 = r15
            r4 = r17
            r6 = r19
            r7 = r20
            r0.<init>(r1, (double) r2, (double) r4, (double[]) r6, (double[]) r7)
            r9.starter = r8
            r0 = r13
            r9.nSteps = r0
            r0 = -4616189618054758400(0xbff0000000000000, double:-1.0)
            r2 = r14
            double r2 = (double) r2
            double r0 = r0 / r2
            r9.exp = r0
            r0 = 4606281698874543309(0x3feccccccccccccd, double:0.9)
            r10.setSafety(r0)
            r0 = 4596373779694328218(0x3fc999999999999a, double:0.2)
            r10.setMinReduction(r0)
            double r0 = r9.exp
            double r0 = -r0
            r2 = 4611686018427387904(0x4000000000000000, double:2.0)
            double r0 = org.apache.commons.math3.util.FastMath.pow((double) r2, (double) r0)
            r10.setMaxGrowth(r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.math3.ode.MultistepFieldIntegrator.<init>(org.apache.commons.math3.Field, java.lang.String, int, int, double, double, double[], double[]):void");
    }

    public FirstOrderFieldIntegrator<T> getStarterIntegrator() {
        return this.starter;
    }

    public void setStarterIntegrator(FirstOrderFieldIntegrator<T> firstOrderFieldIntegrator) {
        this.starter = firstOrderFieldIntegrator;
    }

    /* access modifiers changed from: protected */
    public void start(FieldExpandableODE<T> fieldExpandableODE, FieldODEState<T> fieldODEState, T t) throws DimensionMismatchException, NumberIsTooSmallException, MaxCountExceededException, NoBracketingException {
        this.starter.clearEventHandlers();
        this.starter.clearStepHandlers();
        this.starter.addStepHandler(new FieldNordsieckInitializer(fieldExpandableODE.getMapper(), (this.nSteps + 3) / 2));
        try {
            this.starter.integrate(fieldExpandableODE, fieldODEState, t);
            throw new MathIllegalStateException(LocalizedFormats.MULTISTEP_STARTER_STOPPED_EARLY, new Object[0]);
        } catch (InitializationCompletedMarkerException unused) {
            getEvaluationsCounter().increment(this.starter.getEvaluations());
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
    public void rescale(T t) {
        RealFieldElement realFieldElement = (RealFieldElement) t.divide(getStepSize());
        int i = 0;
        while (true) {
            T[] tArr = this.scaled;
            if (i >= tArr.length) {
                break;
            }
            tArr[i] = (RealFieldElement) tArr[i].multiply(realFieldElement);
            i++;
        }
        RealFieldElement[][] realFieldElementArr = (RealFieldElement[][]) this.nordsieck.getDataRef();
        RealFieldElement realFieldElement2 = realFieldElement;
        for (RealFieldElement[] realFieldElementArr2 : realFieldElementArr) {
            realFieldElement2 = (RealFieldElement) realFieldElement2.multiply(realFieldElement);
            for (int i2 = 0; i2 < realFieldElementArr2.length; i2++) {
                realFieldElementArr2[i2] = (RealFieldElement) realFieldElementArr2[i2].multiply(realFieldElement2);
            }
        }
        setStepSize(t);
    }

    /* access modifiers changed from: protected */
    public T computeStepGrowShrinkFactor(T t) {
        return MathUtils.min((RealFieldElement) ((RealFieldElement) t.getField().getZero()).add(this.maxGrowth), MathUtils.max((RealFieldElement) ((RealFieldElement) t.getField().getZero()).add(this.minReduction), (RealFieldElement) ((RealFieldElement) t.pow(this.exp)).multiply(this.safety)));
    }

    private class FieldNordsieckInitializer implements FieldStepHandler<T> {
        private int count = 0;
        private final FieldEquationsMapper<T> mapper;
        private FieldODEStateAndDerivative<T> savedStart;
        private final T[] t;
        private final T[][] y;
        private final T[][] yDot;

        public void init(FieldODEStateAndDerivative<T> fieldODEStateAndDerivative, T t2) {
        }

        FieldNordsieckInitializer(FieldEquationsMapper<T> fieldEquationsMapper, int i) {
            this.mapper = fieldEquationsMapper;
            this.t = (RealFieldElement[]) MathArrays.buildArray(MultistepFieldIntegrator.this.getField(), i);
            this.y = (RealFieldElement[][]) MathArrays.buildArray(MultistepFieldIntegrator.this.getField(), i, -1);
            this.yDot = (RealFieldElement[][]) MathArrays.buildArray(MultistepFieldIntegrator.this.getField(), i, -1);
        }

        public void handleStep(FieldStepInterpolator<T> fieldStepInterpolator, boolean z) throws MaxCountExceededException {
            if (this.count == 0) {
                FieldODEStateAndDerivative<T> previousState = fieldStepInterpolator.getPreviousState();
                this.savedStart = previousState;
                this.t[this.count] = previousState.getTime();
                this.y[this.count] = this.mapper.mapState(previousState);
                this.yDot[this.count] = this.mapper.mapDerivative(previousState);
            }
            this.count++;
            FieldODEStateAndDerivative<T> currentState = fieldStepInterpolator.getCurrentState();
            this.t[this.count] = currentState.getTime();
            this.y[this.count] = this.mapper.mapState(currentState);
            this.yDot[this.count] = this.mapper.mapDerivative(currentState);
            int i = this.count;
            T[] tArr = this.t;
            if (i == tArr.length - 1) {
                MultistepFieldIntegrator.this.setStepSize((RealFieldElement) ((RealFieldElement) tArr[tArr.length - 1].subtract(tArr[0])).divide((double) (this.t.length - 1)));
                MultistepFieldIntegrator multistepFieldIntegrator = MultistepFieldIntegrator.this;
                multistepFieldIntegrator.scaled = (RealFieldElement[]) MathArrays.buildArray(multistepFieldIntegrator.getField(), this.yDot[0].length);
                for (int i2 = 0; i2 < MultistepFieldIntegrator.this.scaled.length; i2++) {
                    MultistepFieldIntegrator.this.scaled[i2] = (RealFieldElement) this.yDot[0][i2].multiply(MultistepFieldIntegrator.this.getStepSize());
                }
                MultistepFieldIntegrator multistepFieldIntegrator2 = MultistepFieldIntegrator.this;
                multistepFieldIntegrator2.nordsieck = multistepFieldIntegrator2.initializeHighOrderDerivatives(multistepFieldIntegrator2.getStepSize(), this.t, this.y, this.yDot);
                MultistepFieldIntegrator.this.setStepStart(this.savedStart);
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
