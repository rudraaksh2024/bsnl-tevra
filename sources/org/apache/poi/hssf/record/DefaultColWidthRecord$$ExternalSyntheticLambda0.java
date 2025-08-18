package org.apache.poi.hssf.record;

import java.util.function.Supplier;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class DefaultColWidthRecord$$ExternalSyntheticLambda0 implements Supplier {
    public final /* synthetic */ DefaultColWidthRecord f$0;

    public /* synthetic */ DefaultColWidthRecord$$ExternalSyntheticLambda0(DefaultColWidthRecord defaultColWidthRecord) {
        this.f$0 = defaultColWidthRecord;
    }

    public final Object get() {
        return Integer.valueOf(this.f$0.getColWidth());
    }
}
