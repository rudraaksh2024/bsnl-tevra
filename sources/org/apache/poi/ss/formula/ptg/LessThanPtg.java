package org.apache.poi.ss.formula.ptg;

public final class LessThanPtg extends ValueOperatorPtg {
    private static final String LESSTHAN = "<";
    public static final LessThanPtg instance = new LessThanPtg();
    public static final byte sid = 9;

    public int getNumberOfOperands() {
        return 2;
    }

    public byte getSid() {
        return 9;
    }

    private LessThanPtg() {
    }

    public String toFormulaString(String[] strArr) {
        return strArr[0] + LESSTHAN + strArr[1];
    }

    public LessThanPtg copy() {
        return instance;
    }
}
