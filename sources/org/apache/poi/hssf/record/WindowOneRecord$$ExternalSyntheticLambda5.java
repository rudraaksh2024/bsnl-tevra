package org.apache.poi.hssf.record;

import java.util.function.Supplier;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class WindowOneRecord$$ExternalSyntheticLambda5 implements Supplier {
    public final /* synthetic */ WindowOneRecord f$0;

    public /* synthetic */ WindowOneRecord$$ExternalSyntheticLambda5(WindowOneRecord windowOneRecord) {
        this.f$0 = windowOneRecord;
    }

    public final Object get() {
        return Integer.valueOf(this.f$0.getFirstVisibleTab());
    }
}
