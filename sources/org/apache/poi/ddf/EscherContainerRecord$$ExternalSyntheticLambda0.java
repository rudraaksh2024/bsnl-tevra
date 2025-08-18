package org.apache.poi.ddf;

import java.util.function.Predicate;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class EscherContainerRecord$$ExternalSyntheticLambda0 implements Predicate {
    public final /* synthetic */ short f$0;

    public /* synthetic */ EscherContainerRecord$$ExternalSyntheticLambda0(short s) {
        this.f$0 = s;
    }

    public final boolean test(Object obj) {
        return EscherContainerRecord.lambda$hasChildOfType$0(this.f$0, (EscherRecord) obj);
    }
}
