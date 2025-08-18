package org.apache.poi.hssf.record;

import java.util.function.Supplier;
import org.apache.poi.ss.formula.Formula;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class ExternalNameRecord$$ExternalSyntheticLambda4 implements Supplier {
    public final /* synthetic */ Formula f$0;

    public /* synthetic */ ExternalNameRecord$$ExternalSyntheticLambda4(Formula formula) {
        this.f$0 = formula;
    }

    public final Object get() {
        return this.f$0.getTokens();
    }
}
