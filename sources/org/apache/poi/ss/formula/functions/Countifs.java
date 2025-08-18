package org.apache.poi.ss.formula.functions;

import org.apache.poi.ss.formula.OperationEvaluationContext;
import org.apache.poi.ss.formula.eval.NumberEval;
import org.apache.poi.ss.formula.eval.ValueEval;
import org.apache.poi.ss.formula.functions.Baseifs;

public class Countifs extends Baseifs {
    public static final FreeRefFunction instance = new Countifs();

    /* access modifiers changed from: protected */
    public boolean hasInitialRange() {
        return false;
    }

    public /* bridge */ /* synthetic */ ValueEval evaluate(ValueEval[] valueEvalArr, OperationEvaluationContext operationEvaluationContext) {
        return super.evaluate(valueEvalArr, operationEvaluationContext);
    }

    /* access modifiers changed from: protected */
    public Baseifs.Aggregator createAggregator() {
        return new Baseifs.Aggregator() {
            double accumulator = 0.0d;

            public void addValue(ValueEval valueEval) {
                this.accumulator += 1.0d;
            }

            public ValueEval getResult() {
                return new NumberEval(this.accumulator);
            }
        };
    }
}
