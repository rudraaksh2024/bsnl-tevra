package org.apache.poi.xssf.usermodel;

import org.apache.poi.common.Duplicatable;
import org.apache.poi.ooxml.POIXMLException;
import org.apache.poi.ooxml.POIXMLTypeLoader;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.ReadingOrder;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.util.Internal;
import org.apache.poi.util.Removal;
import org.apache.poi.xssf.model.StylesTable;
import org.apache.poi.xssf.model.ThemesTable;
import org.apache.poi.xssf.usermodel.extensions.XSSFCellAlignment;
import org.apache.poi.xssf.usermodel.extensions.XSSFCellBorder;
import org.apache.poi.xssf.usermodel.extensions.XSSFCellFill;
import org.apache.xmlbeans.XmlException;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTBorder;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTBorderPr;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCellAlignment;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTColor;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFill;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFont;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPatternFill;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTXf;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.STBorderStyle;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.STPatternType;

public class XSSFCellStyle implements CellStyle, Duplicatable {
    private XSSFCellAlignment _cellAlignment;
    private final CTXf _cellStyleXf;
    private CTXf _cellXf;
    private int _cellXfId;
    private XSSFFont _font;
    private final StylesTable _stylesSource;
    private ThemesTable _theme;

    public XSSFCellStyle(int i, int i2, StylesTable stylesTable, ThemesTable themesTable) {
        CTXf cTXf;
        this._cellXfId = i;
        this._stylesSource = stylesTable;
        this._cellXf = stylesTable.getCellXfAt(i);
        if (i2 == -1) {
            cTXf = null;
        } else {
            cTXf = stylesTable.getCellStyleXfAt(i2);
        }
        this._cellStyleXf = cTXf;
        this._theme = themesTable;
    }

    @Internal
    public CTXf getCoreXf() {
        return this._cellXf;
    }

    @Internal
    public CTXf getStyleXf() {
        return this._cellStyleXf;
    }

    public XSSFCellStyle(StylesTable stylesTable) {
        this._stylesSource = stylesTable;
        this._cellXf = CTXf.Factory.newInstance();
        this._cellStyleXf = null;
    }

    public void verifyBelongsToStylesSource(StylesTable stylesTable) {
        if (this._stylesSource != stylesTable) {
            throw new IllegalArgumentException("This Style does not belong to the supplied Workbook Styles Source. Are you trying to assign a style from one workbook to the cell of a different workbook?");
        }
    }

    public void cloneStyleFrom(CellStyle cellStyle) {
        if (cellStyle instanceof XSSFCellStyle) {
            XSSFCellStyle xSSFCellStyle = (XSSFCellStyle) cellStyle;
            if (xSSFCellStyle._stylesSource == this._stylesSource) {
                this._cellXf.set(xSSFCellStyle.getCoreXf());
                this._cellStyleXf.set(xSSFCellStyle.getStyleXf());
            } else {
                try {
                    if (this._cellXf.isSetAlignment()) {
                        this._cellXf.unsetAlignment();
                    }
                    if (this._cellXf.isSetExtLst()) {
                        this._cellXf.unsetExtLst();
                    }
                    this._cellXf = CTXf.Factory.parse(xSSFCellStyle.getCoreXf().toString(), POIXMLTypeLoader.DEFAULT_XML_OPTIONS);
                    addFill(CTFill.Factory.parse(xSSFCellStyle.getCTFill().toString(), POIXMLTypeLoader.DEFAULT_XML_OPTIONS));
                    addBorder(CTBorder.Factory.parse(xSSFCellStyle.getCTBorder().toString(), POIXMLTypeLoader.DEFAULT_XML_OPTIONS));
                    this._stylesSource.replaceCellXfAt(this._cellXfId, this._cellXf);
                    setDataFormat(new XSSFDataFormat(this._stylesSource).getFormat(xSSFCellStyle.getDataFormatString()));
                    try {
                        XSSFFont xSSFFont = new XSSFFont(CTFont.Factory.parse(xSSFCellStyle.getFont().getCTFont().toString(), POIXMLTypeLoader.DEFAULT_XML_OPTIONS));
                        xSSFFont.registerTo(this._stylesSource);
                        setFont(xSSFFont);
                    } catch (XmlException e) {
                        throw new POIXMLException((Throwable) e);
                    }
                } catch (XmlException e2) {
                    throw new POIXMLException((Throwable) e2);
                }
            }
            this._font = null;
            this._cellAlignment = null;
            return;
        }
        throw new IllegalArgumentException("Can only clone from one XSSFCellStyle to another, not between HSSFCellStyle and XSSFCellStyle");
    }

