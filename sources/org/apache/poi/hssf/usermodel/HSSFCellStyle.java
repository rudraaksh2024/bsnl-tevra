package org.apache.poi.hssf.usermodel;

import java.util.List;
import java.util.Objects;
import kotlin.jvm.internal.ShortCompanionObject;
import org.apache.poi.common.Duplicatable;
import org.apache.poi.hssf.model.InternalWorkbook;
import org.apache.poi.hssf.record.ExtendedFormatRecord;
import org.apache.poi.hssf.record.FontRecord;
import org.apache.poi.hssf.record.FormatRecord;
import org.apache.poi.hssf.record.StyleRecord;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.util.Removal;

public final class HSSFCellStyle implements CellStyle, Duplicatable {
    private static final ThreadLocal<String> getDataFormatStringCache = new ThreadLocal<>();
    private static final ThreadLocal<Short> lastDateFormat = ThreadLocal.withInitial(new HSSFCellStyle$$ExternalSyntheticLambda0());
    private static final ThreadLocal<List<FormatRecord>> lastFormats = new ThreadLocal<>();
    private final ExtendedFormatRecord _format;
    private final short _index;
    private final InternalWorkbook _workbook;

    protected HSSFCellStyle(short s, ExtendedFormatRecord extendedFormatRecord, HSSFWorkbook hSSFWorkbook) {
        this(s, extendedFormatRecord, hSSFWorkbook.getWorkbook());
    }

    protected HSSFCellStyle(short s, ExtendedFormatRecord extendedFormatRecord, InternalWorkbook internalWorkbook) {
        this._workbook = internalWorkbook;
        this._index = s;
        this._format = extendedFormatRecord;
    }

    protected HSSFCellStyle(HSSFCellStyle hSSFCellStyle) {
        this._workbook = hSSFCellStyle._workbook;
        this._index = hSSFCellStyle._index;
        this._format = hSSFCellStyle._format;
    }

    public short getIndex() {
        return this._index;
    }

    public HSSFCellStyle getParentStyle() {
        short parentIndex = this._format.getParentIndex();
        if (parentIndex == 0 || parentIndex == 4095) {
            return null;
        }
        return new HSSFCellStyle(parentIndex, this._workbook.getExFormatAt(parentIndex), this._workbook);
    }

    public void setDataFormat(short s) {
        this._format.setFormatIndex(s);
    }

    public short getDataFormat() {
        return this._format.getFormatIndex();
    }

    public String getDataFormatString() {
        ThreadLocal<String> threadLocal = getDataFormatStringCache;
        if (threadLocal.get() != null && lastDateFormat.get().shortValue() == getDataFormat() && this._workbook.getFormats().equals(lastFormats.get())) {
            return threadLocal.get();
        }
        lastFormats.set(this._workbook.getFormats());
        lastDateFormat.set(Short.valueOf(getDataFormat()));
        threadLocal.set(getDataFormatString(this._workbook));
        return threadLocal.get();
    }

    public String getDataFormatString(Workbook workbook) {
        HSSFDataFormat hSSFDataFormat = new HSSFDataFormat(((HSSFWorkbook) workbook).getWorkbook());
        if (getDataFormat() == -1) {
            return "General";
        }
        return hSSFDataFormat.getFormat(getDataFormat());
    }

    public String getDataFormatString(InternalWorkbook internalWorkbook) {
        return new HSSFDataFormat(internalWorkbook).getFormat(getDataFormat());
    }

    public void setFont(Font font) {
        setFont((HSSFFont) font);
    }

    public void setFont(HSSFFont hSSFFont) {
        this._format.setIndentNotParentFont(true);
        this._format.setFontIndex((short) hSSFFont.getIndex());
    }

    public int getFontIndex() {
        return this._format.getFontIndex();
    }

    @Deprecated
    @Removal(version = "6.0.0")
    public int getFontIndexAsInt() {
        return this._format.getFontIndex();
    }

    public HSSFFont getFont(Workbook workbook) {
        return ((HSSFWorkbook) workbook).getFontAt(getFontIndex());
    }

    public void setHidden(boolean z) {
        this._format.setIndentNotParentCellOptions(true);
        this._format.setHidden(z);
    }

    public boolean getHidden() {
        return this._format.isHidden();
    }

    public void setLocked(boolean z) {
        this._format.setIndentNotParentCellOptions(true);
        this._format.setLocked(z);
    }

