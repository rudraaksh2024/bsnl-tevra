package org.apache.poi.ss.formula.functions;

import org.apache.poi.ss.formula.OperationEvaluationContext;
import org.apache.poi.ss.formula.eval.ErrorEval;
import org.apache.poi.ss.formula.eval.NumberEval;
import org.apache.poi.ss.formula.eval.ValueEval;
import org.apache.poi.ss.formula.functions.Baseifs;

public final class Averageifs extends Baseifs {
    public static final FreeRefFunction instance = new Averageifs();

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
            Integer count = 0;
            Double sum = Double.valueOf(0.0d);

            public void addValue(ValueEval valueEval) {
                if (valueEval instanceof NumberEval) {
                    this.sum = Double.valueOf(this.sum.doubleValue() + ((NumberEval) valueEval).getNumberValue());
                    this.count = Integer.valueOf(this.count.intValue() + 1);
                }
            }

            public ValueEval getResult() {
                return this.count.intValue() == 0 ? ErrorEval.DIV_ZERO : new NumberEval(this.sum.doubleValue() / ((double) this.count.intValue()));
            }
        };
    }
}
