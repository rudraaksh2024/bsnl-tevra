package org.apache.poi.ss.usermodel;

import org.apache.poi.util.Removal;

public interface CellStyle {
    void cloneStyleFrom(CellStyle cellStyle);

    HorizontalAlignment getAlignment();

    BorderStyle getBorderBottom();

    BorderStyle getBorderLeft();

    BorderStyle getBorderRight();

    BorderStyle getBorderTop();

    short getBottomBorderColor();

    short getDataFormat();

    String getDataFormatString();

    short getFillBackgroundColor();

    Color getFillBackgroundColorColor();

    short getFillForegroundColor();

    Color getFillForegroundColorColor();

    FillPatternType getFillPattern();

    int getFontIndex();

    @Deprecated
    @Removal(version = "6.0.0")
    int getFontIndexAsInt();

    boolean getHidden();

    short getIndention();

    short getIndex();

    short getLeftBorderColor();

    boolean getLocked();

    boolean getQuotePrefixed();

    short getRightBorderColor();

    short getRotation();

    boolean getShrinkToFit();

    short getTopBorderColor();

    VerticalAlignment getVerticalAlignment();

    boolean getWrapText();

    void setAlignment(HorizontalAlignment horizontalAlignment);

    void setBorderBottom(BorderStyle borderStyle);

    void setBorderLeft(BorderStyle borderStyle);

    void setBorderRight(BorderStyle borderStyle);

    void setBorderTop(BorderStyle borderStyle);

    void setBottomBorderColor(short s);

    void setDataFormat(short s);

    void setFillBackgroundColor(short s);

    void setFillForegroundColor(short s);

    void setFillPattern(FillPatternType fillPatternType);

    void setFont(Font font);

    void setHidden(boolean z);

    void setIndention(short s);

    void setLeftBorderColor(short s);

    void setLocked(boolean z);

    void setQuotePrefixed(boolean z);

    void setRightBorderColor(short s);

    void setRotation(short s);

    void setShrinkToFit(boolean z);

    void setTopBorderColor(short s);

    void setVerticalAlignment(VerticalAlignment verticalAlignment);

    void setWrapText(boolean z);
}
