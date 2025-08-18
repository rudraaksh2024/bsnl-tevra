package org.apache.poi.ss.formula;

import java.util.HashSet;
import java.util.Set;
import org.apache.poi.ss.formula.eval.ValueEval;

final class CellEvaluationFrame {
    private final FormulaCellCacheEntry _cce;
    private final Set<CellCacheEntry> _sensitiveInputCells = new HashSet();
    private FormulaUsedBlankCellSet _usedBlankCellGroup;

    public CellEvaluationFrame(FormulaCellCacheEntry formulaCellCacheEntry) {
        this._cce = formulaCellCacheEntry;
    }

    public CellCacheEntry getCCE() {
        return this._cce;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(64);
        sb.append(getClass().getName()).append(" []");
        return sb.toString();
    }

    public void addSensitiveInputCell(CellCacheEntry cellCacheEntry) {
        this._sensitiveInputCells.add(cellCacheEntry);
    }

    private CellCacheEntry[] getSensitiveInputCells() {
        int size = this._sensitiveInputCells.size();
        if (size < 1) {
            return CellCacheEntry.EMPTY_ARRAY;
        }
        CellCacheEntry[] cellCacheEntryArr = new CellCacheEntry[size];
        this._sensitiveInputCells.toArray(cellCacheEntryArr);
        return cellCacheEntryArr;
    }

    public void addUsedBlankCell(EvaluationWorkbook evaluationWorkbook, int i, int i2, int i3, int i4) {
        if (this._usedBlankCellGroup == null) {
            this._usedBlankCellGroup = new FormulaUsedBlankCellSet();
        }
        this._usedBlankCellGroup.addCell(evaluationWorkbook, i, i2, i3, i4);
    }

    public void updateFormulaResult(ValueEval valueEval) {
        this._cce.updateFormulaResult(valueEval, getSensitiveInputCells(), this._usedBlankCellGroup);
    }
}
