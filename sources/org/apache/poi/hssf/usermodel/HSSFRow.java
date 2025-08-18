package org.apache.poi.hssf.usermodel;

import java.util.HashSet;
import java.util.Iterator;
import java.util.NoSuchElementException;
import org.apache.poi.hssf.record.CellValueRecordInterface;
import org.apache.poi.hssf.record.RowRecord;
import org.apache.poi.hssf.usermodel.helpers.HSSFRowShifter;
import org.apache.poi.ss.SpreadsheetVersion;
import org.apache.poi.ss.formula.FormulaShifter;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellCopyContext;
import org.apache.poi.ss.usermodel.CellCopyPolicy;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.helpers.RowShifter;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.CellUtil;
import org.apache.poi.util.Configurator;

public final class HSSFRow implements Row, Comparable<HSSFRow> {
    public static final int INITIAL_CAPACITY = Configurator.getIntValue("HSSFRow.ColInitialCapacity", 5);
    private final HSSFWorkbook book;
    /* access modifiers changed from: private */
    public HSSFCell[] cells;
    private final RowRecord row;
    private int rowNum;
    private final HSSFSheet sheet;

    HSSFRow(HSSFWorkbook hSSFWorkbook, HSSFSheet hSSFSheet, int i) {
        this(hSSFWorkbook, hSSFSheet, new RowRecord(i));
    }

    HSSFRow(HSSFWorkbook hSSFWorkbook, HSSFSheet hSSFSheet, RowRecord rowRecord) {
        int i;
        this.book = hSSFWorkbook;
        this.sheet = hSSFSheet;
        this.row = rowRecord;
        setRowNum(rowRecord.getRowNumber());
        if (rowRecord.getLastCol() < 0 || (i = INITIAL_CAPACITY) < 0) {
            throw new IllegalArgumentException("Had invalid column counts: " + rowRecord.getLastCol() + " and " + INITIAL_CAPACITY);
        }
        this.cells = new HSSFCell[(rowRecord.getLastCol() + i)];
        rowRecord.setEmpty();
    }

    public HSSFCell createCell(int i) {
        return createCell(i, CellType.BLANK);
    }

    public HSSFCell createCell(int i, CellType cellType) {
        short s = (short) i;
        if (i > 32767) {
            s = (short) (65535 - i);
        }
        HSSFCell hSSFCell = new HSSFCell(this.book, this.sheet, getRowNum(), s, cellType);
        addCell(hSSFCell);
        this.sheet.getSheet().addValueRecord(getRowNum(), hSSFCell.getCellValueRecord());
        return hSSFCell;
    }

    public void removeCell(Cell cell) {
        if (cell != null) {
            removeCell((HSSFCell) cell, true);
            return;
        }
        throw new IllegalArgumentException("cell must not be null");
    }

    private void removeCell(HSSFCell hSSFCell, boolean z) {
        int columnIndex = hSSFCell.getColumnIndex();
        if (columnIndex >= 0) {
            HSSFCell[] hSSFCellArr = this.cells;
            if (columnIndex >= hSSFCellArr.length || hSSFCell != hSSFCellArr[columnIndex]) {
                throw new RuntimeException("Specified cell is not from this row");
            }
            if (hSSFCell.isPartOfArrayFormulaGroup()) {
                hSSFCell.tryToDeleteArrayFormula((String) null);
            }
            this.cells[columnIndex] = null;
            if (z) {
                this.sheet.getSheet().removeValueRecord(getRowNum(), hSSFCell.getCellValueRecord());
            }
            if (hSSFCell.getColumnIndex() + 1 == this.row.getLastCol()) {
                RowRecord rowRecord = this.row;
                rowRecord.setLastCol(calculateNewLastCellPlusOne(rowRecord.getLastCol()));
            }
            if (hSSFCell.getColumnIndex() == this.row.getFirstCol()) {
                RowRecord rowRecord2 = this.row;
                rowRecord2.setFirstCol(calculateNewFirstCell(rowRecord2.getFirstCol()));
                return;
            }
            return;
        }
        throw new RuntimeException("Negative cell indexes not allowed");
    }

    /* access modifiers changed from: protected */
    public void removeAllCells() {
        for (HSSFCell hSSFCell : this.cells) {
            if (hSSFCell != null) {
                removeCell(hSSFCell, true);
            }
        }
        this.cells = new HSSFCell[INITIAL_CAPACITY];
    }

