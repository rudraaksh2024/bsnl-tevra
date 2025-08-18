package org.apache.poi.ss.formula.ptg;

public final class PowerPtg extends ValueOperatorPtg {
    public static final PowerPtg instance = new PowerPtg();
    public static final byte sid = 7;

    public int getNumberOfOperands() {
        return 2;
    }

    public byte getSid() {
        return 7;
    }

    private PowerPtg() {
    }

    public String toFormulaString(String[] strArr) {
        return strArr[0] + "^" + strArr[1];
    }

    public PowerPtg copy() {
        return instance;
    }
}
