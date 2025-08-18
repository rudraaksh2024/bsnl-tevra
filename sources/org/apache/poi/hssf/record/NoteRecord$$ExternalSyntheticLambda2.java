package org.apache.poi.hssf.record;

import java.util.function.Supplier;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class NoteRecord$$ExternalSyntheticLambda2 implements Supplier {
    public final /* synthetic */ NoteRecord f$0;

    public /* synthetic */ NoteRecord$$ExternalSyntheticLambda2(NoteRecord noteRecord) {
        this.f$0 = noteRecord;
    }

    public final Object get() {
        return Short.valueOf(this.f$0.getFlags());
    }
}
