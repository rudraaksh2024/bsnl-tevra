package org.apache.poi.ss.usermodel;

import java.util.HashMap;
import java.util.Map;
import org.apache.poi.ss.formula.FormulaShifter;
import org.apache.poi.ss.formula.ptg.Ptg;
import org.apache.poi.ss.util.CellRangeAddress;

public abstract class RangeCopier {
    private Sheet destSheet;
    private FormulaShifter horizontalFormulaShifter;
    private Sheet sourceSheet;
    private FormulaShifter verticalFormulaShifter;

    /* access modifiers changed from: protected */
    public abstract void adjustCellReferencesInsideFormula(Cell cell, Sheet sheet, int i, int i2);

    public RangeCopier(Sheet sheet, Sheet sheet2) {
        this.sourceSheet = sheet;
        this.destSheet = sheet2;
    }

    public RangeCopier(Sheet sheet) {
        this(sheet, sheet);
    }

    public void copyRange(CellRangeAddress cellRangeAddress, CellRangeAddress cellRangeAddress2) {
        copyRange(cellRangeAddress, cellRangeAddress2, false, false);
    }

    public void copyRange(CellRangeAddress cellRangeAddress, CellRangeAddress cellRangeAddress2, boolean z, boolean z2) {
        Sheet cloneSheet = this.sourceSheet.getWorkbook().cloneSheet(this.sourceSheet.getWorkbook().getSheetIndex(this.sourceSheet));
        AnonymousClass1 r8 = z ? new HashMap<Integer, CellStyle>() {
        } : null;
        int lastColumn = cellRangeAddress.getLastColumn() - cellRangeAddress.getFirstColumn();
        int lastRow = cellRangeAddress.getLastRow() - cellRangeAddress.getFirstRow();
        int firstRow = cellRangeAddress2.getFirstRow();
        do {
            int firstColumn = cellRangeAddress2.getFirstColumn();
            int min = Math.min(lastRow, cellRangeAddress2.getLastRow() - firstRow);
            int firstRow2 = cellRangeAddress.getFirstRow() + min;
            int i = firstColumn;
            do {
                int min2 = Math.min(lastColumn, cellRangeAddress2.getLastColumn() - i);
                CellRangeAddress cellRangeAddress3 = new CellRangeAddress(cellRangeAddress.getFirstRow(), firstRow2, cellRangeAddress.getFirstColumn(), cellRangeAddress.getFirstColumn() + min2);
                copyRange(cellRangeAddress3, i - cellRangeAddress3.getFirstColumn(), firstRow - cellRangeAddress3.getFirstRow(), cloneSheet, r8);
                i += min2 + 1;
            } while (i <= cellRangeAddress2.getLastColumn());
            firstRow += min + 1;
        } while (firstRow <= cellRangeAddress2.getLastRow());
        if (z2) {
            this.sourceSheet.getMergedRegions().forEach(new RangeCopier$$ExternalSyntheticLambda0(this));
        }
        this.sourceSheet.getWorkbook().removeSheetAt(this.sourceSheet.getWorkbook().getSheetIndex(cloneSheet));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$copyRange$0$org-apache-poi-ss-usermodel-RangeCopier  reason: not valid java name */
    public /* synthetic */ void m2286lambda$copyRange$0$orgapachepoissusermodelRangeCopier(CellRangeAddress cellRangeAddress) {
        this.destSheet.addMergedRegion(cellRangeAddress);
    }

    private void copyRange(CellRangeAddress cellRangeAddress, int i, int i2, Sheet sheet, Map<Integer, CellStyle> map) {
        if (i != 0) {
            this.horizontalFormulaShifter = FormulaShifter.createForColumnCopy(this.sourceSheet.getWorkbook().getSheetIndex(this.sourceSheet), this.sourceSheet.getSheetName(), cellRangeAddress.getFirstColumn(), cellRangeAddress.getLastColumn(), i, this.sourceSheet.getWorkbook().getSpreadsheetVersion());
        }
        if (i2 != 0) {
            this.verticalFormulaShifter = FormulaShifter.createForRowCopy(this.sourceSheet.getWorkbook().getSheetIndex(this.sourceSheet), this.sourceSheet.getSheetName(), cellRangeAddress.getFirstRow(), cellRangeAddress.getLastRow(), i2, this.sourceSheet.getWorkbook().getSpreadsheetVersion());
        }
        for (int firstRow = cellRangeAddress.getFirstRow(); firstRow <= cellRangeAddress.getLastRow(); firstRow++) {
            Row row = sheet.getRow(firstRow);
            if (row != null) {
                for (int firstColumn = cellRangeAddress.getFirstColumn(); firstColumn <= cellRangeAddress.getLastColumn(); firstColumn++) {
                    Cell cell = row.getCell(firstColumn);
                    if (cell != null) {
                        int i3 = firstRow + i2;
                        Row row2 = this.destSheet.getRow(i3);
                        if (row2 == null) {
                            row2 = this.destSheet.createRow(i3);
                        }
                        int i4 = firstColumn + i;
                        Cell cell2 = row2.getCell(i4);
                        if (cell2 == null) {
                            cell2 = row2.createCell(i4);
                        }
                        cloneCellContent(cell, cell2, map);
                        if (cell2.getCellType() == CellType.FORMULA) {
                            adjustCellReferencesInsideFormula(cell2, this.destSheet, i, i2);
                        }
                    }
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public boolean adjustInBothDirections(Ptg[] ptgArr, int i, int i2, int i3) {
        boolean z = true;
        boolean adjustFormula = i3 != 0 ? this.verticalFormulaShifter.adjustFormula(ptgArr, i) : true;
        if (i2 == 0) {
            return adjustFormula;
        }
        if (!adjustFormula || !this.horizontalFormulaShifter.adjustFormula(ptgArr, i)) {
            z = false;
        }
        return z;
    }

    public static void cloneCellContent(Cell cell, Cell cell2, Map<Integer, CellStyle> map) {
        if (map != null) {
            if (cell.getSheet().getWorkbook() == cell2.getSheet().getWorkbook()) {
                cell2.setCellStyle(cell.getCellStyle());
            } else {
                int hashCode = cell.getCellStyle().hashCode();
                CellStyle cellStyle = map.get(Integer.valueOf(hashCode));
                if (cellStyle == null) {
                    cellStyle = cell2.getSheet().getWorkbook().createCellStyle();
                    cellStyle.cloneStyleFrom(cell.getCellStyle());
                    map.put(Integer.valueOf(hashCode), cellStyle);
                }
                cell2.setCellStyle(cellStyle);
            }
        }
        switch (AnonymousClass2.$SwitchMap$org$apache$poi$ss$usermodel$CellType[cell.getCellType().ordinal()]) {
            case 1:
                cell2.setCellValue(cell.getStringCellValue());
                return;
            case 2:
                cell2.setCellValue(cell.getNumericCellValue());
                return;
            case 3:
                cell2.setBlank();
                return;
            case 4:
                cell2.setCellValue(cell.getBooleanCellValue());
                return;
            case 5:
                cell2.setCellErrorValue(cell.getErrorCellValue());
                return;
            case 6:
                cell2.setCellFormula(cell.getCellFormula());
                return;
            default:
                return;
        }
    }

    /* renamed from: org.apache.poi.ss.usermodel.RangeCopier$2  reason: invalid class name */
    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$org$apache$poi$ss$usermodel$CellType;

        /* JADX WARNING: Can't wrap try/catch for region: R(14:0|1|2|3|4|5|6|7|8|9|10|11|12|14) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x003e */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0033 */
        static {
            /*
                org.apache.poi.ss.usermodel.CellType[] r0 = org.apache.poi.ss.usermodel.CellType.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$org$apache$poi$ss$usermodel$CellType = r0
                org.apache.poi.ss.usermodel.CellType r1 = org.apache.poi.ss.usermodel.CellType.STRING     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$org$apache$poi$ss$usermodel$CellType     // Catch:{ NoSuchFieldError -> 0x001d }
                org.apache.poi.ss.usermodel.CellType r1 = org.apache.poi.ss.usermodel.CellType.NUMERIC     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$org$apache$poi$ss$usermodel$CellType     // Catch:{ NoSuchFieldError -> 0x0028 }
                org.apache.poi.ss.usermodel.CellType r1 = org.apache.poi.ss.usermodel.CellType.BLANK     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = $SwitchMap$org$apache$poi$ss$usermodel$CellType     // Catch:{ NoSuchFieldError -> 0x0033 }
                org.apache.poi.ss.usermodel.CellType r1 = org.apache.poi.ss.usermodel.CellType.BOOLEAN     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                int[] r0 = $SwitchMap$org$apache$poi$ss$usermodel$CellType     // Catch:{ NoSuchFieldError -> 0x003e }
                org.apache.poi.ss.usermodel.CellType r1 = org.apache.poi.ss.usermodel.CellType.ERROR     // Catch:{ NoSuchFieldError -> 0x003e }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x003e }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x003e }
            L_0x003e:
                int[] r0 = $SwitchMap$org$apache$poi$ss$usermodel$CellType     // Catch:{ NoSuchFieldError -> 0x0049 }
                org.apache.poi.ss.usermodel.CellType r1 = org.apache.poi.ss.usermodel.CellType.FORMULA     // Catch:{ NoSuchFieldError -> 0x0049 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0049 }
                r2 = 6
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0049 }
            L_0x0049:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.ss.usermodel.RangeCopier.AnonymousClass2.<clinit>():void");
        }
    }
}
