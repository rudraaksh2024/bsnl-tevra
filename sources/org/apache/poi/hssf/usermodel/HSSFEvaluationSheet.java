package org.apache.poi.hssf.usermodel;

import org.apache.poi.ss.formula.EvaluationCell;
import org.apache.poi.ss.formula.EvaluationSheet;
import org.apache.poi.util.Internal;

@Internal
final class HSSFEvaluationSheet implements EvaluationSheet {
    private final HSSFSheet _hs;

    public void clearAllCachedResultValues() {
    }

    public HSSFEvaluationSheet(HSSFSheet hSSFSheet) {
        this._hs = hSSFSheet;
    }

    public HSSFSheet getHSSFSheet() {
        return this._hs;
    }

    public int getLastRowNum() {
        return this._hs.getLastRowNum();
    }

    public boolean isRowHidden(int i) {
        HSSFRow row = this._hs.getRow(i);
        if (row == null) {
            return false;
        }
        return row.getZeroHeight();
    }

    public EvaluationCell getCell(int i, int i2) {
        HSSFCell cell;
        HSSFRow row = this._hs.getRow(i);
        if (row == null || (cell = row.getCell(i2)) == null) {
            return null;
        }
        return new HSSFEvaluationCell(cell, this);
    }
}
