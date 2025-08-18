package org.apache.poi.xssf.streaming;

import org.apache.poi.ss.formula.EvaluationCell;
import org.apache.poi.ss.formula.EvaluationSheet;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.util.CellRangeAddress;

final class SXSSFEvaluationCell implements EvaluationCell {
    private final SXSSFCell _cell;
    private final EvaluationSheet _evalSheet;

    public SXSSFEvaluationCell(SXSSFCell sXSSFCell, SXSSFEvaluationSheet sXSSFEvaluationSheet) {
        this._cell = sXSSFCell;
        this._evalSheet = sXSSFEvaluationSheet;
    }

    public SXSSFEvaluationCell(SXSSFCell sXSSFCell) {
        this(sXSSFCell, new SXSSFEvaluationSheet(sXSSFCell.getSheet()));
    }

    public Object getIdentityKey() {
        return this._cell;
    }

    public SXSSFCell getSXSSFCell() {
        return this._cell;
    }

    public boolean getBooleanCellValue() {
        return this._cell.getBooleanCellValue();
    }

    public CellType getCellType() {
        return this._cell.getCellType();
    }

    public int getColumnIndex() {
        return this._cell.getColumnIndex();
    }

    public int getErrorCellValue() {
        return this._cell.getErrorCellValue();
    }

    public double getNumericCellValue() {
        return this._cell.getNumericCellValue();
    }

    public int getRowIndex() {
        return this._cell.getRowIndex();
    }

    public EvaluationSheet getSheet() {
        return this._evalSheet;
    }

    public String getStringCellValue() {
        return this._cell.getRichStringCellValue().getString();
    }

    public CellRangeAddress getArrayFormulaRange() {
        return this._cell.getArrayFormulaRange();
    }

    public boolean isPartOfArrayFormulaGroup() {
        return this._cell.isPartOfArrayFormulaGroup();
    }

    public CellType getCachedFormulaResultType() {
        return this._cell.getCachedFormulaResultType();
    }
}
