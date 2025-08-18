package org.apache.poi.ss.formula.ptg;

import org.apache.poi.util.LittleEndianOutput;

public final class UnionPtg extends OperationPtg {
    public static final UnionPtg instance = new UnionPtg();
    public static final byte sid = 16;

    public int getNumberOfOperands() {
        return 2;
    }

    public byte getSid() {
        return sid;
    }

    public int getSize() {
        return 1;
    }

    public final boolean isBaseToken() {
        return true;
    }

    public String toFormulaString() {
        return ",";
    }

    private UnionPtg() {
    }

    public void write(LittleEndianOutput littleEndianOutput) {
        littleEndianOutput.writeByte(getPtgClass() + sid);
    }

    public String toFormulaString(String[] strArr) {
        return strArr[0] + "," + strArr[1];
    }

    public UnionPtg copy() {
        return instance;
    }
}
