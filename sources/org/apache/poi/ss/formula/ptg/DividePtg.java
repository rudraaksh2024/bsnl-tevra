package org.apache.poi.ss.formula.ptg;

import org.apache.poi.openxml4j.opc.PackagingURIHelper;

public final class DividePtg extends ValueOperatorPtg {
    public static final DividePtg instance = new DividePtg();
    public static final byte sid = 6;

    public int getNumberOfOperands() {
        return 2;
    }

    public byte getSid() {
        return 6;
    }

    private DividePtg() {
    }

    public String toFormulaString(String[] strArr) {
        return strArr[0] + PackagingURIHelper.FORWARD_SLASH_STRING + strArr[1];
    }

    public DividePtg copy() {
        return instance;
    }
}