    private void addFill(CTFill cTFill) {
        this._cellXf.setFillId((long) this._stylesSource.putFill(new XSSFCellFill(cTFill, this._stylesSource.getIndexedColors())));
        this._cellXf.setApplyFill(true);
    }

    private void addBorder(CTBorder cTBorder) {
        this._cellXf.setBorderId((long) this._stylesSource.putBorder(new XSSFCellBorder(cTBorder, this._theme, this._stylesSource.getIndexedColors())));
        this._cellXf.setApplyBorder(true);
    }

    public HorizontalAlignment getAlignment() {
        if (!this._cellXf.getApplyAlignment()) {
            return HorizontalAlignment.GENERAL;
        }
        CTCellAlignment alignment = this._cellXf.getAlignment();
        if (alignment == null || !alignment.isSetHorizontal()) {
            return HorizontalAlignment.GENERAL;
        }
        return HorizontalAlignment.forInt(alignment.getHorizontal().intValue() - 1);
    }

    public BorderStyle getBorderBottom() {
        if (!this._cellXf.getApplyBorder()) {
            return BorderStyle.NONE;
        }
        CTBorder cTBorder = this._stylesSource.getBorderAt(Math.toIntExact(this._cellXf.getBorderId())).getCTBorder();
        STBorderStyle.Enum style = cTBorder.isSetBottom() ? cTBorder.getBottom().getStyle() : null;
        if (style == null) {
            return BorderStyle.NONE;
        }
        return BorderStyle.valueOf((short) (style.intValue() - 1));
    }

    public BorderStyle getBorderLeft() {
        if (!this._cellXf.getApplyBorder()) {
            return BorderStyle.NONE;
        }
        CTBorder cTBorder = this._stylesSource.getBorderAt(Math.toIntExact(this._cellXf.getBorderId())).getCTBorder();
        STBorderStyle.Enum style = cTBorder.isSetLeft() ? cTBorder.getLeft().getStyle() : null;
        if (style == null) {
            return BorderStyle.NONE;
        }
        return BorderStyle.valueOf((short) (style.intValue() - 1));
    }

    public BorderStyle getBorderRight() {
        if (!this._cellXf.getApplyBorder()) {
            return BorderStyle.NONE;
        }
        CTBorder cTBorder = this._stylesSource.getBorderAt(Math.toIntExact(this._cellXf.getBorderId())).getCTBorder();
        STBorderStyle.Enum style = cTBorder.isSetRight() ? cTBorder.getRight().getStyle() : null;
        if (style == null) {
            return BorderStyle.NONE;
        }
        return BorderStyle.valueOf((short) (style.intValue() - 1));
    }

    public BorderStyle getBorderTop() {
        if (!this._cellXf.getApplyBorder()) {
            return BorderStyle.NONE;
        }
        CTBorder cTBorder = this._stylesSource.getBorderAt(Math.toIntExact(this._cellXf.getBorderId())).getCTBorder();
        STBorderStyle.Enum style = cTBorder.isSetTop() ? cTBorder.getTop().getStyle() : null;
        if (style == null) {
            return BorderStyle.NONE;
        }
        return BorderStyle.valueOf((short) (style.intValue() - 1));
    }

    public short getBottomBorderColor() {
        XSSFColor bottomBorderXSSFColor = getBottomBorderXSSFColor();
        return bottomBorderXSSFColor == null ? IndexedColors.BLACK.getIndex() : bottomBorderXSSFColor.getIndexed();
    }

    public XSSFColor getBottomBorderXSSFColor() {
        if (!this._cellXf.getApplyBorder()) {
            return null;
        }
        return this._stylesSource.getBorderAt(Math.toIntExact(this._cellXf.getBorderId())).getBorderColor(XSSFCellBorder.BorderSide.BOTTOM);
    }

    public short getDataFormat() {
        return (short) ((int) this._cellXf.getNumFmtId());
    }

    public String getDataFormatString() {
        return new XSSFDataFormat(this._stylesSource).getFormat((short) getDataFormat());
    }

