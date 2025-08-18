package org.apache.poi.ss.formula.ptg;

import java.util.Map;
import java.util.function.Supplier;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.LittleEndianInput;
import org.apache.poi.util.LittleEndianOutput;

public final class NumberPtg extends ScalarConstantPtg {
    public static final int SIZE = 9;
    public static final byte sid = 31;
    private final double field_1_value;

    public NumberPtg copy() {
        return this;
    }

    public byte getSid() {
        return sid;
    }

    public int getSize() {
        return 9;
    }

    public NumberPtg(LittleEndianInput littleEndianInput) {
        this(littleEndianInput.readDouble());
    }

    public NumberPtg(String str) {
        this(Double.parseDouble(str));
    }

    public NumberPtg(double d) {
        this.field_1_value = d;
    }

    public double getValue() {
        return this.field_1_value;
    }

    public void write(LittleEndianOutput littleEndianOutput) {
        littleEndianOutput.writeByte(getPtgClass() + sid);
        littleEndianOutput.writeDouble(getValue());
    }

    public String toFormulaString() {
        return NumberToTextConverter.toText(this.field_1_value);
    }

    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("value", new NumberPtg$$ExternalSyntheticLambda0(this));
    }
}
