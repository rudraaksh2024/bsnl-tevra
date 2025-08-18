package org.apache.poi.ss.util.cellwalk;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddress;

public class CellWalk {
    private final CellRangeAddress range;
    private final Sheet sheet;
    private boolean traverseEmptyCells = false;

    public CellWalk(Sheet sheet2, CellRangeAddress cellRangeAddress) {
        this.sheet = sheet2;
        this.range = cellRangeAddress;
    }

    public boolean isTraverseEmptyCells() {
        return this.traverseEmptyCells;
    }

    public void setTraverseEmptyCells(boolean z) {
        this.traverseEmptyCells = z;
    }

    public void traverse(CellHandler cellHandler) {
        int firstRow = this.range.getFirstRow();
        int lastRow = this.range.getLastRow();
        int firstColumn = this.range.getFirstColumn();
        int lastColumn = this.range.getLastColumn();
        int i = (lastColumn - firstColumn) + 1;
        SimpleCellWalkContext simpleCellWalkContext = new SimpleCellWalkContext();
        int unused = simpleCellWalkContext.rowNumber = firstRow;
        while (simpleCellWalkContext.rowNumber <= lastRow) {
            Row row = this.sheet.getRow(simpleCellWalkContext.rowNumber);
            if (row != null) {
                int unused2 = simpleCellWalkContext.colNumber = firstColumn;
                while (simpleCellWalkContext.colNumber <= lastColumn) {
                    Cell cell = row.getCell(simpleCellWalkContext.colNumber);
                    if (cell != null && (!isEmpty(cell) || this.traverseEmptyCells)) {
                        long unused3 = simpleCellWalkContext.ordinalNumber = Math.addExact(Math.multiplyExact((long) Math.subtractExact(simpleCellWalkContext.rowNumber, firstRow), (long) i), (long) ((simpleCellWalkContext.colNumber - firstColumn) + 1));
                        cellHandler.onCell(cell, simpleCellWalkContext);
                    }
                    SimpleCellWalkContext.access$204(simpleCellWalkContext);
                }
            }
            SimpleCellWalkContext.access$104(simpleCellWalkContext);
        }
    }

    private boolean isEmpty(Cell cell) {
        return cell.getCellType() == CellType.BLANK;
    }

    private static class SimpleCellWalkContext implements CellWalkContext {
        /* access modifiers changed from: private */
        public int colNumber;
        /* access modifiers changed from: private */
        public long ordinalNumber;
        /* access modifiers changed from: private */
        public int rowNumber;

        private SimpleCellWalkContext() {
        }

        static /* synthetic */ int access$104(SimpleCellWalkContext simpleCellWalkContext) {
            int i = simpleCellWalkContext.rowNumber + 1;
            simpleCellWalkContext.rowNumber = i;
            return i;
        }

        static /* synthetic */ int access$204(SimpleCellWalkContext simpleCellWalkContext) {
            int i = simpleCellWalkContext.colNumber + 1;
            simpleCellWalkContext.colNumber = i;
            return i;
        }

        public long getOrdinalNumber() {
            return this.ordinalNumber;
        }

        public int getRowNumber() {
            return this.rowNumber;
        }

        public int getColumnNumber() {
            return this.colNumber;
        }
    }
}
