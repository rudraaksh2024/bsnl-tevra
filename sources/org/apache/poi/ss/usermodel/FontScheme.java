package org.apache.poi.ss.usermodel;

public enum FontScheme {
    NONE(1),
    MAJOR(2),
    MINOR(3);
    
    private static final FontScheme[] _table = null;
    private final int value;

    static {
        FontScheme fontScheme;
        FontScheme fontScheme2;
        FontScheme fontScheme3;
        _table = new FontScheme[]{null, fontScheme, fontScheme2, fontScheme3};
    }

    private FontScheme(int i) {
        this.value = i;
    }

    public int getValue() {
        return this.value;
    }

    public static FontScheme valueOf(int i) {
        return _table[i];
    }
}
