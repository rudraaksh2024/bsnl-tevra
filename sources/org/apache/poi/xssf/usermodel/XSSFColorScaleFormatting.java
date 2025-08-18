package org.apache.poi.xssf.usermodel;

import org.apache.poi.ss.usermodel.Color;
import org.apache.poi.ss.usermodel.ColorScaleFormatting;
import org.apache.poi.ss.usermodel.ConditionalFormattingThreshold;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCfvo;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTColor;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTColorScale;

public class XSSFColorScaleFormatting implements ColorScaleFormatting {
    private IndexedColorMap _indexedColorMap;
    private CTColorScale _scale;

    XSSFColorScaleFormatting(CTColorScale cTColorScale, IndexedColorMap indexedColorMap) {
        this._scale = cTColorScale;
        this._indexedColorMap = indexedColorMap;
    }

    public int getNumControlPoints() {
        return this._scale.sizeOfCfvoArray();
    }

    public void setNumControlPoints(int i) {
        while (i < this._scale.sizeOfCfvoArray()) {
            CTColorScale cTColorScale = this._scale;
            cTColorScale.removeCfvo(cTColorScale.sizeOfCfvoArray() - 1);
            CTColorScale cTColorScale2 = this._scale;
            cTColorScale2.removeColor(cTColorScale2.sizeOfColorArray() - 1);
        }
        while (i > this._scale.sizeOfCfvoArray()) {
            this._scale.addNewCfvo();
            this._scale.addNewColor();
        }
    }

    public XSSFColor[] getColors() {
        CTColor[] colorArray = this._scale.getColorArray();
        XSSFColor[] xSSFColorArr = new XSSFColor[colorArray.length];
        for (int i = 0; i < colorArray.length; i++) {
            xSSFColorArr[i] = XSSFColor.from(colorArray[i], this._indexedColorMap);
        }
        return xSSFColorArr;
    }

    public void setColors(Color[] colorArr) {
        CTColor[] cTColorArr = new CTColor[colorArr.length];
        for (int i = 0; i < colorArr.length; i++) {
            cTColorArr[i] = colorArr[i].getCTColor();
        }
        this._scale.setColorArray(cTColorArr);
    }

    public XSSFConditionalFormattingThreshold[] getThresholds() {
        CTCfvo[] cfvoArray = this._scale.getCfvoArray();
        XSSFConditionalFormattingThreshold[] xSSFConditionalFormattingThresholdArr = new XSSFConditionalFormattingThreshold[cfvoArray.length];
        for (int i = 0; i < cfvoArray.length; i++) {
            xSSFConditionalFormattingThresholdArr[i] = new XSSFConditionalFormattingThreshold(cfvoArray[i]);
        }
        return xSSFConditionalFormattingThresholdArr;
    }

    public void setThresholds(ConditionalFormattingThreshold[] conditionalFormattingThresholdArr) {
        CTCfvo[] cTCfvoArr = new CTCfvo[conditionalFormattingThresholdArr.length];
        for (int i = 0; i < conditionalFormattingThresholdArr.length; i++) {
            cTCfvoArr[i] = conditionalFormattingThresholdArr[i].getCTCfvo();
        }
        this._scale.setCfvoArray(cTCfvoArr);
    }

    public XSSFColor createColor() {
        return XSSFColor.from(this._scale.addNewColor(), this._indexedColorMap);
    }

    public XSSFConditionalFormattingThreshold createThreshold() {
        return new XSSFConditionalFormattingThreshold(this._scale.addNewCfvo());
    }
}
