package org.apache.poi.xssf.usermodel;

import org.apache.poi.ss.usermodel.BorderFormatting;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Color;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTBorder;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTBorderPr;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTColor;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.STBorderStyle;

public class XSSFBorderFormatting implements BorderFormatting {
    CTBorder _border;
    IndexedColorMap _colorMap;

    XSSFBorderFormatting(CTBorder cTBorder, IndexedColorMap indexedColorMap) {
        this._border = cTBorder;
        this._colorMap = indexedColorMap;
    }

    public BorderStyle getBorderBottom() {
        return getBorderStyle(this._border.getBottom());
    }

    public BorderStyle getBorderDiagonal() {
        return getBorderStyle(this._border.getDiagonal());
    }

    public BorderStyle getBorderLeft() {
        return getBorderStyle(this._border.getLeft());
    }

    public BorderStyle getBorderRight() {
        return getBorderStyle(this._border.getRight());
    }

    public BorderStyle getBorderTop() {
        return getBorderStyle(this._border.getTop());
    }

    public XSSFColor getBottomBorderColorColor() {
        return getColor(this._border.getBottom());
    }

    public short getBottomBorderColor() {
        return getIndexedColor(getBottomBorderColorColor());
    }

    public XSSFColor getDiagonalBorderColorColor() {
        return getColor(this._border.getDiagonal());
    }

    public short getDiagonalBorderColor() {
        return getIndexedColor(getDiagonalBorderColorColor());
    }

    public XSSFColor getLeftBorderColorColor() {
        return getColor(this._border.getLeft());
    }

    public short getLeftBorderColor() {
        return getIndexedColor(getLeftBorderColorColor());
    }

    public XSSFColor getRightBorderColorColor() {
        return getColor(this._border.getRight());
    }

    public short getRightBorderColor() {
        return getIndexedColor(getRightBorderColorColor());
    }

    public XSSFColor getTopBorderColorColor() {
        return getColor(this._border.getTop());
    }

    public short getTopBorderColor() {
        return getIndexedColor(getTopBorderColorColor());
    }

    public void setBorderBottom(BorderStyle borderStyle) {
        CTBorderPr bottom = this._border.isSetBottom() ? this._border.getBottom() : this._border.addNewBottom();
        if (borderStyle == BorderStyle.NONE) {
            this._border.unsetBottom();
        } else {
            bottom.setStyle(STBorderStyle.Enum.forInt(borderStyle.getCode() + 1));
        }
    }

    public void setBorderDiagonal(BorderStyle borderStyle) {
        CTBorderPr diagonal = this._border.isSetDiagonal() ? this._border.getDiagonal() : this._border.addNewDiagonal();
        if (borderStyle == BorderStyle.NONE) {
            this._border.unsetDiagonal();
        } else {
            diagonal.setStyle(STBorderStyle.Enum.forInt(borderStyle.getCode() + 1));
        }
    }

    public void setBorderLeft(BorderStyle borderStyle) {
        CTBorderPr left = this._border.isSetLeft() ? this._border.getLeft() : this._border.addNewLeft();
        if (borderStyle == BorderStyle.NONE) {
            this._border.unsetLeft();
        } else {
            left.setStyle(STBorderStyle.Enum.forInt(borderStyle.getCode() + 1));
        }
    }

    public void setBorderRight(BorderStyle borderStyle) {
        CTBorderPr right = this._border.isSetRight() ? this._border.getRight() : this._border.addNewRight();
        if (borderStyle == BorderStyle.NONE) {
            this._border.unsetRight();
        } else {
            right.setStyle(STBorderStyle.Enum.forInt(borderStyle.getCode() + 1));
        }
    }

    public void setBorderTop(BorderStyle borderStyle) {
        CTBorderPr top = this._border.isSetTop() ? this._border.getTop() : this._border.addNewTop();
        if (borderStyle == BorderStyle.NONE) {
            this._border.unsetTop();
        } else {
            top.setStyle(STBorderStyle.Enum.forInt(borderStyle.getCode() + 1));
        }
    }

    public void setBottomBorderColor(Color color) {
        XSSFColor xSSFColor = XSSFColor.toXSSFColor(color);
        if (xSSFColor == null) {
            CTColor cTColor = null;
            setBottomBorderColor((CTColor) null);
            return;
        }
        setBottomBorderColor(xSSFColor.getCTColor());
    }

