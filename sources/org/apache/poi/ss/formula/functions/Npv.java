package org.apache.poi.ss.formula.functions;

import java.util.Arrays;
import org.apache.poi.ss.formula.eval.ErrorEval;
import org.apache.poi.ss.formula.eval.EvaluationException;
import org.apache.poi.ss.formula.eval.NumberEval;
import org.apache.poi.ss.formula.eval.ValueEval;
import org.apache.poi.ss.formula.functions.AggregateFunction;

public final class Npv implements Function {
    public ValueEval evaluate(ValueEval[] valueEvalArr, int i, int i2) {
        if (valueEvalArr.length < 2) {
            return ErrorEval.VALUE_INVALID;
        }
        try {
            double npv = FinanceLib.npv(NumericFunction.singleOperandEvaluate(valueEvalArr[0], i, i2), AggregateFunction.ValueCollector.collectValues((ValueEval[]) Arrays.copyOfRange(valueEvalArr, 1, valueEvalArr.length, ValueEval[].class)));
            NumericFunction.checkValue(npv);
            return new NumberEval(npv);
        } catch (EvaluationException e) {
            return e.getErrorEval();
        }
    }
}
