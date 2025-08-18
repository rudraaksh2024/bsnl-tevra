package org.apache.poi.ss.formula.ptg;

public final class ConcatPtg extends ValueOperatorPtg {
    private static final String CONCAT = "&";
    public static final ConcatPtg instance = new ConcatPtg();
    public static final byte sid = 8;

    public int getNumberOfOperands() {
        return 2;
    }

    public byte getSid() {
        return 8;
    }

    private ConcatPtg() {
    }

    public String toFormulaString(String[] strArr) {
        return strArr[0] + CONCAT + strArr[1];
    }

    public ConcatPtg copy() {
        return instance;
    }
}
