package org.apache.poi.common.usermodel.fonts;

public enum FontFamily {
    FF_DONTCARE(0),
    FF_ROMAN(1),
    FF_SWISS(2),
    FF_MODERN(3),
    FF_SCRIPT(4),
    FF_DECORATIVE(5);
    
    private int nativeId;

    private FontFamily(int i) {
        this.nativeId = i;
    }

    public int getFlag() {
        return this.nativeId;
    }

    public static FontFamily valueOf(int i) {
        for (FontFamily fontFamily : values()) {
            if (fontFamily.nativeId == i) {
                return fontFamily;
            }
        }
        return null;
    }

    public static FontFamily valueOfPitchFamily(byte b) {
        return valueOf(b >>> 4);
    }
}
