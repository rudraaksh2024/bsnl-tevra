package org.apache.poi.ss.formula.eval;

import org.apache.poi.ss.formula.SheetRange;
import org.apache.poi.ss.formula.ptg.AreaI;

public abstract class AreaEvalBase implements AreaEval {
    private final int _firstColumn;
    private final int _firstRow;
    private final int _firstSheet;
    private final int _lastColumn;
    private final int _lastRow;
    private final int _lastSheet;
    private final int _nColumns;
    private final int _nRows;

    public abstract ValueEval getRelativeValue(int i, int i2);

    public abstract ValueEval getRelativeValue(int i, int i2, int i3);

    public boolean isRowHidden(int i) {
        return false;
    }

    public boolean isSubTotal(int i, int i2) {
        return false;
    }

    protected AreaEvalBase(SheetRange sheetRange, int i, int i2, int i3, int i4) {
        this._firstColumn = i2;
        this._firstRow = i;
        this._lastColumn = i4;
        this._lastRow = i3;
        this._nColumns = (i4 - i2) + 1;
        this._nRows = (i3 - i) + 1;
        if (sheetRange != null) {
            this._firstSheet = sheetRange.getFirstSheetIndex();
            this._lastSheet = sheetRange.getLastSheetIndex();
            return;
        }
        this._firstSheet = -1;
        this._lastSheet = -1;
    }

    protected AreaEvalBase(int i, int i2, int i3, int i4) {
        this((SheetRange) null, i, i2, i3, i4);
    }

    protected AreaEvalBase(AreaI areaI) {
        this(areaI, (SheetRange) null);
    }

    protected AreaEvalBase(AreaI areaI, SheetRange sheetRange) {
        this(sheetRange, areaI.getFirstRow(), areaI.getFirstColumn(), areaI.getLastRow(), areaI.getLastColumn());
    }

    public final int getFirstColumn() {
        return this._firstColumn;
    }

    public final int getFirstRow() {
        return this._firstRow;
    }

    public final int getLastColumn() {
        return this._lastColumn;
    }

    public final int getLastRow() {
        return this._lastRow;
    }

    public int getFirstSheetIndex() {
        return this._firstSheet;
    }

    public int getLastSheetIndex() {
        return this._lastSheet;
    }

    public final ValueEval getAbsoluteValue(int i, int i2) {
        int i3 = i - this._firstRow;
        int i4 = i2 - this._firstColumn;
        if (i3 < 0 || i3 >= this._nRows) {
            throw new IllegalArgumentException("Specified row index (" + i + ") is outside the allowed range (" + this._firstRow + ".." + this._lastRow + ")");
        } else if (i4 >= 0 && i4 < this._nColumns) {
            return getRelativeValue(i3, i4);
        } else {
            throw new IllegalArgumentException("Specified column index (" + i2 + ") is outside the allowed range (" + this._firstColumn + ".." + i2 + ")");
        }
    }

    public final boolean contains(int i, int i2) {
        return this._firstRow <= i && this._lastRow >= i && this._firstColumn <= i2 && this._lastColumn >= i2;
    }

    public final boolean containsRow(int i) {
        return this._firstRow <= i && this._lastRow >= i;
    }

    public final boolean containsColumn(int i) {
        return this._firstColumn <= i && this._lastColumn >= i;
    }

    public final boolean isColumn() {
        return this._firstColumn == this._lastColumn;
    }

    public final boolean isRow() {
        return this._firstRow == this._lastRow;
    }

    public int getHeight() {
        return (this._lastRow - this._firstRow) + 1;
    }

    public final ValueEval getValue(int i, int i2) {
        return getRelativeValue(i, i2);
    }

    public final ValueEval getValue(int i, int i2, int i3) {
        return getRelativeValue(i, i2, i3);
    }

    public int getWidth() {
        return (this._lastColumn - this._firstColumn) + 1;
    }
}
