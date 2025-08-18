package org.apache.poi.xssf.streaming;

import org.apache.poi.ss.formula.EvaluationCell;
import org.apache.poi.ss.formula.EvaluationSheet;
import org.apache.poi.ss.formula.FormulaParser;
import org.apache.poi.ss.formula.FormulaType;
import org.apache.poi.ss.formula.ptg.Ptg;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.util.Internal;
import org.apache.poi.xssf.usermodel.BaseXSSFEvaluationWorkbook;

@Internal
public final class SXSSFEvaluationWorkbook extends BaseXSSFEvaluationWorkbook {
    private final SXSSFWorkbook _sxssfBook;

    public static SXSSFEvaluationWorkbook create(SXSSFWorkbook sXSSFWorkbook) {
        if (sXSSFWorkbook == null) {
            return null;
        }
        return new SXSSFEvaluationWorkbook(sXSSFWorkbook);
    }

    private SXSSFEvaluationWorkbook(SXSSFWorkbook sXSSFWorkbook) {
        super(sXSSFWorkbook.getXSSFWorkbook());
        this._sxssfBook = sXSSFWorkbook;
    }

    public int getSheetIndex(EvaluationSheet evaluationSheet) {
        return this._sxssfBook.getSheetIndex((Sheet) ((SXSSFEvaluationSheet) evaluationSheet).getSXSSFSheet());
    }

    public EvaluationSheet getSheet(int i) {
        return new SXSSFEvaluationSheet(this._sxssfBook.getSheetAt(i));
    }

    public Ptg[] getFormulaTokens(EvaluationCell evaluationCell) {
        SXSSFCell sXSSFCell = ((SXSSFEvaluationCell) evaluationCell).getSXSSFCell();
        return FormulaParser.parse(sXSSFCell.getCellFormula(), this, FormulaType.CELL, this._sxssfBook.getSheetIndex((Sheet) sXSSFCell.getSheet()));
    }
}