    public short getFillBackgroundColor() {
        XSSFColor fillBackgroundXSSFColor = getFillBackgroundXSSFColor();
        return fillBackgroundXSSFColor == null ? IndexedColors.AUTOMATIC.getIndex() : fillBackgroundXSSFColor.getIndexed();
    }

    public XSSFColor getFillBackgroundColorColor() {
        return getFillBackgroundXSSFColor();
    }

    public XSSFColor getFillBackgroundXSSFColor() {
        ThemesTable themesTable;
        if (this._cellXf.isSetApplyFill() && !this._cellXf.getApplyFill()) {
            return null;
        }
        XSSFColor fillBackgroundColor = this._stylesSource.getFillAt((int) this._cellXf.getFillId()).getFillBackgroundColor();
        if (!(fillBackgroundColor == null || (themesTable = this._theme) == null)) {
            themesTable.inheritFromThemeAsRequired(fillBackgroundColor);
        }
        return fillBackgroundColor;
    }

    public short getFillForegroundColor() {
        XSSFColor fillForegroundXSSFColor = getFillForegroundXSSFColor();
        return fillForegroundXSSFColor == null ? IndexedColors.AUTOMATIC.getIndex() : fillForegroundXSSFColor.getIndexed();
    }

    public XSSFColor getFillForegroundColorColor() {
        return getFillForegroundXSSFColor();
    }

    public XSSFColor getFillForegroundXSSFColor() {
        ThemesTable themesTable;
        if (this._cellXf.isSetApplyFill() && !this._cellXf.getApplyFill()) {
            return null;
        }
        XSSFColor fillForegroundColor = this._stylesSource.getFillAt((int) this._cellXf.getFillId()).getFillForegroundColor();
        if (!(fillForegroundColor == null || (themesTable = this._theme) == null)) {
            themesTable.inheritFromThemeAsRequired(fillForegroundColor);
        }
        return fillForegroundColor;
    }

    public FillPatternType getFillPattern() {
        if (this._cellXf.isSetApplyFill() && !this._cellXf.getApplyFill()) {
            return FillPatternType.NO_FILL;
        }
        STPatternType.Enum patternType = this._stylesSource.getFillAt((int) this._cellXf.getFillId()).getPatternType();
        if (patternType == null) {
            return FillPatternType.NO_FILL;
        }
        return FillPatternType.forInt(patternType.intValue() - 1);
    }

    public XSSFFont getFont() {
        if (this._font == null) {
            this._font = this._stylesSource.getFontAt(getFontId());
        }
        return this._font;
    }

    public int getFontIndex() {
        return getFontId();
    }

    @Deprecated
    @Removal(version = "6.0.0")
    public int getFontIndexAsInt() {
        return getFontId();
    }

    public boolean getHidden() {
        return this._cellXf.isSetProtection() && this._cellXf.getProtection().isSetHidden() && this._cellXf.getProtection().getHidden();
    }

    public short getIndention() {
        long j;
        CTCellAlignment alignment = this._cellXf.getAlignment();
        if (alignment == null) {
            j = 0;
        } else {
            j = alignment.getIndent();
        }
        return (short) ((int) j);
    }

    public short getIndex() {
        return (short) this._cellXfId;
    }

    /* access modifiers changed from: protected */
    public int getUIndex() {
        return this._cellXfId;
    }

    public short getLeftBorderColor() {
        XSSFColor leftBorderXSSFColor = getLeftBorderXSSFColor();
        return leftBorderXSSFColor == null ? IndexedColors.BLACK.getIndex() : leftBorderXSSFColor.getIndexed();
    }

    public XSSFColor getLeftBorderXSSFColor() {
        if (!this._cellXf.getApplyBorder()) {
            return null;
        }
        return this._stylesSource.getBorderAt(Math.toIntExact(this._cellXf.getBorderId())).getBorderColor(XSSFCellBorder.BorderSide.LEFT);
    }

    public boolean getLocked() {
        return !this._cellXf.isSetProtection() || !this._cellXf.getProtection().isSetLocked() || this._cellXf.getProtection().getLocked();
    }

    public boolean getQuotePrefixed() {
        return this._cellXf.getQuotePrefix();
    }

    public short getRightBorderColor() {
        XSSFColor rightBorderXSSFColor = getRightBorderXSSFColor();
        return rightBorderXSSFColor == null ? IndexedColors.BLACK.getIndex() : rightBorderXSSFColor.getIndexed();
    }

