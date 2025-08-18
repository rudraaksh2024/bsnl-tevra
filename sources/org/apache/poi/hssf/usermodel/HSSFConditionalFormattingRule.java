package org.apache.poi.hssf.usermodel;

import org.apache.poi.hssf.model.HSSFFormulaParser;
import org.apache.poi.hssf.record.CFRule12Record;
import org.apache.poi.hssf.record.CFRuleBase;
import org.apache.poi.hssf.record.cf.BorderFormatting;
import org.apache.poi.hssf.record.cf.FontFormatting;
import org.apache.poi.hssf.record.cf.PatternFormatting;
import org.apache.poi.ss.formula.ptg.Ptg;
import org.apache.poi.ss.usermodel.ConditionFilterData;
import org.apache.poi.ss.usermodel.ConditionFilterType;
import org.apache.poi.ss.usermodel.ConditionType;
import org.apache.poi.ss.usermodel.ConditionalFormattingRule;
import org.apache.poi.ss.usermodel.ExcelNumberFormat;

public final class HSSFConditionalFormattingRule implements ConditionalFormattingRule {
    private static final byte CELL_COMPARISON = 1;
    private final CFRuleBase cfRuleRecord;
    private final HSSFSheet sheet;
    private final HSSFWorkbook workbook;

    public ConditionFilterData getFilterConfiguration() {
        return null;
    }

    public ExcelNumberFormat getNumberFormat() {
        return null;
    }

    public boolean getStopIfTrue() {
        return true;
    }

    public int getStripeSize() {
        return 0;
    }

    public String getText() {
        return null;
    }

    HSSFConditionalFormattingRule(HSSFSheet hSSFSheet, CFRuleBase cFRuleBase) {
        if (hSSFSheet == null) {
            throw new IllegalArgumentException("pSheet must not be null");
        } else if (cFRuleBase != null) {
            this.sheet = hSSFSheet;
            this.workbook = hSSFSheet.getWorkbook();
            this.cfRuleRecord = cFRuleBase;
        } else {
            throw new IllegalArgumentException("pRuleRecord must not be null");
        }
    }

    public int getPriority() {
        CFRule12Record cFRule12Record = getCFRule12Record(false);
        if (cFRule12Record == null) {
            return 0;
        }
        return cFRule12Record.getPriority();
    }

    /* access modifiers changed from: package-private */
    public CFRuleBase getCfRuleRecord() {
        return this.cfRuleRecord;
    }

    private CFRule12Record getCFRule12Record(boolean z) {
        CFRuleBase cFRuleBase = this.cfRuleRecord;
        if (cFRuleBase instanceof CFRule12Record) {
            return (CFRule12Record) cFRuleBase;
        }
        if (!z) {
            return null;
        }
        throw new IllegalArgumentException("Can't convert a CF into a CF12 record");
    }

    private HSSFFontFormatting getFontFormatting(boolean z) {
        if (this.cfRuleRecord.getFontFormatting() == null) {
            if (!z) {
                return null;
            }
            this.cfRuleRecord.setFontFormatting(new FontFormatting());
        }
        return new HSSFFontFormatting(this.cfRuleRecord, this.workbook);
    }

    public HSSFFontFormatting getFontFormatting() {
        return getFontFormatting(false);
    }

    public HSSFFontFormatting createFontFormatting() {
        return getFontFormatting(true);
    }

    private HSSFBorderFormatting getBorderFormatting(boolean z) {
        if (this.cfRuleRecord.getBorderFormatting() == null) {
            if (!z) {
                return null;
            }
            this.cfRuleRecord.setBorderFormatting(new BorderFormatting());
        }
        return new HSSFBorderFormatting(this.cfRuleRecord, this.workbook);
    }

    public HSSFBorderFormatting getBorderFormatting() {
        return getBorderFormatting(false);
    }

    public HSSFBorderFormatting createBorderFormatting() {
        return getBorderFormatting(true);
    }

