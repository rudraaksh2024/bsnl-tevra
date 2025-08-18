package org.apache.poi.ss.usermodel;

public interface FontFormatting {
    short getEscapementType();

    Color getFontColor();

    short getFontColorIndex();

    int getFontHeight();

    short getUnderlineType();

    boolean isBold();

    boolean isItalic();

    boolean isStruckout();

    void resetFontStyle();

    void setEscapementType(short s);

    void setFontColor(Color color);

    void setFontColorIndex(short s);

    void setFontHeight(int i);

    void setFontStyle(boolean z, boolean z2);

    void setUnderlineType(short s);
}
