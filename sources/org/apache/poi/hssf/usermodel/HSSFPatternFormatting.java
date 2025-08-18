package org.apache.poi.hssf.usermodel;

import org.apache.poi.hssf.record.CFRuleBase;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Color;
import org.apache.poi.ss.usermodel.PatternFormatting;

public class HSSFPatternFormatting implements PatternFormatting {
    private final CFRuleBase cfRuleRecord;
    private final org.apache.poi.hssf.record.cf.PatternFormatting patternFormatting;
    private final HSSFWorkbook workbook;

    protected HSSFPatternFormatting(CFRuleBase cFRuleBase, HSSFWorkbook hSSFWorkbook) {
        this.workbook = hSSFWorkbook;
        this.cfRuleRecord = cFRuleBase;
        this.patternFormatting = cFRuleBase.getPatternFormatting();
    }

    /* access modifiers changed from: protected */
    public org.apache.poi.hssf.record.cf.PatternFormatting getPatternFormattingBlock() {
        return this.patternFormatting;
    }

    public HSSFColor getFillBackgroundColorColor() {
        return this.workbook.getCustomPalette().getColor(getFillBackgroundColor());
    }

    public HSSFColor getFillForegroundColorColor() {
        return this.workbook.getCustomPalette().getColor(getFillForegroundColor());
    }

    public short getFillBackgroundColor() {
        return (short) this.patternFormatting.getFillBackgroundColor();
    }

    public short getFillForegroundColor() {
        return (short) this.patternFormatting.getFillForegroundColor();
    }

    public short getFillPattern() {
        return (short) this.patternFormatting.getFillPattern();
    }

    public void setFillBackgroundColor(Color color) {
        HSSFColor hSSFColor = HSSFColor.toHSSFColor(color);
        if (hSSFColor == null) {
            setFillBackgroundColor(0);
        } else {
            setFillBackgroundColor(hSSFColor.getIndex());
        }
    }

    public void setFillForegroundColor(Color color) {
        HSSFColor hSSFColor = HSSFColor.toHSSFColor(color);
        if (hSSFColor == null) {
            setFillForegroundColor(0);
        } else {
            setFillForegroundColor(hSSFColor.getIndex());
        }
    }

    public void setFillBackgroundColor(short s) {
        this.patternFormatting.setFillBackgroundColor(s);
        if (s != 0) {
            this.cfRuleRecord.setPatternBackgroundColorModified(true);
        }
    }

    public void setFillForegroundColor(short s) {
        this.patternFormatting.setFillForegroundColor(s);
        if (s != 0) {
            this.cfRuleRecord.setPatternColorModified(true);
        }
    }

    public void setFillPattern(short s) {
        this.patternFormatting.setFillPattern(s);
        if (s != 0) {
            this.cfRuleRecord.setPatternStyleModified(true);
        }
    }
}
