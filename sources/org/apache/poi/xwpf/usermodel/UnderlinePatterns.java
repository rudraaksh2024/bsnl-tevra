package org.apache.poi.xwpf.usermodel;

import java.util.HashMap;
import java.util.Map;

public enum UnderlinePatterns {
    SINGLE(1),
    WORDS(2),
    DOUBLE(3),
    THICK(4),
    DOTTED(5),
    DOTTED_HEAVY(6),
    DASH(7),
    DASHED_HEAVY(8),
    DASH_LONG(9),
    DASH_LONG_HEAVY(10),
    DOT_DASH(11),
    DASH_DOT_HEAVY(12),
    DOT_DOT_DASH(13),
    DASH_DOT_DOT_HEAVY(14),
    WAVE(15),
    WAVY_HEAVY(16),
    WAVY_DOUBLE(17),
    NONE(18);
    
    private static Map<Integer, UnderlinePatterns> imap;
    private final int value;

    static {
        imap = new HashMap();
        for (UnderlinePatterns underlinePatterns : values()) {
            imap.put(Integer.valueOf(underlinePatterns.getValue()), underlinePatterns);
        }
    }

    private UnderlinePatterns(int i) {
        this.value = i;
    }

    public static UnderlinePatterns valueOf(int i) {
        UnderlinePatterns underlinePatterns = imap.get(Integer.valueOf(i));
        if (underlinePatterns != null) {
            return underlinePatterns;
        }
        throw new IllegalArgumentException("Unknown underline pattern: " + i);
    }

    public int getValue() {
        return this.value;
    }
}
