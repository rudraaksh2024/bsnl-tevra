package org.apache.poi.ddf;

import java.util.function.Predicate;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class AbstractEscherOptRecord$$ExternalSyntheticLambda5 implements Predicate {
    public final /* synthetic */ EscherPropertyTypes f$0;

    public /* synthetic */ AbstractEscherOptRecord$$ExternalSyntheticLambda5(EscherPropertyTypes escherPropertyTypes) {
        this.f$0 = escherPropertyTypes;
    }

    public final boolean test(Object obj) {
        return AbstractEscherOptRecord.lambda$removeEscherProperty$2(this.f$0, (EscherProperty) obj);
    }
}
