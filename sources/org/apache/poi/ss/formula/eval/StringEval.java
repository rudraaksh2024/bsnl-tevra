package org.apache.poi.ss.formula.eval;

import org.apache.poi.ss.formula.ptg.Ptg;
import org.apache.poi.ss.formula.ptg.StringPtg;

public final class StringEval implements StringValueEval {
    public static final StringEval EMPTY_INSTANCE = new StringEval("");
    private final String _value;

    public StringEval(Ptg ptg) {
        this(((StringPtg) ptg).getValue());
    }

    public StringEval(String str) {
        if (str != null) {
            this._value = str;
            return;
        }
        throw new IllegalArgumentException("value must not be null");
    }

    public String getStringValue() {
        return this._value;
    }

    public String toString() {
        return getClass().getName() + " [" + this._value + "]";
    }
}
