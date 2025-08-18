package org.apache.commons.math3.ode;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.TreeSet;
import org.apache.commons.math3.Field;
import org.apache.commons.math3.RealFieldElement;
import org.apache.commons.math3.analysis.solvers.BracketedRealFieldUnivariateSolver;
import org.apache.commons.math3.analysis.solvers.FieldBracketingNthOrderBrentSolver;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.MaxCountExceededException;
import org.apache.commons.math3.exception.NoBracketingException;
import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.ode.events.FieldEventHandler;
import org.apache.commons.math3.ode.events.FieldEventState;
import org.apache.commons.math3.ode.sampling.AbstractFieldStepInterpolator;
import org.apache.commons.math3.ode.sampling.FieldStepHandler;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.IntegerSequence;

public abstract class AbstractFieldIntegrator<T extends RealFieldElement<T>> implements FirstOrderFieldIntegrator<T> {
    private static final double DEFAULT_FUNCTION_VALUE_ACCURACY = 1.0E-15d;
    private static final double DEFAULT_RELATIVE_ACCURACY = 1.0E-14d;
    private transient FieldExpandableODE<T> equations;
    private IntegerSequence.Incrementor evaluations = IntegerSequence.Incrementor.create().withMaximalCount(Integer.MAX_VALUE);
    private Collection<FieldEventState<T>> eventsStates = new ArrayList();
    private final Field<T> field;
    private boolean isLastStep;
    private final String name;
    private boolean resetOccurred;
    private boolean statesInitialized = false;
    private Collection<FieldStepHandler<T>> stepHandlers = new ArrayList();
    private T stepSize = null;
    private FieldODEStateAndDerivative<T> stepStart = null;

    protected AbstractFieldIntegrator(Field<T> field2, String str) {
        this.field = field2;
        this.name = str;
    }

    public Field<T> getField() {
        return this.field;
    }

    public String getName() {
        return this.name;
    }

    public void addStepHandler(FieldStepHandler<T> fieldStepHandler) {
        this.stepHandlers.add(fieldStepHandler);
    }

    public Collection<FieldStepHandler<T>> getStepHandlers() {
        return Collections.unmodifiableCollection(this.stepHandlers);
    }

    public void clearStepHandlers() {
        this.stepHandlers.clear();
    }

    public void addEventHandler(FieldEventHandler<T> fieldEventHandler, double d, double d2, int i) {
        addEventHandler(fieldEventHandler, d, d2, i, new FieldBracketingNthOrderBrentSolver((RealFieldElement) ((RealFieldElement) this.field.getZero()).add(DEFAULT_RELATIVE_ACCURACY), (RealFieldElement) ((RealFieldElement) this.field.getZero()).add(d2), (RealFieldElement) ((RealFieldElement) this.field.getZero()).add(1.0E-15d), 5));
    }

    public void addEventHandler(FieldEventHandler<T> fieldEventHandler, double d, double d2, int i, BracketedRealFieldUnivariateSolver<T> bracketedRealFieldUnivariateSolver) {
        double d3 = d2;
        this.eventsStates.add(new FieldEventState(fieldEventHandler, d, (RealFieldElement) ((RealFieldElement) this.field.getZero()).add(d2), i, bracketedRealFieldUnivariateSolver));
    }

    public Collection<FieldEventHandler<T>> getEventHandlers() {
        ArrayList arrayList = new ArrayList(this.eventsStates.size());
        for (FieldEventState<T> eventHandler : this.eventsStates) {
            arrayList.add(eventHandler.getEventHandler());
        }
        return Collections.unmodifiableCollection(arrayList);
    }

    public void clearEventHandlers() {
        this.eventsStates.clear();
    }

    public FieldODEStateAndDerivative<T> getCurrentStepStart() {
        return this.stepStart;
    }

    public T getCurrentSignedStepsize() {
        return this.stepSize;
    }

    public void setMaxEvaluations(int i) {
        IntegerSequence.Incrementor incrementor = this.evaluations;
        if (i < 0) {
            i = Integer.MAX_VALUE;
        }
        this.evaluations = incrementor.withMaximalCount(i);
    }

    public int getMaxEvaluations() {
        return this.evaluations.getMaximalCount();
    }

    public int getEvaluations() {
        return this.evaluations.getCount();
    }

    /* access modifiers changed from: protected */
    public FieldODEStateAndDerivative<T> initIntegration(FieldExpandableODE<T> fieldExpandableODE, T t, T[] tArr, T t2) {
        this.equations = fieldExpandableODE;
        this.evaluations = this.evaluations.withStart(0);
        fieldExpandableODE.init(t, tArr, t2);
        FieldODEStateAndDerivative<T> fieldODEStateAndDerivative = new FieldODEStateAndDerivative<>(t, tArr, computeDerivatives(t, tArr));
        for (FieldEventState<T> eventHandler : this.eventsStates) {
            eventHandler.getEventHandler().init(fieldODEStateAndDerivative, t2);
        }
        for (FieldStepHandler<T> init : this.stepHandlers) {
            init.init(fieldODEStateAndDerivative, t2);
        }
        setStateInitialized(false);
        return fieldODEStateAndDerivative;
    }

    /* access modifiers changed from: protected */
    public FieldExpandableODE<T> getEquations() {
        return this.equations;
    }

    /* access modifiers changed from: protected */
    public IntegerSequence.Incrementor getEvaluationsCounter() {
        return this.evaluations;
    }

    public T[] computeDerivatives(T t, T[] tArr) throws DimensionMismatchException, MaxCountExceededException, NullPointerException {
        this.evaluations.increment();
        return this.equations.computeDerivatives(t, tArr);
    }

