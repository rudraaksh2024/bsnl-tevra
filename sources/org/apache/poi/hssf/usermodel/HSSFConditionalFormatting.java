package org.apache.poi.hssf.usermodel;

import org.apache.poi.hssf.record.aggregates.CFRecordsAggregate;
import org.apache.poi.ss.usermodel.ConditionalFormatting;
import org.apache.poi.ss.usermodel.ConditionalFormattingRule;
import org.apache.poi.ss.util.CellRangeAddress;

public final class HSSFConditionalFormatting implements ConditionalFormatting {
    private final CFRecordsAggregate cfAggregate;
    private final HSSFSheet sheet;

    HSSFConditionalFormatting(HSSFSheet hSSFSheet, CFRecordsAggregate cFRecordsAggregate) {
        if (hSSFSheet == null) {
            throw new IllegalArgumentException("sheet must not be null");
        } else if (cFRecordsAggregate != null) {
            this.sheet = hSSFSheet;
            this.cfAggregate = cFRecordsAggregate;
        } else {
            throw new IllegalArgumentException("cfAggregate must not be null");
        }
    }

    /* access modifiers changed from: package-private */
    public CFRecordsAggregate getCFRecordsAggregate() {
        return this.cfAggregate;
    }

    public CellRangeAddress[] getFormattingRanges() {
        return this.cfAggregate.getHeader().getCellRanges();
    }

    public void setFormattingRanges(CellRangeAddress[] cellRangeAddressArr) {
        this.cfAggregate.getHeader().setCellRanges(cellRangeAddressArr);
    }

    public void setRule(int i, HSSFConditionalFormattingRule hSSFConditionalFormattingRule) {
        this.cfAggregate.setRule(i, hSSFConditionalFormattingRule.getCfRuleRecord());
    }

    public void setRule(int i, ConditionalFormattingRule conditionalFormattingRule) {
        setRule(i, (HSSFConditionalFormattingRule) conditionalFormattingRule);
    }

    public void addRule(HSSFConditionalFormattingRule hSSFConditionalFormattingRule) {
        this.cfAggregate.addRule(hSSFConditionalFormattingRule.getCfRuleRecord());
    }

    public void addRule(ConditionalFormattingRule conditionalFormattingRule) {
        addRule((HSSFConditionalFormattingRule) conditionalFormattingRule);
    }

    public HSSFConditionalFormattingRule getRule(int i) {
        return new HSSFConditionalFormattingRule(this.sheet, this.cfAggregate.getRule(i));
    }

    public int getNumberOfRules() {
        return this.cfAggregate.getNumberOfRules();
    }

    public String toString() {
        return this.cfAggregate.toString();
    }
}
