package org.apache.poi.ss.formula.ptg;

import java.util.Map;
import java.util.function.Supplier;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.LittleEndianInput;
import org.apache.poi.util.LittleEndianOutput;

public final class MemFuncPtg extends OperandPtg {
    public static final byte sid = 41;
    private final int field_1_len_ref_subexpression;

    public MemFuncPtg copy() {
        return this;
    }

    public byte getDefaultOperandClass() {
        return 0;
    }

    public byte getSid() {
        return sid;
    }

    public int getSize() {
        return 3;
    }

    public String toFormulaString() {
        return "";
    }

    public MemFuncPtg(LittleEndianInput littleEndianInput) {
        this(littleEndianInput.readUShort());
    }

    public MemFuncPtg(int i) {
        this.field_1_len_ref_subexpression = i;
    }

    public void write(LittleEndianOutput littleEndianOutput) {
        littleEndianOutput.writeByte(getPtgClass() + sid);
        littleEndianOutput.writeShort(this.field_1_len_ref_subexpression);
    }

    public int getNumberOfOperands() {
        return this.field_1_len_ref_subexpression;
    }

    public int getLenRefSubexpression() {
        return this.field_1_len_ref_subexpression;
    }

    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("lenRefSubexpression", new MemFuncPtg$$ExternalSyntheticLambda0(this));
    }
}
