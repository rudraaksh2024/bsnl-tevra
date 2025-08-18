package org.apache.poi.ss.formula.ptg;

public final class UnaryPlusPtg extends ValueOperatorPtg {
    private static final String ADD = "+";
    public static final UnaryPlusPtg instance = new UnaryPlusPtg();
    public static final byte sid = 18;

    public int getNumberOfOperands() {
        return 1;
    }

    public byte getSid() {
        return sid;
    }

    private UnaryPlusPtg() {
    }

    public String toFormulaString(String[] strArr) {
        return ADD + strArr[0];
    }

    public UnaryPlusPtg copy() {
        return instance;
    }
}
