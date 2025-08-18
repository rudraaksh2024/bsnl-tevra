package org.apache.commons.math3.ode.events;

import org.apache.commons.math3.RealFieldElement;
import org.apache.commons.math3.analysis.RealFieldUnivariateFunction;
import org.apache.commons.math3.analysis.solvers.AllowedSolution;
import org.apache.commons.math3.analysis.solvers.BracketedRealFieldUnivariateSolver;
import org.apache.commons.math3.exception.MaxCountExceededException;
import org.apache.commons.math3.exception.NoBracketingException;
import org.apache.commons.math3.ode.FieldODEState;
import org.apache.commons.math3.ode.FieldODEStateAndDerivative;
import org.apache.commons.math3.ode.sampling.FieldStepInterpolator;
import org.apache.commons.math3.util.FastMath;

public class FieldEventState<T extends RealFieldElement<T>> {
    private final T convergence;
    private boolean forward;
    private T g0 = null;
    private boolean g0Positive = true;
    /* access modifiers changed from: private */
    public final FieldEventHandler<T> handler;
    private boolean increasing = true;
    private final double maxCheckInterval;
    private final int maxIterationCount;
    private Action nextAction = Action.CONTINUE;
    private boolean pendingEvent = false;
    private T pendingEventTime = null;
    private T previousEventTime = null;
    private final BracketedRealFieldUnivariateSolver<T> solver;
    private T t0 = null;

    public FieldEventState(FieldEventHandler<T> fieldEventHandler, double d, T t, int i, BracketedRealFieldUnivariateSolver<T> bracketedRealFieldUnivariateSolver) {
        this.handler = fieldEventHandler;
        this.maxCheckInterval = d;
        this.convergence = (RealFieldElement) t.abs();
        this.maxIterationCount = i;
        this.solver = bracketedRealFieldUnivariateSolver;
    }

    public FieldEventHandler<T> getEventHandler() {
        return this.handler;
    }

    public double getMaxCheckInterval() {
        return this.maxCheckInterval;
    }

    public T getConvergence() {
        return this.convergence;
    }

    public int getMaxIterationCount() {
        return this.maxIterationCount;
    }

    public void reinitializeBegin(FieldStepInterpolator<T> fieldStepInterpolator) throws MaxCountExceededException {
        FieldODEStateAndDerivative<T> previousState = fieldStepInterpolator.getPreviousState();
        this.t0 = previousState.getTime();
        T g = this.handler.g(previousState);
        this.g0 = g;
        if (g.getReal() == 0.0d) {
            this.g0 = this.handler.g(fieldStepInterpolator.getInterpolatedState((RealFieldElement) this.t0.add(FastMath.max(this.solver.getAbsoluteAccuracy().getReal(), FastMath.abs(((RealFieldElement) this.solver.getRelativeAccuracy().multiply(this.t0)).getReal())) * 0.5d)));
        }
        this.g0Positive = this.g0.getReal() >= 0.0d;
    }

