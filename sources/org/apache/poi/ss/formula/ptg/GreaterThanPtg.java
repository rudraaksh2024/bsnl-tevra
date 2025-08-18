package org.apache.poi.ss.formula.ptg;

public final class GreaterThanPtg extends ValueOperatorPtg {
    private static final String GREATERTHAN = ">";
    public static final GreaterThanPtg instance = new GreaterThanPtg();
    public static final byte sid = 13;

    public int getNumberOfOperands() {
        return 2;
    }

    public byte getSid() {
        return 13;
    }

    private GreaterThanPtg() {
    }

    public String toFormulaString(String[] strArr) {
        return strArr[0] + GREATERTHAN + strArr[1];
    }

    public GreaterThanPtg copy() {
        return instance;
    }
}
