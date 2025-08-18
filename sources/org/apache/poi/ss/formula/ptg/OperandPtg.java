package org.apache.poi.ss.formula.ptg;

public abstract class OperandPtg extends Ptg {
    public abstract OperandPtg copy();

    public final boolean isBaseToken() {
        return false;
    }

    protected OperandPtg() {
    }

    protected OperandPtg(OperandPtg operandPtg) {
        super(operandPtg);
    }
}
