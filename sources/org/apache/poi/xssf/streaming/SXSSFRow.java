package org.apache.poi.xssf.streaming;

import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.SortedMap;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.TreeMap;
import org.apache.poi.ss.SpreadsheetVersion;
import org.apache.poi.ss.formula.eval.NotImplementedException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.util.Internal;
import org.apache.poi.util.NotImplemented;

public class SXSSFRow implements Row, Comparable<SXSSFRow> {
    private static final Boolean UNDEFINED = null;
    /* access modifiers changed from: private */
    public final SortedMap<Integer, SXSSFCell> _cells = new TreeMap();
    private Boolean _collapsed;
    private short _height = -1;
    private Boolean _hidden;
    private int _outlineLevel;
    private int _rowNum;
    private final SXSSFSheet _sheet;
    private short _style = -1;
    private boolean _zHeight;

    public SXSSFRow(SXSSFSheet sXSSFSheet) {
        Boolean bool = UNDEFINED;
        this._hidden = bool;
        this._collapsed = bool;
        this._sheet = sXSSFSheet;
    }

    public Iterator<Cell> allCellsIterator() {
        return new CellIterator();
    }

    public Spliterator<Cell> allCellsSpliterator() {
        return Spliterators.spliterator(allCellsIterator(), (long) getLastCellNum(), 0);
    }

    public boolean hasCustomHeight() {
        return this._height != -1;
    }

    public int getOutlineLevel() {
        return this._outlineLevel;
    }

    /* access modifiers changed from: package-private */
    public void setOutlineLevel(int i) {
        this._outlineLevel = i;
    }

    public Boolean getHidden() {
        return this._hidden;
    }

    public void setHidden(Boolean bool) {
        this._hidden = bool;
    }

    public Boolean getCollapsed() {
        return this._collapsed;
    }

    public void setCollapsed(Boolean bool) {
        this._collapsed = bool;
    }

    public SXSSFCell createCell(int i) {
        return createCell(i, CellType.BLANK);
    }

    public SXSSFCell createCell(int i, CellType cellType) {
        checkBounds(i);
        SXSSFCell sXSSFCell = new SXSSFCell(this, cellType);
        this._cells.put(Integer.valueOf(i), sXSSFCell);
        return sXSSFCell;
    }

    private static void checkBounds(int i) {
        SpreadsheetVersion spreadsheetVersion = SpreadsheetVersion.EXCEL2007;
        int lastColumnIndex = SpreadsheetVersion.EXCEL2007.getLastColumnIndex();
        if (i < 0 || i > lastColumnIndex) {
            throw new IllegalArgumentException("Invalid column index (" + i + ").  Allowable column range for " + spreadsheetVersion.name() + " is (0.." + lastColumnIndex + ") or ('A'..'" + spreadsheetVersion.getLastColumnName() + "')");
        }
    }

    public void removeCell(Cell cell) {
        this._cells.remove(Integer.valueOf(getCellIndex((SXSSFCell) cell)));
    }

    /* access modifiers changed from: package-private */
    public int getCellIndex(SXSSFCell sXSSFCell) {
        for (Map.Entry next : this._cells.entrySet()) {
            if (next.getValue() == sXSSFCell) {
                return ((Integer) next.getKey()).intValue();
            }
        }
        return -1;
    }

    public void setRowNum(int i) {
        this._rowNum = i;
        this._sheet.changeRowNum(this, i);
    }

    public int getRowNum() {
        return this._rowNum;
    }

    public SXSSFCell getCell(int i) {
        return getCell(i, this._sheet.getWorkbook().getMissingCellPolicy());
    }

    public SXSSFCell getCell(int i, Row.MissingCellPolicy missingCellPolicy) {
        checkBounds(i);
        SXSSFCell sXSSFCell = (SXSSFCell) this._cells.get(Integer.valueOf(i));
        int i2 = AnonymousClass1.$SwitchMap$org$apache$poi$ss$usermodel$Row$MissingCellPolicy[missingCellPolicy.ordinal()];
        boolean z = true;
        if (i2 == 1) {
            return sXSSFCell;
        }
        if (i2 == 2) {
            if (sXSSFCell == null || sXSSFCell.getCellType() != CellType.BLANK) {
                z = false;
            }
            if (z) {
                return null;
            }
            return sXSSFCell;
        } else if (i2 == 3) {
            return sXSSFCell == null ? createCell(i, CellType.BLANK) : sXSSFCell;
        } else {
            throw new IllegalArgumentException("Illegal policy " + missingCellPolicy);
        }
    }

