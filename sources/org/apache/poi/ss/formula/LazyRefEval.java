package org.apache.poi.ss.formula;

import org.apache.poi.ss.formula.eval.AreaEval;
import org.apache.poi.ss.formula.eval.RefEvalBase;
import org.apache.poi.ss.formula.eval.ValueEval;
import org.apache.poi.ss.formula.ptg.AreaI;
import org.apache.poi.ss.util.CellReference;

public final class LazyRefEval extends RefEvalBase {
    private final SheetRangeEvaluator _evaluator;

    public LazyRefEval(int i, int i2, SheetRangeEvaluator sheetRangeEvaluator) {
        super((SheetRange) sheetRangeEvaluator, i, i2);
        this._evaluator = sheetRangeEvaluator;
    }

    public ValueEval getInnerValueEval(int i) {
        return this._evaluator.getEvalForCell(i, getRow(), getColumn());
    }

    public AreaEval offset(int i, int i2, int i3, int i4) {
        return new LazyAreaEval(new AreaI.OffsetArea(getRow(), getColumn(), i, i2, i3, i4), this._evaluator);
    }

    public boolean isSubTotal() {
        return this._evaluator.getSheetEvaluator(getFirstSheetIndex()).isSubTotal(getRow(), getColumn());
    }

    public boolean isRowHidden() {
        SheetRangeEvaluator sheetRangeEvaluator = this._evaluator;
        return sheetRangeEvaluator.getSheetEvaluator(sheetRangeEvaluator.getFirstSheetIndex()).isRowHidden(getRow());
    }

    public String toString() {
        return getClass().getName() + "[" + this._evaluator.getSheetNameRange() + '!' + new CellReference(getRow(), getColumn()).formatAsString() + "]";
    }
}
