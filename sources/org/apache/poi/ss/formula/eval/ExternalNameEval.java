package org.apache.poi.ss.formula.eval;

import org.apache.poi.ss.formula.EvaluationName;

public final class ExternalNameEval implements ValueEval {
    private final EvaluationName _name;

    public ExternalNameEval(EvaluationName evaluationName) {
        this._name = evaluationName;
    }

    public EvaluationName getName() {
        return this._name;
    }

    public String toString() {
        return getClass().getName() + " [" + this._name.getNameText() + "]";
    }
}
