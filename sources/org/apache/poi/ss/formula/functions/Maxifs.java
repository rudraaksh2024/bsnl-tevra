package org.apache.poi.ss.formula.functions;

import org.apache.poi.ss.formula.OperationEvaluationContext;
import org.apache.poi.ss.formula.eval.NumberEval;
import org.apache.poi.ss.formula.eval.ValueEval;
import org.apache.poi.ss.formula.functions.Baseifs;

public final class Maxifs extends Baseifs {
    public static final FreeRefFunction instance = new Maxifs();

    /* access modifiers changed from: protected */
    public boolean hasInitialRange() {
        return true;
    }

    public /* bridge */ /* synthetic */ ValueEval evaluate(ValueEval[] valueEvalArr, OperationEvaluationContext operationEvaluationContext) {
        return super.evaluate(valueEvalArr, operationEvaluationContext);
    }

    /* access modifiers changed from: protected */
    public Baseifs.Aggregator createAggregator() {
        return new Baseifs.Aggregator() {
            Double accumulator = null;

            public void addValue(ValueEval valueEval) {
                double numberValue = valueEval instanceof NumberEval ? ((NumberEval) valueEval).getNumberValue() : 0.0d;
                Double d = this.accumulator;
                if (d == null || d.doubleValue() < numberValue) {
                    this.accumulator = Double.valueOf(numberValue);
                }
            }

            public ValueEval getResult() {
                Double d = this.accumulator;
                return new NumberEval(d == null ? 0.0d : d.doubleValue());
            }
        };
    }
}
