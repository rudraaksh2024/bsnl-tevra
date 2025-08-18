package org.apache.poi.xssf.usermodel;

import java.util.ArrayList;
import org.apache.poi.ss.SpreadsheetVersion;
import org.apache.poi.ss.usermodel.ConditionalFormatting;
import org.apache.poi.ss.usermodel.ConditionalFormattingRule;
import org.apache.poi.ss.usermodel.ExtendedColor;
import org.apache.poi.ss.usermodel.IconMultiStateFormatting;
import org.apache.poi.ss.usermodel.SheetConditionalFormatting;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.CellRangeUtil;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCfRule;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTConditionalFormatting;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorksheet;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.STCfType;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.STConditionalFormattingOperator;

public class XSSFSheetConditionalFormatting implements SheetConditionalFormatting {
    protected static final String CF_EXT_2009_NS_X14 = "http://schemas.microsoft.com/office/spreadsheetml/2009/9/main";
    private final XSSFSheet _sheet;

    XSSFSheetConditionalFormatting(XSSFSheet xSSFSheet) {
        this._sheet = xSSFSheet;
    }

    public XSSFConditionalFormattingRule createConditionalFormattingRule(byte b, String str, String str2) {
        STConditionalFormattingOperator.Enum enumR;
        XSSFConditionalFormattingRule xSSFConditionalFormattingRule = new XSSFConditionalFormattingRule(this._sheet);
        CTCfRule cTCfRule = xSSFConditionalFormattingRule.getCTCfRule();
        cTCfRule.addFormula(str);
        if (str2 != null) {
            cTCfRule.addFormula(str2);
        }
        cTCfRule.setType(STCfType.CELL_IS);
        switch (b) {
            case 1:
                enumR = STConditionalFormattingOperator.BETWEEN;
                break;
            case 2:
                enumR = STConditionalFormattingOperator.NOT_BETWEEN;
                break;
            case 3:
                enumR = STConditionalFormattingOperator.EQUAL;
                break;
            case 4:
                enumR = STConditionalFormattingOperator.NOT_EQUAL;
                break;
            case 5:
                enumR = STConditionalFormattingOperator.GREATER_THAN;
                break;
            case 6:
                enumR = STConditionalFormattingOperator.LESS_THAN;
                break;
            case 7:
                enumR = STConditionalFormattingOperator.GREATER_THAN_OR_EQUAL;
                break;
            case 8:
                enumR = STConditionalFormattingOperator.LESS_THAN_OR_EQUAL;
                break;
            default:
                throw new IllegalArgumentException("Unknown comparison operator: " + b);
        }
        cTCfRule.setOperator(enumR);
        return xSSFConditionalFormattingRule;
    }

    public XSSFConditionalFormattingRule createConditionalFormattingRule(byte b, String str) {
        return createConditionalFormattingRule(b, str, (String) null);
    }

    public XSSFConditionalFormattingRule createConditionalFormattingRule(String str) {
        XSSFConditionalFormattingRule xSSFConditionalFormattingRule = new XSSFConditionalFormattingRule(this._sheet);
        CTCfRule cTCfRule = xSSFConditionalFormattingRule.getCTCfRule();
        cTCfRule.addFormula(str);
        cTCfRule.setType(STCfType.EXPRESSION);
        return xSSFConditionalFormattingRule;
    }

    public XSSFConditionalFormattingRule createConditionalFormattingRule(XSSFColor xSSFColor) {
        XSSFConditionalFormattingRule xSSFConditionalFormattingRule = new XSSFConditionalFormattingRule(this._sheet);
        xSSFConditionalFormattingRule.createDataBarFormatting(xSSFColor);
        return xSSFConditionalFormattingRule;
    }

    public XSSFConditionalFormattingRule createConditionalFormattingRule(ExtendedColor extendedColor) {
        return createConditionalFormattingRule((XSSFColor) extendedColor);
    }

    public XSSFConditionalFormattingRule createConditionalFormattingRule(IconMultiStateFormatting.IconSet iconSet) {
        XSSFConditionalFormattingRule xSSFConditionalFormattingRule = new XSSFConditionalFormattingRule(this._sheet);
        xSSFConditionalFormattingRule.createMultiStateFormatting(iconSet);
        return xSSFConditionalFormattingRule;
    }

    public XSSFConditionalFormattingRule createConditionalFormattingColorScaleRule() {
        XSSFConditionalFormattingRule xSSFConditionalFormattingRule = new XSSFConditionalFormattingRule(this._sheet);
        xSSFConditionalFormattingRule.createColorScaleFormatting();
        return xSSFConditionalFormattingRule;
    }

