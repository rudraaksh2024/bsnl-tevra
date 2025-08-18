package org.apache.poi.xssf.usermodel;

import org.apache.poi.ss.formula.BaseFormulaEvaluator;
import org.apache.poi.ss.formula.EvaluationCell;
import org.apache.poi.ss.formula.IStabilityClassifier;
import org.apache.poi.ss.formula.WorkbookEvaluator;
import org.apache.poi.ss.formula.udf.UDFFinder;
import org.apache.poi.ss.usermodel.Cell;

public final class XSSFFormulaEvaluator extends BaseXSSFFormulaEvaluator {
    private final XSSFWorkbook _book;

    public XSSFFormulaEvaluator(XSSFWorkbook xSSFWorkbook) {
        this(xSSFWorkbook, (IStabilityClassifier) null, (UDFFinder) null);
    }

    private XSSFFormulaEvaluator(XSSFWorkbook xSSFWorkbook, IStabilityClassifier iStabilityClassifier, UDFFinder uDFFinder) {
        this(xSSFWorkbook, new WorkbookEvaluator(XSSFEvaluationWorkbook.create(xSSFWorkbook), iStabilityClassifier, uDFFinder));
    }

    protected XSSFFormulaEvaluator(XSSFWorkbook xSSFWorkbook, WorkbookEvaluator workbookEvaluator) {
        super(workbookEvaluator);
        this._book = xSSFWorkbook;
    }

    public static XSSFFormulaEvaluator create(XSSFWorkbook xSSFWorkbook, IStabilityClassifier iStabilityClassifier, UDFFinder uDFFinder) {
        return new XSSFFormulaEvaluator(xSSFWorkbook, iStabilityClassifier, uDFFinder);
    }

    public void notifySetFormula(Cell cell) {
        this._bookEvaluator.notifyUpdateCell(new XSSFEvaluationCell((XSSFCell) cell));
    }

    public void notifyDeleteCell(Cell cell) {
        this._bookEvaluator.notifyDeleteCell(new XSSFEvaluationCell((XSSFCell) cell));
    }

    public void notifyUpdateCell(Cell cell) {
        this._bookEvaluator.notifyUpdateCell(new XSSFEvaluationCell((XSSFCell) cell));
    }

    public static void evaluateAllFormulaCells(XSSFWorkbook xSSFWorkbook) {
        BaseFormulaEvaluator.evaluateAllFormulaCells(xSSFWorkbook);
    }

    public XSSFCell evaluateInCell(Cell cell) {
        return (XSSFCell) super.evaluateInCell(cell);
    }

    public void evaluateAll() {
        evaluateAllFormulaCells(this._book, this);
    }

    /* access modifiers changed from: protected */
    public EvaluationCell toEvaluationCell(Cell cell) {
        if (cell instanceof XSSFCell) {
            return new XSSFEvaluationCell((XSSFCell) cell);
        }
        throw new IllegalArgumentException("Unexpected type of cell: " + cell.getClass() + ". Only XSSFCells can be evaluated.");
    }
}
