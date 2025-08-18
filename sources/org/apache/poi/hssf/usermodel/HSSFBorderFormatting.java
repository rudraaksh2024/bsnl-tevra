package org.apache.poi.hssf.usermodel;

import org.apache.poi.hssf.record.CFRuleBase;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.BorderFormatting;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Color;

public final class HSSFBorderFormatting implements BorderFormatting {
    private final org.apache.poi.hssf.record.cf.BorderFormatting borderFormatting;
    private final CFRuleBase cfRuleRecord;
    private final HSSFWorkbook workbook;

    public void setBorderHorizontal(BorderStyle borderStyle) {
    }

    public void setBorderVertical(BorderStyle borderStyle) {
    }

    public void setHorizontalBorderColor(Color color) {
    }

    public void setHorizontalBorderColor(short s) {
    }

    public void setVerticalBorderColor(Color color) {
    }

    public void setVerticalBorderColor(short s) {
    }

    protected HSSFBorderFormatting(CFRuleBase cFRuleBase, HSSFWorkbook hSSFWorkbook) {
        this.workbook = hSSFWorkbook;
        this.cfRuleRecord = cFRuleBase;
        this.borderFormatting = cFRuleBase.getBorderFormatting();
    }

    /* access modifiers changed from: protected */
    public org.apache.poi.hssf.record.cf.BorderFormatting getBorderFormattingBlock() {
        return this.borderFormatting;
    }

    public BorderStyle getBorderBottom() {
        return BorderStyle.valueOf((short) this.borderFormatting.getBorderBottom());
    }

    public BorderStyle getBorderDiagonal() {
        return BorderStyle.valueOf((short) this.borderFormatting.getBorderDiagonal());
    }

    public BorderStyle getBorderLeft() {
        return BorderStyle.valueOf((short) this.borderFormatting.getBorderLeft());
    }

    public BorderStyle getBorderRight() {
        return BorderStyle.valueOf((short) this.borderFormatting.getBorderRight());
    }

    public BorderStyle getBorderTop() {
        return BorderStyle.valueOf((short) this.borderFormatting.getBorderTop());
    }

    public short getBottomBorderColor() {
        return (short) this.borderFormatting.getBottomBorderColor();
    }

    public HSSFColor getBottomBorderColorColor() {
        return this.workbook.getCustomPalette().getColor(this.borderFormatting.getBottomBorderColor());
    }

    public short getDiagonalBorderColor() {
        return (short) this.borderFormatting.getDiagonalBorderColor();
    }

    public HSSFColor getDiagonalBorderColorColor() {
        return this.workbook.getCustomPalette().getColor(this.borderFormatting.getDiagonalBorderColor());
    }

    public short getLeftBorderColor() {
        return (short) this.borderFormatting.getLeftBorderColor();
    }

    public HSSFColor getLeftBorderColorColor() {
        return this.workbook.getCustomPalette().getColor(this.borderFormatting.getLeftBorderColor());
    }

    public short getRightBorderColor() {
        return (short) this.borderFormatting.getRightBorderColor();
    }

    public HSSFColor getRightBorderColorColor() {
        return this.workbook.getCustomPalette().getColor(this.borderFormatting.getRightBorderColor());
    }

    public short getTopBorderColor() {
        return (short) this.borderFormatting.getTopBorderColor();
    }

    public HSSFColor getTopBorderColorColor() {
        return this.workbook.getCustomPalette().getColor(this.borderFormatting.getTopBorderColor());
    }

    public boolean isBackwardDiagonalOn() {
        return this.borderFormatting.isBackwardDiagonalOn();
    }

    public boolean isForwardDiagonalOn() {
        return this.borderFormatting.isForwardDiagonalOn();
    }

    public void setBackwardDiagonalOn(boolean z) {
        this.borderFormatting.setBackwardDiagonalOn(z);
        if (z) {
            this.cfRuleRecord.setTopLeftBottomRightBorderModified(z);
        }
    }

    public void setForwardDiagonalOn(boolean z) {
        this.borderFormatting.setForwardDiagonalOn(z);
        if (z) {
            this.cfRuleRecord.setBottomLeftTopRightBorderModified(z);
        }
    }

    public void setBorderBottom(BorderStyle borderStyle) {
        short code = borderStyle.getCode();
        this.borderFormatting.setBorderBottom(code);
        if (code != 0) {
            this.cfRuleRecord.setBottomBorderModified(true);
        } else {
            this.cfRuleRecord.setBottomBorderModified(false);
        }
    }

    public void setBorderDiagonal(BorderStyle borderStyle) {
        short code = borderStyle.getCode();
        this.borderFormatting.setBorderDiagonal(code);
        if (code != 0) {
            this.cfRuleRecord.setBottomLeftTopRightBorderModified(true);
            this.cfRuleRecord.setTopLeftBottomRightBorderModified(true);
            return;
        }
        this.cfRuleRecord.setBottomLeftTopRightBorderModified(false);
        this.cfRuleRecord.setTopLeftBottomRightBorderModified(false);
    }

