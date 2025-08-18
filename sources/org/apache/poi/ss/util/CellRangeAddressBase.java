package org.apache.poi.ss.util;

import java.util.EnumSet;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.function.Supplier;
import org.apache.logging.log4j.message.ParameterizedMessage;
import org.apache.poi.common.Duplicatable;
import org.apache.poi.common.usermodel.GenericRecord;
import org.apache.poi.ss.SpreadsheetVersion;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.util.GenericRecordUtil;

public abstract class CellRangeAddressBase implements Iterable<CellAddress>, Duplicatable, GenericRecord {
    private int _firstCol;
    private int _firstRow;
    private int _lastCol;
    private int _lastRow;

    public enum CellPosition {
        TOP,
        BOTTOM,
        LEFT,
        RIGHT,
        INSIDE
    }

    protected CellRangeAddressBase(int i, int i2, int i3, int i4) {
        this._firstRow = i;
        this._lastRow = i2;
        this._firstCol = i3;
        this._lastCol = i4;
    }

    public void validate(SpreadsheetVersion spreadsheetVersion) {
        validateRow(this._firstRow, spreadsheetVersion);
        validateRow(this._lastRow, spreadsheetVersion);
        validateColumn(this._firstCol, spreadsheetVersion);
        validateColumn(this._lastCol, spreadsheetVersion);
    }

    private static void validateRow(int i, SpreadsheetVersion spreadsheetVersion) {
        int lastRowIndex = spreadsheetVersion.getLastRowIndex();
        if (i > lastRowIndex) {
            throw new IllegalArgumentException("Maximum row number is " + lastRowIndex);
        } else if (i < 0) {
            throw new IllegalArgumentException("Minumum row number is 0");
        }
    }

    private static void validateColumn(int i, SpreadsheetVersion spreadsheetVersion) {
        int lastColumnIndex = spreadsheetVersion.getLastColumnIndex();
        if (i > lastColumnIndex) {
            throw new IllegalArgumentException("Maximum column number is " + lastColumnIndex);
        } else if (i < 0) {
            throw new IllegalArgumentException("Minimum column number is 0");
        }
    }

    public final boolean isFullColumnRange() {
        return (this._firstRow == 0 && this._lastRow == SpreadsheetVersion.EXCEL97.getLastRowIndex()) || (this._firstRow == -1 && this._lastRow == -1);
    }

    public final boolean isFullRowRange() {
        return (this._firstCol == 0 && this._lastCol == SpreadsheetVersion.EXCEL97.getLastColumnIndex()) || (this._firstCol == -1 && this._lastCol == -1);
    }

    public final int getFirstColumn() {
        return this._firstCol;
    }

    public final int getFirstRow() {
        return this._firstRow;
    }

    public final int getLastColumn() {
        return this._lastCol;
    }

    public final int getLastRow() {
        return this._lastRow;
    }

    public boolean isInRange(int i, int i2) {
        return this._firstRow <= i && i <= this._lastRow && this._firstCol <= i2 && i2 <= this._lastCol;
    }

    public boolean isInRange(CellReference cellReference) {
        return isInRange(cellReference.getRow(), cellReference.getCol());
    }

    public boolean isInRange(CellAddress cellAddress) {
        return isInRange(cellAddress.getRow(), cellAddress.getColumn());
    }

    public boolean isInRange(Cell cell) {
        return isInRange(cell.getRowIndex(), cell.getColumnIndex());
    }

    public boolean containsRow(int i) {
        return this._firstRow <= i && i <= this._lastRow;
    }

    public boolean containsColumn(int i) {
        return this._firstCol <= i && i <= this._lastCol;
    }

    public boolean intersects(CellRangeAddressBase cellRangeAddressBase) {
        return this._firstRow <= cellRangeAddressBase._lastRow && this._firstCol <= cellRangeAddressBase._lastCol && cellRangeAddressBase._firstRow <= this._lastRow && cellRangeAddressBase._firstCol <= this._lastCol;
    }

    public Set<CellPosition> getPosition(int i, int i2) {
        EnumSet<E> noneOf = EnumSet.noneOf(CellPosition.class);
        if (i <= getFirstRow() || i >= getLastRow() || i2 <= getFirstColumn() || i2 >= getLastColumn()) {
            if (i == getFirstRow()) {
                noneOf.add(CellPosition.TOP);
            }
            if (i == getLastRow()) {
                noneOf.add(CellPosition.BOTTOM);
            }
            if (i2 == getFirstColumn()) {
                noneOf.add(CellPosition.LEFT);
            }
            if (i2 == getLastColumn()) {
                noneOf.add(CellPosition.RIGHT);
            }
            return noneOf;
        }
        noneOf.add(CellPosition.INSIDE);
        return noneOf;
    }