    /* access modifiers changed from: package-private */
    public HSSFCell createCellFromRecord(CellValueRecordInterface cellValueRecordInterface) {
        HSSFCell hSSFCell = new HSSFCell(this.book, this.sheet, cellValueRecordInterface);
        addCell(hSSFCell);
        short column = cellValueRecordInterface.getColumn();
        if (this.row.isEmpty()) {
            this.row.setFirstCol(column);
            this.row.setLastCol(column + 1);
        } else if (column < this.row.getFirstCol()) {
            this.row.setFirstCol(column);
        } else if (column > this.row.getLastCol()) {
            this.row.setLastCol(column + 1);
        }
        return hSSFCell;
    }

    public void setRowNum(int i) {
        int lastRowIndex = SpreadsheetVersion.EXCEL97.getLastRowIndex();
        if (i < 0 || i > lastRowIndex) {
            throw new IllegalArgumentException("Invalid row number (" + i + ") outside allowable range (0.." + lastRowIndex + ")");
        }
        this.rowNum = i;
        RowRecord rowRecord = this.row;
        if (rowRecord != null) {
            rowRecord.setRowNumber(i);
        }
    }

    public int getRowNum() {
        return this.rowNum;
    }

    public HSSFSheet getSheet() {
        return this.sheet;
    }

    public int getOutlineLevel() {
        return this.row.getOutlineLevel();
    }

    public void moveCell(HSSFCell hSSFCell, short s) {
        HSSFCell[] hSSFCellArr = this.cells;
        if (hSSFCellArr.length > s && hSSFCellArr[s] != null) {
            throw new IllegalArgumentException("Asked to move cell to column " + s + " but there's already a cell there");
        } else if (hSSFCellArr[hSSFCell.getColumnIndex()].equals(hSSFCell)) {
            removeCell(hSSFCell, false);
            hSSFCell.updateCellNum(s);
            addCell(hSSFCell);
        } else {
            throw new IllegalArgumentException("Asked to move a cell, but it didn't belong to our row");
        }
    }

    private void addCell(HSSFCell hSSFCell) {
        int columnIndex = hSSFCell.getColumnIndex();
        HSSFCell[] hSSFCellArr = this.cells;
        if (columnIndex >= hSSFCellArr.length) {
            int length = ((hSSFCellArr.length * 3) / 2) + 1;
            if (length < columnIndex + 1) {
                length = INITIAL_CAPACITY + columnIndex;
            }
            HSSFCell[] hSSFCellArr2 = new HSSFCell[length];
            this.cells = hSSFCellArr2;
            System.arraycopy(hSSFCellArr, 0, hSSFCellArr2, 0, hSSFCellArr.length);
        }
        this.cells[columnIndex] = hSSFCell;
        if (this.row.isEmpty() || columnIndex < this.row.getFirstCol()) {
            this.row.setFirstCol((short) columnIndex);
        }
        if (this.row.isEmpty() || columnIndex >= this.row.getLastCol()) {
            this.row.setLastCol((short) (columnIndex + 1));
        }
    }

    private HSSFCell retrieveCell(int i) {
        if (i < 0) {
            return null;
        }
        HSSFCell[] hSSFCellArr = this.cells;
        if (i >= hSSFCellArr.length) {
            return null;
        }
        return hSSFCellArr[i];
    }

    public HSSFCell getCell(int i) {
        return getCell(i, this.book.getMissingCellPolicy());
    }

