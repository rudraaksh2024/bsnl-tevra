package org.apache.poi.hssf.record;

import java.util.function.Supplier;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class PageBreakRecord$$ExternalSyntheticLambda0 implements Supplier {
    public final /* synthetic */ PageBreakRecord f$0;

    public /* synthetic */ PageBreakRecord$$ExternalSyntheticLambda0(PageBreakRecord pageBreakRecord) {
        this.f$0 = pageBreakRecord;
    }

    public final Object get() {
        return Integer.valueOf(this.f$0.getNumBreaks());
    }
}
