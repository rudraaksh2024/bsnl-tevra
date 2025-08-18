package org.apache.poi.ss.formula.ptg;

import org.apache.logging.log4j.util.ProcessIdUtil;

public final class SubtractPtg extends ValueOperatorPtg {
    public static final SubtractPtg instance = new SubtractPtg();
    public static final byte sid = 4;

    public int getNumberOfOperands() {
        return 2;
    }

    public byte getSid() {
        return 4;
    }

    private SubtractPtg() {
    }

    public String toFormulaString(String[] strArr) {
        return strArr[0] + ProcessIdUtil.DEFAULT_PROCESSID + strArr[1];
    }

    public SubtractPtg copy() {
        return instance;
    }
}
