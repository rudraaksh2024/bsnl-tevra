package org.apache.poi.hssf.usermodel;

import org.apache.poi.hssf.record.CFRule12Record;
import org.apache.poi.hssf.record.CFRuleBase;
import org.apache.poi.hssf.record.CFRuleRecord;
import org.apache.poi.hssf.record.aggregates.CFRecordsAggregate;
import org.apache.poi.hssf.record.aggregates.ConditionalFormattingTable;
import org.apache.poi.ss.SpreadsheetVersion;
import org.apache.poi.ss.usermodel.ConditionalFormatting;
import org.apache.poi.ss.usermodel.ConditionalFormattingRule;
import org.apache.poi.ss.usermodel.ExtendedColor;
import org.apache.poi.ss.usermodel.IconMultiStateFormatting;
import org.apache.poi.ss.usermodel.SheetConditionalFormatting;
import org.apache.poi.ss.util.CellRangeAddress;

public final class HSSFSheetConditionalFormatting implements SheetConditionalFormatting {
    private final ConditionalFormattingTable _conditionalFormattingTable;
    private final HSSFSheet _sheet;

    HSSFSheetConditionalFormatting(HSSFSheet hSSFSheet) {
        this._sheet = hSSFSheet;
        this._conditionalFormattingTable = hSSFSheet.getSheet().getConditionalFormattingTable();
    }

    public HSSFConditionalFormattingRule createConditionalFormattingRule(byte b, String str, String str2) {
        return new HSSFConditionalFormattingRule(this._sheet, CFRuleRecord.create(this._sheet, b, str, str2));
    }

    public HSSFConditionalFormattingRule createConditionalFormattingRule(byte b, String str) {
        return new HSSFConditionalFormattingRule(this._sheet, CFRuleRecord.create(this._sheet, b, str, (String) null));
    }

    public HSSFConditionalFormattingRule createConditionalFormattingRule(String str) {
        return new HSSFConditionalFormattingRule(this._sheet, CFRuleRecord.create(this._sheet, str));
    }

    public HSSFConditionalFormattingRule createConditionalFormattingRule(IconMultiStateFormatting.IconSet iconSet) {
        return new HSSFConditionalFormattingRule(this._sheet, CFRule12Record.create(this._sheet, iconSet));
    }

    public HSSFConditionalFormattingRule createConditionalFormattingRule(HSSFExtendedColor hSSFExtendedColor) {
        return new HSSFConditionalFormattingRule(this._sheet, CFRule12Record.create(this._sheet, hSSFExtendedColor.getExtendedColor()));
    }

    public HSSFConditionalFormattingRule createConditionalFormattingRule(ExtendedColor extendedColor) {
        return createConditionalFormattingRule((HSSFExtendedColor) extendedColor);
    }

    public HSSFConditionalFormattingRule createConditionalFormattingColorScaleRule() {
        return new HSSFConditionalFormattingRule(this._sheet, CFRule12Record.createColorScale(this._sheet));
    }

    public int addConditionalFormatting(HSSFConditionalFormatting hSSFConditionalFormatting) {
        return this._conditionalFormattingTable.add(hSSFConditionalFormatting.getCFRecordsAggregate().cloneCFAggregate());
    }

    public int addConditionalFormatting(ConditionalFormatting conditionalFormatting) {
        return addConditionalFormatting((HSSFConditionalFormatting) conditionalFormatting);
    }

