package org.apache.poi.common.usermodel.fonts;

public enum FontPitch {
    DEFAULT(0),
    FIXED(1),
    VARIABLE(2);
    
    private int nativeId;

    private FontPitch(int i) {
        this.nativeId = i;
    }

    public int getNativeId() {
        return this.nativeId;
    }

    public static FontPitch valueOf(int i) {
        for (FontPitch fontPitch : values()) {
            if (fontPitch.nativeId == i) {
                return fontPitch;
            }
        }
        return null;
    }

    public static byte getNativeId(FontPitch fontPitch, FontFamily fontFamily) {
        return (byte) (fontPitch.getNativeId() | (fontFamily.getFlag() << 4));
    }

    public static FontPitch valueOfPitchFamily(byte b) {
        return valueOf((int) b & 3);
    }
}
