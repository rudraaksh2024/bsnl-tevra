package org.apache.poi.ss.usermodel;

public interface BorderFormatting {
    BorderStyle getBorderBottom();

    BorderStyle getBorderDiagonal();

    BorderStyle getBorderHorizontal();

    BorderStyle getBorderLeft();

    BorderStyle getBorderRight();

    BorderStyle getBorderTop();

    BorderStyle getBorderVertical();

    short getBottomBorderColor();

    Color getBottomBorderColorColor();

    short getDiagonalBorderColor();

    Color getDiagonalBorderColorColor();

    short getHorizontalBorderColor();

    Color getHorizontalBorderColorColor();

    short getLeftBorderColor();

    Color getLeftBorderColorColor();

    short getRightBorderColor();

    Color getRightBorderColorColor();

    short getTopBorderColor();

    Color getTopBorderColorColor();

    short getVerticalBorderColor();

    Color getVerticalBorderColorColor();

    void setBorderBottom(BorderStyle borderStyle);

    void setBorderDiagonal(BorderStyle borderStyle);

    void setBorderHorizontal(BorderStyle borderStyle);

    void setBorderLeft(BorderStyle borderStyle);

    void setBorderRight(BorderStyle borderStyle);

    void setBorderTop(BorderStyle borderStyle);

    void setBorderVertical(BorderStyle borderStyle);

    void setBottomBorderColor(Color color);

    void setBottomBorderColor(short s);

    void setDiagonalBorderColor(Color color);

    void setDiagonalBorderColor(short s);

    void setHorizontalBorderColor(Color color);

    void setHorizontalBorderColor(short s);

    void setLeftBorderColor(Color color);

    void setLeftBorderColor(short s);

    void setRightBorderColor(Color color);

    void setRightBorderColor(short s);

    void setTopBorderColor(Color color);

    void setTopBorderColor(short s);

    void setVerticalBorderColor(Color color);

    void setVerticalBorderColor(short s);
}
