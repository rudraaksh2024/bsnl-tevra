package org.apache.poi.hssf.usermodel;

import org.apache.poi.hssf.record.CFRule12Record;
import org.apache.poi.hssf.record.cf.IconMultiStateThreshold;
import org.apache.poi.hssf.record.cf.Threshold;
import org.apache.poi.ss.usermodel.ConditionalFormattingThreshold;
import org.apache.poi.ss.usermodel.IconMultiStateFormatting;

public final class HSSFIconMultiStateFormatting implements IconMultiStateFormatting {
    private final org.apache.poi.hssf.record.cf.IconMultiStateFormatting iconFormatting;
    private final HSSFSheet sheet;

    HSSFIconMultiStateFormatting(CFRule12Record cFRule12Record, HSSFSheet hSSFSheet) {
        this.sheet = hSSFSheet;
        this.iconFormatting = cFRule12Record.getMultiStateFormatting();
    }

    public IconMultiStateFormatting.IconSet getIconSet() {
        return this.iconFormatting.getIconSet();
    }

    public void setIconSet(IconMultiStateFormatting.IconSet iconSet) {
        this.iconFormatting.setIconSet(iconSet);
    }

    public boolean isIconOnly() {
        return this.iconFormatting.isIconOnly();
    }

    public void setIconOnly(boolean z) {
        this.iconFormatting.setIconOnly(z);
    }

    public boolean isReversed() {
        return this.iconFormatting.isReversed();
    }

    public void setReversed(boolean z) {
        this.iconFormatting.setReversed(z);
    }

    public HSSFConditionalFormattingThreshold[] getThresholds() {
        Threshold[] thresholds = this.iconFormatting.getThresholds();
        HSSFConditionalFormattingThreshold[] hSSFConditionalFormattingThresholdArr = new HSSFConditionalFormattingThreshold[thresholds.length];
        for (int i = 0; i < thresholds.length; i++) {
            hSSFConditionalFormattingThresholdArr[i] = new HSSFConditionalFormattingThreshold(thresholds[i], this.sheet);
        }
        return hSSFConditionalFormattingThresholdArr;
    }

    public void setThresholds(ConditionalFormattingThreshold[] conditionalFormattingThresholdArr) {
        int length = conditionalFormattingThresholdArr.length;
        Threshold[] thresholdArr = new Threshold[length];
        for (int i = 0; i < length; i++) {
            thresholdArr[i] = conditionalFormattingThresholdArr[i].getThreshold();
        }
        this.iconFormatting.setThresholds(thresholdArr);
    }

    public HSSFConditionalFormattingThreshold createThreshold() {
        return new HSSFConditionalFormattingThreshold(new IconMultiStateThreshold(), this.sheet);
    }
}
