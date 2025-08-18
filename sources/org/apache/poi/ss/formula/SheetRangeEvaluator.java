package org.apache.poi.ss.formula;

import org.apache.poi.ss.formula.eval.ValueEval;
import org.apache.xmlbeans.impl.common.NameUtil;

final class SheetRangeEvaluator implements SheetRange {
    private final int _firstSheetIndex;
    private final int _lastSheetIndex;
    private final SheetRefEvaluator[] _sheetEvaluators;

    public SheetRangeEvaluator(int i, int i2, SheetRefEvaluator[] sheetRefEvaluatorArr) {
        if (i < 0) {
            throw new IllegalArgumentException("Invalid firstSheetIndex: " + i + ".");
        } else if (i2 >= i) {
            this._firstSheetIndex = i;
            this._lastSheetIndex = i2;
            this._sheetEvaluators = (SheetRefEvaluator[]) sheetRefEvaluatorArr.clone();
        } else {
            throw new IllegalArgumentException("Invalid lastSheetIndex: " + i2 + " for firstSheetIndex: " + i + ".");
        }
    }

    public SheetRangeEvaluator(int i, SheetRefEvaluator sheetRefEvaluator) {
        this(i, i, new SheetRefEvaluator[]{sheetRefEvaluator});
    }

    public SheetRefEvaluator getSheetEvaluator(int i) {
        int i2 = this._firstSheetIndex;
        if (i >= i2 && i <= this._lastSheetIndex) {
            return this._sheetEvaluators[i - i2];
        }
        throw new IllegalArgumentException("Invalid SheetIndex: " + i + " - Outside range " + this._firstSheetIndex + " : " + this._lastSheetIndex);
    }

    public int getFirstSheetIndex() {
        return this._firstSheetIndex;
    }

    public int getLastSheetIndex() {
        return this._lastSheetIndex;
    }

    public String getSheetName(int i) {
        return getSheetEvaluator(i).getSheetName();
    }

    public String getSheetNameRange() {
        StringBuilder sb = new StringBuilder();
        sb.append(getSheetName(this._firstSheetIndex));
        if (this._firstSheetIndex != this._lastSheetIndex) {
            sb.append(NameUtil.COLON);
            sb.append(getSheetName(this._lastSheetIndex));
        }
        return sb.toString();
    }

    public ValueEval getEvalForCell(int i, int i2, int i3) {
        return getSheetEvaluator(i).getEvalForCell(i2, i3);
    }

    public int adjustRowNumber(int i) {
        int i2 = i;
        for (int i3 = this._firstSheetIndex; i3 < this._lastSheetIndex; i3++) {
            i2 = Math.max(i2, this._sheetEvaluators[i3].getLastRowNum());
        }
        return Math.min(i, i2);
    }
}
