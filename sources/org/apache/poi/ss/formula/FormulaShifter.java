package org.apache.poi.ss.formula;

import org.apache.poi.ss.SpreadsheetVersion;
import org.apache.poi.ss.formula.ptg.Area2DPtgBase;
import org.apache.poi.ss.formula.ptg.Area3DPtg;
import org.apache.poi.ss.formula.ptg.Area3DPxg;
import org.apache.poi.ss.formula.ptg.AreaErrPtg;
import org.apache.poi.ss.formula.ptg.AreaPtg;
import org.apache.poi.ss.formula.ptg.AreaPtgBase;
import org.apache.poi.ss.formula.ptg.Deleted3DPxg;
import org.apache.poi.ss.formula.ptg.DeletedArea3DPtg;
import org.apache.poi.ss.formula.ptg.DeletedRef3DPtg;
import org.apache.poi.ss.formula.ptg.Ptg;
import org.apache.poi.ss.formula.ptg.Ref3DPtg;
import org.apache.poi.ss.formula.ptg.Ref3DPxg;
import org.apache.poi.ss.formula.ptg.RefErrorPtg;
import org.apache.poi.ss.formula.ptg.RefPtg;
import org.apache.poi.ss.formula.ptg.RefPtgBase;

public final class FormulaShifter {
    private final int _amountToMove;
    private final int _dstSheetIndex;
    private final int _externSheetIndex;
    private final int _firstMovedIndex;
    private final int _lastMovedIndex;
    private final ShiftMode _mode;
    private final String _sheetName;
    private final int _srcSheetIndex;
    private final SpreadsheetVersion _version;

    private enum ShiftMode {
        RowMove,
        RowCopy,
        ColumnMove,
        ColumnCopy,
        SheetMove
    }

    private FormulaShifter(int i, String str, int i2, int i3, int i4, ShiftMode shiftMode, SpreadsheetVersion spreadsheetVersion) {
        if (i2 <= i3) {
            this._externSheetIndex = i;
            this._sheetName = str;
            this._firstMovedIndex = i2;
            this._lastMovedIndex = i3;
            this._amountToMove = i4;
            this._mode = shiftMode;
            this._version = spreadsheetVersion;
            this._dstSheetIndex = -1;
            this._srcSheetIndex = -1;
            return;
        }
        throw new IllegalArgumentException("firstMovedIndex, lastMovedIndex out of order");
    }

    private FormulaShifter(int i, int i2) {
        this._amountToMove = -1;
        this._lastMovedIndex = -1;
        this._firstMovedIndex = -1;
        this._externSheetIndex = -1;
        this._sheetName = null;
        this._version = null;
        this._srcSheetIndex = i;
        this._dstSheetIndex = i2;
        this._mode = ShiftMode.SheetMove;
    }

    public static FormulaShifter createForRowShift(int i, String str, int i2, int i3, int i4, SpreadsheetVersion spreadsheetVersion) {
        return new FormulaShifter(i, str, i2, i3, i4, ShiftMode.RowMove, spreadsheetVersion);
    }

    public static FormulaShifter createForRowCopy(int i, String str, int i2, int i3, int i4, SpreadsheetVersion spreadsheetVersion) {
        return new FormulaShifter(i, str, i2, i3, i4, ShiftMode.RowCopy, spreadsheetVersion);
    }

    public static FormulaShifter createForColumnShift(int i, String str, int i2, int i3, int i4, SpreadsheetVersion spreadsheetVersion) {
        return new FormulaShifter(i, str, i2, i3, i4, ShiftMode.ColumnMove, spreadsheetVersion);
    }

    public static FormulaShifter createForColumnCopy(int i, String str, int i2, int i3, int i4, SpreadsheetVersion spreadsheetVersion) {
        return new FormulaShifter(i, str, i2, i3, i4, ShiftMode.ColumnCopy, spreadsheetVersion);
    }

    public static FormulaShifter createForSheetShift(int i, int i2) {
        return new FormulaShifter(i, i2);
    }

    public String toString() {
        return getClass().getName() + " [" + this._firstMovedIndex + this._lastMovedIndex + this._amountToMove + "]";
    }

    public boolean adjustFormula(Ptg[] ptgArr, int i) {
        boolean z = false;
        for (int i2 = 0; i2 < ptgArr.length; i2++) {
            Ptg adjustPtg = adjustPtg(ptgArr[i2], i);
            if (adjustPtg != null) {
                ptgArr[i2] = adjustPtg;
                z = true;
            }
        }
        return z;
    }

