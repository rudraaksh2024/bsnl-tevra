package org.apache.poi.xssf.streaming;

import java.util.Iterator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.util.Unbox;
import org.apache.poi.ss.formula.EvaluationCell;
import org.apache.poi.ss.formula.IStabilityClassifier;
import org.apache.poi.ss.formula.WorkbookEvaluator;
import org.apache.poi.ss.formula.udf.UDFFinder;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.BaseXSSFFormulaEvaluator;

public final class SXSSFFormulaEvaluator extends BaseXSSFFormulaEvaluator {
    private static final Logger LOG = LogManager.getLogger((Class<?>) SXSSFFormulaEvaluator.class);
    private final SXSSFWorkbook wb;

    public SXSSFFormulaEvaluator(SXSSFWorkbook sXSSFWorkbook) {
        this(sXSSFWorkbook, (IStabilityClassifier) null, (UDFFinder) null);
    }

    private SXSSFFormulaEvaluator(SXSSFWorkbook sXSSFWorkbook, IStabilityClassifier iStabilityClassifier, UDFFinder uDFFinder) {
        this(sXSSFWorkbook, new WorkbookEvaluator(SXSSFEvaluationWorkbook.create(sXSSFWorkbook), iStabilityClassifier, uDFFinder));
    }

    private SXSSFFormulaEvaluator(SXSSFWorkbook sXSSFWorkbook, WorkbookEvaluator workbookEvaluator) {
        super(workbookEvaluator);
        this.wb = sXSSFWorkbook;
    }

    public static SXSSFFormulaEvaluator create(SXSSFWorkbook sXSSFWorkbook, IStabilityClassifier iStabilityClassifier, UDFFinder uDFFinder) {
        return new SXSSFFormulaEvaluator(sXSSFWorkbook, iStabilityClassifier, uDFFinder);
    }

    public void notifySetFormula(Cell cell) {
        this._bookEvaluator.notifyUpdateCell(new SXSSFEvaluationCell((SXSSFCell) cell));
    }

    public void notifyDeleteCell(Cell cell) {
        this._bookEvaluator.notifyDeleteCell(new SXSSFEvaluationCell((SXSSFCell) cell));
    }

    public void notifyUpdateCell(Cell cell) {
        this._bookEvaluator.notifyUpdateCell(new SXSSFEvaluationCell((SXSSFCell) cell));
    }

    /* access modifiers changed from: protected */
    public EvaluationCell toEvaluationCell(Cell cell) {
        if (cell instanceof SXSSFCell) {
            return new SXSSFEvaluationCell((SXSSFCell) cell);
        }
        throw new IllegalArgumentException("Unexpected type of cell: " + cell.getClass() + ". Only SXSSFCells can be evaluated.");
    }

    public SXSSFCell evaluateInCell(Cell cell) {
        return (SXSSFCell) super.evaluateInCell(cell);
    }

    public static void evaluateAllFormulaCells(SXSSFWorkbook sXSSFWorkbook, boolean z) {
        int lastFlushedRowNum;
        SXSSFFormulaEvaluator sXSSFFormulaEvaluator = new SXSSFFormulaEvaluator(sXSSFWorkbook);
        Iterator<Sheet> it = sXSSFWorkbook.iterator();
        while (it.hasNext()) {
            if (((SXSSFSheet) it.next()).areAllRowsFlushed()) {
                throw new SheetsFlushedException();
            }
        }
        Iterator<Sheet> it2 = sXSSFWorkbook.iterator();
        while (it2.hasNext()) {
            Sheet<Row> next = it2.next();
            if ((next instanceof SXSSFSheet) && (lastFlushedRowNum = ((SXSSFSheet) next).getLastFlushedRowNum()) > -1) {
                if (z) {
                    LOG.atInfo().log("Rows up to {} have already been flushed, skipping", (Object) Unbox.box(lastFlushedRowNum));
                } else {
                    throw new RowFlushedException(0, lastFlushedRowNum);
                }
            }
            for (Row it3 : next) {
                for (Cell next2 : it3) {
                    if (next2.getCellType() == CellType.FORMULA) {
                        sXSSFFormulaEvaluator.evaluateFormulaCell(next2);
                    }
                }
            }
        }
    }

    public void evaluateAll() {
        evaluateAllFormulaCells(this.wb, false);
    }

    public static class SheetsFlushedException extends IllegalStateException {
        protected SheetsFlushedException() {
            super("One or more sheets have been flushed, cannot evaluate all cells");
        }
    }

    public static class RowFlushedException extends IllegalStateException {
        protected RowFlushedException(int i, int i2) {
            super("Row " + i + " has been flushed (rows up to " + i2 + " have been flushed), cannot evaluate all cells");
        }
    }
}
