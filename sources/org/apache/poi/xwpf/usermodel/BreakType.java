package org.apache.poi.xwpf.usermodel;

import java.util.HashMap;
import java.util.Map;

public enum BreakType {
    PAGE(1),
    COLUMN(2),
    TEXT_WRAPPING(3);
    
    private static Map<Integer, BreakType> imap;
    private final int value;

    static {
        int i;
        imap = new HashMap();
        for (BreakType breakType : values()) {
            imap.put(Integer.valueOf(breakType.getValue()), breakType);
        }
    }

    private BreakType(int i) {
        this.value = i;
    }

    public static BreakType valueOf(int i) {
        BreakType breakType = imap.get(Integer.valueOf(i));
        if (breakType != null) {
            return breakType;
        }
        throw new IllegalArgumentException("Unknown break type: " + i);
    }

    public int getValue() {
        return this.value;
    }
}