    public XSSFColor getRightBorderXSSFColor() {
        if (!this._cellXf.getApplyBorder()) {
            return null;
        }
        return this._stylesSource.getBorderAt(Math.toIntExact(this._cellXf.getBorderId())).getBorderColor(XSSFCellBorder.BorderSide.RIGHT);
    }

    public short getRotation() {
        CTCellAlignment alignment = this._cellXf.getAlignment();
        if (alignment == null || alignment.getTextRotation() == null) {
            return 0;
        }
        return alignment.getTextRotation().shortValue();
    }

    public boolean getShrinkToFit() {
        CTCellAlignment alignment = this._cellXf.getAlignment();
        return alignment != null && alignment.getShrinkToFit();
    }

    public short getTopBorderColor() {
        XSSFColor topBorderXSSFColor = getTopBorderXSSFColor();
        return topBorderXSSFColor == null ? IndexedColors.BLACK.getIndex() : topBorderXSSFColor.getIndexed();
    }

    public XSSFColor getTopBorderXSSFColor() {
        if (!this._cellXf.getApplyBorder()) {
            return null;
        }
        return this._stylesSource.getBorderAt(Math.toIntExact(this._cellXf.getBorderId())).getBorderColor(XSSFCellBorder.BorderSide.TOP);
    }

    public VerticalAlignment getVerticalAlignment() {
        if (!this._cellXf.getApplyAlignment()) {
            return VerticalAlignment.BOTTOM;
        }
        CTCellAlignment alignment = this._cellXf.getAlignment();
        if (alignment == null || !alignment.isSetVertical()) {
            return VerticalAlignment.BOTTOM;
        }
        return VerticalAlignment.forInt(alignment.getVertical().intValue() - 1);
    }

    public boolean getWrapText() {
        CTCellAlignment alignment = this._cellXf.getAlignment();
        return alignment != null && alignment.getWrapText();
    }

    public void setAlignment(HorizontalAlignment horizontalAlignment) {
        this._cellXf.setApplyAlignment(true);
        getCellAlignment().setHorizontal(horizontalAlignment);
    }

    public void setBorderBottom(BorderStyle borderStyle) {
        CTBorder cTBorder = getCTBorder();
        CTBorderPr bottom = cTBorder.isSetBottom() ? cTBorder.getBottom() : cTBorder.addNewBottom();
        if (borderStyle == BorderStyle.NONE) {
            cTBorder.unsetBottom();
        } else {
            bottom.setStyle(STBorderStyle.Enum.forInt(borderStyle.getCode() + 1));
        }
        this._cellXf.setBorderId((long) this._stylesSource.putBorder(new XSSFCellBorder(cTBorder, this._theme, this._stylesSource.getIndexedColors())));
        this._cellXf.setApplyBorder(true);
    }

    public void setBorderLeft(BorderStyle borderStyle) {
        CTBorder cTBorder = getCTBorder();
        CTBorderPr left = cTBorder.isSetLeft() ? cTBorder.getLeft() : cTBorder.addNewLeft();
        if (borderStyle == BorderStyle.NONE) {
            cTBorder.unsetLeft();
        } else {
            left.setStyle(STBorderStyle.Enum.forInt(borderStyle.getCode() + 1));
        }
        this._cellXf.setBorderId((long) this._stylesSource.putBorder(new XSSFCellBorder(cTBorder, this._theme, this._stylesSource.getIndexedColors())));
        this._cellXf.setApplyBorder(true);
    }

    public void setBorderRight(BorderStyle borderStyle) {
        CTBorder cTBorder = getCTBorder();
        CTBorderPr right = cTBorder.isSetRight() ? cTBorder.getRight() : cTBorder.addNewRight();
        if (borderStyle == BorderStyle.NONE) {
            cTBorder.unsetRight();
        } else {
            right.setStyle(STBorderStyle.Enum.forInt(borderStyle.getCode() + 1));
        }
        this._cellXf.setBorderId((long) this._stylesSource.putBorder(new XSSFCellBorder(cTBorder, this._theme, this._stylesSource.getIndexedColors())));
        this._cellXf.setApplyBorder(true);
    }

    public void setBorderTop(BorderStyle borderStyle) {
        CTBorder cTBorder = getCTBorder();
        CTBorderPr top = cTBorder.isSetTop() ? cTBorder.getTop() : cTBorder.addNewTop();
        if (borderStyle == BorderStyle.NONE) {
            cTBorder.unsetTop();
        } else {
            top.setStyle(STBorderStyle.Enum.forInt(borderStyle.getCode() + 1));
        }
        this._cellXf.setBorderId((long) this._stylesSource.putBorder(new XSSFCellBorder(cTBorder, this._theme, this._stylesSource.getIndexedColors())));
        this._cellXf.setApplyBorder(true);
    }

