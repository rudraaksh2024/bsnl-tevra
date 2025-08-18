package org.apache.poi.ss.formula.functions;

import org.apache.poi.ss.formula.eval.AreaEval;
import org.apache.poi.ss.formula.eval.ErrorEval;
import org.apache.poi.ss.formula.eval.EvaluationException;
import org.apache.poi.ss.formula.eval.OperandResolver;
import org.apache.poi.ss.formula.eval.RefEval;
import org.apache.poi.ss.formula.eval.ValueEval;

public final class Offset implements Function {
    private static final int LAST_VALID_COLUMN_INDEX = 255;
    private static final int LAST_VALID_ROW_INDEX = 65535;

    static final class LinearOffsetRange {
        private final int _length;
        private final int _offset;

        public LinearOffsetRange(int i, int i2) {
            if (i2 != 0) {
                this._offset = i;
                this._length = i2;
                return;
            }
            throw new RuntimeException("length may not be zero");
        }

        public short getFirstIndex() {
            return (short) this._offset;
        }

        public short getLastIndex() {
            return (short) ((this._offset + this._length) - 1);
        }

        public LinearOffsetRange normaliseAndTranslate(int i) {
            int i2 = this._length;
            if (i2 <= 0) {
                return new LinearOffsetRange(i + this._offset + i2 + 1, -i2);
            }
            if (i == 0) {
                return this;
            }
            return new LinearOffsetRange(i + this._offset, i2);
        }

        public boolean isOutOfBounds(int i, int i2) {
            return this._offset < i || getLastIndex() > i2;
        }

        public String toString() {
            return getClass().getName() + " [" + this._offset + "..." + getLastIndex() + "]";
        }
    }

    private static final class BaseRef {
        private final AreaEval _areaEval;
        private final int _firstColumnIndex;
        private final int _firstRowIndex;
        private final int _height;
        private final RefEval _refEval;
        private final int _width;

        public BaseRef(RefEval refEval) {
            this._refEval = refEval;
            this._areaEval = null;
            this._firstRowIndex = refEval.getRow();
            this._firstColumnIndex = refEval.getColumn();
            this._height = 1;
            this._width = 1;
        }

        public BaseRef(AreaEval areaEval) {
            this._refEval = null;
            this._areaEval = areaEval;
            this._firstRowIndex = areaEval.getFirstRow();
            this._firstColumnIndex = areaEval.getFirstColumn();
            this._height = (areaEval.getLastRow() - areaEval.getFirstRow()) + 1;
            this._width = (areaEval.getLastColumn() - areaEval.getFirstColumn()) + 1;
        }

        public int getWidth() {
            return this._width;
        }

        public int getHeight() {
            return this._height;
        }

        public int getFirstRowIndex() {
            return this._firstRowIndex;
        }

        public int getFirstColumnIndex() {
            return this._firstColumnIndex;
        }