    public void setBottomBorderColor(short s) {
        CTColor newInstance = CTColor.Factory.newInstance();
        newInstance.setIndexed((long) s);
        setBottomBorderColor(newInstance);
    }

    public void setBottomBorderColor(CTColor cTColor) {
        boolean isSetBottom = this._border.isSetBottom();
        CTBorder cTBorder = this._border;
        CTBorderPr bottom = isSetBottom ? cTBorder.getBottom() : cTBorder.addNewBottom();
        if (cTColor == null) {
            bottom.unsetColor();
        } else {
            bottom.setColor(cTColor);
        }
    }

    public void setDiagonalBorderColor(Color color) {
        XSSFColor xSSFColor = XSSFColor.toXSSFColor(color);
        if (xSSFColor == null) {
            CTColor cTColor = null;
            setDiagonalBorderColor((CTColor) null);
            return;
        }
        setDiagonalBorderColor(xSSFColor.getCTColor());
    }

    public void setDiagonalBorderColor(short s) {
        CTColor newInstance = CTColor.Factory.newInstance();
        newInstance.setIndexed((long) s);
        setDiagonalBorderColor(newInstance);
    }

    public void setDiagonalBorderColor(CTColor cTColor) {
        boolean isSetDiagonal = this._border.isSetDiagonal();
        CTBorder cTBorder = this._border;
        CTBorderPr diagonal = isSetDiagonal ? cTBorder.getDiagonal() : cTBorder.addNewDiagonal();
        if (cTColor == null) {
            diagonal.unsetColor();
        } else {
            diagonal.setColor(cTColor);
        }
    }

    public void setLeftBorderColor(Color color) {
        XSSFColor xSSFColor = XSSFColor.toXSSFColor(color);
        if (xSSFColor == null) {
            CTColor cTColor = null;
            setLeftBorderColor((CTColor) null);
            return;
        }
        setLeftBorderColor(xSSFColor.getCTColor());
    }

    public void setLeftBorderColor(short s) {
        CTColor newInstance = CTColor.Factory.newInstance();
        newInstance.setIndexed((long) s);
        setLeftBorderColor(newInstance);
    }

    public void setLeftBorderColor(CTColor cTColor) {
        boolean isSetLeft = this._border.isSetLeft();
        CTBorder cTBorder = this._border;
        CTBorderPr left = isSetLeft ? cTBorder.getLeft() : cTBorder.addNewLeft();
        if (cTColor == null) {
            left.unsetColor();
        } else {
            left.setColor(cTColor);
        }
    }

    public void setRightBorderColor(Color color) {
        XSSFColor xSSFColor = XSSFColor.toXSSFColor(color);
        if (xSSFColor == null) {
            CTColor cTColor = null;
            setRightBorderColor((CTColor) null);
            return;
        }
        setRightBorderColor(xSSFColor.getCTColor());
    }

    public void setRightBorderColor(short s) {
        CTColor newInstance = CTColor.Factory.newInstance();
        newInstance.setIndexed((long) s);
        setRightBorderColor(newInstance);
    }

    public void setRightBorderColor(CTColor cTColor) {
        boolean isSetRight = this._border.isSetRight();
        CTBorder cTBorder = this._border;
        CTBorderPr right = isSetRight ? cTBorder.getRight() : cTBorder.addNewRight();
        if (cTColor == null) {
            right.unsetColor();
        } else {
            right.setColor(cTColor);
        }
    }

    public void setTopBorderColor(Color color) {
        XSSFColor xSSFColor = XSSFColor.toXSSFColor(color);
        if (xSSFColor == null) {
            CTColor cTColor = null;
            setTopBorderColor((CTColor) null);
            return;
        }
        setTopBorderColor(xSSFColor.getCTColor());
    }

    public void setTopBorderColor(short s) {
        CTColor newInstance = CTColor.Factory.newInstance();
        newInstance.setIndexed((long) s);
        setTopBorderColor(newInstance);
    }

    public void setTopBorderColor(CTColor cTColor) {
        boolean isSetTop = this._border.isSetTop();
        CTBorder cTBorder = this._border;
        CTBorderPr top = isSetTop ? cTBorder.getTop() : cTBorder.addNewTop();
        if (cTColor == null) {
            top.unsetColor();
        } else {
            top.setColor(cTColor);
        }
    }

