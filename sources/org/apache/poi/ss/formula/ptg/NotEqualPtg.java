package org.apache.poi.ss.formula.ptg;

public final class NotEqualPtg extends ValueOperatorPtg {
    public static final NotEqualPtg instance = new NotEqualPtg();
    public static final byte sid = 14;

    public int getNumberOfOperands() {
        return 2;
    }

    public byte getSid() {
        return sid;
    }

    private NotEqualPtg() {
    }

    public String toFormulaString(String[] strArr) {
        return strArr[0] + "<>" + strArr[1];
    }

    public NotEqualPtg copy() {
        return instance;
    }
}
