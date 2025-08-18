package org.apache.poi.ss.formula.functions;

import org.apache.poi.ss.formula.eval.BlankEval;
import org.apache.poi.ss.formula.eval.ErrorEval;
import org.apache.poi.ss.formula.eval.EvaluationException;
import org.apache.poi.ss.formula.eval.OperandResolver;
import org.apache.poi.ss.formula.eval.ValueEval;

public final class DGet implements IDStarAlgorithm {
    private ValueEval result;

    public boolean processMatch(ValueEval valueEval) {
        ValueEval valueEval2 = this.result;
        if (valueEval2 == null) {
            this.result = valueEval;
            return true;
        } else if (valueEval2 instanceof BlankEval) {
            this.result = valueEval;
            return true;
        } else if (valueEval instanceof BlankEval) {
            return true;
        } else {
            this.result = ErrorEval.NUM_ERROR;
            return false;
        }
    }

    public ValueEval getResult() {
        ValueEval valueEval = this.result;
        if (valueEval == null) {
            return ErrorEval.VALUE_INVALID;
        }
        if (valueEval instanceof BlankEval) {
            return ErrorEval.VALUE_INVALID;
        }
        try {
            if (OperandResolver.coerceValueToString(OperandResolver.getSingleValue(valueEval, 0, 0)).isEmpty()) {
                return ErrorEval.VALUE_INVALID;
            }
            return this.result;
        } catch (EvaluationException e) {
            return e.getErrorEval();
        }
    }
}
