package org.apache.poi.hssf.record;

import java.util.function.Supplier;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class PrintSetupRecord$$ExternalSyntheticLambda8 implements Supplier {
    public final /* synthetic */ PrintSetupRecord f$0;

    public /* synthetic */ PrintSetupRecord$$ExternalSyntheticLambda8(PrintSetupRecord printSetupRecord) {
        this.f$0 = printSetupRecord;
    }

    public final Object get() {
        return Short.valueOf(this.f$0.getHResolution());
    }
}
