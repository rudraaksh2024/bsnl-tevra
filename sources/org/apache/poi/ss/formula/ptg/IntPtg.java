package org.apache.poi.ss.formula.ptg;

import java.util.Map;
import java.util.function.Supplier;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.LittleEndianInput;
import org.apache.poi.util.LittleEndianOutput;

public final class IntPtg extends ScalarConstantPtg {
    private static final int MAX_VALUE = 65535;
    private static final int MIN_VALUE = 0;
    public static final int SIZE = 3;
    public static final byte sid = 30;
    private final int field_1_value;

    public static boolean isInRange(int i) {
        return i >= 0 && i <= 65535;
    }

    public IntPtg copy() {
        return this;
    }

    public byte getSid() {
        return sid;
    }

    public int getSize() {
        return 3;
    }

    public IntPtg(LittleEndianInput littleEndianInput) {
        this(littleEndianInput.readUShort());
    }

    public IntPtg(int i) {
        if (isInRange(i)) {
            this.field_1_value = i;
            return;
        }
        throw new IllegalArgumentException("value is out of range: " + i);
    }

    public int getValue() {
        return this.field_1_value;
    }

    public void write(LittleEndianOutput littleEndianOutput) {
        littleEndianOutput.writeByte(getPtgClass() + sid);
        littleEndianOutput.writeShort(getValue());
    }

    public String toFormulaString() {
        return String.valueOf(getValue());
    }

    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("value", new IntPtg$$ExternalSyntheticLambda0(this));
    }
}
