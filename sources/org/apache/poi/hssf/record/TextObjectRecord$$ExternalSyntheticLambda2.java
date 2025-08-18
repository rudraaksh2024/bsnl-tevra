package org.apache.poi.hssf.record;

import java.util.function.Supplier;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class TextObjectRecord$$ExternalSyntheticLambda2 implements Supplier {
    public final /* synthetic */ TextObjectRecord f$0;

    public /* synthetic */ TextObjectRecord$$ExternalSyntheticLambda2(TextObjectRecord textObjectRecord) {
        this.f$0 = textObjectRecord;
    }

    public final Object get() {
        return Boolean.valueOf(this.f$0.isTextLocked());
    }
}
