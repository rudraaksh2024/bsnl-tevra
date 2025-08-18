package org.apache.poi.xssf.usermodel;

import java.util.HashMap;
import java.util.Map;
import org.apache.poi.ss.formula.EvaluationCell;
import org.apache.poi.ss.formula.EvaluationSheet;
import org.apache.poi.ss.formula.FormulaParser;
import org.apache.poi.ss.formula.FormulaType;
import org.apache.poi.ss.formula.ptg.Ptg;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.util.Internal;

@Internal
public final class XSSFEvaluationWorkbook extends BaseXSSFEvaluationWorkbook {
    private final Map<XSSFSheet, XSSFEvaluationSheet> _sheetCache = new HashMap();

    public static XSSFEvaluationWorkbook create(XSSFWorkbook xSSFWorkbook) {
        if (xSSFWorkbook == null) {
            return null;
        }
        return new XSSFEvaluationWorkbook(xSSFWorkbook);
    }

    private XSSFEvaluationWorkbook(XSSFWorkbook xSSFWorkbook) {
        super(xSSFWorkbook);
    }

    public void clearAllCachedResultValues() {
        super.clearAllCachedResultValues();
        this._sheetCache.clear();
    }

    public int getSheetIndex(EvaluationSheet evaluationSheet) {
        return this._uBook.getSheetIndex((Sheet) ((XSSFEvaluationSheet) evaluationSheet).getXSSFSheet());
    }

    public EvaluationSheet getSheet(int i) {
        if (i < 0 || i >= this._uBook.getNumberOfSheets()) {
            this._uBook.getSheetAt(i);
        }
        XSSFSheet sheetAt = this._uBook.getSheetAt(i);
        return this._sheetCache.computeIfAbsent(sheetAt, new XSSFEvaluationWorkbook$$ExternalSyntheticLambda0(sheetAt));
    }

    static /* synthetic */ XSSFEvaluationSheet lambda$getSheet$0(XSSFSheet xSSFSheet, XSSFSheet xSSFSheet2) {
        return new XSSFEvaluationSheet(xSSFSheet);
    }

    public Ptg[] getFormulaTokens(EvaluationCell evaluationCell) {
        XSSFCell xSSFCell = ((XSSFEvaluationCell) evaluationCell).getXSSFCell();
        return FormulaParser.parse(xSSFCell.getCellFormula(this), this, FormulaType.CELL, this._uBook.getSheetIndex((Sheet) xSSFCell.getSheet()), xSSFCell.getRowIndex());
    }
}
