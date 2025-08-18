package org.apache.poi.ss.formula;

import org.apache.poi.ss.formula.eval.AreaEval;
import org.apache.poi.ss.formula.eval.AreaEvalBase;
import org.apache.poi.ss.formula.eval.BlankEval;
import org.apache.poi.ss.formula.eval.ValueEval;
import org.apache.poi.ss.formula.ptg.AreaI;
import org.apache.poi.ss.util.CellReference;
import org.apache.xmlbeans.impl.common.NameUtil;

public final class CacheAreaEval extends AreaEvalBase {
    private final ValueEval[] _values;

    public CacheAreaEval(AreaI areaI, ValueEval[] valueEvalArr) {
        super(areaI);
        this._values = valueEvalArr;
    }

    public CacheAreaEval(int i, int i2, int i3, int i4, ValueEval[] valueEvalArr) {
        super(i, i2, i3, i4);
        this._values = valueEvalArr;
    }

    public ValueEval getRelativeValue(int i, int i2) {
        return getRelativeValue(-1, i, i2);
    }

    public ValueEval getRelativeValue(int i, int i2, int i3) {
        return this._values[(i2 * getWidth()) + i3];
    }

    public AreaEval offset(int i, int i2, int i3, int i4) {
        ValueEval valueEval;
        int i5;
        AreaI.OffsetArea offsetArea = new AreaI.OffsetArea(getFirstRow(), getFirstColumn(), i, i2, i3, i4);
        int lastRow = (offsetArea.getLastRow() - offsetArea.getFirstRow()) + 1;
        int lastColumn = (offsetArea.getLastColumn() - offsetArea.getFirstColumn()) + 1;
        ValueEval[] valueEvalArr = new ValueEval[(lastRow * lastColumn)];
        int firstRow = offsetArea.getFirstRow() - getFirstRow();
        int firstColumn = offsetArea.getFirstColumn() - getFirstColumn();
        for (int i6 = 0; i6 < lastRow; i6++) {
            for (int i7 = 0; i7 < lastColumn; i7++) {
                int i8 = firstRow + i6;
                if (i8 > getLastRow() || (i5 = firstColumn + i7) > getLastColumn()) {
                    valueEval = BlankEval.instance;
                } else {
                    valueEval = this._values[(i8 * getWidth()) + i5];
                }
                valueEvalArr[(i6 * lastColumn) + i7] = valueEval;
            }
        }
        return new CacheAreaEval(offsetArea, valueEvalArr);
    }

    public TwoDEval getRow(int i) {
        if (i < getHeight()) {
            int firstRow = getFirstRow() + i;
            int width = getWidth();
            ValueEval[] valueEvalArr = new ValueEval[width];
            for (int i2 = 0; i2 < width; i2++) {
                valueEvalArr[i2] = getRelativeValue(i, i2);
            }
            return new CacheAreaEval(firstRow, getFirstColumn(), firstRow, getLastColumn(), valueEvalArr);
        }
        throw new IllegalArgumentException("Invalid rowIndex " + i + ".  Allowable range is (0.." + getHeight() + ").");
    }

    public TwoDEval getColumn(int i) {
        if (i < getWidth()) {
            int firstColumn = getFirstColumn() + i;
            int height = getHeight();
            ValueEval[] valueEvalArr = new ValueEval[height];
            for (int i2 = 0; i2 < height; i2++) {
                valueEvalArr[i2] = getRelativeValue(i2, i);
            }
            return new CacheAreaEval(getFirstRow(), firstColumn, getLastRow(), firstColumn, valueEvalArr);
        }
        throw new IllegalArgumentException("Invalid columnIndex " + i + ".  Allowable range is (0.." + getWidth() + ").");
    }

    public String toString() {
        return getClass().getName() + "[" + new CellReference(getFirstRow(), getFirstColumn()).formatAsString() + NameUtil.COLON + new CellReference(getLastRow(), getLastColumn()).formatAsString() + "]";
    }
}
