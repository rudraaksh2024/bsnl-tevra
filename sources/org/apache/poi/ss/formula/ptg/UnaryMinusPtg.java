package org.apache.poi.ss.formula.ptg;

public final class UnaryMinusPtg extends ValueOperatorPtg {
    private static final String MINUS = "-";
    public static final UnaryMinusPtg instance = new UnaryMinusPtg();
    public static final byte sid = 19;

    public int getNumberOfOperands() {
        return 1;
    }

    public byte getSid() {
        return sid;
    }

    private UnaryMinusPtg() {
    }

    public String toFormulaString(String[] strArr) {
        return "-" + strArr[0];
    }

    public UnaryMinusPtg copy() {
        return instance;
    }
}
