package org.apache.poi.ss.formula.functions;

import java.util.function.BiFunction;
import java.util.function.Function;
import org.apache.poi.ss.formula.CacheAreaEval;
import org.apache.poi.ss.formula.FormulaParseException;
import org.apache.poi.ss.formula.eval.AreaEval;
import org.apache.poi.ss.formula.eval.ErrorEval;
import org.apache.poi.ss.formula.eval.EvaluationException;
import org.apache.poi.ss.formula.eval.OperandResolver;
import org.apache.poi.ss.formula.eval.RefEval;
import org.apache.poi.ss.formula.eval.ValueEval;

public interface ArrayFunction {
    ValueEval evaluateArray(ValueEval[] valueEvalArr, int i, int i2);

    ValueEval evaluateTwoArrayArgs(ValueEval valueEval, ValueEval valueEval2, int i, int i2, BiFunction<ValueEval, ValueEval, ValueEval> biFunction) {
        return _evaluateTwoArrayArgs(valueEval, valueEval2, i, i2, biFunction);
    }

    ValueEval evaluateOneArrayArg(ValueEval valueEval, int i, int i2, Function<ValueEval, ValueEval> function) {
        return _evaluateOneArrayArg(valueEval, i, i2, function);
    }

    static ValueEval _evaluateTwoArrayArgs(ValueEval valueEval, ValueEval valueEval2, int i, int i2, BiFunction<ValueEval, ValueEval, ValueEval> biFunction) {
        int i3;
        int i4;
        int i5;
        int i6;
        int i7;
        int i8;
        int i9;
        int i10;
        ValueEval valueEval3;
        ValueEval valueEval4;
        ValueEval valueEval5 = valueEval;
        ValueEval valueEval6 = valueEval2;
        if (valueEval5 instanceof AreaEval) {
            AreaEval areaEval = (AreaEval) valueEval5;
            int width = areaEval.getWidth();
            i4 = areaEval.getHeight();
            i3 = areaEval.getFirstColumn();
            int i11 = width;
            i5 = areaEval.getFirstRow();
            i6 = i11;
        } else if (valueEval5 instanceof RefEval) {
            RefEval refEval = (RefEval) valueEval5;
            i3 = refEval.getColumn();
            i5 = refEval.getRow();
            i6 = 1;
            i4 = 1;
        } else {
            i6 = 1;
            i5 = 0;
            i4 = 1;
            i3 = 0;
        }
        if (valueEval6 instanceof AreaEval) {
            AreaEval areaEval2 = (AreaEval) valueEval6;
            i9 = areaEval2.getWidth();
            i8 = areaEval2.getHeight();
            i7 = areaEval2.getFirstColumn();
            i10 = areaEval2.getFirstRow();
        } else if (valueEval6 instanceof RefEval) {
            RefEval refEval2 = (RefEval) valueEval6;
            i7 = refEval2.getColumn();
            i10 = refEval2.getRow();
            i9 = 1;
            i8 = 1;
        } else {
            i10 = 0;
            i9 = 1;
            i8 = 1;
            i7 = 0;
        }
        int max = Math.max(i6, i9);
        int max2 = Math.max(i4, i8);
        int i12 = max2 * max;
        ValueEval[] valueEvalArr = new ValueEval[i12];
        int i13 = 0;
        int i14 = 0;
        while (i14 < max2) {
            int i15 = i13;
            int i16 = 0;
            while (i16 < max) {
                try {
                    valueEval3 = OperandResolver.getSingleValue(valueEval5, i5 + i14, i3 + i16);
                } catch (FormulaParseException unused) {
                    valueEval3 = ErrorEval.NAME_INVALID;
                } catch (EvaluationException e) {
                    valueEval3 = e.getErrorEval();
                } catch (RuntimeException e2) {
                    RuntimeException runtimeException = e2;
                    if (runtimeException.getMessage().startsWith("Don't know how to evaluate name")) {
                        valueEval3 = ErrorEval.NAME_INVALID;
                    } else {
                        throw runtimeException;
                    }
                }
                ValueEval valueEval7 = valueEval3;
                try {
                    valueEval4 = OperandResolver.getSingleValue(valueEval6, i10 + i14, i7 + i16);
                } catch (FormulaParseException unused2) {
                    valueEval4 = ErrorEval.NAME_INVALID;
                } catch (EvaluationException e3) {
                    valueEval4 = e3.getErrorEval();
                } catch (RuntimeException e4) {
                    RuntimeException runtimeException2 = e4;
                    if (runtimeException2.getMessage().startsWith("Don't know how to evaluate name")) {
                        valueEval4 = ErrorEval.NAME_INVALID;
                    } else {
                        throw runtimeException2;
                    }
                }
                if (valueEval7 instanceof ErrorEval) {
                    valueEvalArr[i15] = valueEval7;
                    BiFunction<ValueEval, ValueEval, ValueEval> biFunction2 = biFunction;
                    i15++;
                } else if (valueEval4 instanceof ErrorEval) {
                    valueEvalArr[i15] = valueEval4;
                    i15++;
                    BiFunction<ValueEval, ValueEval, ValueEval> biFunction3 = biFunction;
                } else {
                    valueEvalArr[i15] = biFunction.apply(valueEval7, valueEval4);
                    i15++;
                }
                i16++;
                valueEval5 = valueEval;
            }
            BiFunction<ValueEval, ValueEval, ValueEval> biFunction4 = biFunction;
            i14++;
            valueEval5 = valueEval;
            i13 = i15;
        }
        if (i12 == 1) {
            return valueEvalArr[0];
        }
        return new CacheAreaEval(i, i2, (i + max2) - 1, (i2 + max) - 1, valueEvalArr);
    }

