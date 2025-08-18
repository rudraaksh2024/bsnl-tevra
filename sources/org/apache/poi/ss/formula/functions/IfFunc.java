package org.apache.poi.ss.formula.functions;

import org.apache.poi.ss.formula.CacheAreaEval;
import org.apache.poi.ss.formula.FormulaParseException;
import org.apache.poi.ss.formula.eval.AreaEval;
import org.apache.poi.ss.formula.eval.BlankEval;
import org.apache.poi.ss.formula.eval.BoolEval;
import org.apache.poi.ss.formula.eval.ErrorEval;
import org.apache.poi.ss.formula.eval.EvaluationException;
import org.apache.poi.ss.formula.eval.MissingArgEval;
import org.apache.poi.ss.formula.eval.OperandResolver;
import org.apache.poi.ss.formula.eval.RefEval;
import org.apache.poi.ss.formula.eval.ValueEval;

public final class IfFunc extends Var2or3ArgFunction implements ArrayFunction {
    public ValueEval evaluate(int i, int i2, ValueEval valueEval, ValueEval valueEval2) {
        try {
            if (evaluateFirstArg(valueEval, i, i2)) {
                return valueEval2 == MissingArgEval.instance ? BlankEval.instance : valueEval2;
            }
            return BoolEval.FALSE;
        } catch (EvaluationException e) {
            return e.getErrorEval();
        }
    }

    public ValueEval evaluate(int i, int i2, ValueEval valueEval, ValueEval valueEval2, ValueEval valueEval3) {
        try {
            if (evaluateFirstArg(valueEval, i, i2)) {
                return valueEval2 == MissingArgEval.instance ? BlankEval.instance : valueEval2;
            }
            if (valueEval3 == MissingArgEval.instance) {
                return BlankEval.instance;
            }
            return valueEval3;
        } catch (EvaluationException e) {
            return e.getErrorEval();
        }
    }

    public static boolean evaluateFirstArg(ValueEval valueEval, int i, int i2) throws EvaluationException {
        Boolean coerceValueToBoolean = OperandResolver.coerceValueToBoolean(OperandResolver.getSingleValue(valueEval, i, i2), false);
        if (coerceValueToBoolean == null) {
            return false;
        }
        return coerceValueToBoolean.booleanValue();
    }

    public ValueEval evaluateArray(ValueEval[] valueEvalArr, int i, int i2) {
        if (valueEvalArr.length < 2 || valueEvalArr.length > 3) {
            return ErrorEval.VALUE_INVALID;
        }
        return evaluateArrayArgs(valueEvalArr[0], valueEvalArr[1], valueEvalArr.length == 2 ? BoolEval.FALSE : valueEvalArr[2], i, i2);
    }

    /* access modifiers changed from: package-private */
    public ValueEval evaluateArrayArgs(ValueEval valueEval, ValueEval valueEval2, ValueEval valueEval3, int i, int i2) {
        int i3;
        int i4;
        int i5;
        int i6;
        int i7;
        int i8;
        int i9;
        int i10;
        int i11;
        int i12;
        ValueEval valueEval4;
        ValueEval valueEval5;
        ValueEval valueEval6;
        int i13;
        ValueEval valueEval7 = valueEval;
        ValueEval valueEval8 = valueEval2;
        ValueEval valueEval9 = valueEval3;
        if (valueEval7 instanceof AreaEval) {
            AreaEval areaEval = (AreaEval) valueEval7;
            int width = areaEval.getWidth();
            i4 = areaEval.getHeight();
            i3 = areaEval.getFirstColumn();
            int i14 = width;
            i5 = areaEval.getFirstRow();
            i6 = i14;
        } else if (valueEval7 instanceof RefEval) {
            RefEval refEval = (RefEval) valueEval7;
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
        if (valueEval8 instanceof AreaEval) {
            AreaEval areaEval2 = (AreaEval) valueEval8;
            i9 = areaEval2.getWidth();
            i8 = areaEval2.getHeight();
            i7 = areaEval2.getFirstColumn();
            i10 = areaEval2.getFirstRow();
        } else if (valueEval8 instanceof RefEval) {
            RefEval refEval2 = (RefEval) valueEval8;
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
        if (valueEval9 instanceof AreaEval) {
            AreaEval areaEval3 = (AreaEval) valueEval9;
            i11 = areaEval3.getFirstColumn();
            i12 = areaEval3.getFirstRow();
        } else if (valueEval9 instanceof RefEval) {
            RefEval refEval3 = (RefEval) valueEval9;
            i11 = refEval3.getColumn();
            i12 = refEval3.getRow();
        } else {
            i12 = 0;
            i11 = 0;
        }
        int max = Math.max(i6, i9);
        int max2 = Math.max(i4, i8);
        int i15 = max2 * max;
        ValueEval[] valueEvalArr = new ValueEval[i15];
        int i16 = 0;
        int i17 = 0;
        while (i17 < max2) {
            int i18 = i16;
            int i19 = 0;
            while (i19 < max) {
                int i20 = i5;
                try {
                    valueEval4 = OperandResolver.getSingleValue(valueEval7, i5 + i17, i3 + i19);
                } catch (FormulaParseException unused) {
                    valueEval4 = ErrorEval.NAME_INVALID;
                } catch (EvaluationException e) {
                    valueEval4 = e.getErrorEval();
                }
                ValueEval valueEval10 = valueEval4;
                try {
                    valueEval5 = OperandResolver.getSingleValue(valueEval8, i10 + i17, i7 + i19);
                } catch (FormulaParseException unused2) {
                    valueEval5 = ErrorEval.NAME_INVALID;
                } catch (EvaluationException e2) {
                    valueEval5 = e2.getErrorEval();
                }
                ValueEval valueEval11 = valueEval5;
                try {
                    valueEval6 = OperandResolver.getSingleValue(valueEval9, i12 + i17, i11 + i19);
                } catch (FormulaParseException unused3) {
                    valueEval6 = ErrorEval.NAME_INVALID;
                } catch (EvaluationException e3) {
                    valueEval6 = e3.getErrorEval();
                }
                if (valueEval10 instanceof ErrorEval) {
                    valueEvalArr[i18] = valueEval10;
                    i18++;
                } else {
                    try {
                        Boolean coerceValueToBoolean = OperandResolver.coerceValueToBoolean(valueEval10, false);
                        i13 = i18 + 1;
                        if (coerceValueToBoolean != null) {
                            try {
                                if (coerceValueToBoolean.booleanValue()) {
                                    valueEval6 = valueEval11;
                                }
                            } catch (EvaluationException e4) {
                                e = e4;
                                i18 = i13;
                                i13 = i18 + 1;
                                valueEvalArr[i18] = e.getErrorEval();
                                i18 = i13;
                                i19++;
                                valueEval7 = valueEval;
                                i5 = i20;
                            }
                        }
                        valueEvalArr[i18] = valueEval6;
                    } catch (EvaluationException e5) {
                        e = e5;
                        i13 = i18 + 1;
                        valueEvalArr[i18] = e.getErrorEval();
                        i18 = i13;
                        i19++;
                        valueEval7 = valueEval;
                        i5 = i20;
                    }
                    i18 = i13;
                }
                i19++;
                valueEval7 = valueEval;
                i5 = i20;
            }
            int i21 = i5;
            i17++;
            valueEval7 = valueEval;
            i16 = i18;
        }
        if (i15 == 1) {
            return valueEvalArr[0];
        }
        return new CacheAreaEval(i, i2, (i + max2) - 1, (i2 + max) - 1, valueEvalArr);
    }
}
