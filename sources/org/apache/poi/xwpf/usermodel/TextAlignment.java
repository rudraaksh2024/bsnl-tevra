package org.apache.poi.xwpf.usermodel;

import java.util.HashMap;
import java.util.Map;

public enum TextAlignment {
    TOP(1),
    CENTER(2),
    BASELINE(3),
    BOTTOM(4),
    AUTO(5);
    
    private static Map<Integer, TextAlignment> imap;
    private final int value;

    static {
        int i;
        imap = new HashMap();
        for (TextAlignment textAlignment : values()) {
            imap.put(Integer.valueOf(textAlignment.getValue()), textAlignment);
        }
    }

    private TextAlignment(int i) {
        this.value = i;
    }

    public static TextAlignment valueOf(int i) {
        TextAlignment textAlignment = imap.get(Integer.valueOf(i));
        if (textAlignment != null) {
            return textAlignment;
        }
        throw new IllegalArgumentException("Unknown text alignment: " + i);
    }

    public int getValue() {
        return this.value;
    }
}
