package org.apache.poi.xssf.usermodel;

import org.apache.poi.ss.SpreadsheetVersion;
import org.apache.poi.ss.formula.FormulaParser;
import org.apache.poi.ss.formula.FormulaType;
import org.apache.poi.ss.formula.ptg.Ptg;
import org.apache.poi.ss.usermodel.Name;
import org.apache.poi.ss.util.AreaReference;
import org.apache.poi.ss.util.CellReference;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDefinedName;

public final class XSSFName implements Name {
    public static final String BUILTIN_CONSOLIDATE_AREA = "_xlnm.Consolidate_Area";
    public static final String BUILTIN_CRITERIA = "_xlnm.Criteria:";
    public static final String BUILTIN_DATABASE = "_xlnm.Database";
    public static final String BUILTIN_EXTRACT = "_xlnm.Extract:";
    public static final String BUILTIN_FILTER_DB = "_xlnm._FilterDatabase";
    public static final String BUILTIN_PRINT_AREA = "_xlnm.Print_Area";
    public static final String BUILTIN_PRINT_TITLE = "_xlnm.Print_Titles";
    public static final String BUILTIN_SHEET_TITLE = "_xlnm.Sheet_Title";
    private final CTDefinedName _ctName;
    private final XSSFWorkbook _workbook;

    protected XSSFName(CTDefinedName cTDefinedName, XSSFWorkbook xSSFWorkbook) {
        this._workbook = xSSFWorkbook;
        this._ctName = cTDefinedName;
    }

    /* access modifiers changed from: protected */
    public CTDefinedName getCTName() {
        return this._ctName;
    }

    public String getNameName() {
        return this._ctName.getName();
    }

    public void setNameName(String str) {
        validateName(str);
        String nameName = getNameName();
        int sheetIndex = getSheetIndex();
        for (XSSFName next : this._workbook.getNames(str)) {
            if (next.getSheetIndex() == sheetIndex && next != this) {
                throw new IllegalArgumentException("The " + (sheetIndex == -1 ? "workbook" : "sheet") + " already contains this name: " + str);
            }
        }
        this._ctName.setName(str);
        this._workbook.updateName(this, nameName);
    }

    public String getRefersToFormula() {
        String stringValue = this._ctName.getStringValue();
        if (stringValue == null || stringValue.length() < 1) {
            return null;
        }
        return stringValue;
    }

    public void setRefersToFormula(String str) {
        FormulaParser.parse(str, XSSFEvaluationWorkbook.create(this._workbook), FormulaType.NAMEDRANGE, getSheetIndex(), -1);
        this._ctName.setStringValue(str);
    }

    public boolean isDeleted() {
        String refersToFormula = getRefersToFormula();
        if (refersToFormula == null) {
            return false;
        }
        return Ptg.doesFormulaReferToDeletedCell(FormulaParser.parse(refersToFormula, XSSFEvaluationWorkbook.create(this._workbook), FormulaType.NAMEDRANGE, getSheetIndex(), -1));
    }

    public void setSheetIndex(int i) {
        int numberOfSheets = this._workbook.getNumberOfSheets() - 1;
        if (i < -1 || i > numberOfSheets) {
            throw new IllegalArgumentException("Sheet index (" + i + ") is out of range" + (numberOfSheets == -1 ? "" : " (0.." + numberOfSheets + ")"));
        } else if (i != -1) {
            this._ctName.setLocalSheetId((long) i);
        } else if (this._ctName.isSetLocalSheetId()) {
            this._ctName.unsetLocalSheetId();
        }
    }

    public int getSheetIndex() {
        if (this._ctName.isSetLocalSheetId()) {
            return (int) this._ctName.getLocalSheetId();
        }
        return -1;
    }

    public void setFunction(boolean z) {
        this._ctName.setFunction(z);
    }

    public boolean getFunction() {
        return this._ctName.getFunction();
    }

    public void setFunctionGroupId(int i) {
        this._ctName.setFunctionGroupId((long) i);
    }

    public int getFunctionGroupId() {
        return (int) this._ctName.getFunctionGroupId();
    }

    public String getSheetName() {
        if (!this._ctName.isSetLocalSheetId()) {
            return new AreaReference(getRefersToFormula(), SpreadsheetVersion.EXCEL2007).getFirstCell().getSheetName();
        }
        return this._workbook.getSheetName((int) this._ctName.getLocalSheetId());
    }

    public boolean isFunctionName() {
        return getFunction();
    }

    public boolean isHidden() {
        return this._ctName.getHidden();
    }

    public String getComment() {
        return this._ctName.getComment();
    }

    public void setComment(String str) {
        this._ctName.setComment(str);
    }

    public int hashCode() {
        return this._ctName.toString().hashCode();
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof XSSFName)) {
            return false;
        }
        return this._ctName.toString().equals(((XSSFName) obj).getCTName().toString());
    }

    private static void validateName(String str) {
        if (str.length() == 0) {
            throw new IllegalArgumentException("Name cannot be blank");
        } else if (str.length() > 255) {
            throw new IllegalArgumentException("Invalid name: '" + str + "': cannot exceed 255 characters in length");
        } else if (str.equalsIgnoreCase("R") || str.equalsIgnoreCase("C")) {
            throw new IllegalArgumentException("Invalid name: '" + str + "': cannot be special shorthand R or C");
        } else {
            char charAt = str.charAt(0);
            if (Character.isLetter(charAt) || "_\\".indexOf(charAt) != -1) {
                char[] charArray = str.toCharArray();
                int length = charArray.length;
                int i = 0;
                while (i < length) {
                    char c = charArray[i];
                    if (Character.isLetterOrDigit(c) || "_.\\".indexOf(c) != -1) {
                        i++;
                    } else {
                        throw new IllegalArgumentException("Invalid name: '" + str + "': name must be letter, digit, period, or underscore");
                    }
                }
                if (str.matches("[A-Za-z]+\\d+")) {
                    try {
                        if (CellReference.cellReferenceIsWithinRange(str.replaceAll("\\d", ""), str.replaceAll("[A-Za-z]", ""), SpreadsheetVersion.EXCEL2007)) {
                            throw new IllegalArgumentException("Invalid name: '" + str + "': cannot be $A$1-style cell reference");
                        }
                    } catch (NumberFormatException unused) {
                    }
                }
                if (str.matches("[Rr]\\d+[Cc]\\d+")) {
                    throw new IllegalArgumentException("Invalid name: '" + str + "': cannot be R1C1-style cell reference");
                }
                return;
            }
            throw new IllegalArgumentException("Invalid name: '" + str + "': first character must be underscore or a letter");
        }
    }
}
