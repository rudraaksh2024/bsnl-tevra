package org.apache.poi.hssf.record;

import java.util.function.Supplier;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class LabelRecord$$ExternalSyntheticLambda4 implements Supplier {
    public final /* synthetic */ LabelRecord f$0;

    public /* synthetic */ LabelRecord$$ExternalSyntheticLambda4(LabelRecord labelRecord) {
        this.f$0 = labelRecord;
    }

    public final Object get() {
        return Boolean.valueOf(this.f$0.isUnCompressedUnicode());
    }
}