    public void setBottomBorderColor(short s) {
        XSSFColor from = XSSFColor.from(CTColor.Factory.newInstance(), this._stylesSource.getIndexedColors());
        from.setIndexed(s);
        setBottomBorderColor(from);
    }

    public void setBottomBorderColor(XSSFColor xSSFColor) {
        CTBorder cTBorder = getCTBorder();
        if (xSSFColor != null || cTBorder.isSetBottom()) {
            CTBorderPr bottom = cTBorder.isSetBottom() ? cTBorder.getBottom() : cTBorder.addNewBottom();
            if (xSSFColor != null) {
                bottom.setColor(xSSFColor.getCTColor());
            } else {
                bottom.unsetColor();
            }
            this._cellXf.setBorderId((long) this._stylesSource.putBorder(new XSSFCellBorder(cTBorder, this._theme, this._stylesSource.getIndexedColors())));
            this._cellXf.setApplyBorder(true);
        }
    }

    public void setDataFormat(short s) {
        setDataFormat((int) s & 65535);
    }

    public void setDataFormat(int i) {
        this._cellXf.setApplyNumberFormat(true);
        this._cellXf.setNumFmtId((long) i);
    }

    public void setFillBackgroundColor(XSSFColor xSSFColor) {
        CTFill cTFill = getCTFill();
        CTPatternFill patternFill = cTFill.getPatternFill();
        if (xSSFColor != null) {
            if (patternFill == null) {
                patternFill = cTFill.addNewPatternFill();
            }
            patternFill.setBgColor(xSSFColor.getCTColor());
        } else if (patternFill != null && patternFill.isSetBgColor()) {
            patternFill.unsetBgColor();
        }
        addFill(cTFill);
    }

    public void setFillBackgroundColor(short s) {
        XSSFColor from = XSSFColor.from(CTColor.Factory.newInstance(), this._stylesSource.getIndexedColors());
        from.setIndexed(s);
        setFillBackgroundColor(from);
    }

    public void setFillForegroundColor(XSSFColor xSSFColor) {
        CTFill cTFill = getCTFill();
        CTPatternFill patternFill = cTFill.getPatternFill();
        if (xSSFColor != null) {
            if (patternFill == null) {
                patternFill = cTFill.addNewPatternFill();
            }
            patternFill.setFgColor(xSSFColor.getCTColor());
        } else if (patternFill != null && patternFill.isSetFgColor()) {
            patternFill.unsetFgColor();
        }
        addFill(cTFill);
    }

    public void setFillForegroundColor(short s) {
        XSSFColor from = XSSFColor.from(CTColor.Factory.newInstance(), this._stylesSource.getIndexedColors());
        from.setIndexed(s);
        setFillForegroundColor(from);
    }

    private CTFill getCTFill() {
        if (this._cellXf.isSetApplyFill() && !this._cellXf.getApplyFill()) {
            return CTFill.Factory.newInstance();
        }
        return (CTFill) this._stylesSource.getFillAt((int) this._cellXf.getFillId()).getCTFill().copy();
    }

    public void setReadingOrder(ReadingOrder readingOrder) {
        getCellAlignment().setReadingOrder(readingOrder);
    }

    public ReadingOrder getReadingOrder() {
        return getCellAlignment().getReadingOrder();
    }

    private CTBorder getCTBorder() {
        if (!this._cellXf.getApplyBorder()) {
            return CTBorder.Factory.newInstance();
        }
        return (CTBorder) this._stylesSource.getBorderAt(Math.toIntExact(this._cellXf.getBorderId())).getCTBorder().copy();
    }

    public void setFillPattern(FillPatternType fillPatternType) {
        CTFill cTFill = getCTFill();
        CTPatternFill patternFill = cTFill.isSetPatternFill() ? cTFill.getPatternFill() : cTFill.addNewPatternFill();
        if (fillPatternType != FillPatternType.NO_FILL || !patternFill.isSetPatternType()) {
            patternFill.setPatternType(STPatternType.Enum.forInt(fillPatternType.getCode() + 1));
        } else {
            patternFill.unsetPatternType();
        }
        addFill(cTFill);
    }

