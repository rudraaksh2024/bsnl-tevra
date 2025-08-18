package org.apache.poi.xwpf.usermodel;

import java.util.HashMap;
import java.util.Map;

public enum VerticalAlign {
    BASELINE(1),
    SUPERSCRIPT(2),
    SUBSCRIPT(3);
    
    private static Map<Integer, VerticalAlign> imap;
    private final int value;

    static {
        int i;
        imap = new HashMap();
        for (VerticalAlign verticalAlign : values()) {
            imap.put(Integer.valueOf(verticalAlign.getValue()), verticalAlign);
        }
    }

    private VerticalAlign(int i) {
        this.value = i;
    }

    public static VerticalAlign valueOf(int i) {
        VerticalAlign verticalAlign = imap.get(Integer.valueOf(i));
        if (verticalAlign != null) {
            return verticalAlign;
        }
        throw new IllegalArgumentException("Unknown vertical alignment: " + i);
    }

    public int getValue() {
        return this.value;
    }
}