    /* renamed from: org.apache.poi.ss.formula.FormulaShifter$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$org$apache$poi$ss$formula$FormulaShifter$ShiftMode;

        /* JADX WARNING: Can't wrap try/catch for region: R(12:0|1|2|3|4|5|6|7|8|9|10|12) */
        /* JADX WARNING: Code restructure failed: missing block: B:13:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0033 */
        static {
            /*
                org.apache.poi.ss.formula.FormulaShifter$ShiftMode[] r0 = org.apache.poi.ss.formula.FormulaShifter.ShiftMode.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$org$apache$poi$ss$formula$FormulaShifter$ShiftMode = r0
                org.apache.poi.ss.formula.FormulaShifter$ShiftMode r1 = org.apache.poi.ss.formula.FormulaShifter.ShiftMode.RowMove     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$org$apache$poi$ss$formula$FormulaShifter$ShiftMode     // Catch:{ NoSuchFieldError -> 0x001d }
                org.apache.poi.ss.formula.FormulaShifter$ShiftMode r1 = org.apache.poi.ss.formula.FormulaShifter.ShiftMode.RowCopy     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$org$apache$poi$ss$formula$FormulaShifter$ShiftMode     // Catch:{ NoSuchFieldError -> 0x0028 }
                org.apache.poi.ss.formula.FormulaShifter$ShiftMode r1 = org.apache.poi.ss.formula.FormulaShifter.ShiftMode.ColumnMove     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = $SwitchMap$org$apache$poi$ss$formula$FormulaShifter$ShiftMode     // Catch:{ NoSuchFieldError -> 0x0033 }
                org.apache.poi.ss.formula.FormulaShifter$ShiftMode r1 = org.apache.poi.ss.formula.FormulaShifter.ShiftMode.ColumnCopy     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                int[] r0 = $SwitchMap$org$apache$poi$ss$formula$FormulaShifter$ShiftMode     // Catch:{ NoSuchFieldError -> 0x003e }
                org.apache.poi.ss.formula.FormulaShifter$ShiftMode r1 = org.apache.poi.ss.formula.FormulaShifter.ShiftMode.SheetMove     // Catch:{ NoSuchFieldError -> 0x003e }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x003e }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x003e }
            L_0x003e:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.ss.formula.FormulaShifter.AnonymousClass1.<clinit>():void");
        }
    }

    private Ptg adjustPtg(Ptg ptg, int i) {
        int i2 = AnonymousClass1.$SwitchMap$org$apache$poi$ss$formula$FormulaShifter$ShiftMode[this._mode.ordinal()];
        if (i2 == 1) {
            return adjustPtgDueToRowMove(ptg, i);
        }
        if (i2 == 2) {
            return adjustPtgDueToRowCopy(ptg);
        }
        if (i2 == 3) {
            return adjustPtgDueToColumnMove(ptg, i);
        }
        if (i2 == 4) {
            return adjustPtgDueToColumnCopy(ptg);
        }
        if (i2 == 5) {
            return adjustPtgDueToSheetMove(ptg);
        }
        throw new IllegalStateException("Unsupported shift mode: " + this._mode);
    }

    private Ptg adjustPtgDueToMove(Ptg ptg, int i, boolean z) {
        if (ptg instanceof RefPtg) {
            if (i != this._externSheetIndex) {
                return null;
            }
            RefPtg refPtg = (RefPtg) ptg;
            return z ? rowMoveRefPtg(refPtg) : columnMoveRefPtg(refPtg);
        } else if (ptg instanceof Ref3DPtg) {
            Ref3DPtg ref3DPtg = (Ref3DPtg) ptg;
            if (this._externSheetIndex != ref3DPtg.getExternSheetIndex()) {
                return null;
            }
            return z ? rowMoveRefPtg(ref3DPtg) : columnMoveRefPtg(ref3DPtg);
        } else if (ptg instanceof Ref3DPxg) {
            Ref3DPxg ref3DPxg = (Ref3DPxg) ptg;
            if (ref3DPxg.getExternalWorkbookNumber() > 0 || !this._sheetName.equalsIgnoreCase(ref3DPxg.getSheetName())) {
                return null;
            }
            return z ? rowMoveRefPtg(ref3DPxg) : columnMoveRefPtg(ref3DPxg);
        } else if (ptg instanceof Area2DPtgBase) {
            if (i != this._externSheetIndex) {
                return ptg;
            }
            Area2DPtgBase area2DPtgBase = (Area2DPtgBase) ptg;
            return z ? rowMoveAreaPtg(area2DPtgBase) : columnMoveAreaPtg(area2DPtgBase);
        } else if (ptg instanceof Area3DPtg) {
            Area3DPtg area3DPtg = (Area3DPtg) ptg;
            if (this._externSheetIndex != area3DPtg.getExternSheetIndex()) {
                return null;
            }
            return z ? rowMoveAreaPtg(area3DPtg) : columnMoveAreaPtg(area3DPtg);
        } else {
            if (ptg instanceof Area3DPxg) {
                Area3DPxg area3DPxg = (Area3DPxg) ptg;
                if (area3DPxg.getExternalWorkbookNumber() <= 0 && this._sheetName.equalsIgnoreCase(area3DPxg.getSheetName())) {
                    return z ? rowMoveAreaPtg(area3DPxg) : columnMoveAreaPtg(area3DPxg);
                }
            }
            return null;
        }
    }

    private Ptg adjustPtgDueToRowMove(Ptg ptg, int i) {
        return adjustPtgDueToMove(ptg, i, true);
    }

    private Ptg adjustPtgDueToColumnMove(Ptg ptg, int i) {
        return adjustPtgDueToMove(ptg, i, false);
    }

    private Ptg adjustPtgDueToCopy(Ptg ptg, boolean z) {
        if (ptg instanceof RefPtg) {
            RefPtg refPtg = (RefPtg) ptg;
            return z ? rowCopyRefPtg(refPtg) : columnCopyRefPtg(refPtg);
        } else if (ptg instanceof Ref3DPtg) {
            Ref3DPtg ref3DPtg = (Ref3DPtg) ptg;
            return z ? rowCopyRefPtg(ref3DPtg) : columnCopyRefPtg(ref3DPtg);
        } else if (ptg instanceof Ref3DPxg) {
            Ref3DPxg ref3DPxg = (Ref3DPxg) ptg;
            return z ? rowCopyRefPtg(ref3DPxg) : columnCopyRefPtg(ref3DPxg);
        } else if (ptg instanceof Area2DPtgBase) {
            Area2DPtgBase area2DPtgBase = (Area2DPtgBase) ptg;
            return z ? rowCopyAreaPtg(area2DPtgBase) : columnCopyAreaPtg(area2DPtgBase);
        } else if (ptg instanceof Area3DPtg) {
            Area3DPtg area3DPtg = (Area3DPtg) ptg;
            return z ? rowCopyAreaPtg(area3DPtg) : columnCopyAreaPtg(area3DPtg);
        } else if (!(ptg instanceof Area3DPxg)) {
            return null;
        } else {
            Area3DPxg area3DPxg = (Area3DPxg) ptg;
            return z ? rowCopyAreaPtg(area3DPxg) : columnCopyAreaPtg(area3DPxg);
        }
    }

    private Ptg adjustPtgDueToRowCopy(Ptg ptg) {
        return adjustPtgDueToCopy(ptg, true);
    }

    private Ptg adjustPtgDueToColumnCopy(Ptg ptg) {
        return adjustPtgDueToCopy(ptg, false);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0005, code lost:
        r5 = (org.apache.poi.ss.formula.ptg.Ref3DPtg) r5;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private org.apache.poi.ss.formula.ptg.Ptg adjustPtgDueToSheetMove(org.apache.poi.ss.formula.ptg.Ptg r5) {
        /*
            r4 = this;
            boolean r0 = r5 instanceof org.apache.poi.ss.formula.ptg.Ref3DPtg
            r1 = 0
            if (r0 == 0) goto L_0x0035
            org.apache.poi.ss.formula.ptg.Ref3DPtg r5 = (org.apache.poi.ss.formula.ptg.Ref3DPtg) r5
            int r0 = r5.getExternSheetIndex()
            int r2 = r4._srcSheetIndex
            if (r0 >= r2) goto L_0x0014
            int r3 = r4._dstSheetIndex
            if (r0 >= r3) goto L_0x0014
            return r1
        L_0x0014:
            if (r0 <= r2) goto L_0x001b
            int r3 = r4._dstSheetIndex
            if (r0 <= r3) goto L_0x001b
            return r1
        L_0x001b:
            if (r0 != r2) goto L_0x0023
            int r4 = r4._dstSheetIndex
            r5.setExternSheetIndex(r4)
            return r5
        L_0x0023:
            int r4 = r4._dstSheetIndex
            if (r4 >= r2) goto L_0x002d
            int r0 = r0 + 1
            r5.setExternSheetIndex(r0)
            return r5
        L_0x002d:
            if (r4 <= r2) goto L_0x0035
            int r0 = r0 + -1
            r5.setExternSheetIndex(r0)
            return r5
        L_0x0035:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.ss.formula.FormulaShifter.adjustPtgDueToSheetMove(org.apache.poi.ss.formula.ptg.Ptg):org.apache.poi.ss.formula.ptg.Ptg");
    }

    private Ptg rowMoveRefPtg(RefPtgBase refPtgBase) {
        int row = refPtgBase.getRow();
        int i = this._firstMovedIndex;
        if (i > row || row > this._lastMovedIndex) {
            int i2 = this._amountToMove;
            int i3 = i + i2;
            int i4 = this._lastMovedIndex + i2;
            if (i4 < row || row < i3) {
                return null;
            }
            if (i3 <= row && row <= i4) {
                return createDeletedRef(refPtgBase);
            }
            throw new IllegalStateException("Situation not covered: (" + this._firstMovedIndex + ", " + this._lastMovedIndex + ", " + this._amountToMove + ", " + row + ", " + row + ")");
        }
        refPtgBase.setRow(row + this._amountToMove);
        return refPtgBase;
    }

    private Ptg rowMoveAreaPtg(AreaPtgBase areaPtgBase) {
        int firstRow = areaPtgBase.getFirstRow();
        int lastRow = areaPtgBase.getLastRow();
        int i = this._firstMovedIndex;
        if (i > firstRow || lastRow > this._lastMovedIndex) {
            int i2 = this._amountToMove;
            int i3 = i + i2;
            int i4 = this._lastMovedIndex;
            int i5 = i4 + i2;
            if (firstRow >= i || i4 >= lastRow) {
                if (i > firstRow || firstRow > i4) {
                    if (i > lastRow || lastRow > i4) {
                        if (i5 < firstRow || lastRow < i3) {
                            return null;
                        }
                        if (i3 <= firstRow && lastRow <= i5) {
                            return createDeletedRef(areaPtgBase);
                        }
                        if (firstRow <= i3 && i5 <= lastRow) {
                            return null;
                        }
                        if (i3 < firstRow && firstRow <= i5) {
                            areaPtgBase.setFirstRow(i5 + 1);
                            return areaPtgBase;
                        } else if (i3 > lastRow || lastRow >= i5) {
                            throw new IllegalStateException("Situation not covered: (" + this._firstMovedIndex + ", " + this._lastMovedIndex + ", " + this._amountToMove + ", " + firstRow + ", " + lastRow + ")");
                        } else {
                            areaPtgBase.setLastRow(i3 - 1);
                            return areaPtgBase;
                        }
                    } else if (i2 > 0) {
                        areaPtgBase.setLastRow(lastRow + i2);
                        return areaPtgBase;
                    } else if (i5 < firstRow) {
                        return null;
                    } else {
                        int i6 = lastRow + i2;
                        if (i3 > firstRow) {
                            areaPtgBase.setLastRow(i6);
                            return areaPtgBase;
                        }
                        int i7 = i - 1;
                        if (i5 < i7) {
                            i6 = i7;
                        }
                        areaPtgBase.setFirstRow(Math.min(firstRow, i3));
                        areaPtgBase.setLastRow(i6);
                        return areaPtgBase;
                    }
                } else if (i2 < 0) {
                    areaPtgBase.setFirstRow(firstRow + i2);
                    return areaPtgBase;
                } else if (i3 > lastRow) {
                    return null;
                } else {
                    int i8 = firstRow + i2;
                    if (i5 < lastRow) {
                        areaPtgBase.setFirstRow(i8);
                        return areaPtgBase;
                    }
                    int i9 = i4 + 1;
                    if (i3 > i9) {
                        i8 = i9;
                    }
                    areaPtgBase.setFirstRow(i8);
                    areaPtgBase.setLastRow(Math.max(lastRow, i5));
                    return areaPtgBase;
                }
            } else if (i3 < firstRow && firstRow <= i5) {
                areaPtgBase.setFirstRow(i5 + 1);
                return areaPtgBase;
            } else if (i3 > lastRow || lastRow >= i5) {
                return null;
            } else {
                areaPtgBase.setLastRow(i3 - 1);
                return areaPtgBase;
            }
        } else {
            areaPtgBase.setFirstRow(firstRow + this._amountToMove);
            areaPtgBase.setLastRow(lastRow + this._amountToMove);
            return areaPtgBase;
        }
    }

    private Ptg rowCopyRefPtg(RefPtgBase refPtgBase) {
        int row = refPtgBase.getRow();
        if (!refPtgBase.isRowRelative()) {
            return null;
        }
        int i = this._firstMovedIndex + this._amountToMove;
        if (i < 0 || this._version.getLastRowIndex() < i) {
            return createDeletedRef(refPtgBase);
        }
        int i2 = row + this._amountToMove;
        if (i2 < 0 || this._version.getLastRowIndex() < i2) {
            return createDeletedRef(refPtgBase);
        }
        refPtgBase.setRow(i2);
        return refPtgBase;
    }

    private Ptg rowCopyAreaPtg(AreaPtgBase areaPtgBase) {
        boolean z;
        int firstRow = areaPtgBase.getFirstRow();
        int lastRow = areaPtgBase.getLastRow();
        boolean z2 = true;
        if (areaPtgBase.isFirstRowRelative()) {
            int i = firstRow + this._amountToMove;
            if (i < 0 || this._version.getLastRowIndex() < i) {
                return createDeletedRef(areaPtgBase);
            }
            areaPtgBase.setFirstRow(i);
            z = true;
        } else {
            z = false;
        }
        if (areaPtgBase.isLastRowRelative()) {
            int i2 = lastRow + this._amountToMove;
            if (i2 < 0 || this._version.getLastRowIndex() < i2) {
                return createDeletedRef(areaPtgBase);
            }
            areaPtgBase.setLastRow(i2);
        } else {
            z2 = z;
        }
        if (z2) {
            areaPtgBase.sortTopLeftToBottomRight();
        }
        if (z2) {
            return areaPtgBase;
        }
        return null;
    }

    private Ptg columnMoveRefPtg(RefPtgBase refPtgBase) {
        int column = refPtgBase.getColumn();
        int i = this._firstMovedIndex;
        if (i > column || column > this._lastMovedIndex) {
            int i2 = this._amountToMove;
            int i3 = i + i2;
            int i4 = this._lastMovedIndex + i2;
            if (i4 < column || column < i3) {
                return null;
            }
            if (i3 <= column && column <= i4) {
                return createDeletedRef(refPtgBase);
            }
            throw new IllegalStateException("Situation not covered: (" + this._firstMovedIndex + ", " + this._lastMovedIndex + ", " + this._amountToMove + ", " + column + ", " + column + ")");
        }
        refPtgBase.setColumn(column + this._amountToMove);
        return refPtgBase;
    }

    private Ptg columnMoveAreaPtg(AreaPtgBase areaPtgBase) {
        int firstColumn = areaPtgBase.getFirstColumn();
        int lastColumn = areaPtgBase.getLastColumn();
        int i = this._firstMovedIndex;
        if (i > firstColumn || lastColumn > this._lastMovedIndex) {
            int i2 = this._amountToMove;
            int i3 = i + i2;
            int i4 = this._lastMovedIndex;
            int i5 = i4 + i2;
            if (firstColumn >= i || i4 >= lastColumn) {
                if (i > firstColumn || firstColumn > i4) {
                    if (i > lastColumn || lastColumn > i4) {
                        if (i5 < firstColumn || lastColumn < i3) {
                            return null;
                        }
                        if (i3 <= firstColumn && lastColumn <= i5) {
                            return createDeletedRef(areaPtgBase);
                        }
                        if (firstColumn <= i3 && i5 <= lastColumn) {
                            return null;
                        }
                        if (i3 < firstColumn && firstColumn <= i5) {
                            areaPtgBase.setFirstColumn(i5 + 1);
                            return areaPtgBase;
                        } else if (i3 > lastColumn || lastColumn >= i5) {
                            throw new IllegalStateException("Situation not covered: (" + this._firstMovedIndex + ", " + this._lastMovedIndex + ", " + this._amountToMove + ", " + firstColumn + ", " + lastColumn + ")");
                        } else {
                            areaPtgBase.setLastColumn(i3 - 1);
                            return areaPtgBase;
                        }
                    } else if (i2 > 0) {
                        areaPtgBase.setLastColumn(lastColumn + i2);
                        return areaPtgBase;
                    } else if (i5 < firstColumn) {
                        return null;
                    } else {
                        int i6 = lastColumn + i2;
                        if (i3 > firstColumn) {
                            areaPtgBase.setLastColumn(i6);
                            return areaPtgBase;
                        }
                        int i7 = i - 1;
                        if (i5 < i7) {
                            i6 = i7;
                        }
                        areaPtgBase.setFirstColumn(Math.min(firstColumn, i3));
                        areaPtgBase.setLastColumn(i6);
                        return areaPtgBase;
                    }
                } else if (i2 < 0) {
                    areaPtgBase.setFirstColumn(firstColumn + i2);
                    return areaPtgBase;
                } else if (i3 > lastColumn) {
                    return null;
                } else {
                    int i8 = firstColumn + i2;
                    if (i5 < lastColumn) {
                        areaPtgBase.setFirstColumn(i8);
                        return areaPtgBase;
                    }
                    int i9 = i4 + 1;
                    if (i3 > i9) {
                        i8 = i9;
                    }
                    areaPtgBase.setFirstColumn(i8);
                    areaPtgBase.setLastColumn(Math.max(lastColumn, i5));
                    return areaPtgBase;
                }
            } else if (i3 < firstColumn && firstColumn <= i5) {
                areaPtgBase.setFirstColumn(i5 + 1);
                return areaPtgBase;
            } else if (i3 > lastColumn || lastColumn >= i5) {
                return null;
            } else {
                areaPtgBase.setLastColumn(i3 - 1);
                return areaPtgBase;
            }
        } else {
            areaPtgBase.setFirstColumn(firstColumn + this._amountToMove);
            areaPtgBase.setLastColumn(lastColumn + this._amountToMove);
            return areaPtgBase;
        }
    }

    private Ptg columnCopyRefPtg(RefPtgBase refPtgBase) {
        int column = refPtgBase.getColumn();
        if (!refPtgBase.isColRelative()) {
            return null;
        }
        int i = this._firstMovedIndex + this._amountToMove;
        if (i < 0 || this._version.getLastColumnIndex() < i) {
            return createDeletedRef(refPtgBase);
        }
        int i2 = column + this._amountToMove;
        if (i2 < 0 || this._version.getLastColumnIndex() < i2) {
            return createDeletedRef(refPtgBase);
        }
        refPtgBase.setColumn(i2);
        return refPtgBase;
    }

    private Ptg columnCopyAreaPtg(AreaPtgBase areaPtgBase) {
        boolean z;
        int firstColumn = areaPtgBase.getFirstColumn();
        int lastColumn = areaPtgBase.getLastColumn();
        boolean z2 = true;
        if (areaPtgBase.isFirstColRelative()) {
            int i = firstColumn + this._amountToMove;
            if (i < 0 || this._version.getLastColumnIndex() < i) {
                return createDeletedRef(areaPtgBase);
            }
            areaPtgBase.setFirstColumn(i);
            z = true;
        } else {
            z = false;
        }
        if (areaPtgBase.isLastColRelative()) {
            int i2 = lastColumn + this._amountToMove;
            if (i2 < 0 || this._version.getLastColumnIndex() < i2) {
                return createDeletedRef(areaPtgBase);
            }
            areaPtgBase.setLastColumn(i2);
        } else {
            z2 = z;
        }
        if (z2) {
            areaPtgBase.sortTopLeftToBottomRight();
        }
        if (z2) {
            return areaPtgBase;
        }
        return null;
    }

    private static Ptg createDeletedRef(Ptg ptg) {
        if (ptg instanceof RefPtg) {
            return new RefErrorPtg();
        }
        if (ptg instanceof Ref3DPtg) {
            return new DeletedRef3DPtg(((Ref3DPtg) ptg).getExternSheetIndex());
        }
        if (ptg instanceof AreaPtg) {
            return new AreaErrPtg();
        }
        if (ptg instanceof Area3DPtg) {
            return new DeletedArea3DPtg(((Area3DPtg) ptg).getExternSheetIndex());
        }
        if (ptg instanceof Ref3DPxg) {
            Ref3DPxg ref3DPxg = (Ref3DPxg) ptg;
            return new Deleted3DPxg(ref3DPxg.getExternalWorkbookNumber(), ref3DPxg.getSheetName());
        } else if (ptg instanceof Area3DPxg) {
            Area3DPxg area3DPxg = (Area3DPxg) ptg;
            return new Deleted3DPxg(area3DPxg.getExternalWorkbookNumber(), area3DPxg.getSheetName());
        } else {
            throw new IllegalArgumentException("Unexpected ref ptg class (" + ptg.getClass().getName() + ")");
        }
    }
}
