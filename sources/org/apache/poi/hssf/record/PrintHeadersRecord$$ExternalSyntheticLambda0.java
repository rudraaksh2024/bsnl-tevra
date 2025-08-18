package org.apache.poi.hssf.record;

import java.util.function.Supplier;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class PrintHeadersRecord$$ExternalSyntheticLambda0 implements Supplier {
    public final /* synthetic */ PrintHeadersRecord f$0;

    public /* synthetic */ PrintHeadersRecord$$ExternalSyntheticLambda0(PrintHeadersRecord printHeadersRecord) {
        this.f$0 = printHeadersRecord;
    }

    public final Object get() {
        return Boolean.valueOf(this.f$0.getPrintHeaders());
    }
}
