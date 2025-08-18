package org.apache.poi.hssf.record;

import java.util.List;
import java.util.function.Consumer;
import org.apache.poi.hssf.record.ExternSheetRecord;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class ExternSheetRecord$$ExternalSyntheticLambda1 implements Consumer {
    public final /* synthetic */ List f$0;

    public /* synthetic */ ExternSheetRecord$$ExternalSyntheticLambda1(List list) {
        this.f$0 = list;
    }

    public final void accept(Object obj) {
        this.f$0.add((ExternSheetRecord.RefSubRecord) obj);
    }
}