    public void setBorderLeft(BorderStyle borderStyle) {
        short code = borderStyle.getCode();
        this.borderFormatting.setBorderLeft(code);
        if (code != 0) {
            this.cfRuleRecord.setLeftBorderModified(true);
        } else {
            this.cfRuleRecord.setLeftBorderModified(false);
        }
    }

    public void setBorderRight(BorderStyle borderStyle) {
        short code = borderStyle.getCode();
        this.borderFormatting.setBorderRight(code);
        if (code != 0) {
            this.cfRuleRecord.setRightBorderModified(true);
        } else {
            this.cfRuleRecord.setRightBorderModified(false);
        }
    }

    public void setBorderTop(BorderStyle borderStyle) {
        short code = borderStyle.getCode();
        this.borderFormatting.setBorderTop(code);
        if (code != 0) {
            this.cfRuleRecord.setTopBorderModified(true);
        } else {
            this.cfRuleRecord.setTopBorderModified(false);
        }
    }

    public void setBottomBorderColor(short s) {
        this.borderFormatting.setBottomBorderColor(s);
        if (s != 0) {
            this.cfRuleRecord.setBottomBorderModified(true);
        } else {
            this.cfRuleRecord.setBottomBorderModified(false);
        }
    }

    public void setBottomBorderColor(Color color) {
        HSSFColor hSSFColor = HSSFColor.toHSSFColor(color);
        if (hSSFColor == null) {
            setBottomBorderColor(0);
        } else {
            setBottomBorderColor(hSSFColor.getIndex());
        }
    }

    public void setDiagonalBorderColor(short s) {
        this.borderFormatting.setDiagonalBorderColor(s);
        if (s != 0) {
            this.cfRuleRecord.setBottomLeftTopRightBorderModified(true);
            this.cfRuleRecord.setTopLeftBottomRightBorderModified(true);
            return;
        }
        this.cfRuleRecord.setBottomLeftTopRightBorderModified(false);
        this.cfRuleRecord.setTopLeftBottomRightBorderModified(false);
    }

    public void setDiagonalBorderColor(Color color) {
        HSSFColor hSSFColor = HSSFColor.toHSSFColor(color);
        if (hSSFColor == null) {
            setDiagonalBorderColor(0);
        } else {
            setDiagonalBorderColor(hSSFColor.getIndex());
        }
    }

    public void setLeftBorderColor(short s) {
        this.borderFormatting.setLeftBorderColor(s);
        if (s != 0) {
            this.cfRuleRecord.setLeftBorderModified(true);
        } else {
            this.cfRuleRecord.setLeftBorderModified(false);
        }
    }

    public void setLeftBorderColor(Color color) {
        HSSFColor hSSFColor = HSSFColor.toHSSFColor(color);
        if (hSSFColor == null) {
            setLeftBorderColor(0);
        } else {
            setLeftBorderColor(hSSFColor.getIndex());
        }
    }

    public void setRightBorderColor(short s) {
        this.borderFormatting.setRightBorderColor(s);
        if (s != 0) {
            this.cfRuleRecord.setRightBorderModified(true);
        } else {
            this.cfRuleRecord.setRightBorderModified(false);
        }
    }

    public void setRightBorderColor(Color color) {
        HSSFColor hSSFColor = HSSFColor.toHSSFColor(color);
        if (hSSFColor == null) {
            setRightBorderColor(0);
        } else {
            setRightBorderColor(hSSFColor.getIndex());
        }
    }

    public void setTopBorderColor(short s) {
        this.borderFormatting.setTopBorderColor(s);
        if (s != 0) {
            this.cfRuleRecord.setTopBorderModified(true);
        } else {
            this.cfRuleRecord.setTopBorderModified(false);
        }
    }

    public void setTopBorderColor(Color color) {
        HSSFColor hSSFColor = HSSFColor.toHSSFColor(color);
        if (hSSFColor == null) {
            setTopBorderColor(0);
        } else {
            setTopBorderColor(hSSFColor.getIndex());
        }
    }

    public BorderStyle getBorderVertical() {
        return BorderStyle.NONE;
    }

    public BorderStyle getBorderHorizontal() {
        return BorderStyle.NONE;
    }

    public short getVerticalBorderColor() {
        return HSSFColor.HSSFColorPredefined.AUTOMATIC.getIndex();
    }

    public Color getVerticalBorderColorColor() {
        return HSSFColor.HSSFColorPredefined.AUTOMATIC.getColor();
    }

    public short getHorizontalBorderColor() {
        return HSSFColor.HSSFColorPredefined.AUTOMATIC.getIndex();
    }

    public Color getHorizontalBorderColorColor() {
        return HSSFColor.HSSFColorPredefined.AUTOMATIC.getColor();
    }
}