    public int addConditionalFormatting(CellRangeAddress[] cellRangeAddressArr, ConditionalFormattingRule[] conditionalFormattingRuleArr) {
        if (cellRangeAddressArr != null) {
            int i = 0;
            for (CellRangeAddress validate : cellRangeAddressArr) {
                validate.validate(SpreadsheetVersion.EXCEL2007);
            }
            if (conditionalFormattingRuleArr == null) {
                throw new IllegalArgumentException("cfRules must not be null");
            } else if (conditionalFormattingRuleArr.length != 0) {
                CellRangeAddress[] mergeCellRanges = CellRangeUtil.mergeCellRanges(cellRangeAddressArr);
                CTConditionalFormatting addNewConditionalFormatting = this._sheet.getCTWorksheet().addNewConditionalFormatting();
                ArrayList arrayList = new ArrayList();
                for (CellRangeAddress formatAsString : mergeCellRanges) {
                    arrayList.add(formatAsString.formatAsString());
                }
                addNewConditionalFormatting.setSqref(arrayList);
                int i2 = 1;
                for (CTConditionalFormatting sizeOfCfRuleArray : this._sheet.getCTWorksheet().getConditionalFormattingArray()) {
                    i2 += sizeOfCfRuleArray.sizeOfCfRuleArray();
                }
                int length = conditionalFormattingRuleArr.length;
                while (i < length) {
                    XSSFConditionalFormattingRule xSSFConditionalFormattingRule = conditionalFormattingRuleArr[i];
                    xSSFConditionalFormattingRule.getCTCfRule().setPriority(i2);
                    addNewConditionalFormatting.addNewCfRule().set(xSSFConditionalFormattingRule.getCTCfRule());
                    i++;
                    i2++;
                }
                return this._sheet.getCTWorksheet().sizeOfConditionalFormattingArray() - 1;
            } else {
                throw new IllegalArgumentException("cfRules must not be empty");
            }
        } else {
            throw new IllegalArgumentException("regions must not be null");
        }
    }

    public int addConditionalFormatting(CellRangeAddress[] cellRangeAddressArr, ConditionalFormattingRule conditionalFormattingRule) {
        XSSFConditionalFormattingRule[] xSSFConditionalFormattingRuleArr;
        if (conditionalFormattingRule == null) {
            xSSFConditionalFormattingRuleArr = null;
        } else {
            xSSFConditionalFormattingRuleArr = new XSSFConditionalFormattingRule[]{(XSSFConditionalFormattingRule) conditionalFormattingRule};
        }
        return addConditionalFormatting(cellRangeAddressArr, (ConditionalFormattingRule[]) xSSFConditionalFormattingRuleArr);
    }

    public int addConditionalFormatting(CellRangeAddress[] cellRangeAddressArr, ConditionalFormattingRule conditionalFormattingRule, ConditionalFormattingRule conditionalFormattingRule2) {
        XSSFConditionalFormattingRule[] xSSFConditionalFormattingRuleArr;
        if (conditionalFormattingRule == null) {
            xSSFConditionalFormattingRuleArr = null;
        } else {
            xSSFConditionalFormattingRuleArr = new XSSFConditionalFormattingRule[]{(XSSFConditionalFormattingRule) conditionalFormattingRule, (XSSFConditionalFormattingRule) conditionalFormattingRule2};
        }
        return addConditionalFormatting(cellRangeAddressArr, (ConditionalFormattingRule[]) xSSFConditionalFormattingRuleArr);
    }

    public int addConditionalFormatting(ConditionalFormatting conditionalFormatting) {
        CTWorksheet cTWorksheet = this._sheet.getCTWorksheet();
        cTWorksheet.addNewConditionalFormatting().set(((XSSFConditionalFormatting) conditionalFormatting).getCTConditionalFormatting().copy());
        return cTWorksheet.sizeOfConditionalFormattingArray() - 1;
    }

    public XSSFConditionalFormatting getConditionalFormattingAt(int i) {
        checkIndex(i);
        return new XSSFConditionalFormatting(this._sheet, this._sheet.getCTWorksheet().getConditionalFormattingArray(i));
    }

    public int getNumConditionalFormattings() {
        return this._sheet.getCTWorksheet().sizeOfConditionalFormattingArray();
    }

    public void removeConditionalFormatting(int i) {
        checkIndex(i);
        this._sheet.getCTWorksheet().removeConditionalFormatting(i);
    }

    private void checkIndex(int i) {
        int numConditionalFormattings = getNumConditionalFormattings();
        if (i < 0 || i >= numConditionalFormattings) {
            throw new IllegalArgumentException("Specified CF index " + i + " is outside the allowable range (0.." + (numConditionalFormattings - 1) + ")");
        }
    }
}
