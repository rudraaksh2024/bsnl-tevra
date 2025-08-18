package org.apache.poi.hssf.record.common;

import java.util.function.Supplier;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class FtrHeader$$ExternalSyntheticLambda1 implements Supplier {
    public final /* synthetic */ FtrHeader f$0;

    public /* synthetic */ FtrHeader$$ExternalSyntheticLambda1(FtrHeader ftrHeader) {
        this.f$0 = ftrHeader;
    }

    public final Object get() {
        return Short.valueOf(this.f$0.getGrbitFrt());
    }
}
