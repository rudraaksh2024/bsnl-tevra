package org.apache.poi.common.usermodel.fonts;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.NavigableMap;
import java.util.TreeMap;

public enum FontGroup {
    LATIN,
    EAST_ASIAN,
    SYMBOL,
    COMPLEX_SCRIPT;
    
    private static NavigableMap<Integer, Range> UCS_RANGES;

    static {
        FontGroup fontGroup;
        FontGroup fontGroup2;
        FontGroup fontGroup3;
        FontGroup fontGroup4;
        TreeMap treeMap = new TreeMap();
        UCS_RANGES = treeMap;
        treeMap.put(0, new Range(127, fontGroup));
        UCS_RANGES.put(128, new Range(166, fontGroup));
        UCS_RANGES.put(169, new Range(175, fontGroup));
        UCS_RANGES.put(178, new Range(179, fontGroup));
        UCS_RANGES.put(181, new Range(214, fontGroup));
        UCS_RANGES.put(216, new Range(246, fontGroup));
        UCS_RANGES.put(248, new Range(1423, fontGroup));
        UCS_RANGES.put(1424, new Range(1871, fontGroup4));
        UCS_RANGES.put(1920, new Range(1983, fontGroup4));
        UCS_RANGES.put(2304, new Range(4255, fontGroup4));
        UCS_RANGES.put(4256, new Range(4351, fontGroup));
        UCS_RANGES.put(4608, new Range(4991, fontGroup));
        UCS_RANGES.put(5024, new Range(6015, fontGroup));
        UCS_RANGES.put(7424, new Range(7551, fontGroup));
        UCS_RANGES.put(7680, new Range(8191, fontGroup));
        UCS_RANGES.put(6016, new Range(6319, fontGroup4));
        UCS_RANGES.put(8192, new Range(8203, fontGroup));
        UCS_RANGES.put(8204, new Range(8207, fontGroup4));
        UCS_RANGES.put(8208, new Range(8233, fontGroup));
        UCS_RANGES.put(8234, new Range(8239, fontGroup4));
        UCS_RANGES.put(8240, new Range(8262, fontGroup));
        UCS_RANGES.put(8266, new Range(9311, fontGroup));
        UCS_RANGES.put(9840, new Range(9841, fontGroup4));
        UCS_RANGES.put(10176, new Range(11263, fontGroup));
        UCS_RANGES.put(12441, new Range(12442, fontGroup2));
        UCS_RANGES.put(55349, new Range(55349, fontGroup));
        UCS_RANGES.put(61440, new Range(61695, fontGroup3));
        UCS_RANGES.put(64256, new Range(64279, fontGroup));
        UCS_RANGES.put(64285, new Range(64335, fontGroup4));
        UCS_RANGES.put(65104, new Range(65135, fontGroup));
    }

    public static class FontGroupRange {
        /* access modifiers changed from: private */
        public final FontGroup fontGroup;
        private int len;

        FontGroupRange(FontGroup fontGroup2) {
            this.len = 0;
            this.fontGroup = fontGroup2;
        }

        public int getLength() {
            return this.len;
        }

        public FontGroup getFontGroup() {
            return this.fontGroup;
        }

        /* access modifiers changed from: package-private */
        public void increaseLength(int i) {
            this.len += i;
        }
    }

    private static class Range {
        private final FontGroup fontGroup;
        private final int upper;

        Range(int i, FontGroup fontGroup2) {
            this.upper = i;
            this.fontGroup = fontGroup2;
        }

        /* access modifiers changed from: package-private */
        public int getUpper() {
            return this.upper;
        }

        /* access modifiers changed from: package-private */
        public FontGroup getFontGroup() {
            return this.fontGroup;
        }
    }

    public static List<FontGroupRange> getFontGroupRanges(String str) {
        FontGroup fontGroup;
        ArrayList arrayList = new ArrayList();
        if (str != null && !str.isEmpty()) {
            int length = str.length();
            FontGroupRange fontGroupRange = null;
            int i = 0;
            while (i < length) {
                int codePointAt = str.codePointAt(i);
                int charCount = Character.charCount(codePointAt);
                if (fontGroupRange == null || " \n\r".indexOf(codePointAt) <= -1) {
                    fontGroup = lookup(codePointAt);
                } else {
                    fontGroup = fontGroupRange.fontGroup;
                }
                if (fontGroupRange == null || fontGroupRange.fontGroup != fontGroup) {
                    fontGroupRange = new FontGroupRange(fontGroup);
                    arrayList.add(fontGroupRange);
                }
                fontGroupRange.increaseLength(charCount);
                i += charCount;
            }
        }
        return arrayList;
    }

    public static FontGroup getFontGroupFirst(String str) {
        return (str == null || str.isEmpty()) ? LATIN : lookup(str.codePointAt(0));
    }

    private static FontGroup lookup(int i) {
        Map.Entry<Integer, Range> floorEntry = UCS_RANGES.floorEntry(Integer.valueOf(i));
        Range value = floorEntry != null ? floorEntry.getValue() : null;
        return (value == null || i > value.getUpper()) ? EAST_ASIAN : value.getFontGroup();
    }
}