    /* renamed from: org.apache.poi.hssf.usermodel.HSSFRow$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$org$apache$poi$ss$usermodel$Row$MissingCellPolicy;

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|(3:5|6|8)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        static {
            /*
                org.apache.poi.ss.usermodel.Row$MissingCellPolicy[] r0 = org.apache.poi.ss.usermodel.Row.MissingCellPolicy.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$org$apache$poi$ss$usermodel$Row$MissingCellPolicy = r0
                org.apache.poi.ss.usermodel.Row$MissingCellPolicy r1 = org.apache.poi.ss.usermodel.Row.MissingCellPolicy.RETURN_NULL_AND_BLANK     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$org$apache$poi$ss$usermodel$Row$MissingCellPolicy     // Catch:{ NoSuchFieldError -> 0x001d }
                org.apache.poi.ss.usermodel.Row$MissingCellPolicy r1 = org.apache.poi.ss.usermodel.Row.MissingCellPolicy.RETURN_BLANK_AS_NULL     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$org$apache$poi$ss$usermodel$Row$MissingCellPolicy     // Catch:{ NoSuchFieldError -> 0x0028 }
                org.apache.poi.ss.usermodel.Row$MissingCellPolicy r1 = org.apache.poi.ss.usermodel.Row.MissingCellPolicy.CREATE_NULL_AS_BLANK     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.hssf.usermodel.HSSFRow.AnonymousClass1.<clinit>():void");
        }
    }

    public HSSFCell getCell(int i, Row.MissingCellPolicy missingCellPolicy) {
        HSSFCell retrieveCell = retrieveCell(i);
        int i2 = AnonymousClass1.$SwitchMap$org$apache$poi$ss$usermodel$Row$MissingCellPolicy[missingCellPolicy.ordinal()];
        boolean z = true;
        if (i2 == 1) {
            return retrieveCell;
        }
        if (i2 == 2) {
            if (retrieveCell == null || retrieveCell.getCellType() != CellType.BLANK) {
                z = false;
            }
            if (z) {
                return null;
            }
            return retrieveCell;
        } else if (i2 == 3) {
            return retrieveCell == null ? createCell(i, CellType.BLANK) : retrieveCell;
        } else {
            throw new IllegalArgumentException("Illegal policy " + missingCellPolicy);
        }
    }

    public short getFirstCellNum() {
        if (this.row.isEmpty()) {
            return -1;
        }
        return (short) this.row.getFirstCol();
    }

    public short getLastCellNum() {
        if (this.row.isEmpty()) {
            return -1;
        }
        return (short) this.row.getLastCol();
    }

    public int getPhysicalNumberOfCells() {
        int i = 0;
        for (HSSFCell hSSFCell : this.cells) {
            if (hSSFCell != null) {
                i++;
            }
        }
        return i;
    }

    public void setHeight(short s) {
        if (s == -1) {
            this.row.setHeight(-32513);
            this.row.setBadFontHeight(false);
            return;
        }
        this.row.setBadFontHeight(true);
        this.row.setHeight(s);
    }

    public void setZeroHeight(boolean z) {
        this.row.setZeroHeight(z);
    }

    public boolean getZeroHeight() {
        return this.row.getZeroHeight();
    }

    public void setHeightInPoints(float f) {
        if (f == -1.0f) {
            this.row.setHeight(-32513);
            this.row.setBadFontHeight(false);
            return;
        }
        this.row.setBadFontHeight(true);
        this.row.setHeight((short) ((int) (f * 20.0f)));
    }

    public short getHeight() {
        short height = this.row.getHeight();
        return (32768 & height) != 0 ? this.sheet.getSheet().getDefaultRowHeight() : (short) (height & Short.MAX_VALUE);
    }

    public float getHeightInPoints() {
        return ((float) getHeight()) / 20.0f;
    }

    /* access modifiers changed from: protected */
    public RowRecord getRowRecord() {
        return this.row;
    }

    private int calculateNewLastCellPlusOne(int i) {
        int i2 = i - 1;
        HSSFCell retrieveCell = retrieveCell(i2);
        while (retrieveCell == null) {
            if (i2 < 0) {
                return 0;
            }
            i2--;
            retrieveCell = retrieveCell(i2);
        }
        return i2 + 1;
    }

    private int calculateNewFirstCell(int i) {
        int i2 = i + 1;
        HSSFCell retrieveCell = retrieveCell(i2);
        while (retrieveCell == null) {
            if (i2 <= this.cells.length) {
                return 0;
            }
            i2++;
            retrieveCell = retrieveCell(i2);
        }
        return i2;
    }

    public boolean isFormatted() {
        return this.row.getFormatted();
    }

    public HSSFCellStyle getRowStyle() {
        if (!isFormatted()) {
            return null;
        }
        short xFIndex = this.row.getXFIndex();
        return new HSSFCellStyle(xFIndex, this.book.getWorkbook().getExFormatAt(xFIndex), this.book);
    }

    public void setRowStyle(HSSFCellStyle hSSFCellStyle) {
        this.row.setFormatted(true);
        this.row.setXFIndex(hSSFCellStyle.getIndex());
    }

    public void setRowStyle(CellStyle cellStyle) {
        setRowStyle((HSSFCellStyle) cellStyle);
    }

    public Iterator<Cell> cellIterator() {
        return new CellIterator();
    }

    private class CellIterator implements Iterator<Cell> {
        int nextId = -1;
        int thisId = -1;

        public CellIterator() {
            findNext();
        }

        public boolean hasNext() {
            return this.nextId < HSSFRow.this.cells.length;
        }

        public Cell next() {
            if (hasNext()) {
                HSSFCell[] access$000 = HSSFRow.this.cells;
                int i = this.nextId;
                HSSFCell hSSFCell = access$000[i];
                this.thisId = i;
                findNext();
                return hSSFCell;
            }
            throw new NoSuchElementException("At last element");
        }

        public void remove() {
            if (this.thisId != -1) {
                HSSFRow.this.cells[this.thisId] = null;
                return;
            }
            throw new IllegalStateException("remove() called before next()");
        }

        private void findNext() {
            int i = this.nextId;
            do {
                i++;
                if (i >= HSSFRow.this.cells.length || HSSFRow.this.cells[i] != null) {
                    this.nextId = i;
                }
                i++;
                break;
            } while (HSSFRow.this.cells[i] != null);
            this.nextId = i;
        }
    }

