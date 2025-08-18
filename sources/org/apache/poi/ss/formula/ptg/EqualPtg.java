package org.apache.poi.ss.formula.ptg;

public final class EqualPtg extends ValueOperatorPtg {
    public static final EqualPtg instance = new EqualPtg();
    public static final byte sid = 11;

    public int getNumberOfOperands() {
        return 2;
    }

    public byte getSid() {
        return 11;
    }

    private EqualPtg() {
    }

    public String toFormulaString(String[] strArr) {
        return strArr[0] + "=" + strArr[1];
    }

    public EqualPtg copy() {
        return instance;
    }
}
