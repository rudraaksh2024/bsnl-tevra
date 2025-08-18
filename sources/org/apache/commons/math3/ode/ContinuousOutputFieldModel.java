package org.apache.commons.math3.ode;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.math3.RealFieldElement;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.MathIllegalArgumentException;
import org.apache.commons.math3.exception.MaxCountExceededException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.ode.sampling.FieldStepHandler;
import org.apache.commons.math3.ode.sampling.FieldStepInterpolator;
import org.apache.commons.math3.util.FastMath;

public class ContinuousOutputFieldModel<T extends RealFieldElement<T>> implements FieldStepHandler<T> {
    private T finalTime = null;
    private boolean forward = true;
    private int index = 0;
    private T initialTime = null;
    private List<FieldStepInterpolator<T>> steps = new ArrayList();

    public void append(ContinuousOutputFieldModel<T> continuousOutputFieldModel) throws MathIllegalArgumentException, MaxCountExceededException {
        if (continuousOutputFieldModel.steps.size() != 0) {
            if (this.steps.size() == 0) {
                this.initialTime = continuousOutputFieldModel.initialTime;
                this.forward = continuousOutputFieldModel.forward;
            } else {
                FieldODEStateAndDerivative previousState = this.steps.get(0).getPreviousState();
                FieldODEStateAndDerivative previousState2 = continuousOutputFieldModel.steps.get(0).getPreviousState();
                checkDimensionsEquality(previousState.getStateDimension(), previousState2.getStateDimension());
                checkDimensionsEquality(previousState.getNumberOfSecondaryStates(), previousState2.getNumberOfSecondaryStates());
                for (int i = 0; i < previousState.getNumberOfSecondaryStates(); i++) {
                    checkDimensionsEquality(previousState.getSecondaryStateDimension(i), previousState2.getSecondaryStateDimension(i));
                }
                if (!(this.forward ^ continuousOutputFieldModel.forward)) {
                    FieldStepInterpolator fieldStepInterpolator = this.steps.get(this.index);
                    RealFieldElement time = fieldStepInterpolator.getCurrentState().getTime();
                    RealFieldElement realFieldElement = (RealFieldElement) continuousOutputFieldModel.getInitialTime().subtract(time);
                    if (((RealFieldElement) ((RealFieldElement) realFieldElement.abs()).subtract(((RealFieldElement) ((RealFieldElement) time.subtract(fieldStepInterpolator.getPreviousState().getTime())).abs()).multiply(0.001d))).getReal() > 0.0d) {
                        throw new MathIllegalArgumentException(LocalizedFormats.HOLE_BETWEEN_MODELS_TIME_RANGES, Double.valueOf(((RealFieldElement) realFieldElement.abs()).getReal()));
                    }
                } else {
                    throw new MathIllegalArgumentException(LocalizedFormats.PROPAGATION_DIRECTION_MISMATCH, new Object[0]);
                }
            }
            for (FieldStepInterpolator<T> add : continuousOutputFieldModel.steps) {
                this.steps.add(add);
            }
            int size = this.steps.size() - 1;
            this.index = size;
            this.finalTime = this.steps.get(size).getCurrentState().getTime();
        }
    }

    private void checkDimensionsEquality(int i, int i2) throws DimensionMismatchException {
        if (i != i2) {
            throw new DimensionMismatchException(i2, i);
        }
    }

    public void init(FieldODEStateAndDerivative<T> fieldODEStateAndDerivative, T t) {
        this.initialTime = fieldODEStateAndDerivative.getTime();
        this.finalTime = t;
        this.forward = true;
        this.index = 0;
        this.steps.clear();
    }

    public void handleStep(FieldStepInterpolator<T> fieldStepInterpolator, boolean z) throws MaxCountExceededException {
        if (this.steps.size() == 0) {
            this.initialTime = fieldStepInterpolator.getPreviousState().getTime();
            this.forward = fieldStepInterpolator.isForward();
        }
        this.steps.add(fieldStepInterpolator);
        if (z) {
            this.finalTime = fieldStepInterpolator.getCurrentState().getTime();
            this.index = this.steps.size() - 1;
        }
    }

    public T getInitialTime() {
        return this.initialTime;
    }

    public T getFinalTime() {
        return this.finalTime;
    }

