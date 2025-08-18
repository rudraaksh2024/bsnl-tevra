package org.apache.poi.ss.formula.functions;

import org.apache.poi.ss.formula.eval.NumberEval;
import org.apache.poi.ss.formula.eval.NumericValueEval;
import org.apache.poi.ss.formula.eval.ValueEval;

public final class DMax implements IDStarAlgorithm {
    private ValueEval maximumValue;

    public boolean processMatch(ValueEval valueEval) {
        if (!(valueEval instanceof NumericValueEval)) {
            return true;
        }
        if (this.maximumValue == null) {
            this.maximumValue = valueEval;
            return true;
        } else if (((NumericValueEval) valueEval).getNumberValue() <= ((NumericValueEval) this.maximumValue).getNumberValue()) {
            return true;
        } else {
            this.maximumValue = valueEval;
            return true;
        }
    }

    public ValueEval getResult() {
        ValueEval valueEval = this.maximumValue;
        return valueEval == null ? NumberEval.ZERO : valueEval;
    }
}
