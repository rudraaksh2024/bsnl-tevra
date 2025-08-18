package org.apache.poi.xssf.usermodel;

import java.util.HashMap;
import java.util.Map;
import org.apache.poi.ss.usermodel.ConditionFilterData;
import org.apache.poi.ss.usermodel.ConditionFilterType;
import org.apache.poi.ss.usermodel.ConditionType;
import org.apache.poi.ss.usermodel.ConditionalFormattingRule;
import org.apache.poi.ss.usermodel.ConditionalFormattingThreshold;
import org.apache.poi.ss.usermodel.ExcelNumberFormat;
import org.apache.poi.ss.usermodel.IconMultiStateFormatting;
import org.apache.poi.xssf.model.StylesTable;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTBorder;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCfRule;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCfvo;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTColorScale;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDataBar;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDxf;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFill;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFont;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTIconSet;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTNumFmt;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.STCfType;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.STCfvoType;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.STConditionalFormattingOperator;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.STIconSetType;

public class XSSFConditionalFormattingRule implements ConditionalFormattingRule {
    private static final Map<STCfType.Enum, ConditionFilterType> filterTypeLookup;
    private static final Map<STCfType.Enum, ConditionType> typeLookup;
    private final CTCfRule _cfRule;
    private final XSSFSheet _sh;

    public int getStripeSize() {
        return 0;
    }

    static {
        HashMap hashMap = new HashMap();
        typeLookup = hashMap;
        HashMap hashMap2 = new HashMap();
        filterTypeLookup = hashMap2;
        hashMap.put(STCfType.CELL_IS, ConditionType.CELL_VALUE_IS);
        hashMap.put(STCfType.EXPRESSION, ConditionType.FORMULA);
        hashMap.put(STCfType.COLOR_SCALE, ConditionType.COLOR_SCALE);
        hashMap.put(STCfType.DATA_BAR, ConditionType.DATA_BAR);
        hashMap.put(STCfType.ICON_SET, ConditionType.ICON_SET);
        hashMap.put(STCfType.TOP_10, ConditionType.FILTER);
        hashMap.put(STCfType.UNIQUE_VALUES, ConditionType.FILTER);
        hashMap.put(STCfType.DUPLICATE_VALUES, ConditionType.FILTER);
        hashMap.put(STCfType.CONTAINS_TEXT, ConditionType.FILTER);
        hashMap.put(STCfType.NOT_CONTAINS_TEXT, ConditionType.FILTER);
        hashMap.put(STCfType.BEGINS_WITH, ConditionType.FILTER);
        hashMap.put(STCfType.ENDS_WITH, ConditionType.FILTER);
        hashMap.put(STCfType.CONTAINS_BLANKS, ConditionType.FILTER);
        hashMap.put(STCfType.NOT_CONTAINS_BLANKS, ConditionType.FILTER);
        hashMap.put(STCfType.CONTAINS_ERRORS, ConditionType.FILTER);
        hashMap.put(STCfType.NOT_CONTAINS_ERRORS, ConditionType.FILTER);
        hashMap.put(STCfType.TIME_PERIOD, ConditionType.FILTER);
        hashMap.put(STCfType.ABOVE_AVERAGE, ConditionType.FILTER);
        hashMap2.put(STCfType.TOP_10, ConditionFilterType.TOP_10);
        hashMap2.put(STCfType.UNIQUE_VALUES, ConditionFilterType.UNIQUE_VALUES);
        hashMap2.put(STCfType.DUPLICATE_VALUES, ConditionFilterType.DUPLICATE_VALUES);
        hashMap2.put(STCfType.CONTAINS_TEXT, ConditionFilterType.CONTAINS_TEXT);
        hashMap2.put(STCfType.NOT_CONTAINS_TEXT, ConditionFilterType.NOT_CONTAINS_TEXT);
        hashMap2.put(STCfType.BEGINS_WITH, ConditionFilterType.BEGINS_WITH);
        hashMap2.put(STCfType.ENDS_WITH, ConditionFilterType.ENDS_WITH);
        hashMap2.put(STCfType.CONTAINS_BLANKS, ConditionFilterType.CONTAINS_BLANKS);
        hashMap2.put(STCfType.NOT_CONTAINS_BLANKS, ConditionFilterType.NOT_CONTAINS_BLANKS);
        hashMap2.put(STCfType.CONTAINS_ERRORS, ConditionFilterType.CONTAINS_ERRORS);
        hashMap2.put(STCfType.NOT_CONTAINS_ERRORS, ConditionFilterType.NOT_CONTAINS_ERRORS);
        hashMap2.put(STCfType.TIME_PERIOD, ConditionFilterType.TIME_PERIOD);
        hashMap2.put(STCfType.ABOVE_AVERAGE, ConditionFilterType.ABOVE_AVERAGE);
    }