    public BorderStyle getBorderVertical() {
        return getBorderStyle(this._border.getVertical());
    }

    public BorderStyle getBorderHorizontal() {
        return getBorderStyle(this._border.getHorizontal());
    }

    public short getVerticalBorderColor() {
        return getIndexedColor(getVerticalBorderColorColor());
    }

    public XSSFColor getVerticalBorderColorColor() {
        return getColor(this._border.getVertical());
    }

    public short getHorizontalBorderColor() {
        return getIndexedColor(getHorizontalBorderColorColor());
    }

    public XSSFColor getHorizontalBorderColorColor() {
        return getColor(this._border.getHorizontal());
    }

    public void setBorderHorizontal(BorderStyle borderStyle) {
        CTBorderPr horizontal = this._border.isSetHorizontal() ? this._border.getHorizontal() : this._border.addNewHorizontal();
        if (borderStyle == BorderStyle.NONE) {
            this._border.unsetHorizontal();
        } else {
            horizontal.setStyle(STBorderStyle.Enum.forInt(borderStyle.getCode() + 1));
        }
    }

    public void setBorderVertical(BorderStyle borderStyle) {
        CTBorderPr vertical = this._border.isSetVertical() ? this._border.getVertical() : this._border.addNewVertical();
        if (borderStyle == BorderStyle.NONE) {
            this._border.unsetVertical();
        } else {
            vertical.setStyle(STBorderStyle.Enum.forInt(borderStyle.getCode() + 1));
        }
    }

    public void setHorizontalBorderColor(short s) {
        CTColor newInstance = CTColor.Factory.newInstance();
        newInstance.setIndexed((long) s);
        setHorizontalBorderColor(newInstance);
    }

    public void setHorizontalBorderColor(Color color) {
        XSSFColor xSSFColor = XSSFColor.toXSSFColor(color);
        if (xSSFColor == null) {
            CTColor cTColor = null;
            setBottomBorderColor((CTColor) null);
            return;
        }
        setHorizontalBorderColor(xSSFColor.getCTColor());
    }

    public void setHorizontalBorderColor(CTColor cTColor) {
        boolean isSetHorizontal = this._border.isSetHorizontal();
        CTBorder cTBorder = this._border;
        CTBorderPr horizontal = isSetHorizontal ? cTBorder.getHorizontal() : cTBorder.addNewHorizontal();
        if (cTColor == null) {
            horizontal.unsetColor();
        } else {
            horizontal.setColor(cTColor);
        }
    }

    public void setVerticalBorderColor(short s) {
        CTColor newInstance = CTColor.Factory.newInstance();
        newInstance.setIndexed((long) s);
        setVerticalBorderColor(newInstance);
    }

    public void setVerticalBorderColor(Color color) {
        XSSFColor xSSFColor = XSSFColor.toXSSFColor(color);
        if (xSSFColor == null) {
            CTColor cTColor = null;
            setBottomBorderColor((CTColor) null);
            return;
        }
        setVerticalBorderColor(xSSFColor.getCTColor());
    }

    public void setVerticalBorderColor(CTColor cTColor) {
        boolean isSetVertical = this._border.isSetVertical();
        CTBorder cTBorder = this._border;
        CTBorderPr vertical = isSetVertical ? cTBorder.getVertical() : cTBorder.addNewVertical();
        if (cTColor == null) {
            vertical.unsetColor();
        } else {
            vertical.setColor(cTColor);
        }
    }

    private BorderStyle getBorderStyle(CTBorderPr cTBorderPr) {
        if (cTBorderPr == null) {
            return BorderStyle.NONE;
        }
        STBorderStyle.Enum style = cTBorderPr.getStyle();
        return style == null ? BorderStyle.NONE : BorderStyle.valueOf((short) (style.intValue() - 1));
    }

    private short getIndexedColor(XSSFColor xSSFColor) {
        if (xSSFColor == null) {
            return 0;
        }
        return xSSFColor.getIndexed();
    }

    private XSSFColor getColor(CTBorderPr cTBorderPr) {
        if (cTBorderPr == null) {
            return null;
        }
        return XSSFColor.from(cTBorderPr.getColor(), this._colorMap);
    }
}
