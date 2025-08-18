package org.apache.poi.ss.formula.ptg;

public final class PercentPtg extends ValueOperatorPtg {
    private static final String PERCENT = "%";
    public static final int SIZE = 1;
    public static final PercentPtg instance = new PercentPtg();
    public static final byte sid = 20;

    public int getNumberOfOperands() {
        return 1;
    }

    public byte getSid() {
        return sid;
    }

    private PercentPtg() {
    }

    public String toFormulaString(String[] strArr) {
        return strArr[0] + PERCENT;
    }

    public PercentPtg copy() {
        return instance;
    }
}
