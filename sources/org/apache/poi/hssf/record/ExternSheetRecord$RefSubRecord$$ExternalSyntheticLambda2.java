package org.apache.poi.hssf.record;

import java.util.function.Supplier;
import org.apache.poi.hssf.record.ExternSheetRecord;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class ExternSheetRecord$RefSubRecord$$ExternalSyntheticLambda2 implements Supplier {
    public final /* synthetic */ ExternSheetRecord.RefSubRecord f$0;

    public /* synthetic */ ExternSheetRecord$RefSubRecord$$ExternalSyntheticLambda2(ExternSheetRecord.RefSubRecord refSubRecord) {
        this.f$0 = refSubRecord;
    }

    public final Object get() {
        return Integer.valueOf(this.f$0.getLastSheetIndex());
    }
}
