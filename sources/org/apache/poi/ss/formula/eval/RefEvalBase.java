package org.apache.poi.ss.formula.eval;

import org.apache.poi.ss.formula.SheetRange;

public abstract class RefEvalBase implements RefEval {
    private final int _columnIndex;
    private final int _firstSheetIndex;
    private final int _lastSheetIndex;
    private final int _rowIndex;

    protected RefEvalBase(SheetRange sheetRange, int i, int i2) {
        if (sheetRange != null) {
            this._firstSheetIndex = sheetRange.getFirstSheetIndex();
            this._lastSheetIndex = sheetRange.getLastSheetIndex();
            this._rowIndex = i;
            this._columnIndex = i2;
            return;
        }
        throw new IllegalArgumentException("sheetRange must not be null");
    }

    protected RefEvalBase(int i, int i2, int i3, int i4) {
        this._firstSheetIndex = i;
        this._lastSheetIndex = i2;
        this._rowIndex = i3;
        this._columnIndex = i4;
    }

    protected RefEvalBase(int i, int i2, int i3) {
        this(i, i, i2, i3);
    }

    public int getNumberOfSheets() {
        return (this._lastSheetIndex - this._firstSheetIndex) + 1;
    }

    public int getFirstSheetIndex() {
        return this._firstSheetIndex;
    }

    public int getLastSheetIndex() {
        return this._lastSheetIndex;
    }

    public final int getRow() {
        return this._rowIndex;
    }

    public final int getColumn() {
        return this._columnIndex;
    }
}