    public void setFont(Font font) {
        if (font != null) {
            this._cellXf.setFontId((long) font.getIndex());
            this._cellXf.setApplyFont(true);
            return;
        }
        this._cellXf.setApplyFont(false);
    }

    public void setHidden(boolean z) {
        if (!this._cellXf.isSetProtection()) {
            this._cellXf.addNewProtection();
        }
        this._cellXf.getProtection().setHidden(z);
    }

    public void setIndention(short s) {
        getCellAlignment().setIndent((long) s);
    }

    public void setLeftBorderColor(short s) {
        XSSFColor from = XSSFColor.from(CTColor.Factory.newInstance(), this._stylesSource.getIndexedColors());
        from.setIndexed(s);
        setLeftBorderColor(from);
    }

    public void setLeftBorderColor(XSSFColor xSSFColor) {
        CTBorder cTBorder = getCTBorder();
        if (xSSFColor != null || cTBorder.isSetLeft()) {
            CTBorderPr left = cTBorder.isSetLeft() ? cTBorder.getLeft() : cTBorder.addNewLeft();
            if (xSSFColor != null) {
                left.setColor(xSSFColor.getCTColor());
            } else {
                left.unsetColor();
            }
            this._cellXf.setBorderId((long) this._stylesSource.putBorder(new XSSFCellBorder(cTBorder, this._theme, this._stylesSource.getIndexedColors())));
            this._cellXf.setApplyBorder(true);
        }
    }

    public void setLocked(boolean z) {
        if (!this._cellXf.isSetProtection()) {
            this._cellXf.addNewProtection();
        }
        this._cellXf.getProtection().setLocked(z);
    }

    public void setQuotePrefixed(boolean z) {
        this._cellXf.setQuotePrefix(z);
    }

    public void setRightBorderColor(short s) {
        XSSFColor from = XSSFColor.from(CTColor.Factory.newInstance(), this._stylesSource.getIndexedColors());
        from.setIndexed(s);
        setRightBorderColor(from);
    }

    public void setRightBorderColor(XSSFColor xSSFColor) {
        CTBorder cTBorder = getCTBorder();
        if (xSSFColor != null || cTBorder.isSetRight()) {
            CTBorderPr right = cTBorder.isSetRight() ? cTBorder.getRight() : cTBorder.addNewRight();
            if (xSSFColor != null) {
                right.setColor(xSSFColor.getCTColor());
            } else {
                right.unsetColor();
            }
            this._cellXf.setBorderId((long) this._stylesSource.putBorder(new XSSFCellBorder(cTBorder, this._theme, this._stylesSource.getIndexedColors())));
            this._cellXf.setApplyBorder(true);
        }
    }

    public void setRotation(short s) {
        getCellAlignment().setTextRotation((long) s);
    }

    public void setTopBorderColor(short s) {
        XSSFColor from = XSSFColor.from(CTColor.Factory.newInstance(), this._stylesSource.getIndexedColors());
        from.setIndexed(s);
        setTopBorderColor(from);
    }

    public void setTopBorderColor(XSSFColor xSSFColor) {
        CTBorder cTBorder = getCTBorder();
        if (xSSFColor != null || cTBorder.isSetTop()) {
            CTBorderPr top = cTBorder.isSetTop() ? cTBorder.getTop() : cTBorder.addNewTop();
            if (xSSFColor != null) {
                top.setColor(xSSFColor.getCTColor());
            } else {
                top.unsetColor();
            }
            this._cellXf.setBorderId((long) this._stylesSource.putBorder(new XSSFCellBorder(cTBorder, this._theme, this._stylesSource.getIndexedColors())));
            this._cellXf.setApplyBorder(true);
        }
    }

    public void setVerticalAlignment(VerticalAlignment verticalAlignment) {
        this._cellXf.setApplyAlignment(true);
        getCellAlignment().setVertical(verticalAlignment);
    }

    public void setWrapText(boolean z) {
        getCellAlignment().setWrapText(z);
    }

