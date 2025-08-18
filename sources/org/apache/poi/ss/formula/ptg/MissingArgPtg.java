package org.apache.poi.ss.formula.ptg;

import java.util.Map;
import java.util.function.Supplier;
import org.apache.poi.util.LittleEndianOutput;

public final class MissingArgPtg extends ScalarConstantPtg {
    private static final int SIZE = 1;
    public static final Ptg instance = new MissingArgPtg();
    public static final byte sid = 22;

    public MissingArgPtg copy() {
        return this;
    }

    public Map<String, Supplier<?>> getGenericProperties() {
        return null;
    }

    public byte getSid() {
        return sid;
    }

    public int getSize() {
        return 1;
    }

    public String toFormulaString() {
        return " ";
    }

    private MissingArgPtg() {
    }

    public void write(LittleEndianOutput littleEndianOutput) {
        littleEndianOutput.writeByte(getPtgClass() + sid);
    }
}
