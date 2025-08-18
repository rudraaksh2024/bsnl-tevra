package org.apache.poi.ss.formula.atp;

import org.apache.poi.ss.formula.OperationEvaluationContext;
import org.apache.poi.ss.formula.TwoDEval;
import org.apache.poi.ss.formula.eval.AreaEval;
import org.apache.poi.ss.formula.eval.BlankEval;
import org.apache.poi.ss.formula.eval.ErrorEval;
import org.apache.poi.ss.formula.eval.EvaluationException;
import org.apache.poi.ss.formula.eval.OperandResolver;
import org.apache.poi.ss.formula.eval.StringEval;
import org.apache.poi.ss.formula.eval.ValueEval;
import org.apache.poi.ss.formula.functions.ArrayFunction;
import org.apache.poi.ss.formula.functions.FreeRefFunction;
import org.apache.poi.ss.formula.functions.LookupUtils;

final class XLookupFunction implements FreeRefFunction, ArrayFunction {
    public static final FreeRefFunction instance = new XLookupFunction(ArgumentsEvaluator.instance);
    private final ArgumentsEvaluator evaluator;

    private XLookupFunction(ArgumentsEvaluator argumentsEvaluator) {
        this.evaluator = argumentsEvaluator;
    }

    public ValueEval evaluate(ValueEval[] valueEvalArr, OperationEvaluationContext operationEvaluationContext) {
        return _evaluate(valueEvalArr, operationEvaluationContext.getRowIndex(), operationEvaluationContext.getColumnIndex());
    }

    public ValueEval evaluateArray(ValueEval[] valueEvalArr, int i, int i2) {
        return _evaluate(valueEvalArr, i, i2);
    }

    private ValueEval _evaluate(ValueEval[] valueEvalArr, int i, int i2) {
        if (valueEvalArr.length < 3) {
            return ErrorEval.VALUE_INVALID;
        }
        ValueEval valueEval = BlankEval.instance;
        if (valueEvalArr.length > 3) {
            try {
                ValueEval singleValue = OperandResolver.getSingleValue(valueEvalArr[3], i, i2);
                if (singleValue != null) {
                    valueEval = singleValue;
                }
            } catch (EvaluationException e) {
                return e.getErrorEval();
            }
        }
        ValueEval valueEval2 = valueEval;
        LookupUtils.MatchMode matchMode = LookupUtils.MatchMode.ExactMatch;
        if (valueEvalArr.length > 4) {
            try {
                matchMode = LookupUtils.matchMode(OperandResolver.coerceValueToInt(OperandResolver.getSingleValue(valueEvalArr[4], i, i2)));
            } catch (EvaluationException e2) {
                return e2.getErrorEval();
            } catch (Exception unused) {
                return ErrorEval.VALUE_INVALID;
            }
        }
        LookupUtils.MatchMode matchMode2 = matchMode;
        LookupUtils.SearchMode searchMode = LookupUtils.SearchMode.IterateForward;
        if (valueEvalArr.length > 5) {
            try {
                searchMode = LookupUtils.searchMode(OperandResolver.coerceValueToInt(OperandResolver.getSingleValue(valueEvalArr[5], i, i2)));
            } catch (EvaluationException e3) {
                return e3.getErrorEval();
            } catch (Exception unused2) {
                return ErrorEval.VALUE_INVALID;
            }
        }
        LookupUtils.SearchMode searchMode2 = searchMode;
        return evaluate(i, i2, valueEvalArr[0], valueEvalArr[1], valueEvalArr[2], valueEval2, matchMode2, searchMode2);
    }

    private ValueEval evaluate(int i, int i2, ValueEval valueEval, ValueEval valueEval2, ValueEval valueEval3, ValueEval valueEval4, LookupUtils.MatchMode matchMode, LookupUtils.SearchMode searchMode) {
        LookupUtils.ValueVector valueVector;
        int width;
        try {
            ValueEval singleValue = OperandResolver.getSingleValue(valueEval, i, i2);
            TwoDEval resolveTableArrayArg = LookupUtils.resolveTableArrayArg(valueEval2);
            if (resolveTableArrayArg.isColumn()) {
                valueVector = LookupUtils.createColumnVector(resolveTableArrayArg, 0);
            } else {
                valueVector = LookupUtils.createRowVector(resolveTableArrayArg, 0);
            }
            try {
                int xlookupIndexOfValue = LookupUtils.xlookupIndexOfValue(singleValue, valueVector, matchMode, searchMode);
                if (!(valueEval3 instanceof AreaEval)) {
                    return valueEval3;
                }
                AreaEval areaEval = (AreaEval) valueEval3;
                if (resolveTableArrayArg.isColumn()) {
                    return areaEval.offset(xlookupIndexOfValue, xlookupIndexOfValue, 0, areaEval.getWidth() - 1);
                }
                return areaEval.offset(0, areaEval.getHeight() - 1, xlookupIndexOfValue, xlookupIndexOfValue);
            } catch (EvaluationException e) {
                if (!ErrorEval.NA.equals(e.getErrorEval())) {
                    return e.getErrorEval();
                }
                if (valueEval4 == BlankEval.instance) {
                    return ErrorEval.NA;
                }
                if (!(valueEval3 instanceof AreaEval) || (width = ((AreaEval) valueEval3).getWidth()) <= 1) {
                    return valueEval4;
                }
                return notFoundAreaEval(valueEval4, width);
            }
        } catch (EvaluationException e2) {
            return e2.getErrorEval();
        }
    }

    private AreaEval notFoundAreaEval(final ValueEval valueEval, final int i) {
        return new AreaEval() {
            public boolean containsRow(int i) {
                return i == 0;
            }

            public TwoDEval getColumn(int i) {
                return null;
            }

            public int getFirstColumn() {
                return 0;
            }

            public int getFirstRow() {
                return 0;
            }

            public int getFirstSheetIndex() {
                return 0;
            }

            public int getHeight() {
                return 1;
            }

            public int getLastRow() {
                return 0;
            }

            public int getLastSheetIndex() {
                return 0;
            }

            public TwoDEval getRow(int i) {
                return null;
            }

            public boolean isColumn() {
                return false;
            }

            public boolean isRowHidden(int i) {
                return false;
            }

            public boolean isSubTotal(int i, int i2) {
                return false;
            }

            public AreaEval offset(int i, int i2, int i3, int i4) {
                return null;
            }

            public int getLastColumn() {
                return i - 1;
            }

            public ValueEval getAbsoluteValue(int i, int i2) {
                if (i2 == 0) {
                    return valueEval;
                }
                return new StringEval("");
            }

            public boolean contains(int i, int i2) {
                return containsRow(i) && containsColumn(i2);
            }

            public boolean containsColumn(int i) {
                return i < i;
            }

            public int getWidth() {
                return i;
            }

            public ValueEval getRelativeValue(int i, int i2) {
                return getAbsoluteValue(i, i2);
            }

            public ValueEval getValue(int i, int i2, int i3) {
                return getAbsoluteValue(i2, i3);
            }

            public ValueEval getValue(int i, int i2) {
                return getAbsoluteValue(i, i2);
            }
        };
    }
}
