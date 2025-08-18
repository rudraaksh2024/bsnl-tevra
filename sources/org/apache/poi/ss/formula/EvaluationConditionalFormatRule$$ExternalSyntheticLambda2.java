package org.apache.poi.ss.formula;

import java.util.List;
import java.util.function.Function;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class EvaluationConditionalFormatRule$$ExternalSyntheticLambda2 implements Function {
    public final /* synthetic */ EvaluationConditionalFormatRule f$0;

    public /* synthetic */ EvaluationConditionalFormatRule$$ExternalSyntheticLambda2(EvaluationConditionalFormatRule evaluationConditionalFormatRule) {
        this.f$0 = evaluationConditionalFormatRule;
    }

    public final Object apply(Object obj) {
        return this.f$0.evaluateDuplicateValues((List) obj);
    }
}
