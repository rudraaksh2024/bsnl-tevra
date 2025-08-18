package org.apache.poi.xwpf.usermodel;

import java.util.HashMap;
import java.util.Map;

public enum ParagraphAlignment {
    START(1),
    CENTER(2),
    END(3),
    BOTH(4),
    MEDIUM_KASHIDA(5),
    DISTRIBUTE(6),
    NUM_TAB(7),
    HIGH_KASHIDA(8),
    LOW_KASHIDA(9),
    THAI_DISTRIBUTE(10),
    LEFT(11),
    RIGHT(12);
    
    private static final Map<Integer, ParagraphAlignment> imap = null;
    private final int value;

    static {
        int i;
        imap = new HashMap();
        for (ParagraphAlignment paragraphAlignment : values()) {
            imap.put(Integer.valueOf(paragraphAlignment.getValue()), paragraphAlignment);
        }
    }

    private ParagraphAlignment(int i) {
        this.value = i;
    }

    public static ParagraphAlignment valueOf(int i) {
        ParagraphAlignment paragraphAlignment = imap.get(Integer.valueOf(i));
        if (paragraphAlignment != null) {
            return paragraphAlignment;
        }
        throw new IllegalArgumentException("Unknown paragraph alignment: " + i);
    }

    public int getValue() {
        return this.value;
    }
}
