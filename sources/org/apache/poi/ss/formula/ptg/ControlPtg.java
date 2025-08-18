package org.apache.poi.ss.formula.ptg;

public abstract class ControlPtg extends Ptg {
    public boolean isBaseToken() {
        return true;
    }

    protected ControlPtg() {
    }

    public final byte getDefaultOperandClass() {
        throw new IllegalStateException("Control tokens are not classified");
    }
}
