package org.apache.poi.xssf.streaming;

import org.apache.poi.ss.formula.EvaluationCell;
import org.apache.poi.ss.formula.EvaluationSheet;
import org.apache.poi.util.Internal;
import org.apache.poi.xssf.streaming.SXSSFFormulaEvaluator;

@Internal
final class SXSSFEvaluationSheet implements EvaluationSheet {
    private final SXSSFSheet _xs;

    public void clearAllCachedResultValues() {
    }

    public SXSSFEvaluationSheet(SXSSFSheet sXSSFSheet) {
        this._xs = sXSSFSheet;
    }

    public SXSSFSheet getSXSSFSheet() {
        return this._xs;
    }

    public int getLastRowNum() {
        return this._xs.getLastRowNum();
    }

    public boolean isRowHidden(int i) {
        SXSSFRow row = this._xs.getRow(i);
        if (row == null) {
            return false;
        }
        return row.getZeroHeight();
    }

    public EvaluationCell getCell(int i, int i2) {
        SXSSFRow row = this._xs.getRow(i);
        if (row != null) {
            SXSSFCell cell = row.getCell(i2);
            if (cell == null) {
                return null;
            }
            return new SXSSFEvaluationCell(cell, this);
        } else if (i > this._xs.getLastFlushedRowNum()) {
            return null;
        } else {
            throw new SXSSFFormulaEvaluator.RowFlushedException(i, this._xs.getLastFlushedRowNum());
        }
    }
}
