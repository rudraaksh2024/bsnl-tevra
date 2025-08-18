package org.apache.poi.ss.util;

import java.util.Calendar;
import java.util.Date;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

public class SheetBuilder {
    private final Object[][] cells;
    private String sheetName;
    private boolean shouldCreateEmptyCells;
    private final Workbook workbook;

    public SheetBuilder(Workbook workbook2, Object[][] objArr) {
        this.workbook = workbook2;
        this.cells = (Object[][]) objArr.clone();
    }

    public boolean getCreateEmptyCells() {
        return this.shouldCreateEmptyCells;
    }

    public SheetBuilder setCreateEmptyCells(boolean z) {
        this.shouldCreateEmptyCells = z;
        return this;
    }

    public SheetBuilder setSheetName(String str) {
        this.sheetName = str;
        return this;
    }

    public Sheet build() {
        String str = this.sheetName;
        Sheet createSheet = str == null ? this.workbook.createSheet() : this.workbook.createSheet(str);
        int i = 0;
        while (true) {
            Object[][] objArr = this.cells;
            if (i >= objArr.length) {
                return createSheet;
            }
            Object[] objArr2 = objArr[i];
            Row createRow = createSheet.createRow(i);
            for (int i2 = 0; i2 < objArr2.length; i2++) {
                Object obj = objArr2[i2];
                if (obj != null || this.shouldCreateEmptyCells) {
                    setCellValue(createRow.createCell(i2), obj);
                }
            }
            i++;
        }
    }

    private void setCellValue(Cell cell, Object obj) {
        if (obj != null && cell != null) {
            if (obj instanceof Number) {
                cell.setCellValue(((Number) obj).doubleValue());
            } else if (obj instanceof Date) {
                cell.setCellValue((Date) obj);
            } else if (obj instanceof Calendar) {
                cell.setCellValue((Calendar) obj);
            } else if (isFormulaDefinition(obj)) {
                cell.setCellFormula(getFormula(obj));
            } else {
                cell.setCellValue(obj.toString());
            }
        }
    }

    private boolean isFormulaDefinition(Object obj) {
        if (!(obj instanceof String)) {
            return false;
        }
        String str = (String) obj;
        if (str.length() < 2 || str.charAt(0) != '=') {
            return false;
        }
        return true;
    }

    private String getFormula(Object obj) {
        return ((String) obj).substring(1);
    }
}
