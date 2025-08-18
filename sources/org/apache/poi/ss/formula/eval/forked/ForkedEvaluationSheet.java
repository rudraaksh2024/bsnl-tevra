package org.apache.poi.ss.formula.eval.forked;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import org.apache.poi.ss.formula.EvaluationCell;
import org.apache.poi.ss.formula.EvaluationSheet;
import org.apache.poi.ss.formula.EvaluationWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.util.Internal;

@Internal
final class ForkedEvaluationSheet implements EvaluationSheet {
    private final EvaluationSheet _masterSheet;
    private final Map<RowColKey, ForkedEvaluationCell> _sharedCellsByRowCol = new HashMap();

    public ForkedEvaluationSheet(EvaluationSheet evaluationSheet) {
        this._masterSheet = evaluationSheet;
    }

    public int getLastRowNum() {
        return this._masterSheet.getLastRowNum();
    }

    public boolean isRowHidden(int i) {
        return this._masterSheet.isRowHidden(i);
    }

    public EvaluationCell getCell(int i, int i2) {
        ForkedEvaluationCell forkedEvaluationCell = this._sharedCellsByRowCol.get(new RowColKey(i, i2));
        return forkedEvaluationCell == null ? this._masterSheet.getCell(i, i2) : forkedEvaluationCell;
    }

    public ForkedEvaluationCell getOrCreateUpdatableCell(int i, int i2) {
        RowColKey rowColKey = new RowColKey(i, i2);
        ForkedEvaluationCell forkedEvaluationCell = this._sharedCellsByRowCol.get(rowColKey);
        if (forkedEvaluationCell != null) {
            return forkedEvaluationCell;
        }
        EvaluationCell cell = this._masterSheet.getCell(i, i2);
        if (cell != null) {
            ForkedEvaluationCell forkedEvaluationCell2 = new ForkedEvaluationCell(this, cell);
            this._sharedCellsByRowCol.put(rowColKey, forkedEvaluationCell2);
            return forkedEvaluationCell2;
        }
        throw new UnsupportedOperationException("Underlying cell '" + new CellReference(i, i2).formatAsString() + "' is missing in master sheet.");
    }

    public void copyUpdatedCells(Sheet sheet) {
        int size = this._sharedCellsByRowCol.size();
        RowColKey[] rowColKeyArr = new RowColKey[size];
        this._sharedCellsByRowCol.keySet().toArray(rowColKeyArr);
        Arrays.sort(rowColKeyArr);
        for (int i = 0; i < size; i++) {
            RowColKey rowColKey = rowColKeyArr[i];
            Row row = sheet.getRow(rowColKey.getRowIndex());
            if (row == null) {
                row = sheet.createRow(rowColKey.getRowIndex());
            }
            Cell cell = row.getCell(rowColKey.getColumnIndex());
            if (cell == null) {
                cell = row.createCell(rowColKey.getColumnIndex());
            }
            this._sharedCellsByRowCol.get(rowColKey).copyValue(cell);
        }
    }

    public int getSheetIndex(EvaluationWorkbook evaluationWorkbook) {
        return evaluationWorkbook.getSheetIndex(this._masterSheet);
    }

    public void clearAllCachedResultValues() {
        this._masterSheet.clearAllCachedResultValues();
    }

    private static final class RowColKey implements Comparable<RowColKey> {
        private final int _columnIndex;
        private final int _rowIndex;

        public RowColKey(int i, int i2) {
            this._rowIndex = i;
            this._columnIndex = i2;
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof RowColKey)) {
                return false;
            }
            RowColKey rowColKey = (RowColKey) obj;
            if (this._rowIndex == rowColKey._rowIndex && this._columnIndex == rowColKey._columnIndex) {
                return true;
            }
            return false;
        }

        public int hashCode() {
            return this._columnIndex ^ this._rowIndex;
        }

        public int compareTo(RowColKey rowColKey) {
            int i = this._rowIndex - rowColKey._rowIndex;
            if (i != 0) {
                return i;
            }
            return this._columnIndex - rowColKey._columnIndex;
        }

        public int getRowIndex() {
            return this._rowIndex;
        }

        public int getColumnIndex() {
            return this._columnIndex;
        }
    }
}
