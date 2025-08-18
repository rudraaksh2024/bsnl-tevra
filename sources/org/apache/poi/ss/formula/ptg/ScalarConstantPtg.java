package org.apache.poi.ss.formula.ptg;

public abstract class ScalarConstantPtg extends Ptg {
    public final byte getDefaultOperandClass() {
        return 32;
    }

    public final boolean isBaseToken() {
        return true;
    }
}