    public final void setFirstColumn(int i) {
        this._firstCol = i;
    }

    public final void setFirstRow(int i) {
        this._firstRow = i;
    }

    public final void setLastColumn(int i) {
        this._lastCol = i;
    }

    public final void setLastRow(int i) {
        this._lastRow = i;
    }

    public int getNumberOfCells() {
        return ((this._lastRow - this._firstRow) + 1) * ((this._lastCol - this._firstCol) + 1);
    }

    public Iterator<CellAddress> iterator() {
        return new RowMajorCellAddressIterator(this);
    }

    public Spliterator<CellAddress> spliterator() {
        return Spliterators.spliterator(iterator(), (long) getNumberOfCells(), 0);
    }

    private static class RowMajorCellAddressIterator implements Iterator<CellAddress> {
        private int c;
        private final int firstCol;
        private final int firstRow;
        private final int lastCol;
        private final int lastRow;
        private int r;

        public RowMajorCellAddressIterator(CellRangeAddressBase cellRangeAddressBase) {
            int firstRow2 = cellRangeAddressBase.getFirstRow();
            this.firstRow = firstRow2;
            this.r = firstRow2;
            int firstColumn = cellRangeAddressBase.getFirstColumn();
            this.firstCol = firstColumn;
            this.c = firstColumn;
            int lastRow2 = cellRangeAddressBase.getLastRow();
            this.lastRow = lastRow2;
            int lastColumn = cellRangeAddressBase.getLastColumn();
            this.lastCol = lastColumn;
            if (firstRow2 < 0) {
                throw new IllegalStateException("First row cannot be negative.");
            } else if (firstColumn < 0) {
                throw new IllegalStateException("First column cannot be negative.");
            } else if (firstRow2 > lastRow2) {
                throw new IllegalStateException("First row cannot be greater than last row.");
            } else if (firstColumn > lastColumn) {
                throw new IllegalStateException("First column cannot be greater than last column.");
            }
        }

        public boolean hasNext() {
            return this.r <= this.lastRow && this.c <= this.lastCol;
        }

        public CellAddress next() {
            if (hasNext()) {
                CellAddress cellAddress = new CellAddress(this.r, this.c);
                int i = this.c;
                if (i < this.lastCol) {
                    this.c = i + 1;
                } else {
                    this.c = this.firstCol;
                    this.r++;
                }
                return cellAddress;
            }
            throw new NoSuchElementException();
        }
    }

    public final String toString() {
        return getClass().getName() + " [" + new CellAddress(this._firstRow, this._firstCol).formatAsString() + ParameterizedMessage.ERROR_MSG_SEPARATOR + new CellAddress(this._lastRow, this._lastCol).formatAsString() + "]";
    }

    /* access modifiers changed from: protected */
    public int getMinRow() {
        return Math.min(this._firstRow, this._lastRow);
    }

    /* access modifiers changed from: protected */
    public int getMaxRow() {
        return Math.max(this._firstRow, this._lastRow);
    }

    /* access modifiers changed from: protected */
    public int getMinColumn() {
        return Math.min(this._firstCol, this._lastCol);
    }

    /* access modifiers changed from: protected */
    public int getMaxColumn() {
        return Math.max(this._firstCol, this._lastCol);
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof CellRangeAddressBase)) {
            return false;
        }
        CellRangeAddressBase cellRangeAddressBase = (CellRangeAddressBase) obj;
        if (getMinRow() == cellRangeAddressBase.getMinRow() && getMaxRow() == cellRangeAddressBase.getMaxRow() && getMinColumn() == cellRangeAddressBase.getMinColumn() && getMaxColumn() == cellRangeAddressBase.getMaxColumn()) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return getMinColumn() + (getMaxColumn() << 8) + (getMinRow() << 16) + (getMaxRow() << 24);
    }

    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("firstRow", new CellRangeAddressBase$$ExternalSyntheticLambda0(this), "firstCol", new CellRangeAddressBase$$ExternalSyntheticLambda1(this), "lastRow", new CellRangeAddressBase$$ExternalSyntheticLambda2(this), "lastCol", new CellRangeAddressBase$$ExternalSyntheticLambda3(this));
    }
}