    static ValueEval _evaluateOneArrayArg(ValueEval valueEval, int i, int i2, Function<ValueEval, ValueEval> function) {
        int i3;
        int i4;
        int i5;
        int i6;
        Object obj;
        ValueEval valueEval2 = valueEval;
        if (valueEval2 instanceof AreaEval) {
            AreaEval areaEval = (AreaEval) valueEval2;
            int width = areaEval.getWidth();
            i4 = areaEval.getHeight();
            i3 = areaEval.getFirstColumn();
            int i7 = width;
            i5 = areaEval.getFirstRow();
            i6 = i7;
        } else if (valueEval2 instanceof RefEval) {
            RefEval refEval = (RefEval) valueEval2;
            i3 = refEval.getColumn();
            i5 = refEval.getRow();
            i6 = 1;
            i4 = 1;
        } else {
            i6 = 1;
            i4 = 1;
            i5 = 0;
            i3 = 0;
        }
        int max = Math.max(i6, 1);
        int max2 = Math.max(i4, 1);
        int i8 = max2 * max;
        ValueEval[] valueEvalArr = new ValueEval[i8];
        int i9 = 0;
        int i10 = 0;
        while (i10 < max2) {
            int i11 = i9;
            int i12 = 0;
            while (i12 < max) {
                try {
                    obj = OperandResolver.getSingleValue(valueEval2, i5 + i10, i3 + i12);
                } catch (FormulaParseException unused) {
                    obj = ErrorEval.NAME_INVALID;
                } catch (EvaluationException e) {
                    obj = e.getErrorEval();
                } catch (RuntimeException e2) {
                    RuntimeException runtimeException = e2;
                    if (runtimeException.getMessage().startsWith("Don't know how to evaluate name")) {
                        obj = ErrorEval.NAME_INVALID;
                    } else {
                        throw runtimeException;
                    }
                }
                valueEvalArr[i11] = function.apply(obj);
                i12++;
                i11++;
            }
            Function<ValueEval, ValueEval> function2 = function;
            i10++;
            i9 = i11;
        }
        if (i8 == 1) {
            return valueEvalArr[0];
        }
        return new CacheAreaEval(i, i2, (i + max2) - 1, (i2 + max) - 1, valueEvalArr);
    }
}
