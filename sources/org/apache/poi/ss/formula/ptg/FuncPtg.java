package org.apache.poi.ss.formula.ptg;

import org.apache.poi.ss.formula.function.FunctionMetadata;
import org.apache.poi.ss.formula.function.FunctionMetadataRegistry;
import org.apache.poi.util.LittleEndianInput;
import org.apache.poi.util.LittleEndianOutput;

public final class FuncPtg extends AbstractFunctionPtg {
    public static final int SIZE = 3;
    public static final byte sid = 33;

    public FuncPtg copy() {
        return this;
    }

    public byte getSid() {
        return 33;
    }

    public int getSize() {
        return 3;
    }

    public static FuncPtg create(LittleEndianInput littleEndianInput) {
        return create(littleEndianInput.readUShort());
    }

    private FuncPtg(int i, FunctionMetadata functionMetadata) {
        super(i, functionMetadata.getReturnClassCode(), functionMetadata.getParameterClassCodes(), functionMetadata.getMinParams());
    }

    public static FuncPtg create(int i) {
        FunctionMetadata functionByIndex = FunctionMetadataRegistry.getFunctionByIndex(i);
        if (functionByIndex != null) {
            return new FuncPtg(i, functionByIndex);
        }
        throw new RuntimeException("Invalid built-in function index (" + i + ")");
    }

    public void write(LittleEndianOutput littleEndianOutput) {
        littleEndianOutput.writeByte(getPtgClass() + 33);
        littleEndianOutput.writeShort(getFunctionIndex());
    }
}