    private HSSFPatternFormatting getPatternFormatting(boolean z) {
        if (this.cfRuleRecord.getPatternFormatting() == null) {
            if (!z) {
                return null;
            }
            this.cfRuleRecord.setPatternFormatting(new PatternFormatting());
        }
        return new HSSFPatternFormatting(this.cfRuleRecord, this.workbook);
    }

    public HSSFPatternFormatting getPatternFormatting() {
        return getPatternFormatting(false);
    }

    public HSSFPatternFormatting createPatternFormatting() {
        return getPatternFormatting(true);
    }

    private HSSFDataBarFormatting getDataBarFormatting(boolean z) {
        CFRule12Record cFRule12Record = getCFRule12Record(z);
        if (cFRule12Record == null) {
            return null;
        }
        if (cFRule12Record.getDataBarFormatting() == null) {
            if (!z) {
                return null;
            }
            cFRule12Record.createDataBarFormatting();
        }
        return new HSSFDataBarFormatting(cFRule12Record, this.sheet);
    }

    public HSSFDataBarFormatting getDataBarFormatting() {
        return getDataBarFormatting(false);
    }

    public HSSFDataBarFormatting createDataBarFormatting() {
        return getDataBarFormatting(true);
    }

    private HSSFIconMultiStateFormatting getMultiStateFormatting(boolean z) {
        CFRule12Record cFRule12Record = getCFRule12Record(z);
        if (cFRule12Record == null) {
            return null;
        }
        if (cFRule12Record.getMultiStateFormatting() == null) {
            if (!z) {
                return null;
            }
            cFRule12Record.createMultiStateFormatting();
        }
        return new HSSFIconMultiStateFormatting(cFRule12Record, this.sheet);
    }

    public HSSFIconMultiStateFormatting getMultiStateFormatting() {
        return getMultiStateFormatting(false);
    }

    public HSSFIconMultiStateFormatting createMultiStateFormatting() {
        return getMultiStateFormatting(true);
    }

    private HSSFColorScaleFormatting getColorScaleFormatting(boolean z) {
        CFRule12Record cFRule12Record = getCFRule12Record(z);
        if (cFRule12Record == null) {
            return null;
        }
        if (cFRule12Record.getColorGradientFormatting() == null) {
            if (!z) {
                return null;
            }
            cFRule12Record.createColorGradientFormatting();
        }
        return new HSSFColorScaleFormatting(cFRule12Record, this.sheet);
    }

    public HSSFColorScaleFormatting getColorScaleFormatting() {
        return getColorScaleFormatting(false);
    }

    public HSSFColorScaleFormatting createColorScaleFormatting() {
        return getColorScaleFormatting(true);
    }

    public ConditionType getConditionType() {
        return ConditionType.forId(this.cfRuleRecord.getConditionType());
    }

    public ConditionFilterType getConditionFilterType() {
        if (getConditionType() == ConditionType.FILTER) {
            return ConditionFilterType.FILTER;
        }
        return null;
    }

    public byte getComparisonOperation() {
        return this.cfRuleRecord.getComparisonOperation();
    }

    public String getFormula1() {
        return toFormulaString(this.cfRuleRecord.getParsedExpression1());
    }

    public String getFormula2() {
        if (this.cfRuleRecord.getConditionType() != 1) {
            return null;
        }
        byte comparisonOperation = this.cfRuleRecord.getComparisonOperation();
        if (comparisonOperation == 1 || comparisonOperation == 2) {
            return toFormulaString(this.cfRuleRecord.getParsedExpression2());
        }
        return null;
    }

    /* access modifiers changed from: package-private */
    public String toFormulaString(Ptg[] ptgArr) {
        return toFormulaString(ptgArr, this.workbook);
    }

    static String toFormulaString(Ptg[] ptgArr, HSSFWorkbook hSSFWorkbook) {
        if (ptgArr == null || ptgArr.length == 0) {
            return null;
        }
        return HSSFFormulaParser.toFormulaString(hSSFWorkbook, ptgArr);
    }
}
