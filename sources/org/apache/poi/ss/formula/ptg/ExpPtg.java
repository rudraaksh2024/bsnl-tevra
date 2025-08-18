package org.apache.poi.ss.formula.ptg;

import java.util.Map;
import java.util.function.Supplier;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.LittleEndianInput;
import org.apache.poi.util.LittleEndianOutput;

public final class ExpPtg extends ControlPtg {
    private static final int SIZE = 5;
    public static final short sid = 1;
    private final int field_1_first_row;
    private final int field_2_first_col;

    public ExpPtg copy() {
        return this;
    }

    public byte getSid() {
        return 1;
    }

    public int getSize() {
        return 5;
    }

    public ExpPtg(LittleEndianInput littleEndianInput) {
        this.field_1_first_row = littleEndianInput.readShort();
        this.field_2_first_col = littleEndianInput.readShort();
    }

    public ExpPtg(int i, int i2) {
        this.field_1_first_row = i;
        this.field_2_first_col = i2;
    }

    public void write(LittleEndianOutput littleEndianOutput) {
        littleEndianOutput.writeByte(getPtgClass() + 1);
        littleEndianOutput.writeShort(this.field_1_first_row);
        littleEndianOutput.writeShort(this.field_2_first_col);
    }

    public int getRow() {
        return this.field_1_first_row;
    }

    public int getColumn() {
        return this.field_2_first_col;
    }

    public String toFormulaString() {
        throw new RuntimeException("Coding Error: Expected ExpPtg to be converted from Shared to Non-Shared Formula by ValueRecordsAggregate, but it wasn't");
    }

    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("row", new ExpPtg$$ExternalSyntheticLambda0(this), "column", new ExpPtg$$ExternalSyntheticLambda1(this));
    }
}