    /* renamed from: org.apache.poi.xssf.usermodel.XSSFCellStyle$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$org$apache$poi$xssf$usermodel$extensions$XSSFCellBorder$BorderSide;

        /* JADX WARNING: Can't wrap try/catch for region: R(8:0|1|2|3|4|5|6|(3:7|8|10)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        static {
            /*
                org.apache.poi.xssf.usermodel.extensions.XSSFCellBorder$BorderSide[] r0 = org.apache.poi.xssf.usermodel.extensions.XSSFCellBorder.BorderSide.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$org$apache$poi$xssf$usermodel$extensions$XSSFCellBorder$BorderSide = r0
                org.apache.poi.xssf.usermodel.extensions.XSSFCellBorder$BorderSide r1 = org.apache.poi.xssf.usermodel.extensions.XSSFCellBorder.BorderSide.BOTTOM     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$org$apache$poi$xssf$usermodel$extensions$XSSFCellBorder$BorderSide     // Catch:{ NoSuchFieldError -> 0x001d }
                org.apache.poi.xssf.usermodel.extensions.XSSFCellBorder$BorderSide r1 = org.apache.poi.xssf.usermodel.extensions.XSSFCellBorder.BorderSide.RIGHT     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$org$apache$poi$xssf$usermodel$extensions$XSSFCellBorder$BorderSide     // Catch:{ NoSuchFieldError -> 0x0028 }
                org.apache.poi.xssf.usermodel.extensions.XSSFCellBorder$BorderSide r1 = org.apache.poi.xssf.usermodel.extensions.XSSFCellBorder.BorderSide.TOP     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = $SwitchMap$org$apache$poi$xssf$usermodel$extensions$XSSFCellBorder$BorderSide     // Catch:{ NoSuchFieldError -> 0x0033 }
                org.apache.poi.xssf.usermodel.extensions.XSSFCellBorder$BorderSide r1 = org.apache.poi.xssf.usermodel.extensions.XSSFCellBorder.BorderSide.LEFT     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.xssf.usermodel.XSSFCellStyle.AnonymousClass1.<clinit>():void");
        }
    }

    public XSSFColor getBorderColor(XSSFCellBorder.BorderSide borderSide) {
        int i = AnonymousClass1.$SwitchMap$org$apache$poi$xssf$usermodel$extensions$XSSFCellBorder$BorderSide[borderSide.ordinal()];
        if (i == 1) {
            return getBottomBorderXSSFColor();
        }
        if (i == 2) {
            return getRightBorderXSSFColor();
        }
        if (i == 3) {
            return getTopBorderXSSFColor();
        }
        if (i == 4) {
            return getLeftBorderXSSFColor();
        }
        throw new IllegalArgumentException("Unknown border: " + borderSide);
    }

    public void setBorderColor(XSSFCellBorder.BorderSide borderSide, XSSFColor xSSFColor) {
        int i = AnonymousClass1.$SwitchMap$org$apache$poi$xssf$usermodel$extensions$XSSFCellBorder$BorderSide[borderSide.ordinal()];
        if (i == 1) {
            setBottomBorderColor(xSSFColor);
        } else if (i == 2) {
            setRightBorderColor(xSSFColor);
        } else if (i == 3) {
            setTopBorderColor(xSSFColor);
        } else if (i == 4) {
            setLeftBorderColor(xSSFColor);
        }
    }

    public void setShrinkToFit(boolean z) {
        getCellAlignment().setShrinkToFit(z);
    }

    private int getFontId() {
        long fontId;
        if (this._cellXf.isSetFontId()) {
            fontId = this._cellXf.getFontId();
        } else {
            fontId = this._cellStyleXf.getFontId();
        }
        return (int) fontId;
    }

    /* access modifiers changed from: protected */
    public XSSFCellAlignment getCellAlignment() {
        if (this._cellAlignment == null) {
            this._cellAlignment = new XSSFCellAlignment(getCTCellAlignment());
        }
        return this._cellAlignment;
    }

    private CTCellAlignment getCTCellAlignment() {
        if (this._cellXf.getAlignment() == null) {
            this._cellXf.setAlignment(CTCellAlignment.Factory.newInstance());
        }
        return this._cellXf.getAlignment();
    }

    public int hashCode() {
        return this._cellXf.toString().hashCode();
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof XSSFCellStyle)) {
            return false;
        }
        return this._cellXf.toString().equals(((XSSFCellStyle) obj).getCoreXf().toString());
    }

    public XSSFCellStyle copy() {
        int _getStyleXfsSize = this._stylesSource._getStyleXfsSize();
        return new XSSFCellStyle(this._stylesSource.putCellXf((CTXf) this._cellXf.copy()) - 1, _getStyleXfsSize - 1, this._stylesSource, this._theme);
    }
}
