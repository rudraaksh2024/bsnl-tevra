package org.apache.poi.ss.formula.functions;

import org.apache.poi.ss.formula.eval.AreaEval;
import org.apache.poi.ss.formula.eval.BlankEval;
import org.apache.poi.ss.formula.eval.BoolEval;
import org.apache.poi.ss.formula.eval.ErrorEval;
import org.apache.poi.ss.formula.eval.EvaluationException;
import org.apache.poi.ss.formula.eval.NumberEval;
import org.apache.poi.ss.formula.eval.OperandResolver;
import org.apache.poi.ss.formula.eval.RefEval;
import org.apache.poi.ss.formula.eval.RefListEval;
import org.apache.poi.ss.formula.eval.StringEval;
import org.apache.poi.ss.formula.eval.ValueEval;

public abstract class LogicalFunction extends Fixed1ArgFunction implements ArrayFunction {
    public static final Function ISBLANK = new LogicalFunction() {
        /* access modifiers changed from: protected */
        public boolean evaluate(ValueEval valueEval) {
            return valueEval instanceof BlankEval;
        }
    };
    public static final Function ISERR = new LogicalFunction() {
        /* access modifiers changed from: protected */
        public boolean evaluate(ValueEval valueEval) {
            if (!(valueEval instanceof ErrorEval) || valueEval == ErrorEval.NA) {
                return false;
            }
            return true;
        }
    };
    public static final Function ISERROR = new LogicalFunction() {
        /* access modifiers changed from: protected */
        public boolean evaluate(ValueEval valueEval) {
            return valueEval instanceof ErrorEval;
        }
    };
    public static final Function ISLOGICAL = new LogicalFunction() {
        /* access modifiers changed from: protected */
        public boolean evaluate(ValueEval valueEval) {
            return valueEval instanceof BoolEval;
        }
    };
    public static final Function ISNA = new LogicalFunction() {
        /* access modifiers changed from: protected */
        public boolean evaluate(ValueEval valueEval) {
            return valueEval == ErrorEval.NA;
        }
    };
    public static final Function ISNONTEXT = new LogicalFunction() {
        /* access modifiers changed from: protected */
        public boolean evaluate(ValueEval valueEval) {
            return !(valueEval instanceof StringEval);
        }
    };
    public static final Function ISNUMBER = new LogicalFunction() {
        /* access modifiers changed from: protected */
        public boolean evaluate(ValueEval valueEval) {
            return valueEval instanceof NumberEval;
        }
    };
    public static final Function ISREF = new Fixed1ArgFunction() {
        public ValueEval evaluate(int i, int i2, ValueEval valueEval) {
            if ((valueEval instanceof RefEval) || (valueEval instanceof AreaEval) || (valueEval instanceof RefListEval)) {
                return BoolEval.TRUE;
            }
            return BoolEval.FALSE;
        }
    };
    public static final Function ISTEXT = new LogicalFunction() {
        /* access modifiers changed from: protected */
        public boolean evaluate(ValueEval valueEval) {
            return valueEval instanceof StringEval;
        }
    };

    /* access modifiers changed from: protected */
    public abstract boolean evaluate(ValueEval valueEval);

    public ValueEval evaluate(int i, int i2, ValueEval valueEval) {
        ValueEval valueEval2;
        try {
            valueEval2 = OperandResolver.getSingleValue(valueEval, i, i2);
        } catch (EvaluationException e) {
            valueEval2 = e.getErrorEval();
        }
        return BoolEval.valueOf(evaluate(valueEval2));
    }

    public ValueEval evaluateArray(ValueEval[] valueEvalArr, int i, int i2) {
        if (valueEvalArr.length != 1) {
            return ErrorEval.VALUE_INVALID;
        }
        return evaluateOneArrayArg(valueEvalArr[0], i, i2, new LogicalFunction$$ExternalSyntheticLambda0(this));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$evaluateArray$0$org-apache-poi-ss-formula-functions-LogicalFunction  reason: not valid java name */
    public /* synthetic */ ValueEval m2254lambda$evaluateArray$0$orgapachepoissformulafunctionsLogicalFunction(ValueEval valueEval) {
        return BoolEval.valueOf(evaluate(valueEval));
    }
}
