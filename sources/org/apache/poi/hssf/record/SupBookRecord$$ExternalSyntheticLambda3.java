package org.apache.poi.hssf.record;

import java.util.function.Supplier;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class SupBookRecord$$ExternalSyntheticLambda3 implements Supplier {
    public final /* synthetic */ SupBookRecord f$0;

    public /* synthetic */ SupBookRecord$$ExternalSyntheticLambda3(SupBookRecord supBookRecord) {
        this.f$0 = supBookRecord;
    }

    public final Object get() {
        return Short.valueOf(this.f$0.getNumberOfSheets());
    }
}
