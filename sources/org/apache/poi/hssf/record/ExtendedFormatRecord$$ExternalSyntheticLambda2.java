package org.apache.poi.hssf.record;

import java.util.function.Supplier;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class ExtendedFormatRecord$$ExternalSyntheticLambda2 implements Supplier {
    public final /* synthetic */ ExtendedFormatRecord f$0;

    public /* synthetic */ ExtendedFormatRecord$$ExternalSyntheticLambda2(ExtendedFormatRecord extendedFormatRecord) {
        this.f$0 = extendedFormatRecord;
    }

    public final Object get() {
        return Short.valueOf(this.f$0.getFontIndex());
    }
}
