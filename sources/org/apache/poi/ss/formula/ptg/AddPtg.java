package org.apache.poi.ss.formula.ptg;

public final class AddPtg extends ValueOperatorPtg {
    private static final String ADD = "+";
    public static final AddPtg instance = new AddPtg();
    public static final byte sid = 3;

    public int getNumberOfOperands() {
        return 2;
    }

    public byte getSid() {
        return 3;
    }

    private AddPtg() {
    }

    public String toFormulaString(String[] strArr) {
        return strArr[0] + ADD + strArr[1];
    }

    public AddPtg copy() {
        return instance;
    }
}
