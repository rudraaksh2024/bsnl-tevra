package org.apache.poi.ss.formula.ptg;

import java.util.Map;
import java.util.function.Supplier;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.LittleEndianInput;
import org.apache.poi.util.LittleEndianOutput;

public final class MemErrPtg extends OperandPtg {
    private static final int SIZE = 7;
    public static final short sid = 39;
    private int field_1_reserved;
    private short field_2_subex_len;

    public byte getDefaultOperandClass() {
        return 32;
    }

    public byte getSid() {
        return 39;
    }

    public int getSize() {
        return 7;
    }

    public String toFormulaString() {
        return "ERR#";
    }

    public MemErrPtg(MemErrPtg memErrPtg) {
        super(memErrPtg);
        this.field_1_reserved = memErrPtg.field_1_reserved;
        this.field_2_subex_len = memErrPtg.field_2_subex_len;
    }

    public MemErrPtg(LittleEndianInput littleEndianInput) {
        this.field_1_reserved = littleEndianInput.readInt();
        this.field_2_subex_len = littleEndianInput.readShort();
    }

    public void write(LittleEndianOutput littleEndianOutput) {
        littleEndianOutput.writeByte(getPtgClass() + 39);
        littleEndianOutput.writeInt(this.field_1_reserved);
        littleEndianOutput.writeShort(this.field_2_subex_len);
    }

    public int getLenRefSubexpression() {
        return this.field_2_subex_len;
    }

    public MemErrPtg copy() {
        return new MemErrPtg(this);
    }

    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("lenRefSubexpression", new MemErrPtg$$ExternalSyntheticLambda0(this));
    }
}
