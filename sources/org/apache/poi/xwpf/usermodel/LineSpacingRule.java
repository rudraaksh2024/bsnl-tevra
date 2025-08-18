package org.apache.poi.xwpf.usermodel;

import java.util.HashMap;
import java.util.Map;

public enum LineSpacingRule {
    AUTO(1),
    EXACT(2),
    AT_LEAST(3);
    
    private static Map<Integer, LineSpacingRule> imap;
    private final int value;

    static {
        int i;
        imap = new HashMap();
        for (LineSpacingRule lineSpacingRule : values()) {
            imap.put(Integer.valueOf(lineSpacingRule.getValue()), lineSpacingRule);
        }
    }

    private LineSpacingRule(int i) {
        this.value = i;
    }

    public static LineSpacingRule valueOf(int i) {
        LineSpacingRule lineSpacingRule = imap.get(Integer.valueOf(i));
        if (lineSpacingRule != null) {
            return lineSpacingRule;
        }
        throw new IllegalArgumentException("Unknown line type: " + i);
    }

    public int getValue() {
        return this.value;
    }
}
