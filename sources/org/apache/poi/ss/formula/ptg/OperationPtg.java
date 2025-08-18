package org.apache.poi.ss.formula.ptg;

import java.util.Map;
import java.util.function.Supplier;

public abstract class OperationPtg extends Ptg {
    public static final int TYPE_BINARY = 1;
    public static final int TYPE_FUNCTION = 2;
    public static final int TYPE_UNARY = 0;

    public byte getDefaultOperandClass() {
        return 32;
    }

    public Map<String, Supplier<?>> getGenericProperties() {
        return null;
    }

    public abstract int getNumberOfOperands();

    public abstract String toFormulaString(String[] strArr);

    protected OperationPtg() {
    }
}
