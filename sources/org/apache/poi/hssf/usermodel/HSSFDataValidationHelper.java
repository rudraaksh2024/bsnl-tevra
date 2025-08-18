package org.apache.poi.hssf.usermodel;

import org.apache.poi.ss.usermodel.DataValidation;
import org.apache.poi.ss.usermodel.DataValidationConstraint;
import org.apache.poi.ss.usermodel.DataValidationHelper;
import org.apache.poi.ss.util.CellRangeAddressList;

public class HSSFDataValidationHelper implements DataValidationHelper {
    public HSSFDataValidationHelper(HSSFSheet hSSFSheet) {
    }

    public DataValidationConstraint createDateConstraint(int i, String str, String str2, String str3) {
        return DVConstraint.createDateConstraint(i, str, str2, str3);
    }

    public DataValidationConstraint createExplicitListConstraint(String[] strArr) {
        return DVConstraint.createExplicitListConstraint(strArr);
    }

    public DataValidationConstraint createFormulaListConstraint(String str) {
        return DVConstraint.createFormulaListConstraint(str);
    }

    public DataValidationConstraint createNumericConstraint(int i, int i2, String str, String str2) {
        return DVConstraint.createNumericConstraint(i, i2, str, str2);
    }

    public DataValidationConstraint createIntegerConstraint(int i, String str, String str2) {
        return DVConstraint.createNumericConstraint(1, i, str, str2);
    }

    public DataValidationConstraint createDecimalConstraint(int i, String str, String str2) {
        return DVConstraint.createNumericConstraint(2, i, str, str2);
    }

    public DataValidationConstraint createTextLengthConstraint(int i, String str, String str2) {
        return DVConstraint.createNumericConstraint(6, i, str, str2);
    }

    public DataValidationConstraint createTimeConstraint(int i, String str, String str2) {
        return DVConstraint.createTimeConstraint(i, str, str2);
    }

    public DataValidationConstraint createCustomConstraint(String str) {
        return DVConstraint.createCustomFormulaConstraint(str);
    }

    public DataValidation createValidation(DataValidationConstraint dataValidationConstraint, CellRangeAddressList cellRangeAddressList) {
        return new HSSFDataValidation(cellRangeAddressList, dataValidationConstraint);
    }
}
