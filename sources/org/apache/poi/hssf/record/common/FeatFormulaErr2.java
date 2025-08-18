package org.apache.poi.hssf.record.common;

import java.util.Map;
import java.util.function.Supplier;
import org.apache.poi.hssf.record.RecordInputStream;
import org.apache.poi.util.BitField;
import org.apache.poi.util.BitFieldFactory;
import org.apache.poi.util.GenericRecordJsonWriter;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.LittleEndianOutput;

public final class FeatFormulaErr2 implements SharedFeature {
    private static final BitField CHECK_CALCULATION_ERRORS = BitFieldFactory.getInstance(1);
    private static final BitField CHECK_DATETIME_FORMATS = BitFieldFactory.getInstance(32);
    private static final BitField CHECK_EMPTY_CELL_REF = BitFieldFactory.getInstance(2);
    private static final BitField CHECK_INCONSISTENT_FORMULAS = BitFieldFactory.getInstance(16);
    private static final BitField CHECK_INCONSISTENT_RANGES = BitFieldFactory.getInstance(8);
    private static final BitField CHECK_NUMBERS_AS_TEXT = BitFieldFactory.getInstance(4);
    private static final BitField CHECK_UNPROTECTED_FORMULAS = BitFieldFactory.getInstance(64);
    private static final BitField PERFORM_DATA_VALIDATION = BitFieldFactory.getInstance(128);
    private int errorCheck;

    public int getDataSize() {
        return 4;
    }

    public FeatFormulaErr2() {
    }

    public FeatFormulaErr2(FeatFormulaErr2 featFormulaErr2) {
        this.errorCheck = featFormulaErr2.errorCheck;
    }

    public FeatFormulaErr2(RecordInputStream recordInputStream) {
        this.errorCheck = recordInputStream.readInt();
    }

    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("errorCheck", GenericRecordUtil.getBitsAsString((Supplier<Number>) new FeatFormulaErr2$$ExternalSyntheticLambda0(this), new BitField[]{CHECK_CALCULATION_ERRORS, CHECK_EMPTY_CELL_REF, CHECK_NUMBERS_AS_TEXT, CHECK_INCONSISTENT_RANGES, CHECK_INCONSISTENT_FORMULAS, CHECK_DATETIME_FORMATS, CHECK_UNPROTECTED_FORMULAS, PERFORM_DATA_VALIDATION}, new String[]{"CHECK_CALCULATION_ERRORS", "CHECK_EMPTY_CELL_REF", "CHECK_NUMBERS_AS_TEXT", "CHECK_INCONSISTENT_RANGES", "CHECK_INCONSISTENT_FORMULAS", "CHECK_DATETIME_FORMATS", "CHECK_UNPROTECTED_FORMULAS", "PERFORM_DATA_VALIDATION"}));
    }

    public String toString() {
        return GenericRecordJsonWriter.marshal(this);
    }

    public void serialize(LittleEndianOutput littleEndianOutput) {
        littleEndianOutput.writeInt(this.errorCheck);
    }

    public int _getRawErrorCheckValue() {
        return this.errorCheck;
    }

    public boolean getCheckCalculationErrors() {
        return CHECK_CALCULATION_ERRORS.isSet(this.errorCheck);
    }

    public void setCheckCalculationErrors(boolean z) {
        this.errorCheck = CHECK_CALCULATION_ERRORS.setBoolean(this.errorCheck, z);
    }

    public boolean getCheckEmptyCellRef() {
        return CHECK_EMPTY_CELL_REF.isSet(this.errorCheck);
    }

    public void setCheckEmptyCellRef(boolean z) {
        this.errorCheck = CHECK_EMPTY_CELL_REF.setBoolean(this.errorCheck, z);
    }

    public boolean getCheckNumbersAsText() {
        return CHECK_NUMBERS_AS_TEXT.isSet(this.errorCheck);
    }

    public void setCheckNumbersAsText(boolean z) {
        this.errorCheck = CHECK_NUMBERS_AS_TEXT.setBoolean(this.errorCheck, z);
    }

    public boolean getCheckInconsistentRanges() {
        return CHECK_INCONSISTENT_RANGES.isSet(this.errorCheck);
    }

    public void setCheckInconsistentRanges(boolean z) {
        this.errorCheck = CHECK_INCONSISTENT_RANGES.setBoolean(this.errorCheck, z);
    }

    public boolean getCheckInconsistentFormulas() {
        return CHECK_INCONSISTENT_FORMULAS.isSet(this.errorCheck);
    }

    public void setCheckInconsistentFormulas(boolean z) {
        this.errorCheck = CHECK_INCONSISTENT_FORMULAS.setBoolean(this.errorCheck, z);
    }

    public boolean getCheckDateTimeFormats() {
        return CHECK_DATETIME_FORMATS.isSet(this.errorCheck);
    }

    public void setCheckDateTimeFormats(boolean z) {
        this.errorCheck = CHECK_DATETIME_FORMATS.setBoolean(this.errorCheck, z);
    }

    public boolean getCheckUnprotectedFormulas() {
        return CHECK_UNPROTECTED_FORMULAS.isSet(this.errorCheck);
    }

    public void setCheckUnprotectedFormulas(boolean z) {
        this.errorCheck = CHECK_UNPROTECTED_FORMULAS.setBoolean(this.errorCheck, z);
    }

    public boolean getPerformDataValidation() {
        return PERFORM_DATA_VALIDATION.isSet(this.errorCheck);
    }

    public void setPerformDataValidation(boolean z) {
        this.errorCheck = PERFORM_DATA_VALIDATION.setBoolean(this.errorCheck, z);
    }

    public FeatFormulaErr2 copy() {
        return new FeatFormulaErr2(this);
    }
}