    /* renamed from: org.apache.poi.xssf.streaming.SXSSFRow$1  reason: invalid class name */
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
            throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.xssf.streaming.SXSSFRow.AnonymousClass1.<clinit>():void");
        }
    }

    public short getFirstCellNum() {
        try {
            return this._cells.firstKey().shortValue();
        } catch (NoSuchElementException unused) {
            return -1;
        }
    }

    public short getLastCellNum() {
        if (this._cells.isEmpty()) {
            return -1;
        }
        return (short) (this._cells.lastKey().intValue() + 1);
    }

    public int getPhysicalNumberOfCells() {
        return this._cells.size();
    }

    public void setHeight(short s) {
        this._height = s;
    }

    public void setZeroHeight(boolean z) {
        this._zHeight = z;
    }

    public boolean getZeroHeight() {
        return this._zHeight;
    }

    public void setHeightInPoints(float f) {
        if (f == -1.0f) {
            this._height = -1;
        } else {
            this._height = (short) ((int) (f * 20.0f));
        }
    }

    public short getHeight() {
        short s = this._height;
        return (short) ((int) (s == -1 ? getSheet().getDefaultRowHeightInPoints() * 20.0f : (float) s));
    }

    public float getHeightInPoints() {
        short s = this._height;
        return (float) (s == -1 ? (double) getSheet().getDefaultRowHeightInPoints() : ((double) s) / 20.0d);
    }

    public boolean isFormatted() {
        return this._style > -1;
    }

    public CellStyle getRowStyle() {
        if (!isFormatted()) {
            return null;
        }
        return getSheet().getWorkbook().getCellStyleAt(this._style);
    }

    /* access modifiers changed from: package-private */
    @Internal
    public int getRowStyleIndex() {
        return this._style;
    }

    public void setRowStyle(CellStyle cellStyle) {
        if (cellStyle == null) {
            this._style = -1;
        } else {
            this._style = cellStyle.getIndex();
        }
    }

    public Iterator<Cell> cellIterator() {
        return new FilledCellIterator();
    }

    public Spliterator<Cell> spliterator() {
        return this._cells.values().spliterator();
    }

    public SXSSFSheet getSheet() {
        return this._sheet;
    }

    /* access modifiers changed from: package-private */
    public void setRowNumWithoutUpdatingSheet(int i) {
        this._rowNum = i;
    }

    public class FilledCellIterator implements Iterator<Cell> {
        private final Iterator<SXSSFCell> iter;

        public FilledCellIterator() {
            this.iter = SXSSFRow.this._cells.values().iterator();
        }

        public boolean hasNext() {
            return this.iter.hasNext();
        }

        public Cell next() throws NoSuchElementException {
            return this.iter.next();
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    public class CellIterator implements Iterator<Cell> {
        final int maxColumn;
        int pos;

        public CellIterator() {
            this.maxColumn = SXSSFRow.this.getLastCellNum();
        }

        public boolean hasNext() {
            return this.pos < this.maxColumn;
        }

        public Cell next() throws NoSuchElementException {
            if (hasNext()) {
                SortedMap access$000 = SXSSFRow.this._cells;
                int i = this.pos;
                this.pos = i + 1;
                return (Cell) access$000.get(Integer.valueOf(i));
            }
            throw new NoSuchElementException();
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    public int compareTo(SXSSFRow sXSSFRow) {
        if (getSheet() == sXSSFRow.getSheet()) {
            return Integer.compare(getRowNum(), sXSSFRow.getRowNum());
        }
        throw new IllegalArgumentException("The compared rows must belong to the same sheet");
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof SXSSFRow)) {
            return false;
        }
        SXSSFRow sXSSFRow = (SXSSFRow) obj;
        if (getRowNum() == sXSSFRow.getRowNum() && getSheet() == sXSSFRow.getSheet()) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return this._cells.hashCode();
    }

    @NotImplemented
    public void shiftCellsRight(int i, int i2, int i3) {
        throw new NotImplementedException("shiftCellsRight");
    }

    @NotImplemented
    public void shiftCellsLeft(int i, int i2, int i3) {
        throw new NotImplementedException("shiftCellsLeft");
    }
}
