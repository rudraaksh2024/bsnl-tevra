package org.apache.commons.math3.ode.sampling;

import org.apache.commons.math3.RealFieldElement;
import org.apache.commons.math3.exception.MaxCountExceededException;
import org.apache.commons.math3.ode.FieldODEStateAndDerivative;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.Precision;

public class FieldStepNormalizer<T extends RealFieldElement<T>> implements FieldStepHandler<T> {
    private final StepNormalizerBounds bounds;
    private FieldODEStateAndDerivative<T> first;
    private boolean forward;
    private double h;
    private final FieldFixedStepHandler<T> handler;
    private FieldODEStateAndDerivative<T> last;
    private final StepNormalizerMode mode;

    public FieldStepNormalizer(double d, FieldFixedStepHandler<T> fieldFixedStepHandler) {
        this(d, fieldFixedStepHandler, StepNormalizerMode.INCREMENT, StepNormalizerBounds.FIRST);
    }

    public FieldStepNormalizer(double d, FieldFixedStepHandler<T> fieldFixedStepHandler, StepNormalizerMode stepNormalizerMode) {
        this(d, fieldFixedStepHandler, stepNormalizerMode, StepNormalizerBounds.FIRST);
    }

    public FieldStepNormalizer(double d, FieldFixedStepHandler<T> fieldFixedStepHandler, StepNormalizerBounds stepNormalizerBounds) {
        this(d, fieldFixedStepHandler, StepNormalizerMode.INCREMENT, stepNormalizerBounds);
    }

    public FieldStepNormalizer(double d, FieldFixedStepHandler<T> fieldFixedStepHandler, StepNormalizerMode stepNormalizerMode, StepNormalizerBounds stepNormalizerBounds) {
        this.h = FastMath.abs(d);
        this.handler = fieldFixedStepHandler;
        this.mode = stepNormalizerMode;
        this.bounds = stepNormalizerBounds;
        this.first = null;
        this.last = null;
        this.forward = true;
    }

    public void init(FieldODEStateAndDerivative<T> fieldODEStateAndDerivative, T t) {
        this.first = null;
        this.last = null;
        this.forward = true;
        this.handler.init(fieldODEStateAndDerivative, t);
    }

    public void handleStep(FieldStepInterpolator<T> fieldStepInterpolator, boolean z) throws MaxCountExceededException {
        double d;
        T t;
        boolean z2;
        if (this.last == null) {
            FieldODEStateAndDerivative<T> previousState = fieldStepInterpolator.getPreviousState();
            this.first = previousState;
            this.last = previousState;
            boolean isForward = fieldStepInterpolator.isForward();
            this.forward = isForward;
            if (!isForward) {
                this.h = -this.h;
            }
        }
        if (this.mode == StepNormalizerMode.INCREMENT) {
            t = this.last.getTime();
            d = this.h;
        } else {
            t = (RealFieldElement) this.last.getTime().getField().getZero();
            d = (FastMath.floor(this.last.getTime().getReal() / this.h) + 1.0d) * this.h;
        }
        RealFieldElement realFieldElement = (RealFieldElement) t.add(d);
        if (this.mode == StepNormalizerMode.MULTIPLES && Precision.equals(realFieldElement.getReal(), this.last.getTime().getReal(), 1)) {
            realFieldElement = (RealFieldElement) realFieldElement.add(this.h);
        }
        boolean isNextInStep = isNextInStep(realFieldElement, fieldStepInterpolator);
        while (true) {
            z2 = false;
            if (!isNextInStep) {
                break;
            }
            doNormalizedStep(false);
            this.last = fieldStepInterpolator.getInterpolatedState(realFieldElement);
            realFieldElement = (RealFieldElement) realFieldElement.add(this.h);
            isNextInStep = isNextInStep(realFieldElement, fieldStepInterpolator);
        }
        if (z) {
            if (this.bounds.lastIncluded() && this.last.getTime().getReal() != fieldStepInterpolator.getCurrentState().getTime().getReal()) {
                z2 = true;
            }
            doNormalizedStep(!z2);
            if (z2) {
                this.last = fieldStepInterpolator.getCurrentState();
                doNormalizedStep(true);
            }
        }
    }

    private boolean isNextInStep(T t, FieldStepInterpolator<T> fieldStepInterpolator) {
        if (this.forward) {
            if (t.getReal() <= fieldStepInterpolator.getCurrentState().getTime().getReal()) {
                return true;
            }
        } else if (t.getReal() >= fieldStepInterpolator.getCurrentState().getTime().getReal()) {
            return true;
        }
        return false;
    }

    private void doNormalizedStep(boolean z) {
        if (this.bounds.firstIncluded() || this.first.getTime().getReal() != this.last.getTime().getReal()) {
            this.handler.handleStep(this.last, z);
        }
    }
}
