package org.apache.poi.ss.formula.functions;

import org.apache.poi.ss.formula.eval.NumberEval;
import org.apache.poi.ss.formula.eval.NumericValueEval;
import org.apache.poi.ss.formula.eval.ValueEval;

public final class DSum implements IDStarAlgorithm {
    private double totalValue = 0.0d;

    public boolean processMatch(ValueEval valueEval) {
        if (!(valueEval instanceof NumericValueEval)) {
            return true;
        }
        this.totalValue += ((NumericValueEval) valueEval).getNumberValue();
        return true;
    }

    public ValueEval getResult() {
        return new NumberEval(this.totalValue);
    }
}
