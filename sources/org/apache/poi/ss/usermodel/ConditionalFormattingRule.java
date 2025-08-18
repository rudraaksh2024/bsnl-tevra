package org.apache.poi.ss.usermodel;

public interface ConditionalFormattingRule extends DifferentialStyleProvider {
    BorderFormatting createBorderFormatting();

    FontFormatting createFontFormatting();

    PatternFormatting createPatternFormatting();

    BorderFormatting getBorderFormatting();

    ColorScaleFormatting getColorScaleFormatting();

    byte getComparisonOperation();

    ConditionFilterType getConditionFilterType();

    ConditionType getConditionType();

    DataBarFormatting getDataBarFormatting();

    ConditionFilterData getFilterConfiguration();

    FontFormatting getFontFormatting();

    String getFormula1();

    String getFormula2();

    IconMultiStateFormatting getMultiStateFormatting();

    ExcelNumberFormat getNumberFormat();

    PatternFormatting getPatternFormatting();

    int getPriority();

    boolean getStopIfTrue();

    String getText();
}
