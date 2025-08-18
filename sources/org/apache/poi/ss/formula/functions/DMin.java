package org.apache.poi.ss.formula.functions;

import org.apache.poi.ss.formula.eval.NumberEval;
import org.apache.poi.ss.formula.eval.NumericValueEval;
import org.apache.poi.ss.formula.eval.ValueEval;

public final class DMin implements IDStarAlgorithm {
    private ValueEval minimumValue;

    public boolean processMatch(ValueEval valueEval) {
        if (!(valueEval instanceof NumericValueEval)) {
            return true;
        }
        if (this.minimumValue == null) {
            this.minimumValue = valueEval;
            return true;
        } else if (((NumericValueEval) valueEval).getNumberValue() >= ((NumericValueEval) this.minimumValue).getNumberValue()) {
            return true;
        } else {
            this.minimumValue = valueEval;
            return true;
        }
    }

    public ValueEval getResult() {
        ValueEval valueEval = this.minimumValue;
        return valueEval == null ? NumberEval.ZERO : valueEval;
    }
}
