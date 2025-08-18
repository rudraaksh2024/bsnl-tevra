package org.apache.poi.ss.formula.functions;

import org.apache.poi.ss.formula.TwoDEval;
import org.apache.poi.ss.formula.eval.BoolEval;
import org.apache.poi.ss.formula.eval.EvaluationException;
import org.apache.poi.ss.formula.eval.OperandResolver;
import org.apache.poi.ss.formula.eval.ValueEval;
import org.apache.poi.ss.formula.functions.LookupUtils;

public final class Hlookup extends Var3or4ArgFunction {
    private static final ValueEval DEFAULT_ARG3 = BoolEval.TRUE;

    public ValueEval evaluate(int i, int i2, ValueEval valueEval, ValueEval valueEval2, ValueEval valueEval3) {
        return evaluate(i, i2, valueEval, valueEval2, valueEval3, DEFAULT_ARG3);
    }

    public ValueEval evaluate(int i, int i2, ValueEval valueEval, ValueEval valueEval2, ValueEval valueEval3, ValueEval valueEval4) {
        try {
            ValueEval singleValue = OperandResolver.getSingleValue(valueEval, i, i2);
            TwoDEval resolveTableArrayArg = LookupUtils.resolveTableArrayArg(valueEval2);
            return createResultColumnVector(resolveTableArrayArg, LookupUtils.resolveRowOrColIndexArg(valueEval3, i, i2)).getItem(LookupUtils.lookupFirstIndexOfValue(singleValue, LookupUtils.createRowVector(resolveTableArrayArg, 0), LookupUtils.resolveRangeLookupArg(valueEval4, i, i2)));
        } catch (EvaluationException e) {
            return e.getErrorEval();
        }
    }

    private LookupUtils.ValueVector createResultColumnVector(TwoDEval twoDEval, int i) throws EvaluationException {
        if (i < twoDEval.getHeight()) {
            return LookupUtils.createRowVector(twoDEval, i);
        }
        throw EvaluationException.invalidRef();
    }
}
