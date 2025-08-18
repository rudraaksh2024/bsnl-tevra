package org.apache.poi.hssf.usermodel;

import org.apache.poi.hssf.record.DVRecord;
import org.apache.poi.hssf.usermodel.DVConstraint;
import org.apache.poi.ss.usermodel.DataValidation;
import org.apache.poi.ss.usermodel.DataValidationConstraint;
import org.apache.poi.ss.util.CellRangeAddressList;

public final class HSSFDataValidation implements DataValidation {
    private DVConstraint _constraint;
    private boolean _emptyCellAllowed = true;
    private int _errorStyle = 0;
    private String _error_text;
    private String _error_title;
    private String _prompt_text;
    private String _prompt_title;
    private CellRangeAddressList _regions;
    private boolean _showErrorBox = true;
    private boolean _showPromptBox = true;
    private boolean _suppress_dropdown_arrow;

    public HSSFDataValidation(CellRangeAddressList cellRangeAddressList, DataValidationConstraint dataValidationConstraint) {
        this._regions = cellRangeAddressList;
        this._constraint = (DVConstraint) dataValidationConstraint;
    }

    public DataValidationConstraint getValidationConstraint() {
        return this._constraint;
    }

    public DVConstraint getConstraint() {
        return this._constraint;
    }

    public CellRangeAddressList getRegions() {
        return this._regions;
    }

    public void setErrorStyle(int i) {
        this._errorStyle = i;
    }

    public int getErrorStyle() {
        return this._errorStyle;
    }

    public void setEmptyCellAllowed(boolean z) {
        this._emptyCellAllowed = z;
    }

    public boolean getEmptyCellAllowed() {
        return this._emptyCellAllowed;
    }

    public void setSuppressDropDownArrow(boolean z) {
        this._suppress_dropdown_arrow = z;
    }

    public boolean getSuppressDropDownArrow() {
        if (this._constraint.getValidationType() == 3) {
            return this._suppress_dropdown_arrow;
        }
        return false;
    }

    public void setShowPromptBox(boolean z) {
        this._showPromptBox = z;
    }

    public boolean getShowPromptBox() {
        return this._showPromptBox;
    }

    public void setShowErrorBox(boolean z) {
        this._showErrorBox = z;
    }

    public boolean getShowErrorBox() {
        return this._showErrorBox;
    }

    public void createPromptBox(String str, String str2) {
        if (str != null && str.length() > 32) {
            throw new IllegalStateException("Prompt-title cannot be longer than 32 characters, but had: " + str);
        } else if (str2 == null || str2.length() <= 255) {
            this._prompt_title = str;
            this._prompt_text = str2;
            setShowPromptBox(true);
        } else {
            throw new IllegalStateException("Prompt-text cannot be longer than 255 characters, but had: " + str2);
        }
    }

    public String getPromptBoxTitle() {
        return this._prompt_title;
    }

    public String getPromptBoxText() {
        return this._prompt_text;
    }

    public void createErrorBox(String str, String str2) {
        if (str != null && str.length() > 32) {
            throw new IllegalStateException("Error-title cannot be longer than 32 characters, but had: " + str);
        } else if (str2 == null || str2.length() <= 255) {
            this._error_title = str;
            this._error_text = str2;
            setShowErrorBox(true);
        } else {
            throw new IllegalStateException("Error-text cannot be longer than 255 characters, but had: " + str2);
        }
    }

    public String getErrorBoxTitle() {
        return this._error_title;
    }

    public String getErrorBoxText() {
        return this._error_text;
    }

    public DVRecord createDVRecord(HSSFSheet hSSFSheet) {
        DVConstraint.FormulaPair createFormulas = this._constraint.createFormulas(hSSFSheet);
        return new DVRecord(this._constraint.getValidationType(), this._constraint.getOperator(), this._errorStyle, this._emptyCellAllowed, getSuppressDropDownArrow(), this._constraint.getValidationType() == 3 && this._constraint.getExplicitListValues() != null, this._showPromptBox, this._prompt_title, this._prompt_text, this._showErrorBox, this._error_title, this._error_text, createFormulas.getFormula1(), createFormulas.getFormula2(), this._regions);
    }
}
