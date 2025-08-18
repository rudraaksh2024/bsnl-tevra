package org.apache.poi.hssf.record;

import java.util.function.Supplier;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class CodepageRecord$$ExternalSyntheticLambda0 implements Supplier {
    public final /* synthetic */ CodepageRecord f$0;

    public /* synthetic */ CodepageRecord$$ExternalSyntheticLambda0(CodepageRecord codepageRecord) {
        this.f$0 = codepageRecord;
    }

    public final Object get() {
        return Short.valueOf(this.f$0.getCodepage());
    }
}
