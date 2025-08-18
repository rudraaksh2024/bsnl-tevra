package org.apache.poi.ss.formula.eval;

import org.apache.poi.ss.formula.functions.ArrayFunction;
import org.apache.poi.ss.formula.functions.Fixed2ArgFunction;
import org.apache.poi.ss.formula.functions.Function;
import org.apache.poi.ss.util.NumberComparer;

public abstract class RelationalOperationEval extends Fixed2ArgFunction implements ArrayFunction {
    public static final Function EqualEval = new RelationalOperationEval() {
        /* access modifiers changed from: protected */
        public boolean convertComparisonResult(int i) {
            return i == 0;
        }
    };
    public static final Function GreaterEqualEval = new RelationalOperationEval() {
        /* access modifiers changed from: protected */
        public boolean convertComparisonResult(int i) {
            return i >= 0;
        }
    };
    public static final Function GreaterThanEval = new RelationalOperationEval() {
        /* access modifiers changed from: protected */
        public boolean convertComparisonResult(int i) {
            return i > 0;
        }
    };
    public static final Function LessEqualEval = new RelationalOperationEval() {
        /* access modifiers changed from: protected */
        public boolean convertComparisonResult(int i) {
            return i <= 0;
        }
    };
    public static final Function LessThanEval = new RelationalOperationEval() {
        /* access modifiers changed from: protected */
        public boolean convertComparisonResult(int i) {
            return i < 0;
        }
    };
    public static final Function NotEqualEval = new RelationalOperationEval() {
        /* access modifiers changed from: protected */
        public boolean convertComparisonResult(int i) {
            return i != 0;
        }
    };

    /* access modifiers changed from: protected */
    public abstract boolean convertComparisonResult(int i);

    public ValueEval evaluate(int i, int i2, ValueEval valueEval, ValueEval valueEval2) {
        try {
            return BoolEval.valueOf(convertComparisonResult(doCompare(OperandResolver.getSingleValue(valueEval, i, i2), OperandResolver.getSingleValue(valueEval2, i, i2))));
        } catch (EvaluationException e) {
            return e.getErrorEval();
        }
    }

    public ValueEval evaluateArray(ValueEval[] valueEvalArr, int i, int i2) {
        return evaluateTwoArrayArgs(valueEvalArr[0], valueEvalArr[1], i, i2, new RelationalOperationEval$$ExternalSyntheticLambda0(this));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$evaluateArray$0$org-apache-poi-ss-formula-eval-RelationalOperationEval  reason: not valid java name */
    public /* synthetic */ ValueEval m2250lambda$evaluateArray$0$orgapachepoissformulaevalRelationalOperationEval(ValueEval valueEval, ValueEval valueEval2) {
        return BoolEval.valueOf(convertComparisonResult(doCompare(valueEval, valueEval2)));
    }

    private static int doCompare(ValueEval valueEval, ValueEval valueEval2) {
        if (valueEval == BlankEval.instance || (valueEval instanceof MissingArgEval)) {
            return compareBlank(valueEval2);
        }
        if (valueEval2 == BlankEval.instance || (valueEval2 instanceof MissingArgEval)) {
            return -compareBlank(valueEval);
        }
        if (valueEval instanceof BoolEval) {
            if (!(valueEval2 instanceof BoolEval)) {
                return 1;
            }
            BoolEval boolEval = (BoolEval) valueEval;
            if (boolEval.getBooleanValue() == ((BoolEval) valueEval2).getBooleanValue()) {
                return 0;
            }
            if (boolEval.getBooleanValue()) {
                return 1;
            }
            return -1;
        } else if (valueEval2 instanceof BoolEval) {
            return -1;
        } else {
            if (valueEval instanceof StringEval) {
                if (valueEval2 instanceof StringEval) {
                    return ((StringEval) valueEval).getStringValue().compareToIgnoreCase(((StringEval) valueEval2).getStringValue());
                }
                return 1;
            } else if (valueEval2 instanceof StringEval) {
                return -1;
            } else {
                if ((valueEval instanceof NumberEval) && (valueEval2 instanceof NumberEval)) {
                    return NumberComparer.compare(((NumberEval) valueEval).getNumberValue(), ((NumberEval) valueEval2).getNumberValue());
                }
                throw new IllegalArgumentException("Bad operand types (" + valueEval.getClass().getName() + "), (" + valueEval2.getClass().getName() + ")");
            }
        }
    }

    private static int compareBlank(ValueEval valueEval) {
        if (valueEval == BlankEval.instance || (valueEval instanceof MissingArgEval)) {
            return 0;
        }
        if (valueEval instanceof BoolEval) {
            if (((BoolEval) valueEval).getBooleanValue()) {
                return -1;
            }
            return 0;
        } else if (valueEval instanceof NumberEval) {
            return NumberComparer.compare(0.0d, ((NumberEval) valueEval).getNumberValue());
        } else {
            if (!(valueEval instanceof StringEval)) {
                throw new IllegalArgumentException("bad value class (" + valueEval.getClass().getName() + ")");
            } else if (((StringEval) valueEval).getStringValue().length() < 1) {
                return 0;
            } else {
                return -1;
            }
        }
    }
}
