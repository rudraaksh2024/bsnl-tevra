package org.apache.poi.xssf.usermodel;

import org.apache.poi.ss.usermodel.Color;
import org.apache.poi.ss.usermodel.PatternFormatting;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTColor;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFill;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPatternFill;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.STPatternType;

public class XSSFPatternFormatting implements PatternFormatting {
    IndexedColorMap _colorMap;
    CTFill _fill;

    XSSFPatternFormatting(CTFill cTFill, IndexedColorMap indexedColorMap) {
        this._fill = cTFill;
        this._colorMap = indexedColorMap;
    }

    public XSSFColor getFillBackgroundColorColor() {
        if (!this._fill.isSetPatternFill()) {
            return null;
        }
        return XSSFColor.from(this._fill.getPatternFill().getBgColor(), this._colorMap);
    }

    public XSSFColor getFillForegroundColorColor() {
        if (!this._fill.isSetPatternFill() || !this._fill.getPatternFill().isSetFgColor()) {
            return null;
        }
        return XSSFColor.from(this._fill.getPatternFill().getFgColor(), this._colorMap);
    }

    public short getFillPattern() {
        if (!this._fill.isSetPatternFill() || !this._fill.getPatternFill().isSetPatternType()) {
            return 0;
        }
        return (short) (this._fill.getPatternFill().getPatternType().intValue() - 1);
    }

    public short getFillBackgroundColor() {
        XSSFColor fillBackgroundColorColor = getFillBackgroundColorColor();
        if (fillBackgroundColorColor == null) {
            return 0;
        }
        return fillBackgroundColorColor.getIndexed();
    }

    public short getFillForegroundColor() {
        XSSFColor fillForegroundColorColor = getFillForegroundColorColor();
        if (fillForegroundColorColor == null) {
            return 0;
        }
        return fillForegroundColorColor.getIndexed();
    }

    public void setFillBackgroundColor(Color color) {
        XSSFColor xSSFColor = XSSFColor.toXSSFColor(color);
        if (xSSFColor == null) {
            CTColor cTColor = null;
            setFillBackgroundColor((CTColor) null);
            return;
        }
        setFillBackgroundColor(xSSFColor.getCTColor());
    }

    public void setFillBackgroundColor(short s) {
        CTColor newInstance = CTColor.Factory.newInstance();
        newInstance.setIndexed((long) s);
        setFillBackgroundColor(newInstance);
    }

    private void setFillBackgroundColor(CTColor cTColor) {
        boolean isSetPatternFill = this._fill.isSetPatternFill();
        CTFill cTFill = this._fill;
        CTPatternFill patternFill = isSetPatternFill ? cTFill.getPatternFill() : cTFill.addNewPatternFill();
        if (cTColor == null) {
            patternFill.unsetBgColor();
        } else {
            patternFill.setBgColor(cTColor);
        }
    }

    public void setFillForegroundColor(Color color) {
        XSSFColor xSSFColor = XSSFColor.toXSSFColor(color);
        if (xSSFColor == null) {
            CTColor cTColor = null;
            setFillForegroundColor((CTColor) null);
            return;
        }
        setFillForegroundColor(xSSFColor.getCTColor());
    }

    public void setFillForegroundColor(short s) {
        CTColor newInstance = CTColor.Factory.newInstance();
        newInstance.setIndexed((long) s);
        setFillForegroundColor(newInstance);
    }

    private void setFillForegroundColor(CTColor cTColor) {
        boolean isSetPatternFill = this._fill.isSetPatternFill();
        CTFill cTFill = this._fill;
        CTPatternFill patternFill = isSetPatternFill ? cTFill.getPatternFill() : cTFill.addNewPatternFill();
        if (cTColor == null) {
            patternFill.unsetFgColor();
        } else {
            patternFill.setFgColor(cTColor);
        }
    }

    public void setFillPattern(short s) {
        boolean isSetPatternFill = this._fill.isSetPatternFill();
        CTFill cTFill = this._fill;
        CTPatternFill patternFill = isSetPatternFill ? cTFill.getPatternFill() : cTFill.addNewPatternFill();
        if (s == 0) {
            patternFill.unsetPatternType();
        } else {
            patternFill.setPatternType(STPatternType.Enum.forInt(s + 1));
        }
    }
}
