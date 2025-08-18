package org.apache.poi.ss.formula.ptg;

public final class LessEqualPtg extends ValueOperatorPtg {
    public static final LessEqualPtg instance = new LessEqualPtg();
    public static final byte sid = 10;

    public int getNumberOfOperands() {
        return 2;
    }

    public byte getSid() {
        return 10;
    }

    private LessEqualPtg() {
    }

    public String toFormulaString(String[] strArr) {
        return strArr[0] + "<=" + strArr[1];
    }

    public LessEqualPtg copy() {
        return instance;
    }
}