    public boolean evaluateStep(FieldStepInterpolator<T> fieldStepInterpolator) throws MaxCountExceededException, NoBracketingException {
        int i;
        T t;
        T t2;
        int i2;
        T t3;
        T t4;
        AllowedSolution allowedSolution;
        T t5;
        AnonymousClass1 r11;
        int i3;
        BracketedRealFieldUnivariateSolver<T> bracketedRealFieldUnivariateSolver;
        T t6;
        T t7;
        T t8;
        final FieldStepInterpolator<T> fieldStepInterpolator2 = fieldStepInterpolator;
        this.forward = fieldStepInterpolator.isForward();
        T time = fieldStepInterpolator.getCurrentState().getTime();
        RealFieldElement realFieldElement = (RealFieldElement) time.subtract(this.t0);
        if (((RealFieldElement) ((RealFieldElement) realFieldElement.abs()).subtract(this.convergence)).getReal() < 0.0d) {
            return false;
        }
        int max = FastMath.max(1, (int) FastMath.ceil(FastMath.abs(realFieldElement.getReal()) / this.maxCheckInterval));
        RealFieldElement realFieldElement2 = (RealFieldElement) realFieldElement.divide((double) max);
        AnonymousClass1 r15 = new RealFieldUnivariateFunction<T>() {
            public T value(T t) {
                return FieldEventState.this.handler.g(fieldStepInterpolator2.getInterpolatedState(t));
            }
        };
        T t9 = this.t0;
        T t10 = this.g0;
        int i4 = 0;
        T t11 = t9;
        while (i < max) {
            if (i == max - 1) {
                t = time;
            } else {
                t = (RealFieldElement) this.t0.add(realFieldElement2.multiply(i + 1));
            }
            T g = this.handler.g(fieldStepInterpolator2.getInterpolatedState(t));
            if (this.g0Positive ^ (g.getReal() >= 0.0d)) {
                this.increasing = ((RealFieldElement) g.subtract(t10)).getReal() >= 0.0d;
                if (this.forward) {
                    bracketedRealFieldUnivariateSolver = this.solver;
                    i3 = this.maxIterationCount;
                    t4 = g;
                    r11 = r15;
                    t3 = t;
                    t = t11;
                    i2 = i;
                    t5 = t3;
                    t2 = t11;
                    allowedSolution = AllowedSolution.RIGHT_SIDE;
                } else {
                    t4 = g;
                    t3 = t;
                    i2 = i;
                    t2 = t11;
                    bracketedRealFieldUnivariateSolver = this.solver;
                    i3 = this.maxIterationCount;
                    allowedSolution = AllowedSolution.LEFT_SIDE;
                    r11 = r15;
                    t5 = t2;
                }
                T solve = bracketedRealFieldUnivariateSolver.solve(i3, r11, t, t5, allowedSolution);
                if (this.previousEventTime != null) {
                    T t12 = t2;
                    if (((RealFieldElement) ((RealFieldElement) ((RealFieldElement) solve.subtract(t12)).abs()).subtract(this.convergence)).getReal() <= 0.0d && ((RealFieldElement) ((RealFieldElement) ((RealFieldElement) solve.subtract(this.previousEventTime)).abs()).subtract(this.convergence)).getReal() <= 0.0d) {
                        T t13 = t12;
                        while (true) {
                            t13 = (RealFieldElement) (this.forward ? t13.add(this.convergence) : t13.subtract(this.convergence));
                            t7 = r15.value(t13);
                            if (!(this.g0Positive ^ (t7.getReal() >= 0.0d))) {
                                t8 = t3;
                                break;
                            }
                            t8 = t3;
                            if (!(this.forward ^ (((RealFieldElement) t13.subtract(t8)).getReal() >= 0.0d))) {
                                break;
                            }
                            t3 = t8;
                        }
                        if (this.forward ^ (((RealFieldElement) t13.subtract(t8)).getReal() >= 0.0d)) {
                            i = i2 - 1;
                            t6 = t13;
                            t10 = t7;
                            t11 = t6;
                        } else {
                            this.pendingEventTime = solve;
                            this.pendingEvent = true;
                            return true;
                        }
                    }
                }
                t6 = t3;
                T t14 = this.previousEventTime;
                if (t14 == null || ((RealFieldElement) ((RealFieldElement) ((RealFieldElement) t14.subtract(solve)).abs()).subtract(this.convergence)).getReal() > 0.0d) {
                    this.pendingEventTime = solve;
                    this.pendingEvent = true;
                    return true;
                }
                t7 = t4;
                i = i2;
                t10 = t7;
                t11 = t6;
            } else {
                int i5 = i;
                t11 = t;
                t10 = g;
            }
            i4 = i + 1;
        }
        this.pendingEvent = false;
        this.pendingEventTime = null;
        return false;
    }

    public T getEventTime() {
        if (this.pendingEvent) {
            return this.pendingEventTime;
        }
        return (RealFieldElement) ((RealFieldElement) this.t0.getField().getZero()).add(this.forward ? Double.POSITIVE_INFINITY : Double.NEGATIVE_INFINITY);
    }

    public void stepAccepted(FieldODEStateAndDerivative<T> fieldODEStateAndDerivative) {
        this.t0 = fieldODEStateAndDerivative.getTime();
        this.g0 = this.handler.g(fieldODEStateAndDerivative);
        boolean z = true;
        if (!this.pendingEvent || ((RealFieldElement) ((RealFieldElement) ((RealFieldElement) this.pendingEventTime.subtract(fieldODEStateAndDerivative.getTime())).abs()).subtract(this.convergence)).getReal() > 0.0d) {
            if (this.g0.getReal() < 0.0d) {
                z = false;
            }
            this.g0Positive = z;
            this.nextAction = Action.CONTINUE;
            return;
        }
        this.previousEventTime = fieldODEStateAndDerivative.getTime();
        boolean z2 = this.increasing;
        this.g0Positive = z2;
        this.nextAction = this.handler.eventOccurred(fieldODEStateAndDerivative, !(z2 ^ this.forward));
    }

    public boolean stop() {
        return this.nextAction == Action.STOP;
    }

    public FieldODEState<T> reset(FieldODEStateAndDerivative<T> fieldODEStateAndDerivative) {
        FieldODEState<T> fieldODEState;
        if (!this.pendingEvent || ((RealFieldElement) ((RealFieldElement) ((RealFieldElement) this.pendingEventTime.subtract(fieldODEStateAndDerivative.getTime())).abs()).subtract(this.convergence)).getReal() > 0.0d) {
            return null;
        }
        if (this.nextAction == Action.RESET_STATE) {
            fieldODEState = this.handler.resetState(fieldODEStateAndDerivative);
        } else {
            fieldODEState = fieldODEStateAndDerivative;
            if (this.nextAction != Action.RESET_DERIVATIVES) {
                fieldODEState = null;
            }
        }
        this.pendingEvent = false;
        this.pendingEventTime = null;
        return fieldODEState;
    }
}
