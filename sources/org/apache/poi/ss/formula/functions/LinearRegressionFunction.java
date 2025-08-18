package org.apache.poi.ss.formula.functions;

import org.apache.poi.ss.formula.TwoDEval;
import org.apache.poi.ss.formula.eval.ErrorEval;
import org.apache.poi.ss.formula.eval.EvaluationException;
import org.apache.poi.ss.formula.eval.NumberEval;
import org.apache.poi.ss.formula.eval.RefEval;
import org.apache.poi.ss.formula.eval.ValueEval;
import org.apache.poi.ss.formula.functions.LookupUtils;

public final class LinearRegressionFunction extends Fixed2ArgFunction {
    private final FUNCTION function;

    public enum FUNCTION {
        INTERCEPT,
        SLOPE
    }

    private static abstract class ValueArray implements LookupUtils.ValueVector {
        private final int _size;

        /* access modifiers changed from: protected */
        public abstract ValueEval getItemInternal(int i);

        protected ValueArray(int i) {
            this._size = i;
        }

        public ValueEval getItem(int i) {
            if (i >= 0 && i <= this._size) {
                return getItemInternal(i);
            }
            throw new IllegalArgumentException("Specified index " + i + " is outside range (0.." + (this._size - 1) + ")");
        }

        public final int getSize() {
            return this._size;
        }
    }

    private static final class SingleCellValueArray extends ValueArray {
        private final ValueEval _value;

        public SingleCellValueArray(ValueEval valueEval) {
            super(1);
            this._value = valueEval;
        }

        /* access modifiers changed from: protected */
        public ValueEval getItemInternal(int i) {
            return this._value;
        }
    }

    private static final class RefValueArray extends ValueArray {
        private final RefEval _ref;
        private final int _width;

        public RefValueArray(RefEval refEval) {
            super(refEval.getNumberOfSheets());
            this._ref = refEval;
            this._width = refEval.getNumberOfSheets();
        }

        /* access modifiers changed from: protected */
        public ValueEval getItemInternal(int i) {
            return this._ref.getInnerValueEval((i % this._width) + this._ref.getFirstSheetIndex());
        }
    }

    private static final class AreaValueArray extends ValueArray {
        private final TwoDEval _ae;
        private final int _width;

        public AreaValueArray(TwoDEval twoDEval) {
            super(twoDEval.getWidth() * twoDEval.getHeight());
            this._ae = twoDEval;
            this._width = twoDEval.getWidth();
        }

        /* access modifiers changed from: protected */
        public ValueEval getItemInternal(int i) {
            int i2 = this._width;
            return this._ae.getValue(i / i2, i % i2);
        }
    }

    public LinearRegressionFunction(FUNCTION function2) {
        this.function = function2;
    }

    public ValueEval evaluate(int i, int i2, ValueEval valueEval, ValueEval valueEval2) {
        try {
            LookupUtils.ValueVector createValueVector = createValueVector(valueEval);
            LookupUtils.ValueVector createValueVector2 = createValueVector(valueEval2);
            int size = createValueVector2.getSize();
            if (size != 0) {
                if (createValueVector.getSize() == size) {
                    double evaluateInternal = evaluateInternal(createValueVector2, createValueVector, size);
                    if (Double.isNaN(evaluateInternal) || Double.isInfinite(evaluateInternal)) {
                        return ErrorEval.NUM_ERROR;
                    }
                    return new NumberEval(evaluateInternal);
                }
            }
            return ErrorEval.NA;
        } catch (EvaluationException e) {
            return e.getErrorEval();
        }
    }

    private double evaluateInternal(LookupUtils.ValueVector valueVector, LookupUtils.ValueVector valueVector2, int i) throws EvaluationException {
        LookupUtils.ValueVector valueVector3 = valueVector;
        LookupUtils.ValueVector valueVector4 = valueVector2;
        int i2 = i;
        ErrorEval errorEval = null;
        int i3 = 0;
        boolean z = false;
        double d = 0.0d;
        double d2 = 0.0d;
        while (i3 < i2) {
            ValueEval item = valueVector3.getItem(i3);
            ValueEval item2 = valueVector4.getItem(i3);
            if (!(item instanceof ErrorEval)) {
                if ((item2 instanceof ErrorEval) && errorEval == null) {
                    errorEval = (ErrorEval) item2;
                } else if ((item instanceof NumberEval) && (item2 instanceof NumberEval)) {
                    d += ((NumberEval) item).getNumberValue();
                    d2 += ((NumberEval) item2).getNumberValue();
                    z = true;
                }
                i3++;
            } else {
                throw new EvaluationException((ErrorEval) item);
            }
        }
        if (errorEval != null) {
            throw new EvaluationException(errorEval);
        } else if (z) {
            double d3 = (double) i2;
            double d4 = d / d3;
            double d5 = d2 / d3;
            double d6 = 0.0d;
            double d7 = 0.0d;
            for (int i4 = 0; i4 < i2; i4++) {
                ValueEval item3 = valueVector3.getItem(i4);
                ValueEval item4 = valueVector4.getItem(i4);
                if ((item3 instanceof NumberEval) && (item4 instanceof NumberEval)) {
                    NumberEval numberEval = (NumberEval) item3;
                    d6 += (numberEval.getNumberValue() - d4) * (numberEval.getNumberValue() - d4);
                    d7 += (numberEval.getNumberValue() - d4) * (((NumberEval) item4).getNumberValue() - d5);
                }
            }
            if (d6 != 0.0d) {
                double d8 = d7 / d6;
                return this.function == FUNCTION.INTERCEPT ? d5 - (d4 * d8) : d8;
            }
            throw new EvaluationException(ErrorEval.DIV_ZERO);
        } else {
            throw new EvaluationException(ErrorEval.DIV_ZERO);
        }
    }

    private static LookupUtils.ValueVector createValueVector(ValueEval valueEval) throws EvaluationException {
        if (valueEval instanceof ErrorEval) {
            throw new EvaluationException((ErrorEval) valueEval);
        } else if (valueEval instanceof TwoDEval) {
            return new AreaValueArray((TwoDEval) valueEval);
        } else {
            if (valueEval instanceof RefEval) {
                return new RefValueArray((RefEval) valueEval);
            }
            return new SingleCellValueArray(valueEval);
        }
    }
}
