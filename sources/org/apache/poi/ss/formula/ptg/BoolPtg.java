package org.apache.poi.ss.formula.ptg;

import java.util.Map;
import java.util.function.Supplier;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.LittleEndianInput;
import org.apache.poi.util.LittleEndianOutput;

public final class BoolPtg extends ScalarConstantPtg {
    private static final BoolPtg FALSE = new BoolPtg(false);
    public static final int SIZE = 2;
    private static final BoolPtg TRUE = new BoolPtg(true);
    public static final byte sid = 29;
    private final boolean _value;

    public BoolPtg copy() {
        return this;
    }

    public byte getSid() {
        return sid;
    }

    public int getSize() {
        return 2;
    }

    private BoolPtg(boolean z) {
        this._value = z;
    }

    public static BoolPtg valueOf(boolean z) {
        return z ? TRUE : FALSE;
    }

    public static BoolPtg read(LittleEndianInput littleEndianInput) {
        boolean z = true;
        if (littleEndianInput.readByte() != 1) {
            z = false;
        }
        return valueOf(z);
    }

    public boolean getValue() {
        return this._value;
    }

    public void write(LittleEndianOutput littleEndianOutput) {
        littleEndianOutput.writeByte(getPtgClass() + sid);
        littleEndianOutput.writeByte(this._value ? 1 : 0);
    }

    public String toFormulaString() {
        return this._value ? "TRUE" : "FALSE";
    }

    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("value", new BoolPtg$$ExternalSyntheticLambda0(this));
    }
}
