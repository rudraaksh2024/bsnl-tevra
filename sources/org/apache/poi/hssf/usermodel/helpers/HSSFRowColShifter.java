package org.apache.poi.hssf.usermodel.helpers;

import java.util.Iterator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.util.Unbox;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFEvaluationWorkbook;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.formula.FormulaParseException;
import org.apache.poi.ss.formula.FormulaParser;
import org.apache.poi.ss.formula.FormulaRenderer;
import org.apache.poi.ss.formula.FormulaShifter;
import org.apache.poi.ss.formula.FormulaType;
import org.apache.poi.ss.formula.ptg.Ptg;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.util.Internal;

@Internal
final class HSSFRowColShifter {
    private static final Logger LOG = LogManager.getLogger((Class<?>) HSSFRowColShifter.class);

    private HSSFRowColShifter() {
    }

    static void updateFormulas(Sheet sheet, FormulaShifter formulaShifter) {
        updateSheetFormulas(sheet, formulaShifter);
        for (Sheet next : sheet.getWorkbook()) {
            if (sheet != next) {
                updateSheetFormulas(next, formulaShifter);
            }
        }
    }

    static void updateSheetFormulas(Sheet sheet, FormulaShifter formulaShifter) {
        Iterator<Row> it = sheet.iterator();
        while (it.hasNext()) {
            updateRowFormulas((HSSFRow) it.next(), formulaShifter);
        }
    }

    static void updateRowFormulas(HSSFRow hSSFRow, FormulaShifter formulaShifter) {
        hSSFRow.getSheet();
        Iterator<Cell> it = hSSFRow.iterator();
        while (it.hasNext()) {
            HSSFCell hSSFCell = (HSSFCell) it.next();
            String cellFormula = hSSFCell.getCellFormula();
            if (cellFormula.length() > 0) {
                hSSFCell.setCellFormula(shiftFormula(hSSFRow, cellFormula, formulaShifter));
            }
        }
    }

    static String shiftFormula(Row row, String str, FormulaShifter formulaShifter) {
        Sheet sheet = row.getSheet();
        Workbook workbook = sheet.getWorkbook();
        int sheetIndex = workbook.getSheetIndex(sheet);
        int rowNum = row.getRowNum();
        HSSFEvaluationWorkbook create = HSSFEvaluationWorkbook.create((HSSFWorkbook) workbook);
        try {
            Ptg[] parse = FormulaParser.parse(str, create, FormulaType.CELL, sheetIndex, rowNum);
            return formulaShifter.adjustFormula(parse, sheetIndex) ? FormulaRenderer.toFormulaString(create, parse) : str;
        } catch (FormulaParseException e) {
            LOG.atWarn().withThrowable(e).log("Error shifting formula on row {}", (Object) Unbox.box(row.getRowNum()));
            return str;
        }
    }
}
