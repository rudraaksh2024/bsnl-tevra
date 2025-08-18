package org.apache.poi.ss.formula.ptg;

import java.util.Map;
import java.util.function.Supplier;
import org.apache.poi.util.LittleEndianOutput;

public class UnknownPtg extends Ptg {
    private final int _sid;
    private final short size = 1;

    public UnknownPtg copy() {
        return this;
    }

    public byte getDefaultOperandClass() {
        return 32;
    }

    public Map<String, Supplier<?>> getGenericProperties() {
        return null;
    }

    public int getSize() {
        return 1;
    }

    public boolean isBaseToken() {
        return true;
    }

    public String toFormulaString() {
        return "UNKNOWN";
    }

    public UnknownPtg(int i) {
        this._sid = i;
    }

    public void write(LittleEndianOutput littleEndianOutput) {
        littleEndianOutput.writeByte(this._sid);
    }

    public byte getSid() {
        return (byte) this._sid;
    }
}