    XSSFConditionalFormattingRule(XSSFSheet xSSFSheet) {
        this._cfRule = CTCfRule.Factory.newInstance();
        this._sh = xSSFSheet;
    }

    XSSFConditionalFormattingRule(XSSFSheet xSSFSheet, CTCfRule cTCfRule) {
        this._cfRule = cTCfRule;
        this._sh = xSSFSheet;
    }

    /* access modifiers changed from: package-private */
    public CTCfRule getCTCfRule() {
        return this._cfRule;
    }

    /* access modifiers changed from: package-private */
    public CTDxf getDxf(boolean z) {
        StylesTable stylesSource = this._sh.getWorkbook().getStylesSource();
        CTDxf dxfAt = (stylesSource._getDXfsSize() <= 0 || !this._cfRule.isSetDxfId()) ? null : stylesSource.getDxfAt((int) this._cfRule.getDxfId());
        if (!z || dxfAt != null) {
            return dxfAt;
        }
        CTDxf newInstance = CTDxf.Factory.newInstance();
        this._cfRule.setDxfId(((long) stylesSource.putDxf(newInstance)) - 1);
        return newInstance;
    }

    public int getPriority() {
        int priority = this._cfRule.getPriority();
        if (priority >= 1) {
            return priority;
        }
        return 0;
    }

    public boolean getStopIfTrue() {
        return this._cfRule.getStopIfTrue();
    }

    public XSSFBorderFormatting createBorderFormatting() {
        CTBorder cTBorder;
        CTDxf dxf = getDxf(true);
        if (!dxf.isSetBorder()) {
            cTBorder = dxf.addNewBorder();
        } else {
            cTBorder = dxf.getBorder();
        }
        return new XSSFBorderFormatting(cTBorder, this._sh.getWorkbook().getStylesSource().getIndexedColors());
    }

    public XSSFBorderFormatting getBorderFormatting() {
        CTDxf dxf = getDxf(false);
        if (dxf == null || !dxf.isSetBorder()) {
            return null;
        }
        return new XSSFBorderFormatting(dxf.getBorder(), this._sh.getWorkbook().getStylesSource().getIndexedColors());
    }

    public XSSFFontFormatting createFontFormatting() {
        CTFont cTFont;
        CTDxf dxf = getDxf(true);
        if (!dxf.isSetFont()) {
            cTFont = dxf.addNewFont();
        } else {
            cTFont = dxf.getFont();
        }
        return new XSSFFontFormatting(cTFont, this._sh.getWorkbook().getStylesSource().getIndexedColors());
    }

    public XSSFFontFormatting getFontFormatting() {
        CTDxf dxf = getDxf(false);
        if (dxf == null || !dxf.isSetFont()) {
            return null;
        }
        return new XSSFFontFormatting(dxf.getFont(), this._sh.getWorkbook().getStylesSource().getIndexedColors());
    }

    public XSSFPatternFormatting createPatternFormatting() {
        CTFill cTFill;
        CTDxf dxf = getDxf(true);
        if (!dxf.isSetFill()) {
            cTFill = dxf.addNewFill();
        } else {
            cTFill = dxf.getFill();
        }
        return new XSSFPatternFormatting(cTFill, this._sh.getWorkbook().getStylesSource().getIndexedColors());
    }

    public XSSFPatternFormatting getPatternFormatting() {
        CTDxf dxf = getDxf(false);
        if (dxf == null || !dxf.isSetFill()) {
            return null;
        }
        return new XSSFPatternFormatting(dxf.getFill(), this._sh.getWorkbook().getStylesSource().getIndexedColors());
    }

    public XSSFDataBarFormatting createDataBarFormatting(XSSFColor xSSFColor) {
        if (this._cfRule.isSetDataBar() && this._cfRule.getType() == STCfType.DATA_BAR) {
            return getDataBarFormatting();
        }
        this._cfRule.setType(STCfType.DATA_BAR);
        CTDataBar dataBar = this._cfRule.isSetDataBar() ? this._cfRule.getDataBar() : this._cfRule.addNewDataBar();
        dataBar.setColor(xSSFColor.getCTColor());
        dataBar.addNewCfvo().setType(STCfvoType.Enum.forString(ConditionalFormattingThreshold.RangeType.MIN.name));
        dataBar.addNewCfvo().setType(STCfvoType.Enum.forString(ConditionalFormattingThreshold.RangeType.MAX.name));
        return new XSSFDataBarFormatting(dataBar, this._sh.getWorkbook().getStylesSource().getIndexedColors());
    }

    public XSSFDataBarFormatting getDataBarFormatting() {
        if (this._cfRule.isSetDataBar()) {
            return new XSSFDataBarFormatting(this._cfRule.getDataBar(), this._sh.getWorkbook().getStylesSource().getIndexedColors());
        }
        return null;
    }