        public AreaEval offset(int i, int i2, int i3, int i4) {
            RefEval refEval = this._refEval;
            if (refEval == null) {
                return this._areaEval.offset(i, i2, i3, i4);
            }
            return refEval.offset(i, i2, i3, i4);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:25:0x004f A[Catch:{ EvaluationException -> 0x0064 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public org.apache.poi.ss.formula.eval.ValueEval evaluate(org.apache.poi.ss.formula.eval.ValueEval[] r8, int r9, int r10) {
        /*
            r7 = this;
            int r7 = r8.length
            r0 = 1
            if (r7 < r0) goto L_0x006a
            int r7 = r8.length
            r1 = 5
            if (r7 <= r1) goto L_0x0009
            goto L_0x006a
        L_0x0009:
            r7 = 0
            r2 = r8[r7]     // Catch:{ EvaluationException -> 0x0064 }
            org.apache.poi.ss.formula.functions.Offset$BaseRef r2 = evaluateBaseRef(r2)     // Catch:{ EvaluationException -> 0x0064 }
            r0 = r8[r0]     // Catch:{ EvaluationException -> 0x0064 }
            boolean r3 = r0 instanceof org.apache.poi.ss.formula.eval.MissingArgEval     // Catch:{ EvaluationException -> 0x0064 }
            if (r3 == 0) goto L_0x0018
            r0 = r7
            goto L_0x001c
        L_0x0018:
            int r0 = evaluateIntArg(r0, r9, r10)     // Catch:{ EvaluationException -> 0x0064 }
        L_0x001c:
            r3 = 2
            r3 = r8[r3]     // Catch:{ EvaluationException -> 0x0064 }
            boolean r4 = r3 instanceof org.apache.poi.ss.formula.eval.MissingArgEval     // Catch:{ EvaluationException -> 0x0064 }
            if (r4 == 0) goto L_0x0024
            goto L_0x0028
        L_0x0024:
            int r7 = evaluateIntArg(r3, r9, r10)     // Catch:{ EvaluationException -> 0x0064 }
        L_0x0028:
            int r3 = r2.getHeight()     // Catch:{ EvaluationException -> 0x0064 }
            int r4 = r2.getWidth()     // Catch:{ EvaluationException -> 0x0064 }
            int r5 = r8.length     // Catch:{ EvaluationException -> 0x0064 }
            r6 = 4
            if (r5 == r6) goto L_0x0042
            if (r5 == r1) goto L_0x0037
            goto L_0x004d
        L_0x0037:
            r1 = r8[r6]     // Catch:{ EvaluationException -> 0x0064 }
            boolean r5 = r1 instanceof org.apache.poi.ss.formula.eval.MissingArgEval     // Catch:{ EvaluationException -> 0x0064 }
            if (r5 != 0) goto L_0x0042
            int r1 = evaluateIntArg(r1, r9, r10)     // Catch:{ EvaluationException -> 0x0064 }
            r4 = r1
        L_0x0042:
            r1 = 3
            r8 = r8[r1]     // Catch:{ EvaluationException -> 0x0064 }
            boolean r1 = r8 instanceof org.apache.poi.ss.formula.eval.MissingArgEval     // Catch:{ EvaluationException -> 0x0064 }
            if (r1 != 0) goto L_0x004d
            int r3 = evaluateIntArg(r8, r9, r10)     // Catch:{ EvaluationException -> 0x0064 }
        L_0x004d:
            if (r3 == 0) goto L_0x0061
            if (r4 != 0) goto L_0x0052
            goto L_0x0061
        L_0x0052:
            org.apache.poi.ss.formula.functions.Offset$LinearOffsetRange r8 = new org.apache.poi.ss.formula.functions.Offset$LinearOffsetRange     // Catch:{ EvaluationException -> 0x0064 }
            r8.<init>(r0, r3)     // Catch:{ EvaluationException -> 0x0064 }
            org.apache.poi.ss.formula.functions.Offset$LinearOffsetRange r9 = new org.apache.poi.ss.formula.functions.Offset$LinearOffsetRange     // Catch:{ EvaluationException -> 0x0064 }
            r9.<init>(r7, r4)     // Catch:{ EvaluationException -> 0x0064 }
            org.apache.poi.ss.formula.eval.AreaEval r7 = createOffset(r2, r8, r9)     // Catch:{ EvaluationException -> 0x0064 }
            return r7
        L_0x0061:
            org.apache.poi.ss.formula.eval.ErrorEval r7 = org.apache.poi.ss.formula.eval.ErrorEval.REF_INVALID     // Catch:{ EvaluationException -> 0x0064 }
            return r7
        L_0x0064:
            r7 = move-exception
            org.apache.poi.ss.formula.eval.ErrorEval r7 = r7.getErrorEval()
            return r7
        L_0x006a:
            org.apache.poi.ss.formula.eval.ErrorEval r7 = org.apache.poi.ss.formula.eval.ErrorEval.VALUE_INVALID
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.ss.formula.functions.Offset.evaluate(org.apache.poi.ss.formula.eval.ValueEval[], int, int):org.apache.poi.ss.formula.eval.ValueEval");
    }

    private static AreaEval createOffset(BaseRef baseRef, LinearOffsetRange linearOffsetRange, LinearOffsetRange linearOffsetRange2) throws EvaluationException {
        LinearOffsetRange normaliseAndTranslate = linearOffsetRange.normaliseAndTranslate(baseRef.getFirstRowIndex());
        LinearOffsetRange normaliseAndTranslate2 = linearOffsetRange2.normaliseAndTranslate(baseRef.getFirstColumnIndex());
        if (normaliseAndTranslate.isOutOfBounds(0, 65535)) {
            throw new EvaluationException(ErrorEval.REF_INVALID);
        } else if (!normaliseAndTranslate2.isOutOfBounds(0, 255)) {
            return baseRef.offset(linearOffsetRange.getFirstIndex(), linearOffsetRange.getLastIndex(), linearOffsetRange2.getFirstIndex(), linearOffsetRange2.getLastIndex());
        } else {
            throw new EvaluationException(ErrorEval.REF_INVALID);
        }
    }

    private static BaseRef evaluateBaseRef(ValueEval valueEval) throws EvaluationException {
        if (valueEval instanceof RefEval) {
            return new BaseRef((RefEval) valueEval);
        }
        if (valueEval instanceof AreaEval) {
            return new BaseRef((AreaEval) valueEval);
        }
        if (valueEval instanceof ErrorEval) {
            throw new EvaluationException((ErrorEval) valueEval);
        }
        throw new EvaluationException(ErrorEval.VALUE_INVALID);
    }

    static int evaluateIntArg(ValueEval valueEval, int i, int i2) throws EvaluationException {
        return OperandResolver.coerceValueToInt(OperandResolver.getSingleValue(valueEval, i, i2));
    }
}
