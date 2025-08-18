package org.apache.poi.ss.formula.ptg;

import org.apache.poi.util.LittleEndianOutput;

public abstract class ValueOperatorPtg extends OperationPtg {
    public final byte getDefaultOperandClass() {
        return 32;
    }

    public final int getSize() {
        return 1;
    }

    public final boolean isBaseToken() {
        return true;
    }

    protected ValueOperatorPtg() {
    }

    public void write(LittleEndianOutput littleEndianOutput) {
        littleEndianOutput.writeByte(getSid());
    }

    public final String toFormulaString() {
        throw new RuntimeException("toFormulaString(String[] operands) should be used for subclasses of OperationPtgs");
    }
}