    public boolean getLocked() {
        return this._format.isLocked();
    }

    public void setQuotePrefixed(boolean z) {
        this._format.set123Prefix(z);
    }

    public boolean getQuotePrefixed() {
        return this._format.get123Prefix();
    }

    public void setAlignment(HorizontalAlignment horizontalAlignment) {
        this._format.setIndentNotParentAlignment(true);
        this._format.setAlignment(horizontalAlignment.getCode());
    }

    public HorizontalAlignment getAlignment() {
        return HorizontalAlignment.forInt(this._format.getAlignment());
    }

    public void setWrapText(boolean z) {
        this._format.setIndentNotParentAlignment(true);
        this._format.setWrapText(z);
    }

    public boolean getWrapText() {
        return this._format.getWrapText();
    }

    public void setVerticalAlignment(VerticalAlignment verticalAlignment) {
        this._format.setVerticalAlignment(verticalAlignment.getCode());
    }

    public VerticalAlignment getVerticalAlignment() {
        return VerticalAlignment.forInt(this._format.getVerticalAlignment());
    }

    public void setRotation(short s) {
        if (s != 255) {
            if (s < 0 && s >= -90) {
                s = (short) (90 - s);
            } else if ((s <= 90 || s > 180) && (s < -90 || s > 90)) {
                throw new IllegalArgumentException("The rotation must be between -90 and 90 degrees, or 0xff");
            }
        }
        this._format.setRotation(s);
    }

    public short getRotation() {
        short rotation = this._format.getRotation();
        return (rotation != 255 && rotation > 90) ? (short) (90 - rotation) : rotation;
    }

    public void setIndention(short s) {
        this._format.setIndent(s);
    }

    public short getIndention() {
        return this._format.getIndent();
    }

    public void setBorderLeft(BorderStyle borderStyle) {
        this._format.setIndentNotParentBorder(true);
        this._format.setBorderLeft(borderStyle.getCode());
    }

    public BorderStyle getBorderLeft() {
        return BorderStyle.valueOf(this._format.getBorderLeft());
    }

    public void setBorderRight(BorderStyle borderStyle) {
        this._format.setIndentNotParentBorder(true);
        this._format.setBorderRight(borderStyle.getCode());
    }

    public BorderStyle getBorderRight() {
        return BorderStyle.valueOf(this._format.getBorderRight());
    }

    public void setBorderTop(BorderStyle borderStyle) {
        this._format.setIndentNotParentBorder(true);
        this._format.setBorderTop(borderStyle.getCode());
    }

    public BorderStyle getBorderTop() {
        return BorderStyle.valueOf(this._format.getBorderTop());
    }

    public void setBorderBottom(BorderStyle borderStyle) {
        this._format.setIndentNotParentBorder(true);
        this._format.setBorderBottom(borderStyle.getCode());
    }

    public BorderStyle getBorderBottom() {
        return BorderStyle.valueOf(this._format.getBorderBottom());
    }

    public void setLeftBorderColor(short s) {
        this._format.setLeftBorderPaletteIdx(s);
    }

    public short getLeftBorderColor() {
        return this._format.getLeftBorderPaletteIdx();
    }

    public void setRightBorderColor(short s) {
        this._format.setRightBorderPaletteIdx(s);
    }

    public short getRightBorderColor() {
        return this._format.getRightBorderPaletteIdx();
    }

    public void setTopBorderColor(short s) {
        this._format.setTopBorderPaletteIdx(s);
    }

    public short getTopBorderColor() {
        return this._format.getTopBorderPaletteIdx();
    }

    public void setBottomBorderColor(short s) {
        this._format.setBottomBorderPaletteIdx(s);
    }

    public short getBottomBorderColor() {
        return this._format.getBottomBorderPaletteIdx();
    }

    public void setFillPattern(FillPatternType fillPatternType) {
        this._format.setAdtlFillPattern(fillPatternType.getCode());
    }

    public FillPatternType getFillPattern() {
        return FillPatternType.forInt(this._format.getAdtlFillPattern());
    }

    private void checkDefaultBackgroundFills() {
        short index = HSSFColor.HSSFColorPredefined.AUTOMATIC.getIndex();
        if (this._format.getFillForeground() == index) {
            int i = index + 1;
            if (this._format.getFillBackground() != i) {
                setFillBackgroundColor((short) i);
            }
        } else if (this._format.getFillBackground() == index + 1 && this._format.getFillForeground() != index) {
            setFillBackgroundColor(index);
        }
    }

