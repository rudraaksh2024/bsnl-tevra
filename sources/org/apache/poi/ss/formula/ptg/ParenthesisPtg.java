package org.apache.poi.ss.formula.ptg;

import java.util.Map;
import java.util.function.Supplier;
import org.apache.poi.util.LittleEndianOutput;

public final class ParenthesisPtg extends ControlPtg {
    private static final int SIZE = 1;
    public static final ParenthesisPtg instance = new ParenthesisPtg();
    public static final byte sid = 21;

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
        return "()";
    }

    private ParenthesisPtg() {
    }

    public void write(LittleEndianOutput littleEndianOutput) {
        littleEndianOutput.writeByte(getPtgClass() + sid);
    }

    public String toFormulaString(String[] strArr) {
        return "(" + strArr[0] + ")";
    }

    public ParenthesisPtg copy() {
        return instance;
    }
}