    public int compareTo(HSSFRow hSSFRow) {
        if (getSheet() == hSSFRow.getSheet()) {
            return Integer.compare(getRowNum(), hSSFRow.getRowNum());
        }
        throw new IllegalArgumentException("The compared rows must belong to the same sheet");
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof HSSFRow)) {
            return false;
        }
        HSSFRow hSSFRow = (HSSFRow) obj;
        if (getRowNum() == hSSFRow.getRowNum() && getSheet() == hSSFRow.getSheet()) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return this.row.hashCode();
    }

    public void shiftCellsRight(int i, int i2, int i3) {
        RowShifter.validateShiftParameters(i, i2, i3);
        int i4 = i2 + i3 + 1;
        if (i4 > this.cells.length) {
            extend(i4);
        }
        while (i2 >= i) {
            HSSFCell cell = getCell(i2);
            int i5 = i2 + i3;
            this.cells[i5] = null;
            if (cell != null) {
                moveCell(cell, (short) i5);
            }
            i2--;
        }
        for (int i6 = i; i6 <= (i + i3) - 1; i6++) {
            this.cells[i6] = null;
        }
    }

    private void extend(int i) {
        HSSFCell[] hSSFCellArr = (HSSFCell[]) this.cells.clone();
        HSSFCell[] hSSFCellArr2 = new HSSFCell[i];
        this.cells = hSSFCellArr2;
        System.arraycopy(hSSFCellArr, 0, hSSFCellArr2, 0, hSSFCellArr.length);
    }

    public void shiftCellsLeft(int i, int i2, int i3) {
        RowShifter.validateShiftLeftParameters(i, i2, i3);
        while (i <= i2) {
            HSSFCell cell = getCell(i);
            if (cell != null) {
                int i4 = i - i3;
                this.cells[i4] = null;
                moveCell(cell, (short) i4);
            } else {
                this.cells[i - i3] = null;
            }
            i++;
        }
        int i5 = i2 - i3;
        while (true) {
            i5++;
            if (i5 <= i2) {
                this.cells[i5] = null;
            } else {
                return;
            }
        }
    }

    public void copyRowFrom(Row row2, CellCopyPolicy cellCopyPolicy) {
        copyRowFrom(row2, cellCopyPolicy, (CellCopyContext) null);
    }

    public void copyRowFrom(Row row2, CellCopyPolicy cellCopyPolicy, CellCopyContext cellCopyContext) {
        if (row2 == null) {
            Iterator<Cell> it = iterator();
            while (it.hasNext()) {
                CellUtil.copyCell((Cell) null, it.next(), cellCopyPolicy, cellCopyContext);
            }
            if (cellCopyPolicy.isCopyMergedRegions()) {
                int rowNum2 = getRowNum();
                HashSet hashSet = new HashSet();
                int i = 0;
                for (CellRangeAddress next : getSheet().getMergedRegions()) {
                    if (rowNum2 == next.getFirstRow() && rowNum2 == next.getLastRow()) {
                        hashSet.add(Integer.valueOf(i));
                    }
                    i++;
                }
                getSheet().removeMergedRegions(hashSet);
            }
            if (cellCopyPolicy.isCopyRowHeight()) {
                setHeight(-1);
                return;
            }
            return;
        }
        for (Cell next2 : row2) {
            CellUtil.copyCell(next2, createCell(next2.getColumnIndex()), cellCopyPolicy, cellCopyContext);
        }
        int sheetIndex = this.sheet.getWorkbook().getSheetIndex((Sheet) this.sheet);
        String sheetName = this.sheet.getWorkbook().getSheetName(sheetIndex);
        int rowNum3 = row2.getRowNum();
        int rowNum4 = getRowNum();
        new HSSFRowShifter(this.sheet).updateRowFormulas(this, FormulaShifter.createForRowCopy(sheetIndex, sheetName, rowNum3, rowNum3, rowNum4 - rowNum3, SpreadsheetVersion.EXCEL2007));
        if (cellCopyPolicy.isCopyMergedRegions()) {
            for (CellRangeAddress next3 : row2.getSheet().getMergedRegions()) {
                if (rowNum3 == next3.getFirstRow() && rowNum3 == next3.getLastRow()) {
                    CellRangeAddress copy = next3.copy();
                    copy.setFirstRow(rowNum4);
                    copy.setLastRow(rowNum4);
                    getSheet().addMergedRegion(copy);
                }
            }
        }
        if (cellCopyPolicy.isCopyRowHeight()) {
            setHeight(row2.getHeight());
        }
    }
}
