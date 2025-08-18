package org.apache.poi.xssf.usermodel;

import org.apache.poi.ss.formula.FormulaParser;
import org.apache.poi.ss.formula.FormulaRenderer;
import org.apache.poi.ss.formula.FormulaType;
import org.apache.poi.ss.formula.ptg.Ptg;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.RangeCopier;
import org.apache.poi.ss.usermodel.Sheet;

public class XSSFRangeCopier extends RangeCopier {
    public XSSFRangeCopier(Sheet sheet, Sheet sheet2) {
        super(sheet, sheet2);
    }

    public XSSFRangeCopier(Sheet sheet) {
        super(sheet);
    }

    /* access modifiers changed from: protected */
    public void adjustCellReferencesInsideFormula(Cell cell, Sheet sheet, int i, int i2) {
        XSSFWorkbook xSSFWorkbook = (XSSFWorkbook) sheet.getWorkbook();
        XSSFEvaluationWorkbook create = XSSFEvaluationWorkbook.create(xSSFWorkbook);
        Ptg[] parse = FormulaParser.parse(cell.getCellFormula(), create, FormulaType.CELL, 0);
        if (adjustInBothDirections(parse, xSSFWorkbook.getSheetIndex(sheet), i, i2)) {
            cell.setCellFormula(FormulaRenderer.toFormulaString(create, parse));
        }
    }
}
