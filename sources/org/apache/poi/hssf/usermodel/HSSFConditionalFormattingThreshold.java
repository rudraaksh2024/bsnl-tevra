package org.apache.poi.hssf.usermodel;

import org.apache.poi.hssf.record.CFRuleBase;
import org.apache.poi.hssf.record.cf.Threshold;
import org.apache.poi.ss.usermodel.ConditionalFormattingThreshold;

public final class HSSFConditionalFormattingThreshold implements ConditionalFormattingThreshold {
    private final HSSFSheet sheet;
    private final Threshold threshold;
    private final HSSFWorkbook workbook;

    HSSFConditionalFormattingThreshold(Threshold threshold2, HSSFSheet hSSFSheet) {
        this.threshold = threshold2;
        this.sheet = hSSFSheet;
        this.workbook = hSSFSheet.getWorkbook();
    }

    /* access modifiers changed from: package-private */
    public Threshold getThreshold() {
        return this.threshold;
    }

    public ConditionalFormattingThreshold.RangeType getRangeType() {
        return ConditionalFormattingThreshold.RangeType.byId(this.threshold.getType());
    }

    public void setRangeType(ConditionalFormattingThreshold.RangeType rangeType) {
        this.threshold.setType((byte) rangeType.id);
    }

    public String getFormula() {
        return HSSFConditionalFormattingRule.toFormulaString(this.threshold.getParsedExpression(), this.workbook);
    }

    public void setFormula(String str) {
        this.threshold.setParsedExpression(CFRuleBase.parseFormula(str, this.sheet));
    }

    public Double getValue() {
        return this.threshold.getValue();
    }

    public void setValue(Double d) {
        this.threshold.setValue(d);
    }
}
