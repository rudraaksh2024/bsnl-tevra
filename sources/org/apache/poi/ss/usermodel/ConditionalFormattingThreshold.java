package org.apache.poi.ss.usermodel;

public interface ConditionalFormattingThreshold {
    String getFormula();

    RangeType getRangeType();

    Double getValue();

    void setFormula(String str);

    void setRangeType(RangeType rangeType);

    void setValue(Double d);

    public enum RangeType {
        NUMBER(1, "num"),
        MIN(2, "min"),
        MAX(3, "max"),
        PERCENT(4, "percent"),
        PERCENTILE(5, "percentile"),
        UNALLOCATED(6, (int) null),
        FORMULA(7, "formula");
        
        public final int id;
        public final String name;

        public String toString() {
            return this.id + " - " + this.name;
        }

        public static RangeType byId(int i) {
            if (i <= 0 || i > values().length) {
                return null;
            }
            return values()[i - 1];
        }

        public static RangeType byName(String str) {
            for (RangeType rangeType : values()) {
                String str2 = rangeType.name;
                if (str2 == null && str == null) {
                    return rangeType;
                }
                if (str2 != null && str2.equals(str)) {
                    return rangeType;
                }
            }
            return null;
        }

        private RangeType(int i, String str) {
            this.id = i;
            this.name = str;
        }
    }
}
