package org.apache.poi.xssf.usermodel;

import java.util.HashSet;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.Objects;
import java.util.Spliterator;
import java.util.TreeMap;
import org.apache.poi.ss.SpreadsheetVersion;
import org.apache.poi.ss.formula.FormulaShifter;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellCopyContext;
import org.apache.poi.ss.usermodel.CellCopyPolicy;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.FormulaError;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.helpers.RowShifter;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.CellUtil;
import org.apache.poi.util.Internal;
import org.apache.poi.xssf.model.StylesTable;
import org.apache.poi.xssf.usermodel.helpers.XSSFRowShifter;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCell;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTRow;

public class XSSFRow implements Row, Comparable<XSSFRow> {
    private final TreeMap<Integer, XSSFCell> _cells = new TreeMap<>();
    private final CTRow _row;
    private final XSSFSheet _sheet;

    protected XSSFRow(CTRow cTRow, XSSFSheet xSSFSheet) {
        this._row = cTRow;
        this._sheet = xSSFSheet;
        for (CTCell xSSFCell : cTRow.getCArray()) {
            XSSFCell xSSFCell2 = new XSSFCell(this, xSSFCell);
            this._cells.put(Integer.valueOf(xSSFCell2.getColumnIndex()), xSSFCell2);
            xSSFSheet.onReadCell(xSSFCell2);
        }
        if (!cTRow.isSetR()) {
            int lastRowNum = xSSFSheet.getLastRowNum() + 2;
            if (lastRowNum == 2 && xSSFSheet.getPhysicalNumberOfRows() == 0) {
                lastRowNum = 1;
            }
            cTRow.setR((long) lastRowNum);
        }
    }

    public XSSFSheet getSheet() {
        return this._sheet;
    }

    public Iterator<Cell> cellIterator() {
        return this._cells.values().iterator();
    }

    public Spliterator<Cell> spliterator() {
        return this._cells.values().spliterator();
    }

