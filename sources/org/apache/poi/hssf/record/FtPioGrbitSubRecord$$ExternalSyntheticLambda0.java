package org.apache.poi.hssf.record;

import java.util.function.Supplier;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class FtPioGrbitSubRecord$$ExternalSyntheticLambda0 implements Supplier {
    public final /* synthetic */ FtPioGrbitSubRecord f$0;

    public /* synthetic */ FtPioGrbitSubRecord$$ExternalSyntheticLambda0(FtPioGrbitSubRecord ftPioGrbitSubRecord) {
        this.f$0 = ftPioGrbitSubRecord;
    }

    public final Object get() {
        return Short.valueOf(this.f$0.getFlags());
    }
}
