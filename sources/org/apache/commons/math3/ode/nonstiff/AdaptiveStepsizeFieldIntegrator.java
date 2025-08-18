package org.apache.commons.math3.ode.nonstiff;

import org.apache.commons.math3.Field;
import org.apache.commons.math3.RealFieldElement;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.MaxCountExceededException;
import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.ode.AbstractFieldIntegrator;
import org.apache.commons.math3.ode.FieldEquationsMapper;
import org.apache.commons.math3.ode.FieldODEState;
import org.apache.commons.math3.ode.FieldODEStateAndDerivative;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.MathArrays;
import org.apache.commons.math3.util.MathUtils;

public abstract class AdaptiveStepsizeFieldIntegrator<T extends RealFieldElement<T>> extends AbstractFieldIntegrator<T> {
    private T initialStep;
    protected int mainSetDimension;
    private T maxStep;
    private T minStep;
    protected double scalAbsoluteTolerance;
    protected double scalRelativeTolerance;
    protected double[] vecAbsoluteTolerance;
    protected double[] vecRelativeTolerance;

    public AdaptiveStepsizeFieldIntegrator(Field<T> field, String str, double d, double d2, double d3, double d4) {
        super(field, str);
        setStepSizeControl(d, d2, d3, d4);
        resetInternalState();
    }

    public AdaptiveStepsizeFieldIntegrator(Field<T> field, String str, double d, double d2, double[] dArr, double[] dArr2) {
        super(field, str);
        setStepSizeControl(d, d2, dArr, dArr2);
        resetInternalState();
    }

    public void setStepSizeControl(double d, double d2, double d3, double d4) {
        this.minStep = (RealFieldElement) ((RealFieldElement) getField().getZero()).add(FastMath.abs(d));
        this.maxStep = (RealFieldElement) ((RealFieldElement) getField().getZero()).add(FastMath.abs(d2));
        this.initialStep = (RealFieldElement) ((RealFieldElement) getField().getOne()).negate();
        this.scalAbsoluteTolerance = d3;
        this.scalRelativeTolerance = d4;
        this.vecAbsoluteTolerance = null;
        this.vecRelativeTolerance = null;
    }

    public void setStepSizeControl(double d, double d2, double[] dArr, double[] dArr2) {
        this.minStep = (RealFieldElement) ((RealFieldElement) getField().getZero()).add(FastMath.abs(d));
        this.maxStep = (RealFieldElement) ((RealFieldElement) getField().getZero()).add(FastMath.abs(d2));
        this.initialStep = (RealFieldElement) ((RealFieldElement) getField().getOne()).negate();
        this.scalAbsoluteTolerance = 0.0d;
        this.scalRelativeTolerance = 0.0d;
        this.vecAbsoluteTolerance = (double[]) dArr.clone();
        this.vecRelativeTolerance = (double[]) dArr2.clone();
    }

    public void setInitialStepSize(T t) {
        if (((RealFieldElement) t.subtract(this.minStep)).getReal() < 0.0d || ((RealFieldElement) t.subtract(this.maxStep)).getReal() > 0.0d) {
            this.initialStep = (RealFieldElement) ((RealFieldElement) getField().getOne()).negate();
        } else {
            this.initialStep = t;
        }
    }

    /* access modifiers changed from: protected */
    public void sanityChecks(FieldODEState<T> fieldODEState, T t) throws DimensionMismatchException, NumberIsTooSmallException {
        super.sanityChecks(fieldODEState, t);
        int stateDimension = fieldODEState.getStateDimension();
        this.mainSetDimension = stateDimension;
        double[] dArr = this.vecAbsoluteTolerance;
        if (dArr == null || dArr.length == stateDimension) {
            double[] dArr2 = this.vecRelativeTolerance;
            if (dArr2 != null && dArr2.length != stateDimension) {
                throw new DimensionMismatchException(this.mainSetDimension, this.vecRelativeTolerance.length);
            }
            return;
        }
        throw new DimensionMismatchException(this.mainSetDimension, this.vecAbsoluteTolerance.length);
    }