    public int addConditionalFormatting(CellRangeAddress[] cellRangeAddressArr, HSSFConditionalFormattingRule[] hSSFConditionalFormattingRuleArr) {
        if (cellRangeAddressArr != null) {
            for (CellRangeAddress validate : cellRangeAddressArr) {
                validate.validate(SpreadsheetVersion.EXCEL97);
            }
            if (hSSFConditionalFormattingRuleArr == null) {
                throw new IllegalArgumentException("cfRules must not be null");
            } else if (hSSFConditionalFormattingRuleArr.length == 0) {
                throw new IllegalArgumentException("cfRules must not be empty");
            } else if (hSSFConditionalFormattingRuleArr.length <= 3) {
                CFRuleBase[] cFRuleBaseArr = new CFRuleBase[hSSFConditionalFormattingRuleArr.length];
                for (int i = 0; i != hSSFConditionalFormattingRuleArr.length; i++) {
                    cFRuleBaseArr[i] = hSSFConditionalFormattingRuleArr[i].getCfRuleRecord();
                }
                return this._conditionalFormattingTable.add(new CFRecordsAggregate(cellRangeAddressArr, cFRuleBaseArr));
            } else {
                throw new IllegalArgumentException("Number of rules must not exceed 3");
            }
        } else {
            throw new IllegalArgumentException("regions must not be null");
        }
    }

    public int addConditionalFormatting(CellRangeAddress[] cellRangeAddressArr, ConditionalFormattingRule[] conditionalFormattingRuleArr) {
        HSSFConditionalFormattingRule[] hSSFConditionalFormattingRuleArr;
        if (conditionalFormattingRuleArr instanceof HSSFConditionalFormattingRule[]) {
            hSSFConditionalFormattingRuleArr = (HSSFConditionalFormattingRule[]) conditionalFormattingRuleArr;
        } else {
            int length = conditionalFormattingRuleArr.length;
            HSSFConditionalFormattingRule[] hSSFConditionalFormattingRuleArr2 = new HSSFConditionalFormattingRule[length];
            System.arraycopy(conditionalFormattingRuleArr, 0, hSSFConditionalFormattingRuleArr2, 0, length);
            hSSFConditionalFormattingRuleArr = hSSFConditionalFormattingRuleArr2;
        }
        return addConditionalFormatting(cellRangeAddressArr, hSSFConditionalFormattingRuleArr);
    }

    public int addConditionalFormatting(CellRangeAddress[] cellRangeAddressArr, HSSFConditionalFormattingRule hSSFConditionalFormattingRule) {
        HSSFConditionalFormattingRule[] hSSFConditionalFormattingRuleArr;
        if (hSSFConditionalFormattingRule == null) {
            hSSFConditionalFormattingRuleArr = null;
        } else {
            hSSFConditionalFormattingRuleArr = new HSSFConditionalFormattingRule[]{hSSFConditionalFormattingRule};
        }
        return addConditionalFormatting(cellRangeAddressArr, hSSFConditionalFormattingRuleArr);
    }

    public int addConditionalFormatting(CellRangeAddress[] cellRangeAddressArr, ConditionalFormattingRule conditionalFormattingRule) {
        return addConditionalFormatting(cellRangeAddressArr, (HSSFConditionalFormattingRule) conditionalFormattingRule);
    }

    public int addConditionalFormatting(CellRangeAddress[] cellRangeAddressArr, HSSFConditionalFormattingRule hSSFConditionalFormattingRule, HSSFConditionalFormattingRule hSSFConditionalFormattingRule2) {
        return addConditionalFormatting(cellRangeAddressArr, new HSSFConditionalFormattingRule[]{hSSFConditionalFormattingRule, hSSFConditionalFormattingRule2});
    }

    public int addConditionalFormatting(CellRangeAddress[] cellRangeAddressArr, ConditionalFormattingRule conditionalFormattingRule, ConditionalFormattingRule conditionalFormattingRule2) {
        return addConditionalFormatting(cellRangeAddressArr, (HSSFConditionalFormattingRule) conditionalFormattingRule, (HSSFConditionalFormattingRule) conditionalFormattingRule2);
    }

    public HSSFConditionalFormatting getConditionalFormattingAt(int i) {
        CFRecordsAggregate cFRecordsAggregate = this._conditionalFormattingTable.get(i);
        if (cFRecordsAggregate == null) {
            return null;
        }
        return new HSSFConditionalFormatting(this._sheet, cFRecordsAggregate);
    }

    public int getNumConditionalFormattings() {
        return this._conditionalFormattingTable.size();
    }

    public void removeConditionalFormatting(int i) {
        this._conditionalFormattingTable.remove(i);
    }
}
