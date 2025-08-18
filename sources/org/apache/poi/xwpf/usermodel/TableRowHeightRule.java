package org.apache.poi.xwpf.usermodel;

import java.util.HashMap;
import java.util.Map;

public enum TableRowHeightRule {
    AUTO(1),
    EXACT(2),
    AT_LEAST(3);
    
    private static Map<Integer, TableRowHeightRule> imap;
    private final int value;

    static {
        int i;
        imap = new HashMap();
        for (TableRowHeightRule tableRowHeightRule : values()) {
            imap.put(Integer.valueOf(tableRowHeightRule.getValue()), tableRowHeightRule);
        }
    }

    private TableRowHeightRule(int i) {
        this.value = i;
    }

    public static TableRowHeightRule valueOf(int i) {
        TableRowHeightRule tableRowHeightRule = imap.get(Integer.valueOf(i));
        if (tableRowHeightRule != null) {
            return tableRowHeightRule;
        }
        throw new IllegalArgumentException("Unknown table row height rule: " + i);
    }

    public int getValue() {
        return this.value;
    }
}
