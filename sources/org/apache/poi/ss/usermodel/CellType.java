package org.apache.poi.ss.usermodel;

public enum CellType {
    _NONE(-1),
    NUMERIC(0),
    STRING(1),
    FORMULA(2),
    BLANK(3),
    BOOLEAN(4),
    ERROR(5);
    
    private final int code;

    private CellType(int i) {
        this.code = i;
    }

    public static CellType forInt(int i) {
        for (CellType cellType : values()) {
            if (cellType.code == i) {
                return cellType;
            }
        }
        throw new IllegalArgumentException("Invalid CellType code: " + i);
    }

    public int getCode() {
        return this.code;
    }
}
