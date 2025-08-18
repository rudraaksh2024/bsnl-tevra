package org.apache.poi.ss.formula.eval;

public final class FunctionNameEval implements ValueEval {
    private final String _functionName;

    public FunctionNameEval(String str) {
        this._functionName = str;
    }

    public String getFunctionName() {
        return this._functionName;
    }

    public String toString() {
        return getClass().getName() + " [" + this._functionName + "]";
    }
}
