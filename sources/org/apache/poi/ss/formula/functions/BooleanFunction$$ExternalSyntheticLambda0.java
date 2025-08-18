package org.apache.poi.ss.formula.functions;

import java.util.function.Function;
import org.apache.poi.ss.formula.eval.ValueEval;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class BooleanFunction$$ExternalSyntheticLambda0 implements Function {
    public final /* synthetic */ int f$0;
    public final /* synthetic */ int f$1;

    public /* synthetic */ BooleanFunction$$ExternalSyntheticLambda0(int i, int i2) {
        this.f$0 = i;
        this.f$1 = i2;
    }

    public final Object apply(Object obj) {
        return BooleanFunction.lambda$evaluateNot$0(this.f$0, this.f$1, (ValueEval) obj);
    }
}
