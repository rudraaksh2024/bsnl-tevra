package org.apache.poi.ss.usermodel;

public enum BorderStyle {
    NONE(0),
    THIN(1),
    MEDIUM(2),
    DASHED(3),
    DOTTED(4),
    THICK(5),
    DOUBLE(6),
    HAIR(7),
    MEDIUM_DASHED(8),
    DASH_DOT(9),
    MEDIUM_DASH_DOT(10),
    DASH_DOT_DOT(11),
    MEDIUM_DASH_DOT_DOT(12),
    SLANTED_DASH_DOT(13);
    
    private static final BorderStyle[] _table = null;
    private final short code;

    static {
        _table = new BorderStyle[14];
        for (BorderStyle borderStyle : values()) {
            _table[borderStyle.getCode()] = borderStyle;
        }
    }

    private BorderStyle(int i) {
        this.code = (short) i;
    }

    public short getCode() {
        return this.code;
    }

    public static BorderStyle valueOf(short s) {
        return _table[s];
    }
}
