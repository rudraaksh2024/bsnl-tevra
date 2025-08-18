package org.apache.poi.hssf.record;

import java.util.function.Supplier;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class PaneRecord$$ExternalSyntheticLambda2 implements Supplier {
    public final /* synthetic */ PaneRecord f$0;

    public /* synthetic */ PaneRecord$$ExternalSyntheticLambda2(PaneRecord paneRecord) {
        this.f$0 = paneRecord;
    }

    public final Object get() {
        return Short.valueOf(this.f$0.getTopRow());
    }
}
