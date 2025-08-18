package org.apache.poi.ss.formula.ptg;

import java.util.Map;
import java.util.function.Supplier;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.LittleEndianInput;
import org.apache.poi.util.LittleEndianOutput;

public final class MemAreaPtg extends OperandPtg {
    private static final int SIZE = 7;
    public static final short sid = 38;
    private final int field_1_reserved;
    private final int field_2_subex_len;

    public MemAreaPtg copy() {
        return this;
    }

    public byte getDefaultOperandClass() {
        return 32;
    }

    public byte getSid() {
        return 38;
    }

    public int getSize() {
        return 7;
    }

    public String toFormulaString() {
        return "";
    }

    public MemAreaPtg(int i) {
        this.field_1_reserved = 0;
        this.field_2_subex_len = i;
    }

    public MemAreaPtg(LittleEndianInput littleEndianInput) {
        this.field_1_reserved = littleEndianInput.readInt();
        this.field_2_subex_len = littleEndianInput.readShort();
    }

    public int getLenRefSubexpression() {
        return this.field_2_subex_len;
    }

    public void write(LittleEndianOutput littleEndianOutput) {
        littleEndianOutput.writeByte(getPtgClass() + 38);
        littleEndianOutput.writeInt(this.field_1_reserved);
        littleEndianOutput.writeShort(this.field_2_subex_len);
    }

    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("lenRefSubexpression", new MemAreaPtg$$ExternalSyntheticLambda0(this));
    }
}
