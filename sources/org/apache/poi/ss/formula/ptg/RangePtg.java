package org.apache.poi.ss.formula.ptg;

import org.apache.logging.log4j.message.ParameterizedMessage;
import org.apache.poi.util.LittleEndianOutput;

public final class RangePtg extends OperationPtg {
    public static final int SIZE = 1;
    public static final RangePtg instance = new RangePtg();
    public static final byte sid = 17;

    public int getNumberOfOperands() {
        return 2;
    }

    public byte getSid() {
        return sid;
    }

    public int getSize() {
        return 1;
    }

    public final boolean isBaseToken() {
        return true;
    }

    public String toFormulaString() {
        return ParameterizedMessage.ERROR_MSG_SEPARATOR;
    }

    private RangePtg() {
    }

    public void write(LittleEndianOutput littleEndianOutput) {
        littleEndianOutput.writeByte(getPtgClass() + sid);
    }

    public String toFormulaString(String[] strArr) {
        return strArr[0] + ParameterizedMessage.ERROR_MSG_SEPARATOR + strArr[1];
    }

    public RangePtg copy() {
        return instance;
    }
}
