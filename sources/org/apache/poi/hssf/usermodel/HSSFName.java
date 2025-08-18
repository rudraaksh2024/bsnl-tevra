package org.apache.poi.hssf.usermodel;

import org.apache.poi.hssf.model.HSSFFormulaParser;
import org.apache.poi.hssf.model.InternalWorkbook;
import org.apache.poi.hssf.record.NameCommentRecord;
import org.apache.poi.hssf.record.NameRecord;
import org.apache.poi.ss.SpreadsheetVersion;
import org.apache.poi.ss.formula.FormulaType;
import org.apache.poi.ss.formula.ptg.Ptg;
import org.apache.poi.ss.usermodel.Name;
import org.apache.poi.ss.util.CellReference;

public final class HSSFName implements Name {
    private final HSSFWorkbook _book;
    private final NameCommentRecord _commentRec;
    private final NameRecord _definedNameRec;

    HSSFName(HSSFWorkbook hSSFWorkbook, NameRecord nameRecord) {
        this(hSSFWorkbook, nameRecord, (NameCommentRecord) null);
    }

    HSSFName(HSSFWorkbook hSSFWorkbook, NameRecord nameRecord, NameCommentRecord nameCommentRecord) {
        this._book = hSSFWorkbook;
        this._definedNameRec = nameRecord;
        this._commentRec = nameCommentRecord;
    }

    public String getSheetName() {
        return this._book.getWorkbook().findSheetFirstNameFromExternSheet(this._definedNameRec.getExternSheetNumber());
    }

    public String getNameName() {
        return this._definedNameRec.getNameText();
    }

    public void setNameName(String str) {
        validateName(str);
        InternalWorkbook workbook = this._book.getWorkbook();
        this._definedNameRec.setNameText(str);
        int sheetNumber = this._definedNameRec.getSheetNumber();
        int numNames = workbook.getNumNames() - 1;
        while (numNames >= 0) {
            NameRecord nameRecord = workbook.getNameRecord(numNames);
            if (nameRecord == this._definedNameRec || !nameRecord.getNameText().equalsIgnoreCase(str) || sheetNumber != nameRecord.getSheetNumber()) {
                numNames--;
            } else {
                String str2 = "The " + (sheetNumber == 0 ? "workbook" : "sheet") + " already contains this name: " + str;
                this._definedNameRec.setNameText(str + "(2)");
                throw new IllegalArgumentException(str2);
            }
        }
        NameCommentRecord nameCommentRecord = this._commentRec;
        if (nameCommentRecord != null) {
            nameCommentRecord.setNameText(str);
            this._book.getWorkbook().updateNameCommentRecordCache(this._commentRec);
        }
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
                if (str.matches("[A-Za-z]+\\d+") && CellReference.cellReferenceIsWithinRange(str.replaceAll("\\d", ""), str.replaceAll("[A-Za-z]", ""), SpreadsheetVersion.EXCEL97)) {
                    throw new IllegalArgumentException("Invalid name: '" + str + "': cannot be $A$1-style cell reference");
                } else if (str.matches("[Rr]\\d+[Cc]\\d+")) {
                    throw new IllegalArgumentException("Invalid name: '" + str + "': cannot be R1C1-style cell reference");
                }
            } else {
                throw new IllegalArgumentException("Invalid name: '" + str + "': first character must be underscore or a letter");
            }
        }
    }

    public void setRefersToFormula(String str) {
        this._definedNameRec.setNameDefinition(HSSFFormulaParser.parse(str, this._book, FormulaType.NAMEDRANGE, getSheetIndex()));
    }

    public String getRefersToFormula() {
        if (!this._definedNameRec.isFunctionName()) {
            Ptg[] nameDefinition = this._definedNameRec.getNameDefinition();
            if (nameDefinition.length < 1) {
                return null;
            }
            return HSSFFormulaParser.toFormulaString(this._book, nameDefinition);
        }
        throw new IllegalStateException("Only applicable to named ranges");
    }

    /* access modifiers changed from: package-private */
    public void setNameDefinition(Ptg[] ptgArr) {
        this._definedNameRec.setNameDefinition(ptgArr);
    }

    public boolean isDeleted() {
        return Ptg.doesFormulaReferToDeletedCell(this._definedNameRec.getNameDefinition());
    }

    public boolean isFunctionName() {
        return this._definedNameRec.isFunctionName();
    }

    public boolean isHidden() {
        return this._definedNameRec.isHiddenName();
    }

    public String toString() {
        return getClass().getName() + " [" + this._definedNameRec.getNameText() + "]";
    }

    public void setSheetIndex(int i) {
        int numberOfSheets = this._book.getNumberOfSheets() - 1;
        if (i < -1 || i > numberOfSheets) {
            throw new IllegalArgumentException("Sheet index (" + i + ") is out of range" + (numberOfSheets == -1 ? "" : " (0.." + numberOfSheets + ")"));
        }
        this._definedNameRec.setSheetNumber(i + 1);
    }

    public int getSheetIndex() {
        return this._definedNameRec.getSheetNumber() - 1;
    }

    public String getComment() {
        NameCommentRecord nameCommentRecord = this._commentRec;
        if (nameCommentRecord == null || nameCommentRecord.getCommentText() == null || this._commentRec.getCommentText().length() <= 0) {
            return this._definedNameRec.getDescriptionText();
        }
        return this._commentRec.getCommentText();
    }

    public void setComment(String str) {
        this._definedNameRec.setDescriptionText(str);
        NameCommentRecord nameCommentRecord = this._commentRec;
        if (nameCommentRecord != null) {
            nameCommentRecord.setCommentText(str);
        }
    }

    public void setFunction(boolean z) {
        this._definedNameRec.setFunction(z);
    }
}
