package org.apache.poi.ss.formula.ptg;

public final class MultiplyPtg extends ValueOperatorPtg {
    public static final MultiplyPtg instance = new MultiplyPtg();
    public static final byte sid = 5;

    public int getNumberOfOperands() {
        return 2;
    }

    public byte getSid() {
        return 5;
    }

    private MultiplyPtg() {
    }

    public String toFormulaString(String[] strArr) {
        return strArr[0] + "*" + strArr[1];
    }

    public MultiplyPtg copy() {
        return instance;
    }
}
