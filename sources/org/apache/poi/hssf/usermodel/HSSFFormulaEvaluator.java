package org.apache.poi.hssf.usermodel;

import java.util.Map;
import org.apache.poi.ss.formula.BaseFormulaEvaluator;
import org.apache.poi.ss.formula.CollaboratingWorkbooksEnvironment;
import org.apache.poi.ss.formula.IStabilityClassifier;
import org.apache.poi.ss.formula.WorkbookEvaluator;
import org.apache.poi.ss.formula.eval.BoolEval;
import org.apache.poi.ss.formula.eval.ErrorEval;
import org.apache.poi.ss.formula.eval.NumericValueEval;
import org.apache.poi.ss.formula.eval.StringValueEval;
import org.apache.poi.ss.formula.eval.ValueEval;
import org.apache.poi.ss.formula.udf.UDFFinder;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellValue;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.RichTextString;
import org.apache.poi.ss.usermodel.Workbook;

public class HSSFFormulaEvaluator extends BaseFormulaEvaluator {
    private final HSSFWorkbook _book;

    public HSSFFormulaEvaluator(HSSFWorkbook hSSFWorkbook) {
        this(hSSFWorkbook, (IStabilityClassifier) null);
    }

    public HSSFFormulaEvaluator(HSSFWorkbook hSSFWorkbook, IStabilityClassifier iStabilityClassifier) {
        this(hSSFWorkbook, iStabilityClassifier, (UDFFinder) null);
    }

    private HSSFFormulaEvaluator(HSSFWorkbook hSSFWorkbook, IStabilityClassifier iStabilityClassifier, UDFFinder uDFFinder) {
        super(new WorkbookEvaluator(HSSFEvaluationWorkbook.create(hSSFWorkbook), iStabilityClassifier, uDFFinder));
        this._book = hSSFWorkbook;
    }

    public static HSSFFormulaEvaluator create(HSSFWorkbook hSSFWorkbook, IStabilityClassifier iStabilityClassifier, UDFFinder uDFFinder) {
        return new HSSFFormulaEvaluator(hSSFWorkbook, iStabilityClassifier, uDFFinder);
    }

    /* access modifiers changed from: protected */
    public RichTextString createRichTextString(String str) {
        return new HSSFRichTextString(str);
    }

    public static void setupEnvironment(String[] strArr, HSSFFormulaEvaluator[] hSSFFormulaEvaluatorArr) {
        BaseFormulaEvaluator.setupEnvironment(strArr, hSSFFormulaEvaluatorArr);
    }

    public void setupReferencedWorkbooks(Map<String, FormulaEvaluator> map) {
        CollaboratingWorkbooksEnvironment.setupFormulaEvaluator(map);
    }

    public void notifyUpdateCell(HSSFCell hSSFCell) {
        this._bookEvaluator.notifyUpdateCell(new HSSFEvaluationCell(hSSFCell));
    }

    public void notifyUpdateCell(Cell cell) {
        this._bookEvaluator.notifyUpdateCell(new HSSFEvaluationCell((HSSFCell) cell));
    }

    public void notifyDeleteCell(HSSFCell hSSFCell) {
        this._bookEvaluator.notifyDeleteCell(new HSSFEvaluationCell(hSSFCell));
    }

    public void notifyDeleteCell(Cell cell) {
        this._bookEvaluator.notifyDeleteCell(new HSSFEvaluationCell((HSSFCell) cell));
    }

    public void notifySetFormula(Cell cell) {
        this._bookEvaluator.notifyUpdateCell(new HSSFEvaluationCell((HSSFCell) cell));
    }

    public HSSFCell evaluateInCell(Cell cell) {
        return (HSSFCell) super.evaluateInCell(cell);
    }

    public static void evaluateAllFormulaCells(HSSFWorkbook hSSFWorkbook) {
        evaluateAllFormulaCells(hSSFWorkbook, new HSSFFormulaEvaluator(hSSFWorkbook));
    }

    public static void evaluateAllFormulaCells(Workbook workbook) {
        BaseFormulaEvaluator.evaluateAllFormulaCells(workbook);
    }

    public void evaluateAll() {
        evaluateAllFormulaCells(this._book, this);
    }

    /* access modifiers changed from: protected */
    public CellValue evaluateFormulaCellValue(Cell cell) {
        ValueEval evaluate = this._bookEvaluator.evaluate(new HSSFEvaluationCell((HSSFCell) cell));
        if (evaluate instanceof BoolEval) {
            return CellValue.valueOf(((BoolEval) evaluate).getBooleanValue());
        }
        if (evaluate instanceof NumericValueEval) {
            return new CellValue(((NumericValueEval) evaluate).getNumberValue());
        }
        if (evaluate instanceof StringValueEval) {
            return new CellValue(((StringValueEval) evaluate).getStringValue());
        }
        if (evaluate instanceof ErrorEval) {
            return CellValue.getError(((ErrorEval) evaluate).getErrorCode());
        }
        throw new RuntimeException("Unexpected eval class (" + evaluate.getClass().getName() + ")");
    }

    public void setIgnoreMissingWorkbooks(boolean z) {
        this._bookEvaluator.setIgnoreMissingWorkbooks(z);
    }

    public void setDebugEvaluationOutputForNextEval(boolean z) {
        this._bookEvaluator.setDebugEvaluationOutputForNextEval(z);
    }
}
