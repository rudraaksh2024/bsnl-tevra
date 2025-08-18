package org.apache.commons.math3.ode.sampling;

import org.apache.commons.math3.RealFieldElement;
import org.apache.commons.math3.exception.MaxCountExceededException;
import org.apache.commons.math3.ode.FieldEquationsMapper;
import org.apache.commons.math3.ode.FieldODEStateAndDerivative;

public abstract class AbstractFieldStepInterpolator<T extends RealFieldElement<T>> implements FieldStepInterpolator<T> {
    private final boolean forward;
    private final FieldODEStateAndDerivative<T> globalCurrentState;
    private final FieldODEStateAndDerivative<T> globalPreviousState;
    private FieldEquationsMapper<T> mapper;
    private final FieldODEStateAndDerivative<T> softCurrentState;
    private final FieldODEStateAndDerivative<T> softPreviousState;

    /* access modifiers changed from: protected */
    public abstract FieldODEStateAndDerivative<T> computeInterpolatedStateAndDerivatives(FieldEquationsMapper<T> fieldEquationsMapper, T t, T t2, T t3, T t4) throws MaxCountExceededException;

    /* access modifiers changed from: protected */
    public abstract AbstractFieldStepInterpolator<T> create(boolean z, FieldODEStateAndDerivative<T> fieldODEStateAndDerivative, FieldODEStateAndDerivative<T> fieldODEStateAndDerivative2, FieldODEStateAndDerivative<T> fieldODEStateAndDerivative3, FieldODEStateAndDerivative<T> fieldODEStateAndDerivative4, FieldEquationsMapper<T> fieldEquationsMapper);

    protected AbstractFieldStepInterpolator(boolean z, FieldODEStateAndDerivative<T> fieldODEStateAndDerivative, FieldODEStateAndDerivative<T> fieldODEStateAndDerivative2, FieldODEStateAndDerivative<T> fieldODEStateAndDerivative3, FieldODEStateAndDerivative<T> fieldODEStateAndDerivative4, FieldEquationsMapper<T> fieldEquationsMapper) {
        this.forward = z;
        this.globalPreviousState = fieldODEStateAndDerivative;
        this.globalCurrentState = fieldODEStateAndDerivative2;
        this.softPreviousState = fieldODEStateAndDerivative3;
        this.softCurrentState = fieldODEStateAndDerivative4;
        this.mapper = fieldEquationsMapper;
    }

    public AbstractFieldStepInterpolator<T> restrictStep(FieldODEStateAndDerivative<T> fieldODEStateAndDerivative, FieldODEStateAndDerivative<T> fieldODEStateAndDerivative2) {
        return create(this.forward, this.globalPreviousState, this.globalCurrentState, fieldODEStateAndDerivative, fieldODEStateAndDerivative2, this.mapper);
    }

    public FieldODEStateAndDerivative<T> getGlobalPreviousState() {
        return this.globalPreviousState;
    }

    public FieldODEStateAndDerivative<T> getGlobalCurrentState() {
        return this.globalCurrentState;
    }

    public FieldODEStateAndDerivative<T> getPreviousState() {
        return this.softPreviousState;
    }

    public FieldODEStateAndDerivative<T> getCurrentState() {
        return this.softCurrentState;
    }

    public FieldODEStateAndDerivative<T> getInterpolatedState(T t) {
        RealFieldElement realFieldElement = (RealFieldElement) t.subtract(this.globalPreviousState.getTime());
        RealFieldElement realFieldElement2 = (RealFieldElement) realFieldElement.divide(this.globalCurrentState.getTime().subtract(this.globalPreviousState.getTime()));
        return computeInterpolatedStateAndDerivatives(this.mapper, t, realFieldElement2, realFieldElement, (RealFieldElement) this.globalCurrentState.getTime().subtract(t));
    }

    public boolean isForward() {
        return this.forward;
    }
}