    public XSSFIconMultiStateFormatting createMultiStateFormatting(IconMultiStateFormatting.IconSet iconSet) {
        if (this._cfRule.isSetIconSet() && this._cfRule.getType() == STCfType.ICON_SET) {
            return getMultiStateFormatting();
        }
        this._cfRule.setType(STCfType.ICON_SET);
        boolean isSetIconSet = this._cfRule.isSetIconSet();
        CTCfRule cTCfRule = this._cfRule;
        CTIconSet iconSet2 = isSetIconSet ? cTCfRule.getIconSet() : cTCfRule.addNewIconSet();
        if (iconSet.name != null) {
            iconSet2.setIconSet(STIconSetType.Enum.forString(iconSet.name));
        }
        int i = 100 / iconSet.num;
        STCfvoType.Enum forString = STCfvoType.Enum.forString(ConditionalFormattingThreshold.RangeType.PERCENT.name);
        for (int i2 = 0; i2 < iconSet.num; i2++) {
            CTCfvo addNewCfvo = iconSet2.addNewCfvo();
            addNewCfvo.setType(forString);
            addNewCfvo.setVal(Integer.toString(i2 * i));
        }
        return new XSSFIconMultiStateFormatting(iconSet2);
    }

    public XSSFIconMultiStateFormatting getMultiStateFormatting() {
        if (this._cfRule.isSetIconSet()) {
            return new XSSFIconMultiStateFormatting(this._cfRule.getIconSet());
        }
        return null;
    }

    public XSSFColorScaleFormatting createColorScaleFormatting() {
        if (this._cfRule.isSetColorScale() && this._cfRule.getType() == STCfType.COLOR_SCALE) {
            return getColorScaleFormatting();
        }
        this._cfRule.setType(STCfType.COLOR_SCALE);
        CTColorScale colorScale = this._cfRule.isSetColorScale() ? this._cfRule.getColorScale() : this._cfRule.addNewColorScale();
        if (colorScale.sizeOfCfvoArray() == 0) {
            colorScale.addNewCfvo().setType(STCfvoType.Enum.forString(ConditionalFormattingThreshold.RangeType.MIN.name));
            CTCfvo addNewCfvo = colorScale.addNewCfvo();
            addNewCfvo.setType(STCfvoType.Enum.forString(ConditionalFormattingThreshold.RangeType.PERCENTILE.name));
            addNewCfvo.setVal("50");
            colorScale.addNewCfvo().setType(STCfvoType.Enum.forString(ConditionalFormattingThreshold.RangeType.MAX.name));
            for (int i = 0; i < 3; i++) {
                colorScale.addNewColor();
            }
        }
        return new XSSFColorScaleFormatting(colorScale, this._sh.getWorkbook().getStylesSource().getIndexedColors());
    }

    public XSSFColorScaleFormatting getColorScaleFormatting() {
        if (this._cfRule.isSetColorScale()) {
            return new XSSFColorScaleFormatting(this._cfRule.getColorScale(), this._sh.getWorkbook().getStylesSource().getIndexedColors());
        }
        return null;
    }

    public ExcelNumberFormat getNumberFormat() {
        CTDxf dxf = getDxf(false);
        if (dxf == null || !dxf.isSetNumFmt()) {
            return null;
        }
        CTNumFmt numFmt = dxf.getNumFmt();
        return new ExcelNumberFormat((int) numFmt.getNumFmtId(), numFmt.getFormatCode());
    }

    public ConditionType getConditionType() {
        return typeLookup.get(this._cfRule.getType());
    }

    public ConditionFilterType getConditionFilterType() {
        return filterTypeLookup.get(this._cfRule.getType());
    }

    public ConditionFilterData getFilterConfiguration() {
        return new XSSFConditionFilterData(this._cfRule);
    }

    public byte getComparisonOperation() {
        STConditionalFormattingOperator.Enum operator = this._cfRule.getOperator();
        if (operator == null) {
            return 0;
        }
        switch (operator.intValue()) {
            case 1:
                return 6;
            case 2:
                return 8;
            case 3:
                return 3;
            case 4:
                return 4;
            case 5:
                return 7;
            case 6:
                return 5;
            case 7:
                return 1;
            case 8:
                return 2;
            default:
                return 0;
        }
    }

    public String getFormula1() {
        if (this._cfRule.sizeOfFormulaArray() > 0) {
            return this._cfRule.getFormulaArray(0);
        }
        return null;
    }

    public String getFormula2() {
        if (this._cfRule.sizeOfFormulaArray() == 2) {
            return this._cfRule.getFormulaArray(1);
        }
        return null;
    }

    public String getText() {
        return this._cfRule.getText();
    }
}