    public FieldODEStateAndDerivative<T> getInterpolatedState(T t) {
        T t2 = t;
        int i = 0;
        FieldStepInterpolator fieldStepInterpolator = this.steps.get(0);
        RealFieldElement realFieldElement = (RealFieldElement) ((RealFieldElement) fieldStepInterpolator.getPreviousState().getTime().add(fieldStepInterpolator.getCurrentState().getTime())).multiply(0.5d);
        int size = this.steps.size() - 1;
        FieldStepInterpolator fieldStepInterpolator2 = this.steps.get(size);
        RealFieldElement realFieldElement2 = (RealFieldElement) ((RealFieldElement) fieldStepInterpolator2.getPreviousState().getTime().add(fieldStepInterpolator2.getCurrentState().getTime())).multiply(0.5d);
        if (locatePoint(t2, fieldStepInterpolator) <= 0) {
            this.index = 0;
            return fieldStepInterpolator.getInterpolatedState(t2);
        } else if (locatePoint(t2, fieldStepInterpolator2) >= 0) {
            this.index = size;
            return fieldStepInterpolator2.getInterpolatedState(t2);
        } else {
            while (size - i > 5) {
                FieldStepInterpolator fieldStepInterpolator3 = this.steps.get(this.index);
                int locatePoint = locatePoint(t2, fieldStepInterpolator3);
                if (locatePoint < 0) {
                    size = this.index;
                    realFieldElement2 = (RealFieldElement) ((RealFieldElement) fieldStepInterpolator3.getPreviousState().getTime().add(fieldStepInterpolator3.getCurrentState().getTime())).multiply(0.5d);
                } else if (locatePoint <= 0) {
                    return fieldStepInterpolator3.getInterpolatedState(t2);
                } else {
                    i = this.index;
                    realFieldElement = (RealFieldElement) ((RealFieldElement) fieldStepInterpolator3.getPreviousState().getTime().add(fieldStepInterpolator3.getCurrentState().getTime())).multiply(0.5d);
                }
                int i2 = (i + size) / 2;
                FieldStepInterpolator fieldStepInterpolator4 = this.steps.get(i2);
                RealFieldElement realFieldElement3 = (RealFieldElement) ((RealFieldElement) fieldStepInterpolator4.getPreviousState().getTime().add(fieldStepInterpolator4.getCurrentState().getTime())).multiply(0.5d);
                if (((RealFieldElement) ((RealFieldElement) ((RealFieldElement) realFieldElement3.subtract(realFieldElement)).abs()).subtract(1.0E-6d)).getReal() < 0.0d || ((RealFieldElement) ((RealFieldElement) ((RealFieldElement) realFieldElement2.subtract(realFieldElement3)).abs()).subtract(1.0E-6d)).getReal() < 0.0d) {
                    this.index = i2;
                } else {
                    RealFieldElement realFieldElement4 = (RealFieldElement) realFieldElement2.subtract(realFieldElement3);
                    RealFieldElement realFieldElement5 = (RealFieldElement) realFieldElement3.subtract(realFieldElement);
                    RealFieldElement realFieldElement6 = (RealFieldElement) realFieldElement2.subtract(realFieldElement);
                    RealFieldElement realFieldElement7 = (RealFieldElement) t2.subtract(realFieldElement2);
                    RealFieldElement realFieldElement8 = (RealFieldElement) t2.subtract(realFieldElement3);
                    RealFieldElement realFieldElement9 = (RealFieldElement) t2.subtract(realFieldElement);
                    this.index = (int) FastMath.rint(((RealFieldElement) ((RealFieldElement) ((RealFieldElement) ((RealFieldElement) ((RealFieldElement) ((RealFieldElement) realFieldElement8.multiply(realFieldElement9)).multiply(realFieldElement5)).multiply(size)).subtract(((RealFieldElement) ((RealFieldElement) realFieldElement7.multiply(realFieldElement9)).multiply(realFieldElement6)).multiply(i2))).add(((RealFieldElement) ((RealFieldElement) realFieldElement7.multiply(realFieldElement8)).multiply(realFieldElement4)).multiply(i))).divide(((RealFieldElement) realFieldElement4.multiply(realFieldElement5)).multiply(realFieldElement6))).getReal());
                }
                int max = FastMath.max(i + 1, ((i * 9) + size) / 10);
                int min = FastMath.min(size - 1, ((size * 9) + i) / 10);
                int i3 = this.index;
                if (i3 < max) {
                    this.index = max;
                } else if (i3 > min) {
                    this.index = min;
                }
            }
            this.index = i;
            while (true) {
                int i4 = this.index;
                if (i4 <= size && locatePoint(t2, this.steps.get(i4)) > 0) {
                    this.index++;
                }
            }
            return this.steps.get(this.index).getInterpolatedState(t2);
        }
    }

    private int locatePoint(T t, FieldStepInterpolator<T> fieldStepInterpolator) {
        if (this.forward) {
            if (((RealFieldElement) t.subtract(fieldStepInterpolator.getPreviousState().getTime())).getReal() < 0.0d) {
                return -1;
            }
            return ((RealFieldElement) t.subtract(fieldStepInterpolator.getCurrentState().getTime())).getReal() > 0.0d ? 1 : 0;
        } else if (((RealFieldElement) t.subtract(fieldStepInterpolator.getPreviousState().getTime())).getReal() > 0.0d) {
            return -1;
        } else {
            return ((RealFieldElement) t.subtract(fieldStepInterpolator.getCurrentState().getTime())).getReal() < 0.0d ? 1 : 0;
        }
    }
}
