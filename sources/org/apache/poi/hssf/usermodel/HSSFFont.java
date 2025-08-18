package org.apache.poi.hssf.usermodel;

import androidx.core.view.InputDeviceCompat;
import java.util.Objects;
import org.apache.commons.math3.geometry.VectorFormat;
import org.apache.poi.hssf.record.FontRecord;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.util.Removal;

public final class HSSFFont implements Font {
    static final short BOLDWEIGHT_BOLD = 700;
    static final short BOLDWEIGHT_NORMAL = 400;
    public static final String FONT_ARIAL = "Arial";
    private final FontRecord font;
    private final int index;

    protected HSSFFont(int i, FontRecord fontRecord) {
        this.font = fontRecord;
        this.index = i;
    }

    public void setFontName(String str) {
        this.font.setFontName(str);
    }

    public String getFontName() {
        return this.font.getFontName();
    }

    public int getIndex() {
        return this.index;
    }

    @Deprecated
    @Removal(version = "6.0.0")
    public int getIndexAsInt() {
        return this.index;
    }

    public void setFontHeight(short s) {
        this.font.setFontHeight(s);
    }

    public void setFontHeightInPoints(short s) {
        this.font.setFontHeight((short) (s * 20));
    }

    public short getFontHeight() {
        return this.font.getFontHeight();
    }

    public short getFontHeightInPoints() {
        return (short) (this.font.getFontHeight() / 20);
    }

    public void setItalic(boolean z) {
        this.font.setItalic(z);
    }

    public boolean getItalic() {
        return this.font.isItalic();
    }

    public void setStrikeout(boolean z) {
        this.font.setStrikeout(z);
    }

    public boolean getStrikeout() {
        return this.font.isStruckout();
    }

    public void setColor(short s) {
        this.font.setColorPaletteIndex(s);
    }

    public short getColor() {
        return this.font.getColorPaletteIndex();
    }

    public HSSFColor getHSSFColor(HSSFWorkbook hSSFWorkbook) {
        return hSSFWorkbook.getCustomPalette().getColor(getColor());
    }

    public void setBold(boolean z) {
        if (z) {
            this.font.setBoldWeight(BOLDWEIGHT_BOLD);
        } else {
            this.font.setBoldWeight(BOLDWEIGHT_NORMAL);
        }
    }

    public boolean getBold() {
        return this.font.getBoldWeight() == 700;
    }

    public void setTypeOffset(short s) {
        this.font.setSuperSubScript(s);
    }

    public short getTypeOffset() {
        return this.font.getSuperSubScript();
    }

    public void setUnderline(byte b) {
        this.font.setUnderline(b);
    }

    public byte getUnderline() {
        return this.font.getUnderline();
    }

    public int getCharSet() {
        byte charset = this.font.getCharset();
        return charset >= 0 ? charset : charset + 256;
    }

    public void setCharSet(int i) {
        byte b = (byte) i;
        if (i > 127) {
            b = (byte) (i + InputDeviceCompat.SOURCE_ANY);
        }
        setCharSet(b);
    }

    public void setCharSet(byte b) {
        this.font.setCharset(b);
    }

    public String toString() {
        return "org.apache.poi.hssf.usermodel.HSSFFont{" + this.font + VectorFormat.DEFAULT_SUFFIX;
    }

    public int hashCode() {
        return Objects.hash(new Object[]{this.font, Integer.valueOf(this.index)});
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof HSSFFont)) {
            return false;
        }
        HSSFFont hSSFFont = (HSSFFont) obj;
        FontRecord fontRecord = this.font;
        if (fontRecord == null) {
            if (hSSFFont.font != null) {
                return false;
            }
        } else if (!fontRecord.equals(hSSFFont.font)) {
            return false;
        }
        if (this.index == hSSFFont.index) {
            return true;
        }
        return false;
    }
}