    public void setFillBackgroundColor(short s) {
        this._format.setFillBackground(s);
        checkDefaultBackgroundFills();
    }

    public short getFillBackgroundColor() {
        short index = HSSFColor.HSSFColorPredefined.AUTOMATIC.getIndex();
        short fillBackground = this._format.getFillBackground();
        return fillBackground == index + 1 ? index : fillBackground;
    }

    public HSSFColor getFillBackgroundColorColor() {
        return new HSSFPalette(this._workbook.getCustomPalette()).getColor(getFillBackgroundColor());
    }

    public void setFillForegroundColor(short s) {
        this._format.setFillForeground(s);
        checkDefaultBackgroundFills();
    }

    public short getFillForegroundColor() {
        return this._format.getFillForeground();
    }

    public HSSFColor getFillForegroundColorColor() {
        return new HSSFPalette(this._workbook.getCustomPalette()).getColor(getFillForegroundColor());
    }

    public String getUserStyleName() {
        StyleRecord styleRecord = this._workbook.getStyleRecord(this._index);
        if (styleRecord != null && !styleRecord.isBuiltin()) {
            return styleRecord.getName();
        }
        return null;
    }

    public void setUserStyleName(String str) {
        StyleRecord styleRecord = this._workbook.getStyleRecord(this._index);
        if (styleRecord == null) {
            styleRecord = this._workbook.createStyleRecord(this._index);
        }
        if (!styleRecord.isBuiltin() || this._index > 20) {
            styleRecord.setName(str);
            return;
        }
        throw new IllegalArgumentException("Unable to set user specified style names for built in styles!");
    }

    public void setShrinkToFit(boolean z) {
        this._format.setShrinkToFit(z);
    }

    public boolean getShrinkToFit() {
        return this._format.getShrinkToFit();
    }

    public short getReadingOrder() {
        return this._format.getReadingOrder();
    }

    public void setReadingOrder(short s) {
        this._format.setReadingOrder(s);
    }

    public void verifyBelongsToWorkbook(HSSFWorkbook hSSFWorkbook) {
        if (hSSFWorkbook.getWorkbook() != this._workbook) {
            throw new IllegalArgumentException("This Style does not belong to the supplied Workbook. Are you trying to assign a style from one workbook to the cell of a differnt workbook?");
        }
    }

    public void cloneStyleFrom(CellStyle cellStyle) {
        if (cellStyle instanceof HSSFCellStyle) {
            cloneStyleFrom((HSSFCellStyle) cellStyle);
            return;
        }
        throw new IllegalArgumentException("Can only clone from one HSSFCellStyle to another, not between HSSFCellStyle and XSSFCellStyle");
    }

    public void cloneStyleFrom(HSSFCellStyle hSSFCellStyle) {
        this._format.cloneStyleFrom(hSSFCellStyle._format);
        if (this._workbook != hSSFCellStyle._workbook) {
            lastDateFormat.set(Short.valueOf(ShortCompanionObject.MIN_VALUE));
            lastFormats.remove();
            getDataFormatStringCache.remove();
            setDataFormat((short) this._workbook.createFormat(hSSFCellStyle.getDataFormatString()));
            FontRecord createNewFont = this._workbook.createNewFont();
            createNewFont.cloneStyleFrom(hSSFCellStyle._workbook.getFontRecordAt(hSSFCellStyle.getFontIndex()));
            setFont(new HSSFFont((short) this._workbook.getFontIndex(createNewFont), createNewFont));
        }
    }

    public int hashCode() {
        return Objects.hash(new Object[]{this._format, Short.valueOf(this._index)});
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof HSSFCellStyle)) {
            return false;
        }
        HSSFCellStyle hSSFCellStyle = (HSSFCellStyle) obj;
        ExtendedFormatRecord extendedFormatRecord = this._format;
        if (extendedFormatRecord == null) {
            if (hSSFCellStyle._format != null) {
                return false;
            }
        } else if (!extendedFormatRecord.equals(hSSFCellStyle._format)) {
            return false;
        }
        return this._index == hSSFCellStyle._index;
    }

    public HSSFCellStyle copy() {
        return new HSSFCellStyle(this);
    }
}
