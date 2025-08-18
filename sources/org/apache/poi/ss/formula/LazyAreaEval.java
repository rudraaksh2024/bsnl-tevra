package org.apache.poi.ss.formula;

import org.apache.poi.ss.formula.eval.AreaEval;
import org.apache.poi.ss.formula.eval.AreaEvalBase;
import org.apache.poi.ss.formula.eval.ValueEval;
import org.apache.poi.ss.formula.ptg.AreaI;
import org.apache.poi.ss.util.CellReference;
import org.apache.xmlbeans.impl.common.NameUtil;

final class LazyAreaEval extends AreaEvalBase {
    private final SheetRangeEvaluator _evaluator;

    LazyAreaEval(AreaI areaI, SheetRangeEvaluator sheetRangeEvaluator) {
        super(areaI, sheetRangeEvaluator);
        this._evaluator = sheetRangeEvaluator;
    }

    public LazyAreaEval(int i, int i2, int i3, int i4, SheetRangeEvaluator sheetRangeEvaluator) {
        super(sheetRangeEvaluator, i, i2, sheetRangeEvaluator.adjustRowNumber(i3), i4);
        this._evaluator = sheetRangeEvaluator;
    }

    public ValueEval getRelativeValue(int i, int i2) {
        return getRelativeValue(getFirstSheetIndex(), i, i2);
    }

    public ValueEval getRelativeValue(int i, int i2, int i3) {
        return this._evaluator.getEvalForCell(i, i2 + getFirstRow(), i3 + getFirstColumn());
    }

    public AreaEval offset(int i, int i2, int i3, int i4) {
        return new LazyAreaEval(new AreaI.OffsetArea(getFirstRow(), getFirstColumn(), i, i2, i3, i4), this._evaluator);
    }

    public LazyAreaEval getRow(int i) {
        if (i < getHeight()) {
            int firstRow = getFirstRow() + i;
            return new LazyAreaEval(firstRow, getFirstColumn(), firstRow, getLastColumn(), this._evaluator);
        }
        throw new IllegalArgumentException("Invalid rowIndex " + i + ".  Allowable range is (0.." + getHeight() + ").");
    }

    public LazyAreaEval getColumn(int i) {
        if (i < getWidth()) {
            int firstColumn = getFirstColumn() + i;
            return new LazyAreaEval(getFirstRow(), firstColumn, getLastRow(), firstColumn, this._evaluator);
        }
        throw new IllegalArgumentException("Invalid columnIndex " + i + ".  Allowable range is (0.." + getWidth() + ").");
    }

    public String toString() {
        return getClass().getName() + "[" + this._evaluator.getSheetNameRange() + '!' + new CellReference(getFirstRow(), getFirstColumn()).formatAsString() + NameUtil.COLON + new CellReference(getLastRow(), getLastColumn()).formatAsString() + "]";
    }

    public boolean isSubTotal(int i, int i2) {
        SheetRangeEvaluator sheetRangeEvaluator = this._evaluator;
        return sheetRangeEvaluator.getSheetEvaluator(sheetRangeEvaluator.getFirstSheetIndex()).isSubTotal(getFirstRow() + i, getFirstColumn() + i2);
    }

    public boolean isRowHidden(int i) {
        SheetRangeEvaluator sheetRangeEvaluator = this._evaluator;
        return sheetRangeEvaluator.getSheetEvaluator(sheetRangeEvaluator.getFirstSheetIndex()).isRowHidden(getFirstRow() + i);
    }
}
