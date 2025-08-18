package org.apache.poi.xssf.usermodel;

import org.apache.poi.ss.usermodel.Color;
import org.apache.poi.ss.usermodel.DataBarFormatting;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDataBar;

public class XSSFDataBarFormatting implements DataBarFormatting {
    IndexedColorMap _colorMap;
    CTDataBar _databar;

    public boolean isLeftToRight() {
        return true;
    }

    public void setLeftToRight(boolean z) {
    }

    XSSFDataBarFormatting(CTDataBar cTDataBar, IndexedColorMap indexedColorMap) {
        this._databar = cTDataBar;
        this._colorMap = indexedColorMap;
    }

    public boolean isIconOnly() {
        if (this._databar.isSetShowValue()) {
            return !this._databar.getShowValue();
        }
        return false;
    }

    public void setIconOnly(boolean z) {
        this._databar.setShowValue(!z);
    }

    public int getWidthMin() {
        return (int) this._databar.getMinLength();
    }

    public void setWidthMin(int i) {
        this._databar.setMinLength((long) i);
    }

    public int getWidthMax() {
        return (int) this._databar.getMaxLength();
    }

    public void setWidthMax(int i) {
        this._databar.setMaxLength((long) i);
    }

    public XSSFColor getColor() {
        return XSSFColor.from(this._databar.getColor(), this._colorMap);
    }

    public void setColor(Color color) {
        this._databar.setColor(((XSSFColor) color).getCTColor());
    }

    public XSSFConditionalFormattingThreshold getMinThreshold() {
        return new XSSFConditionalFormattingThreshold(this._databar.getCfvoArray(0));
    }

    public XSSFConditionalFormattingThreshold getMaxThreshold() {
        return new XSSFConditionalFormattingThreshold(this._databar.getCfvoArray(1));
    }

    public XSSFConditionalFormattingThreshold createThreshold() {
        return new XSSFConditionalFormattingThreshold(this._databar.addNewCfvo());
    }
}
