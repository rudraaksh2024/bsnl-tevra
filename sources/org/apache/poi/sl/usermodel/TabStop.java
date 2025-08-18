package org.apache.poi.sl.usermodel;

public interface TabStop {
    double getPositionInPoints();

    TabStopType getType();

    void setPositionInPoints(double d);

    void setType(TabStopType tabStopType);

    public enum TabStopType {
        LEFT(0, 1),
        CENTER(1, 2),
        RIGHT(2, 3),
        DECIMAL(3, 4);
        
        public final int nativeId;
        public final int ooxmlId;

        private TabStopType(int i, int i2) {
            this.nativeId = i;
            this.ooxmlId = i2;
        }

        public static TabStopType fromNativeId(int i) {
            for (TabStopType tabStopType : values()) {
                if (tabStopType.nativeId == i) {
                    return tabStopType;
                }
            }
            return null;
        }

        public static TabStopType fromOoxmlId(int i) {
            for (TabStopType tabStopType : values()) {
                if (tabStopType.ooxmlId == i) {
                    return tabStopType;
                }
            }
            return null;
        }
    }
}