    public T initializeStep(boolean z, int i, T[] tArr, FieldODEStateAndDerivative<T> fieldODEStateAndDerivative, FieldEquationsMapper<T> fieldEquationsMapper) throws MaxCountExceededException, DimensionMismatchException {
        if (this.initialStep.getReal() > 0.0d) {
            T t = this.initialStep;
            return z ? t : (RealFieldElement) t.negate();
        }
        RealFieldElement[] mapState = fieldEquationsMapper.mapState(fieldODEStateAndDerivative);
        RealFieldElement[] mapDerivative = fieldEquationsMapper.mapDerivative(fieldODEStateAndDerivative);
        RealFieldElement realFieldElement = (RealFieldElement) getField().getZero();
        RealFieldElement realFieldElement2 = (RealFieldElement) getField().getZero();
        for (int i2 = 0; i2 < tArr.length; i2++) {
            RealFieldElement realFieldElement3 = (RealFieldElement) mapState[i2].divide(tArr[i2]);
            realFieldElement = (RealFieldElement) realFieldElement.add(realFieldElement3.multiply(realFieldElement3));
            RealFieldElement realFieldElement4 = (RealFieldElement) mapDerivative[i2].divide(tArr[i2]);
            realFieldElement2 = (RealFieldElement) realFieldElement2.add(realFieldElement4.multiply(realFieldElement4));
        }
        RealFieldElement realFieldElement5 = (RealFieldElement) ((realFieldElement.getReal() < 1.0E-10d || realFieldElement2.getReal() < 1.0E-10d) ? ((RealFieldElement) getField().getZero()).add(1.0E-6d) : ((RealFieldElement) ((RealFieldElement) realFieldElement.divide(realFieldElement2)).sqrt()).multiply(0.01d));
        if (!z) {
            realFieldElement5 = (RealFieldElement) realFieldElement5.negate();
        }
        RealFieldElement[] realFieldElementArr = (RealFieldElement[]) MathArrays.buildArray(getField(), mapState.length);
        for (int i3 = 0; i3 < mapState.length; i3++) {
            realFieldElementArr[i3] = (RealFieldElement) mapState[i3].add(mapDerivative[i3].multiply(realFieldElement5));
        }
        RealFieldElement[] computeDerivatives = computeDerivatives((RealFieldElement) fieldODEStateAndDerivative.getTime().add(realFieldElement5), realFieldElementArr);
        RealFieldElement realFieldElement6 = (RealFieldElement) getField().getZero();
        for (int i4 = 0; i4 < tArr.length; i4++) {
            RealFieldElement realFieldElement7 = (RealFieldElement) ((RealFieldElement) computeDerivatives[i4].subtract(mapDerivative[i4])).divide(tArr[i4]);
            realFieldElement6 = (RealFieldElement) realFieldElement6.add(realFieldElement7.multiply(realFieldElement7));
        }
        RealFieldElement max = MathUtils.max((RealFieldElement) realFieldElement2.sqrt(), (RealFieldElement) ((RealFieldElement) realFieldElement6.sqrt()).divide(realFieldElement5));
        T max2 = MathUtils.max(this.minStep, MathUtils.min(this.maxStep, MathUtils.max(MathUtils.min((RealFieldElement) ((RealFieldElement) realFieldElement5.abs()).multiply(100), max.getReal() < 1.0E-15d ? MathUtils.max((RealFieldElement) ((RealFieldElement) getField().getZero()).add(1.0E-6d), (RealFieldElement) ((RealFieldElement) realFieldElement5.abs()).multiply(0.001d)) : (RealFieldElement) ((RealFieldElement) ((RealFieldElement) max.multiply(100)).reciprocal()).pow(1.0d / ((double) i))), (RealFieldElement) ((RealFieldElement) fieldODEStateAndDerivative.getTime().abs()).multiply(1.0E-12d))));
        return !z ? (RealFieldElement) max2.negate() : max2;
    }

    /* access modifiers changed from: protected */
    public T filterStep(T t, boolean z, boolean z2) throws NumberIsTooSmallException {
        if (((RealFieldElement) ((RealFieldElement) t.abs()).subtract(this.minStep)).getReal() < 0.0d) {
            if (z2) {
                t = this.minStep;
                if (!z) {
                    t = (RealFieldElement) t.negate();
                }
            } else {
                throw new NumberIsTooSmallException(LocalizedFormats.MINIMAL_STEPSIZE_REACHED_DURING_INTEGRATION, Double.valueOf(((RealFieldElement) t.abs()).getReal()), Double.valueOf(this.minStep.getReal()), true);
            }
        }
        if (((RealFieldElement) t.subtract(this.maxStep)).getReal() > 0.0d) {
            return this.maxStep;
        }
        return ((RealFieldElement) t.add(this.maxStep)).getReal() < 0.0d ? (RealFieldElement) this.maxStep.negate() : t;
    }

    /* access modifiers changed from: protected */
    public void resetInternalState() {
        setStepStart((FieldODEStateAndDerivative) null);
        setStepSize((RealFieldElement) ((RealFieldElement) this.minStep.multiply(this.maxStep)).sqrt());
    }

    public T getMinStep() {
        return this.minStep;
    }

    public T getMaxStep() {
        return this.maxStep;
    }
}