    public int compareTo(XSSFRow xSSFRow) {
        if (getSheet() == xSSFRow.getSheet()) {
            return Integer.compare(getRowNum(), xSSFRow.getRowNum());
        }
        throw new IllegalArgumentException("The compared rows must belong to the same sheet");
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof XSSFRow)) {
            return false;
        }
        XSSFRow xSSFRow = (XSSFRow) obj;
        if (getRowNum() == xSSFRow.getRowNum() && getSheet() == xSSFRow.getSheet()) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return this._row.hashCode();
    }

    public XSSFCell createCell(int i) {
        return createCell(i, CellType.BLANK);
    }

    public XSSFCell createCell(int i, CellType cellType) {
        CTCell cTCell;
        Integer valueOf = Integer.valueOf(i);
        XSSFCell xSSFCell = this._cells.get(valueOf);
        if (xSSFCell != null) {
            cTCell = xSSFCell.getCTCell();
            cTCell.set(CTCell.Factory.newInstance());
        } else {
            cTCell = this._row.addNewC();
        }
        XSSFCell xSSFCell2 = new XSSFCell(this, cTCell);
        try {
            xSSFCell2.setCellNum(i);
            if (!(cellType == CellType.BLANK || cellType == CellType.FORMULA)) {
                setDefaultValue(xSSFCell2, cellType);
            }
            this._cells.put(valueOf, xSSFCell2);
            return xSSFCell2;
        } catch (IllegalArgumentException e) {
            this._row.removeC(this._row.getCList().size() - 1);
            throw e;
        }
    }

    private static void setDefaultValue(XSSFCell xSSFCell, CellType cellType) {
        int i = AnonymousClass1.$SwitchMap$org$apache$poi$ss$usermodel$CellType[cellType.ordinal()];
        if (i == 1) {
            xSSFCell.setCellValue(0.0d);
        } else if (i == 2) {
            xSSFCell.setCellValue("");
        } else if (i == 3) {
            xSSFCell.setCellValue(false);
        } else if (i == 4) {
            xSSFCell.setCellErrorValue(FormulaError._NO_ERROR);
        } else {
            throw new AssertionError("Unknown cell-type specified: " + cellType);
        }
    }

    public XSSFCell getCell(int i) {
        return getCell(i, this._sheet.getWorkbook().getMissingCellPolicy());
    }

    public XSSFCell getCell(int i, Row.MissingCellPolicy missingCellPolicy) {
        if (i >= 0) {
            XSSFCell xSSFCell = this._cells.get(Integer.valueOf(i));
            int i2 = AnonymousClass1.$SwitchMap$org$apache$poi$ss$usermodel$Row$MissingCellPolicy[missingCellPolicy.ordinal()];
            boolean z = true;
            if (i2 == 1) {
                return xSSFCell;
            }
            if (i2 == 2) {
                if (xSSFCell == null || xSSFCell.getCellType() != CellType.BLANK) {
                    z = false;
                }
                if (z) {
                    return null;
                }
                return xSSFCell;
            } else if (i2 == 3) {
                return xSSFCell == null ? createCell(i, CellType.BLANK) : xSSFCell;
            } else {
                throw new IllegalArgumentException("Illegal policy " + missingCellPolicy);
            }
        } else {
            throw new IllegalArgumentException("Cell index must be >= 0");
        }
    }

    /* renamed from: org.apache.poi.xssf.usermodel.XSSFRow$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$org$apache$poi$ss$usermodel$CellType;
        static final /* synthetic */ int[] $SwitchMap$org$apache$poi$ss$usermodel$Row$MissingCellPolicy;

        /* JADX WARNING: Can't wrap try/catch for region: R(17:0|(2:1|2)|3|(2:5|6)|7|9|10|11|13|14|15|16|17|18|19|20|22) */
        /* JADX WARNING: Can't wrap try/catch for region: R(19:0|1|2|3|5|6|7|9|10|11|13|14|15|16|17|18|19|20|22) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:15:0x0039 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:17:0x0043 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:19:0x004d */
        static {
            /*
                org.apache.poi.ss.usermodel.Row$MissingCellPolicy[] r0 = org.apache.poi.ss.usermodel.Row.MissingCellPolicy.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$org$apache$poi$ss$usermodel$Row$MissingCellPolicy = r0
                r1 = 1
                org.apache.poi.ss.usermodel.Row$MissingCellPolicy r2 = org.apache.poi.ss.usermodel.Row.MissingCellPolicy.RETURN_NULL_AND_BLANK     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r2 = r2.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r0[r2] = r1     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                r0 = 2
                int[] r2 = $SwitchMap$org$apache$poi$ss$usermodel$Row$MissingCellPolicy     // Catch:{ NoSuchFieldError -> 0x001d }
                org.apache.poi.ss.usermodel.Row$MissingCellPolicy r3 = org.apache.poi.ss.usermodel.Row.MissingCellPolicy.RETURN_BLANK_AS_NULL     // Catch:{ NoSuchFieldError -> 0x001d }
                int r3 = r3.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2[r3] = r0     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                r2 = 3
                int[] r3 = $SwitchMap$org$apache$poi$ss$usermodel$Row$MissingCellPolicy     // Catch:{ NoSuchFieldError -> 0x0028 }
                org.apache.poi.ss.usermodel.Row$MissingCellPolicy r4 = org.apache.poi.ss.usermodel.Row.MissingCellPolicy.CREATE_NULL_AS_BLANK     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r3[r4] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                org.apache.poi.ss.usermodel.CellType[] r3 = org.apache.poi.ss.usermodel.CellType.values()
                int r3 = r3.length
                int[] r3 = new int[r3]
                $SwitchMap$org$apache$poi$ss$usermodel$CellType = r3
                org.apache.poi.ss.usermodel.CellType r4 = org.apache.poi.ss.usermodel.CellType.NUMERIC     // Catch:{ NoSuchFieldError -> 0x0039 }
                int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x0039 }
                r3[r4] = r1     // Catch:{ NoSuchFieldError -> 0x0039 }
            L_0x0039:
                int[] r1 = $SwitchMap$org$apache$poi$ss$usermodel$CellType     // Catch:{ NoSuchFieldError -> 0x0043 }
                org.apache.poi.ss.usermodel.CellType r3 = org.apache.poi.ss.usermodel.CellType.STRING     // Catch:{ NoSuchFieldError -> 0x0043 }
                int r3 = r3.ordinal()     // Catch:{ NoSuchFieldError -> 0x0043 }
                r1[r3] = r0     // Catch:{ NoSuchFieldError -> 0x0043 }
            L_0x0043:
                int[] r0 = $SwitchMap$org$apache$poi$ss$usermodel$CellType     // Catch:{ NoSuchFieldError -> 0x004d }
                org.apache.poi.ss.usermodel.CellType r1 = org.apache.poi.ss.usermodel.CellType.BOOLEAN     // Catch:{ NoSuchFieldError -> 0x004d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x004d }
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x004d }
            L_0x004d:
                int[] r0 = $SwitchMap$org$apache$poi$ss$usermodel$CellType     // Catch:{ NoSuchFieldError -> 0x0058 }
                org.apache.poi.ss.usermodel.CellType r1 = org.apache.poi.ss.usermodel.CellType.ERROR     // Catch:{ NoSuchFieldError -> 0x0058 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0058 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0058 }
            L_0x0058:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.xssf.usermodel.XSSFRow.AnonymousClass1.<clinit>():void");
        }
    }

    public short getFirstCellNum() {
        return (short) (this._cells.isEmpty() ? -1 : this._cells.firstKey().intValue());
    }

    public short getLastCellNum() {
        return (short) (this._cells.isEmpty() ? -1 : this._cells.lastKey().intValue() + 1);
    }

    public short getHeight() {
        return (short) ((int) (getHeightInPoints() * 20.0f));
    }

    public float getHeightInPoints() {
        if (this._row.isSetHt()) {
            return (float) this._row.getHt();
        }
        return this._sheet.getDefaultRowHeightInPoints();
    }

    public void setHeight(short s) {
        if (s == -1) {
            if (this._row.isSetHt()) {
                this._row.unsetHt();
            }
            if (this._row.isSetCustomHeight()) {
                this._row.unsetCustomHeight();
                return;
            }
            return;
        }
        this._row.setHt(((double) s) / 20.0d);
        this._row.setCustomHeight(true);
    }

    public void setHeightInPoints(float f) {
        float f2 = -1.0f;
        if (f != -1.0f) {
            f2 = 20.0f * f;
        }
        setHeight((short) ((int) f2));
    }

    public int getPhysicalNumberOfCells() {
        return this._cells.size();
    }

    public int getRowNum() {
        return Math.toIntExact(this._row.getR() - 1);
    }

    public void setRowNum(int i) {
        int lastRowIndex = SpreadsheetVersion.EXCEL2007.getLastRowIndex();
        if (i < 0 || i > lastRowIndex) {
            throw new IllegalArgumentException("Invalid row number (" + i + ") outside allowable range (0.." + lastRowIndex + ")");
        }
        this._row.setR(((long) i) + 1);
    }

    public boolean getZeroHeight() {
        return this._row.getHidden();
    }

    public void setZeroHeight(boolean z) {
        this._row.setHidden(z);
    }

    public boolean isFormatted() {
        return this._row.isSetS();
    }

    public XSSFCellStyle getRowStyle() {
        if (!isFormatted()) {
            return null;
        }
        StylesTable stylesSource = getSheet().getWorkbook().getStylesSource();
        if (stylesSource.getNumCellStyles() > 0) {
            return stylesSource.getStyleAt(Math.toIntExact(this._row.getS()));
        }
        return null;
    }

    public void setRowStyle(CellStyle cellStyle) {
        if (cellStyle != null) {
            StylesTable stylesSource = getSheet().getWorkbook().getStylesSource();
            XSSFCellStyle xSSFCellStyle = (XSSFCellStyle) cellStyle;
            xSSFCellStyle.verifyBelongsToStylesSource(stylesSource);
            this._row.setS((long) stylesSource.putStyle(xSSFCellStyle));
            this._row.setCustomFormat(true);
        } else if (this._row.isSetS()) {
            this._row.unsetS();
            this._row.unsetCustomFormat();
        }
    }

    public void removeCell(Cell cell) {
        if (cell.getRow() != this) {
            throw new IllegalArgumentException("Specified cell does not belong to this row");
        } else if (this._cells.containsValue(cell)) {
            XSSFCell xSSFCell = (XSSFCell) cell;
            if (xSSFCell.isPartOfArrayFormulaGroup()) {
                xSSFCell.setCellFormula((String) null);
            }
            if (cell.getCellType() == CellType.FORMULA) {
                this._sheet.getWorkbook().onDeleteFormula(xSSFCell);
            }
            XSSFCell remove = this._cells.remove(Integer.valueOf(cell.getColumnIndex()));
            int i = 0;
            for (CTCell cTCell : this._row.getCArray()) {
                if (cTCell == remove.getCTCell()) {
                    this._row.removeC(i);
                }
                i++;
            }
        } else {
            throw new IllegalArgumentException("the row does not contain this cell");
        }
    }

    @Internal
    public CTRow getCTRow() {
        return this._row;
    }

    /* access modifiers changed from: protected */
    public void onDocumentWrite() {
        CTCell[] cArray = this._row.getCArray();
        if (cArray.length == this._cells.size()) {
            Iterator<XSSFCell> it = this._cells.values().iterator();
            int length = cArray.length;
            boolean z = false;
            int i = 0;
            while (true) {
                if (i >= length) {
                    z = true;
                    break;
                } else if (cArray[i] != it.next().getCTCell()) {
                    break;
                } else {
                    i++;
                }
            }
            if (z) {
                return;
            }
        }
        fixupCTCells(cArray);
    }

    private void fixupCTCells(CTCell[] cTCellArr) {
        CTCell[] cTCellArr2 = new CTCell[cTCellArr.length];
        IdentityHashMap identityHashMap = new IdentityHashMap(this._cells.size());
        int i = 0;
        int i2 = 0;
        for (CTCell cTCell : cTCellArr) {
            cTCellArr2[i2] = (CTCell) cTCell.copy();
            identityHashMap.put(cTCell, Integer.valueOf(i2));
            i2++;
        }
        for (XSSFCell next : this._cells.values()) {
            Integer num = (Integer) identityHashMap.get(next.getCTCell());
            Objects.requireNonNull(num, "Should find CTCell in _row");
            if (num.intValue() != i) {
                this._row.setCArray(i, cTCellArr2[num.intValue()]);
                next.setCTCell(this._row.getCArray(i));
            }
            i++;
        }
        while (cTCellArr.length > this._cells.size()) {
            this._row.removeC(this._cells.size());
        }
    }

    public String toString() {
        return this._row.toString();
    }

    /* access modifiers changed from: protected */
    public void shift(int i) {
        int rowNum = getRowNum();
        int i2 = i + rowNum;
        String str = "Row[rownum=" + rowNum + "] contains cell(s) included in a multi-cell array formula. You cannot change part of an array.";
        setRowNum(i2);
        Iterator<Cell> it = iterator();
        while (it.hasNext()) {
            ((XSSFCell) it.next()).updateCellReferencesForShifting(str);
        }
    }

    public void copyRowFrom(Row row, CellCopyPolicy cellCopyPolicy) {
        copyRowFrom(row, cellCopyPolicy, (CellCopyContext) null);
    }

    public void copyRowFrom(Row row, CellCopyPolicy cellCopyPolicy, CellCopyContext cellCopyContext) {
        if (row == null) {
            Iterator<Cell> it = iterator();
            while (it.hasNext()) {
                CellUtil.copyCell((Cell) null, it.next(), cellCopyPolicy, cellCopyContext);
            }
            if (cellCopyPolicy.isCopyMergedRegions()) {
                int rowNum = getRowNum();
                HashSet hashSet = new HashSet();
                int i = 0;
                for (CellRangeAddress next : getSheet().getMergedRegions()) {
                    if (rowNum == next.getFirstRow() && rowNum == next.getLastRow()) {
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
        for (Cell next2 : row) {
            CellUtil.copyCell(next2, createCell(next2.getColumnIndex()), cellCopyPolicy, cellCopyContext);
        }
        int sheetIndex = this._sheet.getWorkbook().getSheetIndex((Sheet) this._sheet);
        String sheetName = this._sheet.getWorkbook().getSheetName(sheetIndex);
        int rowNum2 = row.getRowNum();
        int rowNum3 = getRowNum();
        new XSSFRowShifter(this._sheet).updateRowFormulas(this, FormulaShifter.createForRowCopy(sheetIndex, sheetName, rowNum2, rowNum2, rowNum3 - rowNum2, SpreadsheetVersion.EXCEL2007));
        if (cellCopyPolicy.isCopyMergedRegions()) {
            for (CellRangeAddress next3 : row.getSheet().getMergedRegions()) {
                if (rowNum2 == next3.getFirstRow() && rowNum2 == next3.getLastRow()) {
                    CellRangeAddress copy = next3.copy();
                    copy.setFirstRow(rowNum3);
                    copy.setLastRow(rowNum3);
                    getSheet().addMergedRegion(copy);
                }
            }
        }
        if (cellCopyPolicy.isCopyRowHeight()) {
            setHeight(row.getHeight());
        }
    }

    public int getOutlineLevel() {
        return this._row.getOutlineLevel();
    }

    public void shiftCellsRight(int i, int i2, int i3) {
        RowShifter.validateShiftParameters(i, i2, i3);
        while (i2 >= i) {
            shiftCell(i2, i3);
            i2--;
        }
        for (int i4 = i; i4 <= (i + i3) - 1; i4++) {
            this._cells.remove(Integer.valueOf(i4));
            XSSFCell cell = getCell(i4);
            if (cell != null) {
                cell.getCTCell().set(CTCell.Factory.newInstance());
            }
        }
    }

    public void shiftCellsLeft(int i, int i2, int i3) {
        RowShifter.validateShiftLeftParameters(i, i2, i3);
        while (i <= i2) {
            shiftCell(i, -i3);
            i++;
        }
        int i4 = i2 - i3;
        while (true) {
            i4++;
            if (i4 <= i2) {
                this._cells.remove(Integer.valueOf(i4));
                XSSFCell cell = getCell(i4);
                if (cell != null) {
                    cell.getCTCell().set(CTCell.Factory.newInstance());
                }
            } else {
                return;
            }
        }
    }

    private void shiftCell(int i, int i2) {
        int i3 = i2 + i;
        if (i3 >= 0) {
            XSSFCell cell = getCell(i);
            if (cell != null) {
                cell.setCellNum(i3);
                this._cells.put(Integer.valueOf(i3), cell);
                return;
            }
            this._cells.remove(Integer.valueOf(i3));
            XSSFCell cell2 = getCell(i3);
            if (cell2 != null) {
                cell2.getCTCell().set(CTCell.Factory.newInstance());
                return;
            }
            return;
        }
        throw new IllegalStateException("Column index less than zero : " + Integer.valueOf(i3));
    }
}
