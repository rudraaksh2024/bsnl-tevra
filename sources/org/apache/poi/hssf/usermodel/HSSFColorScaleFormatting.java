package org.apache.poi.hssf.usermodel;

import org.apache.poi.hssf.record.CFRule12Record;
import org.apache.poi.hssf.record.cf.ColorGradientFormatting;
import org.apache.poi.hssf.record.cf.ColorGradientThreshold;
import org.apache.poi.hssf.record.common.ExtendedColor;
import org.apache.poi.ss.usermodel.Color;
import org.apache.poi.ss.usermodel.ColorScaleFormatting;
import org.apache.poi.ss.usermodel.ConditionalFormattingThreshold;

public final class HSSFColorScaleFormatting implements ColorScaleFormatting {
    private final CFRule12Record cfRule12Record;
    private final ColorGradientFormatting colorFormatting;
    private final HSSFSheet sheet;

    protected HSSFColorScaleFormatting(CFRule12Record cFRule12Record, HSSFSheet hSSFSheet) {
        this.sheet = hSSFSheet;
        this.cfRule12Record = cFRule12Record;
        this.colorFormatting = cFRule12Record.getColorGradientFormatting();
    }

    public int getNumControlPoints() {
        return this.colorFormatting.getNumControlPoints();
    }

    public void setNumControlPoints(int i) {
        this.colorFormatting.setNumControlPoints(i);
    }

    public HSSFExtendedColor[] getColors() {
        ExtendedColor[] colors = this.colorFormatting.getColors();
        HSSFExtendedColor[] hSSFExtendedColorArr = new HSSFExtendedColor[colors.length];
        for (int i = 0; i < colors.length; i++) {
            hSSFExtendedColorArr[i] = new HSSFExtendedColor(colors[i]);
        }
        return hSSFExtendedColorArr;
    }

    public void setColors(Color[] colorArr) {
        ExtendedColor[] extendedColorArr = new ExtendedColor[colorArr.length];
        for (int i = 0; i < colorArr.length; i++) {
            extendedColorArr[i] = colorArr[i].getExtendedColor();
        }
        this.colorFormatting.setColors(extendedColorArr);
    }

    public HSSFConditionalFormattingThreshold[] getThresholds() {
        ColorGradientThreshold[] thresholds = this.colorFormatting.getThresholds();
        HSSFConditionalFormattingThreshold[] hSSFConditionalFormattingThresholdArr = new HSSFConditionalFormattingThreshold[thresholds.length];
        for (int i = 0; i < thresholds.length; i++) {
            hSSFConditionalFormattingThresholdArr[i] = new HSSFConditionalFormattingThreshold(thresholds[i], this.sheet);
        }
        return hSSFConditionalFormattingThresholdArr;
    }

    public void setThresholds(ConditionalFormattingThreshold[] conditionalFormattingThresholdArr) {
        int length = conditionalFormattingThresholdArr.length;
        ColorGradientThreshold[] colorGradientThresholdArr = new ColorGradientThreshold[length];
        for (int i = 0; i < length; i++) {
            colorGradientThresholdArr[i] = (ColorGradientThreshold) conditionalFormattingThresholdArr[i].getThreshold();
        }
        this.colorFormatting.setThresholds(colorGradientThresholdArr);
    }

    public HSSFConditionalFormattingThreshold createThreshold() {
        return new HSSFConditionalFormattingThreshold(new ColorGradientThreshold(), this.sheet);
    }
}