    /* access modifiers changed from: protected */
    public void setStateInitialized(boolean z) {
        this.statesInitialized = z;
    }

    /* access modifiers changed from: protected */
    public FieldODEStateAndDerivative<T> acceptStep(AbstractFieldStepInterpolator<T> abstractFieldStepInterpolator, T t) throws MaxCountExceededException, DimensionMismatchException, NoBracketingException {
        FieldODEStateAndDerivative<T> globalPreviousState = abstractFieldStepInterpolator.getGlobalPreviousState();
        FieldODEStateAndDerivative<T> globalCurrentState = abstractFieldStepInterpolator.getGlobalCurrentState();
        boolean z = true;
        if (!this.statesInitialized) {
            for (FieldEventState<T> reinitializeBegin : this.eventsStates) {
                reinitializeBegin.reinitializeBegin(abstractFieldStepInterpolator);
            }
            this.statesInitialized = true;
        }
        final int i = abstractFieldStepInterpolator.isForward() ? 1 : -1;
        TreeSet treeSet = new TreeSet(new Comparator<FieldEventState<T>>() {
            public int compare(FieldEventState<T> fieldEventState, FieldEventState<T> fieldEventState2) {
                return i * Double.compare(fieldEventState.getEventTime().getReal(), fieldEventState2.getEventTime().getReal());
            }
        });
        for (FieldEventState next : this.eventsStates) {
            if (next.evaluateStep(abstractFieldStepInterpolator)) {
                treeSet.add(next);
            }
        }
        while (!treeSet.isEmpty()) {
            Iterator it = treeSet.iterator();
            FieldEventState fieldEventState = (FieldEventState) it.next();
            it.remove();
            FieldODEStateAndDerivative<T> interpolatedState = abstractFieldStepInterpolator.getInterpolatedState(fieldEventState.getEventTime());
            AbstractFieldStepInterpolator<T> restrictStep = abstractFieldStepInterpolator.restrictStep(globalPreviousState, interpolatedState);
            for (FieldEventState next2 : this.eventsStates) {
                next2.stepAccepted(interpolatedState);
                this.isLastStep = this.isLastStep || next2.stop();
            }
            for (FieldStepHandler<T> handleStep : this.stepHandlers) {
                handleStep.handleStep(restrictStep, this.isLastStep);
            }
            if (this.isLastStep) {
                return interpolatedState;
            }
            this.resetOccurred = false;
            for (FieldEventState<T> reset : this.eventsStates) {
                FieldODEState<T> reset2 = reset.reset(interpolatedState);
                if (reset2 != null) {
                    RealFieldElement[] mapState = this.equations.getMapper().mapState(reset2);
                    RealFieldElement[] computeDerivatives = computeDerivatives(reset2.getTime(), mapState);
                    this.resetOccurred = true;
                    return this.equations.getMapper().mapStateAndDerivative(reset2.getTime(), mapState, computeDerivatives);
                }
            }
            abstractFieldStepInterpolator = restrictStep.restrictStep(interpolatedState, globalCurrentState);
            if (fieldEventState.evaluateStep(abstractFieldStepInterpolator)) {
                treeSet.add(fieldEventState);
            }
            globalPreviousState = interpolatedState;
        }
        for (FieldEventState next3 : this.eventsStates) {
            next3.stepAccepted(globalCurrentState);
            this.isLastStep = this.isLastStep || next3.stop();
        }
        if (!this.isLastStep && ((RealFieldElement) ((RealFieldElement) globalCurrentState.getTime().subtract(t)).abs()).getReal() > FastMath.ulp(t.getReal())) {
            z = false;
        }
        this.isLastStep = z;
        for (FieldStepHandler<T> handleStep2 : this.stepHandlers) {
            handleStep2.handleStep(abstractFieldStepInterpolator, this.isLastStep);
        }
        return globalCurrentState;
    }

    /* access modifiers changed from: protected */
    public void sanityChecks(FieldODEState<T> fieldODEState, T t) throws NumberIsTooSmallException, DimensionMismatchException {
        double ulp = FastMath.ulp(FastMath.max(FastMath.abs(fieldODEState.getTime().getReal()), FastMath.abs(t.getReal()))) * 1000.0d;
        double real = ((RealFieldElement) ((RealFieldElement) fieldODEState.getTime().subtract(t)).abs()).getReal();
        if (real <= ulp) {
            throw new NumberIsTooSmallException(LocalizedFormats.TOO_SMALL_INTEGRATION_INTERVAL, Double.valueOf(real), Double.valueOf(ulp), false);
        }
    }

    /* access modifiers changed from: protected */
    public boolean resetOccurred() {
        return this.resetOccurred;
    }

    /* access modifiers changed from: protected */
    public void setStepSize(T t) {
        this.stepSize = t;
    }

    /* access modifiers changed from: protected */
    public T getStepSize() {
        return this.stepSize;
    }

    /* access modifiers changed from: protected */
    public void setStepStart(FieldODEStateAndDerivative<T> fieldODEStateAndDerivative) {
        this.stepStart = fieldODEStateAndDerivative;
    }

    /* access modifiers changed from: protected */
    public FieldODEStateAndDerivative<T> getStepStart() {
        return this.stepStart;
    }

    /* access modifiers changed from: protected */
    public void setIsLastStep(boolean z) {
        this.isLastStep = z;
    }

    /* access modifiers changed from: protected */
    public boolean isLastStep() {
        return this.isLastStep;
    }
}
