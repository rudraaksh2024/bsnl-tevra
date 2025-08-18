package org.apache.poi.ss.formula.ptg;

public final class GreaterEqualPtg extends ValueOperatorPtg {
    public static final int SIZE = 1;
    public static final GreaterEqualPtg instance = new GreaterEqualPtg();
    public static final byte sid = 12;

    public int getNumberOfOperands() {
        return 2;
    }

    public byte getSid() {
        return 12;
    }

    private GreaterEqualPtg() {
    }

    public String toFormulaString(String[] strArr) {
        return strArr[0] + ">=" + strArr[1];
    }

    public GreaterEqualPtg copy() {
        return instance;
    }
}
