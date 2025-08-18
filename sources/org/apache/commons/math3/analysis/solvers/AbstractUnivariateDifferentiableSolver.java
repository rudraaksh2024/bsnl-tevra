package org.apache.commons.math3.analysis.solvers;

import org.apache.commons.math3.analysis.differentiation.DerivativeStructure;
import org.apache.commons.math3.analysis.differentiation.UnivariateDifferentiableFunction;
import org.apache.commons.math3.exception.TooManyEvaluationsException;

public abstract class AbstractUnivariateDifferentiableSolver extends BaseAbstractUnivariateSolver<UnivariateDifferentiableFunction> implements UnivariateDifferentiableSolver {
    private UnivariateDifferentiableFunction function;

    protected AbstractUnivariateDifferentiableSolver(double d) {
        super(d);
    }

    protected AbstractUnivariateDifferentiableSolver(double d, double d2, double d3) {
        super(d, d2, d3);
    }

    /* access modifiers changed from: protected */
    public DerivativeStructure computeObjectiveValueAndDerivative(double d) throws TooManyEvaluationsException {
        incrementEvaluationCount();
        return this.function.value(new DerivativeStructure(1, 1, 0, d));
    }

    /* access modifiers changed from: protected */
    public void setup(int i, UnivariateDifferentiableFunction univariateDifferentiableFunction, double d, double d2, double d3) {
        super.setup(i, univariateDifferentiableFunction, d, d2, d3);
        this.function = univariateDifferentiableFunction;
    }
}
